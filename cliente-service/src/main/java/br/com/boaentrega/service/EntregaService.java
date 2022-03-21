/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.boaentrega.service;

import br.com.boaentrega.dto.AtualizaEntregaDTO;
import br.com.boaentrega.dto.BaixaEstoqueDTO;
import br.com.boaentrega.dto.DirectionsDTO;
import br.com.boaentrega.dto.FinalizaEntregaDTO;
import br.com.boaentrega.dto.RomaneioEntregaDTO;
import br.com.boaentrega.message.EntregaSendMessage;
import br.com.boaentrega.model.Cliente;
import br.com.boaentrega.model.Deposito;
import br.com.boaentrega.model.Entrega;
import br.com.boaentrega.model.Fornecedor;
import br.com.boaentrega.model.Mercadoria;
import br.com.boaentrega.model.RomaneioEntrega;
import br.com.boaentrega.model.dominio.AndamentoEntrega;
import br.com.boaentrega.model.dominio.DescricaoFinalEntrega;
import br.com.boaentrega.model.dominio.EstadoTerminoEntrega;
import br.com.boaentrega.repository.ClienteRepository;
import br.com.boaentrega.repository.DepositoRepository;
import br.com.boaentrega.repository.EntregaRepository;
import br.com.boaentrega.repository.FornecedorRepository;
import br.com.boaentrega.repository.MercadoriaRepository;
import br.com.boaentrega.repository.ConsultaRomaneioRepository;
import br.com.boaentrega.repository.RomaneioEntregaRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.Random;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 *
 * @author Eduardo Santos
 */
@Service
public class EntregaService {

    private final EntregaRepository entregaRepository;
    private final FornecedorRepository fornecedorRepository;
    private final ClienteRepository clienteRepository;
    private final MercadoriaRepository mercadoriaRepository;
    private final DepositoRepository depositoRepository;
    private final ConsultaRomaneioRepository consultaEntregaRepository;
    private final RomaneioEntregaRepository romaneioEntregaRepository;
    private final EntregaSendMessage entregaSendMessage;

    @Value("${cadastro.googleapi.key}")
    private String googleApiKey;

    @Autowired
    public EntregaService(EntregaRepository entregaRepository,
            FornecedorRepository fornecedorRepository,
            ClienteRepository clienteRepository,
            MercadoriaRepository mercadoriaRepository,
            DepositoRepository depositoRepository,
            ConsultaRomaneioRepository consultaEntregaRepository,
            RomaneioEntregaRepository romaneioEntregaRepository,
            EntregaSendMessage entregaSendMessage) {
        this.entregaRepository = entregaRepository;
        this.fornecedorRepository = fornecedorRepository;
        this.clienteRepository = clienteRepository;
        this.mercadoriaRepository = mercadoriaRepository;
        this.depositoRepository = depositoRepository;
        this.consultaEntregaRepository = consultaEntregaRepository;
        this.romaneioEntregaRepository = romaneioEntregaRepository;
        this.entregaSendMessage = entregaSendMessage;
    }

    public Entrega inserirNovaEntrega(Entrega entrega) {
        return entregaRepository.save(entrega);
    }

    public RomaneioEntrega inserirMercadoriaRomaneio(RomaneioEntrega romaneio) {
        return romaneioEntregaRepository.save(romaneio);
    }

    public Entrega atualizarAndamentoEntrega(Long id, AtualizaEntregaDTO entrega) {
        Date date = new Date();
        var entregaAtual = entregaRepository.findById(id);

        if (!entregaAtual.get().getStatus().contains("FINALIZADA")) {
            entregaAtual.get().setCodUltimoDeposito(entrega.getCodDeposito());
            entregaAtual.get().setStatus(AndamentoEntrega.obterAndamentoPorValor(entrega.getStatus()).toString());
            entregaAtual.get().setDataAtualizacao(date);
            var entregaAtualizada = entregaRepository.save(entregaAtual.get());
            return entregaAtualizada;
        } else {
            return null;
        }
    }

    public void finalizarEntregar(FinalizaEntregaDTO finalizaEntrega) {
        Date date = new Date();
        var entrega = entregaRepository.findById(finalizaEntrega.getIdEntrega());
        entrega.get().setDataEntrega(date);
        entrega.get().setStatus(AndamentoEntrega.obterAndamentoPorValor(3l).toString());

        //enviado msg ao serviço de gestao. 
        RomaneioEntregaDTO romaneio = new RomaneioEntregaDTO();
        romaneio.setCliente(entrega.get().getCliente().getName());
        romaneio.setNumEntrega(entrega.get().getNumeroEntrega());
        romaneio.setValorFinal(EstadoTerminoEntrega.obterTerminoPorValor(finalizaEntrega.getStatusFinal()).toString());
        romaneio.setDescricao(DescricaoFinalEntrega.obterAndamentoPorValor(finalizaEntrega.getValorDescricao()).toString());

        entregaSendMessage.sendMessageCliente(romaneio);

        entregaRepository.save(entrega.get());
    }

    public List<RomaneioEntregaDTO> buscarRomaneioPorId(Long id) {
        return consultaEntregaRepository.buscarRomaneioEntregaPorId(id);
    }

    public Optional<Mercadoria> buscarMercadoriaPorId(Long id) {
        return mercadoriaRepository.findById(id);
    }

    public Optional<Entrega> buscarEntregaPorId(Long id) {
        return entregaRepository.findById(id);
    }

    public Optional<Cliente> buscarClientePorId(Long id) {
        return clienteRepository.findById(id);
    }

    public Optional<Fornecedor> buscarFornecedorPorId(Long id) {
        return fornecedorRepository.findById(id);
    }

    public Optional<Deposito> buscarDepositoPorId(Long id) {
        return depositoRepository.findById(id);
    }

    public String gerarNumeroEntrega() {
        String numeroEntrega = null;
        Random random = new Random();
        int numero = random.nextInt(9000);
        Date date = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("YYYY");
        String anoAtual = formato.format(date);
        numeroEntrega = anoAtual + "-" + numero;
        return numeroEntrega;
    }

    public HttpResponse calcularRota(String origem, String destino) {

        var client = HttpClient.newHttpClient();
        HttpResponse<String> response = null;
        try {
            var request = HttpRequest.newBuilder(
                    URI.create(
                            "https://maps.googleapis.com/maps/api/directions/json?origin="
                            + origem + "&destination=" + destino
                            + "&key=" + googleApiKey))
                    .header("accept", "application/json")
                    .build();

            response = client.send(request, BodyHandlers.ofString());

            System.out.println(response.body());

            return response;
        } catch (Exception e) {
            e.getMessage();
        }
        return response;
    }

    public void enviarBaixaEstoque(BaixaEstoqueDTO baixaEstoque) {
        entregaSendMessage.sendMessageBaixaEstoque(baixaEstoque);
    }

}

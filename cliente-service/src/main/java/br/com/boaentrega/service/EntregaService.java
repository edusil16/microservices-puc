/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.boaentrega.service;

import br.com.boaentrega.dto.AtualizaEntregaDTO;
import br.com.boaentrega.dto.RomaneioEntregaDTO;
import br.com.boaentrega.message.EntregaSendMessage;
import br.com.boaentrega.model.Cliente;
import br.com.boaentrega.model.Deposito;
import br.com.boaentrega.model.Entrega;
import br.com.boaentrega.model.Fornecedor;
import br.com.boaentrega.model.Mercadoria;
import br.com.boaentrega.model.RomaneioEntrega;
import br.com.boaentrega.model.dominio.AndamentoEntrega;
import br.com.boaentrega.repository.ClienteRepository;
import br.com.boaentrega.repository.DepositoRepository;
import br.com.boaentrega.repository.EntregaRepository;
import br.com.boaentrega.repository.FornecedorRepository;
import br.com.boaentrega.repository.MercadoriaRepository;
import br.com.boaentrega.repository.ConsultaRomaneioRepository;
import br.com.boaentrega.repository.RomaneioEntregaRepository;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
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
        entregaAtual.get().setCodUltimoDeposito(entrega.getCodDeposito());
        entregaAtual.get().setStatus(AndamentoEntrega.obterPerfilPorValor(entrega.getStatus()).toString());
        entregaAtual.get().setDataAtualizacao(date);
        var entregaAtualizada = entregaRepository.save(entregaAtual.get());
        return entregaAtualizada;
    }

    public void finalizarEntregar(Long id) {
        Date date = new Date();
        var entrega = entregaRepository.findById(id);
        entrega.get().setDataEntrega(date);
        entrega.get().setStatus(AndamentoEntrega.obterPerfilPorValor(3l).toString());
        
        //enviado msg ao serviço de gestao. 
        RomaneioEntregaDTO romaneio = new RomaneioEntregaDTO();
        
        entregaSendMessage.sendMessageCliente(romaneio);
        
        
        
        entregaRepository.save(entrega.get());
    }

    public List<RomaneioEntregaDTO> buscarEntregaPorId(Long id) {
        return consultaEntregaRepository.buscarRomaneioEntregaPorId(id);
    }

    public Optional<Mercadoria> buscarMercadoriaPorId(Long id) {
        return mercadoriaRepository.findById(id);
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
}

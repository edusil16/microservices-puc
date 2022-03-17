/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.boaentrega.repository;

import br.com.boaentrega.dto.RomaneioEntregaDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Eduardo Santos
 */
@Repository
public class ConsultaRomaneioRepository {

    JdbcTemplate jdbc;

    @Autowired
    public ConsultaRomaneioRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<RomaneioEntregaDTO> buscarRomaneioEntregaPorId(Long idEntrega) {
        var retorno = jdbc.query("select ent.cod_primeiro_deposito, c.nome_cliente, c.endereco, ent.dt_criacao, ent.num_entrega, r.quantidade, mer.descricao from tb_entregas ent\n"
                + "inner join tb_romaneio r on ent.id_entrega = r.id_entrega\n"
                + "inner join tb_mercadoria mer on r.id_mercadoria = mer.id_mercadoria\n"
                + "inner join tb_cliente c on c.id_cliente = ent.id_cliente\n"
                + "where ent.id_entrega = ?", 
                new Object[]{idEntrega}
                , new RowMapper<RomaneioEntregaDTO>() {
            @Override
            public RomaneioEntregaDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
                RomaneioEntregaDTO entrega = new RomaneioEntregaDTO();
                entrega.setCodDeposito(rs.getString("cod_primeiro_deposito"));
                entrega.setCliente(rs.getString("nome_cliente"));
                entrega.setEndereco(rs.getString("endereco"));
                entrega.setDtCriacao(rs.getString("dt_criacao"));
                entrega.setNumEntrega(rs.getString("num_entrega"));
                entrega.setQuantidade(Long.parseLong(rs.getString("quantidade")));
                entrega.setDescricao(rs.getString("descricao"));

                return entrega;
            }
        });

        return retorno;
    }
}

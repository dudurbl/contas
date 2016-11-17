package contas.controle;

import contas.modelo.Contas;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RepositorioContas {

    public static void inserir(Contas contas) throws Exception {
        Connection c = Conexao.getConexao();
        
        /*String sqlProximoCodigo = "select coalesce(max(codigo), 0) + 1 from produtos";
        PreparedStatement psCodigo = c.prepareStatement(sqlProximoCodigo);
        ResultSet rs = psCodigo.executeQuery();
        rs.next();
        int proximoCodigo = rs.getInt(1);*/
        
        String sql = "insert into  contas (idconta,descricao,valor, data_vencimento) values (?, ?, ?, ?);";
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setInt(1, contas.getIdconta() );
        ps.setString(2, contas.getDescricao());
        ps.setDouble(3, contas.getValor());
        
        ps.setDate(4, new java.sql.Date(contas.getData_vencimento().getTime()));
        ps.execute();
    }
/*
    public static void atualizar(Contas conta) throws Exception {
        Connection c = Conexao.getConexao();
        String sql = "update produtos set descricao=? where codigo=?";
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setString(1, conta.getDecricao());
        ps.setInt(2, conta.getCodigo());
        ps.execute();
    }
    
    public static void excluir(Contas conta) throws Exception {
        excluir(conta.getCodigo());
    }
    
    public static void excluir(Integer codigo) throws Exception {
        Connection c = Conexao.getConexao();
        String sql = "delete produtos where codigo=?";
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setInt(1, codigo);
        ps.execute();
    }
*/
    public static List<Contas> getContas() throws Exception {
        List<Contas> contas = new ArrayList<>();
        Connection c = Conexao.getConexao();
        String sql = "select idconta, descricao, valor, data_vencimento, data_pagamento from contas";
        PreparedStatement ps = c.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
            Contas produto = 
                    new Contas(rs.getInt("idconta"), rs.getString("descricao"), rs.getDouble("valor"), rs.getDate("data_vencimento"), rs.getDate("data_pagamento"));
            contas.add(produto);
        }
        return contas;
    }
    /*
    public static Contas getConta(Integer codigo) throws Exception {
        Contas conta = null;
        Connection c = Conexao.getConexao();
        String sql = "select codigo, descricao from produtos where codigo=?";
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setInt(1, codigo);
        ResultSet rs = ps.executeQuery();
        if(rs.next()) {
            conta = 
                    new Contas(rs.getInt("codigo"), rs.getString("descricao"));
        }
        return conta;
    }*/
}

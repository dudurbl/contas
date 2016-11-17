package contas.controle;

import contas.modelo.Contas;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class RepositorioContas {

    public static void inserir(Contas contas) throws Exception {
        Connection c = Conexao.getConexao();
        
        String sqlProximoCodigo = "select coalesce(max(idconta), 0) + 1 from contas";
        PreparedStatement psCodigo = c.prepareStatement(sqlProximoCodigo);
        ResultSet rs = psCodigo.executeQuery();
        rs.next();
        int proximoCodigo = rs.getInt(1);
        
        String sql = "insert into  contas (idconta,descricao,valor, data_vencimento) values (?, ?, ?, ?);";
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setInt(1, proximoCodigo );
        ps.setString(2, contas.getDescricao());
        ps.setDouble(3, contas.getValor());
        ps.setDate(4, new java.sql.Date(contas.getData_vencimento().getTime()));
        ps.execute();
    }

    public static void atualizar(Contas conta) throws Exception {
        Connection c = Conexao.getConexao();
        String sql = "update contas set descricao=?, valor=?, data_vencimento=? where codigo=?";
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setString(1, conta.getDescricao());
        ps.setDouble(2, conta.getValor());
        ps.setDate(3, new java.sql.Date(conta.getData_vencimento().getTime()));
        ps.execute();
    }
    

    
    public static void excluir(Integer codigo) throws Exception {
        Connection c = Conexao.getConexao();
        String sql = "delete from contas where idconta=?";
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setInt(1, codigo);
        ps.execute();
    }

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
    
    public static List<Contas> getContasPagas() throws Exception {
        List<Contas> contas = new ArrayList<>();
        Connection c = Conexao.getConexao();
        String sql = "select idconta, descricao, valor, data_vencimento, data_pagamento from contas where data_pagamento is not null";
        PreparedStatement ps = c.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
            Contas produto = 
                    new Contas(rs.getInt("idconta"), rs.getString("descricao"), rs.getDouble("valor"), rs.getDate("data_vencimento"), rs.getDate("data_pagamento"));
            contas.add(produto);
        }
        return contas;
    }
    
     public static List<Contas> getContasAPagar() throws Exception {
        List<Contas> contas = new ArrayList<>();
        Connection c = Conexao.getConexao();
        String sql = "select idconta, descricao, valor, data_vencimento, data_pagamento from contas where data_pagamento is null";
        PreparedStatement ps = c.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
            Contas produto = 
                    new Contas(rs.getInt("idconta"), rs.getString("descricao"), rs.getDouble("valor"), rs.getDate("data_vencimento"), rs.getDate("data_pagamento"));
            contas.add(produto);
        }
        return contas;
    }
    
    public static void pagarConta(Integer codigo) throws Exception{
        Connection c = Conexao.getConexao();
        String sql = "update contas set data_pagamento=? where idconta=?";
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setDate(1, new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        ps.setInt(2, codigo);
        ps.execute();
        
    }
    
    public static Contas getConta(Integer codigo) throws Exception {
        Contas conta = null;
        Connection c = Conexao.getConexao();
        String sql = "select idconta, descricao, valor, data_vencimento, data_pagamento from contas where idconta=?";
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setInt(1, codigo);
        ResultSet rs = ps.executeQuery();
        if(rs.next()) {
            conta = new Contas(rs.getInt("idconta"), rs.getString("descricao"), rs.getDouble("valor"), rs.getDate("data_vencimento"), rs.getDate("data_pagamento"));
        }
        return conta;
    }
}

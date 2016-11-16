package contas.controle;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {

    public static Connection getConexao() throws Exception {
        Class.forName("org.hsqldb.jdbcDriver");
        Connection c = DriverManager.getConnection(
                "jdbc:hsqldb:hsql://127.0.0.1/data", "sa", "");
        return c;
    }
    
}
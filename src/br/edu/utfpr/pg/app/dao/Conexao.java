
package br.edu.utfpr.pg.app.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Vinicius 
 * @UTFPR
 */

public class Conexao {

    private static Connection conn;
    private final String driver;
    private final String usuario;
    private final String senha;
    private final String url;

    public Conexao() {
        
        this.driver = "com.mysql.jdbc.Driver";
        this.url = "jdbc:mysql://localhost:3306/basedadosaula";
        this.usuario = "root";
        this.senha = "";
    }

    public Connection conectar() throws ClassNotFoundException, SQLException {
        
        this.conn = null;
        Class.forName(driver);
        this.conn = DriverManager.getConnection(url, usuario, senha);        
        
        return this.conn;
    }
}


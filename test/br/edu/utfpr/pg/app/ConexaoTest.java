
package br.edu.utfpr.pg.app;

import br.edu.utfpr.pg.app.dao.Conexao;
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Vinicius
 * @UTFPR
 */

public class ConexaoTest {

    private Conexao conexao;
    private Connection conn;
    
    @Before
    public void setUp() {
    
        this.conexao = new Conexao();
    }

    @After
    public void tearDown() throws ClassNotFoundException, SQLException {
    
        conn.close();
    }

    @Test
    public void conectarTest() throws ClassNotFoundException, SQLException {
    
        this.conn = conexao.conectar();        
        assertNotNull(this.conn);
    }
}
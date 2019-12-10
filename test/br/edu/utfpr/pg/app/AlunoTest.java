
package br.edu.utfpr.pg.app;

import br.edu.utfpr.pg.app.dao.AlunoDAO;
import br.edu.utfpr.pg.app.entity.Aluno;
import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 * @author Vinicius
 * @UTFPR
 */

public class AlunoTest {
    
    private Aluno aluno;
    
    @Before
    public void setUp() {
        
        this.aluno = new Aluno();
        this.aluno.setRa(1234);
        this.aluno.setNome("João da Silva");
        this.aluno.setCurso("ADS");
        this.aluno.setPeriodo(2);
        this.aluno.setCoeficiente(0.87);
        this.aluno.setSituacao("Regular");
    }
    
    @After
    public void tearDown() throws ClassNotFoundException, SQLException {
        
        new AlunoDAO().excluirCadastroAluno(this.aluno);
        this.aluno = null;
    }

    @Test
    public void cadastrarAlunoTest() throws ClassNotFoundException, SQLException {
        
        assertTrue(new AlunoDAO().cadastrarAluno(this.aluno));
    }
    
    @Test
    public void atualizarCadastroAlunoTest() throws ClassNotFoundException, SQLException {
        
        AlunoDAO alunoDAO = new AlunoDAO();
        alunoDAO.cadastrarAluno(this.aluno);
        
        int raConsulta = 1234;
        
        Aluno alunoAtualizado = alunoDAO.consultarCadastroAluno(raConsulta);
        
        alunoAtualizado.setNome("João da Silva Jr");
        alunoAtualizado.setCurso("Bacharelado em Ciência da Computação");
        alunoAtualizado.setPeriodo(1);
        alunoAtualizado.setCoeficiente(0.90);
        alunoAtualizado.setSituacao("Desistente");
        
        assertTrue(alunoDAO.atualizarCadastroAluno(alunoAtualizado));
    }
    
    @Test
    public void excluirCadastroAlunoTest() throws ClassNotFoundException, SQLException {
        
        AlunoDAO alunoDAO = new AlunoDAO();
        alunoDAO.cadastrarAluno(this.aluno);
        
        assertTrue(alunoDAO.excluirCadastroAluno(this.aluno));
    }
    
    @Test
    public void consultarCadastroAlunoTest() throws ClassNotFoundException, SQLException {
        
        AlunoDAO alunoDAO = new AlunoDAO();
        alunoDAO.cadastrarAluno(this.aluno);
        
        Aluno alunoConsultado = alunoDAO.consultarCadastroAluno(this.aluno.getRa());
        
        assertEquals(this.aluno.getRa(),          alunoConsultado.getRa());
        assertEquals(this.aluno.getNome(),        alunoConsultado.getNome());
        assertEquals(this.aluno.getCurso(),       alunoConsultado.getCurso());
        assertEquals(this.aluno.getPeriodo(),     alunoConsultado.getPeriodo());
        assertEquals(this.aluno.getCoeficiente(), alunoConsultado.getCoeficiente());
        assertEquals(this.aluno.getSituacao(),    alunoConsultado.getSituacao());
    }
    
    @Test
    public void listarAlunosTest() throws ClassNotFoundException, SQLException {
        
        AlunoDAO alunoDAO = new AlunoDAO();
        alunoDAO.cadastrarAluno(this.aluno);
        
        ArrayList<Aluno> alunos = alunoDAO.listarAlunos();
        
        assertTrue(alunos.size() > 0);
        assertTrue(alunos.get(alunos.size() - 1).getRa().equals(this.aluno.getRa()));
    }
}

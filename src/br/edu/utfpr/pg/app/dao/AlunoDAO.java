
package br.edu.utfpr.pg.app.dao;

import br.edu.utfpr.pg.app.dao.Conexao;
import br.edu.utfpr.pg.app.entity.Aluno;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * @author Vinicius
 * @UTFPR
 */

public class AlunoDAO {
    
    public boolean cadastrarAluno(Aluno aluno) throws ClassNotFoundException, SQLException {

        Connection conn = null;
        PreparedStatement stmt = null;

        try {

            Conexao conexao = new Conexao();
            conn = conexao.conectar();

            stmt = conn.prepareStatement("INSERT INTO aluno(ra, nome, curso, periodo, coeficiente, situacao) VALUES(?, ?, ?, ?, ?, ?)");
            stmt.setInt(1, aluno.getRa());
            stmt.setString(2, aluno.getNome());
            stmt.setString(3, aluno.getCurso());
            stmt.setInt(4, aluno.getPeriodo());
            stmt.setDouble(5, aluno.getCoeficiente());
            stmt.setString(6, aluno.getSituacao());

            stmt.execute();
            return true;

        } finally {

            if ((stmt != null) && !stmt.isClosed()) {
                stmt.close();
            }
            if ((conn != null) && !conn.isClosed()) {
                conn.close();
            }
        }
    }

    public boolean atualizarCadastroAluno(Aluno aluno) throws ClassNotFoundException, SQLException {

        Connection conn = null;
        PreparedStatement stmt = null;

        try {

            Conexao conexao = new Conexao();
            conn = conexao.conectar();

            stmt = conn.prepareStatement("UPDATE aluno SET nome = ?, curso = ?, periodo = ?, coeficiente = ?, situacao = ? WHERE ra = ?");
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getCurso());
            stmt.setInt(3, aluno.getPeriodo());
            stmt.setDouble(4, aluno.getCoeficiente());
            stmt.setString(5, aluno.getSituacao());
            stmt.setInt(6, aluno.getRa());

            stmt.execute();
            return true;

        } finally {

            if ((stmt != null) && !stmt.isClosed()) {
                stmt.close();
            }
            if ((conn != null) && !conn.isClosed()) {
                conn.close();
            }
        }
    }

    public boolean excluirCadastroAluno(Aluno aluno) throws ClassNotFoundException, SQLException {

        Connection conn = null;
        PreparedStatement stmt = null;

        try {

            Conexao conexao = new Conexao();
            conn = conexao.conectar();

            stmt = conn.prepareStatement("DELETE FROM aluno WHERE aluno.ra = ?");
            stmt.setInt(1, aluno.getRa());

            stmt.execute();
            return true;

        } finally {

            if ((stmt != null) && !stmt.isClosed()) {
                stmt.close();
            }
            if ((conn != null) && !conn.isClosed()) {
                conn.close();
            }
        }
    }

    public Aluno consultarCadastroAluno(int ra) throws ClassNotFoundException, SQLException {

        String sql = "SELECT * FROM aluno WHERE aluno.ra = " + ra;

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        Aluno aluno = null;

        try {

            Conexao conexao = new Conexao();
            conn = conexao.conectar();

            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            if (rs.next()) {

                aluno = new Aluno();

                aluno.setRa(rs.getInt("ra"));
                aluno.setNome(rs.getString("nome"));
                aluno.setCurso(rs.getString("curso"));
                aluno.setPeriodo(rs.getInt("periodo"));
                aluno.setCoeficiente(rs.getDouble("coeficiente"));
                aluno.setSituacao(rs.getString("situacao"));
            }

            return aluno;

        } finally {

            if ((rs != null) && !rs.isClosed()) {
                rs.close();
            }
            if ((stmt != null) && !stmt.isClosed()) {
                stmt.close();
            }
            if ((conn != null) && !conn.isClosed()) {
                conn.close();
            }
        }
    }

    public ArrayList<Aluno> listarAlunos() throws ClassNotFoundException, SQLException {

        String sql = "SELECT * FROM aluno";

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<Aluno> alunos = new ArrayList<>();

        try {

            Conexao conexao = new Conexao();
            conn = conexao.conectar();

            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {

                Aluno aluno = new Aluno();

                aluno.setRa(rs.getInt("ra"));
                aluno.setNome(rs.getString("nome"));
                aluno.setCurso(rs.getString("curso"));
                aluno.setPeriodo(rs.getInt("periodo"));
                aluno.setCoeficiente(rs.getDouble("coeficiente"));
                aluno.setSituacao(rs.getString("situacao"));

                alunos.add(aluno);
            }

            return alunos;

        } finally {

            if ((rs != null) && !rs.isClosed()) {
                rs.close();
            }
            if ((stmt != null) && !stmt.isClosed()) {
                stmt.close();
            }
            if ((conn != null) && !conn.isClosed()) {
                conn.close();
            }
        }
    }
}

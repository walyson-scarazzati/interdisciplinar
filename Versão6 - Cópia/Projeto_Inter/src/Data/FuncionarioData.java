/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import model.Funcionario;

/**
 *
 * @author MaqLab
 */
public class FuncionarioData {

    public boolean incluir(Funcionario obj) throws Exception {
        Conexao objConexao = new Conexao();
        Connection conn = null;
        PreparedStatement pstmt2 = null;
        ResultSet rs = null;

        String SQL2 = "INSERT INTO Funcionarios (funcionario_id, usuario, senha, salario, tipo) VALUES (?, ?, ?, ?, ?)";

        try {
            conn = objConexao.getConexao();
            conn.setAutoCommit(false);

            int funcionarioId = insertPessoa(conn, obj.getNome(), obj.getData_nasc(), obj.getEndereco(), obj.getTelefone(), obj.getEmail(), obj.getRG(), obj.getCpf());

            // Inserir na tabela Funcionarios
            pstmt2 = conn.prepareStatement(SQL2);
            pstmt2.setInt(1, funcionarioId);
            pstmt2.setString(2, obj.getUsuario());
            pstmt2.setString(3, obj.getSenha());
            pstmt2.setDouble(4, obj.getSalario());
            pstmt2.setInt(5, obj.getTipo());
            pstmt2.executeUpdate();

            conn.commit();
            return true;
        } catch (SQLException e) {
            if (conn != null) {
                conn.rollback();
            }
            throw new Exception("Error adding Funcionario with Dependentes: " + e.getMessage(), e);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstmt2 != null) {
                pstmt2.close();
            }
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    objConexao.closeConnection(conn);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private int insertPessoa(Connection conn, String nome, String dataNasc, String endereco, String telefone, String email, String rg, String cpf) throws SQLException, ParseException {
        String SQL = "INSERT INTO Pessoas (nome, data_nasc, endereco, telefone, email, RG, CPF) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        SimpleDateFormat originalFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = originalFormat.parse(dataNasc);
        String formattedDate = targetFormat.format(date);

        try {
            pstmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, nome);
            pstmt.setString(2, formattedDate);
            pstmt.setString(3, endereco);
            pstmt.setString(4, telefone);
            pstmt.setString(5, email);
            pstmt.setString(6, rg);
            pstmt.setString(7, cpf);
            pstmt.executeUpdate();

            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                throw new SQLException("Failed to insert into Pessoas, no ID obtained.");
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
        }
    }

    private String convertToDate(String date) throws Exception {
        SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
        return outputFormat.format(inputFormat.parse(date));
    }

    public boolean editar(Funcionario obj) throws Exception {
        Conexao objConexao = new Conexao();
        String SQL = "Update Funcionarios set tipo = ?, salario = ?, usuario = ?, senha = ? where funcionario_id= ?";
        PreparedStatement pstmt = objConexao.getConexao().prepareStatement(SQL);
        pstmt.setInt(1, obj.getTipo());
        pstmt.setDouble(2, obj.getSalario());
        pstmt.setString(3, obj.getUsuario());
        pstmt.setString(4, obj.getSenha());
        pstmt.setInt(5, obj.getId());
        int registros = pstmt.executeUpdate();
        if (registros > 0) {
            String SQL2 = "Update Pessoas set nome = ?, data_nasc = ?, endereco = ?, telefone = ?, email = ?, rg = ?, cpf = ?"
                    + " Where id = ?";
            PreparedStatement pstmt2 = objConexao.getConexao().prepareStatement(SQL2);
            pstmt2.setString(1, obj.getNome());
            pstmt2.setString(2, convertToDate(obj.getData_nasc()));
            pstmt2.setString(3, obj.getEndereco());
            pstmt2.setString(4, obj.getTelefone());
            pstmt2.setString(5, obj.getEmail());
            pstmt2.setString(6, obj.getRG());
            pstmt2.setString(7, obj.getCpf());
            pstmt2.setInt(8, obj.getId());
            int registros2 = pstmt2.executeUpdate();
            if (registros2 > 0) {
                return true;
            } else;
        }
        return false;
    }

    public boolean excluir(String cpf) throws Exception {
        Conexao objConexao = new Conexao();
        Connection conn = null;
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
        PreparedStatement pstmt3 = null;

        try {
            conn = objConexao.getConexao();
            conn.setAutoCommit(false);
            String SQL1 = "SELECT id FROM Pessoas WHERE CPF = ?";
            pstmt1 = conn.prepareStatement(SQL1);
            pstmt1.setString(1, cpf);
            ResultSet rs = pstmt1.executeQuery();

            int id = 0;
            if (rs.next()) {
                id = rs.getInt("id"); 
            } else {
                throw new SQLException("Pessoa com CPF " + cpf + " não encontrada.");
            }

            String SQL2 = "DELETE FROM Funcionarios WHERE funcionario_id = ?";
            pstmt2 = conn.prepareStatement(SQL2);
            pstmt2.setInt(1, id);
            int registros = pstmt2.executeUpdate();

            if (registros > 0) {
                String SQL3 = "DELETE FROM Pessoas WHERE id = ?";
                pstmt3 = conn.prepareStatement(SQL3);
                pstmt3.setInt(1, id);
                int registros2 = pstmt3.executeUpdate();

                if (registros2 > 0) {
                    conn.commit();
                    return true;
                } else {
                    conn.rollback();
                    return false;
                }
            } else {
                conn.rollback();
                return false;
            }

        } catch (SQLException e) {
            if (conn != null) {
                conn.rollback();
            }
            throw new Exception("Erro ao excluir funcionário com CPF " + cpf + ": " + e.getMessage(), e);
        } finally {
            if (pstmt1 != null) {
                pstmt1.close();
            }
            if (pstmt2 != null) {
                pstmt2.close();
            }
            if (pstmt3 != null) {
                pstmt3.close();
            }
            if (conn != null) {
                conn.setAutoCommit(true);
                objConexao.closeConnection(conn);
            }
        }
    }

    public Funcionario pesquisarFuncionarios(String cpf) throws Exception {
        Conexao objConexao = new Conexao();
        Funcionario obj = null;
        String SQL = "select id, nome, data_nasc, endereco, telefone, email, rg, cpf, usuario, senha, salario, tipo from Funcionarios f, Pessoas p"
                + " where p.id = f.funcionario_id and  p.cpf = ? ";
        PreparedStatement pstmt = objConexao.getConexao().prepareStatement(SQL);
        pstmt.setString(1, cpf);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            obj = new Funcionario();
            obj.setId(rs.getInt("id"));
            obj.setNome(rs.getString("nome"));
            Date dataNasc = rs.getDate("data_nasc");
            String dataNascFormatada = (dataNasc != null) ? new SimpleDateFormat("dd/MM/yyyy").format(dataNasc) : "";
            obj.setData_nasc(dataNascFormatada);
            obj.setEndereco(rs.getString("endereco"));
            obj.setTelefone(rs.getString("telefone"));
            obj.setEmail(rs.getString("email"));
            obj.setRG(rs.getString("rg"));
            obj.setCpf(rs.getString("cpf"));
            obj.setUsuario(rs.getString("usuario"));
            obj.setSenha(rs.getString("senha"));
            obj.setSalario(rs.getDouble("salario"));
            obj.setTipo(rs.getInt("tipo"));

        }
        return obj;
    }

    public Funcionario validarUsuario(String usuario, String senha) throws Exception {

        Conexao objConexao = new Conexao();
        Funcionario obj = null;
        String SQL = "Select * from Funcionarios where usuario = ? and senha = ? ";
        PreparedStatement pstmt = objConexao.getConexao().prepareStatement(SQL);
        pstmt.setString(1, usuario);
        pstmt.setString(2, senha);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            obj = new Funcionario();
            obj.setUsuario(rs.getString("usuario"));
            obj.setSenha(rs.getString("senha"));
            obj.setTipo(rs.getInt("tipo"));
        }
        return obj;

    }

    public Vector<Funcionario> carregarCombo() throws Exception {
        Vector<Funcionario> dados = new Vector<Funcionario>();
        Conexao objConexao = new Conexao();
        String SQL = "select id, nome, cpf from Pessoas p, Funcionarios f"
                + " where p.id = f.funcionario_id";
        PreparedStatement pstmt = objConexao.getConexao().prepareStatement(SQL);
        ResultSet rs = pstmt.executeQuery();
        dados.add(new Funcionario(0, "<Selecione>", "", "", "", "", "", "", "", "", 0, 0));

        while (rs.next()) {
            Funcionario obj = new Funcionario();
            obj.setId(rs.getInt("id"));
            obj.setNome(rs.getString("nome").trim());
            obj.setCpf(rs.getString("CPF"));
            dados.add(obj);
        }
        return dados;
    }

}

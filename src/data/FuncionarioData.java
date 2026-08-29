/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import extras.SenhaUtil;
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
        String sql2 = "INSERT INTO Funcionarios (funcionario_id, usuario, senha, salario, tipo) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = objConexao.getConexao()) {
            conn.setAutoCommit(false);
            try {
                int funcionarioId = insertPessoa(conn, obj.getNome(), obj.getDataNasc(), obj.getEndereco(), obj.getTelefone(), obj.getEmail(), obj.getRG(), obj.getCpf());

                try (PreparedStatement pstmt2 = conn.prepareStatement(sql2)) {
                    pstmt2.setInt(1, funcionarioId);
                    pstmt2.setString(2, obj.getUsuario());
                    pstmt2.setString(3, SenhaUtil.gerarHash(obj.getSenha()));
                    pstmt2.setDouble(4, obj.getSalario());
                    pstmt2.setInt(5, obj.getTipo());
                    pstmt2.executeUpdate();
                }

                conn.commit();
                return true;
            } catch (SQLException e) {
                conn.rollback();
                throw new Exception("Error adding Funcionario with Dependentes: " + e.getMessage(), e);
            } finally {
                conn.setAutoCommit(true);
            }
        }
    }

    private int insertPessoa(Connection conn, String nome, String dataNasc, String endereco, String telefone, String email, String rg, String cpf) throws SQLException, ParseException {
        String sql = "INSERT INTO Pessoas (nome, data_nasc, endereco, telefone, email, RG, CPF) VALUES (?, ?, ?, ?, ?, ?, ?)";
        SimpleDateFormat originalFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = originalFormat.parse(dataNasc);
        String formattedDate = targetFormat.format(date);

        try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, nome);
            pstmt.setString(2, formattedDate);
            pstmt.setString(3, endereco);
            pstmt.setString(4, telefone);
            pstmt.setString(5, email);
            pstmt.setString(6, rg);
            pstmt.setString(7, cpf);
            pstmt.executeUpdate();

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                } else {
                    throw new SQLException("Failed to insert into Pessoas, no ID obtained.");
                }
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
        String sql = "Update Funcionarios set tipo = ?, salario = ?, usuario = ?, senha = ? where funcionario_id= ?";
        try (Connection conn = objConexao.getConexao(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, obj.getTipo());
            pstmt.setDouble(2, obj.getSalario());
            pstmt.setString(3, obj.getUsuario());
            pstmt.setString(4, SenhaUtil.gerarHash(obj.getSenha()));
            pstmt.setInt(5, obj.getId());
            int registros = pstmt.executeUpdate();
            if (registros > 0) {
                String sql2 = "Update Pessoas set nome = ?, data_nasc = ?, endereco = ?, telefone = ?, email = ?, rg = ?, cpf = ?"
                        + " Where id = ?";
                try (PreparedStatement pstmt2 = conn.prepareStatement(sql2)) {
                    pstmt2.setString(1, obj.getNome());
                    pstmt2.setString(2, convertToDate(obj.getDataNasc()));
                    pstmt2.setString(3, obj.getEndereco());
                    pstmt2.setString(4, obj.getTelefone());
                    pstmt2.setString(5, obj.getEmail());
                    pstmt2.setString(6, obj.getRG());
                    pstmt2.setString(7, obj.getCpf());
                    pstmt2.setInt(8, obj.getId());
                    int registros2 = pstmt2.executeUpdate();
                    return registros2 > 0;
                }
            }
            return false;
        }
    }

    public boolean excluir(String cpf) throws Exception {
        Conexao objConexao = new Conexao();

        try (Connection conn = objConexao.getConexao()) {
            conn.setAutoCommit(false);
            try {
                int id;
                String sql1 = "SELECT id FROM Pessoas WHERE CPF = ?";
                try (PreparedStatement pstmt1 = conn.prepareStatement(sql1)) {
                    pstmt1.setString(1, cpf);
                    try (ResultSet rs = pstmt1.executeQuery()) {
                        if (rs.next()) {
                            id = rs.getInt("id");
                        } else {
                            throw new SQLException("Pessoa com CPF " + cpf + " não encontrada.");
                        }
                    }
                }

                String sql2 = "DELETE FROM Funcionarios WHERE funcionario_id = ?";
                int registros;
                try (PreparedStatement pstmt2 = conn.prepareStatement(sql2)) {
                    pstmt2.setInt(1, id);
                    registros = pstmt2.executeUpdate();
                }

                if (registros > 0) {
                    String sql3 = "DELETE FROM Pessoas WHERE id = ?";
                    try (PreparedStatement pstmt3 = conn.prepareStatement(sql3)) {
                        pstmt3.setInt(1, id);
                        int registros2 = pstmt3.executeUpdate();

                        if (registros2 > 0) {
                            conn.commit();
                            return true;
                        }
                    }
                }
                conn.rollback();
                return false;

            } catch (SQLException e) {
                conn.rollback();
                throw new Exception("Erro ao excluir funcionário com CPF " + cpf + ": " + e.getMessage(), e);
            } finally {
                conn.setAutoCommit(true);
            }
        }
    }

    public Funcionario pesquisarFuncionarios(String cpf) throws Exception {
        Conexao objConexao = new Conexao();
        Funcionario obj = null;
        String sql = "select id, nome, data_nasc, endereco, telefone, email, rg, cpf, usuario, senha, salario, tipo from Funcionarios f, Pessoas p"
                + " where p.id = f.funcionario_id and  p.cpf = ? ";
        try (Connection conn = objConexao.getConexao(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cpf);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    obj = new Funcionario();
                    obj.setId(rs.getInt("id"));
                    obj.setNome(rs.getString("nome"));
                    Date dataNasc = rs.getDate("data_nasc");
                    String dataNascFormatada = (dataNasc != null) ? new SimpleDateFormat("dd/MM/yyyy").format(dataNasc) : "";
                    obj.setDataNasc(dataNascFormatada);
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
            }
        }
        return obj;
    }

    public Funcionario validarUsuario(String usuario, String senha) throws Exception {
        Conexao objConexao = new Conexao();
        Funcionario obj = null;
        String sql = "Select * from Funcionarios where usuario = ? ";
        try (Connection conn = objConexao.getConexao(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, usuario);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String hashArmazenado = rs.getString("senha");
                    if (SenhaUtil.verificar(senha, hashArmazenado)) {
                        obj = new Funcionario();
                        obj.setUsuario(rs.getString("usuario"));
                        obj.setTipo(rs.getInt("tipo"));
                    }
                }
            }
        }
        return obj;
    }

    public Vector<Funcionario> carregarCombo() throws Exception {
        Vector<Funcionario> dados = new Vector<Funcionario>();
        Conexao objConexao = new Conexao();
        String sql = "select id, nome, cpf from Pessoas p, Funcionarios f"
                + " where p.id = f.funcionario_id";
        try (Connection conn = objConexao.getConexao(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            dados.add(new Funcionario(0, "<Selecione>", "", "", "", "", "", "", "", "", 0, 0));

            while (rs.next()) {
                Funcionario obj = new Funcionario();
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome").trim());
                obj.setCpf(rs.getString("CPF"));
                dados.add(obj);
            }
        }
        return dados;
    }

}

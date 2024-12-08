/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import model.Parentesco;

/**
 *
 * @author MaqLab
 */
public class ParentescoData {

    public boolean incluir(Parentesco obj) throws Exception {
        Conexao objConexao = new Conexao();
        String SQL = "INSERT INTO Parentescos (descricao) VALUES (?)";
        PreparedStatement pstmt = objConexao.getConexao().prepareStatement(SQL);
        pstmt.setString(1, obj.getDescricao());
        int registros = pstmt.executeUpdate();
        if (registros > 0) {

            return true;
        } else {
            return false;
        }
    }

    public Vector<Parentesco> carregarCombo() throws Exception {
        Vector<Parentesco> dados = new Vector<Parentesco>();
        Conexao objConexao = new Conexao();
        String SQL = "Select * from Parentescos order by parentesco_id";
        PreparedStatement pstmt = objConexao.getConexao().prepareStatement(SQL);
        ResultSet rs = pstmt.executeQuery();
        dados.add(new Parentesco(0, "<Selecione>"));
        while (rs.next()) {
            dados.add(new Parentesco(rs.getInt("parentesco_id"),
                    rs.getString("descricao")));
        }
        return dados;
    }

    public Vector<Parentesco> listar() throws Exception {
        Vector<Parentesco> dados = new Vector<Parentesco>();
        Conexao objConexao = new Conexao();
        String SQL = "Select * from Parentescos order by parentesco_id";
        PreparedStatement pstmt = objConexao.getConexao().prepareStatement(SQL);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            dados.add(new Parentesco(rs.getInt("parentesco_id"),
                    rs.getString("descricao")));
        }
        return dados;
    }

    public boolean atualizar(Parentesco obj) throws Exception {
        String SQL = "UPDATE Parentescos SET descricao = ? WHERE parentesco_id = ?";
        try (Connection conn = Conexao.getConexao(); // Assuming getConexao returns a valid connection
                 PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            pstmt.setString(1, obj.getDescricao());
            pstmt.setInt(2, obj.getId());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new SQLException("Erro ao atualizar dados: " + e.getMessage(), e);
        }
    }

    public boolean excluir(int id) throws Exception {
        Conexao objConexao = new Conexao();
        String SQL = "DELETE FROM Parentescos WHERE parentesco_id = ?";
        try (PreparedStatement pstmt = objConexao.getConexao().prepareStatement(SQL)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        }
    }

    public ArrayList<Parentesco> pesquisarPorDescricao(String descricao) throws Exception {
        Conexao objConexao = new Conexao();
        ArrayList<Parentesco> lista = new ArrayList<>();
        String SQL = "SELECT * FROM Parentescos WHERE descricao LIKE ?";
        try (PreparedStatement pstmt = objConexao.getConexao().prepareStatement(SQL)) {
            pstmt.setString(1, "%" + descricao + "%"); // Adds wildcards for partial matches
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(new Parentesco(rs.getInt("parentesco_id"), rs.getString("descricao")));
                }
            }
        }
        return lista;
    }

    public Parentesco pesquisar(String parentesco) throws Exception {
        Parentesco obj = null;
        String SQL;

        if (parentesco == null || parentesco.trim().isEmpty()) {
            SQL = "SELECT * FROM Parentescos";
        } else {
            SQL = "SELECT * FROM Parentescos WHERE descricao LIKE ?";
        }

        try (Connection conn = new Conexao().getConexao(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            if (parentesco != null && !parentesco.trim().isEmpty()) {
                pstmt.setString(1, "%" + parentesco + "%");
            }

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                obj = new Parentesco();
                obj.setId(rs.getInt("parentesco_id"));
                obj.setDescricao(rs.getString("descricao"));
            }
        } catch (Exception e) {
            throw new Exception("Erro ao pesquisar parentesco: " + e.getMessage(), e);
        }

        return obj;
    }

    public Parentesco pesquisarPorId(int id) throws Exception {
        Parentesco obj = null;
        String SQL = "SELECT * FROM Parentescos  WHERE parentesco_id = ?";

        try (Connection conn = new Conexao().getConexao(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    obj = new Parentesco();
                    obj.setId(rs.getInt("parentesco_id"));
                    obj.setDescricao(rs.getString("descricao"));
                }
            }
        } catch (Exception e) {
            throw new Exception("Erro ao pesquisar modalidade esportiva por ID: " + e.getMessage(), e);
        }

        return obj;
    }
}

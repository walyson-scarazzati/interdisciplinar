/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import model.Categoria;
import model.Modalidade_esportiva;

/**
 *
 * autor :
 */
public class Modalidade_esportivaData {

    public boolean incluir(Modalidade_esportiva obj) throws Exception {
        String SQL = "INSERT INTO Modalidades_Esportes (descricao, categoria_id) VALUES (?, ?)";

        try (Connection conn = Conexao.getConexao(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            pstmt.setString(1, obj.getDescricao());
            pstmt.setInt(2, obj.getCategoria().getId());

            int registros = pstmt.executeUpdate();
            return registros > 0;

        } catch (SQLException e) {
            throw new Exception("Erro ao incluir modalidade esportiva: " + e.getMessage(), e);
        }
    }

    public Modalidade_esportiva pesquisar(String descricao_esportiva) throws Exception {
        Modalidade_esportiva obj = null;
        String SQL;

        if (descricao_esportiva == null || descricao_esportiva.trim().isEmpty()) {
            SQL = "SELECT * FROM Modalidades_Esportes";
        } else {
            SQL = "SELECT * FROM Modalidades_Esportes WHERE descricao LIKE ?";
        }

        try (Connection conn = new Conexao().getConexao(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            if (descricao_esportiva != null && !descricao_esportiva.trim().isEmpty()) {
                pstmt.setString(1, "%" + descricao_esportiva + "%");
            }

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                obj = new Modalidade_esportiva();
                obj.setId(rs.getInt("id"));
                obj.setDescricao(rs.getString("descricao"));

                // Assuming Categoria is set elsewhere
                Categoria categoria = new Categoria();
                categoria.setId(rs.getInt("categoria_id"));
                obj.setCategoria(categoria);
            }
        } catch (Exception e) {
            throw new Exception("Erro ao pesquisar modalidade esportiva: " + e.getMessage(), e);
        }

        return obj;
    }

    public Modalidade_esportiva pesquisarPorId(int id) throws Exception {
        Modalidade_esportiva obj = null;
        String SQL = "SELECT m.id, m.descricao, c.id AS categoria_id, c.descricao AS categoria_descricao "
                + "FROM Modalidades_Esportes m "
                + "JOIN Categorias c ON m.categoria_id = c.id "
                + "WHERE m.id = ?";

        try (Connection conn = new Conexao().getConexao(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    obj = new Modalidade_esportiva();
                    obj.setId(rs.getInt("id"));
                    obj.setDescricao(rs.getString("descricao"));

                    Categoria categoria = new Categoria();
                    categoria.setId(rs.getInt("categoria_id"));
                    obj.setCategoria(categoria);
                }
            }
        } catch (Exception e) {
            throw new Exception("Erro ao pesquisar modalidade esportiva por ID: " + e.getMessage(), e);
        }

        return obj;
    }

    public Vector<Modalidade_esportiva> listarModalidade_esportiva() throws Exception {
        Vector<Modalidade_esportiva> dados = new Vector<>();
        String SQL = "SELECT * FROM Modalidades_Esportes ORDER BY id";

        try (Connection conn = new Conexao().getConexao(); PreparedStatement pstmt = conn.prepareStatement(SQL); ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Modalidade_esportiva obj = new Modalidade_esportiva();
                obj.setId(rs.getInt("id"));
                obj.setDescricao(rs.getString("descricao"));
                dados.add(obj);
            }
        } catch (Exception e) {
            throw new Exception("Erro ao listar modalidades esportivas: " + e.getMessage(), e);
        }

        return dados;
    }

    public boolean excluir(int id) throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = Conexao.getConexao();

            conn.setAutoCommit(false);

            String removeDependentsSQL = "DELETE FROM Categoria_modalidades WHERE modalidade_id = ?";
            pstmt = conn.prepareStatement(removeDependentsSQL);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();

            String deleteSQL = "DELETE FROM Modalidades_Esportes WHERE id = ?";
            pstmt = conn.prepareStatement(deleteSQL);
            pstmt.setInt(1, id);
            int registros = pstmt.executeUpdate();

            if (registros > 0) {
                conn.commit();
                return true;
            } else {
                conn.rollback();
                return false;
            }
        } catch (Exception e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException se) {

                }
            }
            throw new Exception("Erro ao excluir modalidade esportiva: " + e.getMessage(), e);
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.setAutoCommit(true);
                conn.close();
            }
        }
    }

    public boolean editar(Modalidade_esportiva obj) throws Exception {
        String SQL = "UPDATE Modalidades_Esportes SET descricao = ?, categoria_id = ? WHERE id = ?";
        try (Connection conn = new Conexao().getConexao(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setString(1, obj.getDescricao());
            pstmt.setInt(2, obj.getCategoria().getId());
            pstmt.setInt(3, obj.getId());
            int registros = pstmt.executeUpdate();
            return registros > 0;
        } catch (Exception e) {
            throw new Exception("Erro ao editar modalidade esportiva: " + e.getMessage(), e);
        }
    }

    public Vector<Modalidade_esportiva> carregarCombo() throws Exception {
        Vector<Modalidade_esportiva> dados = new Vector<>();
        String SQL = "SELECT m.id, m.descricao, c.id AS categoria_id, c.descricao AS categoria_descricao "
                + "FROM Modalidades_Esportes m "
                + "JOIN Categorias c ON m.categoria_id = c.id "
                + "ORDER BY c.id";

        try (Connection conn = Conexao.getConexao(); PreparedStatement pstmt = conn.prepareStatement(SQL); ResultSet rs = pstmt.executeQuery()) {

            dados.add(new Modalidade_esportiva(0, "<Selecione>", null));

            while (rs.next()) {
                int id = rs.getInt("id");
                String descricao = rs.getString("descricao");

                String categoriaDescricao = rs.getString("categoria_descricao");
                Categoria categoria = new Categoria(categoriaDescricao);

                dados.add(new Modalidade_esportiva(id, descricao, categoria));
            }
        } catch (Exception e) {
            throw new Exception("Erro ao carregar modalidades esportivas para o combo: " + e.getMessage(), e);
        }

        return dados;

    }

}

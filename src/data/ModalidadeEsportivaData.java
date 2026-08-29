/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import model.Categoria;
import model.ModalidadeEsportiva;

/**
 *
 * autor :
 */
public class ModalidadeEsportivaData {

    public boolean incluir(ModalidadeEsportiva obj) throws Exception {
        String sql = "INSERT INTO Modalidades_Esportes (descricao, categoria_id) VALUES (?, ?)";

        try (Connection conn = Conexao.getConexao(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, obj.getDescricao());
            pstmt.setInt(2, obj.getCategoria().getId());

            int registros = pstmt.executeUpdate();
            return registros > 0;

        } catch (SQLException e) {
            throw new Exception("Erro ao incluir modalidade esportiva: " + e.getMessage(), e);
        }
    }

    public ModalidadeEsportiva pesquisar(String descricao_esportiva) throws Exception {
        ModalidadeEsportiva obj = null;
        String sql;

        if (descricao_esportiva == null || descricao_esportiva.trim().isEmpty()) {
            sql = "SELECT * FROM Modalidades_Esportes";
        } else {
            sql = "SELECT * FROM Modalidades_Esportes WHERE descricao LIKE ?";
        }

        try (Connection conn = new Conexao().getConexao(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            if (descricao_esportiva != null && !descricao_esportiva.trim().isEmpty()) {
                pstmt.setString(1, "%" + descricao_esportiva + "%");
            }

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    obj = new ModalidadeEsportiva();
                    obj.setId(rs.getInt("id"));
                    obj.setDescricao(rs.getString("descricao"));

                    // Assuming Categoria is set elsewhere
                    Categoria categoria = new Categoria();
                    categoria.setId(rs.getInt("categoria_id"));
                    obj.setCategoria(categoria);
                }
            }
        } catch (Exception e) {
            throw new Exception("Erro ao pesquisar modalidade esportiva: " + e.getMessage(), e);
        }

        return obj;
    }

    public ModalidadeEsportiva pesquisarPorId(int id) throws Exception {
        ModalidadeEsportiva obj = null;
        String sql = "SELECT m.id, m.descricao, c.id AS categoria_id, c.descricao AS categoria_descricao "
                + "FROM Modalidades_Esportes m "
                + "JOIN Categorias c ON m.categoria_id = c.id "
                + "WHERE m.id = ?";

        try (Connection conn = new Conexao().getConexao(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    obj = new ModalidadeEsportiva();
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

    public Vector<ModalidadeEsportiva> listarModalidadeEsportiva() throws Exception {
        Vector<ModalidadeEsportiva> dados = new Vector<>();
        String sql = "SELECT m.id, m.descricao, c.id AS categoria_id, c.descricao AS categoria_descricao "
                + "FROM Modalidades_Esportes m "
                + "JOIN Categorias c ON m.categoria_id = c.id "
                + "ORDER BY m.id";

        try (Connection conn = new Conexao().getConexao(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                ModalidadeEsportiva obj = new ModalidadeEsportiva();
                obj.setId(rs.getInt("id"));
                obj.setDescricao(rs.getString("descricao"));

                Categoria categoria = new Categoria();
                categoria.setId(rs.getInt("categoria_id"));
                categoria.setDescricao(rs.getString("categoria_descricao"));
                obj.setCategoria(categoria);

                dados.add(obj);
            }
        } catch (Exception e) {
            throw new Exception("Erro ao listar modalidades esportivas: " + e.getMessage(), e);
        }

        return dados;
    }

    public boolean excluir(int id) throws Exception {
        String sql = "DELETE FROM Modalidades_Esportes WHERE id = ?";
        try (Connection conn = Conexao.getConexao(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int registros = pstmt.executeUpdate();
            return registros > 0;
        } catch (Exception e) {
            throw new Exception("Erro ao excluir modalidade esportiva: " + e.getMessage(), e);
        }
    }

    public boolean editar(ModalidadeEsportiva obj) throws Exception {
        String sql = "UPDATE Modalidades_Esportes SET descricao = ?, categoria_id = ? WHERE id = ?";
        try (Connection conn = new Conexao().getConexao(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, obj.getDescricao());
            pstmt.setInt(2, obj.getCategoria().getId());
            pstmt.setInt(3, obj.getId());
            int registros = pstmt.executeUpdate();
            return registros > 0;
        } catch (Exception e) {
            throw new Exception("Erro ao editar modalidade esportiva: " + e.getMessage(), e);
        }
    }

    public Vector<ModalidadeEsportiva> carregarCombo() throws Exception {
        Vector<ModalidadeEsportiva> dados = new Vector<>();
        String sql = "SELECT m.id, m.descricao, c.id AS categoria_id, c.descricao AS categoria_descricao "
                + "FROM Modalidades_Esportes m "
                + "JOIN Categorias c ON m.categoria_id = c.id "
                + "ORDER BY c.id";

        try (Connection conn = Conexao.getConexao(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {

            dados.add(new ModalidadeEsportiva(0, "<Selecione>", null));

            while (rs.next()) {
                int id = rs.getInt("id");
                String descricao = rs.getString("descricao");

                String categoriaDescricao = rs.getString("categoria_descricao");
                Categoria categoria = new Categoria(categoriaDescricao);

                dados.add(new ModalidadeEsportiva(id, descricao, categoria));
            }
        } catch (Exception e) {
            throw new Exception("Erro ao carregar modalidades esportivas para o combo: " + e.getMessage(), e);
        }

        return dados;

    }

}

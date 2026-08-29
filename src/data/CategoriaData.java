/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import model.Categoria;

/**
 *
 * autor :
 */
public class CategoriaData {

    public boolean incluir(Categoria obj) throws Exception {
        Conexao objConexao = new Conexao();
        String sql = "INSERT INTO Categorias (descricao, valor) VALUES (?, ?)";
        try (Connection conn = objConexao.getConexao(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, obj.getDescricao());
            pstmt.setFloat(2, obj.getValor());
            int registros = pstmt.executeUpdate();
            return registros > 0;
        }
    }

    public Vector<Categoria> listar() throws Exception {
        Conexao objConexao = new Conexao();
        Vector<Categoria> dados = new Vector<Categoria>();
        String sql1 = "Select * from  Categorias";
        try (Connection conn = objConexao.getConexao(); PreparedStatement pstmt = conn.prepareStatement(sql1); ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Categoria obj = new Categoria();
                obj.setId(rs.getInt("id"));
                obj.setDescricao(rs.getString("descricao"));
                obj.setValor(rs.getFloat("valor"));
                dados.add(obj);
            }
        }
        return dados;
    }

    public Categoria pesquisar(String descricao) throws Exception {
        Categoria obj = null;
        String sql;
        if (descricao == null || descricao.trim().isEmpty()) {
            sql = "SELECT * FROM Categorias";
        } else {
            sql = "SELECT * FROM Categorias WHERE descricao LIKE ?";
        }

        try (Connection conn = new Conexao().getConexao(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            if (descricao != null && !descricao.trim().isEmpty()) {
                pstmt.setString(1, "%" + descricao + "%");
            }

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    obj = new Categoria();
                    obj.setId(rs.getInt("id"));
                    obj.setDescricao(rs.getString("descricao"));
                    obj.setValor(rs.getFloat("valor"));
                }
            }
        } catch (Exception e) {
            throw new Exception("Erro ao pesquisar categoria: " + e.getMessage(), e);
        }

        return obj;
    }

    public boolean excluir(int id) throws Exception {
        Conexao objConexao = new Conexao();
        String sql = "Delete from  Categorias where id = ?";
        try (Connection conn = objConexao.getConexao(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int registros = pstmt.executeUpdate();
            return registros > 0;
        }
    }

    public boolean editar(Categoria obj) throws Exception {
        Conexao objConexao = new Conexao();
        String sql = "Update  Categorias set descricao = ?, valor = ? where id = ?";
        try (Connection conn = objConexao.getConexao(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, obj.getDescricao());
            pstmt.setFloat(2, obj.getValor());
            pstmt.setInt(3, obj.getId());
            int registros = pstmt.executeUpdate();
            return registros > 0;
        }
    }

    public Vector<Categoria> carregarCombo() throws Exception {
        Vector<Categoria> dados = new Vector<Categoria>();
        Conexao objConexao = new Conexao();
        String sql = "Select * from  Categorias";
        try (Connection conn = objConexao.getConexao(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            dados.add(new Categoria(0, "<Selecione>", 0));
            while (rs.next()) {
                dados.add(new Categoria(rs.getInt("id"),
                        rs.getString("descricao"),
                        rs.getFloat("valor")));
            }
        }
        return dados;
    }

    public Categoria pesquisarPorId(int id) throws Exception {
        Categoria obj = null;
        String sql = "SELECT * FROM Categorias WHERE id = ? ";

        try (Connection conn = new Conexao().getConexao(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    obj = new Categoria();
                    obj.setId(rs.getInt("id"));
                    obj.setDescricao(rs.getString("descricao"));
                    obj.setValor(rs.getFloat("valor"));
                }
            }
        } catch (Exception e) {
            throw new Exception("Erro ao pesquisar categoria: " + e.getMessage(), e);
        }
        return obj;
    }

}

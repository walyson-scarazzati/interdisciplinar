/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

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
        String SQL = "INSERT INTO Categorias (descricao, valor) VALUES (?, ?)";
        PreparedStatement pstmt = objConexao.getConexao().prepareStatement(SQL);
        pstmt.setString(1, obj.getDescricao());
        pstmt.setFloat(2, obj.getValor());

        int registros = pstmt.executeUpdate();

        if (registros > 0) {

            return true;
        } else {
            return false;
        }
    }

    public Vector<Categoria> listar() throws Exception {
        Conexao objConexao = new Conexao();
        Vector<Categoria> dados = new Vector<Categoria>();
        String SQL1 = "Select * from  Categorias";
        PreparedStatement pstmt = objConexao.getConexao().prepareStatement(SQL1);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            Categoria obj = new Categoria();
            obj.setId(rs.getInt("id"));
            obj.setDescricao(rs.getString("descricao"));
            obj.setValor(rs.getFloat("valor"));
            boolean add = dados.add(obj);

        }
        pstmt.close();
        Conexao.getConexao().close();
        return dados;
    }

    public Categoria pesquisar(String descricao) throws Exception {
        Categoria obj = null;
        Conexao objConexao = new Conexao();
        String SQL;
        if (descricao == null || descricao.trim().isEmpty()) {
            SQL = "SELECT * FROM Categorias";
        } else {
            SQL = "SELECT * FROM Categorias WHERE descricao LIKE ?";
        }

        try (Connection conn = new Conexao().getConexao(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            if (descricao != null && !descricao.trim().isEmpty()) {
                pstmt.setString(1, "%" + descricao + "%");
            }

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                obj = new Categoria();
                obj.setId(rs.getInt("id"));
                obj.setDescricao(rs.getString("descricao"));
                obj.setValor(rs.getFloat("valor"));

            }
        } catch (Exception e) {
            throw new Exception("Erro ao pesquisar modalidade esportiva: " + e.getMessage(), e);
        }

        return obj;
    }

    public boolean excluir(int id) throws Exception {
        Conexao objConexao = new Conexao();
        String SQL = "Delete from  Categorias where id = ?";
        PreparedStatement pstmt = objConexao.getConexao().prepareStatement(SQL);
        pstmt.setInt(1, id);
        int registros = pstmt.executeUpdate();
        if (registros > 0) {

            return true;
        } else {
            return false;
        }
    }

    public boolean editar(Categoria obj) throws Exception {
        Conexao objConexao = new Conexao();
        String SQL = "Update  Categorias set descricao = ?, valor = ? where id = ?";
        PreparedStatement pstmt = objConexao.getConexao().prepareStatement(SQL);
        pstmt.setString(1, obj.getDescricao());
        pstmt.setFloat(2, obj.getValor());
        pstmt.setInt(3, obj.getId());
        int registros = pstmt.executeUpdate();
        if (registros > 0) {
            return true;
        } else {
            return false;
        }

    }

    public Vector<Categoria> carregarCombo() throws Exception {
        Vector<Categoria> dados = new Vector<Categoria>();
        Conexao objConexao = new Conexao();
        String SQL = "Select * from  Categorias";
        PreparedStatement pstmt = objConexao.getConexao().prepareStatement(SQL);
        ResultSet rs = pstmt.executeQuery();
        dados.add(new Categoria(0, "<Selecione>", 0));
        while (rs.next()) {
            dados.add(new Categoria(rs.getInt("id"),
                    rs.getString("descricao"),
                    rs.getFloat("valor")));

        }
        return dados;
    }

    public Categoria pesquisarPorId(int id) throws Exception {
        Categoria obj = null;
        Conexao objConexao = new Conexao();
        String SQL = "SELECT * FROM Categorias WHERE id = ? ";

        try (Connection conn = new Conexao().getConexao(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                obj = new Categoria();
                obj.setId(rs.getInt("id"));
                obj.setDescricao(rs.getString("descricao"));
                obj.setValor(rs.getFloat("valor"));

            }
        } catch (Exception e) {
            throw new Exception("Erro ao pesquisar modalidade esportiva: " + e.getMessage(), e);
        }
        return obj;
    }

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import model.Dependente;
import model.Parentesco;

/**
 *
 * @author MaqLab
 */
public class DependenteData {

    public boolean incluir(Dependente obj) throws Exception {
        Conexao objConexao = new Conexao();
        String SQL = "Insert into Pessoas values (?,?,?,?,?,?,?)";
        PreparedStatement pstmt = objConexao.getConexao().prepareStatement(SQL);
        pstmt.setString(1, obj.getNome());
        pstmt.setString(2, convertToDate(obj.getData_nasc()));
        pstmt.setString(3, obj.getEndereco());
        pstmt.setString(4, obj.getTelefone());
        pstmt.setString(5, obj.getEmail());
        pstmt.setString(6, obj.getRG());
        pstmt.setString(7, obj.getCpf());
        int registros = pstmt.executeUpdate();
        if (registros > 0) {
            String SQL2 = "Insert into Dependentes values (?,?,?)";
            PreparedStatement pstmt2 = objConexao.getConexao().prepareStatement(SQL2);
            pstmt2.setInt(1, obj.getId());
            pstmt2.setInt(2, obj.getParentesco().getId());
            pstmt2.setInt(3, obj.getIdAssociado());
            int registros2 = pstmt2.executeUpdate();
            if (registros2 > 0) {
                objConexao.getConexao().setAutoCommit(true);
                objConexao.getConexao().commit();
                return true;
            } else {
                objConexao.getConexao().setAutoCommit(true);
                objConexao.getConexao().rollback();
                return false;
            }
        } else {
            return false;
        }
    }

    private String convertToDate(String date) throws Exception {
        SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
        return outputFormat.format(inputFormat.parse(date));
    }

    public boolean editar(Dependente obj) throws Exception {
        Conexao objConexao = new Conexao();
        String SQL = "Update Associados set associado_id = ?, parentesco_id  = ? where associado_id = ?";
        PreparedStatement pstmt = objConexao.getConexao().prepareStatement(SQL);
        pstmt.setInt(1, obj.getIdAssociado());
        pstmt.setInt(2, obj.getParentesco().getId());
        pstmt.setInt(3, obj.getId());
        int registros = pstmt.executeUpdate();
        if (registros > 0) {
            String SQL2 = "Update Pessoas set nome = ?, data_nasc = ?, endereco = ?, telefone = ?, email = ?, rg = ?, cpf = ?"
                    + " where id = ?";
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
            return true;
        } else {
            return false;
        }
    }

    public boolean excluir(int id) throws Exception {
        Conexao objConexao = new Conexao();
        String SQL = "Delete from Dependentes where dependente_id  = ?";
        PreparedStatement pstmt = objConexao.getConexao().prepareStatement(SQL);
        pstmt.setInt(1, id);
        int registros = pstmt.executeUpdate();
        if (registros > 0) {
            String SQL2 = "Delete from Pessoas where id = ?";
            PreparedStatement pstmt2 = objConexao.getConexao().prepareStatement(SQL2);
            pstmt2.setInt(1, id);
            int registros2 = pstmt2.executeUpdate();
            if (registros2 > 0) {

                return true;
            } else;
        }
        return false;
    }

    public Dependente pesquisar(String nome) throws Exception {
        Dependente obj = null;
        Conexao objConexao = new Conexao();
        String SQL = "Select id, nome, data_nasc, endereco, telefone, email, rg, cpf, d.parentesco_id,  d.associado_id "
                + " from Dependentes d, Pessoas p, Parentescos pe, Associados a "
                + " where p.id = d.dependente_id and "
                + " d.associado_id = a.associado_id and "
                + " pe.parentesco_id = d.parentesco_id and p.cpf = ?";
        PreparedStatement pstmt = objConexao.getConexao().prepareStatement(SQL);
        pstmt.setString(1, nome);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            obj = new Dependente();
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
            Parentesco obj2 = new Parentesco();
            obj2.setId(rs.getInt("parentesco_id"));
            obj.setParentesco(obj2);
            obj.setIdAssociado(rs.getInt("associado_id"));

        }
        return obj;
    }

    public Vector listar(String arg) throws SQLException, IllegalAccessException, ClassNotFoundException, Exception {
        Vector dados = new Vector();
        Conexao objConexao = new Conexao();

        // Montagem da consulta SQL
        String SQL = "SELECT DISTINCT(A.dependente_id), B.nome, B.CPF, "
                + "CASE WHEN D.data_pgto > D.data_venc THEN 'DEVENDO' ELSE 'PAGO' END AS status_da_mensalidade "
                + "FROM Dependentes A "
                + "JOIN Pessoas B ON B.id = A.dependente_id "
                + "JOIN Associados E ON E.associado_id = A.associado_id "
                + "JOIN Contratos_Titulos C ON E.associado_id = C.associado_id "
                + "JOIN Mensalidades D ON C.id = D.contrato_id ";

        // Verifica se o filtro está presente e adiciona a cláusula WHERE
        if (arg != null && !arg.trim().isEmpty()) {
            SQL += "WHERE B.nome LIKE ? ";
        }

        SQL += "GROUP BY A.dependente_id, B.nome, B.CPF, D.data_pgto, D.data_venc";

        // Criação do PreparedStatement
        PreparedStatement pstmt = objConexao.getConexao().prepareStatement(SQL);

        // Se o filtro foi fornecido, define o parâmetro para a consulta
        if (arg != null && !arg.trim().isEmpty()) {
            pstmt.setString(1, "%" + arg + "%");
        }

        ResultSet rs = pstmt.executeQuery();

        // Processa os resultados da query
        while (rs.next()) {
            Vector linha = new Vector();
            linha.addElement(rs.getInt("dependente_id"));
            linha.addElement(rs.getString("nome"));
            linha.addElement(rs.getString("CPF"));
            linha.addElement(rs.getString("status_da_mensalidade"));
            dados.addElement(linha);
        }

        // Fechamento dos recursos
        rs.close();
        pstmt.close();
        objConexao.getConexao().close();

        return dados;
    }

    public Vector listar2(String arg) throws SQLException, IllegalAccessException, ClassNotFoundException, Exception {

        Vector dados = new Vector();
        Conexao objConexao = new Conexao();
        String SQL = "select DISTINCT(A.dependente_id),B.nome, B.CPF, "
                + "CASE WHEN D.data_pgto > data_venc THEN 'DEVENDO' ELSE 'PAGO' END status_da_mensalidade "
                + "  from Dependentes A "
                + " JOIN Pessoas B On B.id = A.dependente_id "
                + " JOIN Associados  E on E.associado_id = A.associado_id  "
                + " JOIN Contratos_Titulos C on E.associado_id  = C.associado_id "
                + "  JOIN Mensalidades D ON C.id = D.contrato_id "
                + " where B.nome like '%" + arg + "%' "
                + " GROUP BY A.dependente_id,B.nome, B.CPF, D.data_pgto, D.data_venc ";

        PreparedStatement pstmt = objConexao.getConexao().prepareStatement(SQL);
        ResultSet rs = pstmt.executeQuery();

        // percorre a lista de resultado da query:
        while (rs.next()) {
            Vector linha = new Vector();
            linha.addElement(rs.getInt("dependente_id"));
            linha.addElement(rs.getString("nome"));
            linha.addElement(rs.getString("CPF"));
            linha.addElement(rs.getString("status_da_mensalidade"));
            dados.addElement(linha);

        }

        return dados;

    }

}

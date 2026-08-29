/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.sql.Connection;
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

    /** R1.12 - quantidade de dependentes cadastrados. */
    public int contarDependentes() throws Exception {
        Conexao objConexao = new Conexao();
        String sql = "select count(*) total from Dependentes";
        try (Connection conn = objConexao.getConexao(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            return rs.next() ? rs.getInt("total") : 0;
        }
    }

    public boolean incluir(Dependente obj) throws Exception {
        Conexao objConexao = new Conexao();
        try (Connection conn = objConexao.getConexao()) {
            conn.setAutoCommit(false);
            try {
                int registros;
                try (PreparedStatement pstmt = conn.prepareStatement("Insert into Pessoas values (?,?,?,?,?,?,?)")) {
                    pstmt.setString(1, obj.getNome());
                    pstmt.setString(2, convertToDate(obj.getDataNasc()));
                    pstmt.setString(3, obj.getEndereco());
                    pstmt.setString(4, obj.getTelefone());
                    pstmt.setString(5, obj.getEmail());
                    pstmt.setString(6, obj.getRG());
                    pstmt.setString(7, obj.getCpf());
                    registros = pstmt.executeUpdate();
                }

                if (registros > 0) {
                    try (PreparedStatement pstmt2 = conn.prepareStatement("Insert into Dependentes values (?,?,?)")) {
                        pstmt2.setInt(1, obj.getId());
                        pstmt2.setInt(2, obj.getParentesco().getId());
                        pstmt2.setInt(3, obj.getIdAssociado());
                        int registros2 = pstmt2.executeUpdate();
                        if (registros2 > 0) {
                            conn.commit();
                            return true;
                        }
                    }
                }
                conn.rollback();
                return false;
            } catch (Exception e) {
                conn.rollback();
                throw new Exception("Erro ao incluir dependente: " + e.getMessage(), e);
            }
        }
    }

    private String convertToDate(String date) throws Exception {
        SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
        return outputFormat.format(inputFormat.parse(date));
    }

    public boolean editar(Dependente obj) throws Exception {
        Conexao objConexao = new Conexao();
        String sql = "Update Associados set associado_id = ?, parentesco_id  = ? where associado_id = ?";
        try (Connection conn = objConexao.getConexao(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, obj.getIdAssociado());
            pstmt.setInt(2, obj.getParentesco().getId());
            pstmt.setInt(3, obj.getId());
            int registros = pstmt.executeUpdate();
            if (registros > 0) {
                String sql2 = "Update Pessoas set nome = ?, data_nasc = ?, endereco = ?, telefone = ?, email = ?, rg = ?, cpf = ?"
                        + " where id = ?";
                try (PreparedStatement pstmt2 = conn.prepareStatement(sql2)) {
                    pstmt2.setString(1, obj.getNome());
                    pstmt2.setString(2, convertToDate(obj.getDataNasc()));
                    pstmt2.setString(3, obj.getEndereco());
                    pstmt2.setString(4, obj.getTelefone());
                    pstmt2.setString(5, obj.getEmail());
                    pstmt2.setString(6, obj.getRG());
                    pstmt2.setString(7, obj.getCpf());
                    pstmt2.setInt(8, obj.getId());
                    pstmt2.executeUpdate();
                }
                return true;
            }
            return false;
        }
    }

    public boolean excluir(int id) throws Exception {
        Conexao objConexao = new Conexao();
        try (Connection conn = objConexao.getConexao()) {
            conn.setAutoCommit(false);
            try {
                int registros;
                try (PreparedStatement pstmt = conn.prepareStatement("Delete from Dependentes where dependente_id  = ?")) {
                    pstmt.setInt(1, id);
                    registros = pstmt.executeUpdate();
                }

                if (registros > 0) {
                    try (PreparedStatement pstmt2 = conn.prepareStatement("Delete from Pessoas where id = ?")) {
                        pstmt2.setInt(1, id);
                        int registros2 = pstmt2.executeUpdate();
                        if (registros2 > 0) {
                            conn.commit();
                            return true;
                        }
                    }
                }
                conn.rollback();
                return false;
            } catch (Exception e) {
                conn.rollback();
                throw new Exception("Erro ao excluir dependente: " + e.getMessage(), e);
            }
        }
    }

    public Dependente pesquisar(String nome) throws Exception {
        Dependente obj = null;
        Conexao objConexao = new Conexao();
        String sql = "Select id, nome, data_nasc, endereco, telefone, email, rg, cpf, d.parentesco_id,  d.associado_id "
                + " from Dependentes d, Pessoas p, Parentescos pe, Associados a "
                + " where p.id = d.dependente_id and "
                + " d.associado_id = a.associado_id and "
                + " pe.parentesco_id = d.parentesco_id and p.cpf = ?";
        try (Connection conn = objConexao.getConexao(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nome);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    obj = new Dependente();
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
                    Parentesco obj2 = new Parentesco();
                    obj2.setId(rs.getInt("parentesco_id"));
                    obj.setParentesco(obj2);
                    obj.setIdAssociado(rs.getInt("associado_id"));
                }
            }
        }
        return obj;
    }

    public Vector listar(String arg) throws SQLException, IllegalAccessException, ClassNotFoundException, Exception {
        Vector dados = new Vector();
        Conexao objConexao = new Conexao();

        // Montagem da consulta sql
        String sql = "SELECT A.dependente_id, B.nome, B.CPF, "
                + StatusMensalidadeSql.EXPRESSAO + " AS status_da_mensalidade, "
                + "SUM(CASE WHEN D.data_pgto IS NULL THEN D.valor ELSE 0 END) valor_devido, "
                + "MIN(CASE WHEN D.data_pgto IS NULL THEN D.data_venc END) proximo_vencimento "
                + "FROM Dependentes A "
                + "JOIN Pessoas B ON B.id = A.dependente_id "
                + "JOIN Associados E ON E.associado_id = A.associado_id "
                + "JOIN Contratos_Titulos C ON E.associado_id = C.associado_id "
                + "JOIN Mensalidades D ON C.id = D.contrato_id ";

        // Verifica se o filtro está presente e adiciona a cláusula WHERE
        if (arg != null && !arg.trim().isEmpty()) {
            sql += "WHERE B.nome LIKE ? ";
        }

        sql += "GROUP BY A.dependente_id, B.nome, B.CPF";

        try (Connection conn = objConexao.getConexao(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            if (arg != null && !arg.trim().isEmpty()) {
                pstmt.setString(1, "%" + arg + "%");
            }

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Vector linha = new Vector();
                    linha.addElement(rs.getInt("dependente_id"));
                    linha.addElement(rs.getString("nome"));
                    linha.addElement(rs.getString("CPF"));
                    linha.addElement(rs.getString("status_da_mensalidade"));
                    linha.addElement(rs.getFloat("valor_devido"));
                    linha.addElement(StatusMensalidadeSql.formatarData(rs.getDate("proximo_vencimento")));
                    dados.addElement(linha);
                }
            }
        }

        return dados;
    }

    public Vector listar2(String arg) throws SQLException, IllegalAccessException, ClassNotFoundException, Exception {
        Vector dados = new Vector();
        Conexao objConexao = new Conexao();
        String sql = "select A.dependente_id,B.nome, B.CPF, "
                + "CASE WHEN SUM(CASE WHEN D.data_pgto IS NULL AND D.data_venc < CURDATE() THEN 1 ELSE 0 END) > 0 THEN 'DEVENDO' ELSE 'PAGO' END status_da_mensalidade "
                + "  from Dependentes A "
                + " JOIN Pessoas B On B.id = A.dependente_id "
                + " JOIN Associados  E on E.associado_id = A.associado_id  "
                + " JOIN Contratos_Titulos C on E.associado_id  = C.associado_id "
                + "  JOIN Mensalidades D ON C.id = D.contrato_id "
                + " where B.nome like ? "
                + " GROUP BY A.dependente_id,B.nome, B.CPF ";

        try (Connection conn = objConexao.getConexao(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + arg + "%");

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Vector linha = new Vector();
                    linha.addElement(rs.getInt("dependente_id"));
                    linha.addElement(rs.getString("nome"));
                    linha.addElement(rs.getString("CPF"));
                    linha.addElement(rs.getString("status_da_mensalidade"));
                    dados.addElement(linha);
                }
            }
        }

        return dados;
    }

}

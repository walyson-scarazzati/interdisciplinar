/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import model.Contrato;
import model.Mensalidade;

/**
 *
 * @author MaqLab
 */
public class MensalidadeData {

    public boolean incluir(Mensalidade obj) throws Exception {
        Conexao objConexao = new Conexao();
        String sql = "Insert into Mensalidades values(?,?,?,?,?,?,?)";
        try (Connection conn = objConexao.getConexao(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, obj.getId());
            pstmt.setFloat(2, obj.getPreco());
            pstmt.setString(3, convertToDate(obj.getDataPgto()));
            pstmt.setString(4, convertToDate(obj.getDataVenc()));
            pstmt.setFloat(5, obj.getValor());
            pstmt.setInt(6, Integer.parseInt(obj.getMesRef()));
            pstmt.setInt(7, obj.getContrato().getNroContrato());
            int registros = pstmt.executeUpdate();
            return registros > 0;
        }
    }

    private String convertToDate(String date) throws Exception {
        SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
        return outputFormat.format(inputFormat.parse(date));
    }

    public boolean editar(Mensalidade obj) throws Exception {
        Conexao objConexao = new Conexao();
        String sql = "Update Mensalidades set  mes_ref = ?,  valor = ?, data_venc = ?, data_pgto = ?, preco = ? where id = ?";
        try (Connection conn = objConexao.getConexao(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, Integer.parseInt(obj.getMesRef()));
            pstmt.setFloat(2, obj.getValor());
            pstmt.setString(3, convertToDate(obj.getDataVenc()));
            pstmt.setString(4, convertToDate(obj.getDataPgto()));
            pstmt.setFloat(5, obj.getPreco());
            pstmt.setInt(6, obj.getId());
            int registros = pstmt.executeUpdate();
            return registros > 0;
        }
    }

    public boolean registrarPagamento(int id, String dataPagamento) throws Exception {
        Conexao objConexao = new Conexao();
        String sql = "Update Mensalidades set data_pgto = ? where id = ?";
        try (Connection conn = objConexao.getConexao(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, convertToDate(dataPagamento));
            pstmt.setInt(2, id);
            int registros = pstmt.executeUpdate();
            return registros > 0;
        }
    }

    public boolean excluir(int id) throws Exception {
        Conexao objConexao = new Conexao();
        String sql = "Delete from Mensalidades where id = ?";
        try (Connection conn = objConexao.getConexao(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int registros = pstmt.executeUpdate();
            return registros > 0;
        }
    }

    public Mensalidade pesquisar(int id) throws Exception {
        Mensalidade obj = null;
        Conexao objConexao = new Conexao();
        String sql
                = "select m.id, preco, data_pgto, data_venc, valor, contrato_id, mes_ref "
                + " from Mensalidades m, Contratos_Titulos c  where c.id = m.contrato_id and m.id = ? ";

        try (Connection conn = objConexao.getConexao(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    obj = mapear(rs);
                }
            }
        }
        return obj;
    }

    public Mensalidade pesquisarPorContrato(int nroContrato) throws Exception {
        Mensalidade obj = null;
        Conexao objConexao = new Conexao();
        String sql
                = "select m.id, preco, data_pgto, data_venc, valor, contrato_id, mes_ref "
                + " from Mensalidades m where m.contrato_id = ? order by m.id desc limit 1";

        try (Connection conn = objConexao.getConexao(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, nroContrato);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    obj = mapear(rs);
                }
            }
        }
        return obj;
    }

    public Mensalidade pesquisarPendentePorAssociado(int associadoId) throws Exception {
        Conexao objConexao = new Conexao();
        String sql = "select m.id, preco, data_pgto, data_venc, valor, contrato_id, mes_ref "
                + "from Mensalidades m join Contratos_Titulos c on c.id = m.contrato_id "
                + "where c.associado_id = ? and m.data_pgto is null "
                + "order by m.data_venc asc limit 1";
        try (Connection conn = objConexao.getConexao(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, associadoId);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next() ? mapear(rs) : null;
            }
        }
    }

    public Mensalidade pesquisarPendentePorDependente(int dependenteId) throws Exception {
        Conexao objConexao = new Conexao();
        String sql = "select m.id, preco, data_pgto, data_venc, valor, contrato_id, mes_ref "
                + "from Mensalidades m "
                + "join Contratos_Titulos c on c.id = m.contrato_id "
                + "join Dependentes d on d.associado_id = c.associado_id "
                + "where d.dependente_id = ? and m.data_pgto is null "
                + "order by m.data_venc asc limit 1";
        try (Connection conn = objConexao.getConexao(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, dependenteId);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next() ? mapear(rs) : null;
            }
        }
    }

    public Vector<Mensalidade> historicoPorAssociado(int associadoId) throws Exception {
        Conexao objConexao = new Conexao();
        Vector<Mensalidade> dados = new Vector<>();
        String sql = "select m.id, preco, data_pgto, data_venc, valor, contrato_id, mes_ref "
                + "from Mensalidades m join Contratos_Titulos c on c.id = m.contrato_id "
                + "where c.associado_id = ? order by m.data_venc asc";
        try (Connection conn = objConexao.getConexao(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, associadoId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    dados.add(mapear(rs));
                }
            }
        }
        return dados;
    }

    public Vector<Mensalidade> historicoPorDependente(int dependenteId) throws Exception {
        Conexao objConexao = new Conexao();
        Vector<Mensalidade> dados = new Vector<>();
        String sql = "select m.id, preco, data_pgto, data_venc, valor, contrato_id, mes_ref "
                + "from Mensalidades m "
                + "join Contratos_Titulos c on c.id = m.contrato_id "
                + "join Dependentes d on d.associado_id = c.associado_id "
                + "where d.dependente_id = ? order by m.data_venc asc";
        try (Connection conn = objConexao.getConexao(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, dependenteId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    dados.add(mapear(rs));
                }
            }
        }
        return dados;
    }

    private Mensalidade mapear(ResultSet rs) throws Exception {
        SimpleDateFormat formatoBr = new SimpleDateFormat("dd/MM/yyyy");
        Mensalidade obj = new Mensalidade();
        obj.setId(rs.getInt("id"));
        obj.setPreco(rs.getFloat("preco"));
        Date dataPgto = rs.getDate("data_pgto");
        obj.setDataPgto(dataPgto != null ? formatoBr.format(dataPgto) : "");
        Date dataVenc = rs.getDate("data_venc");
        obj.setDataVenc(dataVenc != null ? formatoBr.format(dataVenc) : "");
        obj.setValor(rs.getFloat("valor"));
        Contrato obj2 = new Contrato();
        obj2.setNroContrato(rs.getInt("contrato_id"));
        obj.setContrato(obj2);
        obj.setMesRef(String.valueOf(rs.getInt("mes_ref")));
        return obj;
    }

}

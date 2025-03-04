/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import model.Contrato;
import model.Mensalidade;

/**
 *
 * @author MaqLab
 */
public class MensalidadeData {

    public boolean incluir(Mensalidade obj) throws Exception {
        Conexao objConexao = new Conexao();
        String SQL = "Insert into Mensalidades values(?,?,?,?,?,?,?)";
        PreparedStatement pstmt = objConexao.getConexao().prepareStatement(SQL);
        pstmt.setInt(1, obj.getId());
        pstmt.setFloat(2, obj.getPreco());
        pstmt.setString(3, convertToDate(obj.getData_pgto()));
        pstmt.setString(4, convertToDate(obj.getData_venc()));
        pstmt.setFloat(5, obj.getValor());
        pstmt.setString(6, obj.getMes_ref());
        pstmt.setInt(7, obj.getContrato().getNro_contrato());
        int registros = pstmt.executeUpdate();
        if (registros > 0) {

            return true;
        } else {
            return false;
        }
    }

    private String convertToDate(String date) throws Exception {
        SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
        return outputFormat.format(inputFormat.parse(date));
    }

    public boolean editar(Mensalidade obj) throws Exception {
        Conexao objConexao = new Conexao();
        String SQL = "Update Mensalidades set  mes_ref = ?,  valor = ?, data_venc = ?, data_pgto = ?, preco = ? where id = ?";
        PreparedStatement pstmt = objConexao.getConexao().prepareStatement(SQL);
        pstmt.setString(1, obj.getMes_ref());
        pstmt.setFloat(2, obj.getValor());
        pstmt.setString(3, convertToDate(obj.getData_venc()));
        pstmt.setString(4, convertToDate(obj.getData_pgto()));
        pstmt.setFloat(5, obj.getPreco());
        pstmt.setInt(6, obj.getId());
        int registros = pstmt.executeUpdate();
        if (registros > 0) {
            return true;
        } else {
            return false;
        }

    }

    public boolean excluir(int id) throws Exception {
        Conexao objConexao = new Conexao();
        String SQL = "Delete from Mensalidades where id = ?";
        PreparedStatement pstmt = objConexao.getConexao().prepareStatement(SQL);
        pstmt.setInt(1, id);
        int registros = pstmt.executeUpdate();
        if (registros > 0) {

            return true;
        } else {
            return false;
        }
    }

    public Mensalidade pesquisar(int id) throws Exception {
        Mensalidade obj = null;
        Conexao objConexao = new Conexao();
        String SQL
                = "select c.id, preco, data_pgto, data_venc, valor, contrato_id, mes_ref "
                + " from Mensalidades m, Contratos_Titulos c  where c.id = m.contrato_id and m.id = ? ";

        PreparedStatement pstmt = objConexao.getConexao().prepareStatement(SQL);
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            obj = new Mensalidade();
            obj.setId(rs.getInt("id"));
            obj.setPreco(rs.getFloat("preco"));
            obj.setData_pgto(rs.getString("data_pgto"));
            obj.setData_venc(rs.getString("data_venc"));
            obj.setValor(rs.getFloat("valor"));
            Contrato obj2 = new Contrato();
            obj2.setNro_contrato(rs.getInt("contrato_id"));
            obj.setContrato(obj2);

            obj.setMes_ref(rs.getString("mes_ref"));

        }
        return obj;
    }

}

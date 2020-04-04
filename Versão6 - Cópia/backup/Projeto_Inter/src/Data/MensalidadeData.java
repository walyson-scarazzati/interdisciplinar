/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import model.Contrato;
import model.Mensalidade;

/**
 *
 * @author MaqLab
 */
public class MensalidadeData {
    
//        public Vector<Mensalidade> listar() throws Exception {
//        Conexao objConexao = new Conexao();
//        Vector<Mensalidade> dados = new Vector<Mensalidade>();
//        String SQL1 = "select a.id, a.data, a.hora, "+
//		" p1.id as idCliente, p1.nome as nomeCliente, c.telefone ,"+
//		" p2.id as idFuncionario, p2.nome as nomeFuncionario, f.cargo"+
//                " from tbAgendamentos a, tbClientes c, "+
//                " tbFuncionarios f, tbPessoas p1, tbPessoas p2"+
//                " where	a.idCliente = c.idPessoa and"+
//			" a.idFuncionario = f.idPessoa and"+
//			" c.idPessoa = p1.id and"+
//			" f.idPessoa = p2.id"+
//                " order by a.id";
//        PreparedStatement pstmt = objConexao.getConexao().prepareStatement(SQL1);
//        ResultSet rs = pstmt.executeQuery();
//        while(rs.next()){
//            Mensalidade obj = new Mensalidade();
//            obj.setId(rs.getInt("id"));
//            obj.setData_pgto(rs.getString("data_pgto"));
//            obj.setPreco(rs.getFloat("preco"));
//            obj.setData_venc(rs.getString("data_venc"));
//            obj.setValor(rs.getFloat("valor"));
//            obj.setContrato(new Contrato(rs.getInt("contrato_id"), rs.getString("data_contrato"),
//                  rs.getString("data_cancel"), rs.getInt("status"),rs.getInt("") ,rs.getInt("") ,rs.getInt("")));
//            obj.setMes_ref(rs.getString("mes_ref"));
//            
//            // select dos serviços
//            String SQL2 = "Select sa.idAgendamento, sa.idServico, s.descricao, sa.preco"+
//                " from tbServicosAgendamento sa, tbServicos s  "+
//                " where sa.idAgendamento=? and"+
//                " sa.idServico = s.id";
//            PreparedStatement pstmt2 = objConexao.getConexao().prepareStatement(SQL2);
//            pstmt2.setInt(1,obj.getId());
//            ResultSet rs2 = pstmt2.executeQuery();
//            while(rs2.next()){
//                obj.adicionarServico(new Servico(rs2.getInt("idServico"), 
//                        rs2.getString("descricao"), rs2.getFloat("preco")));
//            }
//            dados.add(obj);
//        }
//        return  dados;
//    }
//    
    public boolean incluir(Mensalidade obj) throws Exception{
        Conexao objConexao = new Conexao();
        String SQL = "Insert into Mensalidades values(?,?,?,?,?,?,?)";
        PreparedStatement pstmt = objConexao.getConexao().prepareStatement(SQL);
        pstmt.setInt(1,obj.getId());
        pstmt.setFloat(2,obj.getPreco());
        pstmt.setString(3,obj.getData_pgto());
        pstmt.setString(4,obj.getData_venc());
        pstmt.setFloat(5,obj.getValor());
        pstmt.setString(6,obj.getMes_ref());
         pstmt.setInt(7,obj.getContrato().getNro_contrato());
        int registros = pstmt.executeUpdate();
        if (registros>0){

            return true;}
        else
            return false; 
    }
    
    public boolean editar(Mensalidade obj) throws Exception{
        Conexao objConexao = new Conexao();
        String SQL = "Update Mensalidades set  mes_ref = ?,  valor = ?, data_venc = ?, data_pgto = ?, preco = ? where id = ?";
        PreparedStatement pstmt = objConexao.getConexao().prepareStatement (SQL);
        pstmt.setString(1,obj.getMes_ref());
        pstmt.setFloat(2,obj.getValor());
        pstmt.setString(3,obj.getData_venc());
        pstmt.setString(4,obj.getData_pgto());
        pstmt.setFloat(5,obj.getPreco());
        pstmt.setInt(6,obj.getId());
        int registros = pstmt.executeUpdate();
        if(registros>0)
            return true;
        else
            return false;
        
    }
         public boolean excluir(int id)throws Exception{
        Conexao objConexao = new Conexao();
        String SQL = "Delete from Mensalidades where id = ?"; 
        PreparedStatement pstmt = objConexao.getConexao().prepareStatement(SQL);
        pstmt.setInt(1, id);
        int registros = pstmt.executeUpdate();
        if(registros>0){
      
            return true;}
        else
            return false;
    }
        
     public Mensalidade pesquisar(int id) throws Exception {
        Mensalidade obj = null;
        Conexao objConexao = new Conexao();
        String SQL = "select id, preco, data_pgto, data_venc, valor, mes_ref from Mensalidades where id = ?";
        PreparedStatement pstmt = objConexao.getConexao().prepareStatement(SQL);
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();
        if(rs.next()){
            //obj = new Servico(rs.getInt("id"), rs.getString("descricao"), 
            //        rs.getFloat("preco"));
            //ou
            obj = new Mensalidade();
            obj.setId(rs.getInt("id"));
            obj.setPreco(rs.getFloat("preco"));
            obj.setData_pgto(rs.getString("data_pgto"));
        obj.setData_venc(rs.getString("data_venc"));
        obj.setValor(rs.getFloat("valor"));
        obj.setMes_ref(rs.getString("mes_ref"));
           
        }
        return obj;
    }
     
     
        
      
     
    
}

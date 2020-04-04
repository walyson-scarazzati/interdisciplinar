/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import model.Modalidade_esportiva;


/**
 *
 * autor : Isabel Cristina da Silva Bolognani
 */
public class Modalidade_esportivaData {
    
    public boolean incluir(Modalidade_esportiva obj) throws Exception{
        Conexao objConexao = new Conexao();
        String SQL = "Insert into Modalidades_Esportes values (?,?,?)";
        PreparedStatement pstmt = objConexao.getConexao().prepareStatement(SQL);
        pstmt.setInt(1, obj.getId());
        pstmt.setString(2,obj.getDescricao());
        pstmt.setInt(3, obj.getCategoria().getId());
        
        int registros = pstmt.executeUpdate();
        
        if (registros>0){

            return true;}
        else
            return false;
    }
    
    public Modalidade_esportiva pesquisar(int id) throws Exception{
        Modalidade_esportiva obj = null;
        Conexao objConexao = new Conexao();
        String SQL = "select * from Modalidades_Esportes where id=?";
        PreparedStatement pstmt = objConexao.getConexao().prepareStatement (SQL);
        pstmt.setInt(1,id);
             
        ResultSet rs= pstmt.executeQuery();
        if(rs.next()){           
            
            obj= new Modalidade_esportiva();
            obj.setId(rs.getInt("id"));
            obj.setDescricao(rs.getString("descricao"));
                               
        }
        return obj;
    }
    
    
    public Vector listarTodos(String pesq) throws Exception {
        Vector dados = new Vector();
        Conexao objConexao = new Conexao();
        String SQL = "Select * from Modalidades_Esportes where descricao like '"
                +pesq+"%' order by descricao";
        PreparedStatement pstmt = objConexao.getConexao().
                prepareStatement(SQL);
        ResultSet rs = pstmt.executeQuery();
        while(rs.next()){
            Vector linha = new Vector();
            linha.add(rs.getInt("id"));
            linha.add(rs.getString("descricao"));
            linha.add("");
            dados.add(linha);
        }
        return dados;
    }
    
     public Vector listar() throws Exception{
       Conexao objConexao = new Conexao();
       Vector dados = new Vector();
       String SQL = "Select * from Modalidades_Esportes order by id";
       PreparedStatement pstmt = objConexao.getConexao().prepareStatement(SQL);
       ResultSet rs = pstmt.executeQuery();
       while(rs.next()){
          Vector linha = new Vector();
          linha.add(rs.getInt("id"));
          linha.add (rs.getString("descricao"));
          dados.add(linha);
       } 
       return dados;
     }
    
       public Vector<Modalidade_esportiva> listarModalidade_esportiva() throws Exception {
       Conexao objConexao = new Conexao ();
        Vector<Modalidade_esportiva> dados = new Vector<Modalidade_esportiva>();
        String SQL = "Select * from Modalidades_Esportes order by id";
        PreparedStatement pstmt = objConexao.getConexao().prepareStatement(SQL);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()){
            
          Modalidade_esportiva obj = new Modalidade_esportiva();
            
        obj.setId(rs.getInt("id"));
        obj.setDescricao(rs.getString("descricao"));
        dados.add(obj);
        }
        return dados;
    }
      
        
      
      public boolean excluir(int id)throws Exception{
        Conexao objConexao = new Conexao();
        String SQL = "Delete from Modalidades_Esportes where id = ?"; 
        PreparedStatement pstmt = objConexao.getConexao().prepareStatement(SQL);
        pstmt.setInt(1, id);
        int registros = pstmt.executeUpdate();
        if(registros>0){
      
            return true;}
        else
            return false;
    }
      
      
      public boolean editar(Modalidade_esportiva obj) throws Exception{
        Conexao objConexao = new Conexao();
        String SQL = "Update Modalidades_Esportes set descricao = ? where id = ?";
        PreparedStatement pstmt = objConexao.getConexao().prepareStatement (SQL);
        pstmt.setString(1, obj.getDescricao());
        pstmt.setInt(2, obj.getId());
        int registros = pstmt.executeUpdate();
        if(registros>0)
        return true;
        else
        return false;
        
  }
      
//      public Vector<Modalidade_esportiva> carregarCombo() throws Exception {
//        Vector<Modalidade_esportiva> dados = new Vector<Modalidade_esportiva>();
//        Conexao objConexao = new Conexao();
//        String SQL = "Select * from Modalidades_Esportes order by descricao";
//        PreparedStatement pstmt = objConexao.getConexao().prepareStatement(SQL);
//        ResultSet rs = pstmt.executeQuery();
//        dados.add(new Modalidade_esportiva(0,"<Selecione>"));
//        while(rs.next()){
//            dados.add(new Modalidade_esportiva(rs.getInt("id"),
//                    rs.getString("descricao")));
//                    }
//   return dados;
//    }
        
} 

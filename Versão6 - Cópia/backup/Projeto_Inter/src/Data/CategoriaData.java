/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import model.Categoria;
import model.Modalidade_esportiva;



/**
 *
 * autor : Isabel Cristina da Silva Bolognani
 */
public class CategoriaData {
    
    public boolean incluir(Categoria obj) throws Exception{
        Conexao objConexao = new Conexao();
        String SQL = "Insert into  Categorias values (?,?,?)";
        PreparedStatement pstmt = objConexao.getConexao().prepareStatement(SQL);
        pstmt.setInt(1, obj.getId());
        pstmt.setString(2,obj.getDescricao());
        pstmt.setFloat(3, obj.getValor());

        
        int registros = pstmt.executeUpdate();
        
        if (registros>0){

            return true;}
        else
            return false;
    }
    
 
    
     public Vector<Categoria> listar() throws Exception {
        Conexao objConexao = new Conexao();
        Vector<Categoria> dados = new Vector<Categoria>();
        String SQL1 = "c.id, c.descricao, c.valor," +
                       "m.id as idModalidades, m.descricao as descricaoModalidades"+
                "from Categorias c, Modalidades_Esportes m" +
                " order by c.id";
        PreparedStatement pstmt = objConexao.getConexao().prepareStatement(SQL1);
        ResultSet rs = pstmt.executeQuery();
        while(rs.next()){
            Categoria obj = new Categoria();
            obj.setId(rs.getInt("id"));
            obj.setDescricao(rs.getString("Descrição"));
            obj.setValor(rs.getFloat("Valor"));
            boolean add = dados.add(obj);
                 
        }
        pstmt.close();
        Conexao.getConexao().close();
        return  dados;
    }
    
    
     public Categoria pesquisar(int id) throws Exception{
        Categoria obj = null;
        Conexao objConexao = new Conexao();
        String SQL = "select * from  Categorias where id=?";
        PreparedStatement pstmt = objConexao.getConexao().prepareStatement (SQL);
        pstmt.setInt(1,id);
             
        ResultSet rs= pstmt.executeQuery();
        if(rs.next()){           
            
            obj= new Categoria();
            obj.setId(rs.getInt("id"));
            obj.setDescricao(rs.getString("descricao"));
            obj.setValor(rs.getFloat("Valor"));
                    
        }
        return obj;
    }
    
    
         public boolean excluir(int id)throws Exception{
        Conexao objConexao = new Conexao();
        String SQL = "Delete from  Categorias where id = ?"; 
        PreparedStatement pstmt = objConexao.getConexao().prepareStatement(SQL);
        pstmt.setInt(1, id);
        int registros = pstmt.executeUpdate();
        if(registros>0){
      
            return true;}
        else
            return false;
    }
      
      
      public boolean editar(Categoria obj) throws Exception{
        Conexao objConexao = new Conexao();
        String SQL = "Update  Categorias set descricao = ?, valor = ? where id = ?";
        PreparedStatement pstmt = objConexao.getConexao().prepareStatement (SQL);
        pstmt.setString(1, obj.getDescricao());
        pstmt.setFloat(2,obj.getValor());
        pstmt.setInt(3, obj.getId());
        int registros = pstmt.executeUpdate();
        if(registros>0)
            return true;
        else
        return false;
        
  }
      
        public Vector<Categoria> carregarCombo() throws Exception {
        Vector<Categoria> dados = new Vector<Categoria>();
        Conexao objConexao = new Conexao();
        String SQL = "Select * from  Categorias order by descricao";          
        PreparedStatement pstmt = objConexao.getConexao().prepareStatement(SQL);
        ResultSet rs = pstmt.executeQuery();
        dados.add(new Categoria(0,"<Selecione>", 0));
        while(rs.next()){      
            dados.add(new Categoria(rs.getInt("id"), 
                    rs.getString("descricao"), 
                    rs.getFloat("valor")));

        }
        return dados;
    } 
      
}
          
          
          
      
          
          
     
      
      
      
      
      
      
     
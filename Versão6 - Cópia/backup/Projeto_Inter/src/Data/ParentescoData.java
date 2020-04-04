/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import model.Parentesco;

/**
 *
 * @author MaqLab
 */
public class ParentescoData {
    
    public boolean incluir(Parentesco obj) throws Exception{
       Conexao objConexao = new Conexao();
       String SQL = "insert into Parentescos values (?,?)";
       PreparedStatement pstmt = objConexao.getConexao().prepareStatement(SQL);
       pstmt.setInt(1,obj.getId());
       pstmt.setString(2,obj.getDescricao());
       int registros = pstmt.executeUpdate();
       if(registros>0){
         
              return true;}
        else
            return false;
    }
   
    
    
        public Vector<Parentesco> carregarCombo() throws Exception {
        Vector<Parentesco> dados = new Vector<Parentesco>();
        Conexao objConexao = new Conexao();
        String SQL = "Select * from Parentescos order by parentesco_id";     
        PreparedStatement pstmt = objConexao.getConexao().prepareStatement(SQL);
        ResultSet rs = pstmt.executeQuery();
        dados.add(new Parentesco(0,"<Selecione>"));
        while(rs.next()){      
            dados.add(new Parentesco(rs.getInt("parentesco_id"), 
                    rs.getString("descricao")));
        }
        return dados;
    } 
    
    
}

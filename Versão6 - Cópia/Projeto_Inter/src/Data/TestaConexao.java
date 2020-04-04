/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

/**
 *
 * @author MaqLab
 */
public class TestaConexao {
       public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, Exception{
       Conexao objConexao = new Conexao();// Objeto Conexão
       try{ // Tratamento
       String SQL = "insert into Parentescos values(3,'Pai')";
       PreparedStatement pstmt = objConexao.getConexao().prepareStatement(SQL);
       int registros = pstmt.executeUpdate();
       if(registros>0)
           JOptionPane.showMessageDialog(null, "Registro salvo com sucesso !");
       else
           JOptionPane.showMessageDialog(null, "Não foi possível salvar o registro");
    }catch (Exception erro){
        JOptionPane.showMessageDialog(null, "Erro ao salvar: " + erro.getMessage());
      }// Fim de tratamento
   }  
}

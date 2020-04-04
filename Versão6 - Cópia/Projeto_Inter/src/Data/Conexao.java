/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author MaqLab
 */
public class Conexao {
      
    private static Connection conexao;
    public Conexao(){
       try{
           Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
           String url = "jdbc:odbc:odbcVendasTitulos";
           conexao = DriverManager.getConnection(url);
           //JOptionPane.showMessageDialog(null, "Conectado com sucesso !");
          }catch (Exception erro){
          JOptionPane.showMessageDialog(null, "Ocorreu um erro:" + erro.getMessage(), "Conexão", 3);
       }
       
    }

    public static Connection getConexao() 
    { return conexao; }
    
}

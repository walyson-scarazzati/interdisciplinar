/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author MaqLab
 */
public class Conexao {
      
private static Connection conexao;

	public Conexao() throws SQLException, InstantiationException,
			IllegalAccessException, ClassNotFoundException, Exception {
		String url = "jdbc:mysql://localhost:3306/venda_de_titulos?useSSL=false&createDatabaseIfNotExist=true";

		String user = "root";
		String password = "123456";

		Class.forName("com.mysql.jdbc.Driver").newInstance();
		conexao = DriverManager.getConnection(url, user, password);
	}

	public void closeConnection() throws SQLException {
		conexao.close();
	}
	
    public static Connection getConexao() 
    { return conexao; }
    
}

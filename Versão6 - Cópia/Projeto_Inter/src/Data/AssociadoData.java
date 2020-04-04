/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import model.Associado;
import static view.jifAssociado.vetorDependentes;

/**
 *
 * @author MaqLab
 */
public class AssociadoData {

    public boolean incluir(Associado obj) throws Exception{
       Conexao objConexao = new Conexao();
       String SQL = "insert into Pessoas values (?,?,?,?,?,?,?,?)";
       PreparedStatement pstmt = objConexao.getConexao().prepareStatement(SQL);
       pstmt.setInt(1,obj.getId()); // tem que seguir a ordem do bd
       pstmt.setString(2,obj.getNome());
       pstmt.setString(3,obj.getData_nasc());
       pstmt.setString(4,obj.getEndereco());
       pstmt.setString(5,obj.getTelefone());
       pstmt.setString(6,obj.getEmail());
       pstmt.setInt(7,obj.getRG());
       pstmt.setInt(8,obj.getCpf());
       int registros = pstmt.executeUpdate();
       if(registros>0){
         String SQL2 = "insert into Associados values (?,?)";
         PreparedStatement pstmt2 = objConexao.getConexao().prepareStatement(SQL2);
         pstmt2.setInt(1,obj.getId());
         pstmt2.setString(2,obj.getProfissao());
         int registros2 = pstmt2.executeUpdate();
         if(registros2>0){
             
                 for(int i =0; i < obj.getDependente().size(); i++)
                 //Fazer um laço de repetição de vetor dependente
                // 
                     
                     
             
            objConexao.getConexao().commit();
            objConexao.getConexao().setAutoCommit(true);
            return true;
         }
            else{
                objConexao.getConexao().rollback();
                objConexao.getConexao().setAutoCommit(true);
            return false;
            }    
         }
       else
           return false;
     }
    public boolean editar(Associado obj) throws Exception{
       Conexao objConexao = new Conexao();
       String SQL = "Update Associados set profissao = ? where associado_id = ?";
       PreparedStatement pstmt = objConexao.getConexao().prepareStatement(SQL);
       pstmt.setString(1, obj.getProfissao());
       pstmt.setInt(2, obj.getId());
       int registros = pstmt.executeUpdate();
       if(registros>0){
         String SQL2 = "Update Pessoas set nome = ?, data_nasc = ?, endereco = ?, telefone = ?, email = ?, rg = ?, cpf = ?"
                 + " where id = ?";
         PreparedStatement pstmt2 = objConexao.getConexao().prepareStatement(SQL2);
         pstmt2.setString(1, obj.getNome());
         pstmt2.setString(2, obj.getData_nasc());
         pstmt2.setString(3, obj.getEndereco());
         pstmt2.setString(4, obj.getTelefone());
         pstmt2.setString(5, obj.getEmail());
         pstmt2.setInt(6, obj.getRG());
         pstmt2.setInt(7, obj.getCpf());
         pstmt2.setInt(8, obj.getId());
         int registros2 = pstmt2.executeUpdate();
        return true;}
       else
           return false;
    }
    public boolean excluir (int id) throws Exception{
       Conexao objConexao = new Conexao();
       objConexao.getConexao().setAutoCommit(false);
       String SQL = "Delete from Associados where associado_id =?";
       PreparedStatement pstmt = objConexao.getConexao().prepareStatement(SQL);
       pstmt.setInt(1, id);
       int registros = pstmt.executeUpdate();
       if(registros > 0){
          String SQL2 = "Delete from Pessoas where id = ?";
          PreparedStatement pstmt2 = objConexao.getConexao().prepareStatement(SQL2);
          pstmt2.setInt(1, id);
          int registros2 = pstmt2.executeUpdate();
          if(registros2 >0){
            objConexao.getConexao().commit();
          }
          return true;}
          else
           return false;
       }
    public Associado pesquisar (int id) throws Exception{
       Conexao objConexao = new Conexao();
       Associado obj = null;
       String SQL = "select id, nome, data_nasc, endereco, telefone, email, rg, cpf, profissao from Associados a, Pessoas p"
               + " where p.id = a.associado_id and p.id =  ? ";
       PreparedStatement pstmt = objConexao.getConexao().prepareStatement(SQL);
       pstmt.setInt(1, id);
       ResultSet rs = pstmt.executeQuery();
       if(rs.next()){
         obj = new Associado();
         obj.setId(rs.getInt("id"));
         obj.setNome(rs.getString("nome"));
         obj.setData_nasc(rs.getString("data_nasc"));
         obj.setEndereco(rs.getString("endereco"));
         obj.setTelefone(rs.getString("telefone"));
         obj.setEmail(rs.getString("email"));
         obj.setRG(rs.getInt("rg"));
         obj.setCpf(rs.getInt("cpf"));
         obj.setProfissao(rs.getString("profissao"));
       }
       return obj;
    }
    
     public Vector<Associado> carregarCombo() throws Exception {
        Vector<Associado> dados = new Vector<Associado>();
        Conexao objConexao = new Conexao();
        String SQL = "select id, nome, cpf from Pessoas p, Associados a" +
                     " where p.id = a.associado_id";
              
        PreparedStatement pstmt = objConexao.getConexao().prepareStatement(SQL);
        ResultSet rs = pstmt.executeQuery();
        dados.add(new Associado(0,  "<Selecione>", "", "", "", "", 0, 0, "", null));
        while(rs.next()){      
            Associado obj = new Associado();
            obj.setId(rs.getInt("id"));
            obj.setNome(rs.getString("nome").trim());
            obj.setCpf(rs.getInt("CPF"));
            dados.add(obj);
        }
        return dados;
    }
     
         public Vector Listar(String arg) throws SQLException {

        Vector dados = new Vector();
        Conexao objConexao = new Conexao();
        String SQL = "select DISTINCT(A.associado_id),B.nome, B.CPF, "
                + "CASE WHEN D.data_pgto > data_venc THEN 'DEVENDO' ELSE 'PAGO' END status_da_mensalidade "
                + "from Associados A JOIN Pessoas B On B.id = A.associado_id "
                + "JOIN Contratos_Titulos C on B.id = C.associado_id "
                + "JOIN Mensalidades D ON C.id = D.contrato_id "
                + "WHERE B.nome LIKE '%" + arg + "%' "
                + "GROUP BY A.associado_id,B.nome, B.CPF, D.data_pgto, D.data_venc ";

        PreparedStatement pstmt = objConexao.getConexao().prepareStatement(SQL);
        ResultSet rs = pstmt.executeQuery();

        // percorre a lista de resultado da query:
        while (rs.next()) {
            Vector linha = new Vector();
            linha.addElement(rs.getInt("associado_id"));
            linha.addElement(rs.getString("nome"));
            linha.addElement(rs.getString("CPF"));
            linha.addElement(rs.getString("status_da_mensalidade"));
            dados.addElement(linha);

        }

        return dados;

    }

    public Vector listar() throws SQLException {
        Vector dados = new Vector();
        Conexao objConexao = new Conexao();
        String SQL = "select DISTINCT(A.associado_id),B.nome, B.CPF, "
                + " CASE WHEN D.data_pgto > data_venc THEN 'DEVENDO' ELSE 'PAGO' END status_da_mensalidade "
                + " from Associados A JOIN Pessoas B On B.id = A.associado_id "
                + " JOIN Contratos_Titulos C on B.id = C.associado_id "
                + " JOIN Mensalidades D ON C.id = D.contrato_id "
                + " GROUP BY A.associado_id,B.nome, B.CPF, D.data_pgto, D.data_venc ";



        PreparedStatement pstmt = objConexao.getConexao().prepareStatement(SQL);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            Vector linha = new Vector();
            linha.addElement(rs.getInt("associado_id"));
            linha.addElement(rs.getString("nome"));
            linha.addElement(rs.getString("CPF"));
            linha.addElement(rs.getString("status_da_mensalidade"));
            dados.addElement(linha);

        }
        return dados;

    }
 
 }

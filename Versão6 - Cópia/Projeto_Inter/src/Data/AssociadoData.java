/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import model.Associado;
import model.Dependente;
import model.Parentesco;
import static view.jifAssociado.vetorDependentes;

/**
 *
 * @author MaqLab
 */
public class AssociadoData {

    public boolean incluir(Associado associado) throws Exception {
        Conexao objConexao = new Conexao();
        Connection conn = null;
        PreparedStatement pstmt2 = null;
        PreparedStatement pstmt4 = null;
        ResultSet rs = null;

        String SQL2 = "INSERT INTO Associados (associado_id, profissao) VALUES (?, ?)";
        String SQL4 = "INSERT INTO Dependentes (dependente_id, associado_id, parentesco_id) VALUES (?, ?, ?)";

        try {
            conn = objConexao.getConexao();
            conn.setAutoCommit(false);

            // Insert Associado into Pessoas
            int associadoId = insertPessoa(conn, associado.getNome(), associado.getData_nasc(), associado.getEndereco(), associado.getTelefone(), associado.getEmail(), associado.getRG(), associado.getCpf());

            // Insert into Associados
            pstmt2 = conn.prepareStatement(SQL2);
            pstmt2.setInt(1, associadoId);
            pstmt2.setString(2, associado.getProfissao());
            pstmt2.executeUpdate();

            // Insert Dependentes
            if (associado.getDependente() != null && !associado.getDependente().isEmpty()) {
                for (Dependente dependente : associado.getDependente()) {
                    // Insert Dependente into Pessoas
                    int dependenteId = insertPessoa(conn, dependente.getNome(), dependente.getData_nasc(), dependente.getEndereco(), dependente.getTelefone(), dependente.getEmail(), dependente.getRG(), dependente.getCpf());

                    // Insert into Dependentes
                    pstmt4 = conn.prepareStatement(SQL4);
                    pstmt4.setInt(1, dependenteId);
                    pstmt4.setInt(2, associadoId);
                    pstmt4.setInt(3, dependente.getParentesco().getId()); // Assuming Parentesco has an ID
                    pstmt4.executeUpdate();
                }
            }

            conn.commit(); // Commit transaction
            return true;
        } catch (SQLException e) {
            if (conn != null) {
                conn.rollback(); // Rollback transaction in case of an error
            }
            throw new Exception("Error adding Associado with Dependentes: " + e.getMessage(), e);
        } finally {
            // Close resources
            if (rs != null) {
                rs.close();
            }
            if (pstmt2 != null) {
                pstmt2.close();
            }
            if (pstmt4 != null) {
                pstmt4.close();
            }
            if (conn != null) {
                conn.setAutoCommit(true); // Restore auto-commit mode
                objConexao.closeConnection(conn);
            }
        }
    }

// Helper method to insert a person and return the generated ID
    private int insertPessoa(Connection conn, String nome, String dataNasc, String endereco, String telefone, String email, String rg, String cpf) throws SQLException, ParseException {
        String SQL = "INSERT INTO Pessoas (nome, data_nasc, endereco, telefone, email, RG, CPF) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        SimpleDateFormat originalFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = originalFormat.parse(dataNasc);
        String formattedDate = targetFormat.format(date);

        try {
            pstmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, nome);
            pstmt.setString(2, formattedDate);
            pstmt.setString(3, endereco);
            pstmt.setString(4, telefone);
            pstmt.setString(5, email);
            pstmt.setString(6, rg);
            pstmt.setString(7, cpf);
            pstmt.executeUpdate();

            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                throw new SQLException("Failed to insert into Pessoas, no ID obtained.");
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
        }
    }

    public boolean editar(Associado obj) throws Exception {
        Conexao objConexao = new Conexao();
        Connection conn = null;
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;

        try {
            conn = objConexao.getConexao();
            conn.setAutoCommit(false); // Start transaction

            String SQL1 = "Update Associados set profissao = ? where associado_id = ?";
            pstmt1 = conn.prepareStatement(SQL1);
            pstmt1.setString(1, obj.getProfissao());
            pstmt1.setInt(2, obj.getId());
            int registros = pstmt1.executeUpdate();

            if (registros > 0) {
                String SQL2 = "Update Pessoas set nome = ?, data_nasc = ?, endereco = ?, telefone = ?, email = ?, rg = ?, cpf = ? where id = ?";
                pstmt2 = conn.prepareStatement(SQL2);
                pstmt2.setString(1, obj.getNome());
                pstmt2.setString(2, obj.getData_nasc());
                pstmt2.setString(3, obj.getEndereco());
                pstmt2.setString(4, obj.getTelefone());
                pstmt2.setString(5, obj.getEmail());
                pstmt2.setString(6, obj.getRG());
                pstmt2.setString(7, obj.getCpf());
                pstmt2.setInt(8, obj.getId());
                pstmt2.executeUpdate();

                conn.commit(); // Commit transaction
                return true;
            } else {
                conn.rollback(); // Rollback transaction on failure
                return false;
            }
        } catch (SQLException e) {
            if (conn != null) {
                conn.rollback(); // Rollback transaction on error
            }
            throw new Exception("Error updating Associado: " + e.getMessage(), e);
        } finally {
            if (pstmt1 != null) {
                pstmt1.close();
            }
            if (pstmt2 != null) {
                pstmt2.close();
            }
            if (conn != null) {
                conn.setAutoCommit(true); // Restore auto-commit
                objConexao.closeConnection(conn);
            }
        }
    }

    public boolean excluir(int id) throws Exception {
        Conexao objConexao = new Conexao();
        Connection conn = null;
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;

        try {
            conn = objConexao.getConexao();
            conn.setAutoCommit(false); // Start transaction

            String SQL1 = "Delete from Associados where associado_id = ?";
            pstmt1 = conn.prepareStatement(SQL1);
            pstmt1.setInt(1, id);
            int registros = pstmt1.executeUpdate();

            if (registros > 0) {
                String SQL2 = "Delete from Pessoas where id = ?";
                pstmt2 = conn.prepareStatement(SQL2);
                pstmt2.setInt(1, id);
                int registros2 = pstmt2.executeUpdate();

                if (registros2 > 0) {
                    conn.commit(); // Commit transaction
                    return true;
                } else {
                    conn.rollback(); // Rollback if deletion from Pessoas fails
                    return false;
                }
            } else {
                conn.rollback(); // Rollback if deletion from Associados fails
                return false;
            }
        } catch (SQLException e) {
            if (conn != null) {
                conn.rollback(); // Rollback transaction on error
            }
            throw new Exception("Error deleting Associado: " + e.getMessage(), e);
        } finally {
            if (pstmt1 != null) {
                pstmt1.close();
            }
            if (pstmt2 != null) {
                pstmt2.close();
            }
            if (conn != null) {
                conn.setAutoCommit(true); // Restore auto-commit
                objConexao.closeConnection(conn);
            }
        }
    }

    public Associado pesquisar(String cpf) throws Exception {
        Conexao objConexao = new Conexao();
        Connection conn = null;
        Associado obj = null;

        String SQL = "SELECT p.id, p.nome, p.data_nasc, p.endereco, p.telefone, p.email, p.rg, p.cpf, a.profissao "
                + "FROM Associados a "
                + "JOIN Pessoas p ON p.id = a.associado_id "
                + "WHERE p.cpf = ?";

        String SQLDependentes = "SELECT d.dependente_id, p2.nome, p2.data_nasc, p2.endereco, p2.telefone, p2.email, p2.rg, p2.cpf, d.parentesco_id "
                + "FROM Dependentes d "
                + "JOIN Pessoas p2 ON p2.id = d.dependente_id "
                + "WHERE d.associado_id = ?";

        try {
            conn = objConexao.getConexao();  // Obtain the connection
            try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
                pstmt.setString(1, cpf);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        obj = new Associado();
                        obj.setId(rs.getInt("id"));
                        obj.setNome(rs.getString("nome"));
                        obj.setData_nasc(rs.getString("data_nasc"));
                        obj.setEndereco(rs.getString("endereco"));
                        obj.setTelefone(rs.getString("telefone"));
                        obj.setEmail(rs.getString("email"));
                        obj.setRG(rs.getString("rg"));
                        obj.setCpf(rs.getString("cpf"));
                        obj.setProfissao(rs.getString("profissao"));

                        // Fetch Dependentes
                        try (PreparedStatement pstmtDependentes = conn.prepareStatement(SQLDependentes)) {
                            pstmtDependentes.setInt(1, obj.getId());
                            try (ResultSet rsDependentes = pstmtDependentes.executeQuery()) {
                                Vector<Dependente> dependentes = new Vector<>();
                                while (rsDependentes.next()) {
                                    Dependente dependente = new Dependente();
                                    dependente.setId(rsDependentes.getInt("dependente_id"));
                                    dependente.setNome(rsDependentes.getString("nome"));
                                    dependente.setData_nasc(rsDependentes.getString("data_nasc"));
                                    dependente.setEndereco(rsDependentes.getString("endereco"));
                                    dependente.setTelefone(rsDependentes.getString("telefone"));
                                    dependente.setEmail(rsDependentes.getString("email"));
                                    dependente.setRG(rsDependentes.getString("rg"));
                                    dependente.setCpf(rsDependentes.getString("cpf"));

                                    // Assuming Parentesco is another object
                                    Parentesco parentesco = new Parentesco();
                                    parentesco.setId(rsDependentes.getInt("parentesco_id"));
                                    dependente.setParentesco(parentesco);

                                    dependentes.add(dependente);
                                }
                                obj.setDependente(dependentes);
                            }
                        }
                    }
                }
            }
        } catch (SQLException e) {
            // Log or handle exception as needed
            throw new Exception("Error fetching Associado by CPF: " + e.getMessage(), e);
        } finally {
            if (conn != null) {
                objConexao.closeConnection(conn);  // Close the connection properly
            }
        }

        return obj;
    }

    public Vector<Associado> carregarCombo() throws Exception {
        Vector<Associado> dados = new Vector<Associado>();
        Conexao objConexao = new Conexao();
        String SQL = "select id, nome, cpf from Pessoas p, Associados a"
                + " where p.id = a.associado_id";

        PreparedStatement pstmt = objConexao.getConexao().prepareStatement(SQL);
        ResultSet rs = pstmt.executeQuery();
        dados.add(new Associado(0, "<Selecione>", "", "", "", "", "", "", "", null));
        while (rs.next()) {
            Associado obj = new Associado();
            obj.setId(rs.getInt("id"));
            obj.setNome(rs.getString("nome").trim());
            obj.setCpf(rs.getString("CPF"));
            dados.add(obj);
        }
        return dados;
    }

    public Vector listar(String arg) throws SQLException, IllegalAccessException, ClassNotFoundException, Exception {

        Vector dados = new Vector();
        Conexao objConexao = new Conexao();
        String SQL = "select DISTINCT(A.associado_id),B.nome, B.CPF, "
                + "CASE WHEN D.data_pgto > data_venc THEN 'DEVENDO' ELSE 'PAGO' END status_da_mensalidade "
                + "from Associados A JOIN Pessoas B On B.id = A.associado_id "
                + "JOIN Contratos_Titulos C on B.id = C.associado_id "
                + "JOIN Mensalidades D ON C.id = D.contrato_id ";

        if (arg != null && !arg.trim().isEmpty()) {
            SQL += "WHERE B.nome LIKE ? ";
        }

        SQL += "GROUP BY A.associado_id, B.nome, B.CPF, D.data_pgto, D.data_venc";

        PreparedStatement pstmt = objConexao.getConexao().prepareStatement(SQL);

        if (arg != null && !arg.trim().isEmpty()) {
            pstmt.setString(1, "%" + arg + "%");
        }

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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import model.Associado;
import model.Categoria;
import model.Contrato;
import model.Funcionario;

/**
 *
 * @author MaqLab
 */
public class ContratoData {

    private static final int PARCELAS_GERADAS_AUTOMATICAMENTE = 12;

    /**
     * Registra a venda de um título: grava o contrato (nº do título gerado
     * automaticamente pelo banco - R1.2), registra a venda no log (R1.3) e
     * gera as mensalidades do contrato (R2.1). Tudo em uma única transação.
     */
    public boolean incluir(Contrato obj) throws Exception {
        Conexao objConexao = new Conexao();
        String sql = "Insert into Contratos_Titulos (data_contrato, data_cancel, status, associado_id, funcionario_id, categorias_id) values(?,?,?,?,?,?)";
        try (Connection conn = objConexao.getConexao()) {
            conn.setAutoCommit(false);
            try {
                SimpleDateFormat originalFormat = new SimpleDateFormat("dd/MM/yyyy");
                SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");

                Date dataContratoParsed;
                try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                    // Handle data_contrato
                    String dataContrato = obj.getDataContrato().trim();
                    if (dataContrato.isEmpty() || dataContrato.equals("/  /")) {
                        pstmt.setNull(1, java.sql.Types.DATE);
                        dataContratoParsed = new Date();
                    } else {
                        dataContratoParsed = originalFormat.parse(dataContrato);
                        pstmt.setString(1, targetFormat.format(dataContratoParsed));
                    }

                    String dataCancelamento = obj.getDataCancelamento().trim();
                    if (dataCancelamento.isEmpty() || dataCancelamento.equals("/  /")) {
                        pstmt.setNull(2, java.sql.Types.DATE);
                    } else {
                        Date date2 = originalFormat.parse(dataCancelamento);
                        pstmt.setString(2, targetFormat.format(date2));
                    }

                    pstmt.setInt(3, obj.getStatus());
                    pstmt.setInt(4, obj.getAssociado().getId());
                    pstmt.setInt(5, obj.getFuncionario().getId());
                    pstmt.setInt(6, obj.getCategoria().getId());

                    int registros = pstmt.executeUpdate();
                    if (registros == 0) {
                        conn.rollback();
                        return false;
                    }

                    try (ResultSet keys = pstmt.getGeneratedKeys()) {
                        if (!keys.next()) {
                            throw new SQLException("Falha ao gerar o número do título.");
                        }
                        obj.setNroContrato(keys.getInt(1));
                    }
                }

                registrarLogVenda(conn, obj);
                gerarMensalidades(conn, obj, dataContratoParsed, targetFormat);

                conn.commit();
                return true;
            } catch (Exception e) {
                conn.rollback();
                throw e;
            } finally {
                conn.setAutoCommit(true);
            }
        }
    }

    private void registrarLogVenda(Connection conn, Contrato obj) throws SQLException {
        String sql = "Insert into Log_Vendas (contrato_id, associado_id, funcionario_id, data_hora, descricao) values (?,?,?,?,?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, obj.getNroContrato());
            pstmt.setInt(2, obj.getAssociado().getId());
            pstmt.setInt(3, obj.getFuncionario().getId());
            pstmt.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            pstmt.setString(5, "Venda do título nº " + obj.getNroContrato()
                    + " (categoria " + obj.getCategoria().getDescricao() + ") registrada com sucesso.");
            pstmt.executeUpdate();
        }
    }

    private void gerarMensalidades(Connection conn, Contrato obj, Date dataContrato, SimpleDateFormat targetFormat) throws SQLException {
        String sql = "Insert into Mensalidades (preco, data_venc, valor, mes_ref, contrato_id) values (?,?,?,?,?)";
        float valorMensalidade = obj.getCategoria().getValor();
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(dataContrato);
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (int mesRef = 1; mesRef <= PARCELAS_GERADAS_AUTOMATICAMENTE; mesRef++) {
                calendario.add(Calendar.MONTH, 1);
                pstmt.setFloat(1, valorMensalidade);
                pstmt.setString(2, targetFormat.format(calendario.getTime()));
                pstmt.setFloat(3, valorMensalidade);
                pstmt.setInt(4, mesRef);
                pstmt.setInt(5, obj.getNroContrato());
                pstmt.addBatch();
            }
            pstmt.executeBatch();
        }
    }

    public boolean excluir(int id) throws Exception {
        Conexao objConexao = new Conexao();
        String sql = "DELETE FROM Contratos_Titulos WHERE id = ?";
        try (Connection conn = objConexao.getConexao(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int registros = pstmt.executeUpdate();
            return registros > 0;
        }
    }

    public boolean editar(Contrato obj) throws Exception {
        Conexao objConexao = new Conexao();
        String sql = "Update  Contratos_Titulos set status = ?, data_cancel  = ?, data_contrato = ? where id = ?";
        try (Connection conn = objConexao.getConexao(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, obj.getStatus());
            pstmt.setString(2, obj.getDataCancelamento());
            pstmt.setString(3, obj.getDataContrato());
            int registros = pstmt.executeUpdate();
            return registros > 0;
        }
    }

    public Contrato pesquisar(int id) throws Exception {
        Contrato obj = null;
        Conexao objConexao = new Conexao();
        String sql = " select data_contrato, data_cancel, status, a.associado_id, f.funcionario_id , c.categorias_id "
                + " from  Contratos_Titulos c Join  Funcionarios f on c.funcionario_id = f.funcionario_id "
                + " Join Associados a on c.associado_id = a.associado_id "
                + " Join Categorias ca on c.categorias_id = ca.id "
                + "where c.id = ?";
        try (Connection conn = objConexao.getConexao(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    obj = new Contrato();
                    Date dataContrato = rs.getDate("data_contrato");
                    String dataContratoFormatada = (dataContrato != null) ? new SimpleDateFormat("dd/MM/yyyy").format(dataContrato) : "";
                    obj.setDataContrato(dataContratoFormatada);

                    Date dataCancel = rs.getDate("data_cancel");
                    String dataCancelFormatada = (dataCancel != null) ? new SimpleDateFormat("dd/MM/yyyy").format(dataCancel) : "";
                    obj.setDataCancelamento(dataCancelFormatada);
                    obj.setStatus(rs.getInt("status"));
                    Associado obj2 = new Associado();
                    obj2.setId(rs.getInt("associado_id"));
                    obj.setAssociado(obj2);

                    Funcionario obj3 = new Funcionario();
                    obj3.setId(rs.getInt("funcionario_id"));
                    obj.setFuncionario(obj3);

                    Categoria obj4 = new Categoria();
                    obj4.setId(rs.getInt("categorias_id"));
                    obj.setCategoria(obj4);
                }
            }
        }
        return obj;
    }

    public Vector<Contrato> listarContratos() throws Exception {
        Conexao objConexao = new Conexao();
        Vector<Contrato> dados = new Vector<Contrato>();
        String sql = "Select * from  Contratos_Titulos order by id";
        try (Connection conn = objConexao.getConexao(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Contrato obj = new Contrato();
                obj.setNroContrato(rs.getInt("id"));
                obj.setDataContrato(rs.getString("data_contrato"));
                obj.setDataCancelamento(rs.getString("data_cancel"));
                obj.setStatus(rs.getInt("status"));
                dados.add(obj);
            }
        }
        return dados;
    }

    public Vector listarTodos() throws Exception {
        Conexao objConexao = new Conexao();
        Vector dados = new Vector();
        String sql = "Select * from  Contratos_Titulos order by id";
        try (Connection conn = objConexao.getConexao(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Vector linha = new Vector();
                linha.add(rs.getInt("id"));
                linha.add(rs.getString("data_contrato"));
                linha.add(rs.getString("data_cancel"));
                linha.add(rs.getInt("status"));
                dados.add(linha);
            }
        }
        return dados;
    }

    /** R1.11 - quantidade de títulos vendidos. */
    public int contarTitulosVendidos() throws Exception {
        Conexao objConexao = new Conexao();
        String sql = "select count(*) total from Contratos_Titulos";
        try (Connection conn = objConexao.getConexao(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            return rs.next() ? rs.getInt("total") : 0;
        }
    }

    public Vector<Contrato> carregarCombo() throws Exception {
        Vector<Contrato> dados = new Vector<Contrato>();
        Conexao objConexao = new Conexao();
        String sql = "select id  from  Contratos_Titulos c ";
        try (Connection conn = objConexao.getConexao(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            dados.add(new Contrato(0, "<Selecione>", "", null, null, 0, null));
            while (rs.next()) {
                Contrato obj = new Contrato();
                obj.setNroContrato(rs.getInt("id"));
                dados.add(obj);
            }
        }
        return dados;
    }
}

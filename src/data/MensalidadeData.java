/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import model.Contrato;
import model.Mensalidade;

/**
 *
 * @author MaqLab
 */
public class MensalidadeData {

    public boolean incluir(Mensalidade obj) throws Exception {
        Conexao objConexao = new Conexao();
        // id é AUTO_INCREMENT e forma_pagamento/valor_recebido/troco/juros_aplicado só
        // existem depois que o pagamento é registrado, então uma mensalidade nova entra
        // sempre com data_pgto em aberto (NULL) e sem forma de pagamento.
        String sql = "Insert into Mensalidades (preco, data_pgto, data_venc, valor, mes_ref, contrato_id) values (?,?,?,?,?,?)";
        try (Connection conn = objConexao.getConexao(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setFloat(1, obj.getPreco());
            setDataOuNula(pstmt, 2, obj.getDataPgto());
            pstmt.setString(3, convertToDate(obj.getDataVenc()));
            pstmt.setFloat(4, obj.getValor());
            pstmt.setInt(5, Integer.parseInt(obj.getMesRef()));
            pstmt.setInt(6, obj.getContrato().getNroContrato());
            int registros = pstmt.executeUpdate();
            return registros > 0;
        }
    }

    private String convertToDate(String date) throws Exception {
        SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
        return outputFormat.format(inputFormat.parse(date));
    }

    /** data_pgto é opcional (mensalidade ainda não paga): grava NULL em vez de estourar o parse. */
    private void setDataOuNula(PreparedStatement pstmt, int indice, String dataBr) throws Exception {
        if (dataBr == null || dataBr.trim().isEmpty()) {
            pstmt.setNull(indice, Types.DATE);
        } else {
            pstmt.setString(indice, convertToDate(dataBr));
        }
    }

    public boolean editar(Mensalidade obj) throws Exception {
        Conexao objConexao = new Conexao();
        String sql = "Update Mensalidades set  mes_ref = ?,  valor = ?, data_venc = ?, data_pgto = ?, preco = ? where id = ?";
        try (Connection conn = objConexao.getConexao(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, Integer.parseInt(obj.getMesRef()));
            pstmt.setFloat(2, obj.getValor());
            pstmt.setString(3, convertToDate(obj.getDataVenc()));
            setDataOuNula(pstmt, 4, obj.getDataPgto());
            pstmt.setFloat(5, obj.getPreco());
            pstmt.setInt(6, obj.getId());
            int registros = pstmt.executeUpdate();
            return registros > 0;
        }
    }

    public boolean registrarPagamento(int id, String dataPagamento) throws Exception {
        Conexao objConexao = new Conexao();
        String sql = "Update Mensalidades set data_pgto = ? where id = ?";
        try (Connection conn = objConexao.getConexao(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, convertToDate(dataPagamento));
            pstmt.setInt(2, id);
            int registros = pstmt.executeUpdate();
            return registros > 0;
        }
    }

    /**
     * Registra o pagamento informando a forma utilizada (R2.3 dinheiro/troco,
     * R2.4 cartão, R2.5 cheque). Pagamentos em cartão são lançados também em
     * Contas a Receber (R2.6), já que a operadora repassa o valor depois.
     */
    public boolean registrarPagamento(int id, String dataPagamento, String formaPagamento, Float valorRecebido, Float troco) throws Exception {
        Conexao objConexao = new Conexao();
        String sql = "Update Mensalidades set data_pgto = ?, forma_pagamento = ?, valor_recebido = ?, troco = ? where id = ?";
        try (Connection conn = objConexao.getConexao(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, convertToDate(dataPagamento));
            pstmt.setString(2, formaPagamento);
            if (valorRecebido != null) {
                pstmt.setFloat(3, valorRecebido);
            } else {
                pstmt.setNull(3, Types.FLOAT);
            }
            if (troco != null) {
                pstmt.setFloat(4, troco);
            } else {
                pstmt.setNull(4, Types.FLOAT);
            }
            pstmt.setInt(5, id);
            int registros = pstmt.executeUpdate();
            if (registros > 0 && "CARTAO".equals(formaPagamento)) {
                registrarContaReceber(conn, id);
            }
            return registros > 0;
        }
    }

    private void registrarContaReceber(Connection conn, int mensalidadeId) throws SQLException {
        float valor = 0;
        try (PreparedStatement pstmt = conn.prepareStatement("select valor from Mensalidades where id = ?")) {
            pstmt.setInt(1, mensalidadeId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    valor = rs.getFloat("valor");
                }
            }
        }
        String sql = "Insert into Contas_Receber (mensalidade_id, valor, data_registro, status) values (?, ?, ?, 'PENDENTE')";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, mensalidadeId);
            pstmt.setFloat(2, valor);
            pstmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            pstmt.executeUpdate();
        }
    }

    /**
     * R2.7 - baixa automática: concilia o retorno bancário/da operadora de
     * cartão (simulado) marcando como pagas as mensalidades cujo lançamento
     * em Contas a Receber já foi liquidado.
     */
    public int baixarAutomaticamente() throws Exception {
        Conexao objConexao = new Conexao();
        String sqlSelect = "select cr.id as cr_id, cr.mensalidade_id from Contas_Receber cr "
                + "join Mensalidades m on m.id = cr.mensalidade_id "
                + "where cr.status = 'PENDENTE' and m.data_pgto is null";
        int quantidade = 0;
        try (Connection conn = objConexao.getConexao()) {
            conn.setAutoCommit(false);
            try {
                String hoje = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                try (PreparedStatement pstmtSelect = conn.prepareStatement(sqlSelect);
                        ResultSet rs = pstmtSelect.executeQuery();
                        PreparedStatement pstmtBaixa = conn.prepareStatement("Update Mensalidades set data_pgto = ? where id = ?");
                        PreparedStatement pstmtConta = conn.prepareStatement("Update Contas_Receber set status = 'RECEBIDO' where id = ?")) {
                    while (rs.next()) {
                        pstmtBaixa.setString(1, hoje);
                        pstmtBaixa.setInt(2, rs.getInt("mensalidade_id"));
                        pstmtBaixa.addBatch();

                        pstmtConta.setInt(1, rs.getInt("cr_id"));
                        pstmtConta.addBatch();
                        quantidade++;
                    }
                    if (quantidade > 0) {
                        pstmtBaixa.executeBatch();
                        pstmtConta.executeBatch();
                    }
                }
                conn.commit();
            } catch (Exception e) {
                conn.rollback();
                throw e;
            } finally {
                conn.setAutoCommit(true);
            }
        }
        return quantidade;
    }

    /**
     * R2.9 - processa nova cobrança aos inadimplentes, aplicando 2% de juros
     * sobre as mensalidades vencidas e ainda não pagas (uma única vez).
     */
    public int aplicarJurosAtrasados() throws Exception {
        Conexao objConexao = new Conexao();
        String sqlSelect = "select id, valor from Mensalidades where data_pgto is null and data_venc < CURDATE() and juros_aplicado = 0";
        int quantidade = 0;
        try (Connection conn = objConexao.getConexao();
                PreparedStatement pstmtSelect = conn.prepareStatement(sqlSelect);
                ResultSet rs = pstmtSelect.executeQuery();
                PreparedStatement pstmtUpdate = conn.prepareStatement("Update Mensalidades set valor = ?, juros_aplicado = 1 where id = ?")) {
            while (rs.next()) {
                float novoValor = rs.getFloat("valor") * 1.02f;
                pstmtUpdate.setFloat(1, novoValor);
                pstmtUpdate.setInt(2, rs.getInt("id"));
                pstmtUpdate.addBatch();
                quantidade++;
            }
            if (quantidade > 0) {
                pstmtUpdate.executeBatch();
            }
        }
        return quantidade;
    }

    /**
     * R2.10 - valor total de pagamentos recebidos no período informado
     * (mensal, trimestral ou anual, conforme o intervalo de datas passado).
     */
    public float totalRecebidoNoPeriodo(String dataInicioBr, String dataFimBr) throws Exception {
        Conexao objConexao = new Conexao();
        String sql = "select COALESCE(SUM(valor),0) total from Mensalidades where data_pgto between ? and ?";
        try (Connection conn = objConexao.getConexao(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, convertToDate(dataInicioBr));
            pstmt.setString(2, convertToDate(dataFimBr));
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next() ? rs.getFloat("total") : 0f;
            }
        }
    }

    public boolean excluir(int id) throws Exception {
        Conexao objConexao = new Conexao();
        String sql = "Delete from Mensalidades where id = ?";
        try (Connection conn = objConexao.getConexao(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int registros = pstmt.executeUpdate();
            return registros > 0;
        }
    }

    public Mensalidade pesquisar(int id) throws Exception {
        Mensalidade obj = null;
        Conexao objConexao = new Conexao();
        String sql
                = "select m.id, preco, data_pgto, data_venc, valor, contrato_id, mes_ref "
                + " from Mensalidades m, Contratos_Titulos c  where c.id = m.contrato_id and m.id = ? ";

        try (Connection conn = objConexao.getConexao(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    obj = mapear(rs);
                }
            }
        }
        return obj;
    }

    public Mensalidade pesquisarPorContrato(int nroContrato) throws Exception {
        Mensalidade obj = null;
        Conexao objConexao = new Conexao();
        String sql
                = "select m.id, preco, data_pgto, data_venc, valor, contrato_id, mes_ref "
                + " from Mensalidades m where m.contrato_id = ? order by m.id desc limit 1";

        try (Connection conn = objConexao.getConexao(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, nroContrato);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    obj = mapear(rs);
                }
            }
        }
        return obj;
    }

    public Mensalidade pesquisarPendentePorAssociado(int associadoId) throws Exception {
        Conexao objConexao = new Conexao();
        String sql = "select m.id, preco, data_pgto, data_venc, valor, contrato_id, mes_ref "
                + "from Mensalidades m join Contratos_Titulos c on c.id = m.contrato_id "
                + "where c.associado_id = ? and m.data_pgto is null "
                + "order by m.data_venc asc limit 1";
        try (Connection conn = objConexao.getConexao(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, associadoId);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next() ? mapear(rs) : null;
            }
        }
    }

    public Mensalidade pesquisarPendentePorDependente(int dependenteId) throws Exception {
        Conexao objConexao = new Conexao();
        String sql = "select m.id, preco, data_pgto, data_venc, valor, contrato_id, mes_ref "
                + "from Mensalidades m "
                + "join Contratos_Titulos c on c.id = m.contrato_id "
                + "join Dependentes d on d.associado_id = c.associado_id "
                + "where d.dependente_id = ? and m.data_pgto is null "
                + "order by m.data_venc asc limit 1";
        try (Connection conn = objConexao.getConexao(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, dependenteId);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next() ? mapear(rs) : null;
            }
        }
    }

    public Vector<Mensalidade> historicoPorAssociado(int associadoId) throws Exception {
        Conexao objConexao = new Conexao();
        Vector<Mensalidade> dados = new Vector<>();
        String sql = "select m.id, preco, data_pgto, data_venc, valor, contrato_id, mes_ref "
                + "from Mensalidades m join Contratos_Titulos c on c.id = m.contrato_id "
                + "where c.associado_id = ? order by m.data_venc asc";
        try (Connection conn = objConexao.getConexao(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, associadoId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    dados.add(mapear(rs));
                }
            }
        }
        return dados;
    }

    public Vector<Mensalidade> historicoPorDependente(int dependenteId) throws Exception {
        Conexao objConexao = new Conexao();
        Vector<Mensalidade> dados = new Vector<>();
        String sql = "select m.id, preco, data_pgto, data_venc, valor, contrato_id, mes_ref "
                + "from Mensalidades m "
                + "join Contratos_Titulos c on c.id = m.contrato_id "
                + "join Dependentes d on d.associado_id = c.associado_id "
                + "where d.dependente_id = ? order by m.data_venc asc";
        try (Connection conn = objConexao.getConexao(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, dependenteId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    dados.add(mapear(rs));
                }
            }
        }
        return dados;
    }

    private Mensalidade mapear(ResultSet rs) throws Exception {
        SimpleDateFormat formatoBr = new SimpleDateFormat("dd/MM/yyyy");
        Mensalidade obj = new Mensalidade();
        obj.setId(rs.getInt("id"));
        obj.setPreco(rs.getFloat("preco"));
        Date dataPgto = rs.getDate("data_pgto");
        obj.setDataPgto(dataPgto != null ? formatoBr.format(dataPgto) : "");
        Date dataVenc = rs.getDate("data_venc");
        obj.setDataVenc(dataVenc != null ? formatoBr.format(dataVenc) : "");
        obj.setValor(rs.getFloat("valor"));
        Contrato obj2 = new Contrato();
        obj2.setNroContrato(rs.getInt("contrato_id"));
        obj.setContrato(obj2);
        obj.setMesRef(String.valueOf(rs.getInt("mes_ref")));
        return obj;
    }

}

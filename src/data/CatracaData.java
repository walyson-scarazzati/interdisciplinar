package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Simula a catraca de entrada do clube (R2.11): consulta o status de
 * pagamento do associado ou dependente que passou a carteirinha e informa se
 * o acesso deve ser liberado ou bloqueado.
 */
public class CatracaData {

    public static class Resultado {

        public final boolean encontrado;
        public final String nome;
        public final String statusMensalidade;
        public final boolean acessoPermitido;

        public Resultado(boolean encontrado, String nome, String statusMensalidade, boolean acessoPermitido) {
            this.encontrado = encontrado;
            this.nome = nome;
            this.statusMensalidade = statusMensalidade;
            this.acessoPermitido = acessoPermitido;
        }
    }

    public Resultado verificarAssociado(String numeroCarteirinha) throws Exception {
        String sql = "select B.nome, " + StatusMensalidadeSql.EXPRESSAO + " status_da_mensalidade "
                + "from Associados A JOIN Pessoas B On B.id = A.associado_id "
                + "JOIN Contratos_Titulos C on B.id = C.associado_id "
                + "JOIN Mensalidades D ON C.id = D.contrato_id "
                + "where B.numero_carteirinha = ? "
                + "GROUP BY A.associado_id, B.nome";
        return consultar(sql, numeroCarteirinha);
    }

    public Resultado verificarDependente(String numeroCarteirinha) throws Exception {
        String sql = "SELECT B.nome, " + StatusMensalidadeSql.EXPRESSAO + " AS status_da_mensalidade "
                + "FROM Dependentes A "
                + "JOIN Pessoas B ON B.id = A.dependente_id "
                + "JOIN Associados E ON E.associado_id = A.associado_id "
                + "JOIN Contratos_Titulos C ON E.associado_id = C.associado_id "
                + "JOIN Mensalidades D ON C.id = D.contrato_id "
                + "where B.numero_carteirinha = ? "
                + "GROUP BY A.dependente_id, B.nome";
        return consultar(sql, numeroCarteirinha);
    }

    private Resultado consultar(String sql, String numeroCarteirinha) throws Exception {
        Conexao objConexao = new Conexao();
        try (Connection conn = objConexao.getConexao(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, numeroCarteirinha);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (!rs.next()) {
                    return new Resultado(false, null, null, false);
                }
                String status = rs.getString("status_da_mensalidade");
                boolean permitido = !"EM ATRASO".equals(status);
                return new Resultado(true, rs.getString("nome"), status, permitido);
            }
        }
    }
}

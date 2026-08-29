package data;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Expressão SQL e utilitários compartilhados para calcular o status de
 * pagamento (PAGO / VENCE EM BREVE / EM ATRASO / PENDENTE) de uma pessoa a
 * partir das mensalidades ligadas ao seu contrato.
 */
public class StatusMensalidadeSql {

    public static final String EXPRESSAO =
            "CASE "
            + "WHEN SUM(CASE WHEN D.data_pgto IS NULL THEN 1 ELSE 0 END) = 0 THEN 'PAGO' "
            + "WHEN MIN(CASE WHEN D.data_pgto IS NULL THEN D.data_venc END) < CURDATE() THEN 'EM ATRASO' "
            + "WHEN MIN(CASE WHEN D.data_pgto IS NULL THEN D.data_venc END) <= DATE_ADD(CURDATE(), INTERVAL 5 DAY) THEN 'VENCE EM BREVE' "
            + "ELSE 'PENDENTE' END";

    public static String formatarData(java.sql.Date data) {
        return data != null ? new SimpleDateFormat("dd/MM/yyyy").format((Date) data) : "";
    }
}

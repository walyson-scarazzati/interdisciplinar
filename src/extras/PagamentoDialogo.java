package extras;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 * Conduz o funcionário pelo fluxo de recebimento de uma mensalidade,
 * cobrindo as formas de pagamento em dinheiro (com cálculo de troco),
 * cartão de crédito e cheque.
 */
public class PagamentoDialogo {

    public static class Resultado {

        public final String formaPagamento; // DINHEIRO, CARTAO ou CHEQUE
        public final Float valorRecebido;   // apenas para pagamento em dinheiro
        public final Float troco;           // apenas para pagamento em dinheiro

        public Resultado(String formaPagamento, Float valorRecebido, Float troco) {
            this.formaPagamento = formaPagamento;
            this.valorRecebido = valorRecebido;
            this.troco = troco;
        }
    }

    private PagamentoDialogo() {
    }

    /**
     * Retorna null se o funcionário cancelar em qualquer etapa.
     */
    public static Resultado coletar(Component parent, float valorDevido) {
        String[] opcoes = {"Dinheiro", "Cartão de Crédito", "Cheque"};
        int escolha = JOptionPane.showOptionDialog(parent,
                "Valor devido: R$ " + String.format("%.2f", valorDevido) + "\nForma de pagamento:",
                "Registrar Pagamento", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, opcoes, opcoes[0]);

        if (escolha == 0) {
            return coletarDinheiro(parent, valorDevido);
        }
        if (escolha == 1) {
            return coletarCartao(parent);
        }
        if (escolha == 2) {
            return coletarCheque(parent);
        }
        return null;
    }

    private static Resultado coletarDinheiro(Component parent, float valorDevido) {
        while (true) {
            String entrada = JOptionPane.showInputDialog(parent,
                    "Quantia recebida em dinheiro (R$):", String.format("%.2f", valorDevido));
            if (entrada == null) {
                return null;
            }
            try {
                float recebido = Float.parseFloat(entrada.replace(",", "."));
                if (recebido < valorDevido) {
                    JOptionPane.showMessageDialog(parent, "Quantia insuficiente para cobrir o valor devido.");
                    continue;
                }
                float troco = recebido - valorDevido;
                JOptionPane.showMessageDialog(parent, "Troco a devolver: R$ " + String.format("%.2f", troco));
                return new Resultado("DINHEIRO", recebido, troco);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(parent, "Valor inválido.");
            }
        }
    }

    private static Resultado coletarCartao(Component parent) {
        String dados = JOptionPane.showInputDialog(parent,
                "Número do cartão (leitora ou digitação manual):");
        if (dados == null || dados.trim().isEmpty()) {
            return null;
        }
        JOptionPane.showMessageDialog(parent,
                "Autorização de crédito aprovada junto ao serviço externo (simulação via modem).\n"
                + "O valor será lançado em Contas a Receber até a operadora repassar o pagamento ao clube.");
        return new Resultado("CARTAO", null, null);
    }

    private static Resultado coletarCheque(Component parent) {
        String dados = JOptionPane.showInputDialog(parent,
                "Dados de identificação do cliente (nome/documento):");
        if (dados == null || dados.trim().isEmpty()) {
            return null;
        }
        JOptionPane.showMessageDialog(parent,
                "Autorização de cheque aprovada junto ao serviço externo (simulação via modem).");
        return new Resultado("CHEQUE", null, null);
    }
}

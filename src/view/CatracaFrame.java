package view;

import data.CatracaData;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * Simula o monitor da catraca (R2.11): o funcionário informa o número da
 * carteirinha (associado ou dependente) e o sistema mostra se o pagamento
 * está em dia, liberando ou bloqueando a entrada.
 */
public class CatracaFrame extends javax.swing.JInternalFrame {

    private final JTextField campoId = new JTextField(10);
    private final JRadioButton radioAssociado = new JRadioButton("Associado", true);
    private final JRadioButton radioDependente = new JRadioButton("Dependente");
    private final JLabel resultado = new JLabel("Aguardando leitura da carteirinha...", SwingConstants.CENTER);

    public CatracaFrame() {
        super("Catraca - Verificação de Acesso", true, true, true, true);
        montarTela();
        setSize(480, 260);
    }

    private void montarTela() {
        JPanel topo = new JPanel();
        topo.add(new JLabel("Nº da carteirinha:"));
        topo.add(campoId);

        ButtonGroup grupo = new ButtonGroup();
        grupo.add(radioAssociado);
        grupo.add(radioDependente);
        topo.add(radioAssociado);
        topo.add(radioDependente);

        JButton verificar = new JButton("Passar Carteirinha");
        verificar.addActionListener(evt -> verificarAcesso());
        topo.add(verificar);

        resultado.setOpaque(true);
        resultado.setFont(new Font("Tahoma", Font.BOLD, 20));
        resultado.setBackground(Color.LIGHT_GRAY);
        resultado.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        JPanel raiz = new JPanel(new BorderLayout());
        raiz.add(topo, BorderLayout.NORTH);
        raiz.add(resultado, BorderLayout.CENTER);

        JPanel legenda = new JPanel(new GridLayout(1, 1));
        legenda.add(new JLabel("Se a catraca não estiver funcionando, pesquise o status manualmente em Controle de pagamento.", SwingConstants.CENTER));
        raiz.add(legenda, BorderLayout.SOUTH);

        setContentPane(raiz);
    }

    private void verificarAcesso() {
        String texto = campoId.getText().trim();
        if (texto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Informe o número da carteirinha.");
            return;
        }
        int id;
        try {
            id = Integer.parseInt(texto);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Número de carteirinha inválido.");
            return;
        }

        try {
            CatracaData dao = new CatracaData();
            CatracaData.Resultado consulta = radioAssociado.isSelected()
                    ? dao.verificarAssociado(id)
                    : dao.verificarDependente(id);

            if (!consulta.encontrado) {
                resultado.setBackground(Color.LIGHT_GRAY);
                resultado.setText("<html><center>Carteirinha não encontrada.</center></html>");
                return;
            }

            if (consulta.acessoPermitido) {
                resultado.setBackground(new Color(60, 179, 113));
                resultado.setText("<html><center>ACESSO PERMITIDO<br>" + consulta.nome + "</center></html>");
            } else {
                resultado.setBackground(new Color(205, 92, 92));
                resultado.setText("<html><center>ACESSO NEGADO<br>" + consulta.nome
                        + "<br>Mensalidade em atraso - procure um funcionário</center></html>");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao verificar acesso: " + ex.getMessage());
        }
    }
}

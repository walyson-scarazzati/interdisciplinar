/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import data.AssociadoData;
import data.ContratoData;
import data.DependenteData;
import data.MensalidadeData;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import model.Funcionario;

/**
 *
 * @author Walyson
 */
public class PrincipalFrame extends javax.swing.JFrame {

    /**
     * Creates new form PrincipalFrame
     */
    public PrincipalFrame() {
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        adicionarConfirmacaoDeSaida();
    }

    public PrincipalFrame(Funcionario obj) {
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        adicionarConfirmacaoDeSaida();
        adicionarMenuRelatorios();

        if (obj.getTipo() == 1) { //comum
            jmCadastrar.setEnabled(true);
            jmControlePagamento.setEnabled(true);
        }

        if (obj.getTipo() == 2) { //administrador
            jmCadastrar.setEnabled(true);
            jmControlePagamento.setEnabled(true);

        }
    }

    /**
     * Menu adicional com os relatórios/rotinas que não têm tela de cadastro
     * própria: contadores (R1.11/R1.12), catraca (R2.11), cobrança com juros
     * (R2.9), baixa automática (R2.7) e total de pagamentos por período
     * (R2.10). Montado em código, fora do bloco gerado pelo Form Editor.
     */
    private void adicionarMenuRelatorios() {
        JMenu jmRelatorios = new JMenu("Relatórios");
        jmRelatorios.setMnemonic(java.awt.event.KeyEvent.VK_R);

        JMenuItem itemTotais = new JMenuItem("Totais do Sistema");
        itemTotais.addActionListener(e -> exibirTotais());
        jmRelatorios.add(itemTotais);

        JMenuItem itemPagamentosPeriodo = new JMenuItem("Total de Pagamentos por Período");
        itemPagamentosPeriodo.addActionListener(e -> exibirTotalPagamentosPeriodo());
        jmRelatorios.add(itemPagamentosPeriodo);

        JMenuItem itemJuros = new JMenuItem("Processar Cobrança com Juros (Inadimplentes)");
        itemJuros.addActionListener(e -> processarCobrancaComJuros());
        jmRelatorios.add(itemJuros);

        JMenuItem itemBaixaAutomatica = new JMenuItem("Baixa Automática (Retorno Bancário)");
        itemBaixaAutomatica.addActionListener(e -> processarBaixaAutomatica());
        jmRelatorios.add(itemBaixaAutomatica);

        JMenuItem itemCatraca = new JMenuItem("Simulador de Catraca");
        itemCatraca.addActionListener(e -> abrirCatraca());
        jmRelatorios.add(itemCatraca);

        jmbPrincipal.add(jmRelatorios);
    }

    private void exibirTotais() {
        try {
            int titulos = new ContratoData().contarTitulosVendidos();
            int associados = new AssociadoData().contarAssociados();
            int dependentes = new DependenteData().contarDependentes();
            JOptionPane.showMessageDialog(this,
                    "Títulos vendidos: " + titulos + "\n"
                    + "Associados cadastrados: " + associados + "\n"
                    + "Dependentes cadastrados: " + dependentes,
                    "Totais do Sistema", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao calcular totais: " + ex.getMessage());
        }
    }

    private void exibirTotalPagamentosPeriodo() {
        String[] opcoes = {"Mensal", "Trimestral", "Anual"};
        int escolha = JOptionPane.showOptionDialog(this, "Selecione o período:", "Total de Pagamentos",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);
        if (escolha < 0) {
            return;
        }
        int meses = escolha == 0 ? 1 : escolha == 1 ? 3 : 12;

        Calendar calendario = Calendar.getInstance();
        Date fim = calendario.getTime();
        calendario.add(Calendar.MONTH, -meses);
        Date inicio = calendario.getTime();

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        try {
            float total = new MensalidadeData().totalRecebidoNoPeriodo(formato.format(inicio), formato.format(fim));
            JOptionPane.showMessageDialog(this,
                    "Total recebido (" + opcoes[escolha].toLowerCase() + ", de " + formato.format(inicio)
                    + " até " + formato.format(fim) + "): R$ " + String.format("%.2f", total));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao calcular total: " + ex.getMessage());
        }
    }

    private void processarCobrancaComJuros() {
        int confirmacao = JOptionPane.showConfirmDialog(this,
                "Aplicar juros de atraso (2%) às mensalidades vencidas e não pagas?",
                "Cobrança com Juros", JOptionPane.YES_NO_OPTION);
        if (confirmacao != JOptionPane.YES_OPTION) {
            return;
        }
        try {
            int quantidade = new MensalidadeData().aplicarJurosAtrasados();
            JOptionPane.showMessageDialog(this, quantidade + " mensalidade(s) atualizada(s) com juros de atraso.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao processar cobrança: " + ex.getMessage());
        }
    }

    private void processarBaixaAutomatica() {
        try {
            int quantidade = new MensalidadeData().baixarAutomaticamente();
            JOptionPane.showMessageDialog(this, quantidade
                    + " mensalidade(s) baixada(s) automaticamente a partir do retorno bancário/da operadora (simulado).");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro na baixa automática: " + ex.getMessage());
        }
    }

    private void abrirCatraca() {
        CatracaFrame obj = new CatracaFrame();
        int x1 = (jdpPrincipal.getSize().width - obj.getSize().width) / 2;
        obj.setBounds(x1, 80, obj.getSize().width, obj.getSize().height);
        jdpPrincipal.add(obj);
        obj.setVisible(true);
    }

    private void adicionarConfirmacaoDeSaida() {
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                sair();
            }
        });
    }

    private void sair() {
        int confirmacao = JOptionPane.showConfirmDialog(this,
                "Deseja realmente sair do sistema?", "Sair",
                JOptionPane.YES_NO_OPTION);
        if (confirmacao == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jdpPrincipal = new javax.swing.JDesktopPane();
        jmbPrincipal = new javax.swing.JMenuBar();
        jmCadastrar = new javax.swing.JMenu();
        jmiAssociado = new javax.swing.JMenuItem();
        jmiCategoria = new javax.swing.JMenuItem();
        jmiContrato = new javax.swing.JMenuItem();
        jmiDependente = new javax.swing.JMenuItem();
        jmiFuncionarios = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jmiMensalidade = new javax.swing.JMenuItem();
        jmiModalidade = new javax.swing.JMenuItem();
        jmiParentesco = new javax.swing.JMenuItem();
        jmiSair = new javax.swing.JMenuItem();
        jmControlePagamento = new javax.swing.JMenu();
        jmiControlePagamento = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Menu");
        setExtendedState(2);

        jmbPrincipal.setBackground(new java.awt.Color(153, 204, 255));
        jmbPrincipal.setForeground(new java.awt.Color(153, 204, 255));

        jmCadastrar.setText("Cadastrar");
        jmCadastrar.setMnemonic(java.awt.event.KeyEvent.VK_C);

        jmiAssociado.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jmiAssociado.setText("Associado");
        jmiAssociado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiAssociadoActionPerformed(evt);
            }
        });
        jmCadastrar.add(jmiAssociado);

        jmiCategoria.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_MASK));
        jmiCategoria.setText("Categoria");
        jmiCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiCategoriaActionPerformed(evt);
            }
        });
        jmCadastrar.add(jmiCategoria);

        jmiContrato.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jmiContrato.setText("Contrato");
        jmiContrato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiContratoActionPerformed(evt);
            }
        });
        jmCadastrar.add(jmiContrato);

        jmiDependente.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        jmiDependente.setText("Dependente");
        jmiDependente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiDependenteActionPerformed(evt);
            }
        });
        jmCadastrar.add(jmiDependente);

        jmiFuncionarios.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
        jmiFuncionarios.setText("Funcionário");
        jmiFuncionarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiFuncionariosActionPerformed(evt);
            }
        });
        jmCadastrar.add(jmiFuncionarios);
        jmCadastrar.add(jSeparator1);

        jmiMensalidade.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.CTRL_MASK));
        jmiMensalidade.setText("Mensalidade");
        jmiMensalidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiMensalidadeActionPerformed(evt);
            }
        });
        jmCadastrar.add(jmiMensalidade);

        jmiModalidade.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        jmiModalidade.setText("Modalidade Esportiva");
        jmiModalidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiModalidadeActionPerformed(evt);
            }
        });
        jmCadastrar.add(jmiModalidade);

        jmiParentesco.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        jmiParentesco.setText("Parentesco");
        jmiParentesco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiParentescoActionPerformed(evt);
            }
        });
        jmCadastrar.add(jmiParentesco);

        jmiSair.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        jmiSair.setText("Sair");
        jmiSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiSairActionPerformed(evt);
            }
        });
        jmCadastrar.add(jmiSair);

        jmbPrincipal.add(jmCadastrar);

        jmControlePagamento.setText("Controle de pagamento");
        jmControlePagamento.setMnemonic(java.awt.event.KeyEvent.VK_O);

        jmiControlePagamento.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jmiControlePagamento.setText("Controle de pagamento");
        jmiControlePagamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiControlePagamentoActionPerformed(evt);
            }
        });
        jmControlePagamento.add(jmiControlePagamento);

        jmbPrincipal.add(jmControlePagamento);

        setJMenuBar(jmbPrincipal);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jdpPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jdpPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jmiControlePagamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiControlePagamentoActionPerformed

//           new pgto().setVisible(true); 
        GereciamentoPagamento obj = new GereciamentoPagamento();
        int x1 = (jdpPrincipal.getSize().width - obj.getSize().width) / 2;

        obj.setBounds(x1, 80, obj.getSize().width, obj.getSize().height);
        jdpPrincipal.add(obj);
        obj.setVisible(true);

    }//GEN-LAST:event_jmiControlePagamentoActionPerformed

    private void jmiSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiSairActionPerformed
        sair();
    }//GEN-LAST:event_jmiSairActionPerformed

    private void jmiParentescoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiParentescoActionPerformed
        ParentescoFrame obj = new ParentescoFrame();
        jdpPrincipal.add(obj);

        int x1 = (jdpPrincipal.getSize().width - obj.getSize().width) / 2;

        obj.setBounds(x1, 80, obj.getSize().width, obj.getSize().height);
        obj.setVisible(true);
    }//GEN-LAST:event_jmiParentescoActionPerformed

    private void jmiModalidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiModalidadeActionPerformed
        ModalidadeFrame obj = new ModalidadeFrame();
        jdpPrincipal.add(obj);
        int x1 = (jdpPrincipal.getSize().width - obj.getSize().width) / 2;

        obj.setBounds(x1, 80, obj.getSize().width, obj.getSize().height);
        obj.setVisible(true);

        // TODO add your handling code here:
    }//GEN-LAST:event_jmiModalidadeActionPerformed

    private void jmiMensalidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiMensalidadeActionPerformed
        MensalidadeFrame obj = new MensalidadeFrame();
        int x1 = (jdpPrincipal.getSize().width - obj.getSize().width) / 2;

        obj.setBounds(x1, 80, obj.getSize().width, obj.getSize().height);
        jdpPrincipal.add(obj);
        obj.setVisible(true);
    }//GEN-LAST:event_jmiMensalidadeActionPerformed

    private void jmiFuncionariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiFuncionariosActionPerformed
        FuncionarioFrame obj = new FuncionarioFrame();
        int x1 = (jdpPrincipal.getSize().width - obj.getSize().width) / 2;

        obj.setBounds(x1, 80, obj.getSize().width, obj.getSize().height);
        jdpPrincipal.add(obj);
        obj.setVisible(true);

        // TODO add your handling code here:
    }//GEN-LAST:event_jmiFuncionariosActionPerformed

    private void jmiDependenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiDependenteActionPerformed
        DependenteFrame obj = new DependenteFrame();
        int x1 = (jdpPrincipal.getSize().width - obj.getSize().width) / 2;

        obj.setBounds(x1, 80, obj.getSize().width, obj.getSize().height);
        jdpPrincipal.add(obj);
        obj.setVisible(true);
    }//GEN-LAST:event_jmiDependenteActionPerformed

    private void jmiContratoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiContratoActionPerformed
        ContratoFrame obj = new ContratoFrame();
        int x1 = (jdpPrincipal.getSize().width - obj.getSize().width) / 2;

        obj.setBounds(x1, 80, obj.getSize().width, obj.getSize().height);
        jdpPrincipal.add(obj);

        obj.setVisible(true);
    }//GEN-LAST:event_jmiContratoActionPerformed

    private void jmiCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiCategoriaActionPerformed
        CategoriasFrame obj = new CategoriasFrame();

        int x1 = (jdpPrincipal.getSize().width - obj.getSize().width) / 2;

        // obj.setBounds(x1,80,obj.getSize().width,obj.getSize().height);
        jdpPrincipal.add(obj);

        obj.setVisible(true);

        // TODO add your handling code here:
    }//GEN-LAST:event_jmiCategoriaActionPerformed

    private void jmiAssociadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiAssociadoActionPerformed
        AssociadoFrame obj = new AssociadoFrame();
        int x1 = (jdpPrincipal.getSize().width - obj.getSize().width) / 2;

        obj.setBounds(x1, 80, obj.getSize().width, obj.getSize().height);
        jdpPrincipal.add(obj);
        obj.setVisible(true);
    }//GEN-LAST:event_jmiAssociadoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PrincipalFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PrincipalFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PrincipalFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PrincipalFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PrincipalFrame().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPopupMenu.Separator jSeparator1;
    public static javax.swing.JDesktopPane jdpPrincipal;
    private javax.swing.JMenu jmCadastrar;
    private javax.swing.JMenu jmControlePagamento;
    private javax.swing.JMenuBar jmbPrincipal;
    private javax.swing.JMenuItem jmiAssociado;
    private javax.swing.JMenuItem jmiCategoria;
    private javax.swing.JMenuItem jmiContrato;
    private javax.swing.JMenuItem jmiControlePagamento;
    private javax.swing.JMenuItem jmiDependente;
    private javax.swing.JMenuItem jmiFuncionarios;
    private javax.swing.JMenuItem jmiMensalidade;
    private javax.swing.JMenuItem jmiModalidade;
    private javax.swing.JMenuItem jmiParentesco;
    private javax.swing.JMenuItem jmiSair;
    // End of variables declaration//GEN-END:variables

}

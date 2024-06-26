/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Data.CategoriaData;
import Data.Modalidade_esportivaData;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import model.Categoria;
import model.Modalidade_esportiva;

/**
 *
 * @author MaqLab
 */
public class jifModalidade extends javax.swing.JInternalFrame {
    Modalidade_esportiva obj;
      Modalidade_esportivaData DAO;
       Vector<Categoria> vetorCategoria;
      int acao=0;
    /**
     * Creates new form jifModalidade
     */
    public jifModalidade() {
        initComponents();
         obj = new Modalidade_esportiva();
         DAO = new Modalidade_esportivaData();
        vetorCategoria = new Vector<Categoria>();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLid1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLid = new javax.swing.JLabel();
        jTid = new javax.swing.JTextField();
        jLdescricao = new javax.swing.JLabel();
        jTdescricao = new javax.swing.JTextField();
        jlmodalidade = new javax.swing.JLabel();
        jcCategoria = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbPesquisar = new javax.swing.JTable();
        jbAdicionar = new javax.swing.JButton();
        jbRemover = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jBnovo = new javax.swing.JButton();
        jBsalvar = new javax.swing.JButton();
        jbEditar = new javax.swing.JButton();
        jBcancelar = new javax.swing.JButton();
        jbExcluir = new javax.swing.JButton();
        jbPesquisar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameActivated(evt);
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        jPanel1.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.setForeground(new java.awt.Color(153, 204, 255));

        jLid1.setBackground(new java.awt.Color(0, 0, 102));
        jLid1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLid1.setForeground(new java.awt.Color(0, 0, 102));
        jLid1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLid1.setText("MODALIDADE ESPORTIVA");

        jLid.setBackground(new java.awt.Color(0, 0, 102));
        jLid.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLid.setForeground(new java.awt.Color(0, 0, 102));
        jLid.setText("ID");

        jTid.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTid.setToolTipText("Digite o id");
        jTid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTidActionPerformed(evt);
            }
        });

        jLdescricao.setBackground(new java.awt.Color(0, 0, 102));
        jLdescricao.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLdescricao.setForeground(new java.awt.Color(0, 0, 102));
        jLdescricao.setText("Descrição");

        jTdescricao.setToolTipText("Digite a descrição");

        jlmodalidade.setBackground(new java.awt.Color(0, 0, 102));
        jlmodalidade.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlmodalidade.setForeground(new java.awt.Color(0, 0, 102));
        jlmodalidade.setText("Categoria");

        jcCategoria.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jcCategoria.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecione" }));
        jcCategoria.setToolTipText("Selecione a Categoria");
        jcCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcCategoriaActionPerformed(evt);
            }
        });

        jtbPesquisar.setBackground(new java.awt.Color(255, 0, 0));
        jtbPesquisar.setForeground(new java.awt.Color(255, 0, 0));
        jtbPesquisar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IdModalidade", "Descricao", "Categoria"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jtbPesquisar);

        jbAdicionar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbAdicionar.setText("+");
        jbAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAdicionarActionPerformed(evt);
            }
        });

        jbRemover.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbRemover.setText("-");
        jbRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbRemoverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(131, 131, 131)
                            .addComponent(jbAdicionar)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jbRemover))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLid, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLdescricao)
                                .addComponent(jlmodalidade))
                            .addGap(40, 40, 40)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTid, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTdescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jcCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(103, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLid)
                    .addComponent(jTid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLdescricao)
                    .addComponent(jTdescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlmodalidade, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbAdicionar)
                    .addComponent(jbRemover))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(153, 204, 255));
        jPanel3.setForeground(new java.awt.Color(153, 204, 255));

        jBnovo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jBnovo.setText("Novo");
        jBnovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBnovoActionPerformed(evt);
            }
        });

        jBsalvar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jBsalvar.setText("Salvar");
        jBsalvar.setEnabled(false);
        jBsalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBsalvarActionPerformed(evt);
            }
        });

        jbEditar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbEditar.setText("Editar");
        jbEditar.setEnabled(false);
        jbEditar.setMinimumSize(new java.awt.Dimension(79, 23));
        jbEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbEditarActionPerformed(evt);
            }
        });

        jBcancelar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jBcancelar.setText("Cancelar");
        jBcancelar.setEnabled(false);
        jBcancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBcancelarActionPerformed(evt);
            }
        });

        jbExcluir.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbExcluir.setText("Excluir");
        jbExcluir.setEnabled(false);
        jbExcluir.setMinimumSize(new java.awt.Dimension(79, 23));
        jbExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbExcluirActionPerformed(evt);
            }
        });

        jbPesquisar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbPesquisar.setText("Pesquisar");
        jbPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbPesquisarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBnovo, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbPesquisar))
                .addGap(100, 100, 100)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBsalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(81, 81, 81)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jBcancelar)
                    .addComponent(jbExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBsalvar)
                    .addComponent(jBnovo)
                    .addComponent(jBcancelar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbPesquisar)))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLid1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLid1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 527, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbRemoverActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbRemoverActionPerformed

    private void jBnovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBnovoActionPerformed
        // TODO add your handling code here:

        jTid.setEditable(true);
        jTdescricao.setEditable(true);
        jcCategoria.setEnabled(true);
        jBnovo.setEnabled(false);
        jbPesquisar.setEnabled(false);
        jBsalvar.setEnabled(true);
        jBcancelar.setEnabled(true);
        jTid.requestFocus();
        limparCampos();
        acao = 1;
    }//GEN-LAST:event_jBnovoActionPerformed

    private void jBcancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBcancelarActionPerformed
        // TODO add your handling code here:

        jTid.setEditable(true);
        jTdescricao.setEditable(false);
        jcCategoria.setEnabled(true);
        jBnovo.setEnabled(true);
        jBsalvar.setEnabled(false);
        jBcancelar.setEnabled(false);
        jbPesquisar.setEnabled(true);
        jbEditar.setEnabled(false);
        jbExcluir.setEnabled(false);
        jTid.requestFocus();
        limparCampos();
    }//GEN-LAST:event_jBcancelarActionPerformed

    private void jTidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTidActionPerformed

    private void jcCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcCategoriaActionPerformed

        // TODO add your handling code here:
    }//GEN-LAST:event_jcCategoriaActionPerformed

    private void jbPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbPesquisarActionPerformed
        if(jTid.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Digite um id");
        } else {
            try {
                DAO = new Modalidade_esportivaData();
                obj = DAO.pesquisar(Integer.parseInt(jTid.getText()));
                if(obj==null){
                    JOptionPane.showMessageDialog(this, "Registro não encontrado !");
                } else {
                    jTdescricao.setText(obj.getDescricao());
                    jbEditar.setEnabled(true);
                    jbExcluir.setEnabled(true);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao pesquisar: " + ex.getMessage());
            }
        }
    }//GEN-LAST:event_jbPesquisarActionPerformed

    private void jBsalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBsalvarActionPerformed
        // TODO add your handling code here:

        try {
            if(validarCampos()){
                obj = new Modalidade_esportiva();
                if(preencherObjeto()){
                    DAO = new Modalidade_esportivaData();
                    if(acao==1) { //incluir
                        if(DAO.incluir(obj)) {
                            JOptionPane.showMessageDialog(this, "Salvo com sucesso !");
                            jBcancelarActionPerformed(evt);
                        }}
                        if(acao==2){
                            if(DAO.editar(obj)){
                                JOptionPane.showMessageDialog(this, "Alterado com sucesso !");
                                jBcancelarActionPerformed(evt);
                            }}
                        }
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erro ao salvar" +
                        ex.getMessage());
                }
    }//GEN-LAST:event_jBsalvarActionPerformed

    private void jbEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbEditarActionPerformed
        jTid.setEditable(false);
        jTdescricao.setEditable(true);
        jcCategoria.setEnabled(true);
        jBnovo.setEnabled(false);
        jBsalvar.setEnabled(true);
        jBcancelar.setEnabled(true);
        jbPesquisar.setEnabled(false);
        jbEditar.setEnabled(false);
        jbExcluir.setEnabled(false);
        acao=2;
    }//GEN-LAST:event_jbEditarActionPerformed

    private void jbAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAdicionarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbAdicionarActionPerformed

    private void jbExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbExcluirActionPerformed
        if(jTid.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Digite o id");
        } else {
            try {
                if(DAO.excluir(Integer.parseInt(jTid.getText()))){
                    JOptionPane.showMessageDialog(this, "Registro excluído com sucesso !");
                    jBcancelarActionPerformed(evt);
                } else {
                    JOptionPane.showMessageDialog(this, "Não foi possível excluir o registro");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Erro ao excluir:" + e.getMessage());
            }
        }
    }//GEN-LAST:event_jbExcluirActionPerformed

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
         CategoriaData DAOCategoria = new CategoriaData();
        try{
          vetorCategoria = DAOCategoria.carregarCombo();
          jcCategoria.setModel(new DefaultComboBoxModel(vetorCategoria));
    }catch(Exception e){
       JOptionPane.showMessageDialog(this, "erro " + e.getMessage());
        }        // TODO add your handling code here:
    }//GEN-LAST:event_formInternalFrameActivated

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBcancelar;
    private javax.swing.JButton jBnovo;
    private javax.swing.JButton jBsalvar;
    private javax.swing.JLabel jLdescricao;
    private javax.swing.JLabel jLid;
    private javax.swing.JLabel jLid1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTdescricao;
    private javax.swing.JTextField jTid;
    private javax.swing.JButton jbAdicionar;
    private javax.swing.JButton jbEditar;
    private javax.swing.JButton jbExcluir;
    private javax.swing.JButton jbPesquisar;
    private javax.swing.JButton jbRemover;
    private javax.swing.JComboBox jcCategoria;
    private javax.swing.JLabel jlmodalidade;
    private javax.swing.JTable jtbPesquisar;
    // End of variables declaration//GEN-END:variables

private void limparCampos() {
        jTid.setText("");
        jTdescricao.setText("");
        jcCategoria.setSelectedIndex(0);
       
    }

private boolean validarCampos() throws Exception {
        if(jTid.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Digite o id");
            jTid.requestFocus();
            return false;
        }
        if(jTdescricao.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Digite o nome do Descrição");
            jTdescricao.requestFocus();
            return false;
        }
        
        if(jcCategoria.getSelectedIndex() == 0){
            JOptionPane.showMessageDialog(this, "Digite a categoria");
            jTdescricao.requestFocus();
            return false;
        }
        
             return true;
    }


private boolean preencherObjeto() throws Exception {
        obj.setId(Integer.parseInt(jTid.getText()));
        obj.setDescricao(jTdescricao.getText());
         obj.setCategoria(vetorCategoria.
            get(jcCategoria.getSelectedIndex()));    
        return true;
    }

}

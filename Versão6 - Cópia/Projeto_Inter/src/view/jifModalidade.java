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
import javax.swing.table.DefaultTableModel;
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
    int acao = 0;

    /**
     * Creates new form jifModalidade
     */
    public jifModalidade() {
        initComponents();
        loadData();
        obj = new Modalidade_esportiva();
        DAO = new Modalidade_esportivaData();
        vetorCategoria = new Vector<Categoria>();
        jtbPesquisar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtbPesquisarMouseClicked(evt);
            }
        });
    }

    private void jtbPesquisarMouseClicked(java.awt.event.MouseEvent evt) {
        int selectedRow = jtbPesquisar.getSelectedRow();
        if (selectedRow >= 0) {
            jbEditar.setEnabled(true);
            jbExcluir.setEnabled(true);
        }
    }

    private void loadData() {
        try {
            DAO = new Modalidade_esportivaData();
            Vector<Modalidade_esportiva> dados = DAO.listarModalidade_esportiva();
            Vector<String> columnNames = new Vector<>();
            columnNames.add("Descrição");
            columnNames.add("Categoria");

            Vector<Vector<Object>> tableData = new Vector<>();

            for (Modalidade_esportiva modalidade : dados) {
                Vector<Object> row = new Vector<>();
                row.add(modalidade.getDescricao());
                Categoria categoria = modalidade.getCategoria();
                if (categoria != null) {
                    row.add(categoria.getDescricao());
                } else {
                    row.add("N/A");
                }

                tableData.add(row);
            }

            // Create the table model and set it to the JTable
            DefaultTableModel model = new DefaultTableModel(tableData, columnNames);
            jtbPesquisar.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar dados: " + e.getMessage());
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

        jPanel1 = new javax.swing.JPanel();
        jLid1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLdescricao = new javax.swing.JLabel();
        jTdescricao = new javax.swing.JTextField();
        jlmodalidade = new javax.swing.JLabel();
        jcCategoria = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbPesquisar = new javax.swing.JTable();
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
        jLid1.setText(" Modalidade Esportiva");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

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

        jtbPesquisar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Descrição", "Categoria de Plano"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jtbPesquisar);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLdescricao)
                            .addComponent(jlmodalidade))
                        .addGap(40, 40, 40)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTdescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jcCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLdescricao)
                    .addComponent(jTdescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlmodalidade, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
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
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLid1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
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
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBnovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBnovoActionPerformed

        jTdescricao.setEditable(true);
        jcCategoria.setEnabled(true);
        jBnovo.setEnabled(false);
        jbPesquisar.setEnabled(false);
        jBsalvar.setEnabled(true);
        jBcancelar.setEnabled(true);
        limparCampos();
        acao = 1;
    }//GEN-LAST:event_jBnovoActionPerformed

    private void jBcancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBcancelarActionPerformed
        // TODO add your handling code here:

        jTdescricao.setEditable(false);
        jcCategoria.setEnabled(true);
        jBnovo.setEnabled(true);
        jBsalvar.setEnabled(false);
        jBcancelar.setEnabled(false);
        jbPesquisar.setEnabled(true);
        jbEditar.setEnabled(false);
        jbExcluir.setEnabled(false);
        limparCampos();
    }//GEN-LAST:event_jBcancelarActionPerformed

    private void jbPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbPesquisarActionPerformed

        try {
            DAO = new Modalidade_esportivaData();
            obj = DAO.pesquisar(jTdescricao.getText());
            if (obj == null) {
                JOptionPane.showMessageDialog(this, "Registro não encontrado !");
            } else {
                jTdescricao.setText(obj.getDescricao());
                jbEditar.setEnabled(true);
                jbExcluir.setEnabled(true);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao pesquisar: " + ex.getMessage());
        }

    }//GEN-LAST:event_jbPesquisarActionPerformed

    private void jBsalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBsalvarActionPerformed

        try {
            if (validarCampos()) {
                obj = new Modalidade_esportiva();
                if (preencherObjeto()) {
                    DAO = new Modalidade_esportivaData();
                    boolean success = false;

                    if (acao == 1) {
                        success = DAO.incluir(obj);
                    } else if (acao == 2) {
                        int id = getSelectedRowId();
                        obj.setId(id);
                        success = DAO.editar(obj);
                    }

                    if (success) {
                        JOptionPane.showMessageDialog(this, "Operação realizada com sucesso !");
                        loadData(); // Refresh data
                        jBcancelarActionPerformed(evt); // Reset form
                    } else {
                        JOptionPane.showMessageDialog(this, "Não foi possível realizar a operação.");
                    }
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar: " + ex.getMessage());
        }
    }//GEN-LAST:event_jBsalvarActionPerformed

    private int getSelectedRowId() {
        int selectedRow = jtbPesquisar.getSelectedRow();

        if (selectedRow >= 0) {
            try {
                Object idObject = jtbPesquisar.getValueAt(selectedRow, 0);
                System.out.println("Valor obtido da tabela na coluna ID: " + idObject);

                if (idObject instanceof Integer) {
                    return (Integer) idObject;
                } else if (idObject instanceof String) {
                    try {
                        Modalidade_esportiva modalidade_esportiva = DAO.pesquisar(idObject.toString());
                        return modalidade_esportiva.getId();
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "ID inválido na tabela. Não é possível realizar operação. Valor encontrado: " + idObject);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Tipo de ID inválido na tabela. Não é possível realizar operação. Tipo encontrado: " + idObject.getClass().getName());
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Erro ao obter o ID da linha selecionada: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Nenhuma linha selecionada.");
        }

        return -1;
    }


    private void jbEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbEditarActionPerformed

        jTdescricao.setEditable(true);
        jcCategoria.setEnabled(true);
        jBnovo.setEnabled(false);
        jBsalvar.setEnabled(true);
        jBcancelar.setEnabled(true);
        jbPesquisar.setEnabled(false);
        jbEditar.setEnabled(false);
        jbExcluir.setEnabled(false);
        acao = 2;

        int selectedRow = getSelectedRowId();

        if (selectedRow >= 0) {
            try {

                DAO = new Modalidade_esportivaData();
                obj = DAO.pesquisarPorId(selectedRow);

                if (obj != null) {
                    jTdescricao.setText(obj.getDescricao());
                    //jcCategoria.setSelectedItem(obj.getCategoria());
                    if (jcCategoria.getItemCount() > 0) {
                        // Seleciona a categoria correspondente no combobox
                        for (int i = 0; i < jcCategoria.getItemCount(); i++) {
                            Categoria categoria = (Categoria) jcCategoria.getItemAt(i);
                            if (categoria.getId() == obj.getCategoria().getId()) {
                                jcCategoria.setSelectedIndex(i);
                                break;
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Erro: O combobox de categorias está vazio.");
                    }
                    jTdescricao.setEditable(true);
                    jcCategoria.setEnabled(true);
                    jBnovo.setEnabled(false);
                    jBsalvar.setEnabled(true);
                    jBcancelar.setEnabled(true);
                    jbPesquisar.setEnabled(false);
                    jbEditar.setEnabled(false);
                    jbExcluir.setEnabled(false);
                    acao = 2;
                } else {
                    JOptionPane.showMessageDialog(this, "Erro ao carregar os dados para edição.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Erro no formato do ID: " + e.getMessage());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Erro ao tentar editar: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecione um registro para editar.");
        }
    }//GEN-LAST:event_jbEditarActionPerformed

    private void jbExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbExcluirActionPerformed

        try {

            int selectedRow = getSelectedRowId();

            if (selectedRow >= 0) {
                if (DAO.excluir(selectedRow)) {
                    JOptionPane.showMessageDialog(this, "Registro excluído com sucesso!");
                    loadData();
                    jBcancelarActionPerformed(evt);
                } else {
                    JOptionPane.showMessageDialog(this, "Não foi possível excluir o registro");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, selecione uma linha para excluir.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Formato de ID inválido: " + e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao excluir: " + e.getMessage());
        }

    }//GEN-LAST:event_jbExcluirActionPerformed

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        CategoriaData DAOCategoria = new CategoriaData();
        try {
            vetorCategoria = DAOCategoria.carregarCombo();
            jcCategoria.setModel(new DefaultComboBoxModel(vetorCategoria));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "erro " + e.getMessage());
        }        // TODO add your handling code here:
    }//GEN-LAST:event_formInternalFrameActivated

    private void jcCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcCategoriaActionPerformed

        // TODO add your handling code here:
    }//GEN-LAST:event_jcCategoriaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBcancelar;
    private javax.swing.JButton jBnovo;
    private javax.swing.JButton jBsalvar;
    private javax.swing.JLabel jLdescricao;
    private javax.swing.JLabel jLid1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTdescricao;
    private javax.swing.JButton jbEditar;
    private javax.swing.JButton jbExcluir;
    private javax.swing.JButton jbPesquisar;
    private javax.swing.JComboBox jcCategoria;
    private javax.swing.JLabel jlmodalidade;
    private javax.swing.JTable jtbPesquisar;
    // End of variables declaration//GEN-END:variables

    private void limparCampos() {
        jTdescricao.setText("");
        jcCategoria.setSelectedIndex(0);

    }

    private boolean validarCampos() throws Exception {
        if (jTdescricao.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Digite o nome do Descrição");
            jTdescricao.requestFocus();
            return false;
        }

        if (jcCategoria.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Digite a categoria");
            jTdescricao.requestFocus();
            return false;
        }

        return true;
    }

    private boolean preencherObjeto() throws Exception {
        obj.setDescricao(jTdescricao.getText());
        obj.setCategoria(vetorCategoria.
                get(jcCategoria.getSelectedIndex()));
        return true;
    }

}

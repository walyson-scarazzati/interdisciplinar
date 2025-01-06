/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Data.CategoriaData;
import Data.Modalidade_esportivaData;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Categoria;
import model.Modalidade_esportiva;

/**
 *
 * @author MaqLab
 */
public class jifCategorias extends javax.swing.JInternalFrame {

    Categoria obj;
    Vector<Modalidade_esportiva> vetorModalidade_esportiva;
    CategoriaData DAO;
    int acao = 0;

    /**
     * Creates new form jifCategorias
     */
    public jifCategorias() {
        initComponents();
        obj = new Categoria();
        loadData();
        vetorModalidade_esportiva = new Vector<Modalidade_esportiva>();
        DAO = new CategoriaData();
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
            DAO = new CategoriaData();
            Vector<Categoria> dados = DAO.listar();
            Vector<String> columnNames = new Vector<>();
            columnNames.add("Descrição");
            columnNames.add("valor");
            Vector<Vector<Object>> tableData = new Vector<>();
            for (Categoria categoria : dados) {
                Vector<Object> row = new Vector<>();
                row.add(categoria.getDescricao());
                row.add(categoria.getValor());
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
        jLvalor = new javax.swing.JLabel();
        jTdescricao = new javax.swing.JTextField();
        jLdescricao = new javax.swing.JLabel();
        jTvalor = new javax.swing.JFormattedTextField();
        jTvalor = new extras.JMoneyField();
        //jFp_Precocusto = new extras.Moeda();

        jScrollPane1 = new javax.swing.JScrollPane();
        jtbPesquisar = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLid1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jBcancelar = new javax.swing.JButton();
        jBnovo = new javax.swing.JButton();
        jBsalvar = new javax.swing.JButton();
        jbPesquisar = new javax.swing.JButton();
        jbEditar = new javax.swing.JButton();
        jbExcluir = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLvalor.setText("Valor");

        jTdescricao.setToolTipText("Digite a descrição");

        jLdescricao.setText("Descrição");

        jTvalor.setEditable(false);
        jTvalor.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jTvalor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTvalorActionPerformed(evt);
            }
        });

        jtbPesquisar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Descrição", "Valor"
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
        if (jtbPesquisar.getColumnModel().getColumnCount() > 0) {
            jtbPesquisar.getColumnModel().getColumn(0).setResizable(false);
            jtbPesquisar.getColumnModel().getColumn(1).setResizable(false);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLvalor)
                    .addComponent(jLdescricao))
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTdescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTvalor, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLdescricao)
                    .addComponent(jTdescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLvalor)
                    .addComponent(jTvalor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(153, 204, 255));
        jPanel2.setForeground(new java.awt.Color(153, 204, 255));

        jLid1.setBackground(new java.awt.Color(0, 0, 102));
        jLid1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLid1.setForeground(new java.awt.Color(0, 0, 102));
        jLid1.setText("Categoria de Planos ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLid1)
                .addGap(47, 47, 47))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLid1)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(153, 204, 255));
        jPanel3.setForeground(new java.awt.Color(153, 204, 255));

        jBcancelar.setText("Cancelar");
        jBcancelar.setEnabled(false);
        jBcancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBcancelarActionPerformed(evt);
            }
        });

        jBnovo.setText("Novo");
        jBnovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBnovoActionPerformed(evt);
            }
        });

        jBsalvar.setText("Salvar");
        jBsalvar.setEnabled(false);
        jBsalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBsalvarActionPerformed(evt);
            }
        });

        jbPesquisar.setText("Pesquisar");
        jbPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbPesquisarActionPerformed(evt);
            }
        });

        jbEditar.setText("Editar");
        jbEditar.setEnabled(false);
        jbEditar.setMaximumSize(null);
        jbEditar.setMinimumSize(new java.awt.Dimension(79, 23));
        jbEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbEditarActionPerformed(evt);
            }
        });

        jbExcluir.setText("Excluir");
        jbExcluir.setEnabled(false);
        jbExcluir.setMaximumSize(null);
        jbExcluir.setMinimumSize(new java.awt.Dimension(79, 23));
        jbExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbExcluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jBnovo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBsalvar))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jbPesquisar)
                        .addGap(31, 31, 31)
                        .addComponent(jbEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(29, 29, 29)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBcancelar)
                    .addComponent(jbExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBcancelar)
                    .addComponent(jBsalvar)
                    .addComponent(jBnovo))
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbPesquisar)
                    .addComponent(jbExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBnovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBnovoActionPerformed

        jTdescricao.setEditable(true);
        jTvalor.setEditable(true);
        jBnovo.setEnabled(false);
        jbPesquisar.setEnabled(false);
        jBsalvar.setEnabled(true);
        jBcancelar.setEnabled(true);
        limparCampos();
        acao = 1;
    }//GEN-LAST:event_jBnovoActionPerformed

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

    private void jbEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbEditarActionPerformed
        jTdescricao.setEditable(true);
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

                DAO = new CategoriaData();
                obj = DAO.pesquisarPorId(selectedRow);

                if (obj != null) {
                    jTdescricao.setText(obj.getDescricao());
                    jTvalor.setText(""+ obj.getValor());
                    jTdescricao.setEditable(true);
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

    private void jbPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbPesquisarActionPerformed

        try {
            DAO = new CategoriaData();
            obj = DAO.pesquisar(jTdescricao.getText());
            if (obj == null) {
                JOptionPane.showMessageDialog(this, "Registro não encontrado !");
            } else {
                jTdescricao.setText(obj.getDescricao());
                jTvalor.setText("" + obj.getValor());
                jbEditar.setEnabled(true);
                jbExcluir.setEnabled(true);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao pesquisar: " + ex.getMessage());
        }

    }//GEN-LAST:event_jbPesquisarActionPerformed

    private void jBcancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBcancelarActionPerformed
        // TODO add your handling code here:

        jTdescricao.setEditable(false);
        jTvalor.setEditable(false);
        jBnovo.setEnabled(true);
        jBsalvar.setEnabled(false);
        jBcancelar.setEnabled(false);
        jbPesquisar.setEnabled(true);
        jbEditar.setEnabled(false);
        jbExcluir.setEnabled(false);
        limparCampos();
    }//GEN-LAST:event_jBcancelarActionPerformed

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
                        Categoria categoria = DAO.pesquisar(idObject.toString());
                        return categoria.getId();
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

    private void jBsalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBsalvarActionPerformed
        try {
            if (validarCampos()) {
                obj = new Categoria();
                if (preencherObjeto()) {
                    DAO = new CategoriaData();
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

    private void jTvalorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTvalorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTvalorActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBcancelar;
    private javax.swing.JButton jBnovo;
    private javax.swing.JButton jBsalvar;
    private javax.swing.JLabel jLdescricao;
    private javax.swing.JLabel jLid1;
    private javax.swing.JLabel jLvalor;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTdescricao;
    private javax.swing.JFormattedTextField jTvalor;
    private javax.swing.JButton jbEditar;
    private javax.swing.JButton jbExcluir;
    private javax.swing.JButton jbPesquisar;
    private javax.swing.JTable jtbPesquisar;
    // End of variables declaration//GEN-END:variables

    private void limparCampos() {
        jTdescricao.setText("");
        jTvalor.setText("");
    }

    private boolean validarCampos() throws Exception {
        String msg = "";

        if (jTdescricao.getText().equals("")) {
            msg += "\nDescrição";
        }
        if (jTvalor.getText().equals("")) {
            jTvalor.setText("0,00");
            msg += "\n Valor";
        }

        if (msg.equals("")) {
            return true;
        } else {
            JOptionPane.showMessageDialog(this,
                    "Os seguintes campos devem ser preenchidos"
                    + msg, "Validar Campos",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

    }

    private boolean preencherObjeto() throws Exception {
        obj = new Categoria();
        obj.setId(1);
        obj.setDescricao(jTdescricao.getText());
        obj.setValor(Float.parseFloat(jTvalor.getText().replace(",", ".").toUpperCase()));

        return true;
    }

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Data.AssociadoData;
import Data.CategoriaData;
import Data.ContratoData;
import Data.FuncionarioData;
import java.awt.Color;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import model.Associado;
import model.Categoria;
import model.Contrato;
import model.Funcionario;

/**
 *
 * @author MaqLab
 */
public class jifContrato extends javax.swing.JInternalFrame {

    Contrato obj;
    ContratoData DAO;
    int acao = 0;
    Vector<Funcionario> vetorFuncionarios;
    Vector<Associado> vetorAssociado;
    Vector<Categoria> vetorCategoria;

    /**
     * Creates new form jifContrato
     */
    public jifContrato() {
        initComponents();

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
        jbExcluir = new javax.swing.JButton();
        jbCancelar = new javax.swing.JButton();
        jbSalvar = new javax.swing.JButton();
        jbEditar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jcFuncionario = new javax.swing.JComboBox();
        jbNovo = new javax.swing.JButton();
        jcAssociado = new javax.swing.JComboBox();
        jtNroContrato = new javax.swing.JTextField();
        jcStatus = new javax.swing.JComboBox();
        jcCategoria = new javax.swing.JComboBox();
        jlStatus = new javax.swing.JLabel();
        jlnroContrato = new javax.swing.JLabel();
        jbPesquisar = new javax.swing.JButton();
        jlDataContrato = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jllDataCancelamento = new javax.swing.JLabel();
        jtDataCon = new javax.swing.JFormattedTextField();
        jtDataCancelar = new javax.swing.JFormattedTextField();

        setBackground(new java.awt.Color(0, 102, 255));
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
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jbExcluir.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbExcluir.setText("Excluir");
        jbExcluir.setEnabled(false);
        jbExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbExcluirActionPerformed(evt);
            }
        });

        jbCancelar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbCancelar.setText("Cancelar");
        jbCancelar.setEnabled(false);
        jbCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCancelarActionPerformed(evt);
            }
        });

        jbSalvar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbSalvar.setText("Salvar");
        jbSalvar.setEnabled(false);
        jbSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSalvarActionPerformed(evt);
            }
        });

        jbEditar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbEditar.setText("Editar");
        jbEditar.setEnabled(false);
        jbEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbEditarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jbEditar)
                .addGap(49, 49, 49)
                .addComponent(jbExcluir)
                .addGap(79, 79, 79)
                .addComponent(jbCancelar)
                .addGap(43, 43, 43)
                .addComponent(jbSalvar)
                .addContainerGap(208, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbCancelar)
                    .addComponent(jbExcluir)
                    .addComponent(jbSalvar)
                    .addComponent(jbEditar))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(153, 204, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setForeground(new java.awt.Color(153, 204, 255));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 102));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Efetuar Contrato");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 102));
        jLabel1.setText("Funcionario:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 102));
        jLabel2.setText("Associado:");

        jcFuncionario.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jcFuncionario.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<Selecione>" }));
        jcFuncionario.setToolTipText("Selecione o status");
        jcFuncionario.setEnabled(false);

        jbNovo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbNovo.setText("Novo");
        jbNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbNovoActionPerformed(evt);
            }
        });

        jcAssociado.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jcAssociado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<Selecione>" }));
        jcAssociado.setToolTipText("Selecione o Associado");
        jcAssociado.setEnabled(false);

        jtNroContrato.setBackground(new java.awt.Color(195, 195, 195));
        jtNroContrato.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jtNroContrato.setToolTipText("Digite o número de contrato");
        jtNroContrato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtNroContratoActionPerformed(evt);
            }
        });

        jcStatus.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jcStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<Selecione>", "Ativo", "Inativo" }));
        jcStatus.setToolTipText("Selecione o status");

        jcCategoria.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jcCategoria.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<Selecione>" }));
        jcCategoria.setToolTipText("Selecione a Categoria");
        jcCategoria.setEnabled(false);

        jlStatus.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlStatus.setForeground(new java.awt.Color(0, 0, 102));
        jlStatus.setText("Status:");

        jlnroContrato.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlnroContrato.setForeground(new java.awt.Color(0, 0, 102));
        jlnroContrato.setText("Nro Contrato:");

        jbPesquisar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbPesquisar.setText("Pesquisar");
        jbPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbPesquisarActionPerformed(evt);
            }
        });

        jlDataContrato.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlDataContrato.setForeground(new java.awt.Color(0, 0, 102));
        jlDataContrato.setText("Data Contrato:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 102));
        jLabel3.setText("Categoria:");

        jllDataCancelamento.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jllDataCancelamento.setForeground(new java.awt.Color(0, 0, 102));
        jllDataCancelamento.setText("Data Cancelamento:");

        jtDataCon.setEditable(false);
        jtDataCon.setBackground(new java.awt.Color(195, 195, 195));
        try {
            jtDataCon.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jtDataCon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtDataConActionPerformed(evt);
            }
        });

        jtDataCancelar.setEditable(false);
        jtDataCancelar.setBackground(new java.awt.Color(195, 195, 195));
        try {
            jtDataCancelar.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jtDataCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtDataCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlnroContrato, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jlDataContrato, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jllDataCancelamento, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jlStatus, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jcStatus, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jcCategoria, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jcAssociado, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jcFuncionario, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jtNroContrato, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jbPesquisar)
                                .addGap(45, 45, 45)
                                .addComponent(jbNovo))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jtDataCon, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jtDataCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(165, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtNroContrato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlnroContrato)
                    .addComponent(jbPesquisar)
                    .addComponent(jbNovo))
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlDataContrato)
                    .addComponent(jtDataCon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jllDataCancelamento)
                    .addComponent(jtDataCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlStatus))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jcAssociado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jcFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jcCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtNroContratoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtNroContratoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtNroContratoActionPerformed

    private void jbNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbNovoActionPerformed
        jtNroContrato.setEditable(true);
        jtDataCon.setEditable(true);
        jtDataCancelar.setEditable(true);
        jcStatus.setEnabled(true);
        jcAssociado.setEnabled(true);
        jcCategoria.setEnabled(true);
        jcFuncionario.setEnabled(true);
        jbNovo.setEnabled(false);
        jbSalvar.setEnabled(true);
        jbCancelar.setEnabled(true);
        jbExcluir.setEnabled(false);
        jbPesquisar.setEnabled(false);
        jbEditar.setEnabled(false);
        acao = 1;
        Color whiteColor = Color.WHITE;
        jtNroContrato.setBackground(whiteColor);
        jtDataCon.setBackground(whiteColor);
        jtDataCancelar.setBackground(whiteColor);
    }//GEN-LAST:event_jbNovoActionPerformed

    private void jbCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCancelarActionPerformed

        jtNroContrato.setEditable(true);
        jtDataCon.setEditable(false);
        jtDataCancelar.setEditable(false);
        jcStatus.setEnabled(false);
        jcAssociado.setEnabled(false);
        jcCategoria.setEnabled(false);
        jcFuncionario.setEnabled(false);
        jbNovo.setEnabled(true);
        jbSalvar.setEnabled(false);
        jbCancelar.setEnabled(false);
        jbExcluir.setEnabled(false);
        jbPesquisar.setEnabled(true);
        jbEditar.setEnabled(false);
        limparCampos();
        Color grayColor = new Color(195, 195, 195);
        jtNroContrato.setBackground(grayColor);
        jtDataCon.setBackground(grayColor);
        jtDataCancelar.setBackground(grayColor);
    }//GEN-LAST:event_jbCancelarActionPerformed

    private void jbSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSalvarActionPerformed
        try {
            if (validarCampos()) {
                obj = new Contrato();
                if (preencherObjeto()) {
                    DAO = new ContratoData();
                    if (acao == 1) {
                        if (DAO.incluir(obj)) {
                            JOptionPane.showMessageDialog(this, "Salvo com sucesso !");
                            jbCancelarActionPerformed(evt);
                        }
                    }
                    if (acao == 2) {
                        if (DAO.editar(obj)) {
                            JOptionPane.showMessageDialog(this, "Alterado com sucesso !");
                            jbCancelarActionPerformed(evt);
                        }
                    }
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar"
                    + ex.getMessage());
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jbSalvarActionPerformed

    private void jbPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbPesquisarActionPerformed
        if (jtNroContrato.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Digite um numero do contrato");
        } else {
            try {
                DAO = new ContratoData();
                obj = DAO.pesquisar(Integer.parseInt(jtNroContrato.getText()));
                if (obj == null) {
                    JOptionPane.showMessageDialog(this, "Registro não encontrado");
                } else {

                    jtDataCon.setText(obj.getData_contrato());
                    jtDataCancelar.setText(obj.getData_cancelamento());
                    //                jcStatus.set("" +obj.getStatus());
                    jcAssociado.setEnabled(true);
                    for (int i = 0; i < vetorAssociado.size(); i++) {
                        if (vetorAssociado.get(i).getId() == obj.getAssociado().getId()) {
                            jcAssociado.setSelectedIndex(i);
                        }
                    }

                    for (int i = 0; i < vetorFuncionarios.size(); i++) {
                        if (vetorFuncionarios.get(i).getId() == obj.getFuncionario().getId()) {
                            jcFuncionario.setSelectedIndex(i);
                        }
                    }
                    for (int i = 0; i < vetorCategoria.size(); i++) {
                        if (vetorCategoria.get(i).getId() == obj.getCategoria().getId()) {
                            jcCategoria.setSelectedIndex(i);
                        }
                    }

                    jcStatus.setSelectedIndex(obj.getStatus());
                    jcStatus.setEnabled(true);
                    jcCategoria.setEnabled(true);
                    jcFuncionario.setEnabled(true);
                    jbNovo.setEnabled(false);
                    jbSalvar.setEnabled(false);
                    jbCancelar.setEnabled(true);
                    jbExcluir.setEnabled(true);
                    jbPesquisar.setEnabled(false);
                    jbEditar.setEnabled(true);

                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao pesquisar: " + ex.getMessage());
            }
        }    // TODO        // TODO add your handling code here:
    }//GEN-LAST:event_jbPesquisarActionPerformed

    private void jbExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbExcluirActionPerformed
        if (jtNroContrato.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Digite o Nro do Contrato");
        } else {
            try {
                if (DAO.excluir(Integer.parseInt(jtNroContrato.getText()))) {
                    JOptionPane.showMessageDialog(this, "Registro excluído com sucesso");
                    jbCancelarActionPerformed(evt);
                } else {
                    JOptionPane.showMessageDialog(this, "Não foi possível excluir com sucesso");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao exclui: " + ex.getMessage());
            }
        }          // TODO add your handling code here:
    }//GEN-LAST:event_jbExcluirActionPerformed

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated

        FuncionarioData DAOFuncionario = new FuncionarioData();
        try {
            vetorFuncionarios = DAOFuncionario.carregarCombo();
            jcFuncionario.setModel(new DefaultComboBoxModel(vetorFuncionarios));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "erro " + e.getMessage());
        }

        AssociadoData DAOAssociado = new AssociadoData();
        try {
            vetorAssociado = DAOAssociado.carregarCombo();
            jcAssociado.setModel(new DefaultComboBoxModel(vetorAssociado));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "erro " + e.getMessage());
        }

        CategoriaData DAOCategoria = new CategoriaData();
        try {
            vetorCategoria = DAOCategoria.carregarCombo();
            jcCategoria.setModel(new DefaultComboBoxModel(vetorCategoria));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "erro " + e.getMessage());
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_formInternalFrameActivated

    private void jtDataConActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtDataConActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtDataConActionPerformed

    private void jtDataCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtDataCancelarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtDataCancelarActionPerformed

    private void jbEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbEditarActionPerformed
        jtNroContrato.setEditable(false);
        jtDataCon.setEditable(false);
        jtDataCancelar.setEditable(true);
        jcStatus.setEnabled(true);
        jcAssociado.setEnabled(true);
        jcCategoria.setEnabled(true);
        jcFuncionario.setEnabled(true);
        jbNovo.setEnabled(false);
        jbSalvar.setEnabled(true);
        jbCancelar.setEnabled(true);
        jbExcluir.setEnabled(false);
        jbPesquisar.setEnabled(false);
        jbEditar.setEnabled(false);
        acao = 2;

        // TODO add your handling code here:
    }//GEN-LAST:event_jbEditarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JButton jbCancelar;
    private javax.swing.JButton jbEditar;
    private javax.swing.JButton jbExcluir;
    private javax.swing.JButton jbNovo;
    private javax.swing.JButton jbPesquisar;
    private javax.swing.JButton jbSalvar;
    private javax.swing.JComboBox jcAssociado;
    private javax.swing.JComboBox jcCategoria;
    private javax.swing.JComboBox jcFuncionario;
    private javax.swing.JComboBox jcStatus;
    private javax.swing.JLabel jlDataContrato;
    private javax.swing.JLabel jlStatus;
    private javax.swing.JLabel jllDataCancelamento;
    private javax.swing.JLabel jlnroContrato;
    private javax.swing.JFormattedTextField jtDataCancelar;
    private javax.swing.JFormattedTextField jtDataCon;
    private javax.swing.JTextField jtNroContrato;
    // End of variables declaration//GEN-END:variables

    private void limparCampos() {
        jtNroContrato.setText("");
        jtDataCancelar.setText("");
        jtDataCon.setText("");
        jcStatus.setSelectedIndex(0);
        jcAssociado.setSelectedIndex(0);
        jcFuncionario.setSelectedIndex(0);
        jcCategoria.setSelectedIndex(0);
    }

    private boolean validarCampos() throws Exception {
        if (jtNroContrato.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Digite o Nro do Contrato");
            jtNroContrato.requestFocus();
            return false;
        }
        if (jtDataCancelar.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Digite a Data de Cancelar");
            jtDataCancelar.requestFocus();
            return false;
        }
        if (jtDataCon.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Digite a Data de Contrato");
            jtDataCon.requestFocus();
            return false;
        }

        if (jcStatus.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Digite o Status");
            return false;
        }

        if (jcAssociado.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Digite o Associado");
            return false;
        }

        if (jcFuncionario.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Digite o funcionario");
            return false;
        }

        if (jcCategoria.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Digite a Categoria");
            return false;
        }

        return true;
    }

    private boolean preencherObjeto() throws Exception {
        obj.setNro_contrato(Integer.parseInt(jtNroContrato.getText()));
        obj.setData_cancelamento(jtDataCancelar.getText());
        obj.setData_contrato(jtDataCon.getText());
        obj.setAssociado(vetorAssociado.
                get(jcAssociado.getSelectedIndex()));
        obj.setFuncionario(vetorFuncionarios.
                get(jcFuncionario.getSelectedIndex()));
        obj.setStatus(jcStatus.getSelectedIndex());

        obj.setCategoria(vetorCategoria.
                get(jcCategoria.getSelectedIndex()));

        return true;
    }
}

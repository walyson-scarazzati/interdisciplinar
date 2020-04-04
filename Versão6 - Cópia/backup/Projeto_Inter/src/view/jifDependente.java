/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Data.AssociadoData;
import Data.DependenteData;
import Data.ParentescoData;
import java.awt.Frame;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.Associado;
import model.Dependente;
import model.Parentesco;
import static view.jifAssociado.obj;

/**
 *
 * @author MaqLab
 */
public class jifDependente extends javax.swing.JInternalFrame {

    Dependente obj;
    Vector<Associado> vetorAssociado;
    Vector<Parentesco> vetorParentescos;
    DependenteData DAO;
    DefaultTableModel modelo;
    Vector<Dependente> vetorDependente;
    int acao = 0;
    //criar um vetor de dependente, na hora que salva(DEPENDENTE) adiciona, e atualiza a jtabel do associado
    //jtabel de associado publica e estatica

    /**
     * Creates new form jifDependente
     */
    public jifDependente() {
        initComponents();
        initComponents();
        obj = new Dependente();
        vetorAssociado = new Vector<Associado>();
        vetorParentescos = new Vector<Parentesco>();
        vetorDependente = new Vector<Dependente>();
        DAO = new DependenteData();



    }

    public jifDependente(int id) {
        initComponents();
        initComponents();
        obj = new Dependente();
        vetorAssociado = new Vector<Associado>();
        vetorParentescos = new Vector<Parentesco>();
        DAO = new DependenteData();
        jtAssociado.setText(String.valueOf(id));


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
        jLabel9 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jlId = new javax.swing.JLabel();
        jtId = new javax.swing.JTextField();
        jlData_Nascimento = new javax.swing.JLabel();
        jftData_Nascimento = new javax.swing.JFormattedTextField();
        jlDependente = new javax.swing.JLabel();
        jtDependente = new javax.swing.JTextField();
        jlEndereco = new javax.swing.JLabel();
        jtEndereco = new javax.swing.JTextField();
        jlTelefone = new javax.swing.JLabel();
        jlEmail = new javax.swing.JLabel();
        jtEmail = new javax.swing.JTextField();
        jlRG = new javax.swing.JLabel();
        jtRG = new javax.swing.JTextField();
        jlCPF = new javax.swing.JLabel();
        jtCPF = new javax.swing.JTextField();
        jlParentesco = new javax.swing.JLabel();
        jcbParentesco = new javax.swing.JComboBox();
        jlDependentes = new javax.swing.JLabel();
        jtAssociado = new javax.swing.JTextField();
        jtTelefone = new javax.swing.JFormattedTextField();
        jPanel3 = new javax.swing.JPanel();
        jbNovo = new javax.swing.JButton();
        jbSalvar = new javax.swing.JButton();
        jbCancelar = new javax.swing.JButton();
        jbPesquisar = new javax.swing.JButton();
        jbExcluir = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameActivated(evt);
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        jPanel1.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.setForeground(new java.awt.Color(153, 204, 255));

        jLabel9.setBackground(new java.awt.Color(0, 0, 102));
        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 102));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Dependente");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jlId.setBackground(new java.awt.Color(0, 0, 102));
        jlId.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlId.setForeground(new java.awt.Color(0, 0, 102));
        jlId.setText("Id");

        jtId.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jtId.setToolTipText("Digite o id");
        jtId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtIdActionPerformed(evt);
            }
        });

        jlData_Nascimento.setBackground(new java.awt.Color(0, 0, 102));
        jlData_Nascimento.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlData_Nascimento.setForeground(new java.awt.Color(0, 0, 102));
        jlData_Nascimento.setText("Data Nascimento");

        jftData_Nascimento.setEditable(false);
        try {
            jftData_Nascimento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jftData_Nascimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jftData_NascimentoActionPerformed(evt);
            }
        });

        jlDependente.setBackground(new java.awt.Color(0, 0, 102));
        jlDependente.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlDependente.setForeground(new java.awt.Color(0, 0, 102));
        jlDependente.setText("Dependente");

        jtDependente.setEditable(false);
        jtDependente.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jtDependente.setToolTipText("Digite o nome ");

        jlEndereco.setBackground(new java.awt.Color(0, 0, 102));
        jlEndereco.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlEndereco.setForeground(new java.awt.Color(0, 0, 102));
        jlEndereco.setText("Endereço");

        jtEndereco.setEditable(false);
        jtEndereco.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jtEndereco.setToolTipText("Digite o endereço");

        jlTelefone.setBackground(new java.awt.Color(0, 0, 102));
        jlTelefone.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlTelefone.setForeground(new java.awt.Color(0, 0, 102));
        jlTelefone.setText("Telefone");

        jlEmail.setBackground(new java.awt.Color(0, 0, 102));
        jlEmail.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlEmail.setForeground(new java.awt.Color(0, 0, 102));
        jlEmail.setText("E-mail");

        jtEmail.setEditable(false);
        jtEmail.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jtEmail.setToolTipText("Digite o email");

        jlRG.setBackground(new java.awt.Color(0, 0, 102));
        jlRG.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlRG.setForeground(new java.awt.Color(0, 0, 102));
        jlRG.setText("RG");

        jtRG.setEditable(false);
        jtRG.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jtRG.setToolTipText("Digite o RG");

        jlCPF.setBackground(new java.awt.Color(0, 0, 102));
        jlCPF.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlCPF.setForeground(new java.awt.Color(0, 0, 102));
        jlCPF.setText("CPF");

        jtCPF.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jtCPF.setToolTipText("Digite o CPF");

        jlParentesco.setBackground(new java.awt.Color(0, 0, 102));
        jlParentesco.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlParentesco.setForeground(new java.awt.Color(0, 0, 102));
        jlParentesco.setText("Parentesco");

        jcbParentesco.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jcbParentesco.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<Selecione>" }));
        jcbParentesco.setToolTipText("Selecione o Parentesco");

        jlDependentes.setBackground(new java.awt.Color(0, 0, 102));
        jlDependentes.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlDependentes.setForeground(new java.awt.Color(0, 0, 102));
        jlDependentes.setText("Associado");

        jtAssociado.setEditable(false);
        jtAssociado.setToolTipText("Associado");
        jtAssociado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtAssociadoActionPerformed(evt);
            }
        });

        jtTelefone.setEditable(false);
        try {
            jtTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("( ## ) ####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jtTelefone.setText("(  )    -        ");
        jtTelefone.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jtTelefone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtTelefoneActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jlTelefone, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jlEndereco, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jlDependente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jlId, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jlRG, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlDependentes)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jlEmail, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jlParentesco, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jtRG, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jlCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jtCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jtId, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(46, 46, 46)
                            .addComponent(jlData_Nascimento, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jftData_Nascimento, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jtDependente)
                        .addComponent(jtEndereco)
                        .addComponent(jtEmail)
                        .addComponent(jcbParentesco, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jtAssociado))
                    .addComponent(jtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(111, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlId, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlData_Nascimento, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jftData_Nascimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlDependente, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtDependente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jlTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlRG, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtRG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlParentesco, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbParentesco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlDependentes, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtAssociado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 8, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(153, 204, 255));
        jPanel3.setForeground(new java.awt.Color(153, 204, 255));

        jbNovo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbNovo.setText("Novo");
        jbNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbNovoActionPerformed(evt);
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

        jbCancelar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbCancelar.setText("Cancelar");
        jbCancelar.setEnabled(false);
        jbCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCancelarActionPerformed(evt);
            }
        });

        jbPesquisar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbPesquisar.setText("Pesquisar");
        jbPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbPesquisarActionPerformed(evt);
            }
        });

        jbExcluir.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbExcluir.setText("Excluir");
        jbExcluir.setEnabled(false);
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
                .addGap(109, 109, 109)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jbPesquisar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(68, 68, 68)
                .addComponent(jbSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jbCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(128, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbNovo)
                    .addComponent(jbCancelar)
                    .addComponent(jbSalvar))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbPesquisar)
                    .addComponent(jbExcluir))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtAssociadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtAssociadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtAssociadoActionPerformed

    private void jbNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbNovoActionPerformed
        jtId.setEditable(true);
        jftData_Nascimento.setEditable(true);
        jtDependente.setEditable(true);
        jtEndereco.setEditable(true);
        jtEmail.setEditable(true);
        jtTelefone.setEditable(true);
        jtRG.setEditable(true);
        jtCPF.setEditable(true);
        jtAssociado.setEditable(false);
        jcbParentesco.setEnabled(true);
        jbNovo.setEnabled(false);
        jbSalvar.setEnabled(true);
        jbCancelar.setEnabled(true);
//        jbEditar.setEnabled(false);
        jbExcluir.setEnabled(false);
        jbPesquisar.setEnabled(false);
        limparCampos();
    }//GEN-LAST:event_jbNovoActionPerformed

    private void jbSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSalvarActionPerformed
        try {
            if (validarCampos()) {
                if (preencherObjeto()) {
//                    if(DAO.incluir(obj)){
                    if (jifAssociado.vetorDependentes == null) {
                        jifAssociado.vetorDependentes = new Vector<>();
                    }
                    jifAssociado.vetorDependentes.add(obj);
                    jifAssociado.mostrarDependentes();
                    JOptionPane.showMessageDialog(this, "Salvo");
                    limparCampos();
//                    }else{
//                          JOptionPane.showMessageDialog(this, "não foi SAlvo");
//                    }
                }
                
                
            }


        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao salvo " + ex.getMessage());
        }



    }//GEN-LAST:event_jbSalvarActionPerformed

    private void jbCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCancelarActionPerformed
        jtId.setEditable(true);
        jftData_Nascimento.setEditable(true);
        jtDependente.setEditable(true);
        jtEndereco.setEditable(true);
        jtEmail.setEditable(true);
        jtTelefone.setEditable(true);
        jtAssociado.setText(null);
        jtRG.setEditable(true);
        jtCPF.setEditable(true);
        jtAssociado.setEditable(true);
        jcbParentesco.setEnabled(true);
        jbNovo.setEnabled(true);
        jbSalvar.setEnabled(false);
        jbCancelar.setEnabled(false);
//        jbEditar.setEnabled(false);
        jbExcluir.setEnabled(false);
        jbPesquisar.setEnabled(true);
        jtId.requestFocus();
        limparCampos();
          acao = 1;
    }//GEN-LAST:event_jbCancelarActionPerformed

    private void jbExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbExcluirActionPerformed
        if (jtId.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Digite o id ");
        } else {
            try {
                if (DAO.excluir(Integer.parseInt(jtId.getText()))) {
                    JOptionPane.showMessageDialog(this, "Excluido com sucesso !");
                    jbCancelarActionPerformed(evt);
                } else {
                    JOptionPane.showMessageDialog(this, "Não foi possível excluir o registro ");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao excluir " + ex.getMessage());
            }
        }
    }//GEN-LAST:event_jbExcluirActionPerformed

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated

        ParentescoData DAOParentesco = new ParentescoData();
        try {
            vetorParentescos = DAOParentesco.carregarCombo();
            jcbParentesco.setModel(new DefaultComboBoxModel(vetorParentescos));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "erro " + e.getMessage());
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_formInternalFrameActivated

    private void jftData_NascimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jftData_NascimentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jftData_NascimentoActionPerformed

    private void jbPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbPesquisarActionPerformed
            if(jtId.getText().equals("")){
           JOptionPane.showMessageDialog(this, "Digite o id");
        }else{
             try{
                  DAO = new DependenteData();
                  obj = DAO.pesquisar(Integer.parseInt(jtId.getText()));
                  if(obj == null){
                     JOptionPane.showMessageDialog(this, "Registro não encontrado");
                  }else{
                     jtId.setText(String.valueOf(obj.getId()));
                     jftData_Nascimento.setText(obj.getData_nasc());
                     jtDependente.setText(obj.getNome());
                     jtEndereco.setText(obj.getEndereco());
                     jtTelefone.setText(String.valueOf(obj.getTelefone()));
                     jtEmail.setText(obj.getEmail());
                     jtRG.setText(String.valueOf(obj.getRG()));
                     jtCPF.setText(String.valueOf(obj.getCpf()));
                     jcbParentesco.setEnabled(true);
                     jtAssociado.setText(String.valueOf(obj.getIdAssociado()));
//                     jbEditar.setEnabled(true);
                     jbCancelar.setEnabled(true);
                     jbExcluir.setEnabled(true);
                     jbPesquisar.setEnabled(false);
                     jbNovo.setEnabled(false);
                     jbSalvar.setEnabled(false);
                  }
          }catch(Exception ex){
             JOptionPane.showMessageDialog(this, "Erro ao pesquisar" + ex.getMessage());
          }
        }
    }//GEN-LAST:event_jbPesquisarActionPerformed

    private void jtIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtIdActionPerformed

    private void jtTelefoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtTelefoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtTelefoneActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JButton jbCancelar;
    private javax.swing.JButton jbExcluir;
    private javax.swing.JButton jbNovo;
    private javax.swing.JButton jbPesquisar;
    private javax.swing.JButton jbSalvar;
    private javax.swing.JComboBox jcbParentesco;
    private javax.swing.JFormattedTextField jftData_Nascimento;
    private javax.swing.JLabel jlCPF;
    private javax.swing.JLabel jlData_Nascimento;
    private javax.swing.JLabel jlDependente;
    private javax.swing.JLabel jlDependentes;
    private javax.swing.JLabel jlEmail;
    private javax.swing.JLabel jlEndereco;
    private javax.swing.JLabel jlId;
    private javax.swing.JLabel jlParentesco;
    private javax.swing.JLabel jlRG;
    private javax.swing.JLabel jlTelefone;
    private javax.swing.JTextField jtAssociado;
    public static javax.swing.JTextField jtCPF;
    public static javax.swing.JTextField jtDependente;
    private javax.swing.JTextField jtEmail;
    private javax.swing.JTextField jtEndereco;
    public static javax.swing.JTextField jtId;
    private javax.swing.JTextField jtRG;
    private javax.swing.JFormattedTextField jtTelefone;
    // End of variables declaration//GEN-END:variables

    private void limparCampos() {
        jtId.setText(null);
        jftData_Nascimento.setText(null);
        jtDependente.setText(null);
        jtEmail.setText(null);
        jtEndereco.setText(null);
        jtTelefone.setText(null);
        jtRG.setText(null);
        jtCPF.setText(null);
        jcbParentesco.setSelectedIndex(0);
    }

    private boolean validarCampos() throws Exception {
        String msg = "";
        if (jtId.getText().equals("")) {
            msg += "\nId";
        }
        if (jftData_Nascimento.getText().equals("")) {
            msg += "\nData_Nascimento";
        }
        if (jtDependente.getText().equals("")) {
            msg += "\nDependente";
        }
        if (jtEndereco.getText().equals("")) {
            msg += "\nEndereco";
        }
                if (jtTelefone.getText().equals("(  )    -    ")) {
            msg += "\nTelefone";
        }
        if (jtTelefone.getText().equals("")) {
            msg += "\nEmail";
        }
        if (jtRG.getText().equals("")) {
            msg += "\nRG";
        }
        if (jtCPF.getText().equals("")) {
            msg += "\nCPF";
        }

        if (jtAssociado.getText().equals("")) {
            msg += "\n Associado";
        }



        if (jcbParentesco.getSelectedIndex() == 0) {
            msg += "\n parentesco";
        }

        if (msg.equals("")) {
            return true;
        } else {
            JOptionPane.showMessageDialog(this, "Os seguintes campos devem ser preenchidos "
                    + msg, "Validar Campos", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private boolean preencherObjeto() throws Exception {
        obj = new Dependente();
        obj.setId(Integer.parseInt(jtId.getText()));
        obj.setData_nasc(jftData_Nascimento.getText());
        obj.setNome(jtDependente.getText());
        obj.setEndereco(jtEndereco.getText());
        obj.setTelefone(jtTelefone.getText());
        obj.setEmail(jtEmail.getText());
        obj.setRG(Integer.parseInt(jtRG.getText()));
        obj.setCpf(Integer.parseInt(jtCPF.getText()));
        obj.setParentesco(vetorParentescos.
                get(jcbParentesco.getSelectedIndex()));

        return true;
    }
}

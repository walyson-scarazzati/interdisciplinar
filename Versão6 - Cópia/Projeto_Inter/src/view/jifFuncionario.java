/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Data.AssociadoData;
import Data.FuncionarioData;
import java.util.Vector;
import javax.swing.JOptionPane;
import model.Funcionario;
import static view.jifAssociado.obj;

/**
 *
 * @author MaqLab
 */
public class jifFuncionario extends javax.swing.JInternalFrame {

    Funcionario obj;
    Vector<Funcionario> vetorFuncionarios;
    FuncionarioData DAO;
    int acao = 0;

    /**
     * Creates new form jifFuncionario
     */
    public jifFuncionario() {
        initComponents();
        obj = new Funcionario();
        vetorFuncionarios = new Vector<Funcionario>();
        DAO = new FuncionarioData();
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
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        Data_Nascimento = new javax.swing.JLabel();
        jftData_Nascimento = new javax.swing.JFormattedTextField();
        jlFuncionario = new javax.swing.JLabel();
        jtFuncionario = new javax.swing.JTextField();
        jlEndereco = new javax.swing.JLabel();
        jtEndereco = new javax.swing.JTextField();
        jlTelefone = new javax.swing.JLabel();
        jlSalario = new javax.swing.JLabel();
        jtSalario = new javax.swing.JFormattedTextField();
        jtSalario = new extras.JMoneyField();
        //jFp_Precocusto = new extras.Moeda();

        jlEmail = new javax.swing.JLabel();
        jtEmail = new javax.swing.JTextField();
        jlRG = new javax.swing.JLabel();
        jtRG = new javax.swing.JTextField();
        jlCPF = new javax.swing.JLabel();
        jtCPF = new javax.swing.JTextField();
        jlUsuario = new javax.swing.JLabel();
        jtUsuario = new javax.swing.JTextField();
        jlSenha = new javax.swing.JLabel();
        jtSenha = new javax.swing.JTextField();
        jlTipo = new javax.swing.JLabel();
        jC_usuario_tipo = new javax.swing.JComboBox();
        jtTelefone = new javax.swing.JFormattedTextField();
        jPanel3 = new javax.swing.JPanel();
        jbNovo = new javax.swing.JButton();
        jbPesquisar = new javax.swing.JButton();
        jbSalvar = new javax.swing.JButton();
        jbCancelar = new javax.swing.JButton();
        jbEditar = new javax.swing.JButton();
        jbExcluir = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);

        jPanel1.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.setForeground(new java.awt.Color(153, 204, 255));

        jLabel1.setBackground(new java.awt.Color(0, 0, 102));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 102));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Funcionario");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));

        Data_Nascimento.setBackground(new java.awt.Color(0, 0, 102));
        Data_Nascimento.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Data_Nascimento.setForeground(new java.awt.Color(0, 0, 102));
        Data_Nascimento.setText("Data Nascimento");

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

        jlFuncionario.setBackground(new java.awt.Color(0, 0, 102));
        jlFuncionario.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlFuncionario.setForeground(new java.awt.Color(0, 0, 102));
        jlFuncionario.setText("Funcionário");

        jtFuncionario.setEditable(false);
        jtFuncionario.setToolTipText("Digite o nome ");

        jlEndereco.setBackground(new java.awt.Color(0, 0, 102));
        jlEndereco.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlEndereco.setForeground(new java.awt.Color(0, 0, 102));
        jlEndereco.setText("Endereço");

        jtEndereco.setEditable(false);
        jtEndereco.setToolTipText("Digite o Endereço");

        jlTelefone.setBackground(new java.awt.Color(0, 0, 102));
        jlTelefone.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlTelefone.setForeground(new java.awt.Color(0, 0, 102));
        jlTelefone.setText("Telefone");

        jlSalario.setBackground(new java.awt.Color(0, 0, 102));
        jlSalario.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlSalario.setForeground(new java.awt.Color(0, 0, 102));
        jlSalario.setText("Salário");

        jtSalario.setEditable(false);
        jtSalario.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N

        jlEmail.setBackground(new java.awt.Color(0, 0, 102));
        jlEmail.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlEmail.setForeground(new java.awt.Color(0, 0, 102));
        jlEmail.setText("E-mail");

        jtEmail.setEditable(false);
        jtEmail.setToolTipText("Digite o email");

        jlRG.setBackground(new java.awt.Color(0, 0, 102));
        jlRG.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlRG.setForeground(new java.awt.Color(0, 0, 102));
        jlRG.setText("RG");

        jtRG.setEditable(false);
        jtRG.setToolTipText("Digite o RG");

        jlCPF.setBackground(new java.awt.Color(0, 0, 102));
        jlCPF.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlCPF.setForeground(new java.awt.Color(0, 0, 102));
        jlCPF.setText("CPF");

        jtCPF.setToolTipText("Digite o CPF");

        jlUsuario.setBackground(new java.awt.Color(0, 0, 102));
        jlUsuario.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlUsuario.setForeground(new java.awt.Color(0, 0, 102));
        jlUsuario.setText("Usuário");

        jtUsuario.setEditable(false);
        jtUsuario.setToolTipText("Digite o usuario para login");

        jlSenha.setBackground(new java.awt.Color(0, 0, 102));
        jlSenha.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlSenha.setForeground(new java.awt.Color(0, 0, 102));
        jlSenha.setText("Senha");

        jtSenha.setEditable(false);
        jtSenha.setToolTipText("Digite a senha para login");

        jlTipo.setBackground(new java.awt.Color(0, 0, 102));
        jlTipo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlTipo.setForeground(new java.awt.Color(0, 0, 102));
        jlTipo.setText("Tipo");

        jC_usuario_tipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<Selecione>", "Comum", "Administrador" }));
        jC_usuario_tipo.setToolTipText("Selecione o Parentesco");

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
                .addGap(75, 75, 75)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(Data_Nascimento, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(jftData_Nascimento, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlEndereco)
                            .addComponent(jlTelefone)
                            .addComponent(jlEmail)
                            .addComponent(jlRG, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlUsuario)
                            .addComponent(jlTipo))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jC_usuario_tipo, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jtFuncionario, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
                                .addComponent(jtEndereco))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jtEmail, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jlSalario, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jtSalario, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jtRG, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(31, 31, Short.MAX_VALUE)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jlCPF)
                                        .addComponent(jlSenha))
                                    .addGap(34, 34, 34)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jtCPF, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                                        .addComponent(jtSenha)))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Data_Nascimento, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jftData_Nascimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlEndereco)
                    .addComponent(jtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlTelefone)
                    .addComponent(jlSalario, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtSalario, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlEmail)
                    .addComponent(jtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlRG)
                    .addComponent(jtRG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlCPF)
                    .addComponent(jtCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlSenha)
                    .addComponent(jtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlUsuario))
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jC_usuario_tipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlTipo))
                .addGap(24, 24, 24))
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

        jbPesquisar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbPesquisar.setText("Pesquisar");
        jbPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbPesquisarActionPerformed(evt);
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

        jbEditar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbEditar.setText("Editar");
        jbEditar.setEnabled(false);
        jbEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbEditarActionPerformed(evt);
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
                .addGap(107, 107, 107)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jbPesquisar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbNovo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(115, 115, 115)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(99, 99, 99)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jbExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(105, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbNovo)
                    .addComponent(jbSalvar)
                    .addComponent(jbCancelar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbPesquisar)
                    .addComponent(jbEditar)
                    .addComponent(jbExcluir))
                .addContainerGap())
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
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbEditarActionPerformed

        jftData_Nascimento.setEditable(true);
        jtFuncionario.setEditable(true);
        jtEndereco.setEditable(true);
        jtTelefone.setEditable(true);
        jtSalario.setEditable(true);
        jtEmail.setEditable(true);
        jtRG.setEditable(true);
        jtCPF.setEditable(true);
        jtUsuario.setEditable(true);
        jC_usuario_tipo.setEnabled(true);
        jtSenha.setEditable(true);
        jbNovo.setEnabled(false);
        jbSalvar.setEnabled(true);
        jbCancelar.setEnabled(true);
        jbEditar.setEnabled(false);
        jbExcluir.setEnabled(false);
        jbPesquisar.setEnabled(false);
        acao = 2;
    }//GEN-LAST:event_jbEditarActionPerformed

    private void jbCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCancelarActionPerformed

        jftData_Nascimento.setEditable(true);
        jtFuncionario.setEditable(true);
        jtEndereco.setEditable(true);
        jtTelefone.setEditable(true);
        jtSalario.setEditable(true);
        jtEmail.setEditable(true);
        jtRG.setEditable(true);
        jtCPF.setEditable(true);
        jtUsuario.setEditable(true);
        jtSenha.setEditable(true);
        jC_usuario_tipo.setEnabled(false);
        jbNovo.setEnabled(true);
        jbSalvar.setEnabled(false);
        jbCancelar.setEnabled(false);
        jbEditar.setEnabled(false);
        jbExcluir.setEnabled(false);
        jbPesquisar.setEnabled(true);
        limparCampos();
    }//GEN-LAST:event_jbCancelarActionPerformed

    private void jbNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbNovoActionPerformed

        jftData_Nascimento.setEditable(true);
        jtFuncionario.setEditable(true);
        jtEndereco.setEditable(true);
        jtTelefone.setEditable(true);
        jtSalario.setEditable(true);
        jtEmail.setEditable(true);
        jtRG.setEditable(true);
        jtCPF.setEditable(true);
        jtUsuario.setEditable(true);
        jtSenha.setEditable(true);
        jC_usuario_tipo.setEnabled(true);
        jbNovo.setEnabled(false);
        jbSalvar.setEnabled(true);
        jbCancelar.setEnabled(true);
        jbEditar.setEnabled(false);
        jbExcluir.setEnabled(false);
        jbPesquisar.setEnabled(false);
        limparCampos();
        acao = 1;
    }//GEN-LAST:event_jbNovoActionPerformed

    private void jbSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSalvarActionPerformed
        try {
            if (validarCampos()) {
                if (preencherObjeto()) {
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
            JOptionPane.showMessageDialog(this, "Erro ao salvar !" + ex.getMessage());
        }
    }//GEN-LAST:event_jbSalvarActionPerformed

    private void jbExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbExcluirActionPerformed

        try {
            if (DAO.excluir(1)) {
                JOptionPane.showMessageDialog(this, "Registro excluído com sucesso !");
                jbCancelarActionPerformed(evt);
            } else {
                JOptionPane.showMessageDialog(this, "Não foi possível excluir o registro ");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao excluir: " + ex.getMessage());
        }

    }//GEN-LAST:event_jbExcluirActionPerformed

    private void jftData_NascimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jftData_NascimentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jftData_NascimentoActionPerformed

    private void jbPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbPesquisarActionPerformed

        try {
            DAO = new FuncionarioData();
            obj = DAO.pesquisarFuncionarios(1);
            if (obj == null) {
                JOptionPane.showMessageDialog(this, "Registro não encontrado");
            } else {
                jftData_Nascimento.setText(obj.getData_nasc());
                jtFuncionario.setText(obj.getNome());
                jtEndereco.setText(obj.getEndereco());
                jtTelefone.setText(String.valueOf(obj.getTelefone()));
                jtSalario.setText(String.valueOf(obj.getSalario()));
                jtEmail.setText(obj.getEmail());
                jtRG.setText(String.valueOf(obj.getRG()));
                jtCPF.setText(String.valueOf(obj.getCpf()));
                jtUsuario.setText(obj.getUsuario());
                jtSenha.setText(obj.getSenha());
                jC_usuario_tipo.setSelectedIndex(obj.getTipo());
                jbEditar.setEnabled(true);
                jbCancelar.setEnabled(true);
                jbExcluir.setEnabled(true);
                jbPesquisar.setEnabled(false);
                jbNovo.setEnabled(false);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao pesquisar" + ex.getMessage());
        }
    }//GEN-LAST:event_jbPesquisarActionPerformed

    private void jtTelefoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtTelefoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtTelefoneActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Data_Nascimento;
    private javax.swing.JComboBox jC_usuario_tipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JButton jbCancelar;
    private javax.swing.JButton jbEditar;
    private javax.swing.JButton jbExcluir;
    private javax.swing.JButton jbNovo;
    private javax.swing.JButton jbPesquisar;
    private javax.swing.JButton jbSalvar;
    private javax.swing.JFormattedTextField jftData_Nascimento;
    private javax.swing.JLabel jlCPF;
    private javax.swing.JLabel jlEmail;
    private javax.swing.JLabel jlEndereco;
    private javax.swing.JLabel jlFuncionario;
    private javax.swing.JLabel jlRG;
    private javax.swing.JLabel jlSalario;
    private javax.swing.JLabel jlSenha;
    private javax.swing.JLabel jlTelefone;
    private javax.swing.JLabel jlTipo;
    private javax.swing.JLabel jlUsuario;
    private javax.swing.JTextField jtCPF;
    private javax.swing.JTextField jtEmail;
    private javax.swing.JTextField jtEndereco;
    private javax.swing.JTextField jtFuncionario;
    private javax.swing.JTextField jtRG;
    private javax.swing.JFormattedTextField jtSalario;
    private javax.swing.JTextField jtSenha;
    private javax.swing.JFormattedTextField jtTelefone;
    private javax.swing.JTextField jtUsuario;
    // End of variables declaration//GEN-END:variables

    private void limparCampos() {
        jftData_Nascimento.setText("");
        jtFuncionario.setText("");
        jtEndereco.setText("");
        jtTelefone.setText("");
        jtSalario.setText("");
        jtEmail.setText("");
        jtRG.setText("");
        jtCPF.setText("");
        jtUsuario.setText("");
        jtSenha.setText("");
        jC_usuario_tipo.setSelectedIndex(0);
    }

    private boolean validarCampos() throws Exception {
        String msg = "";

        if (jftData_Nascimento.getText().equals("  /  /    ")) {
            msg += "\nData_Nascimento";
        }
        if (jtFuncionario.getText().equals("")) {
            msg += "\nFuncionario";
        }
        if (jtEndereco.getText().equals("")) {
            msg += "\nEndereco";
        }
        if (jtTelefone.getText().equals("(  )    -    ")) {
            msg += "\nTelefone";
        }
        if (jtSalario.getText().equals("")) {
            jtSalario.setText("0,00");
            msg += "\nSalario";
        }
        if (jtEmail.getText().equals("")) {
            msg += "\nnEmail";
        }
        if (jtRG.getText().equals("")) {
            msg += "\nRG";
        }
        if (jtCPF.getText().equals("")) {
            msg += "\nCPF";
        }
        if (jtUsuario.getText().equals("")) {
            msg += "\nUsuario";
        }

        if (jtSenha.getText().equals("")) {
            msg += "\nSenha";
        }

        if (jC_usuario_tipo.getSelectedIndex() == 0) {
            msg += "\n Tipo";
        }

        if (msg.equals("")) {
            return true;
        } else {
            JOptionPane.showMessageDialog(this, "Os seguintes campos devem ser preenchidos" + msg, "Validar Campos", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private boolean preencherObjeto() throws Exception {
        obj = new Funcionario();
        obj.setData_nasc(jftData_Nascimento.getText());
        obj.setNome(jtFuncionario.getText());
        obj.setEndereco(jtEndereco.getText());
        obj.setTelefone(jtTelefone.getText());
        obj.setSalario(Double.parseDouble(jtSalario.getText().replace(",", ".").toUpperCase()));
        obj.setEmail(jtEmail.getText());
        obj.setRG(jtRG.getText());
        obj.setCpf(jtCPF.getText());
        obj.setUsuario(jtUsuario.getText());
        obj.setSenha(jtSenha.getText());
        obj.setTipo(jC_usuario_tipo.getSelectedIndex());

        return true;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.GPEDSCVP.interfaces;

import br.edu.GPEDSCVP.classe.Acesso;
import br.edu.GPEDSCVP.classe.Datasheet;
import br.edu.GPEDSCVP.classe.Permissao;
import br.edu.GPEDSCVP.classe.Tela;
import br.edu.GPEDSCVP.dao.daoAcesso;
import br.edu.GPEDSCVP.dao.daoDatasheet;
import br.edu.GPEDSCVP.dao.daoPermissao;
import br.edu.GPEDSCVP.dao.daoTela;
import br.edu.GPEDSCVP.util.ComboBox;
import br.edu.GPEDSCVP.util.FormatarData;
import br.edu.GPEDSCVP.util.ManipulaJtable;
import br.edu.GPEDSCVP.util.Mensagens;
import br.edu.GPEDSCVP.util.Rotinas;
import br.edu.GPEDSCVP.util.UltimaSequencia;
import br.edu.GPEDSCVP.util.ValidaAcesso;
import br.edu.GPEDSCVP.util.ValidaBotoes;
import br.edu.GPEDSCVP.util.ValidaCampos;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import static java.lang.System.out;
import java.sql.Blob;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.sql.rowset.serial.SerialBlob;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author Willys
 */
public class InterfaceDatasheet extends javax.swing.JFrame {

    Datasheet datasheet;
    Tela tela;
    daoTela dao_tela;
    ComboBox combo;
    Acesso acesso;
    FormatarData data;
    Mensagens mensagem;
    daoPermissao dao_permissao;
    daoAcesso dao_acesso;
    daoDatasheet dao_datasheet;
    Permissao permissao;
    ValidaAcesso valida_acesso;
    ValidaBotoes valida_botoes;
    ValidaCampos valida_campos;
    ManipulaJtable Jtable;
    int situacao = Rotinas.PADRAO;
    static byte[] arquivo;
    static boolean importou_arquivo = false;
    
    public InterfaceDatasheet() {
        initComponents();
        
        tela = new Tela();
        datasheet = new Datasheet();
        dao_datasheet = new daoDatasheet();
        dao_tela = new daoTela();
        data = new FormatarData();
        acesso = new Acesso();
        dao_permissao = new daoPermissao();
        dao_acesso = new daoAcesso();
        permissao = new Permissao();
        valida_acesso = new ValidaAcesso();
        valida_botoes = new ValidaBotoes();
        mensagem = new Mensagens();
        combo = new ComboBox();
        Jtable = new ManipulaJtable();
        try {
            valida_campos = new ValidaCampos();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao iniciar registro");
        }
        
        
        //desabilita campos da tela do datasheet
        valida_campos.desabilitaCampos(jPDatasheet);
        
        //Adiciona barra de rolagem obs: obrigatorio para conseguir dimensionar automatico as colunas da jtable
        jTBConsultaDatasheet.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        //Define a situação como inicial para habilitar os botoes utilizados apenas quando inicia a tela
        situacao = Rotinas.INICIAL;

        //habilita os botoes utilizados na inicialização da tela
        valida_botoes.ValidaEstado(jPBotoes, situacao);
        
        //atualiza dados do usuario logado
        dao_acesso.retornaUsuarioLogado(acesso);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTBDatasheet = new javax.swing.JTabbedPane();
        jPDatasheet = new javax.swing.JPanel();
        jPBotoes = new javax.swing.JPanel();
        jBTNovo = new javax.swing.JButton();
        jBTAlterar = new javax.swing.JButton();
        jBTExcluir = new javax.swing.JButton();
        jBTGravar = new javax.swing.JButton();
        jBTCancelar = new javax.swing.JButton();
        jTFIDDatasheet = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTFDescricao = new javax.swing.JTextField();
        jBTBuscarDatasheet = new javax.swing.JButton();
        jFTData = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTBConsultaDatasheet = new javax.swing.JTable();
        jTFFiltro = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jBTBuscar = new javax.swing.JButton();
        jBTVerDatasheet = new javax.swing.JButton();
        jCBBuscarPor = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Datasheets");
        setResizable(false);

        jTBDatasheet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTBDatasheetMouseClicked(evt);
            }
        });
        jTBDatasheet.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTBDatasheetStateChanged(evt);
            }
        });

        jBTNovo.setIcon(new javax.swing.ImageIcon("C:\\Users\\rafa\\Documents\\GPEDS-CVP\\src\\br\\edu\\GPEDSCVP\\icones\\Create.png")); // NOI18N
        jBTNovo.setText("Novo");
        jBTNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTNovoActionPerformed(evt);
            }
        });

        jBTAlterar.setIcon(new javax.swing.ImageIcon("C:\\Users\\rafa\\Documents\\GPEDS-CVP\\src\\br\\edu\\GPEDSCVP\\icones\\Modify.png")); // NOI18N
        jBTAlterar.setText("Alterar");
        jBTAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTAlterarActionPerformed(evt);
            }
        });

        jBTExcluir.setIcon(new javax.swing.ImageIcon("C:\\Users\\rafa\\Documents\\GPEDS-CVP\\src\\br\\edu\\GPEDSCVP\\icones\\No-entry.png")); // NOI18N
        jBTExcluir.setText("Excluir");
        jBTExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTExcluirActionPerformed(evt);
            }
        });

        jBTGravar.setIcon(new javax.swing.ImageIcon("C:\\Users\\rafa\\Documents\\GPEDS-CVP\\src\\br\\edu\\GPEDSCVP\\icones\\Save.png")); // NOI18N
        jBTGravar.setText("Gravar");
        jBTGravar.setName("descricao"); // NOI18N
        jBTGravar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jBTGravarMouseExited(evt);
            }
        });
        jBTGravar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTGravarActionPerformed(evt);
            }
        });

        jBTCancelar.setIcon(new javax.swing.ImageIcon("C:\\Users\\rafa\\Documents\\GPEDS-CVP\\src\\br\\edu\\GPEDSCVP\\icones\\Close.png")); // NOI18N
        jBTCancelar.setText("Cancelar");
        jBTCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPBotoesLayout = new javax.swing.GroupLayout(jPBotoes);
        jPBotoes.setLayout(jPBotoesLayout);
        jPBotoesLayout.setHorizontalGroup(
            jPBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPBotoesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jBTNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBTAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBTExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBTGravar, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBTCancelar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPBotoesLayout.setVerticalGroup(
            jPBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPBotoesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBTGravar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBTExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBTAlterar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBTNovo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBTCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTFIDDatasheet.setEditable(false);
        jTFIDDatasheet.setToolTipText("ID Datasheet");
        jTFIDDatasheet.setName("id_datasheet"); // NOI18N

        jLabel1.setText("ID Datasheet:");

        jLabel2.setText("Datasheet:");

        jTFDescricao.setToolTipText("Datasheet");
        jTFDescricao.setName("descricao"); // NOI18N

        jBTBuscarDatasheet.setIcon(new javax.swing.ImageIcon("C:\\Users\\rafa\\Documents\\GPEDS-CVP\\src\\br\\edu\\GPEDSCVP\\icones\\magnifier.png")); // NOI18N
        jBTBuscarDatasheet.setText("Buscar");
        jBTBuscarDatasheet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTBuscarDatasheetActionPerformed(evt);
            }
        });

        jFTData.setEditable(false);
        jFTData.setToolTipText("Data");
        jFTData.setName("data_cadastro"); // NOI18N

        jLabel4.setText("Data:");

        javax.swing.GroupLayout jPDatasheetLayout = new javax.swing.GroupLayout(jPDatasheet);
        jPDatasheet.setLayout(jPDatasheetLayout);
        jPDatasheetLayout.setHorizontalGroup(
            jPDatasheetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPDatasheetLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPDatasheetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPDatasheetLayout.createSequentialGroup()
                        .addGroup(jPDatasheetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(jPDatasheetLayout.createSequentialGroup()
                                .addComponent(jTFDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBTBuscarDatasheet)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPDatasheetLayout.createSequentialGroup()
                        .addGroup(jPDatasheetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTFIDDatasheet, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jFTData, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21))))
            .addComponent(jPBotoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPDatasheetLayout.setVerticalGroup(
            jPDatasheetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPDatasheetLayout.createSequentialGroup()
                .addGroup(jPDatasheetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPDatasheetLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel1))
                    .addGroup(jPDatasheetLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPDatasheetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jFTData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTFIDDatasheet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPDatasheetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTFDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBTBuscarDatasheet))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 110, Short.MAX_VALUE)
                .addComponent(jPBotoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTBDatasheet.addTab("Cadastro", jPDatasheet);

        jTBConsultaDatasheet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Datasheet", "Descrição", "Data Cadastro"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTBConsultaDatasheet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTBConsultaDatasheetMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTBConsultaDatasheet);

        jLabel29.setText("Filtro:");

        jBTBuscar.setIcon(new javax.swing.ImageIcon("C:\\Users\\rafa\\Documents\\GPEDS-CVP\\src\\br\\edu\\GPEDSCVP\\icones\\magnifier.png")); // NOI18N
        jBTBuscar.setText("Buscar");
        jBTBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTBuscarActionPerformed(evt);
            }
        });

        jBTVerDatasheet.setIcon(new javax.swing.ImageIcon("C:\\Users\\rafa\\Documents\\GPEDS-CVP\\src\\br\\edu\\GPEDSCVP\\icones\\eye.png")); // NOI18N
        jBTVerDatasheet.setText("Ver datasheet");
        jBTVerDatasheet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTVerDatasheetActionPerformed(evt);
            }
        });

        jCBBuscarPor.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Geral", "Código", "Descrição" }));

        jLabel14.setText("Buscar por:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCBBuscarPor, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel29)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTFFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBTBuscar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBTVerDatasheet)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(jLabel14))
                .addGap(2, 2, 2)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTFFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBTBuscar)
                    .addComponent(jBTVerDatasheet, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCBBuscarPor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTBDatasheet.addTab("Consulta", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTBDatasheet)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTBDatasheet, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        setSize(new java.awt.Dimension(534, 354));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jBTNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTNovoActionPerformed
        UltimaSequencia ultima;

        //habilita campos da tela
        valida_campos.habilitaCampos(jPDatasheet);
        //se não for gerente
        if (acesso.getIn_gerente() == 0) {
            //retorna as permissoes de acesso do usuario
            dao_permissao.retornaDadosPermissao(acesso, permissao);
        }

        //Verifica se o usuario possui permissao para incluir registros nessa tela
        if (valida_acesso.verificaAcesso("inserir", acesso, permissao) == true) {

            //Define a situação como incluir para habilitar os botoes utilizados apenas na inclusão
            situacao = Rotinas.INCLUIR;

            //habilita os botoes utilizados na inclusão e desabilita os restantes
            valida_botoes.ValidaEstado(jPBotoes, situacao);
            
            try {
                //Gera id sequencial
                ultima = new UltimaSequencia();
                int sequencia = (Integer) (ultima.ultimasequencia("DATASHEET", "ID_DATASHEET"));
                //seta id no campo id_datasheet
                jTFIDDatasheet.setText(Integer.toString(sequencia));
                
                //Seta a data atual no campo data
                jFTData.setEnabled(true);
                jFTData.setText(data.DataAtual());
                
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Falha ao iniciar a inserção de datasheets");
            }
        }else{
            JOptionPane.showMessageDialog(null, "Voce não possui permissões para incluir datasheets no sistema");
        }
    }//GEN-LAST:event_jBTNovoActionPerformed

    private void jBTAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTAlterarActionPerformed
        //se naõ for gerente
        if (acesso.getIn_gerente() == 0) {
            //retorna as permissoes de acesso do usuario
            dao_permissao.retornaDadosPermissao(acesso, permissao);
        }

        //Verifica se o usuario possui permissao para acessar essa tela
        if (valida_acesso.verificaAcesso("alterar", acesso, permissao) == true) {
            situacao = Rotinas.ALTERAR;
            valida_botoes.ValidaEstado(jPBotoes, situacao);

            valida_campos.habilitaCampos(jPDatasheet);

        }else{
            JOptionPane.showMessageDialog(null, "Voce não possui permissões para alterar cidades no sistema");
        }
    }//GEN-LAST:event_jBTAlterarActionPerformed

    private void jBTExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTExcluirActionPerformed
        if (acesso.getIn_gerente() == 0) {
            //retorna as permissoes de acesso do usuario
            dao_permissao.retornaDadosPermissao(acesso, permissao);
        }

        //Verifica se o usuario possui permissao para excluir registros nessa tela
        if (valida_acesso.verificaAcesso("excluir", acesso, permissao) == true) {

            if (mensagem.ValidaMensagem("Deseja realmente excluir o registro ?") == 0) {

                if(dao_datasheet.excluir(datasheet) == true){
                    JOptionPane.showMessageDialog(null, "Excluido com Sucesso");
                    //limpa campos
                    valida_campos.LimparCampos(jPDatasheet);

                    //Define a situação como incluir para habilitar os botoes utilizados apenas na inclusão
                    situacao = Rotinas.INICIAL;

                    //habilita os botoes utilizados na inclusão e desabilita os restantes
                    valida_botoes.ValidaEstado(jPBotoes, situacao);

                    //desabilita campos
                    valida_campos.desabilitaCampos(jPDatasheet);
                }

            }
        }else{
            JOptionPane.showMessageDialog(null, "Voce não possui permissões para excluir datasheets no sistema");
        }
    }//GEN-LAST:event_jBTExcluirActionPerformed

    private void jBTGravarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBTGravarMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jBTGravarMouseExited

    private void jBTGravarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTGravarActionPerformed
        //Se for inclusão
        if (situacao == Rotinas.INCLUIR) {
            if (valida_campos.validacamposobrigatorios(jPDatasheet, "DATASHEET") == 0) {
                if(importou_arquivo == true){

                    try {

                        //pega dados do datasheet na tela
                        getDatasheet();
                        //inclui dataasheet
                        if(dao_datasheet.incluir(datasheet) == true){
                            //se ocorreu tudo bem na inclusão
                            JOptionPane.showMessageDialog(null, "Salvo com Sucesso");
                            //limpa campos
                            valida_campos.LimparCampos(jPDatasheet);

                            //Define a situação como incluir para habilitar os botoes utilizados apenas na inclusão
                            situacao = Rotinas.INICIAL;

                            //habilita os botoes utilizados na inclusão e desabilita os restantes
                            valida_botoes.ValidaEstado(jPBotoes, situacao);

                            //desabilita campos
                            valida_campos.desabilitaCampos(jPDatasheet);
                        }
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Falha ao incluir datasheet");
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Deve selecionar o arquivo do datasheet para continuar!");
                }
            }
        }else if(situacao == Rotinas.ALTERAR) {
            //pega dados do datasheet na tela
            if (valida_campos.validacamposobrigatorios(jPDatasheet, "DATASHEET") == 0) {
                try {
                    getDatasheet();
                    //altera moeda
                    if(dao_datasheet.alterar(datasheet,importou_arquivo) == true){
                        JOptionPane.showMessageDialog(null, "Salvo com Sucesso");
                        //limpa campos
                        valida_campos.LimparCampos(jPDatasheet);

                        //Define a situação como incluir para habilitar os botoes utilizados apenas na inclusão
                        situacao = Rotinas.INICIAL;

                        //habilita os botoes utilizados na inclusão e desabilita os restantes
                        valida_botoes.ValidaEstado(jPBotoes, situacao);

                        //desabilita campos
                        valida_campos.desabilitaCampos(jPDatasheet);
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Falha ao alterar datasheet");
                }
            }
        }
    }//GEN-LAST:event_jBTGravarActionPerformed

    private void jBTCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTCancelarActionPerformed
        //limpa campos
        valida_campos.LimparCampos(jPDatasheet);

        //Define a situação como incluir para habilitar os botoes utilizados apenas na inclusão
        situacao = Rotinas.INICIAL;

        //habilita os botoes utilizados na inclusão e desabilita os restantes
        valida_botoes.ValidaEstado(jPBotoes, situacao);

        //desabilita campos
        valida_campos.desabilitaCampos(jPDatasheet);
    }//GEN-LAST:event_jBTCancelarActionPerformed

    private void jTBConsultaDatasheetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTBConsultaDatasheetMouseClicked
        //Verifica se houve 2 cliques do mouse
        if (evt.getClickCount() == 2) {

            //recupera a linha clicada
            int linha = jTBConsultaDatasheet.getSelectedRow();

            //Limpa os campos da tela datasheet
            valida_campos.LimparCampos(jPDatasheet);

            //recupera o id do datasheet selecionado
            String codigo = (String) jTBConsultaDatasheet.getValueAt(linha, 0);

            //retorna dados do datasheet
            datasheet.setId_datasheet(Integer.parseInt(codigo));
            dao_datasheet.retornardados(datasheet);

            //Seta na tela de cadastro os dados do datasheet
            setcompDatasheet();
            //retorna para tela de cadastro
            jTBDatasheet.setSelectedIndex(0);

            //Define a situação como incluir para habilitar os botoes utilizados apenas na inclusão
            situacao = Rotinas.PADRAO;

            //habilita os botoes utilizados na inclusão e desabilita os restantes
            valida_botoes.ValidaEstado(jPBotoes, situacao);

            //desabilita campos da tela de datasheet
            valida_campos.desabilitaCampos(jPDatasheet);
        }
    }//GEN-LAST:event_jTBConsultaDatasheetMouseClicked

    private void jTBDatasheetStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTBDatasheetStateChanged
        if(jTBDatasheet.getSelectedIndex() == 1){  
            valida_campos.LimparJtable(jTBConsultaDatasheet);
        }      
    }//GEN-LAST:event_jTBDatasheetStateChanged

    private void jBTBuscarDatasheetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTBuscarDatasheetActionPerformed
        //Dados do arquivo selecionado pelo usuario
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);

        try {
            File f  = chooser.getSelectedFile();
            
            String nome_arquivo = f.getName();
            if(nome_arquivo.contains(".pdf")){
                
            InputStream is = new FileInputStream( f );
            byte[] bytes = new byte[(int)f.length() ];
            int offset = 0;
            int numRead = 0;
            while (offset < bytes.length && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
                offset += numRead;
            }
                              
                jTFDescricao.setText(nome_arquivo.replace(".pdf", ""));
                arquivo = bytes;
                importou_arquivo = true;

                }else{
                    JOptionPane.showMessageDialog(null, "O Arquivo deve ser .pdf!");
                    importou_arquivo = false;
                }
            } catch (Exception e) {
                importou_arquivo = false;
            }

    }//GEN-LAST:event_jBTBuscarDatasheetActionPerformed

    private void jBTBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTBuscarActionPerformed

        //Se não for gerente
        if (acesso.getIn_gerente() == 0) {
            //retorna as permissoes de acesso do usuario
            dao_permissao.retornaDadosPermissao(acesso, permissao);
        }

        //Verifica se o usuario possui permissao para acessar essa tela
        if (valida_acesso.verificaAcesso("consultar", acesso, permissao) == true) {

            // TODO add your handling code here:
            int id_busca;
            String ds_busca;

            //Tira aspas simples da string para evitar código sql
            valida_campos.IgnoraSQL(jTFFiltro);

            switch (jCBBuscarPor.getSelectedIndex()) {
                //Buscar por (Consulta Geral)
                case 0:
                    //Consulta geral de datasheets
                    dao_datasheet.consultaGeral(datasheet);
                    break;
                    
                //Buscar por (código)
                case 1:
                    //Consulta por codigo dos datasheets
                    try {
                        id_busca = Integer.parseInt(jTFFiltro.getText());
                        datasheet.setId_datasheet(id_busca);
                        dao_datasheet.consultaCodigo(datasheet);
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Favor informar um numero para consulta por código!");
                    }
                    break;
                    
                 //Buscar por (descrição)
                case 2:
                    //Consulta por descrição dos datasheets
                    ds_busca = jTFFiltro.getText();
                    datasheet.setDescricao(ds_busca);
                    dao_datasheet.consultaDescricao(datasheet);
                    break;
            }
            //Preenche na JTABLE os dados dos componentes cadastrados
            Jtable.PreencherJtableGenerico(jTBConsultaDatasheet, new String[]{"id_datasheet", "descricao", "data_cadastro"}, datasheet.getRetorno());
            Jtable.ajustarColunasDaTabela(jTBConsultaDatasheet);
        } else {
            JOptionPane.showMessageDialog(null, "Você nao possui permissões para consultar datasheets no sistema");
        }
    }//GEN-LAST:event_jBTBuscarActionPerformed

    private void jBTVerDatasheetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTVerDatasheetActionPerformed
        byte[] arquivo_banco;
        File f = null;
        String nome_arquivo;

        try {
            //recupera a linha clicada
            int linha = jTBConsultaDatasheet.getSelectedRow();
            //recupera o id do datasheet
            int id = Integer.parseInt(jTBConsultaDatasheet.getValueAt(linha, 0).toString());
            try {
                
                datasheet.setId_datasheet(id);
                arquivo_banco = dao_datasheet.retornaArquivo(datasheet);
                //cria arquivo pdf temporário
                nome_arquivo = datasheet.getDescricao().replace(".pdf", "");
                f = File.createTempFile(nome_arquivo, ".pdf");
                FileOutputStream fos = new FileOutputStream(f);
                //escreve bytes no arquivo
                fos.write( arquivo_banco );
                //abre arquivo
                Desktop.getDesktop().open(f); 
                fos.close();
                //deleta arquivo quando fechar a aplicação
                f.deleteOnExit();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Arquivo já está aberto");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Nenhuma linha foi selecionada!");
        }
    }//GEN-LAST:event_jBTVerDatasheetActionPerformed

    private void jTBDatasheetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTBDatasheetMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTBDatasheetMouseClicked

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InterfaceDatasheet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterfaceDatasheet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterfaceDatasheet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterfaceDatasheet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InterfaceDatasheet().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBTAlterar;
    private javax.swing.JButton jBTBuscar;
    private javax.swing.JButton jBTBuscarDatasheet;
    private javax.swing.JButton jBTCancelar;
    private javax.swing.JButton jBTExcluir;
    private javax.swing.JButton jBTGravar;
    private javax.swing.JButton jBTNovo;
    private javax.swing.JButton jBTVerDatasheet;
    private javax.swing.JComboBox jCBBuscarPor;
    private javax.swing.JFormattedTextField jFTData;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPBotoes;
    private javax.swing.JPanel jPDatasheet;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTBConsultaDatasheet;
    private javax.swing.JTabbedPane jTBDatasheet;
    private javax.swing.JTextField jTFDescricao;
    private javax.swing.JTextField jTFFiltro;
    private javax.swing.JTextField jTFIDDatasheet;
    // End of variables declaration//GEN-END:variables

    //Pega dados do datasheet na tela
    public Datasheet getDatasheet() {
        datasheet = new Datasheet();
        
        Date data_atual = new Date(System.currentTimeMillis());
        
        int id_datasheet = Integer.parseInt(jTFIDDatasheet.getText());
        datasheet.setId_datasheet(id_datasheet);
        datasheet.setDescricao(jTFDescricao.getText());
        if(importou_arquivo == true){
            datasheet.setArquivo(arquivo);
        }

        datasheet.setData_cadastro(data.stringParaSQLDate(jFTData.getText()));
        
        return datasheet;
    }
    
     public void setcompDatasheet(){
        //Seta dados do datasheet na tela
        jTFIDDatasheet.setText(Integer.toString(datasheet.getId_datasheet()));
        jTFDescricao.setText(datasheet.getDescricao());
        jFTData.setText(data.dateParaString(datasheet.getData_cadastro()));
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.GPEDSCVP.interfaces;

import br.edu.GPEDSCVP.classe.Acesso;
import br.edu.GPEDSCVP.classe.Permissao;
import br.edu.GPEDSCVP.classe.Projeto;
import br.edu.GPEDSCVP.classe.Tela;
import br.edu.GPEDSCVP.dao.daoAcesso;
import br.edu.GPEDSCVP.dao.daoPermissao;
import br.edu.GPEDSCVP.dao.daoProjeto;
import br.edu.GPEDSCVP.dao.daoTela;
import br.edu.GPEDSCVP.util.FormatarData;
import br.edu.GPEDSCVP.util.ManipulaJtable;
import br.edu.GPEDSCVP.util.Mensagens;
import br.edu.GPEDSCVP.util.Rotinas;
import br.edu.GPEDSCVP.util.UltimaSequencia;
import br.edu.GPEDSCVP.util.ValidaAcesso;
import br.edu.GPEDSCVP.util.ValidaBotoes;
import br.edu.GPEDSCVP.util.ValidaCampos;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author Willys
 */
public class InterfaceProjeto extends javax.swing.JFrame {

    Projeto projeto;
    Tela tela;
    daoTela dao_tela;
    Acesso acesso;
    Mensagens mensagem;
    daoPermissao dao_permissao;
    daoAcesso dao_acesso;
    daoProjeto dao_projeto;
    Permissao permissao;
    ValidaAcesso valida_acesso;
    ValidaBotoes valida_botoes;
    ValidaCampos valida_campos;
    ManipulaJtable Jtable;
    FormatarData data;
    int situacao = Rotinas.PADRAO;

    public InterfaceProjeto() {
        initComponents();

        projeto = new Projeto();
        tela = new Tela();
        dao_tela = new daoTela();
        dao_projeto = new daoProjeto();
        acesso = new Acesso();
        mensagem = new Mensagens();
        dao_permissao = new daoPermissao();
        dao_acesso = new daoAcesso();
        permissao = new Permissao();
        valida_acesso = new ValidaAcesso();
        valida_botoes = new ValidaBotoes();
        data = new FormatarData();
        try {
            valida_campos = new ValidaCampos();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao iniciar registro");
        }
        Jtable = new ManipulaJtable();

        //desabilita campos da tela de projeto
        valida_campos.desabilitaCampos(jPProjeto);

        //Adiciona barra de rolagem obs: obrigatorio para conseguir dimensionar automatico as colunas da jtable
        jTBConsultaProjetos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

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

        jTBProjeto = new javax.swing.JTabbedPane();
        jPProjeto = new javax.swing.JPanel();
        jPBotoes = new javax.swing.JPanel();
        jBTNovo = new javax.swing.JButton();
        jBTAlterar = new javax.swing.JButton();
        jBTExcluir = new javax.swing.JButton();
        jBTGravar = new javax.swing.JButton();
        jBTCancelar = new javax.swing.JButton();
        jTFIDProjeto = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTFDescricao = new javax.swing.JTextField();
        jFTData = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTBConsultaProjetos = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Projetos");
        setResizable(false);

        jTBProjeto.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTBProjetoStateChanged(evt);
            }
        });

        jBTNovo.setIcon(new javax.swing.ImageIcon("C:\\Users\\rafa\\Documents\\GPEDS-CVP\\src\\br\\edu\\GPEDSCVP\\icones\\Botoes_Site_5752_Knob_Add.png")); // NOI18N
        jBTNovo.setText("Novo");
        jBTNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTNovoActionPerformed(evt);
            }
        });

        jBTAlterar.setIcon(new javax.swing.ImageIcon("C:\\Users\\rafa\\Documents\\GPEDS-CVP\\src\\br\\edu\\GPEDSCVP\\icones\\Pencil.png")); // NOI18N
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
                .addComponent(jBTAlterar)
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
                    .addComponent(jBTExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBTAlterar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBTNovo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBTCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBTGravar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTFIDProjeto.setEditable(false);
        jTFIDProjeto.setToolTipText("ID Projeto");
        jTFIDProjeto.setName("id_projeto"); // NOI18N

        jLabel1.setText("ID Projeto:");

        jLabel2.setText("Descrição:");

        jTFDescricao.setToolTipText("Descrição");
        jTFDescricao.setName("descricao"); // NOI18N

        jFTData.setEditable(false);
        jFTData.setToolTipText("Data");
        jFTData.setName(""); // NOI18N

        jLabel5.setText("Data:");

        javax.swing.GroupLayout jPProjetoLayout = new javax.swing.GroupLayout(jPProjeto);
        jPProjeto.setLayout(jPProjetoLayout);
        jPProjetoLayout.setHorizontalGroup(
            jPProjetoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPBotoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPProjetoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPProjetoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jTFDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPProjetoLayout.createSequentialGroup()
                        .addGroup(jPProjetoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTFIDProjeto, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFTData, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPProjetoLayout.setVerticalGroup(
            jPProjetoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPProjetoLayout.createSequentialGroup()
                .addGroup(jPProjetoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPProjetoLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel1))
                    .addGroup(jPProjetoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPProjetoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jFTData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTFIDProjeto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(3, 3, 3)
                .addComponent(jTFDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
                .addComponent(jPBotoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTBProjeto.addTab("Cadastro", jPProjeto);

        jTBConsultaProjetos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Projeto", "Descrição", "Data cadastro", "Última alteração"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTBConsultaProjetos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTBConsultaProjetosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTBConsultaProjetos);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 521, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
        );

        jTBProjeto.addTab("Consulta", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTBProjeto)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTBProjeto, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        setSize(new java.awt.Dimension(540, 338));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jBTNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTNovoActionPerformed
        UltimaSequencia ultima;

        //habilita campos da tela
        valida_campos.habilitaCampos(jPProjeto);
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
                int sequencia = (Integer) (ultima.ultimasequencia("PROJETO", "ID_PROJETO"));
                //seta id no campo id_projeto
                jTFIDProjeto.setText(Integer.toString(sequencia));

                //Seta a data atual no campo data
                jFTData.setEnabled(true);
                jFTData.setText(data.DataAtual());

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Falha ao iniciar a inserção de projetos");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Voce não possui permissões para incluir projetos no sistema");
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

            valida_campos.habilitaCampos(jPProjeto);

        } else {
            JOptionPane.showMessageDialog(null, "Voce não possui permissões para alterar projetos no sistema");
        }
    }//GEN-LAST:event_jBTAlterarActionPerformed

    private void jBTExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTExcluirActionPerformed
        projeto.setId_projeto(Integer.parseInt(jTFIDProjeto.getText()));

        if (dao_projeto.verificaExclusao(projeto) == true) {

            if (acesso.getIn_gerente() == 0) {
                //retorna as permissoes de acesso do usuario
                dao_permissao.retornaDadosPermissao(acesso, permissao);
            }

            //Verifica se o usuario possui permissao para excluir registros nessa tela
            if (valida_acesso.verificaAcesso("excluir", acesso, permissao) == true) {

                if (mensagem.ValidaMensagem("Deseja realmente excluir o registro ?") == 0) {

                    if (dao_projeto.inativaProjeto(projeto) == true) {
                        JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
                        //limpa campos
                        valida_campos.LimparCampos(jPProjeto);

                        //Define a situação como incluir para habilitar os botoes utilizados apenas na inclusão
                        situacao = Rotinas.INICIAL;

                        //habilita os botoes utilizados na inclusão e desabilita os restantes
                        valida_botoes.ValidaEstado(jPBotoes, situacao);

                        //desabilita campos
                        valida_campos.desabilitaCampos(jPProjeto);
                    }

                }
            } else {
                JOptionPane.showMessageDialog(null, "Voce não possui permissões para excluir projetos no sistema");
            }
        }else{
            JOptionPane.showMessageDialog(null, "O registro não pode ser "
            + "excluído, ele está sendo utilizado em outro cadastro/movimento");
        }
    }//GEN-LAST:event_jBTExcluirActionPerformed

    private void jBTGravarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBTGravarMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jBTGravarMouseExited

    private void jBTGravarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTGravarActionPerformed
        //Se for inclusão
        if (situacao == Rotinas.INCLUIR) {
            if (valida_campos.validacamposobrigatorios(jPProjeto, "PROJETO") == 0) {

                try {
                    //pega dados do projeto na tela
                    getProjeto();
                    //inclui projeto
                    if (dao_projeto.incluir(projeto) == true) {
                        //se ocorreu tudo bem na inclusão
                        JOptionPane.showMessageDialog(null, "Salvo com Sucesso");
                        //limpa campos
                        valida_campos.LimparCampos(jPProjeto);

                        //Define a situação como incluir para habilitar os botoes utilizados apenas na inclusão
                        situacao = Rotinas.INICIAL;

                        //habilita os botoes utilizados na inclusão e desabilita os restantes
                        valida_botoes.ValidaEstado(jPBotoes, situacao);

                        //desabilita campos
                        valida_campos.desabilitaCampos(jPProjeto);
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Falha ao incluir projeto");
                }

            }
        } else if (situacao == Rotinas.ALTERAR) {
            //pega dados do projeto na tela
            if (valida_campos.validacamposobrigatorios(jPProjeto, "PROJETO") == 0) {
                try {
                    getProjeto();
                    //altera projeto
                    if (dao_projeto.alterar(projeto) == true) {
                        JOptionPane.showMessageDialog(null, "Salvo com Sucesso");
                        //limpa campos
                        valida_campos.LimparCampos(jPProjeto);

                        //Define a situação como incluir para habilitar os botoes utilizados apenas na inclusão
                        situacao = Rotinas.INICIAL;

                        //habilita os botoes utilizados na inclusão e desabilita os restantes
                        valida_botoes.ValidaEstado(jPBotoes, situacao);

                        //desabilita campos
                        valida_campos.desabilitaCampos(jPProjeto);
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Falha ao alterar projeto");
                }
            }
        }
    }//GEN-LAST:event_jBTGravarActionPerformed

    private void jBTCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTCancelarActionPerformed
        //limpa campos
        valida_campos.LimparCampos(jPProjeto);

        //Define a situação como incluir para habilitar os botoes utilizados apenas na inclusão
        situacao = Rotinas.INICIAL;

        //habilita os botoes utilizados na inclusão e desabilita os restantes
        valida_botoes.ValidaEstado(jPBotoes, situacao);

        //desabilita campos
        valida_campos.desabilitaCampos(jPProjeto);
    }//GEN-LAST:event_jBTCancelarActionPerformed

    private void jTBConsultaProjetosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTBConsultaProjetosMouseClicked
        //Verifica se houve 2 cliques do mouse
        if (evt.getClickCount() == 2) {

            //recupera a linha clicada
            int linha = jTBConsultaProjetos.getSelectedRow();

            //Limpa os campos da tela de projeto
            valida_campos.LimparCampos(jPProjeto);

            //recupera o id do projeto selecionada
            String codigo = (String) jTBConsultaProjetos.getValueAt(linha, 0);

            //retorna dados do projeto
            projeto.setId_projeto(Integer.parseInt(codigo));
            dao_projeto.retornardados(projeto);

            //Seta na tela de cadastro os dados do projeto
            setcompProjeto();
            //retorna para tela de cadastro
            jTBProjeto.setSelectedIndex(0);

            //Define a situação como incluir para habilitar os botoes utilizados apenas na inclusão
            situacao = Rotinas.PADRAO;

            //habilita os botoes utilizados na inclusão e desabilita os restantes
            valida_botoes.ValidaEstado(jPBotoes, situacao);

            //desabilita campos da tela de projeto
            valida_campos.desabilitaCampos(jPProjeto);
        }
    }//GEN-LAST:event_jTBConsultaProjetosMouseClicked

    private void jTBProjetoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTBProjetoStateChanged
        if (jTBProjeto.getSelectedIndex() == 1) {
            //Se não for gerente
            if (acesso.getIn_gerente() == 0) {
                //retorna as permissoes de acesso do usuario
                dao_permissao.retornaDadosPermissao(acesso, permissao);
            }

            //Verifica se o usuario possui permissao para acessar essa tela
            if (valida_acesso.verificaAcesso("consultar", acesso, permissao) == true) {
                //faz uma consulta geral de projetos no banco
                dao_projeto.consultaGeral(projeto);
                //preenche dados na jtable
                Jtable.PreencherJtableGenerico(jTBConsultaProjetos, new String[]{"id_projeto", "descricao", "data_cadastro", "data_alter"}, projeto.getRetorno());
                //ajusta largura das colunas
                Jtable.ajustarColunasDaTabela(jTBConsultaProjetos);
            } else {
                JOptionPane.showMessageDialog(null, "Você nao possui permissões para consultar projetos no sistema");
                //retorna para a tela de cadastro
                jTBProjeto.setSelectedIndex(0);
            }
        }
    }//GEN-LAST:event_jTBProjetoStateChanged

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
            java.util.logging.Logger.getLogger(InterfaceProjeto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterfaceProjeto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterfaceProjeto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterfaceProjeto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InterfaceProjeto().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBTAlterar;
    private javax.swing.JButton jBTCancelar;
    private javax.swing.JButton jBTExcluir;
    private javax.swing.JButton jBTGravar;
    private javax.swing.JButton jBTNovo;
    private javax.swing.JFormattedTextField jFTData;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPBotoes;
    private javax.swing.JPanel jPProjeto;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTBConsultaProjetos;
    private javax.swing.JTabbedPane jTBProjeto;
    private javax.swing.JTextField jTFDescricao;
    private javax.swing.JTextField jTFIDProjeto;
    // End of variables declaration//GEN-END:variables

    //Pega dados do projeto na tela
    public Projeto getProjeto() {
        projeto = new Projeto();

        Date data_atual = new Date(System.currentTimeMillis());

        int id_projeto = Integer.parseInt(jTFIDProjeto.getText());
        projeto.setId_projeto(id_projeto);
        projeto.setDescricao(jTFDescricao.getText());
        projeto.setData_alter(data_atual);
        projeto.setData_cadastro(data.stringParaSQLDate(jFTData.getText()));

        return projeto;
    }

    public void setcompProjeto() {
        //Seta dados do projeto na tela
        jTFIDProjeto.setText(Integer.toString(projeto.getId_projeto()));
        jTFDescricao.setText(projeto.getDescricao());
        jFTData.setText(data.dateParaString(projeto.getData_cadastro()));
    }

}

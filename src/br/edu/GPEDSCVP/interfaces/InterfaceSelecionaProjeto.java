/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.GPEDSCVP.interfaces;

import br.edu.GPEDSCVP.classe.Componente;
import br.edu.GPEDSCVP.classe.ComponenteFornecimento;
import br.edu.GPEDSCVP.classe.ComponenteVersaoProjeto;
import br.edu.GPEDSCVP.classe.Fornecimento;
import br.edu.GPEDSCVP.classe.Projeto;
import br.edu.GPEDSCVP.classe.VersaoProjeto;
import br.edu.GPEDSCVP.dao.daoComponente;
import br.edu.GPEDSCVP.dao.daoComponenteVersaoProjeto;
import br.edu.GPEDSCVP.dao.daoFornecimento;
import br.edu.GPEDSCVP.dao.daoProjeto;
import br.edu.GPEDSCVP.dao.daoVersaoProjeto;
import br.edu.GPEDSCVP.util.ComboBox;
import br.edu.GPEDSCVP.util.ManipulaJtable;
import br.edu.GPEDSCVP.util.ValidaCampos;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Willys
 */
public class InterfaceSelecionaProjeto extends javax.swing.JFrame {

    Projeto projeto;
    Componente componente;
    daoComponente dao_componente;
    daoFornecimento dao_fornecimento;
    ComponenteFornecimento comp_fornec;
    ComponenteVersaoProjeto comp_vers_proj;
    daoComponenteVersaoProjeto dao_comp_vers;
    VersaoProjeto versao;
    daoProjeto dao_projeto;
    daoVersaoProjeto dao_versao;
    ValidaCampos valida_campos;
    ManipulaJtable Jtable;
    ComboBox combo;
    
    int[] array_projetos;
    int[] array_versoes;
    
    public InterfaceSelecionaProjeto() {
        initComponents();
        
        projeto = new Projeto();
        componente = new Componente();
        dao_componente = new daoComponente();
        dao_fornecimento = new daoFornecimento();
        comp_fornec = new ComponenteFornecimento();
        comp_vers_proj = new ComponenteVersaoProjeto();
        dao_projeto = new daoProjeto();
        dao_comp_vers = new daoComponenteVersaoProjeto();
        dao_versao = new daoVersaoProjeto();
        combo = new ComboBox();
        Jtable = new ManipulaJtable();
        versao = new VersaoProjeto();
        try {
            valida_campos = new ValidaCampos();
        } catch (SQLException ex) {
            Logger.getLogger(InterfaceSelecionaProjeto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        dao_projeto.consultaGeral(projeto);
        //Preenche dados nas ComboBox de fornecedor
        array_projetos = combo.PreencherCombo(jCBProjeto, "descricao", projeto.getRetorno(), "id_projeto");
        //seta no array da classe de fornecedores a lista de fornecedores listadas na combo
        projeto.setArray_projetos(array_projetos);
        
        jTFComponente.setText(comp_fornec.getDescricao());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPSelecionarProjeto = new javax.swing.JPanel();
        jCBProjeto = new javax.swing.JComboBox();
        jCBVersoes = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTFComponente = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTFQuantidade = new javax.swing.JTextField();
        jBTConcluir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Selecionar Projeto");
        setResizable(false);

        jCBProjeto.setToolTipText("Projeto");
        jCBProjeto.setName("id_projeto"); // NOI18N
        jCBProjeto.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                jCBProjetoPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
                jCBProjetoPopupMenuWillBecomeVisible(evt);
            }
        });

        jCBVersoes.setToolTipText("Versão");
        jCBVersoes.setName("cod_vers_projeto"); // NOI18N
        jCBVersoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBVersoesActionPerformed(evt);
            }
        });

        jLabel1.setText("Projeto:");

        jLabel2.setText("Versão:");

        jTFComponente.setEditable(false);
        jTFComponente.setName("sem_nome"); // NOI18N

        jLabel3.setText("Componente:");

        jLabel4.setText("Quantidade:");

        jTFQuantidade.setToolTipText("Quantidade");
        jTFQuantidade.setName("qntd_para_projeto"); // NOI18N
        jTFQuantidade.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTFQuantidadeKeyTyped(evt);
            }
        });

        jBTConcluir.setIcon(new javax.swing.ImageIcon("C:\\Users\\rafa\\Documents\\GPEDS-CVP\\src\\br\\edu\\GPEDSCVP\\icones\\accept.png")); // NOI18N
        jBTConcluir.setText("Concluir");
        jBTConcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTConcluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPSelecionarProjetoLayout = new javax.swing.GroupLayout(jPSelecionarProjeto);
        jPSelecionarProjeto.setLayout(jPSelecionarProjetoLayout);
        jPSelecionarProjetoLayout.setHorizontalGroup(
            jPSelecionarProjetoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPSelecionarProjetoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPSelecionarProjetoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jTFComponente, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPSelecionarProjetoLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addGap(66, 66, 66))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPSelecionarProjetoLayout.createSequentialGroup()
                        .addGroup(jPSelecionarProjetoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jTFQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBTConcluir))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPSelecionarProjetoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jCBProjeto, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jCBVersoes, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPSelecionarProjetoLayout.setVerticalGroup(
            jPSelecionarProjetoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPSelecionarProjetoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPSelecionarProjetoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPSelecionarProjetoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCBProjeto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCBVersoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPSelecionarProjetoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPSelecionarProjetoLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(5, 5, 5)
                        .addComponent(jTFComponente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jBTConcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPSelecionarProjeto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPSelecionarProjeto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(293, 215));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jBTConcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTConcluirActionPerformed
        if (valida_campos.validacamposobrigatorios(jPSelecionarProjeto, "COMPONENTES_VERSAO_PROJETO") == 0) {
            try {
                if(comp_fornec.getQntd_componente() >= Integer.parseInt(jTFQuantidade.getText())){
                    getCompVersProj();
                    
                    //verifica se o componente selecionado possui composição
                    componente.setId_componente(comp_vers_proj.getId_componente());
                    componente.setDescricao(comp_vers_proj.getComponente());
                    versao.setCod_vers_projeto(comp_vers_proj.getCod_vers_projeto());
                    
                    //componene possui composição ?
                    if(dao_componente.verificaExisteComposicao(componente) == true){
                        //Toda composição do componente tem um registro de fornecimento para a versão do projeto em questão?
                        if(dao_fornecimento.verificaExisteFornecimentoComposicao(comp_vers_proj, versao) == true){  
                            //sim, então add o componente para a versão
                            dao_comp_vers.addComponenteVersao(comp_vers_proj, comp_fornec.getTabela(), comp_fornec.getSituacao());
                        //    dao_componente.calculaComposicaoComponente(componente);
                            Jtable.ajustarColunasDaTabela(comp_vers_proj.getTabela());
                            this.dispose();
                        }else{
                            //não, então fecha a tela e da a mensagem  de erro
                            this.dispose();
                        }
                    }else{
                        //não, o componente não possui composição, então add direto sem verificação
                        dao_comp_vers.addComponenteVersao(comp_vers_proj, comp_fornec.getTabela(), comp_fornec.getSituacao());
                        Jtable.ajustarColunasDaTabela(comp_vers_proj.getTabela());
                        this.dispose(); 
                    }

                }else{
                    JOptionPane.showMessageDialog(null, "Quantidade informada está acima da quantidade restante / "+comp_fornec.getQntd_componente());
                    jTFQuantidade.grabFocus();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Falha ao adicionar componente");
            }
        }
    }//GEN-LAST:event_jBTConcluirActionPerformed

    private void jCBVersoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBVersoesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCBVersoesActionPerformed

    private void jCBProjetoPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jCBProjetoPopupMenuWillBecomeInvisible
        
        if(jCBProjeto.getSelectedIndex() > 0){
            int id_projeto = projeto.getArray_projetos(jCBProjeto.getSelectedIndex() - 1);
            versao.setId_projeto(id_projeto);

            dao_versao.consultaCodigo(versao);
            //Preenche dados nas ComboBox de versões 
            array_versoes = combo.PreencherCombo(jCBVersoes, "versao", versao.getRetorno(), "cod_vers_projeto");
            //seta no array da classe de versões a lista de versões listadas na combo
            versao.setArray_versoes(array_versoes); 
        }else{
            jCBVersoes.removeAllItems();
        }
    }//GEN-LAST:event_jCBProjetoPopupMenuWillBecomeInvisible

    private void jCBProjetoPopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jCBProjetoPopupMenuWillBecomeVisible
        dao_projeto.consultaGeral(projeto);
        //Preenche dados nas ComboBox de fornecedor
        array_projetos = combo.PreencherCombo(jCBProjeto, "descricao", projeto.getRetorno(), "id_projeto");
        //seta no array da classe de fornecedores a lista de fornecedores listadas na combo
        projeto.setArray_projetos(array_projetos);
    }//GEN-LAST:event_jCBProjetoPopupMenuWillBecomeVisible

    private void jTFQuantidadeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFQuantidadeKeyTyped
        String caracteres="0987654321";
        if(!caracteres.contains(evt.getKeyChar()+"")){
            evt.consume();
        }
    }//GEN-LAST:event_jTFQuantidadeKeyTyped

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
            java.util.logging.Logger.getLogger(InterfaceSelecionaProjeto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterfaceSelecionaProjeto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterfaceSelecionaProjeto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterfaceSelecionaProjeto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InterfaceSelecionaProjeto().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBTConcluir;
    private javax.swing.JComboBox jCBProjeto;
    private javax.swing.JComboBox jCBVersoes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPSelecionarProjeto;
    private javax.swing.JTextField jTFComponente;
    private javax.swing.JTextField jTFQuantidade;
    // End of variables declaration//GEN-END:variables

    public ComponenteVersaoProjeto getCompVersProj(){
        
        comp_vers_proj = new ComponenteVersaoProjeto();
        
        Date data_atual = new Date(System.currentTimeMillis());

        comp_vers_proj.setVersao(Double.parseDouble(jCBVersoes.getSelectedItem().toString()));
        comp_vers_proj.setId_projeto(projeto.getArray_projetos(jCBProjeto.getSelectedIndex() - 1));
        comp_vers_proj.setCod_vers_projeto(versao.getArray_versoes(jCBVersoes.getSelectedIndex() - 1));
        comp_vers_proj.setProjeto(jCBProjeto.getSelectedItem().toString());
        comp_vers_proj.setId_componente(comp_fornec.getId_componente());
        comp_vers_proj.setComponente(jTFComponente.getText());
        comp_vers_proj.setQntd_para_projeto(Integer.parseInt(jTFQuantidade.getText()));
        comp_vers_proj.setData_alter(data_atual);

        return comp_vers_proj;
    }
}

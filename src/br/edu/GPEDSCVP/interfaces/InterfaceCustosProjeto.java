/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.GPEDSCVP.interfaces;

import br.edu.GPEDSCVP.classe.Projeto;
import br.edu.GPEDSCVP.classe.VersaoProjeto;
import br.edu.GPEDSCVP.dao.daoCustos;
import br.edu.GPEDSCVP.dao.daoProjeto;
import br.edu.GPEDSCVP.dao.daoVersaoProjeto;
import br.edu.GPEDSCVP.util.ComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author rafa
 */
public class InterfaceCustosProjeto extends javax.swing.JFrame {

    Projeto projeto;
    VersaoProjeto versao_projeto;
    daoProjeto dao_projeto;
    daoCustos dao_custos;
    daoVersaoProjeto dao_versao;
    int[] array_versoes;
    int[] array_projetos;
    ComboBox combo;
    
    
    public InterfaceCustosProjeto() {
        initComponents();
        
        projeto = new Projeto();
        versao_projeto = new VersaoProjeto();
        dao_projeto = new daoProjeto();
        combo = new ComboBox();
        dao_versao = new daoVersaoProjeto();
        dao_custos = new daoCustos();
        
        //carrega dados nas combobox
        dao_projeto.consultaGeral(projeto);
        //Preenche dados nas ComboBox de projetos
        array_projetos = combo.PreencherCombo(jCBProjeto, "descricao", projeto.getRetorno(), "id_projeto");
        //seta no array da classe de projetos a lista de projetos listadas na combo
        projeto.setArray_projetos(array_projetos);
        
        jCBTipoCusto.addItem("Selecione item");
        jCBTipoCusto.addItem("Total do projeto");
        jCBTipoCusto.addItem("Versão do projeto");
        jCBTipoCusto.addItem("Protótipo versão");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPCustosProjeto = new javax.swing.JPanel();
        jCBTipoCusto = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jCBProjeto = new javax.swing.JComboBox();
        jCBVersao = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTBComponentesMecanicos = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTFTotalProjeto = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTBComponentesEletronicos = new javax.swing.JTable();
        jTFTotalEletronicos1 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTFTotalEletronicos2 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTFTotalEletronicos3 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTBCertificações = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jBTListar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Custos do projeto");
        setResizable(false);

        jCBTipoCusto.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                jCBTipoCustoPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jLabel1.setText("Custo:");

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

        jLabel2.setText("Projeto:");

        jLabel3.setText("Versão:");

        jTBComponentesMecanicos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Título 1", "Título 2", "Título 3", "Título 4"
            }
        ));
        jScrollPane1.setViewportView(jTBComponentesMecanicos);

        jLabel4.setText("Componentes eletrônicos:");

        jLabel5.setText("Componentes mecânicos:");

        jLabel6.setText("Total R$:");

        jTBComponentesEletronicos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Componentes versão", "ID Componente", "Descrição", "Qntd", "ID Moeda", "Moeda", "Valor unit", "Imposto unit R$", "Data Fornecimento", "Total R$", "ID moeda", "Valor frete", "ID moeda", "Valor imposto"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTBComponentesEletronicos);

        jLabel7.setText("Total R$:");

        jLabel8.setText("Total R$:");

        jLabel9.setText("Certificações:");

        jTFTotalEletronicos3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFTotalEletronicos3ActionPerformed(evt);
            }
        });

        jLabel10.setText("Total R$:");

        jTBCertificações.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Título 1", "Título 2", "Título 3", "Título 4"
            }
        ));
        jScrollPane4.setViewportView(jTBCertificações);

        jLabel11.setText("Certificações:");

        jBTListar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/edu/GPEDSCVP/icones/application_cascade.png"))); // NOI18N
        jBTListar.setText("Listar");
        jBTListar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTListarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPCustosProjetoLayout = new javax.swing.GroupLayout(jPCustosProjeto);
        jPCustosProjeto.setLayout(jPCustosProjetoLayout);
        jPCustosProjetoLayout.setHorizontalGroup(
            jPCustosProjetoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPCustosProjetoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPCustosProjetoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPCustosProjetoLayout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPCustosProjetoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTFTotalEletronicos3, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPCustosProjetoLayout.createSequentialGroup()
                        .addGroup(jPCustosProjetoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPCustosProjetoLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTFTotalEletronicos1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPCustosProjetoLayout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTFTotalProjeto, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPCustosProjetoLayout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(8, 8, 8))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPCustosProjetoLayout.createSequentialGroup()
                                .addGroup(jPCustosProjetoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jCBTipoCusto, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPCustosProjetoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jCBProjeto, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                                .addGroup(jPCustosProjetoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jCBVersao, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(29, 29, 29)))
                        .addGroup(jPCustosProjetoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPCustosProjetoLayout.createSequentialGroup()
                                .addGap(337, 337, 337)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTFTotalEletronicos2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPCustosProjetoLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(jPCustosProjetoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 489, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5)
                                    .addComponent(jBTListar, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
        );
        jPCustosProjetoLayout.setVerticalGroup(
            jPCustosProjetoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPCustosProjetoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPCustosProjetoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPCustosProjetoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCBTipoCusto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCBProjeto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCBVersao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBTListar))
                .addGap(14, 14, 14)
                .addGroup(jPCustosProjetoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPCustosProjetoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGroup(jPCustosProjetoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPCustosProjetoLayout.createSequentialGroup()
                        .addGroup(jPCustosProjetoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPCustosProjetoLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPCustosProjetoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(jTFTotalProjeto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPCustosProjetoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jTFTotalEletronicos2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel8))))
                            .addGroup(jPCustosProjetoLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel11)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPCustosProjetoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jTFTotalEletronicos1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(149, 149, 149)
                        .addComponent(jLabel9)
                        .addContainerGap())
                    .addGroup(jPCustosProjetoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFTotalEletronicos3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPCustosProjeto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPCustosProjeto, javax.swing.GroupLayout.PREFERRED_SIZE, 624, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(1034, 663));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTFTotalEletronicos3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFTotalEletronicos3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFTotalEletronicos3ActionPerformed

    private void jCBProjetoPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jCBProjetoPopupMenuWillBecomeInvisible
        try {
            if(jCBProjeto.getSelectedIndex() > 0){
                jCBVersao.setEditable(true);
                versao_projeto.setId_projeto(projeto.getArray_projetos(jCBProjeto.getSelectedIndex() - 1));
                //consulta versões para preencher na combobox de versões
                dao_versao.consultaCodigo(versao_projeto);
                array_versoes = combo.PreencherCombo(jCBVersao, "versao", versao_projeto.getRetorno(), "cod_vers_projeto");
                //seta no array da classe de versoes a lista de versoes listadas na combo
                versao_projeto.setArray_versoes(array_versoes);
            }else{
                jCBVersao.removeAllItems();
            } 
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jCBProjetoPopupMenuWillBecomeInvisible

    private void jCBProjetoPopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jCBProjetoPopupMenuWillBecomeVisible
         //carrega dados nas combobox
        dao_projeto.consultaGeral(projeto);
        //Preenche dados nas ComboBox de projetos
        array_projetos = combo.PreencherCombo(jCBProjeto, "descricao", projeto.getRetorno(), "id_projeto");
        //seta no array da classe de projetos a lista de projetos listadas na combo
        projeto.setArray_projetos(array_projetos);
    }//GEN-LAST:event_jCBProjetoPopupMenuWillBecomeVisible

    private void jBTListarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTListarActionPerformed
        Integer id_projeto;
        Integer id_versao;
        
        switch(jCBTipoCusto.getSelectedIndex()){
            //caso não selecionou nenhuma opção
            case 0:
                JOptionPane.showMessageDialog(null, "Selecione o tipo de custo para consulta!");
                break;
            //caso selecionou custo total do projeto
            case 1:
                if(jCBProjeto.getSelectedIndex() > 0){
                    id_projeto = projeto.getArray_projetos(jCBProjeto.getSelectedIndex() - 1);
                    projeto.setId_projeto(id_projeto);
                    dao_custos.consultaTodosCompFornecProjeto(projeto, "E");
                    //dao_custos.consultaTodosCompFornecProjeto(projeto, "M");
                }else{
                    JOptionPane.showMessageDialog(null, "Selecione um projeto!");
                }
                break;
        }
    }//GEN-LAST:event_jBTListarActionPerformed

    private void jCBTipoCustoPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jCBTipoCustoPopupMenuWillBecomeInvisible
        switch(jCBTipoCusto.getSelectedIndex()){
            case 1:
                try {
                    jCBVersao.setSelectedIndex(0);
                    jCBVersao.setEnabled(false);
                } catch (Exception e) {
                    jCBVersao.setEnabled(false);
                }
                break;
                
            case 2:
                jCBVersao.setEnabled(true);
                break;
                
            case 3:
                jCBVersao.setEnabled(true);
                break;
        }
    }//GEN-LAST:event_jCBTipoCustoPopupMenuWillBecomeInvisible

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
            java.util.logging.Logger.getLogger(InterfaceCustosProjeto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterfaceCustosProjeto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterfaceCustosProjeto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterfaceCustosProjeto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InterfaceCustosProjeto().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBTListar;
    private javax.swing.JComboBox jCBProjeto;
    private javax.swing.JComboBox jCBTipoCusto;
    private javax.swing.JComboBox jCBVersao;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPCustosProjeto;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTBCertificações;
    private javax.swing.JTable jTBComponentesEletronicos;
    private javax.swing.JTable jTBComponentesMecanicos;
    private javax.swing.JTextField jTFTotalEletronicos1;
    private javax.swing.JTextField jTFTotalEletronicos2;
    private javax.swing.JTextField jTFTotalEletronicos3;
    private javax.swing.JTextField jTFTotalProjeto;
    // End of variables declaration//GEN-END:variables
}

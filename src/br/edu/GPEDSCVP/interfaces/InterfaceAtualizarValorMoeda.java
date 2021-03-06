/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.GPEDSCVP.interfaces;

import br.edu.GPEDSCVP.classe.Acesso;
import br.edu.GPEDSCVP.classe.AtualizacaoMoeda;
import br.edu.GPEDSCVP.classe.Moeda;
import br.edu.GPEDSCVP.classe.Permissao;
import br.edu.GPEDSCVP.dao.daoAcesso;
import br.edu.GPEDSCVP.dao.daoMoeda;
import br.edu.GPEDSCVP.dao.daoPermissao;
import br.edu.GPEDSCVP.interfaces.InterfacePessoa.EvenOddRenderer;
import br.edu.GPEDSCVP.util.ManipulaJtable;
import br.edu.GPEDSCVP.util.Mensagens;
import br.edu.GPEDSCVP.util.TableCellListener;
import br.edu.GPEDSCVP.util.ValidaAcesso;
import br.edu.GPEDSCVP.util.ValidaBotoes;
import java.awt.event.ActionEvent;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Willys
 */
public class InterfaceAtualizarValorMoeda extends javax.swing.JFrame {

    Acesso acesso;
    AtualizacaoMoeda atualizacao_moeda;
    Moeda moeda;
    Mensagens mensagem;
    daoPermissao dao_permissao;
    daoAcesso dao_acesso;
    daoMoeda dao_moeda;
    Permissao permissao;
    ValidaAcesso valida_acesso;
    ValidaBotoes valida_botoes;
    ManipulaJtable Jtable;
    
    public InterfaceAtualizarValorMoeda() {
        initComponents();
        
        acesso = new Acesso();
        moeda = new Moeda();
        dao_permissao = new daoPermissao();
        dao_acesso = new daoAcesso();
        dao_moeda = new daoMoeda();
        permissao = new Permissao();
        valida_acesso = new ValidaAcesso();
        valida_botoes = new ValidaBotoes();
        Jtable = new ManipulaJtable();
        mensagem = new Mensagens();
        atualizacao_moeda = new AtualizacaoMoeda();
        
        new TableCellListener(jTBAtualizarMoedas, new TableCellEditorAction());
        
        //Seta mascara na coluna de valor da jtable
        Jtable.setarMascara(jTBAtualizarMoedas, jFTMascara,3);
        
        //Adiciona barra de rolagem obs: obrigatorio para conseguir dimensionar automatico as colunas da jtable
        jTBAtualizarMoedas.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
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

        jFTMascara = new javax.swing.JFormattedTextField();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTBAtualizarMoedas = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTBAtualizacoes = new javax.swing.JTable();

        jFTMascara.setText("jFormattedTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Atualização do valor das moedas");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jTBAtualizarMoedas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Moeda", "Descrição", "Unidade", "Valor R$", "Última atualização", "alterado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTBAtualizarMoedas.setName("Atualizar Valor Moeda"); // NOI18N
        jScrollPane1.setViewportView(jTBAtualizarMoedas);
        if (jTBAtualizarMoedas.getColumnModel().getColumnCount() > 0) {
            jTBAtualizarMoedas.getColumnModel().getColumn(3).setResizable(false);
            jTBAtualizarMoedas.getColumnModel().getColumn(5).setMinWidth(0);
            jTBAtualizarMoedas.getColumnModel().getColumn(5).setPreferredWidth(0);
            jTBAtualizarMoedas.getColumnModel().getColumn(5).setMaxWidth(0);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Atualizar", jPanel1);

        jTBAtualizacoes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Moeda", "Descrição", "Unidade", "Valor R$", "Última atualização"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTBAtualizacoes.setName("Atualizar Valor Moeda"); // NOI18N
        jScrollPane2.setViewportView(jTBAtualizacoes);
        if (jTBAtualizacoes.getColumnModel().getColumnCount() > 0) {
            jTBAtualizacoes.getColumnModel().getColumn(3).setResizable(false);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Atualizações", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        setSize(new java.awt.Dimension(381, 291));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
          //Se não for gerente
            if (acesso.getIn_gerente() == 0) {
                //retorna as permissoes de acesso do usuario  
                dao_permissao.retornaDadosPermissao(acesso, permissao);
            }

            //Verifica se o usuario possui permissao para acessar essa tela
            if (valida_acesso.verificaAcesso("consultar", acesso, permissao) == true) {
                //faz uma consulta geral de moedas no banco
                dao_moeda.consultaGeralAtualizacao(atualizacao_moeda);
                //preenche dados na jtable
                Jtable.PreencherJtableGenerico(jTBAtualizarMoedas, new String[]{"id_moeda", "descricao","unidade","valor", "data_atualizacao","false"}, atualizacao_moeda.getRetorno());
                //ajusta largura das colunas
                Jtable.ajustarColunasDaTabela(jTBAtualizarMoedas);
                
                dao_moeda.consultaTodasAtualizacao(atualizacao_moeda);
                //preenche dados na jtable
                Jtable.PreencherJtableGenerico(jTBAtualizacoes, new String[]{"id_moeda", "descricao","unidade","valor", "data_atualizacao"}, atualizacao_moeda.getRetorno());
                //ajusta largura das colunas
                Jtable.ajustarColunasDaTabela(jTBAtualizacoes);
                
            }else{
                JOptionPane.showMessageDialog(null, "Você nao possui permissões para consultar moedas no sistema");
               
            } 
    }//GEN-LAST:event_formWindowOpened

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
            java.util.logging.Logger.getLogger(InterfaceAtualizarValorMoeda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterfaceAtualizarValorMoeda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterfaceAtualizarValorMoeda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterfaceAtualizarValorMoeda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InterfaceAtualizarValorMoeda().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFormattedTextField jFTMascara;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTBAtualizacoes;
    private javax.swing.JTable jTBAtualizarMoedas;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables

     public AtualizacaoMoeda getAtualizacao() {
        //  Variaveis e conversões
        Date data_atual = new Date(System.currentTimeMillis());
       
        atualizacao_moeda.setTabela(jTBAtualizarMoedas);
        atualizacao_moeda.setData_alter(data_atual);
   
        return atualizacao_moeda;
    }
    
    private void onCellEditor(JTable table, int column, int row, Object oldValue, Object newValue){
        System.out.println("Coluna:" + column + "Valor novo: " + newValue + " Valor antigo: " + oldValue);
        if (table == jTBAtualizarMoedas) {
            //Se o valor novo não for vazio 
            if(!newValue.toString().replace(" ", "").equals("")){
                if(column == 3 ){
                    try { 
                        //verifica se o valor setado é um valor double
                        Double valor = 0.00;    
                        valor = Double.parseDouble(table.getValueAt(row, column).toString().replace(".", "").replace(",", "."));
                        if(valor > 0){
                            
                            table.setValueAt(1, row, 5);
                            //Seta valor com ponto em vez de virgula
                            table.setValueAt(valor, row, column);
                        
                       
                            //Se não for gerente
                            if (acesso.getIn_gerente() == 0) {
                                //retorna as permissoes de acesso do usuario  
                                dao_permissao.retornaDadosPermissao(acesso, permissao);
                            }

                            //Verifica se o usuario possui permissao para alterar dados dessa tela
                            if (valida_acesso.verificaAcesso("alterar", acesso, permissao) == true) {
                                getAtualizacao();
                                try {
                                    dao_moeda.incluirAtualizacao(atualizacao_moeda);
                                   // JOptionPane.showMessageDialog(null, "Atualizado com Sucesso!");

                                    //faz uma consulta geral de moedas no banco
                                    dao_moeda.consultaGeralAtualizacao(atualizacao_moeda);
                                    //preenche dados na jtable
                                    Jtable.PreencherJtableGenerico(jTBAtualizarMoedas, new String[]{"id_moeda", "descricao","unidade","valor", "data_atualizacao","false"}, atualizacao_moeda.getRetorno());
                                    //ajusta largura das colunas
                                    Jtable.ajustarColunasDaTabela(jTBAtualizarMoedas);
                                    
                                    dao_moeda.consultaTodasAtualizacao(atualizacao_moeda);
                                    //preenche dados na jtable
                                    Jtable.PreencherJtableGenerico(jTBAtualizacoes, new String[]{"id_moeda", "descricao","unidade","valor", "data_atualizacao"}, atualizacao_moeda.getRetorno());
                                    //ajusta largura das colunas
                                    Jtable.ajustarColunasDaTabela(jTBAtualizacoes);

                                } catch (SQLException ex) {
                                    JOptionPane.showMessageDialog(null, "Falha ao alterar valores das moedas");
                                }

                            }else{
                                JOptionPane.showMessageDialog(null, "Você não possui permissões para alterar valores das moedas no sistema");
                                table.setValueAt(oldValue, row, column);
                            } 
                        
                        }else{
                            JOptionPane.showMessageDialog(null, "O valor da moeda deve ser maior que zero!");
                            table.setValueAt(oldValue, row, column);
                        }
                        
                    } catch (Exception e) {
                        //se não for double, emite a mensagem e retorna para o valor que estava
                        JOptionPane.showMessageDialog(null, "O valor da moeda deve ser numérico!");  
                        table.setValueAt(oldValue, row, column);
                    }
                }
            }else
            {
                //seta na jtable o valor que estava antes de apagar
                table.setValueAt(oldValue, row, column);
            }
  
        }
    }

    class TableCellEditorAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            TableCellListener tbListener = (TableCellListener) e.getSource();
            
            onCellEditor(tbListener.getTable(), tbListener.getColumn(), tbListener.getRow(), tbListener.getOldValue(), tbListener.getNewValue());
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.GPEDSCVP.interfaces;

import br.edu.GPEDSCVP.classe.Acesso;
import br.edu.GPEDSCVP.classe.Usuario;
import br.edu.GPEDSCVP.dao.daoAcesso;
import br.edu.GPEDSCVP.dao.daoPessoa;
import br.edu.GPEDSCVP.util.Criptografia;
import br.edu.GPEDSCVP.util.ValidaCampos;
import java.sql.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Willys
 */
public class InterfaceLogin extends javax.swing.JFrame {
    
    Usuario usuario;
    Acesso acesso;
    Criptografia criptografar;
    daoPessoa dao_pessoa;
    daoAcesso dao_acesso;
    ValidaCampos valida_campos;

    /**
     * Creates new form InterfaceLogin
     */
    public InterfaceLogin() {
        initComponents();
       
        jPImagem.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        
        dao_pessoa = new daoPessoa();
        usuario = new Usuario();
        dao_acesso = new daoAcesso();
        acesso = new Acesso();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPLogin = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTFLogin = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jBTLogar = new javax.swing.JButton();
        jBTCancelar = new javax.swing.JButton();
        jPImagem = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPFSenha = new javax.swing.JPasswordField();

        javax.swing.GroupLayout jPLoginLayout = new javax.swing.GroupLayout(jPLogin);
        jPLogin.setLayout(jPLoginLayout);
        jPLoginLayout.setHorizontalGroup(
            jPLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jPLoginLayout.setVerticalGroup(
            jPLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Login de acesso");

        jLabel1.setText("Login:");

        jLabel2.setText("Senha:");

        jBTLogar.setIcon(new javax.swing.ImageIcon("C:\\Users\\rafa\\Documents\\GPEDS-CVP\\src\\br\\edu\\GPEDSCVP\\icones\\Botoes_Site_5745_Knob_Valid_Green.png")); // NOI18N
        jBTLogar.setText("Logar");
        jBTLogar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTLogarActionPerformed(evt);
            }
        });

        jBTCancelar.setIcon(new javax.swing.ImageIcon("C:\\Users\\rafa\\Documents\\GPEDS-CVP\\src\\br\\edu\\GPEDSCVP\\icones\\Botoes_Site_5750_Knob_Cancel.png")); // NOI18N
        jBTCancelar.setText("Cancelar");
        jBTCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTCancelarActionPerformed(evt);
            }
        });

        jLabel3.setIcon(new javax.swing.ImageIcon("C:\\Users\\rafa\\Documents\\GPEDS-CVP\\src\\br\\edu\\GPEDSCVP\\icones\\user.png")); // NOI18N

        javax.swing.GroupLayout jPImagemLayout = new javax.swing.GroupLayout(jPImagem);
        jPImagem.setLayout(jPImagemLayout);
        jPImagemLayout.setHorizontalGroup(
            jPImagemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPImagemLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPImagemLayout.setVerticalGroup(
            jPImagemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPImagemLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPImagem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1)
                            .addComponent(jTFLogin, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                            .addComponent(jLabel2)
                            .addComponent(jPFSenha)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 138, Short.MAX_VALUE)
                        .addComponent(jBTLogar, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jBTCancelar)))
                .addGap(35, 35, 35))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPFSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPImagem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jBTCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBTLogar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 22, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(431, 285));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jBTCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTCancelarActionPerformed
        jTFLogin.setText("");
        jPFSenha.setText("");
    }//GEN-LAST:event_jBTCancelarActionPerformed

    private void jBTLogarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTLogarActionPerformed
        //Verifica se os campos não estão vazios
        if(!jTFLogin.getText().equals("")){
            if(!jPFSenha.getText().equals("")){
                 //Pega dados de login da tela
                getcompUsuario();
                //verifica se é um login e senha validos
                if(dao_pessoa.validaLoginSenha(usuario) == true){
                    //Pega dados de acesso
                    getcompAcesso();
                    //Grava acesso
                    dao_acesso.gravarAcesso(acesso);
                    //Retorna dados do usuario logado
                    dao_acesso.retornaUsuarioLogado(acesso);
                    //remove da tela a tela atual
                    this.dispose();
                    //Traz para tela a tela principal do sistema 
                    new InterfacePrincipal().setVisible(true);

                }else{
                    JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos");
                }
            }else{
                JOptionPane.showMessageDialog(null, "Favor preencha o campo de senha");
                jPFSenha.grabFocus();
            }   
        }else{
            JOptionPane.showMessageDialog(null, "Favor preencha o campo de login");
            jTFLogin.grabFocus();
        }
    }//GEN-LAST:event_jBTLogarActionPerformed

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
            java.util.logging.Logger.getLogger(InterfaceLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterfaceLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterfaceLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterfaceLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InterfaceLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBTCancelar;
    private javax.swing.JButton jBTLogar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPasswordField jPFSenha;
    private javax.swing.JPanel jPImagem;
    private javax.swing.JPanel jPLogin;
    private javax.swing.JTextField jTFLogin;
    // End of variables declaration//GEN-END:variables

    private Usuario getcompUsuario() {
        
        usuario.setLogin(jTFLogin.getText());
        usuario.setSenha(criptografar.criptografarMD5(jPFSenha.getText()));
        
        return usuario;        
    }
    
    private Acesso getcompAcesso() {
         //  Variaveis e conversões
        Date data_atual = new Date(System.currentTimeMillis());
        
        acesso.setId_usuario(usuario.getId_pessoa());
        acesso.setData_acesso(data_atual);
        
        return acesso;        
    }
}

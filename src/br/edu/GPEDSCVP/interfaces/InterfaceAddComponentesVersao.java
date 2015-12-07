/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.GPEDSCVP.interfaces;

import br.edu.GPEDSCVP.classe.Acesso;
import br.edu.GPEDSCVP.classe.Componente;
import br.edu.GPEDSCVP.classe.ComponenteVersaoProjeto;
import br.edu.GPEDSCVP.classe.ComposicaoComponente;
import br.edu.GPEDSCVP.classe.Datasheet;
import br.edu.GPEDSCVP.classe.Permissao;
import br.edu.GPEDSCVP.classe.Tela;
import br.edu.GPEDSCVP.classe.VersaoProjeto;
import br.edu.GPEDSCVP.dao.daoAcesso;
import br.edu.GPEDSCVP.dao.daoComponente;
import br.edu.GPEDSCVP.dao.daoComponenteVersaoProjeto;
import br.edu.GPEDSCVP.dao.daoDatasheet;
import br.edu.GPEDSCVP.dao.daoPermissao;
import br.edu.GPEDSCVP.dao.daoTela;
import br.edu.GPEDSCVP.dao.daoVersaoProjeto;
import br.edu.GPEDSCVP.util.ComboBox;
import br.edu.GPEDSCVP.util.FormatarData;
import br.edu.GPEDSCVP.util.ManipulaJtable;
import br.edu.GPEDSCVP.util.Mensagens;
import br.edu.GPEDSCVP.util.ValidaAcesso;
import br.edu.GPEDSCVP.util.ValidaCampos;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author Willys
 */
public class InterfaceAddComponentesVersao extends javax.swing.JFrame {

    Componente componente;
    VersaoProjeto versao_projeto;
    ComposicaoComponente composicao;
    ComponenteVersaoProjeto comp_vers_proj;
    Tela tela;
    Datasheet datasheet;
    daoDatasheet dao_datasheet;
    daoTela dao_tela;
    ComboBox combo;
    daoComponente dao_componente;
    daoVersaoProjeto dao_versao;
    daoComponenteVersaoProjeto dao_comp_vers_proj;
    Acesso acesso;
    Mensagens mensagem;
    daoPermissao dao_permissao;
    daoAcesso dao_acesso;
    Permissao permissao;
    ValidaAcesso valida_acesso;
    ValidaCampos valida_campos;
    ManipulaJtable Jtable;
    FormatarData data;
    
    public InterfaceAddComponentesVersao() {
        initComponents();
        
        componente = new Componente();
        versao_projeto = new VersaoProjeto();
        comp_vers_proj = new ComponenteVersaoProjeto();
        composicao = new ComposicaoComponente();
        tela = new Tela();
        datasheet = new Datasheet();
        dao_datasheet = new daoDatasheet();
        dao_comp_vers_proj = new daoComponenteVersaoProjeto();
        dao_tela = new daoTela();
        dao_versao = new daoVersaoProjeto();
        acesso = new Acesso();
        dao_permissao = new daoPermissao();
        dao_acesso = new daoAcesso();
        dao_componente = new daoComponente();
        permissao = new Permissao();
        valida_acesso = new ValidaAcesso();
        try {
            valida_campos = new ValidaCampos();
        } catch (SQLException ex) {
            Logger.getLogger(InterfaceComposicaoComponente.class.getName()).log(Level.SEVERE, null, ex);
        }
        mensagem = new Mensagens();
        data = new FormatarData();
        combo = new ComboBox();
        Jtable = new ManipulaJtable();
        
        //Adiciona barra de rolagem obs: obrigatorio para conseguir dimensionar automatico as colunas da jtable
        jTBConsultaComponentes.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTBCidade = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTBConsultaComponentes = new javax.swing.JTable();
        jCBBuscarPor = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();
        jTFFiltro = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jBTBuscar = new javax.swing.JButton();
        jBTVerDatasheet = new javax.swing.JButton();
        jBTConcluir = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTFDSComp = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTFQntd = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTFIDComp = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Componentes Fornecidos");
        setResizable(false);

        jTBCidade.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTBCidadeStateChanged(evt);
            }
        });

        jTBConsultaComponentes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Componentes Versões", "ID Projeto", "Projeto", "ID Versão", "Versão", "ID Fornecimento", "Descrição", "Data", "ID Componente", "Componente", "Tipo", "Qntd p/ projeto", "Qntd no projeto", "ID Moeda", "Moeda", "Valor Unit"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTBConsultaComponentes.setName("jTBConsultaComponentes"); // NOI18N
        jTBConsultaComponentes.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jTBConsultaComponentesMouseDragged(evt);
            }
        });
        jTBConsultaComponentes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTBConsultaComponentesMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTBConsultaComponentesMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTBConsultaComponentesMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTBConsultaComponentes);

        jCBBuscarPor.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Geral", "Código", "Descrição" }));

        jLabel14.setText("Buscar por:");

        jLabel29.setText("Filtro de busca do componente:");

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

        jBTConcluir.setIcon(new javax.swing.ImageIcon("C:\\Users\\rafa\\Documents\\GPEDS-CVP\\src\\br\\edu\\GPEDSCVP\\icones\\accept.png")); // NOI18N
        jBTConcluir.setText("Concluir");
        jBTConcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTConcluirActionPerformed(evt);
            }
        });

        jLabel1.setText("Componente:");

        jTFDSComp.setEditable(false);

        jLabel2.setText("Quantidade:");

        jTFQntd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTFQntdKeyTyped(evt);
            }
        });

        jLabel3.setText("ID Componente:");

        jTFIDComp.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jTFIDComp, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTFDSComp, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jTFQntd, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBTConcluir))
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jCBBuscarPor, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel29)
                                .addGap(230, 230, 230))
                            .addComponent(jTFFiltro))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jBTBuscar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jBTVerDatasheet)))
                .addGap(116, 116, 116))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel29)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCBBuscarPor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTFFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBTBuscar)
                            .addComponent(jBTVerDatasheet, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTFQntd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTFIDComp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jBTConcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(5, 5, 5)
                        .addComponent(jTFDSComp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jTBCidade.addTab("Consulta", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTBCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 747, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTBCidade)
        );

        setSize(new java.awt.Dimension(763, 427));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTBConsultaComponentesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTBConsultaComponentesMouseClicked
        //recupera a linha clicada
        int linha = jTBConsultaComponentes.getSelectedRow();
        String descricao = jTBConsultaComponentes.getValueAt(linha, 9).toString();
        Integer id_componente = Integer.parseInt(jTBConsultaComponentes.getValueAt(linha, 8).toString());
        jTFDSComp.setText(descricao);
        jTFIDComp.setText(String.valueOf(id_componente));      
    }//GEN-LAST:event_jTBConsultaComponentesMouseClicked

    private void jTBConsultaComponentesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTBConsultaComponentesMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jTBConsultaComponentesMouseEntered

    private void jTBConsultaComponentesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTBConsultaComponentesMousePressed

    }//GEN-LAST:event_jTBConsultaComponentesMousePressed

    private void jTBConsultaComponentesMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTBConsultaComponentesMouseDragged

    }//GEN-LAST:event_jTBConsultaComponentesMouseDragged

    private void jBTBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTBuscarActionPerformed

        //Se não for gerente
        if (acesso.getIn_gerente() == 0) {
            //retorna as permissoes de acesso do usuario
            dao_permissao.retornaDadosPermissao(acesso, permissao);
        }

        //Verifica se o usuario possui permissao para acessar essa tela
        if (valida_acesso.verificaAcesso("consultar", acesso, permissao) == true) {

            // recupera linha selecionada
            int linha = jTBConsultaComponentes.getSelectedRow();
            int id_busca = 0;
            String ds_busca = "";

            //Tira aspas simples da string para evitar código sql
            valida_campos.IgnoraSQL(jTFFiltro);

            //Combobox buscar por: geral
            switch(jCBBuscarPor.getSelectedIndex()){
                case 0:
                    //Consulta geral de componentes fornecidos para uma versão
                    dao_comp_vers_proj.consultaGeralCompFornecVersao(comp_vers_proj);
                break;
                    
                case 1:
                    //Consulta geral de componentes por código
                    try {
                        id_busca = Integer.parseInt(jTFFiltro.getText());
                        comp_vers_proj.setId_componente(id_busca);
                        dao_comp_vers_proj.consultaCodigoCompFornecVersao(comp_vers_proj);
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Deve informar um valor inteiro para consultar por código");
                        jTFFiltro.grabFocus();
                    }
                break;
                    
                case 2:
                    //Consulta geral de componentes pela descrição
                    ds_busca = jTFFiltro.getText();
                    if(!ds_busca.replace(" ", "").equals("")){
                        comp_vers_proj.setComponente(ds_busca);
                        dao_comp_vers_proj.consultaDescricaoCompFornecVersao(comp_vers_proj);
                    }else{
                        JOptionPane.showMessageDialog(null, "Informe a descrição para consulta");
                        jTFFiltro.grabFocus();
                    }
                break;
            }
            
            //Preenche na JTABLE os dados dos componentes cadastrados
            Jtable.PreencherJtableGenerico(jTBConsultaComponentes, new String[]{"id_comp_versao", "id_projeto", "projeto.descricao", "cod_vers_projeto", "versao", "id_fornecimento",
            "fornecimento.descricao","fornecimento.data_cadastro","id_componente","componente.descricao","tipo","qntd_para_projeto","qntd_no_projeto","id_moeda","unidade","valor_unit"}, comp_vers_proj.getRetorno());
            
            //ajusta largura das colunas
            Jtable.ajustarColunasDaTabela(jTBConsultaComponentes);
            
            if(jTBConsultaComponentes.getRowCount() <= 0){
                JOptionPane.showMessageDialog(null, "Nenhum componente encontrado!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Você nao possui permissões para consultar componentes no sistema");
        }
    }//GEN-LAST:event_jBTBuscarActionPerformed

    private void jBTVerDatasheetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTVerDatasheetActionPerformed
        byte[] arquivo_banco;
        File f = null;
        String nome_arquivo;
        int id = 0;

        try {
            //recupera a linha clicada
            int linha = jTBConsultaComponentes.getSelectedRow();
            //se foi selecionado alguma linha
            if(linha >= 0){
                id = Integer.parseInt(jTBConsultaComponentes.getValueAt(linha, 7).toString());
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
            }else{
                JOptionPane.showMessageDialog(null, "Nenhuma linha foi selecionada!");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Este componente não possui datasheet!");
        }
    }//GEN-LAST:event_jBTVerDatasheetActionPerformed

    private void jBTConcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTConcluirActionPerformed
        JTable tabela = comp_vers_proj.getTabela();
        int totlinha_comp_proj = tabela.getRowCount();
        int linha = jTBConsultaComponentes.getSelectedRow();
        Integer id_comp;
        Integer qntd_add;
        Integer qntd_atual_no_projeto;
        boolean encontrou = false;
        
        if(linha >= 0){
            
            try {
                Integer qntd = Integer.parseInt(jTFQntd.getText());
                qntd_atual_no_projeto = Integer.parseInt(jTBConsultaComponentes.getValueAt(linha, 12).toString());
                if(qntd > 0){
                    getCompVersProj();
                    //verfica se a quantidade ja adicionada para o projeto + a quantidade informada excede a quantidade para o projeto
                    if(totlinha_comp_proj > 0){
                        for (int i_comp = 0; i_comp < totlinha_comp_proj; i_comp++){
                            id_comp = Integer.parseInt(tabela.getValueAt(i_comp, 2).toString());
                            if(id_comp == comp_vers_proj.getId_componente()){
                           
                                encontrou = true;
                                qntd_add = Integer.parseInt(tabela.getValueAt(i_comp, 7).toString());

                                if(qntd_add + qntd > comp_vers_proj.getQntd_para_projeto()){
                                    JOptionPane.showMessageDialog(null, "Quantidade excede a quantidade restante para este projeto");
                                    break;
                                }else{
                                    dao_comp_vers_proj.addCompParaProjeto(comp_vers_proj,jTBConsultaComponentes,composicao.getSituacao());
                                    Jtable.ajustarColunasDaTabela(comp_vers_proj.getTabela());
                                    this.dispose();
                                    break;
                                }
                            }
                        }
                    
                        if(encontrou == false){
                            //verifica se quantidade informada não é maior que a quantidade restante
                            if(qntd <= comp_vers_proj.getQntd_para_projeto() - Integer.parseInt(jTBConsultaComponentes.getValueAt(linha, 12).toString())){
                                dao_comp_vers_proj.addCompParaProjeto(comp_vers_proj,jTBConsultaComponentes,composicao.getSituacao());
                                Jtable.ajustarColunasDaTabela(comp_vers_proj.getTabela());
                                this.dispose();
                            }else{
                                JOptionPane.showMessageDialog(null, "Quantidade excede a quantidade restante para este projeto");
                            } 
                        }
                    }else{
                        //verifica se quantidade informada não é maior que a quantidade restante
                        if(qntd <= comp_vers_proj.getQntd_para_projeto() - Integer.parseInt(jTBConsultaComponentes.getValueAt(linha, 12).toString())){
                            dao_comp_vers_proj.addCompParaProjeto(comp_vers_proj,jTBConsultaComponentes,composicao.getSituacao());
                            Jtable.ajustarColunasDaTabela(comp_vers_proj.getTabela());
                            this.dispose();
                        }else{
                            JOptionPane.showMessageDialog(null, "Quantidade excede a quantidade restante para este projeto");
                        } 
                    }
                    }else{
                        JOptionPane.showMessageDialog(null, "Quantidade deve ser maior que 0!");
                    }
           
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Falha ao adicionar composição");
            } 
        }else{
            JOptionPane.showMessageDialog(null, "Selecione um componente!");
        }
    }//GEN-LAST:event_jBTConcluirActionPerformed

    private void jTBCidadeStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTBCidadeStateChanged

    }//GEN-LAST:event_jTBCidadeStateChanged

    private void jTFQntdKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFQntdKeyTyped
        String caracteres="0987654321";
        if(!caracteres.contains(evt.getKeyChar()+"")){
            evt.consume();
        }
    }//GEN-LAST:event_jTFQntdKeyTyped

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
            java.util.logging.Logger.getLogger(InterfaceAddComponentesVersao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterfaceAddComponentesVersao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterfaceAddComponentesVersao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterfaceAddComponentesVersao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InterfaceAddComponentesVersao().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBTBuscar;
    private javax.swing.JButton jBTConcluir;
    private javax.swing.JButton jBTVerDatasheet;
    private javax.swing.JComboBox jCBBuscarPor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTBCidade;
    private javax.swing.JTable jTBConsultaComponentes;
    private javax.swing.JTextField jTFDSComp;
    private javax.swing.JTextField jTFFiltro;
    private javax.swing.JTextField jTFIDComp;
    private javax.swing.JTextField jTFQntd;
    // End of variables declaration//GEN-END:variables

    public ComponenteVersaoProjeto getCompVersProj(){
        comp_vers_proj = new ComponenteVersaoProjeto();
        int linha = jTBConsultaComponentes.getSelectedRow();
        Date data_atual = new Date(System.currentTimeMillis());
        Timestamp data_fornec = data.stringParaTimeStamp(jTBConsultaComponentes.getValueAt(linha, 7).toString());
        Integer id_comp_vers = Integer.parseInt(jTBConsultaComponentes.getValueAt(linha, 0).toString());
        Integer id_componente = Integer.parseInt(jTBConsultaComponentes.getValueAt(linha, 8).toString());
        Integer id_versao_proj = Integer.parseInt(jTBConsultaComponentes.getValueAt(linha, 3).toString());
        String ds_componente = jTBConsultaComponentes.getValueAt(linha, 9).toString();
        Integer id_moeda = Integer.parseInt(jTBConsultaComponentes.getValueAt(linha, 13).toString());
        String unidade = jTBConsultaComponentes.getValueAt(linha, 14).toString();
        Double valor_unit = Double.parseDouble(jTBConsultaComponentes.getValueAt(linha, 15).toString().replace(",", "."));
        Integer qntd_para_projeto = Integer.parseInt(jTBConsultaComponentes.getValueAt(linha, 11).toString());
        Integer qntd_no_projeto = Integer.parseInt(jTFQntd.getText());

        comp_vers_proj.setId_comp_versao(id_comp_vers);
        comp_vers_proj.setId_componente(id_componente);
        comp_vers_proj.setComponente(ds_componente);
        comp_vers_proj.setCod_vers_projeto(id_versao_proj);
        comp_vers_proj.setId_moeda(id_moeda);
        comp_vers_proj.setUnidade(unidade);
        comp_vers_proj.setValor_unit(valor_unit);
        comp_vers_proj.setQntd_para_projeto(qntd_para_projeto);
        comp_vers_proj.setQntd_no_projeto(qntd_no_projeto);
        comp_vers_proj.setData_alter(data_atual);
        comp_vers_proj.setData_fornec(data_fornec);

        return comp_vers_proj;
    }

}

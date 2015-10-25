/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.GPEDSCVP.interfaces;

import br.edu.GPEDSCVP.classe.Acesso;
import br.edu.GPEDSCVP.classe.Componente;
import br.edu.GPEDSCVP.classe.ComposicaoComponente;
import br.edu.GPEDSCVP.classe.Datasheet;
import br.edu.GPEDSCVP.classe.Permissao;
import br.edu.GPEDSCVP.classe.Tela;
import br.edu.GPEDSCVP.dao.daoAcesso;
import br.edu.GPEDSCVP.dao.daoComponente;
import br.edu.GPEDSCVP.dao.daoDatasheet;
import br.edu.GPEDSCVP.dao.daoPermissao;
import br.edu.GPEDSCVP.dao.daoTela;
import br.edu.GPEDSCVP.util.ComboBox;
import javax.swing.JTable;
import br.edu.GPEDSCVP.util.FormatarData;
import br.edu.GPEDSCVP.util.ManipulaJtable;
import br.edu.GPEDSCVP.util.Mensagens;
import br.edu.GPEDSCVP.util.Rotinas;
import br.edu.GPEDSCVP.util.ValidaAcesso;
import br.edu.GPEDSCVP.util.ValidaCampos;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Willys
 */
public class InterfaceComposicaoComponente extends javax.swing.JFrame {

    Componente componente;
    ComposicaoComponente composicao;
    Tela tela;
    Datasheet datasheet;
    daoDatasheet dao_datasheet;
    daoTela dao_tela;
    ComboBox combo;
    daoComponente dao_componente;
    Acesso acesso;
    Mensagens mensagem;
    daoPermissao dao_permissao;
    daoAcesso dao_acesso;
    Permissao permissao;
    ValidaAcesso valida_acesso;
    ValidaCampos valida_campos;
    ManipulaJtable Jtable;
    FormatarData data;
    int situacao = Rotinas.PADRAO;

    
    public InterfaceComposicaoComponente() {
        initComponents();
        
        //Cria renderer para as Jtable  
        TableCellRenderer renderer = new EvenOddRenderer();
        jTBConsultaComponentes.setDefaultRenderer(Object.class, renderer);

        jTBConsultaComponentes.getSelectionModel().addListSelectionListener(new ListSelectionListener() {  
        @Override  
        public void valueChanged(ListSelectionEvent evt) {  
        if (evt.getValueIsAdjusting())  
            return;  
        int selected = jTBConsultaComponentes.getSelectedRow(); //Use getSelectedRows se vc permite seleção múltipla  
            //faça algo com selected  
        }  
        } ); 
        
        /*
        jTBConsultaComponentes.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mouseClickedCellJtable(evt);
            }
        });
        */
        //Adiciona barra de rolagem obs: obrigatorio para conseguir dimensionar automatico as colunas da jtable
        jTBConsultaComponentes.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        componente = new Componente();
        composicao = new ComposicaoComponente();
        tela = new Tela();
        datasheet = new Datasheet();
        dao_datasheet = new daoDatasheet();
        dao_tela = new daoTela();
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
        jCBTipo = new javax.swing.JComboBox();
        jLabel15 = new javax.swing.JLabel();
        jBTConcluir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Consulta de Componentes");
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
                "Sel", "ID Componente", "Tipo", "Descricao", "Rev", "ID Material", "Material", "ID Datasheet", "Datasheet", "Data Cadastro", "Última alteração", "Qntd"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTBConsultaComponentes.setName("Componentes"); // NOI18N
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
        jTBConsultaComponentes.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jTBConsultaComponentesMouseDragged(evt);
            }
        });
        jScrollPane1.setViewportView(jTBConsultaComponentes);

        jCBBuscarPor.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Geral", "Código", "Descrição" }));

        jLabel14.setText("Buscar por:");

        jLabel29.setText("Filtro de busca:");

        jBTBuscar.setIcon(new javax.swing.ImageIcon("D:\\MEUS ARQUIVOS\\arquivos faculdade\\6PERIODO\\TCCII\\ICONES\\icones\\menores\\magnifier.png")); // NOI18N
        jBTBuscar.setText("Buscar");
        jBTBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTBuscarActionPerformed(evt);
            }
        });

        jBTVerDatasheet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/edu/GPEDSCVP/icones/eye.png"))); // NOI18N
        jBTVerDatasheet.setText("Ver datasheet");
        jBTVerDatasheet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTVerDatasheetActionPerformed(evt);
            }
        });

        jCBTipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Geral", "Eletrônico", "Mecânico" }));

        jLabel15.setText("Tipo:");

        jBTConcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/edu/GPEDSCVP/icones/accept.png"))); // NOI18N
        jBTConcluir.setText("Concluir");
        jBTConcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTConcluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jBTConcluir))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addComponent(jCBBuscarPor, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel15)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addComponent(jCBTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel29)
                                        .addGap(0, 448, Short.MAX_VALUE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jTFFiltro)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jBTBuscar)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jBTVerDatasheet)))))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel29)
                    .addComponent(jLabel15)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCBBuscarPor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCBTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTFFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBTBuscar)
                            .addComponent(jBTVerDatasheet, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBTConcluir)
                .addContainerGap())
        );

        jTBCidade.addTab("Consulta", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTBCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 763, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTBCidade))
        );

        setSize(new java.awt.Dimension(779, 458));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTBConsultaComponentesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTBConsultaComponentesMouseClicked
        //recupera a linha clicada
        int linha = jTBConsultaComponentes.getSelectedRow();
        String descricao = jTBConsultaComponentes.getValueAt(linha, 3).toString();
        Boolean sel = (Boolean) jTBConsultaComponentes.getValueAt(linha, 0);
        Integer id_componente = Integer.parseInt(jTBConsultaComponentes.getValueAt(linha, 1).toString());
        Object valor;
        Integer quantidade;
        Boolean valor_valido = false;
        if (sel != null){
            if(sel == true){
                //evita duplicação de componentes
                if(Jtable.evitarDuplicacao(componente.getTabela(), descricao) == false){
                    if(id_componente != composicao.getId_componente()){
                        //Marca linha como selecionada
                        jTBConsultaComponentes.setValueAt(true, linha, 0);
                        do {  
                            valor = JOptionPane.showInputDialog("Informe a quantidade: "); 
                            //se nao cancelou e não fechou
                            if(valor != null){
                                try {
                                    //valida se foi digitado um valor inteiro
                                    quantidade = Integer.parseInt((String) valor);
                                    valor_valido = true;
                                    //seta na jtable a quantidade
                                    jTBConsultaComponentes.setValueAt(quantidade, linha, 11);
                                   
                                } catch (Exception e) {
                                   //Não foi irformado um valor inteiro
                                   JOptionPane.showMessageDialog(null, "Deve informar um valor inteiro!");
                                   valor_valido = false;
                                }
                            }else{
                                valor_valido = true;
                                jTBConsultaComponentes.setValueAt(false, linha, 0);
                            }
                
                        } while (valor_valido == false); 
                    }else{
                        JOptionPane.showMessageDialog(null, "Componente não pode compor ele mesmo!");
                        jTBConsultaComponentes.setValueAt(false, linha, 0);
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Este componente já foi adicionado na composição!");
                    jTBConsultaComponentes.setValueAt(false, linha, 0);
                }
            }else{
                //seta valor nulo na quantidade se nao estiver selecionado
                jTBConsultaComponentes.setValueAt("", linha, 11);
            }
        }
    }//GEN-LAST:event_jTBConsultaComponentesMouseClicked

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
            
            switch (jCBTipo.getSelectedIndex()) {
                //Tipo : Consulta Geral
                case 0:
                    //Combobox buscar por: geral
                    switch(jCBBuscarPor.getSelectedIndex()){
                        case 0:
                            //Consulta geral de componentes
                            dao_componente.consultageral(componente);
                            break;
                        case 1:
                            //Consulta geral de componentes por código
                            try {
                                id_busca = Integer.parseInt(jTFFiltro.getText());
                                componente.setId_componente(id_busca);
                                dao_componente.consultageralCodigo(componente);
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(null, "Deve informar um valor inteiro para consultar por código");
                                jTFFiltro.grabFocus();
                            }
                            break;
                        case 2:
                            //Consulta geral de componentes pela descrição
                            ds_busca = jTFFiltro.getText();
                            if(!ds_busca.replace(" ", "").equals("")){
                                componente.setDescricao(ds_busca);
                                dao_componente.consultageralDescricao(componente);
                            }else{
                                JOptionPane.showMessageDialog(null, "Informe a descrição para consulta");
                                jTFFiltro.grabFocus();
                            }
                            break;
                    }
                    break;
                //Tipo: Eletrônico
                case 1:
                    
                     //Combobox buscar por: geral
                    switch(jCBBuscarPor.getSelectedIndex()){
                        case 0:
                            //Consulta geral de componentes
                            dao_componente.consultageralEletronicos(componente);
                            break;
                        case 1:
                            //Consulta geral de componentes por código
                            try {
                                id_busca = Integer.parseInt(jTFFiltro.getText());
                                componente.setId_componente(id_busca);
                                dao_componente.consultaeletronicoCodigo(componente);
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(null, "Deve informar um valor inteiro para consultar por código");
                                jTFFiltro.grabFocus();
                            }
                            break;
                        case 2:
                            //Consulta geral de componentes pela descrição
                            ds_busca = jTFFiltro.getText();
                            if(!ds_busca.replace(" ", "").equals("")){
                                componente.setDescricao(ds_busca);
                                dao_componente.consultaeletronicoDescricao(componente);
                            }else{
                                JOptionPane.showMessageDialog(null, "Informe a descrição para consulta");
                                jTFFiltro.grabFocus();
                            }
                            break;
                    }
                    break;
                    
                case 2:
                    
                    //Combobox buscar por: geral
                    switch(jCBBuscarPor.getSelectedIndex()){
                        case 0:
                            //Consulta geral de componentes
                            dao_componente.consultageralMecanicos(componente);
                            break;
                        case 1:
                            //Consulta geral de componentes por código
                            try {
                                id_busca = Integer.parseInt(jTFFiltro.getText());
                                componente.setId_componente(id_busca);
                                dao_componente.consultamecanicoCodigo(componente);
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(null, "Deve informar um valor inteiro para consultar por código");
                                jTFFiltro.grabFocus();
                            }
                            break;
                        case 2:
                            //Consulta geral de componentes pela descrição
                            ds_busca = jTFFiltro.getText();
                            if(!ds_busca.replace(" ", "").equals("")){
                                componente.setDescricao(ds_busca);
                                dao_componente.consultamecanicoDescricao(componente);
                            }else{
                                JOptionPane.showMessageDialog(null, "Informe a descrição para consulta");
                                jTFFiltro.grabFocus();
                            }
                            break;
                    }
                    break;
            }
            //Preenche na JTABLE os dados dos componentes cadastrados
            Jtable.PreencherJtableGenerico(jTBConsultaComponentes, new String[]{"null","id_componente", "tipo", "componente.descricao", "revisao", "id_material", "material.descricao","componente.id_datasheet",
            "datasheet.descricao","componente.data_cadastro","componente.data_alter"}, componente.getRetorno());
            
            //ajusta largura das colunas
            Jtable.ajustarColunasDaTabela(jTBConsultaComponentes);
            
            //Jtable.ajustarColunasDaTabela(jTBConsultaComponentes);
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

    private void jTBCidadeStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTBCidadeStateChanged

    }//GEN-LAST:event_jTBCidadeStateChanged

    private void jBTConcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTConcluirActionPerformed

        try {
            dao_componente.addComposicao(componente,jTBConsultaComponentes);
            Jtable.ajustarColunasDaTabela(componente.getTabela());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao adicionar composição");
        }
    }//GEN-LAST:event_jBTConcluirActionPerformed

    private void jTBConsultaComponentesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTBConsultaComponentesMousePressed

    }//GEN-LAST:event_jTBConsultaComponentesMousePressed

    private void jTBConsultaComponentesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTBConsultaComponentesMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jTBConsultaComponentesMouseEntered

    private void jTBConsultaComponentesMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTBConsultaComponentesMouseDragged
        
    }//GEN-LAST:event_jTBConsultaComponentesMouseDragged

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InterfaceComposicaoComponente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterfaceComposicaoComponente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterfaceComposicaoComponente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterfaceComposicaoComponente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InterfaceComposicaoComponente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBTBuscar;
    private javax.swing.JButton jBTConcluir;
    private javax.swing.JButton jBTVerDatasheet;
    private javax.swing.JComboBox jCBBuscarPor;
    private javax.swing.JComboBox jCBTipo;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTBCidade;
    private javax.swing.JTable jTBConsultaComponentes;
    private javax.swing.JTextField jTFFiltro;
    // End of variables declaration//GEN-END:variables



    class EvenOddRenderer implements TableCellRenderer {

    public final DefaultTableCellRenderer DEFAULT_RENDERER = new DefaultTableCellRenderer();
 
    public Component getTableCellRendererComponent(JTable table, Object value,
    boolean isSelected, boolean hasFocus, int row, int column) {
        
        Component renderer = DEFAULT_RENDERER.getTableCellRendererComponent(
        table, value, isSelected, hasFocus, row, column);
        ((JLabel) renderer).setOpaque(true);

        Boolean sel = false;
        String qntd = null;

        sel = (Boolean) table.getValueAt(row, 0);

        if(isSelected){
           
            //garante que quando estiver selecinado é true e caso contrario e false (nunca sera nulo)
            if(sel != null){
                if(sel == true ){
                    table.setValueAt(true, row, 0);
                }else{
                    table.setValueAt(false, row, 0);
                    table.setValueAt("", row, 11);
                }
            }       
        }
    return renderer;
    }
}
    
    public void mouseClickedCellJtable(MouseEvent evt){
        int row = jTBConsultaComponentes.rowAtPoint(evt.getPoint());
        int col = jTBConsultaComponentes.columnAtPoint(evt.getPoint());
        if (row >= 0 && col >= 0) {
            JOptionPane.showMessageDialog(null, row);
            JOptionPane.showMessageDialog(null, col);
        }
    }
    /*
    public void mouseClicked(java.awt.event.MouseEvent evt) {
        int row = jTBConsultaComponentes.rowAtPoint(evt.getPoint());
        int col = jTBConsultaComponentes.columnAtPoint(evt.getPoint());
        if (row >= 0 && col >= 0) {
            JOptionPane.showMessageDialog(null, row);
            JOptionPane.showMessageDialog(null, col);
        }
    }
    */
}
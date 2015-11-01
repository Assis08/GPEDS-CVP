/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.GPEDSCVP.interfaces;

import br.edu.GPEDSCVP.classe.Acesso;
import br.edu.GPEDSCVP.classe.Componente;
import br.edu.GPEDSCVP.classe.ComposicaoComponente;
import br.edu.GPEDSCVP.classe.Contato;
import br.edu.GPEDSCVP.classe.Datasheet;
import br.edu.GPEDSCVP.classe.Fornecedor;
import br.edu.GPEDSCVP.classe.FornecedoresComponente;
import br.edu.GPEDSCVP.classe.Material;
import br.edu.GPEDSCVP.classe.Permissao;
import br.edu.GPEDSCVP.classe.Tela;
import br.edu.GPEDSCVP.dao.daoAcesso;
import br.edu.GPEDSCVP.dao.daoCidade;
import br.edu.GPEDSCVP.dao.daoComponente;
import br.edu.GPEDSCVP.dao.daoComposicaoComponente;
import br.edu.GPEDSCVP.dao.daoContato;
import br.edu.GPEDSCVP.dao.daoDatasheet;
import br.edu.GPEDSCVP.dao.daoEstado;
import br.edu.GPEDSCVP.dao.daoFornecedoresComponente;
import br.edu.GPEDSCVP.dao.daoMaterial;
import br.edu.GPEDSCVP.dao.daoPermissao;
import br.edu.GPEDSCVP.dao.daoTela;
import br.edu.GPEDSCVP.util.ComboBox;
import br.edu.GPEDSCVP.util.FormatarData;
import br.edu.GPEDSCVP.util.ManipulaJtable;
import br.edu.GPEDSCVP.util.Mensagens;
import br.edu.GPEDSCVP.util.Rotinas;
import br.edu.GPEDSCVP.util.TableCellListener;
import br.edu.GPEDSCVP.util.UltimaSequencia;
import br.edu.GPEDSCVP.util.ValidaAcesso;
import br.edu.GPEDSCVP.util.ValidaBotoes;
import br.edu.GPEDSCVP.util.ValidaCampos;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Willys
 */
public class InterfaceComponente extends javax.swing.JFrame {

    Componente componente;
    Contato contato;
    Fornecedor fornecedor;
    FornecedoresComponente fornec_comp;
    ComposicaoComponente composicao;
    Material material;
    Tela tela;
    Datasheet datasheet;
    daoTela dao_tela;
    ComboBox combo;
    daoCidade dao_cidade;
    daoEstado dao_estado;
    daoContato dao_contato;
    daoDatasheet dao_datasheet;
    daoComponente dao_componente;
    daoComposicaoComponente dao_composicao;
    daoFornecedoresComponente dao_fornec_comp;
    daoMaterial dao_material;
    Acesso acesso;
    Mensagens mensagem;
    daoPermissao dao_permissao;
    daoAcesso dao_acesso;
    Permissao permissao;
    ValidaAcesso valida_acesso;
    ValidaBotoes valida_botoes;
    ValidaCampos valida_campos;
    ManipulaJtable Jtable;
    FormatarData data;
    int[] array_material;
    int[] array_datasheet;
    int situacao = Rotinas.PADRAO;

    public InterfaceComponente() {
        initComponents();
        
        //Cria renderer para as Jtable  
        TableCellRenderer renderer = new EvenOddRenderer();
        jTBComposicao.setDefaultRenderer(Object.class, renderer);
        jTBFornecedores.setDefaultRenderer(Object.class, renderer);
        
        //implementa Listener para edição da jtable
        new TableCellListener(jTBComposicao, new TableCellEditorAction());
        
        componente = new Componente();
        contato = new Contato();
        fornecedor = new Fornecedor();
        fornec_comp = new FornecedoresComponente();
        composicao = new ComposicaoComponente();
        material = new Material();
        datasheet = new Datasheet();
        tela = new Tela();
        dao_tela = new daoTela();
        dao_cidade = new daoCidade();
        dao_contato = new daoContato();
        dao_datasheet = new daoDatasheet();
        dao_estado = new daoEstado();
        dao_composicao = new daoComposicaoComponente();
        dao_fornec_comp = new daoFornecedoresComponente();
        acesso = new Acesso();
        dao_permissao = new daoPermissao();
        dao_acesso = new daoAcesso();
        dao_componente = new daoComponente();
        dao_material = new daoMaterial();
        permissao = new Permissao();
        valida_acesso = new ValidaAcesso();
        valida_botoes = new ValidaBotoes();
        mensagem = new Mensagens();
        data = new FormatarData();
        combo = new ComboBox();
        Jtable = new ManipulaJtable();
        try {
            valida_campos = new ValidaCampos();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao iniciar registro");
        }
        
        
        //desabilita campos da tela de componente
        valida_campos.desabilitaCampos(jPComponente);
        
        //Adiciona barra de rolagem obs: obrigatorio para conseguir dimensionar automatico as colunas da jtable
        jTBConsultaComponentes.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jTBComposicao.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jTBFornecedores.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jTBConsultaComposicao.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jTBConsultaFornecedores.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jTBContatoFornecedores.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        //Define a situação como inicial para habilitar os botoes utilizados apenas quando inicia a tela
        situacao = Rotinas.INICIAL;

        //habilita os botoes utilizados na inicialização da tela
        valida_botoes.ValidaEstado(jPBotoes, situacao);
        
        //atualiza dados do usuario logado
        dao_acesso.retornaUsuarioLogado(acesso);
        
        dao_material.consultaGeral(material);
        //Preenche dados nas ComboBox de material
        array_material = combo.PreencherCombo(jCBMaterial, "descricao", material.getRetorno(), "id_material");
        //seta no array da classe de material a lista de materiais listadas na combo
        material.setArray_material(array_material);
        
        dao_datasheet.consultaGeral(datasheet);
        //Preenche dados nas ComboBox de datasheet
        array_datasheet = combo.PreencherCombo(jCBDatasheet, "descricao", datasheet.getRetorno(), "id_datasheet");
        //seta no array da classe de material a lista de materiais listadas na combo
        datasheet.setArray_datasheet(array_datasheet);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTBComponente = new javax.swing.JTabbedPane();
        jPComponente = new javax.swing.JPanel();
        jPBotoes = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jTFIDComponente = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jCBMaterial = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jBTNovoMaterial = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jTFDescrição = new javax.swing.JTextField();
        jCBTipo = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jTFRevisao = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTBComposicao = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jFTData = new javax.swing.JFormattedTextField();
        jBTAddComposicao = new javax.swing.JButton();
        jBTRemoveComposicao = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jCBDatasheet = new javax.swing.JComboBox();
        jBTNovoDatasheet = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTBFornecedores = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jBTRemoveFornecedores = new javax.swing.JButton();
        jBTAddFornecedor = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTBConsultaComponentes = new javax.swing.JTable();
        jCBBuscarPor = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();
        jTFFiltro = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jBTBuscar = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTBConsultaComposicao = new javax.swing.JTable();
        jBTVerDatasheet = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jCBTipoConsulta = new javax.swing.JComboBox();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTBConsultaFornecedores = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTBContatoFornecedores = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Componentes");
        setResizable(false);

        jTBComponente.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTBComponenteStateChanged(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon("D:\\MEUS ARQUIVOS\\arquivos faculdade\\6PERIODO\\TCCII\\ICONES\\Botoes_Site_5752_Knob_Add.png")); // NOI18N
        jButton1.setText("Novo");
        jButton1.setToolTipText("Cidade");
        jButton1.setName("ID_CIDADE"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon("D:\\MEUS ARQUIVOS\\arquivos faculdade\\6PERIODO\\TCCII\\ICONES\\icones\\32x32\\document-pencil-icon (Custom).png")); // NOI18N
        jButton2.setText("Alterar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon("D:\\MEUS ARQUIVOS\\arquivos faculdade\\6PERIODO\\TCCII\\ICONES\\Botoes_Site_5751_Knob_Remove_Red.png")); // NOI18N
        jButton3.setText("Excluir");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon("D:\\MEUS ARQUIVOS\\arquivos faculdade\\6PERIODO\\TCCII\\ICONES\\Hardware-Floppy-icon (Custom).png")); // NOI18N
        jButton5.setText("Gravar");
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton5MouseExited(evt);
            }
        });
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon("D:\\MEUS ARQUIVOS\\arquivos faculdade\\6PERIODO\\TCCII\\ICONES\\Botoes_Site_5750_Knob_Cancel.png")); // NOI18N
        jButton4.setText("Cancelar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPBotoesLayout = new javax.swing.GroupLayout(jPBotoes);
        jPBotoes.setLayout(jPBotoesLayout);
        jPBotoesLayout.setHorizontalGroup(
            jPBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPBotoesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPBotoesLayout.setVerticalGroup(
            jPBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPBotoesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTFIDComponente.setEditable(false);
        jTFIDComponente.setName("id_componente"); // NOI18N

        jLabel1.setText("ID Componente:");

        jCBMaterial.setToolTipText("Material");
        jCBMaterial.setName("id_material"); // NOI18N
        jCBMaterial.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                jCBMaterialPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
                jCBMaterialPopupMenuWillBecomeVisible(evt);
            }
        });

        jLabel3.setText("Material:");

        jBTNovoMaterial.setIcon(new javax.swing.ImageIcon("D:\\MEUS ARQUIVOS\\arquivos faculdade\\6PERIODO\\TCCII\\ICONES\\icones\\menores\\add.png")); // NOI18N
        jBTNovoMaterial.setText("Novo");
        jBTNovoMaterial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTNovoMaterialActionPerformed(evt);
            }
        });

        jLabel4.setText("Descrição:");

        jTFDescrição.setToolTipText("Descrição");
        jTFDescrição.setName("descricao"); // NOI18N

        jCBTipo.setToolTipText("Tipo");
        jCBTipo.setName("tipo"); // NOI18N
        jCBTipo.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                jCBTipoPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
                jCBTipoPopupMenuWillBecomeVisible(evt);
            }
        });

        jLabel5.setText("Tipo:");

        jTFRevisao.setToolTipText("Rev");
        jTFRevisao.setName("revisao"); // NOI18N
        jTFRevisao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTFRevisaoKeyTyped(evt);
            }
        });

        jLabel6.setText("Rev:");

        jTBComposicao.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sel", "ID Composição", "ID Componente", "Tipo", "Componente", "ID Material", "Material", "Qntd", "exc"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTBComposicao.setName("Composição"); // NOI18N
        jTBComposicao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTBComposicaoMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTBComposicao);
        if (jTBComposicao.getColumnModel().getColumnCount() > 0) {
            jTBComposicao.getColumnModel().getColumn(8).setMinWidth(0);
            jTBComposicao.getColumnModel().getColumn(8).setPreferredWidth(0);
            jTBComposicao.getColumnModel().getColumn(8).setMaxWidth(0);
        }

        jLabel2.setText("Composição:");

        jLabel7.setText("Data:");

        jFTData.setEditable(false);
        jFTData.setToolTipText("Data");
        jFTData.setName("data_cadastro"); // NOI18N

        jBTAddComposicao.setIcon(new javax.swing.ImageIcon("D:\\MEUS ARQUIVOS\\arquivos faculdade\\6PERIODO\\TCCII\\ICONES\\Botoes_Site_5752_Knob_Add.png")); // NOI18N
        jBTAddComposicao.setName("descricao"); // NOI18N
        jBTAddComposicao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTAddComposicaoActionPerformed(evt);
            }
        });

        jBTRemoveComposicao.setIcon(new javax.swing.ImageIcon("D:\\MEUS ARQUIVOS\\arquivos faculdade\\6PERIODO\\TCCII\\ICONES\\Botoes_Site_5751_Knob_Remove_Red.png")); // NOI18N
        jBTRemoveComposicao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTRemoveComposicaoActionPerformed(evt);
            }
        });

        jLabel8.setText("Datasheet:");

        jCBDatasheet.setToolTipText("Datasheet");
        jCBDatasheet.setName("id_datasheet"); // NOI18N
        jCBDatasheet.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
                jCBDatasheetPopupMenuWillBecomeVisible(evt);
            }
        });

        jBTNovoDatasheet.setIcon(new javax.swing.ImageIcon("D:\\MEUS ARQUIVOS\\arquivos faculdade\\6PERIODO\\TCCII\\ICONES\\icones\\menores\\add.png")); // NOI18N
        jBTNovoDatasheet.setText("Novo");
        jBTNovoDatasheet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTNovoDatasheetActionPerformed(evt);
            }
        });

        jTBFornecedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "sel", "ID Fornecedores", "ID Fornecedor", "Descrição", "CNPJ", "Site", "exc"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(jTBFornecedores);
        if (jTBFornecedores.getColumnModel().getColumnCount() > 0) {
            jTBFornecedores.getColumnModel().getColumn(6).setMinWidth(0);
            jTBFornecedores.getColumnModel().getColumn(6).setPreferredWidth(0);
            jTBFornecedores.getColumnModel().getColumn(6).setMaxWidth(0);
        }

        jLabel10.setText("Fornecedores:");

        jBTRemoveFornecedores.setIcon(new javax.swing.ImageIcon("D:\\MEUS ARQUIVOS\\arquivos faculdade\\6PERIODO\\TCCII\\ICONES\\Botoes_Site_5751_Knob_Remove_Red.png")); // NOI18N
        jBTRemoveFornecedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTRemoveFornecedoresActionPerformed(evt);
            }
        });

        jBTAddFornecedor.setIcon(new javax.swing.ImageIcon("D:\\MEUS ARQUIVOS\\arquivos faculdade\\6PERIODO\\TCCII\\ICONES\\Botoes_Site_5752_Knob_Add.png")); // NOI18N
        jBTAddFornecedor.setName("descricao"); // NOI18N
        jBTAddFornecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTAddFornecedorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPComponenteLayout = new javax.swing.GroupLayout(jPComponente);
        jPComponente.setLayout(jPComponenteLayout);
        jPComponenteLayout.setHorizontalGroup(
            jPComponenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPComponenteLayout.createSequentialGroup()
                .addContainerGap(66, Short.MAX_VALUE)
                .addComponent(jPBotoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52))
            .addGroup(jPComponenteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPComponenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPComponenteLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPComponenteLayout.createSequentialGroup()
                        .addGroup(jPComponenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPComponenteLayout.createSequentialGroup()
                                .addGroup(jPComponenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPComponenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel4)
                                        .addComponent(jTFDescrição, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPComponenteLayout.createSequentialGroup()
                                        .addGroup(jPComponenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jTFIDComponente, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPComponenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5)
                                            .addComponent(jCBTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(55, 55, 55)
                                        .addGroup(jPComponenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTFRevisao, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel6))))
                                .addGap(18, 18, 18)
                                .addGroup(jPComponenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPComponenteLayout.createSequentialGroup()
                                        .addGroup(jPComponenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPComponenteLayout.createSequentialGroup()
                                                .addComponent(jCBMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jBTNovoMaterial))
                                            .addComponent(jLabel3))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPComponenteLayout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jFTData, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPComponenteLayout.createSequentialGroup()
                                .addComponent(jScrollPane2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPComponenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jBTAddComposicao, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jBTRemoveComposicao, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPComponenteLayout.createSequentialGroup()
                                .addGroup(jPComponenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addGroup(jPComponenteLayout.createSequentialGroup()
                                        .addComponent(jCBDatasheet, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jBTNovoDatasheet))
                                    .addComponent(jLabel2)
                                    .addComponent(jScrollPane4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPComponenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jBTAddFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jBTRemoveFornecedores, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(26, 26, 26))))
        );
        jPComponenteLayout.setVerticalGroup(
            jPComponenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPComponenteLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPComponenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPComponenteLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFIDComponente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPComponenteLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCBTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPComponenteLayout.createSequentialGroup()
                        .addGroup(jPComponenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPComponenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel7)
                                .addComponent(jFTData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFRevisao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPComponenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPComponenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTFDescrição, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCBMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBTNovoMaterial))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPComponenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCBDatasheet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBTNovoDatasheet))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPComponenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPComponenteLayout.createSequentialGroup()
                        .addComponent(jBTAddComposicao, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBTRemoveComposicao, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPComponenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPComponenteLayout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPBotoes, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPComponenteLayout.createSequentialGroup()
                        .addComponent(jBTAddFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBTRemoveFornecedores, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(16, 16, 16))
        );

        jTBComponente.addTab("Cadastro", jPComponente);

        jTBConsultaComponentes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Componente", "Tipo", "Descricao", "Rev", "ID Material", "Material", "ID Datasheet", "Datasheet", "Data cadastro", "Última alteração"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTBConsultaComponentes.setName("Componentes"); // NOI18N
        jTBConsultaComponentes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTBConsultaComponentesMouseClicked(evt);
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

        jTBConsultaComposicao.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Componente", "Tipo", "Descricao", "ID Material", "Material", "Qntd"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTBConsultaComposicao.setName("Composicao"); // NOI18N
        jTBConsultaComposicao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTBConsultaComposicaoMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTBConsultaComposicao);

        jBTVerDatasheet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/edu/GPEDSCVP/icones/eye.png"))); // NOI18N
        jBTVerDatasheet.setText("Ver datasheet");
        jBTVerDatasheet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTVerDatasheetActionPerformed(evt);
            }
        });

        jLabel9.setText("Composição:");

        jCBTipoConsulta.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Geral", "Eletrônico", "Mecânico" }));

        jLabel15.setText("Tipo:");

        jTBConsultaFornecedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Fornecedor", "Descrição", "CNPJ", "Site"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTBConsultaFornecedores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTBConsultaFornecedoresMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(jTBConsultaFornecedores);

        jTBContatoFornecedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Contato", "Descrição", "Email", "Fone"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTBContatoFornecedores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTBContatoFornecedoresMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(jTBContatoFornecedores);

        jLabel11.setText("Contato:");

        jLabel12.setText("Fornecedores:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane3)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addComponent(jCBBuscarPor, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(4, 4, 4)
                                        .addComponent(jLabel15))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jCBTipoConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(7, 7, 7)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jTFFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jBTBuscar)
                                        .addGap(18, 18, 18)
                                        .addComponent(jBTVerDatasheet))
                                    .addComponent(jLabel29)))
                            .addComponent(jLabel9))
                        .addGap(0, 12, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 501, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel29, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCBBuscarPor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTFFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBTBuscar)
                            .addComponent(jCBTipoConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBTVerDatasheet, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(4, 4, 4)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jTBComponente.addTab("Consulta", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTBComponente, javax.swing.GroupLayout.PREFERRED_SIZE, 815, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jTBComponente)
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(831, 688));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jCBMaterialPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jCBMaterialPopupMenuWillBecomeInvisible

    }//GEN-LAST:event_jCBMaterialPopupMenuWillBecomeInvisible

    private void jCBMaterialPopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jCBMaterialPopupMenuWillBecomeVisible
        dao_material.consultaGeral(material);
        //Preenche dados nas ComboBox de material
        array_material = combo.PreencherCombo(jCBMaterial, "descricao", material.getRetorno(), "id_material");
        //seta no array da classe de material a lista de materiais listadas na combo
        material.setArray_material(array_material);
    }//GEN-LAST:event_jCBMaterialPopupMenuWillBecomeVisible

    private void jBTNovoMaterialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTNovoMaterialActionPerformed
        try {
            //atualiza dados do usuario logado
            dao_acesso.retornaUsuarioLogado(acesso);
            
            //Inclui a opção todas telas como primeira opção
            tela.setDescricao("Todas telas");
            tela.setId_tela(1);
            dao_tela.incluir(tela);
            
            //Inclui a tela de Materiais
            tela.setDescricao("Materiais");
            tela.setId_tela(10);
            dao_tela.incluir(tela);
            
            //Armazena dados de acesso da tela para verificar permissões
            acesso.setId_tela(10);
            acesso.setNome_tela("Materiais");
            
            //se naõ for gerente
            if(acesso.getIn_gerente() == 0){
                //retorna as permissoes de acesso do usuario  
                dao_permissao.retornaDadosPermissao(acesso, permissao);
            } 
          
           //Verifica se o usuario possui permissao para acessar essa tela
           if (valida_acesso.verificaAcesso("acesso",acesso, permissao) == true){
                //Traz para tela a tela de cadastro de material 
                new InterfaceMaterial().setVisible(true);
           }else{
               JOptionPane.showMessageDialog(null, "Voce não possui permissões para acessar essa tela"); 
           }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao abrir tela de cadastro de materiais");
        }
       
    }//GEN-LAST:event_jBTNovoMaterialActionPerformed

    private void jTBConsultaComponentesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTBConsultaComponentesMouseClicked
        //Verifica se houve 1 clique do mouse
        
        //recupera a linha clicada
        int linha = jTBConsultaComponentes.getSelectedRow();
        Integer id_comp = Integer.parseInt(jTBConsultaComponentes.getValueAt(linha, 0).toString());
        
        //busca composição do componente clicado
        composicao.setId_componente(id_comp);
        dao_composicao.consultaCodigoComponente(composicao);

        //busca fornecedores do componente clicado
        fornec_comp.setId_componente(id_comp);
        dao_fornec_comp.consultaFornecedoresComponente(fornec_comp);
        
        if (evt.getClickCount() == 1) {
            
             //Preenche na JTABLE os dados dos componentes cadastrados
            Jtable.PreencherJtableGenerico(jTBConsultaComposicao, new String[]{"id_subcomponente", "tipo", "componente.descricao", "material.id_material", "material.descricao", "qntd"}, composicao.getRetorno());
            Jtable.ajustarColunasDaTabela(jTBConsultaComposicao);
            //Preenche na JTABLE os dados dos fornecedores dos componentes 
            Jtable.PreencherJtableGenerico(jTBConsultaFornecedores, new String[]{"id_pessoa", "nome", "cpf_cnpj", "site"}, fornec_comp.getRetorno());
            Jtable.ajustarColunasDaTabela(jTBConsultaFornecedores);
            
        //Verifica se houve 2 cliques do mouse
        }else  if (evt.getClickCount() == 2){
            
            //Limpa os campos da tela componente
            valida_campos.LimparCampos(jPComponente);
            //limpa tabela de endereços e composição na tela de cadastro de componente
            valida_campos.LimparJtable(jTBComposicao);
            valida_campos.LimparJtable(jTBFornecedores);
            //desabilita campos
            valida_campos.desabilitaCampos(jPComponente);
           
            //Carrega conteudo das combobox
            jCBTipo.addItem("Selecione tipo");
            jCBTipo.addItem("Eletrônico");
            jCBTipo.addItem("Mecânico");

            dao_material.consultaGeral(material);
            //Preenche dados nas ComboBox de material
            array_material = combo.PreencherCombo(jCBMaterial, "descricao", material.getRetorno(), "id_material");
            //seta no array da classe de material a lista de materiais listadas na combo
            material.setArray_material(array_material);
            
            dao_datasheet.consultaGeral(datasheet);
            //Preenche dados nas ComboBox de datasheet
            array_datasheet = combo.PreencherCombo(jCBDatasheet, "descricao", datasheet.getRetorno(), "id_datasheet");
            //seta no array da classe de material a lista de materiais listadas na combo
            datasheet.setArray_datasheet(array_datasheet);
            
            //retorna dados do componente
            componente.setId_componente(id_comp);
            dao_componente.retornardadosComponente(componente);

            //seta dados do componente na tela de cadastro
            setcompComponente();
            
            //Preenche na JTABLE composicao para alteração
            Jtable.PreencherJtableGenerico(jTBComposicao, new String[]{"null","id_composicao","id_subcomponente", "tipo", "componente.descricao", "material.id_material", "material.descricao", "qntd", "false"}, composicao.getRetorno());
            Jtable.ajustarColunasDaTabela(jTBComposicao);
            
            //Preenche na JTABLE os dados dos fornecedores dos componentes 
            Jtable.PreencherJtableGenerico(jTBFornecedores, new String[]{"null","id_fornecedores_comp","id_pessoa", "nome", "cpf_cnpj", "site", "false"}, fornec_comp.getRetorno());
            Jtable.ajustarColunasDaTabela(jTBFornecedores);
            
            //retorna para tela de cadastro
            jTBComponente.setSelectedIndex(0);
         
            //Define a situação como padrao para habilitar os botoes utilizados apenas na alteração ou exclusão
            situacao = Rotinas.PADRAO;
            //habilita os botoes utilizados na alteraçao e exclusão e desabilita os restantes
            valida_botoes.ValidaEstado(jPBotoes, situacao);
        }
    }//GEN-LAST:event_jTBConsultaComponentesMouseClicked

    private void jTBComponenteStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTBComponenteStateChanged
        
    }//GEN-LAST:event_jTBComponenteStateChanged

    private void jCBTipoPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jCBTipoPopupMenuWillBecomeInvisible
        if(jCBTipo.getSelectedItem().equals("Eletrônico")){
            //desabilita caixa de seleção de material
            jCBMaterial.setSelectedItem("selecione item");
            jCBMaterial.setEnabled(false);
            jBTNovoMaterial.setEnabled(false);
        }else if (jCBTipo.getSelectedItem().equals("Mecânico")){
            //habilita caixa de seleção de material
            jCBMaterial.setEnabled(true);
            jBTNovoMaterial.setEnabled(true);
        }
    }//GEN-LAST:event_jCBTipoPopupMenuWillBecomeInvisible

    private void jCBTipoPopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jCBTipoPopupMenuWillBecomeVisible
        // TODO add your handling code here:
    }//GEN-LAST:event_jCBTipoPopupMenuWillBecomeVisible

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        UltimaSequencia ultima;

        //habilita campos da tela
        valida_campos.habilitaCampos(jPComponente);
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
                int sequencia = (Integer) (ultima.ultimasequencia("COMPONENTE", "ID_COMPONENTE"));
                //seta id no campo camponente
                jTFIDComponente.setText(Integer.toString(sequencia));
                
                //Seta a data atual no campo data
                jFTData.setEnabled(true);
                jFTData.setText(data.DataAtual());
                
                //Carrega conteudo das combobox
                jCBTipo.addItem("Selecione tipo");
                jCBTipo.addItem("Eletrônico");
                jCBTipo.addItem("Mecânico");
                
                dao_material.consultaGeral(material);
                //Preenche dados nas ComboBox de material
                array_material = combo.PreencherCombo(jCBMaterial, "descricao", material.getRetorno(), "id_material");
                //seta no array da classe de material a lista de materiais listadas na combo
                material.setArray_material(array_material);
                
                dao_datasheet.consultaGeral(datasheet);
                //Preenche dados nas ComboBox de datasheet
                array_datasheet = combo.PreencherCombo(jCBDatasheet, "descricao", datasheet.getRetorno(), "id_datasheet");
                //seta no array da classe de material a lista de materiais listadas na combo
                datasheet.setArray_datasheet(array_datasheet);
                
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Falha ao iniciar a inserção de componente");
            }
        }else{
            JOptionPane.showMessageDialog(null, "Voce não possui permissões para incluir componentes no sistema");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

         //se naõ for gerente
        if (acesso.getIn_gerente() == 0) {
            //retorna as permissoes de acesso do usuario  
            dao_permissao.retornaDadosPermissao(acesso, permissao);
        }

        //Verifica se o usuario possui permissao para acessar essa tela
        if (valida_acesso.verificaAcesso("alterar", acesso, permissao) == true) {
            //Define a situação como alterar para habilitar os botoes utilizados apenas na inclusão
            situacao = Rotinas.ALTERAR;
            valida_botoes.ValidaEstado(jPBotoes, situacao);
            valida_campos.habilitaCampos(jPComponente);
        } else {
            JOptionPane.showMessageDialog(null, "Você não possui permissões para alterar registros de componentes no sistema");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        //se naõ for gerente
        if (acesso.getIn_gerente() == 0) {
            //retorna as permissoes de acesso do usuario  
            dao_permissao.retornaDadosPermissao(acesso, permissao);
        }
        //Verifica se o usuario possui permissao para acessar essa tela
        if (valida_acesso.verificaAcesso("excluir", acesso, permissao) == true) {

            //Seta o id da pessoa para exclusão
            componente.setId_componente(Integer.parseInt(jTFIDComponente.getText()));

                if (mensagem.ValidaMensagem("Deseja realmente excluir o registro ?") == 0) {
                    //Inativa componente
                    dao_componente.inativaComponente(componente);
                    JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
                    //Limpa os campos da tela pessoa
                    valida_campos.LimparCampos(jPComponente);
                    valida_campos.LimparJtable(jTBComposicao);
                    valida_campos.LimparJtable(jTBFornecedores);
                    valida_campos.LimparJtable(jTBConsultaComponentes);
                    valida_campos.LimparJtable(jTBConsultaFornecedores);
                    valida_campos.LimparJtable(jTBConsultaComposicao);
                    valida_campos.LimparJtable(jTBContatoFornecedores);
                    
                    //Define a situação como inicial para habilitar os botoes utilizados apenas quando inicia a tela
                    situacao = Rotinas.INICIAL;

                    //habilita os botoes utilizados na inicialização da tela
                    valida_botoes.ValidaEstado(jPBotoes, situacao);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Voce não possui permissões para excluir pessoas no sistema");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5MouseExited

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
         //Se for inclusão
        if (situacao == Rotinas.INCLUIR) {
            if (valida_campos.validacamposobrigatorios(jPComponente, "COMPONENTE") == 0) {
                try {
                    //pega dados do componente na tela
                    getComponente();
                    //inclui componente
                    if(dao_componente.incluir(componente,jCBTipo.getSelectedItem().toString()) == true){
                        
                       getComposicao();
                       getFornecedores();
                       dao_componente.gravarComposicao(composicao);
                       dao_componente.gravarFornecedores(fornec_comp);
                        
                        //se ocorreu tudo bem na inclusão
                        JOptionPane.showMessageDialog(null, "Salvo com Sucesso");
                        //limpa campos
                        valida_campos.LimparCampos(jPComponente);
                        valida_campos.LimparJtable(jTBComposicao);
                        valida_campos.LimparJtable(jTBFornecedores);
                        valida_campos.LimparJtable(jTBConsultaComponentes);
                        valida_campos.LimparJtable(jTBConsultaFornecedores);
                        valida_campos.LimparJtable(jTBConsultaComposicao);
                        valida_campos.LimparJtable(jTBContatoFornecedores);
                        
                        //Define a situação como incluir para habilitar os botoes utilizados apenas na inclusão
                        situacao = Rotinas.INICIAL;

                        //habilita os botoes utilizados na inclusão e desabilita os restantes
                        valida_botoes.ValidaEstado(jPBotoes, situacao);

                        //desabilita campos
                        valida_campos.desabilitaCampos(jPComponente);
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Falha ao incluir componente");
                }

            }
        }else if(situacao == Rotinas.ALTERAR) {
            //pega dados do material na tela
            if (valida_campos.validacamposobrigatorios(jPComponente, "COMPONENTE") == 0) {
                try {
                    getComponente();
                    //alterar componente
                    if(dao_componente.alterar(componente, componente.getTipo()) == true){
                       //altera composição
                       getComposicao();
                       getFornecedores();
                       dao_composicao.alterarComposicao(composicao);
                       dao_fornec_comp.alterarFornecedores(fornec_comp);
                       
                        JOptionPane.showMessageDialog(null, "Salvo com Sucesso");
                        //limpa campos
                        valida_campos.LimparCampos(jPComponente);
                        valida_campos.LimparJtable(jTBComposicao);
                        valida_campos.LimparJtable(jTBFornecedores);
                        valida_campos.LimparJtable(jTBConsultaComponentes);
                        valida_campos.LimparJtable(jTBConsultaFornecedores);
                        valida_campos.LimparJtable(jTBConsultaComposicao);
                        valida_campos.LimparJtable(jTBContatoFornecedores);

                        //Define a situação como incluir para habilitar os botoes utilizados apenas na inclusão
                        situacao = Rotinas.INICIAL;

                        //habilita os botoes utilizados na inclusão e desabilita os restantes
                        valida_botoes.ValidaEstado(jPBotoes, situacao);

                        //desabilita campos
                        valida_campos.desabilitaCampos(jPComponente);
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Falha ao alterar componente");
                }
            }
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        //limpa campos 
        valida_campos.LimparCampos(jPComponente);
        valida_campos.LimparJtable(jTBComposicao);
        valida_campos.LimparJtable(jTBFornecedores);

        //Define a situação como incluir para habilitar os botoes utilizados apenas na inclusão
        situacao = Rotinas.INICIAL;

        //habilita os botoes utilizados na inclusão e desabilita os restantes
        valida_botoes.ValidaEstado(jPBotoes, situacao);

        //desabilita campos
        valida_campos.desabilitaCampos(jPComponente);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jBTAddComposicaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTAddComposicaoActionPerformed
        new InterfaceComposicaoComponente().setVisible(true);
        componente.setTabela(jTBComposicao);
        composicao.setSituacao(situacao);
        composicao.setId_componente(Integer.parseInt(jTFIDComponente.getText()));
    }//GEN-LAST:event_jBTAddComposicaoActionPerformed

    private void jBTRemoveComposicaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTRemoveComposicaoActionPerformed
        if (valida_campos.VerificaJtable(jTBComposicao) == 1) {
            int linha = jTBComposicao.getSelectedRow();
            Integer exc = Integer.parseInt(jTBComposicao.getValueAt(linha, 8).toString());
            //se não for um item removido
            if (exc == 0) {
                Jtable.removeItens(jTBComposicao, situacao);
            }else{
                JOptionPane.showMessageDialog(null, "Item já removido");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Não possui componentes para remover");
        }
    }//GEN-LAST:event_jBTRemoveComposicaoActionPerformed

    private void jTFRevisaoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFRevisaoKeyTyped
        String caracteres="0987654321";
        if(!caracteres.contains(evt.getKeyChar()+"")){
            evt.consume();
        }
    }//GEN-LAST:event_jTFRevisaoKeyTyped

    private void jBTBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTBuscarActionPerformed

        //Se não for gerente
        if (acesso.getIn_gerente() == 0) {
            //retorna as permissoes de acesso do usuario
            dao_permissao.retornaDadosPermissao(acesso, permissao);
        }

        //Verifica se o usuario possui permissao para acessar essa tela
        if (valida_acesso.verificaAcesso("consultar", acesso, permissao) == true) {

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
            
            switch (jCBTipoConsulta.getSelectedIndex()) {
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
            }
            //Preenche na JTABLE os dados dos componentes cadastrados
            Jtable.PreencherJtableGenerico(jTBConsultaComponentes, new String[]{"id_componente", "tipo", "componente.descricao", "revisao", "id_material", "material.descricao", "componente.id_datasheet","datasheet.descricao","data_cadastro", "data_alter"}, componente.getRetorno());
            Jtable.ajustarColunasDaTabela(jTBConsultaComponentes);
        } else {
            JOptionPane.showMessageDialog(null, "Você nao possui permissões para consultar componentes no sistema");
        }
    }//GEN-LAST:event_jBTBuscarActionPerformed

    private void jTBConsultaComposicaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTBConsultaComposicaoMouseClicked

        //recupera a linha clicada
        int linha = jTBConsultaComposicao.getSelectedRow();
        Integer id_comp = Integer.parseInt(jTBConsultaComposicao.getValueAt(linha, 0).toString());

        //busca fornecedores do componente clicado
        fornec_comp.setId_componente(id_comp);
        dao_fornec_comp.consultaFornecedoresComponente(fornec_comp);

        //Preenche na JTABLE os dados dos fornecedores dos componentes 
        Jtable.PreencherJtableGenerico(jTBConsultaFornecedores, new String[]{"id_pessoa", "nome", "cpf_cnpj", "site"}, fornec_comp.getRetorno());
        Jtable.ajustarColunasDaTabela(jTBConsultaFornecedores);
        
    }//GEN-LAST:event_jTBConsultaComposicaoMouseClicked

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
                id = Integer.parseInt(jTBConsultaComponentes.getValueAt(linha, 6).toString());
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

    private void jBTNovoDatasheetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTNovoDatasheetActionPerformed
        try {
            //atualiza dados do usuario logado
            dao_acesso.retornaUsuarioLogado(acesso);
            
            //Inclui a opção todas telas como primeira opção
            tela.setDescricao("Todas telas");
            tela.setId_tela(1);
            dao_tela.incluir(tela);
            
            //Inclui a tela de Datasheets
            tela.setDescricao("Datasheets");
            tela.setId_tela(11);
            dao_tela.incluir(tela);
            
            //Armazena dados de acesso da tela para verificar permissões
            acesso.setId_tela(11);
            acesso.setNome_tela("Datasheets");
            
            //se naõ for gerente
            if(acesso.getIn_gerente() == 0){
                //retorna as permissoes de acesso do usuario  
                dao_permissao.retornaDadosPermissao(acesso, permissao);
            } 
          
           //Verifica se o usuario possui permissao para acessar essa tela
           if (valida_acesso.verificaAcesso("acesso",acesso, permissao) == true){
                //Traz para tela a tela de cadastro de datasheet 
                new InterfaceDatasheet().setVisible(true);
           }else{
               JOptionPane.showMessageDialog(null, "Voce não possui permissões para acessar essa tela"); 
           }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao abrir tela de cadastro de datasheets");
        }
    }//GEN-LAST:event_jBTNovoDatasheetActionPerformed

    private void jCBDatasheetPopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jCBDatasheetPopupMenuWillBecomeVisible
        dao_datasheet.consultaGeral(datasheet);
        //Preenche dados nas ComboBox de datasheet
        array_datasheet = combo.PreencherCombo(jCBDatasheet, "descricao", datasheet.getRetorno(), "id_datasheet");
        //seta no array da classe de material a lista de materiais listadas na combo
        datasheet.setArray_datasheet(array_datasheet);
    }//GEN-LAST:event_jCBDatasheetPopupMenuWillBecomeVisible

    private void jBTRemoveFornecedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTRemoveFornecedoresActionPerformed
         if (valida_campos.VerificaJtable(jTBFornecedores) == 1) {
            int linha = jTBFornecedores.getSelectedRow();
            Integer exc = Integer.parseInt(jTBFornecedores.getValueAt(linha, 6).toString());
            //se não for um item removido
            if (exc == 0) {
                Jtable.removeItens(jTBFornecedores, situacao);
            }else{
                JOptionPane.showMessageDialog(null, "Item já removido");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Não possui fornecedores para remover");
        }
    }//GEN-LAST:event_jBTRemoveFornecedoresActionPerformed

    private void jBTAddFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTAddFornecedorActionPerformed
        try {
            //atualiza dados do usuario logado
            dao_acesso.retornaUsuarioLogado(acesso);
            
            //Inclui a opção todas telas como primeira opção
            tela.setDescricao("Todas telas");
            tela.setId_tela(1);
            dao_tela.incluir(tela);
            
            //Inclui a tela de Pessoas
            tela.setDescricao("Pessoas");
            tela.setId_tela(2);
            dao_tela.incluir(tela);
            
            //Armazena dados de acesso da tela para verificar permissões
            acesso.setId_tela(2);
            acesso.setNome_tela("Pessoas");
            
            //se naõ for gerente
            if(acesso.getIn_gerente() == 0){
                //retorna as permissoes de acesso do usuario  
                dao_permissao.retornaDadosPermissao(acesso, permissao);
            } 
          
           //Verifica se o usuario possui permissao para acessar essa tela
           if (valida_acesso.verificaAcesso("acesso",acesso, permissao) == true){
                //Traz para tela a tela de cadastro de pessoas 
                fornec_comp.setTabela(jTBFornecedores);
                fornec_comp.setSituacao(situacao);
                new InterfaceConsultaFornecedores().setVisible(true);
           }else{
               JOptionPane.showMessageDialog(null, "Voce não possui permissões para acessar essa tela"); 
           }
            
        } catch (SQLException ex) {
            Logger.getLogger(InterfacePessoa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jBTAddFornecedorActionPerformed

    private void jTBConsultaFornecedoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTBConsultaFornecedoresMouseClicked
       
        //recupera a linha clicada
        int linha = jTBConsultaFornecedores.getSelectedRow();
        Integer id_fornec = Integer.parseInt(jTBConsultaFornecedores.getValueAt(linha, 0).toString());

        //busca contato dos fornecedor clicado
        contato.setId_pessoa(id_fornec);
        dao_contato.consultacodigo(contato);

        //Preenche na JTABLE os dados dos fornecedores dos componentes 
        Jtable.PreencherJtableGenerico(jTBContatoFornecedores, new String[]{"id_contato","descricao", "email", "numero"}, contato.getRetorno());
        Jtable.ajustarColunasDaTabela(jTBContatoFornecedores);
        
    }//GEN-LAST:event_jTBConsultaFornecedoresMouseClicked

    private void jTBContatoFornecedoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTBContatoFornecedoresMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTBContatoFornecedoresMouseClicked

    private void jTBComposicaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTBComposicaoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTBComposicaoMouseClicked

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
            java.util.logging.Logger.getLogger(InterfaceComponente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterfaceComponente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterfaceComponente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterfaceComponente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InterfaceComponente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBTAddComposicao;
    private javax.swing.JButton jBTAddFornecedor;
    private javax.swing.JButton jBTBuscar;
    private javax.swing.JButton jBTNovoDatasheet;
    private javax.swing.JButton jBTNovoMaterial;
    private javax.swing.JButton jBTRemoveComposicao;
    private javax.swing.JButton jBTRemoveFornecedores;
    private javax.swing.JButton jBTVerDatasheet;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox jCBBuscarPor;
    private javax.swing.JComboBox jCBDatasheet;
    private javax.swing.JComboBox jCBMaterial;
    private javax.swing.JComboBox jCBTipo;
    private javax.swing.JComboBox jCBTipoConsulta;
    private javax.swing.JFormattedTextField jFTData;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPBotoes;
    private javax.swing.JPanel jPComponente;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTBComponente;
    private javax.swing.JTable jTBComposicao;
    private javax.swing.JTable jTBConsultaComponentes;
    private javax.swing.JTable jTBConsultaComposicao;
    private javax.swing.JTable jTBConsultaFornecedores;
    private javax.swing.JTable jTBContatoFornecedores;
    private javax.swing.JTable jTBFornecedores;
    private javax.swing.JTextField jTFDescrição;
    private javax.swing.JTextField jTFFiltro;
    private javax.swing.JTextField jTFIDComponente;
    private javax.swing.JTextField jTFRevisao;
    // End of variables declaration//GEN-END:variables

    //Pega dados do componente na tela
    public Componente getComponente() {
        
        componente = new Componente();
        
        Date data_atual = new Date(System.currentTimeMillis());
        
        int id_componente = Integer.parseInt(jTFIDComponente.getText());
        componente.setId_componente(id_componente);
        componente.setDescricao(jTFDescrição.getText());
        if(jCBTipo.getSelectedItem().equals("Mecânico")){
            if(jCBMaterial.getSelectedIndex() > 0){
                componente.setId_material(material.getArray_material(jCBMaterial.getSelectedIndex() - 1));
            }else{
                componente.setId_material(0);
            }
            
        }
        if(jCBTipo.getSelectedItem().equals("Eletrônico")){
            componente.setTipo("E");
        }else if (jCBTipo.getSelectedItem().equals("Mecânico")){
            componente.setTipo("M");
        }
        componente.setRevisao(jTFRevisao.getText());
        if(jCBDatasheet.getSelectedItem().equals("selecione item")){
            componente.setId_datasheet(0);
        }else{
            componente.setId_datasheet(datasheet.getArray_datasheet(jCBDatasheet.getSelectedIndex() - 1));
        }
        
        componente.setData_cadastro(data.stringParaSQLDate(jFTData.getText()));
        componente.setData_alter(data_atual);
      
        return componente;
    }
    
    public ComposicaoComponente getComposicao(){
        
         
        composicao = new ComposicaoComponente();
        
        Date data_atual = new Date(System.currentTimeMillis());
        composicao.setId_componente(Integer.parseInt(jTFIDComponente.getText()));
        composicao.setData_alter(data_atual);
        composicao.setData_cadastro(data.stringParaSQLDate(jFTData.getText()));
        composicao.setTabela(jTBComposicao);
        
        return composicao;
    }
    
     public FornecedoresComponente getFornecedores(){
        
         
        fornec_comp = new FornecedoresComponente();
        
        Date data_atual = new Date(System.currentTimeMillis());
        fornec_comp.setId_componente(Integer.parseInt(jTFIDComponente.getText()));
        fornec_comp.setData_alter(data_atual);
        fornec_comp.setData_cadastro(data.stringParaSQLDate(jFTData.getText()));
        fornec_comp.setTabela(jTBFornecedores);
        
        return fornec_comp;
    }
     
     public void setcompComponente(){
         jTFIDComponente.setText(String.valueOf(componente.getId_componente()));
         jTFDescrição.setText(componente.getDescricao());
         jTFRevisao.setText(componente.getRevisao());
         jFTData.setText(String.valueOf(data.organizaData(componente.getData_cadastro())));
         if(componente.getTipo().equals("E")){
             jCBTipo.setSelectedItem("Eletrônico");
         }else if(componente.getTipo().equals("M")){
             jCBTipo.setSelectedItem("Mecânico");
         }else{
             jCBTipo.setSelectedItem("Selecione item");
         }
         if(componente.getDs_material() != null){
              jCBMaterial.setSelectedItem(componente.getDs_material());
         }else{
             jCBMaterial.setSelectedItem("Selecione item");
         }
         if(componente.getDs_datasheet()!= null){
              jCBDatasheet.setSelectedItem(componente.getDs_datasheet());
         }else{
             jCBDatasheet.setSelectedItem("Selecione item");
         }
     }
     
    class EvenOddRenderer implements TableCellRenderer {

    public final DefaultTableCellRenderer DEFAULT_RENDERER = new DefaultTableCellRenderer();
 
    public Component getTableCellRendererComponent(JTable table, Object value,
    boolean isSelected, boolean hasFocus, int row, int column) {
    Component renderer = DEFAULT_RENDERER.getTableCellRendererComponent(
        table, value, isSelected, hasFocus, row, column);
    ((JLabel) renderer).setOpaque(true);
    Color foreground, background;
    int totcolun = table.getColumnCount();
   
    Integer exc = 0;
    Boolean sel = false;

 
    exc = Integer.parseInt(table.getValueAt(row, totcolun-1).toString());
    sel = (Boolean) table.getValueAt(row, 0);
    
    if(isSelected){
        if(exc == 1){
           background = Color.RED;
           renderer.setBackground(background);
        }
        //garante que quando estiver selecinado é true e caso contrario e false (nunca sera nulo)
        
        if(sel != null){
            if(sel == true){
                table.setValueAt(true, row, 0);
            }else{
                table.setValueAt(false, row, 0);
            }
            
        }       
     }
   
     if(!isSelected){
        if(exc == 1){
            background = Color.RED;
            renderer.setBackground(background);
        }else{
            background = Color.WHITE;
            renderer.setBackground(background);
        }
     }
    return renderer;
  }
}
    
    private void onCellEditor(JTable table, int column, int row, Object oldValue, Object newValue){
        System.out.println("Coluna:" + column + "Valor novo: " + newValue + " Valor antigo: " + oldValue);
        if (table == jTBComposicao) {
            //Se o valor novo não for vazio 
            if(!newValue.toString().replace(" ", "").equals("")){
                
                if(column == 7 ){
                    try { 
                        //verifica se o valor setado é um valor double
                        Integer qntd = Integer.parseInt(table.getValueAt(row, column).toString());
                        if(qntd > 0){
                            //Seta valor com ponto em vez de virgula
                            table.setValueAt(qntd, row, column);
                        }else{
                            JOptionPane.showMessageDialog(null, "A quantidade deve ser maior que zero!");
                            table.setValueAt(oldValue, row, column);
                        }
                       
                        
                    } catch (Exception e) {
                        //se não for double, emite a mensagem e retorna para o valor que estava
                        JOptionPane.showMessageDialog(null, "Informe um valor numérico para quantidade!");  
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

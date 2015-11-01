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
import br.edu.GPEDSCVP.classe.Moeda;
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
import br.edu.GPEDSCVP.dao.daoMoeda;
import br.edu.GPEDSCVP.dao.daoPermissao;
import br.edu.GPEDSCVP.dao.daoPessoa;
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
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author Willys
 */
public class InterfaceFornecimento extends javax.swing.JFrame {

    Componente componente;
    Fornecedor fornecedor;
    Moeda moeda;
    daoMoeda dao_moeda;
    ComposicaoComponente composicao;
    Material material;
    Tela tela;
    daoTela dao_tela;
    ComboBox combo;
    daoComponente dao_componente;
    daoPessoa dao_fornecedor;
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
    int[] array_moedas;
    int[] array_fornecedores;
    int situacao = Rotinas.PADRAO;
    
    public InterfaceFornecimento() {
        initComponents();
        
        componente = new Componente();
        fornecedor = new Fornecedor();
        composicao = new ComposicaoComponente();
        material = new Material();
        moeda = new Moeda();
        tela = new Tela();
        dao_tela = new daoTela();
        acesso = new Acesso();
        dao_permissao = new daoPermissao();
        dao_moeda = new daoMoeda();
        dao_fornecedor = new daoPessoa();
        dao_acesso = new daoAcesso();
        dao_componente = new daoComponente();
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
        
         //desabilita campos da tela de fornecimento
        valida_campos.desabilitaCampos(jPFornecimento);
        
        //Adiciona barra de rolagem obs: obrigatorio para conseguir dimensionar automatico as colunas da jtable
        jTBComponentes.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jTBComponentesProjetos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        dao_fornecedor.consultageral(fornecedor);
        //Preenche dados nas ComboBox de fornecedor
        array_fornecedores = combo.PreencherCombo(jCBFornecedor, "nome", fornecedor.getRetorno(), "id_pessoa");
        //seta no array da classe de fornecedores a lista de fornecedores listadas na combo
        fornecedor.setArray_fornecedor(array_fornecedores);

        dao_moeda.consultaGeral(moeda);
        //Preenche dados nas ComboBox de moeda
        array_moedas = combo.PreencherCombo(jCBMoedaFrete, "unidade", moeda.getRetorno(), "id_moeda");
        array_moedas = combo.PreencherCombo(jCBMoedaImpostos, "unidade", moeda.getRetorno(), "id_moeda");
        //seta no array da classe de moeda a lista de moedas listadas na combo
        moeda.setArray_moeda(array_moedas);
        
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

        jTBComponente = new javax.swing.JTabbedPane();
        jPFornecimento = new javax.swing.JPanel();
        jPBotoes = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jTFIDFornecimento = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jCBFornecedor = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jBTNovoFornecedor = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jTFDescrição = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTBComponentes = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jFTData = new javax.swing.JFormattedTextField();
        jBTAddComponente = new javax.swing.JButton();
        jBTRemoveComponente = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTBComponentesProjetos = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jCBMoedaFrete = new javax.swing.JComboBox();
        jTFValorFrete = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel13 = new javax.swing.JLabel();
        jCBMoedaImpostos = new javax.swing.JComboBox();
        jTFValorImpostos = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jBTAddparaProjeto = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jBTRemoveComponenteProjeto = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTBConsultaComponentes = new javax.swing.JTable();
        jCBBuscarPor = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();
        jTFFiltro = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jBTBuscar = new javax.swing.JButton();
        jBTVerDatasheet = new javax.swing.JButton();
        jCBTipoConsulta = new javax.swing.JComboBox();
        jLabel15 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Fornecimentos");
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

        jTFIDFornecimento.setEditable(false);
        jTFIDFornecimento.setName("id_componente"); // NOI18N

        jLabel1.setText("ID Fornecimento:");

        jCBFornecedor.setToolTipText("Material");
        jCBFornecedor.setName("id_material"); // NOI18N
        jCBFornecedor.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                jCBFornecedorPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
                jCBFornecedorPopupMenuWillBecomeVisible(evt);
            }
        });

        jLabel3.setText("Fornecedor:");

        jBTNovoFornecedor.setIcon(new javax.swing.ImageIcon("D:\\MEUS ARQUIVOS\\arquivos faculdade\\6PERIODO\\TCCII\\ICONES\\icones\\menores\\add.png")); // NOI18N
        jBTNovoFornecedor.setText("Novo");
        jBTNovoFornecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTNovoFornecedorActionPerformed(evt);
            }
        });

        jLabel4.setText("Descrição:");

        jTFDescrição.setToolTipText("Descrição");
        jTFDescrição.setName("descricao"); // NOI18N

        jTBComponentes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sel", "ID Fornecidos", "ID Componente", "Descrição", "Valor Unitário", "ID Moeda", "Moeda", "Qtde", "Total", "exc"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTBComponentes.setName("Composição"); // NOI18N
        jTBComponentes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTBComponentesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTBComponentes);
        if (jTBComponentes.getColumnModel().getColumnCount() > 0) {
            jTBComponentes.getColumnModel().getColumn(9).setMinWidth(0);
            jTBComponentes.getColumnModel().getColumn(9).setPreferredWidth(0);
            jTBComponentes.getColumnModel().getColumn(9).setMaxWidth(0);
        }

        jLabel2.setText("Componentes:");

        jLabel7.setText("Data:");

        jFTData.setEditable(false);
        jFTData.setToolTipText("Data");
        jFTData.setName("data_cadastro"); // NOI18N

        jBTAddComponente.setIcon(new javax.swing.ImageIcon("D:\\MEUS ARQUIVOS\\arquivos faculdade\\6PERIODO\\TCCII\\ICONES\\Botoes_Site_5752_Knob_Add.png")); // NOI18N
        jBTAddComponente.setName("descricao"); // NOI18N
        jBTAddComponente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTAddComponenteActionPerformed(evt);
            }
        });

        jBTRemoveComponente.setIcon(new javax.swing.ImageIcon("D:\\MEUS ARQUIVOS\\arquivos faculdade\\6PERIODO\\TCCII\\ICONES\\Botoes_Site_5751_Knob_Remove_Red.png")); // NOI18N
        jBTRemoveComponente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTRemoveComponenteActionPerformed(evt);
            }
        });

        jTBComponentesProjetos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "sel", "ID Componentes", "Projeto", "Versão", "ID Componente", "Componente", "Qntde"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(jTBComponentesProjetos);

        jLabel10.setText("Componentes para Projetos:");

        jLabel8.setText("Moeda:");

        jCBMoedaFrete.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
                jCBMoedaFretePopupMenuWillBecomeVisible(evt);
            }
        });

        jLabel5.setText("Frete");

        jLabel6.setText("Impostos");

        jLabel13.setText("Moeda:");

        jCBMoedaImpostos.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
                jCBMoedaImpostosPopupMenuWillBecomeVisible(evt);
            }
        });

        jLabel16.setText("Valor:");

        jLabel17.setText("Valor:");

        jBTAddparaProjeto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/edu/GPEDSCVP/icones/arrow_down.png"))); // NOI18N

        jLabel18.setText("Adicionar para projeto:");

        jBTRemoveComponenteProjeto.setIcon(new javax.swing.ImageIcon("D:\\MEUS ARQUIVOS\\arquivos faculdade\\6PERIODO\\TCCII\\ICONES\\Botoes_Site_5751_Knob_Remove_Red.png")); // NOI18N
        jBTRemoveComponenteProjeto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTRemoveComponenteProjetoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPFornecimentoLayout = new javax.swing.GroupLayout(jPFornecimento);
        jPFornecimento.setLayout(jPFornecimentoLayout);
        jPFornecimentoLayout.setHorizontalGroup(
            jPFornecimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPFornecimentoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPFornecimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPFornecimentoLayout.createSequentialGroup()
                        .addGroup(jPFornecimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTFIDFornecimento, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(30, 30, 30)
                        .addGroup(jPFornecimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPFornecimentoLayout.createSequentialGroup()
                                .addComponent(jTFDescrição, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPFornecimentoLayout.createSequentialGroup()
                                .addGroup(jPFornecimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPFornecimentoLayout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addGroup(jPFornecimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel6)
                                            .addGroup(jPFornecimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPFornecimentoLayout.createSequentialGroup()
                                                    .addGroup(jPFornecimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel13)
                                                        .addComponent(jCBMoedaImpostos, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addGroup(jPFornecimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jTFValorImpostos, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel17)))
                                                .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(jPFornecimentoLayout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel7)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jFTData, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(26, 26, 26))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPFornecimentoLayout.createSequentialGroup()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPFornecimentoLayout.createSequentialGroup()
                        .addGroup(jPFornecimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPFornecimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel2)
                                .addComponent(jLabel3)
                                .addGroup(jPFornecimentoLayout.createSequentialGroup()
                                    .addComponent(jCBFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jBTNovoFornecedor))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPFornecimentoLayout.createSequentialGroup()
                                    .addGroup(jPFornecimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jCBMoedaFrete, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel8))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPFornecimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel16)
                                        .addComponent(jTFValorFrete, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jLabel5))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPFornecimentoLayout.createSequentialGroup()
                        .addGroup(jPFornecimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPFornecimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPFornecimentoLayout.createSequentialGroup()
                                    .addComponent(jLabel10)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel18))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPFornecimentoLayout.createSequentialGroup()
                                    .addGap(0, 0, Short.MAX_VALUE)
                                    .addGroup(jPFornecimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 759, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jPBotoes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPFornecimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jBTRemoveComponente, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBTAddComponente, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBTRemoveComponenteProjeto, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBTAddparaProjeto))
                        .addGap(34, 34, 34))))
        );
        jPFornecimentoLayout.setVerticalGroup(
            jPFornecimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPFornecimentoLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPFornecimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPFornecimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(jFTData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPFornecimentoLayout.createSequentialGroup()
                        .addGroup(jPFornecimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPFornecimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTFIDFornecimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTFDescrição, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(12, 12, 12)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPFornecimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCBFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBTNovoFornecedor))
                .addGap(20, 20, 20)
                .addGroup(jPFornecimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPFornecimentoLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPFornecimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPFornecimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCBMoedaFrete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTFValorFrete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPFornecimentoLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPFornecimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(jLabel17))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPFornecimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCBMoedaImpostos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTFValorImpostos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPFornecimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPFornecimentoLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPFornecimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPFornecimentoLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel18))
                            .addComponent(jLabel10)))
                    .addGroup(jPFornecimentoLayout.createSequentialGroup()
                        .addComponent(jBTAddComponente, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(jBTRemoveComponente, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBTAddparaProjeto, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(jPFornecimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBTRemoveComponenteProjeto, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPBotoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        jTBComponente.addTab("Cadastro", jPFornecimento);

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

        jBTVerDatasheet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/edu/GPEDSCVP/icones/eye.png"))); // NOI18N
        jBTVerDatasheet.setText("Ver datasheet");
        jBTVerDatasheet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTVerDatasheetActionPerformed(evt);
            }
        });

        jCBTipoConsulta.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Geral", "Eletrônico", "Mecânico" }));

        jLabel15.setText("Tipo:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
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
                            .addComponent(jLabel29))
                        .addGap(0, 60, Short.MAX_VALUE)))
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
                .addContainerGap(407, Short.MAX_VALUE))
        );

        jTBComponente.addTab("Consulta", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTBComponente)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jTBComponente)
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(879, 698));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        UltimaSequencia ultima;

        //habilita campos da tela
        valida_campos.habilitaCampos(jPFornecimento);
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
                int sequencia = (Integer) (ultima.ultimasequencia("FORNECIMENTO", "ID_FORNECIMENTO"));
                //seta id no campo ID Fornecimento
                jTFIDFornecimento.setText(Integer.toString(sequencia));

                //Seta a data atual no campo data
                jFTData.setEnabled(true);
                jFTData.setText(data.DataAtual());

                dao_fornecedor.consultageral(fornecedor);
                //Preenche dados nas ComboBox de fornecedor
                array_fornecedores = combo.PreencherCombo(jCBFornecedor, "nome", fornecedor.getRetorno(), "id_pessoa");
                //seta no array da classe de fornecedores a lista de fornecedores listadas na combo
                fornecedor.setArray_fornecedor(array_fornecedores);
                
                dao_moeda.consultaGeral(moeda);
                //Preenche dados nas ComboBox de moeda
                array_moedas = combo.PreencherCombo(jCBMoedaFrete, "unidade", moeda.getRetorno(), "id_moeda");
                array_moedas = combo.PreencherCombo(jCBMoedaImpostos, "unidade", moeda.getRetorno(), "id_moeda");
                //seta no array da classe de moeda a lista de moedas listadas na combo
                moeda.setArray_moeda(array_moedas);
                

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Falha ao iniciar a inserção de fornecimentos");
            }
        }else{
            JOptionPane.showMessageDialog(null, "Voce não possui permissões para incluir fornecimentos no sistema");
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
            valida_campos.habilitaCampos(jPFornecimento);
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
            componente.setId_componente(Integer.parseInt(jTFIDFornecimento.getText()));

            if (mensagem.ValidaMensagem("Deseja realmente excluir o registro ?") == 0) {
                //Inativa componente
                dao_componente.inativaComponente(componente);
                JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
                //Limpa os campos da tela pessoa
                valida_campos.LimparCampos(jPFornecimento);
                valida_campos.LimparJtable(jTBComponentes);
                valida_campos.LimparJtable(jTBComponentesProjetos);
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
            if (valida_campos.validacamposobrigatorios(jPFornecimento, "COMPONENTE") == 0) {
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
                        valida_campos.LimparCampos(jPFornecimento);
                        valida_campos.LimparJtable(jTBComponentes);
                        valida_campos.LimparJtable(jTBComponentesProjetos);
                        valida_campos.LimparJtable(jTBConsultaComponentes);
                        valida_campos.LimparJtable(jTBConsultaFornecedores);
                        valida_campos.LimparJtable(jTBConsultaComposicao);
                        valida_campos.LimparJtable(jTBContatoFornecedores);

                        //Define a situação como incluir para habilitar os botoes utilizados apenas na inclusão
                        situacao = Rotinas.INICIAL;

                        //habilita os botoes utilizados na inclusão e desabilita os restantes
                        valida_botoes.ValidaEstado(jPBotoes, situacao);

                        //desabilita campos
                        valida_campos.desabilitaCampos(jPFornecimento);
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Falha ao incluir componente");
                }

            }
        }else if(situacao == Rotinas.ALTERAR) {
            //pega dados do material na tela
            if (valida_campos.validacamposobrigatorios(jPFornecimento, "COMPONENTE") == 0) {
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
                        valida_campos.LimparCampos(jPFornecimento);
                        valida_campos.LimparJtable(jTBComponentes);
                        valida_campos.LimparJtable(jTBComponentesProjetos);
                        valida_campos.LimparJtable(jTBConsultaComponentes);
                        valida_campos.LimparJtable(jTBConsultaFornecedores);
                        valida_campos.LimparJtable(jTBConsultaComposicao);
                        valida_campos.LimparJtable(jTBContatoFornecedores);

                        //Define a situação como incluir para habilitar os botoes utilizados apenas na inclusão
                        situacao = Rotinas.INICIAL;

                        //habilita os botoes utilizados na inclusão e desabilita os restantes
                        valida_botoes.ValidaEstado(jPBotoes, situacao);

                        //desabilita campos
                        valida_campos.desabilitaCampos(jPFornecimento);
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Falha ao alterar componente");
                }
            }
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        //limpa campos
        valida_campos.LimparCampos(jPFornecimento);
        valida_campos.LimparJtable(jTBComponentes);
        valida_campos.LimparJtable(jTBComponentesProjetos);

        //Define a situação como incluir para habilitar os botoes utilizados apenas na inclusão
        situacao = Rotinas.INICIAL;

        //habilita os botoes utilizados na inclusão e desabilita os restantes
        valida_botoes.ValidaEstado(jPBotoes, situacao);

        //desabilita campos
        valida_campos.desabilitaCampos(jPFornecimento);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jCBFornecedorPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jCBFornecedorPopupMenuWillBecomeInvisible

    }//GEN-LAST:event_jCBFornecedorPopupMenuWillBecomeInvisible

    private void jCBFornecedorPopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jCBFornecedorPopupMenuWillBecomeVisible
        dao_fornecedor.consultageral(fornecedor);
        //Preenche dados nas ComboBox de fornecedores
        array_fornecedores = combo.PreencherCombo(jCBFornecedor, "nome", fornecedor.getRetorno(), "id_pessoa");
        //seta no array da classe de fornecedores a lista de fornecedores listados na combo
        fornecedor.setArray_fornecedor(array_fornecedores);
    }//GEN-LAST:event_jCBFornecedorPopupMenuWillBecomeVisible

    private void jBTNovoFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTNovoFornecedorActionPerformed
       try {
            //atualiza dados do usuario logado
            dao_acesso.retornaUsuarioLogado(acesso);
            //Inclui a opção todas telas como primeira opção
            tela.setDescricao("Todas telas");
            tela.setId_tela(1);
            dao_tela.incluir(tela);
            
            //Inclui a a tela de Pessoas
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
                new InterfacePessoa().setVisible(true);
           }else{
               JOptionPane.showMessageDialog(null, "Voce não possui permissões para acessar essa tela"); 
           }
            
        } catch (SQLException ex) {
            Logger.getLogger(InterfacePessoa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jBTNovoFornecedorActionPerformed

    private void jTBComponentesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTBComponentesMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTBComponentesMouseClicked

    private void jBTAddComponenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTAddComponenteActionPerformed
        new InterfaceComposicaoComponente().setVisible(true);
        componente.setTabela(jTBComponentes);
        composicao.setSituacao(situacao);
        composicao.setId_componente(Integer.parseInt(jTFIDFornecimento.getText()));
    }//GEN-LAST:event_jBTAddComponenteActionPerformed

    private void jBTRemoveComponenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTRemoveComponenteActionPerformed
        if (valida_campos.VerificaJtable(jTBComponentes) == 1) {
            int linha = jTBComponentes.getSelectedRow();
            Integer exc = Integer.parseInt(jTBComponentes.getValueAt(linha, 8).toString());
            //se não for um item removido
            if (exc == 0) {
                Jtable.removeItens(jTBComponentes, situacao);
            }else{
                JOptionPane.showMessageDialog(null, "Item já removido");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Não possui componentes para remover");
        }
    }//GEN-LAST:event_jBTRemoveComponenteActionPerformed

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
            valida_campos.LimparCampos(jPFornecimento);
            //limpa tabela de endereços e composição na tela de cadastro de componente
            valida_campos.LimparJtable(jTBComponentes);
            valida_campos.LimparJtable(jTBComponentesProjetos);
            //desabilita campos
            valida_campos.desabilitaCampos(jPFornecimento);

            //Carrega conteudo das combobox
            jCBTipo.addItem("Selecione tipo");
            jCBTipo.addItem("Eletrônico");
            jCBTipo.addItem("Mecânico");

            dao_material.consultaGeral(material);
            //Preenche dados nas ComboBox de material
            array_material = combo.PreencherCombo(jCBFornecedor, "descricao", material.getRetorno(), "id_material");
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
            Jtable.PreencherJtableGenerico(jTBComponentes, new String[]{"null","id_composicao","id_subcomponente", "tipo", "componente.descricao", "material.id_material", "material.descricao", "qntd", "false"}, composicao.getRetorno());
            Jtable.ajustarColunasDaTabela(jTBComponentes);

            //Preenche na JTABLE os dados dos fornecedores dos componentes
            Jtable.PreencherJtableGenerico(jTBComponentesProjetos, new String[]{"null","id_fornecedores_comp","id_pessoa", "nome", "cpf_cnpj", "site", "false"}, fornec_comp.getRetorno());
            Jtable.ajustarColunasDaTabela(jTBComponentesProjetos);

            //retorna para tela de cadastro
            jTBComponente.setSelectedIndex(0);

            //Define a situação como padrao para habilitar os botoes utilizados apenas na alteração ou exclusão
            situacao = Rotinas.PADRAO;
            //habilita os botoes utilizados na alteraçao e exclusão e desabilita os restantes
            valida_botoes.ValidaEstado(jPBotoes, situacao);
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

    private void jTBComponenteStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTBComponenteStateChanged

    }//GEN-LAST:event_jTBComponenteStateChanged

    private void jBTRemoveComponenteProjetoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTRemoveComponenteProjetoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBTRemoveComponenteProjetoActionPerformed

    private void jCBMoedaFretePopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jCBMoedaFretePopupMenuWillBecomeVisible
        dao_moeda.consultaGeral(moeda);
        //Preenche dados nas ComboBox de moeda
        array_moedas = combo.PreencherCombo(jCBMoedaFrete, "unidade", moeda.getRetorno(), "id_moeda");
        //seta no array da classe de moeda a lista de moedas listadas na combo
        moeda.setArray_moeda(array_moedas);
    }//GEN-LAST:event_jCBMoedaFretePopupMenuWillBecomeVisible

    private void jCBMoedaImpostosPopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jCBMoedaImpostosPopupMenuWillBecomeVisible
        dao_moeda.consultaGeral(moeda);
        //Preenche dados nas ComboBox de moeda
        array_moedas = combo.PreencherCombo(jCBMoedaImpostos, "unidade", moeda.getRetorno(), "id_moeda");
        //seta no array da classe de moeda a lista de moedas listadas na combo
        moeda.setArray_moeda(array_moedas);
    }//GEN-LAST:event_jCBMoedaImpostosPopupMenuWillBecomeVisible

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
            java.util.logging.Logger.getLogger(InterfaceFornecimento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterfaceFornecimento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterfaceFornecimento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterfaceFornecimento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InterfaceFornecimento().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBTAddComponente;
    private javax.swing.JButton jBTAddparaProjeto;
    private javax.swing.JButton jBTBuscar;
    private javax.swing.JButton jBTNovoFornecedor;
    private javax.swing.JButton jBTRemoveComponente;
    private javax.swing.JButton jBTRemoveComponenteProjeto;
    private javax.swing.JButton jBTVerDatasheet;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox jCBBuscarPor;
    private javax.swing.JComboBox jCBFornecedor;
    private javax.swing.JComboBox jCBMoedaFrete;
    private javax.swing.JComboBox jCBMoedaImpostos;
    private javax.swing.JComboBox jCBTipoConsulta;
    private javax.swing.JFormattedTextField jFTData;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPBotoes;
    private javax.swing.JPanel jPFornecimento;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTBComponente;
    private javax.swing.JTable jTBComponentes;
    private javax.swing.JTable jTBComponentesProjetos;
    private javax.swing.JTable jTBConsultaComponentes;
    private javax.swing.JTextField jTFDescrição;
    private javax.swing.JTextField jTFFiltro;
    private javax.swing.JTextField jTFIDFornecimento;
    private javax.swing.JTextField jTFValorFrete;
    private javax.swing.JTextField jTFValorImpostos;
    // End of variables declaration//GEN-END:variables
}

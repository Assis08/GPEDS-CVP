/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.GPEDSCVP.interfaces;

import br.edu.GPEDSCVP.classe.Acesso;
import br.edu.GPEDSCVP.classe.Componente;
import br.edu.GPEDSCVP.classe.ComponenteFornecimento;
import br.edu.GPEDSCVP.classe.ComponenteVersaoProjeto;
import br.edu.GPEDSCVP.classe.ComposicaoComponente;
import br.edu.GPEDSCVP.classe.Fornecedor;
import br.edu.GPEDSCVP.classe.Fornecimento;
import br.edu.GPEDSCVP.classe.Material;
import br.edu.GPEDSCVP.classe.Moeda;
import br.edu.GPEDSCVP.classe.Permissao;
import br.edu.GPEDSCVP.classe.Tela;
import br.edu.GPEDSCVP.classe.VersaoProjeto;
import br.edu.GPEDSCVP.dao.daoAcesso;
import br.edu.GPEDSCVP.dao.daoComponente;
import br.edu.GPEDSCVP.dao.daoComponenteVersaoProjeto;
import br.edu.GPEDSCVP.dao.daoComponentesFornecimento;
import br.edu.GPEDSCVP.dao.daoFornecimento;
import br.edu.GPEDSCVP.dao.daoMoeda;
import br.edu.GPEDSCVP.dao.daoPermissao;
import br.edu.GPEDSCVP.dao.daoPessoa;
import br.edu.GPEDSCVP.dao.daoTela;
import br.edu.GPEDSCVP.util.ComboBox;
import br.edu.GPEDSCVP.util.Conversoes;
import br.edu.GPEDSCVP.util.FormatarData;
import br.edu.GPEDSCVP.util.ManipulaJtable;
import br.edu.GPEDSCVP.util.Mensagens;
import br.edu.GPEDSCVP.util.Rotinas;
import br.edu.GPEDSCVP.util.TableCellListener;
import br.edu.GPEDSCVP.util.UltimaSequencia;
import br.edu.GPEDSCVP.util.ValidaAcesso;
import br.edu.GPEDSCVP.util.ValidaBotoes;
import br.edu.GPEDSCVP.util.ValidaCampos;
import br.edu.GPEDSCVP.util.ValorLimitadoListener;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.DefaultFormatterFactory;

/**
 *
 * @author Willys
 */
public class InterfaceFornecimento extends javax.swing.JFrame {

    Componente componente;
    VersaoProjeto versao;
    Fornecimento fornecimento;
    ComponenteFornecimento comp_fornec;
    ComponenteVersaoProjeto comp_vers_proj;
    Fornecedor fornecedor;
    Moeda moeda;
    daoMoeda dao_moeda;
    daoFornecimento dao_fornecimento;
    daoComponentesFornecimento dao_comp_fornec;
    daoComponenteVersaoProjeto dao_comp_vers;
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
    Conversoes conversao;
    int[] array_moedas;
    int[] array_fornecedores;
    int situacao = Rotinas.PADRAO;
    
    
    public InterfaceFornecimento() {
        initComponents();
        
        //Cria renderer para as Jtable  
        TableCellRenderer renderer = new EvenOddRenderer();
        jTBComponentes.setDefaultRenderer(Object.class, renderer);
        jTBComponentesProjetos.setDefaultRenderer(Object.class, renderer);
       
        //implementa Listener para edição da jtable
        new TableCellListener(jTBComponentes, new TableCellEditorAction());
        new TableCellListener(jTBComponentesProjetos, new TableCellEditorAction());
        
        componente = new Componente();
        fornecimento = new Fornecimento();
        versao = new VersaoProjeto();
        dao_fornecimento = new daoFornecimento();
        dao_comp_fornec = new daoComponentesFornecimento();
        dao_comp_vers = new daoComponenteVersaoProjeto();
        comp_fornec = new ComponenteFornecimento();
        comp_vers_proj = new ComponenteVersaoProjeto();
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
        conversao = new Conversoes();
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
        jTBConsultaFornecimentos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jTBConsultaCompFornec.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        //seta mascaras nos campos monetários
        valida_campos.formataMonetario(jFTValorFrete);
        valida_campos.formataMonetario(jFTFreteReais);
        valida_campos.formataMonetario(jFTValorImpostos);
        valida_campos.formataMonetario(jFTImpostosReais);
       // valida_campos.formataMonetario(jFTMascaMonetaria);
        
        //Seta mascara na coluna de valor da jtable
        Jtable.setarMascara(jTBComponentes, jFTMascaMonetaria,4);
        
        //seta mascara no campo de data
        jFTData.setFormatterFactory(new DefaultFormatterFactory(valida_campos.formata("##/##/#### ##:##:##")));
        
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

        jCheckBox1 = new javax.swing.JCheckBox();
        jFTMascaMonetaria = new javax.swing.JFormattedTextField();
        jTBFornecimento = new javax.swing.JTabbedPane();
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
        jSeparator1 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel13 = new javax.swing.JLabel();
        jCBMoedaImpostos = new javax.swing.JComboBox();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jBTAddparaProjeto = new javax.swing.JButton();
        jBTRemoveComponenteProjeto = new javax.swing.JButton();
        jFTValorFrete = new javax.swing.JFormattedTextField();
        jFTValorImpostos = new javax.swing.JFormattedTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jFTFreteReais = new javax.swing.JFormattedTextField();
        jFTImpostosReais = new javax.swing.JFormattedTextField();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTBConsultaFornecimentos = new javax.swing.JTable();
        jCBBuscarPor = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();
        jTFFiltro = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jBTBuscar = new javax.swing.JButton();
        jCBTipoConsulta = new javax.swing.JComboBox();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTBConsultaCompFornec = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jTFTotalFornecimento = new javax.swing.JTextField();

        jCheckBox1.setText("jCheckBox1");

        jFTMascaMonetaria.setText("jFormattedTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Fornecimentos");
        setResizable(false);

        jTBFornecimento.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTBFornecimentoStateChanged(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon("C:\\Users\\rafa\\Documents\\GPEDS-CVP\\src\\br\\edu\\GPEDSCVP\\icones\\Botoes_Site_5752_Knob_Add.png")); // NOI18N
        jButton1.setText("Novo");
        jButton1.setToolTipText("Cidade");
        jButton1.setName("ID_CIDADE"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon("C:\\Users\\rafa\\Documents\\GPEDS-CVP\\src\\br\\edu\\GPEDSCVP\\icones\\Botoes_5122_pencil_48.png")); // NOI18N
        jButton2.setText("Alterar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon("C:\\Users\\rafa\\Documents\\GPEDS-CVP\\src\\br\\edu\\GPEDSCVP\\icones\\Botoes_Site_5751_Knob_Remove_Red.png")); // NOI18N
        jButton3.setText("Excluir");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon("C:\\Users\\rafa\\Documents\\GPEDS-CVP\\src\\br\\edu\\GPEDSCVP\\icones\\Hardware-Floppy-icon (Custom).png")); // NOI18N
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

        jButton4.setIcon(new javax.swing.ImageIcon("C:\\Users\\rafa\\Documents\\GPEDS-CVP\\src\\br\\edu\\GPEDSCVP\\icones\\Botoes_Site_5750_Knob_Cancel.png")); // NOI18N
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
        jTFIDFornecimento.setName("id_fornecimento"); // NOI18N

        jLabel1.setText("ID Fornecimento:");

        jCBFornecedor.setToolTipText("Fornecedor");
        jCBFornecedor.setName("id_pessoa"); // NOI18N
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

        jBTNovoFornecedor.setIcon(new javax.swing.ImageIcon("C:\\Users\\rafa\\Documents\\GPEDS-CVP\\src\\br\\edu\\GPEDSCVP\\icones\\add.png")); // NOI18N
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
                "Sel", "ID Fornecidos", "ID Componente", "Componente", "Valor Unitário", "ID Moeda", "Moeda", "Qtde Fornecida", "Restante", "Total", "exc"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, true, false, false, true, false, false, false
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
            jTBComponentes.getColumnModel().getColumn(10).setMinWidth(0);
            jTBComponentes.getColumnModel().getColumn(10).setPreferredWidth(0);
            jTBComponentes.getColumnModel().getColumn(10).setMaxWidth(0);
        }

        jLabel2.setText("Componentes:");

        jLabel7.setText("Data/Hora:");

        jFTData.setToolTipText("Data");
        jFTData.setName("data_cadastro"); // NOI18N
        jFTData.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jFTDataCaretUpdate(evt);
            }
        });
        jFTData.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jFTDataFocusLost(evt);
            }
        });
        jFTData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jFTDataMouseClicked(evt);
            }
        });
        jFTData.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                jFTDataInputMethodTextChanged(evt);
            }
        });
        jFTData.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFTDataKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jFTDataKeyTyped(evt);
            }
        });

        jBTAddComponente.setIcon(new javax.swing.ImageIcon("C:\\Users\\rafa\\Documents\\GPEDS-CVP\\src\\br\\edu\\GPEDSCVP\\icones\\Botoes_Site_5752_Knob_Add.png")); // NOI18N
        jBTAddComponente.setName("descricao"); // NOI18N
        jBTAddComponente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTAddComponenteActionPerformed(evt);
            }
        });

        jBTRemoveComponente.setIcon(new javax.swing.ImageIcon("C:\\Users\\rafa\\Documents\\GPEDS-CVP\\src\\br\\edu\\GPEDSCVP\\icones\\Botoes_Site_5751_Knob_Remove_Red.png")); // NOI18N
        jBTRemoveComponente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTRemoveComponenteActionPerformed(evt);
            }
        });

        jTBComponentesProjetos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "sel", "ID Componentes", "ID Fornecidos", "ID Projeto", "ID Versão", "Projeto", "Versão", "ID Componente", "Componente", "Qntde", "exc"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, false, false, false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(jTBComponentesProjetos);
        if (jTBComponentesProjetos.getColumnModel().getColumnCount() > 0) {
            jTBComponentesProjetos.getColumnModel().getColumn(10).setMinWidth(0);
            jTBComponentesProjetos.getColumnModel().getColumn(10).setPreferredWidth(0);
            jTBComponentesProjetos.getColumnModel().getColumn(10).setMaxWidth(0);
        }

        jLabel10.setText("Componentes para Projetos:");

        jLabel8.setText("Moeda:");

        jCBMoedaFrete.setToolTipText("Moeda Frete");
        jCBMoedaFrete.setName("id_moeda_frete"); // NOI18N
        jCBMoedaFrete.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                jCBMoedaFretePopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
                jCBMoedaFretePopupMenuWillBecomeVisible(evt);
            }
        });

        jLabel5.setText("Frete");

        jLabel6.setText("Impostos");

        jLabel13.setText("Moeda:");

        jCBMoedaImpostos.setToolTipText("Moeda Impostos");
        jCBMoedaImpostos.setName("id_moeda_imp"); // NOI18N
        jCBMoedaImpostos.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                jCBMoedaImpostosPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
                jCBMoedaImpostosPopupMenuWillBecomeVisible(evt);
            }
        });

        jLabel16.setText("Valor:");

        jLabel17.setText("Valor:");

        jBTAddparaProjeto.setIcon(new javax.swing.ImageIcon("C:\\Users\\rafa\\Documents\\GPEDS-CVP\\src\\br\\edu\\GPEDSCVP\\icones\\arrow_down.png")); // NOI18N
        jBTAddparaProjeto.setText("Add Projeto");
        jBTAddparaProjeto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTAddparaProjetoActionPerformed(evt);
            }
        });

        jBTRemoveComponenteProjeto.setIcon(new javax.swing.ImageIcon("C:\\Users\\rafa\\Documents\\GPEDS-CVP\\src\\br\\edu\\GPEDSCVP\\icones\\Botoes_Site_5751_Knob_Remove_Red.png")); // NOI18N
        jBTRemoveComponenteProjeto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTRemoveComponenteProjetoActionPerformed(evt);
            }
        });

        jFTValorFrete.setToolTipText("Frete Valor");
        jFTValorFrete.setName("vl_frete"); // NOI18N
        jFTValorFrete.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jFTValorFreteCaretUpdate(evt);
            }
        });
        jFTValorFrete.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jFTValorFreteFocusLost(evt);
            }
        });
        jFTValorFrete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFTValorFreteActionPerformed(evt);
            }
        });
        jFTValorFrete.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFTValorFreteKeyPressed(evt);
            }
        });

        jFTValorImpostos.setToolTipText("Impostos Valor");
        jFTValorImpostos.setName("vl_impostos"); // NOI18N
        jFTValorImpostos.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jFTValorImpostosCaretUpdate(evt);
            }
        });
        jFTValorImpostos.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jFTValorImpostosFocusLost(evt);
            }
        });
        jFTValorImpostos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFTValorImpostosActionPerformed(evt);
            }
        });

        jLabel19.setText("R$:");

        jLabel20.setText("R$:");

        jFTFreteReais.setEditable(false);
        jFTFreteReais.setName("sem_nome"); // NOI18N

        jFTImpostosReais.setEditable(false);
        jFTImpostosReais.setName("sem_nome"); // NOI18N

        javax.swing.GroupLayout jPFornecimentoLayout = new javax.swing.GroupLayout(jPFornecimento);
        jPFornecimento.setLayout(jPFornecimentoLayout);
        jPFornecimentoLayout.setHorizontalGroup(
            jPFornecimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPFornecimentoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPFornecimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPFornecimentoLayout.createSequentialGroup()
                        .addGroup(jPFornecimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPFornecimentoLayout.createSequentialGroup()
                                .addGroup(jPFornecimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPFornecimentoLayout.createSequentialGroup()
                                        .addGroup(jPFornecimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jCBMoedaFrete, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel8))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPFornecimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jFTValorFrete, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel16))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPFornecimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel19)
                                            .addComponent(jFTFreteReais)))
                                    .addComponent(jLabel5)
                                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPFornecimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jSeparator2)
                                    .addComponent(jLabel6)
                                    .addGroup(jPFornecimentoLayout.createSequentialGroup()
                                        .addGroup(jPFornecimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel13)
                                            .addComponent(jCBMoedaImpostos, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPFornecimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jFTValorImpostos, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel17))
                                        .addGroup(jPFornecimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPFornecimentoLayout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel20)
                                                .addGap(68, 68, 68))
                                            .addGroup(jPFornecimentoLayout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jFTImpostosReais, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))))))
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPFornecimentoLayout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jBTAddparaProjeto))
                            .addGroup(jPFornecimentoLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jPBotoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPFornecimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jBTRemoveComponenteProjeto, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBTAddComponente, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBTRemoveComponente, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(102, 102, 102))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPFornecimentoLayout.createSequentialGroup()
                        .addGroup(jPFornecimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTFIDFornecimento, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(30, 30, 30)
                        .addGroup(jPFornecimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTFDescrição, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPFornecimentoLayout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jFTData, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(81, 81, 81))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPFornecimentoLayout.createSequentialGroup()
                        .addGroup(jPFornecimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPFornecimentoLayout.createSequentialGroup()
                        .addComponent(jCBFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jBTNovoFornecedor)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPFornecimentoLayout.setVerticalGroup(
            jPFornecimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPFornecimentoLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPFornecimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPFornecimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(jFTData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPFornecimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPFornecimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTFIDFornecimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTFDescrição, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                        .addGroup(jPFornecimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPFornecimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel8)
                                .addComponent(jLabel16))
                            .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPFornecimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCBMoedaFrete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFTValorFrete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFTFreteReais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPFornecimentoLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPFornecimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(jLabel17)
                            .addComponent(jLabel20))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPFornecimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCBMoedaImpostos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFTValorImpostos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFTImpostosReais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPFornecimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPFornecimentoLayout.createSequentialGroup()
                        .addComponent(jBTAddComponente, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBTRemoveComponente, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPFornecimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jBTAddparaProjeto, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPFornecimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBTRemoveComponenteProjeto, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPBotoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        jTBFornecimento.addTab("Cadastro", jPFornecimento);

        jTBConsultaFornecimentos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Fornecimento", "Descrição", "ID Fornecedor", "Fornecedor", "ID Moeda Frete", "Moeda", "Valor Frete", "ID Moeda Imposto", "Moeda", "Valor Impostos", "Data Fornecimento", "Última alteração"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTBConsultaFornecimentos.setName("Componentes"); // NOI18N
        jTBConsultaFornecimentos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTBConsultaFornecimentosMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTBConsultaFornecimentosMouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(jTBConsultaFornecimentos);

        jCBBuscarPor.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Geral", "Código", "Descrição" }));

        jLabel14.setText("Buscar por:");

        jLabel29.setText("Filtro de busca:");

        jBTBuscar.setIcon(new javax.swing.ImageIcon("C:\\Users\\rafa\\Documents\\GPEDS-CVP\\src\\br\\edu\\GPEDSCVP\\icones\\magnifier.png")); // NOI18N
        jBTBuscar.setText("Buscar");
        jBTBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTBuscarActionPerformed(evt);
            }
        });

        jCBTipoConsulta.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Fornecimento", "Componente", "Projeto" }));

        jLabel15.setText("Tipo:");

        jTBConsultaCompFornec.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Componentes", "ID Componente", "Descrição", "Tipo", "ID Moeda", "Moeda", "Valor Unit", "Imposto Unit (R$)", "Qntde", "Total R$", "Última alteração"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTBConsultaCompFornec.setName("Componentes"); // NOI18N
        jTBConsultaCompFornec.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTBConsultaCompFornecMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTBConsultaCompFornec);

        jLabel9.setText("Componentes:");

        jLabel11.setText("Total R$ :");

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
                                        .addComponent(jBTBuscar))
                                    .addComponent(jLabel29)))
                            .addComponent(jLabel9))
                        .addGap(0, 165, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFTotalFornecimento, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                            .addComponent(jCBTipoConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jTFTotalFornecimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jTBFornecimento.addTab("Consulta", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTBFornecimento, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 829, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jTBFornecimento)
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(845, 697));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        UltimaSequencia ultima;

        //habilita campos da tela
        valida_campos.habilitaCampos(jPFornecimento);
        //desabilita alguns campos necessários
        jFTValorFrete.setEnabled(false);
        jFTValorImpostos.setEnabled(false);
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
                jFTData.setText(data.DataHoraAtual());

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

            //Seta o id do fornecimento para exclusão
            fornecimento.setId_fornecimento(Integer.parseInt(jTFIDFornecimento.getText()));

            if (mensagem.ValidaMensagem("Deseja realmente excluir o registro ?") == 0) {
                //Inativa fornecimento
                dao_fornecimento.inativaFornecimento(fornecimento);
                JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
                //Limpa os campos da tela fornecimento
                valida_campos.LimparCampos(jPFornecimento);
                valida_campos.LimparJtable(jTBComponentes);
                valida_campos.LimparJtable(jTBComponentesProjetos);
                valida_campos.LimparJtable(jTBConsultaFornecimentos);
                valida_campos.LimparJtable(jTBConsultaCompFornec);
                jFTFreteReais.setValue(null);
                jFTValorFrete.setValue(null);
                jFTValorImpostos.setValue(null);
                jFTImpostosReais.setValue(null);

                //Define a situação como inicial para habilitar os botoes utilizados apenas quando inicia a tela
                situacao = Rotinas.INICIAL;

                //habilita os botoes utilizados na inicialização da tela
                valida_botoes.ValidaEstado(jPBotoes, situacao);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Voce não possui permissões para excluir fornecimentos no sistema");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5MouseExited

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        //Se for inclusão
        if (situacao == Rotinas.INCLUIR) {
            if (valida_campos.validacamposobrigatorios(jPFornecimento, "FORNECIMENTO") == 0) {
                if(valida_campos.VerificaJtable(jTBComponentes) == 1){ 
                    if(dao_comp_fornec.verificaCompFornec(jTBComponentes) == true){
                        try {
                            //pega dados do fornecimento na tela
                            getFornecimento();
                            //inclui componente
                            if(dao_fornecimento.incluir(fornecimento) == true){
                                getCompFornec();
                                getCompVersProj();
                                
                                dao_comp_fornec.gravarCompFornec(comp_fornec);
                                dao_comp_vers.gravarCompVersProj(comp_vers_proj);

                                //se ocorreu tudo bem na inclusão
                                JOptionPane.showMessageDialog(null, "Salvo com Sucesso");
                                //limpa campos
                                jFTFreteReais.setValue(null);
                                jFTValorFrete.setValue(null);
                                jFTValorImpostos.setValue(null);
                                jFTImpostosReais.setValue(null);
                                valida_campos.LimparCampos(jPFornecimento);
                                valida_campos.LimparJtable(jTBComponentes);
                                valida_campos.LimparJtable(jTBComponentesProjetos);
                                valida_campos.LimparJtable(jTBConsultaFornecimentos);
                                valida_campos.LimparJtable(jTBConsultaCompFornec);
                               
                                //Define a situação como incluir para habilitar os botoes utilizados apenas na inclusão
                                situacao = Rotinas.INICIAL;

                                //habilita os botoes utilizados na inclusão e desabilita os restantes
                                valida_botoes.ValidaEstado(jPBotoes, situacao);

                                //desabilita campos
                                valida_campos.desabilitaCampos(jPFornecimento);
                            }
                        } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(null, "Falha ao incluir fornecimento");
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Todos componentes fornecidos devem ser adicionados para projeto(s)!");
                    } 
                }else{
                    JOptionPane.showMessageDialog(null,"Informar quais componentes foram fornecidos!");
                }
            }
        }else if(situacao == Rotinas.ALTERAR) {
            //pega dados do material na tela
            if (valida_campos.validacamposobrigatorios(jPFornecimento, "FORNECIMENTO") == 0) {
                 if(valida_campos.VerificaJtable(jTBComponentes) == 1){ 
                    if(dao_comp_fornec.verificaCompFornec(jTBComponentes) == true){
                        try {
                            getFornecimento();
                            //alterar componente
                            if(dao_fornecimento.alterar(fornecimento) == true){
                                //altera composição
                                getCompFornec();
                                getCompVersProj();

                                dao_comp_fornec.alterarCompFornec(comp_fornec);
                                dao_comp_vers.alterarCompVersProj(comp_vers_proj);

                                JOptionPane.showMessageDialog(null, "Salvo com Sucesso");
                                //limpa campos
                                jFTFreteReais.setValue(null);
                                jFTValorFrete.setValue(null);
                                jFTValorImpostos.setValue(null);
                                jFTImpostosReais.setValue(null);
                                valida_campos.LimparCampos(jPFornecimento);
                                valida_campos.LimparJtable(jTBComponentes);
                                valida_campos.LimparJtable(jTBComponentesProjetos);
                                valida_campos.LimparJtable(jTBConsultaFornecimentos);
                                valida_campos.LimparJtable(jTBConsultaCompFornec);
                                
                                //Define a situação como incluir para habilitar os botoes utilizados apenas na inclusão
                                situacao = Rotinas.INICIAL;

                                //habilita os botoes utilizados na inclusão e desabilita os restantes
                                valida_botoes.ValidaEstado(jPBotoes, situacao);

                                //desabilita campos
                                valida_campos.desabilitaCampos(jPFornecimento);
                            }
                        } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(null, "Falha ao alterar fornecimento");
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Todos componentes fornecidos devem ser adicionados para projeto(s)!");
                    }
                 }else{
                     JOptionPane.showMessageDialog(null,"Informar quais componentes foram fornecidos!");
                 }
            }
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        //limpa campos
        valida_campos.LimparCampos(jPFornecimento);
        valida_campos.LimparJtable(jTBComponentes);
        valida_campos.LimparJtable(jTBComponentesProjetos);
        jFTFreteReais.setValue(null);
        jFTValorFrete.setValue(null);
        jFTValorImpostos.setValue(null);
        jFTImpostosReais.setValue(null);

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
        comp_fornec.setTabela(jTBComponentes);
        comp_fornec.setSituacao(situacao);
        new InterfaceFornecimentoComponente().setVisible(true);
    }//GEN-LAST:event_jBTAddComponenteActionPerformed

    private void jBTRemoveComponenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTRemoveComponenteActionPerformed
        if (valida_campos.VerificaJtable(jTBComponentes) == 1) {
            int linha = jTBComponentes.getSelectedRow();
            Integer exc = Integer.parseInt(jTBComponentes.getValueAt(linha, 10).toString());
            //se não for um item removido
            if (exc == 0) {
                dao_comp_fornec.removeAtualizaItens(jTBComponentes,jTBComponentesProjetos, situacao);
            }else{
                JOptionPane.showMessageDialog(null, "Item já removido");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Não possui componentes para remover");
        }
    }//GEN-LAST:event_jBTRemoveComponenteActionPerformed

    private void jCBMoedaFretePopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jCBMoedaFretePopupMenuWillBecomeVisible

    }//GEN-LAST:event_jCBMoedaFretePopupMenuWillBecomeVisible

    private void jCBMoedaImpostosPopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jCBMoedaImpostosPopupMenuWillBecomeVisible

    }//GEN-LAST:event_jCBMoedaImpostosPopupMenuWillBecomeVisible

    private void jBTRemoveComponenteProjetoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTRemoveComponenteProjetoActionPerformed
        if (valida_campos.VerificaJtable(jTBComponentesProjetos) == 1) {
            int linha = jTBComponentesProjetos.getSelectedRow();
            Integer exc = Integer.parseInt(jTBComponentesProjetos.getValueAt(linha, 10).toString());
            //se não for um item removido
            if (exc == 0) {
                dao_comp_vers.removeAtualizaItens(jTBComponentesProjetos,jTBComponentes, situacao);
            }else{
                JOptionPane.showMessageDialog(null, "Item já removido");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Não possui componentes para remover");
        }
    }//GEN-LAST:event_jBTRemoveComponenteProjetoActionPerformed

    private void jTBConsultaFornecimentosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTBConsultaFornecimentosMouseClicked
        //Verifica se houve 1 clique do mouse

        //recupera a linha clicada
        int linha = jTBConsultaFornecimentos.getSelectedRow();
        Integer id_fornec = Integer.parseInt(jTBConsultaFornecimentos.getValueAt(linha, 0).toString());

        //busca componentes do fornecimento clicado
        comp_fornec.setId_fornecimento(id_fornec);
        comp_fornec.setTabela(jTBConsultaCompFornec);
        dao_comp_fornec.consultaCompFornec(comp_fornec);

        if (evt.getClickCount() == 1) {

            //Preenche na JTABLE os dados dos componentes cadastrados
            Jtable.PreencherJtableGenerico(jTBConsultaCompFornec, new String[]{"componentes_fornecimento.id_comp_fornec", "componentes_fornecimento.id_componente", 
                "componente.descricao", "componente.tipo", "componentes_fornecimento.id_moeda","moeda.unidade","componentes_fornecimento.valor_unit","null","qntd_componente","null","componentes_fornecimento.data_alter"}, comp_fornec.getRetorno());
            Jtable.ajustarColunasDaTabela(jTBConsultaCompFornec);
            
            //calcula imposto unitário de cada componente listado no fornecimento
            dao_comp_fornec.calculaImpostoUnit(comp_fornec, jTBConsultaFornecimentos);
            
            //calcula o valor total do componente no fornecimento
            dao_comp_fornec.calculaTotalCompFornec(comp_fornec, jTBConsultaFornecimentos);
            
            //Seta na field de total, o calculo do total do fornecimento selecionado
            jTFTotalFornecimento.setText(dao_comp_fornec.calculaTotalFornec(comp_fornec).toString());

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

            //retorna dados do fornecimento
            fornecimento.setId_fornecimento(id_fornec);
            dao_fornecimento.retornardadosFornecimento(fornecimento);

            //busca composição do componente clicado
            comp_fornec.setId_fornecimento(id_fornec);
            dao_comp_fornec.consultaCompFornec(comp_fornec);
            
            comp_vers_proj.setId_fornecimento(id_fornec);
            dao_comp_vers.consultaCompFornecVersProj(comp_vers_proj);
            //seta dados do componente na tela de cadastro
            setcompFornecimento();
           
            //Preenche na JTABLE de componentes fornecidos
            Jtable.PreencherJtableGenerico(jTBComponentes, new String[]{"null","componentes_fornecimento.id_comp_fornec","componentes_fornecimento.id_componente",
            "componente.descricao","componentes_fornecimento.valor_unit","componentes_fornecimento.id_moeda","moeda.unidade","qntd_componente","qntd_restante",
            "total","false"}, comp_fornec.getRetorno());
            Jtable.ajustarColunasDaTabela(jTBComponentes);

            //Preenche na JTABLE os componentes adicionados para os projetos
            Jtable.PreencherJtableGenerico(jTBComponentesProjetos, new String[]{"null","id_comp_versao","componentes_versao_projeto.id_comp_fornec",
            "componentes_versao_projeto.id_projeto","componentes_versao_projeto.cod_vers_projeto",
            "projeto.descricao","versao_projeto.versao","componentes_versao_projeto.id_componente","componente.descricao","qntd_para_projeto","false"}, comp_vers_proj.getRetorno());
            Jtable.ajustarColunasDaTabela(jTBComponentesProjetos);

            //retorna para tela de cadastro
            jTBFornecimento.setSelectedIndex(0);

            //Define a situação como padrao para habilitar os botoes utilizados apenas na alteração ou exclusão
            situacao = Rotinas.PADRAO;
            //habilita os botoes utilizados na alteraçao e exclusão e desabilita os restantes
            valida_botoes.ValidaEstado(jPBotoes, situacao);
        }
    }//GEN-LAST:event_jTBConsultaFornecimentosMouseClicked

    private void jBTBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTBuscarActionPerformed

        //Se não for gerente
        if (acesso.getIn_gerente() == 0) {
            //retorna as permissoes de acesso do usuario
            dao_permissao.retornaDadosPermissao(acesso, permissao);
        }

        //Verifica se o usuario possui permissao para acessar essa tela
        if (valida_acesso.verificaAcesso("consultar", acesso, permissao) == true) {

                // recupera linha selecionada
                int linha = jTBConsultaFornecimentos.getSelectedRow();
                int id_busca = 0;
                String ds_busca = "";
                
                //Limpa jtable para nova consulta 
                valida_campos.LimparJtable(jTBConsultaFornecimentos);
                valida_campos.LimparJtable(jTBConsultaCompFornec);

                //Tira aspas simples da string para evitar código sql
                valida_campos.IgnoraSQL(jTFFiltro);

                switch (jCBBuscarPor.getSelectedIndex()) {
                    //Buscar por : Consulta Geral
                    case 0:
                    //tipo : Fornecimentos
                    switch(jCBTipoConsulta.getSelectedIndex()){
                        case 0:
                            //Consulta geral de fornecimentos
                            dao_fornecimento.consultageral(fornecimento);
                            break;
                        case 1:
                            //Consulta geral de fornecimentos
                            dao_fornecimento.consultageral(fornecimento);
                            break;
                        case 2:
                            //Consulta geral de fornecimentos
                            dao_fornecimento.consultageral(fornecimento);
                        break;
                    }
                    break;
                    //Buscar por : Código
                    case 1:

                    //Combobox tipo: fornecimento
                    switch(jCBTipoConsulta.getSelectedIndex()){
                        case 0:
                            //Consulta pelo código do fornecimento
                            try {
                                id_busca = Integer.parseInt(jTFFiltro.getText());
                                fornecimento.setId_fornecimento(id_busca);
                                dao_fornecimento.consultaCodigoFornec(fornecimento);
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(null, "Deve informar um valor inteiro para consultar por código");
                                jTFFiltro.grabFocus();
                            }
                            break;
                        case 1:
                            //Consulta de fornecimentos pelo codigo do componente
                            try {
                                id_busca = Integer.parseInt(jTFFiltro.getText());
                                dao_fornecimento.consultaCodigoCompFornec(fornecimento,id_busca);
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(null, "Deve informar um valor inteiro para consultar por código");
                                jTFFiltro.grabFocus();
                            }
                            break;
                        case 2:
                             //Consulta de fornecimentos pelo codigo do projeto
                            try {
                                id_busca = Integer.parseInt(jTFFiltro.getText());
                                dao_fornecimento.consultaCodigoProjFornec(fornecimento,id_busca);
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(null, "Deve informar um valor inteiro para consultar por código");
                                jTFFiltro.grabFocus();
                            }
                        break;
                    }
                    break;

                    case 2:

                    //Combobox tipo: fornecimento
                    switch(jCBTipoConsulta.getSelectedIndex()){
                        case 0:
                            //Consulta fornecimentos em geral pela descrição
                            ds_busca = jTFFiltro.getText();
                            if(!ds_busca.replace(" ", "").equals("")){
                                fornecimento.setDescricao(ds_busca);
                                dao_fornecimento.consultaDescFornec(fornecimento);
                            }else{
                                JOptionPane.showMessageDialog(null, "Informe a descrição para consulta");
                                jTFFiltro.grabFocus();
                            }
                            break;
                        case 1:
                        //Consulta fornecimentos em geral pela descrição do componente
                            ds_busca = jTFFiltro.getText();
                            if(!ds_busca.replace(" ", "").equals("")){
                                dao_fornecimento.consultaDescCompFornec(fornecimento,ds_busca);
                            }else{
                                JOptionPane.showMessageDialog(null, "Informe a descrição para consulta");
                                jTFFiltro.grabFocus();
                            }
                        break;
                        case 2:
                        //Consulta geral de componentes pela descrição
                        ds_busca = jTFFiltro.getText();
                        if(!ds_busca.replace(" ", "").equals("")){
                            dao_fornecimento.consultaDescProjFornec(fornecimento,ds_busca);
                        }else{
                            JOptionPane.showMessageDialog(null, "Informe a descrição para consulta");
                            jTFFiltro.grabFocus();
                        }
                        break;
                    }
                    break;
                }
            //Preenche na JTABLE os dados dos componentes cadastrados
            Jtable.PreencherJtableGenerico(jTBConsultaFornecimentos, new String[]{"fornecimento.id_fornecimento","fornecimento.descricao","fornecedor.id_pessoa","pessoa.nome",
            "id_moeda_frete","moeda_frete.unidade","vl_frete","id_moeda_imp","moeda_imposto.unidade","vl_impostos","fornecimento.data_cadastro","fornecimento.data_alter"}, fornecimento.getRetorno());
            Jtable.ajustarColunasDaTabela(jTBConsultaFornecimentos);
        } else {
            JOptionPane.showMessageDialog(null, "Você nao possui permissões para consultar componentes no sistema");
        }
    }//GEN-LAST:event_jBTBuscarActionPerformed

    private void jTBFornecimentoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTBFornecimentoStateChanged

    }//GEN-LAST:event_jTBFornecimentoStateChanged

    private void jFTValorFreteFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFTValorFreteFocusLost
        
        Timestamp data_fornec = data.stringParaTimeStamp(jFTData.getText());
        
        if(jFTValorFrete.isEnabled())
        {
            if(!jFTValorFrete.getText().equals("")){
                Double valor_inserido;
                Double valor_convertido = 0.00;
                String valor;
                valor = jFTValorFrete.getText();
                valor = valor.replace(".", "").replace(",", ".");
                valor_inserido = Double.parseDouble(valor);
                if(valor_inserido > 0){
                    
                    valor_convertido = dao_moeda.converteparaReais(valor_inserido, moeda.getArray_moeda(jCBMoedaFrete.getSelectedIndex() - 1),data_fornec);
                    
                }else{
                    jFTFreteReais.setText("0.00");
                    jFTValorFrete.setText("0.00");
                }
                
                if(valor_convertido <= 0 && valor_inserido > 0){
                    jFTFreteReais.setValue(null);
                    jFTFreteReais.setText(null);
                    jFTValorFrete.setText(null);
                    jFTValorFrete.setValue(null);
                }else{
                    
                    if(valor_convertido >= 999999999){
                        JOptionPane.showMessageDialog(null, "Valor convertido excede o valor máximo!");
                        jFTValorFrete.setValue(null);
                        jFTValorFrete.setText(null);
                        jFTFreteReais.setValue(null);
                        jFTFreteReais.setText(null);
                        jFTValorFrete.grabFocus();
                    }else{
                        jFTFreteReais.setText(String.valueOf(valor_convertido).replace(".", ","));
                    }
                }   
            }
        }
    }//GEN-LAST:event_jFTValorFreteFocusLost

    private void jFTValorImpostosFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFTValorImpostosFocusLost
         Timestamp data_fornec = data.stringParaTimeStamp(jFTData.getText());
        
        //Verifica se foi selecionado uma moeda
        if(jFTValorImpostos.isEnabled()){
            //se o campo do valor não for vazio
            if(!jFTValorImpostos.getText().equals("")){
                Double valor_inserido;
                Double valor_convertido = 0.00;
                String valor;
                valor = jFTValorImpostos.getText();
                valor = valor.replace(".", "").replace(",", ".");
                valor_inserido = Double.parseDouble(valor);
       
                if(valor_inserido > 0){
                    valor_convertido = dao_moeda.converteparaReais(valor_inserido, moeda.getArray_moeda(jCBMoedaImpostos.getSelectedIndex() - 1),data_fornec);
                }else{
                    jFTImpostosReais.setText("0.00");
                    jFTValorImpostos.setText("0.00");
                }
                
                if(valor_convertido <= 0 && valor_inserido > 0){
                    jFTImpostosReais.setValue(null);
                    jFTImpostosReais.setText(null);
                    jFTValorImpostos.setValue(null);
                    jFTValorImpostos.setText(null);
                }else{
                    if(valor_convertido >= 999999999){
                        JOptionPane.showMessageDialog(null, "Valor convertido excede o valor máximo!");
                        jFTValorImpostos.setValue(null);
                        jFTValorImpostos.setText(null);
                        jFTImpostosReais.setValue(null);
                        jFTImpostosReais.setText(null);
                        jFTValorImpostos.grabFocus();
                    }else{
                        jFTImpostosReais.setText(String.valueOf(valor_convertido).replace(".", ","));
                    }
                }
            }
        }
    }//GEN-LAST:event_jFTValorImpostosFocusLost

    private void jFTValorFreteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFTValorFreteActionPerformed
       
    }//GEN-LAST:event_jFTValorFreteActionPerformed

    private void jFTValorFreteCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jFTValorFreteCaretUpdate
       
    }//GEN-LAST:event_jFTValorFreteCaretUpdate

    private void jFTValorImpostosCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jFTValorImpostosCaretUpdate
        
       
    }//GEN-LAST:event_jFTValorImpostosCaretUpdate

    private void jCBMoedaImpostosPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jCBMoedaImpostosPopupMenuWillBecomeInvisible
        Timestamp data_fornec = data.stringParaTimeStamp(jFTData.getText());  
        //Verifica se foi selecionado uma moeda
        if(jCBMoedaImpostos.getSelectedIndex() > 0){
            jFTValorImpostos.setEnabled(true);
            //se o campo do valor não for vazio
            if(!jFTValorImpostos.getText().equals("")){
                double valor_inserido;
                double valor_convertido;
                valor_inserido = Double.parseDouble(jFTValorImpostos.getText().replace(".", "").replace(",", "."));
                valor_convertido = dao_moeda.converteparaReais(valor_inserido, moeda.getArray_moeda(jCBMoedaImpostos.getSelectedIndex() - 1),data_fornec);
                
                if(valor_convertido >= 999999999){
                        JOptionPane.showMessageDialog(null, "Valor convertido excede o valor máximo!");
                        jFTValorImpostos.setValue(null);
                        jFTValorImpostos.setText(null);
                        jFTImpostosReais.setValue(null);
                        jFTImpostosReais.setText(null);
                        jFTValorImpostos.grabFocus();
                }else{
                    jFTImpostosReais.setText(String.valueOf(valor_convertido).replace(".", ","));
                }
              
            }
        }else{
            jFTValorImpostos.setEnabled(false);
            jFTValorImpostos.setValue(null);
            jFTImpostosReais.setValue(null);
        }
        
    }//GEN-LAST:event_jCBMoedaImpostosPopupMenuWillBecomeInvisible

    private void jCBMoedaFretePopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jCBMoedaFretePopupMenuWillBecomeInvisible
        Timestamp data_fornec = data.stringParaTimeStamp(jFTData.getText());         
        //se estiver selecionado uma moeda
        if(jCBMoedaFrete.getSelectedIndex() > 0){
            jFTValorFrete.setEnabled(true);
            //se o campo do valor não for vazio
            if(!jFTValorFrete.getText().equals("")){
                double valor_inserido;
                double valor_convertido;
                valor_inserido = Double.parseDouble(jFTValorFrete.getText().replace(".", "").replace(",", "."));
                valor_convertido = dao_moeda.converteparaReais(valor_inserido, moeda.getArray_moeda(jCBMoedaFrete.getSelectedIndex() - 1),data_fornec);
                
                if(valor_convertido >= 999999999){
                    JOptionPane.showMessageDialog(null, "Valor convertido excede o valor máximo!");
                    jFTValorFrete.setValue(null);
                    jFTValorFrete.setText(null);
                    jFTFreteReais.setValue(null);
                    jFTFreteReais.setText(null);
                    jFTValorFrete.grabFocus();
                }else{
                    jFTFreteReais.setText(String.valueOf(valor_convertido).replace(".", ","));
                }
            }
        }else{
            jFTValorFrete.setEnabled(false);
            jFTValorFrete.setValue(null);
            jFTFreteReais.setValue(null);
        }
    }//GEN-LAST:event_jCBMoedaFretePopupMenuWillBecomeInvisible

    private void jBTAddparaProjetoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTAddparaProjetoActionPerformed
        int linha = jTBComponentes.getSelectedRow();     
        Integer exc = Integer.parseInt(jTBComponentes.getValueAt(linha, 10).toString());
        if(linha >= 0){
            if(exc == 0){
                String componente = jTBComponentes.getValueAt(linha, 3).toString();
                int qntd_comp = Integer.parseInt(jTBComponentes.getValueAt(linha, 8).toString());
                int id_componente = Integer.parseInt(jTBComponentes.getValueAt(linha, 2).toString());
                int id_comp_fornec = Integer.parseInt(jTBComponentes.getValueAt(linha, 1).toString());
                comp_fornec.setId_componente(id_componente);
                comp_fornec.setDescricao(componente);
                comp_fornec.setQntd_componente(qntd_comp);
                comp_fornec.setSituacao(situacao);
                comp_fornec.setTabela(jTBComponentes);
                comp_vers_proj.setId_comp_fornec(id_comp_fornec);
                comp_vers_proj.setTabela(jTBComponentesProjetos);
                
                new InterfaceSelecionaProjeto().setVisible(true);
            }else{
                JOptionPane.showMessageDialog(null, "Este componente foi removido, não pode ser adicionado para projetos!");
            }
           
        }else{
            JOptionPane.showMessageDialog(null, "Selecione um componente!");
        }
        
    }//GEN-LAST:event_jBTAddparaProjetoActionPerformed

    private void jTBConsultaCompFornecMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTBConsultaCompFornecMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTBConsultaCompFornecMouseClicked

    private void jTBConsultaFornecimentosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTBConsultaFornecimentosMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jTBConsultaFornecimentosMouseEntered

    private void jFTDataKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFTDataKeyTyped
        String caracteres="0987654321";
        if(!caracteres.contains(evt.getKeyChar()+"")){
            evt.consume();
        }
    }//GEN-LAST:event_jFTDataKeyTyped

    private void jFTDataFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFTDataFocusLost
        String data_fornec;

        //pega a data do campo 
        data_fornec = jFTData.getText();
        //retira mascara do campo para pegar o valor adicionado
        data_fornec = data_fornec.replace("/", "");
        data_fornec = data_fornec.replace(":", "");
        data_fornec = data_fornec.replace(" ", "");

        //Garante que sempre que apagar um conteudo do campo com mascara o mesmo se tornara vazio
        if (data_fornec.equals("")) {
            jFTData.setValue("");
        } else {
            if (valida_campos.ValidaDataHora(jFTData.getText()) == false) {
                jFTData.grabFocus();
            }
        }
    }//GEN-LAST:event_jFTDataFocusLost

    private void jFTDataCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jFTDataCaretUpdate
        
       
    }//GEN-LAST:event_jFTDataCaretUpdate

    private void jFTDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jFTDataMouseClicked
        
    }//GEN-LAST:event_jFTDataMouseClicked

    private void jFTDataInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_jFTDataInputMethodTextChanged
       
    }//GEN-LAST:event_jFTDataInputMethodTextChanged

    private void jFTDataKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFTDataKeyPressed
        jFTFreteReais.setValue(null);
        jFTImpostosReais.setValue(null);
        jFTValorFrete.setValue(null);
        jFTValorImpostos.setValue(null);
        jFTFreteReais.setText(null);
        jFTImpostosReais.setText(null);
        jFTValorFrete.setText(null);
        jFTValorImpostos.setText(null);
    }//GEN-LAST:event_jFTDataKeyPressed

    private void jFTValorFreteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFTValorFreteKeyPressed
      
    }//GEN-LAST:event_jFTValorFreteKeyPressed

    private void jFTValorImpostosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFTValorImpostosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFTValorImpostosActionPerformed

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
            java.util.logging.Logger.getLogger(InterfaceFornecimento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterfaceFornecimento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterfaceFornecimento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterfaceFornecimento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
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
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JFormattedTextField jFTData;
    private javax.swing.JFormattedTextField jFTFreteReais;
    private javax.swing.JFormattedTextField jFTImpostosReais;
    private javax.swing.JFormattedTextField jFTMascaMonetaria;
    private javax.swing.JFormattedTextField jFTValorFrete;
    private javax.swing.JFormattedTextField jFTValorImpostos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPBotoes;
    private javax.swing.JPanel jPFornecimento;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTBComponentes;
    private javax.swing.JTable jTBComponentesProjetos;
    private javax.swing.JTable jTBConsultaCompFornec;
    private javax.swing.JTable jTBConsultaFornecimentos;
    private javax.swing.JTabbedPane jTBFornecimento;
    private javax.swing.JTextField jTFDescrição;
    private javax.swing.JTextField jTFFiltro;
    private javax.swing.JTextField jTFIDFornecimento;
    private javax.swing.JTextField jTFTotalFornecimento;
    // End of variables declaration//GEN-END:variables

    public Fornecimento getFornecimento(){
        
        fornecimento = new Fornecimento();
        
        Date data_atual = new Date(System.currentTimeMillis());
        
        int id_fornecimento = Integer.parseInt(jTFIDFornecimento.getText());
        Double valor_frete = Double.parseDouble(jFTValorFrete.getText().replace(",", "."));
        Double valor_imposto = Double.parseDouble(jFTValorImpostos.getText().replace(",", "."));
        
        fornecimento.setId_fornecimento(id_fornecimento);
        fornecimento.setDescricao(jTFDescrição.getText());
        fornecimento.setId_pessoa(fornecedor.getArray_fornecedor(jCBFornecedor.getSelectedIndex() - 1));
        fornecimento.setId_moeda_frete(moeda.getArray_moeda(jCBMoedaFrete.getSelectedIndex() - 1));
        fornecimento.setValor_frete(valor_frete);
        fornecimento.setId_moeda_imp(moeda.getArray_moeda(jCBMoedaImpostos.getSelectedIndex() - 1));
        fornecimento.setValor_impostos(valor_imposto);
        fornecimento.setData_cadastro(data.stringParaTimeStamp(jFTData.getText()));
        fornecimento.setData_alter(data_atual);

        return fornecimento;
    }
    
     public ComponenteFornecimento getCompFornec(){
        
        comp_fornec = new ComponenteFornecimento();
        
        Date data_atual = new Date(System.currentTimeMillis());
        
        int id_fornecimento = Integer.parseInt(jTFIDFornecimento.getText());

        comp_fornec.setId_fornecimento(id_fornecimento);
        comp_fornec.setData_alter(data_atual);
        comp_fornec.setTabela(jTBComponentes);

        return comp_fornec;
    }
     
    public ComponenteVersaoProjeto getCompVersProj(){

       comp_vers_proj = new ComponenteVersaoProjeto();

       Date data_atual = new Date(System.currentTimeMillis());

       int id_fornecimento = Integer.parseInt(jTFIDFornecimento.getText());

       comp_vers_proj.setId_fornecimento(id_fornecimento);
       comp_vers_proj.setData_alter(data_atual);
       comp_vers_proj.setTabela(jTBComponentesProjetos);

       return comp_vers_proj;
   }
    
    public void setcompFornecimento(){
         jTFIDFornecimento.setText(String.valueOf(fornecimento.getId_fornecimento()));
         jTFDescrição.setText(fornecimento.getDescricao());
         jCBFornecedor.setSelectedItem(fornecimento.getDs_pessoa());
         jCBMoedaFrete.setSelectedItem(fornecimento.getDs_moeda_frete());
         jFTValorFrete.setText(String.valueOf(fornecimento.getValor_frete()).replace(".", ","));
         jFTFreteReais.setText(String.valueOf(dao_moeda.converteparaReais(fornecimento.getValor_frete(), fornecimento.getId_moeda_frete(), fornecimento.getData_cadastro())).replace(".", ","));
         jCBMoedaImpostos.setSelectedItem(fornecimento.getDs_moeda_imp());
         jFTValorImpostos.setText(String.valueOf(fornecimento.getValor_impostos()).replace(".", ","));
         jFTImpostosReais.setText(String.valueOf(dao_moeda.converteparaReais(fornecimento.getValor_impostos(), fornecimento.getId_moeda_imp(), fornecimento.getData_cadastro())).replace(".", ","));
         jFTData.setText(String.valueOf(data.organizaData(fornecimento.getData_cadastro())));
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
        if (table == jTBComponentes) {
            Integer exc = Integer.parseInt(table.getValueAt(row, 10).toString());
            Integer id_comp = Integer.parseInt(table.getValueAt(row, 2).toString());
            //se não for um item excluido, deixa manipular valores
            if(exc == 0){
                
                //Se o valor novo não for vazio
                if(!newValue.toString().replace(" ", "").equals("")){

                    if(column == 7 ){
                        try { 
                            //verifica se o valor setado é um valor double
                            Integer qntd = Integer.parseInt(table.getValueAt(row, column).toString());
                            Integer restante = Integer.parseInt(table.getValueAt(row, 8).toString());
                            Double valor_unit = Double.parseDouble(table.getValueAt(row, 4).toString().replace(".", "").replace(",", "."));
                            Double total;
                            if(qntd > 0){
                                //recalcula o total
                                total = valor_unit * qntd;
                                //recalcula o restante
                                if(Integer.parseInt(newValue.toString()) > Integer.parseInt(oldValue.toString())){
                                    restante = restante + (Integer.parseInt(newValue.toString()) - Integer.parseInt(oldValue.toString()));
                                }else{
                                    restante = restante - (Integer.parseInt(oldValue.toString()) - Integer.parseInt(newValue.toString()));
                                }
                                if(restante > -1){
                                    table.setValueAt(restante, row, 8);
                                    table.setValueAt(conversao.doubleParaObjectDecimalFormat(total),row, 9);
                                }else{
                                    table.setValueAt(oldValue,row, column);
                                    JOptionPane.showMessageDialog(null, "A quantidade informada está abaixo da quantidade atribuida para os projetos!");
                                }
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

                    if(column == 4){

                         try { 
                            //verifica se o componente possui composição, se sim, não deixa alterar o valor
                            componente.setId_componente(id_comp);
                            if(dao_componente.verificaExisteComposicao(componente) == false){
                                //verifica se o valor setado é um valor double
                                Integer qntd = Integer.parseInt(table.getValueAt(row, 7).toString());
                                Double valor_unit = Double.parseDouble(table.getValueAt(row, column).toString().replace(".", "").replace(",", "."));
                                Double total;
                                if(valor_unit > 0.00){
                                    //table.setValueAt(qntd, row, column);
                                    //recalcula o total
                                    total = qntd * valor_unit;
                                    table.setValueAt(conversao.doubleParaObjectDecimalFormat(total), row, 9);
                                }else{
                                    JOptionPane.showMessageDialog(null, "O Valor unitário deve ser maior que zero!");
                                    table.setValueAt(oldValue, row, column);
                                }
                            }else{
                                JOptionPane.showMessageDialog(null, "Impossível aterar o valor unitário!\nEste componente é composto por uma composição, o valor unitário é calculado referente ao valor de sua composição");  
                                table.setValueAt(oldValue, row, column);
                            }
                        } catch (Exception e) {
                            //se não for double, emite a mensagem e retorna para o valor que estava
                            JOptionPane.showMessageDialog(null, "Informe um valor numérico para valor unitário!");  
                            table.setValueAt(oldValue, row, column);
                        }

                    }
                }else
                {
                    //seta na jtable o valor que estava antes de apagar
                    table.setValueAt(oldValue, row, column);
                }
            }
          
  
        }else if (table == jTBComponentesProjetos) {
            Integer restante = 0;
            Integer qntd_add = 0;
            Integer qntd_remov = 0;
            Integer qntd_no_projeto = 0;
            Integer id_componente = Integer.parseInt(table.getValueAt(row, 7).toString());
            Integer exc = Integer.parseInt(table.getValueAt(row, 10).toString());
            Integer id_comp_versao = Integer.parseInt(table.getValueAt(row, 1).toString());
            
            //retorna a quantidade utiliza no projeto do componente
            comp_vers_proj.setId_comp_versao(id_comp_versao);
            qntd_no_projeto = dao_comp_vers.retornaQntdNoProjeto(comp_vers_proj);
            
            //se não for um item excluido, deixa manipular valores
            if(exc == 0){
            
                //Se o valor novo não for vazio 
                if(!newValue.toString().replace(" ", "").equals("")){
                    if(column == 9 ){
                        try {
                            Integer qntd = Integer.parseInt(table.getValueAt(row, column).toString());
                            if(qntd > 0){
                                if(qntd > qntd_no_projeto){
                                    
                                    //percorre jtable dos componentes fornecidos para saber a quantidade restante de cada
                                    for(int i = 0; i < jTBComponentes.getRowCount(); i++){
                                        //encontrou o componente que esta sendo alterado a quantidade na jtable de fornecidos?
                                        if(Integer.parseInt(jTBComponentes.getValueAt(i, 2).toString()) == id_componente){
                                            //recupera a quantidade restante do mesmo
                                            restante = Integer.parseInt(jTBComponentes.getValueAt(i, 8).toString());
                                            //calcula a quantiade que foi adicionada para o componente
                                            if(Integer.parseInt(newValue.toString()) > Integer.parseInt(oldValue.toString())){
                                                qntd_add = Integer.parseInt(newValue.toString()) - Integer.parseInt(oldValue.toString());
                                                //quantidade adicionada é maior que o restante ?
                                                if(qntd_add > restante){
                                                    JOptionPane.showMessageDialog(null, "Quantidade adicionada excede a quantidade restante!");
                                                    table.setValueAt(oldValue, row, column);
                                                }else{
                                                    jTBComponentes.setValueAt(restante - qntd_add, i, 8);
                                                }
                                                break;
                                                // foi decrementado a quantidade
                                            }else{
                                            qntd_remov = Integer.parseInt(oldValue.toString()) - Integer.parseInt(newValue.toString());
                                            jTBComponentes.setValueAt(restante + qntd_remov, i, 8);
                                            }
                                        }
                                    }
                                }else{
                                    JOptionPane.showMessageDialog(null, "Quantidade informada é menor que a quantidade que já está sendo utilizada no projeto!");
                                    table.setValueAt(oldValue,row, column);
                                }
                            }else{
                                JOptionPane.showMessageDialog(null, "Quantidade deve ser maior que zero!");
                                table.setValueAt(oldValue,row, column);
                            }
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Informe um valor numérico para quantidade!");  
                        }
                    }
                }
            }
        }
        Jtable.ajustarColunasDaTabela(jTBComponentes);
        Jtable.ajustarColunasDaTabela(jTBComponentesProjetos);
    }

    class TableCellEditorAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            TableCellListener tbListener = (TableCellListener) e.getSource();
            
            onCellEditor(tbListener.getTable(), tbListener.getColumn(), tbListener.getRow(), tbListener.getOldValue(), tbListener.getNewValue());
        }
    }
}

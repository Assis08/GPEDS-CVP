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
import br.edu.GPEDSCVP.classe.Fornecimento;
import br.edu.GPEDSCVP.classe.Moeda;
import br.edu.GPEDSCVP.classe.Permissao;
import br.edu.GPEDSCVP.classe.Projeto;
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
import br.edu.GPEDSCVP.dao.daoProjeto;
import br.edu.GPEDSCVP.dao.daoTela;
import br.edu.GPEDSCVP.dao.daoVersaoProjeto;
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
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Willys
 */
public class InterfaceVersaoProjeto extends javax.swing.JFrame {

    Componente componente;
    Projeto projeto;
    VersaoProjeto versao;
    Fornecimento fornecimento;
    ComponenteFornecimento comp_fornec;
    ComponenteVersaoProjeto comp_vers_proj;
    Moeda moeda;
    daoMoeda dao_moeda;
    daoProjeto dao_projeto;
    daoVersaoProjeto dao_versao;
    daoFornecimento dao_fornecimento;
    daoComponentesFornecimento dao_comp_fornec;
    daoComponenteVersaoProjeto dao_comp_vers;
    ComposicaoComponente composicao;
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
    int[] array_versoes;
    int[] array_projetos;
    int situacao = Rotinas.PADRAO;
    
    public InterfaceVersaoProjeto() {
        initComponents();
        
        componente = new Componente();
        projeto = new Projeto();
        fornecimento = new Fornecimento();
        versao = new VersaoProjeto();
        dao_fornecimento = new daoFornecimento();
        dao_comp_fornec = new daoComponentesFornecimento();
        dao_comp_vers = new daoComponenteVersaoProjeto();
        dao_projeto = new daoProjeto();
        dao_versao = new daoVersaoProjeto();
        comp_fornec = new ComponenteFornecimento();
        comp_vers_proj = new ComponenteVersaoProjeto();
        composicao = new ComposicaoComponente();
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
        
        //desabilita campos da tela de projeto
        valida_campos.desabilitaCampos(jPVersaoProjeto);
        
        //Adiciona barra de rolagem obs: obrigatorio para conseguir dimensionar automatico as colunas da jtable
        jTBComponentesEletronicos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jTBComponentesMecanicos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        //Cria renderer para as Jtable  
        TableCellRenderer renderer = new InterfaceVersaoProjeto.EvenOddRenderer();
        jTBComponentesEletronicos.setDefaultRenderer(Object.class, renderer);
        jTBComponentesMecanicos.setDefaultRenderer(Object.class, renderer);
        
        //implementa Listener para edição da jtable
        new TableCellListener(jTBComponentesEletronicos, new InterfaceVersaoProjeto.TableCellEditorAction());
        new TableCellListener(jTBComponentesMecanicos, new InterfaceVersaoProjeto.TableCellEditorAction());
        
        //Define a situação como inicial para habilitar os botoes utilizados apenas quando inicia a tela
        situacao = Rotinas.INICIAL;

        //habilita os botoes utilizados na inicialização da tela
        valida_botoes.ValidaEstado(jPBotoes, situacao);
        
        //atualiza dados do usuario logado
        dao_acesso.retornaUsuarioLogado(acesso);
        
        // carrega dados nas combobox
        dao_projeto.consultaGeral(projeto);
        //Preenche dados nas ComboBox de projetos
        array_projetos = combo.PreencherCombo(jCBProjeto, "descricao", projeto.getRetorno(), "id_projeto");
        //seta no array da classe de projetos a lista de projetos listadas na combo
        projeto.setArray_projetos(array_projetos);
        
        jCBComercializado.addItem("Selecione");
        jCBComercializado.addItem("Sim");
        jCBComercializado.addItem("Não");
        
        jCBCertificacao.addItem("Selecione");
        jCBCertificacao.addItem("Sim");
        jCBCertificacao.addItem("Não");
        
        limpa_dados_versao();
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
        jPVersaoProjeto = new javax.swing.JPanel();
        jPBotoes = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTBComponentesEletronicos = new javax.swing.JTable();
        jBTAddComposicao = new javax.swing.JButton();
        jBTRemoveComposicao = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jBTRemoveFornecedores = new javax.swing.JButton();
        jBTAddFornecedor = new javax.swing.JButton();
        jFTTotalEletronico = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        jFTTotalMecanico = new javax.swing.JFormattedTextField();
        jLabel8 = new javax.swing.JLabel();
        jFTTotalComponentes = new javax.swing.JFormattedTextField();
        jLabel9 = new javax.swing.JLabel();
        jPDadosVersao = new javax.swing.JPanel();
        jTFLote = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jCBComercializado = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jCBVersao = new javax.swing.JComboBox();
        jLabel13 = new javax.swing.JLabel();
        jCBProjeto = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jFTData = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        jTFIDVersao = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jBTNovoEstado = new javax.swing.JButton();
        jCBCertificacao = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTBComponentesMecanicos = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Versões de Projetos");
        setResizable(false);

        jTBComponente.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTBComponenteStateChanged(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/edu/GPEDSCVP/icones/Botoes_Site_5751_Knob_Remove_Red.png"))); // NOI18N
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

        jButton1.setIcon(new javax.swing.ImageIcon("C:\\Users\\rafa\\Documents\\GPEDS-CVP\\src\\br\\edu\\GPEDSCVP\\icones\\Botoes_Site_5752_Knob_Add.png")); // NOI18N
        jButton1.setText("Novo");
        jButton1.setToolTipText("Cidade");
        jButton1.setName("ID_CIDADE"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
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
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPBotoesLayout.setVerticalGroup(
            jPBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPBotoesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTBComponentesEletronicos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sel", "ID Componentes Versão", "ID Componente", "Componente", "ID Moeda", "Moeda", "Valor Unit", "Quantidade", "Total R$", "exc", "old_qntd"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, false, true, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTBComponentesEletronicos.setName("jTBComponentesEletronicos"); // NOI18N
        jTBComponentesEletronicos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTBComponentesEletronicosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTBComponentesEletronicos);
        if (jTBComponentesEletronicos.getColumnModel().getColumnCount() > 0) {
            jTBComponentesEletronicos.getColumnModel().getColumn(9).setMinWidth(0);
            jTBComponentesEletronicos.getColumnModel().getColumn(9).setPreferredWidth(0);
            jTBComponentesEletronicos.getColumnModel().getColumn(9).setMaxWidth(0);
        }

        jBTAddComposicao.setIcon(new javax.swing.ImageIcon("C:\\Users\\rafa\\Documents\\GPEDS-CVP\\src\\br\\edu\\GPEDSCVP\\icones\\Botoes_Site_5752_Knob_Add.png")); // NOI18N
        jBTAddComposicao.setName("descricao"); // NOI18N
        jBTAddComposicao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTAddComposicaoActionPerformed(evt);
            }
        });

        jBTRemoveComposicao.setIcon(new javax.swing.ImageIcon("C:\\Users\\rafa\\Documents\\GPEDS-CVP\\src\\br\\edu\\GPEDSCVP\\icones\\Botoes_Site_5751_Knob_Remove_Red.png")); // NOI18N
        jBTRemoveComposicao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTRemoveComposicaoActionPerformed(evt);
            }
        });

        jLabel10.setText("Componentes Mecânicos:");

        jBTRemoveFornecedores.setIcon(new javax.swing.ImageIcon("C:\\Users\\rafa\\Documents\\GPEDS-CVP\\src\\br\\edu\\GPEDSCVP\\icones\\Botoes_Site_5751_Knob_Remove_Red.png")); // NOI18N
        jBTRemoveFornecedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTRemoveFornecedoresActionPerformed(evt);
            }
        });

        jBTAddFornecedor.setIcon(new javax.swing.ImageIcon("C:\\Users\\rafa\\Documents\\GPEDS-CVP\\src\\br\\edu\\GPEDSCVP\\icones\\Botoes_Site_5752_Knob_Add.png")); // NOI18N
        jBTAddFornecedor.setName("descricao"); // NOI18N
        jBTAddFornecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTAddFornecedorActionPerformed(evt);
            }
        });

        jFTTotalEletronico.setEditable(false);
        jFTTotalEletronico.setName("TotalEletronicos"); // NOI18N

        jLabel4.setText("Total R$:");

        jFTTotalMecanico.setEditable(false);
        jFTTotalMecanico.setName("TotalMecanicos"); // NOI18N

        jLabel8.setText("Total R$:");

        jFTTotalComponentes.setEditable(false);
        jFTTotalComponentes.setName("TotalComponentes"); // NOI18N

        jLabel9.setText("Total Componentes R$:");

        jTFLote.setToolTipText("Lote");
        jTFLote.setName("lote"); // NOI18N
        jTFLote.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTFLoteKeyTyped(evt);
            }
        });

        jLabel6.setText("Lote:");

        jCBComercializado.setToolTipText("Comercializado");
        jCBComercializado.setName("comercializado"); // NOI18N
        jCBComercializado.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                jCBComercializadoPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
                jCBComercializadoPopupMenuWillBecomeVisible(evt);
            }
        });

        jLabel3.setText("Comercializado:");

        jCBVersao.setToolTipText("Versão");
        jCBVersao.setName("versao"); // NOI18N
        jCBVersao.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                jCBVersaoPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
                jCBVersaoPopupMenuWillBecomeVisible(evt);
            }
        });

        jLabel13.setText("Versão");

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

        jLabel5.setText("Projeto:");

        jFTData.setEditable(false);
        jFTData.setToolTipText("Data");
        jFTData.setName("data_cadastro"); // NOI18N

        jLabel7.setText("Data:");

        jTFIDVersao.setEditable(false);
        jTFIDVersao.setName("cod_vers_proj"); // NOI18N

        jLabel1.setText("ID Versão:");

        jBTNovoEstado.setIcon(new javax.swing.ImageIcon("C:\\Users\\rafa\\Documents\\GPEDS-CVP\\src\\br\\edu\\GPEDSCVP\\icones\\add.png")); // NOI18N
        jBTNovoEstado.setText("Novo");
        jBTNovoEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTNovoEstadoActionPerformed(evt);
            }
        });

        jCBCertificacao.setToolTipText("Certificação");
        jCBCertificacao.setName("certificacao"); // NOI18N

        jLabel11.setText("Certificação:");

        javax.swing.GroupLayout jPDadosVersaoLayout = new javax.swing.GroupLayout(jPDadosVersao);
        jPDadosVersao.setLayout(jPDadosVersaoLayout);
        jPDadosVersaoLayout.setHorizontalGroup(
            jPDadosVersaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPDadosVersaoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPDadosVersaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTFIDVersao, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(18, 18, 18)
                .addGroup(jPDadosVersaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPDadosVersaoLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBTNovoEstado))
                    .addComponent(jCBProjeto, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPDadosVersaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(jCBVersao, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPDadosVersaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCBComercializado, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPDadosVersaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTFLote, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPDadosVersaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPDadosVersaoLayout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFTData, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPDadosVersaoLayout.createSequentialGroup()
                        .addComponent(jCBCertificacao, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPDadosVersaoLayout.setVerticalGroup(
            jPDadosVersaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPDadosVersaoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPDadosVersaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPDadosVersaoLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCBComercializado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPDadosVersaoLayout.createSequentialGroup()
                        .addGroup(jPDadosVersaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPDadosVersaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTFLote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCBCertificacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPDadosVersaoLayout.createSequentialGroup()
                        .addGroup(jPDadosVersaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPDadosVersaoLayout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(jPDadosVersaoLayout.createSequentialGroup()
                                .addComponent(jBTNovoEstado)
                                .addGap(6, 6, 6)))
                        .addComponent(jCBProjeto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPDadosVersaoLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFIDVersao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jCBVersao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPDadosVersaoLayout.createSequentialGroup()
                        .addGroup(jPDadosVersaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel13)
                            .addGroup(jPDadosVersaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jFTData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7)))
                        .addGap(26, 26, 26)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setText("Componentes Eletrônicos:");

        jTBComponentesMecanicos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sel", "ID Componentes Versão", "ID Componente", "Componente", "ID Moeda", "Moeda", "Valor Unit", "Quantidade", "Total R$", "exc", "old_qntd"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, false, true, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTBComponentesMecanicos.setName("jTBComponentesMecanicos"); // NOI18N
        jTBComponentesMecanicos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTBComponentesMecanicosMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTBComponentesMecanicos);
        if (jTBComponentesMecanicos.getColumnModel().getColumnCount() > 0) {
            jTBComponentesMecanicos.getColumnModel().getColumn(9).setMinWidth(0);
            jTBComponentesMecanicos.getColumnModel().getColumn(9).setPreferredWidth(0);
            jTBComponentesMecanicos.getColumnModel().getColumn(9).setMaxWidth(0);
        }

        javax.swing.GroupLayout jPVersaoProjetoLayout = new javax.swing.GroupLayout(jPVersaoProjeto);
        jPVersaoProjeto.setLayout(jPVersaoProjetoLayout);
        jPVersaoProjetoLayout.setHorizontalGroup(
            jPVersaoProjetoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPVersaoProjetoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPVersaoProjetoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPVersaoProjetoLayout.createSequentialGroup()
                        .addComponent(jPBotoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(64, 64, 64)
                        .addGroup(jPVersaoProjetoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jFTTotalComponentes, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)))
                    .addComponent(jLabel2)
                    .addGroup(jPVersaoProjetoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPDadosVersao, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPVersaoProjetoLayout.createSequentialGroup()
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 711, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPVersaoProjetoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jBTAddComposicao, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jFTTotalEletronico, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4)
                                .addComponent(jBTRemoveComposicao, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jLabel10)
                    .addGroup(jPVersaoProjetoLayout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 711, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPVersaoProjetoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jFTTotalMecanico, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPVersaoProjetoLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel8))
                            .addComponent(jBTRemoveFornecedores, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBTAddFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPVersaoProjetoLayout.setVerticalGroup(
            jPVersaoProjetoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPVersaoProjetoLayout.createSequentialGroup()
                .addComponent(jPDadosVersao, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGroup(jPVersaoProjetoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPVersaoProjetoLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPVersaoProjetoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPVersaoProjetoLayout.createSequentialGroup()
                                .addComponent(jBTAddComposicao, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(115, 115, 115)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jFTTotalEletronico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPVersaoProjetoLayout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jBTRemoveComposicao, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPVersaoProjetoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPVersaoProjetoLayout.createSequentialGroup()
                        .addComponent(jBTAddFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBTRemoveFornecedores, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(77, 77, 77)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFTTotalMecanico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPVersaoProjetoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPBotoes, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPVersaoProjetoLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFTTotalComponentes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jTBComponente.addTab("Cadastro", jPVersaoProjeto);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTBComponente, javax.swing.GroupLayout.PREFERRED_SIZE, 825, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTBComponente, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        setSize(new java.awt.Dimension(841, 653));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
      /*
        //se naõ for gerente
        if (acesso.getIn_gerente() == 0) {
            //retorna as permissoes de acesso do usuario
            dao_permissao.retornaDadosPermissao(acesso, permissao);
        }
        //Verifica se o usuario possui permissao para acessar essa tela
        if (valida_acesso.verificaAcesso("excluir", acesso, permissao) == true) {

            //Seta o id da pessoa para exclusão
            componente.setId_componente(Integer.parseInt(jTFIDVersao.getText()));

            if (mensagem.ValidaMensagem("Deseja realmente excluir o registro ?") == 0) {
                //Inativa componente
                dao_componente.inativaComponente(componente);
                JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
                //Limpa os campos da tela pessoa
                valida_campos.LimparCampos(jPVersaoProjeto);
                valida_campos.LimparJtable(jTBComponentesEletronicos);
                valida_campos.LimparJtable(jTBComponentesMecanicos);
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
        */
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5MouseExited

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        //Se for inclusão
        if (situacao == Rotinas.INCLUIR) {
            if (valida_campos.validacamposobrigatorios(jPDadosVersao, "Versao_Projeto") == 0) {
                try {
                    //pega dados da versão na tela
                    getVersaoProjeto();
                    //inclui componente
                    if(dao_versao.incluir(versao) == true){
                        
                    JOptionPane.showMessageDialog(null, "Salvo com Sucesso");
/*
                        getComposicao();
                        getFornecedores();
                        dao_componente.gravarComposicao(composicao);
                        dao_componente.gravarFornecedores(fornec_comp);

                        //se ocorreu tudo bem na inclusão
                        JOptionPane.showMessageDialog(null, "Salvo com Sucesso");
                        //limpa campos
                        valida_campos.LimparCampos(jPVersaoProjeto);
                        valida_campos.LimparJtable(jTBComponentesEletronicos);
                        valida_campos.LimparJtable(jTBComponentesMecanicos);
                        valida_campos.LimparJtable(jTBConsultaComponentes);
                        valida_campos.LimparJtable(jTBConsultaFornecedores);
                        valida_campos.LimparJtable(jTBConsultaComposicao);
                        valida_campos.LimparJtable(jTBContatoFornecedores);

                        //Define a situação como incluir para habilitar os botoes utilizados apenas na inclusão
                        situacao = Rotinas.INICIAL;

                        //habilita os botoes utilizados na inclusão e desabilita os restantes
                        valida_botoes.ValidaEstado(jPBotoes, situacao);

                        //desabilita campos
                        valida_campos.desabilitaCampos(jPVersaoProjeto);
                        */
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Falha ao manipular versão");
                }

            }
        }else if(situacao == Rotinas.ALTERAR) {
            //pega dados do material na tela
            if (valida_campos.validacamposobrigatorios(jPDadosVersao, "Versao_Projeto") == 0) {
                try {
                    getVersaoProjeto();
                    getCompVersaoProj();
                    //alterar componente
                    if(dao_versao.alterar(versao)== true){
                        
                        dao_versao.SalvarCompNoProjeto(comp_vers_proj,jTBComponentesEletronicos); 
                        dao_versao.SalvarCompNoProjeto(comp_vers_proj,jTBComponentesMecanicos); 
                        
                        JOptionPane.showMessageDialog(null, "alterado com Sucesso");
                    /*    //altera composição
                        getComposicao();
                        getFornecedores();
                        dao_composicao.alterarComposicao(composicao);
                        dao_fornec_comp.alterarFornecedores(fornec_comp);

                       
                        //limpa campos
                        valida_campos.LimparCampos(jPVersaoProjeto);
                        valida_campos.LimparJtable(jTBComponentesEletronicos);
                        valida_campos.LimparJtable(jTBComponentesMecanicos);
                        valida_campos.LimparJtable(jTBConsultaComponentes);
                        valida_campos.LimparJtable(jTBConsultaFornecedores);
                        valida_campos.LimparJtable(jTBConsultaComposicao);
                        valida_campos.LimparJtable(jTBContatoFornecedores);

                        //Define a situação como incluir para habilitar os botoes utilizados apenas na inclusão
                        situacao = Rotinas.INICIAL;

                        //habilita os botoes utilizados na inclusão e desabilita os restantes
                        valida_botoes.ValidaEstado(jPBotoes, situacao);

                        //desabilita campos
                        valida_campos.desabilitaCampos(jPVersaoProjeto);
                        */
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Falha ao alterar versão");
                }
            }
        }
        
        
        Integer id_projeto = projeto.getArray_projetos(jCBProjeto.getSelectedIndex() - 1);
        Double versao_projeto = Double.parseDouble(jCBVersao.getSelectedItem().toString());
        versao.setId_projeto(id_projeto);
        versao.setVersao(versao_projeto);
        dao_versao.retornardados(versao);
            
       
        //Lista componentes eletrônicos da versão
        comp_vers_proj.setId_projeto(id_projeto);
        comp_vers_proj.setVersao(versao_projeto);
        comp_vers_proj.setCod_vers_projeto(Integer.parseInt(jTFIDVersao.getText()));

        comp_vers_proj.setTipo("E");

        dao_comp_vers.consultaCompVersaoProjeto(comp_vers_proj);

        //Preenche na JTABLE de componentes eletrônicos todos componentes eletronicos sendo utilizados nesta versão do projeto
        Jtable.PreencherJtableGenerico(jTBComponentesEletronicos, new String[]{"null","id_comp_versao","id_componente","componente.descricao","id_moeda","unidade","valor_unit","qntd_no_projeto","total","false","qntd_no_projeto"}, comp_vers_proj.getRetorno());

        //ajusta largura das colunas
        Jtable.ajustarColunasDaTabela(jTBComponentesEletronicos);

        //converte os totais dos compoenntes em reais pois o banco retorna o valor calculado referente a moeda que o mesmo foi cadastrado
        dao_comp_vers.converteTotalComp(comp_vers_proj, jTBComponentesEletronicos);
        //seta o total de componentes eletronicos
        jFTTotalEletronico.setText(String.valueOf(conversao.doubleParaObjectDecimalFormat(dao_comp_vers.calcula_total_componentes(jTBComponentesEletronicos))));

        //Lista componentes mecânicos da versão
        comp_vers_proj.setTipo("M");

        dao_comp_vers.consultaCompVersaoProjeto(comp_vers_proj);

        //Preenche na JTABLE de componentes eletrônicos todos componentes eletronicos sendo utilizados nesta versão do projeto
        Jtable.PreencherJtableGenerico(jTBComponentesMecanicos, new String[]{"null","id_comp_versao","id_componente","componente.descricao","id_moeda","unidade","valor_unit","qntd_no_projeto","total","false","qntd_no_projeto"}, comp_vers_proj.getRetorno());

        //ajusta largura das colunas
        Jtable.ajustarColunasDaTabela(jTBComponentesMecanicos);

        //converte os totais dos compoenntes em reais pois o banco retorna o valor calculado referente a moeda que o mesmo foi cadastrado
        dao_comp_vers.converteTotalComp(comp_vers_proj,jTBComponentesMecanicos);
        //seta o total de componentes eletronicos
        jFTTotalMecanico.setText(String.valueOf(conversao.doubleParaObjectDecimalFormat(dao_comp_vers.calcula_total_componentes(jTBComponentesMecanicos))));
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        UltimaSequencia ultima;

        //se não for gerente
        if (acesso.getIn_gerente() == 0) {
            //retorna as permissoes de acesso do usuario
            dao_permissao.retornaDadosPermissao(acesso, permissao);
        }

        //Verifica se o usuario possui permissao para incluir registros nessa tela
        if (valida_acesso.verificaAcesso("inserir", acesso, permissao) == true) {

            try {
                
                if (mensagem.ValidaMensagem("Deseja criar uma nova versão deste projeto ?") == 0) {

                    if(jCBProjeto.getSelectedIndex() > 0){

                        Integer id_projeto = projeto.getArray_projetos(jCBProjeto.getSelectedIndex() - 1);
                        projeto.setId_projeto(id_projeto);
                        versao.setPrim_opcao_versao(dao_versao.criaCodigoVersao(versao, "PRIMEIRA_CASA"));
                        versao.setSegun_opcao_versao(dao_versao.criaCodigoVersao(versao, "SEGUNDA_CASA"));

                        InterfaceSelecionaVersao dialog = new InterfaceSelecionaVersao(this,true); //= new InterfaceSelecionaVersao();   
                        dialog.setModal(true);  
                        dialog.setVisible(true); 
                        dialog = null; 

                        if(VersaoProjeto.getVersao_selecionada() != null){

                            //Gera id sequencial
                            ultima = new UltimaSequencia();
                            int sequencia = (Integer) (ultima.ultimasequencia("VERSAO_PROJETO", "COD_VERS_PROJETO"));
                            //seta id da nova versão no campo ID
                            jTFIDVersao.setText(Integer.toString(sequencia));

                            //Seta a data atual no campo data
                            jFTData.setEnabled(true);
                            jFTData.setText(data.DataAtual());

                            jCBVersao.removeAllItems();
                            jCBVersao.addItem("Selecione item");  
                            jCBVersao.addItem(String.valueOf(versao.getVersao_selecionada())); 
                            jCBVersao.setSelectedIndex(1);
                            versao.setVersao_selecionada(null);

                            jCBCertificacao.setEnabled(true);
                            jCBComercializado.setEnabled(true);

                            //Define a situação como incluir para habilitar os botoes utilizados apenas na inclusão
                            situacao = Rotinas.INCLUIR;

                            //habilita campos da tela
                            valida_campos.habilitaCampos(jPVersaoProjeto);

                            //habilita os botoes utilizados na inclusão e desabilita os restantes
                            valida_botoes.ValidaEstado(jPBotoes, situacao);
                        }

                    }else{
                        JOptionPane.showMessageDialog(null, "Selecione um Projeto!");
                    }
                       
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Falha ao iniciar a inserção de componente");
            }
        }else{
            JOptionPane.showMessageDialog(null, "Voce não possui permissões para incluir componentes no sistema");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jCBComercializadoPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jCBComercializadoPopupMenuWillBecomeInvisible
        if(jCBComercializado.getSelectedItem().equals("Sim")){
            jTFLote.setText("");
            jTFLote.setEnabled(true);
        }else{
            jTFLote.setText("");
            jTFLote.setEnabled(false);
        }
    }//GEN-LAST:event_jCBComercializadoPopupMenuWillBecomeInvisible

    private void jCBComercializadoPopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jCBComercializadoPopupMenuWillBecomeVisible
       
    }//GEN-LAST:event_jCBComercializadoPopupMenuWillBecomeVisible

    private void jCBProjetoPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jCBProjetoPopupMenuWillBecomeInvisible
       
        if(jCBProjeto.getSelectedIndex() > 0){
          
            versao.setId_projeto(projeto.getArray_projetos(jCBProjeto.getSelectedIndex() - 1));
            //consulta versões para preencher na combobox de versões
            dao_versao.consultaCodigo(versao);
            array_versoes = combo.PreencherCombo(jCBVersao, "versao", versao.getRetorno(), "cod_vers_projeto");
            //seta no array da classe de versoes a lista de versoes listadas na combo
            versao.setArray_versoes(array_versoes);
            
            jTFIDVersao.setText("");
            
            situacao = Rotinas.INICIAL;
            
            //habilita os botoes utilizados na inclusão e desabilita os restantes
            valida_botoes.ValidaEstado(jPBotoes, situacao);

       }else{
            limpa_dados_versao();
            jCBVersao.removeAllItems();
            valida_campos.desabilitaCampos(jPVersaoProjeto);
       }
    }//GEN-LAST:event_jCBProjetoPopupMenuWillBecomeInvisible

    private void jCBProjetoPopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jCBProjetoPopupMenuWillBecomeVisible
        // carrega dados nas combobox
        dao_projeto.consultaGeral(projeto);
        //Preenche dados nas ComboBox de projetos
        array_projetos = combo.PreencherCombo(jCBProjeto, "descricao", projeto.getRetorno(), "id_projeto");
        //seta no array da classe de projetos a lista de projetos listadas na combo
        projeto.setArray_projetos(array_projetos);
    }//GEN-LAST:event_jCBProjetoPopupMenuWillBecomeVisible

    private void jTBComponentesEletronicosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTBComponentesEletronicosMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTBComponentesEletronicosMouseClicked

    private void jBTAddComposicaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTAddComposicaoActionPerformed
        comp_vers_proj.setId_projeto(projeto.getArray_projetos(jCBProjeto.getSelectedIndex() - 1));
        comp_vers_proj.setVersao(Double.parseDouble(jCBVersao.getSelectedItem().toString()));
        comp_vers_proj.setTipo("E");
        comp_vers_proj.setTabela(jTBComponentesEletronicos);
        comp_vers_proj.setField_total_eletronicos(jFTTotalEletronico);
        comp_vers_proj.setField_total_mecanicos(jFTTotalMecanico);
        comp_vers_proj.setField_total_comp(jFTTotalComponentes);
        new InterfaceAddComponentesVersao().setVisible(true);
    }//GEN-LAST:event_jBTAddComposicaoActionPerformed

    private void jBTRemoveComposicaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTRemoveComposicaoActionPerformed
        if (valida_campos.VerificaJtable(jTBComponentesEletronicos) == 1) {
            int linha = jTBComponentesEletronicos.getSelectedRow();
            Integer exc = Integer.parseInt(jTBComponentesEletronicos.getValueAt(linha, 9).toString());
            //se não for um item removido
            if (exc == 0) {
                
                Double total_componentes = Double.parseDouble(jFTTotalComponentes.getText().replace(".", "").replace(",", "."));
                Double total_eletronicos = Double.parseDouble(jFTTotalEletronico.getText().replace(".", "").replace(",", "."));
                Double qntd_remover = Double.parseDouble(jTBComponentesEletronicos.getValueAt(linha, 7).toString().replace(".", "").replace(",", "."));
                
                //remove itens e atualiza os totais
                dao_comp_vers.removeItensAtulizaTotal(jTBComponentesEletronicos,situacao, jFTTotalEletronico,jFTTotalComponentes);

            }else{
                JOptionPane.showMessageDialog(null, "Item já removido");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Não possui componentes para remover");
        }
    }//GEN-LAST:event_jBTRemoveComposicaoActionPerformed

    private void jBTRemoveFornecedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTRemoveFornecedoresActionPerformed
        if (valida_campos.VerificaJtable(jTBComponentesMecanicos) == 1) {
            int linha = jTBComponentesMecanicos.getSelectedRow();
            Integer exc = Integer.parseInt(jTBComponentesMecanicos.getValueAt(linha, 9).toString());
            //se não for um item removido
            if (exc == 0) {
                //remove itens e atualiza os totais
                dao_comp_vers.removeItensAtulizaTotal(jTBComponentesMecanicos,situacao, jFTTotalMecanico,jFTTotalComponentes);
            }else{
                JOptionPane.showMessageDialog(null, "Item já removido");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Não possui fornecedores para remover");
        }
    }//GEN-LAST:event_jBTRemoveFornecedoresActionPerformed

    private void jBTAddFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTAddFornecedorActionPerformed
        comp_vers_proj.setId_projeto(projeto.getArray_projetos(jCBProjeto.getSelectedIndex() - 1));
        comp_vers_proj.setVersao(Double.parseDouble(jCBVersao.getSelectedItem().toString()));
        comp_vers_proj.setTipo("M");
        comp_vers_proj.setTabela(jTBComponentesMecanicos);
        comp_vers_proj.setField_total_eletronicos(jFTTotalEletronico);
        comp_vers_proj.setField_total_mecanicos(jFTTotalMecanico);
        comp_vers_proj.setField_total_comp(jFTTotalComponentes);
        new InterfaceAddComponentesVersao().setVisible(true);
    }//GEN-LAST:event_jBTAddFornecedorActionPerformed

    private void jCBVersaoPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jCBVersaoPopupMenuWillBecomeInvisible
        Double versao_projeto;
        Double total_eletronicos = 0.0;
        Double total_mecanicos = 0.0;
        Integer id_projeto;
        
        if(situacao == Rotinas.ALTERAR || situacao == Rotinas.INICIAL ){
            //se estiver selecionado uma versão
            if(jCBVersao.getSelectedIndex() > 0){
                //retorna os dados da versão selecionada
                id_projeto = projeto.getArray_projetos(jCBProjeto.getSelectedIndex() - 1);
                versao_projeto = Double.parseDouble(jCBVersao.getSelectedItem().toString());
                versao.setId_projeto(id_projeto);
                versao.setVersao(versao_projeto);
                dao_versao.retornardados(versao);
            
                //seta dados da versão na tela
                setcompVersaoProjeto();
            
                //habilita jtables da tela
                valida_campos.habilitaCampos(jPVersaoProjeto);
                jCBComercializado.setEnabled(true);
                jCBCertificacao.setEnabled(true);
            
                //Lista componentes eletrônicos da versão
                comp_vers_proj.setId_projeto(id_projeto);
                comp_vers_proj.setVersao(versao_projeto);
                comp_vers_proj.setCod_vers_projeto(Integer.parseInt(jTFIDVersao.getText()));
                
                comp_vers_proj.setTipo("E");
                
                dao_comp_vers.consultaCompVersaoProjeto(comp_vers_proj);
            
                //Preenche na JTABLE de componentes eletrônicos todos componentes eletronicos sendo utilizados nesta versão do projeto
                Jtable.PreencherJtableGenerico(jTBComponentesEletronicos, new String[]{"null","id_comp_versao","id_componente","componente.descricao","id_moeda","unidade","valor_unit","qntd_no_projeto","total","false","qntd_no_projeto"}, comp_vers_proj.getRetorno());

                //ajusta largura das colunas
                Jtable.ajustarColunasDaTabela(jTBComponentesEletronicos);
                
                //converte os totais dos compoenntes em reais pois o banco retorna o valor calculado referente a moeda que o mesmo foi cadastrado
                dao_comp_vers.converteTotalComp(comp_vers_proj, jTBComponentesEletronicos);
                //seta o total de componentes eletronicos
                jFTTotalEletronico.setText(String.valueOf(conversao.doubleParaObjectDecimalFormat(dao_comp_vers.calcula_total_componentes(jTBComponentesEletronicos))));
                
                //Lista componentes mecânicos da versão
                comp_vers_proj.setTipo("M");
                
                dao_comp_vers.consultaCompVersaoProjeto(comp_vers_proj);
            
                //Preenche na JTABLE de componentes eletrônicos todos componentes eletronicos sendo utilizados nesta versão do projeto
                Jtable.PreencherJtableGenerico(jTBComponentesMecanicos, new String[]{"null","id_comp_versao","id_componente","componente.descricao","id_moeda","unidade","valor_unit","qntd_no_projeto","total","false","qntd_no_projeto"}, comp_vers_proj.getRetorno());

                //ajusta largura das colunas
                Jtable.ajustarColunasDaTabela(jTBComponentesMecanicos);
                
                //converte os totais dos compoenntes em reais pois o banco retorna o valor calculado referente a moeda que o mesmo foi cadastrado
                dao_comp_vers.converteTotalComp(comp_vers_proj,jTBComponentesMecanicos);
                //seta o total de componentes eletronicos
                jFTTotalMecanico.setText(String.valueOf(conversao.doubleParaObjectDecimalFormat(dao_comp_vers.calcula_total_componentes(jTBComponentesMecanicos))));
                
                situacao = Rotinas.ALTERAR;

                //habilita os botoes utilizados na inclusão e desabilita os restantes
                valida_botoes.ValidaEstado(jPBotoes, situacao);
                
                //calcula o total de todos componentes
                total_eletronicos = Double.parseDouble(jFTTotalEletronico.getText().replace(".", "").replace(",", "."));
                total_mecanicos = Double.parseDouble(jFTTotalMecanico.getText().replace(".", "").replace(",", "."));
                jFTTotalComponentes.setText(String.valueOf(conversao.doubleParaObjectDecimalFormat(total_eletronicos + total_mecanicos)));
                 
            }else{
                limpa_dados_versao();
                //desabilita jtables da tela
                valida_campos.desabilitaCampos(jPVersaoProjeto);
                
                situacao = Rotinas.INICIAL;

                //habilita os botoes utilizados na inclusão e desabilita os restantes
                valida_botoes.ValidaEstado(jPBotoes, situacao);
            }
        }
    }//GEN-LAST:event_jCBVersaoPopupMenuWillBecomeInvisible

    private void jCBVersaoPopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jCBVersaoPopupMenuWillBecomeVisible
        // TODO add your handling code here:
    }//GEN-LAST:event_jCBVersaoPopupMenuWillBecomeVisible

    private void jTBComponenteStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTBComponenteStateChanged

    }//GEN-LAST:event_jTBComponenteStateChanged

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        //limpa campos
        valida_campos.LimparCampos(jPDadosVersao);
        valida_campos.LimparCampos(jPVersaoProjeto);
        valida_campos.LimparJtable(jTBComponentesEletronicos);
        valida_campos.LimparJtable(jTBComponentesMecanicos);
       
        //Define a situação como incluir para habilitar os botoes utilizados apenas na inclusão
        situacao = Rotinas.INICIAL;

        //habilita os botoes utilizados na inclusão e desabilita os restantes
        valida_botoes.ValidaEstado(jPBotoes, situacao);

        //desabilita campos
        valida_campos.desabilitaCampos(jPVersaoProjeto);
        
        // carrega dados nas combobox
        dao_projeto.consultaGeral(projeto);
        //Preenche dados nas ComboBox de projetos
        array_projetos = combo.PreencherCombo(jCBProjeto, "descricao", projeto.getRetorno(), "id_projeto");
        //seta no array da classe de projetos a lista de projetos listadas na combo
        projeto.setArray_projetos(array_projetos);
        
        jCBComercializado.addItem("Selecione");
        jCBComercializado.addItem("Sim");
        jCBComercializado.addItem("Não");
        
        jCBCertificacao.addItem("Selecione");
        jCBCertificacao.addItem("Sim");
        jCBCertificacao.addItem("Não");
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jBTNovoEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTNovoEstadoActionPerformed
        try {
            //atualiza dados do usuario logado
            dao_acesso.retornaUsuarioLogado(acesso);

            //Inclui a opção todas telas como primeira opção
            tela.setDescricao("Todas telas");
            tela.setId_tela(1);
            dao_tela.incluir(tela);

            //Inclui a tela de Moedas
            tela.setDescricao("Projetos");
            tela.setId_tela(8);
            dao_tela.incluir(tela);

            //Armazena dados de acesso da tela para verificar permissões
            acesso.setId_tela(8);
            acesso.setNome_tela("Projetos");

            //se naõ for gerente
            if(acesso.getIn_gerente() == 0){
                //retorna as permissoes de acesso do usuario
                dao_permissao.retornaDadosPermissao(acesso, permissao);
            }

            //Verifica se o usuario possui permissao para acessar essa tela
            if (valida_acesso.verificaAcesso("acesso",acesso, permissao) == true){
                //Traz para tela a tela de cadastro de pessoas
                new InterfaceProjeto().setVisible(true);
            }else{
                JOptionPane.showMessageDialog(null, "Voce não possui permissões para acessar essa tela");
            }

        } catch (SQLException ex) {
            Logger.getLogger(InterfacePessoa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jBTNovoEstadoActionPerformed

    private void jTFLoteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFLoteKeyTyped
        String caracteres="0987654321";
        if(!caracteres.contains(evt.getKeyChar()+"")){
            evt.consume();
        }
    }//GEN-LAST:event_jTFLoteKeyTyped

    private void jTBComponentesMecanicosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTBComponentesMecanicosMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTBComponentesMecanicosMouseClicked

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
            java.util.logging.Logger.getLogger(InterfaceVersaoProjeto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterfaceVersaoProjeto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterfaceVersaoProjeto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterfaceVersaoProjeto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InterfaceVersaoProjeto().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBTAddComposicao;
    private javax.swing.JButton jBTAddFornecedor;
    private javax.swing.JButton jBTNovoEstado;
    private javax.swing.JButton jBTRemoveComposicao;
    private javax.swing.JButton jBTRemoveFornecedores;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox jCBCertificacao;
    private javax.swing.JComboBox jCBComercializado;
    private javax.swing.JComboBox jCBProjeto;
    private javax.swing.JComboBox jCBVersao;
    private javax.swing.JFormattedTextField jFTData;
    private javax.swing.JFormattedTextField jFTTotalComponentes;
    private javax.swing.JFormattedTextField jFTTotalEletronico;
    private javax.swing.JFormattedTextField jFTTotalMecanico;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPBotoes;
    private javax.swing.JPanel jPDadosVersao;
    private javax.swing.JPanel jPVersaoProjeto;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTBComponente;
    private javax.swing.JTable jTBComponentesEletronicos;
    private javax.swing.JTable jTBComponentesMecanicos;
    private javax.swing.JTextField jTFIDVersao;
    private javax.swing.JTextField jTFLote;
    // End of variables declaration//GEN-END:variables

    public VersaoProjeto getVersaoProjeto(){
        versao = new VersaoProjeto();
        
        Date data_atual = new Date(System.currentTimeMillis());
        
        int id_versao = Integer.parseInt(jTFIDVersao.getText());
      
        versao.setCod_vers_projeto(id_versao);
        //versao.setVersao(Double.parseDouble(String.valueOf(versao.getArray_versoes(jCBVersao.getSelectedIndex() - 1))) );
        versao.setId_projeto(projeto.getArray_projetos(jCBProjeto.getSelectedIndex() - 1));
        versao.setVersao(Double.parseDouble(jCBVersao.getSelectedItem().toString()));
        if(jCBComercializado.getSelectedItem().equals("Sim")){
            versao.setComercializado("S");
            versao.setLote(Integer.parseInt(jTFLote.getText()));
        }else if (jCBComercializado.getSelectedItem().equals("Não")){
             versao.setComercializado("N");
        }else{
            versao.setComercializado("Não");
        }

        versao.setData_cadastro(data.stringParaSQLDate(jFTData.getText()));
        versao.setData_alter(data_atual);

        return versao;
    }
    
    public ComponenteVersaoProjeto getCompVersaoProj(){
        
        comp_vers_proj = new ComponenteVersaoProjeto();

        int id_versao = Integer.parseInt(jTFIDVersao.getText());
        comp_vers_proj.setCod_vers_projeto(id_versao);
        return comp_vers_proj;
    }
    
    public void setcompVersaoProjeto(){
    jTFIDVersao.setText(String.valueOf(versao.getCod_vers_projeto()));
    if(versao.getComercializado().equals("S")){
        jCBComercializado.setSelectedItem("Sim");
        jTFLote.setText(String.valueOf(versao.getLote()));
    }else if (versao.getComercializado().equals("N")){
        jCBComercializado.setSelectedItem("Não"); 
        jTFLote.setEnabled(false);
    }else{
        jCBComercializado.setSelectedIndex(0); 
        jTFLote.setEnabled(false);
    }
    
    if(versao.getCertificacao().equals("S")){
        jCBCertificacao.setSelectedItem("Sim");
    }else if (versao.getComercializado().equals("N")){
         jCBCertificacao.setSelectedItem("Não"); 
    }else{
        jCBCertificacao.setSelectedIndex(0); 
    }
    
    jFTData.setText(data.organizaData(versao.getData_cadastro()));
    }
 
 public void limpa_dados_versao(){
    jTFIDVersao.setText("");
    jFTData.setText("");
    jTFLote.setText("");
    jCBComercializado.setSelectedIndex(0);
    jCBComercializado.setEnabled(false);
    jCBCertificacao.setSelectedIndex(0);
    jCBCertificacao.setEnabled(false);
    jTFLote.setEnabled(false);
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


            exc = Integer.parseInt(table.getValueAt(row, 9).toString());
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

            Integer exc = Integer.parseInt(table.getValueAt(row, 9).toString());
             
            //se não for um item excluido, deixa manipular valores
            if(exc == 0){
                
                //Se o valor novo não for vazio
                if(!newValue.toString().replace(" ", "").equals("")){

                    if(column == 7 ){
                        try { 
                            //verifica se o valor setado é um valor numerico
                            Integer qntd = Integer.parseInt(table.getValueAt(row, column).toString());
                            Double valor_unit = Double.parseDouble(table.getValueAt(row, 6).toString().replace(",", "."));
                            Double total;
                            if(qntd > 0){
                                //recalcula o total
                                total = valor_unit * qntd;
                                table.setValueAt(total, row, 8);
                               
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

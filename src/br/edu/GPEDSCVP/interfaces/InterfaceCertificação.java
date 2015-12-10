/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.GPEDSCVP.interfaces;

import br.edu.GPEDSCVP.classe.Acesso;
import br.edu.GPEDSCVP.classe.Certificacao;
import br.edu.GPEDSCVP.classe.Certificadora;
import br.edu.GPEDSCVP.classe.Norma;
import br.edu.GPEDSCVP.classe.Permissao;
import br.edu.GPEDSCVP.classe.Projeto;
import br.edu.GPEDSCVP.classe.Tela;
import br.edu.GPEDSCVP.classe.VersaoProjeto;
import br.edu.GPEDSCVP.dao.daoAcesso;
import br.edu.GPEDSCVP.dao.daoCertificacao;
import br.edu.GPEDSCVP.dao.daoNorma;
import br.edu.GPEDSCVP.dao.daoPermissao;
import br.edu.GPEDSCVP.dao.daoPessoa;
import br.edu.GPEDSCVP.dao.daoProjeto;
import br.edu.GPEDSCVP.dao.daoTela;
import br.edu.GPEDSCVP.dao.daoVersaoProjeto;
import br.edu.GPEDSCVP.util.ComboBox;
import br.edu.GPEDSCVP.util.DocumentoLimitado;
import br.edu.GPEDSCVP.util.FormatarData;
import br.edu.GPEDSCVP.util.ManipulaJtable;
import br.edu.GPEDSCVP.util.Mensagens;
import br.edu.GPEDSCVP.util.Rotinas;
import br.edu.GPEDSCVP.util.TamanhoLimitadoListener;
import br.edu.GPEDSCVP.util.UltimaSequencia;
import br.edu.GPEDSCVP.util.ValidaAcesso;
import br.edu.GPEDSCVP.util.ValidaBotoes;
import br.edu.GPEDSCVP.util.ValidaCampos;
import br.edu.GPEDSCVP.util.ValorLimitadoListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

/**
 *
 * @author rafa
 */
public class InterfaceCertificação extends javax.swing.JFrame {

    Certificacao certificacao;
    daoCertificacao dao_certificacao;
    Projeto projeto;
    VersaoProjeto versao;
    Norma norma;
    Certificadora certificadora;
    Tela tela;
    ComboBox combo;
    Acesso acesso;
    Mensagens mensagem;
    daoPermissao dao_permissao;
    daoAcesso dao_acesso;
    daoPessoa dao_pessoa;
    daoProjeto dao_projeto;
    daoTela dao_tela;
    daoVersaoProjeto dao_versao;
    daoNorma dao_norma;
    Permissao permissao;
    ValidaAcesso valida_acesso;
    ValidaBotoes valida_botoes;
    ValidaCampos valida_campos;
    ManipulaJtable Jtable;
    FormatarData data;
    int[] array_normas;
    int[] array_certificadoras;
    int[] array_projetos;
    int[] array_versoes;

    int situacao = Rotinas.PADRAO;

    public InterfaceCertificação() {
        initComponents();

        projeto = new Projeto();
        versao = new VersaoProjeto();
        certificacao = new Certificacao();
        certificadora = new Certificadora();
        norma = new Norma();
        dao_norma = new daoNorma();
        dao_pessoa = new daoPessoa();
        dao_certificacao = new daoCertificacao();
        dao_projeto = new daoProjeto();
        dao_tela = new daoTela();
        dao_versao = new daoVersaoProjeto();
        tela = new Tela();
        acesso = new Acesso();
        dao_permissao = new daoPermissao();
        dao_acesso = new daoAcesso();
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
        //limita numeros monetarios
        //jFTValorCertif.addKeyListener( new ValorLimitadoListener(0,999999999));

        //Adiciona barra de rolagem obs: obrigatorio para conseguir dimensionar automatico as colunas da jtable
        jTBConsultaCertificacoes.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        //seta mascaras nos campos
        valida_campos.formataMonetario(jFTValorCertif);
        jFTDataEnsaio.setFormatterFactory(new DefaultFormatterFactory(valida_campos.formata("##/##/####")));

        //atualiza dados do usuario logado
        dao_acesso.retornaUsuarioLogado(acesso);

        dao_pessoa.consultageral(certificadora);
        //Preenche dados nas ComboBox de certificadoras
        array_certificadoras = combo.PreencherCombo(jCBCertificadora, "nome", certificadora.getRetorno(), "id_pessoa");
        //seta no array da classe de pessoa a lista de certificadoras listadas na combo
        certificadora.setArray_pessoas(array_certificadoras);

        dao_projeto.consultaGeral(projeto);
        //Preenche dados nas ComboBox de projetos
        array_projetos = combo.PreencherCombo(jCBProjeto, "descricao", projeto.getRetorno(), "id_projeto");
        //seta no array da classe de projetos a lista de projetos listadas na combo
        projeto.setArray_projetos(array_projetos);

        dao_norma.consultaGeral(norma);
        //Preenche dados nas ComboBox de normas
        array_normas = combo.PreencherCombo(jCBNorma, "titulo", norma.getRetorno(), "id_norma");
        //seta no array da classe de normas a lista de normas listadas na combo
        norma.setArray_norma(array_normas);

        //desabilita campos da tela de componente
        valida_campos.desabilitaCampos(jPCertificacao);

        //Define a situação como inicial para habilitar os botoes utilizados apenas quando inicia a tela
        situacao = Rotinas.INICIAL;

        //habilita os botoes utilizados na inicialização da tela
        valida_botoes.ValidaEstado(jPBotoes, situacao);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTBCertificacao = new javax.swing.JTabbedPane();
        jPCertificacao = new javax.swing.JPanel();
        jPBotoes = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jTFIDCertificacao = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jCBCertificadora = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTFDescrição = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jCBProjeto = new javax.swing.JComboBox();
        jCBVersao = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        jFTDataEnsaio = new javax.swing.JFormattedTextField();
        jLabel13 = new javax.swing.JLabel();
        jCBNorma = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jCBResultado = new javax.swing.JComboBox();
        jFTValorCertif = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTADescReprov = new javax.swing.JTextArea();
        jLabel16 = new javax.swing.JLabel();
        jFTData = new javax.swing.JFormattedTextField();
        jLabel9 = new javax.swing.JLabel();
        jBTNovoMaterial = new javax.swing.JButton();
        jBTNovoMaterial1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTBConsultaCertificacoes = new javax.swing.JTable();
        jCBBuscarPor = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();
        jTFFiltro = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jBTBuscar = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTAConsultaDescReprov = new javax.swing.JTextArea();
        jLabel17 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Certificações");
        setResizable(false);

        jTBCertificacao.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTBCertificacaoStateChanged(evt);
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

        jButton3.setIcon(new javax.swing.ImageIcon("C:\\Users\\rafa\\Documents\\GPEDS-CVP\\src\\br\\edu\\GPEDSCVP\\icones\\Botoes_Site_5751_Knob_Remove_Red.png")); // NOI18N
        jButton3.setText("Excluir");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPBotoesLayout = new javax.swing.GroupLayout(jPBotoes);
        jPBotoes.setLayout(jPBotoesLayout);
        jPBotoesLayout.setHorizontalGroup(
            jPBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPBotoesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addContainerGap()
                .addGroup(jPBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTFIDCertificacao.setEditable(false);
        jTFIDCertificacao.setToolTipText("ID Certificação");
        jTFIDCertificacao.setName("id_certificacao"); // NOI18N

        jLabel1.setText("ID Certificação:");

        jCBCertificadora.setToolTipText("Certificadora");
        jCBCertificadora.setName("id_pessoa"); // NOI18N
        jCBCertificadora.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                jCBCertificadoraPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
                jCBCertificadoraPopupMenuWillBecomeVisible(evt);
            }
        });

        jLabel3.setText("Certificadora:");

        jLabel4.setText("Descrição:");

        jTFDescrição.setToolTipText("Descrição");
        jTFDescrição.setName("descricao"); // NOI18N

        jLabel8.setText("Projeto:");

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

        jCBVersao.setToolTipText("Versão");
        jCBVersao.setName("cod_vers_projeto"); // NOI18N
        jCBVersao.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
                jCBVersaoPopupMenuWillBecomeVisible(evt);
            }
        });

        jLabel10.setText("Versão:");

        jFTDataEnsaio.setToolTipText("Data ensaio");
        jFTDataEnsaio.setName("data_ensaio"); // NOI18N
        jFTDataEnsaio.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jFTDataEnsaioFocusLost(evt);
            }
        });

        jLabel13.setText("Data ensaio:");

        jCBNorma.setToolTipText("Norma");
        jCBNorma.setName("id_norma"); // NOI18N
        jCBNorma.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                jCBNormaPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
                jCBNormaPopupMenuWillBecomeVisible(evt);
            }
        });

        jLabel5.setText("Norma:");

        jLabel2.setText("Resultado:");

        jCBResultado.setToolTipText("Resultado");
        jCBResultado.setName("resultado"); // NOI18N
        jCBResultado.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                jCBResultadoPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jFTValorCertif.setToolTipText("Valor");
        jFTValorCertif.setName("valor"); // NOI18N

        jLabel6.setText("Valor R$:");

        jTADescReprov.setColumns(20);
        jTADescReprov.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        jTADescReprov.setRows(5);
        jTADescReprov.setToolTipText("Descrição reprovação");
        jTADescReprov.setEnabled(false);
        jTADescReprov.setName("descricao_reprovacao"); // NOI18N
        jScrollPane2.setViewportView(jTADescReprov);

        jLabel16.setText("Descrição reprovação:");

        jFTData.setEditable(false);
        jFTData.setToolTipText("Data");
        jFTData.setName("data_cadastro"); // NOI18N

        jLabel9.setText("Data:");

        jBTNovoMaterial.setIcon(new javax.swing.ImageIcon("C:\\Users\\rafa\\Documents\\GPEDS-CVP\\src\\br\\edu\\GPEDSCVP\\icones\\add.png")); // NOI18N
        jBTNovoMaterial.setText("Novo");
        jBTNovoMaterial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTNovoMaterialActionPerformed(evt);
            }
        });

        jBTNovoMaterial1.setIcon(new javax.swing.ImageIcon("C:\\Users\\rafa\\Documents\\GPEDS-CVP\\src\\br\\edu\\GPEDSCVP\\icones\\add.png")); // NOI18N
        jBTNovoMaterial1.setText("Novo");
        jBTNovoMaterial1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTNovoMaterial1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPCertificacaoLayout = new javax.swing.GroupLayout(jPCertificacao);
        jPCertificacao.setLayout(jPCertificacaoLayout);
        jPCertificacaoLayout.setHorizontalGroup(
            jPCertificacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPCertificacaoLayout.createSequentialGroup()
                .addGroup(jPCertificacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPCertificacaoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPCertificacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPCertificacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPCertificacaoLayout.createSequentialGroup()
                                    .addGroup(jPCertificacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel4)
                                        .addComponent(jTFDescrição, javax.swing.GroupLayout.PREFERRED_SIZE, 482, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPCertificacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel3)
                                        .addGroup(jPCertificacaoLayout.createSequentialGroup()
                                            .addComponent(jCBCertificadora, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jBTNovoMaterial))))
                                .addGroup(jPCertificacaoLayout.createSequentialGroup()
                                    .addGroup(jPCertificacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jTFIDCertificacao, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addGap(544, 544, 544)
                                    .addComponent(jLabel9)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jFTData, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel16)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 684, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPCertificacaoLayout.createSequentialGroup()
                                .addGroup(jPCertificacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPCertificacaoLayout.createSequentialGroup()
                                        .addGroup(jPCertificacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jFTDataEnsaio, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel13))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPCertificacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jCBProjeto, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel8))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPCertificacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel10)
                                            .addComponent(jCBVersao, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPCertificacaoLayout.createSequentialGroup()
                                        .addGroup(jPCertificacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jCBResultado, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel2))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPCertificacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel6)
                                            .addComponent(jFTValorCertif, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(46, 46, 46)
                                .addGroup(jPCertificacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPCertificacaoLayout.createSequentialGroup()
                                        .addComponent(jCBNorma, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jBTNovoMaterial1))
                                    .addComponent(jLabel5)))))
                    .addGroup(jPCertificacaoLayout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(jPBotoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPCertificacaoLayout.setVerticalGroup(
            jPCertificacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPCertificacaoLayout.createSequentialGroup()
                .addGroup(jPCertificacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPCertificacaoLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel1))
                    .addGroup(jPCertificacaoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPCertificacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jFTData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTFIDCertificacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addGroup(jPCertificacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPCertificacaoLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPCertificacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTFDescrição, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCBCertificadora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBTNovoMaterial)))
                    .addGroup(jPCertificacaoLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(31, 31, 31)))
                .addGroup(jPCertificacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPCertificacaoLayout.createSequentialGroup()
                        .addGroup(jPCertificacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(jLabel8)
                            .addComponent(jLabel10)
                            .addComponent(jLabel5))
                        .addGap(29, 29, 29))
                    .addGroup(jPCertificacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCBVersao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jCBProjeto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jFTDataEnsaio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jCBNorma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jBTNovoMaterial1)))
                .addGap(28, 28, 28)
                .addGroup(jPCertificacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPCertificacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCBResultado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFTValorCertif, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                .addComponent(jPBotoes, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTBCertificacao.addTab("Cadastro", jPCertificacao);

        jTBConsultaCertificacoes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Certificação", "Descrição", "ID Certificadora", "Certificadora", "ID Norma", "Norma", "ID Projeto", "Projeto", "Versão", "Resultado", "Data ensaio", "Valor R$", "Data cadastro", "Última alteração"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTBConsultaCertificacoes.setName("Componentes"); // NOI18N
        jTBConsultaCertificacoes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTBConsultaCertificacoesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTBConsultaCertificacoes);

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

        jTAConsultaDescReprov.setEditable(false);
        jTAConsultaDescReprov.setColumns(20);
        jTAConsultaDescReprov.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        jTAConsultaDescReprov.setRows(5);
        jTAConsultaDescReprov.setToolTipText("Descrição reprovação");
        jTAConsultaDescReprov.setEnabled(false);
        jTAConsultaDescReprov.setName("descricao_reprovacao"); // NOI18N
        jScrollPane3.setViewportView(jTAConsultaDescReprov);

        jLabel17.setText("Descrição reprovação:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jCBBuscarPor, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTFFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jBTBuscar))
                            .addComponent(jLabel29))
                        .addContainerGap(279, Short.MAX_VALUE))
                    .addComponent(jScrollPane3)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(jLabel29, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCBBuscarPor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTFFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBTBuscar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTBCertificacao.addTab("Consulta", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTBCertificacao)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTBCertificacao, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        setSize(new java.awt.Dimension(822, 619));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        UltimaSequencia ultima;

        //habilita campos da tela
        valida_campos.habilitaCampos(jPCertificacao);
        jTADescReprov.setEnabled(true);
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
                int sequencia = (Integer) (ultima.ultimasequencia("CERTIFICACAO", "ID_CERTIFICACAO"));
                //seta id no campo camponente
                jTFIDCertificacao.setText(Integer.toString(sequencia));

                //Seta a data atual no campo data
                jFTData.setEnabled(true);
                jFTData.setText(data.DataAtual());

                //Carrega conteudo das combobox
                jCBResultado.addItem("Selecione item");
                jCBResultado.addItem("Aprovado");
                jCBResultado.addItem("Reprovado");

                dao_pessoa.consultageral(certificadora);
                //Preenche dados nas ComboBox de certificadoras
                array_certificadoras = combo.PreencherCombo(jCBCertificadora, "nome", certificadora.getRetorno(), "id_pessoa");
                //seta no array da classe de pessoa a lista de certificadoras listadas na combo
                certificadora.setArray_pessoas(array_certificadoras);

                dao_projeto.consultaGeral(projeto);
                //Preenche dados nas ComboBox de projetos
                array_projetos = combo.PreencherCombo(jCBProjeto, "descricao", projeto.getRetorno(), "id_projeto");
                //seta no array da classe de projetos a lista de projetos listadas na combo
                projeto.setArray_projetos(array_projetos);

                dao_norma.consultaGeral(norma);
                //Preenche dados nas ComboBox de normas
                array_normas = combo.PreencherCombo(jCBNorma, "titulo", norma.getRetorno(), "id_norma");
                //seta no array da classe de normas a lista de normas listadas na combo
                norma.setArray_norma(array_normas);

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Falha ao iniciar a inserção de certificação");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Voce não possui permissões para incluir certificações no sistema");
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
            valida_campos.habilitaCampos(jPCertificacao);
            jTADescReprov.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(null, "Você não possui permissões para alterar registros de certificações no sistema");
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
            certificacao.setId_certificacao(Integer.parseInt(jTFIDCertificacao.getText()));

            if (mensagem.ValidaMensagem("Deseja realmente excluir o registro ?") == 0) {
                //Inativa componente
                dao_certificacao.inativaCertificacao(certificacao);
                JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
                //Limpa os campos da tela certificação
                valida_campos.LimparCampos(jPCertificacao);
                valida_campos.LimparJtable(jTBConsultaCertificacoes);
                jFTValorCertif.setText(null);
                jFTValorCertif.setValue(null);
                jTADescReprov.setText("");

                //Define a situação como inicial para habilitar os botoes utilizados apenas quando inicia a tela
                situacao = Rotinas.INICIAL;

                //habilita os botoes utilizados na inicialização da tela
                valida_botoes.ValidaEstado(jPBotoes, situacao);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Voce não possui permissões para excluir certificações no sistema");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5MouseExited

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        //Se for inclusão
        if (situacao == Rotinas.INCLUIR) {
            if (valida_campos.validacamposobrigatorios(jPCertificacao, "CERTIFICACAO") == 0) {

                try {
                    //pega dados da certificação na tela
                    getCertificacao();
                    //inclui componente
                    if (dao_certificacao.incluir(certificacao) == true) {

                        //se ocorreu tudo bem na inclusão
                        JOptionPane.showMessageDialog(null, "Salvo com Sucesso");
                        //limpa campos
                        valida_campos.LimparCampos(jPCertificacao);
                        jFTValorCertif.setText(null);
                        jFTValorCertif.setValue(null);
                        jTADescReprov.setText("");
                        valida_campos.LimparJtable(jTBConsultaCertificacoes);

                        //Define a situação como incluir para habilitar os botoes utilizados apenas na inclusão
                        situacao = Rotinas.INICIAL;

                        //habilita os botoes utilizados na inclusão e desabilita os restantes
                        valida_botoes.ValidaEstado(jPBotoes, situacao);

                        //desabilita campos
                        valida_campos.desabilitaCampos(jPCertificacao);
                        jTADescReprov.setEnabled(false);
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Falha ao incluir certificação");
                }

            }
        } else if (situacao == Rotinas.ALTERAR) {

            //pega dados do material na tela
            if (valida_campos.validacamposobrigatorios(jPCertificacao, "CERTIFICACAO") == 0) {
                try {
                    getCertificacao();
                    //alterar componente
                    if (dao_certificacao.alterar(certificacao) == true) {
                        //altera composição

                        JOptionPane.showMessageDialog(null, "Salvo com Sucesso");
                        //limpa campos
                        valida_campos.LimparCampos(jPCertificacao);
                        jFTValorCertif.setText(null);
                        jFTValorCertif.setValue(null);
                        jTADescReprov.setText("");
                        valida_campos.LimparJtable(jTBConsultaCertificacoes);

                        //Define a situação como incluir para habilitar os botoes utilizados apenas na inclusão
                        situacao = Rotinas.INICIAL;

                        //habilita os botoes utilizados na inclusão e desabilita os restantes
                        valida_botoes.ValidaEstado(jPBotoes, situacao);

                        //desabilita campos
                        valida_campos.desabilitaCampos(jPCertificacao);
                        jTADescReprov.setEnabled(false);
                    }

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Falha ao alterar certificação");
                }
            }
        }

    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        //limpa campos
        valida_campos.LimparCampos(jPCertificacao);
        jFTValorCertif.setText(null);
        jFTValorCertif.setValue(null);
        jTADescReprov.setText("");

        //Define a situação como incluir para habilitar os botoes utilizados apenas na inclusão
        situacao = Rotinas.INICIAL;

        //habilita os botoes utilizados na inclusão e desabilita os restantes
        valida_botoes.ValidaEstado(jPBotoes, situacao);

        //desabilita campos
        valida_campos.desabilitaCampos(jPCertificacao);
        jTADescReprov.setEnabled(false);

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jCBCertificadoraPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jCBCertificadoraPopupMenuWillBecomeInvisible

    }//GEN-LAST:event_jCBCertificadoraPopupMenuWillBecomeInvisible

    private void jCBCertificadoraPopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jCBCertificadoraPopupMenuWillBecomeVisible
        dao_pessoa.consultageral(certificadora);
        //Preenche dados nas ComboBox de certificadoras
        array_certificadoras = combo.PreencherCombo(jCBCertificadora, "nome", certificadora.getRetorno(), "id_pessoa");
        //seta no array da classe de pessoa a lista de certificadoras listadas na combo
        certificadora.setArray_pessoas(array_certificadoras);
    }//GEN-LAST:event_jCBCertificadoraPopupMenuWillBecomeVisible

    private void jCBProjetoPopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jCBProjetoPopupMenuWillBecomeVisible

    }//GEN-LAST:event_jCBProjetoPopupMenuWillBecomeVisible

    private void jTBConsultaCertificacoesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTBConsultaCertificacoesMouseClicked
        //Verifica se houve 1 clique do mouse

        //recupera a linha clicada
        int linha = jTBConsultaCertificacoes.getSelectedRow();
        Integer id_certificacao = Integer.parseInt(jTBConsultaCertificacoes.getValueAt(linha, 0).toString());

        if (evt.getClickCount() == 1) {

            //retorna dados da certificacao
            certificacao.setId_certificacao(id_certificacao);
            dao_certificacao.retornardados(certificacao);
            jTAConsultaDescReprov.setText(certificacao.getDesc_reprov());

        } else if (evt.getClickCount() == 2) {

            //Limpa os campos da tela componente
            valida_campos.LimparCampos(jPCertificacao);

            //desabilita campos
            valida_campos.desabilitaCampos(jPCertificacao);

            //Carrega conteudo das combobox
            jCBResultado.addItem("Selecione item");
            jCBResultado.addItem("Aprovado");
            jCBResultado.addItem("Reprovado");

            dao_pessoa.consultageral(certificadora);
            //Preenche dados nas ComboBox de certificadoras
            array_certificadoras = combo.PreencherCombo(jCBCertificadora, "nome", certificadora.getRetorno(), "id_pessoa");
            //seta no array da classe de pessoa a lista de certificadoras listadas na combo
            certificadora.setArray_pessoas(array_certificadoras);

            dao_projeto.consultaGeral(projeto);
            //Preenche dados nas ComboBox de projetos
            array_projetos = combo.PreencherCombo(jCBProjeto, "descricao", projeto.getRetorno(), "id_projeto");
            //seta no array da classe de projetos a lista de projetos listadas na combo
            projeto.setArray_projetos(array_projetos);

            dao_norma.consultaGeral(norma);
            //Preenche dados nas ComboBox de normas
            array_normas = combo.PreencherCombo(jCBNorma, "titulo", norma.getRetorno(), "id_norma");
            //seta no array da classe de normas a lista de normas listadas na combo
            norma.setArray_norma(array_normas);

            //retorna dados da certificacao
            certificacao.setId_certificacao(id_certificacao);
            dao_certificacao.retornardados(certificacao);

            //seta dados do componente na tela de cadastro
            setcompCertificacao();

            //retorna para tela de cadastro
            jTBCertificacao.setSelectedIndex(0);

            //Define a situação como padrao para habilitar os botoes utilizados apenas na alteração ou exclusão
            situacao = Rotinas.PADRAO;
            //habilita os botoes utilizados na alteraçao e exclusão e desabilita os restantes
            valida_botoes.ValidaEstado(jPBotoes, situacao);
        }
    }//GEN-LAST:event_jTBConsultaCertificacoesMouseClicked

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
                int linha = jTBConsultaCertificacoes.getSelectedRow();
                int id_busca = 0;
                String ds_busca = "";

                //Tira aspas simples da string para evitar código sql
                valida_campos.IgnoraSQL(jTFFiltro);

                //Combobox buscar por: geral
                switch (jCBBuscarPor.getSelectedIndex()) {
                    case 0:
                        //Consulta geral de componentes
                        dao_certificacao.consultaGeral(certificacao);
                        break;
                    case 1:
                        //Consulta geral de componentes por código
                        try {
                            id_busca = Integer.parseInt(jTFFiltro.getText());
                            certificacao.setId_certificacao(id_busca);
                            dao_certificacao.consultaGeralCodigo(certificacao);
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Deve informar um valor inteiro para consultar por código");
                            jTFFiltro.grabFocus();
                        }
                        break;
                    case 2:
                        //Consulta geral de componentes pela descrição
                        ds_busca = jTFFiltro.getText();
                        if (!ds_busca.replace(" ", "").equals("")) {
                            certificacao.setDescricao(ds_busca);
                            dao_certificacao.consultaGeralDescricao(certificacao);
                        } else {
                            JOptionPane.showMessageDialog(null, "Informe a descrição para consulta");
                            jTFFiltro.grabFocus();
                        }
                        break;
                }
            }
            //Preenche na JTABLE os dados dos componentes cadastrados
            Jtable.PreencherJtableGenerico(jTBConsultaCertificacoes, new String[]{"id_certificacao", "certificacao.descricao", "id_pessoa", "pessoa.nome", "id_norma", "norma.titulo",
                "id_projeto", "projeto.descricao", "versao_projeto.versao", "resultado", "data_ensaio", "certificacao.valor", "certificacao.data_cadastro", "certificacao.data_alter"}, certificacao.getRetorno());
            Jtable.ajustarColunasDaTabela(jTBConsultaCertificacoes);
        } else {
            JOptionPane.showMessageDialog(null, "Você nao possui permissões para consultar certificações no sistema");
        }
    }//GEN-LAST:event_jBTBuscarActionPerformed

    private void jTBCertificacaoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTBCertificacaoStateChanged

    }//GEN-LAST:event_jTBCertificacaoStateChanged

    private void jCBVersaoPopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jCBVersaoPopupMenuWillBecomeVisible
        // TODO add your handling code here:
    }//GEN-LAST:event_jCBVersaoPopupMenuWillBecomeVisible

    private void jCBNormaPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jCBNormaPopupMenuWillBecomeInvisible
        // TODO add your handling code here:
    }//GEN-LAST:event_jCBNormaPopupMenuWillBecomeInvisible

    private void jCBNormaPopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jCBNormaPopupMenuWillBecomeVisible
        dao_norma.consultaGeral(norma);
        //Preenche dados nas ComboBox de normas
        array_normas = combo.PreencherCombo(jCBNorma, "titulo", norma.getRetorno(), "id_norma");
        //seta no array da classe de normas a lista de normas listadas na combo
        norma.setArray_norma(array_normas);
    }//GEN-LAST:event_jCBNormaPopupMenuWillBecomeVisible

    private void jCBProjetoPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jCBProjetoPopupMenuWillBecomeInvisible
        if (jCBProjeto.getSelectedIndex() > 0) {

            versao.setId_projeto(projeto.getArray_projetos(jCBProjeto.getSelectedIndex() - 1));
            //consulta versões para preencher na combobox de versões
            dao_versao.consultaCodigo(versao);
            array_versoes = combo.PreencherCombo(jCBVersao, "versao", versao.getRetorno(), "cod_vers_projeto");
            //seta no array da classe de versoes a lista de versoes listadas na combo
            versao.setArray_versoes(array_versoes);
        }
    }//GEN-LAST:event_jCBProjetoPopupMenuWillBecomeInvisible

    private void jBTNovoMaterialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTNovoMaterialActionPerformed
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
            if (acesso.getIn_gerente() == 0) {
                //retorna as permissoes de acesso do usuario  
                dao_permissao.retornaDadosPermissao(acesso, permissao);
            }

            //Verifica se o usuario possui permissao para acessar essa tela
            if (valida_acesso.verificaAcesso("acesso", acesso, permissao) == true) {
                //Traz para tela a tela de cadastro de pessoas 
                new InterfacePessoa().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Voce não possui permissões para acessar essa tela");
            }

        } catch (SQLException ex) {
            Logger.getLogger(InterfacePessoa.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jBTNovoMaterialActionPerformed

    private void jBTNovoMaterial1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTNovoMaterial1ActionPerformed
        try {
            //atualiza dados do usuario logado
            dao_acesso.retornaUsuarioLogado(acesso);

            //Inclui a opção todas telas como primeira opção
            tela.setDescricao("Todas telas");
            tela.setId_tela(1);
            dao_tela.incluir(tela);

            //Inclui a tela de Projetos
            tela.setDescricao("Normas");
            tela.setId_tela(15);
            dao_tela.incluir(tela);

            //Armazena dados de acesso da tela para verificar permissões
            acesso.setId_tela(15);
            acesso.setNome_tela("Normas");

            //se naõ for gerente
            if (acesso.getIn_gerente() == 0) {
                //retorna as permissoes de acesso do usuario  
                dao_permissao.retornaDadosPermissao(acesso, permissao);
            }

            //Verifica se o usuario possui permissao para acessar essa tela
            if (valida_acesso.verificaAcesso("acesso", acesso, permissao) == true) {
                //Traz para tela a tela de cadastro de pessoas 
                new InterfaceNorma().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Voce não possui permissões para acessar essa tela");
            }

        } catch (SQLException ex) {
            Logger.getLogger(InterfaceNorma.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jBTNovoMaterial1ActionPerformed

    private void jCBResultadoPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jCBResultadoPopupMenuWillBecomeInvisible
        if (jCBResultado.getSelectedIndex() == 2) {
            jTADescReprov.setEnabled(true);
        } else {
            jTADescReprov.setText("");
            jTADescReprov.setEnabled(false);
        }
    }//GEN-LAST:event_jCBResultadoPopupMenuWillBecomeInvisible

    private void jFTDataEnsaioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFTDataEnsaioFocusLost

        String data_fornec;

        //pega a data do campo 
        data_fornec = jFTDataEnsaio.getText();
        //retira mascara do campo para pegar o valor adicionado
        data_fornec = data_fornec.replace("/", "");
        data_fornec = data_fornec.replace(":", "");
        data_fornec = data_fornec.replace(" ", "");

        //Garante que sempre que apagar um conteudo do campo com mascara o mesmo se tornara vazio
        if (data_fornec.equals("")) {
            jFTDataEnsaio.setValue("");
        } else {
            if (valida_campos.ValidaData(jFTDataEnsaio.getText()) == false) {
                jFTDataEnsaio.grabFocus();
            }
        }
    }//GEN-LAST:event_jFTDataEnsaioFocusLost

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
            java.util.logging.Logger.getLogger(InterfaceCertificação.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterfaceCertificação.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterfaceCertificação.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterfaceCertificação.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InterfaceCertificação().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBTBuscar;
    private javax.swing.JButton jBTNovoMaterial;
    private javax.swing.JButton jBTNovoMaterial1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox jCBBuscarPor;
    private javax.swing.JComboBox jCBCertificadora;
    private javax.swing.JComboBox jCBNorma;
    private javax.swing.JComboBox jCBProjeto;
    private javax.swing.JComboBox jCBResultado;
    private javax.swing.JComboBox jCBVersao;
    private javax.swing.JFormattedTextField jFTData;
    private javax.swing.JFormattedTextField jFTDataEnsaio;
    private javax.swing.JFormattedTextField jFTValorCertif;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPBotoes;
    private javax.swing.JPanel jPCertificacao;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTAConsultaDescReprov;
    private javax.swing.JTextArea jTADescReprov;
    private javax.swing.JTabbedPane jTBCertificacao;
    private javax.swing.JTable jTBConsultaCertificacoes;
    private javax.swing.JTextField jTFDescrição;
    private javax.swing.JTextField jTFFiltro;
    private javax.swing.JTextField jTFIDCertificacao;
    // End of variables declaration//GEN-END:variables

    public Certificacao getCertificacao() {

        certificacao = new Certificacao();

        Date data_atual = new Date(System.currentTimeMillis());

        int id_certificacao = Integer.parseInt(jTFIDCertificacao.getText());
        Double valor = Double.parseDouble(jFTValorCertif.getText().replace(".", "").replace(",", "."));

        certificacao.setId_certificacao(id_certificacao);
        certificacao.setDescricao(jTFDescrição.getText());
        certificacao.setId_certificadora(certificadora.getArray_pessoas(jCBCertificadora.getSelectedIndex() - 1));
        certificacao.setId_norma(norma.getArray_norma(jCBNorma.getSelectedIndex() - 1));
        certificacao.setId_projeto(projeto.getArray_projetos(jCBProjeto.getSelectedIndex() - 1));

        certificacao.setCod_versao_projeto(versao.getArray_versoes(jCBVersao.getSelectedIndex() - 1));
        certificacao.setValor(valor);
        certificacao.setData_ensaio(data.stringParaSQLDate(jFTDataEnsaio.getText()));
        certificacao.setData_cadastro(data.stringParaSQLDate(jFTData.getText()));
        if (jCBResultado.getSelectedItem().equals("Aprovado")) {
            certificacao.setResultado("A");
        } else if (jCBResultado.getSelectedItem().equals("Reprovado")) {
            certificacao.setDesc_reprov(jTADescReprov.getText());
            certificacao.setResultado("R");
        }

        certificacao.setData_alter(data_atual);

        return certificacao;
    }

    public void setcompCertificacao() {

        jTFIDCertificacao.setText(String.valueOf(certificacao.getId_certificacao()));
        jTFDescrição.setText(certificacao.getDescricao());
        jCBCertificadora.setSelectedItem(certificacao.getDs_certificadora());
        jFTDataEnsaio.setText(data.organizaData(certificacao.getData_ensaio()));
        jCBProjeto.setSelectedItem(certificacao.getDs_projeto());

        versao.setId_projeto(certificacao.getId_projeto());
        //consulta versões para preencher na combobox de versões
        dao_versao.consultaCodigo(versao);
        array_versoes = combo.PreencherCombo(jCBVersao, "versao", versao.getRetorno(), "cod_vers_projeto");
        //seta no array da classe de versoes a lista de versoes listadas na combo
        versao.setArray_versoes(array_versoes);

        jCBVersao.setSelectedItem(certificacao.getVersao());
        jCBNorma.setSelectedItem(certificacao.getDs_norma());
        if (certificacao.getResultado().equals("A")) {
            jCBResultado.setSelectedIndex(1);
        } else if (certificacao.getResultado().equals("R")) {
            jCBResultado.setSelectedIndex(2);
        } else {
            jCBResultado.setSelectedIndex(0);
        }
        jFTValorCertif.setText(String.valueOf(certificacao.getValor()).replace(".", ","));
        jFTData.setText(String.valueOf(data.organizaData(certificacao.getData_cadastro())));
        jTADescReprov.setText(certificacao.getDesc_reprov());
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.GPEDSCVP.interfaces;

import br.edu.GPEDSCVP.classe.Certificadora;
import br.edu.GPEDSCVP.classe.Cidade;
import br.edu.GPEDSCVP.classe.Contato;
import br.edu.GPEDSCVP.classe.Endereco;
import br.edu.GPEDSCVP.classe.Fornecedor;
import br.edu.GPEDSCVP.classe.Permissao;
import br.edu.GPEDSCVP.classe.Pessoa;
import br.edu.GPEDSCVP.classe.PessoaFisica;
import br.edu.GPEDSCVP.classe.PessoaJuridica;
import br.edu.GPEDSCVP.classe.Tela;
import br.edu.GPEDSCVP.classe.Usuario;
import br.edu.GPEDSCVP.dao.daoCidade;
import br.edu.GPEDSCVP.dao.daoContato;
import br.edu.GPEDSCVP.dao.daoEndereco;
import br.edu.GPEDSCVP.dao.daoPermissao;
import br.edu.GPEDSCVP.dao.daoPessoa;
import br.edu.GPEDSCVP.dao.daoTela;
import br.edu.GPEDSCVP.validacao.ComboBox;
import br.edu.GPEDSCVP.validacao.Criptografia;
import br.edu.GPEDSCVP.validacao.FormatarData;
import br.edu.GPEDSCVP.validacao.Mensagens;
import br.edu.GPEDSCVP.validacao.ManipulaJtable;
import br.edu.GPEDSCVP.validacao.Rotinas;
import br.edu.GPEDSCVP.validacao.UltimaSequencia;
import br.edu.GPEDSCVP.validacao.ValidaBotoes;
import br.edu.GPEDSCVP.validacao.ValidaCampos;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.text.DefaultFormatterFactory;

/**
 *
 * @author Willys
 */
public class InterfacePessoa extends javax.swing.JFrame {
    
    ManipulaJtable Jtable;
    ComboBox combo;
    ValidaCampos validaCampos;
    Mensagens mensagem;
    FormatarData data;
    daoPessoa dao_pessoa;
    daoContato dao_contato;
    daoCidade dao_cidade;
    daoEndereco dao_endereco;
    daoPermissao dao_permissao;
    daoTela dao_tela;
    Certificadora certificadora;
    Fornecedor fornecedor;
    Permissao permissao;
    Pessoa pessoa;
    Endereco endereco;
    Usuario usuario;
    Cidade cidade;
    Contato contato;
    Tela tela;
    Rotinas rotinas; 
    Criptografia criptografar;
    ValidaBotoes validabotoes;
    int situacao = Rotinas.PADRAO;
    int[] array_cidade;
    int[] array_tela;

    /**
     * Creates new form InterfacePessoa
     */
    public InterfacePessoa() {
        
        initComponents();
        
        //Instancia de todas as classes
        Jtable = new ManipulaJtable();
        criptografar = new Criptografia();
        data = new FormatarData();
        combo = new ComboBox();
        mensagem = new Mensagens();
        dao_pessoa = new daoPessoa();
        dao_contato = new daoContato();
        dao_cidade = new daoCidade();
        dao_endereco = new daoEndereco();
        dao_permissao = new daoPermissao();
        dao_tela = new daoTela();
        contato = new Contato();
        endereco = new Endereco();
        cidade = new Cidade();
        tela = new Tela();
        permissao = new Permissao();
        usuario = new Usuario();
        certificadora = new Certificadora();
        fornecedor = new Fornecedor();
        pessoa = new Pessoa();
        try {
            validaCampos = new ValidaCampos();
        } catch (SQLException ex) {
            Logger.getLogger(InterfacePessoa.class.getName()).log(Level.SEVERE, null, ex);
        }
        validabotoes = new ValidaBotoes();
        
        //Adiciona barra de rolagem na vertical das JTABLE
        jTBDadosPessoas.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); 
        jTBContato.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); 
        jTBEndereco.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); 
        jTBPermissoes.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); 
                
        //Desabilita todos os campos
        validaCampos.desabilitaCampos(jPPessoa);
        validaCampos.desabilitaCampos(jPTipoPessoa);
        validaCampos.desabilitaCampos(jPContato);
        validaCampos.desabilitaCampos(jPEndereco);
        validaCampos.desabilitaCampos(jPPermissoes); 
        validaCampos.desabilitaCampos(jPUsuario); 
        
        //Desabilita botões especificos da tela
        jBTAddContato.setEnabled(false);
        jBTRemoveContato.setEnabled(false);
        jBTAddEndereco.setEnabled(false);
        jBTRemoveEndereco.setEnabled(false);
        jBTAddPermissao.setEnabled(false);
        jBTRemovePermissao.setEnabled(false);
        
        //Define a situação como inicial para habilitar os botoes utilizados apenas quando inicia a tela
        situacao = Rotinas.INICIAL;
        
        //habilita os botoes utilizados na inicialização da tela
        validabotoes.ValidaEstado(jPBotoes, situacao);  
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTBPessoas = new javax.swing.JTabbedPane();
        jPCadastroPessoa = new javax.swing.JPanel();
        jPPessoa = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTFIDPessoa = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTFNome = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTFRazaoSocial = new javax.swing.JTextField();
        jRBCPF = new javax.swing.JRadioButton();
        jRBCNPJ = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jRBMasc = new javax.swing.JRadioButton();
        jRBFem = new javax.swing.JRadioButton();
        jFTDataNasc = new javax.swing.JFormattedTextField();
        jFTCPFCNPJ = new javax.swing.JFormattedTextField();
        jCBCalibracoes = new javax.swing.JCheckBox();
        jLabel11 = new javax.swing.JLabel();
        JTFSite = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jTFRamo = new javax.swing.JTextField();
        jFTRG = new javax.swing.JFormattedTextField();
        jPBotoes = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jTBPAdicionais = new javax.swing.JTabbedPane();
        jPContato = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTBContato = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jCBTipoContato = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        jTFDescContato = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jBTAddContato = new javax.swing.JButton();
        jBTRemoveContato = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jTFEmail = new javax.swing.JTextField();
        jFTNumeroContato = new javax.swing.JFormattedTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTBEndereco = new javax.swing.JTable();
        jBTRemoveEndereco = new javax.swing.JButton();
        jBTAddEndereco = new javax.swing.JButton();
        jPEndereco = new javax.swing.JPanel();
        jTFNumeroEnd = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jTFUF = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jCBCidade = new javax.swing.JComboBox();
        jLabel24 = new javax.swing.JLabel();
        jTFRua = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jTFBairro = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jTFDescEnd = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jFTCep = new javax.swing.JFormattedTextField();
        jPanel4 = new javax.swing.JPanel();
        jPPermissoes = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jCBTela = new javax.swing.JComboBox();
        jLabel26 = new javax.swing.JLabel();
        jCBGerente = new javax.swing.JCheckBox();
        jCBInserir = new javax.swing.JCheckBox();
        jCBAlterar = new javax.swing.JCheckBox();
        jCBExcluir = new javax.swing.JCheckBox();
        jCBConsultar = new javax.swing.JCheckBox();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTBPermissoes = new javax.swing.JTable();
        jBTAddPermissao = new javax.swing.JButton();
        jBTRemovePermissao = new javax.swing.JButton();
        jPUsuario = new javax.swing.JPanel();
        jCBMostSenha = new javax.swing.JCheckBox();
        jTFLogin = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jPFSenha = new javax.swing.JPasswordField();
        jLabel28 = new javax.swing.JLabel();
        jPTipoPessoa = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jRBPessoaJuridica = new javax.swing.JRadioButton();
        jRBPessoaFisica = new javax.swing.JRadioButton();
        jLabel17 = new javax.swing.JLabel();
        jRBCertificadora = new javax.swing.JRadioButton();
        jRBFornecedor = new javax.swing.JRadioButton();
        jCBInternacional = new javax.swing.JCheckBox();
        jTFData = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jCBBuscarPessoa = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();
        jCBBuscarPor = new javax.swing.JComboBox();
        jTFFiltro = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jBTBuscar = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTBDadosPessoas = new javax.swing.JTable();
        jLabel30 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        addWindowStateListener(new java.awt.event.WindowStateListener() {
            public void windowStateChanged(java.awt.event.WindowEvent evt) {
                formWindowStateChanged(evt);
            }
        });

        jLabel1.setText("ID Pessoa:");

        jTFIDPessoa.setEditable(false);
        jTFIDPessoa.setToolTipText("ID_Pessoa");
        jTFIDPessoa.setName("id_pessoa"); // NOI18N

        jLabel2.setText("Nome:");

        jTFNome.setToolTipText("Nome");
        jTFNome.setName("nome"); // NOI18N

        jLabel3.setText("Razão Social:");

        jTFRazaoSocial.setToolTipText("Razão Social");
        jTFRazaoSocial.setName("razao_social"); // NOI18N

        jRBCPF.setText("CPF");
        jRBCPF.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRBCPFMouseClicked(evt);
            }
        });

        jRBCNPJ.setText("CNPJ");

        jLabel5.setText("RG:");

        jLabel6.setText("Data Nascimento:");

        jLabel7.setText("Sexo:");

        jRBMasc.setText("Masculino");
        jRBMasc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRBMascMouseClicked(evt);
            }
        });
        jRBMasc.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jRBMascStateChanged(evt);
            }
        });

        jRBFem.setText("Feminino");
        jRBFem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRBFemMouseClicked(evt);
            }
        });
        jRBFem.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jRBFemStateChanged(evt);
            }
        });

        jFTDataNasc.setToolTipText("Data Nascimento");
        jFTDataNasc.setName("dt_nasc"); // NOI18N
        jFTDataNasc.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jFTDataNascFocusLost(evt);
            }
        });

        jFTCPFCNPJ.setToolTipText("CPF/CNPJ");
        jFTCPFCNPJ.setName("cpf_cnpj"); // NOI18N
        jFTCPFCNPJ.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jFTCPFCNPJMouseExited(evt);
            }
        });
        jFTCPFCNPJ.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jFTCPFCNPJFocusLost(evt);
            }
        });

        jCBCalibracoes.setText("Calibrações");

        jLabel11.setText("Site:");

        JTFSite.setToolTipText("Site");
        JTFSite.setName("site"); // NOI18N

        jLabel12.setText("Ramo:");

        jTFRamo.setToolTipText("Ramo");
        jTFRamo.setName("ramo"); // NOI18N

        jFTRG.setToolTipText("RG");
        jFTRG.setName("rg"); // NOI18N
        jFTRG.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jFTRGFocusLost(evt);
            }
        });

        javax.swing.GroupLayout jPPessoaLayout = new javax.swing.GroupLayout(jPPessoa);
        jPPessoa.setLayout(jPPessoaLayout);
        jPPessoaLayout.setHorizontalGroup(
            jPPessoaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPPessoaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPPessoaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPPessoaLayout.createSequentialGroup()
                        .addGroup(jPPessoaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jFTDataNasc, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(43, 43, 43)
                        .addGroup(jPPessoaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addGroup(jPPessoaLayout.createSequentialGroup()
                                .addComponent(jRBMasc)
                                .addGap(10, 10, 10)
                                .addComponent(jRBFem)))
                        .addGap(54, 54, 54)
                        .addGroup(jPPessoaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPPessoaLayout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(244, 405, Short.MAX_VALUE))
                            .addGroup(jPPessoaLayout.createSequentialGroup()
                                .addComponent(jTFRamo, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37)
                                .addComponent(jCBCalibracoes)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPPessoaLayout.createSequentialGroup()
                        .addGroup(jPPessoaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTFIDPessoa, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(jPPessoaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jTFNome, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(jPPessoaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jTFRazaoSocial, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPPessoaLayout.createSequentialGroup()
                        .addGroup(jPPessoaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPPessoaLayout.createSequentialGroup()
                                .addComponent(jRBCPF)
                                .addGap(10, 10, 10)
                                .addComponent(jRBCNPJ))
                            .addComponent(jFTCPFCNPJ, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPPessoaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPPessoaLayout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(184, 184, 184))
                            .addGroup(jPPessoaLayout.createSequentialGroup()
                                .addComponent(jFTRG)
                                .addGap(24, 24, 24)))
                        .addGroup(jPPessoaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(JTFSite)
                            .addGroup(jPPessoaLayout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(244, 244, 244)))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPPessoaLayout.setVerticalGroup(
            jPPessoaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPPessoaLayout.createSequentialGroup()
                .addGroup(jPPessoaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPPessoaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTFIDPessoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTFNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTFRazaoSocial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPPessoaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRBCPF)
                    .addComponent(jRBCNPJ)
                    .addComponent(jLabel5)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPPessoaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFTCPFCNPJ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JTFSite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFTRG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPPessoaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPPessoaLayout.createSequentialGroup()
                        .addGroup(jPPessoaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFTDataNasc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPPessoaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jRBMasc)
                        .addComponent(jRBFem)
                        .addComponent(jCBCalibracoes)
                        .addComponent(jTFRamo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton1.setIcon(new javax.swing.ImageIcon("D:\\MEUS ARQUIVOS\\arquivos faculdade\\6PERIODO\\TCCII\\ICONES\\Botoes_Site_5752_Knob_Add.png")); // NOI18N
        jButton1.setText("Novo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon("D:\\MEUS ARQUIVOS\\arquivos faculdade\\6PERIODO\\TCCII\\ICONES\\Botoes_5122_pencil_48.png")); // NOI18N
        jButton2.setText("Alterar");

        jButton3.setIcon(new javax.swing.ImageIcon("D:\\MEUS ARQUIVOS\\arquivos faculdade\\6PERIODO\\TCCII\\ICONES\\Botoes_Site_5751_Knob_Remove_Red.png")); // NOI18N
        jButton3.setText("Excluir");

        jButton4.setIcon(new javax.swing.ImageIcon("D:\\MEUS ARQUIVOS\\arquivos faculdade\\6PERIODO\\TCCII\\ICONES\\Botoes_Site_5750_Knob_Cancel.png")); // NOI18N
        jButton4.setText("Cancelar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
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

        javax.swing.GroupLayout jPBotoesLayout = new javax.swing.GroupLayout(jPBotoes);
        jPBotoes.setLayout(jPBotoesLayout);
        jPBotoesLayout.setHorizontalGroup(
            jPBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPBotoesLayout.createSequentialGroup()
                .addGap(78, 78, 78)
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
            .addGroup(jPBotoesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTBContato.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sel", "ID Contato", "Tipo", "Descrição", "Numero", "Email"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTBContato);
        if (jTBContato.getColumnModel().getColumnCount() > 0) {
            jTBContato.getColumnModel().getColumn(1).setMinWidth(1);
            jTBContato.getColumnModel().getColumn(2).setMinWidth(1);
        }

        jLabel8.setText("Tipo:");

        jCBTipoContato.setToolTipText("Tipo");
        jCBTipoContato.setName("tipo"); // NOI18N
        jCBTipoContato.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCBTipoContatoMouseClicked(evt);
            }
        });
        jCBTipoContato.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCBTipoContatoItemStateChanged(evt);
            }
        });

        jLabel9.setText("Descrição:");

        jTFDescContato.setToolTipText("Descrição");
        jTFDescContato.setName("descricao"); // NOI18N

        jLabel10.setText("Numero:");

        jBTAddContato.setIcon(new javax.swing.ImageIcon("D:\\MEUS ARQUIVOS\\arquivos faculdade\\6PERIODO\\TCCII\\ICONES\\Botoes_Site_5752_Knob_Add.png")); // NOI18N
        jBTAddContato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTAddContatoActionPerformed(evt);
            }
        });

        jBTRemoveContato.setIcon(new javax.swing.ImageIcon("D:\\MEUS ARQUIVOS\\arquivos faculdade\\6PERIODO\\TCCII\\ICONES\\Botoes_Site_5751_Knob_Remove_Red.png")); // NOI18N
        jBTRemoveContato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTRemoveContatoActionPerformed(evt);
            }
        });

        jLabel18.setText("Email:");

        jTFEmail.setToolTipText("Email");
        jTFEmail.setName("email"); // NOI18N

        jFTNumeroContato.setToolTipText("Numero");
        jFTNumeroContato.setName("numero"); // NOI18N
        jFTNumeroContato.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jFTNumeroContatoMouseClicked(evt);
            }
        });
        jFTNumeroContato.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jFTNumeroContatoFocusLost(evt);
            }
        });

        javax.swing.GroupLayout jPContatoLayout = new javax.swing.GroupLayout(jPContato);
        jPContato.setLayout(jPContatoLayout);
        jPContatoLayout.setHorizontalGroup(
            jPContatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPContatoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPContatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPContatoLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 744, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPContatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jBTAddContato, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBTRemoveContato, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPContatoLayout.createSequentialGroup()
                        .addGroup(jPContatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jCBTipoContato, 0, 135, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jFTNumeroContato))
                        .addGap(34, 34, 34)
                        .addGroup(jPContatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jTFDescContato, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18)
                            .addComponent(jTFEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel10))
                .addContainerGap(65, Short.MAX_VALUE))
        );
        jPContatoLayout.setVerticalGroup(
            jPContatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPContatoLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPContatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPContatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCBTipoContato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTFDescContato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPContatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPContatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTFEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFTNumeroContato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPContatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPContatoLayout.createSequentialGroup()
                        .addComponent(jBTAddContato, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBTRemoveContato, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33))
        );

        jTBPAdicionais.addTab("Contato", jPContato);

        jTBEndereco.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sel", "ID Endereço", "Descrição", "Rua", "Nº", "Bairro", "CEP", "Cidade", "UF"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTBEndereco);
        if (jTBEndereco.getColumnModel().getColumnCount() > 0) {
            jTBEndereco.getColumnModel().getColumn(1).setMinWidth(1);
            jTBEndereco.getColumnModel().getColumn(2).setMinWidth(1);
            jTBEndereco.getColumnModel().getColumn(7).setResizable(false);
        }

        jBTRemoveEndereco.setIcon(new javax.swing.ImageIcon("D:\\MEUS ARQUIVOS\\arquivos faculdade\\6PERIODO\\TCCII\\ICONES\\Botoes_Site_5751_Knob_Remove_Red.png")); // NOI18N
        jBTRemoveEndereco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTRemoveEnderecoActionPerformed(evt);
            }
        });

        jBTAddEndereco.setIcon(new javax.swing.ImageIcon("D:\\MEUS ARQUIVOS\\arquivos faculdade\\6PERIODO\\TCCII\\ICONES\\Botoes_Site_5752_Knob_Add.png")); // NOI18N
        jBTAddEndereco.setName("descricao"); // NOI18N
        jBTAddEndereco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTAddEnderecoActionPerformed(evt);
            }
        });

        jTFNumeroEnd.setToolTipText("Nº");
        jTFNumeroEnd.setName("numero"); // NOI18N
        jTFNumeroEnd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFNumeroEndActionPerformed(evt);
            }
        });

        jLabel21.setText("Nº:");

        jTFUF.setEditable(false);
        jTFUF.setName("uf"); // NOI18N
        jTFUF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFUFActionPerformed(evt);
            }
        });

        jLabel25.setText("UF:");

        jCBCidade.setName("cidade"); // NOI18N
        jCBCidade.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCBCidadeMouseClicked(evt);
            }
        });
        jCBCidade.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                jCBCidadePopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
                jCBCidadePopupMenuWillBecomeVisible(evt);
            }
        });
        jCBCidade.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCBCidadeItemStateChanged(evt);
            }
        });
        jCBCidade.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                jCBCidadeInputMethodTextChanged(evt);
            }
        });

        jLabel24.setText("Cidade");

        jTFRua.setToolTipText("Rua");
        jTFRua.setName("rua"); // NOI18N

        jLabel20.setText("Rua:");

        jLabel23.setText("CEP:");

        jTFBairro.setToolTipText("Bairro");
        jTFBairro.setName("bairro"); // NOI18N

        jLabel22.setText("Bairro:");

        jTFDescEnd.setToolTipText("Descrição");
        jTFDescEnd.setName("descricao"); // NOI18N

        jLabel19.setText("Descrição:");

        jFTCep.setToolTipText("CEP");
        jFTCep.setName("cep"); // NOI18N
        jFTCep.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jFTCepFocusLost(evt);
            }
        });

        javax.swing.GroupLayout jPEnderecoLayout = new javax.swing.GroupLayout(jPEndereco);
        jPEndereco.setLayout(jPEnderecoLayout);
        jPEnderecoLayout.setHorizontalGroup(
            jPEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPEnderecoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPEnderecoLayout.createSequentialGroup()
                        .addGroup(jPEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addComponent(jTFDescEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTFRua, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20))
                        .addGap(9, 9, 9)
                        .addGroup(jPEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTFNumeroEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21)))
                    .addGroup(jPEnderecoLayout.createSequentialGroup()
                        .addGroup(jPEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPEnderecoLayout.createSequentialGroup()
                                .addComponent(jTFBairro)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jFTCep, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(11, 11, 11))
                            .addGroup(jPEnderecoLayout.createSequentialGroup()
                                .addComponent(jLabel22)
                                .addGap(242, 242, 242)
                                .addComponent(jLabel23)
                                .addGap(163, 163, 163)))
                        .addGroup(jPEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel24)
                            .addComponent(jCBCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPEnderecoLayout.createSequentialGroup()
                                .addComponent(jLabel25)
                                .addGap(71, 71, 71))
                            .addComponent(jTFUF))))
                .addContainerGap())
        );
        jPEnderecoLayout.setVerticalGroup(
            jPEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPEnderecoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jLabel20)
                    .addComponent(jLabel21))
                .addGap(4, 4, 4)
                .addGroup(jPEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTFDescEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTFRua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTFNumeroEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPEnderecoLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23)
                            .addComponent(jLabel22)
                            .addComponent(jLabel24)
                            .addComponent(jLabel25))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTFUF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCBCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPEnderecoLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTFBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFTCep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 744, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jBTAddEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBTRemoveEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(56, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jBTAddEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBTRemoveEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jTBPAdicionais.addTab("Endereço", jPanel3);

        jLabel15.setText("Permissões de acesso:");

        jCBTela.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                jCBTelaPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jLabel26.setText("Tela:");

        jCBGerente.setText("Gerente");
        jCBGerente.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCBGerenteStateChanged(evt);
            }
        });

        jCBInserir.setText("Inserir");

        jCBAlterar.setText("Alterar");

        jCBExcluir.setText("Excluir");

        jCBConsultar.setText("Consultar");

        javax.swing.GroupLayout jPPermissoesLayout = new javax.swing.GroupLayout(jPPermissoes);
        jPPermissoes.setLayout(jPPermissoesLayout);
        jPPermissoesLayout.setHorizontalGroup(
            jPPermissoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPPermissoesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPPermissoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPPermissoesLayout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPPermissoesLayout.createSequentialGroup()
                        .addGroup(jPPermissoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator1)
                            .addGroup(jPPermissoesLayout.createSequentialGroup()
                                .addGroup(jPPermissoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPPermissoesLayout.createSequentialGroup()
                                        .addComponent(jCBGerente)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                                        .addComponent(jLabel26)
                                        .addGap(18, 18, 18)
                                        .addComponent(jCBTela, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPPermissoesLayout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jCBInserir)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jCBAlterar)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jCBExcluir)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jCBConsultar)))
                                .addGap(14, 14, 14)))
                        .addGap(18, 18, 18))))
        );
        jPPermissoesLayout.setVerticalGroup(
            jPPermissoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPPermissoesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPPermissoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPPermissoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCBTela, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel26))
                    .addComponent(jCBGerente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPPermissoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCBAlterar)
                    .addComponent(jCBInserir)
                    .addComponent(jCBExcluir)
                    .addComponent(jCBConsultar))
                .addGap(60, 60, 60))
        );

        jTBPermissoes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sel", "ID Permissao", "ID Tela", "Tela", "Acesso", "Inserir", "Alterar", "Excluir", "Consultar"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTBPermissoes);
        if (jTBPermissoes.getColumnModel().getColumnCount() > 0) {
            jTBPermissoes.getColumnModel().getColumn(1).setMinWidth(1);
            jTBPermissoes.getColumnModel().getColumn(2).setMinWidth(1);
            jTBPermissoes.getColumnModel().getColumn(7).setResizable(false);
        }

        jBTAddPermissao.setIcon(new javax.swing.ImageIcon("D:\\MEUS ARQUIVOS\\arquivos faculdade\\6PERIODO\\TCCII\\ICONES\\Botoes_Site_5752_Knob_Add.png")); // NOI18N
        jBTAddPermissao.setName("descricao"); // NOI18N
        jBTAddPermissao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTAddPermissaoActionPerformed(evt);
            }
        });

        jBTRemovePermissao.setIcon(new javax.swing.ImageIcon("D:\\MEUS ARQUIVOS\\arquivos faculdade\\6PERIODO\\TCCII\\ICONES\\Botoes_Site_5751_Knob_Remove_Red.png")); // NOI18N
        jBTRemovePermissao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTRemovePermissaoActionPerformed(evt);
            }
        });

        jCBMostSenha.setText("Mostrar senha");
        jCBMostSenha.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCBMostSenhaStateChanged(evt);
            }
        });
        jCBMostSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBMostSenhaActionPerformed(evt);
            }
        });

        jTFLogin.setToolTipText("Login");
        jTFLogin.setName("login"); // NOI18N
        jTFLogin.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTFLoginFocusLost(evt);
            }
        });

        jLabel27.setText("Login:");

        jPFSenha.setToolTipText("Senha");
        jPFSenha.setName("senha"); // NOI18N

        jLabel28.setText("Senha:");

        javax.swing.GroupLayout jPUsuarioLayout = new javax.swing.GroupLayout(jPUsuario);
        jPUsuario.setLayout(jPUsuarioLayout);
        jPUsuarioLayout.setHorizontalGroup(
            jPUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPUsuarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel27)
                    .addComponent(jLabel28)
                    .addGroup(jPUsuarioLayout.createSequentialGroup()
                        .addGroup(jPUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPFSenha, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                            .addComponent(jTFLogin))
                        .addGap(18, 18, 18)
                        .addComponent(jCBMostSenha)))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPUsuarioLayout.setVerticalGroup(
            jPUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPUsuarioLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTFLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel28)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jPFSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCBMostSenha))
                .addGap(35, 35, 35))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(406, Short.MAX_VALUE)
                .addComponent(jPPermissoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 757, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBTAddPermissao, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBTRemovePermissao, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(456, Short.MAX_VALUE)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPPermissoes, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jBTAddPermissao, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBTRemovePermissao, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 77, Short.MAX_VALUE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addComponent(jPUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 163, Short.MAX_VALUE)))
        );

        jTBPAdicionais.addTab("Permissões de Acesso", jPanel4);

        jLabel16.setText("Pessoa:");

        jRBPessoaJuridica.setText("Pessoa Jurídica");
        jRBPessoaJuridica.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRBPessoaJuridicaMouseClicked(evt);
            }
        });
        jRBPessoaJuridica.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jRBPessoaJuridicaStateChanged(evt);
            }
        });
        jRBPessoaJuridica.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRBPessoaJuridicaItemStateChanged(evt);
            }
        });
        jRBPessoaJuridica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBPessoaJuridicaActionPerformed(evt);
            }
        });

        jRBPessoaFisica.setText("Pessoa Física");
        jRBPessoaFisica.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRBPessoaFisicaMouseClicked(evt);
            }
        });
        jRBPessoaFisica.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jRBPessoaFisicaStateChanged(evt);
            }
        });
        jRBPessoaFisica.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRBPessoaFisicaItemStateChanged(evt);
            }
        });

        jLabel17.setText("Jurídica:");

        jRBCertificadora.setText("Certificadora");
        jRBCertificadora.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRBCertificadoraMouseClicked(evt);
            }
        });
        jRBCertificadora.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jRBCertificadoraStateChanged(evt);
            }
        });
        jRBCertificadora.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRBCertificadoraItemStateChanged(evt);
            }
        });
        jRBCertificadora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBCertificadoraActionPerformed(evt);
            }
        });

        jRBFornecedor.setText("Fornecedor");
        jRBFornecedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRBFornecedorMouseClicked(evt);
            }
        });
        jRBFornecedor.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jRBFornecedorStateChanged(evt);
            }
        });
        jRBFornecedor.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRBFornecedorItemStateChanged(evt);
            }
        });

        jCBInternacional.setText("Empresa Internacional");
        jCBInternacional.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCBInternacionalStateChanged(evt);
            }
        });

        jTFData.setEditable(false);
        jTFData.setName("data_cadastro"); // NOI18N

        jLabel4.setText("Data:");

        javax.swing.GroupLayout jPTipoPessoaLayout = new javax.swing.GroupLayout(jPTipoPessoa);
        jPTipoPessoa.setLayout(jPTipoPessoaLayout);
        jPTipoPessoaLayout.setHorizontalGroup(
            jPTipoPessoaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPTipoPessoaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRBPessoaJuridica)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRBPessoaFisica)
                .addGap(32, 32, 32)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRBCertificadora)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRBFornecedor)
                .addGap(34, 34, 34)
                .addComponent(jCBInternacional)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTFData, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPTipoPessoaLayout.setVerticalGroup(
            jPTipoPessoaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPTipoPessoaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPTipoPessoaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPTipoPessoaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(jTFData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPTipoPessoaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jRBCertificadora)
                        .addComponent(jRBFornecedor)
                        .addComponent(jLabel17)
                        .addComponent(jCBInternacional))
                    .addGroup(jPTipoPessoaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jRBPessoaJuridica)
                        .addComponent(jRBPessoaFisica))
                    .addComponent(jLabel16))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPCadastroPessoaLayout = new javax.swing.GroupLayout(jPCadastroPessoa);
        jPCadastroPessoa.setLayout(jPCadastroPessoaLayout);
        jPCadastroPessoaLayout.setHorizontalGroup(
            jPCadastroPessoaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPCadastroPessoaLayout.createSequentialGroup()
                .addGroup(jPCadastroPessoaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPCadastroPessoaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPCadastroPessoaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTBPAdicionais, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jPBotoes, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPPessoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPCadastroPessoaLayout.createSequentialGroup()
                        .addComponent(jPTipoPessoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPCadastroPessoaLayout.setVerticalGroup(
            jPCadastroPessoaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPCadastroPessoaLayout.createSequentialGroup()
                .addComponent(jPTipoPessoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPPessoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jTBPAdicionais)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPBotoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        jTBPessoas.addTab("Cadastro", jPCadastroPessoa);

        jLabel13.setText("Pessoa:");

        jCBBuscarPessoa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecione Pessoa", "Usuário", "Certificadora", "Fornecedor" }));

        jLabel14.setText("Buscar por:");

        jCBBuscarPor.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Geral", "Código", "Descrição" }));

        jLabel29.setText("Filtro de busca:");

        jBTBuscar.setText("Buscar");
        jBTBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTBuscarActionPerformed(evt);
            }
        });

        jTBDadosPessoas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Pessoa", "Login", "Descrição", "Razão Social", "CPF/CNPJ", "RG", "Sexo", "Data.Nasc", "Ultima alteração"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTBDadosPessoas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTBDadosPessoasMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTBDadosPessoas);

        jLabel30.setText("Pessoa:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13)
                                    .addComponent(jCBBuscarPessoa, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jCBBuscarPor, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel14))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel29)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jTFFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jBTBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jLabel30))
                        .addGap(0, 130, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addComponent(jLabel29))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCBBuscarPessoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCBBuscarPor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTFFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBTBuscar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel30)
                .addGap(7, 7, 7)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 551, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTBPessoas.addTab("Consulta", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTBPessoas)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTBPessoas, javax.swing.GroupLayout.PREFERRED_SIZE, 676, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(900, 714));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        int retorno_mensagem;
        try
        {
            if(jRBPessoaFisica.isSelected()){
                if (validaCampos.validacamposobrigatorios(jPPessoa, "PESSOA") == 0){ 
                    if (validaCampos.validacamposobrigatorios(jPPessoa, "PESSOA_FISICA") == 0){
                        if(validaCampos.validacamposobrigatorios(jPUsuario, "USUARIO") == 0){
                            //Se alguma permissão foi adicionada
                            if((validaCampos.VerificaJtable(jTBPermissoes) == 1) || jCBGerente.isSelected()){
                            
                                //Pega atributos da tela de cadastro de pessoa fisica.
                                getcompPessoaFisica();

                                //Pega atributos da tela de permissões de acesso
                                getPermissoes(); 

                                //se não estiver preenchido os dados de contato
                                if((jCBTipoContato.getSelectedIndex() == 0)){
                                    //Retorno da mensagem: 0-sim 1-não;
                                    retorno_mensagem = mensagem.ValidaMensagem("Deseja salvar registro sem o contato?");

                                    if(retorno_mensagem == 0){
                                        //se não estiver preenchido os dados de endereço
                                        if(validaCampos.VerificaJtable(jTBEndereco) == 0){
                                            retorno_mensagem = mensagem.ValidaMensagem("Deseja salvar registro sem o endereço?");

                                            if(retorno_mensagem == 0){

                                                //Inclui pessoa fisica
                                                dao_pessoa.incluir(usuario);   
                                                dao_permissao.gravarPermissao(permissao);
                                                JOptionPane.showMessageDialog(null, "Salvo com Sucesso");

                                                //Define a situação como cancelar para habilitar os botoes utilizados apenas quando cancela um processo
                                                situacao = Rotinas.INICIAL;

                                                //habilita os botoes utilizados quando cancela um processo
                                                validabotoes.ValidaEstado(jPBotoes, situacao);

                                                //Limpa os campos do container pessoa
                                                validaCampos.LimparCampos(jPPessoa);
                                                validaCampos.LimparCampos(jPTipoPessoa);
                                               //Limpa os campos do container contato
                                                validaCampos.LimparCampos(jPContato);
                                                validaCampos.LimparJtable(jTBContato);
                                                //Limpa os campos do container endereco
                                                validaCampos.LimparCampos(jPEndereco);
                                                validaCampos.LimparJtable(jTBEndereco);
                                                //Limpa os campos do container permissoes
                                                validaCampos.LimparCampos(jPPermissoes);
                                                validaCampos.LimparJtable(jTBPermissoes);
                                                //Limpa os campos do container usuario
                                                validaCampos.LimparCampos(jPUsuario);

                                                //Desabilita todos os campos do container pessoa
                                                validaCampos.desabilitaCampos(jPPessoa);
                                                validaCampos.desabilitaCampos(jPTipoPessoa);
                                                validaCampos.desabilitaCampos(jPEndereco);
                                                //Desabilita todos os campos do container contato
                                                validaCampos.desabilitaCampos(jPContato);
                                                //Desabilita todos os campos do container permissões
                                                validaCampos.desabilitaCampos(jPPermissoes);
                                                //Desabilita todos os campos do container usuário
                                                validaCampos.desabilitaCampos(jPUsuario);
                                                //Desabilita os botoes especificos da tela
                                                jBTAddContato.setEnabled(false);
                                                jBTRemoveContato.setEnabled(false);
                                                jBTAddEndereco.setEnabled(false);
                                                jBTRemoveEndereco.setEnabled(false);
                                                jBTAddPermissao.setEnabled(false);
                                                jBTRemovePermissao.setEnabled(false);
                                            }else{
                                                //Seta foco na aba de endereço
                                                 jTBPAdicionais.setSelectedIndex(1);
                                            }
                                        }else{
                                                //Salva Pessoa com endereço 

                                                //Inclui pessoa fisica
                                                dao_pessoa.incluir(usuario);   

                                                //Inclui endereço
                                                dao_endereco.gravarEndereco(endereco);
                                                
                                                //Inclui permissões
                                                dao_permissao.gravarPermissao(permissao);

                                                JOptionPane.showMessageDialog(null, "Salvo com Sucesso");

                                                //Define a situação como cancelar para habilitar os botoes utilizados apenas quando cancela um processo
                                                situacao = Rotinas.INICIAL;

                                                //habilita os botoes utilizados quando cancela um processo
                                                validabotoes.ValidaEstado(jPBotoes, situacao);

                                                //Limpa os campos do container pessoa
                                                validaCampos.LimparCampos(jPPessoa);
                                                validaCampos.LimparCampos(jPTipoPessoa);
                                                //Limpa os campos do container contato
                                                validaCampos.LimparCampos(jPContato);
                                                validaCampos.LimparJtable(jTBContato);
                                                //Limpa os campos do container endereco
                                                validaCampos.LimparCampos(jPEndereco);
                                                validaCampos.LimparJtable(jTBEndereco);
                                                //Limpa os campos do container permissoes
                                                validaCampos.LimparCampos(jPPermissoes);
                                                validaCampos.LimparJtable(jTBPermissoes);
                                                //Limpa os campos do container usuario
                                                validaCampos.LimparCampos(jPUsuario);

                                                //Desabilita todos os campos do container pessoa
                                                validaCampos.desabilitaCampos(jPPessoa);
                                                validaCampos.desabilitaCampos(jPTipoPessoa);
                                                //Desabilita todos os campos do container contato
                                                validaCampos.desabilitaCampos(jPContato);
                                                //Desabilita todos os campos do container endereco
                                                validaCampos.desabilitaCampos(jPEndereco);
                                                //Desabilita todos os campos do container usuario
                                                validaCampos.desabilitaCampos(jPUsuario);
                                                //Desabilita todos os campos do container permissoes
                                                validaCampos.desabilitaCampos(jPPermissoes);

                                                //Desabilita os botoes especificos da tela
                                                jBTAddContato.setEnabled(false);
                                                jBTRemoveContato.setEnabled(false);
                                                jBTAddEndereco.setEnabled(false);
                                                jBTRemoveEndereco.setEnabled(false);
                                                jBTAddPermissao.setEnabled(false);
                                                jBTRemovePermissao.setEnabled(false);
                                        }
                                    }else{
                                     //Seta o foco na aba de contato
                                     jTBPAdicionais.setSelectedIndex(0);
                                    }
                                //Salvar pessoa e contato
                                } else{
                                    if(validaCampos.VerificaJtable(jTBContato) == 0){
                                        JOptionPane.showMessageDialog(null, "Favor, adicionar dados do contato");
                                        //Seta foco na aba de contato
                                        jTBPAdicionais.setSelectedIndex(0);
                                    }else{
                                             //se não estiver preenchido os dados de endereço
                                            if(validaCampos.VerificaJtable(jTBEndereco) == 0){
                                                retorno_mensagem = mensagem.ValidaMensagem("Deseja salvar registro sem o endereço?");

                                            if(retorno_mensagem == 0){
                                                //Salva Pessoa sem endereço 

                                                //Inclui pessoa fisica
                                                dao_pessoa.incluir(usuario);   

                                                //Inclui contato
                                                dao_contato.gravarContatos(contato);
                                                //Inclui permissões de acesso
                                                dao_permissao.gravarPermissao(permissao);

                                                JOptionPane.showMessageDialog(null, "Salvo com Sucesso");

                                                //Define a situação como cancelar para habilitar os botoes utilizados apenas quando cancela um processo
                                                situacao = Rotinas.INICIAL;

                                                //habilita os botoes utilizados quando cancela um processo
                                                validabotoes.ValidaEstado(jPBotoes, situacao);

                                                //Limpa os campos do container pessoa
                                                validaCampos.LimparCampos(jPPessoa);
                                                validaCampos.LimparCampos(jPTipoPessoa);
                                                //Limpa os campos do container contato
                                                validaCampos.LimparCampos(jPContato);
                                                validaCampos.LimparJtable(jTBContato);
                                                //Limpa os campos do container endereco
                                                validaCampos.LimparCampos(jPEndereco);
                                                validaCampos.LimparJtable(jTBEndereco);
                                                //Limpa os campos do container permissões
                                                validaCampos.LimparCampos(jPPermissoes);
                                                validaCampos.LimparJtable(jTBPermissoes);
                                                //Limpa os campos do container usuario
                                                validaCampos.LimparCampos(jPUsuario);

                                                //Desabilita todos os campos do container pessoa
                                                validaCampos.desabilitaCampos(jPPessoa);
                                                validaCampos.desabilitaCampos(jPTipoPessoa);
                                                //Desabilita todos os campos do container contato
                                                validaCampos.desabilitaCampos(jPContato);
                                                //Desabilita todos os campos do container endereco
                                                validaCampos.desabilitaCampos(jPEndereco);
                                                //Desabilita todos os campos do container usuario
                                                validaCampos.desabilitaCampos(jPUsuario);
                                                //Desabilita todos os campos do container permissoes
                                                validaCampos.desabilitaCampos(jPPermissoes);
   
                                                //Desabilita os botoes especificos da tela
                                                jBTAddContato.setEnabled(false);
                                                jBTRemoveContato.setEnabled(false);
                                                jBTAddEndereco.setEnabled(false);
                                                jBTRemoveEndereco.setEnabled(false);
                                                jBTAddPermissao.setEnabled(false);
                                                jBTRemovePermissao.setEnabled(false);

                                            }else{
                                                 //Seta foco na aba de endereço
                                                 jTBPAdicionais.setSelectedIndex(1);
                                            }
                                            }else{

                                                //Salva Pessoa com contato e endereço 

                                                //Inclui pessoa fisica
                                                dao_pessoa.incluir(usuario);   

                                                //Inclui contato
                                                dao_contato.gravarContatos(contato);

                                                //Inclui endereço
                                                dao_endereco.gravarEndereco(endereco);
                                                
                                                //Inclui permissoes
                                                dao_permissao.gravarPermissao(permissao);

                                                JOptionPane.showMessageDialog(null, "Salvo com Sucesso");

                                                //Define a situação como cancelar para habilitar os botoes utilizados apenas quando cancela um processo
                                                situacao = Rotinas.INICIAL;

                                                //habilita os botoes utilizados quando cancela um processo
                                                validabotoes.ValidaEstado(jPBotoes, situacao);

                                                //Limpa os campos do container pessoa
                                                validaCampos.LimparCampos(jPPessoa);
                                                validaCampos.LimparCampos(jPTipoPessoa);
                                                //Limpa os campos do container contato
                                                validaCampos.LimparCampos(jPContato);
                                                validaCampos.LimparJtable(jTBContato);
                                                //Limpa os campos do container endereco
                                                validaCampos.LimparCampos(jPEndereco);
                                                validaCampos.LimparJtable(jTBEndereco);
                                                //Limpa os campos do container permissões
                                                validaCampos.LimparCampos(jPPermissoes);
                                                validaCampos.LimparJtable(jTBPermissoes);
                                                //Limpa os campos do container usuario
                                                validaCampos.LimparCampos(jPUsuario);

                                                //Desabilita todos os campos do container pessoa
                                                validaCampos.desabilitaCampos(jPPessoa);
                                                validaCampos.desabilitaCampos(jPTipoPessoa);
                                                //Desabilita todos os campos do container contato
                                                validaCampos.desabilitaCampos(jPContato);
                                                //Desabilita todos os campos do container endereco
                                                validaCampos.desabilitaCampos(jPEndereco);
                                                //Desabilita todos os campos do container usuario
                                                validaCampos.desabilitaCampos(jPUsuario);
                                                //Desabilita todos os campos do container permissoes
                                                validaCampos.desabilitaCampos(jPPermissoes);

                                                //Desabilita os botoes especificos da tela
                                                jBTAddContato.setEnabled(false);
                                                jBTRemoveContato.setEnabled(false);
                                                jBTAddEndereco.setEnabled(false);
                                                jBTRemoveEndereco.setEnabled(false);
                                                jBTAddPermissao.setEnabled(false);
                                                jBTRemovePermissao.setEnabled(false);

                                            }

                                        }

                                    }
                            }else{
                                JOptionPane.showMessageDialog(null, "Favor adicionar permissões de acesso do usuário");
                                jTBPAdicionais.setSelectedIndex(2);
                            }
                        }else{
                                jTBPAdicionais.setSelectedIndex(2);
                        }      
                    }
                }
            }else if(jRBPessoaJuridica.isSelected()){
                if(jRBCertificadora.isSelected()){
                    if (validaCampos.validacamposobrigatorios(jPPessoa, "PESSOA") == 0) { 
                         if (validaCampos.validacamposobrigatorios(jPPessoa, "PESSOA_JURIDICA") == 0) {
                             if (validaCampos.validacamposobrigatorios(jPPessoa, "CERTIFICADORA") == 0) {
                                //Pega atributos da tela de cadastro de pessoa juridica.
                                getcompCertificadora();
                                
                                //se não estiver preenchido os dados de contato
                                if((jCBTipoContato.getSelectedIndex() == 0)){
                                    //Retorno da mensagem: 0-sim 1-não;
                                    retorno_mensagem = mensagem.ValidaMensagem("Deseja salvar registro sem o contato?");
                            
                                    if(retorno_mensagem == 0){
                                        //se não estiver preenchido os dados de endereço
                                        if(validaCampos.VerificaJtable(jTBEndereco) == 0){
                                            retorno_mensagem = mensagem.ValidaMensagem("Deseja salvar registro sem o endereço?");

                                            if(retorno_mensagem == 0){
                                                //Inclui pessoa fisica
                                                dao_pessoa.incluir(certificadora);   
                                                JOptionPane.showMessageDialog(null, "Salvo com Sucesso");

                                                //Define a situação como cancelar para habilitar os botoes utilizados apenas quando cancela um processo
                                                situacao = Rotinas.INICIAL;

                                                //habilita os botoes utilizados quando cancela um processo
                                                validabotoes.ValidaEstado(jPBotoes, situacao);

                                                //Limpa os campos do container pessoa
                                                validaCampos.LimparCampos(jPPessoa);
                                                validaCampos.LimparCampos(jPTipoPessoa);
                                                //Limpa os campos do container contato
                                                validaCampos.LimparCampos(jPContato);
                                                validaCampos.LimparJtable(jTBContato);
                                                //Limpa os campos do container endereço
                                                validaCampos.LimparCampos(jPEndereco);
                                                validaCampos.LimparJtable(jTBEndereco);

                                                //Desabilita todos os campos do container pessoa
                                                validaCampos.desabilitaCampos(jPPessoa);
                                                validaCampos.desabilitaCampos(jPTipoPessoa);
                                                validaCampos.desabilitaCampos(jPEndereco);
                                                //Desabilita todos os campos do container contato
                                                validaCampos.desabilitaCampos(jPContato);
                                                //Desabilita os botoes especificos da tela
                                                jBTAddContato.setEnabled(false);
                                                jBTRemoveContato.setEnabled(false);
                                                jBTAddEndereco.setEnabled(false);
                                                jBTRemoveEndereco.setEnabled(false);
                                            }else{
                                                 //Seta foco na aba de endereço
                                                 jTBPAdicionais.setSelectedIndex(1);
                                            }
                                        }else{

                                                //Salva Pessoa com endereço 

                                                //Inclui pessoa fisica
                                                dao_pessoa.incluir(certificadora);   

                                                //Inclui endereço
                                                dao_endereco.gravarEndereco(endereco);

                                                JOptionPane.showMessageDialog(null, "Salvo com Sucesso");

                                                //Define a situação como cancelar para habilitar os botoes utilizados apenas quando cancela um processo
                                                situacao = Rotinas.INICIAL;

                                                //habilita os botoes utilizados quando cancela um processo
                                                validabotoes.ValidaEstado(jPBotoes, situacao);

                                                //Limpa os campos do container pessoa
                                                validaCampos.LimparCampos(jPPessoa);
                                                validaCampos.LimparCampos(jPTipoPessoa);
                                                //Limpa os campos do container contato
                                                validaCampos.LimparCampos(jPContato);
                                                validaCampos.LimparJtable(jTBContato);
                                                //Limpa os campos do container endereco
                                                validaCampos.LimparCampos(jPEndereco);
                                                validaCampos.LimparJtable(jTBEndereco);

                                                //Desabilita todos os campos do container pessoa
                                                validaCampos.desabilitaCampos(jPPessoa);
                                                validaCampos.desabilitaCampos(jPTipoPessoa);
                                                //Desabilita todos os campos do container contato
                                                validaCampos.desabilitaCampos(jPContato);
                                                //Desabilita todos os campos do container endereco
                                                validaCampos.desabilitaCampos(jPEndereco);

                                                //Desabilita os botoes especificos da tela
                                                jBTAddContato.setEnabled(false);
                                                jBTRemoveContato.setEnabled(false);
                                                jBTAddEndereco.setEnabled(false);
                                                jBTRemoveEndereco.setEnabled(false);
                                        }
                                    }else{
                                         //Seta foco na aba de contato
                                         jTBPAdicionais.setSelectedIndex(0);
                                    }
                        //Salvar pessoa e contato
                        } else{
                            if(validaCampos.VerificaJtable(jTBContato) == 0){
                                JOptionPane.showMessageDialog(null, "Favor, adicionar dados do contato");
                                //Seta foco na aba de contato
                                jTBPAdicionais.setSelectedIndex(0);
                            }else{
                                     //se não estiver preenchido os dados de endereço
                                    if(validaCampos.VerificaJtable(jTBEndereco) == 0){
                                    retorno_mensagem = mensagem.ValidaMensagem("Deseja salvar registro sem o endereço?");
                            
                                    if(retorno_mensagem == 0){
                                        //Salva Pessoa sem endereço 

                                        //Inclui pessoa fisica
                                        dao_pessoa.incluir(certificadora);   

                                        //Inclui contato
                                        //dao_contato.incluir(contato);
                                        dao_contato.gravarContatos(contato);

                                        JOptionPane.showMessageDialog(null, "Salvo com Sucesso");

                                        //Define a situação como cancelar para habilitar os botoes utilizados apenas quando cancela um processo
                                        situacao = Rotinas.INICIAL;

                                        //habilita os botoes utilizados quando cancela um processo
                                        validabotoes.ValidaEstado(jPBotoes, situacao);

                                        //Limpa os campos do container pessoa
                                        validaCampos.LimparCampos(jPPessoa);
                                        validaCampos.LimparCampos(jPTipoPessoa);
                                        //Limpa os campos do container contato
                                        validaCampos.LimparCampos(jPContato);
                                        validaCampos.LimparJtable(jTBContato);
                                        //Limpa os campos do container endereco
                                        validaCampos.LimparCampos(jPEndereco);
                                        validaCampos.LimparJtable(jTBEndereco);

                                        //Desabilita todos os campos do container pessoa
                                        validaCampos.desabilitaCampos(jPPessoa);
                                        validaCampos.desabilitaCampos(jPTipoPessoa);
                                        //Desabilita todos os campos do container contato
                                        validaCampos.desabilitaCampos(jPContato);
                                        //Desabilita todos os campos do container endereco
                                        validaCampos.desabilitaCampos(jPEndereco);
                                        //Desabilita os botoes especificos da tela
                                        jBTAddContato.setEnabled(false);
                                        jBTRemoveContato.setEnabled(false);
                                        jBTAddEndereco.setEnabled(false);
                                        jBTRemoveEndereco.setEnabled(false);
                                        
                                    }else{
                                         //Seta foco na aba de endereço
                                         jTBPAdicionais.setSelectedIndex(1);
                                    }
                                    }else{
                                                                        
                                        //Salva Pessoa com contato e endereço 

                                        //Inclui pessoa fisica
                                        dao_pessoa.incluir(certificadora);   

                                        //Inclui contato
                                        dao_contato.gravarContatos(contato);
                                        
                                        //Inclui endereço
                                        dao_endereco.gravarEndereco(endereco);

                                        JOptionPane.showMessageDialog(null, "Salvo com Sucesso");

                                        //Define a situação como cancelar para habilitar os botoes utilizados apenas quando cancela um processo
                                        situacao = Rotinas.INICIAL;

                                        //habilita os botoes utilizados quando cancela um processo
                                        validabotoes.ValidaEstado(jPBotoes, situacao);

                                        //Limpa os campos do container pessoa
                                        validaCampos.LimparCampos(jPPessoa);
                                        validaCampos.LimparCampos(jPTipoPessoa);
                                        //Limpa os campos do container contato
                                        validaCampos.LimparCampos(jPContato);
                                        validaCampos.LimparJtable(jTBContato);
                                        //Limpa os campos do container endereco
                                        validaCampos.LimparCampos(jPEndereco);
                                        validaCampos.LimparJtable(jTBEndereco);

                                        //Desabilita todos os campos do container pessoa
                                        validaCampos.desabilitaCampos(jPPessoa);
                                        validaCampos.desabilitaCampos(jPTipoPessoa);
                                        //Desabilita todos os campos do container contato
                                        validaCampos.desabilitaCampos(jPContato);
                                        //Desabilita todos os campos do container endereco
                                        validaCampos.desabilitaCampos(jPEndereco);
                                        
                                        //Desabilita os botoes especificos da tela
                                        jBTAddContato.setEnabled(false);
                                        jBTRemoveContato.setEnabled(false);
                                        jBTAddEndereco.setEnabled(false);
                                        jBTRemoveEndereco.setEnabled(false);
                                        
                                    }
                                   
                                }
                              }  
                            }
                        }
                    }
                }else if(jRBFornecedor.isSelected()){
                    
                    if (validaCampos.validacamposobrigatorios(jPPessoa, "PESSOA") == 0) { 
                         if (validaCampos.validacamposobrigatorios(jPPessoa, "PESSOA_JURIDICA") == 0) {
                             if (validaCampos.validacamposobrigatorios(jPPessoa, "FORNECEDOR") == 0) {
                                //Pega atributos da tela de cadastro de pessoa juridica.
                                getcompFornecedor();
                           
                                //se não estiver preenchido os dados de contato
                                if((jCBTipoContato.getSelectedIndex() == 0)){
                                    
                                    //Retorno da mensagem: 0-sim 1-não;
                                    retorno_mensagem = mensagem.ValidaMensagem("Deseja salvar registro sem o contato?");
                            
                                    if(retorno_mensagem == 0){

                                        //se não estiver preenchido os dados de endereço
                                        if(validaCampos.VerificaJtable(jTBEndereco) == 0){
                                            retorno_mensagem = mensagem.ValidaMensagem("Deseja salvar registro sem o endereço?");

                                            if(retorno_mensagem == 0){
                                                //Inclui pessoa fisica
                                                dao_pessoa.incluir(fornecedor);   
                                                JOptionPane.showMessageDialog(null, "Salvo com Sucesso");

                                                //Define a situação como cancelar para habilitar os botoes utilizados apenas quando cancela um processo
                                                situacao = Rotinas.INICIAL;

                                                //habilita os botoes utilizados quando cancela um processo
                                                validabotoes.ValidaEstado(jPBotoes, situacao);

                                                //Limpa os campos do container pessoa
                                                validaCampos.LimparCampos(jPPessoa);
                                                validaCampos.LimparCampos(jPTipoPessoa);
                                                //Limpa os campos do container contato
                                                validaCampos.LimparCampos(jPContato);
                                                validaCampos.LimparJtable(jTBContato);
                                                //Limpa os campos do container endereço
                                                validaCampos.LimparCampos(jPEndereco);
                                                validaCampos.LimparJtable(jTBEndereco);

                                                //Desabilita todos os campos do container pessoa
                                                validaCampos.desabilitaCampos(jPPessoa);
                                                validaCampos.desabilitaCampos(jPTipoPessoa);
                                                validaCampos.desabilitaCampos(jPEndereco);
                                                //Desabilita todos os campos do container contato
                                                validaCampos.desabilitaCampos(jPContato);
                                                //Desabilita os botoes especificos da tela
                                                jBTAddContato.setEnabled(false);
                                                jBTRemoveContato.setEnabled(false);
                                                jBTAddEndereco.setEnabled(false);
                                                jBTRemoveEndereco.setEnabled(false);
                                            }else{
                                                 //Seta foco na aba de endereço
                                                 jTBPAdicionais.setSelectedIndex(1);
                                            }
                                        }else{

                                                //Salva Pessoa com endereço 

                                                //Inclui fornecedor
                                                dao_pessoa.incluir(fornecedor);   

                                                //Inclui endereço
                                                dao_endereco.gravarEndereco(endereco);

                                                JOptionPane.showMessageDialog(null, "Salvo com Sucesso");

                                                //Define a situação como cancelar para habilitar os botoes utilizados apenas quando cancela um processo
                                                situacao = Rotinas.INICIAL;

                                                //habilita os botoes utilizados quando cancela um processo
                                                validabotoes.ValidaEstado(jPBotoes, situacao);

                                                //Limpa os campos do container pessoa
                                                validaCampos.LimparCampos(jPPessoa);
                                                validaCampos.LimparCampos(jPTipoPessoa);
                                                //Limpa os campos do container contato
                                                validaCampos.LimparCampos(jPContato);
                                                validaCampos.LimparJtable(jTBContato);
                                                //Limpa os campos do container endereco
                                                validaCampos.LimparCampos(jPEndereco);
                                                validaCampos.LimparJtable(jTBEndereco);

                                                //Desabilita todos os campos do container pessoa
                                                validaCampos.desabilitaCampos(jPPessoa);
                                                validaCampos.desabilitaCampos(jPTipoPessoa);
                                                //Desabilita todos os campos do container contato
                                                validaCampos.desabilitaCampos(jPContato);
                                                //Desabilita todos os campos do container endereco
                                                validaCampos.desabilitaCampos(jPEndereco);

                                                //Desabilita os botoes especificos da tela
                                                jBTAddContato.setEnabled(false);
                                                jBTRemoveContato.setEnabled(false);
                                                jBTAddEndereco.setEnabled(false);
                                                jBTRemoveEndereco.setEnabled(false);
                                        }
                                    }else{
                                         //Seta foco na aba de contato
                                         jTBPAdicionais.setSelectedIndex(0);
                                    }
                                //Salvar pessoa e contato
                                } else{
                                    if(validaCampos.VerificaJtable(jTBContato) == 0){
                                        JOptionPane.showMessageDialog(null, "Favor, adicionar dados do contato");
                                        //Seta foco na aba de contato
                                         jTBPAdicionais.setSelectedIndex(0);
                                    }else{
                                        //se não estiver preenchido os dados de endereço
                                        if(validaCampos.VerificaJtable(jTBEndereco) == 0){
                                            retorno_mensagem = mensagem.ValidaMensagem("Deseja salvar registro sem o endereço?");
                            
                                            if(retorno_mensagem == 0){
                                                //Salva fornecedor sem endereço 

                                                //Inclui fornecedor
                                                dao_pessoa.incluir(fornecedor);   

                                                //Inclui contato
                                                //dao_contato.incluir(contato);
                                                dao_contato.gravarContatos(contato);

                                                JOptionPane.showMessageDialog(null, "Salvo com Sucesso");

                                                //Define a situação como cancelar para habilitar os botoes utilizados apenas quando cancela um processo
                                                situacao = Rotinas.INICIAL;

                                                //habilita os botoes utilizados quando cancela um processo
                                                validabotoes.ValidaEstado(jPBotoes, situacao);

                                                //Limpa os campos do container pessoa
                                                validaCampos.LimparCampos(jPPessoa);
                                                validaCampos.LimparCampos(jPTipoPessoa);
                                                //Limpa os campos do container contato
                                                validaCampos.LimparCampos(jPContato);
                                                validaCampos.LimparJtable(jTBContato);
                                                //Limpa os campos do container endereco
                                                validaCampos.LimparCampos(jPEndereco);
                                                validaCampos.LimparJtable(jTBEndereco);

                                                //Desabilita todos os campos do container pessoa
                                                validaCampos.desabilitaCampos(jPPessoa);
                                                validaCampos.desabilitaCampos(jPTipoPessoa);
                                                //Desabilita todos os campos do container contato
                                                validaCampos.desabilitaCampos(jPContato);
                                                //Desabilita todos os campos do container endereco
                                                validaCampos.desabilitaCampos(jPEndereco);
                                                //Desabilita os botoes especificos da tela
                                                jBTAddContato.setEnabled(false);
                                                jBTRemoveContato.setEnabled(false);
                                                jBTAddEndereco.setEnabled(false);
                                                jBTRemoveEndereco.setEnabled(false);
                                            }else{
                                                    //Seta foco na aba de endereço
                                                    jTBPAdicionais.setSelectedIndex(1);
                                            }
                                        }else{
                                                                        
                                            //Salva certificadora com contato e endereço 

                                            //Inclui contato
                                            dao_contato.gravarContatos(contato);

                                            //Inclui endereço
                                            dao_endereco.gravarEndereco(endereco);

                                            JOptionPane.showMessageDialog(null, "Salvo com Sucesso");

                                            //Define a situação como cancelar para habilitar os botoes utilizados apenas quando cancela um processo
                                            situacao = Rotinas.INICIAL;

                                            //habilita os botoes utilizados quando cancela um processo
                                            validabotoes.ValidaEstado(jPBotoes, situacao);

                                            //Limpa os campos do container pessoa
                                            validaCampos.LimparCampos(jPPessoa);
                                            validaCampos.LimparCampos(jPTipoPessoa);
                                            //Limpa os campos do container contato
                                            validaCampos.LimparCampos(jPContato);
                                            validaCampos.LimparJtable(jTBContato);
                                            //Limpa os campos do container endereco
                                            validaCampos.LimparCampos(jPEndereco);
                                            validaCampos.LimparJtable(jTBEndereco);

                                            //Desabilita todos os campos do container pessoa
                                            validaCampos.desabilitaCampos(jPPessoa);
                                            validaCampos.desabilitaCampos(jPTipoPessoa);
                                            //Desabilita todos os campos do container contato
                                            validaCampos.desabilitaCampos(jPContato);
                                            //Desabilita todos os campos do container endereco
                                            validaCampos.desabilitaCampos(jPEndereco);

                                            //Desabilita os botoes especificos da tela
                                            jBTAddContato.setEnabled(false);
                                            jBTRemoveContato.setEnabled(false);
                                            jBTAddEndereco.setEnabled(false);
                                            jBTRemoveEndereco.setEnabled(false);
                                        
                                        }
                                    }   
                                }
                             }
                         }
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Selecione o tipo de pessoa juridica para prosseguir");
                }
            }else {
                JOptionPane.showMessageDialog(null, "Selecione o tipo da pessoa para prosseguir");
            }    
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Falha ao incluir a Pessoa");
        }

    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        UltimaSequencia ultima;
        
        //Seta mascaras nos campos necessários
        setaMascaras ();
        
        //Carrega conteudo das combobox
        jCBTipoContato.addItem("Selecione tipo");
        jCBTipoContato.addItem("Email");
        jCBTipoContato.addItem("Telefone");
        
        dao_cidade.consultageral(cidade);
        //Preenche dados nas ComboBox de cidade
        array_cidade = combo.PreencherCombo(jCBCidade, "descricao",cidade.getRetorno(), "id_cidade" );
        //seta no array da classe de cidade a lista de cidades listadas na combo
        cidade.setArray_cidade(array_cidade);
        
        dao_tela.consultageral(tela);
        //Preenche dados nas ComboBox de telas
        array_tela = combo.PreencherCombo(jCBTela, "descricao",tela.getRetorno(), "id_tela" );
        //seta no array da classe da tela a lista de telas listadas na combo
        tela.setArray_tela(array_tela);
  
        //Define a situação como incluir para habilitar os botoes utilizados apenas na inclusão
        situacao = Rotinas.INCLUIR;
        
        //habilita os botoes utilizados na inclusão e desabilita os restantes
        validabotoes.ValidaEstado(jPBotoes, situacao);
        
        //Habilita os botoes especificos da tela
        jBTAddContato.setEnabled(true);
        jBTRemoveContato.setEnabled(true);
        jBTAddEndereco.setEnabled(true);
        jBTRemoveEndereco.setEnabled(true);
 
        //Seta a data atual no campo data
        jTFData.setText(data.DataAtual());
       
        try {         
            ultima = new UltimaSequencia();
            //Gera id sequencial
            int sequencia = (Integer) (ultima.ultimasequencia("PESSOA","ID_PESSOA"));
            //Seta no campo id de pessoa o utltimo id gerado.
            jTFIDPessoa.setText(Integer.toString(sequencia));
            
        } catch (SQLException ex) {
            Logger.getLogger(InterfacePessoa.class.getName()).log(Level.SEVERE, null, ex);
        }  
        
        //Habilita campos necessários
        jRBPessoaFisica.setEnabled(true);
        jRBPessoaJuridica.setEnabled(true);
        validaCampos.habilitaCampos(jPEndereco);
        jBTAddPermissao.setEnabled(true);
        jBTRemovePermissao.setEnabled(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        
        //Define a situação como cancelar para habilitar os botoes utilizados apenas quando cancela um processo
        situacao = Rotinas.INICIAL;
        
        //habilita os botoes utilizados quando cancela um processo
        validabotoes.ValidaEstado(jPBotoes, situacao);
        
        //Limpa os campos do container pessoa
        validaCampos.LimparCampos(jPPessoa);
        validaCampos.LimparCampos(jPTipoPessoa);
        validaCampos.LimparCampos(jPContato);
        validaCampos.LimparCampos(jPEndereco);
        validaCampos.LimparCampos(jPPermissoes);
        validaCampos.LimparCampos(jPUsuario);
        validaCampos.LimparJtable(jTBContato);
        validaCampos.LimparJtable(jTBEndereco);
        validaCampos.LimparJtable(jTBPermissoes);
        
        //Desabilita todos os campos do container pessoa
        validaCampos.desabilitaCampos(jPPessoa);
        validaCampos.desabilitaCampos(jPTipoPessoa);
        validaCampos.desabilitaCampos(jPContato);
        validaCampos.desabilitaCampos(jPEndereco);
        validaCampos.desabilitaCampos(jPPermissoes); 
        validaCampos.desabilitaCampos(jPUsuario); 
        
        //Desabilita os botoes especificos da tela
        jBTAddContato.setEnabled(false);
        jBTRemoveContato.setEnabled(false);
        jBTAddEndereco.setEnabled(false);
        jBTRemoveEndereco.setEnabled(false);
        jBTAddPermissao.setEnabled(false);
        jBTRemovePermissao.setEnabled(false);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jRBPessoaJuridicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBPessoaJuridicaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRBPessoaJuridicaActionPerformed

    private void jRBPessoaJuridicaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRBPessoaJuridicaMouseClicked
        if(jRBPessoaJuridica.isEnabled()){
            jRBPessoaJuridica.setSelected(true);
        }
        
    }//GEN-LAST:event_jRBPessoaJuridicaMouseClicked

    private void jRBPessoaFisicaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRBPessoaFisicaMouseClicked
        if(jRBPessoaFisica.isEnabled()){
            jRBPessoaFisica.setSelected(true);
        }
    }//GEN-LAST:event_jRBPessoaFisicaMouseClicked

    private void jRBMascMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRBMascMouseClicked
    
    }//GEN-LAST:event_jRBMascMouseClicked

    private void jRBFemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRBFemMouseClicked

    }//GEN-LAST:event_jRBFemMouseClicked

    private void jRBCPFMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRBCPFMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jRBCPFMouseClicked

    private void jRBFornecedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRBFornecedorMouseClicked
        if(jRBFornecedor.isEnabled()){
            jRBFornecedor.setSelected(true);
        }
        
    }//GEN-LAST:event_jRBFornecedorMouseClicked

    private void jRBCertificadoraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRBCertificadoraMouseClicked
         if(jRBCertificadora.isEnabled()){
            jRBCertificadora.setSelected(true);
        }
    }//GEN-LAST:event_jRBCertificadoraMouseClicked

    private void jRBCertificadoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBCertificadoraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRBCertificadoraActionPerformed

    private void jRBPessoaJuridicaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jRBPessoaJuridicaStateChanged
       
    }//GEN-LAST:event_jRBPessoaJuridicaStateChanged

    private void jRBPessoaFisicaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jRBPessoaFisicaStateChanged
       
    }//GEN-LAST:event_jRBPessoaFisicaStateChanged

    private void jRBCertificadoraStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jRBCertificadoraStateChanged

    }//GEN-LAST:event_jRBCertificadoraStateChanged

    private void jRBFornecedorStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jRBFornecedorStateChanged
       
    }//GEN-LAST:event_jRBFornecedorStateChanged

    private void jBTAddContatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTAddContatoActionPerformed
        contato = new Contato();
        //Se não estiver selecionado o tipo do contato
        if(jCBTipoContato.getSelectedIndex() == 0){ 
            JOptionPane.showMessageDialog(null, "Selecione o tipo do contato para continuar");
        }else{
            //Se estiver selecionado o tipo do contato
            if (validaCampos.validacamposobrigatorios(jPContato, "CONTATO") == 0) {
                //Pega dados da tela de contato
                getContato();
                try {
                    //adiciona dados do contato na Jtable de contatos
                    dao_contato.addContato(contato);
                    //limpa campos de contato
                    jTFDescContato.setText("");
                    jFTNumeroContato.setText("");
                    jTFEmail.setText("");
                    //ajusta largura das colunas de acordo com o tamanho do dado
                    Jtable.ajustarColunasDaTabela(jTBContato);
                    
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Falha ao adicionar contato");
                }
            }
        }
    }//GEN-LAST:event_jBTAddContatoActionPerformed

    private void jCBTipoContatoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCBTipoContatoMouseClicked

    }//GEN-LAST:event_jCBTipoContatoMouseClicked

    private void jCBTipoContatoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCBTipoContatoItemStateChanged
        //Se for email 
        if(jCBTipoContato.getSelectedIndex() == 1){
            jTFDescContato.setEnabled(true);
            jTFEmail.setEnabled(true);
            jFTNumeroContato.setEnabled(false); 
            jFTNumeroContato.setText("");
        //Se for telefone    
        }else if (jCBTipoContato.getSelectedIndex() == 2){
            jFTNumeroContato.setEnabled(true); 
            jTFDescContato.setEnabled(true);
            jTFEmail.setEnabled(false);
            jTFEmail.setText("");
        //Se nenhum selecionado 
        }else{
            jFTNumeroContato.setEnabled(false); 
            jTFDescContato.setEnabled(false);  
            jTFEmail.setEnabled(false);
            jFTNumeroContato.setText(""); 
            jTFDescContato.setText("");  
            jTFEmail.setText("");
        }
    }//GEN-LAST:event_jCBTipoContatoItemStateChanged

    private void jBTRemoveContatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTRemoveContatoActionPerformed
         if(validaCampos.VerificaJtable(jTBContato) == 1){
            Jtable.removeItens(jTBContato);
         }else{
             JOptionPane.showMessageDialog(null, "Não possui contatos para remover");
         }
    }//GEN-LAST:event_jBTRemoveContatoActionPerformed

    private void jTFNumeroEndActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFNumeroEndActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFNumeroEndActionPerformed

    private void jTFUFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFUFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFUFActionPerformed

    private void jBTRemoveEnderecoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTRemoveEnderecoActionPerformed
         if(validaCampos.VerificaJtable(jTBEndereco) == 1){
            Jtable.removeItens(jTBEndereco);
         }else{
             JOptionPane.showMessageDialog(null, "Não possui endereços para remover");
         }
    }//GEN-LAST:event_jBTRemoveEnderecoActionPerformed

    private void jBTAddEnderecoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTAddEnderecoActionPerformed
       endereco = new Endereco();
         if (validaCampos.validacamposobrigatorios(jPEndereco, "ENDERECO") == 0) {
             
                if(jCBCidade.getSelectedIndex() == 0){
                    JOptionPane.showMessageDialog(null, "Favor, selecione uma cidade");
                }else{
                    
                    //Pega dados da tela de endereco
                    getEndereco();
                    try {
                        //adiciona dados do endereço na Jtable de endereços
                        dao_endereco.addEndereco(endereco);

                        //limpa campos de endereço
                        validaCampos.LimparCampos(jPEndereco);

                        //Carrega novamente a combo de cidades
                        dao_cidade.consultageral(cidade);
                        combo.PreencherCombo(jCBCidade, "descricao",cidade.getRetorno(), "id_cidade" );
                        
                        //ajusta largura das colunas de acordo com o tamanho do dado
                        Jtable.ajustarColunasDaTabela(jTBEndereco);
                       

                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Falha ao adicionar endereco");
                    }
                } 
        }
    }//GEN-LAST:event_jBTAddEnderecoActionPerformed

    private void jButton5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseExited
        // TODO add your handling code here:
       
    }//GEN-LAST:event_jButton5MouseExited

    private void jFTCPFCNPJMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jFTCPFCNPJMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jFTCPFCNPJMouseExited

    private void jCBCidadeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCBCidadeItemStateChanged
       
    }//GEN-LAST:event_jCBCidadeItemStateChanged

    private void jCBCidadeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCBCidadeMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_jCBCidadeMouseClicked

    private void jCBCidadeInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_jCBCidadeInputMethodTextChanged
        // TODO add your handling code here:

    }//GEN-LAST:event_jCBCidadeInputMethodTextChanged

    private void jFTCPFCNPJFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFTCPFCNPJFocusLost
        // TODO add your handling code here:
        
        String cpf_cnpj;
        int id_pessoa;
        //pega o cpf do campo 
        cpf_cnpj = jFTCPFCNPJ.getText();
        //pega o id da pessoa
        id_pessoa = Integer.parseInt(jTFIDPessoa.getText());
        //retira mascara do campo para pegar o valor adicionado
        cpf_cnpj = cpf_cnpj.replace(".", "");
        cpf_cnpj = cpf_cnpj.replace("-", "");
        cpf_cnpj = cpf_cnpj.replace("/", "");
        cpf_cnpj = cpf_cnpj.replace(" ", "");

        if(cpf_cnpj.equals("")){
            jFTCPFCNPJ.setValue("");
        }else{
            //Valida CPF
            if(jRBCPF.isSelected()){
                if (validaCampos.ValidaCpf(cpf_cnpj) == false){
                    JOptionPane.showMessageDialog(null, "CPF Invalido! Favor verificar o numero do CPF");
                    jFTCPFCNPJ.grabFocus();
                }else{
                    //Verifica se o CPF já está cadastrado
                    pessoa.setCpf_cnpj(jFTCPFCNPJ.getText());
                    if(dao_pessoa.verificaCpfCnpj(pessoa) == true){
                        JOptionPane.showMessageDialog(null, "Este CPF já está cadastrado!");
                        jFTCPFCNPJ.grabFocus();
                    }
                }
            }
            
            //Valida CNPJ
            if(jRBCNPJ.isSelected()){
                if (validaCampos.ValidaCnpj(cpf_cnpj) == false){
                    JOptionPane.showMessageDialog(null, "CNPJ Invalido! Favor verificar o numero do CNPJ");
                    jFTCPFCNPJ.grabFocus();
                }else{
                    //Verifica se o CNPJ já está cadastrado
                    pessoa.setCpf_cnpj(jFTCPFCNPJ.getText());
                    if(dao_pessoa.verificaCpfCnpj(pessoa) == true){
                        JOptionPane.showMessageDialog(null, "Este CNPJ já está cadastrado!");
                        jFTCPFCNPJ.grabFocus();
                    }
                }
            }
        }
    }//GEN-LAST:event_jFTCPFCNPJFocusLost

    private void jCBInternacionalStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jCBInternacionalStateChanged
        if(jCBInternacional.isEnabled()){
            if(jCBInternacional.isSelected()){
                jFTCPFCNPJ.setValue("");
                jFTCPFCNPJ.setEnabled(false);
            }else{
            jFTCPFCNPJ.setEnabled(true);
        }
        }
    }//GEN-LAST:event_jCBInternacionalStateChanged

    private void jCBCidadePopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jCBCidadePopupMenuWillBecomeVisible
  
    }//GEN-LAST:event_jCBCidadePopupMenuWillBecomeVisible

    private void jCBCidadePopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jCBCidadePopupMenuWillBecomeInvisible
        //Verifica se o campo está habilitado
        if((jCBCidade.isEnabled()) ){
            if(jCBCidade.getSelectedIndex() == 0){ 
                jTFUF.setText("");
            }else{
                //pega o id da cidade selecionada
                // -1 foi utilizado pelo fato de adicionar um valor padrao no primeiro item da combo
                cidade.setId_cidade(cidade.getArray_cidade(jCBCidade.getSelectedIndex()-1));
                //retorna os dados da cidade pelo id
                dao_cidade.retornardados(cidade);
                //Seta UF no campo de UF da cidade
                jTFUF.setText(cidade.getUf().toString());
            }
            
            }
    }//GEN-LAST:event_jCBCidadePopupMenuWillBecomeInvisible

    private void jFTRGFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFTRGFocusLost
        // TODO add your handling code here:
        String rg;
        //pega o cpf do campo 
        rg = jFTRG.getText();
        //retira mascara do campo para pegar o valor adicionado
        rg = rg.replace(".", "");
        rg = rg.replace("-", "");
        rg = rg.replace(" ", "");
        
        //Garante que sempre que apagar um conteudo do campo com mascara o mesmo se tornara vazio
        if(rg.equals("")){
            jFTRG.setValue("");
        }else{
            //Verifica se o RG já está cadastrado
            usuario.setRg(jFTRG.getText());
            if(dao_pessoa.verificaRG(usuario) == true){
                JOptionPane.showMessageDialog(null, "Este RG já está cadastrado!");
                jFTRG.grabFocus();
            }
        }
    }//GEN-LAST:event_jFTRGFocusLost

    private void jFTDataNascFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFTDataNascFocusLost
        String data_nasc;
      
        //pega o cpf do campo 
        data_nasc = jFTDataNasc.getText();
        //retira mascara do campo para pegar o valor adicionado
        data_nasc = data_nasc.replace("/", "");
        data_nasc = data_nasc.replace(" ", "");
        
        //Garante que sempre que apagar um conteudo do campo com mascara o mesmo se tornara vazio
        if(data_nasc.equals("")){
            jFTDataNasc.setValue("");
        }else{
            if(validaCampos.ValidaData(jFTDataNasc.getText()) == false){
                jFTDataNasc.grabFocus();
            }
        }
    }//GEN-LAST:event_jFTDataNascFocusLost

    private void jFTNumeroContatoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFTNumeroContatoFocusLost
        // TODO add your handling code here:
        String numero_contato;
        //pega o valor do campo 
        numero_contato = jFTNumeroContato.getText();
        //retira mascara do campo para pegar o valor adicionado
        numero_contato = numero_contato.replace("(", "");
        numero_contato = numero_contato.replace(")", "");
        numero_contato = numero_contato.replace("-", "");
        numero_contato = numero_contato.replace(" ", "");
        
        //Garante que sempre que apagar um conteudo do campo com mascara o mesmo se tornara vazio
        if(numero_contato.equals("")){
            jFTNumeroContato.setValue("");
        }else{
            jFTNumeroContato.setText("");
            jFTNumeroContato.setValue("");
            //se for numero no modelo (44)3529-1126
            if(numero_contato.length() == 10){
                //Seta mascara no campo de Telefone
                jFTNumeroContato.setFormatterFactory(new DefaultFormatterFactory( validaCampos.formata("(##)####-####")));
                jFTNumeroContato.setText(numero_contato);
            //se for numero no modelo (44)53529-1126
            }else if(numero_contato.length() == 11){
                 //Seta mascara no campo de Telefone
                jFTNumeroContato.setFormatterFactory(new DefaultFormatterFactory( validaCampos.formata("(##)#####-####")));
                jFTNumeroContato.setText(numero_contato);
            }
        }
    }//GEN-LAST:event_jFTNumeroContatoFocusLost

    private void jFTCepFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFTCepFocusLost
        // TODO add your handling code here:
        String cep;
        //pega o cpf do campo 
        cep = jFTCep.getText();
        //retira mascara do campo para pegar o valor adicionado
        cep = cep.replace("-", "");
        cep = cep.replace(" ", "");
        
        //Garante que sempre que apagar um conteudo do campo com mascara o mesmo se tornara vazio
        if(cep.equals("")){
            jFTCep.setValue("");
        }
    }//GEN-LAST:event_jFTCepFocusLost

    private void jRBFemStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jRBFemStateChanged
        
        if(jRBFem.isEnabled()){
            if(jRBFem.isSelected()){
                jRBFem.setSelected(true);
                jRBMasc.setSelected(false);
            }
        }else{
                jRBMasc.setSelected(false);
                jRBFem.setSelected(false);
            }
    }//GEN-LAST:event_jRBFemStateChanged

    private void jRBMascStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jRBMascStateChanged
        if(jRBMasc.isEnabled()){ 
            if(jRBMasc.isSelected()){
                jRBFem.setSelected(false);
                jRBMasc.setSelected(true);
            }
        }else{
                jRBMasc.setSelected(false);
                jRBFem.setSelected(false);
            }
    }//GEN-LAST:event_jRBMascStateChanged

    private void jBTAddPermissaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTAddPermissaoActionPerformed
        permissao = new Permissao();

        //Se a opção gerente não estiver selecionada
        if(!jCBGerente.isSelected()){
            if(jCBTela.getSelectedIndex() == 0){
                JOptionPane.showMessageDialog(null, "Favor, selecione uma tela");
            }else{

                    //Pega dados da tela de endereco
                    getPermissoes();
                    try {
                        //adiciona dados do endereço na Jtable de endereços
                        dao_permissao.addPermissao(permissao);

                        //limpa campos de endereço
                        validaCampos.LimparCampos(jPPermissoes);

                        //Carrega novamente a combo de telas
                        dao_tela.consultageral(tela);
                        combo.PreencherCombo(jCBTela, "descricao",tela.getRetorno(), "id_tela" );
                        
                        //ajusta largura das colunas de acordo com o tamanho do dado
                        Jtable.ajustarColunasDaTabela(jTBPermissoes);

                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Falha ao adicionar permissão");
                    }
                }   
        }else{
                JOptionPane.showMessageDialog(null, "A opção gerente está selecionada, usuário terá acesso a todas as telas");
             } 
    }//GEN-LAST:event_jBTAddPermissaoActionPerformed

    private void jBTRemovePermissaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTRemovePermissaoActionPerformed
         if(validaCampos.VerificaJtable(jTBPermissoes) == 1){
            Jtable.removeItens(jTBPermissoes);
         }else{
             JOptionPane.showMessageDialog(null, "Não possui itens para remover");
         }
    }//GEN-LAST:event_jBTRemovePermissaoActionPerformed

    private void jCBGerenteStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jCBGerenteStateChanged
        if(jCBGerente.isSelected()){
            jCBTela.setSelectedIndex(1);
            jCBTela.setEnabled(false);
            jCBInserir.setEnabled(false);
            jCBAlterar.setEnabled(false);
            jCBConsultar.setEnabled(false);
            jCBExcluir.setEnabled(false);
            validaCampos.LimparJtable(jTBPermissoes);
        }else{
  
            jCBTela.setEnabled(true);
            jCBInserir.setEnabled(true);
            jCBAlterar.setEnabled(true);
            jCBConsultar.setEnabled(true);
            jCBExcluir.setEnabled(true);
        }

    }//GEN-LAST:event_jCBGerenteStateChanged

    private void jCBTelaPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jCBTelaPopupMenuWillBecomeInvisible
        if((jCBTela.isEnabled()) ){
            if(jCBTela.getSelectedIndex() != 0){
                //pega o id da cidade selecionada
                // -1 foi utilizado pelo fato de adicionar um valor padrao no primeiro item da combo
                tela.setId_tela(tela.getArray_tela(jCBTela.getSelectedIndex()-1));
                
                //retorna os dados da tela pelo id
                dao_tela.retornardados(tela);
                
                
            }
        }
        if(jCBTela.getSelectedIndex() == 1){
            jCBGerente.setSelected(true);
            jCBGerente.setEnabled(false);
            jCBTela.setEnabled(true);
        }else{
            jCBGerente.setSelected(false);
            validaCampos.habilitaCampos(jPPermissoes);
        }
    }//GEN-LAST:event_jCBTelaPopupMenuWillBecomeInvisible

    private void jCBMostSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBMostSenhaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCBMostSenhaActionPerformed

    private void jCBMostSenhaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jCBMostSenhaStateChanged
        if(jCBMostSenha.isSelected()){
        jPFSenha.setEchoChar( (char) 0);
        }else{
            jPFSenha.setEchoChar('●');
        }
    }//GEN-LAST:event_jCBMostSenhaStateChanged

    private void jRBPessoaFisicaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRBPessoaFisicaItemStateChanged
     //garante que sempre quando estiver selecionado pessoa fisica, pessoa juridica não estará selecionado
        if(jRBPessoaFisica.isEnabled()){
            if(jRBPessoaFisica.isSelected()){
                
                //desabilita campos de pessoa juridica
                jRBPessoaJuridica.setSelected(false);
                jRBPessoaFisica.setSelected(true);
                jRBCertificadora.setSelected(false);
                jRBFornecedor.setSelected(false);
                jRBCertificadora.setEnabled(false);
                jRBFornecedor.setEnabled(false);
                jCBTipoContato.setEnabled(true);
            
                //Habilita campos de pessoa fisíca
                jTFNome.setEnabled(true);
                jFTDataNasc.setEnabled(true);
                jRBCPF.setSelected(true);
                jFTRG.setEnabled(true);
                jFTCPFCNPJ.setEnabled(true);
                jRBMasc.setEnabled(true);
                jRBFem.setEnabled(true);
                jTFNome.setEnabled(true);
                validaCampos.habilitaCampos(jPPermissoes);
                validaCampos.habilitaCampos(jPUsuario);
                jBTAddPermissao.setEnabled(true);
                jBTRemovePermissao.setEnabled(true);

                //Desabilita campos de pessoa jurídica
                jTFRazaoSocial.setEnabled(false);
                jRBCNPJ.setSelected(false);
                jCBInternacional.setEnabled(false);
                jCBInternacional.setSelected(false);
                JTFSite.setEnabled(false);
                jTFRamo.setEnabled(false);
                jCBCalibracoes.setEnabled(false);
                
                //limpa campos de pessoa jurídica
                jTFRazaoSocial.setText("");
                jCBInternacional.setSelected(false);
                JTFSite.setText("");
                jTFRamo.setText("");
                jCBCalibracoes.setSelected(false);
                jTFNome.setText("");
                
                //Seta mascara no campo de CPF
                jFTCPFCNPJ.setFormatterFactory(new DefaultFormatterFactory( validaCampos.formata("###.###.###-##")));
                //limpa campo depois que setou a mascara. obs: não ira afetar a mascara.
                jFTCPFCNPJ.setValue("");
                
            }  
        }
    }//GEN-LAST:event_jRBPessoaFisicaItemStateChanged

    private void jRBPessoaJuridicaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRBPessoaJuridicaItemStateChanged
          //garante que sempre quando estiver selecionado pessoa juridica, pessoa fisica não estará selecionado
         if(jRBPessoaJuridica.isEnabled()){
            if((jRBPessoaJuridica.isSelected()) && ((!jRBCertificadora.isSelected()) && (!jRBFornecedor.isSelected()))){
                
                validaCampos.desabilitaCampos(jPPessoa);
                validaCampos.desabilitaCampos(jPPermissoes);
                validaCampos.desabilitaCampos(jPUsuario);
                jRBPessoaFisica.setSelected(false);
                jCBTipoContato.setEnabled(false);
                jBTAddPermissao.setEnabled(false);
                jBTRemovePermissao.setEnabled(false);
                
                //limpa campos de pessoa fisíca
                jTFNome.setText("");
                jFTDataNasc.setText("");
                jFTRG.setText("");
                jFTCPFCNPJ.setText("");
                jTFNome.setText("");
                jRBMasc.setSelected(false);
                jRBFem.setSelected(false);
        
                //Habilita campos de pessoa jurídica
                jRBPessoaFisica.setSelected(false);
                jRBPessoaJuridica.setSelected(true);
                jRBCertificadora.setSelected(false);
                jRBFornecedor.setSelected(false);
                jRBCertificadora.setEnabled(true);
                jRBFornecedor.setEnabled(true);
            }
         }
    }//GEN-LAST:event_jRBPessoaJuridicaItemStateChanged

    private void jRBCertificadoraItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRBCertificadoraItemStateChanged
         if(jRBCertificadora.isEnabled()){
            if(jRBCertificadora.isSelected()){

               //Habilita campos de pessoa jurídica
               jRBCertificadora.setEnabled(true);
               jRBCertificadora.setSelected(true);
               jRBFornecedor.setEnabled(true);
               jRBFornecedor.setSelected(false);
               jRBCNPJ.setSelected(true);
               if(!jCBInternacional.isSelected()){
                jFTCPFCNPJ.setEnabled(true);    
               }
               jTFRazaoSocial.setEnabled(true);
               jTFNome.setEnabled(true);
               jCBCalibracoes.setEnabled(true);
               jCBTipoContato.setEnabled(true);
               jCBInternacional.setEnabled(true);

               //Desabilita campos de pessoa física
               JTFSite.setEnabled(false);
               jTFRamo.setEnabled(false);
               jFTDataNasc.setEnabled(false);
               jFTRG.setEnabled(false);
               jRBCPF.setSelected(false);
               jRBMasc.setEnabled(false);
               jRBFem.setEnabled(false);
               jRBMasc.setSelected(false);
               jRBFem.setSelected(false);
               
               //Seta mascara no campo de CNPJ
               jFTCPFCNPJ.setFormatterFactory(new DefaultFormatterFactory( validaCampos.formata("##.###.###/####-##")));
               //limpa campo depois que setou a mascara. obs: não ira afetar a mascara
               jFTCPFCNPJ.setValue("");
              

            }
        }
    }//GEN-LAST:event_jRBCertificadoraItemStateChanged

    private void jRBFornecedorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRBFornecedorItemStateChanged
         
         if(jRBFornecedor.isEnabled()){
            if(jRBFornecedor.isSelected()){
                
                //Habilita campos de pessoa jurídica
                jRBFornecedor.setEnabled(true);
                jRBFornecedor.setSelected(true);
                jRBCertificadora.setEnabled(true);
                jRBCertificadora.setSelected(false);
                jRBCNPJ.setSelected(true);
                if(!jCBInternacional.isSelected()){
                jFTCPFCNPJ.setEnabled(true);    
                }
                jTFRazaoSocial.setEnabled(true);
                jTFNome.setEnabled(true);
                JTFSite.setEnabled(true);
                jTFRamo.setEnabled(true);
                jCBTipoContato.setEnabled(true);
                jCBInternacional.setEnabled(true);

                //Desabilita campos de pessoa física
                jFTDataNasc.setEnabled(false);
                jFTRG.setEnabled(false);
                jRBCPF.setSelected(false);
                jRBMasc.setEnabled(false);
                jRBFem.setEnabled(false);
                jRBMasc.setSelected(false);
                jRBFem.setSelected(false);
                jCBCalibracoes.setEnabled(false);

                //Seta mascara no campo de CNPJ
                jFTCPFCNPJ.setFormatterFactory(new DefaultFormatterFactory( validaCampos.formata("##.###.###/####-##")));
                //limpa campo depois que setou a mascara. obs: não ira afetar a mascara
                jFTCPFCNPJ.setValue("");
            }
         }
    }//GEN-LAST:event_jRBFornecedorItemStateChanged

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        
    }//GEN-LAST:event_formWindowActivated

    private void formWindowStateChanged(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowStateChanged
        
    }//GEN-LAST:event_formWindowStateChanged

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        try {
            //Inclui a opção todas telas como primeira opção
            tela.setDescricao("Todas telas");
            tela.setId_tela(1);
            dao_tela.incluir(tela);
            
            //Inclui a a tela de Pessoas
            tela.setDescricao("Pessoas");
            tela.setId_tela(2);
            dao_tela.incluir(tela);
            
        } catch (SQLException ex) {
            Logger.getLogger(InterfacePessoa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_formWindowOpened

    private void jTFLoginFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTFLoginFocusLost
        //Verifica se o Login já está cadastrado
        
        if(!jTFLogin.getText().equals("")){
            usuario.setLogin(jTFLogin.getText());
            if(dao_pessoa.verificaLogin(usuario) == true){
                JOptionPane.showMessageDialog(null, "Este Login já está cadastrado!");
                jTFLogin.grabFocus();
            }
        }
    }//GEN-LAST:event_jTFLoginFocusLost

    private void jBTBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTBuscarActionPerformed
        // TODO add your handling code here:
        int id_busca;
        String ds_busca;
        
        //Tira aspas simples da string para evitar código sql
        validaCampos.IgnoraSQL(jTFFiltro);

        switch (jCBBuscarPessoa.getSelectedIndex()) {
        //Selecione pessoa (Consulta Geral) 
        case 0:
            //Geral
            if(jCBBuscarPor.getSelectedIndex() == 0){
                if(dao_pessoa.consultageral(pessoa) == false){
                    JOptionPane.showMessageDialog(null, "Não foram encontrados registros referente ao filtro");
                }    
            //Código
            }else if(jCBBuscarPor.getSelectedIndex() == 1){
                if(!jTFFiltro.getText().equals("")){
                    try {
                        id_busca = Integer.parseInt(jTFFiltro.getText());
                        pessoa.setId_pessoa(id_busca);
                        if(dao_pessoa.consultacodigo(pessoa) == false){
                            JOptionPane.showMessageDialog(null, "Não foram encontrados registros referente ao filtro");
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Pelo código deve ser informado um valor inteiro");
                        jTFFiltro.grabFocus();
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Preencha o filtro para busca");
                    jTFFiltro.grabFocus();
                }
            //Descrição
            }else if (jCBBuscarPor.getSelectedIndex() == 2){
                ds_busca = jTFFiltro.getText();
                if(!ds_busca.equals("")){
                    pessoa.setNome(ds_busca);
                    if(dao_pessoa.consultadesc(pessoa) == false){
                        JOptionPane.showMessageDialog(null, "Não foram encontrados registros referente ao filtro");
                    }
                }else{
                     JOptionPane.showMessageDialog(null, "Preencha o filtro para busca");
                     jTFFiltro.grabFocus();
                }
            }
            //Preenche na JTABLE os dados das pessoas cadastradas
            Jtable.PreencherJtableGenerico(jTBDadosPessoas, new String[]{"id_pessoa","login","nome","razao_social","cpf_cnpj","rg","sexo","dt_nasc","data_alter"}, pessoa.getRetorno());
            break;
                    
        //Usuario 
        case 1:
            //Geral
            if(jCBBuscarPor.getSelectedIndex() == 0){
                if(dao_pessoa.consultageral(usuario) == false){
                    JOptionPane.showMessageDialog(null, "Não foram encontrados registros referente ao filtro");
                }    
            //código
            }else if(jCBBuscarPor.getSelectedIndex() == 1){
                if(!jTFFiltro.getText().equals("")){
                    try {
                        id_busca = Integer.parseInt(jTFFiltro.getText());
                        usuario.setId_pessoa(id_busca);
                        if(dao_pessoa.consultacodigo(usuario) == false){
                            JOptionPane.showMessageDialog(null, "Não foram encontrados registros referente ao filtro");
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Pelo código deve ser informado um valor inteiro");
                        jTFFiltro.grabFocus();
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Preencha o filtro para busca");
                    jTFFiltro.grabFocus();
                }
            //Descrição
            }else if(jCBBuscarPor.getSelectedIndex() == 2){
                ds_busca = jTFFiltro.getText();
                usuario.setNome(ds_busca);
                if(!ds_busca.equals("")){
                    if(dao_pessoa.consultadesc(usuario) == false){
                        JOptionPane.showMessageDialog(null, "Não foram encontrados registros referente ao filtro");
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Preencha o filtro para busca");
                    jTFFiltro.grabFocus();
                } 
            }
            //Preenche na JTABLE os dados dos usuários cadastrados
            Jtable.PreencherJtableGenerico(jTBDadosPessoas, new String[]{"id_pessoa","login","nome","razao_social","cpf_cnpj","rg","sexo","dt_nasc","data_alter"}, usuario.getRetorno());
            break;
        //Certificadora
        case 2:
            //Geral
            if(jCBBuscarPor.getSelectedIndex() == 0){
                if(dao_pessoa.consultageral(certificadora) == false){
                    JOptionPane.showMessageDialog(null, "Não foram encontrados registros referente ao filtro");
                }    
            //código
            }else if(jCBBuscarPor.getSelectedIndex() == 1){
                if(!jTFFiltro.getText().equals("")){
                    try {
                        id_busca = Integer.parseInt(jTFFiltro.getText());
                        certificadora.setId_pessoa(id_busca);
                        if(dao_pessoa.consultacodigo(certificadora) == false){
                            JOptionPane.showMessageDialog(null, "Não foram encontrados registros referente ao filtro");
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Pelo código deve ser informado um valor inteiro");
                        jTFFiltro.grabFocus();
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Preencha o filtro para busca");
                    jTFFiltro.grabFocus();
                }
            //Descrição
            }else if(jCBBuscarPor.getSelectedIndex() == 2){
                ds_busca = jTFFiltro.getText();
                if(!ds_busca.equals("")){
                    certificadora.setNome(ds_busca);
                    if(dao_pessoa.consultadesc(certificadora) == false){
                        JOptionPane.showMessageDialog(null, "Não foram encontrados registros referente ao filtro");
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Preencha o filtro para busca");
                    jTFFiltro.grabFocus();
                } 
            }
            //Preenche na JTABLE os dados dos usuários cadastrados
            Jtable.PreencherJtableGenerico(jTBDadosPessoas, new String[]{"id_pessoa","login","nome","razao_social","cpf_cnpj","rg","sexo","dt_nasc","data_alter"}, certificadora.getRetorno());
            break;
            
            //Certificadora
        case 3:
            //Geral
            if(jCBBuscarPor.getSelectedIndex() == 0){
                if(dao_pessoa.consultageral(fornecedor) == false){
                    JOptionPane.showMessageDialog(null, "Não foram encontrados registros referente ao filtro");
                }    
            //código
            }else if(jCBBuscarPor.getSelectedIndex() == 1){
                if(!jTFFiltro.getText().equals("")){
                    try {
                        id_busca = Integer.parseInt(jTFFiltro.getText());
                        fornecedor.setId_pessoa(id_busca);
                        if(dao_pessoa.consultacodigo(fornecedor) == false){
                            JOptionPane.showMessageDialog(null, "Não foram encontrados registros referente ao filtro");
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Pelo código deve ser informado um valor inteiro");
                        jTFFiltro.grabFocus();
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Preencha o filtro para busca");
                    jTFFiltro.grabFocus();
                }
            //Descrição
            }else if(jCBBuscarPor.getSelectedIndex() == 2){
                ds_busca = jTFFiltro.getText();
                if(!ds_busca.equals("")){
                    fornecedor.setNome(ds_busca);
                    if(dao_pessoa.consultadesc(fornecedor) == false){
                        JOptionPane.showMessageDialog(null, "Não foram encontrados registros referente ao filtro");
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Preencha o filtro para busca");
                    jTFFiltro.grabFocus();
                } 
            }
            //Preenche na JTABLE os dados dos usuários cadastrados
            Jtable.PreencherJtableGenerico(jTBDadosPessoas, new String[]{"id_pessoa","login","nome","razao_social","cpf_cnpj","rg","sexo","dt_nasc","data_alter"}, fornecedor.getRetorno());
            break;
        }
        Jtable.ajustarColunasDaTabela(jTBDadosPessoas);
    }//GEN-LAST:event_jBTBuscarActionPerformed

    private void jFTNumeroContatoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jFTNumeroContatoMouseClicked
        String numero_contato;
        //pega o valor do campo 
        numero_contato = jFTNumeroContato.getText();
        //retira mascara do campo para pegar o valor adicionado
        numero_contato = numero_contato.replace("(", "");
        numero_contato = numero_contato.replace(")", "");
        numero_contato = numero_contato.replace("-", "");
        numero_contato = numero_contato.replace(" ", "");
        //limpa mascara e valor do campo
        jFTNumeroContato.setText("");
        jFTNumeroContato.setValue("");
        //seta a mascara para quando estiver editando
        jFTNumeroContato.setFormatterFactory(new DefaultFormatterFactory( validaCampos.formata("(##)#########")));
        //seta o valor que estava no campo
        jFTNumeroContato.setText(numero_contato);
        
        //limpa campo depois que setou a mascara. obs: não ira afetar a mascara.
        //jFTNumeroContato.setValue("");
    }//GEN-LAST:event_jFTNumeroContatoMouseClicked

    private void jTBDadosPessoasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTBDadosPessoasMouseClicked
        //Verifica se houve 2 cliques do mouse 
        if (evt.getClickCount() == 2) {

            //recupera a linha clicada
            int linha = jTBDadosPessoas.getSelectedRow();
            //recupera o id da pessoa selecionada
            String codigo = (String) jTBDadosPessoas.getValueAt(linha, 0);
            //Seta o id da pessoa para retornar os dados
            pessoa.setId_pessoa(Integer.parseInt(codigo));
            //retorna dados da pessoa
            dao_pessoa.retornardadosGeral(pessoa);
            //Adiciona nos campos os dados da pessoa

            setcompPessoa();

            jTBPessoas.setSelectedIndex(0); 
            jTBPAdicionais.setSelectedIndex(0);
       }
    }//GEN-LAST:event_jTBDadosPessoasMouseClicked

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
            java.util.logging.Logger.getLogger(InterfacePessoa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterfacePessoa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterfacePessoa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterfacePessoa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InterfacePessoa().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField JTFSite;
    private javax.swing.JButton jBTAddContato;
    private javax.swing.JButton jBTAddEndereco;
    private javax.swing.JButton jBTAddPermissao;
    private javax.swing.JButton jBTBuscar;
    private javax.swing.JButton jBTRemoveContato;
    private javax.swing.JButton jBTRemoveEndereco;
    private javax.swing.JButton jBTRemovePermissao;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JCheckBox jCBAlterar;
    private javax.swing.JComboBox jCBBuscarPessoa;
    private javax.swing.JComboBox jCBBuscarPor;
    private javax.swing.JCheckBox jCBCalibracoes;
    private javax.swing.JComboBox jCBCidade;
    private javax.swing.JCheckBox jCBConsultar;
    private javax.swing.JCheckBox jCBExcluir;
    private javax.swing.JCheckBox jCBGerente;
    private javax.swing.JCheckBox jCBInserir;
    private javax.swing.JCheckBox jCBInternacional;
    private javax.swing.JCheckBox jCBMostSenha;
    private javax.swing.JComboBox jCBTela;
    private javax.swing.JComboBox jCBTipoContato;
    private javax.swing.JFormattedTextField jFTCPFCNPJ;
    private javax.swing.JFormattedTextField jFTCep;
    private javax.swing.JFormattedTextField jFTDataNasc;
    private javax.swing.JFormattedTextField jFTNumeroContato;
    private javax.swing.JFormattedTextField jFTRG;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPBotoes;
    private javax.swing.JPanel jPCadastroPessoa;
    private javax.swing.JPanel jPContato;
    private javax.swing.JPanel jPEndereco;
    private javax.swing.JPasswordField jPFSenha;
    private javax.swing.JPanel jPPermissoes;
    private javax.swing.JPanel jPPessoa;
    private javax.swing.JPanel jPTipoPessoa;
    private javax.swing.JPanel jPUsuario;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JRadioButton jRBCNPJ;
    private javax.swing.JRadioButton jRBCPF;
    private javax.swing.JRadioButton jRBCertificadora;
    private javax.swing.JRadioButton jRBFem;
    private javax.swing.JRadioButton jRBFornecedor;
    private javax.swing.JRadioButton jRBMasc;
    private javax.swing.JRadioButton jRBPessoaFisica;
    private javax.swing.JRadioButton jRBPessoaJuridica;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTBContato;
    private javax.swing.JTable jTBDadosPessoas;
    private javax.swing.JTable jTBEndereco;
    private javax.swing.JTabbedPane jTBPAdicionais;
    private javax.swing.JTable jTBPermissoes;
    private javax.swing.JTabbedPane jTBPessoas;
    private javax.swing.JTextField jTFBairro;
    private javax.swing.JTextField jTFData;
    private javax.swing.JTextField jTFDescContato;
    private javax.swing.JTextField jTFDescEnd;
    private javax.swing.JTextField jTFEmail;
    private javax.swing.JTextField jTFFiltro;
    private javax.swing.JTextField jTFIDPessoa;
    private javax.swing.JTextField jTFLogin;
    private javax.swing.JTextField jTFNome;
    private javax.swing.JTextField jTFNumeroEnd;
    private javax.swing.JTextField jTFRamo;
    private javax.swing.JTextField jTFRazaoSocial;
    private javax.swing.JTextField jTFRua;
    private javax.swing.JTextField jTFUF;
    // End of variables declaration//GEN-END:variables


    //Pega dados de pessoa fisíca da tela 
    public Usuario getcompPessoaFisica() {
        //  Variaveis e conversões
        Date data_atual = new Date(System.currentTimeMillis());
        //Pessoa
        usuario.setNome(jTFNome.getText());
        usuario.setCpf_cnpj(jFTCPFCNPJ.getText());
        usuario.setData_cadastro(data_atual);
        usuario.setData_alter(data_atual);
        
        //Pessoa Fisíca
        usuario.setDt_nasc(jFTDataNasc.getText());
        usuario.setRg(jFTRG.getText());
        if (jRBMasc.isSelected()){
            usuario.setSexo("M");
        }else if (jRBFem.isSelected()){
            usuario.setSexo("F");
        }
        
        //Usuario
        usuario.setLogin(jTFLogin.getText());
        //criptografa a senha
        usuario.setSenha(criptografar.criptografarMD5(jPFSenha.getPassword().toString()));
        if(jCBGerente.isSelected()){
            usuario.setIn_gerente(1);
        }else{
            usuario.setIn_gerente(0);
        }
        return usuario;
    }
    
    //Pega dados de pessoa jurídica da tela
    public Pessoa getcompCertificadora() {
        //  Variaveis e conversões
        Date data_atual = new Date(System.currentTimeMillis());
        //Pessoa
        certificadora.setNome(jTFNome.getText());
        certificadora.setCpf_cnpj(jFTCPFCNPJ.getText());
        certificadora.setData_cadastro(data_atual);
        certificadora.setData_alter(data_atual);
        
        //Pessoa Juridica
        certificadora.setRazao_social(jTFRazaoSocial.getText());
        
        //Certificadora
        if(jCBCalibracoes.isSelected()){
            certificadora.setIn_calibracoes('S');
        }else{
            certificadora.setIn_calibracoes('N');
        }
        
        return certificadora;
    }
    
    public Pessoa getcompFornecedor() {
        //  Variaveis e conversões
        Date data_atual = new Date(System.currentTimeMillis());
        
        //Pessoa
        fornecedor.setNome(jTFNome.getText());
        if(jCBInternacional.isSelected()){
            fornecedor.setCpf_cnpj("");
        }else{
            fornecedor.setCpf_cnpj(jFTCPFCNPJ.getText());
        }

        fornecedor.setData_cadastro(data_atual);
        fornecedor.setData_alter(data_atual);
        
        //Pessoa Juridica
        fornecedor.setRazao_social(jTFRazaoSocial.getText());
        
        //Fornecedor
        fornecedor.setSite(JTFSite.getText());
        fornecedor.setRamo(jTFRamo.getText());
        
        return fornecedor;
    }
    
    private void setcompPessoa() {
        //Implementar
    }
    
    //Pega dados da tela de contatos 
    public Contato getContato() {
        //  Variaveis e conversões
        Date data_atual = new Date(System.currentTimeMillis());
        String fone;
        int id_pessoa = Integer.parseInt(jTFIDPessoa.getText());
        contato.setData_alter(data_atual);
        contato.setId_pessoa(id_pessoa);
        contato.setTabela(jTBContato);
        contato.setDescricao(jTFDescContato.getText()); 

        //Se for Email
        if(jCBTipoContato.getSelectedIndex() == 1){
            contato.setEmail(jTFEmail.getText());
            contato.setTipo("E");
            
        //Se for telefone    
        }else if(jCBTipoContato.getSelectedIndex() == 2){
            fone = jFTNumeroContato.getText();
            contato.setFone(fone);
            contato.setTipo("F");
        }

        return contato;
    }
    
    public Endereco getEndereco(){
        //  Variaveis e conversões
        Date data_atual = new Date(System.currentTimeMillis());
        int id_pessoa = Integer.parseInt(jTFIDPessoa.getText());
        
        endereco.setId_pessoa(id_pessoa);
        endereco.setId_cidade(cidade.getId_cidade()); // id de cidade é setado no momento que é selecionado a cidade no combobox
        endereco.setDescricao(jTFDescEnd.getText());
        endereco.setCep(jFTCep.getText());
        endereco.setRua(jTFRua.getText());
        endereco.setNumero(jTFNumeroEnd.getText());
        endereco.setBairro(jTFBairro.getText());
        endereco.setData_alter(data_atual);
        endereco.setTabela(jTBEndereco);
        endereco.setUf(jTFUF.getText());
        
        return endereco; 
    }
    
    public Permissao getPermissoes(){
        //  Variaveis e conversões
        Date data_atual = new Date(System.currentTimeMillis());
        int id_pessoa = Integer.parseInt(jTFIDPessoa.getText());
        
        permissao.setId_usuario(id_pessoa);
        permissao.setId_tela(tela.getId_tela());
        permissao.setData_alter(data_atual);
        permissao.setTabela(jTBPermissoes);
        permissao.setNome_tela(tela.getDescricao());
        permissao.setAcesso(0);
        

        if(jCBAlterar.isSelected()){
            permissao.setAlterar(1);
            permissao.setAcesso(1);
        }else{
             permissao.setAlterar(0);
        }
        if(jCBConsultar.isSelected()){
            permissao.setConsultar(1);
            permissao.setAcesso(1);
        }else{
             permissao.setConsultar(0);
        }
        if(jCBExcluir.isSelected()){
            permissao.setExcluir(1);
            permissao.setAcesso(1);
        }else{
             permissao.setExcluir(0);
        }
        if(jCBInserir.isSelected()){
            permissao.setInserir(1);
            permissao.setAcesso(1);
        }else{
             permissao.setInserir(0);
        }
        return permissao;
    }
     public void setaMascaras (){
        //Seta mascara no campo  data de nascimento
        jFTDataNasc.setFormatterFactory(new DefaultFormatterFactory( validaCampos.formata("##/##/####")));
        //limpa campo depois que setou a mascara. obs: não ira afetar a mascara.
        jFTDataNasc.setValue("");
        //Seta mascara no campo de RG
        jFTRG.setFormatterFactory(new DefaultFormatterFactory( validaCampos.formata("##.###.###-#")));
        //limpa campo depois que setou a mascara. obs: não ira afetar a mascara.
        jFTRG.setValue(""); 
        //Seta mascara no campo de Telefone
        jFTNumeroContato.setFormatterFactory(new DefaultFormatterFactory( validaCampos.formata("(##)#########")));
        //limpa campo depois que setou a mascara. obs: não ira afetar a mascara.
        jFTNumeroContato.setValue(""); 
        //Seta mascara no campo cep
        jFTCep.setFormatterFactory(new DefaultFormatterFactory( validaCampos.formata("#####-###")));
        //limpa campo depois que setou a mascara. obs: não ira afetar a mascara.
        jFTCep.setValue("");   
    }

}
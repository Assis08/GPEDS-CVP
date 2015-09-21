/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.GPEDSCVP.util;

import br.edu.GPEDSCVP.conexao.ConexaoBanco;
import java.awt.Component;
import java.awt.Container;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author Willys
 */

//Método para desabilitar todos os campos contidos no container passado por parametro
public class ValidaCampos {
    
    ConexaoBanco conecta_banco;
    public ResultSetMetaData metaData;
    //Constantes do método de validação para cpf e cnpj
    private static final int[] pesoCPF = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
    private static final int[] pesoCNPJ = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
    
    public ValidaCampos()
        throws SQLException
    {
        conecta_banco = new ConexaoBanco();
    }
       
    public void desabilitaCampos(Container container){
        
        Component components[] = container.getComponents();
           for (Component component : components){   
               //Desabilita todas JTextField do container
               if (component instanceof JTextField){
                   JTextField field = (JTextField) component;
                   if (field.isEnabled()){
                        if ((field.getText().equals(""))){
                            field.setEnabled(false);
                        }
                   }
               } 
               //Desabilita botoes do container
               if (component instanceof JButton){
                   JButton button = (JButton) component;
                   if (button.isEnabled()){
                        button.setEnabled(false);
                   }
               } 
               
               //Desabilita todas JFormattedTextField do container
               if (component instanceof JFormattedTextField){
                   JFormattedTextField field = (JFormattedTextField) component;
                   if (field.isEnabled()){
                        field.setEnabled(false); 
                   }    
               }
               //Desabilita todos JRadioButton do container
               if (component instanceof JRadioButton){
                    JRadioButton JRBT = (JRadioButton) component; 
                    if(JRBT.isEnabled()){
                        JRBT.setEnabled(false);
                   }
               }
               
               //Desabilita todos JCheckBox do container
               if (component instanceof JCheckBox){
                    JCheckBox JCB = (JCheckBox) component; 
                    if(JCB.isEnabled()){
                        JCB.setEnabled(false);
                   }
               }
               
               if (component instanceof JComboBox){
                    JComboBox JCB = (JComboBox) component; 
                    if(JCB.isEnabled()){
                        JCB.setEnabled(false);
                   }
               }
           }
     }
    
    public void habilitaCampos(Container container){
        
        Component components[] = container.getComponents();
           for (Component component : components){   
               //Habilita todas JTextField do container
               if (component instanceof JTextField){
                   JTextField field = (JTextField) component;
                   if (!field.isEnabled()){
                        field.setEnabled(true); 
                   }
               } 
               //Habilita todas JFormattedTextField do container
               if (component instanceof JFormattedTextField){
                   JFormattedTextField field = (JFormattedTextField) component;
                   if (!field.isEnabled()){
                        field.setEnabled(true); 
                   }    
               }
               
               //Habilita todos botoes do container
               if (component instanceof JButton){
                   JButton button = (JButton) component;
                   if (!button.isEnabled()){
                        button.setEnabled(true); 
                   }    
               }
               //Habilita todos JRadioButton do container
               if (component instanceof JRadioButton){
                    JRadioButton JRBT = (JRadioButton) component; 
                    if(!JRBT.isEnabled()){
                        JRBT.setEnabled(true);
                   }
               }
               
               //Habilita todos JCheckBox do container
               if (component instanceof JCheckBox){
                    JCheckBox JCB = (JCheckBox) component; 
                    if(!JCB.isEnabled()){
                        JCB.setEnabled(true);
                   }
               }
               //Habilita todos JComboBox do container
               if (component instanceof JComboBox){
                    JComboBox JCB = (JComboBox) component; 
                    if(!JCB.isEnabled()){
                        JCB.setEnabled(true);
                   }
               }
           }
     }
    
    //Método para limpar os campos de um container
        public void LimparCampos(Container container) {
        Component components[] = container.getComponents();
        for (Component component : components) {
             if (component instanceof JTextField) {
                JTextField field = (JTextField) component;
                field.setText("");
             }
             else if ((component instanceof JComboBox)) {
                JComboBox field = (JComboBox) component;
                field.removeAllItems();
            } else if ((component instanceof JRadioButton)){
                JRadioButton field = (JRadioButton) component;
                field.setSelected(false);
            } else if ((component instanceof JCheckBox )){
                JCheckBox field = (JCheckBox) component;
                field.setSelected(false);
            }else if (component instanceof JFormattedTextField) {
                JFormattedTextField field = (JFormattedTextField) component;
                field.setText("");
                field.setValue("");
            }
        }
    }
       
    //Método para validar se os campos obrigatórios estão preenchidos     
    public int validacamposobrigatorios(Container container, String tabela){
         
        Integer retorno =0;
        
        conecta_banco.executeSQL("select * from " + tabela + " LIMIT 1");
        try{           
            metaData = conecta_banco.resultset.getMetaData();
            int conta = metaData.getColumnCount();
               for ( int i = 1; i <= conta; i++){
                   String atributo = metaData.getColumnName(i);
                   int situacao = metaData.isNullable(i);
                   if (situacao == 0){
                        Component components[] = container.getComponents();
                        for (Component component : components){
                            if (component instanceof JTextField){
                                JTextField field = (JTextField) component;
                                if (field.isEnabled()){
                                    //Remove aspas simples do texto da String para evitar codigos SQL
                                    field.setText(field.getText().replace("'", ""));

                                    if (field.getName().equals(atributo)){
                                        if (field.getText().equals("") || (field.getText().equals("  /  /    ")) || (field.getText().equals("  .   .   /    -  ")) ||
                                        (field.getText().equals("   .   .   -  ")) ||(field.getText().equals("  .   .   - ")) ||(field.getText().equals("(  )    -    ")) || 
                                                (field.getText().equals("     -   ")) || (field.getText().equals("(  )         ")))  {
                                        JOptionPane.showMessageDialog(null, "campo: "+ field.getToolTipText()+ " é obrigatorio");
                                        
                                        field.grabFocus(); 
                                        retorno = 1;
                                        break;
                                        }
                                    }                                 
                                }
                            }
                        }                   
                    }
               }
           }catch (SQLException ex){   
           }                      
        return retorno;
    }
    
    //Método para criar e retornar uma mascara(JTextField) por parâmetro 
    public static MaskFormatter formata(String mascara) {
   
       try {
            MaskFormatter mascara_cpf = new MaskFormatter(mascara);   
            mascara_cpf.setValidCharacters("0123456789");
            //tf_CPF = new javax.swing.JFormattedTextField(mascara_cpf);
            return mascara_cpf;
      }
      catch (Exception error_mask) {
          JOptionPane.showMessageDialog(null,"Erro: "+error_mask);
      }
       return null;
    }
    
    //Método para limpar dados de uma Jtable
    public void LimparJtable(JTable tabela){

        DefaultTableModel tableModel =(DefaultTableModel) tabela.getModel();  
        tableModel.setNumRows(0);
    }
    
    //Método para verificar se a Jtable possui registros
    public int VerificaJtable(JTable tabela){
         if (tabela.getRowCount()>0){
             return 1;        
         }else
             return 0;
    }
    
    //Método para calcular digito verificador 
    private static int calcularDigito(String str, int[] peso) {
        int soma = 0;
        for (int indice=str.length()-1, digito; indice >= 0; indice-- ) {
            digito = Integer.parseInt(str.substring(indice,indice+1));
            soma += digito*peso[peso.length-str.length()+indice];
        }
        soma = 11 - soma % 11;
        return soma > 9 ? 0 : soma;
    }
    //Método para validar Cpf
    public static boolean ValidaCpf(String cpf) {
        
        cpf = cpf.replace(".", "");
        cpf = cpf.replace("-", "");
        cpf = cpf.replace("/", "");
        cpf = cpf.replace(" ", "");
        
        if((cpf.equals("11111111111")) ||
        (cpf.equals("22222222222")) ||
        (cpf.equals("33333333333")) ||
        (cpf.equals("44444444444")) ||
        (cpf.equals("55555555555")) ||
        (cpf.equals("66666666666")) ||
        (cpf.equals("77777777777")) ||
        (cpf.equals("88888888888")) ||
        (cpf.equals("99999999999"))){
            return false;
        }
        
        if ((cpf==null) || (cpf.length()!=11)) return false;

        Integer digito1 = calcularDigito(cpf.substring(0,9), pesoCPF);
        Integer digito2 = calcularDigito(cpf.substring(0,9) + digito1, pesoCPF);
        return cpf.equals(cpf.substring(0,9) + digito1.toString() + digito2.toString());
    }
   //Método para validar Cnpj
    public static boolean ValidaCnpj(String cnpj) {
        
        cnpj = cnpj.replace(".", "");
        cnpj = cnpj.replace("-", "");
        cnpj = cnpj.replace("/", "");
        cnpj = cnpj.replace(" ", "");

        if((cnpj.equals("11111111111111")) ||
        (cnpj.equals("22222222222222")) ||
        (cnpj.equals("33333333333333")) ||
        (cnpj.equals("44444444444444")) ||
        (cnpj.equals("55555555555555")) ||
        (cnpj.equals("66666666666666")) ||
        (cnpj.equals("77777777777777")) ||
        (cnpj.equals("88888888888888")) ||
        (cnpj.equals("99999999999999"))){
            return false;
        }
        if ((cnpj==null)||(cnpj.length()!=14)) return false;

        Integer digito1 = calcularDigito(cnpj.substring(0,12), pesoCNPJ);
        Integer digito2 = calcularDigito(cnpj.substring(0,12) + digito1, pesoCNPJ);
        return cnpj.equals(cnpj.substring(0,12) + digito1.toString() + digito2.toString());
    }
    //Verifica se data é válida
    public static boolean ValidaData(String data) {
        FormatarData formata_data = new FormatarData(); 
        Date data_atual = new Date();  
       
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        try {
            Date data_formatada = sdf.parse(data);
            int idade = formata_data.calculaIdade(data, "dd/MM/yyyy");
            if((idade>13) && (idade < 80)){
                //Data válida
                return true;
            }else{
                JOptionPane.showMessageDialog(null, "Data fora do limite, favor verificar a data");
                return false;
            }
        } catch(ParseException e){
            // data inválida
            JOptionPane.showMessageDialog(null, "Data inválida, favor verificar a data");
            return false;
          }
   }
    
    public static String xss(String texto) {
        if (texto == null) {
            return null;
        }
        StringBuilder textoXSS = new StringBuilder();
        int tamanhoTexto = texto.length();
        for (int i = 0; i < tamanhoTexto; i++) {
            char caracter = texto.charAt(i);
            if (caracter == '<') {
                textoXSS.append("&lt;");
            } else if (caracter == '>') {
                textoXSS.append("&gt;");
            } else if (caracter == '&') {
                textoXSS.append("&amp;");
            } else if (caracter == '"') {
                textoXSS.append("&quot;");
            } else if (caracter == ' ') {
                textoXSS.append("&nbsp;");
            } else {
                textoXSS.append(caracter);
            }
        }
        return textoXSS.toString();
    }
    
    public void IgnoraSQL (JTextField field){
        field.setText(field.getText().replace("'", ""));
    }
}



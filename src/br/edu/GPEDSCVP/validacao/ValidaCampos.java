/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.GPEDSCVP.validacao;

import br.edu.GPEDSCVP.conexao.ConexaoBanco;
import java.awt.Component;
import java.awt.Container;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author Willys
 */

//Método para desabilitar todos os campos contidos no container passado por parametro
public class ValidaCampos {
    
    ConexaoBanco conecta_banco;
    public ResultSetMetaData metaData;
    
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
            }
            if (component instanceof JFormattedTextField) {
                JFormattedTextField field = (JFormattedTextField) component;
                field.setText("");
             }
        }
    }
       
    //Método para validar se os campos obrigatórios estão preenchidos     
    public int validacamposobrigatorios(Container container, String tabela){
         
        Integer retorno =0;
        String valor_campo;
        
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
                                    if (field.getName().equals(atributo)){
                                        if (field.getText().equals("") || (field.getText().equals("  /  /    ")) || (field.getText().equals("  .   .   /    -  ")) ||
                                        (field.getText().equals("   .   .   -  ")))  {
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
}

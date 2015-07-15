/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.GPEDSCVP.validacao;

import java.awt.Component;
import java.awt.Container;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author Willys
 */

//Método para desabilitar todos os campos contidos no container passado por parametro
public class ValidaCampos {
    
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
               //Desabilita todos JRadioButton do container
               if (component instanceof JRadioButton){
                    JRadioButton JRBT = (JRadioButton) component; 
                    if(JRBT.isEnabled()){
                        JRBT.setEnabled(false);
                   }
               }
           }
     }
}

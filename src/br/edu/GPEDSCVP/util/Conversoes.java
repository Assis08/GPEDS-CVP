/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.GPEDSCVP.util;

import java.text.DecimalFormat;
import javax.swing.JOptionPane;

/**
 *
 * @author Willys
 */
public class Conversoes {
    
    //Método para formatar um valor double para um objeto decimal format
    public static Object doubleParaObjectDecimalFormat(Double valor) {
       Object valor_format;
       try {
            DecimalFormat dFormat = new DecimalFormat("#,###,##0.00") ;
            valor_format = dFormat.format(valor);
            return valor_format;
      }
      catch (Exception error_mask) {
          JOptionPane.showMessageDialog(null,"Erro: "+error_mask);
      }
       return null;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.GPEDSCVP.util;

import javax.swing.JOptionPane;

/**
 *
 * @author Willys
 */
public class Mensagens {
    
    //Mensagem de SIM (0) ou Não (1)
    public int ValidaMensagem (String mensagem){
        
        Object[] options = { "Sim", "Não" };  
        int result = JOptionPane.showOptionDialog(null,  
        mensagem,  
        "", JOptionPane.YES_NO_OPTION,  
        JOptionPane.QUESTION_MESSAGE, null, options, options[0]);   

        return result;
}
    
    
}

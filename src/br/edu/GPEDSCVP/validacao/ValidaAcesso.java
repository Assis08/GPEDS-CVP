/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.GPEDSCVP.validacao;

import br.edu.GPEDSCVP.classe.Acesso;
import br.edu.GPEDSCVP.classe.Permissao;
import javax.swing.JOptionPane;


/**
 *
 * @author Willys
 */
public class ValidaAcesso {
    
    public boolean verificaAcesso(String tipo, Acesso acesso, Permissao permissao){
        
        if(acesso.getIn_gerente() == 1){
            return true;
        }
        
        switch(tipo){
            case "acesso":
                if (permissao.getAcesso().equals("S")){
                    return true;
                }else{
                    return false;
                }    
            
            case "inserir":
                if (permissao.getInserir().equals("S")){
                    return true;
                }else{
                    return false;
                }
                
            case "alterar":
                 if (permissao.getAlterar().equals("S")){
                    return true;
                }else{
                    return false;
                }
                
            case "excluir":
                if (permissao.getExcluir().equals("S")){
                    return true;
                }else{
                    return false;
                }
                
            case "consultar":
                if (permissao.getConsultar().equals("S")){
                    return true;
                }else{
                    return false;
                }          
        }
        
        return false;
    }
    
}

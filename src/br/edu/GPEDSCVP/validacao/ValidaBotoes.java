/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.GPEDSCVP.validacao;

import java.awt.Component;
import java.awt.Container;
import javax.swing.JButton;

/**
 *
 * @author Willys
 */
public class ValidaBotoes {
    
    //Método para validar o estado dos botoes e setar os botoes como habilitado ou não
    public void ValidaEstado(Container container, int estado) {
        Component components[] = container.getComponents();
        for (Component component : components) {
            if (component instanceof JButton) {
                JButton field = (JButton) component;
                String texto = field.getText();
                if ((estado == Rotinas.INCLUIR) || (estado == Rotinas.ALTERAR)){
                    if (texto.equals("Novo") || (texto.equals("Alterar")) || (texto.equals("Excluir"))) {
                        field.setEnabled(false);
                    } else {
                        field.setEnabled(true);
                    }
                } else if (estado == Rotinas.PADRAO) {
                    if ((texto.equals("Alterar")) || (texto.equals("Excluir")) || (texto.equals("Cancelar"))) {
                        field.setEnabled(true);
                    } else {
                        field.setEnabled(false);
                    }
                } else if (estado == Rotinas.INICIAL) {
                    if  ((texto.equals("Alterar")) || (texto.equals("Excluir")) || (texto.equals("Cancelar")) || (texto.equals("Gravar"))) {
                        field.setEnabled(false);
                    } else {
                        field.setEnabled(true);
                    }
                }
            }
        }
    }
}

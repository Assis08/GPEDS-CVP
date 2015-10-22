/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.GPEDSCVP.classe;

import javax.swing.JTable;

/**
 *
 * @author Willys
 */
public class ComposicaoComponente {
    
    private int qntd;
    private int id_componente;
    private static JTable tabela;

    public ComposicaoComponente() {
        qntd = 0;
        id_componente = 0;
    }

    public int getQntd() {
        return qntd;
    }

    public void setQntd(int qntd) {
        this.qntd = qntd;
    }

    public static JTable getTabela() {
        return tabela;
    }

    public static void setTabela(JTable tabela) {
        ComposicaoComponente.tabela = tabela;
    }

    public int getId_componente() {
        return id_componente;
    }

    public void setId_componente(int id_componente) {
        this.id_componente = id_componente;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.GPEDSCVP.classe;

import java.sql.Date;
import javax.swing.JTable;

/**
 *
 * @author Willys
 */
public class ComposicaoComponente {
    
    private int qntd;
    private int id_componente;
    private Date data_cadastro;
    private Date data_alter;
    private static JTable tabela;
    

    public ComposicaoComponente() {
        qntd = 0;
        id_componente = 0;
        data_cadastro = null;
        data_alter = null;
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

    public Date getData_cadastro() {
        return data_cadastro;
    }

    public void setData_cadastro(Date data_cadastro) {
        this.data_cadastro = data_cadastro;
    }

    public Date getData_alter() {
        return data_alter;
    }

    public void setData_alter(Date data_alter) {
        this.data_alter = data_alter;
    }
}

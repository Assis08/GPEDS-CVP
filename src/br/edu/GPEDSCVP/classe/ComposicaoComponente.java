/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.GPEDSCVP.classe;

import java.sql.Date;
import java.sql.ResultSet;
import javax.swing.JTable;

/**
 *
 * @author Willys
 */
public class ComposicaoComponente {
    
    private static int situacao;
    private int qntd;
    private static int id_componente;
    private int id_subcomponente;
    private int id_composicao;
    private Date data_cadastro;
    private Date data_alter;
    private static JTable tabela;
    private ResultSet retorno;
    

    public ComposicaoComponente() {
        qntd = 0;
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

    public static int getId_componente() {
        return id_componente;
    }

    public static void setId_componente(int id_componente) {
        ComposicaoComponente.id_componente = id_componente;
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

    public ResultSet getRetorno() {
        return retorno;
    }

    public void setRetorno(ResultSet retorno) {
        this.retorno = retorno;
    } 

    public int getId_subcomponente() {
        return id_subcomponente;
    }

    public void setId_subcomponente(int id_subcomponente) {
        this.id_subcomponente = id_subcomponente;
    }

    public int getId_composicao() {
        return id_composicao;
    }

    public void setId_composicao(int id_composicao) {
        this.id_composicao = id_composicao;
    }

    public int getSituacao() {
        return situacao;
    }

    public void setSituacao(int situacao) {
        this.situacao = situacao;
    }
}

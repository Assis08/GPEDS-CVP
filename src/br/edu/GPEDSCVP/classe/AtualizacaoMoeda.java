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
public class AtualizacaoMoeda {
    
    private int id_atualizacao;
    private int id_moeda;
    private Date data_alter;
    private double valor;
    private ResultSet retorno;
    private JTable tabela;

    public AtualizacaoMoeda() {
        id_atualizacao = 0;
        id_moeda = 0;
        data_alter = null;
        valor = 0;
    }
    
    public int getId_atualizacao() {
        return id_atualizacao;
    }

    public void setId_atualizacao(int id_atualizacao) {
        this.id_atualizacao = id_atualizacao;
    }

    public int getId_moeda() {
        return id_moeda;
    }

    public void setId_moeda(int id_moeda) {
        this.id_moeda = id_moeda;
    }

    public Date getData_alter() {
        return data_alter;
    }

    public void setData_alter(Date data_alter) {
        this.data_alter = data_alter;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public ResultSet getRetorno() {
        return retorno;
    }

    public void setRetorno(ResultSet retorno) {
        this.retorno = retorno;
    }

    public JTable getTabela() {
        return tabela;
    }

    public void setTabela(JTable tabela) {
        this.tabela = tabela;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.GPEDSCVP.classe;

import java.sql.Date;
import java.sql.ResultSet;

/**
 *
 * @author Willys
 */
public class Moeda {
    
    private int id_moeda;
    private String decricao;
    private String unidade;
    private Date data_alter;
    private ResultSet retorno;
    private int[] array_moeda;

    public Moeda() {
        id_moeda = 0;
        decricao = "";
        unidade = "";
        data_alter = null;
    }

    public int getId_moeda() {
        return id_moeda;
    }

    public void setId_moeda(int id_moeda) {
        this.id_moeda = id_moeda;
    }

    public String getDecricao() {
        return decricao;
    }

    public void setDecricao(String decricao) {
        this.decricao = decricao;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public ResultSet getRetorno() {
        return retorno;
    }

    public void setRetorno(ResultSet retorno) {
        this.retorno = retorno;
    }

    public Date getData_alter() {
        return data_alter;
    }

    public void setData_alter(Date data_alter) {
        this.data_alter = data_alter;
    }

    public int getArray_moeda(int posicao) {
        return array_moeda[posicao];
    }

    public void setArray_moeda(int[] array_moeda) {
        this.array_moeda = array_moeda;
    }
}

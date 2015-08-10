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
public class Cidade {
    
    private int id_cidade;
    private String uf;
    private String descricao;
    private Date data_alter;
    private JTable tabela;
    private ResultSet retorno;
    private int[] array_cidade;

    public Cidade() {
        id_cidade = 0;
        uf = "";
        descricao = "";
        data_alter = null;
    }

    public int getId_cidade() {
        return id_cidade;
    }

    public void setId_cidade(int id_cidade) {
        this.id_cidade = id_cidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getData_alter() {
        return data_alter;
    }

    public void setData_alter(Date data_alter) {
        this.data_alter = data_alter;
    }

    public JTable getTabela() {
        return tabela;
    }

    public void setTabela(JTable tabela) {
        this.tabela = tabela;
    }

    public ResultSet getRetorno() {
        return retorno;
    }

    public void setRetorno(ResultSet retorno) {
        this.retorno = retorno;
    }  

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public int getArray_cidade(int posicao) {
        return array_cidade[posicao];
    }

    public void setArray_cidade(int[] array_cidade) {
        this.array_cidade = array_cidade;
    }
}

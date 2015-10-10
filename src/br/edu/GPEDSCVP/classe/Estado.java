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
public class Estado {
    
    private int id_uf;
    private String uf;
    private int id_pais;
    private String descricao;
    private String sigla_pais;
    private Date data_alter;
    private JTable tabela;
    private ResultSet retorno;
    private int[] array_estado;

    public Estado() {
        id_uf = 0;
        uf = "";
        id_pais = 0;
        descricao = "";
        sigla_pais = "";
        data_alter = null;
    }

    public int getId_uf() {
        return id_uf;
    }

    public void setId_uf(int id_uf) {
        this.id_uf = id_uf;
    }
    
    public String getUf() {
        return uf;
    }

    public String getSigla_pais() {
        return sigla_pais;
    }

    public void setSigla_pais(String sigla_pais) {
        this.sigla_pais = sigla_pais;
    }

    
    public void setUf(String uf) {
        this.uf = uf;
    }

    public int getId_pais() {
        return id_pais;
    }

    public void setId_pais(int id_pais) {
        this.id_pais = id_pais;
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

    public int getArray_estado(int posicao) {
        return array_estado[posicao];
    }

    public void setArray_estado(int[] array_estado) {
        this.array_estado = array_estado;
    }
}

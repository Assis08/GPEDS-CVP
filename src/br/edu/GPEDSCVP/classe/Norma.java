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
 * @author rafa
 */
public class Norma {
    
    private int id_norma;
    private String descricao;
    private String titulo;
    private String edicao;
    private ResultSet retorno;
    private Date data_cadastro;
    private Date data_alter;
    private int[] array_norma;

    public Norma() {
        this.id_norma = 0;
        this.descricao = "";
        this.titulo = "";
        this.edicao = "";
        this.data_cadastro = null;
        this.data_alter = null;
    }

    public int getId_norma() {
        return id_norma;
    }

    public void setId_norma(int id_norma) {
        this.id_norma = id_norma;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEdicao() {
        return edicao;
    }

    public void setEdicao(String edicao) {
        this.edicao = edicao;
    }

    public ResultSet getRetorno() {
        return retorno;
    }

    public void setRetorno(ResultSet retorno) {
        this.retorno = retorno;
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

    public int getArray_norma(int posicao) {
        return array_norma[posicao];
    }

    public void setArray_norma(int[] array_norma) {
        this.array_norma = array_norma;
    }
}

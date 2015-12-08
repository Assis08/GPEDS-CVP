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
 * @author rafa
 */
public class Certificacao {
    
    private int id_certificacao;
    private int cod_versao_projeto;
    private String versao;
    private int id_projeto;
    private String ds_projeto;
    private int id_certificadora;
    private String ds_certificadora;
    private int id_norma;
    private String ds_norma;
    private String descricao;
    private String resultado;
    private String desc_reprov;
    private Date data_ensaio;
    private Date data_cadastro;
    private Date data_alter;
    private double valor;
    private String in_ativo;
    private static JTable tabela;
    private ResultSet retorno;

    public Certificacao() {
        id_certificacao = 0;
        cod_versao_projeto = 0;
        versao = "";
        id_projeto = 0;
        ds_projeto = "";
        id_certificadora = 0;
        ds_certificadora = "";
        id_norma = 0;
        ds_norma = ""; 
        descricao = "";
        resultado = "";
        desc_reprov = "";
        data_ensaio = null;
        data_cadastro = null;
        data_alter = null;
        valor = 0;
        in_ativo = "";
    }

    public int getId_certificacao() {
        return id_certificacao;
    }

    public void setId_certificacao(int id_certificao) {
        this.id_certificacao = id_certificao;
    }

    public int getCod_versao_projeto() {
        return cod_versao_projeto;
    }

    public void setCod_versao_projeto(int cod_versao_projeto) {
        this.cod_versao_projeto = cod_versao_projeto;
    }

    public int getId_projeto() {
        return id_projeto;
    }

    public void setId_projeto(int id_projeto) {
        this.id_projeto = id_projeto;
    }

    public int getId_certificadora() {
        return id_certificadora;
    }

    public void setId_certificadora(int id_certificadora) {
        this.id_certificadora = id_certificadora;
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

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getDesc_reprov() {
        return desc_reprov;
    }

    public void setDesc_reprov(String desc_reprov) {
        this.desc_reprov = desc_reprov;
    }

    public Date getData_ensaio() {
        return data_ensaio;
    }

    public void setData_ensaio(Date data_ensaio) {
        this.data_ensaio = data_ensaio;
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

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getIn_ativo() {
        return in_ativo;
    }

    public void setIn_ativo(String in_ativo) {
        this.in_ativo = in_ativo;
    }

    public static JTable getTabela() {
        return tabela;
    }

    public static void setTabela(JTable tabela) {
        Certificacao.tabela = tabela;
    }

    public ResultSet getRetorno() {
        return retorno;
    }

    public void setRetorno(ResultSet retorno) {
        this.retorno = retorno;
    }

    public String getVersao() {
        return versao;
    }

    public void setVersao(String versao) {
        this.versao = versao;
    }

    public String getDs_projeto() {
        return ds_projeto;
    }

    public void setDs_projeto(String ds_projeto) {
        this.ds_projeto = ds_projeto;
    }

    public String getDs_certificadora() {
        return ds_certificadora;
    }

    public void setDs_certificadora(String ds_certificadora) {
        this.ds_certificadora = ds_certificadora;
    }

    public String getDs_norma() {
        return ds_norma;
    }

    public void setDs_norma(String ds_norma) {
        this.ds_norma = ds_norma;
    }
}

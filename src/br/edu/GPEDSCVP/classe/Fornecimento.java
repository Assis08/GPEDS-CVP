/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.GPEDSCVP.classe;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Timestamp;
import javax.swing.JTable;

/**
 *
 * @author Willys
 */
public class Fornecimento {
    
    private int id_fornecimento;
    private int id_moeda_imp;
    private String ds_moeda_imp;
    private int id_moeda_frete;
    private String ds_moeda_frete;
    private int id_pessoa;
    private String ds_pessoa;
    private String descricao;
    private Timestamp data_cadastro;
    private double valor_frete;
    private double valor_impostos;
    private Date data_alter;
    private static JTable tabela;
    private ResultSet retorno;

    public Fornecimento() {
        id_fornecimento = 0;
        id_moeda_imp = 0;
        id_moeda_frete = 0;
        id_pessoa = 0;
        descricao = "";
        data_cadastro = null;
        valor_frete = 0;
        valor_impostos = 0;
        data_alter = null;
    }
    
    public int getId_fornecimento() {
        return id_fornecimento;
    }

    public void setId_fornecimento(int id_fornecimento) {
        this.id_fornecimento = id_fornecimento;
    }

    public int getId_moeda_imp() {
        return id_moeda_imp;
    }

    public void setId_moeda_imp(int id_moeda_imp) {
        this.id_moeda_imp = id_moeda_imp;
    }

    public int getId_moeda_frete() {
        return id_moeda_frete;
    }

    public void setId_moeda_frete(int id_moeda_frete) {
        this.id_moeda_frete = id_moeda_frete;
    }

    public int getId_pessoa() {
        return id_pessoa;
    }

    public void setId_pessoa(int id_pessoa) {
        this.id_pessoa = id_pessoa;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Timestamp getData_cadastro() {
        return data_cadastro;
    }

    public void setData_cadastro(Timestamp data_cadastro) {
        this.data_cadastro = data_cadastro;
    }

    public double getValor_frete() {
        return valor_frete;
    }

    public void setValor_frete(double valor_frete) {
        this.valor_frete = valor_frete;
    }

    public double getValor_impostos() {
        return valor_impostos;
    }

    public void setValor_impostos(double valor_impostos) {
        this.valor_impostos = valor_impostos;
    }

    public Date getData_alter() {
        return data_alter;
    }

    public void setData_alter(Date data_alter) {
        this.data_alter = data_alter;
    }

    public static JTable getTabela() {
        return tabela;
    }

    public static void setTabela(JTable tabela) {
        Fornecimento.tabela = tabela;
    }

    public ResultSet getRetorno() {
        return retorno;
    }

    public void setRetorno(ResultSet retorno) {
        this.retorno = retorno;
    }

    public String getDs_moeda_imp() {
        return ds_moeda_imp;
    }

    public void setDs_moeda_imp(String ds_moeda_imp) {
        this.ds_moeda_imp = ds_moeda_imp;
    }

    public String getDs_moeda_frete() {
        return ds_moeda_frete;
    }

    public void setDs_moeda_frete(String ds_moeda_frete) {
        this.ds_moeda_frete = ds_moeda_frete;
    }

    public String getDs_pessoa() {
        return ds_pessoa;
    }

    public void setDs_pessoa(String ds_pessoa) {
        this.ds_pessoa = ds_pessoa;
    }
    
}

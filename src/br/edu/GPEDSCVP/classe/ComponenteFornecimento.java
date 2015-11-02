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
public class ComponenteFornecimento {
    
    private static int situacao;
    private int id_comp_fornec;
    private int id_componente;
    private String descricao;
    private int id_fornecimento;
    private int id_moeda;
    private String moeda;
    private int qntd_componente;
    private double imposto_unit;
    private double valor_unit;
    private Date data_alter;
    private static JTable tabela;
    private ResultSet retorno;

    public ComponenteFornecimento() {
        id_comp_fornec = 0;
        descricao = "";
        moeda = "";
        id_componente = 0;
        id_fornecimento = 0;
        id_moeda = 0;
        qntd_componente = 0;
        imposto_unit = 0;
        valor_unit = 0;
        data_alter = null;
    }

    public static int getSituacao() {
        return situacao;
    }

    public static void setSituacao(int situacao) {
        ComponenteFornecimento.situacao = situacao;
    }

    public int getId_comp_fornec() {
        return id_comp_fornec;
    }

    public void setId_comp_fornec(int id_comp_fornec) {
        this.id_comp_fornec = id_comp_fornec;
    }

    public int getId_componente() {
        return id_componente;
    }

    public void setId_componente(int id_componente) {
        this.id_componente = id_componente;
    }

    public int getId_fornecimento() {
        return id_fornecimento;
    }

    public void setId_fornecimento(int id_fornecimento) {
        this.id_fornecimento = id_fornecimento;
    }

    public int getId_moeda() {
        return id_moeda;
    }

    public void setId_moeda(int id_moeda) {
        this.id_moeda = id_moeda;
    }

    public int getQntd_componente() {
        return qntd_componente;
    }

    public void setQntd_componente(int qntd_componente) {
        this.qntd_componente = qntd_componente;
    }

    public double getImposto_unit() {
        return imposto_unit;
    }

    public void setImposto_unit(double imposto_unit) {
        this.imposto_unit = imposto_unit;
    }

    public double getValor_unit() {
        return valor_unit;
    }

    public void setValor_unit(double valor_unit) {
        this.valor_unit = valor_unit;
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
        ComponenteFornecimento.tabela = tabela;
    }

    public ResultSet getRetorno() {
        return retorno;
    }

    public void setRetorno(ResultSet retorno) {
        this.retorno = retorno;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getMoeda() {
        return moeda;
    }

    public void setMoeda(String moeda) {
        this.moeda = moeda;
    }
}

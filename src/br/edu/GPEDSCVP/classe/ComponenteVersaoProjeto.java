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
public class ComponenteVersaoProjeto {
    
    private int id_comp_versao;
    private static int id_projeto;
    private String projeto;
    private int cod_vers_projeto;
    private static double versao;
    private int id_fornecimento;
    private int id_componente;
    private String componente;
    private double valor_unit;
    private int id_moeda;
    private String unidade;
    private static String tipo;
    private static int id_comp_fornec;
    private int qntd_para_projeto;
    private int qntd_no_projeto;
    private String situacao;
    private Date data_alter;
    private static JTable tabela;
    private ResultSet retorno;

    public ComponenteVersaoProjeto() {
        id_comp_versao = 0;
        projeto = "";
        cod_vers_projeto = 0;
        id_fornecimento = 0;
        id_componente = 0;
        componente = "";
        qntd_para_projeto = 0;
        qntd_no_projeto = 0;
        situacao = "";
        data_alter = null;
    }

    public int getId_comp_versao() {
        return id_comp_versao;
    }

    public void setId_comp_versao(int id_comp_versao) {
        this.id_comp_versao = id_comp_versao;
    }

    public int getId_projeto() {
        return id_projeto;
    }

    public void setId_projeto(int id_projeto) {
        this.id_projeto = id_projeto;
    }

    public int getCod_vers_projeto() {
        return cod_vers_projeto;
    }

    public void setCod_vers_projeto(int cod_vers_projeto) {
        this.cod_vers_projeto = cod_vers_projeto;
    }

    public int getId_fornecimento() {
        return id_fornecimento;
    }

    public void setId_fornecimento(int id_fornecimento) {
        this.id_fornecimento = id_fornecimento;
    }

    public int getId_componente() {
        return id_componente;
    }

    public void setId_componente(int id_componente) {
        this.id_componente = id_componente;
    }

    public static int getId_comp_fornec() {
        return id_comp_fornec;
    }

    public static void setId_comp_fornec(int id_comp_fornec) {
        ComponenteVersaoProjeto.id_comp_fornec = id_comp_fornec;
    }

    public int getQntd_para_projeto() {
        return qntd_para_projeto;
    }

    public void setQntd_para_projeto(int qntd_para_projeto) {
        this.qntd_para_projeto = qntd_para_projeto;
    }

    public int getQntd_no_projeto() {
        return qntd_no_projeto;
    }

    public void setQntd_no_projeto(int qntd_no_projeto) {
        this.qntd_no_projeto = qntd_no_projeto;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
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
        ComponenteVersaoProjeto.tabela = tabela;
    }

    public ResultSet getRetorno() {
        return retorno;
    }

    public void setRetorno(ResultSet retorno) {
        this.retorno = retorno;
    }

    public String getProjeto() {
        return projeto;
    }

    public void setProjeto(String projeto) {
        this.projeto = projeto;
    }

    public String getComponente() {
        return componente;
    }

    public void setComponente(String componente) {
        this.componente = componente;
    }

    public double getVersao() {
        return versao;
    }

    public void setVersao(double versao) {
        this.versao = versao;
    }

    public static String getTipo() {
        return tipo;
    }

    public static void setTipo(String tipo) {
        ComponenteVersaoProjeto.tipo = tipo;
    }

    public int getId_moeda() {
        return id_moeda;
    }

    public void setId_moeda(int id_moeda) {
        this.id_moeda = id_moeda;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public double getValor_unit() {
        return valor_unit;
    }

    public void setValor_unit(double valor_unit) {
        this.valor_unit = valor_unit;
    }
    
}

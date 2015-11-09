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
public class VersaoProjeto {
    
    private int cod_vers_projeto;
    private int id_projeto;
    private int versao;
    private int oculto;
    private String comercializado;
    private int lote;
    private String certificacao;
    private Date data_cadastro;
    private Date data_alter;
    private JTable tabela;
    private ResultSet retorno;
    private int[] array_versoes;

    public VersaoProjeto() {
        cod_vers_projeto = 0;
        id_projeto = 0;
        versao = 0;
        oculto = 0;
        comercializado = "";
        lote = 0;
        certificacao = "";
        data_cadastro = null;
        data_alter = null;
    }

    public int getCod_vers_projeto() {
        return cod_vers_projeto;
    }

    public void setCod_vers_projeto(int cod_vers_projeto) {
        this.cod_vers_projeto = cod_vers_projeto;
    }

    public int getId_projeto() {
        return id_projeto;
    }

    public void setId_projeto(int id_projeto) {
        this.id_projeto = id_projeto;
    }

    public int getVersao() {
        return versao;
    }

    public void setVersao(int versao) {
        this.versao = versao;
    }

    public int getOculto() {
        return oculto;
    }

    public void setOculto(int oculto) {
        this.oculto = oculto;
    }

    public String getComercializado() {
        return comercializado;
    }

    public void setComercializado(String comercializado) {
        this.comercializado = comercializado;
    }

    public int getLote() {
        return lote;
    }

    public void setLote(int lote) {
        this.lote = lote;
    }

    public String getCertificacao() {
        return certificacao;
    }

    public void setCertificacao(String certificacao) {
        this.certificacao = certificacao;
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

    public int getArray_versoes(int posicao) {
        return array_versoes[posicao];
    }

    public void setArray_versoes(int[] array_versoes) {
        this.array_versoes = array_versoes;
    }
}

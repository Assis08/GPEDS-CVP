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
public class Permissao
{
    private int id_permissao;
    private int id_usuario;
    private int id_tela;
    private String nome_tela;
    private static String acesso;
    private String inserir;
    private String alterar;
    private String excluir;
    private String consultar;
    private Date data_alter;
    private int in_gerente;
    private ResultSet retorno;
    private JTable tabela;

    public Permissao()
    {
        id_permissao = 0;
        id_usuario = 0;
        id_tela = 0;
        nome_tela = "";
        acesso = "";
        inserir = "";
        alterar = "";
        excluir = "";
        consultar = "";
        data_alter = null;

    }

    public int getId_permissao()
    {
        return id_permissao;
    }

    public void setId_permissao(int id_permissao)
    {
        this.id_permissao = id_permissao;
    }

    public int getId_usuario()
    {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario)
    {
        this.id_usuario = id_usuario;
    }

    public String getAcesso()
    {
        return acesso;
    }

    public void setAcesso(String acesso)
    {
        this.acesso = acesso;
    }

    public String getInserir()
    {
        return inserir;
    }

    public void setInserir(String inserir)
    {
        this.inserir = inserir;
    }

    public String getAlterar()
    {
        return alterar;
    }

    public void setAlterar(String alterar)
    {
        this.alterar = alterar;
    }

    public String getExcluir()
    {
        return excluir;
    }

    public void setExcluir(String excluir)
    {
        this.excluir = excluir;
    }

    public String getConsultar()
    {
        return consultar;
    }

    public void setConsultar(String consultar)
    {
        this.consultar = consultar;
    }

    public Date getData_alter()
    {
        return data_alter;
    }

    public void setData_alter(Date data_alter)
    {
        this.data_alter = data_alter;
    }

    public ResultSet getRetorno()
    {
        return retorno;
    }

    public void setRetorno(ResultSet retorno)
    {
        this.retorno = retorno;
    }

    public int getId_tela() {
        return id_tela;
    }

    public void setId_tela(int id_tela) {
        this.id_tela = id_tela;
    }

    public JTable getTabela() {
        return tabela;
    }

    public void setTabela(JTable tabela) {
        this.tabela = tabela;
    }

    public String getNome_tela() {
        return nome_tela;
    }

    public void setNome_tela(String nome_tela) {
        this.nome_tela = nome_tela;
    }

    public int getIn_gerente() {
        return in_gerente;
    }

    public void setIn_gerente(int in_gerente) {
        this.in_gerente = in_gerente;
    }
}


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.GPEDSCVP.classe;

import java.sql.ResultSet;

/**
 *
 * @author Willys
 */
public class Permissao
{
    private int id_permissao;
    private int id_usuario;
    private int acesso;
    private int inserir;
    private int alterar;
    private int excluir;
    private int consultar;
    private String data_alter;
    private ResultSet retorno;

    public Permissao()
    {
        this.id_permissao = id_permissao;
        this.id_usuario = id_usuario;
        this.acesso = acesso;
        this.inserir = inserir;
        this.alterar = alterar;
        this.excluir = excluir;
        this.consultar = consultar;
        this.data_alter = data_alter;
        this.retorno = retorno;
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

    public int getAcesso()
    {
        return acesso;
    }

    public void setAcesso(int acesso)
    {
        this.acesso = acesso;
    }

    public int getInserir()
    {
        return inserir;
    }

    public void setInserir(int inserir)
    {
        this.inserir = inserir;
    }

    public int getAlterar()
    {
        return alterar;
    }

    public void setAlterar(int alterar)
    {
        this.alterar = alterar;
    }

    public int getExcluir()
    {
        return excluir;
    }

    public void setExcluir(int excluir)
    {
        this.excluir = excluir;
    }

    public int getConsultar()
    {
        return consultar;
    }

    public void setConsultar(int consultar)
    {
        this.consultar = consultar;
    }

    public String getData_alter()
    {
        return data_alter;
    }

    public void setData_alter(String data_alter)
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

    
}


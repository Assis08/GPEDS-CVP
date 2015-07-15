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
public class Tela {
    
    private int id_tela;
    private String descricao;
    private ResultSet retorno;
    
    public Tela()
    {
        this.id_tela = id_tela;
        this.descricao = descricao;
        this.retorno = retorno;
    }

    public int getId_tela()
    {
        return id_tela;
    }

    public void setId_tela(int id_tela)
    {
        this.id_tela = id_tela;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
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

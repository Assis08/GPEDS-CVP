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
public class Acesso {
    
    private int id_acesso;
    private int id_usuario;
    private String data_acesso;
    private ResultSet retorno;
    
    public Acesso()
    {
        this.id_acesso = id_acesso;
        this.id_usuario = id_usuario;
        this.data_acesso = data_acesso;
        this.retorno = retorno;
    }

    public int getId_acesso()
    {
        return id_acesso;
    }

    public void setId_acesso(int id_acesso)
    {
        this.id_acesso = id_acesso;
    }

    public int getId_usuario()
    {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario)
    {
        this.id_usuario = id_usuario;
    }

    public String getData_acesso()
    {
        return data_acesso;
    }

    public void setData_acesso(String data_acesso)
    {
        this.data_acesso = data_acesso;
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


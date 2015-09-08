/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.GPEDSCVP.classe;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Timestamp;

/**
 *
 * @author Willys
 */
public class Acesso {
    
    private int id_acesso;
    private Date data_acesso;
    private ResultSet retorno;
    
    private static int id_usuario;
    private static String nome_usuario;
    private static String login_usuario;
    
    public Acesso()
    {
       id_acesso = 0;
       data_acesso = null;
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

    public Date getData_acesso()
    {
        return data_acesso;
    }

    public void setData_acesso(Date data_acesso)
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

    public static String getNome_usuario() {
        return nome_usuario;
    }

    public static void setNome_usuario(String nome_usuario) {
        Acesso.nome_usuario = nome_usuario;
    }

    public static String getLogin_usuario() {
        return login_usuario;
    }

    public static void setLogin_usuario(String login_usuario) {
        Acesso.login_usuario = login_usuario;
    }

    
}


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.GPEDSCVP.classe;

import sun.security.util.Password;

/**
 *
 * @author Willys
 */
public class Usuario extends PessoaFisica
{
    private String login;
    private String senha;
    private int in_gerente;
    
    public Usuario()
    {
        login = "";
        senha = "";
        in_gerente = 0;
    }

    public String getLogin()
    {
        return login;
    }

    public void setLogin(String login)
    {
        this.login = login;
    }

    public String getSenha()
    {
        return senha;
    }

    public void setSenha(String senha)
    {
        this.senha = senha;
    }

    public int getIn_gerente()
    {
        return in_gerente;
    }

    public void setIn_gerente(int in_gerente)
    {
        this.in_gerente = in_gerente;
    }
}


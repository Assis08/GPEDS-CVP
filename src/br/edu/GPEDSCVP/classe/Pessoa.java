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
public class Pessoa
{
    private int id_pessoa;
    private String nome;
    private String cpf_cnpj;
    private Date data_cadastro;
    private Date data_alter;
    private ResultSet retorno;
    private static JTable tabela;
    
    public Pessoa()
    {
        id_pessoa = 0;
        nome = "";
        cpf_cnpj = "";
        data_cadastro = null;
        data_alter = null;
        retorno = null;
    }

    public int getId_pessoa()
    {
        return id_pessoa;
    }

    public void setId_pessoa(int id_pessoa)
    {
        this.id_pessoa = id_pessoa;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public String getCpf_cnpj()
    {
        return cpf_cnpj;
    }

    public void setCpf_cnpj(String cpf_cnpj)
    {
        this.cpf_cnpj = cpf_cnpj;
    }

    public Date getData_cadastro()
    {
        return data_cadastro;
    }

    public void setData_cadastro(Date data_cadastro)
    {
        this.data_cadastro = data_cadastro;
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

    public JTable getTabela() {
        return tabela;
    }

    public void setTabela(JTable tabela) {
        this.tabela = tabela;
    }
                 
}


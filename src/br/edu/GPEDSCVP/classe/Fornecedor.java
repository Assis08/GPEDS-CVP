/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.GPEDSCVP.classe;

import javax.swing.JTable;

/**
 *
 * @author Willys
 */
public class Fornecedor extends PessoaJuridica
{
    private String site;
    private String ramo;

    public Fornecedor()
    {
        site = "";
        ramo = "";
    }

    public String getSite()
    {
        return site;
    }

    public void setSite(String site)
    {
        this.site = site;
    }

    public String getRamo()
    {
        return ramo;
    }

    public void setRamo(String ramo)
    {
        this.ramo = ramo;
    }
}


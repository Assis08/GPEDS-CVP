/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.GPEDSCVP.classe;

import java.sql.Date;

/**
 *
 * @author Willys
 */
public class PessoaFisica extends Pessoa
{
    private Date dt_nasc;
    private String rg;
    private String sexo;
    
    public PessoaFisica()
    {
        dt_nasc = null;
        rg = "";
        sexo = "";
    }

    public Date getDt_nasc()
    {
        return dt_nasc;
    }

    public void setDt_nasc(Date dt_nasc)
    {
        this.dt_nasc = dt_nasc;
    }

    public String getRg()
    {
        return rg;
    }

    public void setRg(String rg)
    {
        this.rg = rg;
    }

    public String getSexo()
    {
        return sexo;
    }

    public void setSexo(String sexo)
    {
        this.sexo = sexo;
    }

    
}


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.GPEDSCVP.classe;

/**
 *
 * @author Willys
 */
public class PessoaJuridica extends Pessoa
{
     private String razao_social;
     private String internacional;
    
    public PessoaJuridica()
    {
        razao_social = "";
        internacional = "";
    }

    public String getRazao_social()
    {
        return razao_social;
    }

    public void setRazao_social(String razao_social)
    {
        this.razao_social = razao_social;
    }

    public String getInternacional() {
        return internacional;
    }

    public void setInternacional(String internacional) {
        this.internacional = internacional;
    }
}


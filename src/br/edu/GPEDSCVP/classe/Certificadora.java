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
public class Certificadora extends PessoaJuridica
{
    private char in_calibracoes;
    
    public Certificadora()
    {
        in_calibracoes = '\0';
    }

    public char getIn_calibracoes()
    {
        return in_calibracoes;
    }

    public void setIn_calibracoes(char in_calibracoes)
    {
        this.in_calibracoes = in_calibracoes;
    }

    
}


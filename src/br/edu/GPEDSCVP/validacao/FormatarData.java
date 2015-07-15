/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.GPEDSCVP.validacao;

import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 *
 * @author Willys
 */
public class FormatarData {

    public FormatarData()
    {
    }

    public String DataAtual()
    {
        Date data = new Date(System.currentTimeMillis());
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
        String data_atual = fmt.format(data);
        return data_atual;
    }

    public static Date dateParaSQLDate(java.util.Date data)
    {
        try
        {
            return new Date(data.getTime());
        }
        catch(Exception ex)
        {
            return null;
        }
    }

    
}

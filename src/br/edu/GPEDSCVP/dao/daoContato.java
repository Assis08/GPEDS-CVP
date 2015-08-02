/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.GPEDSCVP.dao;

import br.edu.GPEDSCVP.classe.Contato;
import br.edu.GPEDSCVP.conexao.ConexaoBanco;
import br.edu.GPEDSCVP.validacao.FormatarData;
import br.edu.GPEDSCVP.validacao.UltimaSequencia;
import java.sql.SQLException;

/**
 *
 * @author Willys
 */
public class daoContato {
    
    ConexaoBanco conecta_banco;
    UltimaSequencia ultima;
    
    public daoContato()
    {
        try
        {
            conecta_banco = new ConexaoBanco();
        }
        catch(SQLException ex)
        {
            
        }
    }
    
    public void incluir(Contato contato)throws SQLException
    {
       
        //Insert de contato
        ultima = new UltimaSequencia();
        int sequencia = (Integer) (ultima.ultimasequencia("CONTATO","ID_CONTATO"));
        String sql = "INSERT INTO CONTATO VALUES ("
                + sequencia + ","
                + contato.getId_pessoa()+ ","
                + contato.getFone()+ ",'"
                + contato.getDescricao()+ "','"
                + contato.getTipo()+"','"
                + FormatarData.dateParaTimeStamp(contato.getData_alter())+ "')";

                conecta_banco.incluirSQL(sql);
    }
    
    
    
}

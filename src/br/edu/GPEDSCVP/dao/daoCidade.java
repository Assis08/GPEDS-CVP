/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.GPEDSCVP.dao;

import br.edu.GPEDSCVP.classe.Cidade;
import br.edu.GPEDSCVP.conexao.ConexaoBanco;
import br.edu.GPEDSCVP.validacao.UltimaSequencia;
import java.sql.SQLException;

/**
 *
 * @author Willys
 */
public class daoCidade {
    
    ConexaoBanco conecta_banco;
    UltimaSequencia ultima;
    
    public daoCidade()
    {
        try
        {
            conecta_banco = new ConexaoBanco();
        }
        catch(SQLException ex)
        {
            
        }
    }
    
    public void consultageral(Cidade cidade){
        String sql = "select * from cidade";
        conecta_banco.executeSQL(sql);
        cidade.setRetorno(conecta_banco.resultset);
    }
    
}

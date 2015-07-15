/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.GPEDSCVP.dao;

import br.edu.GPEDSCVP.classe.Pessoa;
import br.edu.GPEDSCVP.conexao.ConexaoBanco;
import br.edu.GPEDSCVP.validacao.UltimaSequencia;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Willys
 */
public class daoPessoa {
    
    ConexaoBanco conecta_banco;
    UltimaSequencia ultima;
    
    public daoPessoa()
    {
        try
        {
            conecta_banco = new ConexaoBanco();
        }
        catch(SQLException ex)
        {
            
        }
    }

    public void incluir(Pessoa pessoa)
        throws SQLException
    {/*
        ultima = new UltimaSequencia();
        int sequencia = Integer.valueOf(ultima.ultimasequencia("Pessoa", "id_pessoa")).intValue();
        String sql = ("INSERT INTO PESSOA VALUES (").append(sequencia).append(",'").append(pessoa.getNome()).append("','").append(pessoa.getCpf_cnpj()).append("','").append(FormatarData.dateParaSQLDate(pessoa.getData_cadastro())).append("','").append(FormatarData.dateParaSQLDate(pessoa.getData_alter())).append("')").toString();
        conecta_banco.incluirSQL(sql);
            */
    }
}

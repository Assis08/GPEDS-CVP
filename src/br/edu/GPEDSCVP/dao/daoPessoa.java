/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.GPEDSCVP.dao;

import br.edu.GPEDSCVP.classe.Certificadora;
import br.edu.GPEDSCVP.classe.Fornecedor;
import br.edu.GPEDSCVP.classe.Pessoa;
import br.edu.GPEDSCVP.classe.PessoaFisica;
import br.edu.GPEDSCVP.classe.PessoaJuridica;
import br.edu.GPEDSCVP.conexao.ConexaoBanco;
import br.edu.GPEDSCVP.validacao.FormatarData;
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

    public void incluir(PessoaFisica pessoa)throws SQLException
    {
       
        //Insert de pessoa
        ultima = new UltimaSequencia();
        int sequencia = (Integer) (ultima.ultimasequencia("PESSOA","ID_PESSOA"));
        String sql = "INSERT INTO PESSOA VALUES ("
                + sequencia + ",'"
                + pessoa.getNome()+ "','"
                + pessoa.getCpf_cnpj()+ "','"
                + FormatarData.dateParaSQLDate(pessoa.getData_cadastro())+ "','"
                + FormatarData.dateParaTimeStamp(pessoa.getData_alter())+ "')";
        
                conecta_banco.incluirSQL(sql);
               
               //Insert de pessoa fisica 
               sql = "INSERT INTO PESSOA_FISICA VALUES ("
                + sequencia + ",'"
                + FormatarData.stringParaSQLDate(pessoa.getDt_nasc())+ "','"
                + pessoa.getRg()+ "','"
                + pessoa.getSexo()+ "')"; 
                
               conecta_banco.incluirSQL(sql);

    }
    
    public void incluir(Certificadora pessoa)throws SQLException
    {
        //Insert de pessoa
        ultima = new UltimaSequencia();
        int sequencia = (Integer) (ultima.ultimasequencia("PESSOA","ID_PESSOA"));
        String sql = "INSERT INTO PESSOA VALUES ("
                + sequencia + ",'"
                + pessoa.getNome()+ "','"
                + pessoa.getCpf_cnpj()+ "','"
                + FormatarData.dateParaSQLDate(pessoa.getData_cadastro())+ "','"
                + FormatarData.dateParaTimeStamp(pessoa.getData_alter())+ "')";
        
                conecta_banco.incluirSQL(sql);
               
               //Insert de pessoa juridica 
               sql = "INSERT INTO PESSOA_JURIDICA VALUES ("
                + sequencia + ",'"
                + pessoa.getRazao_social()+ "')"; 
                
               conecta_banco.incluirSQL(sql);
               
               //Insert de certificadora 
               sql = "INSERT INTO certificadora VALUES ("
                + sequencia + ",'"
                + pessoa.getIn_calibracoes()+ "')"; 
                
               conecta_banco.incluirSQL(sql); 
    }
    
     public void incluir(Fornecedor pessoa)throws SQLException
    {
        //Insert de pessoa
        ultima = new UltimaSequencia();
        int sequencia = (Integer) (ultima.ultimasequencia("PESSOA","ID_PESSOA"));
        String sql = "INSERT INTO PESSOA VALUES ("
                + sequencia + ",'"
                + pessoa.getNome()+ "','"
                + pessoa.getCpf_cnpj()+ "','"
                + FormatarData.dateParaSQLDate(pessoa.getData_cadastro())+ "','"
                + FormatarData.dateParaTimeStamp(pessoa.getData_alter())+ "')";
        
                conecta_banco.incluirSQL(sql);
               
               //Insert de pessoa juridica 
               sql = "INSERT INTO PESSOA_JURIDICA VALUES ("
                + sequencia + ",'"
                + pessoa.getRazao_social()+ "')"; 
                
               conecta_banco.incluirSQL(sql);
               
               //Insert de fornecedor 
               sql = "INSERT INTO fornecedor VALUES ("
                + sequencia + ",'"
                + pessoa.getSite()+"','"
                + pessoa.getRamo()+ "')"; 
                
               conecta_banco.incluirSQL(sql);
    }
}

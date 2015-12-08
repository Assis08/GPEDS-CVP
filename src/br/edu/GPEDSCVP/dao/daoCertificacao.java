/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.GPEDSCVP.dao;

import br.edu.GPEDSCVP.classe.Certificacao;
import br.edu.GPEDSCVP.conexao.ConexaoBanco;
import br.edu.GPEDSCVP.util.ExcessaoBanco;
import br.edu.GPEDSCVP.util.FormatarData;
import br.edu.GPEDSCVP.util.UltimaSequencia;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author rafa
 */
public class daoCertificacao {
    
    ConexaoBanco conecta_banco;
    UltimaSequencia ultima;
    
    public daoCertificacao()
    {
        try
        {
            conecta_banco = new ConexaoBanco();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Falha na fonte de dados");
        }
    }
    
     //Método de incluir certificacao no banco
    public boolean incluir(Certificacao certificacao)throws SQLException
    {
        //Insert de certificacao
        ultima = new UltimaSequencia();
        int resultado;
        
        int sequencia = (Integer) (ultima.ultimasequencia("CERTIFICACAO","ID_CERTIFICACAO"));
       // Chamada do métoto genérico executaSQL(), basta passar os parâmetros na ordem correta
        resultado = conecta_banco.executeSQL("INSERT INTO certificacao (id_certificacao, id_projeto, cod_vers_projeto,id_pessoa,id_norma,descricao,"
                + "resultado,descricao_reprovacao,data_ensaio,data_cadastro,valor,data_alter,in_ativo) "
        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ",
        sequencia,
        certificacao.getId_projeto(),
        certificacao.getCod_versao_projeto(),
        certificacao.getId_certificadora(),
        certificacao.getId_norma(),
        certificacao.getDescricao(),
        certificacao.getResultado(),
        certificacao.getDesc_reprov(),
        certificacao.getData_ensaio(),
        certificacao.getData_cadastro(),
        certificacao.getValor(),
        FormatarData.dateParaTimeStamp(certificacao.getData_alter()),
        "A");

        if(resultado == ExcessaoBanco.ERRO_LIMITE_CARACTERES){
           return false;
        }else if(resultado == ExcessaoBanco.OUTROS_ERROS){
            return false;
        }else if (resultado == ExcessaoBanco.ERRO_LIMITE_ARQUIVO){
            return false;
        }

        return true; 
    }
    
    public boolean alterar(Certificacao certificacao)throws SQLException
    {
        int resultado;

        resultado = conecta_banco.executeSQL("UPDATE certificacao SET descricao = ?, id_pessoa = ?, data_ensaio = ?, id_projeto = ?, cod_vers_projeto = ?, id_norma = ?,"
        +"resultado = ?, valor = ?, data_cadastro = ?, data_alter = ?, descricao_reprovacao = ? "
        + "WHERE id_certificacao = ? ",
        certificacao.getDescricao(),
        certificacao.getId_certificadora(),
        certificacao.getData_ensaio(),
        certificacao.getId_projeto(),
        certificacao.getCod_versao_projeto(),
        certificacao.getId_norma(),
        certificacao.getResultado(),
        certificacao.getValor(),
        certificacao.getData_cadastro(),
        FormatarData.dateParaTimeStamp(certificacao.getData_alter()),
        certificacao.getDesc_reprov(),
        certificacao.getId_certificacao());

        if(resultado == ExcessaoBanco.ERRO_LIMITE_CARACTERES){
            return false;
        }else if(resultado == ExcessaoBanco.OUTROS_ERROS){
            return false;
        }else if (resultado == ExcessaoBanco.ERRO_LIMITE_ARQUIVO){
            return false;
        }
        return true;  
    }
    
    public void consultaGeral(Certificacao certificacao){
        
        conecta_banco.executeSQL("select * from certificacao" 
        +" inner join versao_projeto on (versao_projeto.cod_vers_projeto = certificacao.cod_vers_projeto)" 
        +" inner join projeto on (versao_projeto.id_projeto = projeto.id_projeto)" 
        +" inner join norma on (norma.id_norma = certificacao.id_norma)" 
        +" inner join certificadora on (certificadora.id_pessoa = certificacao.id_pessoa)" 
        +" inner join pessoa_juridica on (pessoa_juridica.id_pessoa = certificadora.id_pessoa)" 
        +" inner join pessoa on (pessoa.id_pessoa = pessoa_juridica.id_pessoa)"
        +" where certificacao.in_Ativo = 'A'");

        certificacao.setRetorno(conecta_banco.resultset);
      
    }
    
    public void consultaGeralCodigo(Certificacao certificacao){
        
        conecta_banco.executeSQL("select * from certificacao" 
        +" inner join versao_projeto on (versao_projeto.cod_vers_projeto = certificacao.cod_vers_projeto)" 
        +" inner join projeto on (versao_projeto.id_projeto = projeto.id_projeto)" 
        +" inner join norma on (norma.id_norma = certificacao.id_norma)" 
        +" inner join certificadora on (certificadora.id_pessoa = certificacao.id_pessoa)" 
        +" inner join pessoa_juridica on (pessoa_juridica.id_pessoa = certificadora.id_pessoa)" 
        +" inner join pessoa on (pessoa.id_pessoa = pessoa_juridica.id_pessoa)"
        +" where certificacao.id_certificacao = "+certificacao.getId_certificacao()+" and certificacao.in_Ativo = 'A'");

        certificacao.setRetorno(conecta_banco.resultset);
      
    }
    
    public void consultaGeralDescricao(Certificacao certificacao){
        
        conecta_banco.executeSQL("select * from certificacao" 
        +" inner join versao_projeto on (versao_projeto.cod_vers_projeto = certificacao.cod_vers_projeto)" 
        +" inner join projeto on (versao_projeto.id_projeto = projeto.id_projeto)" 
        +" inner join norma on (norma.id_norma = certificacao.id_norma)" 
        +" inner join certificadora on (certificadora.id_pessoa = certificacao.id_pessoa)" 
        +" inner join pessoa_juridica on (pessoa_juridica.id_pessoa = certificadora.id_pessoa)" 
        +" inner join pessoa on (pessoa.id_pessoa = pessoa_juridica.id_pessoa)"
        +" where certificacao.descricao like '"+certificacao.getDescricao()+"%' and certificacao.in_Ativo = 'A'");

        certificacao.setRetorno(conecta_banco.resultset);
      
    }
    
    public void retornardados(Certificacao certificacao){
        
        conecta_banco.executeSQL("select * from certificacao" 
        +" inner join versao_projeto on (versao_projeto.cod_vers_projeto = certificacao.cod_vers_projeto)" 
        +" inner join projeto on (versao_projeto.id_projeto = projeto.id_projeto)" 
        +" inner join norma on (norma.id_norma = certificacao.id_norma)" 
        +" inner join certificadora on (certificadora.id_pessoa = certificacao.id_pessoa)" 
        +" inner join pessoa_juridica on (pessoa_juridica.id_pessoa = certificadora.id_pessoa)" 
        +" inner join pessoa on (pessoa.id_pessoa = pessoa_juridica.id_pessoa)"
        +" where id_certificacao = "+certificacao.getId_certificacao());
        
        try {        
            conecta_banco.resultset.first();
            certificacao.setId_certificacao(conecta_banco.resultset.getInt("id_certificacao"));
            certificacao.setDescricao(conecta_banco.resultset.getString("certificacao.descricao"));
            certificacao.setId_certificadora(conecta_banco.resultset.getInt("certificacao.id_pessoa"));
            certificacao.setDs_certificadora(conecta_banco.resultset.getString("nome")); 
            certificacao.setData_ensaio(conecta_banco.resultset.getDate("certificacao.data_ensaio"));
            certificacao.setId_projeto(conecta_banco.resultset.getInt("certificacao.id_projeto"));
            certificacao.setDs_projeto(conecta_banco.resultset.getString("projeto.descricao"));
            certificacao.setCod_versao_projeto(conecta_banco.resultset.getInt("certificacao.cod_vers_projeto"));
            certificacao.setVersao(conecta_banco.resultset.getString("versao_projeto.versao"));
            certificacao.setId_norma(conecta_banco.resultset.getInt("certificacao.id_norma"));
            certificacao.setDs_norma(conecta_banco.resultset.getString("norma.titulo"));
            certificacao.setResultado(conecta_banco.resultset.getString("resultado"));
            certificacao.setValor(conecta_banco.resultset.getDouble("certificacao.valor"));
            certificacao.setData_cadastro(conecta_banco.resultset.getDate("certificacao.data_cadastro"));
            certificacao.setDesc_reprov(conecta_banco.resultset.getString("descricao_reprovacao"));
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao retornar dados da certificacao");
        }
    }
    
    public boolean inativaCertificacao(Certificacao certificacao){
            
        int result;
        
        String sql = "UPDATE CERTIFICACAO SET IN_ATIVO = 'I' WHERE ID_CERTIFICACAO = "+ certificacao.getId_certificacao();
        
        result = conecta_banco.atualizarSQL(sql);
        
        if(result == ExcessaoBanco.ERRO_CHAVE_ESTRANGEIRA){
            return false;
        }else if (result == ExcessaoBanco.ERRO_LIMITE_CARACTERES){
            return false;
        }else if(result == ExcessaoBanco.OUTROS_ERROS) {
            return false;
        }      
        return true;
    }  
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.GPEDSCVP.dao;

import br.edu.GPEDSCVP.classe.Projeto;
import br.edu.GPEDSCVP.conexao.ConexaoBanco;
import br.edu.GPEDSCVP.util.ExcessaoBanco;
import br.edu.GPEDSCVP.util.FormatarData;
import br.edu.GPEDSCVP.util.UltimaSequencia;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Willys
 */
public class daoProjeto {
    
    ConexaoBanco conecta_banco;
    UltimaSequencia ultima;
    
    public daoProjeto()
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
    
    public void consultaGeral(Projeto projeto){
        
        conecta_banco.executeSQL("select * from projeto where in_ativo = 'A'");

        projeto.setRetorno(conecta_banco.resultset);
    }
    
    //Método de incluir projeto no banco
    public boolean incluir(Projeto projeto)throws SQLException
    {
        //Insert de projeto
        ultima = new UltimaSequencia();
        int resultado;
        
            int sequencia = (Integer) (ultima.ultimasequencia("PROJETO","ID_PROJETO"));
            String sql = "INSERT INTO PROJETO VALUES ("
                + sequencia + ",'"
                + projeto.getDescricao()+ "','"
                + FormatarData.dateParaTimeStamp(projeto.getData_cadastro())+ "','"
                + FormatarData.dateParaTimeStamp(projeto.getData_alter())+"','A')";
        
                resultado = conecta_banco.incluirSQL(sql);

               if(resultado == ExcessaoBanco.ERRO_LIMITE_CARACTERES){
                   return false;
               }else if(resultado == ExcessaoBanco.OUTROS_ERROS){
                   return false;
               }

            return true;    
    }
    
    public boolean alterar(Projeto projeto)throws SQLException
    {
        int result;
        String sql = "UPDATE PROJETO SET ID_PROJETO = "+ projeto.getId_projeto()+","
            + "DESCRICAO = '" + projeto.getDescricao()+"',"
            + "DATA_ALTER = '" + FormatarData.dateParaTimeStamp(projeto.getData_alter())+"'"
            + " WHERE ID_PROJETO = " + projeto.getId_projeto();

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
    
    public void retornardados(Projeto projeto){
        String sql = "select * from projeto where id_projeto = "+ projeto.getId_projeto();
           
        conecta_banco.executeSQL(sql);
        try {        
            conecta_banco.resultset.first();
            projeto.setId_projeto(conecta_banco.resultset.getInt("id_projeto"));
            projeto.setDescricao(conecta_banco.resultset.getString("descricao"));
            projeto.setData_cadastro(conecta_banco.resultset.getDate("data_cadastro"));
            projeto.setData_alter(conecta_banco.resultset.getDate("data_alter"));
           
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao retornar dados de projeto");
        }
       
    }
    
    public boolean inativaProjeto(Projeto projeto){
            
        int result;
        
        String sql = "UPDATE PROJETO SET IN_ATIVO = 'I' WHERE ID_PROJETO = "+ projeto.getId_projeto();
        
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

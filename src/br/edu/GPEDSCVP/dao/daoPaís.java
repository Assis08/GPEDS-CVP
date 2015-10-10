/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.GPEDSCVP.dao;

import br.edu.GPEDSCVP.classe.País;
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
public class daoPaís {
    
    ConexaoBanco conecta_banco;
    UltimaSequencia ultima;
    
    public daoPaís()
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
    
    public void consultaGeral(País pais){
        
        conecta_banco.executeSQL("select * from pais");

        pais.setRetorno(conecta_banco.resultset);
      
    }
    
    public void retornardados(País pais) {
            
        conecta_banco.executeSQL("select * from pais where id_pais = "+pais.getId_pais());
       
        try {        
            conecta_banco.resultset.first();
            
            pais.setId_pais(conecta_banco.resultset.getInt("id_pais"));
            pais.setSigla(conecta_banco.resultset.getString("sigla"));
            pais.setDescricao(conecta_banco.resultset.getString("descricao"));

        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, "Falha ao retornar dados de país");
        }
    } 
    
    //Método de incluir país no banco
    public boolean incluir(País pais)throws SQLException
    {
        //Insert de país
        ultima = new UltimaSequencia();
        int resultado;
        
        int sequencia = (Integer) (ultima.ultimasequencia("PAIS","ID_PAIS"));
        String sql = "INSERT INTO PAIS VALUES ("
                + sequencia + ",'"
                + pais.getSigla()+ "','"
                + pais.getDescricao()+ "','"
                + FormatarData.dateParaTimeStamp(pais.getData_alter())+"')";
        
                resultado = conecta_banco.incluirSQL(sql);

               if(resultado == ExcessaoBanco.ERRO_LIMITE_CARACTERES){
                   return false;
               }else if(resultado == ExcessaoBanco.OUTROS_ERROS){
                   return false;
               }

            return true;    
    }
    
    //Método de excluir país no banco
    public boolean excluir(País pais) 
    {
        int result;
        
        try {
            //exclui país
            String sql = "DELETE FROM PAIS WHERE ID_PAIS = "+pais.getId_pais();
            
            result = conecta_banco.atualizarSQL(sql);
            
            if(result == ExcessaoBanco.ERRO_CHAVE_ESTRANGEIRA){
                return false;
            } else if (result == ExcessaoBanco.OUTROS_ERROS){
                return false;
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Falha ao tentar excluir país");
        }

        return true;
    }
    
    public boolean alterar(País pais)throws SQLException
    {
        int result;
        String sql = "UPDATE PAIS SET ID_PAIS = "+ pais.getId_pais()+","
                + "SIGLA = '" + pais.getSigla()+"',"
                + "DESCRICAO = '" + pais.getDescricao()+"',"
                + "DATA_ALTER = '" + FormatarData.dateParaTimeStamp(pais.getData_alter())+"'"
                + " WHERE ID_PAIS = " + pais.getId_pais();
     
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

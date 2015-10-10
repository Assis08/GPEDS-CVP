/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.GPEDSCVP.dao;

import br.edu.GPEDSCVP.classe.Cidade;
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
            JOptionPane.showMessageDialog(null, "Falha na fonte de dados");
        }
    }
    
    public void consultaGeral(Cidade cidade){
        
        conecta_banco.executeSQL("select * from cidade");

        cidade.setRetorno(conecta_banco.resultset);
      
    }
    
    public void consultageral(Cidade cidade){
        String sql = "select * from cidade";
        conecta_banco.executeSQL(sql);
        cidade.setRetorno(conecta_banco.resultset);
    }
    
     public void retornardados(Cidade cidade){
        String sql = "select * from cidade where id_cidade = "+ cidade.getId_cidade();
           
        conecta_banco.executeSQL(sql);
        try {        
            conecta_banco.resultset.first();
            cidade.setId_cidade(conecta_banco.resultset.getInt("id_cidade"));
            cidade.setUf(conecta_banco.resultset.getString("uf"));
            cidade.setId_uf(conecta_banco.resultset.getInt("id_uf"));
            cidade.setDescricao(conecta_banco.resultset.getString("descricao"));
            cidade.setData_alter(conecta_banco.resultset.getDate("data_alter"));
           
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao retornar dados de cidade");
        }
       
    }
     
    //Método de incluir cidade no banco
    public boolean incluir(Cidade cidade)throws SQLException
    {
        //Insert de cidade
        ultima = new UltimaSequencia();
        int resultado;
        
        int sequencia = (Integer) (ultima.ultimasequencia("CIDADE","ID_CIDADE"));
        String sql = "INSERT INTO CIDADE VALUES ("
                + sequencia + ","
                + cidade.getId_uf()+ ",'"
                + cidade.getUf()+ "','"
                + cidade.getDescricao()+ "','"
                +FormatarData.dateParaTimeStamp(cidade.getData_alter())+"')";
        
                resultado = conecta_banco.incluirSQL(sql);

               if(resultado == ExcessaoBanco.ERRO_LIMITE_CARACTERES){
                   return false;
               }else if(resultado == ExcessaoBanco.OUTROS_ERROS){
                   return false;
               }

            return true;    
    }
    
    public boolean alterar(Cidade cidade)throws SQLException
    {
     int result;
     String sql = "UPDATE CIDADE SET ID_CIDADE= "+ cidade.getId_cidade()+","
                + "ID_UF = " + cidade.getId_uf()+","
                + "UF = '" + cidade.getUf()+"',"
                + "DESCRICAO = '" + cidade.getDescricao()+"',"
                + "DATA_ALTER = '" + FormatarData.dateParaTimeStamp(cidade.getData_alter())+"'"
                + " WHERE ID_CIDADE = " + cidade.getId_cidade();
     
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
    
    //Método de excluir moeda no banco
    public boolean excluir(Cidade cidade) 
    {
        int result;
        
        try {
            //exclui todas atualizações de valores da moeda
            String sql = "DELETE FROM CIDADE WHERE ID_CIDADE = "+cidade.getId_cidade();
            result = conecta_banco.atualizarSQL(sql);
          
            if(result == ExcessaoBanco.ERRO_CHAVE_ESTRANGEIRA){
                return false;
            } else if (result == ExcessaoBanco.OUTROS_ERROS){
                return false;
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Falha ao tentar excluir moeda");
        }
        return true;
    }
    
}

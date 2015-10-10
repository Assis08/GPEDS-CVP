/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.GPEDSCVP.dao;

import br.edu.GPEDSCVP.classe.Estado;
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
public class daoEstado {
    
    ConexaoBanco conecta_banco;
    UltimaSequencia ultima;
    
    public daoEstado()
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
    
    public void consultageral(Estado estado){
        String sql = "select * from estado order by id_uf asc";
        conecta_banco.executeSQL(sql);
        estado.setRetorno(conecta_banco.resultset);
    }
    
     //Método de incluir estado no banco
    public boolean incluir(Estado estado)throws SQLException
    {
        //Insert de cidade
        ultima = new UltimaSequencia();
        int resultado;
        
        int sequencia = (Integer) (ultima.ultimasequencia("ESTADO","ID_UF"));
        String sql = "INSERT INTO ESTADO VALUES ("
                + sequencia + ",'"
                + estado.getUf()+ "',"
                + estado.getId_pais()+ ",'"
                + estado.getSigla_pais()+ "','"
                + estado.getDescricao()+ "','"
                +FormatarData.dateParaTimeStamp(estado.getData_alter())+"')";
        
                resultado = conecta_banco.incluirSQL(sql);

               if(resultado == ExcessaoBanco.ERRO_LIMITE_CARACTERES){
                   return false;
               }else if(resultado == ExcessaoBanco.OUTROS_ERROS){
                   return false;
               }

            return true;    
    }
    
    public boolean alterar(Estado estado)throws SQLException
    {
        int result;
        String sql = "UPDATE ESTADO SET ID_UF = "+ estado.getId_uf()+","
                + "UF = '" + estado.getUf()+"',"
                + "ID_PAIS = " + estado.getId_pais()+","
                + "SIGLA = '" + estado.getSigla_pais()+"',"
                + "DESCRICAO = '" + estado.getDescricao()+"',"
                + "DATA_ALTER = '" + FormatarData.dateParaTimeStamp(estado.getData_alter())+"'"
                + " WHERE ID_UF = " + estado.getId_uf();
     
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
    
    //Método de excluir estados no banco
    public boolean excluir(Estado estado) 
    {
        int result;
        
        try {
            //exclui estado
            String sql = "DELETE FROM ESTADO WHERE ID_UF = "+estado.getId_uf();
            result = conecta_banco.atualizarSQL(sql);
          
            if(result == ExcessaoBanco.ERRO_CHAVE_ESTRANGEIRA){
                return false;
            } else if (result == ExcessaoBanco.OUTROS_ERROS){
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Falha ao tentar excluir estado");
        }
        return true;
    }
    
    public void retornardados(Estado estado) {
            
        conecta_banco.executeSQL("select * from estado where id_uf = "+estado.getId_uf());
       
        try {        
            conecta_banco.resultset.first();
            
            estado.setId_uf(conecta_banco.resultset.getInt("id_uf"));
            estado.setUf(conecta_banco.resultset.getString("uf"));
            estado.setId_pais(conecta_banco.resultset.getInt("id_pais"));
            estado.setSigla_pais(conecta_banco.resultset.getString("sigla"));
            estado.setDescricao(conecta_banco.resultset.getString("descricao"));

        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, "Falha ao retornar dados do estado");
        }
    } 
    
}

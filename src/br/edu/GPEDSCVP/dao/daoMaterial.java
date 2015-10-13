/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.GPEDSCVP.dao;

import br.edu.GPEDSCVP.classe.Material;
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
public class daoMaterial {
    
    ConexaoBanco conecta_banco;
    UltimaSequencia ultima;
    
    public daoMaterial()
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
    
    public void consultaGeral(Material material){
        
        conecta_banco.executeSQL("select * from material");

        material.setRetorno(conecta_banco.resultset);
    }
    
    //Método de incluir material no banco
    public boolean incluir(Material material)throws SQLException
    {
        //Insert de material
        ultima = new UltimaSequencia();
        int resultado;
        
        int sequencia = (Integer) (ultima.ultimasequencia("MATERIAL","ID_MATERIAL"));
        String sql = "INSERT INTO MATERIAL VALUES ("
                + sequencia + ",'"
                + material.getDescricao()+ "','"
                +FormatarData.dateParaTimeStamp(material.getData_alter())+"')";

                resultado = conecta_banco.incluirSQL(sql);

                if(resultado == ExcessaoBanco.ERRO_LIMITE_CARACTERES){
                    return false;
                }else if(resultado == ExcessaoBanco.OUTROS_ERROS){
                    return false;
                }

                return true;    
    }
    
    public boolean alterar(Material material)throws SQLException
    {
        int result;
        String sql = "UPDATE MATERIAL SET ID_MATERIAL= "+ material.getId_material()+","
                + "DESCRICAO = '" + material.getDescricao()+"',"
                + "DATA_ALTER = '" + FormatarData.dateParaTimeStamp(material.getData_alter())+"'"
                + " WHERE ID_MATERIAL = " + material.getId_material();
     
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
    
    public void retornardados(Material material){
        
        String sql = "select * from material where id_material = "+ material.getId_material();
           
        conecta_banco.executeSQL(sql);
        try {        
            conecta_banco.resultset.first();
            material.setId_material(conecta_banco.resultset.getInt("id_material"));
            material.setDescricao(conecta_banco.resultset.getString("descricao"));
            material.setData_alter(conecta_banco.resultset.getDate("data_alter"));
           
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao retornar dados do material");
        } 
    }
    
    //Método de excluir material no banco
    public boolean excluir(Material material) 
    {
        int result;
        
        try {
            //exclui material
            String sql = "DELETE FROM MATERIAL WHERE ID_MATERIAL = "+material.getId_material();
            result = conecta_banco.atualizarSQL(sql);
          
            if(result == ExcessaoBanco.ERRO_CHAVE_ESTRANGEIRA){
                return false;
            } else if (result == ExcessaoBanco.OUTROS_ERROS){
                return false;
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Falha ao tentar excluir material");
        }
        return true;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.GPEDSCVP.dao;

import br.edu.GPEDSCVP.classe.Moeda;
import br.edu.GPEDSCVP.conexao.ConexaoBanco;
import br.edu.GPEDSCVP.util.FormatarData;
import br.edu.GPEDSCVP.util.UltimaSequencia;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import javax.swing.JOptionPane;

/**
 *
 * @author Willys
 */
public class daoMoeda {
    
    ConexaoBanco conecta_banco;
    UltimaSequencia ultima;
    
    public daoMoeda()
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
    
    
    //Método de incluir contato no banco
    public void incluir(Moeda moeda)throws SQLException
    {
        //Insert de moeda
        ultima = new UltimaSequencia();
        int sequencia = (Integer) (ultima.ultimasequencia("MOEDA","ID_MOEDA"));
        String sql = "INSERT INTO MOEDA VALUES ("
                + sequencia + ",'"
                + moeda.getDecricao()+ "','"
                + moeda.getUnidade()+ "','"
                +FormatarData.dateParaTimeStamp(moeda.getData_alter())+"')";

                conecta_banco.incluirSQL(sql);
    }
    
     //Método de incluir contato no banco
    public void excluir(Moeda moeda)throws SQLIntegrityConstraintViolationException
    {
        //excluir de moeda
        String sql = "DELETE FROM MOEDA WHERE ID_MOEDA = "+moeda.getId_moeda();

        try {
            conecta_banco.atualizarSQL(sql);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Falha ao excluir moeda");
        }  
    }
    
    public void alterar(Moeda moeda)throws SQLException
    {
     String sql = "UPDATE MOEDA SET ID_MOEDA = "+ moeda.getId_moeda()+","
                + "DESCRICAO = '" + moeda.getDecricao()+"',"
                + "UNIDADE = '" + moeda.getUnidade()+"',"
                + "DATA_ALTER = '" + FormatarData.dateParaTimeStamp(moeda.getData_alter())+"'"
                + " WHERE ID_MOEDA = " + moeda.getId_moeda();
     
                conecta_banco.atualizarSQL(sql);
    }
    
    public void consultaGeral(Moeda moeda){
        
        conecta_banco.executeSQL("select * from moeda");

        moeda.setRetorno(conecta_banco.resultset);
      
    }
     
    public void retornardadosMoeda(Moeda moeda) {
            
        conecta_banco.executeSQL("select * from moeda where id_moeda = "+moeda.getId_moeda());
       
        try {        
            conecta_banco.resultset.first();
            
            moeda.setId_moeda(conecta_banco.resultset.getInt("id_moeda"));
            moeda.setDecricao(conecta_banco.resultset.getString("descricao"));
            moeda.setUnidade(conecta_banco.resultset.getString("unidade"));
          
            
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, "Falha ao retornar dados da moeda");
        }
     } 
    
}

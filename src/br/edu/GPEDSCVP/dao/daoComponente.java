/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.GPEDSCVP.dao;

import br.edu.GPEDSCVP.classe.Componente;
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
public class daoComponente {
    
    ConexaoBanco conecta_banco;
    UltimaSequencia ultima;
    
    public daoComponente()
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
    
    //Método de incluir componente no banco
    public boolean incluir(Componente componente)throws SQLException
    {
        //Insert de componente
        ultima = new UltimaSequencia();
        int resultado;
        
        int sequencia = (Integer) (ultima.ultimasequencia("COMPONENTE","ID_COMPONENTE"));
        String sql = "INSERT INTO COMPONENTE VALUES ("
                + sequencia + ","
                + componente.getId_material()+ ",'"
                + componente.getDescricao()+ "','"
                + componente.getTipo()+ "','"
                + componente.getRevisao()+ "','"
                + componente.getDatasheet()+ "','"
                + componente.getPath_datasheet()+ "','"
                + FormatarData.dateParaTimeStamp(componente.getData_cadastro())+ "','"
                + FormatarData.dateParaTimeStamp(componente.getData_alter())+"')";
             
                resultado = conecta_banco.incluirSQL(sql);

               if(resultado == ExcessaoBanco.ERRO_LIMITE_CARACTERES){
                   return false;
               }else if(resultado == ExcessaoBanco.OUTROS_ERROS){
                   return false;
               }

            return true;    
    }
    
    //Consulta geral de componentes
    public void consultageral(Componente componente){
        conecta_banco.executeSQL("select id_componente,tipo,componente.descricao,revisao,material.id_material,material.descricao,datasheet,"
                               + " data_cadastro,componente.data_alter from componente"
                               + " inner join material on (componente.id_material = material.id_material)"
                               + " order by id_componente asc");

        componente.setRetorno(conecta_banco.resultset);

    }
    
    public String retornaCaminhoArquivo(Componente componente){
        
        String sql = "select * from componente where id_componente = "+ componente.getId_componente();
           
        conecta_banco.executeSQL(sql);
        try {        
            conecta_banco.resultset.first();
            return conecta_banco.resultset.getString("path_datasheet");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao retornar caminho do arquivo");
        }
        return null;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.GPEDSCVP.dao;

import br.edu.GPEDSCVP.classe.Datasheet;
import br.edu.GPEDSCVP.conexao.ConexaoBanco;
import br.edu.GPEDSCVP.util.ExcessaoBanco;
import br.edu.GPEDSCVP.util.FormatarData;
import br.edu.GPEDSCVP.util.UltimaSequencia;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Blob;
import java.sql.SQLException;
import javax.sql.rowset.serial.SerialBlob;
import javax.swing.JOptionPane;

/**
 *
 * @author Willys
 */
public class daoDatasheet {
    
    ConexaoBanco conecta_banco;
    UltimaSequencia ultima;
    
    public daoDatasheet()
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
   /* 
    //Método de incluir datasheet no banco
    public boolean incluir(Datasheet datasheet)throws SQLException
    {
        //Insert de datasheet
        ultima = new UltimaSequencia();
        int resultado;
        
        int sequencia = (Integer) (ultima.ultimasequencia("DATASHEET","ID_DATASHEET"));
        String sql = "INSERT INTO DATASHEET VALUES ("
                + sequencia + ",'"
                + datasheet.getDescricao()+ "','"
                + datasheet.getArquivo()+ "','"
                + FormatarData.dateParaTimeStamp(datasheet.getData_cadastro())+"')";
        
                resultado = conecta_banco.incluirSQL(sql);

               if(resultado == ExcessaoBanco.ERRO_LIMITE_CARACTERES){
                   return false;
               }else if(resultado == ExcessaoBanco.OUTROS_ERROS){
                   return false;
               }

            return true;    
    }
    */
    
    public boolean incluir(Datasheet datasheet) throws SQLException {
        ultima = new UltimaSequencia();
        int resultado;
        
        int sequencia = (Integer) (ultima.ultimasequencia("DATASHEET","ID_DATASHEET"));
        
        // Chamada do métoto genérico executaSQL(), basta passar os parâmetros na ordem correta
        conecta_banco.executeSQL("INSERT INTO datasheet (id_datasheet, descricao, arquivo, data_cadastro) "
                + "VALUES (?, ?, ?, ?) ",
                sequencia,
                datasheet.getDescricao(),
                datasheet.getArquivo(),
                FormatarData.dateParaTimeStamp(datasheet.getData_cadastro()));
        
        return true;

    }
    
    public void consultaGeral(Datasheet datasheet){
        
        conecta_banco.executeSQL("select * from datasheet");

        datasheet.setRetorno(conecta_banco.resultset);
    }
    
    public void consultaCodigo(Datasheet datasheet){
        
        conecta_banco.executeSQL("select * from datasheet where id_datasheet = " + datasheet.getId_datasheet());

        datasheet.setRetorno(conecta_banco.resultset);
    }
    
    public void consultaDescricao(Datasheet datasheet){
        
        conecta_banco.executeSQL("select * from datasheet where descricao like '" + datasheet.getDescricao()+"%'");

        datasheet.setRetorno(conecta_banco.resultset);
    }
    
    public byte[] retornaArquivo(Datasheet datasheet){
        
        String sql = "select * from datasheet where id_datasheet = "+ datasheet.getId_datasheet();
           
        conecta_banco.executeSQL(sql);
        try {        
            conecta_banco.resultset.first();
            return conecta_banco.resultset.getBytes("arquivo");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao retornar caminho do arquivo");
        }
        return null;
    }
}

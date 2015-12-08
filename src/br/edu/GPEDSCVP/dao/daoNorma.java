/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.GPEDSCVP.dao;

import br.edu.GPEDSCVP.classe.Norma;
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
public class daoNorma {
    
    ConexaoBanco conecta_banco;
    UltimaSequencia ultima;
    
    public daoNorma()
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
    
     //Método de incluir norma no banco
    public boolean incluir(Norma norma)throws SQLException
    {
        //Insert de norma
        ultima = new UltimaSequencia();
        int resultado;
        
        int sequencia = (Integer) (ultima.ultimasequencia("NORMA","ID_NORMA"));
       // Chamada do métoto genérico executaSQL(), basta passar os parâmetros na ordem correta
        resultado = conecta_banco.executeSQL("INSERT INTO norma (id_norma, descricao, titulo,edicao,data_cadastro,data_alter) "
        + "VALUES (?, ?, ?, ?, ?, ?) ",
        sequencia,
        norma.getDescricao(),
        norma.getTitulo(),
        norma.getEdicao(),
        norma.getData_cadastro(),
        FormatarData.dateParaTimeStamp(norma.getData_alter()));

        if(resultado == ExcessaoBanco.ERRO_LIMITE_CARACTERES){
           return false;
        }else if(resultado == ExcessaoBanco.OUTROS_ERROS){
            return false;
        }else if (resultado == ExcessaoBanco.ERRO_LIMITE_ARQUIVO){
            return false;
        }

        return true; 
    }
    
    //Método de alterar norma no banco
    public boolean alterar(Norma norma)throws SQLException
    {
           int resultado;

           resultado = conecta_banco.executeSQL("UPDATE norma SET descricao = ?, titulo = ?, edicao = ?, data_alter = ? "
           + "WHERE id_norma = ? ",
           norma.getDescricao(),
           norma.getTitulo(),
           norma.getEdicao(),
           FormatarData.dateParaTimeStamp(norma.getData_alter()),
           norma.getId_norma());

           if(resultado == ExcessaoBanco.ERRO_LIMITE_CARACTERES){
               return false;
           }else if(resultado == ExcessaoBanco.OUTROS_ERROS){
               return false;
           }else if (resultado == ExcessaoBanco.ERRO_LIMITE_ARQUIVO){
               return false;
           }
           return true;  
    }
    
    public boolean excluir(Norma norma){
            
        int result;
        
        String sql = "DELETE FROM NORMA WHERE ID_NORMA = "+ norma.getId_norma();
        
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
    
    public void consultaGeral(Norma norma){
        
        conecta_banco.executeSQL("select * from norma");

        norma.setRetorno(conecta_banco.resultset);
    }
    
    public void retornardados(Norma norma){
        String sql = "select * from norma where id_norma = "+ norma.getId_norma();
           
        conecta_banco.executeSQL(sql);
        try {        
            conecta_banco.resultset.first();
            norma.setId_norma(conecta_banco.resultset.getInt("id_norma"));
            norma.setDescricao(conecta_banco.resultset.getString("descricao"));
            norma.setTitulo(conecta_banco.resultset.getString("titulo"));
            norma.setEdicao(conecta_banco.resultset.getString("edicao"));
            norma.setData_cadastro(conecta_banco.resultset.getDate("data_cadastro"));
            norma.setData_alter(conecta_banco.resultset.getDate("data_alter"));
           
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao retornar dados da norma");
        }
       
    }
}

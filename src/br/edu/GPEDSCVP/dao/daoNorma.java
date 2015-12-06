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
    
     //Método de incluir material no banco
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
        FormatarData.dateParaTimeStamp(norma.getData_cadastro()));

        if(resultado == ExcessaoBanco.ERRO_LIMITE_CARACTERES){
           return false;
        }else if(resultado == ExcessaoBanco.OUTROS_ERROS){
            return false;
        }else if (resultado == ExcessaoBanco.ERRO_LIMITE_ARQUIVO){
            return false;
        }

        return true; 
    }
}

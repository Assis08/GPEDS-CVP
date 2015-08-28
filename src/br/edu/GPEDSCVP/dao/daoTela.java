/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.GPEDSCVP.dao;

import br.edu.GPEDSCVP.classe.Tela;
import br.edu.GPEDSCVP.conexao.ConexaoBanco;
import br.edu.GPEDSCVP.validacao.UltimaSequencia;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Willys
 */
public class daoTela {
    
    ConexaoBanco conecta_banco;
    UltimaSequencia ultima;
    
    public daoTela()
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
    
     //Método de incluir tela no banco
    public void incluir(Tela tela)throws SQLException
    {
        //Insert de Tela
        ultima = new UltimaSequencia();
        int sequencia = (Integer) (ultima.ultimasequencia("TELA","ID_TELA"));
        String sql = "INSERT INTO TELA VALUES ("
                + tela.getId_tela() + ",'"
                + tela.getDescricao()+"') ON DUPLICATE KEY UPDATE DESCRICAO = ('"+tela.getDescricao()+"')";

                conecta_banco.incluirSQL(sql);
    }
    
    public void consultageral(Tela tela){
        String sql = "select * from tela";
        conecta_banco.executeSQL(sql);
        tela.setRetorno(conecta_banco.resultset);
    }
    
     public void retornardados(Tela tela){
        String sql = "select * from tela where id_tela = "+ tela.getId_tela();
           
        conecta_banco.executeSQL(sql);
        try {        
            conecta_banco.resultset.first();
            tela.setId_tela(conecta_banco.resultset.getInt("id_tela"));
            tela.setDescricao(conecta_banco.resultset.getString("descricao"));
           
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao retornar dados de telas");
        }
       
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.GPEDSCVP.dao;

import br.edu.GPEDSCVP.classe.Cidade;
import br.edu.GPEDSCVP.conexao.ConexaoBanco;
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
            cidade.setDescricao(conecta_banco.resultset.getString("descricao"));
            cidade.setData_alter(conecta_banco.resultset.getDate("data_alter"));
           
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao retornar dados de cidade");
        }
       
    }
    
}

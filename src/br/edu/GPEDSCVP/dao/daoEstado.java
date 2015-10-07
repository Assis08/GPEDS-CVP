/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.GPEDSCVP.dao;

import br.edu.GPEDSCVP.classe.Cidade;
import br.edu.GPEDSCVP.classe.Estado;
import br.edu.GPEDSCVP.conexao.ConexaoBanco;
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
    
}

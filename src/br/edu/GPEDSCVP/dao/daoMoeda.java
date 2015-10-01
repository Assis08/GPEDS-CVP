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
        //Insert de contato
        ultima = new UltimaSequencia();
        int sequencia = (Integer) (ultima.ultimasequencia("MOEDA","ID_MOEDA"));
        String sql = "INSERT INTO MOEDA VALUES ("
                + sequencia + ",'"
                + moeda.getDecricao()+ "','"
                + moeda.getUnidade()+ "','"
                +FormatarData.dateParaTimeStamp(moeda.getData_alter())+"')";

                conecta_banco.incluirSQL(sql);
    }
    
}

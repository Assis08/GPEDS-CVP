/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.GPEDSCVP.validacao;

import br.edu.GPEDSCVP.conexao.ConexaoBanco;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Willys
 */
public class UltimaSequencia {
    
    public UltimaSequencia()
        throws SQLException
    {
        conexao_banco = new ConexaoBanco();
    }

    public int ultimasequencia(String tabela, String atributo)
    {
        String sql = (new StringBuilder()).append("SELECT COALESCE (MAX(").append(atributo).append("),0) + 1 AS ULTIMO FROM ").append(tabela).toString();
        conexao_banco.executeSQL(sql);
        try
        {
            ConexaoBanco _tmp = conexao_banco;
            ConexaoBanco.resultset.first();
            ConexaoBanco _tmp1 = conexao_banco;
            return ConexaoBanco.resultset.getInt("ULTIMO");
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Resultado nao encontrado");
        }
        return 0;
    }

    ConexaoBanco conexao_banco;

    
}

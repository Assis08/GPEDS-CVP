/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.GPEDSCVP.dao;

import br.edu.GPEDSCVP.classe.VersaoProjeto;
import br.edu.GPEDSCVP.conexao.ConexaoBanco;
import br.edu.GPEDSCVP.util.UltimaSequencia;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Willys
 */
public class daoVersaoProjeto {
    
    ConexaoBanco conecta_banco;
    UltimaSequencia ultima;
    
    public daoVersaoProjeto()
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
    
    public void consultaCodigo(VersaoProjeto projeto){
        
        conecta_banco.executeSQL("select * from versao_projeto where oculto = "+0+" and id_projeto = "+projeto.getId_projeto());

        projeto.setRetorno(conecta_banco.resultset);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.GPEDSCVP.dao;

import br.edu.GPEDSCVP.classe.Projeto;
import br.edu.GPEDSCVP.conexao.ConexaoBanco;
import br.edu.GPEDSCVP.util.UltimaSequencia;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author rafa
 */
public class daoCustos {
    
    ConexaoBanco conecta_banco;
    UltimaSequencia ultima;
    
    public daoCustos()
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
    
    public void consultaTodosCompFornecProjeto(Projeto projeto, String tipo){

        conecta_banco.executeSQL("select * from fornecimento" 
        +" inner join componentes_fornecimento on (componentes_fornecimento.id_fornecimento = fornecimento.id_fornecimento)" 
        +" inner join componente on (componente.id_componente = componentes_fornecimento.id_componente)"
        +" inner join componentes_versao_projeto on (componentes_versao_projeto.id_comp_fornec = componentes_fornecimento.id_comp_fornec)"
        +" where componentes_versao_projeto.id_projeto =" +projeto.getId_projeto()+" and fornecimento.in_ativo = 'A' and componente.tipo = '"+tipo+"'");

        projeto.setRetorno(conecta_banco.resultset);
    }
    
}

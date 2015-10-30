/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.GPEDSCVP.dao;

import br.edu.GPEDSCVP.classe.ComposicaoComponente;
import br.edu.GPEDSCVP.conexao.ConexaoBanco;
import br.edu.GPEDSCVP.util.UltimaSequencia;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Willys
 */
public class daoComposicaoComponente {
    
    ConexaoBanco conecta_banco;
    UltimaSequencia ultima;
    
    public daoComposicaoComponente()
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
    
    //Consulta pelo codigo de componentes mecânicos
    public void consultaCodigoComponente(ComposicaoComponente composicao){
        conecta_banco.executeSQL("select id_subcomponente, tipo, componente.descricao, material.id_material, material.descricao, qntd from composicao_componente" +
                                " inner join componente on (componente.id_componente = composicao_componente.id_subcomponente)" +
                                " left join material on (componente.id_material = material.id_material)" +
                                " where composicao_componente.id_componente = "+composicao.getId_componente()+" order by id_subcomponente asc");
                                composicao.setRetorno(conecta_banco.resultset);
    }
    
}

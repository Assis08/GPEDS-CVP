/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.GPEDSCVP.dao;

import br.edu.GPEDSCVP.classe.ComponenteVersaoProjeto;
import br.edu.GPEDSCVP.classe.ComposicaoComponente;
import br.edu.GPEDSCVP.conexao.ConexaoBanco;
import br.edu.GPEDSCVP.util.FormatarData;
import br.edu.GPEDSCVP.util.UltimaSequencia;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Willys
 */
public class daoComposicaoComponente {
    
    ConexaoBanco conecta_banco;
    UltimaSequencia ultima;
    daoComponenteVersaoProjeto dao_comp_vers = new daoComponenteVersaoProjeto();
    ComponenteVersaoProjeto comp_vers_proj = new ComponenteVersaoProjeto();
    
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
        conecta_banco.executeSQL("select null,id_composicao, id_subcomponente, tipo, componente.descricao, material.id_material, material.descricao, qntd, false from composicao_componente" +
                                " inner join componente on (componente.id_componente = composicao_componente.id_subcomponente)" +
                                " left join material on (componente.id_material = material.id_material)" +
                                " where composicao_componente.id_componente = "+composicao.getId_componente()+" order by id_subcomponente asc");
                                composicao.setRetorno(conecta_banco.resultset);
    }
    
     public void alterarComposicao (ComposicaoComponente composicao){
        
        Integer id_subcomponente;
        Integer id_componente;
        Integer id_composicao;
        Integer qntd;
        String tipo = "";
        String data_cadastro;
        
        DefaultTableModel tabela = (DefaultTableModel) composicao.getTabela().getModel();
        int totlinha = tabela.getRowCount();
        for (int i = 0; i < totlinha; i++){
            id_composicao = Integer.parseInt(tabela.getValueAt(i, 1).toString());
            id_subcomponente = Integer.parseInt(tabela.getValueAt(i, 2).toString());
            tipo = tabela.getValueAt(i, 3).toString();
            qntd = Integer.parseInt(tabela.getValueAt(i, 7).toString());
            Integer exc = Integer.parseInt(tabela.getValueAt(i, 8).toString());

            //Verifica se já existe a composicao cadastrada
            String sql = "select * from composicao_componente where id_composicao = "+ id_composicao;
            try {
                conecta_banco.executeSQL(sql);
                conecta_banco.resultset.first();
                //se existe a composicao
                if(id_composicao == conecta_banco.resultset.getInt("id_composicao")){
                    //apenas altera 
                    sql = "UPDATE COMPOSICAO_COMPONENTE SET ID_COMPOSICAO = "+ id_composicao+","
                    + "ID_SUBCOMPONENTE = " + id_subcomponente+","
                    + "ID_COMPONENTE = " + composicao.getId_componente()+","
                    + "qntd = " + qntd+","
                    + "estado = 'A',"
                    + "DATA_CADASTRO = '" + FormatarData.dateParaSQLDate(composicao.getData_cadastro()) +"',"
                    + "DATA_ALTER = '"+ FormatarData.dateParaTimeStamp(composicao.getData_alter())+"' "
                    + " WHERE ID_COMPOSICAO = " + id_composicao;
            
                    conecta_banco.atualizarSQL(sql);  
                
                }

            } catch (Exception e) {
                //Chegou aqui porque a composicao não existe, então inclui
               
                 sql = "INSERT INTO COMPOSICAO_COMPONENTE VALUES ("
                    +id_composicao + ","
                    +id_subcomponente+","
                    +composicao.getId_componente() +","
                    +qntd + ","
                    +"'A','"
                    +FormatarData.dateParaSQLDate(composicao.getData_cadastro()) + "','"
                    + FormatarData.dateParaTimeStamp(composicao.getData_alter())+"')";
            
                    conecta_banco.incluirSQL(sql);
            }
            
            //Se for um registro excluido da Jtable
            if(exc == 1){
                //Exclui do banco
                sql = "DELETE FROM COMPOSICAO_COMPONENTE WHERE ID_COMPOSICAO = " + id_composicao;
                conecta_banco.atualizarSQL(sql);
            }
        }

    }
    
}

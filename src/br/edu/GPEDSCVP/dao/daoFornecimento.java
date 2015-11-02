/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.GPEDSCVP.dao;

import br.edu.GPEDSCVP.classe.ComponenteFornecimento;
import br.edu.GPEDSCVP.conexao.ConexaoBanco;
import br.edu.GPEDSCVP.util.Rotinas;
import br.edu.GPEDSCVP.util.UltimaSequencia;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Willys
 */
public class daoFornecimento {
    
    ConexaoBanco conecta_banco;
    UltimaSequencia ultima;
    
    public daoFornecimento()
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
    
    //Método de incluir fornecedores para um componente
    public void addComponenteFornecimento( ComponenteFornecimento componente , JTable ConsultaComponentes, int situacao) throws SQLException{
        
        DefaultTableModel TabelaConsultaComponentes = (DefaultTableModel)ConsultaComponentes.getModel();
        DefaultTableModel TabelaComponentesFornecidos = (DefaultTableModel) componente.getTabela().getModel();
        ultima = new UltimaSequencia();
        
        Boolean sel = false;
        String valor_unit;
        String total;
        Integer id_fornecidos;
      
        int totlinha_comp_fornec = TabelaComponentesFornecidos.getRowCount();
       
        //se não possuir linhas
        if(totlinha_comp_fornec == 0){
            //add a primeira linha
            TabelaComponentesFornecidos.setNumRows(1);  
            id_fornecidos = ultima.ultimasequencia("COMPONENTES_FORNECIMENTO","ID_COMP_FORNEC"); 
            //formata valores monetários 
            valor_unit = String.valueOf(componente.getValor_unit());
            valor_unit = valor_unit.replace(".", ",");
            
            total = String.valueOf(componente.getValor_unit() * componente.getQntd_componente());
            total = total.replace(".", ",");
            //seta valores na jtable
            TabelaComponentesFornecidos.setValueAt(false, 0, 0);
            TabelaComponentesFornecidos.setValueAt(id_fornecidos, 0,1);
            TabelaComponentesFornecidos.setValueAt(componente.getId_componente(), 0, 2);
            TabelaComponentesFornecidos.setValueAt(componente.getDescricao(), 0, 3);
            TabelaComponentesFornecidos.setValueAt(valor_unit, 0, 4);
            TabelaComponentesFornecidos.setValueAt(componente.getId_moeda(), 0, 5);
            TabelaComponentesFornecidos.setValueAt(componente.getMoeda(), 0, 6);
            TabelaComponentesFornecidos.setValueAt(componente.getQntd_componente(), 0, 7);
            TabelaComponentesFornecidos.setValueAt(total, 0, 8);
                        
        }else{
           // totlinha_comp_fornec = componente.getTabela().getRowCount();
            for (int i_comp = 0; i_comp < totlinha_comp_fornec; i_comp++){
                //Chegou na ultima linha
                if( i_comp == totlinha_comp_fornec-1){

                    //se estiver em modo de alteração
                    if(situacao == Rotinas.ALTERAR){
                        //gera ultimo id baseado no banco
                        id_fornecidos = ultima.ultimasequencia("COMPONENTES_FORNECIMENTO","ID_COMP_FORNEC"); 
                    }else{
                        //gera o ultimo id baseado no ultimo registro da jtable
                        id_fornecidos = (Integer.parseInt(TabelaComponentesFornecidos.getValueAt(totlinha_comp_fornec-1, 1).toString())+1);
                    }
                    
                    //formata valores monetários 
                    valor_unit = String.valueOf(componente.getValor_unit());
                    valor_unit = valor_unit.replace(".", ",");

                    total = String.valueOf(componente.getValor_unit() * componente.getQntd_componente());
                    total = total.replace(".", ",");

                    //adiciona uma linha a mais
                    TabelaComponentesFornecidos.setNumRows(totlinha_comp_fornec+1);  
                     //seta valores na jtable
                    TabelaComponentesFornecidos.setValueAt(false, totlinha_comp_fornec, 0);
                    TabelaComponentesFornecidos.setValueAt(id_fornecidos, totlinha_comp_fornec,1);
                    TabelaComponentesFornecidos.setValueAt(componente.getId_componente(), totlinha_comp_fornec, 2);
                    TabelaComponentesFornecidos.setValueAt(componente.getDescricao(), totlinha_comp_fornec, 3);
                    TabelaComponentesFornecidos.setValueAt(valor_unit, totlinha_comp_fornec, 4);
                    TabelaComponentesFornecidos.setValueAt(componente.getId_moeda(), totlinha_comp_fornec, 5);
                    TabelaComponentesFornecidos.setValueAt(componente.getMoeda(), totlinha_comp_fornec, 6);
                    TabelaComponentesFornecidos.setValueAt(componente.getQntd_componente(), totlinha_comp_fornec, 7);
                    TabelaComponentesFornecidos.setValueAt(total, totlinha_comp_fornec, 8);
                }
            }  
        } 
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.GPEDSCVP.dao;

import br.edu.GPEDSCVP.classe.ComponenteFornecimento;
import br.edu.GPEDSCVP.conexao.ConexaoBanco;
import br.edu.GPEDSCVP.util.FormatarData;
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
public class daoComponentesFornecimento {
    
    ConexaoBanco conecta_banco;
    UltimaSequencia ultima;
    
    public daoComponentesFornecimento()
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
            TabelaComponentesFornecidos.setValueAt(componente.getQntd_componente(), 0, 8);
            TabelaComponentesFornecidos.setValueAt(total, 0, 9);
            TabelaComponentesFornecidos.setValueAt(0, 0, 10);
                        
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
                    TabelaComponentesFornecidos.setValueAt(componente.getQntd_componente(), totlinha_comp_fornec, 8);
                    TabelaComponentesFornecidos.setValueAt(total, totlinha_comp_fornec, 9);
                    TabelaComponentesFornecidos.setValueAt(0, totlinha_comp_fornec, 10);
                }
            }  
        } 
    }
    
     public void gravarCompFornec (ComponenteFornecimento componentes){
        
        Integer id_comp_fornec;
        Integer id_componente;
        Integer id_fornecimento;
        Integer id_moeda;
        Integer qntd;
        Double valor_unit;

        DefaultTableModel tabela = (DefaultTableModel) componentes.getTabela().getModel();
        int totlinha = tabela.getRowCount();
        for (int i = 0; i < totlinha; i++){
            
            id_comp_fornec = Integer.parseInt(tabela.getValueAt(i, 1).toString());
            id_componente = Integer.parseInt(tabela.getValueAt(i, 2).toString());
            id_fornecimento = componentes.getId_fornecimento();
            id_moeda = Integer.parseInt(tabela.getValueAt(i, 5).toString());
            qntd = Integer.parseInt(tabela.getValueAt(i, 7).toString());
            valor_unit = Double.parseDouble(tabela.getValueAt(i, 4).toString().replace(",", "."));
                    
            //int sequencia = (Integer) (ultima.ultimasequencia("COMPOSICAO_COMPONENTE","ID_SUBCOMPONENTE"));
            conecta_banco.executeSQL("INSERT INTO componentes_fornecimento (id_comp_fornec,id_componente, id_fornecimento, id_moeda, qntd_componente,valor_unit,data_alter) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?) ",
            id_comp_fornec,
            id_componente,
            id_fornecimento,
            id_moeda,
            qntd,
            valor_unit,
            FormatarData.dateParaTimeStamp(componentes.getData_alter()));
        }
     }
     
     public boolean verificaCompFornec (ComponenteFornecimento componentes){
        
        Integer qntd;
        Double valor_unit;

        DefaultTableModel tabela = (DefaultTableModel) componentes.getTabela().getModel();
        int totlinha = tabela.getRowCount();
        for (int i = 0; i < totlinha; i++){
            qntd = Integer.parseInt(tabela.getValueAt(i, 8).toString());
            if(qntd >0){
                return false;
            }
        }
        return true;
     }
     
    //Método para remover um registro da Jtable
    public void removeAtualizaItens(JTable jtable, JTable jtable_atualiza, int situacao) {
        
        DefaultTableModel tabela = (DefaultTableModel) jtable.getModel();
        DefaultTableModel tabela_atualiza = (DefaultTableModel) jtable_atualiza.getModel();
        
        int totlinha = tabela.getRowCount();
        int totlinha_atualiza = tabela_atualiza.getRowCount();
        int totcolun = tabela.getColumnCount();
        Boolean sel = false;
        Boolean achou = false;
        Integer id_componente;
        
        int opcao = JOptionPane.showConfirmDialog(null, "Deseja remover os registros selecionados? ",
                "remover",
                JOptionPane.YES_NO_OPTION);
        if (opcao == JOptionPane.YES_OPTION) {
            //Percorre linhas da jtable
            for(int i = totlinha-1; i >= 0; i--){

                sel = (Boolean) tabela.getValueAt(i, 0);
                //Se a linha estiver selecionada
                if(sel != null){
                    if(sel == true){
                        id_componente = Integer.parseInt(tabela.getValueAt(i, 2).toString());
                        //ativa flag que achou uma linha selecionada
                        achou = true;
                        if(situacao == Rotinas.ALTERAR){
                            //seta o valor 1 na coluna excluido da jtable
                            jtable.setValueAt(1, i, totcolun-1);
                            jtable.setValueAt(false, i, 0);
                            //habilita e desabilita para atualizar o jtable (caso contrario pinta de vermelho só quando clica na linha)
                            jtable.setEnabled(false);
                            jtable.setEnabled(true);
                        }else{
                            tabela.removeRow(i);
                            for(int i_atualiza = totlinha_atualiza-1; i_atualiza >= 0; i_atualiza--){
                                if(tabela_atualiza.getValueAt(i_atualiza, 6) == id_componente){
                                    tabela_atualiza.removeRow(i_atualiza); 
                                }
                            }
                        }
                    }
                }
            }
        }
        if(achou == false){
            JOptionPane.showMessageDialog(null, "Nehuma linha selecionada!");
        }
    }
}

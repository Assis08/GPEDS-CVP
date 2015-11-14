/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.GPEDSCVP.dao;

import br.edu.GPEDSCVP.classe.ComponenteVersaoProjeto;
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
public class daoComponenteVersaoProjeto {
    
    ConexaoBanco conecta_banco;
    UltimaSequencia ultima;
    
    public daoComponenteVersaoProjeto()
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
    public void addComponenteVersao( ComponenteVersaoProjeto componente_projeto ,JTable componente_fornec, int situacao) throws SQLException{

        DefaultTableModel TabelaCompVersProj = (DefaultTableModel) componente_projeto.getTabela().getModel();
        DefaultTableModel TabelaCompFornec = (DefaultTableModel) componente_fornec.getModel();
        
        ultima = new UltimaSequencia();
        
        Integer qntd_fornecida;
        Integer id_componentes;
      
        int totlinha_comp_proj = TabelaCompVersProj.getRowCount();
        int totlinha_comp_fornec = TabelaCompFornec.getRowCount();
       
        //se não possuir linhas
        if(totlinha_comp_proj == 0){
            //add a primeira linha
            TabelaCompVersProj.setNumRows(1);  
            id_componentes = ultima.ultimasequencia("COMPONENTES_VERSAO_PROJETO","ID_COMP_VERSAO");

            //seta valores na jtable
            TabelaCompVersProj.setValueAt(false, 0, 0);
            TabelaCompVersProj.setValueAt(id_componentes, 0,1);
            TabelaCompVersProj.setValueAt(componente_projeto.getId_comp_fornec(),0,2);
            TabelaCompVersProj.setValueAt(componente_projeto.getId_projeto(), 0, 3);
            TabelaCompVersProj.setValueAt(componente_projeto.getCod_vers_projeto(), 0, 4);
            TabelaCompVersProj.setValueAt(componente_projeto.getProjeto(), 0, 5);
            TabelaCompVersProj.setValueAt(componente_projeto.getVersao(), 0, 6);
            TabelaCompVersProj.setValueAt(componente_projeto.getId_componente(), 0, 7);
            TabelaCompVersProj.setValueAt(componente_projeto.getComponente(), 0, 8);
            TabelaCompVersProj.setValueAt(componente_projeto.getQntd_para_projeto(), 0, 9);
            TabelaCompVersProj.setValueAt(0, 0, 10);
         
            //processo para dar baixa na quantidade fornecida
            for (int i_comp_fornec = 0; i_comp_fornec < totlinha_comp_fornec; i_comp_fornec++){
                if(TabelaCompFornec.getValueAt(i_comp_fornec, 2) != null){
                    if(Integer.parseInt(TabelaCompFornec.getValueAt(i_comp_fornec, 2).toString()) == componente_projeto.getId_componente()){
                        qntd_fornecida = Integer.parseInt(TabelaCompFornec.getValueAt(i_comp_fornec, 8).toString());
                        //da baixa em componente fornecidos referente ao valor fornecido para projeto
                        TabelaCompFornec.setValueAt(qntd_fornecida - componente_projeto.getQntd_para_projeto(), i_comp_fornec, 8);
                    }
                }
            }
                        
        }else{
           // totlinha_comp_fornec = componente.getTabela().getRowCount();
            for (int i_comp = 0; i_comp < totlinha_comp_proj; i_comp++){
                
                //se o componente ja existir entao sobreescreve apenas a quantidade 
                if(Integer.parseInt(TabelaCompVersProj.getValueAt(i_comp, 7).toString()) == componente_projeto.getId_componente()&&
                   Integer.parseInt(TabelaCompVersProj.getValueAt(i_comp, 4).toString()) == componente_projeto.getCod_vers_projeto()) {
                   Integer qntd_atual = Integer.parseInt(TabelaCompVersProj.getValueAt(i_comp, 9).toString());
                   TabelaCompVersProj.setValueAt(qntd_atual+componente_projeto.getQntd_para_projeto(), i_comp, 9);
                   
                   //processo para dar baixa na quantidade fornecida
                    for (int i_comp_fornec = 0; i_comp_fornec < totlinha_comp_fornec; i_comp_fornec++){
                        if(TabelaCompFornec.getValueAt(i_comp_fornec, 2) != null){
                            if(Integer.parseInt(TabelaCompFornec.getValueAt(i_comp_fornec, 2).toString()) == componente_projeto.getId_componente()){
                                qntd_fornecida = Integer.parseInt(TabelaCompFornec.getValueAt(i_comp_fornec, 8).toString());
                                //da baixa em componente fornecidos referente ao valor fornecido para projeto
                                TabelaCompFornec.setValueAt(qntd_fornecida - componente_projeto.getQntd_para_projeto(), i_comp_fornec, 8);
                            }
                        }
                    }
                   break;
                }else{
                    
                    //Chegou na ultima linha
                    if( i_comp == totlinha_comp_proj-1){

                        //se estiver em modo de alteração
                        if(situacao == Rotinas.ALTERAR){
                            //gera ultimo id baseado no banco
                            id_componentes = ultima.ultimasequencia("COMPONENTES_VERSAO_PROJETO","ID_COMP_VERSAO"); 
                        }else{
                            //gera o ultimo id baseado no ultimo registro da jtable
                            id_componentes = (Integer.parseInt(TabelaCompVersProj.getValueAt(totlinha_comp_proj-1, 1).toString())+1);
                        }

                        //adiciona uma linha a mais
                        TabelaCompVersProj.setNumRows(totlinha_comp_proj+1);  
                        //seta valores na jtable
                        TabelaCompVersProj.setValueAt(false, totlinha_comp_proj, 0);
                        TabelaCompVersProj.setValueAt(id_componentes, totlinha_comp_proj,1);
                        TabelaCompVersProj.setValueAt(componente_projeto.getId_comp_fornec(),totlinha_comp_proj,2);
                        TabelaCompVersProj.setValueAt(componente_projeto.getId_projeto(), totlinha_comp_proj, 3);
                        TabelaCompVersProj.setValueAt(componente_projeto.getCod_vers_projeto(), totlinha_comp_proj, 4);
                        TabelaCompVersProj.setValueAt(componente_projeto.getProjeto(), totlinha_comp_proj, 5);
                        TabelaCompVersProj.setValueAt(componente_projeto.getVersao(), totlinha_comp_proj, 6);
                        TabelaCompVersProj.setValueAt(componente_projeto.getId_componente(), totlinha_comp_proj, 7);
                        TabelaCompVersProj.setValueAt(componente_projeto.getComponente(), totlinha_comp_proj, 8);
                        TabelaCompVersProj.setValueAt(componente_projeto.getQntd_para_projeto(), totlinha_comp_proj, 9);
                        TabelaCompVersProj.setValueAt(0, totlinha_comp_proj, 10);

                        //processo para dar baixa na quantidade fornecida
                        for (int i_comp_fornec = 0; i_comp_fornec < totlinha_comp_fornec; i_comp_fornec++){
                            if(TabelaCompFornec.getValueAt(i_comp_fornec, 2) != null){
                                if(Integer.parseInt(TabelaCompFornec.getValueAt(i_comp_fornec, 2).toString()) == componente_projeto.getId_componente()){
                                    qntd_fornecida = Integer.parseInt(TabelaCompFornec.getValueAt(i_comp_fornec, 8).toString());
                                    //da baixa em componente fornecidos referente ao valor fornecido para projeto
                                    TabelaCompFornec.setValueAt(qntd_fornecida - componente_projeto.getQntd_para_projeto(), i_comp_fornec, 8);
                                }
                            }
                        }
                    }
                }
            }  
        } 
    }
    
    public void gravarCompVersProj (ComponenteVersaoProjeto componentes){

       Integer id_comp_vers;
       Integer id_comp_fornec;
       Integer id_projeto;
       Integer cod_vers_projeto;
       Integer id_componente;
       Integer qntd_para_projeto;

       DefaultTableModel tabela = (DefaultTableModel) componentes.getTabela().getModel();
       int totlinha = tabela.getRowCount();
       for (int i = 0; i < totlinha; i++){

           id_comp_vers = Integer.parseInt(tabela.getValueAt(i, 1).toString());
           id_comp_fornec = Integer.parseInt(tabela.getValueAt(i, 2).toString());
           id_projeto = Integer.parseInt(tabela.getValueAt(i, 3).toString());
           cod_vers_projeto = Integer.parseInt(tabela.getValueAt(i, 4).toString());
           id_componente = Integer.parseInt(tabela.getValueAt(i, 7).toString());
           qntd_para_projeto = Integer.parseInt(tabela.getValueAt(i, 9).toString());

           //int sequencia = (Integer) (ultima.ultimasequencia("COMPOSICAO_COMPONENTE","ID_SUBCOMPONENTE"));
           conecta_banco.executeSQL("INSERT INTO componentes_versao_projeto (id_comp_versao,id_projeto, cod_vers_projeto, id_fornecimento, id_componente,"
                   + "id_comp_fornec,qntd_para_projeto, qntd_no_projeto, situacao, data_alter) "
           + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ",
           id_comp_vers,
           id_projeto,
           cod_vers_projeto,
           componentes.getId_fornecimento(),
           id_componente,
           id_comp_fornec,
           qntd_para_projeto,
           0,
           "A",
           FormatarData.dateParaTimeStamp(componentes.getData_alter()));
           
          
           
       }
    }
    
    //Método para remover um registro da Jtable
    public void removeAtualizaItens(JTable jtable, JTable jtable_atualiza, int situacao) {
        
        DefaultTableModel tabela = (DefaultTableModel) jtable.getModel();
        DefaultTableModel tabela_atualiza = (DefaultTableModel) jtable_atualiza.getModel();
        
        int totlinha = tabela.getRowCount();
        int totcolun = tabela.getColumnCount();
        int totlinha_atualiza = tabela_atualiza.getRowCount();
        Integer id_componente;
        Integer id_componente_atualiza;
        Integer qntd_atual;
        Integer qntd_add;
        
        Boolean sel = false;
        Boolean achou = false;
        
        int opcao = JOptionPane.showConfirmDialog(null, "Deseja remover os registros selecionados? ",
                "remover",
                JOptionPane.YES_NO_OPTION);
        if (opcao == JOptionPane.YES_OPTION) {
        //Percorre linhas da jtable
        for(int i =totlinha-1; i >=0; i--){
            sel = (Boolean) tabela.getValueAt(i, 0);
            //Se a linha estiver selecionada
            if(sel != null){
                if(sel == true){
                    id_componente = Integer.parseInt(tabela.getValueAt(i, 7).toString());
                    qntd_add = Integer.parseInt(tabela.getValueAt(i, 9).toString());
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
                        for(int i_atualiza =0; i_atualiza < totlinha_atualiza; i_atualiza++){
                            if(id_componente == tabela_atualiza.getValueAt(i_atualiza, 2)){
                                qntd_atual = Integer.parseInt(tabela_atualiza.getValueAt(i_atualiza, 8).toString());
                                tabela_atualiza.setValueAt(qntd_atual + qntd_add, i_atualiza, 8);
                                break;
                            }
                        }
                        tabela.removeRow(i);
                    }
                }
            }
        }
        if(achou == false){
            JOptionPane.showMessageDialog(null, "Nehuma linha selecionada!");
        }
        }
    }
}

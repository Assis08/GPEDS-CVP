/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.GPEDSCVP.dao;

import br.edu.GPEDSCVP.classe.ComponenteVersaoProjeto;
import br.edu.GPEDSCVP.classe.VersaoProjeto;
import br.edu.GPEDSCVP.conexao.ConexaoBanco;
import br.edu.GPEDSCVP.util.Conversoes;
import br.edu.GPEDSCVP.util.FormatarData;
import br.edu.GPEDSCVP.util.Rotinas;
import br.edu.GPEDSCVP.util.UltimaSequencia;
import br.edu.GPEDSCVP.util.ValidaCampos;
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
    Conversoes conversoes = new Conversoes();
    
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
        Integer exc;
      
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
                
                exc = Integer.parseInt(TabelaCompVersProj.getValueAt(i_comp, 10).toString());
                
                //se o componente ja existir e não estiver excluido entao sobreescreve apenas a quantidade 
                if(Integer.parseInt(TabelaCompVersProj.getValueAt(i_comp, 7).toString()) == componente_projeto.getId_componente()&&
                   Integer.parseInt(TabelaCompVersProj.getValueAt(i_comp, 4).toString()) == componente_projeto.getCod_vers_projeto() &&
                   exc == 0) {
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
                    //Se não existir o componente ou estiver marcado como excluido
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
                            
                            exc = Integer.parseInt(TabelaCompFornec.getValueAt(i_comp_fornec, 10).toString());
                            
                            if(TabelaCompFornec.getValueAt(i_comp_fornec, 2) != null){
                                if(Integer.parseInt(TabelaCompFornec.getValueAt(i_comp_fornec, 2).toString()) == componente_projeto.getId_componente() &&
                                   exc == 0){
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

           conecta_banco.executeSQL("INSERT INTO componentes_versao_projeto (id_comp_versao,id_projeto, cod_vers_projeto, id_fornecimento, id_componente,"
                   + "id_comp_fornec,qntd_para_projeto, qntd_no_projeto, situacao, data_alter, in_ativo) "
           + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ",
           id_comp_vers,
           id_projeto,
           cod_vers_projeto,
           componentes.getId_fornecimento(),
           id_componente,
           id_comp_fornec,
           qntd_para_projeto,
           0,
           "NC",
           FormatarData.dateParaTimeStamp(componentes.getData_alter()),
           "A");
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
                        
                        for(int i_atualiza =0; i_atualiza < totlinha_atualiza; i_atualiza++){
                            if(id_componente == Integer.parseInt(tabela_atualiza.getValueAt(i_atualiza, 2).toString())){
                                qntd_atual = Integer.parseInt(tabela_atualiza.getValueAt(i_atualiza, 8).toString());
                                tabela_atualiza.setValueAt(qntd_atual + qntd_add, i_atualiza, 8);
                                break;
                            }
                        }
      
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
    
    //Consulta pelo codigo do fornecimento os componentes fornecidos para os projetos
    public void consultaCompFornecVersProj(ComponenteVersaoProjeto componente){
        conecta_banco.executeSQL("select null, id_comp_versao,componentes_versao_projeto.id_comp_fornec,componentes_versao_projeto.id_fornecimento, componentes_versao_projeto.id_projeto,componentes_versao_projeto.cod_vers_projeto,"
                               + " projeto.descricao,versao_projeto.versao,componentes_versao_projeto.id_componente,componente.descricao,qntd_para_projeto,componentes_versao_projeto.in_ativo, false from componentes_versao_projeto"
                               + " inner join versao_projeto on (versao_projeto.cod_vers_projeto = componentes_versao_projeto.cod_vers_projeto)" 
                               + " inner join projeto on (projeto.id_projeto = versao_projeto.id_projeto)"
                               + " inner join componentes_fornecimento on (componentes_fornecimento.id_comp_fornec = componentes_versao_projeto.id_comp_fornec)"
                               + " inner join componente on (componente.id_componente = componentes_fornecimento.id_componente)"
                               + " where componentes_versao_projeto.id_fornecimento = "+componente.getId_fornecimento()+" and componentes_versao_projeto.in_ativo = 'A' "
                               + " order by id_comp_versao asc");
        
                                componente.setRetorno(conecta_banco.resultset);
    }
    
    public void alterarCompVersProj (ComponenteVersaoProjeto compVersProj){
        
        Integer id_comp_vers_proj;
        Integer id_fornecidos;
        Integer id_projeto;
        Integer id_versao;
        Integer id_componente;
        Integer qntd_para_projeto;
      
        DefaultTableModel tabela = (DefaultTableModel) compVersProj.getTabela().getModel();
        
        int totlinha = tabela.getRowCount();
        
        for (int i = 0; i < totlinha; i++){
            
            id_comp_vers_proj = Integer.parseInt(tabela.getValueAt(i, 1).toString());
            id_fornecidos = Integer.parseInt(tabela.getValueAt(i, 2).toString());
            id_projeto = Integer.parseInt(tabela.getValueAt(i, 3).toString());
            id_versao = Integer.parseInt(tabela.getValueAt(i, 4).toString());
            id_componente = Integer.parseInt(tabela.getValueAt(i, 7).toString());
            qntd_para_projeto = Integer.parseInt(tabela.getValueAt(i, 9).toString());
            Integer exc = Integer.parseInt(tabela.getValueAt(i, 10).toString());

            //Verifica se já existe o fornecimento deste componente cadastrado para um projeto
            String sql = "select * from componentes_versao_projeto where id_comp_versao = "+ id_comp_vers_proj;
            try {
                conecta_banco.executeSQL(sql);
                conecta_banco.resultset.first();
                //se já existe um projeto com este fornecimento
                if(id_comp_vers_proj == conecta_banco.resultset.getInt("id_comp_versao")){
                    //apenas altera 
                    conecta_banco.executeSQL("UPDATE componentes_versao_projeto SET id_projeto = ?, cod_vers_projeto = ?, id_componente = ?, id_comp_fornec = ?, qntd_para_projeto = ?, data_alter = ?"
                    + "WHERE id_comp_versao = ? ",
                    id_projeto,
                    id_versao,
                    id_componente,
                    id_fornecidos,
                    qntd_para_projeto,
                    FormatarData.dateParaTimeStamp(compVersProj.getData_alter()),
                    id_comp_vers_proj);
                }

            } catch (Exception e) {
                
                    //Chegou aqui porque o fornecimento do componente não existe, então inclui
               
                    conecta_banco.executeSQL("INSERT INTO componentes_versao_projeto (id_comp_versao,id_projeto, cod_vers_projeto, id_fornecimento, id_componente,"
                    + "id_comp_fornec,qntd_para_projeto, qntd_no_projeto, situacao, data_alter, in_ativo) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ",
                    id_comp_vers_proj,
                    id_projeto,
                    id_versao,
                    compVersProj.getId_fornecimento(),
                    id_componente,
                    id_fornecidos,
                    qntd_para_projeto,
                    0,
                    "NC",
                    FormatarData.dateParaTimeStamp(compVersProj.getData_alter()),
                    "A");
                 }
            
            //Se for um registro excluido da Jtable
            if(exc == 1){
                //Inativa o componentes fornecidos para projetos
                conecta_banco.atualizarSQL("UPDATE COMPONENTES_VERSAO_PROJETO SET IN_ATIVO = 'I'"
                                         + " WHERE ID_COMP_VERSAO = " + id_comp_vers_proj);
            }
        }
    } 
    
    //Consulta de todos componentes fornecidos para uma determinada versão do projeto
    public void consultaGeralCompFornecVersao(ComponenteVersaoProjeto comp_vers_proj){
        conecta_banco.executeSQL("select null, componentes_versao_projeto.id_comp_versao,componentes_versao_projeto.id_projeto,projeto.descricao," 
                               +" componentes_versao_projeto.cod_vers_projeto,versao_projeto.versao,componentes_versao_projeto.id_fornecimento," 
                               +" fornecimento.descricao,componentes_versao_projeto.id_componente,componente.descricao, componente.tipo,"
                               +" componentes_versao_projeto.id_comp_fornec, qntd_para_projeto,qntd_no_projeto,situacao,fornecimento.data_cadastro,"
                               +" componentes_versao_projeto.data_alter,componentes_versao_projeto.in_ativo,componentes_fornecimento.id_moeda,moeda.unidade,"
                               +" componentes_fornecimento.valor_unit from componentes_versao_projeto" 
                               +" inner join componentes_fornecimento on (componentes_fornecimento.id_comp_fornec = componentes_versao_projeto.id_comp_fornec)" 
                               +" inner join moeda on (moeda.id_moeda = componentes_fornecimento.id_moeda)"
                               +" inner join componente on (componente.id_componente = componentes_fornecimento.id_componente)"
                               +" inner join fornecimento on (fornecimento.id_fornecimento = componentes_fornecimento.id_fornecimento)" 
                               +" inner join versao_projeto on (versao_projeto.cod_vers_projeto = componentes_versao_projeto.cod_vers_projeto)"
                               +" inner join projeto on (projeto.id_projeto = versao_projeto.id_projeto)" 
                               +" where componentes_versao_projeto.id_projeto = "+comp_vers_proj.getId_projeto()+" and versao_projeto.versao = "+comp_vers_proj.getVersao()+""
                               +" and componente.tipo = '"+comp_vers_proj.getTipo()+"' and componentes_versao_projeto.in_ativo = 'A'"
                               +" and (qntd_para_projeto - qntd_no_projeto) > 0 ");
        
                                comp_vers_proj.setRetorno(conecta_banco.resultset);
    }
    
    //Consulta de todos componentes fornecidos para uma determinada versão do projeto pelo código do componente
    public void consultaCodigoCompFornecVersao(ComponenteVersaoProjeto comp_vers_proj){
         conecta_banco.executeSQL("select null, componentes_versao_projeto.id_comp_versao,componentes_versao_projeto.id_projeto,projeto.descricao," 
                               +" componentes_versao_projeto.cod_vers_projeto,versao_projeto.versao,componentes_versao_projeto.id_fornecimento," 
                               +" fornecimento.descricao,componentes_versao_projeto.id_componente,componente.descricao, componente.tipo,"
                               +" componentes_versao_projeto.id_comp_fornec, qntd_para_projeto,qntd_no_projeto,situacao,fornecimento.data_cadastro,"
                               +" componentes_versao_projeto.data_alter,componentes_versao_projeto.in_ativo,componentes_fornecimento.id_moeda,moeda.unidade,"
                               +" componentes_fornecimento.valor_unit from componentes_versao_projeto" 
                               +" inner join componentes_fornecimento on (componentes_fornecimento.id_comp_fornec = componentes_versao_projeto.id_comp_fornec)" 
                               +" inner join moeda on (moeda.id_moeda = componentes_fornecimento.id_moeda)" 
                               +" inner join componente on (componente.id_componente = componentes_fornecimento.id_componente)"
                               +" inner join fornecimento on (fornecimento.id_fornecimento = componentes_fornecimento.id_fornecimento)" 
                               +" inner join versao_projeto on (versao_projeto.cod_vers_projeto = componentes_versao_projeto.cod_vers_projeto)"
                               +" inner join projeto on (projeto.id_projeto = versao_projeto.id_projeto)" 
                               +" where componentes_versao_projeto.id_projeto = "+comp_vers_proj.getId_projeto()+" and versao_projeto.versao = "+comp_vers_proj.getVersao()+""
                               +" and componente.tipo = '"+comp_vers_proj.getTipo()+"' and componentes_versao_projeto.id_componente = "+comp_vers_proj.getId_componente()+""
                               +" and componentes_versao_projeto.in_ativo = 'A'"
                               +" and (qntd_para_projeto - qntd_no_projeto) > 0 ");         
                              
                               comp_vers_proj.setRetorno(conecta_banco.resultset);
    }
    
    //Consulta de todos componentes fornecidos para uma determinada versão do projeto pela descrição do componente
    public void consultaDescricaoCompFornecVersao(ComponenteVersaoProjeto comp_vers_proj){
        conecta_banco.executeSQL("select null, componentes_versao_projeto.id_comp_versao,componentes_versao_projeto.id_projeto,projeto.descricao," 
                               +" componentes_versao_projeto.cod_vers_projeto,versao_projeto.versao,componentes_versao_projeto.id_fornecimento," 
                               +" fornecimento.descricao,componentes_versao_projeto.id_componente,componente.descricao, componente.tipo,"
                               +" componentes_versao_projeto.id_comp_fornec, qntd_para_projeto,qntd_no_projeto,situacao,fornecimento.data_cadastro,"
                               +" componentes_versao_projeto.data_alter,componentes_versao_projeto.in_ativo,componentes_fornecimento.id_moeda,moeda.unidade,"
                               +" componentes_fornecimento.valor_unit from componentes_versao_projeto" 
                               +" inner join componentes_fornecimento on (componentes_fornecimento.id_comp_fornec = componentes_versao_projeto.id_comp_fornec)" 
                               +" inner join moeda on (moeda.id_moeda = componentes_fornecimento.id_moeda)" 
                               +" inner join componente on (componente.id_componente = componentes_fornecimento.id_componente)"
                               +" inner join fornecimento on (fornecimento.id_fornecimento = componentes_fornecimento.id_fornecimento)" 
                               +" inner join versao_projeto on (versao_projeto.cod_vers_projeto = componentes_versao_projeto.cod_vers_projeto)"
                               +" inner join projeto on (projeto.id_projeto = versao_projeto.id_projeto)" 
                               +" where componentes_versao_projeto.id_projeto = "+comp_vers_proj.getId_projeto()+" and versao_projeto.versao = "+comp_vers_proj.getVersao()+""
                               +" and componente.tipo = '"+comp_vers_proj.getTipo()+"' and componente.descricao like '"+comp_vers_proj.getComponente()+"%'"
                               +" and componentes_versao_projeto.in_ativo = 'A'"
                               +" and (qntd_para_projeto - qntd_no_projeto) > 0 ");
        
                               comp_vers_proj.setRetorno(conecta_banco.resultset);
    }
    
    //Método de incluir componentes para uma versão do projeto
    public void addCompParaProjeto(ComponenteVersaoProjeto comp_vers_proj,JTable componente, int situacao) throws SQLException{
        
        DefaultTableModel TabelaCompVersProj = (DefaultTableModel) comp_vers_proj.getTabela().getModel();
        DefaultTableModel TabelaCompFornec = (DefaultTableModel) componente.getModel();
        Double total;
        int totlinha_comp_proj = TabelaCompVersProj.getRowCount();
        int totlinha_comp_fornec = TabelaCompFornec.getRowCount();
       
        //se não possuir linhas
        if(totlinha_comp_proj == 0){
            //add a primeira linha
            TabelaCompVersProj.setNumRows(1);  
            total = comp_vers_proj.getQntd_no_projeto() * comp_vers_proj.getValor_unit();
            //seta valores na jtable
            TabelaCompVersProj.setValueAt(false, 0, 0);
            TabelaCompVersProj.setValueAt(comp_vers_proj.getId_comp_fornec(), 0,1);
            TabelaCompVersProj.setValueAt(comp_vers_proj.getId_componente(),0,2);
            TabelaCompVersProj.setValueAt(comp_vers_proj.getComponente(), 0, 3);
            TabelaCompVersProj.setValueAt(comp_vers_proj.getId_moeda(), 0, 4);
            TabelaCompVersProj.setValueAt(comp_vers_proj.getUnidade(), 0, 5);
            TabelaCompVersProj.setValueAt(conversoes.doubleParaObjectDecimalFormat(comp_vers_proj.getValor_unit()), 0, 6);
            TabelaCompVersProj.setValueAt(comp_vers_proj.getQntd_no_projeto(), 0, 7);
            TabelaCompVersProj.setValueAt(conversoes.doubleParaObjectDecimalFormat(total), 0, 8);
            TabelaCompVersProj.setValueAt(0, 0, 9);
        
        }else{
         
            for (int i_comp = 0; i_comp < totlinha_comp_proj; i_comp++){
                
                Integer exc = Integer.parseInt(TabelaCompVersProj.getValueAt(i_comp, 8).toString());
                total = comp_vers_proj.getQntd_no_projeto() * comp_vers_proj.getValor_unit();
                //se o componente ja existir e não estiver excluido entao sobreescreve apenas a quantidade 
                if((Integer.parseInt(TabelaCompVersProj.getValueAt(i_comp, 1).toString()) == comp_vers_proj.getId_componente()) && exc == 0) {
                   
                    Integer qntd_atual = Integer.parseInt(TabelaCompVersProj.getValueAt(i_comp, 7).toString());
                    TabelaCompVersProj.setValueAt(qntd_atual+comp_vers_proj.getQntd_no_projeto(), i_comp, 7);
        
                }else{
                    //Se não existir o componente ou estiver marcado como excluido
                    //Chegou na ultima linha
                    if( i_comp == totlinha_comp_proj-1){

                        //adiciona uma linha a mais
                        TabelaCompVersProj.setNumRows(totlinha_comp_proj+1);  
                        //seta valores na jtable
                        TabelaCompVersProj.setValueAt(false, 0, 0);
                        TabelaCompVersProj.setValueAt(comp_vers_proj.getId_comp_versao(),totlinha_comp_proj,1);
                        TabelaCompVersProj.setValueAt(comp_vers_proj.getId_componente(),totlinha_comp_proj,2);
                        TabelaCompVersProj.setValueAt(comp_vers_proj.getComponente(), totlinha_comp_proj, 3);
                        TabelaCompVersProj.setValueAt(comp_vers_proj.getId_moeda(), totlinha_comp_proj, 4);
                        TabelaCompVersProj.setValueAt(comp_vers_proj.getUnidade(), totlinha_comp_proj, 5);
                        TabelaCompVersProj.setValueAt(conversoes.doubleParaObjectDecimalFormat(comp_vers_proj.getValor_unit()), totlinha_comp_proj, 6);
                        TabelaCompVersProj.setValueAt(comp_vers_proj.getQntd_no_projeto(), totlinha_comp_proj, 7);
                        TabelaCompVersProj.setValueAt(conversoes.doubleParaObjectDecimalFormat(total), 0, 8);
                        TabelaCompVersProj.setValueAt(0, totlinha_comp_proj, 9);
                    }
                }
            }  
        } 
    }
}

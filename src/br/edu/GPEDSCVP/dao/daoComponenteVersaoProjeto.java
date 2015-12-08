/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.GPEDSCVP.dao;

import br.edu.GPEDSCVP.classe.Componente;
import br.edu.GPEDSCVP.classe.ComponenteVersaoProjeto;
import br.edu.GPEDSCVP.classe.VersaoProjeto;
import br.edu.GPEDSCVP.conexao.ConexaoBanco;
import br.edu.GPEDSCVP.util.Conversoes;
import br.edu.GPEDSCVP.util.ExcessaoBanco;
import br.edu.GPEDSCVP.util.FormatarData;
import br.edu.GPEDSCVP.util.Rotinas;
import br.edu.GPEDSCVP.util.UltimaSequencia;
import br.edu.GPEDSCVP.util.ValidaCampos;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import javax.swing.JFormattedTextField;
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
    daoMoeda dao_moeda = new daoMoeda();
    daoComponente dao_componente = new daoComponente();
    daoComponentesFornecimento dao_comp_fornec = new daoComponentesFornecimento();
    FormatarData data = new FormatarData();
    //ComponenteVersaoProjeto comp_vers_proj = new ComponenteVersaoProjeto();
    Componente componente = new Componente();
    
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
    public void addComponenteVersao( ComponenteVersaoProjeto componente_projeto,JTable componente_fornec, int situacao) throws SQLException{

        DefaultTableModel TabelaCompVersProj = (DefaultTableModel) componente_projeto.getTabela().getModel();
        DefaultTableModel TabelaCompFornec = (DefaultTableModel) componente_fornec.getModel();
        
        ultima = new UltimaSequencia();
        
        Integer qntd_fornecida;
        Integer id_componentes;
        Double valor_comp = 0.0;
        Double total_comp = 0.0;
        Object valor_comp_convert;
        Object total_comp_convert;
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
         
            //processo para dar baixa na quantidade fornecida e para calcular o custo unitário do componente caso seja formado por uma composição
            for (int i_comp_fornec = 0; i_comp_fornec < totlinha_comp_fornec; i_comp_fornec++){
                if(TabelaCompFornec.getValueAt(i_comp_fornec, 2) != null){
                    if(Integer.parseInt(TabelaCompFornec.getValueAt(i_comp_fornec, 2).toString()) == componente_projeto.getId_componente()){
                        qntd_fornecida = Integer.parseInt(TabelaCompFornec.getValueAt(i_comp_fornec, 8).toString());
                        //da baixa em componente fornecidos referente ao valor fornecido para projeto
                        TabelaCompFornec.setValueAt(qntd_fornecida - componente_projeto.getQntd_para_projeto(), i_comp_fornec, 8);
                        
                        //verifica se o componente possui composição
                        componente.setId_componente(componente_projeto.getId_componente());
                        
                        if(dao_componente.verificaExisteComposicao(componente)== true){
                            //se possui composição então calcula o custo unitário do componente e seta na jtable
                            valor_comp = dao_componente.calculaComposicaoComponente(componente_projeto);
                            valor_comp_convert = conversoes.doubleParaObjectDecimalFormat(valor_comp);
                            TabelaCompFornec.setValueAt(valor_comp_convert, i_comp_fornec, 4);
                            //calcula o total 
                            total_comp = valor_comp * componente_projeto.getQntd_para_projeto();
                            total_comp_convert = conversoes.doubleParaObjectDecimalFormat(total_comp);
                            TabelaCompFornec.setValueAt(total_comp_convert, i_comp_fornec, 9);
                        }
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
                        //adiciona uma linha a mais
                        TabelaCompVersProj.setNumRows(totlinha_comp_proj+1);  
                        
                        //se estiver em modo de alteração
                        if(situacao == Rotinas.ALTERAR){
                            //gera ultimo id baseado no banco
                            id_componentes = ultima.ultimasequencia("COMPONENTES_VERSAO_PROJETO","ID_COMP_VERSAO");
                            
                            if(id_componentes > Integer.parseInt( TabelaCompVersProj.getValueAt(totlinha_comp_proj-1, 1).toString())){
                                 TabelaCompVersProj.setValueAt(id_componentes, totlinha_comp_proj,1);
                            }else{
                                //gera id baseado no ultimo registro da Jtable
                                id_componentes = Integer.parseInt( TabelaCompVersProj.getValueAt(totlinha_comp_proj-1, 1).toString());
                                TabelaCompVersProj.setValueAt(id_componentes+1, totlinha_comp_proj,1);
                            }
                        }else{
                            //gera o ultimo id baseado no ultimo registro da jtable
                            id_componentes = (Integer.parseInt(TabelaCompVersProj.getValueAt(totlinha_comp_proj-1, 1).toString())+1);
                            TabelaCompVersProj.setValueAt(id_componentes, totlinha_comp_proj,1);
                        }

                        //seta valores na jtable
                        TabelaCompVersProj.setValueAt(false, totlinha_comp_proj, 0);
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
                                    
                                    //verifica se o componente possui composição
                                    componente.setId_componente(componente_projeto.getId_componente());
                        
                                    if(dao_componente.verificaExisteComposicao(componente)== true){
                                        //se possui composição então calcula o custo unitário do componente e seta na jtable
                                        valor_comp = dao_componente.calculaComposicaoComponente(componente_projeto);
                                        valor_comp_convert = conversoes.doubleParaObjectDecimalFormat(valor_comp);
                                        TabelaCompFornec.setValueAt(valor_comp_convert, i_comp_fornec, 4);
                                        //calcula o total 
                                        total_comp = valor_comp * componente_projeto.getQntd_para_projeto();
                                        total_comp_convert = conversoes.doubleParaObjectDecimalFormat(total_comp);
                                        TabelaCompFornec.setValueAt(total_comp_convert, i_comp_fornec, 9);
                                    }
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
                
                //atualiza valor unitário dos componentes que possuem este na composição 
                compVersProj.setId_componente(id_componente);
                compVersProj.setCod_vers_projeto(id_versao);
                atualizaValorUnitCompPai(compVersProj);

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
    
    //Consulta de todos componentes fornecidos para uma determinada versão do projeto e que estão sendo utilizadas no projeto
    public void consultaCompVersaoProjeto(ComponenteVersaoProjeto comp_vers_proj){
         conecta_banco.executeSQL("select null, componentes_versao_projeto.id_comp_versao,componentes_versao_projeto.id_projeto,projeto.descricao," 
                               +" componentes_versao_projeto.cod_vers_projeto,versao_projeto.versao,componentes_versao_projeto.id_fornecimento," 
                               +" fornecimento.descricao,componentes_versao_projeto.id_componente,componente.descricao, componente.tipo,"
                               +" componentes_versao_projeto.id_comp_fornec, qntd_para_projeto,qntd_no_projeto,situacao,fornecimento.data_cadastro,"
                               +" componentes_versao_projeto.data_alter,componentes_versao_projeto.in_ativo,componentes_fornecimento.id_moeda,moeda.unidade,"
                               +" componentes_fornecimento.valor_unit,componentes_fornecimento.valor_unit * qntd_no_projeto as total,false from componentes_versao_projeto" 
                               +" inner join componentes_fornecimento on (componentes_fornecimento.id_comp_fornec = componentes_versao_projeto.id_comp_fornec)" 
                               +" inner join moeda on (moeda.id_moeda = componentes_fornecimento.id_moeda)" 
                               +" inner join componente on (componente.id_componente = componentes_fornecimento.id_componente)"
                               +" inner join fornecimento on (fornecimento.id_fornecimento = componentes_fornecimento.id_fornecimento)" 
                               +" inner join versao_projeto on (versao_projeto.cod_vers_projeto = componentes_versao_projeto.cod_vers_projeto)"
                               +" inner join projeto on (projeto.id_projeto = versao_projeto.id_projeto)" 
                               +" where componentes_versao_projeto.id_projeto = "+comp_vers_proj.getId_projeto()+" and versao_projeto.versao = "+comp_vers_proj.getVersao()+""
                               +" and componente.tipo = '"+comp_vers_proj.getTipo()+"' and componentes_versao_projeto.in_ativo = 'A' and situacao = 'C'");         
                              
                               comp_vers_proj.setRetorno(conecta_banco.resultset);
    }
    
    
    
    //Método de incluir componentes para uma versão do projeto
    public void addCompParaProjeto(ComponenteVersaoProjeto comp_vers_proj,JTable componente, int situacao) throws SQLException{
        
        DefaultTableModel TabelaCompVersProj = (DefaultTableModel) comp_vers_proj.getTabela().getModel();
        DefaultTableModel TabelaCompFornec = (DefaultTableModel) componente.getModel();
        JFormattedTextField JftTotalEletronico = comp_vers_proj.getField_total_eletronicos();
        JFormattedTextField JftTotalMecanico = comp_vers_proj.getField_total_mecanicos();
        JFormattedTextField JftTotalComp = comp_vers_proj.getField_total_comp(); 
        
        Double total = 0.0;
        Double total_eletronicos = 0.0;
        Double total_mecanicos = 0.0;
        Double total_comp = 0.0;
        Double total_composicao = 0.0;
        Integer id_componente;
       
        int totlinha_comp_proj = TabelaCompVersProj.getRowCount();
        int totlinha_comp_fornec = TabelaCompFornec.getRowCount();
        //armazena o id do componente antes de calcular a composição do componente pois no processo do calculo altera o id do componente
        id_componente = comp_vers_proj.getId_componente();
        //total_composicao = dao_componente.calculaComposicaoComponente(comp_vers_proj);
       
        //se não possuir linhas
        if(totlinha_comp_proj == 0){
            //add a primeira linha
            TabelaCompVersProj.setNumRows(1);  
            
            //seta valores na jtable
            TabelaCompVersProj.setValueAt(false, 0, 0);
            TabelaCompVersProj.setValueAt(comp_vers_proj.getId_comp_versao(), 0,1);
            TabelaCompVersProj.setValueAt(id_componente,0,2);
            TabelaCompVersProj.setValueAt(comp_vers_proj.getComponente(), 0, 3);
            TabelaCompVersProj.setValueAt(comp_vers_proj.getId_moeda(), 0, 4);
            TabelaCompVersProj.setValueAt(comp_vers_proj.getUnidade(), 0, 5);
            if(comp_vers_proj.getValor_unit() > 0){
                total = comp_vers_proj.getQntd_no_projeto() * comp_vers_proj.getValor_unit();
                TabelaCompVersProj.setValueAt(conversoes.doubleParaObjectDecimalFormat(comp_vers_proj.getValor_unit()), 0, 6);
            }else{
               
                 TabelaCompVersProj.setValueAt(conversoes.doubleParaObjectDecimalFormat(total_composicao), 0, 6);
                 //calcula o total do componente utilizado no projeto
                 total = comp_vers_proj.getQntd_no_projeto() * total_composicao;
            }
            //Converte o total do componente para reais
            total = dao_moeda.converteparaReais(total, comp_vers_proj.getId_moeda(), comp_vers_proj.getData_fornec());
            TabelaCompVersProj.setValueAt(comp_vers_proj.getQntd_no_projeto(), 0, 7);
            TabelaCompVersProj.setValueAt(conversoes.doubleParaObjectDecimalFormat(total), 0, 8);
            TabelaCompVersProj.setValueAt(0, 0, 9);
            TabelaCompVersProj.setValueAt(0, 0, 10);

        }else{
         
            for (int i_comp = 0; i_comp < totlinha_comp_proj; i_comp++){
                
                Integer exc = Integer.parseInt(TabelaCompVersProj.getValueAt(i_comp, 9).toString());
                total = comp_vers_proj.getQntd_no_projeto() * comp_vers_proj.getValor_unit();
                //se o componente ja existir e não estiver excluido entao sobreescreve apenas a quantidade 
                if((Integer.parseInt(TabelaCompVersProj.getValueAt(i_comp, 1).toString()) == comp_vers_proj.getId_comp_versao()) && exc == 0) {
                    // adiciona a nova quantidade para o componente e racalcula o total
                    Integer qntd_atual = Integer.parseInt(TabelaCompVersProj.getValueAt(i_comp, 7).toString());
                    TabelaCompVersProj.setValueAt(qntd_atual+comp_vers_proj.getQntd_no_projeto(), i_comp, 7);
                    
                    if(comp_vers_proj.getValor_unit() > 0){
                        total = (qntd_atual + comp_vers_proj.getQntd_no_projeto()) * comp_vers_proj.getValor_unit();
                    }else{
                        total = (qntd_atual + comp_vers_proj.getQntd_no_projeto()) * total_composicao; 
                    }
                    
                    total = dao_moeda.converteparaReais(total, comp_vers_proj.getId_moeda(), comp_vers_proj.getData_fornec());
                    TabelaCompVersProj.setValueAt(conversoes.doubleParaObjectDecimalFormat(total), i_comp, 8);
                    break;
                }else{
                    //Se não existir o componente ou estiver marcado como excluido
                    //Chegou na ultima linha
                    if( i_comp == totlinha_comp_proj-1){

                        //adiciona uma linha a mais
                        TabelaCompVersProj.setNumRows(totlinha_comp_proj+1);  
                        //seta valores na jtable
                        TabelaCompVersProj.setValueAt(false, 0, 0);
                        TabelaCompVersProj.setValueAt(comp_vers_proj.getId_comp_versao(),totlinha_comp_proj,1);
                        TabelaCompVersProj.setValueAt(id_componente,totlinha_comp_proj,2);
                        TabelaCompVersProj.setValueAt(comp_vers_proj.getComponente(), totlinha_comp_proj, 3);
                        TabelaCompVersProj.setValueAt(comp_vers_proj.getId_moeda(), totlinha_comp_proj, 4);
                        TabelaCompVersProj.setValueAt(comp_vers_proj.getUnidade(), totlinha_comp_proj, 5);
                        if(comp_vers_proj.getValor_unit() > 0){
                            total = comp_vers_proj.getQntd_no_projeto() * comp_vers_proj.getValor_unit();
                            TabelaCompVersProj.setValueAt(conversoes.doubleParaObjectDecimalFormat(comp_vers_proj.getValor_unit()), totlinha_comp_proj, 6);
                        }else{
               
                            TabelaCompVersProj.setValueAt(conversoes.doubleParaObjectDecimalFormat(total_composicao), totlinha_comp_proj, 6);
                            total = comp_vers_proj.getQntd_no_projeto() * total_composicao;
                        }
                        total = dao_moeda.converteparaReais(total, comp_vers_proj.getId_moeda(), comp_vers_proj.getData_fornec());
                        TabelaCompVersProj.setValueAt(comp_vers_proj.getQntd_no_projeto(), totlinha_comp_proj, 7);
                        TabelaCompVersProj.setValueAt(conversoes.doubleParaObjectDecimalFormat(total), totlinha_comp_proj, 8);
                        TabelaCompVersProj.setValueAt(0, totlinha_comp_proj, 9);
                        TabelaCompVersProj.setValueAt(0, totlinha_comp_proj, 10);
                    }
                }
            }  
        } 
        //seta os valores totais nas jtfield de componentes eletronicos e mecanicos
        if(!JftTotalComp.getText().equals("")){
            total_comp = Double.parseDouble(JftTotalComp.getText().replace(".", "").replace(",", "."));
        }else{
            total_comp = 0.0;
        }
        if(comp_vers_proj.getTabela().getName().equals("jTBComponentesEletronicos")){
            total_eletronicos = calcula_total_componentes(comp_vers_proj.getTabela());
            JftTotalEletronico.setText(conversoes.doubleParaObjectDecimalFormat(total_eletronicos).toString());
        }else  if(comp_vers_proj.getTabela().getName().equals("jTBComponentesMecanicos")) {
            total_mecanicos = calcula_total_componentes(comp_vers_proj.getTabela());
            JftTotalMecanico.setText(conversoes.doubleParaObjectDecimalFormat(total_mecanicos).toString());
        }else{
            JftTotalMecanico.setText("0.00");
            JftTotalEletronico.setText("0.00");
            JftTotalComp.setText("0.00");
        }
        
        //pega os totais de cada tipo de componentes (Eletronicos e Mecanicos) calcula, e seta no total de componentes
        if(!JftTotalEletronico.getText().equals("")){
            total_eletronicos = Double.parseDouble(JftTotalEletronico.getText().replace(".", "").replace(",", "."));
        }else{
            total_eletronicos = 0.0;
        }
        
        if(!JftTotalMecanico.getText().equals("")){
            total_mecanicos = Double.parseDouble(JftTotalMecanico.getText().replace(".", "").replace(",", "."));
        }else{
            total_mecanicos = 0.0;
        }
        JftTotalComp.setText(conversoes.doubleParaObjectDecimalFormat(total_eletronicos + total_mecanicos).toString());
    }
    
    //metodo para converter para reais o total de todos componentes
    public void converteTotalComp(ComponenteVersaoProjeto comp_vers_proj, JTable Tabela_comp){
        
        DefaultTableModel tabela = (DefaultTableModel) Tabela_comp.getModel();
        int totlinha_comp = tabela.getRowCount();
        Double total_comp = 0.0;
        Object total_convertido = 0.0;
        Integer id_moeda;
        Integer id_comp_vers;
        Integer qntd_comp;
        Integer id_componente;
        Timestamp data_fornec;
        
        for (int i_comp = 0; i_comp < totlinha_comp; i_comp++){

            id_comp_vers = Integer.parseInt(Tabela_comp.getValueAt(i_comp, 1).toString());
            id_componente = Integer.parseInt(Tabela_comp.getValueAt(i_comp, 2).toString());
            id_moeda = Integer.parseInt(Tabela_comp.getValueAt(i_comp, 4).toString());
            qntd_comp = Integer.parseInt(Tabela_comp.getValueAt(i_comp, 7).toString());
            total_comp = Double.parseDouble(Tabela_comp.getValueAt(i_comp, 8).toString().replace(".", "").replace(",", "."));
            
            //retorna a data de fornecimento do componente
            comp_vers_proj.setId_comp_versao(id_comp_vers);
            comp_vers_proj.setId_componente(id_componente);
            componente.setId_componente(id_componente);
            
            data_fornec = dao_comp_fornec.retornaDataFornecimentoComponente(comp_vers_proj);

            //converte o valor em reais
            total_comp = dao_moeda.converteparaReais(total_comp, id_moeda, data_fornec);
            total_convertido = conversoes.doubleParaObjectDecimalFormat(total_comp);
            //seta na jtable o novo valor total
            tabela.setValueAt(total_convertido, i_comp, 8);
            
        }
    }

     //Método para calcular o custo do componente que possui composição
    public void atualizaQntdFornecComposicaoComponente(ComponenteVersaoProjeto componente, Integer qntd_inicial_comp){
       
        int resultado;
        Integer id_componente_composicao;
        Integer id_comp_Versao;
        Integer id_moeda;
        Integer qntd_para_composicao;
        Integer qntd_no_projeto;
        Integer qntd_para_projeto;
        Integer qntd_restante;
        Integer nova_qntd;
        Timestamp data_fornec;
        Double valor_unit = 0.0;
        Double total_composicao = 0.0;
        ResultSet result_composicao = null;
        ResultSet result_composicao_fornec = null;
        //faz a consulta de composição do componente
        conecta_banco.executeSQL("select * from composicao_componente where id_componente = "+componente.getId_componente());
        result_composicao = conecta_banco.resultset;
        try {
            while ( result_composicao.next()) {
                
                //armazena dados da composição
                id_componente_composicao = result_composicao.getInt("id_subcomponente");
                qntd_para_composicao = result_composicao.getInt("qntd");
                qntd_para_composicao = qntd_para_composicao * (componente.getQntd_no_projeto() - qntd_inicial_comp);
                componente.setId_componente(id_componente_composicao);
                
                //consulta todos os fornecimentos do componente na composição para o projeto
                conecta_banco.executeSQL("select * from componentes_fornecimento" 
                +" inner join fornecimento on (fornecimento.id_fornecimento = componentes_fornecimento.id_fornecimento)"
                +" inner join componentes_versao_projeto on (componentes_versao_projeto.id_comp_fornec = componentes_fornecimento.id_comp_fornec)"
                +" where componentes_versao_projeto.cod_vers_projeto = "+componente.getCod_vers_projeto()+" and componentes_versao_projeto.id_componente = "+id_componente_composicao+" and qntd_para_projeto - qntd_no_projeto  > 0;");
                
                result_composicao_fornec = conecta_banco.resultset;
                
                try {   
                    //percorre todos fornecimentos do componente em especifico
                   while(result_composicao_fornec.next()){
                       //armazena dados do componente para o projeto
                       id_comp_Versao = result_composicao_fornec.getInt("id_comp_versao");
                       qntd_no_projeto = result_composicao_fornec.getInt("qntd_no_projeto");
                       qntd_para_projeto = result_composicao_fornec.getInt("qntd_para_projeto");
                       
                       //armazena a quantidade que ainda não esta sendo utilizada no projeto
                       qntd_restante = qntd_para_projeto - qntd_no_projeto;
                       
                       //verifica se a quantidade que não esta sendo utilizada é maior que a quantidade que esta precisando para o projeto
                       if(qntd_restante >= qntd_para_composicao){
                           //se sim então adiciona para o projeto a quantidade necesseria 
                            nova_qntd = qntd_no_projeto  + qntd_para_composicao;
                            //atualiza no banco a quantidade
                            resultado = conecta_banco.executeSQL("UPDATE componentes_versao_projeto SET qntd_no_projeto = ? "
                            + "WHERE id_comp_versao = ? ",
                            nova_qntd,
                            id_comp_Versao);
                            
                            break;
                       }else{
                            //se não então utiliza todos componentes desse fornecimento e utiliza o restante de outros fornecimento que seja desse componente e para este projeto
                           
                           qntd_para_composicao = qntd_para_composicao - (qntd_para_projeto - qntd_no_projeto);
                           
                            resultado = conecta_banco.executeSQL("UPDATE componentes_versao_projeto SET qntd_no_projeto = ? "
                            + "WHERE id_comp_versao = ? ",
                            qntd_para_projeto,
                            id_comp_Versao);
                       }
                   }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Falha ao atualizar quantidade dos componentes na composição");
                }
                
                atualizaQntdFornecComposicaoComponente(componente, qntd_inicial_comp);
            }
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "Falha ao atualizar quantidade dos componentes na composição");
        }
       
        
        /*
        
          //Método para calcular o custo do componente que possui composição
    public void atualizaQntdFornecComposicaoComponente(ComponenteVersaoProjeto componente, Integer qntd_inicial_comp){
       
        int resultado;
        Integer id_componente_composicao;
        Integer id_comp_Versao;
        Integer id_moeda;
        Integer qntd_para_composicao;
        Integer qntd_removida;
        Integer qntd_para_remover = 0;
        Integer qntd_no_projeto;
        Integer qntd_para_projeto;
        Integer qntd_restante;
        Integer nova_qntd;
        Timestamp data_fornec;
        Double valor_unit = 0.0;
        Double total_composicao = 0.0;
        ResultSet result_composicao = null;
        ResultSet result_composicao_fornec = null;
        boolean inserir = false;
        boolean remover = false;
        //faz a consulta de composição do componente
        conecta_banco.executeSQL("select * from composicao_componente where id_componente = "+componente.getId_componente());
        result_composicao = conecta_banco.resultset;
        try {
            while ( result_composicao.next()) {
                
                //armazena dados da composição
                id_componente_composicao = result_composicao.getInt("id_subcomponente");
                qntd_para_composicao = result_composicao.getInt("qntd");
                
                if(qntd_inicial_comp > componente.getQntd_no_projeto()){
                    remover = true;
                    inserir = false;
                    qntd_removida = qntd_inicial_comp - componente.getQntd_no_projeto();
                    qntd_para_remover = qntd_removida * qntd_para_composicao;
                }else{
                    inserir = true;
                    remover = false;
                    qntd_para_composicao = qntd_para_composicao * (componente.getQntd_no_projeto() - qntd_inicial_comp);
                }
                
                componente.setId_componente(id_componente_composicao);
                
                //consulta todos os fornecimentos do componente na composição para o projeto
                conecta_banco.executeSQL("select * from componentes_fornecimento" 
                +" inner join fornecimento on (fornecimento.id_fornecimento = componentes_fornecimento.id_fornecimento)"
                +" inner join componentes_versao_projeto on (componentes_versao_projeto.id_comp_fornec = componentes_fornecimento.id_comp_fornec)"
                +" where componentes_versao_projeto.cod_vers_projeto = "+componente.getCod_vers_projeto()+" and componentes_versao_projeto.id_componente = "+id_componente_composicao+" and qntd_para_projeto - qntd_no_projeto  > 0;");
                
                result_composicao_fornec = conecta_banco.resultset;
                
                try {   
                    //percorre todos fornecimentos do componente em especifico
                   while(result_composicao_fornec.next()){
                       //armazena dados do componente para o projeto
                       id_comp_Versao = result_composicao_fornec.getInt("id_comp_versao");
                       qntd_no_projeto = result_composicao_fornec.getInt("qntd_no_projeto");
                       qntd_para_projeto = result_composicao_fornec.getInt("qntd_para_projeto");
                       
                       //se for para inserção de componente no projeto, então da baixa
                       if(inserir){
                           
                            //armazena a quantidade que ainda não esta sendo utilizada no projeto
                            qntd_restante = qntd_para_projeto - qntd_no_projeto;
                       
                            //verifica se a quantidade que não esta sendo utilizada é maior que a quantidade que esta precisando para o projeto
                            if(qntd_restante >= qntd_para_composicao){
                                //se sim então adiciona para o projeto a quantidade necesseria 
                                nova_qntd = qntd_no_projeto  + qntd_para_composicao;
                                //atualiza no banco a quantidade
                                resultado = conecta_banco.executeSQL("UPDATE componentes_versao_projeto SET qntd_no_projeto = ? "
                                + "WHERE id_comp_versao = ? ",
                                nova_qntd,
                                id_comp_Versao);
                                break;
                            }else{
                                //se não então utiliza todos componentes desse fornecimento e utiliza o restante de outros fornecimento que seja desse componente e para este projeto

                               qntd_para_composicao = qntd_para_composicao - (qntd_para_projeto - qntd_no_projeto);

                                resultado = conecta_banco.executeSQL("UPDATE componentes_versao_projeto SET qntd_no_projeto = ? "
                                + "WHERE id_comp_versao = ? ",
                                qntd_para_projeto,
                                id_comp_Versao);
                            }
                       }
                       //se for remoção de componente no projeto, então reintegra no estoque
                       if(remover){

                            //verifica se a quantidade que não esta sendo utilizada é maior que a quantidade que esta precisando para o projeto
                            if(qntd_no_projeto - qntd_para_remover >=0){
                                //se sim então adiciona para o projeto a quantidade necesseria 
                                nova_qntd = qntd_no_projeto  - qntd_para_remover;
                                //atualiza no banco a quantidade
                                resultado = conecta_banco.executeSQL("UPDATE componentes_versao_projeto SET qntd_no_projeto = ? "
                                + "WHERE id_comp_versao = ? ",
                                nova_qntd,
                                id_comp_Versao);
                                break;
                            }else{
                                //se não então utiliza todos componentes desse fornecimento e utiliza o restante de outros fornecimento que seja desse componente e para este projeto
                               qntd_para_remover = qntd_para_remover - qntd_no_projeto;
                               
                                resultado = conecta_banco.executeSQL("UPDATE componentes_versao_projeto SET qntd_no_projeto = ? "
                                + "WHERE id_comp_versao = ? ",
                                0,
                                id_comp_Versao);
                            }
                           
                       }
                   }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Falha ao atualizar quantidade dos componentes na composição");
                }
                
                atualizaQntdFornecComposicaoComponente(componente, qntd_inicial_comp);
            }
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "Falha ao atualizar quantidade dos componentes na composição");
        }
       
    }

        
        */
    }
    
    //metodo para calcular o total de componentes na jtable
    public double calcula_total_componentes(JTable Tabela_comp){
        int totlinha_comp = Tabela_comp.getRowCount();
        Double total_comp = 0.0;
        Double total = 0.0;
        Integer id_moeda;
        for (int i_comp = 0; i_comp < totlinha_comp; i_comp++){
            id_moeda = Integer.parseInt(Tabela_comp.getValueAt(i_comp, 4).toString());
            total_comp = Double.parseDouble(Tabela_comp.getValueAt(i_comp, 8).toString().replace(".", "").replace(",", "."));
           // total_comp = dao_moeda.converteparaReais(total_comp, id_moeda,null);
            total = total + total_comp;
        }
       return total;
    }
    
     //Método para remover um registro da Jtable
    public void removeItensAtulizaTotal(JTable jtable, int situacao, JFormattedTextField JTFTotalComp,JFormattedTextField JTFTotal) {
        
        DefaultTableModel tabela = (DefaultTableModel) jtable.getModel();
        int totlinha = tabela.getRowCount();
        int totcolun = tabela.getColumnCount();
        Boolean sel = false;
        Double valor_remover = 0.0;
        Double total_remover = 0.0;
        Double valor_total_comp = Double.parseDouble(JTFTotalComp.getText().replace(".", "").replace(",", "."));
        Double valor_total = Double.parseDouble(JTFTotal.getText().replace(".", "").replace(",", "."));;
        Boolean achou = false;
        
        int opcao = JOptionPane.showConfirmDialog(null, "Deseja remover os registros selecionados? ",
                "remover",
                JOptionPane.YES_NO_OPTION);
        if (opcao == JOptionPane.YES_OPTION) {
        //Percorre linhas da jtable
        for(int i = totlinha-1; i >= 0; i--){

            sel = (Boolean) tabela.getValueAt(i, 0);
            valor_remover = Double.parseDouble(tabela.getValueAt(i, 8).toString().replace(".", "").replace(",", "."));
            Integer old_qntd = Integer.parseInt(tabela.getValueAt(i, 10).toString());
            
            //Se a linha estiver selecionada
            if(sel != null){
                if(sel == true){
                    
                    total_remover = total_remover + valor_remover;
                    
                    //ativa flag que achou uma linha selecionada
                    achou = true;
                    if((situacao == Rotinas.ALTERAR || situacao == Rotinas.TODOS) && old_qntd > 0){
                        //seta o valor 1 na coluna excluido da jtable
                        jtable.setValueAt(1, i, 9);
                        jtable.setValueAt(false, i, 0);
                        //habilita e desabilita para atualizar o jtable (caso contrario pinta de vermelho só quando clica na linha)
                        jtable.setEnabled(false);
                        jtable.setEnabled(true);
                    }else{
                        tabela.removeRow(i);
                    }
                }
            }
        }
        JTFTotalComp.setText(conversoes.doubleParaObjectDecimalFormat(valor_total_comp - total_remover).toString());
        JTFTotal.setText(conversoes.doubleParaObjectDecimalFormat(valor_total - total_remover).toString());
        if(achou == false){
            JOptionPane.showMessageDialog(null, "Nehuma linha selecionada!");
        }
        }
    }
    
    public Integer retornaQntdParaProjeto(ComponenteVersaoProjeto componente){
        Integer qntd_para_projeto = 0;
        String sql = "select * from componentes_versao_projeto where id_comp_versao = "+ componente.getId_comp_versao();
           
        conecta_banco.executeSQL(sql);
        try {        
            conecta_banco.resultset.first();
            qntd_para_projeto = conecta_banco.resultset.getInt("qntd_para_projeto");

            return qntd_para_projeto;
           
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao retornar quantidade fornecida do componente");
        }
       return 0;
    }
    
    public Integer retornaQntdNoProjeto(ComponenteVersaoProjeto componente){
        Integer qntd_no_projeto = 0;
        String sql = "select * from componentes_versao_projeto where id_comp_versao = "+ componente.getId_comp_versao();
           
        conecta_banco.executeSQL(sql);
        try {        
            conecta_banco.resultset.first();
            qntd_no_projeto = conecta_banco.resultset.getInt("qntd_no_projeto");

            return qntd_no_projeto;
           
        } catch (SQLException ex) {
            
        }
       return 0;
    }
    
    //método para atualizar o valor unitario de todos componentes que possuem o componente na composição, ou seja o componente que foi alterado.
    public void atualizaValorUnitCompPai(ComponenteVersaoProjeto compVersProj){
        ResultSet composicao;
        ResultSet comp_fornec;
        
        Integer id_comp_fornec;
        Integer cod_vers_proj;

        
        //lista todos componentes pai que possuem o componente na composição
        Double valor_composicao;
        conecta_banco.executeSQL("select * from composicao_componente where id_subcomponente = "+compVersProj.getId_componente()); 
        composicao =  conecta_banco.resultset;
        try {     
            
            while ( composicao.next()) {
                
                Integer id_componente_pai = composicao.getInt("id_componente");

                //lista todos fornecimentos do componente pai
                conecta_banco.executeSQL("select * from componentes_fornecimento"
                +" inner join componentes_versao_projeto on (componentes_fornecimento.id_comp_fornec = componentes_versao_projeto.id_comp_fornec)"
                +" where componentes_fornecimento.id_componente = "+id_componente_pai); 
                comp_fornec =  conecta_banco.resultset;

                //percorre todos fornecimento do componente pai
                while ( comp_fornec.next()) {
                   
                    cod_vers_proj = comp_fornec.getInt("cod_vers_projeto");
                    id_comp_fornec = comp_fornec.getInt("id_comp_fornec");
                    compVersProj.setCod_vers_projeto(cod_vers_proj);
                    compVersProj.setId_componente(id_componente_pai);
                    
                    valor_composicao = dao_componente.calculaComposicaoComponente(compVersProj);
                    /*
                    JOptionPane.showMessageDialog(null, "id_comp_fornec "+id_comp_fornec);
                    JOptionPane.showMessageDialog(null, "valor "+valor_composicao);
                    */
                    //atualiza valor unitario do componente pai
                    conecta_banco.executeSQL("UPDATE componentes_fornecimento SET valor_unit = ? "
                    + "WHERE id_comp_fornec = ? ",
                    valor_composicao,
                    id_comp_fornec);          
                }
            }
           
        } catch (SQLException ex) {
          JOptionPane.showMessageDialog(null, "Falha");
        }               
    }
}

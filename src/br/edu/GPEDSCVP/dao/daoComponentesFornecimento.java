/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.GPEDSCVP.dao;

import br.edu.GPEDSCVP.classe.ComponenteFornecimento;
import br.edu.GPEDSCVP.classe.ComponenteVersaoProjeto;
import br.edu.GPEDSCVP.conexao.ConexaoBanco;
import br.edu.GPEDSCVP.util.FormatarData;
import br.edu.GPEDSCVP.util.Rotinas;
import br.edu.GPEDSCVP.util.UltimaSequencia;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
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
    daoMoeda dao_moeda = new daoMoeda();
    FormatarData data = new FormatarData();
    
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
        
        DecimalFormat dFormat = new DecimalFormat("#,###,##0.00") ;
        Boolean sel = false;
        Double valor_unit;
        Object valor_unit_comp;
        Double total;
        Object valor_total_comp;
        Integer id_fornecidos;
      
        int totlinha_comp_fornec = TabelaComponentesFornecidos.getRowCount();
       
        //se não possuir linhas
        if(totlinha_comp_fornec == 0){
            //add a primeira linha
            TabelaComponentesFornecidos.setNumRows(1);  
            id_fornecidos = ultima.ultimasequencia("COMPONENTES_FORNECIMENTO","ID_COMP_FORNEC"); 
            //formata valores monetários 
            valor_unit = componente.getValor_unit();
            valor_unit_comp = dFormat.format(valor_unit);
            //calcula total
            total = componente.getValor_unit() * componente.getQntd_componente();
            valor_total_comp = dFormat.format(total);
            //seta valores na jtable
            TabelaComponentesFornecidos.setValueAt(false, 0, 0);
            TabelaComponentesFornecidos.setValueAt(id_fornecidos, 0,1);
            TabelaComponentesFornecidos.setValueAt(componente.getId_componente(), 0, 2);
            TabelaComponentesFornecidos.setValueAt(componente.getDescricao(), 0, 3);
            TabelaComponentesFornecidos.setValueAt(valor_unit_comp, 0, 4);
            TabelaComponentesFornecidos.setValueAt(componente.getId_moeda(), 0, 5);
            TabelaComponentesFornecidos.setValueAt(componente.getMoeda(), 0, 6);
            TabelaComponentesFornecidos.setValueAt(componente.getQntd_componente(), 0, 7);
            TabelaComponentesFornecidos.setValueAt(componente.getQntd_componente(), 0, 8);
            TabelaComponentesFornecidos.setValueAt(valor_total_comp, 0, 9);
            TabelaComponentesFornecidos.setValueAt(0, 0, 10);
                        
        }else{
           // totlinha_comp_fornec = componente.getTabela().getRowCount();
            for (int i_comp = 0; i_comp < totlinha_comp_fornec; i_comp++){
                //Chegou na ultima linha
                if( i_comp == totlinha_comp_fornec-1){
                    
                     //adiciona uma linha a mais
                    TabelaComponentesFornecidos.setNumRows(totlinha_comp_fornec+1);  

                    //se estiver em modo de alteração
                    if(situacao == Rotinas.ALTERAR){
                        //gera ultimo id baseado no banco
                        id_fornecidos = ultima.ultimasequencia("COMPONENTES_FORNECIMENTO","ID_COMP_FORNEC"); 
                        
                        if(id_fornecidos > Integer.parseInt(TabelaComponentesFornecidos.getValueAt(totlinha_comp_fornec-1, 1).toString())){
                            TabelaComponentesFornecidos.setValueAt(id_fornecidos, totlinha_comp_fornec,1);
                        }else{
                             id_fornecidos = Integer.parseInt(TabelaComponentesFornecidos.getValueAt(totlinha_comp_fornec-1, 1).toString());
                             TabelaComponentesFornecidos.setValueAt(id_fornecidos+1, totlinha_comp_fornec,1);
                        }
                    }else{
                        //gera o ultimo id baseado no ultimo registro da jtable
                        id_fornecidos = (Integer.parseInt(TabelaComponentesFornecidos.getValueAt(totlinha_comp_fornec-1, 1).toString())+1);
                        TabelaComponentesFornecidos.setValueAt(id_fornecidos, totlinha_comp_fornec,1);
                    }
                    
                    //formata valores monetários 
                    valor_unit = componente.getValor_unit();
                    valor_unit_comp = dFormat.format(valor_unit);
                    //calcula total
                    total = componente.getValor_unit() * componente.getQntd_componente();
                    valor_total_comp = dFormat.format(total);
            
                     //seta valores na jtable
                    TabelaComponentesFornecidos.setValueAt(false, totlinha_comp_fornec, 0);
                    TabelaComponentesFornecidos.setValueAt(componente.getId_componente(), totlinha_comp_fornec, 2);
                    TabelaComponentesFornecidos.setValueAt(componente.getDescricao(), totlinha_comp_fornec, 3);
                    TabelaComponentesFornecidos.setValueAt(valor_unit_comp, totlinha_comp_fornec, 4);
                    TabelaComponentesFornecidos.setValueAt(componente.getId_moeda(), totlinha_comp_fornec, 5);
                    TabelaComponentesFornecidos.setValueAt(componente.getMoeda(), totlinha_comp_fornec, 6);
                    TabelaComponentesFornecidos.setValueAt(componente.getQntd_componente(), totlinha_comp_fornec, 7);
                    TabelaComponentesFornecidos.setValueAt(componente.getQntd_componente(), totlinha_comp_fornec, 8);
                    TabelaComponentesFornecidos.setValueAt(valor_total_comp, totlinha_comp_fornec, 9);
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
                    
            conecta_banco.executeSQL("INSERT INTO componentes_fornecimento (id_comp_fornec,id_componente, id_fornecimento, id_moeda, qntd_componente, valor_unit, data_alter, in_ativo) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?) ",
            id_comp_fornec,
            id_componente,
            id_fornecimento,
            id_moeda,
            qntd,
            valor_unit,
            FormatarData.dateParaTimeStamp(componentes.getData_alter()),
            "A");
        }
     }
     //Verifica se todos componentes fornecidos foram destinados a uma projeto
     public boolean verificaCompFornec (JTable componentes){
        
        Integer qntd;
        Integer exc;
        Double valor_unit;

        DefaultTableModel tabela = (DefaultTableModel) componentes.getModel();
        int totlinha = tabela.getRowCount();
        for (int i = 0; i < totlinha; i++){
            qntd = Integer.parseInt(tabela.getValueAt(i, 8).toString());
            exc = Integer.parseInt(tabela.getValueAt(i, 10).toString());
            if(qntd >0 && exc == 0){
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
                           
                            for(int i_atualiza = totlinha_atualiza-1; i_atualiza >= 0; i_atualiza--){
                                if(Integer.parseInt(tabela_atualiza.getValueAt(i_atualiza, 7).toString()) == id_componente){
                                    tabela_atualiza.setValueAt(1, i_atualiza, 10); 
                                }
                            }
                            
                            //habilita e desabilita para atualizar o jtable (caso contrario pinta de vermelho só quando clica na linha)
                            jtable.setEnabled(false);
                            jtable_atualiza.setEnabled(false);
                            jtable.setEnabled(true);
                            jtable_atualiza.setEnabled(true);
                            
                        }else{
                            tabela.removeRow(i);
                            for(int i_atualiza = totlinha_atualiza-1; i_atualiza >= 0; i_atualiza--){
                                if(tabela_atualiza.getValueAt(i_atualiza, 7) == id_componente){
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
    
    //Consulta pelo codigo de componentes mecânicos
    public void consultaCompFornec(ComponenteFornecimento componente){
        conecta_banco.executeSQL("select null, componentes_fornecimento.id_comp_fornec,componentes_fornecimento.id_fornecimento,componentes_fornecimento.id_componente,"
                                + "componente.descricao, componente.tipo," 
                                +" componentes_fornecimento.id_moeda, moeda.unidade,componentes_fornecimento.valor_unit,null,qntd_componente,"
                                +" 0 qntd_restante,componentes_fornecimento.valor_unit * qntd_componente total,componentes_fornecimento.data_alter,componentes_fornecimento.in_ativo,false from componentes_fornecimento"
                                +" inner join componente on (componente.id_componente = componentes_fornecimento.id_componente)" 
                                +" inner join moeda on (moeda.id_moeda = componentes_fornecimento.id_moeda)"
                                +" where id_fornecimento = "+componente.getId_fornecimento()+" and componentes_fornecimento.in_ativo = 'A' group by (componentes_fornecimento.id_comp_fornec)");
        
                                componente.setRetorno(conecta_banco.resultset);
    }
    
     public void calculaImpostoUnit (ComponenteFornecimento componentes, JTable tabela_fornec){
        
        DecimalFormat dFormat = new DecimalFormat("#,###,##0.00") ;
        Double valor_frete;
        Double valor_imposto;
        Integer id_moeda_frete;
        Integer id_moeda_imp;
        Double total_reais = 0.0;
        Object imposto_unit;
        String data_fornec;
        

        DefaultTableModel tabela_componentes = (DefaultTableModel) componentes.getTabela().getModel();
        DefaultTableModel tabela_fornecimento = (DefaultTableModel) tabela_fornec.getModel();
        
        int totlinha_fornec = tabela_fornecimento.getRowCount();
        int totlinha_comp = tabela_componentes.getRowCount();
        
        int linha = tabela_fornec.getSelectedRow();

        valor_frete = Double.parseDouble(tabela_fornecimento.getValueAt(linha, 6).toString().replace(".", "").replace(",", "."));
        id_moeda_frete = Integer.parseInt(tabela_fornecimento.getValueAt(linha, 4).toString());
        valor_imposto = Double.parseDouble(tabela_fornecimento.getValueAt(linha, 9).toString().replace(".", "").replace(",", "."));
        id_moeda_imp = Integer.parseInt(tabela_fornecimento.getValueAt(linha, 7).toString());
        data_fornec = tabela_fornecimento.getValueAt(linha, 10).toString().toString();
       
        total_reais = total_reais + dao_moeda.converteparaReais(valor_frete, id_moeda_frete,data.stringParaTimeStamp(data_fornec));
        total_reais = total_reais + dao_moeda.converteparaReais(valor_imposto, id_moeda_imp,data.stringParaTimeStamp(data_fornec));

        imposto_unit =  dFormat.format(total_reais / totlinha_comp);
        
        for (int i = 0; i < totlinha_comp; i++){
            tabela_componentes.setValueAt(imposto_unit, i, 7);
        } 
     }
     
     public void calculaTotalCompFornec (ComponenteFornecimento componentes, JTable tabela_fornec){
        
        DecimalFormat dFormat = new DecimalFormat("#,###,##0.00") ;
        Integer id_moeda_valor_unit = 0;
        Double valor_unitario = 0.0;
        Double imposto_unitario = 0.0;
        Integer qntd = 0;
        Double total = 0.0;
        Object total_comp_fornec;
        String data_fornec;
        

        DefaultTableModel tabela_componentes = (DefaultTableModel) componentes.getTabela().getModel();
        DefaultTableModel tabela_fornecimento = (DefaultTableModel) tabela_fornec.getModel();
        
        int linha = tabela_fornec.getSelectedRow();

        data_fornec = tabela_fornecimento.getValueAt(linha, 10).toString().toString();
        
        int totlinha_comp = tabela_componentes.getRowCount();

        for (int i = 0; i < totlinha_comp; i++){
            
            id_moeda_valor_unit = Integer.parseInt(tabela_componentes.getValueAt(i, 4).toString());
            
            valor_unitario = Double.parseDouble(tabela_componentes.getValueAt(i, 6).toString().replace(".", "").replace(",", "."));
            imposto_unitario = Double.parseDouble(tabela_componentes.getValueAt(i, 7).toString().replace(".", "").replace(",", "."));
            qntd = Integer.parseInt(tabela_componentes.getValueAt(i, 8).toString());
           
            // converte para reais
            total = total + dao_moeda.converteparaReais(valor_unitario, id_moeda_valor_unit,data.stringParaTimeStamp(data_fornec));
            // multiplica pela quantidade fornecida
            total = total * qntd;
            //acrescenta mais o imposto unitário
            total = total + imposto_unitario;

            total_comp_fornec =  dFormat.format(total);
            
            tabela_componentes.setValueAt(total_comp_fornec, i, 9);
            
            //reseta valores
            total = 0.0;
            total_comp_fornec = 0;
        } 
     }
     
      public Object calculaTotalFornec (ComponenteFornecimento componentes){
        
        DecimalFormat dFormat = new DecimalFormat("#,###,##0.00") ;
        Double total = 0.0;
        Object total_fonec;

        DefaultTableModel tabela_componentes = (DefaultTableModel) componentes.getTabela().getModel();
        
        int totlinha_comp = tabela_componentes.getRowCount();

        for (int i = 0; i < totlinha_comp; i++){
           
            total = total + Double.parseDouble(tabela_componentes.getValueAt(i, 9).toString().replace(".", "").replace(",", "."));
        }
        total_fonec = dFormat.format(total);
        total = 0.0;
        return total_fonec;
        
     }
      
     public void alterarCompFornec (ComponenteFornecimento compFornec){
        
        Integer id_fornecidos;
        Integer id_componente;
        Double valor_unit;
        Integer id_moeda;
        Integer qntd_fornec;
        
        
        DefaultTableModel tabela = (DefaultTableModel) compFornec.getTabela().getModel();
        
        int totlinha = tabela.getRowCount();
        
        for (int i = 0; i < totlinha; i++){
            id_fornecidos = Integer.parseInt(tabela.getValueAt(i, 1).toString());
            id_componente = Integer.parseInt(tabela.getValueAt(i, 2).toString());
            valor_unit = Double.parseDouble(tabela.getValueAt(i, 4).toString().replace(",", "."));
            id_moeda = Integer.parseInt(tabela.getValueAt(i, 5).toString());
            qntd_fornec = Integer.parseInt(tabela.getValueAt(i, 7).toString());
            Integer exc = Integer.parseInt(tabela.getValueAt(i, 10).toString());

            //Verifica se já existe o fornecimento deste componente cadastrado
            String sql = "select * from componentes_fornecimento where id_comp_fornec = "+ id_fornecidos;
            try {
                conecta_banco.executeSQL(sql);
                conecta_banco.resultset.first();
                //se já existe o fornecimento deste componentes
                if(id_fornecidos == conecta_banco.resultset.getInt("id_comp_fornec")){
                    //apenas altera 
                    conecta_banco.executeSQL("UPDATE componentes_fornecimento SET id_componente = ?, id_moeda = ?, qntd_componente = ?, valor_unit = ?, data_alter = ? "
                    + "WHERE id_comp_fornec = ? ",
                    id_componente,
                    id_moeda,
                    qntd_fornec,
                    valor_unit,
                    FormatarData.dateParaTimeStamp(compFornec.getData_alter()),
                    id_fornecidos); 
                }

            } catch (Exception e) {
                //Chegou aqui porque o fornecimento do componente não existe, então inclui
               
                conecta_banco.executeSQL("INSERT INTO componentes_fornecimento (id_comp_fornec,id_componente, id_fornecimento, id_moeda, qntd_componente, valor_unit, data_alter, in_ativo) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?) ",
                id_fornecidos,
                id_componente,
                compFornec.getId_fornecimento(),
                id_moeda,
                qntd_fornec,
                valor_unit,
                FormatarData.dateParaTimeStamp(compFornec.getData_alter()),
                "A");
            }
            
            //Se for um registro excluido da Jtable
            if(exc == 1){
                //Inativa o fornecimento do componente
                conecta_banco.atualizarSQL("UPDATE COMPONENTES_FORNECIMENTO SET IN_ATIVO = 'I'"
                                         + " WHERE ID_COMP_FORNEC = " + id_fornecidos);
            }
        }
    }
     
    //metodo para retornar a data que foi fornecida um determinado componente
    public Timestamp retornaDataFornecimentoComponente(ComponenteVersaoProjeto componente){
        conecta_banco.executeSQL("select * from componentes_fornecimento" 
        + " inner join fornecimento on (fornecimento.id_fornecimento = componentes_fornecimento.id_fornecimento)"
        + " inner join componentes_versao_projeto on (componentes_versao_projeto.id_comp_fornec = componentes_fornecimento.id_comp_fornec)" 
        + " where componentes_versao_projeto.id_comp_versao = "+componente.getId_comp_versao()+" and componentes_fornecimento.in_ativo = 'A'");
        
        try {        
           conecta_banco.resultset.first();
           Timestamp data_fornec = conecta_banco.resultset.getTimestamp("data_cadastro");
           return data_fornec;
        } catch (SQLException ex) {
           return null;
        }                     
    }
}

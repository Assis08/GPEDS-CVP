/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.GPEDSCVP.dao;

import br.edu.GPEDSCVP.classe.Componente;
import br.edu.GPEDSCVP.classe.ComponenteVersaoProjeto;
import br.edu.GPEDSCVP.classe.ComposicaoComponente;
import br.edu.GPEDSCVP.classe.Fornecedor;
import br.edu.GPEDSCVP.classe.FornecedoresComponente;
import br.edu.GPEDSCVP.classe.VersaoProjeto;
import br.edu.GPEDSCVP.conexao.ConexaoBanco;
import br.edu.GPEDSCVP.util.Conversoes;
import br.edu.GPEDSCVP.util.ExcessaoBanco;
import br.edu.GPEDSCVP.util.FormatarData;
import br.edu.GPEDSCVP.util.Rotinas;
import br.edu.GPEDSCVP.util.UltimaSequencia;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Willys
 */
public class daoComponente {
    
    ConexaoBanco conecta_banco;
    UltimaSequencia ultima;
    daoMoeda dao_moeda = new daoMoeda();
    Conversoes conversoes = new Conversoes();
    
    public daoComponente()
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
    
    //Método de incluir componente no banco
    public boolean incluir(Componente componente, String tipo)throws SQLException
    {
        //Insert de componente
        ultima = new UltimaSequencia();
        int resultado;
        //se for eletrônico
        if (tipo.equals("Eletrônico")){
            //se não possuir datasheet
            if (componente.getId_datasheet() == 0){
                int sequencia = (Integer) (ultima.ultimasequencia("COMPONENTE","ID_COMPONENTE"));

                resultado = conecta_banco.executeSQL("INSERT INTO componente (id_componente , descricao, tipo, revisao, data_cadastro, data_alter, in_ativo ) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?) ",
                sequencia,
                componente.getDescricao(),
                componente.getTipo(),
                componente.getRevisao(),
                FormatarData.dateParaSQLDate(componente.getData_cadastro()),
                FormatarData.dateParaTimeStamp(componente.getData_alter()),
                "A");
            }else{
                //se possuir datasheet
                int sequencia = (Integer) (ultima.ultimasequencia("COMPONENTE","ID_COMPONENTE"));

                resultado = conecta_banco.executeSQL("INSERT INTO componente (id_componente, id_datasheet, descricao, tipo, revisao, data_cadastro, data_alter, in_ativo) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?) ",
                sequencia,
                componente.getId_datasheet(),
                componente.getDescricao(),
                componente.getTipo(),
                componente.getRevisao(),
                FormatarData.dateParaSQLDate(componente.getData_cadastro()),
                FormatarData.dateParaTimeStamp(componente.getData_alter()),
                "A");
            }
        //se for mecânico  
        }else{
            //se não possuir datasheet
            if (componente.getId_datasheet() == 0){
                
                int sequencia = (Integer) (ultima.ultimasequencia("COMPONENTE","ID_COMPONENTE"));
                // se não possuir material
                if(componente.getId_material() == 0){
                    
                    resultado = conecta_banco.executeSQL("INSERT INTO componente (id_componente , descricao, tipo, revisao, data_cadastro, data_alter, in_ativo) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?) ",
                    sequencia,
                    componente.getDescricao(),
                    componente.getTipo(),
                    componente.getRevisao(),
                    FormatarData.dateParaSQLDate(componente.getData_cadastro()),
                    FormatarData.dateParaTimeStamp(componente.getData_alter()),
                    "A");
                }else{
                    //se possuir material
                    resultado = conecta_banco.executeSQL("INSERT INTO componente (id_componente , descricao, tipo, revisao, id_material, data_cadastro, data_alter, in_ativo) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?) ",
                    sequencia,
                    componente.getDescricao(),
                    componente.getTipo(),
                    componente.getRevisao(),
                    componente.getId_material(),
                    FormatarData.dateParaSQLDate(componente.getData_cadastro()),
                    FormatarData.dateParaTimeStamp(componente.getData_alter()),
                    "A");
                }

            }else{
                //se possuir datasheet
                int sequencia = (Integer) (ultima.ultimasequencia("COMPONENTE","ID_COMPONENTE"));
                
                if(componente.getId_material() == 0){
                    //se não possuir material
                    
                    resultado = conecta_banco.executeSQL("INSERT INTO componente (id_componente, id_datasheet, descricao, tipo, revisao, data_cadastro, data_alter, in_ativo) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?) ",
                    sequencia,
                    componente.getId_datasheet(),
                    componente.getDescricao(),
                    componente.getTipo(),
                    componente.getRevisao(),
                    FormatarData.dateParaSQLDate(componente.getData_cadastro()),
                    FormatarData.dateParaTimeStamp(componente.getData_alter()),
                    "A");
                }else{
                    //se possuir material 
                    
                    resultado = conecta_banco.executeSQL("INSERT INTO componente (id_componente, id_datasheet, descricao, tipo, revisao, id_material, data_cadastro, data_alter, in_ativo) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) ",
                    sequencia,
                    componente.getId_datasheet(),
                    componente.getDescricao(),
                    componente.getTipo(),
                    componente.getRevisao(),
                    componente.getId_material(),
                    FormatarData.dateParaSQLDate(componente.getData_cadastro()),
                    FormatarData.dateParaTimeStamp(componente.getData_alter()),
                    "A");
                }
            }
        }
        
        if(resultado == ExcessaoBanco.ERRO_LIMITE_CARACTERES){
            return false;
        }else if(resultado == ExcessaoBanco.OUTROS_ERROS){
            return false;
        }else if (resultado == ExcessaoBanco.ERRO_LIMITE_ARQUIVO){
            return false;
        }
        return true;    
    }

    //Método de alterar componente no banco
    public boolean alterar(Componente componente, String tipo)throws SQLException
    {
        //Alterar componente
        ultima = new UltimaSequencia();
        int resultado;
        //se for eletrônico
        if (tipo.equals("E")){
            //se não possuir datasheet
            if (componente.getId_datasheet() == 0){
                resultado = conecta_banco.executeSQL("UPDATE componente SET id_datasheet = ?, descricao = ?, tipo = ?, revisao = ?, id_material = ?, data_cadastro = ?, data_alter = ? "
                + "WHERE id_componente = ? ",
                null,
                componente.getDescricao(),
                componente.getTipo(),
                componente.getRevisao(),
                null,
                FormatarData.dateParaSQLDate(componente.getData_cadastro()),
                FormatarData.dateParaTimeStamp(componente.getData_alter()),
                componente.getId_componente());
            }else{
                //se possuir datasheet
                resultado = conecta_banco.executeSQL("UPDATE componente SET id_datasheet = ?, descricao = ?, tipo = ?, revisao = ?, id_material = ?, data_cadastro = ?, data_alter = ? "
                + "WHERE id_componente = ? ",
                componente.getId_datasheet(),
                componente.getDescricao(),
                componente.getTipo(),
                componente.getRevisao(),
                null,
                FormatarData.dateParaSQLDate(componente.getData_cadastro()),
                FormatarData.dateParaTimeStamp(componente.getData_alter()),
                componente.getId_componente());
            }
        //se for mecânico  
        }else {
            //se não possuir datasheet
            if (componente.getId_datasheet() == 0){
                
                // se não possuir material
                if(componente.getId_material() == 0){
                    resultado = conecta_banco.executeSQL("UPDATE componente SET id_datasheet = ?, descricao = ?, tipo = ?, revisao = ?, id_material = ?, data_cadastro = ?, data_alter = ? "
                    + "WHERE id_componente = ? ",
                    null,
                    componente.getDescricao(),
                    componente.getTipo(),
                    componente.getRevisao(),
                    null,
                    FormatarData.dateParaSQLDate(componente.getData_cadastro()),
                    FormatarData.dateParaTimeStamp(componente.getData_alter()),
                    componente.getId_componente());
                }else{
                    //se possuir material
                    resultado = conecta_banco.executeSQL("UPDATE componente SET id_datasheet = ?, descricao = ?, tipo = ?, revisao = ?, id_material = ?, data_cadastro = ?, data_alter = ? "
                    + "WHERE id_componente = ? ",
                    null,
                    componente.getDescricao(),
                    componente.getTipo(),
                    componente.getRevisao(),
                    componente.getId_material(),
                    FormatarData.dateParaSQLDate(componente.getData_cadastro()),
                    FormatarData.dateParaTimeStamp(componente.getData_alter()),
                    componente.getId_componente());
                }

            }else{
                //se possuir datasheet

                if(componente.getId_material() == 0){
                    //se não possuir material
                    
                    resultado = conecta_banco.executeSQL("UPDATE componente SET id_datasheet = ?, descricao = ?, tipo = ?, revisao = ?, id_material = ?, data_cadastro = ?, data_alter = ? "
                    + "WHERE id_componente = ? ",
                    componente.getId_datasheet(),
                    componente.getDescricao(),
                    componente.getTipo(),
                    componente.getRevisao(),
                    null,
                    FormatarData.dateParaSQLDate(componente.getData_cadastro()),
                    FormatarData.dateParaTimeStamp(componente.getData_alter()),
                    componente.getId_componente());
                }else{
                    //se possuir material 
                    
                    resultado = conecta_banco.executeSQL("UPDATE componente SET id_datasheet = ?, descricao = ?, tipo = ?, revisao = ?, id_material = ?, data_cadastro = ?, data_alter = ? "
                    + "WHERE id_componente = ? ",
                    componente.getId_datasheet(),
                    componente.getDescricao(),
                    componente.getTipo(),
                    componente.getRevisao(),
                    componente.getId_material(),
                    FormatarData.dateParaSQLDate(componente.getData_cadastro()),
                    FormatarData.dateParaTimeStamp(componente.getData_alter()),
                    componente.getId_componente());
                }
            }
        }
        
        if(resultado == ExcessaoBanco.ERRO_LIMITE_CARACTERES){
            return false;
        }else if(resultado == ExcessaoBanco.OUTROS_ERROS){
            return false;
        }else if (resultado == ExcessaoBanco.ERRO_LIMITE_ARQUIVO){
            return false;
        }
        return true;    
    }
    
    public void gravarComposicao (ComposicaoComponente composicao){
        Integer id_subcomponente;
        Integer id_composicao;
        String tipo;
        String descricao;
        Integer qntd;
        
        DefaultTableModel tabela = (DefaultTableModel) composicao.getTabela().getModel();
        int totlinha = tabela.getRowCount();
        for (int i = 0; i < totlinha; i++){
            
            id_composicao = Integer.parseInt(tabela.getValueAt(i, 1).toString());
            id_subcomponente = Integer.parseInt(tabela.getValueAt(i, 2).toString());
            tipo = tabela.getValueAt(i, 3).toString();
            descricao = tabela.getValueAt(i, 4).toString();
            qntd = Integer.parseInt(tabela.getValueAt(i, 7).toString());
            
                    
            //int sequencia = (Integer) (ultima.ultimasequencia("COMPOSICAO_COMPONENTE","ID_SUBCOMPONENTE"));
            conecta_banco.executeSQL("INSERT INTO composicao_componente (id_composicao,id_subcomponente, id_componente, qntd, estado,data_cadastro,data_alter) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?) ",
            id_composicao,
            id_subcomponente,
            composicao.getId_componente(),
            qntd,
            "A",
            FormatarData.dateParaSQLDate(composicao.getData_cadastro()),
            FormatarData.dateParaTimeStamp(composicao.getData_alter()));
        }
     }
    
    public void gravarFornecedores (FornecedoresComponente fornecedor){
        Integer id_funcionario;
        Integer id_componente;
     
        
        DefaultTableModel tabela = (DefaultTableModel) fornecedor.getTabela().getModel();
        int totlinha = tabela.getRowCount();
        for (int i = 0; i < totlinha; i++){
            
            id_componente = fornecedor.getId_componente();
            id_funcionario = Integer.parseInt(tabela.getValueAt(i, 2).toString());
         
            int sequencia = (Integer) (ultima.ultimasequencia("FORNECEDOR_COMPONENTE","ID_FORNECEDORES_COMP"));
            conecta_banco.executeSQL("INSERT INTO fornecedor_componente (id_fornecedores_comp, id_pessoa, id_componente,data_alter,data_cadastro,in_ativo) "
            + "VALUES (?, ?, ?, ?, ?, ?) ",
            sequencia,
            id_funcionario,
            id_componente,
            FormatarData.dateParaTimeStamp(fornecedor.getData_alter()),
            FormatarData.dateParaSQLDate(fornecedor.getData_cadastro()),
            "A");
        }
     }
    
    //Consulta pelo codigo de componentes seus fornecedores
    public void consultaCodigoComponente(ComposicaoComponente composicao){
        conecta_banco.executeSQL("select id_subcomponente, tipo, componente.descricao, material.id_material, material.descricao, qntd from composicao_componente" +
                                " inner join componente on (componente.id_componente = composicao_componente.id_subcomponente)" +
                                " left join material on (componente.id_material = material.id_material)" +
                                " where composicao_componente.id_componente = "+composicao.getId_componente()+" and componente.in_ativo = 'A'  order by id_subcomponente asc");
        composicao.setRetorno(conecta_banco.resultset);
    }
    
    //Consulta geral de componentes
    public void consultageral(Componente componente){
        conecta_banco.executeSQL("select null, id_componente,tipo,componente.descricao,revisao,material.id_material,material.descricao,componente.id_datasheet,datasheet.descricao,"
                            +   "componente.data_cadastro,componente.data_alter from componente" 
                            +   " left join material on (componente.id_material = material.id_material)" 
                            +   " left join datasheet on (componente.id_datasheet = datasheet.id_datasheet)"
                            +   "where componente.in_ativo = 'A' order by id_componente asc");

        componente.setRetorno(conecta_banco.resultset);

    }
    
     //Consulta geral de componentes eletrônicos
    public void consultageralEletronicos(Componente componente){
        conecta_banco.executeSQL("select null, id_componente,tipo,componente.descricao,revisao,material.id_material,material.descricao,componente.id_datasheet,datasheet.descricao,"
                            +   "componente.data_cadastro,componente.data_alter from componente" 
                            +   " left join material on (componente.id_material = material.id_material)" 
                            +   " left join datasheet on (componente.id_datasheet = datasheet.id_datasheet)" 
                            +   " where tipo = 'E' and componente.in_ativo = 'A'"
                            +   " order by id_componente asc");

        componente.setRetorno(conecta_banco.resultset);

    }
    

    //Consulta geral de componentes mecânicos
    public void consultageralMecanicos(Componente componente){
        conecta_banco.executeSQL("select null, id_componente,tipo,componente.descricao,revisao,material.id_material,material.descricao,componente.id_datasheet,datasheet.descricao,"
                            +   "componente.data_cadastro,componente.data_alter from componente" 
                            +   " left join material on (componente.id_material = material.id_material)" 
                            +   " left join datasheet on (componente.id_datasheet = datasheet.id_datasheet)" 
                            +   " where tipo = 'M' and componente.in_ativo = 'A'"
                            +   " order by id_componente asc");

        componente.setRetorno(conecta_banco.resultset);

    }
    
    //Consulta codigo geral de componentes
    public void consultageralCodigo(Componente componente){
        conecta_banco.executeSQL("select null, id_componente,tipo,componente.descricao,revisao,material.id_material,material.descricao,componente.id_datasheet,datasheet.descricao,"
                            +   "componente.data_cadastro,componente.data_alter from componente" 
                            +   " left join material on (componente.id_material = material.id_material)" 
                            +   " left join datasheet on (componente.id_datasheet = datasheet.id_datasheet)" 
                            +   " where componente.id_componente = "+componente.getId_componente()
                            +   " and componente.in_ativo = 'A' order by id_componente asc");

        componente.setRetorno(conecta_banco.resultset);

    }
    
    //Consulta pelo codigo de componentes eletrônicos
    public void consultaeletronicoCodigo(Componente componente){
        conecta_banco.executeSQL("select null, id_componente,tipo,componente.descricao,revisao,material.id_material,material.descricao,componente.id_datasheet,datasheet.descricao,"
                            +   "componente.data_cadastro,componente.data_alter from componente" 
                            +   " left join material on (componente.id_material = material.id_material)" 
                            +   " left join datasheet on (componente.id_datasheet = datasheet.id_datasheet)" 
                            +   " where tipo = 'E' and componente.id_componente = "+componente.getId_componente()
                            +   " and componente.in_ativo = 'A' order by id_componente asc");

        componente.setRetorno(conecta_banco.resultset);

    }
    
    //Consulta pelo codigo de componentes mecânicos
    public void consultamecanicoCodigo(Componente componente){
        conecta_banco.executeSQL("select null, id_componente,tipo,componente.descricao,revisao,material.id_material,material.descricao,componente.id_datasheet,datasheet.descricao,"
                            +   "componente.data_cadastro,componente.data_alter from componente" 
                            +   " left join material on (componente.id_material = material.id_material)" 
                            +   " left join datasheet on (componente.id_datasheet = datasheet.id_datasheet)" 
                            +   " where tipo = 'M' and componente.id_componente = "+componente.getId_componente()
                            +   " componente.in_ativo = 'A' order by id_componente asc");

        componente.setRetorno(conecta_banco.resultset);

    }
    
    //Consulta descricao geral de componentes
    public void consultageralDescricao(Componente componente){
        conecta_banco.executeSQL("select null, id_componente,tipo,componente.descricao,revisao,material.id_material,material.descricao,componente.id_datasheet,datasheet.descricao,"
                            +   "componente.data_cadastro,componente.data_alter from componente" 
                            +   " left join material on (componente.id_material = material.id_material)" 
                            +   " left join datasheet on (componente.id_datasheet = datasheet.id_datasheet)" 
                            +   " where componente.descricao like '"+componente.getDescricao()+"%'"
                            +   " and componente.in_ativo = 'A' order by id_componente asc");

        componente.setRetorno(conecta_banco.resultset);

    }
    
    //Consulta descrição eletrônicos
    public void consultaeletronicoDescricao(Componente componente){
        conecta_banco.executeSQL("select null, id_componente,tipo,componente.descricao,revisao,material.id_material,material.descricao,componente.id_datasheet,datasheet.descricao,"
                            +   "componente.data_cadastro,componente.data_alter from componente" 
                            +   " left join material on (componente.id_material = material.id_material)" 
                            +   " left join datasheet on (componente.id_datasheet = datasheet.id_datasheet)" 
                            +   " where tipo = 'E' and componente.descricao like '"+componente.getDescricao()+"%'"
                            +   " and componente.in_ativo = 'A' order by id_componente asc");

        componente.setRetorno(conecta_banco.resultset);

    }
    
    //Consulta descrição mecânicos
    public void consultamecanicoDescricao(Componente componente){
        conecta_banco.executeSQL("select null, id_componente,tipo,componente.descricao,revisao,material.id_material,material.descricao,componente.id_datasheet,datasheet.descricao,"
                            +   "componente.data_cadastro,componente.data_alter from componente" 
                            +   " left join material on (componente.id_material = material.id_material)" 
                            +   " left join datasheet on (componente.id_datasheet = datasheet.id_datasheet)" 
                            +   " where tipo = 'M' and componente.descricao like '"+componente.getDescricao()+"%'"
                            +   " componente.in_ativo = 'A' order by id_componente asc");

        componente.setRetorno(conecta_banco.resultset);

    }
    
    public String retornaCaminhoArquivo(Componente componente){
        
        String sql = "select * from componente where id_componente = "+ componente.getId_componente();
           
        conecta_banco.executeSQL(sql);
        try {        
            conecta_banco.resultset.first();
            return conecta_banco.resultset.getString("path_datasheet");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao retornar caminho do arquivo");
        }
        return null;
    }
    
    //Método de incluir a composição de componentes para um determinado componente
    public void addComposicao( Componente componente , JTable composicao, int situacao) throws SQLException{
        
        DefaultTableModel TabelaComposicao = (DefaultTableModel)componente.getTabela().getModel();
        DefaultTableModel TabelaComponente = (DefaultTableModel) composicao.getModel();
        ultima = new UltimaSequencia();
        
        Boolean sel = false;
        Integer id_componente;
        int id_composicao;
        String tipo;
        String descricao;
        Integer id_material = null;
        String material = null;
        Integer qntd;

        int totlinha_composicao = composicao.getRowCount();
        int totlinha_componente = componente.getTabela().getRowCount();

      
        for (int i = 0; i < totlinha_composicao; i++){
            
            sel = (Boolean) TabelaComponente.getValueAt(i, 0);
            //Se a linha estiver selecionada
            if(sel != null){

                if(sel == true){
                    TabelaComponente.setValueAt(false, i, 0);

                    id_componente = Integer.parseInt(TabelaComponente.getValueAt(i,1).toString()); 
                    tipo = TabelaComponente.getValueAt(i, 2).toString();
                    descricao = TabelaComponente.getValueAt(i, 3).toString();
                    //Se for componente mecânico
                    if(tipo.equals("M")){
                        if(TabelaComponente.getValueAt(i, 6)!= null){
                            id_material = Integer.parseInt(TabelaComponente.getValueAt(i, 5).toString()); 
                            material = TabelaComponente.getValueAt(i, 6).toString();
                        }else{
                            id_material = 0;
                            material = "";
                        }
                       
                    }
                    qntd = Integer.parseInt(TabelaComponente.getValueAt(i, 11).toString().toString());
                    //ao pegar a quantidade já seta a quantidade como zero na jtable
                    TabelaComponente.setValueAt("", i, 11);
                    
                    totlinha_componente = componente.getTabela().getRowCount();

                    if(totlinha_componente == 0){
                        //add mais uma linha
                        TabelaComposicao.setNumRows(1); 
                        //gera ultimo id composicao baseado no banco
                        id_composicao = ultima.ultimasequencia("COMPOSICAO_COMPONENTE","ID_COMPOSICAO");
                        
                        //seta valores na jtable
                        TabelaComposicao.setValueAt(false, 0, 0);
                        TabelaComposicao.setValueAt(id_composicao, 0, 1);
                        TabelaComposicao.setValueAt(id_componente, 0, 2);
                        TabelaComposicao.setValueAt(tipo, 0, 3);
                        TabelaComposicao.setValueAt(descricao, 0, 4);
                        if(tipo.equals("M")){
                            if(id_material > 0){
                                TabelaComposicao.setValueAt(id_material, 0, 5);
                                TabelaComposicao.setValueAt(material, 0, 6);
                            }
                           
                        }
                        TabelaComposicao.setValueAt(qntd, 0, 7);
                        TabelaComposicao.setValueAt(0, 0, 8);
                        
                    }else{
                        
                        for (int i_comp = 0; i_comp < totlinha_componente; i_comp++){
                            //Chegou na ultima linha
                            if( i_comp == totlinha_componente-1){
                                //adiciona uma linha a mais
                                TabelaComposicao.setNumRows(totlinha_componente+1);  
                                
                                //se estiver em modo de alteração
                                if(situacao == Rotinas.ALTERAR){
                                    //gera ultimo id composicao baseado no banco
                                    id_composicao = ultima.ultimasequencia("COMPOSICAO_COMPONENTE","ID_COMPOSICAO");
                                    
                                    if(id_composicao > Integer.parseInt(TabelaComposicao.getValueAt(totlinha_componente-1, 1).toString())){
                                         TabelaComposicao.setValueAt(id_composicao, totlinha_componente, 1);
                                    }else
                                    {
                                        //gera o ultimo id baseado no ultimo registro da jtable
                                        id_composicao = Integer.parseInt(TabelaComposicao.getValueAt(totlinha_componente-1, 1).toString());
                                        TabelaComposicao.setValueAt(id_composicao+1, totlinha_componente, 1);
                                    }
                                }else{
                                    //gera o ultimo id baseado no ultimo registro da jtable
                                    id_composicao = (Integer.parseInt(TabelaComposicao.getValueAt(totlinha_componente-1, 1).toString())+1);
                                    TabelaComposicao.setValueAt(id_composicao, totlinha_componente, 1);
                                }
                                
                                 //seta valores na jtable
                                TabelaComposicao.setValueAt(false, 0, 0);
                              //  TabelaComposicao.setValueAt(id_composicao, totlinha_componente, 1);
                                TabelaComposicao.setValueAt(id_componente, totlinha_componente, 2);
                                TabelaComposicao.setValueAt(tipo, totlinha_componente, 3);
                                TabelaComposicao.setValueAt(descricao, totlinha_componente, 4);
                                if(tipo.equals("M")){
                                    if(id_material > 0){
                                        TabelaComposicao.setValueAt(id_material, totlinha_componente, 5);
                                        TabelaComposicao.setValueAt(material, totlinha_componente, 6);
                                    }
                                }
                                TabelaComposicao.setValueAt(qntd, totlinha_componente, 7);
                                TabelaComposicao.setValueAt(0, totlinha_componente, 8);
                            }
                        }  
                    }
                } 
            }
        }
    }
    
    //Método de incluir fornecedores para um componente
    public void addFornecedores( FornecedoresComponente fornecedor , JTable fornecedor_comp, int situacao) throws SQLException{
        
        DefaultTableModel TabelaFornecedores = (DefaultTableModel)fornecedor_comp.getModel();
        DefaultTableModel TabelaFornecedoresComp = (DefaultTableModel) fornecedor.getTabela().getModel();
        ultima = new UltimaSequencia();
        
        Boolean sel = false;
        int id_fornec_comp;
        Integer id_fornecedor;
        String descricao;
        String cnpj;
        String site;
        Integer id_material = null;
        String material = null;
        Integer qntd;

        int totlinha_fornec = TabelaFornecedores.getRowCount();
        int totlinha_fornec_comp = fornecedor_comp.getRowCount();

      
        for (int i = 0; i < totlinha_fornec; i++){
            
            sel = (Boolean) TabelaFornecedores.getValueAt(i, 0);
            //Se a linha estiver selecionada
            if(sel != null){

                if(sel == true){
                    TabelaFornecedores.setValueAt(false, i, 0);
                    id_fornec_comp = ultima.ultimasequencia("FORNECEDOR_COMPONENTE","ID_FORNECEDORES_COMP"); 
                    id_fornecedor = Integer.parseInt(TabelaFornecedores.getValueAt(i, 1).toString()); 
                    descricao = TabelaFornecedores.getValueAt(i, 2).toString();
                    cnpj = TabelaFornecedores.getValueAt(i, 3).toString();
                    site = TabelaFornecedores.getValueAt(i, 4).toString();

                    totlinha_fornec_comp = fornecedor.getTabela().getRowCount();

                    if(totlinha_fornec_comp == 0){
                        //add mais uma linha
                        TabelaFornecedoresComp.setNumRows(1);  
                        //seta valores na jtable
                        TabelaFornecedoresComp.setValueAt(false, 0, 0);
                        TabelaFornecedoresComp.setValueAt(id_fornec_comp, 0,1);
                        TabelaFornecedoresComp.setValueAt(id_fornecedor, 0, 2);
                        TabelaFornecedoresComp.setValueAt(descricao, 0, 3);
                        TabelaFornecedoresComp.setValueAt(cnpj, 0, 4);
                        TabelaFornecedoresComp.setValueAt(site, 0, 5);
                        TabelaFornecedoresComp.setValueAt(0, 0, 6);
                        
                    }else{
                        
                        for (int i_comp = 0; i_comp < totlinha_fornec_comp; i_comp++){
                            //Chegou na ultima linha
                            if( i_comp == totlinha_fornec_comp-1){
                                
                                //adiciona uma linha a mais
                                TabelaFornecedoresComp.setNumRows(totlinha_fornec_comp+1);  
                                
                                //se estiver em modo de alteração
                                if(situacao == Rotinas.ALTERAR){
                                    //gera ultimo id composicao baseado no banco
                                    id_fornec_comp = ultima.ultimasequencia("FORNECEDOR_COMPONENTE","ID_FORNECEDORES_COMP"); 
                                    if(id_fornec_comp > Integer.parseInt(TabelaFornecedoresComp.getValueAt(totlinha_fornec_comp-1, 1).toString())){
                                         TabelaFornecedoresComp.setValueAt(id_fornec_comp, totlinha_fornec_comp, 1);
                                    }else{
                                        id_fornec_comp = Integer.parseInt(TabelaFornecedoresComp.getValueAt(totlinha_fornec_comp-1, 1).toString());
                                        TabelaFornecedoresComp.setValueAt(id_fornec_comp+1, totlinha_fornec_comp, 1);
                                    }
                                }else{
                                    //gera o ultimo id baseado no ultimo registro da jtable
                                    id_fornec_comp = (Integer.parseInt(TabelaFornecedoresComp.getValueAt(totlinha_fornec_comp-1, 1).toString())+1);
                                    TabelaFornecedoresComp.setValueAt(id_fornec_comp, totlinha_fornec_comp, 1);
                                }

                                 //seta valores na jtable
                                TabelaFornecedoresComp.setValueAt(false, totlinha_fornec_comp, 0);
                                TabelaFornecedoresComp.setValueAt(id_fornecedor, totlinha_fornec_comp, 2);
                                TabelaFornecedoresComp.setValueAt(descricao, totlinha_fornec_comp, 3);
                                TabelaFornecedoresComp.setValueAt(cnpj, totlinha_fornec_comp, 4);
                                TabelaFornecedoresComp.setValueAt(site, totlinha_fornec_comp, 5);
                                TabelaFornecedoresComp.setValueAt(0, totlinha_fornec_comp, 6);
                            }
                        }  
                    }
                } 
            }
        }
    }
    
     //Método para retornar dados do componente
    public void retornardadosComponente(Componente componente) {
            
        conecta_banco.executeSQL("select null, id_componente,tipo,componente.descricao,revisao,material.id_material,material.descricao,componente.id_datasheet,datasheet.descricao,"
                            +   "componente.data_cadastro,componente.data_alter from componente" 
                            +   " left join material on (componente.id_material = material.id_material)" 
                            +   " left join datasheet on (componente.id_datasheet = datasheet.id_datasheet)" 
                            +   " where componente.id_componente = "+componente.getId_componente()+" order by id_componente asc");

        try {        
 
            conecta_banco.resultset.first();
            
            componente.setId_componente(conecta_banco.resultset.getInt("id_componente"));
            componente.setTipo(conecta_banco.resultset.getString("tipo"));
            componente.setRevisao(conecta_banco.resultset.getString("revisao"));
            componente.setDescricao(conecta_banco.resultset.getString("componente.descricao"));
            componente.setDs_material(conecta_banco.resultset.getString("material.descricao"));
            componente.setDs_datasheet(conecta_banco.resultset.getString("datasheet.descricao"));
            componente.setData_cadastro(conecta_banco.resultset.getDate("data_cadastro"));
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao retornar dados do componente");
        }
     }
    //Método para inativar componente
    public void inativaComponente(Componente componente){
        //Inativa componente    
        conecta_banco.atualizarSQL("UPDATE COMPONENTE SET IN_ATIVO = 'I'"
                               + " WHERE ID_COMPONENTE = " + componente.getId_componente());
        //Inativa composição do componente
        conecta_banco.atualizarSQL("UPDATE COMPOSICAO_COMPONENTE SET ESTADO = 'I'"
                               + " WHERE ID_COMPONENTE = " + componente.getId_componente());
        //inativa fornecedores do componente
        conecta_banco.atualizarSQL("UPDATE FORNECEDOR_COMPONENTE SET IN_ATIVO = 'I'"
                               + " WHERE ID_COMPONENTE = " + componente.getId_componente());
    }
    // verifica a existencia de composição do componente em questão, retorna true se existe composicao e false se não existe
    public boolean verificaExisteComposicao(Componente componente){
        Integer id_componente_composicao = 0;
        //faz a consulta de composição do componente
        conecta_banco.executeSQL("select * from composicao_componente where composicao_componente.id_componente = "+componente.getId_componente());
        try {        
           conecta_banco.resultset.first();
           id_componente_composicao = conecta_banco.resultset.getInt("id_componente");
           return true;
        } catch (SQLException ex) {
           return false;
           
        }
    }
    
    //Método para calcular o custo do componente que possui composição
    public Double calculaComposicaoComponente(ComponenteVersaoProjeto componente){
        Integer id_componente_composicao;
        Integer id_moeda;
        Integer qntd_componente_composicao;
        Timestamp data_fornec;
        Double valor_unit = 0.0;
        Double total_composicao = 0.0;
        ResultSet result_composicao = null;
        ResultSet result_valor_comp = null;
        //faz a consulta de composição do componente
        conecta_banco.executeSQL("select * from composicao_componente where id_componente = "+componente.getId_componente());
        result_composicao = conecta_banco.resultset;
        try {
            while ( result_composicao.next()) {
                
                id_componente_composicao = result_composicao.getInt("id_subcomponente");
                qntd_componente_composicao = result_composicao.getInt("qntd");
                componente.setId_componente(id_componente_composicao);
                
             
                //sql para consulta do custo unitário do componente(composição) baseado no ultimo fornecimento feito do mesmo para a versão do projeto
                
                conecta_banco.executeSQL("select componentes_fornecimento.id_comp_fornec, componentes_fornecimento.id_componente,componentes_fornecimento.id_fornecimento," 
                                        +" componentes_fornecimento.id_moeda,componentes_fornecimento.qntd_componente,componentes_fornecimento.valor_unit,componentes_fornecimento.in_ativo," 
                                        +" componentes_versao_projeto.id_projeto,componentes_versao_projeto.cod_vers_projeto, fornecimento.data_cadastro from componentes_fornecimento" 
                                        +" inner join componentes_versao_projeto on (componentes_versao_projeto.id_comp_fornec = componentes_fornecimento.id_comp_fornec)" 
                                        +" inner join fornecimento on (fornecimento.id_fornecimento = componentes_fornecimento.id_fornecimento)"
                                        +" where componentes_fornecimento.id_componente = "+componente.getId_componente()+" and componentes_versao_projeto.cod_vers_projeto = "+componente.getCod_vers_projeto()+" "
                                        +" and componentes_fornecimento.in_ativo = 'A'" 
                                        +" and fornecimento.data_cadastro >= (select max(fornecimento.data_cadastro) from componentes_fornecimento"
                                        +" inner join componentes_versao_projeto on (componentes_versao_projeto.id_comp_fornec = componentes_fornecimento.id_comp_fornec)" 
                                        +" inner join fornecimento on (fornecimento.id_fornecimento = componentes_fornecimento.id_fornecimento)" 
                                        +" where componentes_fornecimento.id_componente = "+componente.getId_componente()+" and componentes_versao_projeto.cod_vers_projeto = "+componente.getCod_vers_projeto()+" "
                                        +" and componentes_fornecimento.in_ativo = 'A')");
                
                                        result_valor_comp = conecta_banco.resultset;
                
                try {   
                    //armazena valores para o calculo
                    result_valor_comp.first();
                    valor_unit = result_valor_comp.getDouble("valor_unit");
                    id_moeda = result_valor_comp.getInt("id_moeda");
                    data_fornec = result_valor_comp.getTimestamp("data_cadastro");
                    
                    /*
                    JOptionPane.showMessageDialog(null, "ID Componente = "+id_componente_composicao);
                    JOptionPane.showMessageDialog(null, "Valor unitário = "+valor_unit);
                    JOptionPane.showMessageDialog(null, "ID Moeda = "+id_moeda);
                    JOptionPane.showMessageDialog(null, "Data = "+data_fornec);
                    JOptionPane.showMessageDialog(null, "Qntd = "+qntd_componente_composicao);
                    */
                    
                    //converte valor_unitario para reais
                    valor_unit = dao_moeda.converteparaReais(valor_unit, id_moeda, data_fornec);
                    //calcula o total
                    total_composicao = total_composicao + (valor_unit*qntd_componente_composicao);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Falha ao calcular valor unitário da composição do componente.");
                }
                
                calculaComposicaoComponente(componente);
            }
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "Falha ao calcular valor unitário da composição do componente.");
        }
        
        return total_composicao;
    }
}



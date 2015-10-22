/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.GPEDSCVP.dao;

import br.edu.GPEDSCVP.classe.Componente;
import br.edu.GPEDSCVP.conexao.ConexaoBanco;
import br.edu.GPEDSCVP.util.ExcessaoBanco;
import br.edu.GPEDSCVP.util.FormatarData;
import br.edu.GPEDSCVP.util.UltimaSequencia;
import java.sql.SQLException;
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
            
            if (componente.getId_datasheet() == 0){
                int sequencia = (Integer) (ultima.ultimasequencia("COMPONENTE","ID_COMPONENTE"));

                resultado = conecta_banco.executeSQL("INSERT INTO componente (id_componente , descricao, tipo, revisao, data_cadastro, data_alter) "
                + "VALUES (?, ?, ?, ?, ?, ?) ",
                sequencia,
                componente.getDescricao(),
                componente.getTipo(),
                componente.getRevisao(),
                FormatarData.dateParaTimeStamp(componente.getData_cadastro()),
                FormatarData.dateParaTimeStamp(componente.getData_alter()));
            }else{
                int sequencia = (Integer) (ultima.ultimasequencia("COMPONENTE","ID_COMPONENTE"));

                resultado = conecta_banco.executeSQL("INSERT INTO componente (id_componente, id_datasheet, descricao, tipo, revisao, data_cadastro, data_alter) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?) ",
                sequencia,
                componente.getId_datasheet(),
                componente.getDescricao(),
                componente.getTipo(),
                componente.getRevisao(),
                FormatarData.dateParaTimeStamp(componente.getData_cadastro()),
                FormatarData.dateParaTimeStamp(componente.getData_alter()));
            }
        //se for mecânico  
        }else{
            
            if (componente.getId_datasheet() == 0){
                int sequencia = (Integer) (ultima.ultimasequencia("COMPONENTE","ID_COMPONENTE"));

                resultado = conecta_banco.executeSQL("INSERT INTO componente (id_componente , descricao, tipo, revisao, id_material, data_cadastro, data_alter) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?) ",
                sequencia,
                componente.getDescricao(),
                componente.getTipo(),
                componente.getRevisao(),
                componente.getId_material(),
                FormatarData.dateParaTimeStamp(componente.getData_cadastro()),
                FormatarData.dateParaTimeStamp(componente.getData_alter()));
            }else{
                int sequencia = (Integer) (ultima.ultimasequencia("COMPONENTE","ID_COMPONENTE"));

                resultado = conecta_banco.executeSQL("INSERT INTO componente (id_componente, id_datasheet, descricao, tipo, revisao, id_material, data_cadastro, data_alter) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?) ",
                sequencia,
                componente.getId_datasheet(),
                componente.getDescricao(),
                componente.getTipo(),
                componente.getRevisao(),
                componente.getId_material(),
                FormatarData.dateParaTimeStamp(componente.getData_cadastro()),
                FormatarData.dateParaTimeStamp(componente.getData_alter()));
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
    
    //Consulta geral de componentes
    public void consultageral(Componente componente){
        conecta_banco.executeSQL("select null, id_componente,tipo,componente.descricao,revisao,material.id_material,material.descricao,componente.id_datasheet,datasheet.descricao,"
                            +   "componente.data_cadastro,componente.data_alter from componente" 
                            +   " left join material on (componente.id_material = material.id_material)" 
                            +   " left join datasheet on (componente.id_datasheet = datasheet.id_datasheet)" 
                            +   " order by id_componente asc");

        componente.setRetorno(conecta_banco.resultset);

    }
    
     //Consulta geral de componentes eletrônicos
    public void consultageralEletronicos(Componente componente){
        conecta_banco.executeSQL("select null, id_componente,tipo,componente.descricao,revisao,material.id_material,material.descricao,componente.id_datasheet,datasheet.descricao,"
                            +   "componente.data_cadastro,componente.data_alter from componente" 
                            +   " left join material on (componente.id_material = material.id_material)" 
                            +   " left join datasheet on (componente.id_datasheet = datasheet.id_datasheet)" 
                            +   " where tipo = 'E'"
                            +   " order by id_componente asc");

        componente.setRetorno(conecta_banco.resultset);

    }
    
    //Consulta geral de componentes mecânicos
    public void consultageralMecanicos(Componente componente){
        conecta_banco.executeSQL("select null, id_componente,tipo,componente.descricao,revisao,material.id_material,material.descricao,componente.id_datasheet,datasheet.descricao,"
                            +   "componente.data_cadastro,componente.data_alter from componente" 
                            +   " left join material on (componente.id_material = material.id_material)" 
                            +   " left join datasheet on (componente.id_datasheet = datasheet.id_datasheet)" 
                            +   " where tipo = 'M'"
                            +   " order by id_componente asc");

        componente.setRetorno(conecta_banco.resultset);

    }
    
    //Consulta geral de componentes
    public void consultageralCodigo(Componente componente){
        conecta_banco.executeSQL("select null, id_componente,tipo,componente.descricao,revisao,material.id_material,material.descricao,componente.id_datasheet,datasheet.descricao,"
                            +   "componente.data_cadastro,componente.data_alter from componente" 
                            +   " left join material on (componente.id_material = material.id_material)" 
                            +   " left join datasheet on (componente.id_datasheet = datasheet.id_datasheet)" 
                            +   " where componente.id_componente = "+componente.getId_componente()
                            +   " order by id_componente asc");

        componente.setRetorno(conecta_banco.resultset);

    }
    
     //Consulta geral de componentes
    public void consultaeletronicoCodigo(Componente componente){
        conecta_banco.executeSQL("select null, id_componente,tipo,componente.descricao,revisao,material.id_material,material.descricao,componente.id_datasheet,datasheet.descricao,"
                            +   "componente.data_cadastro,componente.data_alter from componente" 
                            +   " left join material on (componente.id_material = material.id_material)" 
                            +   " left join datasheet on (componente.id_datasheet = datasheet.id_datasheet)" 
                            +   " where tipo = 'E' and componente.id_componente = "+componente.getId_componente()
                            +   " order by id_componente asc");

        componente.setRetorno(conecta_banco.resultset);

    }
    
    //Consulta geral de componentes
    public void consultageralDescricao(Componente componente){
        conecta_banco.executeSQL("select null, id_componente,tipo,componente.descricao,revisao,material.id_material,material.descricao,componente.id_datasheet,datasheet.descricao,"
                            +   "componente.data_cadastro,componente.data_alter from componente" 
                            +   " left join material on (componente.id_material = material.id_material)" 
                            +   " left join datasheet on (componente.id_datasheet = datasheet.id_datasheet)" 
                            +   " where componente.descricao like '"+componente.getDescricao()+"%'"
                            +   " order by id_componente asc");

        componente.setRetorno(conecta_banco.resultset);

    }
    
    //Consulta geral de componentes
    public void consultaeletronicoDescricao(Componente componente){
        conecta_banco.executeSQL("select null, id_componente,tipo,componente.descricao,revisao,material.id_material,material.descricao,componente.id_datasheet,datasheet.descricao,"
                            +   "componente.data_cadastro,componente.data_alter from componente" 
                            +   " left join material on (componente.id_material = material.id_material)" 
                            +   " left join datasheet on (componente.id_datasheet = datasheet.id_datasheet)" 
                            +   " where tipo = 'E' and componente.descricao like '"+componente.getDescricao()+"%'"
                            +   " order by id_componente asc");

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
    
    //Método de incluir contato na Jtable
    public void addComposicao( Componente componente , JTable composicao) throws SQLException{
        
        DefaultTableModel TabelaComposicao = (DefaultTableModel)componente.getTabela().getModel();
        DefaultTableModel TabelaComponente = (DefaultTableModel) composicao.getModel();
        Boolean sel = false;
        Integer id_componente;
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

                    id_componente = Integer.parseInt(TabelaComponente.getValueAt(i, 1).toString()); 
                    tipo = TabelaComponente.getValueAt(i, 2).toString();
                    descricao = TabelaComponente.getValueAt(i, 3).toString();
                    //Se for componente mecânico
                    if(tipo.equals("M")){
                        id_material = Integer.parseInt(TabelaComponente.getValueAt(i, 5).toString()); 
                        material = TabelaComponente.getValueAt(i, 6).toString();
                    }
                    qntd = Integer.parseInt(TabelaComponente.getValueAt(i, 11).toString().toString());
                    //ao pegar a quantidade já seta a quantidade como zero na jtable
                    TabelaComponente.setValueAt("", i, 11);
                    
                    totlinha_componente = componente.getTabela().getRowCount();

                    if(totlinha_componente == 0){
                        //add mais uma linha
                        TabelaComposicao.setNumRows(1);  
                        //seta valores na jtable
                        TabelaComposicao.setValueAt(false, 0, 0);
                        TabelaComposicao.setValueAt(id_componente, 0, 1);
                        TabelaComposicao.setValueAt(tipo, 0, 2);
                        TabelaComposicao.setValueAt(descricao, 0, 3);
                        if(tipo.equals("M")){
                            TabelaComposicao.setValueAt(id_material, 0, 4);
                            TabelaComposicao.setValueAt(material, 0, 5);
                        }
                        TabelaComposicao.setValueAt(qntd, 0, 6);
                        
                    }else{
                        
                        for (int i_comp = 0; i_comp < totlinha_componente; i_comp++){
                            //Chegou na ultima linha
                            if( i_comp == totlinha_componente-1){
                                //adiciona uma linha a mais
                                TabelaComposicao.setNumRows(totlinha_componente+1);  
                                 //seta valores na jtable
                                TabelaComposicao.setValueAt(false, 0, 0);
                                TabelaComposicao.setValueAt(id_componente, totlinha_componente, 1);
                                TabelaComposicao.setValueAt(tipo, totlinha_componente, 2);
                                TabelaComposicao.setValueAt(descricao, totlinha_componente, 3);
                                if(tipo.equals("M")){
                                    TabelaComposicao.setValueAt(id_material, totlinha_componente, 4);
                                    TabelaComposicao.setValueAt(material, totlinha_componente, 5);
                                }
                                TabelaComposicao.setValueAt(qntd, totlinha_componente, 6); 
                            }

                        }  
                    }
                } 
            }
        }
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.GPEDSCVP.dao;

import br.edu.GPEDSCVP.classe.Acesso;
import br.edu.GPEDSCVP.classe.Permissao;
import br.edu.GPEDSCVP.classe.Pessoa;
import br.edu.GPEDSCVP.classe.Tela;
import br.edu.GPEDSCVP.classe.Usuario;
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
public class daoPermissao {
    
    ConexaoBanco conecta_banco;
    UltimaSequencia ultima;
    
    public daoPermissao()
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
    
      //Método de incluir contato no banco
    public void incluir(Permissao permissao)throws SQLException
    {
        //Insert de permissões de acesso
        ultima = new UltimaSequencia();
        int sequencia = (Integer) (ultima.ultimasequencia("PERMISSAO","ID_PERMISSAO"));
        String sql = "INSERT INTO PERMISSAO VALUES ("
                + sequencia + ","
                + permissao.getId_usuario()+ ","
                + permissao.getId_tela()+ ","
                + permissao.getAcesso()+ ","
                + permissao.getInserir()+ ","
                + permissao.getAlterar()+ ","
                + permissao.getExcluir()+ ","
                + permissao.getConsultar()+",'"
                + FormatarData.dateParaTimeStamp(permissao.getData_alter())+"')";

                conecta_banco.incluirSQL(sql);
    }
    
    //Método de incluir endereco na Jtable
    public void addPermissao(Permissao permissao) throws SQLException{
        DefaultTableModel TabelaPermissao = (DefaultTableModel)permissao.getTabela().getModel();

        ultima = new UltimaSequencia();
        
        Integer sequencia;
        int totlinha = permissao.getTabela().getRowCount();
        //adiciona uma linha a mais
        TabelaPermissao.setNumRows(totlinha + 1);
        //se for a primeira linha, gera o id do contato referente ao ultimo cadastrado no banco
        if(totlinha ==0){
            TabelaPermissao.setValueAt(ultima.ultimasequencia("PERMISSAO","ID_PERMISSAO"), totlinha, 1);
        //se não for a primeira linha, gera o id do contato referente ao id da ulima linha da Jtable   
        }else{
            
            //armazena o ultimo id da Jtable
            sequencia = (Integer) TabelaPermissao.getValueAt(totlinha-1, 1);
            //seta o ultimo id na nova linha
            TabelaPermissao.setValueAt(sequencia+1,totlinha, 1);    
        }
        
        //Seta os demais valores dos campos em suas respectivas colunas
        TabelaPermissao.setValueAt(permissao.getId_tela(),totlinha,2);
        TabelaPermissao.setValueAt(permissao.getNome_tela(),totlinha,3);
        if(permissao.getAcesso().equals("S")){
            TabelaPermissao.setValueAt("Sim",totlinha,4);
        }else{
            TabelaPermissao.setValueAt("Não",totlinha,4);
        }
        if(permissao.getInserir().equals("S")){
            TabelaPermissao.setValueAt("Sim",totlinha,5);
        }else{
            TabelaPermissao.setValueAt("Não",totlinha,5);
        }
        if(permissao.getAlterar().equals("S")){
            TabelaPermissao.setValueAt("Sim",totlinha,6);
        }else{
            TabelaPermissao.setValueAt("Não",totlinha,6);
        }
        if(permissao.getExcluir().equals("S")){
            TabelaPermissao.setValueAt("Sim",totlinha,7); 
        }else{
            TabelaPermissao.setValueAt("Não",totlinha,7); 
        }
        if(permissao.getConsultar().equals("S")){
            TabelaPermissao.setValueAt("Sim", totlinha,8);
        }else{
            TabelaPermissao.setValueAt("Não", totlinha,8);
        }
    }
    
     public void gravarPermissao (Permissao permissao){
        DefaultTableModel tabela = (DefaultTableModel) permissao.getTabela().getModel();
        int totlinha = tabela.getRowCount();
        String acesso;
        String inserir;
        String alterar;
        String excluir;
        String consultar;
        
        for (int i = 0; i < totlinha; i++){
            
            
            Integer id_permissao = (Integer) tabela.getValueAt(i, 1);
            Integer id_tela = (Integer) tabela.getValueAt(i, 2);
            if(tabela.getValueAt(i, 4).equals("Sim")){
                acesso = "S";
            }else{
                acesso = "N";
            }
            if(tabela.getValueAt(i, 5).equals("Sim")){
                inserir = "S";
            }else{
                inserir = "N";
            }
            if(tabela.getValueAt(i, 6).equals("Sim")){
                alterar = "S";
            }else{
                alterar = "N";
            }
            if(tabela.getValueAt(i, 7).equals("Sim")){
                excluir = "S";
            }else{
                excluir = "N";
            }
            if(tabela.getValueAt(i, 8).equals("Sim")){
                consultar = "S";
            }else{
                consultar = "N";
            }

            String sql = "INSERT INTO PERMISSAO VALUES ("
                + id_permissao + ","
                + permissao.getId_usuario()+ ","
                + id_tela+ ",'"
                + acesso+ "','"
                + inserir+"','"
                + alterar+"','"
                + excluir+"','"
                + consultar+"','"
                + FormatarData.dateParaTimeStamp(permissao.getData_alter())+"')";

                conecta_banco.incluirSQL(sql);
        }
     }
     
     public void alterarPermissao (Permissao permissao){
       
        DefaultTableModel tabela = (DefaultTableModel) permissao.getTabela().getModel();
        int totlinha = tabela.getRowCount();
        String acesso;
        String inserir;
        String alterar;
        String excluir;
        String consultar;
        
        for (int i = 0; i < totlinha; i++){
            JOptionPane.showMessageDialog(null, tabela.getValueAt(i, 1));
            JOptionPane.showMessageDialog(null, tabela.getValueAt(i, 2));
            int id_permissao =  Integer.parseInt((String) tabela.getValueAt(i, 1));
            int id_tela =  Integer.parseInt((String) tabela.getValueAt(i, 2));
           
            if(tabela.getValueAt(i, 4).equals("Sim")){
                acesso = "S";
            }else{
                acesso = "N";
            }
            if(tabela.getValueAt(i, 5).equals("Sim")){
                inserir = "S";
            }else{
                inserir = "N";
            }
            if(tabela.getValueAt(i, 6).equals("Sim")){
                alterar = "S";
            }else{
                alterar = "N";
            }
            if(tabela.getValueAt(i, 7).equals("Sim")){
                excluir = "S";
            }else{
                excluir = "N";
            }
            if(tabela.getValueAt(i, 8).equals("Sim")){
                consultar = "S";
            }else{
                consultar = "N";
            }
            
             String sql = "UPDATE PERMISSAO SET ID_PERMISSAO = "+ id_permissao+","
                + "ID_TELA = " + id_tela+","
                + "ACESSO = '" + acesso+"',"
                + "INSERIR = '" + inserir+"',"
                + "ALTERAR = '" + alterar+"',"
                + "EXCLUIR = '" + excluir+"',"
                + "CONSULTAR = '" + consultar+"',"
                + "CONSULTAR = '" + consultar+"'," 
                + "DATA_ALTER = '"+ FormatarData.dateParaTimeStamp(permissao.getData_alter())+"' "     
                + "WHERE ID_PERMISSAO = " + id_permissao;
             
                conecta_banco.atualizarSQL(sql);
        }
     }
     
    //consulta permissões pelo codigo da pessoa 
    public void consultacodigo(Permissao permissao){

       conecta_banco.executeSQL("select null,id_usuario, id_permissao, permissao.id_tela, tela.descricao, acesso, inserir, alterar, excluir,"
               + " consultar from permissao" 
               + " inner join tela on (tela.id_tela = permissao.id_tela)"
               + " where permissao.id_usuario = "+permissao.getId_usuario());
       
               permissao.setRetorno(conecta_banco.resultset);
    }
    
    public void retornaDadosPermissao(Acesso acesso, Permissao permissao){

        conecta_banco.executeSQL("select * from permissao where id_usuario = "+ acesso.getId_usuario()+" and id_tela = "+ acesso.getId_tela());
        
        try {        
 
            conecta_banco.resultset.first();
            
            permissao.setId_usuario(conecta_banco.resultset.getInt("id_usuario"));
            permissao.setId_permissao(conecta_banco.resultset.getInt("id_permissao"));
            permissao.setId_tela(conecta_banco.resultset.getInt("id_tela"));
            permissao.setAcesso(conecta_banco.resultset.getString("acesso"));
            permissao.setInserir(conecta_banco.resultset.getString("inserir"));
            permissao.setAlterar(conecta_banco.resultset.getString("alterar"));
            permissao.setExcluir(conecta_banco.resultset.getString("excluir"));
            permissao.setConsultar(conecta_banco.resultset.getString("consultar"));
            permissao.setData_alter(conecta_banco.resultset.getDate("data_alter"));
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao retornar dados das permissões de acesso");
        }
    }
}

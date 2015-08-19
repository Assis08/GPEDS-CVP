/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.GPEDSCVP.dao;

import br.edu.GPEDSCVP.classe.Permissao;
import br.edu.GPEDSCVP.conexao.ConexaoBanco;
import br.edu.GPEDSCVP.validacao.FormatarData;
import br.edu.GPEDSCVP.validacao.UltimaSequencia;
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
        if(permissao.getAcesso() == 1){
            TabelaPermissao.setValueAt("Sim",totlinha,4);
        }else{
            TabelaPermissao.setValueAt("Não",totlinha,4);
        }
        if(permissao.getInserir() == 1){
            TabelaPermissao.setValueAt("Sim",totlinha,5);
        }else{
            TabelaPermissao.setValueAt("Não",totlinha,5);
        }
        if(permissao.getAlterar() == 1){
            TabelaPermissao.setValueAt("Sim",totlinha,6);
        }else{
            TabelaPermissao.setValueAt("Não",totlinha,6);
        }
        if(permissao.getExcluir() == 1){
            TabelaPermissao.setValueAt("Sim",totlinha,7); 
        }else{
            TabelaPermissao.setValueAt("Não",totlinha,7); 
        }
        if(permissao.getConsultar() == 1){
            TabelaPermissao.setValueAt("Sim", totlinha,8);
        }else{
            TabelaPermissao.setValueAt("Não", totlinha,8);
        }
    }
    
     public void gravarPermissao (Permissao permissao){
        DefaultTableModel tabela = (DefaultTableModel) permissao.getTabela().getModel();
        int totlinha = tabela.getRowCount();
        Integer acesso;
        Integer inserir;
        Integer alterar;
        Integer excluir;
        Integer consultar;
        
        for (int i = 0; i < totlinha; i++){
            
            
            Integer id_permissao = (Integer) tabela.getValueAt(i, 1);
            Integer id_tela = (Integer) tabela.getValueAt(i, 2);
            if(tabela.getValueAt(i, 4).equals("Sim")){
                acesso = 1;
            }else{
                acesso = 0;
            }
            if(tabela.getValueAt(i, 5).equals("Sim")){
                inserir = 1;
            }else{
                inserir = 0;
            }
            if(tabela.getValueAt(i, 6).equals("Sim")){
                alterar = 1;
            }else{
                alterar = 0;
            }
            if(tabela.getValueAt(i, 7).equals("Sim")){
                excluir = 1;
            }else{
                excluir = 0;
            }
            if(tabela.getValueAt(i, 8).equals("Sim")){
                consultar = 1;
            }else{
                consultar = 0;
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
    
}

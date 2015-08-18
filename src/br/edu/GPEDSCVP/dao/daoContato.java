/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.GPEDSCVP.dao;

import br.edu.GPEDSCVP.classe.Contato;
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
public class daoContato {
    
    ConexaoBanco conecta_banco;
    UltimaSequencia ultima;
    
    public daoContato()
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
    public void incluir(Contato contato)throws SQLException
    {
       
        //Insert de contato
        ultima = new UltimaSequencia();
        int sequencia = (Integer) (ultima.ultimasequencia("CONTATO","ID_CONTATO"));
        String sql = "INSERT INTO CONTATO VALUES ("
                + sequencia + ","
                + contato.getId_pessoa()+ ","
                + contato.getFone()+ ",'"
                + contato.getDescricao()+ "','"
                + contato.getTipo()+"','"
                + FormatarData.dateParaTimeStamp(contato.getData_alter())+"','"
                + contato.getEmail()+"')";

                conecta_banco.incluirSQL(sql);
    }
    //Método de incluir contato na Jtable
    public void addContato(Contato contato) throws SQLException{
        DefaultTableModel TabelaContato = (DefaultTableModel)contato.getTabela().getModel();
        ultima = new UltimaSequencia();
        
        Integer sequencia;
        int totlinha = contato.getTabela().getRowCount();
        //adiciona uma linha a mais
        TabelaContato.setNumRows(totlinha + 1);
        //se for a primeira linha, gera o id do contato referente ao ultimo cadastrado no banco
        if(totlinha ==0){
        TabelaContato.setValueAt(ultima.ultimasequencia("CONTATO","ID_CONTATO"), totlinha, 1);
        //se não for a primeira linha, gera o id do contato referente ao id da ulima linha da Jtable   
        }else{
            //armazena o ultimo id da Jtable
            sequencia = (Integer) TabelaContato.getValueAt(totlinha-1, 1);
            //seta o ultimo id na nova linha
            TabelaContato.setValueAt(sequencia+1,totlinha, 1);    
        }
        //Seta os demais valores dos campos em suas respectivas colunas
        if(contato.getTipo().equals("E")){
            TabelaContato.setValueAt("Email",totlinha,2);    
        }else{
            TabelaContato.setValueAt("Fone",totlinha,2);
        }
        TabelaContato.setValueAt(contato.getDescricao(),totlinha,3);
        TabelaContato.setValueAt(contato.getFone(),totlinha,4);
        TabelaContato.setValueAt(contato.getEmail(),totlinha,5);
    }
 
   
     public void gravarContatos (Contato contato){
        String numero = "";
        String email = "";
        String tipo;
        DefaultTableModel tabela = (DefaultTableModel) contato.getTabela().getModel();
        int totlinha = tabela.getRowCount();
        for (int i = 0; i < totlinha; i++){
            
            Integer id = (Integer) tabela.getValueAt(i, 1);
            if(tabela.getValueAt(i, 2).equals("Email")){
                tipo = "E";
            }else{
                tipo = "F";
            }
            String descricao = (String) tabela.getValueAt(i, 3);
            if((tabela.getValueAt(i, 4)) != ""){
                numero = (String) tabela.getValueAt(i, 4);
            }else{
                numero = "";
            }
            if((tabela.getValueAt(i, 5)) != ""){
                email = (String) tabela.getValueAt(i, 5);
            }else{
                email = "";
            }

            String sql = "INSERT INTO CONTATO VALUES ("
                    +id + ","
                    +contato.getId_pessoa()+",'"
                    +numero +"','"
                    +descricao + "','"
                    +tipo + "','"
                    + FormatarData.dateParaTimeStamp(contato.getData_alter())+"','"
                    +email +"')";
            
            conecta_banco.incluirSQL(sql);
        }
     }
}

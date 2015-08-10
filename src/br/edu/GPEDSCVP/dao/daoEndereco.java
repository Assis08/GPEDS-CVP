/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.GPEDSCVP.dao;

import br.edu.GPEDSCVP.classe.Endereco;
import br.edu.GPEDSCVP.conexao.ConexaoBanco;
import br.edu.GPEDSCVP.validacao.FormatarData;
import br.edu.GPEDSCVP.validacao.UltimaSequencia;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Willys
 */
public class daoEndereco {
    
    ConexaoBanco conecta_banco;
    UltimaSequencia ultima;
    
    public daoEndereco()
    {
        try
        {
            conecta_banco = new ConexaoBanco();
        }
        catch(SQLException ex)
        {
            
        }
    }
    
     //Método de incluir contato no banco
    public void incluir(Endereco endereco)throws SQLException
    {
       
        //Insert de endereço
        ultima = new UltimaSequencia();
        int sequencia = (Integer) (ultima.ultimasequencia("ENDERECO","ID_ENDERECO"));
        String sql = "INSERT INTO ENDERECO VALUES ("
                + sequencia + ","
                + endereco.getId_pessoa()+ ","
                + endereco.getId_cidade()+ ",'"
                + endereco.getDescricao()+ "','"
                + endereco.getCep()+"','"
                + endereco.getRua()+"','"
                + endereco.getNumero()+"','"
                + endereco.getBairro()+"','"
                + FormatarData.dateParaTimeStamp(endereco.getData_alter())+"')";

                conecta_banco.incluirSQL(sql);
    }
    
    //Método de incluir endereco na Jtable
    public void addEndereco(Endereco endereco) throws SQLException{
        DefaultTableModel TabelaEndereco = (DefaultTableModel)endereco.getTabela().getModel();
        ultima = new UltimaSequencia();
        
        Integer sequencia;
        int totlinha = endereco.getTabela().getRowCount();
        //adiciona uma linha a mais
        TabelaEndereco.setNumRows(totlinha + 1);
        //se for a primeira linha, gera o id do contato referente ao ultimo cadastrado no banco
        if(totlinha ==0){
            TabelaEndereco.setValueAt(ultima.ultimasequencia("ENDERECO","ID_ENDERECO"), totlinha, 1);
        //se não for a primeira linha, gera o id do contato referente ao id da ulima linha da Jtable   
        }else{
            //armazena o ultimo id da Jtable
            sequencia = (Integer) TabelaEndereco.getValueAt(totlinha-1, 1);
            //seta o ultimo id na nova linha
            TabelaEndereco.setValueAt(sequencia+1,totlinha, 1);    
        }
        //Seta os demais valores dos campos em suas respectivas colunas
       
        TabelaEndereco.setValueAt(endereco.getDescricao(),totlinha,2);
        TabelaEndereco.setValueAt(endereco.getRua(),totlinha,3);
        TabelaEndereco.setValueAt(endereco.getNumero(),totlinha,4);
        TabelaEndereco.setValueAt(endereco.getBairro(),totlinha,5);
        TabelaEndereco.setValueAt(endereco.getCep(),totlinha,6);
        TabelaEndereco.setValueAt(endereco.getId_cidade(),totlinha,7);          
    }
    
    public void gravarEndereco (Endereco endereco){
        DefaultTableModel tabela = (DefaultTableModel) endereco.getTabela().getModel();
        int totlinha = tabela.getRowCount();
        for (int i = 0; i < totlinha; i++){
            
            
            Integer id = (Integer) tabela.getValueAt(i, 1);
            String descricao = (String) tabela.getValueAt(i, 2);
            String rua = (String) tabela.getValueAt(i, 3);
            String numero = (String) tabela.getValueAt(i, 4);
            String bairro = (String) tabela.getValueAt(i, 5);
            String cep = (String) tabela.getValueAt(i, 6);
            int cidade = (Integer) tabela.getValueAt(i, 7);
          
           

            String sql = "INSERT INTO ENDERECO VALUES ("
                + id + ","
                + endereco.getId_pessoa()+ ","
                + cidade+ ",'"
                + descricao+ "','"
                + cep+"','"
                + rua+"','"
                + numero+"','"
                + bairro+"','"
                + FormatarData.dateParaTimeStamp(endereco.getData_alter())+"')";

                conecta_banco.incluirSQL(sql);
        }
     }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.GPEDSCVP.dao;

import br.edu.GPEDSCVP.classe.Endereco;
import br.edu.GPEDSCVP.conexao.ConexaoBanco;
import br.edu.GPEDSCVP.util.FormatarData;
import br.edu.GPEDSCVP.util.Rotinas;
import br.edu.GPEDSCVP.util.UltimaSequencia;
import java.sql.SQLException;
import javax.swing.JOptionPane;
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
            JOptionPane.showMessageDialog(null, "Falha na fonte de dados");
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
    
    
    //Método de incluir contato na Jtable
    public void addEndereco(Endereco endereco , int situacao ) throws SQLException{
        DefaultTableModel TabelaEndereco = (DefaultTableModel)endereco.getTabela().getModel();
        ultima = new UltimaSequencia();
        
        int sequencia;
        int totlinha = endereco.getTabela().getRowCount();
      
        //se for a primeira linha, gera o id do endereço referente ao ultimo cadastrado no banco
        if(totlinha ==0){
            //adiciona uma linha a mais
            TabelaEndereco.setNumRows(totlinha + 1);
            
            TabelaEndereco.setValueAt(ultima.ultimasequencia("ENDERECO","ID_ENDERECO"), totlinha, 1);
            //Seta os demais valores dos campos em suas respectivas colunas
            TabelaEndereco.setValueAt(endereco.getDescricao(),totlinha,2);
            TabelaEndereco.setValueAt(endereco.getRua(),totlinha,3);
            TabelaEndereco.setValueAt(endereco.getNumero(),totlinha,4);
            TabelaEndereco.setValueAt(endereco.getBairro(),totlinha,5);
            TabelaEndereco.setValueAt(endereco.getCep(),totlinha,6);
            TabelaEndereco.setValueAt(endereco.getId_cidade(),totlinha,7); 
            TabelaEndereco.setValueAt(endereco.getDs_cidade(),totlinha,8); 
            TabelaEndereco.setValueAt(endereco.getUf(), totlinha,9);
        //se não for a primeira linha, gera o id do endereço referente ao id da ultima linha da Jtable   
        }else{
            for (int i = 0; i < totlinha; i++){
                
                Integer id_existente = Integer.parseInt(TabelaEndereco.getValueAt(i, 1).toString()); 
           
                //Se for um contato ja existente ja Jtable
                if(endereco.getId_endereco()== id_existente){
                    //Seta os novos valores no endereço na Jtable
                    TabelaEndereco.setValueAt(endereco.getDescricao(),i,2);
                    TabelaEndereco.setValueAt(endereco.getRua(),i,3);
                    TabelaEndereco.setValueAt(endereco.getNumero(),i,4);
                    TabelaEndereco.setValueAt(endereco.getBairro(),i,5);
                    TabelaEndereco.setValueAt(endereco.getCep(),i,6);
                    TabelaEndereco.setValueAt(endereco.getId_cidade(),i,7); 
                    TabelaEndereco.setValueAt(endereco.getDs_cidade(),i,8); 
                    TabelaEndereco.setValueAt(endereco.getUf(), i,9);
                    break;
                //Se não existe o endereço na Jtable    
                }else{

                    //Chegou na ultima linha
                    if( i == totlinha-1){
                        //adiciona uma linha a mais
                        TabelaEndereco.setNumRows(totlinha + 1);
                        //Se estiver em modo de alteração
                        if(situacao == Rotinas.ALTERAR){
                           //Pega o ultimo ID do banco de dados
                           sequencia = ultima.ultimasequencia("ENDERECO","ID_ENDERECO");
                           //seta o ultimo id na nova linha
                           TabelaEndereco.setValueAt(sequencia,totlinha, 1); 
                        //Se não estiver em modo de alteração   
                        }else{
                            //armazena o ultimo id da Jtable
                            sequencia = Integer.parseInt(TabelaEndereco.getValueAt(totlinha-1, 1).toString());
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
                        TabelaEndereco.setValueAt(endereco.getDs_cidade(),totlinha,8);  
                        TabelaEndereco.setValueAt(endereco.getUf(), totlinha,9);
                    }
                }
            }
        }
    }

    public void gravarEndereco (Endereco endereco){
        DefaultTableModel tabela = (DefaultTableModel) endereco.getTabela().getModel();
        int totlinha = tabela.getRowCount();
        for (int i = 0; i < totlinha; i++){
            
            
            Integer id = Integer.parseInt(tabela.getValueAt(i, 1).toString());
            String descricao = (String) tabela.getValueAt(i, 2);
            String rua = (String) tabela.getValueAt(i, 3);
            String numero = (String) tabela.getValueAt(i, 4);
            String bairro = (String) tabela.getValueAt(i, 5);
            String cep = (String) tabela.getValueAt(i, 6);
            Integer cidade = Integer.parseInt(tabela.getValueAt(i, 7).toString());

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

    public void alterarEndereco (Endereco endereco){
        String rua = "";
        String descricao = "";
        String numero = "";
        String bairro = "";
        String cep = "";
        Integer cidade;
        String uf = "";
        
        DefaultTableModel tabela = (DefaultTableModel) endereco.getTabela().getModel();
        int totlinha = tabela.getRowCount();
        for (int i = 0; i < totlinha; i++){

            Integer id = Integer.parseInt(tabela.getValueAt(i, 1).toString());
            descricao = (String) tabela.getValueAt(i, 2);
            rua = (String) tabela.getValueAt(i, 3);
            numero = (String) tabela.getValueAt(i, 4);
            bairro = (String) tabela.getValueAt(i, 5);
            cep = (String) tabela.getValueAt(i, 6);
            Integer id_cidade = Integer.parseInt(tabela.getValueAt(i, 7).toString());

            //Verifica se já existe o contato
            String sql = "select * from endereco where id_endereco = "+ id;
            try {
                conecta_banco.executeSQL(sql);
                conecta_banco.resultset.first();
                //se existe o contato
                if(id == conecta_banco.resultset.getInt("id_endereco")){
                    //apenas altera 
                    sql = "UPDATE ENDERECO SET ID_ENDERECO = "+ id+","
                    + "ID_CIDADE = " + id_cidade+","
                    + "DESCRICAO = '" + descricao+"',"
                    + "CEP = '" + cep+"',"
                    + "RUA = '" + rua+"',"
                    + "NUMERO = '" + numero+"',"
                    + "BAIRRO = '" + bairro+"',"
                    + "DATA_ALTER = '"+ FormatarData.dateParaTimeStamp(endereco.getData_alter())+"' "
                    + " WHERE ID_ENDERECO = " + id;
            
                    conecta_banco.atualizarSQL(sql);  
                }
                
            } catch (Exception e) {
                //Chegou aqui porque o endereco não existe, então inclui
                 sql = "INSERT INTO ENDERECO VALUES ("
                    +id + ","
                    +endereco.getId_pessoa()+","
                    +id_cidade +",'"
                    +descricao + "','"
                    +cep + "','"
                    +rua + "','"
                    +numero + "','"
                    +bairro + "','"
                    + FormatarData.dateParaTimeStamp(endereco.getData_alter())+"')";
            
                    conecta_banco.incluirSQL(sql);
            }
        }
    }

    //consulta endereço pelo codigo da pessoa 
    public void consultacodigo(Endereco endereco){

       conecta_banco.executeSQL("select null, endereco.id_endereco, endereco.descricao, rua, numero, bairro, cep,endereco.id_cidade, cidade.descricao,"
            +" cidade.uf from endereco"
            +" inner join cidade on (cidade.id_cidade = endereco.id_cidade)"
            +" where endereco.id_pessoa = "+endereco.getId_pessoa());
       
            endereco.setRetorno(conecta_banco.resultset);
    }
    


}

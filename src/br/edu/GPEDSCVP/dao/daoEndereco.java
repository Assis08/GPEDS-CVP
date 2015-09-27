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
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
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
            TabelaEndereco.setValueAt(0,totlinha,10);
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
                    TabelaEndereco.setValueAt(0,i,10);
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
                        TabelaEndereco.setValueAt(0,totlinha,10);
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

    public void alterarEndereco (Endereco endereco, ArrayList<Integer> enderecos_deletados){
        
        String rua = "";
        String descricao = "";
        String numero = "";
        String bairro = "";
        String cep = "";
        Integer cidade;
        String uf = "";
        
        if(!enderecos_deletados.isEmpty()){
             JOptionPane.showMessageDialog(null, enderecos_deletados.get(0));
        }
       
      
        
        DefaultTableModel tabela = (DefaultTableModel) endereco.getTabela().getModel();
        int totlinha = tabela.getRowCount();
        for (int i = 0; i < totlinha; i++){
            //JOptionPane.showMessageDialog(null, enderecos_deletados.get(i));
            Integer id = Integer.parseInt(tabela.getValueAt(i, 1).toString());
            descricao = (String) tabela.getValueAt(i, 2);
            rua = (String) tabela.getValueAt(i, 3);
            numero = (String) tabela.getValueAt(i, 4);
            bairro = (String) tabela.getValueAt(i, 5);
            cep = (String) tabela.getValueAt(i, 6);
            Integer id_cidade = Integer.parseInt(tabela.getValueAt(i, 7).toString());
            Integer exc = Integer.parseInt(tabela.getValueAt(i, 10).toString());

            //Verifica se já existe o endereco
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
                //Se for um registro excluido da Jtable
                if(exc == 1){
                    //Exclui do banco
                    sql = "DELETE FROM ENDERECO WHERE ID_ENDERECO = " + id;
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
        
        for(int i =0; i < enderecos_deletados.size(); i++ ){
            JOptionPane.showMessageDialog(null, enderecos_deletados.get(i));
        }
    }
    
     //Método para remover um registro da Jtable
    public void excluirEndereco(JTable jtable) {
        DefaultTableModel tabela = (DefaultTableModel) jtable.getModel();
        int totlinha = tabela.getRowCount();
        Boolean sel = false;
        Integer id_endereco;
                
        int opcao = JOptionPane.showConfirmDialog(null, "Deseja remover as linhas selecionadas? ",
                "remover",
                JOptionPane.YES_NO_OPTION);
        if (opcao == JOptionPane.YES_OPTION) {
            for (int i = totlinha - 1; i >= 0; i--) { 
                if (tabela.getValueAt(i, 0) == null) {
                    sel = false;
                    break;
                } else {
                    Boolean selecionado = (Boolean) tabela.getValueAt(i, 0);
                    if (selecionado == true) {
                        sel = true;
                        id_endereco = Integer.parseInt(tabela.getValueAt(i, 1).toString());
                        //Deleta registro do banco
                        String sql = "DELETE FROM ENDERECO WHERE ID_ENDERECO = "+ id_endereco;
                        conecta_banco.atualizarSQL(sql);
                        
                        tabela.removeRow(i);
                        break;
                    }
                }
            }
            if (sel == false) {
                 JOptionPane.showMessageDialog(null, "Não ha nenhum registro selecionado !");
            }
        }

    }

    //consulta endereço pelo codigo da pessoa 
    public void consultacodigo(Endereco endereco){

       conecta_banco.executeSQL("select null, endereco.id_endereco, endereco.descricao, rua, numero, bairro, cep,endereco.id_cidade, cidade.descricao,"
            +" cidade.uf, false from endereco"
            +" inner join cidade on (cidade.id_cidade = endereco.id_cidade)"
            +" where endereco.id_pessoa = "+endereco.getId_pessoa());
       
            endereco.setRetorno(conecta_banco.resultset);
    }
    


}

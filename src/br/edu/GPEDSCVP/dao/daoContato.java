/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.GPEDSCVP.dao;

import br.edu.GPEDSCVP.classe.Contato;
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
    public void addContato(Contato contato , int situacao ) throws SQLException{
        DefaultTableModel TabelaContato = (DefaultTableModel)contato.getTabela().getModel();
        ultima = new UltimaSequencia();
        
        int sequencia;
        int totlinha = contato.getTabela().getRowCount();
      
        //se for a primeira linha, gera o id do contato referente ao ultimo cadastrado no banco
        if(totlinha ==0){
            //adiciona uma linha a mais
            TabelaContato.setNumRows(totlinha + 1);
            
            TabelaContato.setValueAt(ultima.ultimasequencia("CONTATO","ID_CONTATO"), totlinha, 1);
            //Seta os demais valores dos campos em suas respectivas colunas
            if(contato.getTipo().equals("E")){
                TabelaContato.setValueAt("Email",totlinha,2);    
            }else{
                TabelaContato.setValueAt("Fone",totlinha,2);
            }
            TabelaContato.setValueAt(contato.getDescricao(),totlinha,3);
            TabelaContato.setValueAt(contato.getFone(),totlinha,4);
            TabelaContato.setValueAt(contato.getEmail(),totlinha,5);
            TabelaContato.setValueAt(0,totlinha,6);
        //se não for a primeira linha, gera o id do contato referente ao id da ultima linha da Jtable   
        }else{
            for (int i = 0; i < totlinha; i++){
                
                Integer id_existente = Integer.parseInt(TabelaContato.getValueAt(i, 1).toString()); 
           
                //Se for um contato ja existente ja Jtable
                if(contato.getId_contato() == id_existente){
                    //Seta os novos valores no contato na Jtable
                    if(contato.getTipo().equals("E")){
                        TabelaContato.setValueAt("Email",i,2);    
                    }else{
                        TabelaContato.setValueAt("Fone",i,2);
                    }
                    TabelaContato.setValueAt(contato.getDescricao(),i,3);
                    TabelaContato.setValueAt(contato.getFone(),i,4);
                    TabelaContato.setValueAt(contato.getEmail(),i,5);
                    TabelaContato.setValueAt(0,i,6);
                    break;
                //Se não existe o contato na Jtable    
                }else{

                    //Chegou na ultima linha
                    if( i == totlinha-1){
                        //adiciona uma linha a mais
                        TabelaContato.setNumRows(totlinha + 1);
                        //Se estiver em modo de alteração
                        if(situacao == Rotinas.ALTERAR){
                           //Pega o ultimo ID do banco de dados
                           sequencia = ultima.ultimasequencia("CONTATO","ID_CONTATO");
                           
                           if(sequencia > Integer.parseInt(TabelaContato.getValueAt(totlinha-1, 1).toString())){
                                //seta o ultimo id na nova linha
                                TabelaContato.setValueAt(sequencia,totlinha, 1); 
                           }else{
                                //armazena o ultimo id da Jtable
                                sequencia = Integer.parseInt(TabelaContato.getValueAt(totlinha-1, 1).toString());
                                //seta o ultimo id na nova linha
                                TabelaContato.setValueAt(sequencia+1,totlinha, 1); 
                           }
                        //Se não estiver em modo de alteração   
                        }else{
                            //armazena o ultimo id da Jtable
                            sequencia = Integer.parseInt(TabelaContato.getValueAt(totlinha-1, 1).toString());
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
                        TabelaContato.setValueAt(0,totlinha,6);
                    }
                }
            }
        }
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
    
    
    public void alterarContatos (Contato contato){
        String numero = "";
        String email = "";
        String tipo;
        DefaultTableModel tabela = (DefaultTableModel) contato.getTabela().getModel();
        int totlinha = tabela.getRowCount();
        for (int i = 0; i < totlinha; i++){
            
            Integer id = Integer.parseInt(tabela.getValueAt(i, 1).toString());
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
            Integer exc = Integer.parseInt(tabela.getValueAt(i, 6).toString());
            
            //Verifica se já existe o contato
            String sql = "select * from contato where id_contato = "+ id;
            try {
                conecta_banco.executeSQL(sql);
                conecta_banco.resultset.first();
                //se existe o contato
                if(id == conecta_banco.resultset.getInt("id_contato")){
                    //apenas altera 
                    sql = "UPDATE CONTATO SET ID_CONTATO = "+ id+","
                    + "NUMERO = '" + numero+"',"
                    + "DESCRICAO = '" + descricao+"',"
                    + "TIPO = '" + tipo+"',"
                    + "DATA_ALTER = '"+ FormatarData.dateParaTimeStamp(contato.getData_alter())+"',"
                    + "EMAIL = '" + email+"'"  
                    + " WHERE ID_CONTATO = " + id;
            
                    conecta_banco.atualizarSQL(sql);  
                }
                
            } catch (Exception e) {
                //Chegou aqui porque o contato não existe, então inclui
                if(exc == 0){
                      sql = "INSERT INTO CONTATO VALUES ("
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
            //Se for um registro excluido da Jtable
            if(exc == 1){
                //Exclui do banco
                sql = "DELETE FROM CONTATO WHERE ID_CONTATO = " + id;
                conecta_banco.atualizarSQL(sql);
            }
        }
     }
     
    //consulta contato pelo codigo da pessoa 
    public void consultacodigo(Contato contato){

       conecta_banco.executeSQL("select null,contato.id_pessoa,contato.id_contato,contato.tipo, contato.descricao,"
            + " contato.numero, contato.email, false from contato"
            + " where contato.id_pessoa = "+contato.getId_pessoa()+" order by id_contato asc");
       
            contato.setRetorno(conecta_banco.resultset);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.GPEDSCVP.dao;

import br.edu.GPEDSCVP.classe.Certificadora;
import br.edu.GPEDSCVP.classe.Fornecedor;
import br.edu.GPEDSCVP.classe.Pessoa;
import br.edu.GPEDSCVP.classe.PessoaFisica;
import br.edu.GPEDSCVP.classe.PessoaJuridica;
import br.edu.GPEDSCVP.classe.Usuario;
import br.edu.GPEDSCVP.conexao.ConexaoBanco;
import br.edu.GPEDSCVP.validacao.FormatarData;
import br.edu.GPEDSCVP.validacao.UltimaSequencia;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Willys
 */
public class daoPessoa {
    
    ConexaoBanco conecta_banco;
    UltimaSequencia ultima;
    
    public daoPessoa()
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

    public void incluir(Usuario pessoa)throws SQLException
    {
       
        //Insert de pessoa
        ultima = new UltimaSequencia();
        int sequencia = (Integer) (ultima.ultimasequencia("PESSOA","ID_PESSOA"));
        String sql = "INSERT INTO PESSOA VALUES ("
                + sequencia + ",'"
                + pessoa.getNome()+ "','"
                + pessoa.getCpf_cnpj()+ "','"
                + FormatarData.dateParaSQLDate(pessoa.getData_cadastro())+ "','"
                + FormatarData.dateParaTimeStamp(pessoa.getData_alter())+ "')";
        
                conecta_banco.incluirSQL(sql);
               
               //Insert de pessoa fisica 
               sql = "INSERT INTO PESSOA_FISICA VALUES ("
                + sequencia + ",'"
                + FormatarData.stringParaSQLDate(pessoa.getDt_nasc())+ "','"
                + pessoa.getRg()+ "','"
                + pessoa.getSexo()+ "')"; 
                
               conecta_banco.incluirSQL(sql);
               
               //Insert de usuario
               sql = "INSERT INTO USUARIO VALUES ("
                + sequencia + ",'"
                + pessoa.getLogin()+ "','"
                + pessoa.getSenha()+ "',"
                + pessoa.getIn_gerente()+ ")";
               
               conecta_banco.incluirSQL(sql);
    }
    
    public void incluir(Certificadora pessoa)throws SQLException
    {
        //Insert de pessoa
        ultima = new UltimaSequencia();
        int sequencia = (Integer) (ultima.ultimasequencia("PESSOA","ID_PESSOA"));
        String sql = "INSERT INTO PESSOA VALUES ("
                + sequencia + ",'"
                + pessoa.getNome()+ "','"
                + pessoa.getCpf_cnpj()+ "','"
                + FormatarData.dateParaSQLDate(pessoa.getData_cadastro())+ "','"
                + FormatarData.dateParaTimeStamp(pessoa.getData_alter())+ "')";
        
                conecta_banco.incluirSQL(sql);
               
               //Insert de pessoa juridica 
               sql = "INSERT INTO PESSOA_JURIDICA VALUES ("
                + sequencia + ",'"
                + pessoa.getRazao_social()+ "')"; 
                
               conecta_banco.incluirSQL(sql);
               
               //Insert de certificadora 
               sql = "INSERT INTO certificadora VALUES ("
                + sequencia + ",'"
                + pessoa.getIn_calibracoes()+ "')"; 
                
               conecta_banco.incluirSQL(sql); 
    }
    
     public void incluir(Fornecedor pessoa)throws SQLException
    {
        //Insert de pessoa
        ultima = new UltimaSequencia();
        int sequencia = (Integer) (ultima.ultimasequencia("PESSOA","ID_PESSOA"));
        String sql = "INSERT INTO PESSOA VALUES ("
                + sequencia + ",'"
                + pessoa.getNome()+ "','"
                + pessoa.getCpf_cnpj()+ "','"
                + FormatarData.dateParaSQLDate(pessoa.getData_cadastro())+ "','"
                + FormatarData.dateParaTimeStamp(pessoa.getData_alter())+ "')";
        
                conecta_banco.incluirSQL(sql);
               
               //Insert de pessoa juridica 
               sql = "INSERT INTO PESSOA_JURIDICA VALUES ("
                + sequencia + ",'"
                + pessoa.getRazao_social()+ "')"; 
                
               conecta_banco.incluirSQL(sql);
               
               //Insert de fornecedor 
               sql = "INSERT INTO fornecedor VALUES ("
                + sequencia + ",'"
                + pessoa.getSite()+"','"
                + pessoa.getRamo()+ "')"; 
                
               conecta_banco.incluirSQL(sql);
    }
     //Valida se o CPF ou CNPJ já esta cadastrado
     public Boolean verificaCpfCnpj(Pessoa pessoa){
        String sql = "select * from pessoa where pessoa.cpf_cnpj = '"+ pessoa.getCpf_cnpj()+"'";
       
         try {
                conecta_banco.executeSQL(sql);
                conecta_banco.resultset.first();
                while(conecta_banco.resultset.next()){
                    if(pessoa.getCpf_cnpj().equals(conecta_banco.resultset.getString("cpf_cnpj")) ){
                        return true;
                    }
                }
         } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Falha ao retornar dados do CPF ou CNPJ");
         }
         return false;
    }
     //Valida se o CPF ou CNPJ já esta cadastrado
     public Boolean verificaRG(Usuario pessoa){
        String sql = "select * from pessoa_fisica where rg = '"+ pessoa.getRg()+"'";
         try {
                conecta_banco.executeSQL(sql);
                conecta_banco.resultset.first();
                while(conecta_banco.resultset.next()){
                    if(pessoa.getRg().equals(conecta_banco.resultset.getString("rg")) ){
                        return true;
                    }
                }
         } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Falha ao retornar dados do RG");
         }
         return false;
    }
     
    //Valida se o Login já esta cadastrado
    public Boolean verificaLogin(Usuario pessoa){
        String sql = "select * from usuario where login = '"+ pessoa.getLogin()+"'";
         try {
                conecta_banco.executeSQL(sql);
                conecta_banco.resultset.first();
                while(conecta_banco.resultset.next()){
                    if(pessoa.getLogin().equals(conecta_banco.resultset.getString("login")) ){
                        return true;
                    }
                }
         } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Falha ao retornar dados de Login");
         }
         return false;
    }
    
    public boolean consultacodigo(Usuario pessoa){
        JOptionPane.showMessageDialog(null, pessoa.getId_pessoa());
        conecta_banco.executeSQL("select pessoa.id_pessoa,usuario.login,pessoa.nome,pessoa_juridica.razao_social,pessoa.cpf_cnpj,"
                + "pessoa_fisica.rg, pessoa_fisica.sexo,pessoa_fisica.dt_nasc,pessoa.data_alter from pessoa "
                + "inner join pessoa_fisica on (pessoa_fisica.id_pessoa = pessoa.id_pessoa)"
                + "inner join usuario on (pessoa_fisica.id_pessoa = usuario.id_usuario )"
                + "left join pessoa_juridica on (pessoa_juridica.id_pessoa = pessoa.id_pessoa )"
                + "where pessoa.id_pessoa = "+pessoa.getId_pessoa());
        
       
        
        if(conecta_banco.resultset.equals(null)){
            return false;
        }else{
             pessoa.setRetorno(conecta_banco.resultset);
        }
        return true;
    }
    
   
    public void consultageral(Pessoa pessoa){
        conecta_banco.executeSQL("SELECT * FROM PESSOA WHERE ID_PESSOA = "+ pessoa.getId_pessoa());
        pessoa.setRetorno(conecta_banco.resultset);
    }
    /*
    public void consultadescricao(ClassePessoa pessoa){
        conecta_oracle.executeSQL("SELECT * FROM CAD_PESSOA WHERE DS_PESSOA LIKE '%"+pessoa.getDspessoa()+"%'");
        pessoa.setRetorno(conecta_oracle.resultset);
    }
    */
    
     
}

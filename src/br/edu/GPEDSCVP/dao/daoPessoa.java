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
import br.edu.GPEDSCVP.util.FormatarData;
import br.edu.GPEDSCVP.util.UltimaSequencia;
import java.sql.ResultSet;
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
                + FormatarData.dateParaTimeStamp(pessoa.getData_alter())+ "','A','U')";
        
                conecta_banco.incluirSQL(sql);
               
               //Insert de pessoa fisica 
               sql = "INSERT INTO PESSOA_FISICA VALUES ("
                + sequencia + ",'"
                + FormatarData.dateParaSQLDate(pessoa.getDt_nasc())+ "','"
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
                + FormatarData.dateParaTimeStamp(pessoa.getData_alter())+ "','A','C')";
        
                conecta_banco.incluirSQL(sql);
               
               //Insert de pessoa juridica 
               sql = "INSERT INTO PESSOA_JURIDICA VALUES ("
                + sequencia + ",'"
                + pessoa.getRazao_social()+"','" 
                + pessoa.getInternacional()+"')"; 
                
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
                + FormatarData.dateParaTimeStamp(pessoa.getData_alter())+"','A','F')";
        
                conecta_banco.incluirSQL(sql);
               
               //Insert de pessoa juridica 
               sql = "INSERT INTO PESSOA_JURIDICA VALUES ("
                + sequencia + ",'"
                + pessoa.getRazao_social()+"','"
                + pessoa.getInternacional()+"')"; 
                
               conecta_banco.incluirSQL(sql);
               
               //Insert de fornecedor 
               sql = "INSERT INTO fornecedor VALUES ("
                + sequencia + ",'"
                + pessoa.getSite()+"','"
                + pessoa.getRamo()+ "')"; 
                
               conecta_banco.incluirSQL(sql);
    }
    
    public void alterar(Usuario pessoa)throws SQLException
    {
     String sql = "UPDATE PESSOA SET ID_PESSOA = "+ pessoa.getId_pessoa()+","
                + "NOME = '" + pessoa.getNome()+"',"
                + "CPF_CNPJ = '" + pessoa.getCpf_cnpj()+"',"
                + "DATA_ALTER = '" + FormatarData.dateParaTimeStamp(pessoa.getData_alter())+"'"
                + " WHERE PESSOA.ID_PESSOA = " + pessoa.getId_pessoa();
     
                conecta_banco.atualizarSQL(sql);
                
            sql = "UPDATE PESSOA_FISICA SET ID_PESSOA = "+ pessoa.getId_pessoa()+","
                + "DT_NASC = '" + pessoa.getDt_nasc()+"',"
                + "RG = '" + pessoa.getRg()+"',"
                + "SEXO = '" + pessoa.getSexo()+"'"
                + " WHERE PESSOA_FISICA.ID_PESSOA = " + pessoa.getId_pessoa();
     
                conecta_banco.atualizarSQL(sql);
                //Se não foi digitada uma nova senha
                if(pessoa.getSenha().replace(" ", "").equals("")){
                  
                    sql = "UPDATE USUARIO SET ID_USUARIO = "+ pessoa.getId_pessoa()+","
                    + "LOGIN = '" + pessoa.getLogin()+"',"
                    + "IN_GERENTE = " + pessoa.getIn_gerente()+""
                    + " WHERE USUARIO.ID_USUARIO = " + pessoa.getId_pessoa();
                }else{
                    //Se foi digitada uma nova senha
                    sql = "UPDATE USUARIO SET ID_USUARIO = "+ pessoa.getId_pessoa()+","
                    + "LOGIN = '" + pessoa.getLogin()+"',"
                    + "SENHA = '" + pessoa.getSenha()+"',"
                    + "IN_GERENTE = " + pessoa.getIn_gerente()+""
                    + " WHERE USUARIO.ID_USUARIO = " + pessoa.getId_pessoa();
                }
                conecta_banco.atualizarSQL(sql);    
    }
    
    public void alterar(Certificadora pessoa)throws SQLException
    {
     String sql = "UPDATE PESSOA SET ID_PESSOA = "+ pessoa.getId_pessoa()+","
                + "NOME = '" + pessoa.getNome()+"',"
                + "CPF_CNPJ = '" + pessoa.getCpf_cnpj()+"',"
                + "DATA_ALTER = '" + FormatarData.dateParaTimeStamp(pessoa.getData_alter())+"'"
                + " WHERE PESSOA.ID_PESSOA = " + pessoa.getId_pessoa();
     
                conecta_banco.atualizarSQL(sql);
                
            sql = "UPDATE PESSOA_JURIDICA SET ID_PESSOA = "+ pessoa.getId_pessoa()+","
                + "RAZAO_SOCIAL = '" + pessoa.getRazao_social()+"',"
                + "INTERNACIONAL = '" + pessoa.getInternacional()+"'"
                + " WHERE PESSOA_JURIDICA.ID_PESSOA = " + pessoa.getId_pessoa();
     
                conecta_banco.atualizarSQL(sql);
                 
            sql = "UPDATE CERTIFICADORA SET ID_PESSOA = "+ pessoa.getId_pessoa()+","
                + "IN_CALIBRACOES = '" + pessoa.getIn_calibracoes()+"'"
                + " WHERE CERTIFICADORA.ID_PESSOA = " + pessoa.getId_pessoa();
               
                conecta_banco.atualizarSQL(sql);    
    }
    
    
     public void alterar(Fornecedor pessoa)throws SQLException
    {
     String sql = "UPDATE PESSOA SET ID_PESSOA = "+ pessoa.getId_pessoa()+","
                + "NOME = '" + pessoa.getNome()+"',"
                + "CPF_CNPJ = '" + pessoa.getCpf_cnpj()+"',"
                + "DATA_ALTER = '" + FormatarData.dateParaTimeStamp(pessoa.getData_alter())+"'"
                + " WHERE PESSOA.ID_PESSOA = " + pessoa.getId_pessoa();
     
                conecta_banco.atualizarSQL(sql);
                
            sql = "UPDATE PESSOA_JURIDICA SET ID_PESSOA = "+ pessoa.getId_pessoa()+","
                + "RAZAO_SOCIAL = '" + pessoa.getRazao_social()+"',"
                + "INTERNACIONAL = '" + pessoa.getInternacional()+"',"
                + " WHERE PESSOA_JURIDICA.ID_PESSOA = " + pessoa.getId_pessoa();
     
                conecta_banco.atualizarSQL(sql);
                 
            sql = "UPDATE FORNECEDOR SET ID_PESSOA = "+ pessoa.getId_pessoa()+","
                + "SITE = '" + pessoa.getSite()+"',"
                + "RAMO = '" + pessoa.getRamo()+"'"
                + " WHERE FORNECEDOR.ID_PESSOA = " + pessoa.getId_pessoa();
               
                conecta_banco.atualizarSQL(sql);    
    }
    
    //Valida se o CPF ou CNPJ já esta cadastrado
    public Boolean verificaCpfCnpj(Pessoa pessoa){
        String sql = "select * from pessoa where pessoa.cpf_cnpj = '"+ pessoa.getCpf_cnpj()+"' and pessoa.in_ativo = 'A'";
       
         try {
                conecta_banco.executeSQL(sql);
                conecta_banco.resultset.first();
               
                if(pessoa.getCpf_cnpj().equals(conecta_banco.resultset.getString("cpf_cnpj")) ){
                    return true;
                }           
         } catch (Exception e) {
             return false;
         }
         return false;
    }
     //Valida se o RG já esta cadastrado
     public Boolean verificaRG(Usuario pessoa){
        String sql = "select pessoa.in_ativo, pessoa_fisica.id_pessoa, pessoa_fisica.rg from pessoa" 
                     +" inner join pessoa_fisica on (pessoa_fisica.id_pessoa = pessoa.id_pessoa)"
                     +"where pessoa_fisica.rg = '"+ pessoa.getRg()+"'and pessoa.in_ativo = 'A'";
         try {
                conecta_banco.executeSQL(sql);
                conecta_banco.resultset.first();
                
                if(pessoa.getRg().equals(conecta_banco.resultset.getString("rg")) ){
                    return true;
                }
                
         } catch (Exception e) {
             return false;
         }
         return false;
    }
     
    //Valida se o Login já esta cadastrado
    public Boolean verificaLogin(Usuario pessoa){
        String sql = "select pessoa.in_ativo, usuario.login from pessoa"
        + " inner join pessoa_fisica on (pessoa_fisica.id_pessoa = pessoa.id_pessoa)"
        + " inner join usuario on (pessoa_fisica.id_pessoa = usuario.id_usuario)  "
        + " where login = '"+ pessoa.getLogin()+"' and pessoa.in_ativo = 'A'";
         try {
                conecta_banco.executeSQL(sql);
                conecta_banco.resultset.first();
              
                if(pessoa.getLogin().equals(conecta_banco.resultset.getString("login")) ){
                    return true;
                }
                
         } catch (Exception e) {
            return false;
         }
         return false;
    }
    
    //Valida usuário e senha no momento do login
    public Boolean validaLoginSenha(Usuario pessoa){
        String sql = "select pessoa.in_ativo, usuario.login, usuario.senha, usuario.id_usuario from pessoa"
        + " inner join pessoa_fisica on (pessoa_fisica.id_pessoa = pessoa.id_pessoa)"
        + " inner join usuario on (usuario.id_usuario = pessoa_fisica.id_pessoa) "
        + " where login = '"+ pessoa.getLogin()+"' and senha = '"+pessoa.getSenha()+"' and pessoa.in_ativo = 'A'";
         try {
                conecta_banco.executeSQL(sql);
                conecta_banco.resultset.first();
              
                if(pessoa.getLogin().equals(conecta_banco.resultset.getString("login")) || 
                   pessoa.getSenha().equals(conecta_banco.resultset.getString("senha"))){
                   pessoa.setId_pessoa(conecta_banco.resultset.getInt("id_usuario"));
                   return true;
                }
                
         } catch (Exception e) {
            return false;
         }
         return false;
    }
    
    //Consulta pelo código do usuário
    public boolean consultacodigo(Usuario pessoa) {
        
        ResultSet rs = null;
        
        conecta_banco.executeSQL("select pessoa.id_pessoa,pessoa.tipo,pessoa.in_ativo, usuario.login,pessoa.nome,pessoa_juridica.razao_social,pessoa.cpf_cnpj,"
                + "pessoa_fisica.rg, pessoa_fisica.sexo,pessoa_fisica.dt_nasc,pessoa.data_alter from pessoa "
                + "inner join pessoa_fisica on (pessoa_fisica.id_pessoa = pessoa.id_pessoa)"
                + "inner join usuario on (pessoa_fisica.id_pessoa = usuario.id_usuario )"
                + "left join pessoa_juridica on (pessoa_juridica.id_pessoa = pessoa.id_pessoa )"
                + "where pessoa.id_pessoa = "+pessoa.getId_pessoa()+" and pessoa.in_ativo = 'A'");
        
                pessoa.setRetorno(conecta_banco.resultset);
        
        /*        
        pessoa.setRetorno(conecta_banco.resultset);
        //Rotina para verificar se retornou algum registro
        try {
            //Move o cursor para primeira linha do resultset, com isso verifica se há reigstro existente
                if(conecta_banco.resultset.absolute(1)){
                    return true;
                }else{
                    return false;
                }
            } catch (SQLException ex) {
                return false;
            } 
        */
        return true;
    }
    
    //Consulta pelo código certificadora
    public boolean consultacodigo(Certificadora pessoa) {
        ResultSet rs = null;
        conecta_banco.executeSQL("select pessoa.id_pessoa,pessoa.tipo,usuario.login,pessoa.nome,pessoa_juridica.razao_social,pessoa.cpf_cnpj,"
                + "pessoa_fisica.rg, pessoa_fisica.sexo,pessoa_fisica.dt_nasc,pessoa.data_alter from pessoa "
                + "left join pessoa_fisica on (pessoa_fisica.id_pessoa = pessoa.id_pessoa)"
                + "left join usuario on (pessoa_fisica.id_pessoa = usuario.id_usuario )"
                + "inner join pessoa_juridica on (pessoa_juridica.id_pessoa = pessoa.id_pessoa )"
                + "inner join certificadora on (certificadora.id_pessoa = pessoa.id_pessoa )"
                + "where pessoa.id_pessoa = "+pessoa.getId_pessoa() +" and pessoa.in_ativo = 'A'");
        
        pessoa.setRetorno(conecta_banco.resultset);
        //Rotina para verificar se retornou algum registro
        /*
        try {
            //Move o cursor para primeira linha do resultset, com isso verifica se há reigstro existente
                if(conecta_banco.resultset.absolute(1)){
                    return true;
                }else{
                    return false;
                }
            } catch (SQLException ex) {
                return false;
            }
        */
        return true;
    }
    
    //Consulta pelo código geral
    public boolean consultacodigo(Pessoa pessoa) {

        conecta_banco.executeSQL("select pessoa.id_pessoa,pessoa.tipo,usuario.login,pessoa.nome,pessoa_juridica.razao_social,pessoa.cpf_cnpj,"
                + "pessoa_fisica.rg, pessoa_fisica.sexo,pessoa_fisica.dt_nasc,pessoa.data_alter from pessoa "
                + "left join pessoa_fisica on (pessoa_fisica.id_pessoa = pessoa.id_pessoa)"
                + "left join usuario on (pessoa_fisica.id_pessoa = usuario.id_usuario )"
                + "left join pessoa_juridica on (pessoa_juridica.id_pessoa = pessoa.id_pessoa )"
                + "where pessoa.id_pessoa = "+pessoa.getId_pessoa());
        
        pessoa.setRetorno(conecta_banco.resultset);
        //Rotina para verificar se retornou algum registro
        /*
        try {
            //Move o cursor para primeira linha do resultset, com isso verifica se há reigstro existente
                if(conecta_banco.resultset.absolute(1)){
                    return true;
                }else{
                    return false;
                }
            } catch (SQLException ex) {
                return false;
            }
        */
        return true;
    }
    
    //Consulta pelo código geral
    public boolean consultacodigo(Fornecedor pessoa) {
        
         conecta_banco.executeSQL("select pessoa.id_pessoa,pessoa.tipo,usuario.login,pessoa.nome,pessoa_juridica.razao_social,pessoa.cpf_cnpj,"
            + "pessoa_fisica.rg, pessoa_fisica.sexo,pessoa_fisica.dt_nasc,pessoa.data_alter from pessoa "
            + "left join pessoa_fisica on (pessoa_fisica.id_pessoa = pessoa.id_pessoa)"
            + "left join usuario on (pessoa_fisica.id_pessoa = usuario.id_usuario )"
            + "inner join pessoa_juridica on (pessoa_juridica.id_pessoa = pessoa.id_pessoa )"
            + "inner join fornecedor on (fornecedor.id_pessoa = pessoa.id_pessoa )"
            + "where pessoa.id_pessoa = "+pessoa.getId_pessoa() +" and pessoa.in_ativo = 'A'");
        
         pessoa.setRetorno(conecta_banco.resultset);
        //Rotina para verificar se retornou algum registro
         /*
        try {
            //Move o cursor para primeira linha do resultset, com isso verifica se há reigstro existente
                if(conecta_banco.resultset.absolute(1)){
                    return true;
                }else{
                    return false;
                }
            } catch (SQLException ex) {
                return false;
            }
         */
         return true;
    }
    
    //Consulta de usuário
    public boolean consultageral(Usuario pessoa){
        conecta_banco.executeSQL("select pessoa.id_pessoa,pessoa.tipo,usuario.login,pessoa.nome,pessoa_juridica.razao_social,pessoa.cpf_cnpj,"
                + "pessoa_fisica.rg, pessoa_fisica.sexo,pessoa_fisica.dt_nasc,pessoa.data_alter from pessoa "
                + "inner join pessoa_fisica on (pessoa_fisica.id_pessoa = pessoa.id_pessoa)"
                + "inner join usuario on (pessoa_fisica.id_pessoa = usuario.id_usuario )"
                + "left join pessoa_juridica on (pessoa_juridica.id_pessoa = pessoa.id_pessoa )"
                + "where pessoa.in_ativo = 'A'");

        pessoa.setRetorno(conecta_banco.resultset);
        /*
        try {
            //Move o cursor para primeira linha do resultset, com isso verifica se há reigstro existente
                if(conecta_banco.resultset.absolute(1)){
                    return true;
                }else{
                    return false;
                }
            } catch (SQLException ex) {
                return false;
            }
        */
        return true;
    }
    
    //Consulta geral de pessoas
    public boolean consultageral(Pessoa pessoa){
        conecta_banco.executeSQL("select pessoa.id_pessoa,pessoa.tipo,usuario.login,pessoa.nome,pessoa_juridica.razao_social,pessoa.cpf_cnpj,"
                + "pessoa_fisica.rg, pessoa_fisica.sexo,pessoa_fisica.dt_nasc,pessoa.data_alter from pessoa "
                + "left join pessoa_fisica on (pessoa_fisica.id_pessoa = pessoa.id_pessoa)"
                + "left join usuario on (pessoa_fisica.id_pessoa = usuario.id_usuario )"
                + "left join pessoa_juridica on (pessoa_juridica.id_pessoa = pessoa.id_pessoa )"
                + "where pessoa.in_ativo = 'A' "
                + "order by pessoa.id_pessoa asc");

        pessoa.setRetorno(conecta_banco.resultset);
        /*
        try {
            //Move o cursor para primeira linha do resultset, com isso verifica se há reigstro existente
                if(conecta_banco.resultset.absolute(1)){
                    return true;
                }else{
                    return false;
                }
            } catch (SQLException ex) {
                return false;
            }
        */
        return true;
    }
    
    //Consulta geral de certificadoras
    public boolean consultageral(Certificadora pessoa){
        
        conecta_banco.executeSQL("select pessoa.id_pessoa,pessoa.tipo,usuario.login,pessoa.nome,pessoa_juridica.razao_social,pessoa.cpf_cnpj,"
                + "pessoa_fisica.rg, pessoa_fisica.sexo,pessoa_fisica.dt_nasc,pessoa.data_alter from pessoa "
                + "left join pessoa_fisica on (pessoa_fisica.id_pessoa = pessoa.id_pessoa)"
                + "left join usuario on (pessoa_fisica.id_pessoa = usuario.id_usuario )"
                + "inner join pessoa_juridica on (pessoa_juridica.id_pessoa = pessoa.id_pessoa )"
                + "inner join certificadora on (certificadora.id_pessoa = pessoa.id_pessoa )"
                + "where pessoa.in_ativo = 'A'");

        pessoa.setRetorno(conecta_banco.resultset);
        /*
        try {
            //Move o cursor para primeira linha do resultset, com isso verifica se há reigstro existente
            if(conecta_banco.resultset.absolute(1)){
                return true;
            }else{
                return false;
            }
        } catch (SQLException ex) {
            return false;
        }
        */
        return true;
    }
    
    //Consulta geral de fornecedores
    public boolean consultageral(Fornecedor pessoa){
         conecta_banco.executeSQL("select pessoa.id_pessoa,pessoa.tipo,usuario.login,pessoa.nome,pessoa_juridica.razao_social,pessoa.cpf_cnpj,"
            + "pessoa_fisica.rg, pessoa_fisica.sexo,pessoa_fisica.dt_nasc,pessoa.data_alter from pessoa "
            + "left join pessoa_fisica on (pessoa_fisica.id_pessoa = pessoa.id_pessoa)"
            + "left join usuario on (pessoa_fisica.id_pessoa = usuario.id_usuario )"
            + "inner join pessoa_juridica on (pessoa_juridica.id_pessoa = pessoa.id_pessoa )"
            + "inner join fornecedor on (fornecedor.id_pessoa = pessoa.id_pessoa )"
            + "where pessoa.in_ativo = 'A'");
         
            pessoa.setRetorno(conecta_banco.resultset);
             /*
            try {
            //Move o cursor para primeira linha do resultset, com isso verifica se há reigstro existente
                if(conecta_banco.resultset.absolute(1)){
                    return true;
                }else{
                    return false;
                }
            } catch (SQLException ex) {
                return false;
            }
                     */
            return true;
    }
    
    //Consulta geral pela descrição
    public boolean consultadesc(Pessoa pessoa){

        conecta_banco.executeSQL("select pessoa.id_pessoa,pessoa.tipo,usuario.login,pessoa.nome,pessoa_juridica.razao_social,pessoa.cpf_cnpj,"
                + "pessoa_fisica.rg, pessoa_fisica.sexo,pessoa_fisica.dt_nasc,pessoa.data_alter from pessoa "
                + "left join pessoa_fisica on (pessoa_fisica.id_pessoa = pessoa.id_pessoa)"
                + "left join usuario on (pessoa_fisica.id_pessoa = usuario.id_usuario )"
                + "left join pessoa_juridica on (pessoa_juridica.id_pessoa = pessoa.id_pessoa )"
                + "where pessoa.nome = '"+pessoa.getNome()+"' and pessoa.in_ativo = 'A'");
        
        pessoa.setRetorno(conecta_banco.resultset);
        /*
        try {
            //Move o cursor para primeira linha do resultset, com isso verifica se há reigstro existente
            if(conecta_banco.resultset.absolute(1)){
                return true;
            }else{
                return false;
            }
        } catch (SQLException ex) {
            return false;
        }
        */
        
        return true;
    }
    //Consulta pela descrição do usuário
    public boolean consultadesc(Usuario pessoa){
        conecta_banco.executeSQL("select pessoa.id_pessoa,pessoa.tipo,usuario.login,pessoa.nome,pessoa_juridica.razao_social,pessoa.cpf_cnpj,"
                + "pessoa_fisica.rg, pessoa_fisica.sexo,pessoa_fisica.dt_nasc,pessoa.data_alter from pessoa "
                + "inner join pessoa_fisica on (pessoa_fisica.id_pessoa = pessoa.id_pessoa)"
                + "inner join usuario on (pessoa_fisica.id_pessoa = usuario.id_usuario )"
                + "left join pessoa_juridica on (pessoa_juridica.id_pessoa = pessoa.id_pessoa )"
                + "where pessoa.nome = '"+pessoa.getNome()+"' and pessoa.in_ativo = 'A'");
        
        pessoa.setRetorno(conecta_banco.resultset);
        //Rotina para verificar se retornou algum registro
        /*
        try {
            //Move o cursor para primeira linha do resultset, com isso verifica se há reigstro existente
                if(conecta_banco.resultset.absolute(1)){
                    return true;
                }else{
                    return false;
                }
            } catch (SQLException ex) {
                return false;
            }
                */
        return true;
    }
    
    //Consulta pela descrição de certificadoras
    public boolean consultadesc(Certificadora pessoa){
        
            conecta_banco.executeSQL("select pessoa.id_pessoa,pessoa.tipo,usuario.login,pessoa.nome,pessoa_juridica.razao_social,pessoa.cpf_cnpj,"
            + "pessoa_fisica.rg, pessoa_fisica.sexo,pessoa_fisica.dt_nasc,pessoa.data_alter from pessoa "
            + "left join pessoa_fisica on (pessoa_fisica.id_pessoa = pessoa.id_pessoa)"
            + "left join usuario on (pessoa_fisica.id_pessoa = usuario.id_usuario )"
            + "inner join pessoa_juridica on (pessoa_juridica.id_pessoa = pessoa.id_pessoa )"
            + "inner join certificadora on (certificadora.id_pessoa = pessoa.id_pessoa )"
            + "where pessoa.nome = '"+pessoa.getNome()+"' and pessoa.in_ativo = 'A'");
        
        pessoa.setRetorno(conecta_banco.resultset);
        //Rotina para verificar se retornou algum registro
        
        /*
        try {
            //Move o cursor para primeira linha do resultset, com isso verifica se há reigstro existente
                if(conecta_banco.resultset.absolute(1)){
                    return true;
                }else{
                    return false;
                }
            } catch (SQLException ex) {
                return false;
            }
                */
        return true;
    }
    
    //Consulta pela descrição de fornecedores
    public boolean consultadesc(Fornecedor pessoa){
        
            conecta_banco.executeSQL("select pessoa.id_pessoa,pessoa.tipo,usuario.login,pessoa.nome,pessoa_juridica.razao_social,pessoa.cpf_cnpj,"
            + "pessoa_fisica.rg, pessoa_fisica.sexo,pessoa_fisica.dt_nasc,pessoa.data_alter from pessoa "
            + "left join pessoa_fisica on (pessoa_fisica.id_pessoa = pessoa.id_pessoa)"
            + "left join usuario on (pessoa_fisica.id_pessoa = usuario.id_usuario )"
            + "inner join pessoa_juridica on (pessoa_juridica.id_pessoa = pessoa.id_pessoa )"
            + "inner join fornecedor on (fornecedor.id_pessoa = pessoa.id_pessoa )"
            + "where pessoa.nome = '"+pessoa.getNome()+"' and pessoa.in_ativo = 'A'");
        
        pessoa.setRetorno(conecta_banco.resultset);
        //Rotina para verificar se retornou algum registro
        /*
        try {
            //Move o cursor para primeira linha do resultset, com isso verifica se há reigstro existente
                if(conecta_banco.resultset.absolute(1)){
                    return true;
                }else{
                    return false;
                }
            } catch (SQLException ex) {
                return false;
            }
                */
        return true;
    }
    //Método para retornar dados de Pessoa Fisica
    public void retornardadosUsuario(Usuario pessoa) {
            
        conecta_banco.executeSQL("SELECT pessoa.id_pessoa, pessoa.nome, pessoa.cpf_cnpj, pessoa.data_cadastro,pessoa_fisica.dt_nasc,"
        +" pessoa_fisica.rg,pessoa_fisica.sexo,usuario.login,usuario.senha,usuario.in_gerente from pessoa" 
        +" inner join pessoa_fisica on (pessoa_fisica.id_pessoa = pessoa.id_pessoa)"
        +" inner join usuario on (usuario.id_usuario = pessoa_fisica.id_pessoa)"
        +" where pessoa.id_pessoa = "+pessoa.getId_pessoa() +" and pessoa.in_ativo = 'A'");

        try {        
            conecta_banco.resultset.first();
            
            pessoa.setId_pessoa(conecta_banco.resultset.getInt("id_pessoa"));
            
            pessoa.setNome(conecta_banco.resultset.getString("nome"));
            pessoa.setCpf_cnpj(conecta_banco.resultset.getString("cpf_cnpj"));
            pessoa.setData_cadastro(conecta_banco.resultset.getDate("data_cadastro"));
            pessoa.setDt_nasc(conecta_banco.resultset.getDate("dt_nasc"));
            pessoa.setRg(conecta_banco.resultset.getString("rg"));
            pessoa.setSexo(conecta_banco.resultset.getString("sexo"));
            pessoa.setLogin(conecta_banco.resultset.getString("login"));
            pessoa.setSenha(conecta_banco.resultset.getString("senha"));
            pessoa.setIn_gerente(conecta_banco.resultset.getInt("in_gerente"));
            
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, "Falha ao retornar dados da pessoa");
        }
     } 
    
    //Método para retornar dados de Certificadora
    public void retornardadosCertificadora(Certificadora pessoa) {
            
        conecta_banco.executeSQL("SELECT pessoa.id_pessoa, pessoa.nome, pessoa.cpf_cnpj, pessoa.data_cadastro,pessoa_juridica.razao_social,"
        +" pessoa_juridica.internacional,certificadora.in_calibracoes from pessoa"
        +" inner join pessoa_juridica on (pessoa_juridica.id_pessoa = pessoa.id_pessoa)"
        +" left join certificadora on (certificadora.id_pessoa = pessoa_juridica.id_pessoa)"
        +" where pessoa.id_pessoa = "+pessoa.getId_pessoa() +" and pessoa.in_ativo = 'A'");

        try {        
 
            conecta_banco.resultset.first();
            
            pessoa.setId_pessoa(conecta_banco.resultset.getInt("id_pessoa"));
            pessoa.setNome(conecta_banco.resultset.getString("nome"));
            pessoa.setCpf_cnpj(conecta_banco.resultset.getString("cpf_cnpj"));
            pessoa.setData_cadastro(conecta_banco.resultset.getDate("data_cadastro"));
            pessoa.setRazao_social(conecta_banco.resultset.getString("razao_social"));
            pessoa.setIn_calibracoes(conecta_banco.resultset.getString("in_calibracoes"));

        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, "Falha ao retornar dados da pessoa");
        }
     }
    
    //Método para retornar dados de Certificadora
    public void retornardadosFornecedor(Fornecedor pessoa) {
            
        conecta_banco.executeSQL("SELECT pessoa.id_pessoa, pessoa.nome, pessoa.cpf_cnpj, pessoa.data_cadastro,pessoa_juridica.razao_social,pessoa_juridica.internacional,fornecedor.site,"
        +" fornecedor.ramo from pessoa"
        +" inner join pessoa_juridica on (pessoa_juridica.id_pessoa = pessoa.id_pessoa)"
        +" inner join fornecedor on (fornecedor.id_pessoa = pessoa_juridica.id_pessoa)"
        +" where pessoa.id_pessoa = "+pessoa.getId_pessoa() +" and pessoa.in_ativo = 'A'");

        try {        
 
            conecta_banco.resultset.first();
            
            pessoa.setId_pessoa(conecta_banco.resultset.getInt("id_pessoa"));
            pessoa.setNome(conecta_banco.resultset.getString("nome"));
            pessoa.setCpf_cnpj(conecta_banco.resultset.getString("cpf_cnpj"));
            pessoa.setData_cadastro(conecta_banco.resultset.getDate("data_cadastro"));
            pessoa.setRazao_social(conecta_banco.resultset.getString("razao_social"));
            pessoa.setInternacional(conecta_banco.resultset.getString("internacional"));
            pessoa.setRamo(conecta_banco.resultset.getString("ramo"));
            pessoa.setSite(conecta_banco.resultset.getString("site"));
   
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao retornar dados da pessoa");
        }
     }
    
    public void inativaPessoa(Pessoa pessoa){
            
        conecta_banco.atualizarSQL("UPDATE PESSOA SET IN_ATIVO = 'I'"
                               + " WHERE ID_PESSOA = " + pessoa.getId_pessoa());
    }  
    
    
}

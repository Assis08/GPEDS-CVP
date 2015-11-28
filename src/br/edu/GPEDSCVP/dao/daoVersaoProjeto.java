/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.GPEDSCVP.dao;

import br.edu.GPEDSCVP.classe.VersaoProjeto;
import br.edu.GPEDSCVP.conexao.ConexaoBanco;
import br.edu.GPEDSCVP.util.ExcessaoBanco;
import br.edu.GPEDSCVP.util.FormatarData;
import br.edu.GPEDSCVP.util.UltimaSequencia;
import java.sql.SQLException;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;

/**
 *
 * @author Willys
 */
public class daoVersaoProjeto {
    
    ConexaoBanco conecta_banco;
    UltimaSequencia ultima;
    
    public daoVersaoProjeto()
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
    
    public void consultaCodigo(VersaoProjeto projeto){
        
        conecta_banco.executeSQL("select * from versao_projeto where in_Ativo = 'A' and id_projeto = "+projeto.getId_projeto());

        projeto.setRetorno(conecta_banco.resultset);
    }
    
    public void retornardados(VersaoProjeto versao){
        String sql = "select * from versao_projeto where id_projeto = "+versao.getId_projeto()+" and versao = "+versao.getVersao()
        +" and in_ativo = 'A'";
           
        conecta_banco.executeSQL(sql);
        try {        
            
            conecta_banco.resultset.first();
            versao.setCod_vers_projeto(conecta_banco.resultset.getInt("cod_vers_projeto"));
            versao.setId_projeto(conecta_banco.resultset.getInt("id_projeto"));
            versao.setVersao(conecta_banco.resultset.getDouble("versao"));
            versao.setComercializado(conecta_banco.resultset.getString("comercializado"));
            versao.setLote(conecta_banco.resultset.getInt("lote"));
            versao.setCertificacao(conecta_banco.resultset.getString("certificacao"));
            versao.setData_cadastro(conecta_banco.resultset.getDate("data_cadastro"));
            versao.setData_alter(conecta_banco.resultset.getDate("data_alter"));
           
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao retornar dados da versão");
        }
       
    }
    
    public Double criaCodigoVersao(VersaoProjeto versao, String tipo_inc){
        
        Integer versao_prim_casa = 0;
        Double cod_versao = 0.0;
        String valor;
        //Verifica se existe versões para este projeto
        String sql = "select max(versao) versao from versao_projeto where id_projeto = "+versao.getId_projeto()+" and  in_ativo = 'A'";
           
        conecta_banco.executeSQL(sql);
        try {
            conecta_banco.resultset.first();
            cod_versao = conecta_banco.resultset.getDouble("versao");
   
            if(cod_versao > 0.0){
                //Incremente casa antes da virgula
                if(tipo_inc.equals("PRIMEIRA_CASA")){

                    valor = String.valueOf(cod_versao).replace(".", "");
                    versao_prim_casa = Integer.parseInt(valor) / 10;
                    cod_versao = Double.parseDouble(versao_prim_casa.toString()) + 1;

                }else if(tipo_inc.equals("SEGUNDA_CASA")){
                    //incrementa casa após a virgula
                   String nova_versao;
                   DecimalFormat fmt = new DecimalFormat("0.0");
                   cod_versao = cod_versao + 0.1;
                   nova_versao = fmt.format(cod_versao).replace(",", ".");
                   cod_versao = Double.parseDouble(nova_versao);
                }
            }else{
                cod_versao = 1.0;
            }
        } catch (SQLException ex) {
            cod_versao = 1.0;
        }
        return cod_versao;
    }
    
     //Método de incluir uma nova versão no banco
    public boolean incluir(VersaoProjeto versao) throws SQLException{
        //Insert de versão
        ultima = new UltimaSequencia();
        int resultado;

        int sequencia = (Integer) (ultima.ultimasequencia("VERSAO_PROJETO","COD_VERS_PROJETO"));

        resultado = conecta_banco.executeSQL("INSERT INTO versao_projeto (cod_vers_projeto , id_projeto, versao, in_ativo, comercializado, lote, certificacao,"
        + "data_cadastro,data_alter) "
        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) ",
        sequencia,
        versao.getId_projeto(),
        versao.getVersao(),
        "A",
        versao.getComercializado(),
        versao.getLote(),
        versao.getCertificacao(),
        FormatarData.dateParaSQLDate(versao.getData_cadastro()),
        FormatarData.dateParaTimeStamp(versao.getData_alter()));

        if(resultado == ExcessaoBanco.ERRO_LIMITE_CARACTERES){
            return false;
        }else if(resultado == ExcessaoBanco.OUTROS_ERROS){
            return false;
        }else if (resultado == ExcessaoBanco.ERRO_LIMITE_ARQUIVO){
            return false;
        }
        return true;    
    }
    
    //Método de alterar fornecimento no banco
    public boolean alterar(VersaoProjeto versao) throws SQLException {
       
        int resultado;
       
        resultado = conecta_banco.executeSQL("UPDATE versao_projeto SET comercializado = ?, lote = ?, certificacao = ?, data_alter = ? "
        + "WHERE cod_vers_projeto = ? ",
        versao.getComercializado(),
        versao.getLote(),
        versao.getCertificacao(),
        versao.getData_alter(),
        versao.getCod_vers_projeto());

        if(resultado == ExcessaoBanco.ERRO_LIMITE_CARACTERES){
            return false;
        }else if(resultado == ExcessaoBanco.OUTROS_ERROS){
            return false;
        }else if (resultado == ExcessaoBanco.ERRO_LIMITE_ARQUIVO){
            return false;
        }
        return true;    
    }
}

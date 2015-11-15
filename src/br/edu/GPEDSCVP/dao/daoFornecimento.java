/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.GPEDSCVP.dao;

import br.edu.GPEDSCVP.classe.ComponenteFornecimento;
import br.edu.GPEDSCVP.classe.Fornecimento;
import br.edu.GPEDSCVP.conexao.ConexaoBanco;
import br.edu.GPEDSCVP.util.ExcessaoBanco;
import br.edu.GPEDSCVP.util.FormatarData;
import br.edu.GPEDSCVP.util.Rotinas;
import br.edu.GPEDSCVP.util.UltimaSequencia;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Willys
 */
public class daoFornecimento {
    
    ConexaoBanco conecta_banco;
    UltimaSequencia ultima;
    
    public daoFornecimento()
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
    
    
     //Método de incluir componente no banco
    public boolean incluir(Fornecimento fornecimento) throws SQLException{
        //Insert de fornecimento
        ultima = new UltimaSequencia();
        int resultado;

        int sequencia = (Integer) (ultima.ultimasequencia("FORNECIMENTO","ID_FORNECIMENTO"));

        System.out.println("id_moeda_frete" + fornecimento.getId_moeda_frete());
        System.out.println("id_moeda_imposto" + fornecimento.getId_moeda_imp());
        
        resultado = conecta_banco.executeSQL("INSERT INTO fornecimento (id_fornecimento , id_pessoa, descricao, data_cadastro, id_moeda_frete, vl_frete, id_moeda_imp,"
                + "vl_impostos,data_alter ) "
        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) ",
        sequencia,
        fornecimento.getId_pessoa(),
        fornecimento.getDescricao(),
        FormatarData.dateParaTimeStamp(fornecimento.getData_cadastro()),
        fornecimento.getId_moeda_frete(),
        fornecimento.getValor_frete(),
        fornecimento.getId_moeda_imp(),
        fornecimento.getValor_impostos(),
        FormatarData.dateParaTimeStamp(fornecimento.getData_alter()));

        if(resultado == ExcessaoBanco.ERRO_LIMITE_CARACTERES){
            return false;
        }else if(resultado == ExcessaoBanco.OUTROS_ERROS){
            return false;
        }else if (resultado == ExcessaoBanco.ERRO_LIMITE_ARQUIVO){
            return false;
        }
        return true;    
    }
    
    //Consulta geral de fornecimentos
    public void consultageral(Fornecimento fornecimento){
        conecta_banco.executeSQL("select fornecimento.id_fornecimento,fornecimento.descricao,fornecedor.id_pessoa,pessoa.nome,id_moeda_frete,moeda_frete.unidade,"
                               + " vl_frete,id_moeda_imp,moeda_imposto.unidade,vl_impostos,fornecimento.data_cadastro,fornecimento.data_alter from fornecimento"
                               + " inner join fornecedor on (fornecedor.id_pessoa = fornecimento.id_pessoa)"
                               + " inner join pessoa_juridica on (pessoa_juridica.id_pessoa = fornecedor.id_pessoa)"
                               + " inner join pessoa on (pessoa.id_pessoa = pessoa_juridica.id_pessoa)"
                               + " inner join moeda moeda_frete on (fornecimento.id_moeda_frete = moeda_frete.id_moeda)"
                               + " inner join moeda moeda_imposto on (fornecimento.id_moeda_imp = moeda_imposto.id_moeda) order by fornecimento.data_cadastro desc");

        fornecimento.setRetorno(conecta_banco.resultset);

    }
    
    //Consulta codigo fornecimentos
    public void consultaCodigoFornec(Fornecimento fornecimento){
        conecta_banco.executeSQL("select fornecimento.id_fornecimento,fornecimento.descricao,fornecedor.id_pessoa,pessoa.nome,id_moeda_frete,moeda_frete.unidade,"
                               + " vl_frete,id_moeda_imp,moeda_imposto.unidade,vl_impostos,fornecimento.data_cadastro,fornecimento.data_alter from fornecimento"
                               + " inner join fornecedor on (fornecedor.id_pessoa = fornecimento.id_pessoa)"
                               + " inner join pessoa_juridica on (pessoa_juridica.id_pessoa = fornecedor.id_pessoa)"
                               + " inner join pessoa on (pessoa.id_pessoa = pessoa_juridica.id_pessoa)"
                               + " inner join moeda moeda_frete on (fornecimento.id_moeda_frete = moeda_frete.id_moeda)"
                               + " inner join moeda moeda_imposto on (fornecimento.id_moeda_imp = moeda_imposto.id_moeda) where id_fornecimento = "+fornecimento.getId_fornecimento()
                               + " group by (fornecimento.id_fornecimento)" 
                               + " order by fornecimento.data_cadastro desc");

        fornecimento.setRetorno(conecta_banco.resultset);

    }
    
    //Consulta fornecimentos pelo código do componente
    public void consultaCodigoCompFornec(Fornecimento fornecimento, int id_componente){
        conecta_banco.executeSQL("select fornecimento.id_fornecimento,fornecimento.descricao,fornecedor.id_pessoa,pessoa.nome,id_moeda_frete,moeda_frete.unidade,"
                               + " vl_frete,id_moeda_imp,moeda_imposto.unidade,vl_impostos,fornecimento.data_cadastro,fornecimento.data_alter,componentes_fornecimento.id_componente from fornecimento"
                               + " inner join fornecedor on (fornecedor.id_pessoa = fornecimento.id_pessoa)"
                               + " inner join pessoa_juridica on (pessoa_juridica.id_pessoa = fornecedor.id_pessoa)"
                               + " inner join pessoa on (pessoa.id_pessoa = pessoa_juridica.id_pessoa)"
                               + " inner join moeda moeda_frete on (fornecimento.id_moeda_frete = moeda_frete.id_moeda)"
                               + " inner join moeda moeda_imposto on (fornecimento.id_moeda_imp = moeda_imposto.id_moeda)"
                               + " inner join componentes_fornecimento on (componentes_fornecimento.id_fornecimento = fornecimento.id_fornecimento)"
                               + " where componentes_fornecimento.id_componente = "+id_componente
                               + " group by (fornecimento.id_fornecimento)"
                               + " order by fornecimento.data_cadastro desc");

        fornecimento.setRetorno(conecta_banco.resultset);

    }
    
    //Consulta fornecimentos pelo código do componente
    public void consultaCodigoProjFornec(Fornecimento fornecimento, int id_projeto){
        conecta_banco.executeSQL("select fornecimento.id_fornecimento,fornecimento.descricao,fornecedor.id_pessoa,pessoa.nome,id_moeda_frete,moeda_frete.unidade,"
                               + " vl_frete,id_moeda_imp,moeda_imposto.unidade,vl_impostos,fornecimento.data_cadastro,fornecimento.data_alter,componentes_versao_projeto.id_projeto from fornecimento"
                               + " inner join fornecedor on (fornecedor.id_pessoa = fornecimento.id_pessoa)"
                               + " inner join pessoa_juridica on (pessoa_juridica.id_pessoa = fornecedor.id_pessoa)"
                               + " inner join pessoa on (pessoa.id_pessoa = pessoa_juridica.id_pessoa)"
                               + " inner join moeda moeda_frete on (fornecimento.id_moeda_frete = moeda_frete.id_moeda)"
                               + " inner join moeda moeda_imposto on (fornecimento.id_moeda_imp = moeda_imposto.id_moeda)"
                               + " inner join componentes_fornecimento on (componentes_fornecimento.id_fornecimento = fornecimento.id_fornecimento)"
                               + " inner join componentes_versao_projeto on (componentes_versao_projeto.id_comp_fornec = componentes_fornecimento.id_comp_fornec)"
                               + " where componentes_versao_projeto.id_projeto = "+id_projeto
                               + " group by (fornecimento.id_fornecimento)"
                               + " order by fornecimento.data_cadastro desc");

        fornecimento.setRetorno(conecta_banco.resultset);

    }
    
    //Consulta codigo fornecimentos
    public void consultaDescFornec(Fornecimento fornecimento){
        conecta_banco.executeSQL("select fornecimento.id_fornecimento,fornecimento.descricao,fornecedor.id_pessoa,pessoa.nome,id_moeda_frete,moeda_frete.unidade,"
                               + " vl_frete,id_moeda_imp,moeda_imposto.unidade,vl_impostos,fornecimento.data_cadastro,fornecimento.data_alter,fornecimento.descricao from fornecimento"
                               + " inner join fornecedor on (fornecedor.id_pessoa = fornecimento.id_pessoa)"
                               + " inner join pessoa_juridica on (pessoa_juridica.id_pessoa = fornecedor.id_pessoa)"
                               + " inner join pessoa on (pessoa.id_pessoa = pessoa_juridica.id_pessoa)"
                               + " inner join moeda moeda_frete on (fornecimento.id_moeda_frete = moeda_frete.id_moeda)"
                               + " inner join moeda moeda_imposto on (fornecimento.id_moeda_imp = moeda_imposto.id_moeda) where fornecimento.descricao like '"+fornecimento.getDescricao()+"%'"                                                                                                              
                               + " group by (fornecimento.id_fornecimento)"
                               + " order by fornecimento.data_cadastro desc");

        fornecimento.setRetorno(conecta_banco.resultset);
    }
    
    //Consulta fornecimentos pelo código do componente
    public void consultaDescCompFornec(Fornecimento fornecimento, String ds_componente){
        conecta_banco.executeSQL("select fornecimento.id_fornecimento,fornecimento.descricao,fornecedor.id_pessoa,pessoa.nome,id_moeda_frete,moeda_frete.unidade,"
                               + " vl_frete,id_moeda_imp,moeda_imposto.unidade,vl_impostos,fornecimento.data_cadastro,fornecimento.data_alter,componente.descricao from fornecimento"
                               + " inner join fornecedor on (fornecedor.id_pessoa = fornecimento.id_pessoa)"
                               + " inner join pessoa_juridica on (pessoa_juridica.id_pessoa = fornecedor.id_pessoa)"
                               + " inner join pessoa on (pessoa.id_pessoa = pessoa_juridica.id_pessoa)"
                               + " inner join moeda moeda_frete on (fornecimento.id_moeda_frete = moeda_frete.id_moeda)"
                               + " inner join moeda moeda_imposto on (fornecimento.id_moeda_imp = moeda_imposto.id_moeda)"
                               + " inner join componentes_fornecimento on (componentes_fornecimento.id_fornecimento = fornecimento.id_fornecimento)"
                               + " inner join componente on (componente.id_componente = componentes_fornecimento.id_componente)"
                               + " where componente.descricao like '"+ds_componente+"%'"
                               + " group by (fornecimento.id_fornecimento)"
                               + " order by fornecimento.data_cadastro desc");

        fornecimento.setRetorno(conecta_banco.resultset);
    }
    
    //Consulta fornecimentos pela descrição do projeto
    public void consultaDescProjFornec(Fornecimento fornecimento, String ds_projeto){
        conecta_banco.executeSQL("select fornecimento.id_fornecimento,fornecimento.descricao,fornecedor.id_pessoa,pessoa.nome,id_moeda_frete,moeda_frete.unidade,"
                               + " vl_frete,id_moeda_imp,moeda_imposto.unidade,vl_impostos,fornecimento.data_cadastro,fornecimento.data_alter,projeto.descricao from fornecimento"
                               + " inner join fornecedor on (fornecedor.id_pessoa = fornecimento.id_pessoa)"
                               + " inner join pessoa_juridica on (pessoa_juridica.id_pessoa = fornecedor.id_pessoa)"
                               + " inner join pessoa on (pessoa.id_pessoa = pessoa_juridica.id_pessoa)"
                               + " inner join moeda moeda_frete on (fornecimento.id_moeda_frete = moeda_frete.id_moeda)"
                               + " inner join moeda moeda_imposto on (fornecimento.id_moeda_imp = moeda_imposto.id_moeda)"
                               + " inner join componentes_fornecimento on (componentes_fornecimento.id_fornecimento = fornecimento.id_fornecimento)"
                               + " inner join componentes_versao_projeto on (componentes_versao_projeto.id_comp_fornec = componentes_fornecimento.id_comp_fornec)"
                               + " inner join versao_projeto on (componentes_versao_projeto.cod_vers_projeto = versao_projeto.cod_vers_projeto)"
                               + " inner join projeto on (versao_projeto.id_projeto = projeto.id_projeto)"
                               + " where projeto.descricao like '"+ds_projeto+"%'"
                               + " group by (fornecimento.id_fornecimento)"
                               + " order by fornecimento.data_cadastro desc");

        fornecimento.setRetorno(conecta_banco.resultset);

    }
    
      //Método para retornar dados do componente
    public void retornardadosFornecimento(Fornecimento fornecimento) {
            
         conecta_banco.executeSQL("select fornecimento.id_fornecimento,fornecimento.descricao,fornecedor.id_pessoa,pessoa.nome,id_moeda_frete,moeda_frete.unidade,"
                               + " vl_frete,id_moeda_imp,moeda_imposto.unidade,vl_impostos,fornecimento.data_cadastro,fornecimento.data_alter from fornecimento"
                               + " inner join fornecedor on (fornecedor.id_pessoa = fornecimento.id_pessoa)"
                               + " inner join pessoa_juridica on (pessoa_juridica.id_pessoa = fornecedor.id_pessoa)"
                               + " inner join pessoa on (pessoa.id_pessoa = pessoa_juridica.id_pessoa)"
                               + " inner join moeda moeda_frete on (fornecimento.id_moeda_frete = moeda_frete.id_moeda)"
                               + " inner join moeda moeda_imposto on (fornecimento.id_moeda_imp = moeda_imposto.id_moeda) where fornecimento.id_fornecimento = "+fornecimento.getId_fornecimento());

        try {        
 
            conecta_banco.resultset.first();
            
            fornecimento.setId_fornecimento(conecta_banco.resultset.getInt("id_fornecimento"));
            fornecimento.setDescricao(conecta_banco.resultset.getString("descricao"));
            fornecimento.setDs_pessoa(conecta_banco.resultset.getString("pessoa.nome"));
            fornecimento.setId_moeda_frete(conecta_banco.resultset.getInt("fornecimento.id_moeda_frete"));
            fornecimento.setDs_moeda_frete(conecta_banco.resultset.getString("moeda_frete.unidade"));
            fornecimento.setValor_frete(conecta_banco.resultset.getDouble("vl_frete"));
            fornecimento.setId_moeda_imp(conecta_banco.resultset.getInt("fornecimento.id_moeda_imp"));
            fornecimento.setDs_moeda_imp(conecta_banco.resultset.getString("moeda_imposto.unidade"));
            fornecimento.setValor_impostos(conecta_banco.resultset.getDouble("vl_impostos"));
            fornecimento.setData_cadastro(conecta_banco.resultset.getDate("fornecimento.data_cadastro"));
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao retornar dados do fornecimento");
        }
     }
}

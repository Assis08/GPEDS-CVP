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

        resultado = conecta_banco.executeSQL("INSERT INTO fornecimento (id_fornecimento , id_pessoa, descricao, data_fornecimento, id_moeda_frete, vl_frete, id_moeda_imp,"
                + "vl_impostos,data_alter ) "
        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) ",
        sequencia,
        fornecimento.getId_pessoa(),
        fornecimento.getDescricao(),
        FormatarData.dateParaSQLDate(fornecimento.getData_cadastro()),
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
    
    //Consulta geral de componentes
    public void consultageral(Fornecimento fornecimento){
        conecta_banco.executeSQL("select fornecimento.id_fornecimento,fornecimento.descricao,fornecedor.id_pessoa,pessoa.nome,id_moeda_frete,moeda_frete.unidade,"
                               + " vl_frete,id_moeda_imp,moeda_imposto.unidade,vl_impostos,data_fornecimento,fornecimento.data_alter from fornecimento"
                               + " inner join fornecedor on (fornecedor.id_pessoa = fornecimento.id_pessoa)"
                               + " inner join pessoa_juridica on (pessoa_juridica.id_pessoa = fornecedor.id_pessoa)"
                               + " inner join pessoa on (pessoa.id_pessoa = pessoa_juridica.id_pessoa)"
                               + " inner join moeda moeda_frete on (fornecimento.id_moeda_frete = moeda_frete.id_moeda)"
                               + " inner join moeda moeda_imposto on (fornecimento.id_moeda_imp = moeda_imposto.id_moeda)");

        fornecimento.setRetorno(conecta_banco.resultset);

    }
}

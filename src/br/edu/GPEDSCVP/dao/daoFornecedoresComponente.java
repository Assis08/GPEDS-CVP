/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.GPEDSCVP.dao;

import br.edu.GPEDSCVP.classe.FornecedoresComponente;
import br.edu.GPEDSCVP.conexao.ConexaoBanco;
import br.edu.GPEDSCVP.util.UltimaSequencia;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Willys
 */
public class daoFornecedoresComponente {
    
    ConexaoBanco conecta_banco;
    UltimaSequencia ultima;
    
    public daoFornecedoresComponente()
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
    
    //Consulta pelo codigo de componentes mecânicos
    public void consultaFornecedoresComponente(FornecedoresComponente fornecomp){
        conecta_banco.executeSQL("select id_fornecedores_comp,id_componente,fornecedor_componente.id_pessoa, pessoa.nome, pessoa.cpf_cnpj, fornecedor.site from fornecedor_componente" 
                               + " inner join fornecedor on (fornecedor.id_pessoa = fornecedor_componente.id_pessoa)" 
                               + " inner join pessoa_juridica on (fornecedor.id_pessoa = pessoa_juridica.id_pessoa)" 
                               + " inner join pessoa on (pessoa_juridica.id_pessoa = pessoa.id_pessoa)"
                               + " where id_componente = "+fornecomp.getId_componente()+" order by fornecedor_componente.id_pessoa asc");
                               fornecomp.setRetorno(conecta_banco.resultset);
    }
    
}

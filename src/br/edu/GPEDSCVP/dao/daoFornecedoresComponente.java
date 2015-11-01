/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.GPEDSCVP.dao;

import br.edu.GPEDSCVP.classe.FornecedoresComponente;
import br.edu.GPEDSCVP.conexao.ConexaoBanco;
import br.edu.GPEDSCVP.util.FormatarData;
import br.edu.GPEDSCVP.util.UltimaSequencia;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

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
        conecta_banco.executeSQL("select null, id_fornecedores_comp,id_componente,fornecedor_componente.id_pessoa, pessoa.nome, pessoa.cpf_cnpj, fornecedor.site, false from fornecedor_componente" 
                               + " inner join fornecedor on (fornecedor.id_pessoa = fornecedor_componente.id_pessoa)" 
                               + " inner join pessoa_juridica on (fornecedor.id_pessoa = pessoa_juridica.id_pessoa)" 
                               + " inner join pessoa on (pessoa_juridica.id_pessoa = pessoa.id_pessoa)"
                               + " where id_componente = "+fornecomp.getId_componente()+" order by fornecedor_componente.id_pessoa asc");
                               fornecomp.setRetorno(conecta_banco.resultset);
    }
    
    public void alterarFornecedores (FornecedoresComponente fornecedores){
        
        Integer id_fornec_comp;
        Integer id_fornecedor;

        DefaultTableModel tabela = (DefaultTableModel) fornecedores.getTabela().getModel();
        int totlinha = tabela.getRowCount();
        for (int i = 0; i < totlinha; i++){
            id_fornec_comp = Integer.parseInt(tabela.getValueAt(i, 1).toString());
            id_fornecedor = Integer.parseInt(tabela.getValueAt(i, 2).toString());
            Integer exc = Integer.parseInt(tabela.getValueAt(i, 6).toString());

            //Verifica se já existe o fornecedor cadastrado nos fornecedores do componente
            String sql = "select * from fornecedor_componente where id_fornecedores_comp = "+ id_fornec_comp;
            try {
                conecta_banco.executeSQL(sql);
                conecta_banco.resultset.first();
                //se existe o fornecedor nos fornecedores do componente
                if(id_fornec_comp == conecta_banco.resultset.getInt("id_fornecedores_comp")){
                    //apenas altera 
                    sql = "UPDATE FORNECEDOR_COMPONENTE SET ID_FORNECEDORES_COMP = "+ id_fornec_comp+","
                    + "ID_PESSOA = " + id_fornecedor+","
                    + "ID_COMPONENTE = " + fornecedores.getId_componente()+","
                    + "DATA_CADASTRO = '" + FormatarData.dateParaSQLDate(fornecedores.getData_cadastro()) +"',"
                    + "DATA_ALTER = '"+ FormatarData.dateParaTimeStamp(fornecedores.getData_alter())+"' "
                    + " WHERE ID_FORNECEDORES_COMP = " + id_fornec_comp;
            
                    conecta_banco.atualizarSQL(sql);  
                }

            } catch (Exception e) {
                //Chegou aqui porque os fornecedor não foi adicionado para esse componente
               
                 sql = "INSERT INTO FORNECEDOR_COMPONENTE VALUES ("
                    +id_fornec_comp + ","
                    +id_fornecedor+","
                    +fornecedores.getId_componente() +",'"
                    +FormatarData.dateParaSQLDate(fornecedores.getData_cadastro()) + "','"
                    + FormatarData.dateParaTimeStamp(fornecedores.getData_alter())+"','A')";
            
                    conecta_banco.incluirSQL(sql);
            }
            
            //Se for um registro excluido da Jtable
            if(exc == 1){
                //Exclui do banco
                sql = "DELETE FROM FORNECEDOR_COMPONENTE WHERE ID_FORNECEDORES_COMP = " + id_fornec_comp;
                conecta_banco.atualizarSQL(sql);
            }
        }

    }
    
    
}

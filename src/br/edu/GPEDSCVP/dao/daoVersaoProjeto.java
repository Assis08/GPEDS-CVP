/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.GPEDSCVP.dao;

import br.edu.GPEDSCVP.classe.Componente;
import br.edu.GPEDSCVP.classe.ComponenteVersaoProjeto;
import br.edu.GPEDSCVP.classe.VersaoProjeto;
import br.edu.GPEDSCVP.conexao.ConexaoBanco;
import br.edu.GPEDSCVP.util.ExcessaoBanco;
import br.edu.GPEDSCVP.util.FormatarData;
import br.edu.GPEDSCVP.util.UltimaSequencia;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Willys
 */
public class daoVersaoProjeto {
    
    ConexaoBanco conecta_banco;
    UltimaSequencia ultima;
    daoComponente dao_comp = new daoComponente();
    daoComponenteVersaoProjeto dao_comp_vers = new daoComponenteVersaoProjeto();
    daoMoeda  dao_moeda = new daoMoeda();
    Componente componente = new Componente();
    
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
    
    public boolean SalvarCompNoProjeto (ComponenteVersaoProjeto comp_vers_proj, JTable tabela_comp){
        
        Integer id_componente;
        Integer id_comp_vers;
        Integer nova_qntd;
        Integer qntd_atual_no_projeto = 0;
        Integer id_comp_atualizar;
        
        ResultSet resultset_comp_fornec;

        DefaultTableModel tabela = (DefaultTableModel)tabela_comp.getModel();
        int totlinha = tabela.getRowCount();
        for (int i = 0; i < totlinha; i++){
            
            id_comp_vers = Integer.parseInt(tabela.getValueAt(i, 1).toString());
            id_componente = Integer.parseInt(tabela.getValueAt(i, 2).toString());
            nova_qntd = Integer.parseInt(tabela.getValueAt(i, 7).toString());
            
            componente.setId_componente(id_componente);
            comp_vers_proj.setId_componente(id_componente);
            comp_vers_proj.setId_comp_versao(id_comp_vers);
            
            //verifica se o componente possui composicao
            if(dao_comp.verificaExisteComposicao(componente) == true){
                
                //**************verifica pois esta com erro***********************
                dao_comp_vers.atualizaQntdFornecComposicaoComponente(comp_vers_proj);
            
            }else{
              //não possui composição então atualiza a quantidade para projeto do componente
                
              resultset_comp_fornec = retornaCompFornecVersProj(comp_vers_proj);
              //percorre o resultset de todos fornecimento do componente especifico para o projeto
                try {
                    while ( resultset_comp_fornec.next()) {
                        
                        id_comp_atualizar =  resultset_comp_fornec.getInt("id_comp_versao");
                        qntd_atual_no_projeto =  resultset_comp_fornec.getInt("qntd_no_projeto");

                        int resultado;
       
                        resultado = conecta_banco.executeSQL("UPDATE componentes_versao_projeto SET qntd_no_projeto = ?, situacao = ? "
                        + "WHERE id_comp_versao = ? ",
                        nova_qntd,
                        "C",
                        id_comp_atualizar);

                        if(resultado == ExcessaoBanco.ERRO_LIMITE_CARACTERES){
                            return false;
                        }else if(resultado == ExcessaoBanco.OUTROS_ERROS){
                            return false;
                        }else if (resultado == ExcessaoBanco.ERRO_LIMITE_ARQUIVO){
                            return false;
                        }
                    } 
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Falha ao converter valores dos componentes");
                }
            }
        }
          return true;    
     }
    
    //retorna dados do fornecimento de um componente me especifico
    public ResultSet retornaCompFornecVersProj(ComponenteVersaoProjeto comp_vers_proj){
        
        conecta_banco.executeSQL("select * from componentes_versao_projeto"
        +" inner join componentes_fornecimento on (componentes_fornecimento.id_comp_fornec = componentes_versao_projeto.id_comp_fornec)"
        +" where componentes_versao_projeto.id_comp_versao = "+comp_vers_proj.getId_comp_versao()+ " and componentes_fornecimento.in_ativo = 'A'");

        return conecta_banco.resultset;

    }
    
 
    
    //Método para calcular o custo do componente que possui composição
    public Double calculaComposicaoComponente(ComponenteVersaoProjeto componente){
        Integer id_componente_composicao;
        Integer id_moeda;
        Integer qntd_componente_composicao;
        Timestamp data_fornec;
        Double valor_unit = 0.0;
        Double total_composicao = 0.0;
        ResultSet result_composicao = null;
        ResultSet result_valor_comp = null;
        //faz a consulta de composição do componente
        conecta_banco.executeSQL("select * from composicao_componente where id_componente = "+componente.getId_componente());
        result_composicao = conecta_banco.resultset;
        try {
            while ( result_composicao.next()) {
                
                id_componente_composicao = result_composicao.getInt("id_subcomponente");
                qntd_componente_composicao = result_composicao.getInt("qntd");
                componente.setId_componente(id_componente_composicao);
                
             
                //sql para consulta do custo unitário do componente(composição) baseado no ultimo fornecimento feito do mesmo para a versão do projeto
                
                conecta_banco.executeSQL("select componentes_fornecimento.id_comp_fornec, componentes_fornecimento.id_componente,componentes_fornecimento.id_fornecimento," 
                                        +" componentes_fornecimento.id_moeda,componentes_fornecimento.qntd_componente,componentes_fornecimento.valor_unit,componentes_fornecimento.in_ativo," 
                                        +" componentes_versao_projeto.id_projeto,componentes_versao_projeto.cod_vers_projeto, fornecimento.data_cadastro from componentes_fornecimento" 
                                        +" inner join componentes_versao_projeto on (componentes_versao_projeto.id_comp_fornec = componentes_fornecimento.id_comp_fornec)" 
                                        +" inner join fornecimento on (fornecimento.id_fornecimento = componentes_fornecimento.id_fornecimento)"
                                        +" where componentes_fornecimento.id_componente = "+componente.getId_componente()+" and componentes_versao_projeto.cod_vers_projeto = "+componente.getCod_vers_projeto()+" "
                                        +" and componentes_fornecimento.in_ativo = 'A'" 
                                        +" and fornecimento.data_cadastro >= (select max(fornecimento.data_cadastro) from componentes_fornecimento"
                                        +" inner join componentes_versao_projeto on (componentes_versao_projeto.id_comp_fornec = componentes_fornecimento.id_comp_fornec)" 
                                        +" inner join fornecimento on (fornecimento.id_fornecimento = componentes_fornecimento.id_fornecimento)" 
                                        +" where componentes_fornecimento.id_componente = "+componente.getId_componente()+" and componentes_versao_projeto.cod_vers_projeto = "+componente.getCod_vers_projeto()+" "
                                        +" and componentes_fornecimento.in_ativo = 'A')");
                
                                        result_valor_comp = conecta_banco.resultset;
                
                try {   
                    //armazena valores para o calculo
                    result_valor_comp.first();
                    valor_unit = result_valor_comp.getDouble("valor_unit");
                    id_moeda = result_valor_comp.getInt("id_moeda");
                    data_fornec = result_valor_comp.getTimestamp("data_cadastro");

                    //converte valor_unitario para reais
                    valor_unit = dao_moeda.converteparaReais(valor_unit, id_moeda, data_fornec);
                    //calcula o total
                    total_composicao = total_composicao + (valor_unit*qntd_componente_composicao);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Falha ao calcular valor unitário da composição do componente.");
                }
                
                calculaComposicaoComponente(componente);
            }
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "Falha ao calcular valor unitário da composição do componente.");
        }
        
        return total_composicao;
    }
}

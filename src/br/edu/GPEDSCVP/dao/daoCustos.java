/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.GPEDSCVP.dao;

import br.edu.GPEDSCVP.classe.Componente;
import br.edu.GPEDSCVP.classe.Projeto;
import br.edu.GPEDSCVP.classe.VersaoProjeto;
import br.edu.GPEDSCVP.conexao.ConexaoBanco;
import br.edu.GPEDSCVP.util.Conversoes;
import br.edu.GPEDSCVP.util.FormatarData;
import br.edu.GPEDSCVP.util.UltimaSequencia;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author rafa
 */
public class daoCustos {
    
    ConexaoBanco conecta_banco;
    UltimaSequencia ultima;
    daoMoeda dao_moeda = new daoMoeda();
    daoComponente dao_comp = new daoComponente();
    FormatarData data = new FormatarData();
    Conversoes converte = new Conversoes();
    Componente componente = new Componente();
    
    
    public daoCustos()
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
    
    public void consultaTodosCompFornecProjeto(Projeto projeto, String tipo){

        conecta_banco.executeSQL("select fornecimento.id_fornecimento, componentes_versao_projeto.id_comp_versao, componentes_versao_projeto.id_componente, componente.descricao,"
        +"versao_projeto.versao,componentes_versao_projeto.qntd_para_projeto,componentes_fornecimento.id_moeda, moeda.unidade, " 
        +"componentes_fornecimento.valor_unit, 0 as imposto_unit, fornecimento.data_cadastro, 0 as total,fornecimento.id_moeda_frete," 
        +"fornecimento.vl_frete, fornecimento.id_moeda_imp, fornecimento.vl_impostos from fornecimento" 
        +" inner join componentes_fornecimento on (componentes_fornecimento.id_fornecimento = fornecimento.id_fornecimento)" 
        +" inner join moeda on (moeda.id_moeda = componentes_fornecimento.id_moeda)" 
        +" inner join componente on (componente.id_componente = componentes_fornecimento.id_componente)" 
        +" inner join componentes_versao_projeto on (componentes_versao_projeto.id_comp_fornec = componentes_fornecimento.id_comp_fornec)"
        +" inner join versao_projeto on (versao_projeto.cod_vers_projeto = componentes_versao_projeto.cod_vers_projeto)"
        +" where componentes_versao_projeto.id_projeto =" +projeto.getId_projeto()+" and fornecimento.in_ativo = 'A' and componentes_versao_projeto.in_ativo = 'A' and componente.tipo = '"+tipo+"'");

        projeto.setRetorno(conecta_banco.resultset);
    }
    
    public void consultaTodosCompFornecVersao(VersaoProjeto versao, String tipo){

        conecta_banco.executeSQL("select fornecimento.id_fornecimento,componentes_versao_projeto.id_comp_versao, componentes_versao_projeto.id_componente, componente.descricao,"
        +"versao_projeto.versao,componentes_versao_projeto.qntd_para_projeto,componentes_fornecimento.id_moeda, moeda.unidade, " 
        +"componentes_fornecimento.valor_unit, 0 as imposto_unit, fornecimento.data_cadastro, 0 as total,fornecimento.id_moeda_frete," 
        +"fornecimento.vl_frete, fornecimento.id_moeda_imp, fornecimento.vl_impostos from fornecimento" 
        +" inner join componentes_fornecimento on (componentes_fornecimento.id_fornecimento = fornecimento.id_fornecimento)" 
        +" inner join moeda on (moeda.id_moeda = componentes_fornecimento.id_moeda)" 
        +" inner join componente on (componente.id_componente = componentes_fornecimento.id_componente)" 
        +" inner join componentes_versao_projeto on (componentes_versao_projeto.id_comp_fornec = componentes_fornecimento.id_comp_fornec)"
        +" inner join versao_projeto on (versao_projeto.cod_vers_projeto = componentes_versao_projeto.cod_vers_projeto)"
        +" where componentes_versao_projeto.id_projeto =" +versao.getId_projeto()+" and componentes_versao_projeto.cod_vers_projeto ="+versao.getCod_vers_projeto()+""
        +" and fornecimento.in_ativo = 'A' and componentes_versao_projeto.in_ativo = 'A' and componente.tipo = '"+tipo+"'");

        versao.setRetorno(conecta_banco.resultset);
    }
    
    public void consultaTodosCompNaVersao(VersaoProjeto versao, String tipo){

        conecta_banco.executeSQL("select fornecimento.id_fornecimento,componentes_versao_projeto.id_comp_versao, componentes_versao_projeto.id_componente, componente.descricao,"
        +"versao_projeto.versao,componentes_versao_projeto.qntd_no_projeto,componentes_fornecimento.id_moeda, moeda.unidade, " 
        +"componentes_fornecimento.valor_unit, 0 as imposto_unit, fornecimento.data_cadastro, 0 as total,fornecimento.id_moeda_frete," 
        +"fornecimento.vl_frete, fornecimento.id_moeda_imp, fornecimento.vl_impostos,componentes_versao_projeto.situacao from fornecimento" 
        +" inner join componentes_fornecimento on (componentes_fornecimento.id_fornecimento = fornecimento.id_fornecimento)" 
        +" inner join moeda on (moeda.id_moeda = componentes_fornecimento.id_moeda)" 
        +" inner join componente on (componente.id_componente = componentes_fornecimento.id_componente)" 
        +" inner join componentes_versao_projeto on (componentes_versao_projeto.id_comp_fornec = componentes_fornecimento.id_comp_fornec)"
        +" inner join versao_projeto on (versao_projeto.cod_vers_projeto = componentes_versao_projeto.cod_vers_projeto)"
        +" where componentes_versao_projeto.id_projeto =" +versao.getId_projeto()+" and componentes_versao_projeto.cod_vers_projeto ="+versao.getCod_vers_projeto()+""
        +" and fornecimento.in_ativo = 'A' and componentes_versao_projeto.in_ativo = 'A' and componente.tipo = '"+tipo+"' and componentes_versao_projeto.situacao = 'C'");

        versao.setRetorno(conecta_banco.resultset);
    }
    
     public void consultaTodasCertifProjeto(Projeto projeto){

        conecta_banco.executeSQL("SELECT id_certificacao,certificacao.descricao,pessoa.id_pessoa, pessoa.nome,versao_projeto.versao, norma.id_norma, norma.titulo,resultado,"
        +"valor,data_ensaio from certificacao"
        +" inner join certificadora on (certificadora.id_pessoa = certificacao.id_pessoa)" 
        +" inner join pessoa_juridica on (pessoa_juridica.id_pessoa = certificadora.id_pessoa)" 
        +" inner join pessoa on (pessoa.id_pessoa = pessoa_juridica.id_pessoa)" 
        +" inner join norma on (norma.id_norma = certificacao.id_norma)"
        +" inner join versao_projeto on (versao_projeto.cod_vers_projeto = certificacao.cod_vers_projeto)"
        +" where certificacao.id_projeto = "+projeto.getId_projeto()+" and certificacao.in_ativo = 'A'");

        projeto.setRetorno(conecta_banco.resultset);
    }
     
    public void consultaTodasCertifVersao(VersaoProjeto versao){

        conecta_banco.executeSQL("SELECT id_certificacao,certificacao.descricao,pessoa.id_pessoa, pessoa.nome,versao_projeto.versao, norma.id_norma, norma.titulo,resultado,"
        +"valor,data_ensaio from certificacao"
        +" inner join certificadora on (certificadora.id_pessoa = certificacao.id_pessoa)" 
        +" inner join pessoa_juridica on (pessoa_juridica.id_pessoa = certificadora.id_pessoa)" 
        +" inner join pessoa on (pessoa.id_pessoa = pessoa_juridica.id_pessoa)" 
        +" inner join norma on (norma.id_norma = certificacao.id_norma)"
        +" inner join versao_projeto on (versao_projeto.cod_vers_projeto = certificacao.cod_vers_projeto)"
        +" where certificacao.id_projeto = "+versao.getId_projeto()+" and certificacao.cod_vers_projeto = "+versao.getCod_vers_projeto()+" and certificacao.in_ativo = 'A'");

        versao.setRetorno(conecta_banco.resultset);
    }
    
    //método para calcular o imposto unitário de cada componente
    public void calculaImpostoUnitarioComp(JTable Tabela_comp){
        DefaultTableModel tabela = (DefaultTableModel) Tabela_comp.getModel();
        int totlinha_comp = tabela.getRowCount();
        
        Integer id_fornecimento;
        Integer qntd_comp_fornecimento;
        Double valor_frete;
        Double valor_imposto;
        Integer id_moeda_frete;
        Integer id_moeda_imposto;
        Double imposto_unit;
        Object imposto_unit_convert;
        Timestamp data_fornec;
      
        for (int i_comp = 0; i_comp < totlinha_comp; i_comp++){
            
            id_fornecimento = Integer.parseInt(tabela.getValueAt(i_comp, 0).toString());
            data_fornec = data.stringParaTimeStamp(tabela.getValueAt(i_comp, 10).toString());
            id_moeda_frete = Integer.parseInt(tabela.getValueAt(i_comp, 12).toString());
            valor_frete = Double.parseDouble(tabela.getValueAt(i_comp, 13).toString().replace(".", "").replace(",", "."));
            id_moeda_imposto = Integer.parseInt(tabela.getValueAt(i_comp, 14).toString());
            valor_imposto = Double.parseDouble(tabela.getValueAt(i_comp, 15).toString().replace(".", "").replace(",", "."));
            
            String sql = "select count(id_fornecimento) qntd from componentes_fornecimento where id_fornecimento = "+ id_fornecimento+" and componentes_fornecimento.in_ativo = 'A'";
            
            //armazena a quantidade de componentes que foram fornecidos nesse fornecimento para ter a quantidade para média
            try {
                conecta_banco.executeSQL(sql);
                conecta_banco.resultset.first();
                qntd_comp_fornecimento = conecta_banco.resultset.getInt("qntd");
            } catch (Exception e) {
                qntd_comp_fornecimento = 0;
            }
            //converte para reais os valores de frete e imposto
            valor_frete = dao_moeda.converteparaReais(valor_frete, id_moeda_frete, data_fornec);
            valor_imposto = dao_moeda.converteparaReais(valor_imposto, id_moeda_imposto, data_fornec);
           
            //calcula o imposto unitário
            imposto_unit = (valor_frete + valor_imposto)/qntd_comp_fornecimento;
            //converte para formato monetário
            imposto_unit_convert = converte.doubleParaObjectDecimalFormat(imposto_unit);
            //seta na jtable o imposto unitário
            tabela.setValueAt(imposto_unit_convert, i_comp, 9);
        }
    }
    
    //método para calcular o total de cada componente
    public void calculaTotalCompProjeto(JTable Tabela_comp){
        DefaultTableModel tabela = (DefaultTableModel) Tabela_comp.getModel();
        int totlinha_comp = tabela.getRowCount();
        
        Integer id_moeda;
        Double valor_unit;
        Integer qntd;
        Double imposto_unit;
        Double total_comp = 0.00;
        Object total_comp_format;
        Timestamp data_fornec;
      
        for (int i_comp = 0; i_comp < totlinha_comp; i_comp++){
            
            id_moeda = Integer.parseInt(tabela.getValueAt(i_comp, 6).toString());
            valor_unit = Double.parseDouble(tabela.getValueAt(i_comp, 8).toString().replace(".", "").replace(",", "."));
            qntd = Integer.parseInt(tabela.getValueAt(i_comp, 5).toString());
            imposto_unit = Double.parseDouble(tabela.getValueAt(i_comp, 9).toString().replace(".", "").replace(",", "."));
            data_fornec = data.stringParaTimeStamp(tabela.getValueAt(i_comp, 10).toString());
          
            //soma o total do componente
            total_comp = valor_unit * qntd;

            //converte para reais
            total_comp = dao_moeda.converteparaReais(total_comp, id_moeda, data_fornec);

            //calcula o total do fornecimento
            total_comp = total_comp + imposto_unit;
            
            //formata para monetario
            total_comp_format = converte.doubleParaObjectDecimalFormat(total_comp);
            
            tabela.setValueAt(total_comp_format, i_comp, 11);
        }
    }
    
    
    //método para calcular o total de todos componentes na jtable
    public Double calculaTotalComponentes(JTable Tabela_comp){
        DefaultTableModel tabela = (DefaultTableModel) Tabela_comp.getModel();
        int totlinha_comp = tabela.getRowCount();
        
        
        Double valor_total = 0.00;
        Double total_comp = 0.00;
      
      
        for (int i_comp = 0; i_comp < totlinha_comp; i_comp++){
            
            total_comp = Double.parseDouble(tabela.getValueAt(i_comp, 11).toString().replace(".", "").replace(",", "."));

            valor_total = valor_total + total_comp;
        }
        return valor_total;
    }
    
    //método para calcular o total de todos componentes na jtable
    public Double calculaTotalCertificacao(JTable Tabela_certif){
        DefaultTableModel tabela = (DefaultTableModel) Tabela_certif.getModel();
        int totlinha_comp = tabela.getRowCount();
        
        Double valor_total = 0.00;
        Double total_cert = 0.00;
      
        for (int i_cert = 0; i_cert < totlinha_comp; i_cert++){
            
            total_cert = Double.parseDouble(tabela.getValueAt(i_cert, 6).toString().replace(".", "").replace(",", "."));

            valor_total = valor_total + total_cert;
        }
        return valor_total;
    }
    
    public void removeComponentesCompostos(JTable Tabela_comp){
        DefaultTableModel tabela = (DefaultTableModel) Tabela_comp.getModel();
        int totlinha_comp = tabela.getRowCount();
        Integer id_componente; 
        
        for (int i_comp = totlinha_comp-1; i_comp >=0; i_comp--){
            
            id_componente = Integer.parseInt(tabela.getValueAt(i_comp, 2).toString());
           
            componente.setId_componente(id_componente);
            if(dao_comp.verificaExisteComposicao(componente) == true){
                tabela.removeRow(i_comp);
            }
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.GPEDSCVP.dao;

import br.edu.GPEDSCVP.classe.Acesso;
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
public class daoAcesso {
    
    ConexaoBanco conecta_banco;
    UltimaSequencia ultima;
    
    public daoAcesso()
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
    
    public void gravarAcesso(Acesso acesso){
    
        try {
            //Insert de acesso
            ultima = new UltimaSequencia();
            
            int sequencia = (Integer) (ultima.ultimasequencia("ACESSO","ID_ACESSO"));
            String sql = "INSERT INTO ACESSO VALUES ("
                + sequencia + ","
                + acesso.getId_usuario()+ ",'"
                + FormatarData.dateParaTimeStamp(acesso.getData_acesso())+ "')";
        
                conecta_banco.incluirSQL(sql);
                
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao registrar acesso do usuário");
        }
       
    }
    
    //retorna dados do usuário logado
    public void retornaUsuarioLogado(Acesso acesso){
        String sql = "select nome, usuario.id_usuario, usuario.login, usuario.in_gerente from pessoa"
        +" inner join pessoa_fisica on (pessoa_fisica.id_pessoa = pessoa.id_pessoa)"
        +" inner join usuario on (usuario.id_usuario = pessoa_fisica.id_pessoa)"
        +" where pessoa.id_pessoa = "+ acesso.getId_usuario();
         try {
                conecta_banco.executeSQL(sql);
                conecta_banco.resultset.first();
              
                acesso.setId_usuario(conecta_banco.resultset.getInt("id_usuario"));
                acesso.setLogin_usuario(conecta_banco.resultset.getString("login"));
                acesso.setNome_usuario(conecta_banco.resultset.getString("nome"));
                acesso.setIn_gerente(conecta_banco.resultset.getInt("in_gerente"));

         } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Falha ao retornar dados do usuário logado");
         }
    }
    
}

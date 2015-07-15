/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.GPEDSCVP.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Willys
 */
public class ConexaoBanco {
    
    public static Connection ConexaoBanco;
    public static Statement statement;
    public static ResultSet resultset;
    public ResultSetMetaData metaData;
    public int retorno;
    
    public ConexaoBanco()
        throws SQLException
    {
        retorno = 0;
        conecta();
    }

    public static Connection conecta()
        throws SQLException
    {
        if(ConexaoBanco != null)
            return ConexaoBanco;
        try
        {
            ConexaoBanco = DriverManager.getConnection("jdbc:mysql://127.0.0.1/gpedsdb", "root", "root");
            System.out.println("conectado");
            JOptionPane.showMessageDialog(null, "Conectado com sucesso");
            return ConexaoBanco;
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Erro na conexão com a fontede dados: ");
            ex.printStackTrace();
            return null;
        }
    }

    public void desconecta()
    {
        boolean result = true;
        try
        {
            ConexaoBanco.close();
        }
        catch(SQLException fecha)
        {
            JOptionPane.showMessageDialog(null, (new StringBuilder()).append("banco fechado fechar o banco de dados: ").append(fecha).toString());
            result = false;
        }
    }

    public void executeSQL(String sql)
    {
        try
        {
            statement = ConexaoBanco.createStatement(1005, 1007);
            resultset = statement.executeQuery(sql);
            retorno = 1;
        }
        catch(SQLException sqlex)
        {
            JOptionPane.showMessageDialog(null, (new StringBuilder()).append("Não foi possivel localizar o registro \n").append(sqlex).toString());
        }
        try
        {
            metaData = resultset.getMetaData();
        }
        catch(SQLException erro) { }
    }

    public void incluirSQL(String sql)
    {
        try
        {
            System.out.print((new StringBuilder()).append("insert : ").append(sql).toString());
            statement = ConexaoBanco.createStatement(1005, 1007);
            statement.executeUpdate(sql);
            retorno = 1;
        }
        catch(SQLException sqlex)
        {
            if(sqlex.getErrorCode() == 1)
                JOptionPane.showMessageDialog(null, "O registro não pode ser incluido pois ja esta cadastrado");
            else
                JOptionPane.showMessageDialog(null, (new StringBuilder()).append("Não foi possivel executar o comando sql,").append(sqlex).append(", o sql passado foi").append(sql).toString());
        }
    }
}

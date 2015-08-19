/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.GPEDSCVP.validacao;

import java.awt.FontMetrics;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author Willys
 */
public class ManipulaJtable {
    
    public ManipulaJtable()
    {
    }

    public void FormatarJtable(JTable tabela, int valores[])
    {
        DefaultTableModel modelo = (DefaultTableModel)tabela.getModel();
        modelo.setNumRows(0);
        if(modelo.getColumnCount() == valores.length)
        {
            for(int x = 0; x < valores.length; x++)
                tabela.getColumnModel().getColumn(x).setPreferredWidth(valores[x]);

        } else
        {
            JOptionPane.showMessageDialog(null, "Favor verificar os parametros passados !");
        }
    }

    public void PreencherJtableGenerico(JTable tabela, String campos[], ResultSet resultSet)
    {
        DefaultTableModel modelo = (DefaultTableModel)tabela.getModel();
        modelo.setNumRows(0);
        try
        {
            Object row[];
            for(; resultSet.next(); modelo.addRow(row))
            {
                int len = campos.length;
                row = new Object[len];
                for(int i = 0; i < len; i++)
                    row[i] = resultSet.getString(campos[i]);

            }

        }
        catch(SQLException erro)
        {
            JOptionPane.showMessageDialog(null, (new StringBuilder()).append("Erro ao listar no JTable ").append(erro).toString());
        }
    }
    
    //Método para dimensionar a largura das colunas da Jtable de acordo com o tamanho do texto
    public void ajustaColunas(JTable tabela) {  
        tabela.setAutoResizeMode(0);  
        FontMetrics fm = tabela.getGraphics().getFontMetrics();  
  
        for(int i = 0; i < tabela.getColumnCount(); i++) {  
            String columnName = tabela.getColumnName(i);  
            TableColumn col = tabela.getColumnModel().getColumn(i);  
            col.setMinWidth(fm.stringWidth(columnName) + 10);  
        }                         
    } 
    
     //Método para remover um registro da Jtable
     public void removeItens(JTable  jtable){
        DefaultTableModel tabela = (DefaultTableModel)jtable.getModel();
        int totlinha = tabela.getRowCount();
        Boolean sel = false;
        
        int opcao = JOptionPane.showConfirmDialog(null, "Deseja remover as linhas selecionadas? ",
                "remover", 
                    JOptionPane.YES_NO_OPTION);
        if(opcao == JOptionPane.YES_OPTION){
            for(int i = totlinha - 1; i>=0 ; i--){
                if(tabela.getValueAt(i,0) == null){
                    sel = false;
                }else{
                     Boolean selecionado = (Boolean) tabela.getValueAt(i,0);
                    if(selecionado == true){
                        sel = true;
                        tabela.removeRow(i);
                    }
                }
            }
            if (sel == false){
                JOptionPane.showMessageDialog(null, "Não ha nenhum registro selecionado !"); 
            }
        }
        
    }  

    
}

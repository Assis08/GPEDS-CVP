/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.GPEDSCVP.validacao;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

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
      
        DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
        modelo.setNumRows(0);
        try {
            while (resultSet.next()) {
                int len = campos.length;
                Object[] row = new Object[len];
                for (int i = 0; i < len; i++) {
                    row[i] = resultSet.getString(campos[i]);
                }
                modelo.addRow(row);
            }
                } catch (SQLException erro) {
                    JOptionPane.showMessageDialog(null, "Erro ao listar no JTable " + erro);
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
    //Método para ajustar colunas da JTABLE de acordo com o tamanho do dado
    public static void ajustarColunasDaTabela(JTable ttabela) {
        for (int c = 0; c < ttabela.getColumnCount(); c++) {

            DefaultTableColumnModel colModel = (DefaultTableColumnModel) ttabela.getColumnModel();
            TableColumn col = colModel.getColumn(c);
            int width = 0;

            // Get width of column header  
            TableCellRenderer renderer = col.getHeaderRenderer();
            if (renderer == null) {
                renderer = ttabela.getTableHeader().getDefaultRenderer();
            }
            Component comp = renderer.getTableCellRendererComponent(ttabela, col.getHeaderValue(), false, false, -1, 0);
            width = comp.getPreferredSize().width;

            // Get maximum width of column data  
            for (int r = 0; r < ttabela.getRowCount(); r++) {
                renderer = ttabela.getCellRenderer(r, c);
                comp = renderer.getTableCellRendererComponent(ttabela, ttabela.getValueAt(r, c), false, false, r, c);
                width = Math.max(width, comp.getPreferredSize().width);
            }

            // Add margin  
            width += 2 * 2;

            // Set the width  
            col.setPreferredWidth(width);
        }
        for (int r = 0; r < ttabela.getRowCount(); r++) {
            // Get the preferred height  
            //  int h = getPreferredRowHeight(ttabela, r, 0);
            int height = ttabela.getRowHeight();

            // Determine highest cell in the row  
            for (int c = 0; c < ttabela.getColumnCount(); c++) {
                TableCellRenderer renderer = ttabela.getCellRenderer(r, c);
                Component comp = ttabela.prepareRenderer(renderer, r, c);
                int h = comp.getPreferredSize().height + 2 * 0;
                height = Math.max(height, h);
            }

            // Now set the row height using the preferred height  
            if (ttabela.getRowHeight(r) != height) {
                ttabela.setRowHeight(r, height);
            }
        }
        ttabela.setRowHeight(20);
        ttabela.setIntercellSpacing(new Dimension(2, 2));
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.GPEDSCVP.util;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.event.ActionEvent;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.DefaultCellEditor;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;
import javax.swing.text.TableView;
import javax.swing.text.TableView.TableRow;

/**
 *
 * @author Willys
 */
public class ManipulaJtable {

    public ManipulaJtable() {
    }

    public void FormatarJtable(JTable tabela, int valores[]) {
        DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
        modelo.setNumRows(0);
        if (modelo.getColumnCount() == valores.length) {
            for (int x = 0; x < valores.length; x++) {
                tabela.getColumnModel().getColumn(x).setPreferredWidth(valores[x]);
            }

        } else {
            JOptionPane.showMessageDialog(null, "Favor verificar os parametros passados !");
        }
    }

    public void PreencherJtableGenerico(JTable tabela, String campos[], ResultSet resultSet) {
        FormatarData data = new FormatarData();
        DecimalFormat dFormat = new DecimalFormat("#,###,##0.00") ;
        DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
        modelo.setNumRows(0);
        //tabela.addPropertyChangeListener();

        try {
            while (resultSet.next()) {
                int len = campos.length;
                Object[] row = new Object[len];
                for (int i = 0; i < len; i++) {
                    //Verifica o tipo do dado para formatar e preencher na Jtable
                    if(resultSet.getObject(campos[i]) instanceof  Double){
                        row[i] = dFormat.format(resultSet.getObject(campos[i]));
                    }else if (resultSet.getObject(campos[i]) instanceof Timestamp) {
                        row[i] = data.organizaData(resultSet.getObject(campos[i]));
                    } else if (resultSet.getObject(campos[i]) instanceof java.sql.Date) {
                        row[i] = data.organizaData(resultSet.getObject(campos[i]));
                    } else if (resultSet.getObject(campos[i]) instanceof String){
                        if(resultSet.getString(campos[i]).equals("S")){
                            row[i] = "Sim";
                        }else  if(resultSet.getString(campos[i]).equals("N")){
                            row[i] = "Não";
                        }else if ((resultSet.getString(campos[i]).equals("F")) && (tabela.getName().equals("Contato")) ){
                            row[i] = "Fone";
                        }else if ((resultSet.getString(campos[i]).equals("E")) && (tabela.getName().equals("Contato")) ){
                            row[i] = "Email";
                        }else{
                             row[i] = resultSet.getString(campos[i]);
                        }
                    }else{
                         row[i] = resultSet.getString(campos[i]);
                    }
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

        for (int i = 0; i < tabela.getColumnCount(); i++) {
            String columnName = tabela.getColumnName(i);
            TableColumn col = tabela.getColumnModel().getColumn(i);
            col.setMinWidth(fm.stringWidth(columnName) + 10);
        }
    }

     //Método para remover um registro da Jtable
    public void removeItens(JTable jtable, int situacao) {
        DefaultTableModel tabela = (DefaultTableModel) jtable.getModel();
        int totlinha = tabela.getRowCount();
        int totcolun = tabela.getColumnCount();
        Boolean sel = false;
        Boolean achou = false;
        
        int opcao = JOptionPane.showConfirmDialog(null, "Deseja remover os registros selecionados? ",
                "remover",
                JOptionPane.YES_NO_OPTION);
        if (opcao == JOptionPane.YES_OPTION) {
        //Percorre linhas da jtable
        for(int i = totlinha-1; i >= 0; i--){

            sel = (Boolean) tabela.getValueAt(i, 0);
            //Se a linha estiver selecionada
            if(sel != null){
                if(sel == true){

                    //ativa flag que achou uma linha selecionada
                    achou = true;
                    if(situacao == Rotinas.ALTERAR){
                        //seta o valor 1 na coluna excluido da jtable
                        jtable.setValueAt(1, i, totcolun-1);
                        jtable.setValueAt(false, i, 0);
                        //habilita e desabilita para atualizar o jtable (caso contrario pinta de vermelho só quando clica na linha)
                        jtable.setEnabled(false);
                        jtable.setEnabled(true);
                    }else{
                        tabela.removeRow(i);
                    }
                }
            }
        }
        if(achou == false){
            JOptionPane.showMessageDialog(null, "Nehuma linha selecionada!");
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
    
    public boolean evitarDuplicacao(JTable tabela, String valor){
        
        DefaultTableModel Tabela = (DefaultTableModel)tabela.getModel();
      
        int tot_row = Tabela.getRowCount();
        int tot_col = Tabela.getColumnCount();
        
        for(int i_row = 0; i_row < tot_row; i_row++){
            for(int i_colun = 1; i_colun< tot_col; i_colun++){
                //se for um valor diferente de null
                if(Tabela.getValueAt(i_row, i_colun)!= null){
                    if(Tabela.getValueAt(i_row, i_colun).toString().equals(valor)){
                    //valor duplicado
                    return true;
                    }    
                }
               
                
                
            }
        }
        return false;
    }
    
    
    private void onCellEditor(JTable table, int column, int row, Object oldValue, Object newValue){
        System.out.println("Coluna:" + column + "Valor novo: " + newValue + " Valor antigo: " + oldValue);
        if (table == table) {
            
        }
    }

    class TableCellEditorAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            TableCellListener tbListener = (TableCellListener) e.getSource();
            onCellEditor(tbListener.getTable(), tbListener.getColumn(), tbListener.getRow(), tbListener.getOldValue(), tbListener.getNewValue());
        }
    }
    //Método para serar mascara em uma determinada coluna da jtable
    public void setarMascara(JTable tabela, JFormattedTextField jftmascara, int numColuna){
       
        ValidaCampos valida_campos;
        try {
            valida_campos = new ValidaCampos();
            valida_campos.formataMonetario(jftmascara);
        } catch (SQLException ex) {
            Logger.getLogger(ManipulaJtable.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        TableColumn col = tabela.getColumnModel().getColumn(numColuna);  
    
        col.setCellEditor(new DefaultCellEditor(jftmascara));      

    }

    //Método para formatar um field para receber valores monetários
    public static void setarMascaraMonetaria(JTable tabela, JFormattedTextField field, int numColuna) {
   
       try {
           
            DecimalFormat dFormat = new DecimalFormat("#,###,###.00") ;
            NumberFormatter formatter = new NumberFormatter(dFormat) ;
            formatter.setFormat(dFormat) ;
            formatter.setAllowsInvalid(false) ; 
           
            field.setFormatterFactory ( new DefaultFormatterFactory ( formatter ) ) ;
           
            TableColumn col = tabela.getColumnModel().getColumn(numColuna);  
    
            col.setCellEditor(new DefaultCellEditor(field));    

      }
      catch (Exception error_mask) {
          JOptionPane.showMessageDialog(null,"Erro: "+error_mask);
      }
    }
   
}

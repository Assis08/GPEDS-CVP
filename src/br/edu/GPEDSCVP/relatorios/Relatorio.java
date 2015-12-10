/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.GPEDSCVP.relatorios;

import java.sql.Connection;
import java.util.HashMap;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author rafa
 */
public class Relatorio {
    
    private Connection conn;
    
    public Relatorio(Connection conn){
        this.conn = conn;
    }
    
    public void relatorioCustoVersao(Integer id_versao) throws Exception{
        HashMap parametros = new HashMap();
        parametros.put("ID_VERSAO", id_versao);
        
        JasperPrint print = JasperFillManager.fillReport("relatorios/custosProjetos.jasper", parametros, conn);
        JasperViewer view = new JasperViewer(print, false);
        view.setVisible(true);
    }
    
}

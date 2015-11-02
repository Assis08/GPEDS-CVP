/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.GPEDSCVP.classe;

import java.sql.Date;
import java.sql.ResultSet;
import javax.swing.JTable;

/**
 *
 * @author Willys
 */
public class Fornecimento {
    
    private int id_fornecimento;
    private int id_pessoa;
    private String descricao;
    private Date data_cadastro;
    private double valor_frete;
    private double valor_impostos;
    private Date data_alter;
    private static JTable tabela;
    private ResultSet retorno;

    
}

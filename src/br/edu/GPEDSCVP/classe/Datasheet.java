/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.GPEDSCVP.classe;

import java.io.File;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Date;
import java.sql.ResultSet;
import javax.swing.JTable;

/**
 *
 * @author Willys
 */
public class Datasheet {
    
    private int id_datasheet;
    private String descricao;
    private byte[] arquivo;
    private Date data_cadastro;
    private JTable tabela;
    private ResultSet retorno;
    private int[] array_datasheet;

    public Datasheet() {
        id_datasheet = 0;
        descricao = "";
        arquivo = null;
        data_cadastro = null; 
    }

    public int getId_datasheet() {
        return id_datasheet;
    }

    public void setId_datasheet(int id_datasheet) {
        this.id_datasheet = id_datasheet;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public byte[] getArquivo() {
        return arquivo;
    }

    public void setArquivo(byte[] arquivo) {
        this.arquivo = arquivo;
    }

    public Date getData_cadastro() {
        return data_cadastro;
    }

    public void setData_cadastro(Date data_cadastro) {
        this.data_cadastro = data_cadastro;
    }

    public JTable getTabela() {
        return tabela;
    }

    public void setTabela(JTable tabela) {
        this.tabela = tabela;
    }

    public ResultSet getRetorno() {
        return retorno;
    }

    public void setRetorno(ResultSet retorno) {
        this.retorno = retorno;
    }

    public int[] getArray_datasheet() {
        return array_datasheet;
    }

    public void setArray_datasheet(int[] array_datasheet) {
        this.array_datasheet = array_datasheet;
    }
}

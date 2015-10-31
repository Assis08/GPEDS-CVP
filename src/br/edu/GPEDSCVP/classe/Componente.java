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
public class Componente {
    
    private int id_componente;
    private int id_material;
    private String ds_material;
    private int id_datasheet;
    private String ds_datasheet;
    private String descricao;
    private String tipo;
    private String revisao;
    private Date data_alter;
    private Date data_cadastro;
    private static JTable tabela;
    private ResultSet retorno;

    public Componente() {
        id_componente = 0;
        id_material = 0;
        id_datasheet = 0;
        descricao = "";
        tipo = "";
        revisao = "";
        data_alter = null;
        data_cadastro = null;
    }

    public int getId_componente() {
        return id_componente;
    }

    public void setId_componente(int id_componente) {
        this.id_componente = id_componente;
    }

    public int getId_material() {
        return id_material;
    }

    public int getId_datasheet() {
        return id_datasheet;
    }

    public void setId_datasheet(int id_datasheet) {
        this.id_datasheet = id_datasheet;
    }
    
    public void setId_material(int id_material) {
        this.id_material = id_material;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getRevisao() {
        return revisao;
    }

    public void setRevisao(String revisao) {
        this.revisao = revisao;
    }
    
    public Date getData_alter() {
        return data_alter;
    }

    public void setData_alter(Date data_alter) {
        this.data_alter = data_alter;
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

    public String getDs_material() {
        return ds_material;
    }

    public void setDs_material(String ds_material) {
        this.ds_material = ds_material;
    }

    public String getDs_datasheet() {
        return ds_datasheet;
    }

    public void setDs_datasheet(String ds_datasheet) {
        this.ds_datasheet = ds_datasheet;
    }
}

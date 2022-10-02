/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;
/**
 *
 * @author wally
 */
public class Exame {
    private int id; 
    private String descricao;
    private Date dataExame;     

    public Exame(int id, String descricao, Date dataExame) {
        this.id = id;
        this.descricao = descricao;
        this.dataExame = dataExame;
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public Date getDataExame() {
        return dataExame;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setDataExame(Date dataExame) {
        this.dataExame = dataExame;
    }

    @Override
    public String toString() {
        return "Exame{" + "id=" + id + ", descricao=" + descricao + ", dataExame=" + dataExame + '}';
    }

   
}

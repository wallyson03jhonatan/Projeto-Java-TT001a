/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;
/**
 *
 * @author wally
 */
public class Exame {
    private String descricao;
    private LocalDate dataExame;     

    public Exame(String descricao, LocalDate dataExame) {
        this.descricao = descricao;
        this.dataExame = dataExame;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDate getDataExame() {
        return dataExame;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setDataExame(LocalDate dataExame) {
        this.dataExame = dataExame;
    }
    
}

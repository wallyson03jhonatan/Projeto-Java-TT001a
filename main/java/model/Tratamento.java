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
public class Tratamento {
    private int protocolo;
    private LocalDate dataIni;
    private LocalDate dataFim;

    public Tratamento(int protocolo, LocalDate dataIni, LocalDate dataFim) {
        this.protocolo = protocolo;
        this.dataIni = dataIni;
        this.dataFim = dataFim;
    }

    public int getProtocolo() {
        return protocolo;
    }

    public LocalDate getDataIni() {
        return dataIni;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataIni(LocalDate dataIni) {
        this.dataIni = dataIni;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

}

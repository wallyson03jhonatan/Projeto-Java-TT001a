/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

public class Tratamento {
    private int protocolo;
    private Date dataIni;
    private Date dataFim;

    public Tratamento(int protocolo, Date dataIni, Date dataFim) {
        this.protocolo = protocolo;
        this.dataIni = dataIni;
        this.dataFim = dataFim;
    }

    public int getProtocolo() {
        return protocolo;
    }

    public Date getDataIni() {
        return dataIni;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataIni(Date dataIni) {
        this.dataIni = dataIni;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    @Override
    public String toString() {
        return "Tratamento{" + "protocolo=" + protocolo + ", dataIni=" + dataIni + ", dataFim=" + dataFim + '}';
    }

    
}

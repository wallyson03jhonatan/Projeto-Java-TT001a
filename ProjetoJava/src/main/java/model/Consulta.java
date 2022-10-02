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
public class Consulta {
    private int id;
    private Date dataConsulta; 
    private String historico;

    public Consulta(int id, Date dataConsulta, String historico) {
        this.id = id;
        this.dataConsulta = dataConsulta;
        this.historico = historico;
    }

    public int getId() {
        return id;
    }

    public Date getDataConsulta() {
        return dataConsulta;
    }

    public String getHistorico() {
        return historico;
    }

    public void setDataConsulta(Date dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public void setHistorico(String historico) {
        this.historico = historico;
    }

    @Override
    public String toString() {
        return "Consulta{" + "id=" + id + ", dataConsulta=" + dataConsulta + ", historico=" + historico + '}';
    }

    

}

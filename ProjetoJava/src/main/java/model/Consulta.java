package model;

import java.sql.Date;

public class Consulta {
    private int id;
    private Date dataConsulta; 
    private String historico;
    private int idVeterinario;

    public Consulta(int id, Date dataConsulta, String historico, int idVeterinario) {
        this.id = id;
        this.dataConsulta = dataConsulta;
        this.historico = historico;
        this.idVeterinario = idVeterinario;
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

    public int getIdVeterinario() {
        return idVeterinario;
    }
    
    public void setDataConsulta(Date dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public void setHistorico(String historico) {
        this.historico = historico;
    }

    public void setIdVeterinario(int idVeterinario) {
        this.idVeterinario = idVeterinario;
    }

    @Override
    public String toString() {
        return "Consulta{" + "id=" + id + ", dataConsulta=" + dataConsulta + ", historico=" + historico + ", idVeterinario=" + idVeterinario + '}';
    }
    
}

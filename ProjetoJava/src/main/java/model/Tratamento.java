package model;

import java.sql.Date;

public class Tratamento {
    private int protocolo;
    private Date dataIni;
    private Date dataFim;
    private int idAnimal;
    private int idConsulta;

    public Tratamento(int protocolo, Date dataIni, Date dataFim, int idAnimal, int idConsulta) {
        this.protocolo = protocolo;
        this.dataIni = dataIni;
        this.dataFim = dataFim;
        this.idAnimal = idAnimal;
        this.idConsulta = idConsulta;
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

    public int getIdAnimal() {
        return idAnimal;
    }

    public int getIdConsulta() {
        return idConsulta;
    }
    
    public void setDataIni(Date dataIni) {
        this.dataIni = dataIni;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public void setIdAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
    }

    public void setIdConsulta(int idConsulta) {
        this.idConsulta = idConsulta;
    }

    @Override
    public String toString() {
        return "Tratamento{" + "protocolo=" + protocolo + ", dataIni=" + dataIni + ", dataFim=" + dataFim + ", idAnimal=" + idAnimal + ", idConsulta=" + idConsulta + '}';
    }

}

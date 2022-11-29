package model;

import java.util.Calendar;

public class Consulta {
    private int id;
    private Calendar data;
    private int hora;
    private String historico;
    private int idAnimal;
    private int idVeterinario;
    private boolean finalizado;

    public Consulta(int id, Calendar data, int hora, String historico, int idAnimal, int idVeterinario, boolean finalizado) {
        this.id = id;
        this.data = data;
        this.hora = hora;
        this.historico = historico;
        this.idAnimal = idAnimal;
        this.idVeterinario = idVeterinario;
        this.finalizado = finalizado;
    }

    public int getId() {
        return id;
    }

    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public String getHistorico() {
        return historico;
    }

    public void setHistorico(String historico) {
        this.historico = historico;
    }

    public int getIdAnimal() {
        return idAnimal;
    }

    public boolean isFinalizado() {
        return finalizado;
    }
    
    public void setIdAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
    }

    public int getIdVeterinario() {
        return idVeterinario;
    }

    public void setIdVeterinario(int idVeterinario) {
        this.idVeterinario = idVeterinario;
    }

    public void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
    }

    @Override
    public String toString() {
        return "Consulta{" + "id=" + id + ", data=" + data + ", hora=" + hora + ", historico=" + historico + ", idAnimal=" + idAnimal + ", idVeterinario=" + idVeterinario + ", finalizado=" + finalizado + '}';
    }
    
}

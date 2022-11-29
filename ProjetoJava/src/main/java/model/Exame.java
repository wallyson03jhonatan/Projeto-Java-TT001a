package model;

import java.util.Calendar;

public class Exame {
    private int id; 
    private String descricao;
    private Calendar data;   
    private int idConsulta;

    public Exame(int id, String descricao, Calendar data, int idConsulta) {
        this.id = id;
        this.descricao = descricao;
        this.data = data;
        this.idConsulta = idConsulta;
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public Calendar getData() {
        return data;
    }

    public int getIdConsulta() {
        return idConsulta;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setData(Calendar data) {
        this.data = data;
    }

    public void setIdConsulta(int idConsulta) {
        this.idConsulta = idConsulta;
    }

    @Override
    public String toString() {
        return "Exame{" + "id=" + id + ", descricao=" + descricao + ", dataExame=" + data + ", idConsulta=" + idConsulta + '}';
    }

}

package model;

import java.sql.Date;

public class Exame {
    private int id; 
    private String descricao;
    private Date dataExame;   
    private int idConsulta;

    public Exame(int id, String descricao, Date dataExame, int idConsulta) {
        this.id = id;
        this.descricao = descricao;
        this.dataExame = dataExame;
        this.idConsulta = idConsulta;
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

    public int getIdConsulta() {
        return idConsulta;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setDataExame(Date dataExame) {
        this.dataExame = dataExame;
    }

    public void setIdConsulta(int idConsulta) {
        this.idConsulta = idConsulta;
    }

    @Override
    public String toString() {
        return "Exame{" + "id=" + id + ", descricao=" + descricao + ", dataExame=" + dataExame + ", idConsulta=" + idConsulta + '}';
    }

}

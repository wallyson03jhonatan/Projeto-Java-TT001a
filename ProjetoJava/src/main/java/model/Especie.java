/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author wally
 */
public class Especie {
    private int id;
    private String nomeEspecie;

    public Especie(int id, String nomeEspecie) {
        this.id = id;
        this.nomeEspecie = nomeEspecie;
    }

    public int getId() {
        return id;
    }

    public String getNomeEspecie() {
        return nomeEspecie;
    }

    public void setNomeEspecie(String nomeEspecie) {
        this.nomeEspecie = nomeEspecie;
    }
      
}

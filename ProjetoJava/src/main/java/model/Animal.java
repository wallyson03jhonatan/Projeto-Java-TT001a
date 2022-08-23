/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author wally
 */
public class Animal {
    private int id;
    private String nome;
    private int idadeAnimal;
    private int sexoAnimal;

    public Animal(int id, String nome, int idadeAnimal, int sexoAnimal) {
        this.id = id;
        this.nome = nome;
        this.idadeAnimal = idadeAnimal;
        this.sexoAnimal = sexoAnimal;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getIdadeAnimal() {
        return idadeAnimal;
    }

    public int getSexoAnimal() {
        return sexoAnimal;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdadeAnimal(int idadeAnimal) {
        this.idadeAnimal = idadeAnimal;
    }

    public void setSexoAnimal(int sexoAnimal) {
        this.sexoAnimal = sexoAnimal;
    }

  
}

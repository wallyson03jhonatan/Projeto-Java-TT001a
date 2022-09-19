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
    private int idCliente;
    private int idEspecie;
    
    public Animal() {
    }
    

    public Animal(int id, String nome, int idadeAnimal, int sexoAnimal, int idCliente, int idEspecie) {
        this.id = id;
        this.nome = nome;
        this.idadeAnimal = idadeAnimal;
        this.sexoAnimal = sexoAnimal;
        this.idCliente = idCliente;
        this.idEspecie = idEspecie;
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

    public int getIdCliente() {
        return idCliente;
    }

    public int getIdEspecie() {
        return idEspecie;
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

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public void setIdEspecie(int idEspecie) {
        this.idEspecie = idEspecie;
    }

    @Override
    public String toString() {
        return "Animal{" + "id=" + id + ", nome=" + nome + ", idadeAnimal=" + idadeAnimal + ", sexoAnimal=" + sexoAnimal + ", idCliente=" + idCliente + ", idEspecie=" + idEspecie + '}';
    }  
}

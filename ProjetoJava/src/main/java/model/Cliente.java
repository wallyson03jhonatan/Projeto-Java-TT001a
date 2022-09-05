/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Wally 
 */
public class Cliente {
    private int id;
    private String nome;
    private String cpf;
    
    public Cliente() {
    }
    
    private List<Animal> animais; 
      
    public Cliente(int id, String nome, String cpf) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.animais = new ArrayList<Animal>();

    }
   

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

     public void addAnimal(Animal animal){
        animais.add(animal);
    }
    
    public List<Animal> getAnimals(){
        List<Animal> copia = new ArrayList<Animal> (animais);
        return copia;
    }

    @Override
    public String toString() {
        String desc = "Cliente{" + "id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", animais=" + animais + '}';
        String strAnimais = animais.toString();
        return desc + "\n" + strAnimais;
    }
}


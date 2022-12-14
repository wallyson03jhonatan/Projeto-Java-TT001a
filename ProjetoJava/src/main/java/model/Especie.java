package model;

public class Especie {
    private int id;
    private String nome;
    
    public Especie() {
    }

    public Especie(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
      
    public String toString() {
        return "Especie{" + "id=" + id + ", nome=" + nome + '}';
    }
}

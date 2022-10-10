package model;

public class Animal {
    private int id;
    private String nome;
    private int anoNascAnimal;
    private int sexoAnimal;
    private int idCliente;
    private int idEspecie;
    
    public Animal() {
    }
    

    public Animal(int id, String nome, int anoNascAnimal, int sexoAnimal, int idCliente, int idEspecie) {
        this.id = id;
        this.nome = nome;
        this.anoNascAnimal = anoNascAnimal;
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

    public int getAnoNascAnimal() {
        return anoNascAnimal;
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

    public void setAnoNascAnimal(int anoNascAnimal) {
        this.anoNascAnimal = anoNascAnimal;
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
        return "Animal{" + "id=" + id + ", nome=" + nome + ", idadeAnimal=" + anoNascAnimal + ", sexoAnimal=" + sexoAnimal + ", idCliente=" + idCliente + ", idEspecie=" + idEspecie + '}';
    }  
}

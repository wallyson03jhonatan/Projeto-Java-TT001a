package model;

public class Animal {
    private int id;
    private String nome;
    private int anoNasc;
    private String sexo;
    private int idCliente;
    private int idEspecie;
    
    public Animal() {
    }
    

    public Animal(int id, String nome, int anoNasc, String sexo, int idCliente, int idEspecie) {
        this.id = id;
        this.nome = nome;
        this.anoNasc = anoNasc;
        this.sexo = sexo;
        this.idCliente = idCliente;
        this.idEspecie = idEspecie;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getAnoNasc() {
        return anoNasc;
    }

    public String getSexo() {
        return sexo;
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

    public void setAnoNasc(int anoNasc) {
        this.anoNasc = anoNasc;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public void setIdEspecie(int idEspecie) {
        this.idEspecie = idEspecie;
    }

    @Override
    public String toString() {
        return "Animal{" + "id=" + id + ", nome=" + nome + ", idadeAnimal=" + anoNasc + ", sexoAnimal=" + sexo + ", idCliente=" + idCliente + ", idEspecie=" + idEspecie + '}';
    }  
}

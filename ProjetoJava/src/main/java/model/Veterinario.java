package model;

public class Veterinario {
    private int id; 
    private String nome;
    private String cpf;
    private String telefone;

    public Veterinario(int id, String nome, String cpf, String telefone) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
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

    public String getTelefone() {
        return telefone;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
 
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "Veterinario{" + "id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", telefone=" + telefone + '}';
    }
    
}

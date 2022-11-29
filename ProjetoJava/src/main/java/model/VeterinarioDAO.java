package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static model.DAO.getConnection;

public class VeterinarioDAO extends DAO {
    private static VeterinarioDAO instance;
    
    private VeterinarioDAO(){
        getConnection();
        createTable();
    }
    
    public static VeterinarioDAO getInstance(){
        return (instance==null?(instance = new VeterinarioDAO()):instance);
    }
    
    public Veterinario create(String nome, String cpf, String telefone){
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("INSERT INTO veterinario (nome, cpf, telefone) VALUES (?, ?, ?)");
            stmt.setString(1, nome);
            stmt.setString(2, cpf);            
            stmt.setString(3, telefone);
            executeUpdate(stmt);
        } catch (SQLException ex){
            Logger.getLogger(VeterinarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.retrieveById(lastId("veterinario", "id"));
    };

    public Veterinario buildingObject(ResultSet rs){
        Veterinario vet = null;
        try {
            vet = new Veterinario(rs.getInt("id"), rs.getString("nome"), rs.getString("cpf"), rs.getString("telefone"));
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return vet;
    }
    
    public List retrieve(String query){
        List<Veterinario> veterinarios = new ArrayList();
        ResultSet rs = getResultSet(query);
        try {
            while (rs.next()){
                veterinarios.add(buildingObject(rs));
            }
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return veterinarios;
    };
    
    public List retrieveAll(){
        return this.retrieve("SELECT * FROM veterinario");
    }
    
    public List retrieveLast(){
        return this.retrieve("SELECT * FROM veterinario WHERE id = " + lastId("veterinario", "id"));
    }
    
    public Veterinario retrieveById(int id){
        List<Veterinario> veterinario = this.retrieve("SELECT * FROM veterinario WHERE id = " + id);
        return (veterinario.isEmpty()?null:veterinario.get(0));
    };
    
    public List retrieveBySimilarName(String nome){
        return this.retrieve("SELECT * FROM veterinario WHERE nome LIKE '%" + nome + "%'");
    }
    
    public void update(Veterinario veterinario){
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("UPDATE veterinario set nome=?, cpf=?, telefone=? where id=?");
            stmt.setString(1, veterinario.getNome());
            stmt.setString(2, veterinario.getCpf());
            stmt.setString(3, veterinario.getTelefone()); 
            stmt.setInt(4, veterinario.getId());
            executeUpdate(stmt);
        } catch (SQLException e){
            System.err.println("Exception: " + e.getMessage());
        }
    };
    
    public void delete(int id){
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("DELETE FROM veterinario where id=?");
            stmt.setInt(1, id);
            executeUpdate(stmt);
        } catch (SQLException e){
            System.err.println("Exception: " + e.getMessage());
        }
    };
}
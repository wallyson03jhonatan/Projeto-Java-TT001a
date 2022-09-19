package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;

public class AnimalDAO extends DAO {
    private static AnimalDAO instance;
    
    private AnimalDAO(){
        getConnection();
        createTable();
    }
    
      
    public static AnimalDAO getInstance(){
        return (instance==null?(instance = new AnimalDAO()):instance);
    }
    
    public Animal create(String nome, int idadeAnimal, int  sexoAnimal, int idCliente, int idEspecie){
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("INSERT INTO animal (nome, idadeAnimal, sexoAnimal, idCliente, idEspecie) VALUES (?, ?, ?, ?, ?)");
            stmt.setString(1, nome);
            stmt.setInt(2, idadeAnimal);
            stmt.setInt(3, sexoAnimal); 
            stmt.setInt(4, idCliente);
            stmt.setInt(5, idEspecie);
            executeUpdate(stmt);
        } catch (SQLException ex){
            Logger.getLogger(AnimalDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.retrieveById(lastId("animal", "id"));
    };

     public Animal buildingObject(ResultSet rs){
        Animal animal = null;
        try {
            animal = new Animal(rs.getInt("id"), rs.getString("nome"), rs.getInt("idadeAnimal"), rs.getInt("sexoAnimal"), rs.getInt("idCliente"), rs.getInt("idEspecie"));
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return animal;
    }
      public List retrieve(String query){
        List<Animal> animais = new ArrayList();
        ResultSet rs = getResultSet(query);
        try {
            while (rs.next()){
                animais.add(buildingObject(rs));
            }
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return animais;
    };

       public List retrieveAll(){
        return this.retrieve("SELECT * FROM animal");
    }
    
    public List retrieveLast(){
        return this.retrieve("SELECT * FROM animal WHERE id = " + lastId("animal", "id"));
    }
    
    public Animal retrieveById(int id){
        List<Animal> animais = this.retrieve("SELECT * FROM animal WHERE id = " + id);
        return (animais.isEmpty()?null:animais.get(0));
    };
    
    
    public List retrieveBySimilarNome(String nome){
        return this.retrieve("SELECT * FROM animal WHERE nome LIKE '%" + nome + "%'");
    }
    
    public void update(int id, String nome, int idadeAnimal, int sexoAnimal, int idCliente, int idEspecie){
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("UPDATE animal set nome=?, idadeAnimal=?, sexoAnimal=?, idCliente=?, idEspecie=? where id=?");
            stmt.setString(1, nome);
            stmt.setInt(2, idadeAnimal);
            stmt.setInt(3, sexoAnimal);   
            stmt.setInt(4, idCliente);
            stmt.setInt(5, idEspecie);
            stmt.setInt(6, id);
            executeUpdate(stmt);
        } catch (SQLException e){
            System.err.println("Exception: " + e.getMessage());
        }
    };

     public void delete(int id){
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("DELETE FROM animal where id=?");
            stmt.setInt(1, id);
            executeUpdate(stmt);
        } catch (SQLException e){
            System.err.println("Exception: " + e.getMessage());
        }
    };

}
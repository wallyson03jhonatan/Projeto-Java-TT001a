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
    
    public Animal create(String nome, int anoNascAnimal, String  sexoAnimal, int idCliente, int idEspecie){
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("INSERT INTO animal (nome, anoNasc, sexo, id_cliente, id_especie) VALUES (?, ?, ?, ?, ?)");
            stmt.setString(1, nome);
            stmt.setInt(2, anoNascAnimal);
            stmt.setString(3, sexoAnimal); 
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
            animal = new Animal(rs.getInt("id"), rs.getString("nome"), rs.getInt("anoNasc"), rs.getString("sexo"), rs.getInt("id_cliente"), rs.getInt("id_especie"));
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
    
    public List retrieveByCliente(int id){
        return this.retrieve("SELECT * FROM animal WHERE id_cliente = " + id);
    };
    
    public List retrieveByEspecie(int id){
        return this.retrieve("SELECT * FROM animal WHERE id_especie = " + id);
    };
    
    public List retrieveBySimilarNome(String nome, int idCliente){
        return this.retrieve("SELECT * FROM animal WHERE nome LIKE '%" + nome + "%' and id_cliente = " + idCliente);
    }
    
    public void update(Animal animal){
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("UPDATE animal set nome=?, anoNasc=?, sexo=? where id=?");
            stmt.setString(1, animal.getNome());
            stmt.setInt(2, animal.getAnoNasc());
            stmt.setString(3, animal.getSexo());   
            stmt.setInt(4, animal.getId());
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
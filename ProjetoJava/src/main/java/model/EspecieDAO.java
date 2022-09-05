package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static model.DAO.getConnection;

public class EspecieDAO extends DAO {
    private static EspecieDAO instance;
    
    private EspecieDAO(){
        getConnection();
        createTable();
    }
    
    public static EspecieDAO getInstance(){
        return (instance==null?(instance = new EspecieDAO()):instance);
    }
    
    public Especie create(String nome){
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("INSERT INTO especie (nome) VALUES (?)");
            stmt.setString(1, nome);
            executeUpdate(stmt);
        } catch (SQLException ex){
            Logger.getLogger(AnimalDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.retrieveById(lastId("especie", "id"));
    };
    
     public Especie buildingObject(ResultSet rs){
        Especie especie = null;
        try {
            especie = new Especie(rs.getInt("id"), rs.getString("nome"));
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return Especie;
    }
    
    public List retrieve(String query){
        List<Especie> especies = new ArrayList();
        ResultSet rs = getResultSet(query);
        try {
            while (rs.next()){
                especies.add(buildingObject(rs));
            }
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return especies;
    };
    
        public List retrieveAll(){
        return this.retrieve("SELECT * FROM especie");
    }
    
    public List retrieveLast(){
        return this.retrieve("SELECT * FROM especie WHERE id = " + lastId("especie", "id"));
    }
    
    public Especie retrieveById(int id){
        List<Especie> especie = this.retrieve("SELECT * FROM especie WHERE id = " + id);
        return (especie.isEmpty()?null:especie.get(0));
    };
    
    public List retrieveBySimilarName(String nome){
        return this.retrieve("SELECT * FROM especie WHERE nome LIKE '%" + nome + "%'");
    }
    
    public void update(int id, String nome){
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("UPDATE especie set nome=? where id=?");
            stmt.setString(1, nome);
            stmt.setInt(2, id);
            executeUpdate(stmt);
        } catch (SQLException e){
            System.err.println("Exception: " + e.getMessage());
        }
    };
    
    public void delete(int id){
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("DELETE FROM especie where id=?");
            stmt.setInt(1, id);
            executeUpdate(stmt);
        } catch (SQLException e){
            System.err.println("Exception: " + e.getMessage());
        }
    };
}
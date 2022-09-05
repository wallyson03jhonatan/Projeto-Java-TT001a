package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static model.DAO.getConnection;

public class ClienteDAO extends DAO {
    private static ClienteDAO instance;
    
    private ClienteDAO(){
        getConnection();
        createTable();
    }
    
      public static ClienteDAO getInstance(){
        return (instance==null?(instance = new ClienteDAO()):instance);
    }
    
    public Cliente create(String nome, String cpf){
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("INSERT INTO cliente (nome, cpf) VALUES (?, ?)");
            stmt.setString(1, nome);
            stmt.setString(2, cpf);
            executeUpdate(stmt);
        } catch (SQLException ex){
            Logger.getLogger(AnimalDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.retrieveById(lastId("cliente", "id"));
    };
    
    public Cliente buildingObject(ResultSet rs){
        Cliente cliente = null;
        try {
            cliente = new Cliente(rs.getInt("id"), rs.getString("nome"), rs.getString("cpf"));
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return cliente;
    }
     
     public List retrieve(String query){
        List<Cliente> clientes = new ArrayList();
        ResultSet rs = getResultSet(query);
        try {
            while (rs.next()){
                clientes.add(buildingObject(rs));
            }
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return clientes;
    };
     
    public List retrieveAll(){
        return this.retrieve("SELECT * FROM cliente");
    }
    
    public List retrieveLast(){
        return this.retrieve("SELECT * FROM cliente WHERE id = " + lastId("cliente", "id"));
    }
    
    public Cliente retrieveById(int id){
        List<Cliente> cliente = this.retrieve("SELECT * FROM cliente WHERE id = " + id);
        return (cliente.isEmpty()?null:cliente.get(0));
    };
    
    public List retrieveBySimilarName(String nome){
        return this.retrieve("SELECT * FROM cliente WHERE nome LIKE '%" + nome + "%'");
    }
    
    public void update(int id, String nome, String cpf){
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("UPDATE cliente set nome=?, cpf=? where id=?");
            stmt.setString(1, nome);
            stmt.setString(2, cpf);
            stmt.setInt(3, id);
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
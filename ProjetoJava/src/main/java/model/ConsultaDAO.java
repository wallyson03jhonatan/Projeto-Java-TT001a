/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static model.DAO.getConnection;
import java.sql.Date;

public class ConsultaDAO extends DAO {
    private static ConsultaDAO instance;
    
    private ConsultaDAO(){
        getConnection();
        createTable();
    }
    
    public static ConsultaDAO getInstance(){
        return (instance==null?(instance = new ConsultaDAO()):instance);
    }
    
    public Consulta create(Date dataConsulta, String historico){
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("INSERT INTO consult (dataConsulta, historico) VALUES (?, ?)");
            stmt.setDate(1, dataConsulta);
            stmt.setString(2, historico);                             
            executeUpdate(stmt);
        } catch (SQLException ex){
            Logger.getLogger(ConsultaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.retrieveById(lastId("consulta", "id"));
    };

    public Consulta buildingObject(ResultSet rs){
        Consulta conulta = null;
        try {
            conulta = new Consulta(rs.getInt("id"), rs.getDate("dataConsulta"), rs.getString("historico"));
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return conulta;
    }
    
    public List retrieve(String query){
        List<Consulta> consultas = new ArrayList();
        ResultSet rs = getResultSet(query);
        try {
            while (rs.next()){
                consultas.add(buildingObject(rs));
            }
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return consultas;
    };
    
    public List retrieveAll(){
        return this.retrieve("SELECT * FROM consulta");
    }
    
    public List retrieveLast(){
        return this.retrieve("SELECT * FROM consulta WHERE id = " + lastId("consulta", "id"));
    }
    
    public Consulta retrieveById(int id){
        List<Consulta> consulta = this.retrieve("SELECT * FROM consulta WHERE id = " + id);
        return (consulta.isEmpty()?null:consulta.get(0));
    };
    
    public void update(int id, Date dataConsulta, String historico){
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("UPDATE consulta set dataConsulta=?, historico=? where id=?");
            stmt.setDate(1, dataConsulta);
            stmt.setString(2, historico);
            stmt.setInt(3, id);
            executeUpdate(stmt);
        } catch (SQLException e){
            System.err.println("Exception: " + e.getMessage());
        }
    };
    
    public void delete(int id){
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("DELETE FROM consulta where id=?");
            stmt.setInt(1, id);
            executeUpdate(stmt);
        } catch (SQLException e){
            System.err.println("Exception: " + e.getMessage());
        }
    };
}
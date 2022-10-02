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

public class TratamentoDAO extends DAO{
    private static TratamentoDAO instance;
    
    private TratamentoDAO(){
        getConnection();
        createTable();
    }
    
    public static TratamentoDAO getInstance(){
        return (instance==null?(instance = new TratamentoDAO()):instance);
    }
    
    public Tratamento create(Date dataIni, Date dataFim, int protocolo){
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("INSERT INTO Tratamento (ini, fim, protocolo) VALUES (?, ?, ?)");
            stmt.setDate(1, dataIni);
            stmt.setDate(2, dataFim);
            stmt.setInt(3, protocolo);            
            executeUpdate(stmt);
        } catch (SQLException ex){
            Logger.getLogger(TratamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.retrieveById(lastId("tratamento", "protocolo"));
    };

    public Tratamento buildingObject(ResultSet rs){
        Tratamento tratamento = null;
        try {
            tratamento = new Tratamento(rs.getInt("protocolo"), rs.getDate("ini"), rs.getDate("fim"));
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return tratamento;
    }
    
    public List retrieve(String query){
        List<Tratamento> tratamentos = new ArrayList();
        ResultSet rs = getResultSet(query);
        try {
            while (rs.next()){
                tratamentos.add(buildingObject(rs));
            }
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return tratamentos;
    };
    
    public List retrieveAll(){
        return this.retrieve("SELECT * FROM tratamento");
    }
    
    public List retrieveLast(){
        return this.retrieve("SELECT * FROM tratamento WHERE id = " + lastId("tratamento", "protocolo"));
    }
    
    public Tratamento retrieveById(int protocolo){
        List<Tratamento> tratamento = this.retrieve("SELECT * FROM tratamento WHERE protocolo = " + protocolo);
        return (tratamento.isEmpty()?null:tratamento.get(0));
    };
    
    public void update(int protocolo, Date ini, Date fim){
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("UPDATE tratamento set ini=?, fim=? where protocolo=?");
            stmt.setDate(1, ini);
            stmt.setDate(2, fim);
            stmt.setInt(3, protocolo); 
            executeUpdate(stmt);
        } catch (SQLException e){
            System.err.println("Exception: " + e.getMessage());
        }
    };
    
    public void delete(int protocolo){
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("DELETE FROM tratamento where protocolo=?");
            stmt.setInt(1, protocolo);
            executeUpdate(stmt);
        } catch (SQLException e){
            System.err.println("Exception: " + e.getMessage());
        }
    };
}
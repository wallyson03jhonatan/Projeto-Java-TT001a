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
import java.util.Calendar;

public class ConsultaDAO extends DAO {
    private static ConsultaDAO instance;
    
    private ConsultaDAO(){
        getConnection();
        createTable();
    }
    
    public static ConsultaDAO getInstance(){
        return (instance==null?(instance = new ConsultaDAO()):instance);
    }
    
    public Consulta create(Calendar data, Integer hora, String historico, int idAnimal, int idVeterinario, boolean finalizado){
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("INSERT INTO consulta (data, hora, historico, id_animal, id_veterinario, finalizado) VALUES (?, ?, ?, ?, ?, ?)");
            stmt.setDate(1, new Date(data.getTimeInMillis()));
            stmt.setInt(2, hora);            
            stmt.setString(3, historico);
            stmt.setInt(4, idAnimal);
            stmt.setInt(5, idVeterinario);            
            stmt.setBoolean(6, finalizado);
            executeUpdate(stmt);
        } catch (SQLException ex){
            Logger.getLogger(ConsultaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.retrieveById(lastId("consulta", "id"));
    };

    public Consulta buildingObject(ResultSet rs){
        Consulta conulta = null;
        try {
            Calendar dt = Calendar.getInstance();
            dt.setTime(rs.getDate("data"));
            conulta = new Consulta(rs.getInt("id"), dt, rs.getInt("hora"), rs.getString("historico"), rs.getInt("id_animal"), rs.getInt("id_veterinario"), rs.getBoolean("finalizado"));
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
    
    public List retrieveByAnimal(int id){
        return this.retrieve("SELECT * FROM consulta WHERE id_animal = " + id);
    };
    
    public List retrieveByVeterinario(int id){
        return this.retrieve("SELECT * FROM consulta WHERE id_veterinario = " + id);
    };
    
    public void update(Consulta consulta){
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("UPDATE consulta set dataConsulta=?, hora_consulta=?, historico=?, finalizado=? where id=?");
            stmt.setDate(1, new Date(consulta.getData().getTimeInMillis()));
            stmt.setInt(2, consulta.getHora());
            stmt.setString(3, consulta.getHistorico());            
            stmt.setBoolean(4, consulta.isFinalizado());
            stmt.setInt(5, consulta.getId());
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
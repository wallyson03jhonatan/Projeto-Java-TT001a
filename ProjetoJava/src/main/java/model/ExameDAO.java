package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Date;
import java.util.Calendar;
import static model.DAO.getConnection;

public class ExameDAO extends DAO {
  private static ExameDAO instance;
    
    private ExameDAO(){
        getConnection();
        createTable();
    }
    
    public static ExameDAO getInstance(){
        return (instance==null?(instance = new ExameDAO()):instance);
    }
    
    public Exame create(String descricao, Calendar data, int idConsulta){
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("INSERT INTO exame (descricao, data, id_consulta) VALUES (?, ?, ?)");
            stmt.setString(1, descricao);
            stmt.setDate(2, new Date(data.getTimeInMillis()));
            stmt.setInt(3, idConsulta);
            executeUpdate(stmt);
        } catch (SQLException ex){
            Logger.getLogger(ExameDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.retrieveById(lastId("exame", "id"));
    };

    public Exame buildingObject(ResultSet rs){
        Exame exame = null;
        try {
            Calendar dt = Calendar.getInstance();
            dt.setTime(rs.getDate("data"));
            exame = new Exame(rs.getInt("id"), rs.getString("descricao"), dt, rs.getInt("id_consulta"));
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return exame;
    }
    
    public List retrieve(String query){
        List<Exame> exames = new ArrayList();
        ResultSet rs = getResultSet(query);
        try {
            while (rs.next()){
                exames.add(buildingObject(rs));
            }
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return exames;
    };
    
    public List retrieveAll(){
        return this.retrieve("SELECT * FROM exame");
    }
    
    public List retrieveLast(){
        return this.retrieve("SELECT * FROM exame WHERE id = " + lastId("exame", "id"));
    }
    
    public Exame retrieveById(int id){
        List<Exame> exames = this.retrieve("SELECT * FROM exame WHERE id = " + id);
        return (exames.isEmpty()?null:exames.get(0));
    };
    
    public List retrieveByConsulta(int id){
        return this.retrieve("SELECT * FROM exame WHERE id_consulta = " + id);
    };
    
    public List retrieveBySimilarDescricao(String descricao, int idConsulta){
        return this.retrieve("SELECT * FROM exame WHERE descricao LIKE '%" + descricao + "%' and id_consulta = " + idConsulta);
    };
    
    public void update(Exame exame){
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("UPDATE exame set descricao=?, data=? where id=?");
            stmt.setString(1, exame.getDescricao());
            stmt.setDate(2, new Date(exame.getData().getTimeInMillis()));
            stmt.setInt(3, exame.getId());
            executeUpdate(stmt);
        } catch (SQLException e){
            System.err.println("Exception: " + e.getMessage());
        }
    };
    
    public void delete(int id){
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("DELETE FROM exame where id=?");
            stmt.setInt(1, id);
            executeUpdate(stmt);
        } catch (SQLException e){
            System.err.println("Exception: " + e.getMessage());
        }
    };  
}
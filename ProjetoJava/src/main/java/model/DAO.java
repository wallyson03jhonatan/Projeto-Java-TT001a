package model;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class DAO {
    public static final String DBURL = "jdbc:sqlite:clinica_veterinaria8.db";
    private static Connection con;
    protected static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    //Conexão com o banco 
    public static Connection getConnection(){
        if (con == null){
            try {
                con = DriverManager.getConnection(DBURL);
                if (con != null) {
                    DatabaseMetaData meta = con.getMetaData();
                }
            } 
            catch (SQLException e){
                System.err.println("Exception: " + e.getMessage());
            }
        }
        return con;
    }
    
    protected ResultSet getResultSet(String query){
        Statement s;
        ResultSet rs = null;
        try {
            s = (Statement) con.createStatement();
            rs = s.executeQuery(query);
        } 
        catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return rs;
    }
    
    protected int executeUpdate(PreparedStatement queryStatement) throws SQLException{  
        int update;
        update = queryStatement.executeUpdate();
        return update;
    }
    
    protected int lastId(String tableName, String primaryKey){
        Statement s;
        int lastId = -1;
        try {
            s = (Statement) con.createStatement();
            ResultSet rs = s.executeQuery("SELECT MAX(" + primaryKey + ") as id FROM " + tableName);
            if (rs.next()){
                lastId = rs.getInt("id");
            }
        } 
        catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return lastId;
    }
    
    // Tabelas banco criação
    protected final boolean createTable(){
        try {
            PreparedStatement stmt;
            
            //stmt = DAO.getConnection().prepareStatement("DROP TABLE animal; /n");
            //executeUpdate(stmt);
            
            //Tabela Cliente
            stmt = DAO.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS cliente (\n"
                    + "id INTEGER PRIMARY KEY, \n"
                    + "nome VARCHAR, \n"
                    + "cpf VARCHAR); \n"
            );
            executeUpdate(stmt);
            
            // Tabela Especie
            stmt = DAO.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS especie (\n"
                    + "id INTEGER PRIMARY KEY, \n"
                    + "nome VARCHAR); \n"
            );
            executeUpdate(stmt);
            
            // Tabela Animal
            stmt = DAO.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS animal (\n"
                    + "id INTEGER PRIMARY KEY, \n"
                    + "nome VARCHAR, \n" 
                    + "anoNasc INTEGER, \n"
                    + "sexo INTEGER, \n"
                    + "id_cliente INTEGER, \n"
                    + "id_especie INTEGER); \n"
            );
            executeUpdate(stmt);
            
            // Tabela Veterinario
            stmt = DAO.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS veterinario (\n"
                    + "id INTEGER PRIMARY KEY, \n"
                    + "nome VARCHAR, \n"
                    + "cpf VARCHAR, \n"
                    + "telefone VARCHAR); \n"
            );
            executeUpdate(stmt);
            
            // Tabela Tratamento
            stmt = DAO.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS tratamento (\n"
                    + "protocolo INTEGER PRIMARY KEY, \n"
                    + "ini DATE, \n"
                    + "fim DATE,\n"
                    + "id_animal INTEGER,\n"
                    + "id_consulta INTEGER); \n"
            );
            executeUpdate(stmt);
            
            // Tabela Consulta
            stmt = DAO.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS consulta (\n"
                    + "id INTEGER PRIMARY KEY, \n"
                    + "data DATE, \n"
                    + "hora INTEGER, \n"
                    + "historico VARCHAR,\n"
                    + "id_animal INTEGER, \n"
                    + "id_veterinario INTEGER, \n"
                    + "finalizado BOOLEAN); \n"
            );
            executeUpdate(stmt);
         
            stmt = DAO.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS exame (\n"
                    + "id INTEGER PRIMARY KEY, \n"
                    + "data DATE, \n"
                    + "descricao VARCHAR,\n"
                    + "id_consulta INTEGER); \n"  
            );
            executeUpdate(stmt);
        } 
        catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return true;
    }
    
    public static void Terminar(){
        try {
            (DAO.getConnection()).close();
        } catch (SQLException e){
            System.err.println("Exception: " + e.getMessage());
        }
    }
}
package model;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class DAO {
    public static final String DBURL = "jdbc:sqlite:vet.db";
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
            ResultSet rs = s.executeQuery("SELECT MAX(" + primaryKey + ") as id FROM " + tableName);;
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

            //Tabela Cliente
            stmt = DAO.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS Cliente (\n"
                    + "id INTEGER PRIMARY KEY, \n"
                    + "nome VARCHAR, \n"
                    + "cpf VARCHAR, \n");
            executeUpdate(stmt);
            
            // Tabela Especie
            stmt = DAO.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS Spicie (\n"
                    + "id INTEGER PRIMARY KEY, \n"
                    + "nome VARCHAR); \n"
            );
            executeUpdate(stmt);
            
            // Tabela Animal
            stmt = DAO.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS Animal (\n"
                    + "id INTEGER PRIMARY KEY, \n"
                    + "nome VARCHAR, \n" 
                    + "idadeAnimal INTEGER, \n"
                    + "sexoAnimal INTEGER, \n"

            );
            executeUpdate(stmt);
            
            // Tabela Veterinario
            stmt = DAO.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS Veterinario (\n"
                    + "id INTEGER PRIMARY KEY, \n"
                    + "nome VARCHAR, \n"
                    + "cpf VARCHAR, \n"
                    + "telefone VARCHAR, \n"

            );
            executeUpdate(stmt);
            
            // Tabela Tratamento
            stmt = DAO.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS Tratamento (\n"
                    + "protocolo INTEGER PRIMARY KEY, \n"
                    + "dataIni DATE, \n"
                    + "dataFim DATE); \n"
            );
            executeUpdate(stmt);
            
            // Tabela Consulta
            stmt = DAO.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS Consulta (\n"
                    + "id INTEGER PRIMARY KEY, \n"
                    + "dataConsulta DATE, \n"
                    + "historico VARCHAR); \n"
            );
            executeUpdate(stmt);
         
            stmt = DAO.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS Exame (\n"
                    + "id INTEGER PRIMARY KEY, \n"
                    + "dataExame DATE, \n"
                    + "descricao VARCHAR); \n"  
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
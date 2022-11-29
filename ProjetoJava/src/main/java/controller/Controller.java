package controller;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import java.util.ArrayList;
import java.util.List;
import model.*;
import view.*;
import view.GenericTableModel;
import java.util.Calendar;

public class Controller {
    private static Cliente clienteSelecionado = null;
    private static Especie especieSelecionado = null;
    private static Animal animalSelecionado = null;
    private static Veterinario veterinarioSelecionado = null;
    private static Consulta consultaSelecionado = null;
    private static Exame exameSelecionado = null;
    private static JTextField clienteSelecionadoTextField = null;
    private static JTextField especieSelecionadoTextField = null;
    private static JTextField animalSelecionadoTextField = null;
    private static JTextField veterinarioSelecionadoTextField = null;    
    private static JTextField consultaSelecionadoTextField = null;
    private static JTextField exameSelecionadoTextField = null;

    
    public static void setTextFields(JTextField cliente, JTextField especie, JTextField animal, JTextField veterinario, JTextField consulta, JTextField exame){
        clienteSelecionadoTextField = cliente;
        especieSelecionadoTextField = especie;
        animalSelecionadoTextField = animal;
        veterinarioSelecionadoTextField = veterinario;
        consultaSelecionadoTextField = consulta;
        exameSelecionadoTextField = exame;
    }
    
    public static void setTableModel(JTable table, GenericTableModel tableModel){
        table.setModel(tableModel);
    }
    
    public static Cliente getClienteSelecionado(){
        return clienteSelecionado;
    }
    
    public static Especie getEspecieSelecionado(){
        return especieSelecionado;
    }
    
    public static Animal getAnimalSelecionado(){
        return animalSelecionado;
    }
    
    public static Veterinario getVeterinarioSelecionado(){
        return veterinarioSelecionado;
    }
    
    public static Consulta getConsultaSelecionado(){
        return consultaSelecionado;
    }
    
    public static Exame getExameSelecionado(){
        return exameSelecionado;
    }
    
    public static void setSelected(Object selected, JTable table){
        if (selected instanceof Cliente){
            clienteSelecionado = (Cliente)selected;
            clienteSelecionadoTextField.setText(clienteSelecionado.getNome());
            setAnimalModel(table);
            animalSelecionado = null;
            animalSelecionadoTextField.setText("");
        } else if (selected instanceof Especie){
            especieSelecionado = (Especie)selected;
            especieSelecionadoTextField.setText(especieSelecionado.getNome());
        } else if (selected instanceof Animal){
            animalSelecionado = (Animal)selected;
            animalSelecionadoTextField.setText(animalSelecionado.getNome());
        } else if (selected instanceof Veterinario){
            veterinarioSelecionado = (Veterinario)selected;
            veterinarioSelecionadoTextField.setText(veterinarioSelecionado.getNome());
        } else if (selected instanceof Consulta){
            consultaSelecionado = (Consulta)selected;
            consultaSelecionadoTextField.setText(String.valueOf(consultaSelecionado.getId()));
            setExameModel(table);
            exameSelecionado = null;
            exameSelecionadoTextField.setText("");
        } else if (selected instanceof Exame){
            exameSelecionado = (Exame)selected;
            exameSelecionadoTextField.setText(exameSelecionado.getDescricao());
        }
    }
    
    public static void setClienteModel(JTable table){
        setTableModel(table, new ClienteTableModel(ClienteDAO.getInstance().retrieveAll()));
    }
    
    public static boolean setAnimalModel(JTable table){
        if (getClienteSelecionado() != null){
            setTableModel(table, new AnimalTableModel(AnimalDAO.getInstance().retrieveByCliente(getClienteSelecionado().getId())));
            return true;
        } else {
            setTableModel(table, new AnimalTableModel(new ArrayList()));
            return false;
        }
    }
    
    public static void setEspecieModel(JTable table){
        setTableModel(table, new EspecieTableModel(EspecieDAO.getInstance().retrieveAll()));
    }
    
    public static void setVeterinarioModel(JTable table){
        setTableModel(table, new VeterinarioTableModel(VeterinarioDAO.getInstance().retrieveAll()));
    }
    
    public static void setConsultaModel(JTable table){
        setTableModel(table, new ConsultaTableModel(ConsultaDAO.getInstance().retrieveAll()));
    }
    
    public static boolean setExameModel(JTable table){
        if (getConsultaSelecionado() != null){
            setTableModel(table, new ExameTableModel(ExameDAO.getInstance().retrieveByConsulta(getConsultaSelecionado().getId())));
            return true;
        } else {
            setTableModel(table, new ExameTableModel(new ArrayList()));
            return false;
        }
    }
    
    public static void getClienteBySimilarNomeBuscar(JTable table, String nomeParcial){
        ((GenericTableModel)table.getModel()).addListOfItems(ClienteDAO.getInstance().retrieveBySimilarName(nomeParcial));
    }
    
    public static void getAnimalBySimilarNomeBuscar(JTable table, String nomeParcial){
        ((GenericTableModel)table.getModel()).addListOfItems(AnimalDAO.getInstance().retrieveBySimilarNome(nomeParcial, getClienteSelecionado().getId()));
    }
    
    public static void getEspecieBySimilarNomeBuscar(JTable table, String nomeParcial){
        ((GenericTableModel)table.getModel()).addListOfItems(EspecieDAO.getInstance().retrieveBySimilarName(nomeParcial));
    }
    
    public static void getVeterinarioBySimilarNomeBuscar(JTable table, String nomeParcial){
        ((GenericTableModel)table.getModel()).addListOfItems(VeterinarioDAO.getInstance().retrieveBySimilarName(nomeParcial));
    }
    
    public static void getExameBySimilarDescricaoBuscar(JTable table, String descricaoParcial){
        ((GenericTableModel)table.getModel()).addListOfItems(ExameDAO.getInstance().retrieveBySimilarDescricao(descricaoParcial, getConsultaSelecionado().getId()));
    }
    
    public static Cliente salvarCliente(){
        Cliente cliente = ClienteDAO.getInstance().create("", "");
        return cliente;
    }
    
    public static Animal salvarAnimal(){
        Animal animal = AnimalDAO.getInstance().create("", 0, "", getClienteSelecionado().getId(), getEspecieSelecionado().getId());
        return animal;
    }
    
    public static Especie salvarEspecie(){
        Especie especie = EspecieDAO.getInstance().create("");
        return especie;
    }
    
    public static Veterinario salvarVeterinario(){
        Veterinario veterinario = VeterinarioDAO.getInstance().create("", "", "");
        return veterinario;
    }
    
    public static Consulta salvarConsulta(){
        Consulta consulta = ConsultaDAO.getInstance().create(Calendar.getInstance(),8,"",Controller.getAnimalSelecionado().getId(),Controller.getVeterinarioSelecionado().getId(),false);
        return consulta;
    }
    
     public static Exame salvarExame(){
        Exame exame = ExameDAO.getInstance().create("", Calendar.getInstance(), getConsultaSelecionado().getId());
        return exame;
    }
    
    public static boolean deletarCliente(){
        List<Animal> animals = AnimalDAO.getInstance().retrieveByCliente(getClienteSelecionado().getId());
        if (animals.isEmpty()) {
            ClienteDAO.getInstance().delete(getClienteSelecionado().getId());
            return true;
        } else {
            return false;
        }
    }
    
    public static boolean deletarEspecie(){
        List<Animal> animals = AnimalDAO.getInstance().retrieveByEspecie(getEspecieSelecionado().getId());
        if (animals.isEmpty()) {
            EspecieDAO.getInstance().delete(getEspecieSelecionado().getId());
            return true;
        } else {
            return false;
        }
    }
    
    public static boolean deletarAnimal(){
        List<Consulta> consultas = ConsultaDAO.getInstance().retrieveByAnimal(getAnimalSelecionado().getId());
        if (consultas.isEmpty()){
            AnimalDAO.getInstance().delete(getAnimalSelecionado().getId());
            return true;
        } else {
            return false;
        }
    }
    
    public static boolean deletarVeterinario(){
        List<Consulta> consultas = ConsultaDAO.getInstance().retrieveByVeterinario(getVeterinarioSelecionado().getId());
        if (consultas.isEmpty()){
            VeterinarioDAO.getInstance().delete(getVeterinarioSelecionado().getId());
            return true;
        } else {
            return false;
        }
    }
    
    public static boolean deletarConsulta(){
        List<Consulta> consultas = ExameDAO.getInstance().retrieveByConsulta(getConsultaSelecionado().getId());
        if (consultas.isEmpty()){
            ConsultaDAO.getInstance().delete(getConsultaSelecionado().getId());
            return true;
        } else {
            return false;
        }
    }
    
    public static void deletarExame(){
        ExameDAO.getInstance().delete(getExameSelecionado().getId());
    }
    
    public static void filtrarConsultas(JTable table, JToggleButton jtTodos, JToggleButton jtHoje, JToggleButton jgVeterinario){
        if (table.getModel() instanceof ConsultaTableModel){
            String where = "";
            if(!(jtTodos.isSelected()) && (jtHoje.isSelected()) && (!(jgVeterinario.isSelected()))){
                where = "WHERE data >= date('now')";
            } else if (!(jtTodos.isSelected()) && (!(jtHoje.isSelected())) && (jgVeterinario.isSelected())){
                where = "WHERE id_veterinario = " + getVeterinarioSelecionado().getId();
            } else if (!(jtTodos.isSelected()) && ((jtHoje.isSelected())) && (jgVeterinario.isSelected())) {
                 where = "WHERE data >= date('now') and id_veterinario = " + getVeterinarioSelecionado().getId();
            }
             String query = "SELECT * FROM consulta " + where + " ORDER BY data, hora";
            ((GenericTableModel) table.getModel()).addListOfItems(ConsultaDAO.getInstance().retrieve(query));
        }
    }
}

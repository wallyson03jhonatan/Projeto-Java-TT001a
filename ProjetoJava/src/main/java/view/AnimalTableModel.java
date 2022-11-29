package view;

import java.util.List;
import model.*;

 public class AnimalTableModel extends GenericTableModel{
    public AnimalTableModel(List vDados){
        super(vDados, new String[]{"Nome", "AnoNascimento", "Sexo", "Cliente", "Espécie"});
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex){
        switch (columnIndex){
            case 0 -> {
                return String.class;
            }
            case 1 -> {
                return Integer.class;
            }
            case 2 -> {
                return String.class;
            }
            case 3 -> {
                return String.class;
            }
            case 4 -> {
                return String.class;
            }
            default -> throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Animal animal = (Animal) vDados.get(rowIndex);
        
        switch (columnIndex){
            case 0 -> {
                return animal.getNome();
            }
            case 1 -> {
                return animal.getAnoNasc();
            }
            case 2 -> {
                return animal.getSexo();
            }
            case 3 -> {
                Cliente cliente = ClienteDAO.getInstance().retrieveById(animal.getIdCliente());
                return cliente.getNome();
            }
            case 4 -> {
                Especie especie = EspecieDAO.getInstance().retrieveById(animal.getIdEspecie());
                return especie.getNome();
            }
            default -> throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Animal animal = (Animal) vDados.get(rowIndex);
        
        switch (columnIndex){
            case 0 -> {
                animal.setNome((String) aValue);
                break;
            }
            case 1 -> {
                animal.setAnoNasc((int) aValue);
                break;
            }
            case 2 -> {
                animal.setSexo((String) aValue);
                break;
            }
            case 3 -> {
                break;
            }
            case 4 -> {
                break;
            }
            default -> throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
        
        AnimalDAO.getInstance().update(animal);
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex){
        if (columnIndex > 2) {
            return false;
        } else {
            return true;
        }
    }
}

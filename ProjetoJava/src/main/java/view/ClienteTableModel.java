package view;

import java.util.List;
import model.*;

 public class ClienteTableModel extends GenericTableModel{
    public ClienteTableModel(List vDados){
        super(vDados, new String[]{"Nome", "CPF"});
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex){
        switch (columnIndex){
            case 0 -> {
                return String.class;
            }
            case 1 -> {
                return String.class;
            }
            default -> throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Cliente cliente = (Cliente) vDados.get(rowIndex);
        
        switch (columnIndex){
            case 0 -> {
                return cliente.getNome();
            }
            case 1 -> {
                return cliente.getCpf();
            }
            default -> throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Cliente cliente = (Cliente) vDados.get(rowIndex);
        
        switch (columnIndex){
            case 0 -> {
                cliente.setNome((String) aValue);
                break;
            }
            case 1 -> {
                cliente.setCpf((String) aValue);
                break;
            }
            default -> throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
        
        ClienteDAO.getInstance().update(cliente);
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex){
        return true;
    }
}

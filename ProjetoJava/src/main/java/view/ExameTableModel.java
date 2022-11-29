package view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.*;

 public class ExameTableModel extends GenericTableModel{
     private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
      
    public ExameTableModel(List vDados){
        super(vDados, new String[]{"Data", "Descrição"});
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
        Exame exame = (Exame) vDados.get(rowIndex);
        
        switch (columnIndex){
            case 0 -> {
               return dateFormat.format(exame.getData().getTime());
            }
            case 1 -> {
                return exame.getDescricao();
            }
            default -> throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Exame exame = (Exame) vDados.get(rowIndex);
        
        switch (columnIndex){
            case 0 -> {
                Calendar cal = Calendar.getInstance();
                try{
                    cal.setTime(dateFormat.parse((String) aValue));
                } catch (ParseException ex){
                    Logger.getLogger(ConsultaTableModel.class.getName()).log(Level.SEVERE, null, ex);
                }
                exame.setData(cal);
                break;
            }
            case 1 -> {
                exame.setDescricao((String) aValue);
                break;
            }
            default -> throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
        
        ExameDAO.getInstance().update(exame);
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex){
        return true;
    }
}

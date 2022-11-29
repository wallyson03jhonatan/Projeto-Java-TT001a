package view;

import java.util.List;
import java.util.ArrayList;
import model.*;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

 public class ConsultaTableModel extends GenericTableModel{
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
     
    public ConsultaTableModel(List vDados){
        super(vDados, new String[]{"Data", "Hora", "Histórico", "Cliente", "Animal", "Veterinário", "Exame", "Finalizado"});
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
            case 5 -> {
                return String.class;
            }
            case 6 -> {
                return List.class;
            }
            case 7 -> {
                return Boolean.class;
            }
            default -> throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Consulta consulta = (Consulta) vDados.get(rowIndex);
        
        switch (columnIndex){
            case 0 -> {
                return dateFormat.format(consulta.getData().getTime());
            }
            case 1 -> {
                return consulta.getHora();
            }
            case 2 -> {
                return consulta.getHistorico();
            }
            case 3 -> {
                Animal animal = AnimalDAO.getInstance().retrieveById(consulta.getIdAnimal());
                Cliente cliente = ClienteDAO.getInstance().retrieveById(animal.getIdCliente());
                return cliente.getNome();
            }
            case 4 -> {
                Animal animal = AnimalDAO.getInstance().retrieveById(consulta.getIdAnimal());
                return animal.getNome();
            }
            case 5 -> {
                Veterinario veterinario = VeterinarioDAO.getInstance().retrieveById(consulta.getIdVeterinario());
                return veterinario.getNome();
            }
            case 6 -> {
                List<Exame> exames = ExameDAO.getInstance().retrieveByConsulta(consulta.getId());
                if (exames.isEmpty()){
                    return "";
                }
                var DescricaoExamLista = new ArrayList<String>();
                for (Exame descricaoExame: exames) {
                    DescricaoExamLista.add(descricaoExame.getDescricao());
                }
                return DescricaoExamLista;
            }
            case 7 -> {
                return consulta.isFinalizado();
            }
            default -> throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Consulta consulta = (Consulta) vDados.get(rowIndex);
        
        switch (columnIndex){
            case 0 -> {
                Calendar cal = Calendar.getInstance();
                try{
                    cal.setTime(dateFormat.parse((String) aValue));
                } catch (ParseException ex){
                    Logger.getLogger(ConsultaTableModel.class.getName()).log(Level.SEVERE, null, ex);
                }
                consulta.setData(cal);
                break;
            }
            case 1 -> {
                consulta.setHora((Integer) aValue);
                break;
            }
            case 2 -> {
                consulta.setHistorico((String) aValue);
                break;
            }
            case 3 -> {
                break;
            }
            case 4 -> {
                break;
            }
            case 5 -> {
                break;
            }
            case 6 -> {
                break;
            }
            case 7 -> {
                consulta.setFinalizado((Boolean) aValue);
                break;
            }
            default -> throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
        
        ConsultaDAO.getInstance().update(consulta);
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex){
        if ((columnIndex > 2) && (columnIndex < 7)) {
            return false;
        } else {
            return true;
        }
    }
}

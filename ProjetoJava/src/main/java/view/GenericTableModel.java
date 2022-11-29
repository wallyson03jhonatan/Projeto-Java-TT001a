package view;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;

public abstract class GenericTableModel extends AbstractTableModel{
    protected ArrayList<Object> vDados;
    protected String[] colunas;
    
    public GenericTableModel(List vDados, String[] colunas){
        this.colunas = colunas;
        this.vDados = (ArrayList)vDados;
    }
    
    @Override
    public int getColumnCount(){
        return colunas.length;
    }
    
    @Override
    public int getRowCount(){
        return vDados.size();
    }
    
    @Override
    public String getColumnName(int columnIndex){
        return colunas[columnIndex];
    }    
    
    public Object getItem(int indiceLinha){
        if (indiceLinha < 0){
            return null;
        }
        return vDados.get(indiceLinha);
    }
    
    public void addItem(Object obj){
        vDados.add(obj);
        int ultimoIndice = getRowCount() - 1;
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }
    
    public void removeItem(int indiceLinha){
        vDados.remove(indiceLinha);
        fireTableRowsDeleted(indiceLinha, indiceLinha);
    }
    
    public void addListOfItems(List<Object> vItens){
        this.clear();
        for (Object obj : vItens){
            this.addItem(obj);
        }
    }
    
    public void clear(){
        vDados.clear();
        fireTableDataChanged();
    }
    
    public boolean isEmpty(){
        return vDados.isEmpty();
    }
    
    public void setColumnWidth(JTable myTable, int[] vWidth){
        myTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for (int i=0; i < vWidth.length; i++){
            TableColumn col = myTable.getColumnModel().getColumn(i);
            col.setPreferredWidth(vWidth[i]);
        }
    }
    
    public void selectAndScrool(JTable table, int rowIndex){
        table.getSelectionModel().setSelectionInterval(rowIndex, rowIndex);
        scrollToVisible(table, rowIndex);
    } 
    
    public void scrollToVisible(JTable table, int rowIndex){
        scrollToVisible(table, rowIndex, 0);
    } 
    
    public void scrollToVisible(JTable table, int rowIndex, int vColIndex){
        if (!(table.getParent() instanceof JViewport)){
            return;
        }
        this.setViewPortPosition((JViewport) table.getParent(), table.getCellRect(rowIndex, vColIndex, true));
    }
    
    public void setViewPortPosition(JViewport viewport, Rectangle position){
        Point pt = viewport.getViewPosition();
        position.setLocation(position.x - pt.x, position.y = pt.y);
        viewport.scrollRectToVisible(position);
    }
    
}

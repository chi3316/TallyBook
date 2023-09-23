package PengTallyBook.Gui.Model;

import PengTallyBook.DAO.CategoryDAO;
import PengTallyBook.Entity.Record;
import PengTallyBook.Gui.Panel.MonthPickerPanel;
import PengTallyBook.Service.RecordService;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * HistoryListPanel 的 Table 的数据模型，实现了 TableModel 接口
 */
public class RecordTableModel implements TableModel {
    private String[] columnNames = new String[] {"ID", "花费", "分类", "备注", "日期"};
    public List<Record> records = new RecordService().listMonth(MonthPickerPanel.instance.date);

    @Override
    public int getRowCount() {
        return records.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(columnIndex == 0)
                return records.get(rowIndex).getSerial_number();
        if(columnIndex == 1)
                return records.get(rowIndex).getAmount();
        if(columnIndex == 2) {
            int sign_number = records.get(rowIndex).getSign_number();
            try {
                return new CategoryDAO().get(sign_number).getName();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if(columnIndex == 3)
                return records.get(rowIndex).getDesc();
        if(columnIndex == 4)
            return records.get(rowIndex).getRecord_date();
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

    }

    @Override
    public void addTableModelListener(TableModelListener l) {

    }

    @Override
    public void removeTableModelListener(TableModelListener l) {

    }
}

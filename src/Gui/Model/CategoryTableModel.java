package PengTallyBook.Gui.Model;

import PengTallyBook.Entity.Category;
import PengTallyBook.Service.CategoryService;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * CategoryPanel 的 Table 的数据模型，实现了 TableModel 接口
 * 将不同的数据源（例如数据库结果集、集合、数组等）与表格组件关联起来，以便在表格中显示和操作数据。
 */
public class CategoryTableModel implements TableModel {
    //列名
    private String[] columnNames = new String[] {"分类名称","消费次数"};
    //行数据
    public List<Category> categories = new CategoryService().list();

    public CategoryTableModel() throws SQLException, IOException {
    }

    public List<Category> getCategories() {
        return categories;
    }
    @Override
    public int getRowCount() {
        return categories.size();
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
        //第一列的值：种类名
        if(columnIndex == 0) return categories.get(rowIndex).getName();
        //第二列的值：消费次数
        if(columnIndex == 1) return categories.get(rowIndex).getRecordNumber();
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

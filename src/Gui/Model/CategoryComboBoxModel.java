package PengTallyBook.Gui.Model;

import PengTallyBook.Entity.Category;
import PengTallyBook.Service.CategoryService;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
/**
 * CategoryPanel 的 ComboBox 的数据模型，实现了ComboBoxModel<>接口，将数据和ComboBox（组合框）的可视部分（通常是下拉列表）分离开
 */
public class CategoryComboBoxModel implements ComboBoxModel<Category> {
    //从service层获取数据
    public List<Category> categories = new CategoryService().list();
    //第一个
    public Category c;

    public CategoryComboBoxModel() throws SQLException, IOException {
        if(!categories.isEmpty()) {
            c = categories.get(0);
        }
    }
    @Override
    public void setSelectedItem(Object anItem) {
        c = (Category) anItem;
    }

    @Override
    public Object getSelectedItem() {
        return c;
    }

    @Override
    public int getSize() {
        return categories.size();
    }

    @Override
    public Category getElementAt(int index) {
        return categories.get(index);
    }

    @Override
    public void addListDataListener(ListDataListener l) {

    }

    @Override
    public void removeListDataListener(ListDataListener l) {

    }
}

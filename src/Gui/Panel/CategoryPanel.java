package PengTallyBook.Gui.Panel;

import PengTallyBook.Entity.Category;
import PengTallyBook.Gui.Listener.CategoryListener;
import PengTallyBook.Gui.Model.CategoryTableModel;
import PengTallyBook.Service.CategoryService;
import PengTallyBook.Util.ColorUtil;
import PengTallyBook.Util.GuiUtil;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;

/**
 * 显示分类的面板 CategoryPanel
 */
public class CategoryPanel extends WorkingPanel{
    static{
        GuiUtil.UseLNF();
    }

    public static CategoryPanel instance;

    static {
        try {
            instance = new CategoryPanel();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public JButton bAdd = new JButton("新增");
    public JButton bEdit = new JButton("编辑");
    public JButton bDelete = new JButton("删除");

    //将表格 table 关联到数据模型 ctm 上
    private CategoryTableModel ctm = new CategoryTableModel();
    private JTable table = new JTable(ctm);

    public static void main(String[] args) {
        GuiUtil.showPanel(CategoryPanel.instance,0.85);
    }

    public CategoryPanel() throws SQLException, IOException {
        GuiUtil.setColor(ColorUtil.blueColor,bAdd,bEdit,bDelete);
        JScrollPane jsp = new JScrollPane(table);
        JPanel jPanel = new JPanel();
        jPanel.add(bAdd);
        jPanel.add(bEdit);
        jPanel.add(bDelete);

        //边界布局（BorderLayout）是一种常用的布局管理器，
        //它将容器划分为五个区域：北、南、东、西和中央。每个区域只能容纳一个组件
        this.setLayout(new BorderLayout());
        this.add(jsp,BorderLayout.CENTER);
        this.add(jPanel,BorderLayout.SOUTH);
        this.addListener();
    }

    public boolean checkSelected() {
        return table.getSelectedRow() >= 0;
    }

    public Category getSelectCategory() {
        int index = table.getSelectedRow();
        return ctm.getCategories().get(index > 0 ? index : 0);
    }
    @Override
    public void updateData()  {
        try {
            ctm.categories = new CategoryService().list();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        table.updateUI();
        if(ctm.categories.size() == 0) {
            bAdd.setEnabled(false);
            bEdit.setEnabled(false);
        }else {
            bAdd.setEnabled(true);
            bEdit.setEnabled(true);
        }
    }

    @Override
    public void addListener() {
        CategoryListener categoryListener = new CategoryListener();
        bAdd.addActionListener(categoryListener);
        bEdit.addActionListener(categoryListener);
        bDelete.addActionListener(categoryListener);
    }
}

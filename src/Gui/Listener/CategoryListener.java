package PengTallyBook.Gui.Listener;

import PengTallyBook.Entity.Category;
import PengTallyBook.Gui.Panel.CategoryPanel;
import PengTallyBook.Service.CategoryService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

/**
 * CategoryPanel的监听器，实现事件监听的行为
 */
public class CategoryListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        CategoryService categoryService = new CategoryService();
        CategoryPanel p = CategoryPanel.instance;
        //获得用户点击的按钮
        JButton b = (JButton) e.getSource();
        if(p.bAdd == b) {
            //显示文本输入框
            String name = JOptionPane.showInputDialog(null);
            if(name == null) {
                return;
            }
            if(name.length() == 0) {
                JOptionPane.showMessageDialog(p,"名字不能为空");
                return;
            }
            try {
                categoryService.add(name);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

        if(p.bDelete == b) {
            if(!p.checkSelected()) {
                JOptionPane.showMessageDialog(p,"请先选中一行");
                return;
            }
            Category c = p.getSelectCategory();
            if(c.getRecordNumber() != 0) {
                JOptionPane.showMessageDialog(p,"该分类下还有数据");
                return;
            }
            //JOptionPane.showConfirmDialog(p,"确认删除？") 用户点击后返回一个整数值，点击确认返回 0
            if(JOptionPane.OK_OPTION != JOptionPane.showConfirmDialog(p,"确认删除？")) {
                return;
            }
            int sign_number = c.getSign_number();
            try {
                categoryService.delete(sign_number);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        if(p.bEdit == b) {
            if(!p.checkSelected()) {
                JOptionPane.showMessageDialog(p,"请先选中一行");
                return;
            }
            Category c = p.getSelectCategory();
            int sign_number = c.getSign_number();
            String name = JOptionPane.showInputDialog("修改名称",c.getName());
            if(name == null) {
                return;
            }
            if(name.length() == 0) {
                JOptionPane.showMessageDialog(p,"名字不能为空");
                return;
            }
            categoryService.update(sign_number, name);
        }
        p.updateData();
    }
}

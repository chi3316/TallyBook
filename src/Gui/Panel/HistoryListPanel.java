package PengTallyBook.Gui.Panel;

import PengTallyBook.Entity.Record;
import PengTallyBook.Gui.Listener.HistoryListListener;
import PengTallyBook.Gui.Model.RecordTableModel;
import PengTallyBook.Service.RecordService;
import PengTallyBook.Util.ColorUtil;
import PengTallyBook.Util.GuiUtil;

import javax.swing.*;
import java.awt.*;

public class HistoryListPanel extends WorkingPanel{
    static {
        GuiUtil.UseLNF();
    }
    public static HistoryListPanel instance = new HistoryListPanel();

    public JButton bAdd = new JButton("新增");
    public JButton bEdit = new JButton("编辑");
    public JButton bDelete = new JButton("删除");
    private RecordTableModel rtm =  new RecordTableModel();
    private JTable table = new JTable(rtm);

    HistoryListPanel() {
        GuiUtil.setColor(ColorUtil.blueColor,bAdd,bDelete,bEdit);
        JScrollPane jScrollPane = new JScrollPane(table);

        JPanel jSubmit = new JPanel();
        jSubmit.add(bAdd);
        jSubmit.add(bEdit);
        jSubmit.add(bDelete);

        this.setLayout(new BorderLayout());
        this.add(jSubmit,BorderLayout.SOUTH);
        this.add(jScrollPane);

        addListener();
    }

    public static void main(String[] args) {
        GuiUtil.showPanel(instance,0.85);
    }
    public boolean checkSelected() {
        return table.getSelectedRow() >= 0;
    }
    public Record getSelectedREcord() {
        int index = table.getSelectedRow();
        return rtm.records.get(index > 0 ? index : 0);
    }

    @Override
    public void updateData() {
        rtm.records = new RecordService().listMonth(MonthPickerPanel.instance.date);
        table.updateUI();
    }

    @Override
    public void addListener() {
        HistoryListListener l = new HistoryListListener();
        bDelete.addActionListener(l);
        bEdit.addActionListener(l);
        bAdd.addActionListener(l);
    }
}

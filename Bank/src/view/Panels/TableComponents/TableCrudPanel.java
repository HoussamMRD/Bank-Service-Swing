package view.Panels.TableComponents;

import javax.swing.*;
import java.awt.*;

public class TableCrudPanel extends JPanel {
    private ClassLoader cl = getClass().getClassLoader();
    private JComboBox<String> filter;
    private JButton btn_add, btn_edit, btn_remove;

    public JButton deleteBtn() {
        return btn_remove;
    }

    public JButton getBtn_add() {
        return btn_add;
    }

    public JComboBox<String> getFilter() {
        return filter;
    }

    public JButton getBtn_edit() {
        return btn_edit;
    }

    private void initButtons(){
        btn_add = new JButton(new ImageIcon(cl.getResource("images/icons/add.png")));
        btn_add.setBorderPainted(false);
        btn_edit = new JButton(new ImageIcon(cl.getResource("images/icons/edit.png")));
        btn_edit.setBorderPainted(false);
        btn_remove = new JButton(new ImageIcon(cl.getResource("images/icons/delete.png")));
        btn_remove.setBorderPainted(false);
        filter = new JComboBox<>();
    }
    public TableCrudPanel(){
        initButtons();
        setLayout(new FlowLayout());
        setBackground(Color.white);
        add(btn_add);
        add(btn_edit);
        add(btn_remove);
        add(new JLabel("Trier par : "));
        add(filter);
    }
}

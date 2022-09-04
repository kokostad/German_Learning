package edu.german.tools;

import java.awt.Font;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import javax.swing.JPanel;

public class TwoEditFields extends JPanel {
    private static final long serialVersionUID = 1L;
    private JTextField one;
    private JTextField two;
    private int labelLong = 130;
    private int editFieldStart = labelLong + 5;
    private int editFieldLong = 160;
    private int editFieldHight = 25;
    private int FONT_SIZE = 15;
    private String s1;
    private String s2;

    public TwoEditFields(String s1, String s2) {
            this.s1 = s1;
            this.s2 = s2;

            Font font = new MyFont().myFont(FONT_SIZE);

            JLabel l1 = new JLabel(s1 + ": ", SwingConstants.RIGHT);
            l1.setFont(font);
            l1.setBounds(0, 0, labelLong, editFieldHight);
            one = new JTextField();
            one.setFont(font);
            one.setBounds(editFieldStart, 0, editFieldLong, editFieldHight);

            JPanel p1 = new JPanel();
            p1.setLayout(null);
            p1.add(l1);
            p1.add(one);

            JLabel l2 = new JLabel(s2 + ": ", SwingConstants.RIGHT);
            l2.setFont(font);
            l2.setBounds(0, 0, labelLong, editFieldHight);
            two = new JTextField();
            two.setFont(font);
            two.setBounds(editFieldStart, 0, editFieldLong, editFieldHight);

            JPanel p2 = new JPanel();
            p2.setLayout(null);
            p2.add(l2);
            p2.add(two);

            GridLayout gl = new GridLayout(1, 2, 5, 5);
            setLayout(gl);
            add(p1);
            add(p2);
    }

    public void clear() {
            one.setText(null);
            two.setText(null);
    }

    public String getOne() {
            return one.getText();
    }

    public String getTwo() {
            return two.getText();
    }

    public String getFirst() {
            return getOne().toString();
    }

    public String getSecond() {
            return getTwo().toString();
    }

    public List<Map<String, String>> getList() {
            Map<String, String> map = new HashMap<String, String>();
            map.put(s1, getOne());
            map.put(s2, getTwo());

            List<Map<String, String>> list = new LinkedList<Map<String, String>>();
            list.add(map);

            return list;
    }

}

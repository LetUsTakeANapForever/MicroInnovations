package TimeTable;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;

public class TimeTableGUI extends JFrame implements ActionListener{
    private Box verticalBox;
    private JPanel p1,p2,p3;
    private DefaultTableModel tModel;
    private JTable table;
    private JScrollPane scrollPane;
    private JTextField codeField, subjectField, timeField;
    private JLabel codeLabel, subjectLabel, timeLabel;
    private JButton addBT, deleteBT, saveBT;
    public TimeTableGUI(){
        Initial();
        setComponent();
        Fianlly();
    }
    public void Initial(){
        getContentPane().setLayout(new BorderLayout());
        p1 = new JPanel();
        p2 = new JPanel();
        p3 = new JPanel();
        p1.setLayout(new GridLayout(3,2,5,5)); // 3x2 GridLayout with gaps
        p2.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); //Add gap
        p3.setLayout(new BorderLayout());

        verticalBox = Box.createVerticalBox();
        verticalBox.add(p1);
        verticalBox.add(Box.createVerticalStrut(10)); // Add some space between p1 and p2
        verticalBox.add(p2);

        getContentPane().add(verticalBox, BorderLayout.NORTH);
        getContentPane().add(p3, BorderLayout.CENTER);
        
    }
    public void setComponent(){
        codeLabel = new JLabel("Code Subject:");
        subjectLabel = new JLabel("Subject:");
        timeLabel = new JLabel("Date/Time:");

        codeField = new JTextField();
        subjectField = new JTextField();
        timeField = new JTextField();

        p1.add(codeLabel);p1.add(codeField);p1.add(subjectLabel);p1.add(subjectField);p1.add(timeLabel);p1.add(timeField);

        addBT = new JButton("ADD DATA");
        deleteBT = new JButton("DELETE");
        saveBT = new JButton("SAVE");

        String[] column = {"Subject", "Code Subject", "Date/Time"};

        tModel = new DefaultTableModel(column, 0);

        table = new JTable(tModel);

        scrollPane = new JScrollPane(table);

        addBT.addActionListener(this);deleteBT.addActionListener(this);saveBT.addActionListener(this);
        p2.add(addBT);p2.add(deleteBT);p2.add(saveBT);p3.add(scrollPane);

    }
    public void Fianlly(){
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == addBT){
            if(!codeField.getText().isEmpty() && !subjectField.getText().isEmpty() && !timeField.getText().isEmpty()){
                String[] data = { codeField.getText(), subjectField. getText(), timeField.getText()};
                tModel.addRow(data);
            }else{
                JOptionPane.showMessageDialog(this, "Please input data");
            }
        }if(e.getSource() == deleteBT){
            try{
                tModel.removeRow(table.getSelectedRow());
            }catch(ArrayIndexOutOfBoundsException ex){
                JOptionPane.showMessageDialog(this, "Pick a row to delete");
            }
            
        }if(e.getSource() == saveBT){
            // Write a  new csv file
        }
    }
}

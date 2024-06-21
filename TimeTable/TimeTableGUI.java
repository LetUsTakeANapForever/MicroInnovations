package TimeTable;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class TimeTableGUI extends JFrame implements ActionListener{
    private Box verticalBox;
    private JPanel p1,p2,p3;
    private DefaultTableModel tModel;
    private JTable table;
    private JScrollPane scrollPane;
    private JTextField codeField, subjectField, timeField, nameField;
    private JLabel codeLabel, subjectLabel, timeLabel, nameLabel;
    private JButton addBT, deleteBT, saveBT, okayBT;
    private File f;
    private FileWriter fw;
    private BufferedWriter bw;
    private ArrayList <String> dataArrayList = new ArrayList<>();
    private JFrame d;
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

        String[] column = {"Code Subject", "Subject", "Date/Time"};

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
                dataArrayList.add(codeField.getText());
                dataArrayList.add(subjectField.getText());
                dataArrayList.add(timeField.getText());
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
            namePopUp();
        }
        if (e.getSource() == okayBT){
                d.setVisible(false);
                writeCSV(nameField.getText());
            }
    }

    public void writeCSV(String fileName){
        String dataRow1 = "Code Subject,Subject,Date/Time";
        String dataRow = (dataArrayList.toString());
        dataRow = dataRow.replace("[", "");
        dataRow = dataRow.replace("]", "");
        String str = "";

        try{
            f = new File(fileName+".csv");
            fw = new FileWriter(f);
            bw = new BufferedWriter(fw);
            if(dataRow1 != null){
                String[] st = dataRow1.split(",");
                for(int i = 0; i < 3 ; i++){
                    str = st[i];
                    if (!(i == 2))
                        str = String.valueOf(st[i]) + ", ";
                    bw.write(str);
                }
            }
            bw.write("\n ");
            if(dataRow != null){
                String[] dataSplit  = dataRow.split(",");
                    for(int i = 0; i < dataSplit.length; i++){
                        str = dataSplit[i];
                        if (!(i + 1 % 3 == 0))
                            str = String.valueOf(dataSplit[i]) + ", ";
                        bw.write(str);
                        if(i % 3 == 2)
                            bw.write("\n");
                    }
            }
        }catch(Exception e){
            System.out.println(e);
        }finally{
            try {
                bw.close();fw.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    public void namePopUp(){
        d = new JFrame();

        nameLabel = new JLabel("File name: ");
        nameField = new JTextField(10);
        okayBT = new JButton("OK");

        okayBT.addActionListener(this);

        d.getContentPane().setLayout(new FlowLayout());
        d.getContentPane().add(nameLabel);
        d.getContentPane().add(nameField);
        d.getContentPane().add(okayBT);

        d.pack();
        d.setLocationRelativeTo(null);
        d.setVisible(true);
    }
}

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class WindowGUI extends JFrame implements ActionListener, KeyListener {
    private JButton encode_bt, decode_bt;
    private JPanel p1, p2;
    private JLabel shift_l, text_l, result_l;
    private JTextField shift_tf, text_tf;
    private Container cp;
    private MainProgram mp;
    private boolean encode_func;

    public WindowGUI() {
        super("CeasaeShifter");
        Initial();
        setComponent();
        Finally();
    }

    private void Initial() {
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        p1 = new JPanel();
        p1.setLayout(new FlowLayout());
        p2 = new JPanel();
        p2.setLayout(new GridLayout(3, 2));
    }

    private void setComponent() {
        encode_bt = new JButton("Encode");
        encode_bt.setBackground(Color.DARK_GRAY);
        decode_bt = new JButton("Decode");
        decode_bt.setBackground(Color.GRAY);

        shift_l = new JLabel("Shift: ");
        text_l = new JLabel("Text: ");
        result_l = new JLabel();

        shift_tf = new JTextField(20);
        text_tf = new JTextField(20);

        encode_bt.addActionListener(this);
        decode_bt.addActionListener(this);
        text_tf.addKeyListener(this);

        p1.add(encode_bt);
        p1.add(decode_bt);
        p2.add(shift_l);
        p2.add(shift_tf);
        p2.add(text_l);
        p2.add(text_tf);
        p2.add(result_l);
        cp.add(p1, BorderLayout.NORTH);
        cp.add(p2, BorderLayout.CENTER);
    }

    private void Finally() {
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == encode_bt) {
            encode_func = true;
            encode_bt.setBackground(Color.DARK_GRAY);
            decode_bt.setBackground(Color.GRAY);
        }
        if (e.getSource() == decode_bt) {
            encode_func = false;
            decode_bt.setBackground(Color.DARK_GRAY);
            encode_bt.setBackground(Color.GRAY);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        try {
            if (shift_tf.getText().isEmpty()) {
                result_l.setText("Please input shift value");
                return;
            }
            if (!isNumeric(shift_tf.getText())) {
                result_l.setText("Shift value must be a number");
                return;
            }
            if (isNumeric(text_tf.getText())) {
                result_l.setText("Input only text in the text entry");
                return;
            }
            if (e.getSource() == text_tf && encode_func) {
                mp = new MainProgram();
                result_l.setText(mp.encodeString(Integer.parseInt(shift_tf.getText()), text_tf.getText()));
            }
            if (e.getSource() == text_tf && !encode_func) {
                mp = new MainProgram();
                result_l.setText(mp.decodeString(Integer.parseInt(shift_tf.getText()), text_tf.getText()));
            }
        } catch (Exception exep) {
            result_l.setText("Error");
            System.out.println(exep);
        }

    }

    private boolean isNumeric(String str) {
        for (char c : str.toCharArray()) {
            if (Character.isAlphabetic(c))
                return false;
        }
        return true;
    }
}

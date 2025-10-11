import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame {
    private final JTextArea text = new JTextArea("...");
    private int num1 = 0, result = 0;
    private String l_operator;

    public History history = new History();


    private final JButton[] numbers = new JButton[10];
    private final JButton add = new JButton("+");
    private final JButton minus = new JButton("-");
    private final JButton multiply = new JButton("*");
    private final JButton division = new JButton("/");
    private final JButton equals = new JButton("=");

    public Calculator() {

        startup();

    }

    private void startup(){

        setTitle("Cool Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500, 150, 500, 500);
        setIconImage(Toolkit.getDefaultToolkit().getImage(Calculator.class.getResource("/images/icon.png")));

        JPanel panel = new JPanel();

        text.setEditable(false);
        text.setLineWrap(true);
        text.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        text.setFocusable(Boolean.FALSE);
        text.setSize(450, 5);
        panel.add(text);

        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayout(4, 4, 10, 10));

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = createButton(i);
        }
        for (int i = 1; i < numbers.length; i++) {
            panel1.add(numbers[i]);
        }
        panel1.add(new JLabel());
        panel1.add(numbers[0]);

        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(4, 4, 10, 10));

        add.addActionListener(new operators());
        add.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        minus.addActionListener(new operators());
        minus.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        multiply.addActionListener(new operators());
        multiply.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        division.addActionListener(new operators());
        division.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        equals.addActionListener(new operators());
        equals.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        panel2.add(add);
        panel2.add(minus);
        panel2.add(multiply);
        panel2.add(division);
        panel2.add(equals);

        add(panel, BorderLayout.NORTH);
        add(panel1);
        add(panel2, BorderLayout.EAST);


        setVisible(true);
    }

    private JButton createButton(int i){
        String y = String.valueOf(i);
        JButton button = new JButton(y);
        button.addActionListener(new numbers());
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return button;

    }

    private class numbers implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            num1 = Integer.parseInt(num1 + e.getActionCommand());
            text.setText(String.valueOf(num1));
        }

    }

    private class operators implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String operator = e.getActionCommand();
            if (l_operator == null){
                l_operator = operator;
                result = num1;
                num1 = 0;
                text.setText(String.valueOf(0));
                return;
            }
            if (l_operator.equals("+")){
                history.text.setText(history.text.getText() + "\n" + result + l_operator + num1 +  " = " + (result + num1));
                result = num1 + result;
                num1 = 0;
                text.setText(String.valueOf(result));
            }
            else if (l_operator.equals("-")){
                history.text.setText(history.text.getText() + "\n" + result + l_operator + num1 +  " = " + (result - num1));
                result = result - num1;
                num1 = 0;
                text.setText(String.valueOf(result));
            }
            else if (l_operator.equals("*")){
                history.text.setText(history.text.getText() + "\n" + result + l_operator + num1 +  " = " + result * num1);
                result = num1 * result;
                num1 = 0;
                text.setText(String.valueOf(result));
            }
            else if (l_operator.equals("/")){
                try {
                    history.text.setText(history.text.getText() + "\n" + result + l_operator + num1 + " = " + result / num1);
                    result = result / num1;
                    num1 = 0;
                    text.setText(String.valueOf(result));
                }
                catch (ArithmeticException ae) {
                    history.text.setText(history.text.getText() + "\n" + "cannot divide by zero");
                }
            }
            else if (e.getActionCommand().equals("=")){
                if (operator.equals("+")){
                    history.text.setText(history.text.getText() + "\n" + result + l_operator + num1 +  " = " + (result + num1));
                    result = num1 + result;
                    num1 = 0;
                    text.setText(String.valueOf(result));
                }
                else if (operator.equals("-")){
                    history.text.setText(history.text.getText() + "\n" + result + l_operator + num1 +  " = " + (result - num1));
                    result = result - num1;
                    num1 = 0;
                    text.setText(String.valueOf(result));
                }
                if (operator.equals("*")) {
                    history.text.setText(history.text.getText() + "\n" + result + l_operator + num1 + " = " + result * num1);
                    result = num1 * result;
                    num1 = 0;
                }
                text.setText(String.valueOf(result));
                }
                else if (operator.equals("/")){
                    history.text.setText(history.text.getText() + "\n" + result + l_operator + num1 +  " = " + result / num1);
                    result = result / num1;
                    num1 = 0;
                text.setText(String.valueOf(result));
                }
                l_operator = operator;
            }



        }


    }



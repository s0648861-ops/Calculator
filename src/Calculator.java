import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame {
    private final JTextArea text = new JTextArea("...");
    private int num = 0, result = 0;
    private String operator;

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

    private void startup() {

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

    private JButton createButton(int i) {
        String y = String.valueOf(i);
        JButton button = new JButton(y);
        button.addActionListener(new numbers());
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return button;

    }

    private class numbers implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            num = Integer.parseInt(num + e.getActionCommand());
            text.setText(String.valueOf(num));
        }

    }

    private class operators implements ActionListener {
        @Override
            public void actionPerformed(ActionEvent e) {
                if (operator == null) {
                    operator = e.getActionCommand();
                    result = num;
                    num = 0;
                    text.setText(String.valueOf(0));
                    return;
                }
                if (e.getActionCommand().equals("=")) {
                    switch (operator) {
                        case "+" -> {
                            history.text.setText(history.text.getText() + "\n" + result + operator + num + " = " + (result + num));
                            result = num + result;
                            num = 0;
                            text.setText(String.valueOf(result));
                            operator = "0";
                        }
                        case "-" -> {
                            history.text.setText(history.text.getText() + "\n" + result + operator + num + " = " + (result - num));
                            result = result - num;
                            num = 0;
                            text.setText(String.valueOf(result));
                            operator = "0";
                        }
                        case "*" -> {
                            history.text.setText(history.text.getText() + "\n" + result + operator + num + " = " + result * num);
                            result = num * result;
                            num = 0;
                            text.setText(String.valueOf(result));
                            operator = "0";
                        }
                        case "/" -> {
                            try {
                                history.text.setText(history.text.getText() + "\n" + result + operator + num + " = " + result / num);
                                result = result / num;
                                num = 0;
                                text.setText(String.valueOf(result));
                            } catch (ArithmeticException ae) {
                                history.text.setText(history.text.getText() + "\n" + "cannot divide by zero");
                            }
                            operator = "0";
                        }
                    }
                }
                switch (operator) {
                    case "+" -> {
                    history.text.setText(history.text.getText() + "\n" + result + operator + num + " = " + (result + num));
                    result = num + result;
                    num = 0;
                    text.setText(String.valueOf(result));
                } case "-" -> {
                    history.text.setText(history.text.getText() + "\n" + result + operator + num + " = " + (result - num));
                    result = result - num;
                    num = 0;
                    text.setText(String.valueOf(result));
                } case "*" -> {
                    history.text.setText(history.text.getText() + "\n" + result + operator + num + " = " + result * num);
                    result = num * result;
                    num = 0;
                    text.setText(String.valueOf(result));
                } case "/" -> {
                    try {
                        history.text.setText(history.text.getText() + "\n" + result + operator + num + " = " + result / num);
                        result = result / num;
                        num = 0;
                        text.setText(String.valueOf(result));
                    } catch (ArithmeticException ae) {
                        history.text.setText(history.text.getText() + "\n" + "cannot divide by zero");
                    }
                }
            }
            operator = e.getActionCommand();
        }
    }
}




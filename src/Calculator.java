import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame {
    
    private final JTextArea text = new JTextArea("...");
    private int num = 0, decimal = 0;
    private double result = 0, w_num = 0;
    private String operator;


    public History history = new History();


    private final JButton percentage = new JButton("%");
    private final JButton sin = new JButton("sin");
    private final JButton cos = new JButton("cos");
    private final JButton tan = new JButton("tan");
    private final JButton ctg = new JButton("ctg");
    private final JButton backspace = new JButton("<-");
    private final JButton[] numbers = new JButton[10];
    private final JButton add = new JButton("+");
    private final JButton minus = new JButton("-");
    private final JButton multiply = new JButton("*");
    private final JButton division = new JButton("/");
    private final JButton equals = new JButton("=");
    private final JButton dot = new JButton(".");

    public Calculator(){
        startup();
        new Images();
    }

    private final JPanel panel = new javax.swing.JPanel() {
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Paint p = new GradientPaint(0.0f, 0.0f, new Color(100, 100, 100, 0),
                    500, 150, new Color(100, 100, 100, 255), true);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setPaint(p);
            g2d.fillRect(0, 0, getWidth(), getHeight());
        }
    };


    private void startup() {

        BorderLayout layout = new BorderLayout();
        layout.setHgap(10);
        layout.setVgap(10);

        setTitle("Cool Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500, 150, 360, 500);
        setIconImage(Toolkit.getDefaultToolkit().getImage(Calculator.class.getResource("/images/icon.png")));
        setResizable(false);
        setBackground(new Color(0, 0, 0));

        setContentPane(panel);
        panel.setLayout(layout);

        text.setEditable(false);
        text.setLineWrap(true);
        text.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        text.setFocusable(Boolean.FALSE);
        text.setSize(100, 100);
        panel.add(text, BorderLayout.PAGE_START);

        JPanel panel1 = new JPanel();

        panel1.setOpaque(false);

        panel1.setLayout(new GridLayout(6, 4, 10, 10));
        panel1.setSize(500, 5);

        percentage.addActionListener(new operators());
        percentage.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        backspace.addActionListener(new backspace());
        backspace.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        sin.addActionListener(new trigonometric());
        sin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        cos.addActionListener(new trigonometric());
        cos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        tan.addActionListener(new trigonometric());
        tan.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        ctg.addActionListener(new operators());
        ctg.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

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

        dot.addActionListener(new dot());
        dot.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = createButton(i);
        }

        panel1.add(new JLabel());
        panel1.add(new JLabel());
        panel1.add(backspace);
        panel1.add(percentage);
        panel1.add(sin);
        panel1.add(cos);
        panel1.add(tan);
        panel1.add(ctg);

        for (int i = 1; i < 4; i++) {
            panel1.add(numbers[i]);
        }

        panel1.add(add);


        for (int i = 4; i < 7; i++) {
            panel1.add(numbers[i]);
        }

        panel1.add(minus);


        for (int i = 7; i < 10; i++) {
            panel1.add(numbers[i]);
        }

        panel1.add(multiply);

        panel1.add(dot);
        panel1.add(numbers[0]);

        panel1.add(division);
        panel1.add(equals);

        panel.add(panel1);

        setVisible(true);

    }

    private JButton createButton(int i) {
        String y = String.valueOf(i);
        JButton button = new JButton(y);
        button.addActionListener(new numbers());
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return button;

    }

    private class dot implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (decimal == 1){
                System.out.println("Im not so stupid ok?");
                return;
            }
            w_num = num;
            decimal = 1;
            text.setText(num + e.getActionCommand() + 0);
            num = 0;
        }
    }

    private class numbers implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (decimal > 0) {
                num = Integer.parseInt(((num + e.getActionCommand())));
                text.setText((int) w_num + "." + num);
            }
            else {
                num = Integer.parseInt(((num + e.getActionCommand())));
                text.setText(String.valueOf(num));
            }
        }
    }

    private class backspace implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            num = Integer.parseInt(String.valueOf(num / 10));
            text.setText(String.valueOf(num));
        }
    }

    private class trigonometric implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            if (decimal == 1){
                w_num = w_num + ((double)num / 10);
            }
            else{
                w_num = num;
            }

            if (result == 0 && num != 0){
                result = num;
            }

            switch (e.getActionCommand()) {
                case "sin" -> {
                    history.text.setText(history.text.getText() + "\n" + e.getActionCommand() + "(" + result + ")" + " = " + (Math.sin(result)));
                    result = Math.sin(result);
                }
                case "cos" -> {
                    history.text.setText(history.text.getText() + "\n" + e.getActionCommand() + "(" + result + ")" +  " = " + (Math.cos(result)));
                    result = Math.cos(result);
                }
                case "tan" -> {
                    history.text.setText(history.text.getText() + "\n" + e.getActionCommand() + "(" + result + ")" +  " = " + (Math.tan(result)));
                    result = Math.tan(result);
                }
                case "ctg" -> {
                    history.text.setText(history.text.getText() + "\n" + e.getActionCommand() + "(" + result + ")" +  " = " + (Math.cos(result)/Math.sin(result)));
                    result = Math.cos(result)/Math.sin(result);
                }
            }
            text.setText("0");
            num = 0;
            w_num = 0;
            decimal = 0;
        }
    }

    private class operators implements ActionListener {
        @Override
            public void actionPerformed(ActionEvent e) {

                if (decimal == 1){
                    w_num = w_num + ((double)num / 10);
                }
                else{
                    w_num = num;
                }

                if (operator == null) {
                    operator = e.getActionCommand();
                    if (result == 0){
                        result = w_num;
                    }
                    else if (w_num != 0){
                        math();
                    }
                    w_num = 0;
                    num = 0;
                    text.setText(String.valueOf(0));
                    decimal = 0;
                    return;
                }
                if (e.getActionCommand().equals("=")) {
                    math();
                    operator = null;
                    return;
                }
            math();
            decimal = 0;
            operator = e.getActionCommand();
        }

        private void math() {
            switch (operator) {
                case "+" -> {
                    history.text.setText(history.text.getText() + "\n" + result + operator + w_num + " = " + (result + w_num));
                    result = w_num + result;
                    w_num = 0;
                    num = 0;
                }
                case "-" -> {
                    history.text.setText(history.text.getText() + "\n" + result + operator + w_num + " = " + (result - w_num));
                    result = result - w_num;
                    w_num = 0;
                    num = 0;
                }
                case "*" -> {
                    history.text.setText(history.text.getText() + "\n" + result + operator + w_num + " = " + result * w_num);
                    result = w_num * result;
                    w_num = 0;
                    num = 0;
                }
                case "%" -> {
                    history.text.setText(history.text.getText() + "\n" + result + operator + w_num + " = " + result % w_num);
                    result = result % w_num;
                    w_num = 0;
                    num = 0;
                }
                case "/" -> {
                    if (w_num == 0) {
                        history.text.setText(history.text.getText() + "\n" + "cannot divide by zero");
                        return;
                    }
                    history.text.setText(history.text.getText() + "\n" + result + operator + w_num + " = " + result / w_num);
                    result = result / w_num;
                    w_num = 0;
                    num = 0;
                }
            }
            text.setText("0");
        }

    }
}




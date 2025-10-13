import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class History extends JFrame {
    public JTextArea text = new JTextArea();
    private final JButton clear = new JButton("Clear history");
    int A = 1;

    public History() {
        startup();
    }

    public void startup(){
        setTitle("History");
        setBounds(865, 150, 222, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setIconImage(Toolkit.getDefaultToolkit().getImage(History.class.getResource("/images/icon_h.png")));
        setResizable(false);

        text.setSize(200,10000);
        text.setEditable(false);
        text.setText("This is the history");
        text.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));

        clear.addActionListener(new Clear());
        clear.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        System.out.println(text.getText());

        add(text, BorderLayout.CENTER);
        add(clear, BorderLayout.SOUTH);
        setVisible(true);
    }

    private class Clear implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            text.setText("History was cleared " + A + " times");
            A++;
        }
    }

}



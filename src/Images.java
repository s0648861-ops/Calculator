import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Objects;

public class Images extends JFrame implements KeyListener {
    private final ImageIcon[] imageIcons = new ImageIcon[4];
    private final JLabel labels = new JLabel();
    private final JButton back = new JButton("Back");
    private final JButton loop = new JButton("Loop");
    private final JButton forward = new JButton("Forward");

    private int i = 0, l = 1;

    public Images(){
        startup();
    }

    private void startup(){
        setTitle("Image viewer");
        setLocation(150, 150);
        setSize(300, 500);
        setFocusable(true);
        setFocusTraversalKeysEnabled(true);
        addKeyListener(this);
        setResizable(false);

        for (int i = 0; i < imageIcons.length; i++) {
            imageIcons[i] = new ImageIcon(Objects.requireNonNull(getClass().getResource("cats/" + i + ".jpg")));
        }

        labels.setIcon(imageIcons[0]);
        setIconImage(imageIcons[i].getImage());
        labels.setSize(300, 300);

        loop.addActionListener(_ -> Loop());
        loop.addKeyListener(this);
        forward.addActionListener(new Direction());
        forward.addKeyListener(this);
        back.addActionListener(new Direction());
        back.addKeyListener(this);

        JPanel panel = new JPanel();
        GridLayout layout = new GridLayout(2, 4);
        layout.setHgap(10);
        layout.setVgap(10);
        panel.setLayout(layout);

        panel.add(back);
        panel.add(loop);
        panel.add(forward);
        panel.add(new JLabel("A to go back"));
        panel.add(new JLabel("R to start loop"));
        panel.add(new JLabel("<html>D to go <br/> forward</html>"));


        add(labels,  BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);

		setVisible(true);

    }

    private void Loop() {
            if (l == 1) {
            return;
            }
            l = 1;
            Thread thread = new Thread(() -> {
                try {
                    while (l == 1) {
                        while (i < imageIcons.length) {
                            Thread.sleep(1000);
                            labels.setIcon(imageIcons[i]);
                            setIconImage(imageIcons[i].getImage());
                            i++;
                        }
                        i = 0;
                    }
                } catch (InterruptedException ex) {
                    System.out.println("Loop interrupted");
                }
            });
            thread.start();
        }

    private class Direction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            l = 0;
            if(e.getSource() == forward) {

                if (i >= imageIcons.length - 1) {
                    i = 0;
                    labels.setIcon(imageIcons[i]);
                    setIconImage(imageIcons[i].getImage());
                    return;
                }
                i++;
                labels.setIcon(imageIcons[i]);
                setIconImage(imageIcons[i].getImage());
            }
            else if(e.getSource() == back) {
                
                if (i <= 0) {
                    i = imageIcons.length - 1;
                    labels.setIcon(imageIcons[i]);
                    setIconImage(imageIcons[i].getImage());
                    return;
                }
                i--;
                labels.setIcon(imageIcons[i]);
                setIconImage(imageIcons[i].getImage());
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

        if(String.valueOf(e.getKeyChar()).equalsIgnoreCase("d")) {
            
            l = 0;
            if (i >= imageIcons.length - 1) {
                i = 0;
                labels.setIcon(imageIcons[i]);
                setIconImage(imageIcons[i].getImage());
                return;
            }
            i++;
            labels.setIcon(imageIcons[i]);
            setIconImage(imageIcons[i].getImage());
        }
        if (String.valueOf(e.getKeyChar()).equalsIgnoreCase("a")) {
            
            l = 0;
            if (i <= 0) {
                i = imageIcons.length - 1;
                labels.setIcon(imageIcons[i]);
                setIconImage(imageIcons[i].getImage());
                return;
            }
            i--;
            labels.setIcon(imageIcons[i]);
            setIconImage(imageIcons[i].getImage());
        }
        if (String.valueOf(e.getKeyChar()).equalsIgnoreCase("r")) {
                Loop();
        }
    }
}

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

public class multiplier {
	public static void main(String[] args) {
	JFrame frame = new JFrame();
	frame.setSize(400, 100);
    frame.setForeground(Color.LIGHT_GRAY);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("Multiplier");
    JTextField t1 = new JTextField(5);
    JTextField t2 = new JTextField(5);
	JButton times = new JButton("*");
	JLabel label = new JLabel();
	times.addActionListener((ActionListener) new buttonClicker(t1, t2, frame, label));
	frame.setLayout(new FlowLayout());
	frame.add(t1);
	frame.add(times);
	frame.add(t2);
	frame.setVisible(true);
	}
}

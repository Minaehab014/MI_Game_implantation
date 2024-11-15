package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class PlayerNames extends  JPanel implements ActionListener {


private JLabel j1;
private JLabel j2;
private JTextField t1;
private JTextField t2;
private JButton b1;
private mainview m;
public PlayerNames( mainview m) {
this.m = m;
this.setLayout(null);
j1 = new JLabel("FirstPlayerName");
j1.setBounds(30,100,100,30);
this.add(j1);
t1 = new JTextField();
t1.setBounds(200,100,100,30);
this.add(t1);
j2 = new JLabel("SecondPlayerName");
j2.setBounds(400,100,150,30);
this.add(j2);
t2 = new JTextField();
t2.setBounds(600,100,100,30);
this.add(t2);
b1 = new JButton("next");
b1.addActionListener(this);
b1.setBounds(300,200,100,30);
this.add(b1);
}
public void actionPerformed(ActionEvent e) {
	if(e.getSource() == b1) {
		if(t1.getText().equals("") || t2.getText().equals("")) {
			JOptionPane.showMessageDialog(this," players must enter their names","ERROR",JOptionPane.ERROR_MESSAGE);
		}
		else {
		m.switchToNextPanel( t1.getText(),t2.getText());
	}
	}
}

}

















package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import engine.Game;
import model.world.Champion;

public class Choose extends JPanel implements ActionListener {
private mainview main;
private JComboBox  <String> j1;
private JComboBox  <String> j2;
private JComboBox  <String>j3;
private JComboBox  <String> j4;
private JComboBox  <String>j5;
private JComboBox  <String> j6;
private JCheckBox c1;
private JCheckBox c2;
private JCheckBox c3;
private ButtonGroup g1;
private JCheckBox c4;
private JCheckBox c5;
private JCheckBox c6;
private ButtonGroup g2;
private JLabel l1;
private JLabel l2;
private JButton b;

public Choose (mainview main) {
	this.main = main;
	this.setLayout(null);
	l1 = new JLabel("first player champions");
	l1.setBounds(50, 5, 200, 50);
	this.add(l1);
	
	
	
	j1 = new JComboBox<String>(this.ChooseChampion());
	j1.setBounds(50, 50, 1000, 50);
	this.add(j1);
	
	c1 = new JCheckBox("IS LEADER");
	c1.setBounds(1100, 30, 300, 100);
	this.add(c1);
	
	j2 = new JComboBox<String>(this.ChooseChampion());
	j2.setBounds(50, 150, 1000, 50);
	this.add(j2);
	
	c2 = new JCheckBox("IS LEADER");
	c2.setBounds(1100, 130, 100, 100);
	this.add(c2);
	
	j3 = new JComboBox<String>(this.ChooseChampion());
	j3.setBounds(50, 250, 1000, 50);
	this.add(j3);
	
	c3 = new JCheckBox(" IS LEADER");
	c3.setBounds(1100, 230, 100, 100);
	this.add(c3);
	
	 g1 = new ButtonGroup();
	 g1.add(c1);
	 g1.add(c3);
	 g1.add(c2);
	
	
	
	 l2 = new JLabel("second player champions");
		l2.setBounds(50, 300, 200, 100);
		this.add(l2);
		
		
		j4 = new JComboBox<String>(this.ChooseChampion());
		j4.setBounds(50, 450, 1000, 50);
		this.add(j4);
		
		c4 = new JCheckBox("IS LEADER");
		c4.setBounds(1100, 430, 300, 100);
		this.add(c4);
		
		j5 = new JComboBox<String>(this.ChooseChampion());
		j5.setBounds(50, 550, 1000, 50);
		this.add(j5);
		
		c5 = new JCheckBox(" IS LEADER");
		c5.setBounds(1100, 530, 100, 100);
		this.add(c5);
		
		j6 = new JComboBox<String>(this.ChooseChampion());
		j6.setBounds(50, 650, 1000, 50);
		this.add(j6);
		
		c6 = new JCheckBox("IS LEADER");
		c6.setBounds(1100, 620, 100, 100);
		this.add(c6);
		
		 g2 = new ButtonGroup();
		 g2.add(c4);
		 g2.add(c5);
		 g2.add(c6);
	
	    b = new JButton("Start");
	    b.setBounds(530, 750, 100, 30);
	    this.add(b);
	    b.addActionListener(this);
	   
	    c1.setSelected(true);
		c4.setSelected(true);
}


public String[] ChooseChampion() {
	String z;
	String f = "";
	String[] x = new String[Game.getAvailableChampions().size()]; 
	for(int i = 0 ; i < Game.getAvailableChampions().size() ; i++ ) {
		for(int y = 0; y < Game.getAvailableChampions().get(i).getAbilities().size();y++) {
			
				
		 
		 z = 	 Game.getAvailableChampions().get(i).getAbilities().get(0).getName() + " , " +" " + Game.getAvailableChampions().get(i).getAbilities().get(1).getName()+" , " +" "+ Game.getAvailableChampions().get(i).getAbilities().get(2).getName();
	     x[i] = Game.getAvailableChampions().get(i).getName() + ", " + "Hp"+ " "+Game.getAvailableChampions().get(i).getMaxHP()+ " ," + "Action points "+ " "+Game.getAvailableChampions().get(i).getMaxActionPointsPerTurn()+ ", " + "Mana"+ " "+Game.getAvailableChampions().get(i).getMana()+ " ," + "AttackRange"+ " "+Game.getAvailableChampions().get(i).getAttackRange()+ ", " + "Attack Damage"+ " "+Game.getAvailableChampions().get(i).getAttackDamage()+ ", " + "Speed"+ " " +Game.getAvailableChampions().get(i).getSpeed()+ ", " + "abilities"+ " "+z;	
	}
		z = "";
	}
	return x ; 

}
public String  effects(Champion c) {
	
	String x = "";
	for(int i = 0 ; i < c.getAppliedEffects().size();i++ ) {
		x =c.getAppliedEffects().get(i).getName() + " " ;
	}
	return x;
} 
public String [] abilities(Champion c) {
	String[] x = new String[Game.getAvailableAbilities().size()];
	for(int i = 0 ; i < Game.getAvailableAbilities().size();i++ ) {
		x [i]=Game.getAvailableAbilities().get(i).getName() + " " ;
	}
	return x;
}

public int leader1() {
	if(c1.isSelected() == true) {
		return j1.getSelectedIndex();
	}
	else if(c2.isSelected() == true) {
		return j2.getSelectedIndex();
	}
	else if(c3.isSelected() == true) {
		return j3.getSelectedIndex();}
	else {
		return 0;
	}
}

public int leader2() {
	if(c4.isSelected() == true) {
		return j4.getSelectedIndex();
	}
	else if(c5.isSelected() == true) {
		return j5.getSelectedIndex();
	}
	else if(c6.isSelected() == true) {
		return j6.getSelectedIndex();}
	else {
		return 0;
	}
}


public void actionPerformed(ActionEvent e) {
	if(e.getSource() == b) {
		if(j1.getSelectedIndex() == j2.getSelectedIndex() || j1.getSelectedIndex() == j3.getSelectedIndex() || j3.getSelectedIndex() == j2.getSelectedIndex()|| j4.getSelectedIndex() == j5.getSelectedIndex() || j4.getSelectedIndex() == j6.getSelectedIndex() || j5.getSelectedIndex() == j6.getSelectedIndex()|| j1.getSelectedIndex() == j4.getSelectedIndex() || j1.getSelectedIndex() == j5.getSelectedIndex() || j1.getSelectedIndex() == j6.getSelectedIndex() || j4.getSelectedIndex() == j2.getSelectedIndex() || j5.getSelectedIndex() == j2.getSelectedIndex() || j6.getSelectedIndex() == j2.getSelectedIndex() ||j4.getSelectedIndex() == j3.getSelectedIndex() || j5.getSelectedIndex() == j3.getSelectedIndex() || j6.getSelectedIndex() == j3.getSelectedIndex()) {
			JOptionPane.showMessageDialog(this,"Players Should be different","ERROR",JOptionPane.ERROR_MESSAGE);
		}
		else {
			main.switchToFinalPanel(j1.getSelectedIndex(),j2.getSelectedIndex(),j3.getSelectedIndex(),j4.getSelectedIndex(),j5.getSelectedIndex(),j6.getSelectedIndex(),this.leader1(),this.leader2());
		}
	

	}
}
}

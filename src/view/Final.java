package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import engine.Game;
import exceptions.AbilityUseException;
import exceptions.ChampionDisarmedException;
import exceptions.InvalidTargetException;
import exceptions.LeaderAbilityAlreadyUsedException;
import exceptions.LeaderNotCurrentException;
import exceptions.NotEnoughResourcesException;
import exceptions.UnallowedMovementException;
import model.abilities.AreaOfEffect;
import model.abilities.CrowdControlAbility;
import model.abilities.DamagingAbility;
import model.abilities.HealingAbility;
import model.world.AntiHero;
import model.world.Champion;
import model.world.Cover;
import model.world.Direction;
import model.world.Hero;
import model.world.Villain;

public class Final extends JPanel implements ActionListener,MouseListener {
     
	

	private mainview main;
	
	private JButton[][] b;
	
    private Direction d;
	 
    private JComboBox c ,c3, c1 ,c4,c5;
     
  
    
    private JButton j,g,m,m1,m2,m3,m4,m5;
    
    private JCheckBox b1 ,b2,b3;
     
    private JLabel j1 ,j2,j3 ,j4,j5,j6,j7,j8,j9,j10,j11,j12,j13,m6,m7,j15;
    
    private ButtonGroup	 g2 ;
	 
    private JTextField t1,t2;
    
	public Final(mainview main) {
		this.main = main;
		this.setLayout(null);
		
		String [] f = { "select move" , "up" , "down" , "left" , "right"};
		
		c  = new JComboBox(f);
		c.setBounds(750, 400, 100, 50);
		this.add(c);
		c.addActionListener(this);
		
		String [] a = { "attack" , "up" , "down" , "left" , "right"};
		c1  = new JComboBox(a);
		c1.setBounds(750, 550, 100, 50);
		this.add(c1);
		c1.addActionListener(this);
		
        String [] o = { "cast direction" , "up" , "down" , "left" , "right"};
		
		c4  = new JComboBox(o);
		c4.setBounds(1250, 400, 150, 50);
		this.add(c4);
		c4.addActionListener(this);
		
		j = new JButton("END TURN");
		j.setBounds(750, 700, 100, 50);
		this.add(j);
		j.addActionListener(this);
		
		j1 = new JLabel( "first player name : " + " " + main.getG().getFirstPlayer().getName());
		j1.setBounds(160, 50, 300, 20);
		this.add(j1);
		
		j2 = new JLabel( "second player name : " + " " + main.getG().getSecondPlayer().getName());
		j2.setBounds(160,90 , 300, 20);
		this.add(j2);
		

		j4 = new JLabel("Current champion info.");
		j4.setBounds(400, 7, 150, 50);
		this.add(j4);
		
		g=new JButton("cast");
		g.setBounds(1250, 600,100 ,30);
		this.add(g);
		g.addActionListener(this);
		
		m = new JButton("Ability 1");
		m.setBounds(900, 40, 100, 30);
		this.add(m);
		m.addActionListener(this);
		
		m1 = new JButton("Ability 2");
		m1.setBounds(900, 100, 100, 30);
		this.add(m1);
		m1.addActionListener(this);
		
		m2 = new JButton("Ability 3");
		m2.setBounds(900, 170, 100, 30);
		this.add(m2);
		m2.addActionListener(this);
		

		m3 = new JButton("use leader ability");
		m3.setBounds(1250,500 , 150, 30);
		this.add(m3);
		m3.addActionListener(this);
		
		m4 = new JButton("effects");
		m4.setBounds(1050,40 , 100, 30);
		this.add(m4);
		m4.addActionListener(this);
	
		j13 = new JLabel(" Press on Champions for information");
		this.add(j13);
		j13.setBounds(750, 250, 300, 30);
		
		m6 = new JLabel(" First Player  Leader Ability : " +  " Not Used");
		m6.setBounds(750,300 ,300 , 30);
		this.add(m6);
		
		
		m7 = new JLabel(" Second Player  Leader Ability : " +  " Not Used");
		m7.setBounds(750,350 , 300, 30);
		this.add(m7);
		
		j15 = new JLabel(" right click to cast single target ability");
		j15.setBounds(1200, 700, 300, 30);
		this.add(j15);
		
	
	int x = 120;
	int y = 120;	
		
	b = new JButton[5][5];
	
	for(int i = 0 ; i < b.length ; i++) {
		for(int j = 0; j < b.length;j++) {
			b[i][j] = new JButton();
			b[i][j].addActionListener(this);
			b[i][j].setBackground(Color.WHITE);
			b[i][j].setBounds(x, y, 120, 120);
			this.add(b[i][j]);
			b[i][j].addMouseListener(this);
			x = x + 120 ;
		}
		x = 120;
		y = y + 120;
	}

displayGame();
   
	}
	public void displayChampions() {
		for(int i = 0 ; i < main.getG().getBoard().length ; i++) {
			for(int j = 0; j < main.getG().getBoard().length;j++) {
				if( main.getG().getBoard()[i][j] != null) {
					if( main.getG().getBoard()[i][j] instanceof Champion) {
						Champion c = (Champion)  main.getG().getBoard()[i][j];
						Font f1 = new Font("ok",Font.BOLD,8);
						b[i][j].setText(c.getName()+" / " +c.getCurrentHP());
                        b[i][j].setFont(f1);  					
                        if(main.getG().getFirstPlayer().getTeam().contains(c)) {
                        	b[i][j].setBackground(Color.blue);
                        
                        }
                        else if (main.getG().getSecondPlayer().getTeam().contains(c)) {
                        	b[i][j].setBackground(Color.red);
                        }
					}
				}
				else {
					b[i][j].setBackground(Color.white);
					b[i][j].setText("");
				}
			}
		}
	}
	
		public void displaycovers() {
			for(int i = 0 ; i < main.getG().getBoard().length ; i++) {
				for(int j = 0; j < main.getG().getBoard().length;j++) {
					if( main.getG().getBoard()[i][j] != null) {
					 if(main.getG().getBoard()[i][j] instanceof Cover) {
						Cover x = (Cover) main.getG().getBoard()[i][j];
						b[i][j].setText("COVER" +" / " + x.getCurrentHP()+ "");
						Font f1 = new Font("ok",Font.BOLD,8);
						
						b[i][j].setFont(f1);
						b[i][j].setForeground(Color.white);
						b[i][j].setBackground(Color.black);
					}
				}	
					else {
						b[i][j].setBackground(Color.white);
					}
			 }
		  }
	   }
		public void  displayplayer() {
			
			for(int i = 0 ; i < main.getG().getBoard().length ; i++) {
				for(int j = 0; j < main.getG().getBoard().length;j++) {
					if( main.getG().getBoard()[i][j] != null) {
						if( main.getG().getBoard()[i][j] instanceof Champion) {
							Champion e = (Champion)  main.getG().getBoard()[i][j];    
			                 if (e.equals(main.getG().getCurrentChampion())) {
			                	 b[i][j].setBackground(Color.PINK);
			         			
			                    }
						     }   
					     }       	 
					 }        	 
				}       	 
			}
        
		public void updateCurrentChampioninfo() {
			j3 = new JLabel( "NAME" +  ": "+main.getG().getCurrentChampion().getName());
			j3.setBounds(400,30 , 150, 50);
			this.add(j3);
			
			j5 = new JLabel( "ACTION POINTS" +  ": "+main.getG().getCurrentChampion().getCurrentActionPoints());
			j5.setBounds(400,70 , 150, 50);
			this.add(j5);
			
			j6 = new JLabel( "MANA" +  ": "+main.getG().getCurrentChampion().getMana());
			j6.setBounds(550,30 , 150, 50);
			this.add(j6);
			
			j7 = new JLabel( "ATTACK DAMAGE" +  ": "+main.getG().getCurrentChampion().getAttackDamage());
			j7.setBounds(550,70 , 150, 50);
			this.add(j7);
			
			j8 = new JLabel( "ATTACK RANGE" +  ": "+main.getG().getCurrentChampion().getAttackRange());
			j8.setBounds(700,30 , 150, 50);
			this.add(j8);
			
			if(main.getG().getCurrentChampion() instanceof AntiHero) {
			j9 = new JLabel( "TYPE" +  ": " + "anti hero ");
			j9.setBounds(700,70 , 150, 50);
			this.add(j9);
			}
			else if(main.getG().getCurrentChampion() instanceof Hero) {
				j9 = new JLabel( "TYPE" +  ": " + " hero ");
				j9.setBounds(700,70 , 150, 50);
				this.add(j9);
			}
			else if(main.getG().getCurrentChampion() instanceof Villain) {
				j9 = new JLabel( "TYPE" +  ": " + " villain");
				j9.setBounds(700,70 , 150, 50);
				this.add(j9);
			}
			 
			
			
			 
			 String [] y = new  String[main.getG().getCurrentChampion().getAbilities().size()];
				for(int x = 0; x < main.getG().getCurrentChampion().getAbilities().size();x++ ) {
					 y [x] = main.getG().getCurrentChampion().getAbilities().get(x).getName();
				}
			  j10 = new JLabel(y[0]);
			 j10.setBounds(900, 400, 150, 50);
			 this.add(j10);
			 
			 j11 = new JLabel(y[1]);
			 j11.setBounds(900, 500, 150, 50);
			 this.add(j11);
			 
			 j12 = new JLabel(y[2]);
			 j12.setBounds(900, 600, 150, 50);
			 this.add(j12);
			 
			 b1 = new JCheckBox();
			 b1.setBounds(1100, 410, 30, 30);
			 this.add(b1);
			 
			 b2 = new JCheckBox();
			 b2.setBounds(1100, 510, 30, 30);
			 this.add(b2);
			 
			 b3 = new JCheckBox();
			 b3.setBounds(1100, 610, 30, 30);
			 this.add(b3);
			
			 g2 = new ButtonGroup();
			 g2.add(b1);
			 g2.add(b2);
			 g2.add(b3);
			 
			 b1.setSelected(true);
			 
			 
			 
			 
			 
			 
		}
		
		
		public void refreshformove() {
			
			displayChampions();
			displayplayer();
			
		}
		
		public void refreshforattack() {
			displayChampions();
			displaycovers();
			displayplayer();
		}
		
	public void displayGame() {
		displaycovers();
		displayChampions();
		 displayplayer();
		 updateCurrentChampioninfo();
		
	}
	 
	public void refreshforcast() {
		displayChampions();
		displaycovers();
		displayplayer();
		j5.setText( "ACTION POINTS" +  ": "+main.getG().getCurrentChampion().getCurrentActionPoints());
		j6.setText("");
		j6.setText("MANA" +  ": "+main.getG().getCurrentChampion().getMana());	
		j8.setText("ATTACK RANGE" +  ": "+main.getG().getCurrentChampion().getAttackRange());
		j7.setText( "ATTACK DAMAGE" +  ": "+main.getG().getCurrentChampion().getAttackDamage());
	}
	
    public void checkgame() {
    	if(main.getG().checkGameOver() != null) {
    		if(main.getG().getFirstPlayer().getTeam().size() == 0) {
    			JOptionPane.showMessageDialog(this," Second Player win ","ERROR",JOptionPane.ERROR_MESSAGE);
    		}
    		if(main.getG().getSecondPlayer().getTeam().size() == 0) {
    			JOptionPane.showMessageDialog(this," First Player win ","ERROR",JOptionPane.ERROR_MESSAGE);
    			
    		}
    	}
    }
    
  
    
    
    
	
	public void actionPerformed(ActionEvent e) {
		  
		int x = (int) main.getG().getCurrentChampion().getLocation().getX();
		int y = (int) main.getG().getCurrentChampion().getLocation().getY();
		// move*****
	if(c.getSelectedIndex() == 1) {
		
			try {
				main.getG().move(Direction.UP);
				 b[x][y].setText("");
					b[x][y].setBackground(Color.WHITE);
					
					j5.setText( "ACTION POINTS" +  ": "+main.getG().getCurrentChampion().getCurrentActionPoints());
					j6.setText("MANA" +  ": "+main.getG().getCurrentChampion().getMana());	
					refreshformove();
					c.setSelectedIndex(0);
			} catch (UnallowedMovementException e1) {
				JOptionPane.showMessageDialog(this," UnallowedMovementException ","ERROR",JOptionPane.ERROR_MESSAGE);
				c.setSelectedIndex(0);
			} catch (NotEnoughResourcesException e1) {
				JOptionPane.showMessageDialog(this," NotEnoughResourcesException ","ERROR",JOptionPane.ERROR_MESSAGE);
				c.setSelectedIndex(0);
				
			}
	}
			if(c.getSelectedIndex() == 2) {
				
				try {
					main.getG().move(Direction.DOWN);
					 b[x][y].setText("");
						b[x][y].setBackground(Color.WHITE);
						
						j5.setText( "ACTION POINTS" +  ": "+main.getG().getCurrentChampion().getCurrentActionPoints());
						j6.setText("MANA" +  ": "+main.getG().getCurrentChampion().getMana());	
						refreshformove();
						c.setSelectedIndex(0);
				} catch (UnallowedMovementException e1) {
					JOptionPane.showMessageDialog(this," UnallowedMovementException ","ERROR",JOptionPane.ERROR_MESSAGE);
					c.setSelectedIndex(0);
				} catch (NotEnoughResourcesException e1) {
					JOptionPane.showMessageDialog(this," NotEnoughResourcesException ","ERROR",JOptionPane.ERROR_MESSAGE);
					c.setSelectedIndex(0);
					
				}
			}
				if(c.getSelectedIndex() == 3) {
					
					try {
						main.getG().move(Direction.LEFT);
						 b[x][y].setText("");
							b[x][y].setBackground(Color.WHITE);
							
							j5.setText( "ACTION POINTS" +  ": "+main.getG().getCurrentChampion().getCurrentActionPoints());
							j6.setText("MANA" +  ": "+main.getG().getCurrentChampion().getMana());	
							refreshformove();
							c.setSelectedIndex(0);
					} catch (UnallowedMovementException e1) {
						JOptionPane.showMessageDialog(this," UnallowedMovementException ","ERROR",JOptionPane.ERROR_MESSAGE);
						c.setSelectedIndex(0);
					} catch (NotEnoughResourcesException e1) {
						JOptionPane.showMessageDialog(this," NotEnoughResourcesException ","ERROR",JOptionPane.ERROR_MESSAGE);
						c.setSelectedIndex(0);
						
					}
				}
					if(c.getSelectedIndex() == 4) {
						
						try {
							main.getG().move(Direction.RIGHT);
							 b[x][y].setText("");
								b[x][y].setBackground(Color.WHITE);
							
								j5.setText( "ACTION POINTS" +  ": "+main.getG().getCurrentChampion().getCurrentActionPoints());
								j6.setText("MANA" +  ": "+main.getG().getCurrentChampion().getMana());	
								refreshformove();
								c.setSelectedIndex(0);
						} catch (UnallowedMovementException e1) {
							JOptionPane.showMessageDialog(this," UnallowedMovementException ","ERROR",JOptionPane.ERROR_MESSAGE);
							c.setSelectedIndex(0);
						} catch (NotEnoughResourcesException e1) {
							JOptionPane.showMessageDialog(this," NotEnoughResourcesException ","ERROR",JOptionPane.ERROR_MESSAGE);
							c.setSelectedIndex(0);
							
						}
					}
	//attack******
	if(c1.getSelectedIndex() == 1) {
		
			try {
				main.getG().attack(Direction.UP);
			
				j6.setText("");
				j5.setText("");
				j6.setText("MANA" +  ": "+main.getG().getCurrentChampion().getMana());	
				j5.setText( "ACTION POINTS" +  ": "+main.getG().getCurrentChampion().getCurrentActionPoints());
				refreshforattack();
				checkgame();
				c1.setSelectedIndex(0);
			} catch (ChampionDisarmedException e1) {
				JOptionPane.showMessageDialog(this," ChampionDisarmedException ","ERROR",JOptionPane.ERROR_MESSAGE);
				c1.setSelectedIndex(0);
			} catch (NotEnoughResourcesException e1) {
				JOptionPane.showMessageDialog(this," NotEnoughResourcesException ","ERROR",JOptionPane.ERROR_MESSAGE);
				c1.setSelectedIndex(0);
			} catch (InvalidTargetException e1) {
				JOptionPane.showMessageDialog(this," InvalidTargetException ","ERROR",JOptionPane.ERROR_MESSAGE);
				c1.setSelectedIndex(0);
			}
			
		
	}
			
	if(c1.getSelectedIndex() == 2) {
		
		try {
			main.getG().attack(Direction.DOWN);
			
			j6.setText("");
			j5.setText("");
			j6.setText("MANA" +  ": "+main.getG().getCurrentChampion().getMana());	
			j5.setText( "ACTION POINTS" +  ": "+main.getG().getCurrentChampion().getCurrentActionPoints());
			refreshforattack();
			checkgame();
			c1.setSelectedIndex(0);
		} catch (ChampionDisarmedException e1) {
			JOptionPane.showMessageDialog(this," ChampionDisarmedException ","ERROR",JOptionPane.ERROR_MESSAGE);
			c1.setSelectedIndex(0);
		} catch (NotEnoughResourcesException e1) {
			JOptionPane.showMessageDialog(this," NotEnoughResourcesException ","ERROR",JOptionPane.ERROR_MESSAGE);
			c1.setSelectedIndex(0);
		} catch (InvalidTargetException e1) {
			JOptionPane.showMessageDialog(this," InvalidTargetException ","ERROR",JOptionPane.ERROR_MESSAGE);
			c1.setSelectedIndex(0);
		}
		
	
}
	if(c1.getSelectedIndex() == 3) {
		
		try {
			main.getG().attack(Direction.LEFT);
			
			j6.setText("");
			j5.setText("");
			j6.setText("MANA" +  ": "+main.getG().getCurrentChampion().getMana());	
			j5.setText( "ACTION POINTS" +  ": "+main.getG().getCurrentChampion().getCurrentActionPoints());
			refreshforattack();
			checkgame();
			c1.setSelectedIndex(0);
		} catch (ChampionDisarmedException e1) {
			JOptionPane.showMessageDialog(this," ChampionDisarmedException ","ERROR",JOptionPane.ERROR_MESSAGE);
			c1.setSelectedIndex(0);
		} catch (NotEnoughResourcesException e1) {
			JOptionPane.showMessageDialog(this," NotEnoughResourcesException ","ERROR",JOptionPane.ERROR_MESSAGE);
			c1.setSelectedIndex(0);
		} catch (InvalidTargetException e1) {
			JOptionPane.showMessageDialog(this," InvalidTargetException ","ERROR",JOptionPane.ERROR_MESSAGE);
			c1.setSelectedIndex(0);
		}
		
	
}
	if(c1.getSelectedIndex() == 4) {
		
		try {
			main.getG().attack(Direction.RIGHT);
			
			j6.setText("");
			j5.setText("");
			j6.setText("MANA" +  ": "+main.getG().getCurrentChampion().getMana());	
			j5.setText( "ACTION POINTS" +  ": "+main.getG().getCurrentChampion().getCurrentActionPoints());
			refreshforattack();
			checkgame();
			c1.setSelectedIndex(0);
		} catch (ChampionDisarmedException e1) {
			JOptionPane.showMessageDialog(this," ChampionDisarmedException ","ERROR",JOptionPane.ERROR_MESSAGE);
			c1.setSelectedIndex(0);
		} catch (NotEnoughResourcesException e1) {
			JOptionPane.showMessageDialog(this," NotEnoughResourcesException ","ERROR",JOptionPane.ERROR_MESSAGE);
			c1.setSelectedIndex(0);
		} catch (InvalidTargetException e1) {
			JOptionPane.showMessageDialog(this," InvalidTargetException ","ERROR",JOptionPane.ERROR_MESSAGE);
			c1.setSelectedIndex(0);
		}
		
	
}
	
	// castting***************************
	
	if(b1.isSelected() == true && c4.getSelectedIndex() == 1) {
		try {
			main.getG().castAbility(main.getG().getCurrentChampion().getAbilities().get(0), Direction.UP);
			refreshforcast();
			checkgame();
			c4.setSelectedIndex(0);
		} catch (AbilityUseException e1) {
			JOptionPane.showMessageDialog(this," AbilityUseException ","ERROR",JOptionPane.ERROR_MESSAGE);;
			c4.setSelectedIndex(0);
		} catch (NotEnoughResourcesException e1) {
			JOptionPane.showMessageDialog(this," NotEnoughResourcesException","ERROR",JOptionPane.ERROR_MESSAGE);;
			c4.setSelectedIndex(0);
		} catch (CloneNotSupportedException e1) {
			JOptionPane.showMessageDialog(this," CloneNotSupportedException","ERROR",JOptionPane.ERROR_MESSAGE);;
			c4.setSelectedIndex(0);
		}
	}
	if(b1.isSelected() == true && c4.getSelectedIndex() == 2) {
		try {
			main.getG().castAbility(main.getG().getCurrentChampion().getAbilities().get(0), Direction.DOWN);
			refreshforcast(); 
			checkgame();
			c4.setSelectedIndex(0);
		} catch (AbilityUseException e1) {
			JOptionPane.showMessageDialog(this," AbilityUseException ","ERROR",JOptionPane.ERROR_MESSAGE);;
			c4.setSelectedIndex(0);
		} catch (NotEnoughResourcesException e1) {
			JOptionPane.showMessageDialog(this," NotEnoughResourcesException","ERROR",JOptionPane.ERROR_MESSAGE);;
			c4.setSelectedIndex(0);
		} catch (CloneNotSupportedException e1) {
			JOptionPane.showMessageDialog(this," CloneNotSupportedException","ERROR",JOptionPane.ERROR_MESSAGE);;
			c4.setSelectedIndex(0);
		}
	}
	if(b1.isSelected() == true && c4.getSelectedIndex() == 3) {
		try {
			main.getG().castAbility(main.getG().getCurrentChampion().getAbilities().get(0), Direction.LEFT);
			refreshforcast(); 
			checkgame();
			c4.setSelectedIndex(0);
		} catch (AbilityUseException e1) {
			JOptionPane.showMessageDialog(this," AbilityUseException ","ERROR",JOptionPane.ERROR_MESSAGE);;
			c4.setSelectedIndex(0);
		} catch (NotEnoughResourcesException e1) {
			JOptionPane.showMessageDialog(this," NotEnoughResourcesException","ERROR",JOptionPane.ERROR_MESSAGE);;
			c4.setSelectedIndex(0);
		} catch (CloneNotSupportedException e1) {
			JOptionPane.showMessageDialog(this," CloneNotSupportedException","ERROR",JOptionPane.ERROR_MESSAGE);;
			c4.setSelectedIndex(0);
		}
	}
	if(b1.isSelected() == true && c4.getSelectedIndex() == 4) {
		try {
			main.getG().castAbility(main.getG().getCurrentChampion().getAbilities().get(0), Direction.RIGHT);
			refreshforcast(); 
			checkgame();
			c4.setSelectedIndex(0);
		} catch (AbilityUseException e1) {
			JOptionPane.showMessageDialog(this," AbilityUseException ","ERROR",JOptionPane.ERROR_MESSAGE);;
			c4.setSelectedIndex(0);
		} catch (NotEnoughResourcesException e1) {
			JOptionPane.showMessageDialog(this," NotEnoughResourcesException","ERROR",JOptionPane.ERROR_MESSAGE);;
			c4.setSelectedIndex(0);
		} catch (CloneNotSupportedException e1) {
			JOptionPane.showMessageDialog(this," CloneNotSupportedException","ERROR",JOptionPane.ERROR_MESSAGE);;
			c4.setSelectedIndex(0);
		}
	}
	if(b2.isSelected() == true && c4.getSelectedIndex() == 1) {
		try {
			main.getG().castAbility(main.getG().getCurrentChampion().getAbilities().get(1), Direction.UP);
			refreshforcast(); 
			checkgame();
			c4.setSelectedIndex(0);
		} catch (AbilityUseException e1) {
			JOptionPane.showMessageDialog(this," AbilityUseException ","ERROR",JOptionPane.ERROR_MESSAGE);;
			c4.setSelectedIndex(0);
		} catch (NotEnoughResourcesException e1) {
			JOptionPane.showMessageDialog(this," NotEnoughResourcesException","ERROR",JOptionPane.ERROR_MESSAGE);;
			c4.setSelectedIndex(0);
		} catch (CloneNotSupportedException e1) {
			JOptionPane.showMessageDialog(this," CloneNotSupportedException","ERROR",JOptionPane.ERROR_MESSAGE);;
			c4.setSelectedIndex(0);
		}
	}
	if(b2.isSelected() == true && c4.getSelectedIndex() == 2) {
		try {
			main.getG().castAbility(main.getG().getCurrentChampion().getAbilities().get(1), Direction.DOWN);
			refreshforcast(); 
			checkgame();
			c4.setSelectedIndex(0);
		} catch (AbilityUseException e1) {
			JOptionPane.showMessageDialog(this," AbilityUseException ","ERROR",JOptionPane.ERROR_MESSAGE);;
			c4.setSelectedIndex(0);
		} catch (NotEnoughResourcesException e1) {
			JOptionPane.showMessageDialog(this," NotEnoughResourcesException","ERROR",JOptionPane.ERROR_MESSAGE);;
			c4.setSelectedIndex(0);
		} catch (CloneNotSupportedException e1) {
			JOptionPane.showMessageDialog(this," CloneNotSupportedException","ERROR",JOptionPane.ERROR_MESSAGE);;
			c4.setSelectedIndex(0);
		}
	}
	if(b2.isSelected() == true && c4.getSelectedIndex() == 3) {
		try {
			main.getG().castAbility(main.getG().getCurrentChampion().getAbilities().get(1), Direction.LEFT);
			refreshforcast(); 
			checkgame();
			c4.setSelectedIndex(0);
		} catch (AbilityUseException e1) {
			JOptionPane.showMessageDialog(this," AbilityUseException ","ERROR",JOptionPane.ERROR_MESSAGE);;
			c4.setSelectedIndex(0);
		} catch (NotEnoughResourcesException e1) {
			JOptionPane.showMessageDialog(this," NotEnoughResourcesException","ERROR",JOptionPane.ERROR_MESSAGE);;
			c4.setSelectedIndex(0);
		} catch (CloneNotSupportedException e1) {
			JOptionPane.showMessageDialog(this," CloneNotSupportedException","ERROR",JOptionPane.ERROR_MESSAGE);;
			c4.setSelectedIndex(0);
		}
	}
	if(b2.isSelected() == true && c4.getSelectedIndex() == 4) {
		try {
			main.getG().castAbility(main.getG().getCurrentChampion().getAbilities().get(1), Direction.RIGHT);
			refreshforcast(); 
			checkgame();
			c4.setSelectedIndex(0);
		} catch (AbilityUseException e1) {
			JOptionPane.showMessageDialog(this," AbilityUseException ","ERROR",JOptionPane.ERROR_MESSAGE);;
			c4.setSelectedIndex(0);
		} catch (NotEnoughResourcesException e1) {
			JOptionPane.showMessageDialog(this," NotEnoughResourcesException","ERROR",JOptionPane.ERROR_MESSAGE);;
			c4.setSelectedIndex(0);
		} catch (CloneNotSupportedException e1) {
			JOptionPane.showMessageDialog(this," CloneNotSupportedException","ERROR",JOptionPane.ERROR_MESSAGE);;
			c4.setSelectedIndex(0);
		}
	}
	if(b3.isSelected() == true && c4.getSelectedIndex() == 1) {
		try {
			main.getG().castAbility(main.getG().getCurrentChampion().getAbilities().get(2), Direction.UP);
			refreshforcast(); 
			checkgame();
			c4.setSelectedIndex(0);
		 } catch (AbilityUseException e1) {
			JOptionPane.showMessageDialog(this," AbilityUseException ","ERROR",JOptionPane.ERROR_MESSAGE);;
			c4.setSelectedIndex(0);
		} catch (NotEnoughResourcesException e1) {
			JOptionPane.showMessageDialog(this," NotEnoughResourcesException","ERROR",JOptionPane.ERROR_MESSAGE);;
			c4.setSelectedIndex(0);
		} catch (CloneNotSupportedException e1) {
			JOptionPane.showMessageDialog(this," CloneNotSupportedException","ERROR",JOptionPane.ERROR_MESSAGE);;
			c4.setSelectedIndex(0);
		
		}
	}
	if(b3.isSelected() == true && c4.getSelectedIndex() == 2) {
		try {
			main.getG().castAbility(main.getG().getCurrentChampion().getAbilities().get(2), Direction.DOWN);
			refreshforcast(); 
			checkgame();
			c4.setSelectedIndex(0);
		} catch (AbilityUseException e1) {
			JOptionPane.showMessageDialog(this," AbilityUseException ","ERROR",JOptionPane.ERROR_MESSAGE);;
			c4.setSelectedIndex(0);
		} catch (NotEnoughResourcesException e1) {
			JOptionPane.showMessageDialog(this," NotEnoughResourcesException","ERROR",JOptionPane.ERROR_MESSAGE);;
			c4.setSelectedIndex(0);
		} catch (CloneNotSupportedException e1) {
			JOptionPane.showMessageDialog(this," CloneNotSupportedException","ERROR",JOptionPane.ERROR_MESSAGE);;
			c4.setSelectedIndex(0);
		}
		}
	
	if(b3.isSelected() == true && c4.getSelectedIndex() == 3) {
	try {
			main.getG().castAbility(main.getG().getCurrentChampion().getAbilities().get(2), Direction.LEFT);
			refreshforcast(); 
			checkgame();
			c4.setSelectedIndex(0);
	} catch (AbilityUseException e1) {
		JOptionPane.showMessageDialog(this," AbilityUseException ","ERROR",JOptionPane.ERROR_MESSAGE);;
		c4.setSelectedIndex(0);
	} catch (NotEnoughResourcesException e1) {
		JOptionPane.showMessageDialog(this," NotEnoughResourcesException","ERROR",JOptionPane.ERROR_MESSAGE);;
		c4.setSelectedIndex(0);
	} catch (CloneNotSupportedException e1) {
		JOptionPane.showMessageDialog(this," CloneNotSupportedException","ERROR",JOptionPane.ERROR_MESSAGE);;
		c4.setSelectedIndex(0);
	}
	}
	if(b3.isSelected() == true && c4.getSelectedIndex() == 4) {
		
			try {
				main.getG().castAbility(main.getG().getCurrentChampion().getAbilities().get(2), Direction.RIGHT);
				refreshforcast(); 
				checkgame();
				c4.setSelectedIndex(0);
			} catch (AbilityUseException e1) {
				JOptionPane.showMessageDialog(this," AbilityUseException ","ERROR",JOptionPane.ERROR_MESSAGE);;
				c4.setSelectedIndex(0);
			} catch (NotEnoughResourcesException e1) {
				JOptionPane.showMessageDialog(this," NotEnoughResourcesException","ERROR",JOptionPane.ERROR_MESSAGE);;
				c4.setSelectedIndex(0);
			} catch (CloneNotSupportedException e1) {
				JOptionPane.showMessageDialog(this," CloneNotSupportedException","ERROR",JOptionPane.ERROR_MESSAGE);;
				c4.setSelectedIndex(0);
			}
		
	}
	
	//endturn******
	if(e.getSource() == j) {
		main.getG().endTurn();
		refreshformove();
		
		j3.setText("NAME"+" : " + main.getG().getCurrentChampion().getName());
		j5.setText("");
		j5.setText( "ACTION POINTS" +  ": "+main.getG().getCurrentChampion().getCurrentActionPoints());
		j6.setText("");
		j6.setText("MANA" +  ": "+main.getG().getCurrentChampion().getMana());	
		j7.setText( "ATTACK DAMAGE" +  ": "+main.getG().getCurrentChampion().getAttackDamage());
		j8.setText("ATTACK RANGE" +  ": "+main.getG().getCurrentChampion().getAttackRange());
		j9.setText("");
		if(main.getG().getCurrentChampion() instanceof AntiHero) {
			j9 = new JLabel( "TYPE" +  ": " + "anti hero ");
			j9.setBounds(700,70 , 150, 50);
			this.add(j9);
			}
			else if(main.getG().getCurrentChampion() instanceof Hero) {
				j9 = new JLabel( "TYPE" +  ": " + " hero ");
				j9.setBounds(700,70 , 150, 50);
				this.add(j9);
			}
			else if(main.getG().getCurrentChampion() instanceof Villain) {
				j9 = new JLabel( "TYPE" +  ": " + " villain");
				j9.setBounds(700,70 , 150, 50);
				this.add(j9);
			}
		j10.setText("");
		j11.setText("");
		j12.setText("");
		String [] y1 = new  String[main.getG().getCurrentChampion().getAbilities().size()];
		for(int x1 = 0; x1 < main.getG().getCurrentChampion().getAbilities().size();x1++ ) {
			 y1 [x1] = main.getG().getCurrentChampion().getAbilities().get(x1).getName();
		}
	  j10.setText(y1[0]);
	
	 j11.setText(y1[1]);
	
	 j12.setText(y1[2]);
	
	 
	 
	  
	  }
	 // info*****************************************
	 	for(int x2 = 0; x2 < main.getG().getBoard().length;x2++) {
	 		for(int y2 = 0; y2 < main.getG().getBoard().length;y2++) {
		 		if(main.getG().getBoard()[x2][y2] instanceof Champion) {
		 			Champion c = (Champion) main.getG().getBoard()[x2][y2];
		 			String s ="";
	 				String  [] s1 = new String[c.getAppliedEffects().size()];
		 			for(int p = 0 ; p < c.getAppliedEffects().size();p++) {
		 				s1 [p]= " Effect Name :" + " " + c.getAppliedEffects().get(p).getName()+","+ " "+ " Effect Duration :"+" "+ c.getAppliedEffects().get(p).getDuration() +","+" ";
		 			}
		 			for(int f = 0 ; f < s1.length;f++) {
		 				s = s1[f];
		 				
		 			}
		 			
		 			
		 			if(c instanceof Hero) {
		 				
		 				
		 			if(e.getSource() == b[x2][y2]) {
		 				JOptionPane.showMessageDialog(this, "Name:"+ " " +  c.getName()+ " "+ " Mana :"+ c.getMana() +" "+ " Abilities:"+" "+c.getAbilities().get(0).getName()+","+c.getAbilities().get(1).getName()+","+c.getAbilities().get(2).getName()+" ,"+ " " + " Damge amount :"+" "+c.getAttackDamage()+" ,"+ " " + " Damge range :"+" "+c.getAttackRange()+" ,"+ " " +" health points :"+ " "+ c.getCurrentHP()+" ,"+ " " +" action points :" + " "+ c.getMaxActionPointsPerTurn()+" ," +" Type :" + " hero" + ","+ s,"info",JOptionPane.INFORMATION_MESSAGE);
						
		 			}
		 			}
		 			if(c instanceof AntiHero) {
			 			if(e.getSource() == b[x2][y2]) {
			 				JOptionPane.showMessageDialog(this, "Name:"+ " " +c.getName()+" "+ " Mana :"+ c.getMana() +" " +" "+ " Abilities:"+" "+c.getAbilities().get(0).getName()+","+c.getAbilities().get(1).getName()+","+c.getAbilities().get(2).getName()+" ,"+ " " + " Damge amount :"+" "+c.getAttackDamage()+" ,"+ " " + " Damge range :"+" "+c.getAttackRange()+" ,"+ " " +" health points :"+ " "+ c.getCurrentHP()+" ,"+ " " +" action points :" + " "+ c.getMaxActionPointsPerTurn()+" ," +" Type :" + " AntiHero" + ","+ s,"info",JOptionPane.INFORMATION_MESSAGE);
		 		    }
		 			}
		 			if(c instanceof Villain) {
			 			if(e.getSource() == b[x2][y2]) {
			 				JOptionPane.showMessageDialog(this, "Name:"+ " " +c.getName()+" "+ " Mana :"+ c.getMana() +" " +" "+ " Abilities:"+" "+c.getAbilities().get(0).getName()+","+c.getAbilities().get(1).getName()+","+c.getAbilities().get(2).getName()+" ,"+ " " + " Damge amount :"+" "+c.getAttackDamage()+" ,"+ " " + " Damge range :"+" "+c.getAttackRange()+" ,"+ " " +" health points :"+ " "+ c.getCurrentHP()+" ,"+ " " +" action points :" + " "+ c.getMaxActionPointsPerTurn()+" ," +" Type :" + " villian" + ","+ s,"info",JOptionPane.INFORMATION_MESSAGE);
		 		    }
		 			}
		 			
		 		}
		 	}
	 	}
	   
	 	if(e.getSource() == g) {
	 		if(b1.isSelected() == true) {
	 			
 				if(main.getG().getCurrentChampion().getAbilities().get(0).getCastArea() == AreaOfEffect.SELFTARGET ||main.getG().getCurrentChampion().getAbilities().get(0).getCastArea() == AreaOfEffect.SURROUND || main.getG().getCurrentChampion().getAbilities().get(0).getCastArea() == AreaOfEffect.TEAMTARGET ) {
 					try {
						main.getG().castAbility(main.getG().getCurrentChampion().getAbilities().get(0));
						refreshforcast();
						checkgame();
					} catch (AbilityUseException e1) {
						JOptionPane.showMessageDialog(this," AbilityUseException ","ERROR",JOptionPane.ERROR_MESSAGE);;
					} catch (NotEnoughResourcesException e1) {
						JOptionPane.showMessageDialog(this," NotEnoughResourcesException ","ERROR",JOptionPane.ERROR_MESSAGE);;
					} catch (CloneNotSupportedException e1) {
						JOptionPane.showMessageDialog(this," CloneNotSupportedException ","ERROR",JOptionPane.ERROR_MESSAGE);;
					}
				
			
 		}
 				else if(main.getG().getCurrentChampion().getAbilities().get(0).getCastArea() == AreaOfEffect.DIRECTIONAL) {
 					JOptionPane.showMessageDialog(this," Choose direction ","ERROR",JOptionPane.ERROR_MESSAGE);;
 				}
 				else if(main.getG().getCurrentChampion().getAbilities().get(0).getCastArea() == AreaOfEffect.SINGLETARGET) {
 					JOptionPane.showMessageDialog(this," right click on target ","ERROR",JOptionPane.ERROR_MESSAGE);;
 				}
	 	}
	 		if(b2.isSelected() == true) {
	 			
 				if(main.getG().getCurrentChampion().getAbilities().get(1).getCastArea() == AreaOfEffect.SELFTARGET ||main.getG().getCurrentChampion().getAbilities().get(1).getCastArea() == AreaOfEffect.SURROUND || main.getG().getCurrentChampion().getAbilities().get(1).getCastArea() == AreaOfEffect.TEAMTARGET ) {
 					try {
						main.getG().castAbility(main.getG().getCurrentChampion().getAbilities().get(1));
						refreshforcast();
						checkgame();
						
					} catch (AbilityUseException e1) {
						JOptionPane.showMessageDialog(this," AbilityUseException ","ERROR",JOptionPane.ERROR_MESSAGE);;
					} catch (NotEnoughResourcesException e1) {
						JOptionPane.showMessageDialog(this," NotEnoughResourcesException ","ERROR",JOptionPane.ERROR_MESSAGE);;
					} catch (CloneNotSupportedException e1) {
						JOptionPane.showMessageDialog(this," CloneNotSupportedException ","ERROR",JOptionPane.ERROR_MESSAGE);;
					}
				
			
 		}
 				else if(main.getG().getCurrentChampion().getAbilities().get(1).getCastArea() == AreaOfEffect.DIRECTIONAL) {
 					JOptionPane.showMessageDialog(this," Choose direction ","ERROR",JOptionPane.ERROR_MESSAGE);;
 				}
 				else if(main.getG().getCurrentChampion().getAbilities().get(1).getCastArea() == AreaOfEffect.SINGLETARGET) {
 					JOptionPane.showMessageDialog(this," right click Target","ERROR",JOptionPane.ERROR_MESSAGE);;
 				}
	 	}
	 	
	 	if(b3.isSelected() == true) {
 			
 				if(main.getG().getCurrentChampion().getAbilities().get(2).getCastArea() == AreaOfEffect.SELFTARGET ||main.getG().getCurrentChampion().getAbilities().get(2).getCastArea() == AreaOfEffect.SURROUND || main.getG().getCurrentChampion().getAbilities().get(2).getCastArea() == AreaOfEffect.TEAMTARGET ) {
 					try {
						main.getG().castAbility(main.getG().getCurrentChampion().getAbilities().get(2));
						refreshforcast();
						checkgame();
					} catch (AbilityUseException e1) {
						JOptionPane.showMessageDialog(this," AbilityUseException ","ERROR",JOptionPane.ERROR_MESSAGE);;
					} catch (NotEnoughResourcesException e1) {
						JOptionPane.showMessageDialog(this," NotEnoughResourcesException ","ERROR",JOptionPane.ERROR_MESSAGE);;
					} catch (CloneNotSupportedException e1) {
						JOptionPane.showMessageDialog(this," CloneNotSupportedException ","ERROR",JOptionPane.ERROR_MESSAGE);;
					}
 		}
 				else if(main.getG().getCurrentChampion().getAbilities().get(2).getCastArea() == AreaOfEffect.DIRECTIONAL) {
 					JOptionPane.showMessageDialog(this," Choose direction ","ERROR",JOptionPane.ERROR_MESSAGE);
 				}
 				else if(main.getG().getCurrentChampion().getAbilities().get(2).getCastArea() == AreaOfEffect.SINGLETARGET) {
 					JOptionPane.showMessageDialog(this," right click target ","ERROR",JOptionPane.ERROR_MESSAGE);
 				}
	 	}
	 	}
	  
	 	if(e.getSource() == m) {
	 		Champion c = main.getG().getCurrentChampion();
	 		
			if(c.getAbilities().get(0) instanceof HealingAbility) {
				HealingAbility h = (HealingAbility)(c.getAbilities().get(0));
	 			
	 		JOptionPane.showMessageDialog(this," Name : "+  " "+ c.getAbilities().get(0).getName()+" , "+" Area of Effect : "+  " "+ c.getAbilities().get(0).getCastArea()+" , "+" Range : "+  " "+ c.getAbilities().get(0).getCastRange()+" , "+" Mana cost : "+  " "+ c.getAbilities().get(0).getManaCost()+" , "+" Basecooldown : "+  " "+ c.getAbilities().get(0).getBaseCooldown()+" , "+" CurrentCooldown : "+  " "+ c.getAbilities().get(0).getCurrentCooldown()+" , "+" type : "+  "healing ability"+" , "+ " "+" Healing Amount :"+h.getHealAmount()+"","INFO",JOptionPane.INFORMATION_MESSAGE);;
	 	
			}
	 	 if(c.getAbilities().get(0) instanceof DamagingAbility) {
 			DamagingAbility d = (DamagingAbility)(c.getAbilities().get(0));
	 		JOptionPane.showMessageDialog(this," Name : "+  " "+ c.getAbilities().get(0).getName()+" , "+" Area of Effect : "+  " "+ c.getAbilities().get(0).getCastArea()+" , "+" Range : "+  " "+ c.getAbilities().get(0).getCastRange()+" , "+" Mana cost : "+  " "+ c.getAbilities().get(0).getManaCost()+" , "+" Basecooldown : "+  " "+ c.getAbilities().get(0).getBaseCooldown()+" , "+" CurrentCooldown : "+  " "+ c.getAbilities().get(0).getCurrentCooldown()+" , "+" type : "+ " Damaging ability" + " , "+ " Damaging Amount :" + d.getDamageAmount()+"","INFO",JOptionPane.INFORMATION_MESSAGE);;
	 	 }
	 		if(c.getAbilities().get(0) instanceof CrowdControlAbility) {
	 			CrowdControlAbility a = (CrowdControlAbility)(c.getAbilities().get(0));
		 		JOptionPane.showMessageDialog(this," Name : "+  " "+ c.getAbilities().get(0).getName()+" , "+" Area of Effect : "+  " "+ c.getAbilities().get(0).getCastArea()+" , "+" Range : "+  " "+ c.getAbilities().get(0).getCastRange()+" , "+" Mana cost : "+  " "+ c.getAbilities().get(0).getManaCost()+" , "+" Basecooldown : "+  " "+ c.getAbilities().get(0).getBaseCooldown()+" , "+" CurrentCooldown : "+  " "+ c.getAbilities().get(0).getCurrentCooldown()+" , "+" type : "+ " CrowdControl ability " +" " + " , "+" effect name : "+ a.getEffect().getName() +  ",  effect duration :"+  a.getEffect().getDuration(),  "INFO",JOptionPane.INFORMATION_MESSAGE);;
			}
	 	 
			
	 	}
	 	
	 	if(e.getSource() == m1) {
	 		Champion c = main.getG().getCurrentChampion();
	 		
	 		if(c.getAbilities().get(1) instanceof HealingAbility) {
				HealingAbility h = (HealingAbility)(c.getAbilities().get(1));
	 			
	 		JOptionPane.showMessageDialog(this," Name : "+  " "+ c.getAbilities().get(1).getName()+" , "+" Area of Effect : "+  " "+ c.getAbilities().get(1).getCastArea()+" , "+" Range : "+  " "+ c.getAbilities().get(1).getCastRange()+" , "+" Mana cost : "+  " "+ c.getAbilities().get(1).getManaCost()+" , "+" Basecooldown : "+  " "+ c.getAbilities().get(1).getBaseCooldown()+" , "+" CurrentCooldown : "+  " "+ c.getAbilities().get(1).getCurrentCooldown()+" , "+" type : "+  "healing ability"+" , "+ " "+" Healing Amount :"+h.getHealAmount()+"","INFO",JOptionPane.INFORMATION_MESSAGE);;
	 		}
	 
	 	 if(c.getAbilities().get(1) instanceof DamagingAbility) {
 			DamagingAbility d = (DamagingAbility)(c.getAbilities().get(1));
	 		JOptionPane.showMessageDialog(this," Name : "+  " "+ c.getAbilities().get(1).getName()+" , "+" Area of Effect : "+  " "+ c.getAbilities().get(1).getCastArea()+" , "+" Range : "+  " "+ c.getAbilities().get(1).getCastRange()+" , "+" Mana cost : "+  " "+ c.getAbilities().get(1).getManaCost()+" , "+" Basecooldown : "+  " "+ c.getAbilities().get(1).getBaseCooldown()+" , "+" CurrentCooldown : "+  " "+ c.getAbilities().get(1).getCurrentCooldown()+" , "+" type : "+ " Damaging ability" + " , "+ " Damaging Amount :" + d.getDamageAmount()+"","INFO",JOptionPane.INFORMATION_MESSAGE);;
	 	 }
	 		if(c.getAbilities().get(1) instanceof CrowdControlAbility) {
	 			CrowdControlAbility a = (CrowdControlAbility)(c.getAbilities().get(1));
		 		JOptionPane.showMessageDialog(this," Name : "+  " "+ c.getAbilities().get(1).getName()+" , "+" Area of Effect : "+  " "+ c.getAbilities().get(0).getCastArea()+" , "+" Range : "+  " "+ c.getAbilities().get(1).getCastRange()+" , "+" Mana cost : "+  " "+ c.getAbilities().get(1).getManaCost()+" , "+" Basecooldown : "+  " "+ c.getAbilities().get(1).getBaseCooldown()+" , "+" CurrentCooldown : "+  " "+ c.getAbilities().get(1).getCurrentCooldown()+" , "+" type : "+ " CrowdControl ability " +" " + " , "+" effect name : "+ a.getEffect().getName() +  ",  effect duration :"+  a.getEffect().getDuration(),  "INFO",JOptionPane.INFORMATION_MESSAGE);;
			}
	 	 
			
	 	}
	 	if(e.getSource() == m2) {
	 		Champion c = main.getG().getCurrentChampion();
	 		
	 
	 		if(c.getAbilities().get(2) instanceof HealingAbility) {
				HealingAbility h = (HealingAbility)(c.getAbilities().get(2));
	 			
	 		JOptionPane.showMessageDialog(this," Name : "+  " "+ c.getAbilities().get(2).getName()+" , "+" Area of Effect : "+  " "+ c.getAbilities().get(2).getCastArea()+" , "+" Range : "+  " "+ c.getAbilities().get(2).getCastRange()+" , "+" Mana cost : "+  " "+ c.getAbilities().get(2).getManaCost()+" , "+" Basecooldown : "+  " "+ c.getAbilities().get(2).getBaseCooldown()+" , "+" CurrentCooldown : "+  " "+ c.getAbilities().get(2).getCurrentCooldown()+" , "+" type : "+  "healing ability"+" , "+ " "+" Healing Amount :"+h.getHealAmount()+"","INFO",JOptionPane.INFORMATION_MESSAGE);;
	 	
	 		}
	 	 if(c.getAbilities().get(2) instanceof DamagingAbility) {
 			DamagingAbility d = (DamagingAbility)(c.getAbilities().get(2));
	 		JOptionPane.showMessageDialog(this," Name : "+  " "+ c.getAbilities().get(2).getName()+" , "+" Area of Effect : "+  " "+ c.getAbilities().get(2).getCastArea()+" , "+" Range : "+  " "+ c.getAbilities().get(2).getCastRange()+" , "+" Mana cost : "+  " "+ c.getAbilities().get(2).getManaCost()+" , "+" Basecooldown : "+  " "+ c.getAbilities().get(2).getBaseCooldown()+" , "+" CurrentCooldown : "+  " "+ c.getAbilities().get(2).getCurrentCooldown()+" , "+" type : "+ " Damaging ability" + " , "+ " Damaging Amount :" + d.getDamageAmount()+"","INFO",JOptionPane.INFORMATION_MESSAGE);;
	 	 }
	 		if(c.getAbilities().get(2) instanceof CrowdControlAbility) {
	 			CrowdControlAbility a = (CrowdControlAbility)(c.getAbilities().get(2));
		 		JOptionPane.showMessageDialog(this," Name : "+  " "+ c.getAbilities().get(2).getName()+" , "+" Area of Effect : "+  " "+ c.getAbilities().get(2).getCastArea()+" , "+" Range : "+  " "+ c.getAbilities().get(2).getCastRange()+" , "+" Mana cost : "+  " "+ c.getAbilities().get(2).getManaCost()+" , "+" Basecooldown : "+  " "+ c.getAbilities().get(2).getBaseCooldown()+" , "+" CurrentCooldown : "+  " "+ c.getAbilities().get(2).getCurrentCooldown()+" , "+" type : "+ " CrowdControl ability " +" " + " , "+" effect name : "+ a.getEffect().getName() +  ",  effect duration :"+  a.getEffect().getDuration(),  "INFO",JOptionPane.INFORMATION_MESSAGE);;
			}
	 	 
			
	 	}
	 	if(e.getSource() == m3) {
	 		try {
				main.getG().useLeaderAbility();
				refreshforcast();
				checkgame();
				if(main.getG().getFirstPlayer().getTeam().contains(main.getG().getCurrentChampion())) {
					m6.setText(" First Player  Leader Ability : " +  "  Used");
				}
				if(main.getG().getSecondPlayer().getTeam().contains(main.getG().getCurrentChampion())) {
					m7.setText(" Second Player  Leader Ability : " +  " Used");
				}
				
			} catch (LeaderAbilityAlreadyUsedException e1) {
				JOptionPane.showMessageDialog(this," LeaderAbilityAlreadyUsedException ","ERROR",JOptionPane.ERROR_MESSAGE);;
			} catch (LeaderNotCurrentException e1) {
				JOptionPane.showMessageDialog(this," LeaderNotCurrentException ","ERROR",JOptionPane.ERROR_MESSAGE);;
			}
	 	}
	 	
	 	if(e.getSource() == m4) {
	 		String s[] = new String[main.getG().getCurrentChampion().getAppliedEffects().size()];
	 		for(int x6 = 0 ; x6 < main.getG().getCurrentChampion().getAppliedEffects().size();x6++) {
	 			s [x6] =  " Name : "+ main.getG().getCurrentChampion().getAppliedEffects().get(x6).getName() +  " , " + "  Duration :" +  main.getG().getCurrentChampion().getAppliedEffects().get(x6).getDuration();
	 		}
	 		String p = "";
	 		for(int y6 = 0; y6 <s.length;y6++) {
	 			p =  s[y6] + " ";
	 			
	 		}
	 		JOptionPane.showMessageDialog(this, p,"INFO",JOptionPane.INFORMATION_MESSAGE);;
	 		
	 	}
	 	
	}
	@Override
	public void mouseClicked(MouseEvent e) {
	
		if (e.getModifiers() == MouseEvent.BUTTON3_MASK && e.getClickCount() !=0) {
			for(int i = 0 ; i < main.getG().getBoard().length ; i++) {
				for(int j = 0; j < main.getG().getBoard().length;j++) {
					if( main.getG().getBoard()[i][j] != null) {
							if(e.getSource() == b[i][j]) {
								if( main.getG().getBoard()[i][j] instanceof Cover) {
									Cover c =  (Cover) main.getG().getBoard()[i][j];
								if(main.getG().getCurrentChampion().getAbilities().get(0).getCastArea() == AreaOfEffect.SINGLETARGET) {
									try {
										main.getG().castAbility(main.getG().getCurrentChampion().getAbilities().get(0),(int)  c.getLocation().getX(),(int) c.getLocation().getY());
										refreshforcast();
										checkgame();
									} catch (AbilityUseException e1) {
										JOptionPane.showMessageDialog(this, "AbilityUseException","Error",JOptionPane.ERROR_MESSAGE);;
									} catch (NotEnoughResourcesException e1) {
										JOptionPane.showMessageDialog(this, "NotEnoughResourcesException","Error",JOptionPane.ERROR_MESSAGE);;
									} catch (InvalidTargetException e1) {
										JOptionPane.showMessageDialog(this, "InvalidTargetException","Error",JOptionPane.ERROR_MESSAGE);;
									} catch (CloneNotSupportedException e1) {
										JOptionPane.showMessageDialog(this, "CloneNotSupportedException","Error",JOptionPane.ERROR_MESSAGE);;
									}
								}
								if(main.getG().getCurrentChampion().getAbilities().get(1).getCastArea() == AreaOfEffect.SINGLETARGET) {
									try {
										main.getG().castAbility(main.getG().getCurrentChampion().getAbilities().get(1),(int) c.getLocation().getX(),(int) c.getLocation().getY());
										refreshforcast();
										checkgame();
									} catch (AbilityUseException e1) {
										JOptionPane.showMessageDialog(this, "AbilityUseException","Error",JOptionPane.ERROR_MESSAGE);;
									} catch (NotEnoughResourcesException e1) {
										JOptionPane.showMessageDialog(this, "NotEnoughResourcesException","Error",JOptionPane.ERROR_MESSAGE);;
									} catch (InvalidTargetException e1) {
										JOptionPane.showMessageDialog(this, "InvalidTargetException","Error",JOptionPane.ERROR_MESSAGE);;
									} catch (CloneNotSupportedException e1) {
										JOptionPane.showMessageDialog(this, "CloneNotSupportedException","Error",JOptionPane.ERROR_MESSAGE);;
									}
								}
								if(main.getG().getCurrentChampion().getAbilities().get(2).getCastArea() == AreaOfEffect.SINGLETARGET) {
									try {
										main.getG().castAbility(main.getG().getCurrentChampion().getAbilities().get(2),(int) c.getLocation().getX(),(int) c.getLocation().getY());
										refreshforcast();
										checkgame();
									} catch (AbilityUseException e1) {
										JOptionPane.showMessageDialog(this, "AbilityUseException","Error",JOptionPane.ERROR_MESSAGE);;
									} catch (NotEnoughResourcesException e1) {
										JOptionPane.showMessageDialog(this, "NotEnoughResourcesException","Error",JOptionPane.ERROR_MESSAGE);;
									} catch (InvalidTargetException e1) {
										JOptionPane.showMessageDialog(this, "InvalidTargetException","Error",JOptionPane.ERROR_MESSAGE);;
									} catch (CloneNotSupportedException e1) {
										JOptionPane.showMessageDialog(this, "CloneNotSupportedException","Error",JOptionPane.ERROR_MESSAGE);;
									}
								}
							}
								else if( main.getG().getBoard()[i][j] instanceof Champion) {
									Champion c =  (Champion) main.getG().getBoard()[i][j];
								if(main.getG().getCurrentChampion().getAbilities().get(0).getCastArea() == AreaOfEffect.SINGLETARGET) {
									try {
										main.getG().castAbility(main.getG().getCurrentChampion().getAbilities().get(0),(int)  c.getLocation().getX(),(int) c.getLocation().getY());
										refreshforcast();
										checkgame();
									} catch (AbilityUseException e1) {
										JOptionPane.showMessageDialog(this, "AbilityUseException","Error",JOptionPane.ERROR_MESSAGE);;
									} catch (NotEnoughResourcesException e1) {
										JOptionPane.showMessageDialog(this, "NotEnoughResourcesException","Error",JOptionPane.ERROR_MESSAGE);;
									} catch (InvalidTargetException e1) {
										JOptionPane.showMessageDialog(this, "InvalidTargetException","Error",JOptionPane.ERROR_MESSAGE);;
									} catch (CloneNotSupportedException e1) {
										JOptionPane.showMessageDialog(this, "CloneNotSupportedException","Error",JOptionPane.ERROR_MESSAGE);;
									}
								}
								if(main.getG().getCurrentChampion().getAbilities().get(1).getCastArea() == AreaOfEffect.SINGLETARGET) {
									try {
										main.getG().castAbility(main.getG().getCurrentChampion().getAbilities().get(1),(int) c.getLocation().getX(),(int) c.getLocation().getY());
										refreshforcast();
										checkgame();
									} catch (AbilityUseException e1) {
										JOptionPane.showMessageDialog(this, "AbilityUseException","Error",JOptionPane.ERROR_MESSAGE);;
									} catch (NotEnoughResourcesException e1) {
										JOptionPane.showMessageDialog(this, "NotEnoughResourcesException","Error",JOptionPane.ERROR_MESSAGE);;
									} catch (InvalidTargetException e1) {
										JOptionPane.showMessageDialog(this, "InvalidTargetException","Error",JOptionPane.ERROR_MESSAGE);;
									} catch (CloneNotSupportedException e1) {
										JOptionPane.showMessageDialog(this, "CloneNotSupportedException","Error",JOptionPane.ERROR_MESSAGE);;
									}
								}
								if(main.getG().getCurrentChampion().getAbilities().get(2).getCastArea() == AreaOfEffect.SINGLETARGET) {
									try {
										main.getG().castAbility(main.getG().getCurrentChampion().getAbilities().get(2),(int) c.getLocation().getX(),(int) c.getLocation().getY());
										refreshforcast();
										checkgame();
									} catch (AbilityUseException e1) {
										JOptionPane.showMessageDialog(this, "AbilityUseException","Error",JOptionPane.ERROR_MESSAGE);;
									} catch (NotEnoughResourcesException e1) {
										JOptionPane.showMessageDialog(this, "NotEnoughResourcesException","Error",JOptionPane.ERROR_MESSAGE);;
									} catch (InvalidTargetException e1) {
										JOptionPane.showMessageDialog(this, "InvalidTargetException","Error",JOptionPane.ERROR_MESSAGE);;
									} catch (CloneNotSupportedException e1) {
										JOptionPane.showMessageDialog(this, "CloneNotSupportedException","Error",JOptionPane.ERROR_MESSAGE);;
									}
								}
							}
								
							
							}
						}	
						}	
			}
			
			
			
		}
			
			}
		
	   
	
	@Override
	public void mousePressed(MouseEvent e) {
		
	
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}

	



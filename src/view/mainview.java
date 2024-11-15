package view;

import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import engine.Game;
import engine.Player;

public class mainview extends JFrame {
     private Game game;
	 private PlayerNames n;
	 private Choose w;	
	 private Player p1;
	 private Player p2;
	 private Final o;
	 private JButton attack;
	 
		public mainview() {
			 n = new PlayerNames(this);
			this.getContentPane().add(n);
			this.setTitle("I love you 3000");
			try {
				Game.loadAbilities("abilities.csv");
				Game.loadChampions("Champions.csv");
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			this.setSize(1450,850);
			this.setVisible(true);
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		}
		public void switchToNextPanel(String text , String text2) {
             p1 = new Player(text);
             p2 = new Player(text2);
            
			this.getContentPane().remove(n);
			
		
			w = new Choose(this);
			this.getContentPane().add(w);
			this.validate();
			this.repaint();
		}
		public void switchToFinalPanel(int a ,int b, int c, int d ,int e , int f,int g, int h) {
			p1.setLeader(Game.getAvailableChampions().get(g));
			p1.getTeam().add(Game.getAvailableChampions().get(a));
			p1.getTeam().add(Game.getAvailableChampions().get(b));
			p1.getTeam().add(Game.getAvailableChampions().get(c));
			
			p2.setLeader(Game.getAvailableChampions().get(h));
			p2.getTeam().add(Game.getAvailableChampions().get(d));
			p2.getTeam().add(Game.getAvailableChampions().get(e));
			p2.getTeam().add(Game.getAvailableChampions().get(f));
			
			game = new Game(p1,p2);
			game.placeChampions();
			game.placeCovers();
			
			o = new Final(this);
			this.getContentPane().remove(w);
			this.getContentPane().add(o);
			
			this.validate();
			this.repaint();
			
			
		}
		
		
	public Game getG() {
			return game;
		}
	public static void main(String [] args) {
		mainview  f =  new mainview();
	}

}

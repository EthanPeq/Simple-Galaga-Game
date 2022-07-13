import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;


public class GalagaPanel extends JPanel implements KeyListener{
	protected JPanel panel;
	protected ArrayList<Alien> alienList;
	ImageIcon GalagaShip, GalagaAlien, space;
	Ship ship;
	Bullet bullet;
	int dead, listlength;
	
	GalagaPanel(){
		panel = new JPanel();
		panel.setBackground(Color.black);
		
		GalagaShip = new ImageIcon("imageFolder//GalagaShip.png");
		GalagaAlien = new ImageIcon("imageFolder//GalagaAlien.jpg");
		space = new ImageIcon("imageFolder//GalagaSpace.jpg");
		
		alienList = new ArrayList<Alien>();
		
		for(int i = 0; i <= 4; i++) {
			Alien newAlien = new Alien();
			newAlien.setPicture(GalagaAlien);
			alienList.add(newAlien);
		}
		
		//add ship
		ship = new Ship();
		ship.setPicture(GalagaShip);
		ship.x = 200;
		ship.y = 400;
		
		//initialize bullet
		bullet = new Bullet();
		
		//key listener
		addKeyListener(this);
		setFocusable(true);
	}
	
	public void paintComponent(Graphics g)
	{
		//clear the screen
		g.drawImage(space.getImage(),0,0,getWidth(),getHeight(),this);
		
		//update the game
		for( Alien go : alienList ){
			go.draw(g,this);
			listlength++;
			go.bullet.draw(g, this);
			if(!go.alive)
				dead++;
		}
		ship.draw(g, this);
		bullet.draw(g, this);

		if(ship.alive == false){
			g.setFont(new Font("sansserif", Font.BOLD, 32));
			g.drawString("GAME OVER!!!!", 150, 300);
		}
		if(dead == listlength){
			g.setFont(new Font("sansserif", Font.BOLD, 32));
			g.drawString("GAME WON!!!!", 150, 300);
		}
		else{
			listlength = 0;
			dead = 0;
		}
		
	}
	
	public void update(){
		//update all objects in game
		for( Alien go : alienList ){
			go.update();
			if(bullet.intersects(go)){
				go.kill();
			}
			
			if(go.intersects(ship) && !go.attribute.equalsIgnoreCase("ship")){
				ship.kill();
			}
			
			if(go.bullet.intersects(ship)){
				ship.kill();
			}
		}
		
		bullet.update();
		ship.update();
		
		repaint();
	}

	public void keyPressed(KeyEvent k){
		char c = k.getKeyChar();
		
		if( k.getKeyCode() == KeyEvent.VK_RIGHT )
			ship.dx = 5;
		if( k.getKeyCode() == KeyEvent.VK_LEFT )
			ship.dx = -5;
		if(c == ' '){
			bullet.x = ship.x;
			bullet.y = ship.y - 30;
		}
	}

	public void keyReleased(KeyEvent k){
		if( k.getKeyCode() == KeyEvent.VK_LEFT ||  k.getKeyCode() == KeyEvent.VK_RIGHT )
			ship.dx = 0;
	}

	public void keyTyped(KeyEvent k){
	}

	public JPanel getPanel() {
		return panel;
	}
}

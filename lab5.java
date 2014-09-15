/**
*Evan Preisler - dex4zero4zone4
*NUI Galway - Space Invaders Clone
*Twitter @dex4zero4zone4
*2014
*/
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.*;

public class lab5 extends JFrame implements Runnable, KeyListener {
	private static final Dimension WindowSize = new Dimension(800,600);
	private BufferStrategy strategy;
	private static final int NUMALIENS = 30;
	private alien [] AliensArray = new alien[NUMALIENS];
	private LinkedList bulletList = new LinkedList();
	private boolean shoot = false;
	private boolean canShoot = true;
	private Image bulletImage;
	private spaceship PlayerShip;
	private boolean gameOver = true;
	private int counter = 0;
	private int score = 0; 
	private int highScore = 0;
	private int level = 1;
	private String scoreBoard, scoreBoard2;
	
	public lab5(){
		ImageIcon iconAlien = new ImageIcon("C:\\/*Enter your own path here*/\\alien.png");
		ImageIcon iconAlien2 = new ImageIcon("C:\\/*Enter your own path here*/\\alien2.png");
		ImageIcon iconShip = new ImageIcon("C:\\/*Enter your own path here*/\\ship.png");
		ImageIcon iconBullet = new ImageIcon("C:\\/*Enter your own path here*/\\bullet.png");
		bulletImage = iconBullet.getImage();
		this.setTitle("SPACEEEEEEEE!!!!!!!");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screensize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int x=0;
		int y=0;
		setBounds(x, y, WindowSize.width, WindowSize.height);
		setVisible(true);
		for (int i=0; i<NUMALIENS; i++){
			AliensArray[i]= new alien(iconAlien.getImage(), iconAlien2.getImage());
		}
		
		PlayerShip = new spaceship(iconShip.getImage());
		createBufferStrategy(2);
		strategy = getBufferStrategy();
		Thread t = new Thread(this);
		t.start();
		addKeyListener(this);
	}
	
	public void startNewWave(){
		bulletList.clear();
		PlayerShip.setPosition(272,525);
		alien.moveRight +=2;
		alien.moveLeft +=2;
		for (int i=0; i<NUMALIENS; i++){
			AliensArray[i].initpos(i);
		}
		counter=0;
	}
	
	public void newGame(){
		bulletList.clear();
		PlayerShip.setPosition(272,525);
		alien.moveRight =3;
		alien.moveLeft =3;
		for (int i=0; i<NUMALIENS; i++){
			AliensArray[i].initpos(i);
		}
		counter=0;
		level=1;
		score=0;
		gameOver = true;
	}
	
	public void run(){
		while(true){
			try{ Thread.sleep(50);
			}catch(InterruptedException e){}
			for(int i=0; i<NUMALIENS; i++){
				AliensArray[i].move();
				if(alien.change2==false) {
					for(int j=0; j<NUMALIENS; j++){
						AliensArray[j].dropLine();
					}
				}
			}
			Iterator interator = bulletList.iterator();
			while(interator.hasNext()){
				playerBullet b = (playerBullet) interator.next();
				b.move();
				for(int i=0; i<NUMALIENS; i++){
					if(((AliensArray[i].x<b.x+5)&&(AliensArray[i].x+50>b.x))&&
							((AliensArray[i].y<b.y)&&(AliensArray[i].y+50>b.y))&&
							(AliensArray[i].isAlive==true)){
						AliensArray[i].isAlive = false;
						if(score>=highScore){
							highScore+=10;
						}
						score+=10;
						counter++;
						interator.remove();
						break;
					}
				}
			}
			for(int i=0; i<NUMALIENS; i++){
				if(((AliensArray[i].x<PlayerShip.x+50)&&(AliensArray[i].x+50>PlayerShip.x))&&
						((AliensArray[i].y<PlayerShip.y)&&(AliensArray[i].y+50>PlayerShip.y))&&
						(AliensArray[i].isAlive==true)){
					newGame();
				}
			}
			if(counter==NUMALIENS) gameOver = true;
			PlayerShip.move();
			this.repaint();
		}
	}
	
	public void keyPressed(KeyEvent e){
		int i=0;
		int keystate = e.getKeyCode();
		switch(keystate){
		case KeyEvent.VK_LEFT:
			i=-1;
			PlayerShip.setXSpeed(i);
			break;
		case KeyEvent.VK_RIGHT:
			i=1;
			PlayerShip.setXSpeed(i);
			break;
		case KeyEvent.VK_SPACE:
			shoot = true;
			if(canShoot){
				if(shoot==true) shootBullet();
			}
			canShoot=false;
			break;
		case KeyEvent.VK_ENTER:
			level++;
			gameOver = false;
			startNewWave();
			break;
		}	
	}
	
	public void keyReleased(KeyEvent e){
		int i=0;
		PlayerShip.setXSpeed(i);
		shoot = false;
		canShoot=true;
	}
	
	public void keyTyped(KeyEvent e){
		
	}         
	
	public void shootBullet(){
		playerBullet b = new playerBullet(bulletImage);
		b.setPosition(PlayerShip.x+50/2, PlayerShip.y);
		bulletList.add(b);
	}
	
	public void paint(Graphics g){
		if(!gameOver){
		g = strategy.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 800, 600);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Times", Font.BOLD, 16));
		scoreBoard = ("Your Score: "+score);
		g.drawString(scoreBoard, 15, 50);
		g.setFont(new Font("Times", Font.BOLD, 16));
		scoreBoard2 = ("High Score: "+highScore);
		g.drawString(scoreBoard2, 650, 50);
		for(int i=0; i<NUMALIENS; i++){
			if(AliensArray[i].isAlive==true)AliensArray[i].paint(g);
		}
		Iterator interator = bulletList.iterator();
		while(interator.hasNext()){
			playerBullet b = (playerBullet) interator.next();
			b.paint(g);
		}
		PlayerShip.paint(g);
		g.dispose();
		strategy.show();
		}else{
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 800, 600);
			g.setColor(Color.WHITE);
			g.setFont(new Font("Times", Font.BOLD, 45));
			g.drawString("Level: "+level, 300, 200);
			g.setFont(new Font("Times", Font.BOLD, 32));
			g.drawString("Score:"+score, 200, 250);
			g.drawString("High Score:"+highScore, 400, 250);
			g.drawString("Push Enter", 300, 300);
		}
	}
	
	public static void main(String [] args){
		lab5 a = new lab5();
	}
}
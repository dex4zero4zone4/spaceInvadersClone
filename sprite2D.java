/**
*Evan Preisler - dex4zero4zone4
*NUI Galway - Space Invaders Clone
*Twitter @dex4zero4zone4
*2014
*/
import java.awt.Graphics;
import java.awt.Image;

public abstract class sprite2D {
	protected double x,y;
	protected static double moveRight=3, moveLeft=3;
	protected double xSpeed=0;
	protected Image myImage, myImage2;
	int framesDrawn=0;
	
	public sprite2D(Image i, Image i2){
		myImage = i;
		myImage2 = i2;
		x = 15;
		y = 35;
	}

	public void setPosition(double xx, double yy){
		x = xx;
		y = yy;
	}

	public void setXSpeed(double dx){
		if(dx==1)xSpeed+=5;
		if(dx==-1)xSpeed-=5;
		if(dx==0)xSpeed=0;
	}
	
	public void paint(Graphics g){
		framesDrawn++;
		if(framesDrawn%75<50) g.drawImage(myImage, (int)x, (int)y, null);
		else g.drawImage(myImage2, (int)x, (int)y, null);
	}
}
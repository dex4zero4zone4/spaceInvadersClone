/**
*Evan Preisler - dex4zero4zone4
*NUI Galway - Space Invaders Clone
*Twitter @dex4zero4zone4
*2014
*/
import java.awt.Image;

public class alien extends sprite2D {
		static boolean change = true;
		static boolean change2 = true;
		public boolean isAlive = false;
		protected int counter = 0;
		
	public alien(Image im, Image im2){
		super(im, im2);
	}
	
	public void initpos(int num)
	{
		if(x==0)x+=15;
		x = (num%5)*60;
		x+=15;
		y = (num/5)*60;
		y+=50;
		framesDrawn=0;
		isAlive = true;
	}
	
	public void move(){
		if(x<15) x=15;
		else if (x>800) x=800;
		if(y<35) y=35;
		if(change==true) x+=moveRight;
		if(x>755)change=false;
		if(x>755)change2=false;
		if(change==false) reverseDirection();
	}

	public void reverseDirection(){
		if(change==false)x-=moveLeft;
		if(x<15)change=true;
		if(x<15)change2=false;
	}
	
	public void dropLine (){
		y+=1;
		counter++;
		if(counter==30){change2=true; counter=0;} 
	}
}

/**
*Evan Preisler - dex4zero4zone4
*NUI Galway - Space Invaders Clone
*Twitter @dex4zero4zone4
*2014
*/
import java.awt.Image;

public class spaceship extends sprite2D {
	
	public spaceship(Image i){
		super(i, i);
	}
	
	public void move(){
		x+=xSpeed;
		if(x<15) x=15;
		else if(x>785)x=735;
	}
}

/**
*Evan Preisler - dex4zero4zone4
*NUI Galway - Space Invaders Clone
*Twitter @dex4zero4zone4
*2014
*/
import java.awt.Image;
public class playerBullet extends sprite2D{
	public playerBullet(Image i){
		super(i, i);
		
	}
	
	public void move(){
		y-=10;
	}
}

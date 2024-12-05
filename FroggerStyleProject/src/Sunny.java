import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Sunny{
	private Image forward, backward, left, right; 	
	private AffineTransform tx;
	
	int dir = 0; 					//0-forward, 1-backward, 2-left, 3-right
	int width, height;
	int x, y;						//position of the object
	int vx, vy;						//movement variables
	double scaleWidth = 2.0;		//change to scale image
	double scaleHeight = 2.0; 		//change to scale image

	public Sunny() {
		forward 	= getImage("/imgs/"+"sunny.png"); //load the image for Tree

	

		//alter these
		width = 41;
		height = 20;
		x = 600/2-width/2;
		y = 64-17;
		vx = 0;
		vy = 0;
		
		tx = AffineTransform.getTranslateInstance(0, 0);
		
		init(x, y); 				//initialize the location of the image
									//use your variables
		
	}
	
	public Sunny(int x, int y, boolean im) {
		
		this();
		
		this.x = x;
		this.y = y;
		if (im) {
			forward 	= getImage("/imgs/"+"omori-pixilart.png"); //load the image for Tree
		}
		
	}

	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		
		x+=vx;
		y+=vy;	
		
		init(x,y);
		
		
		g2.drawImage(forward, tx, null);
		
	}
	
	public boolean collided(Omori character) {
		
		Rectangle main = new Rectangle(
				character.x + character.woff+17,
				character.y + character.hoff,
				character.width,
				character.height
				);
		
		Rectangle object = new Rectangle(x+character.woff, y+character.hoff, width, height);
		
		
		return main.intersects(object);
		
	}
	
	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(scaleWidth, scaleHeight);
	}

	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Sunny.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

}

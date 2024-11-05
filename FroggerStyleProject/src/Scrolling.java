import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Scrolling{
	private Image forward, backward, left, right; 	
	private AffineTransform tx;
	
	int dir = 0; 					//0-forward, 1-backward, 2-left, 3-right
	int width, height;
	int x, y;						//position of the object
	int vx, vy;						//movement variables
	double scaleWidth = 2.0;		//change to scale image
	double scaleHeight = 2.0; 		//change to scale image
	int type;

	public Scrolling() {
		/*forward 	= getImage("/imgs/"+"omori-pixilart.png"); //load the image for Tree
		backward 	= getImage("/imgs/"+"backward.png"); //load the image for Tree
		left 		= getImage("/imgs/"+"left.png"); //load the image for Tree
		right 		= getImage("/imgs/"+"right.png"); //load the image for Tree*/

		//alter these
		width = 100;
		height = 100;
		x = -width;
		y = 300;
		vx = 5;
		vy = 0;
		type = 0;
		
		tx = AffineTransform.getTranslateInstance(0, 0);
		
		init(x, y); 				//initialize the location of the image
									//use your variables
		
	}
	
	public Scrolling(int x, int y, int type) {
		this();
		
		this.x = x;
		this.y = y;
		this.type = type;
		
	}

	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		
		x+=vx;
		y+=vy;	
		
		if (x > 640) {
			x = -width;
			type = (int) (Math.random()*2);
		}
		
		init(x,y);
		
		if (type == 0) {
			forward = getImage("/imgs/"+"blank.png");
		} else {
			forward 	= getImage("/imgs/"+"omori-pixilart.png"); //load the image for Tree
		}
		
		g2.drawImage(forward, tx, null);

	}
	
	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(scaleWidth, scaleHeight);
	}

	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Scrolling.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

}

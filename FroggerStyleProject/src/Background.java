import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Background{
	private Image forward, backward, left, right; 	
	private AffineTransform tx;
	
	int dir = 0; 					//0-forward, 1-backward, 2-left, 3-right
	int width, height;
	int x, y;						//position of the object
	int vx, vy;						//movement variables
	double scaleWidth = 1.0;		//change to scale image
	double scaleHeight = 1.0; 		//change to scale image

	public Background() {
		forward 	= getImage("/imgs/"+"forwardFile.png"); //load the image for Tree
		backward 	= getImage("/imgs/"+"backward.png"); //load the image for Tree
		left 		= getImage("/imgs/"+"left.png"); //load the image for Tree
		right 		= getImage("/imgs/"+"right.png"); //load the image for Tree

		//alter these
		width = 0;
		height = 0;
		x = 0;
		y = 0;
		vx = 0;
		vy = 0;
		
		tx = AffineTransform.getTranslateInstance(0, 0);
		
		init(x, y); 				//initialize the location of the image
									//use your variables
		
	}

	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		
		x+=vx;
		y+=vy;	
		
		init(x,y);
		
		Image grass = getImage("/imgs/grass.png");
		Image grass1 = getImage("/imgs/grass1.png");
		Image grass2 = getImage("/imgs/ground3.png");
		Image end = getImage("/imgs/blanket.png");
		
		g.drawImage(grass1, 0, 0, 640, 64, null);
		g.drawImage(grass1, 0, 64, 640, 64, null);
		g.drawImage(grass, 0, 64*6, 640, 64, null);

	


		
		
		Image southroad = getImage("/imgs/southroad.png");
		
		g.drawImage(southroad, 0, 64*3, 640, 78, null);

		
		Image northroad = getImage("/imgs/northroad.png");
		
		g.drawImage(northroad, 0, 64*2-15, 640, 78, null);
		
		g.drawImage(grass2, 0, 64*4, 640, 64, null);
		g.drawImage(grass2, 0, 64*4-2, 640, 64, null);
		
		g.drawImage(grass, 0, 64*6, 640, 64, null);
		g.drawImage(grass1, 0, 64*9, 640, 64, null);
		
		g.drawImage(southroad, 0, 64*11, 640, 78, null);
		g.drawImage(northroad, 0, 64*10-15, 640, 78, null);
		
		g.drawImage(grass2, 0, 64*12, 640, 64, null);
		g.drawImage(grass2, 0, 64*12-2, 640, 64, null);
		
		g.drawImage(grass, 0, 64*14, 640, 64, null);
		

		g.drawImage(end, 0, 64*15, 640, 64, null);


	}
	
	public void drawEnd(Graphics g) {
		Image endScreen = getImage("/imgs/omori-pixilart.png");
		g.drawImage(endScreen, -(1062-640)/2, 0, 1062, 1062, null);
	}
	
	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(scaleWidth, scaleHeight);
	}

	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Background.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

}

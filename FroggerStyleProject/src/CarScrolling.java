import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class CarScrolling{
	private Image forward, car1, car2, car3, car4, car5, blank;	
	private AffineTransform tx;
	
	int dir = 0; 					//0-forward, 1-backward, 2-left, 3-right
	int width, height;
	int x, y;						//position of the object
	int vx, vy;						//movement variables
	double scaleWidth = 2.0;		//change to scale image
	double scaleHeight = 2.0; 		//change to scale image
	int type;

	public CarScrolling() {
		/*forward 	= getImage("/imgs/"+"omori-pixilart.png"); //load the image for Tree
		backward 	= getImage("/imgs/"+"backward.png"); //load the image for Tree
		left 		= getImage("/imgs/"+"left.png"); //load the image for Tree
		right 		= getImage("/imgs/"+"right.png"); //load the image for Tree*/
		
		car1 	= getImage("/imgs/"+"car1.png");
		car2 	= getImage("/imgs/"+"car2.png");
		car3 	= getImage("/imgs/"+"car3.png");
		car4 	= getImage("/imgs/"+"car4.png");
		car5 	= getImage("/imgs/"+"car5.png");
		blank = getImage("/imgs/"+"blank.png");


		//alter these
		width = 72*2;
		height = 64;
		x = -width;
		y = 300;
		vx = 4;
		vy = 0;
		type = 1;
		
		tx = AffineTransform.getTranslateInstance(0, 0);
		
		init(x, y); 				//initialize the location of the image
									//use your variables
		
	}
	
	public CarScrolling(int x, int y, int type, boolean dir) {
		this();
		
		this.x = x;
		this.y = y;
		this.type = type;
		if (dir) {
			vx = 8;
		} else {
			vx = 4;
		}
	}

	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		
		x+=vx;
		y+=vy;	
		
		if (x > 640) {
			x = -width-76;
			type = (int) (Math.random()*2);
			if (type == 1) {
				type = (int) (Math.random()*4+1);
			}
		}
		
		init(x,y);
		
		
		
		switch(type) {
		case 0:
			forward = blank;
			break;
		case 1:
			forward = car1;
			break;
		case 2:
			forward = car2;
			break;
		case 3:
			forward = car3;
			break;
		case 4:
			forward = car4;
			break;
		case 5:
			forward = car5;
		}
		
		g2.drawImage(forward, tx, null);

		if (Frame.debugging) {
			g.drawRect(x, y+17*2, width, height);
			g.drawString(Integer.toString(type), x+32, y+32);
		}
	}
	
	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(scaleWidth, scaleHeight);
	}

	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = CarScrolling.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}
	
	public boolean collided(Omori character) {
		
		Rectangle main = new Rectangle(
				character.x + character.woff+17,
				character.y + character.hoff+character.height-character.feeth,
				character.width-17*2,
				character.feeth
				);
		
		Rectangle object = new Rectangle(x, y+17*2, width, height);
		
		
		return main.intersects(object);
		
	}

}

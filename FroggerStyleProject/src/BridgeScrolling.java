import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class BridgeScrolling{
	private Image forward, left, right, middle, blank; 	
	private AffineTransform tx;
	
	int dir; 					//0-forward, 1-backward, 2-left, 3-right
	int width, height;
	int x, y;						//position of the object
	int vx, vy;						//movement variables
	double scaleWidth = 2.0;		//change to scale image
	double scaleHeight = 2.0; 		//change to scale image
	int type;

	public BridgeScrolling() {
		/*forward 	= getImage("/imgs/"+"omori-pixilart.png"); //load the image for Tree
		backward 	= getImage("/imgs/"+"backward.png"); //load the image for Tree
		left 		= getImage("/imgs/"+"left.png"); //load the image for Tree
		right 		= getImage("/imgs/"+"right.png"); //load the image for Tree*/
		
		
		left = getImage("/imgs/leftboard.png");
		right = getImage("imgs/rightboard.png");
		middle = getImage("imgs/middleboard.png");
		blank = getImage("/imgs/"+"blank.png");
		
		

		//alter these
		width = 64;
		height = 64;
		x = -width;
		y = 300;
		vx = Frame.speed;
		vy = 0;
		type = 0;
		
		
		tx = AffineTransform.getTranslateInstance(0, 0);
		
		init(x, y); 				//initialize the location of the image
									//use your variables
		
	}
	
	public BridgeScrolling(int x, int y, int dir) {
		this();
		
		this.x = x;
		this.y = y;
		this.dir = dir;
		if (dir == 0) {
			vx = Frame.speed;
		} else {
			vx = -Frame.speed;
		}
		
		
		
	}

	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		
		x+=vx;
		y+=vy;	
		
		init(x,y);
		
		switch(type) {
		case 0:
			forward = blank;
			break;
		case 1:
			forward = right;
			break;
		case 2:
			forward = middle;
			break;
		case 3:
			forward = left;
			break;
		case 4:
			forward = blank;
			break;
		}
		
		g2.drawImage(forward, tx, null);
		if (Frame.debugging) {
			g.drawRect(x, y, width, height);
			g.drawString(Integer.toString(type), x+32, y+32);
		}

	}
	
	//collision
	public boolean collided(Omori character) {
		
		Rectangle main = new Rectangle(
				character.x + character.woff,
				character.y + character.hoff+character.height-character.feeth,
				character.width,
				character.feeth
				);
		
		Rectangle object = new Rectangle(x, y, width, height);
		
		
		return main.intersects(object);
		
	}
	
	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(scaleWidth, scaleHeight);
	}

	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = BridgeScrolling.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

}

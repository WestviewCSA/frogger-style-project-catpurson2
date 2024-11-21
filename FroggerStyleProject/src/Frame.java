import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Frame extends JPanel implements ActionListener, MouseListener, KeyListener {
	
	// to debug
	public static boolean debugging = false;
	
	//Timer related variables
	int waveTimer = 5; //each wave of enemies is 20s
	long ellapseTime = 0;
	Font timeFont = new Font("Courier", Font.BOLD, 70);
	int level = 0;
	
	
	Font myFont = new Font("Courier", Font.BOLD, 40);
	SimpleAudioPlayer backgroundMusic = new SimpleAudioPlayer("scifi.wav", false);
//	Music soundBang = new Music("bang.wav", false);
//	Music soundHaha = new Music("haha.wav", false);
	
	//frame width/height
	int width = 640;
	int height = 1062;
	Omori omori = new Omori();
	WaterScrolling[] water1 = new WaterScrolling[2];
	WaterScrolling[] water2 = new WaterScrolling[2];
	WaterScrolling[] water3 = new WaterScrolling[2];
	WaterScrolling[] water4 = new WaterScrolling[2];
	BridgeScrolling[] bridge1 = new BridgeScrolling[11]; 
	BridgeScrolling[] bridge2 = new BridgeScrolling[11]; 
	BridgeScrolling[] bridge3 = new BridgeScrolling[11];
	BridgeScrolling[] bridge4 = new BridgeScrolling[11];
	CarScrolling[] car1 = new CarScrolling[6];
	CarScrolling[] car2 = new CarScrolling[6];
	CarScrolling[] car3 = new CarScrolling[6];
	CarScrolling[] car4 = new CarScrolling[6];
	ArrayList<Sunny> sunny = new ArrayList<Sunny>();
	Background back = new Background();
	
	
	
	
	
	{for (int i = 0; i < 2; i++) {
		System.out.println("gay");
	}}

	public void paint(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(Color.green);
		
		back.paint(g);
		
		for (CarScrolling i : car1) {
			i.paint(g);
		}
		
		for (CarScrolling i : car2) {
			i.paint(g);
		}
		
		for (CarScrolling i : car3) {
			i.paint(g);
		}
		
		for (CarScrolling i : car4) {
			i.paint(g);
		}

		
		
		for (WaterScrolling i : water1) {
			i.paint(g);
		}
		for (WaterScrolling i : water2) {
			i.paint(g);
		}
		for (WaterScrolling i : water3) {
			i.paint(g);
		}
		for (WaterScrolling i : water4) {
			i.paint(g);
		}
		
		if (debugging) {for (int i = 0; i < 20; i++) {
			g.drawLine(0, (i+1)*64, 640, (i+1)*64);
			g.setColor(Color.red);
			g.drawLine(0, 448, 640, 448);
			g.setColor(Color.green);
		}}
		
		
		for (int i = 0; i < bridge1.length; i++) {
			if (bridge1[i].x >= 640) {
				BridgeScrolling j;
				BridgeScrolling k;
				if (i <= 1) {
					k = bridge1[10];
				} else {
					k = bridge1[i-2];
				}
				if (i == 0) {
					j = bridge1[10];
					k = bridge1[9];
				} else {
					j = bridge1[i-1];
				}
				
				switch (j.type) { 
				case 0:
					bridge1[i].type = (int) (Math.random()*2);
					break;
				case 1: 
					bridge1[i].type = (int) (Math.random()*1.75)+2;
					break;
				case 2:
					if (k.type == 2) {
						bridge1[i].type = 3;
					} else {
						bridge1[i].type = (int) (Math.random()*1.3+2.25);
					}
					break;
				case 3:
					bridge1[i].type = (int) (Math.random()*1.05);
					break;
				}
				
				if (bridge1[i].dir == 0) {
					bridge1[i].x = -bridge1[i].width;
				} else {
					bridge1[i].x = width + bridge1[i].width;
				}

			}
		}
		
		for (int i = 0; i < bridge2.length; i++) {
			if (bridge2[i].x <= -bridge2[i].width) {
				BridgeScrolling j;
				BridgeScrolling k;
				if (i >= 9) {
					k = bridge2[0];
				} else {
					k = bridge2[i+2];
				}
				if (i == 10) {
					j = bridge2[0];
					k = bridge2[1];
				} else {
					j = bridge2[i+1];
				}
				
				switch (j.type) { 
				case 0:
					bridge2[i].type = 3 + (int) (Math.random()*2);
					break;
				case 3: 
					bridge2[i].type = (int) (Math.random()*1.75+1.25);
					break;
				case 2:
					if (k.type == 2) {
						bridge2[i].type = 1;
					} else {
						bridge2[i].type = (int) (Math.random()*.8+1.5);
					}
					break;
				case 1:
					bridge2[i].type = (int) (Math.random()*1.05+3.95);
					break;
				case 4:
					bridge2[i].type = 3 + (int) (Math.random()*2);
					break;
				}
				
				
				if (bridge2[i].dir == 0) {
					bridge2[i].x = -bridge2[i].width;
				} else {
					bridge2[i].x = width;
				}
			}
		}
		
		bridge3 = bridgeRandom(bridge3);
		bridge4 = bridgeRandom(bridge4);
		
		for (BridgeScrolling i : bridge1) {
			i.paint(g);
		}
		
		for (BridgeScrolling i : bridge2) {
			i.paint(g);
		}
		
		for (BridgeScrolling i : bridge3) {
			i.paint(g);
		}
		
		for (BridgeScrolling i : bridge4) {
			i.paint(g);
		}
		
		int die = 0;
		
		for (BridgeScrolling i : bridge1) {
			if (i.collided(omori)) {
				if (i.type == 0) {
					die = 1;
				} else if(die != 1) {
					die = 2;
				}
			}
		}
		
		for (BridgeScrolling i : bridge2) {
			if (i.collided(omori)) {
				if (i.type == 0 || i.type == 4) {
					die = 1;
					break;
				} else if(die != 1) {
					die = 3;
				}
			}
		}
		
		for (BridgeScrolling i : bridge3) {
			if (i.collided(omori)) {
				if (i.type == 0) {
					die = 1;
				} else if(die != 1) {
					die = 2;
				}
			}
		}
		
		for (BridgeScrolling i : bridge4) {
			if (i.collided(omori)) {
				if (i.type == 0) {
					die = 1;
				} else if(die != 1) {
					die = 2;
				}
			}
		}
		
		
		switch(die) {
		case 0:
			omori.vxa = 0;
			break;
		case 1: 
			omori.reset();
			break;
		case 2:
			omori.vxa = 4;
			break;
		case 3:
			omori.vxa = -4;
			break;
		}
		
		for (CarScrolling i : car1) {
			if(i.collided(omori) && i.type != 0) {
				omori.reset();
			}
		}
		
		for (CarScrolling i : car2) {
			if(i.collided(omori) && i.type != 0) {
				omori.reset();
			}
		}
		
		for (CarScrolling i : car3) {
			if(i.collided(omori) && i.type != 0) {
				omori.reset();
			}
		}
		
		for (CarScrolling i : car4) {
			if(i.collided(omori) && i.type != 0) {
				omori.reset();
			}
		}
		
		for(Sunny i : sunny) {
			i.paint(g);
		}
		
		omori.paint(g);
		
	

	}
	
	public static void main(String[] arg) {
		Frame f = new Frame();
	}
	
	public Frame() {
		JFrame f = new JFrame("Duck Hunt");
		f.setSize(new Dimension(width, height));
		f.setBackground(Color.white);
		f.add(this);
		f.setResizable(false);
 		f.addMouseListener(this);
		f.addKeyListener(this);
	
		//backgroundMusic.play();
		
		//set up array
		
		for (int i = 0; i < water1.length; i++) {
			water1[i] = new WaterScrolling(640-640*(i+1), 448);
		}
		for (int i = 0; i < water2.length; i++) {
			water2[i] = new WaterScrolling(640-640*(i+1), 448+64);
		}
		for (int i = 0; i < water3.length; i++) {
			water3[i] = new WaterScrolling(640-640*(i+1), 64*5);
		}
		for (int i = 0; i < water4.length; i++) {
			water4[i] = new WaterScrolling(640-640*(i+1), 64*13);
		}
		for(int i = 0; i < bridge1.length; i++) {
			bridge1[i] = new BridgeScrolling(640-64*(i+1), 448, 0);
		}
		for(int i = 0; i < bridge2.length; i++) {
			bridge2[i] = new BridgeScrolling(640-64*(i+1), 448+64, 1);
		}
		for(int i = 0; i < bridge3.length; i++) {
			bridge3[i] = new BridgeScrolling(640-64*(i+1), 64*5, 0);
		}
		for(int i = 0; i < bridge4.length; i++) {
			bridge4[i] = new BridgeScrolling(640-64*(i+1), 64*13, 0);
		}
		for(int i = 0; i < car1.length; i++) {
			car1[i] = new CarScrolling(640-72*2*(i+1), 64*2-17*2, (int) (Math.random() * 5), false);
		}
		for(int i = 0; i < car2.length; i++) {
			car2[i] = new CarScrolling(640-72*2*(i+1), 64*2-17*2+64, (int) (Math.random() * 5), true);
		}
		for(int i = 0; i < car3.length; i++) {
			car3[i] = new CarScrolling(640-72*2*(i+1), 64*10-17*2, (int) (Math.random() * 5), true);
		}
		for(int i = 0; i < car4.length; i++) {
			car4[i] = new CarScrolling(640-72*2*(i+1), 64*11-17*2, (int) (Math.random() * 5), false);
		}
		
		while(sunny.size() < 5) {
			sunny.add(new Sunny(sunny.size()*64*2+32, 64*15));
		}
		

	
		
		//the cursor image must be outside of the src folder
		//you will need to import a couple of classes to make it fully 
		//functional! use eclipse quick-fixes
		setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
				new ImageIcon("torch.png").getImage(),
				new Point(0,0),"custom cursor"));	
		
		
		Timer t = new Timer(16, this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
	
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent m) {
		
	
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		//System.out.println(arg0.getKeyCode());
		
		switch(arg0.getKeyCode()) {
		case 38:
			omori.vyk = -4;
			break;
		case 40:
			omori.vyk = 4;
			break;
		case 37:
			omori.vxk = -4;
			break;
		case 39:
			omori.vxk = 4;
			break;
		}
		
		
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getKeyCode()) {
		case 38:
			omori.vyk = 0;
		case 40:
			omori.vyk = 0;
		case 37:
			omori.vxk = 0;
		case 39:
			omori.vxk = 0;
		}
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public BridgeScrolling[] bridgeRandom(BridgeScrolling[] bridge1) {
		
		
		for (int i = 0; i < bridge1.length; i++) {
			if (bridge1[i].x >= 640) {
				BridgeScrolling j;
				BridgeScrolling k;
				if (i <= 1) {
					k = bridge1[10];
				} else {
					k = bridge1[i-2];
				}
				if (i == 0) {
					j = bridge1[10];
					k = bridge1[9];
				} else {
					j = bridge1[i-1];
				}
				
				switch (j.type) { 
				case 0:
					bridge1[i].type = (int) (Math.random()*2);
					break;
				case 1: 
					bridge1[i].type = (int) (Math.random()*1.75)+2;
					break;
				case 2:
					if (k.type == 2) {
						bridge1[i].type = 3;
					} else {
						bridge1[i].type = (int) (Math.random()*1.3+2.25);
					}
					break;
				case 3:
					bridge1[i].type = (int) (Math.random()*1.05);
					break;
				}
				
				if (bridge1[i].dir == 0) {
					bridge1[i].x = -bridge1[i].width;
				} else {
					bridge1[i].x = width + bridge1[i].width;
				}

			}
		}
		
		return bridge1;
	}

}

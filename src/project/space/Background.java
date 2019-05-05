package project.space;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Background extends JPanel {
	JTextArea area;
	public static final int WIDTH = 1600;
	public static final int HEIGHT = 900;
	Atom circle;
	ObjectManager objectManager;
	Point point;
	Point pointX;
	Point pointY;
	Double degree;
	boolean allTickFlag = true;
	Graphics graphic;
	int ballSize;
	double ballSpeed;
	int totalBall = 3000;
	boolean paintFlag = true;

	public Background() {
		degree = new Double(3.3);
		objectManager = new ObjectManager();
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setBackground(Color.GRAY);

//		Color color1 = new Color(231, 80, 121); // -1617799
//		Color color2 = new Color(50, 32, 208);  // -13492016
//		Color color3 = new Color(255, 237, 77); // -4787
//		Color color4 = new Color(160, 252, 252); // -6226692
//		Color color5 = new Color(157, 221, 255); // -6431233

		createCircle(new Color(231, 80, 121));// ���� ��
		createCircle(new Color(50, 32, 208));// ��� ��
		createCircle(new Color(255, 237, 77));// ��� ��
		createCircle(new Color(160, 252, 252));// Ǫ�� ��
		createCircle(new Color(157, 221, 255));

	}

	public void createCircle(Color color) { // �� ����
		// �ӵ��� Į�� �־���� �ҵ� �̹����� �ʿ����
		ballSize = 2;
		ballSpeed = 1.5;
		for (int i = 0; i < totalBall; i++) {
			int flagA = (int) (Math.random() * 2); // vel -���� ���� �÷���
			int flagB = (int) (Math.random() * 2);

			double velX = (Math.random() * ballSpeed) + 0.001; // velX x�ӵ���
			double velY = (Math.random() * ballSpeed) + 0.001; // velY y�ӵ���

			// ������� x= 700~900���� y= 400~600����
			double startX = (Math.random() * 200) + 680; // ó������ x��ġ ��ġ�� �ٲ������...�ȸ���
			double startY = (Math.random() * 200) + 260; // ó�� ���� y��ġ

			if (flagA == 0) {
				if (flagB == 0) {
					circle = new Atom(i, startX, startY, ballSize, ballSize, ballSize, ballSize, velX, velY,
							color);
					objectManager.addObject(circle);
				} else {
					circle = new Atom(i, startX, startY, ballSize, ballSize, ballSize, ballSize, velX, -velY,
							color);
					objectManager.addObject(circle);
				}
			} else {
				if (flagB == 0) {
					circle = new Atom(i, startX, startY, ballSize, ballSize, ballSize, ballSize, -velX, velY,
							color);
					objectManager.addObject(circle);
				} else {
					circle = new Atom(i, startX, startY, ballSize, ballSize, ballSize, ballSize, -velX, -velY,
							color);
					objectManager.addObject(circle);
				}
			}

		}

	}

	public void allTick() {
		for (int i = 0; i < objectManager.objectList.size(); i++) {
			Atom obj = objectManager.objectList.get(i);

			obj.tick(); // randomMove���� �ӵ����� ��� �����ְų� ���ִ� ������ �޼���
			obj.randomMove(); // x,y �ӵ����� ��� �ٲ���

		}
	}

	// �Ѱ����� ��ġ�� �޼��� ȣ������!
	public void gather(Point point) {
		for (int i = 0; i < objectManager.objectList.size(); i++) {
			Atom obj = objectManager.objectList.get(i);
			obj.gather(point);
		}
	}

	// ȸ���ϴ� �޼��� ȣ��!
	public void rolling(Point point, int count) {
		if (count == 1) {
			for (int i = 0; i < objectManager.objectList.size(); i++) {
				Atom obj = objectManager.objectList.get(i);
				obj.galaxyLeft(point);
			}
		} else if (count == 2) {
			for (int i = 0; i < objectManager.objectList.size(); i++) {
				Atom obj = objectManager.objectList.get(i);
				obj.galaxyRight(point);
			}
		} else if (count == 3) {
			for (int i = 0; i < objectManager.objectList.size(); i++) {
				Atom obj = objectManager.objectList.get(i);
				obj.galaxyUp(point);
			}
		} else if (count == 4) {
			for (int i = 0; i < objectManager.objectList.size(); i++) {
				Atom obj = objectManager.objectList.get(i);
				obj.galaxyDown(point);
			}
		} else if (count == 5) {
			for (int i = 0; i < objectManager.objectList.size(); i++) {
				Atom obj = objectManager.objectList.get(i);
				obj.galaxyGather(point);
			}
		} else if (count == 6) {
			for (int i = 0; i < objectManager.objectList.size(); i++) {
				Atom obj = objectManager.objectList.get(i);
				obj.galaxyGather2(point);
			}
		} else if (count == 7) {
			for (int i = 0; i < objectManager.objectList.size(); i++) {
				Atom obj = objectManager.objectList.get(i);
				obj.colorRotate(point);
			}
		} else if (count == 8) {
			for (int i = 0; i < objectManager.objectList.size(); i++) {
				Atom obj = objectManager.objectList.get(i);
				obj.colorRotate2(point);
			}
		} else if (count == 9) {
			for (int i = 0; i < objectManager.objectList.size(); i++) {
				Atom obj = objectManager.objectList.get(i);
				obj.galaxyRing(point);
			}
		} else if (count == 10) {
			for (int i = 0; i < objectManager.objectList.size(); i++) {
				Atom obj = objectManager.objectList.get(i);
				obj.vibration(point);
			}
		}else if (count == 11) {
			for (int i = 0; i < objectManager.objectList.size(); i++) {
				Atom obj = objectManager.objectList.get(i);
				obj.atom(point);
			}
		}else if (count == 12) {
			for (int i = 0; i < objectManager.objectList.size(); i++) {
				Atom obj = objectManager.objectList.get(i);
				obj.tornado(point);
			}
		}
	}

	public void paint(Graphics g) {
		// �÷��׸� �ָ� �ٸ� �׸� �׸��� ���� �ҵ�
		graphic = g;
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		for (int i = 0; i < objectManager.objectList.size(); i++) {
			Atom obj = objectManager.objectList.get(i);
			try {
				obj.render(g); // ���� �����̴� ���� ��� �׷��ִ� �޼���
			} catch (Exception e) {
				// System.out.println(e); //���� ��� ��
			}
		}
	}

	static public Point getRotatePoint(Point point, Point pivotPoint, double degrees) {
		double dSetDegree = Math.toRadians(degrees);
		double cosq = Math.cos(dSetDegree);
		double sinq = Math.sin(dSetDegree);
		double sx = point.x - pivotPoint.x;
		double sy = point.y - pivotPoint.y;
		double rx = (sx * cosq - sy * sinq) + pivotPoint.x; // ��� ��ǥ x
		double ry = (sx * sinq + sy * cosq) + pivotPoint.y; // ��� ��ǥ y

		return new Point((int) rx, (int) ry);
	}

	public void rotateObject() {
		for (int i = 0; i < objectManager.objectList.size(); i++) {
			Atom obj = objectManager.objectList.get(i);
			// ���� �ð����� tick vel�� �ٲ���� �ҵ�
			obj.onePointRotation(graphic);

		}
	}

}

package project.space;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;

public class Atom extends RoundRectangle2D.Double {
	double x;
	double y;
	double width;
	double height;
	double arcwidth;
	double archeight;

	double velX;
	double velY;

	Color color;
	boolean flagVelX = true;
	boolean flagVelY = true;
	Point p1, p2, p3, p4;
	int degree = 5;
	int index;

	Point middle = new Point(400, 450);// ��� ȸ���� ���� ����Ʈ
	Point middle2 = new Point(1600, 450);// ��� ȸ���� ���� ����Ʈ
	// ObjectManager objectManager;
	/*
	 * �߷� ���ӵ�,,ź���� �־�� �ҰŰ���.������ ��,,Ÿ������ �����ϴ� ���� �ʿ� AffineTransform(����Ʈ);
	 * 
	 */

	public Atom(int index, double x, double y, double width, double height, double arcwidth, double archeight,
			double velX, double velY, Color color) {
		this.index = index;
		// System.out.println("���° �ε��� : " + index);
		this.x = x;
		this.y = y;
		this.width = width; // ������ ũ�Ⱚ
		this.height = height;
		this.arcwidth = arcwidth; // ������ ���� ���
		this.archeight = archeight;
		this.velX = velX;
		this.velY = velY;
		this.color = color;

	}

	public void tick() {
		// �÷��� ��� �Ұ� ����
		if (flagVelX) {
			x += velX;
		} else {
			x -= velX;
		}
		if (flagVelY) {
			y += velY;
		} else {
			y -= velY;
		}
		if (x > 1600 || x < 0) { // ���� �ε����� �÷��� �ٲ㼭 �ð� ������ ����
			flagVelX = !flagVelX;
		}
		if (y > 900 || y < 0) {
			flagVelY = !flagVelY;
		}
	}

	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g.setColor(color);
		g2.fillRoundRect((int) x, (int) y, (int) width, (int) height, (int) arcwidth, (int) archeight);

	}

	public void render2(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g.setColor(color);
		g2.fillRoundRect((int) x, (int) y, (int) width, (int) height, (int) arcwidth, (int) archeight);
		// g2.rotate(theta, index, velY);

	}

	public void randomMove() {
		int flagA = (int) (Math.random() * 2); // vel -���� ���� �÷��� 1, �ƴϸ� 0
		int flagB = (int) (Math.random() * 2);
		double randomVelX = (Math.random() * 2) + 0.001; // velX
		double randomVelY = (Math.random() * 2) + 0.001; // velY
		// ���� �ϳ� �� �ɾ���� x y ��� �������
		if (x > 0 || x < Background.WIDTH) {
			if (flagA == 0) {
				x += randomVelX;
			} else {
				x += -randomVelX;
			}
		} else {
			if (flagA == 0) {
				x -= randomVelX;
			} else {
				x -= -randomVelX;
			}
		}
		if (y > 0 || y < Background.HEIGHT) {
			if (flagB == 0) {
				y += randomVelY;
			} else {
				y += -randomVelY;
			}
		} else {
			if (flagB == 0) {
				y -= randomVelY;
			} else {
				y -= -randomVelY;
			}
		}
	}

	// ���� ����� ���̴� �޼��� ������ �ҰŰ���.
	public void gather(Point point) { // �������� ��� ��ü�� ���̴� �޼���
		int speed = 5;
		double fastSpeed = 0;
		double slowSpeed = 0;
		double randomVelX = (Math.random() * speed) + 0.001; // velX �ӵ�
		double randomVelY = (Math.random() * speed) + 0.001; // velY �ӵ�
		double destinationX = point.getX();
		double destinationY = point.getY();

//		double halfLeftX= point.getX()-(Math.random() * 50)+0.01;
//		double halfRightX= point.getX()+(Math.random() * 50)+0.01;
//		
//		double halfLeftY= point.getY()-(Math.random() * 50)+0.01;
//		double halfRightY= point.getY()+(Math.random() * 50)+0.01;
		// x���� ������ ���� ������ y�൵ ��������

		if (randomVelX > randomVelY) {
			fastSpeed = randomVelX;
		} else {
			slowSpeed = randomVelY;
		}
		if (x >= y) {
			if (x > destinationX) {
				x -= slowSpeed;
			} else {
				x += slowSpeed;
			}
			if (y > destinationY) {
				y -= fastSpeed;
			} else {
				y += fastSpeed;
			}
		} else {
			if (x > destinationX) {
				x -= fastSpeed;
			} else {
				x += fastSpeed;
			}
			if (y > destinationY) {
				y -= slowSpeed;
			} else {
				y += slowSpeed;
			}
		}
	}

	// ȸ�� �ϴ� �޼��� ��� �ϴ°ž�...��.��. ȸ���� �Ϸ���....,x,y���� ���� �ؾ��ϴµ� tick���� �ٸ��ɷ� ��ü �ؾ��ϴµ�...
	// �ӵ��� �ٲٰ�
	// ������ ���������ϳ�.?

	public void rotate(Point point) {
		double randomVelX = (Math.random() * 2) + 0.001; // velX
		double randomVelY = (Math.random() * 2) + 0.001; // velY
		Point myPoint = new Point((int) this.getX(), (int) this.getY());

		for (int i = 0; i < 360; i++) {
			p1 = getRotatePoint(new Point(200, 100), point, i); // ù �������� ������ point ��ǥ�� �־���� �ϴ°Ű�����.?

			double setX = p1.getX();
			double setY = p1.getX();
			System.out.println("setX : " + setX);
			if (x > setX) {
				x += randomVelX;
			} else {
				x -= randomVelX;
			}
			if (y > setX) {
				y += randomVelY;
			} else {
				y -= randomVelY;
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public void galaxyLeft(Point point) {// ���ϼ� ǥ�� �������� ����
		double dx = point.getX() - x;
		double dy = point.getY() - y;

		if (dx == 0 && dy == 0)
			return;
		double dir = Math.atan2(dy, dx);
		double dir2 = Math.tan(dx);
		double dir3 = Math.tan(dy);
		// System.out.println(dir);
		int speed = 5;

		x = x + (speed * Math.sin(dir));
		y = y - (speed * Math.cos(dir));

	}

	public void galaxyRight(Point point) {// ���ϼ� ǥ�� ���������� ����

		double dx = point.getX() - x;
		double dy = point.getY() - y;
		if (dx == 0 && dy == 0)
			return;
		double dir = Math.atan2(dy, dx);
		int speed = 5;
		y = (y + speed * (Math.cos(dir)) / 5);
		x = x - (speed * Math.sin(dir));
	}

	public void galaxyUp(Point point) {// ��Ȧ�� ǥ��
		double dx = point.getX() - x;
		double dy = point.getY() - y;
		if (dx == 0 && dy == 0)
			return;
		double dir = Math.atan2(dy, dx);

		int speed = 5;
		y = y + (speed * Math.cos(dir - 2));
		x = x - (speed * Math.sin(dir - 2));

	}

	public void galaxyDown(Point point) {// ����� ǥ��
		double dx = point.getX() - x;
		double dy = point.getY() - y;
		if (dx == 0 && dy == 0)
			return;
		double dir = Math.atan2(dy, dx);

		int speed = 5;
		y = y + (speed * Math.cos(dir + 2));
		x = x - (speed * Math.sin(dir + 2));

	}

	public void galaxyGather(Point point) {// �������� ���� ����

		double dx = point.getX() - x;
		double dy = point.getY() - y;
		if (dx == 0 && dy == 0)
			return;
		double dir = Math.atan2(dy, dx);

		int speed = 5;
		y = y + (speed * Math.cos(dir - 2));
		x = x - (speed * Math.cos(dir - 2));

	}

	public void galaxyGather2(Point point) {// �������� ���� ������

		double dx = point.getX() - x;
		double dy = point.getY() - y;
		if (dx == 0 && dy == 0)
			return;
		double dir = Math.atan2(dy, dx);

		int speed = 5;
		y = y - (speed * Math.cos(dir + 2));
		x = x + (speed * Math.cos(dir + 2));

	}

	public void galaxyGather3(Point point) {// �������� ���� ������

		double dx = point.getX() - x;
		double dy = point.getY() - y;
		if (dx == 0 && dy == 0)
			return;
		double dir = Math.atan2(dy, dx);

		int speed = 5;
		x = x - (speed * Math.cos(dir + 2));
		y = y + (speed * Math.cos(dir + 2));

	}

	public void galaxyRing(Point point) {// �������� ���� ������
		if (color.getRGB() == -1617799) {
			double dx = point.getX() - x;
			double dy = point.getY() - y;
			if (dx == 0 && dy == 0)
				return;
			double dir = Math.atan2(dy * -100, dx * -100);

			int speed = 10;
			y = y + (speed * Math.cos(dir - 0.3));
			x = x - (speed * Math.sin(dir - 0.3005));
		}

		if (color.getRGB() == -6431233) {
			double dx = point.getX() - x;
			double dy = point.getY() - y;
			if (dx == 0 && dy == 0)
				return;
			double dir = Math.atan2(dy * -100, dx * -100);

			int speed = 10;
			y = y + (speed * Math.cos(dir - 0.38));
			x = x - (speed * Math.sin(dir - 0.3805));
		}
		if (color.getRGB() == -4787) {
			double dx = point.getX() - x;
			double dy = point.getY() - y;
			if (dx == 0 && dy == 0)
				return;
			double dir = Math.atan2(dy * -100, dx * -100);

			int speed = 10;
			y = y + (speed * Math.cos(dir - 0.45));
			x = x - (speed * Math.sin(dir - 0.43));
		}
		if (color.getRGB() == -6226692) {
			double dx = point.getX() - x;
			double dy = point.getY() - y;
			if (dx == 0 && dy == 0)
				return;
			double dir = Math.atan2(dy * -100, dx * -100);

			int speed = 10;
			y = y + (speed * Math.cos(dir - 0.46));
			x = x - (speed * Math.sin(dir - 0.44));
		}
		if (color.getRGB() == -13492016) {
			double dx = point.getX() - x;
			double dy = point.getY() - y;
			if (dx == 0 && dy == 0)
				return;
			double dir = Math.atan2(dy * -100, dx * -100);

			int speed = 10;
			y = y + (speed * Math.cos(dir - 0.42));
			x = x - (speed * Math.sin(dir - 0.44));
		}
		double dx = point.getX() - x;
		double dy = point.getY() - y;
		if (dx == 0 && dy == 0)
			return;
		double dir = Math.atan2(dy, dx);

		int speed = 5;
		y = y + (speed * Math.cos(dir - 2));
		x = x - (speed * Math.sin(dir - 2));

	}

	public void vibration(Point point) {// ���ϼ� ǥ�� �������� ����
		double dx = point.getX() - x;
		double dy = point.getY() - y;
		if (dx == 0 && dy == 0)
			return;
		double dir = Math.atan2(dy, dx);
		double dir2 = Math.tan(dx);
		double dir3 = Math.tan(dy);
		int speed = 5;
		x = x + (speed * Math.sin(dir3));
		y = y - (speed * Math.cos(dir2) - 1.8);

	}

	public void colorRotate(Point point) {
		double dx = point.getX() - x;
		double dy = point.getY() - y;
		if (dx == 0 && dy == 0)
			return;
		double dir = Math.atan2(dy, dx);
		double dir4 = Math.atan(-0.5);
		int speed = 8;
		y = y + (speed * Math.cos(dir - 0.7844543)); // �ڿ� ���ڴ� �������� �����ϴµ�
		x = x - (speed * Math.sin(dir + 0.694738944));
		// ��
	}

	public void colorRotate2(Point point) {
		int plusx = 120;
		int plusy = 120;
		if (color.getRGB() == -1617799) { // ����

			// ��
			double dx = point.getX() - x;
			double dy = point.getY() - y;
			if (dx == 0 && dy == 0)
				return;
			double dir = Math.atan2(dy, dx);
			int speed = 5;
			y = y + (speed * Math.cos(dir));
			x = x - (speed * Math.sin(dir));
		}
		if (color.getRGB() == -6431233) { // û��
			double dx = point.getX() - x + plusx;
			double dy = point.getY() - y + plusy;
			if (dx == 0 && dy == 0)
				return;
			double dir = Math.atan2(dy, dx);
			double dir4 = Math.atan(-0.5);
			int speed = 5;
			y = y + (speed * Math.cos(dir - 0.63)); // �ڿ� ���ڴ� �������� �����ϴµ�
			x = x - (speed * Math.sin(dir + 0.544));
			// ��
		}
		if (color.getRGB() == -4787) {// ���
			double dx = point.getX() - x - plusx;
			double dy = point.getY() - y - plusy;
			if (dx == 0 && dy == 0)
				return;
			double dir = Math.atan2(dy, dx);
			double dir2 = Math.atan(-0.5);
			double dir3 = Math.atan(-0.5);
			int speed = 5;
			y = y + (speed * Math.cos(dir - 0.454365654)); // �ڿ� ���ڴ� �������� �����ϴµ�
			x = x - (speed * Math.sin(dir + 0.4445345));

		}
		if (color.getRGB() == -6226692) { // ���
			double dx = point.getX() - x - plusx;
			double dy = point.getY() - y + plusy;
			if (dx == 0 && dy == 0)
				return;
			double dir = Math.atan2(dy, dx);
			double dir2 = Math.atan(-0.5);
			double dir3 = Math.atan(-0.5);
			// System.out.println("dir : "+dir4);
			int speed = 5;
			y = y + (speed * Math.cos(dir + 0.1765654)); // �ڿ� ���ڴ� �������� �����ϴµ�
			x = x - (speed * Math.sin(dir - 0.2145345));

		}
		if (color.getRGB() == -13492016) { // �Ķ�

			double dx = point.getX() - x + plusx;
			double dy = point.getY() - y - plusy;

			if (dx == 0 && dy == 0)
				return;
			double dir = Math.atan2(dy, dx);
			double dir2 = Math.tan(dx);
			double dir3 = Math.tan(dy);
			int speed = 5;

			x = x + (speed * (Math.sin(dir - 0.16445345)) / 2);
			y = y - (speed * Math.cos(dir + 0.10365654));

		}
	}

	public void atom(Point point) {
		if (color.getRGB() == -1617799) {// ��
			double dx = point.getX() - x;
			double dy = point.getY() - y;
			if (dx == 0 && dy == 0)
				return;
			double dir = Math.atan2(dy, dx);
			double dir2 = Math.atan(-0.5);
			double dir3 = Math.atan(-0.5);
			int speed = 5;
			y = y + (speed * (Math.cos(dir - 0.87365654) * 8)); // �ڿ� ���ڴ� �������� �����ϴµ�
			x = x - (speed * (Math.sin(dir + 0.3425) * 13));

		}

		if (color.getRGB() == -6431233) { // û��
			double dx = point.getX() - x;
			double dy = point.getY() - y;
			if (dx == 0 && dy == 0)
				return;
			double dir = Math.atan2(dy, dx);
			double dir2 = Math.atan(-0.5);
			double dir3 = Math.atan(-0.5);
			int speed = 5;
			y = y + (10 * (Math.cos(dir - 0.104365654) * 5)); // �ڿ� ���ڴ� �������� �����ϴµ�
			x = x - (7 * (Math.sin(dir + 0.125) * 1.2));
			// ��
		}
		if (color.getRGB() == -4787) {// ���
			double dx = point.getX() - x;
			double dy = point.getY() - y;
			if (dx == 0 && dy == 0)
				return;
			double dir = Math.atan2(dy, dx);
			double dir2 = Math.atan(-0.5);
			double dir3 = Math.atan(-0.5);
			int speed = 5;
			y = y + (7 * (Math.cos(dir - 0.954365654) * 3)); // ���� Ÿ���� ����� ���ؼ��� dir - ū�� �־������
			x = x - (speed * (Math.sin(dir + 0.125) * 15));

		}
		if (color.getRGB() == -6226692) { // ���
			double dx = point.getX() - x;
			double dy = point.getY() - y;
			if (dx == 0 && dy == 0)
				return;
			double dir = Math.atan2(dy, dx);
			double dir2 = Math.atan(-0.5);
			double dir3 = Math.atan(-0.5);
			int speed = 5;
			y = y + (7 * (Math.cos(dir + 0.9065654) * 2)); // ���� Ÿ���� ����� ���ؼ��� dir - ū�� �־������
			x = x - (speed * (Math.sin(dir - 0.249425) * 12));
		}
		if (color.getRGB() == -13492016) { // �Ķ�
			double dx = point.getX() - x;
			double dy = point.getY() - y;
			if (dx == 0 && dy == 0)
				return;
			double dir = Math.atan2(dy, dx);
			double dir2 = Math.atan(-0.5);
			double dir3 = Math.atan(-0.5);
			int speed = 5;
			y = y + (5 * (Math.cos(dir + 0.604365654) * 6.2)); // �ڿ� ���ڴ� �������� �����ϴµ�
			x = x - (7 * (Math.sin(dir - 0.70323425) * 4.5));
		}
		
		///////////////////////////////////////////////

	}
	
	public void tornado(Point point) {
		if (color.getRGB() == -1617799) { // ����

			// ��
			double dx = point.getX() - x;
			double dy = point.getY() - y;
			if (dx == 0 && dy == 0)
				return;
			double dir = Math.atan2(dy, dx);
			int speed = 5;
			y = y + (speed * Math.cos(dir));
			x = x - (speed * Math.sin(dir));
		}
		if (color.getRGB() == -6431233) { // û��
			double dx = point.getX() - x;
			double dy = point.getY() - y;
			if (dx == 0 && dy == 0)
				return;
			double dir = Math.atan2(dy, dx);
			double dir4 = Math.atan(-0.5);
			int speed = 5;
			y = y + (speed * Math.cos(dir - 0.63)); // �ڿ� ���ڴ� �������� �����ϴµ�
			x = x - (speed * Math.sin(dir + 0.544));
			// ��
		}
		if (color.getRGB() == -4787) {// ���
			double dx = point.getX() - x;
			double dy = point.getY() - y;
			if (dx == 0 && dy == 0)
				return;
			double dir = Math.atan2(dy, dx);
			double dir2 = Math.atan(-0.5);
			double dir3 = Math.atan(-0.5);
			int speed = 5;
			y = y + (speed * Math.cos(dir - 0.454365654)); // �ڿ� ���ڴ� �������� �����ϴµ�
			x = x - (speed * Math.sin(dir + 0.4445345));

		}
		if (color.getRGB() == -6226692) { // ���
			double dx = point.getX() - x;
			double dy = point.getY() - y;
			if (dx == 0 && dy == 0)
				return;
			double dir = Math.atan2(dy, dx);
			double dir2 = Math.atan(-0.5);
			double dir3 = Math.atan(-0.5);
			// System.out.println("dir : "+dir4);
			int speed = 5;
			y = y + (speed * Math.cos(dir + 0.1765654)); // �ڿ� ���ڴ� �������� �����ϴµ�
			x = x - (speed * Math.sin(dir - 0.2145345));

		}
		if (color.getRGB() == -13492016) { // �Ķ�

			double dx = point.getX() - x;
			double dy = point.getY() - y;

			if (dx == 0 && dy == 0)
				return;
			double dir = Math.atan2(dy, dx);
			double dir2 = Math.tan(dx);
			double dir3 = Math.tan(dy);
			int speed = 5;

			x = x + (speed * (Math.sin(dir - 0.16445345)) / 3);
			y = y - (speed * Math.cos(dir + 0.20365654));

		}
	}

	// pivotPoint �� ������ǥ �̹Ƿ� Ŭ���� Point �� �־������ (�߽���)
	// point�� ���� �ű���� �ϴ� ��ǥ
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

	public void onePointRotation(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		// g2.clearRect(0, 0, 500, 500);
		double dAngle = 30;
		AffineTransform old = g2.getTransform();// ���� ���� �����ϱ�
		g2.rotate(Math.toRadians(dAngle), 250, 250);// ȸ��, ȸ�� �߽�x, ȸ���߽�y
		g2.setTransform(old);// ���� ���·�(���� drawImage�Լ��� ȸ�����°� �����)

		// g.drawString(dAngle + "", 10, 10);// ���� ���

	}

//	  private function init():void
//      {
//           //Ư�� ������Ʈ sp �� ���� ��ǥ
//           sp.x = 100
//           sp.y = 100
//
//           var ob:Object = transformation(0,0,sp.x,sp.y,30*Math.PI/180)   
//           trace(ob.x , ob.y)
//
//           sp.x = ob.x
//           sp.y = ob.y
//      }
//
//      private function transformation(cx:Number,cy:Number,
//                                         px:Number,py:Number,
//                                         rad:Number):Object
//      {
//
//           var rx:Number = (px-cx)*Math.cos(rad) - (py-cy)*Math.sin(rad) + cx;
//           var ry:Number = (px-cx)*Math.sin(rad) + (py-cy)*Math.cos(rad) + cy;                
//           return {x:rx , y:ry}     
//      }

//	Point path = new Point((int) point.getX() - 200, (int) point.getY() - 300);
//	Point setPoint = getRotatePoint(path, point, 5);
//
////	double dx = setPoint.getX() - x;
////	double dy = setPoint.getY() - y;
////	if (dx == 0 && dy == 0)return;
////	double dir = Math.atan2(dy, dx);
//	int speed = 5;
////
////	x = x + (speed * Math.sin(dir));
////	y = y - (speed * Math.cos(dir));
//	double setx = setPoint.getX();
//	double sety = setPoint.getY();
//	double dir = Math.atan2(setx, sety);x=x+(speed*Math.sin(dir));y=y-(speed*Math.cos(dir));

}

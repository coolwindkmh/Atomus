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

	Point middle = new Point(400, 450);// 가운데 회전을 위한 포인트
	Point middle2 = new Point(1600, 450);// 가운데 회전을 위한 포인트
	// ObjectManager objectManager;
	/*
	 * 중력 가속도,,탄성도 넣어야 할거같음.하지만 모름,,타원으로 돌게하는 공식 필요 AffineTransform(포인트);
	 * 
	 */

	public Atom(int index, double x, double y, double width, double height, double arcwidth, double archeight,
			double velX, double velY, Color color) {
		this.index = index;
		// System.out.println("몇번째 인덱스 : " + index);
		this.x = x;
		this.y = y;
		this.width = width; // 구슬의 크기값
		this.height = height;
		this.arcwidth = arcwidth; // 구슬의 원형 모양
		this.archeight = archeight;
		this.velX = velX;
		this.velY = velY;
		this.color = color;

	}

	public void tick() {
		// 플레그 줘야 할거 같음
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
		if (x > 1600 || x < 0) { // 벽에 부딪히면 플래그 바꿔서 팅겨 나가게 만듬
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
		int flagA = (int) (Math.random() * 2); // vel -값을 위한 플래그 1, 아니면 0
		int flagB = (int) (Math.random() * 2);
		double randomVelX = (Math.random() * 2) + 0.001; // velX
		double randomVelY = (Math.random() * 2) + 0.001; // velY
		// 조건 하나 더 걸어야함 x y 배경 벗어났을시
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

	// 먼저 가운데로 모이는 메서드 만들어야 할거같음.
	public void gather(Point point) { // 한점으로 모든 객체가 모이는 메서드
		int speed = 5;
		double fastSpeed = 0;
		double slowSpeed = 0;
		double randomVelX = (Math.random() * speed) + 0.001; // velX 속도
		double randomVelY = (Math.random() * speed) + 0.001; // velY 속도
		double destinationX = point.getX();
		double destinationY = point.getY();

//		double halfLeftX= point.getX()-(Math.random() * 50)+0.01;
//		double halfRightX= point.getX()+(Math.random() * 50)+0.01;
//		
//		double halfLeftY= point.getY()-(Math.random() * 50)+0.01;
//		double halfRightY= point.getY()+(Math.random() * 50)+0.01;
		// x축의 오른쪽 왼쪽 나눠서 y축도 마찬가지

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

	// 회전 하는 메서드 어떻게 하는거야...ㅠ.ㅠ. 회전을 하려면....,x,y값을 돌게 해야하는데 tick말고 다른걸로 대체 해야하는디...
	// 속도도 바꾸고
	// 각도로 움직여야하나.?

	public void rotate(Point point) {
		double randomVelX = (Math.random() * 2) + 0.001; // velX
		double randomVelY = (Math.random() * 2) + 0.001; // velY
		Point myPoint = new Point((int) this.getX(), (int) this.getY());

		for (int i = 0; i < 360; i++) {
			p1 = getRotatePoint(new Point(200, 100), point, i); // 첫 변수에는 움직일 point 좌표값 넣어줘야 하는거같은데.?

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

	public void galaxyLeft(Point point) {// 은하수 표현 왼쪽으로 돌기
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

	public void galaxyRight(Point point) {// 은하수 표현 오른쪽으로 돌기

		double dx = point.getX() - x;
		double dy = point.getY() - y;
		if (dx == 0 && dy == 0)
			return;
		double dir = Math.atan2(dy, dx);
		int speed = 5;
		y = (y + speed * (Math.cos(dir)) / 5);
		x = x - (speed * Math.sin(dir));
	}

	public void galaxyUp(Point point) {// 블랙홀을 표현
		double dx = point.getX() - x;
		double dy = point.getY() - y;
		if (dx == 0 && dy == 0)
			return;
		double dir = Math.atan2(dy, dx);

		int speed = 5;
		y = y + (speed * Math.cos(dir - 2));
		x = x - (speed * Math.sin(dir - 2));

	}

	public void galaxyDown(Point point) {// 빅뱅을 표현
		double dx = point.getX() - x;
		double dy = point.getY() - y;
		if (dx == 0 && dy == 0)
			return;
		double dir = Math.atan2(dy, dx);

		int speed = 5;
		y = y + (speed * Math.cos(dir + 2));
		x = x - (speed * Math.sin(dir + 2));

	}

	public void galaxyGather(Point point) {// 옆에서본 은하 왼쪽

		double dx = point.getX() - x;
		double dy = point.getY() - y;
		if (dx == 0 && dy == 0)
			return;
		double dir = Math.atan2(dy, dx);

		int speed = 5;
		y = y + (speed * Math.cos(dir - 2));
		x = x - (speed * Math.cos(dir - 2));

	}

	public void galaxyGather2(Point point) {// 옆에서본 은하 오른쪽

		double dx = point.getX() - x;
		double dy = point.getY() - y;
		if (dx == 0 && dy == 0)
			return;
		double dir = Math.atan2(dy, dx);

		int speed = 5;
		y = y - (speed * Math.cos(dir + 2));
		x = x + (speed * Math.cos(dir + 2));

	}

	public void galaxyGather3(Point point) {// 옆에서본 은하 오른쪽

		double dx = point.getX() - x;
		double dy = point.getY() - y;
		if (dx == 0 && dy == 0)
			return;
		double dir = Math.atan2(dy, dx);

		int speed = 5;
		x = x - (speed * Math.cos(dir + 2));
		y = y + (speed * Math.cos(dir + 2));

	}

	public void galaxyRing(Point point) {// 옆에서본 은하 오른쪽
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

	public void vibration(Point point) {// 은하수 표현 왼쪽으로 돌기
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
		y = y + (speed * Math.cos(dir - 0.7844543)); // 뒤에 숫자는 고정값이 들어가야하는듯
		x = x - (speed * Math.sin(dir + 0.694738944));
		// 끝
	}

	public void colorRotate2(Point point) {
		int plusx = 120;
		int plusy = 120;
		if (color.getRGB() == -1617799) { // 빨강

			// 좌
			double dx = point.getX() - x;
			double dy = point.getY() - y;
			if (dx == 0 && dy == 0)
				return;
			double dir = Math.atan2(dy, dx);
			int speed = 5;
			y = y + (speed * Math.cos(dir));
			x = x - (speed * Math.sin(dir));
		}
		if (color.getRGB() == -6431233) { // 청흰
			double dx = point.getX() - x + plusx;
			double dy = point.getY() - y + plusy;
			if (dx == 0 && dy == 0)
				return;
			double dir = Math.atan2(dy, dx);
			double dir4 = Math.atan(-0.5);
			int speed = 5;
			y = y + (speed * Math.cos(dir - 0.63)); // 뒤에 숫자는 고정값이 들어가야하는듯
			x = x - (speed * Math.sin(dir + 0.544));
			// 우
		}
		if (color.getRGB() == -4787) {// 노랑
			double dx = point.getX() - x - plusx;
			double dy = point.getY() - y - plusy;
			if (dx == 0 && dy == 0)
				return;
			double dir = Math.atan2(dy, dx);
			double dir2 = Math.atan(-0.5);
			double dir3 = Math.atan(-0.5);
			int speed = 5;
			y = y + (speed * Math.cos(dir - 0.454365654)); // 뒤에 숫자는 고정값이 들어가야하는듯
			x = x - (speed * Math.sin(dir + 0.4445345));

		}
		if (color.getRGB() == -6226692) { // 흰색
			double dx = point.getX() - x - plusx;
			double dy = point.getY() - y + plusy;
			if (dx == 0 && dy == 0)
				return;
			double dir = Math.atan2(dy, dx);
			double dir2 = Math.atan(-0.5);
			double dir3 = Math.atan(-0.5);
			// System.out.println("dir : "+dir4);
			int speed = 5;
			y = y + (speed * Math.cos(dir + 0.1765654)); // 뒤에 숫자는 고정값이 들어가야하는듯
			x = x - (speed * Math.sin(dir - 0.2145345));

		}
		if (color.getRGB() == -13492016) { // 파랑

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
		if (color.getRGB() == -1617799) {// 빨
			double dx = point.getX() - x;
			double dy = point.getY() - y;
			if (dx == 0 && dy == 0)
				return;
			double dir = Math.atan2(dy, dx);
			double dir2 = Math.atan(-0.5);
			double dir3 = Math.atan(-0.5);
			int speed = 5;
			y = y + (speed * (Math.cos(dir - 0.87365654) * 8)); // 뒤에 숫자는 고정값이 들어가야하는듯
			x = x - (speed * (Math.sin(dir + 0.3425) * 13));

		}

		if (color.getRGB() == -6431233) { // 청흰
			double dx = point.getX() - x;
			double dy = point.getY() - y;
			if (dx == 0 && dy == 0)
				return;
			double dir = Math.atan2(dy, dx);
			double dir2 = Math.atan(-0.5);
			double dir3 = Math.atan(-0.5);
			int speed = 5;
			y = y + (10 * (Math.cos(dir - 0.104365654) * 5)); // 뒤에 숫자는 고정값이 들어가야하는듯
			x = x - (7 * (Math.sin(dir + 0.125) * 1.2));
			// 우
		}
		if (color.getRGB() == -4787) {// 노랑
			double dx = point.getX() - x;
			double dy = point.getY() - y;
			if (dx == 0 && dy == 0)
				return;
			double dir = Math.atan2(dy, dx);
			double dir2 = Math.atan(-0.5);
			double dir3 = Math.atan(-0.5);
			int speed = 5;
			y = y + (7 * (Math.cos(dir - 0.954365654) * 3)); // 얇은 타원을 만들기 위해서는 dir - 큰수 넣어줘야함
			x = x - (speed * (Math.sin(dir + 0.125) * 15));

		}
		if (color.getRGB() == -6226692) { // 흰색
			double dx = point.getX() - x;
			double dy = point.getY() - y;
			if (dx == 0 && dy == 0)
				return;
			double dir = Math.atan2(dy, dx);
			double dir2 = Math.atan(-0.5);
			double dir3 = Math.atan(-0.5);
			int speed = 5;
			y = y + (7 * (Math.cos(dir + 0.9065654) * 2)); // 얇은 타원을 만들기 위해서는 dir - 큰수 넣어줘야함
			x = x - (speed * (Math.sin(dir - 0.249425) * 12));
		}
		if (color.getRGB() == -13492016) { // 파랑
			double dx = point.getX() - x;
			double dy = point.getY() - y;
			if (dx == 0 && dy == 0)
				return;
			double dir = Math.atan2(dy, dx);
			double dir2 = Math.atan(-0.5);
			double dir3 = Math.atan(-0.5);
			int speed = 5;
			y = y + (5 * (Math.cos(dir + 0.604365654) * 6.2)); // 뒤에 숫자는 고정값이 들어가야하는듯
			x = x - (7 * (Math.sin(dir - 0.70323425) * 4.5));
		}
		
		///////////////////////////////////////////////

	}
	
	public void tornado(Point point) {
		if (color.getRGB() == -1617799) { // 빨강

			// 좌
			double dx = point.getX() - x;
			double dy = point.getY() - y;
			if (dx == 0 && dy == 0)
				return;
			double dir = Math.atan2(dy, dx);
			int speed = 5;
			y = y + (speed * Math.cos(dir));
			x = x - (speed * Math.sin(dir));
		}
		if (color.getRGB() == -6431233) { // 청흰
			double dx = point.getX() - x;
			double dy = point.getY() - y;
			if (dx == 0 && dy == 0)
				return;
			double dir = Math.atan2(dy, dx);
			double dir4 = Math.atan(-0.5);
			int speed = 5;
			y = y + (speed * Math.cos(dir - 0.63)); // 뒤에 숫자는 고정값이 들어가야하는듯
			x = x - (speed * Math.sin(dir + 0.544));
			// 우
		}
		if (color.getRGB() == -4787) {// 노랑
			double dx = point.getX() - x;
			double dy = point.getY() - y;
			if (dx == 0 && dy == 0)
				return;
			double dir = Math.atan2(dy, dx);
			double dir2 = Math.atan(-0.5);
			double dir3 = Math.atan(-0.5);
			int speed = 5;
			y = y + (speed * Math.cos(dir - 0.454365654)); // 뒤에 숫자는 고정값이 들어가야하는듯
			x = x - (speed * Math.sin(dir + 0.4445345));

		}
		if (color.getRGB() == -6226692) { // 흰색
			double dx = point.getX() - x;
			double dy = point.getY() - y;
			if (dx == 0 && dy == 0)
				return;
			double dir = Math.atan2(dy, dx);
			double dir2 = Math.atan(-0.5);
			double dir3 = Math.atan(-0.5);
			// System.out.println("dir : "+dir4);
			int speed = 5;
			y = y + (speed * Math.cos(dir + 0.1765654)); // 뒤에 숫자는 고정값이 들어가야하는듯
			x = x - (speed * Math.sin(dir - 0.2145345));

		}
		if (color.getRGB() == -13492016) { // 파랑

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

	// pivotPoint 는 중점좌표 이므로 클릭한 Point 를 넣어줘야함 (중심축)
	// point는 현재 옮기고자 하는 좌표
	static public Point getRotatePoint(Point point, Point pivotPoint, double degrees) {

		double dSetDegree = Math.toRadians(degrees);
		double cosq = Math.cos(dSetDegree);
		double sinq = Math.sin(dSetDegree);
		double sx = point.x - pivotPoint.x;
		double sy = point.y - pivotPoint.y;
		double rx = (sx * cosq - sy * sinq) + pivotPoint.x; // 결과 좌표 x
		double ry = (sx * sinq + sy * cosq) + pivotPoint.y; // 결과 좌표 y

		return new Point((int) rx, (int) ry);
	}

	public void onePointRotation(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		// g2.clearRect(0, 0, 500, 500);
		double dAngle = 30;
		AffineTransform old = g2.getTransform();// 현재 상태 저장하기
		g2.rotate(Math.toRadians(dAngle), 250, 250);// 회전, 회전 중심x, 회전중심y
		g2.setTransform(old);// 원래 상태로(위에 drawImage함수만 회전상태가 적용됨)

		// g.drawString(dAngle + "", 10, 10);// 각도 출력

	}

//	  private function init():void
//      {
//           //특정 오브젝트 sp 의 원래 좌표
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

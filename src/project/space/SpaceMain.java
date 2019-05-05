package project.space;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;

import javax.swing.JFrame;

public class SpaceMain extends JFrame {
	Background panel;
	Thread thread;
	boolean allTickFlag = true;
	boolean gatherFlag = false;
	boolean galaxyFlag = false;
	boolean vibrationFlag = false;
	Graphics g;
	Thread thread2;
	Thread thread3, thread4, thread5;
	Point middle = new Point(800, 450);// 가운데 회전을 위한 포인트
	Point middle2 = new Point(800, 470);
	SpaceMain gameMain;
	public SpaceMain() {
		panel = new Background();
		gameMain = this;
		add(panel);
		firstThread();
		addMouseMotionListener(new MouseMotionListener() {
			public void mouseMoved(MouseEvent e) {

			}

			public void mouseDragged(MouseEvent e) {
				allTickFlag = !allTickFlag;
				gatherFlag = !gatherFlag;
				thread3 = new Thread() {
					public void run() {
						// 여기서 회전 만들장!
						while (gatherFlag) {
							panel.gather(e.getPoint());
							// panel.rolling(middle, 8);
							// panel.rolling(e.getPoint());
							try {
								Thread.sleep(10);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				};
				thread3.start();
			}
		});

		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				allTickFlag = !allTickFlag;
				gatherFlag = !gatherFlag;
				panel.paintFlag = !panel.paintFlag;
				thread2 = new Thread() {
					public void run() {
						// 여기서 회전 만들장!
						while (gatherFlag) {
							panel.gather(e.getPoint());
							try {
								Thread.sleep(10);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				};
				thread2.start();
			}

			// 마우스 눌럿다땟을때 확 퍼졌다가 다시 alltick 호출해서 원래 상태로
//			//몇초 뒤에 바뀌게 스레드 슬립 줘야 할거같음
		});

		addKeyListener(new KeyAdapter() {

			public void keyReleased(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == KeyEvent.VK_LEFT) {
					allTickFlag = !allTickFlag;
					gatherFlag = !gatherFlag;
					panel.paintFlag = !panel.paintFlag;
					galaxyFlag = !galaxyFlag;
					thread4 = new Thread() {
						public void run() {
							// 여기서 회전 만들장!
							while (galaxyFlag) {
								panel.rolling(middle, 1);
								try {
									Thread.sleep(10);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}
					};
					thread4.start();
				} else if (key == KeyEvent.VK_RIGHT) {
					allTickFlag = !allTickFlag;
					gatherFlag = !gatherFlag;
					panel.paintFlag = !panel.paintFlag;
					galaxyFlag = !galaxyFlag;
					thread4 = new Thread() {
						public void run() {
							// 여기서 회전 만들장!
							while (galaxyFlag) {
								panel.rolling(middle, 2);
								try {
									Thread.sleep(10);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
							// System.out.println("x : "+panel.circle.x);
						}
					};
					thread4.start();
				} else if (key == KeyEvent.VK_UP) {
					allTickFlag = !allTickFlag;
					gatherFlag = !gatherFlag;
					panel.paintFlag = !panel.paintFlag;
					galaxyFlag = !galaxyFlag;
					thread4 = new Thread() {
						public void run() {
							// 여기서 회전 만들장!
							while (galaxyFlag) {
								panel.rolling(middle, 3);
								try {
									Thread.sleep(10);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}
					};
					thread4.start();
				} else if (key == KeyEvent.VK_DOWN) {
					allTickFlag = !allTickFlag;
					gatherFlag = !gatherFlag;
					panel.paintFlag = !panel.paintFlag;
					galaxyFlag = !galaxyFlag;
					thread4 = new Thread() {
						public void run() {
							while (galaxyFlag) {
								panel.rolling(middle, 4);
								try {
									Thread.sleep(10);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}
					};
					thread4.start();
				} else if (key == KeyEvent.VK_Q) {
					allTickFlag = !allTickFlag;
					gatherFlag = !gatherFlag;
					panel.paintFlag = !panel.paintFlag;
					galaxyFlag = !galaxyFlag;
					thread4 = new Thread() {
						public void run() {
							while (galaxyFlag) {
								panel.rolling(middle, 5);
								try {
									Thread.sleep(10);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}
					};
					thread4.start();
				} else if (key == KeyEvent.VK_W) {
					allTickFlag = !allTickFlag;
					gatherFlag = !gatherFlag;
					panel.paintFlag = !panel.paintFlag;
					galaxyFlag = !galaxyFlag;
					thread4 = new Thread() {
						public void run() {
							while (galaxyFlag) {
								panel.rolling(middle, 6);
								try {
									Thread.sleep(10);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}
					};
					thread4.start();
				} else if (key == KeyEvent.VK_E) {
					allTickFlag = !allTickFlag;
					gatherFlag = !gatherFlag;
					panel.paintFlag = !panel.paintFlag;
					galaxyFlag = !galaxyFlag;
					thread4 = new Thread() {
						public void run() {
							while (galaxyFlag) {
								panel.rolling(middle, 7);
								try {
									Thread.sleep(10);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}
					};
					thread4.start();
				} else if (key == KeyEvent.VK_R) {
					allTickFlag = !allTickFlag;
					gatherFlag = !gatherFlag;
					panel.paintFlag = !panel.paintFlag;
					galaxyFlag = !galaxyFlag;
					thread4 = new Thread() {
						public void run() {
							while (galaxyFlag) {
								panel.rolling(middle, 8);
								try {
									Thread.sleep(10);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}
					};
					thread4.start();
				} else if (key == KeyEvent.VK_A) {
					allTickFlag = !allTickFlag;
					gatherFlag = !gatherFlag;
					panel.paintFlag = !panel.paintFlag;
					galaxyFlag = !galaxyFlag;
					thread4 = new Thread() {
						public void run() {
							while (galaxyFlag) {
								panel.rolling(middle, 9);
								try {
									Thread.sleep(10);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}
					};
					thread4.start();
				} else if (key == KeyEvent.VK_S) {
					allTickFlag = !allTickFlag;
					gatherFlag = !gatherFlag;
					panel.paintFlag = !panel.paintFlag;
//					galaxyFlag = !galaxyFlag;
					vibrationFlag = !vibrationFlag;
					thread5 = new Thread() {
						public void run() {
							while (vibrationFlag) {
								panel.rolling(middle, 10);
								try {
									Thread.sleep(10);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
							// System.out.println("x : "+panel.circle.x);
						}
					};
					thread5.start();
				} else if (key == KeyEvent.VK_D) {
					allTickFlag = !allTickFlag;
					gatherFlag = !gatherFlag;
					panel.paintFlag = !panel.paintFlag;
					galaxyFlag = !galaxyFlag;
					thread4 = new Thread() {
						public void run() {
							while (galaxyFlag) {
								panel.rolling(middle, 11);
								try {
									Thread.sleep(10);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}
					};
					thread4.start();
				} else if (key == KeyEvent.VK_F) {
					allTickFlag = !allTickFlag;
					gatherFlag = !gatherFlag;
					panel.paintFlag = !panel.paintFlag;
					galaxyFlag = !galaxyFlag;
					thread4 = new Thread() {
						public void run() {
							while (galaxyFlag) {
								panel.rolling(middle, 12);
								try {
									Thread.sleep(10);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}
					};
					thread4.start();
				}
			}

		});

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		pack();
		setVisible(true);
		setTitle("Space");
		thread.start();
	}

	public void firstThread() {
		thread = new Thread() {
			public void run() {
				while (true) {
					if (allTickFlag) {
						panel.allTick();
					} else {
						panel.rotateObject();
					}
					panel.repaint();
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
	}

	public static void main(String[] args) {
		new SpaceMain();

	}

}

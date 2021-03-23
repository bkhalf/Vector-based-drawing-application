package eg.edu.alexu.csd.oop.draw;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.swing.SpringLayout;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Shape;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.CardLayout;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.SystemColor;
import java.awt.ComponentOrientation;
import java.awt.Insets;

public class Painter {

	private JFrame frame;
	drweng dg = new drweng();
	MouseListener ml;
	MouseMotionListener mx;
	int numOfShape = 0;
	Canvas canvse = new Canvas();
	boolean isfound = false;
	boolean pressed_shape = false;
	ourshape typeshape;
	int typeIndex;
	boolean isselect = false;
	boolean canMove = true;
	Color color = Color.black;
	Color fillColor = Color.white;
	boolean isfilled = false;
	ArrayList<Integer> selectPositions = new ArrayList<Integer>();	
	ArrayList<ourshape> selectedshape = new ArrayList<ourshape>();
	ourshape sh;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Painter window = new Painter();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Painter() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.windowText);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setSize(2500, 2000);
		frame.setTitle("Paint");
		frame.getContentPane().setLayout(null);
		canvse.setBackground(Color.WHITE);

		canvse.setSize(1924, 864);
		canvse.setLocation(0, 170);
		canvse.setPreferredSize(new Dimension(2000, 1500));
		canvse.setMaximumSize(new Dimension(2000, 1500));
		canvse.setMinimumSize(new Dimension(2000, 1500));
		frame.getContentPane().add(canvse);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.window);
		panel.setAutoscrolls(true);
		panel.setIgnoreRepaint(true);
		panel.setForeground(Color.BLACK);
		panel.setBounds(0, 0, 1924, 160);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(SystemColor.window);
		menuBar.setBounds(0, 0, 600, 27);
		panel.add(menuBar);

		JMenuItem mntmNewMenuItem = new JMenuItem("File");
		mntmNewMenuItem.setBackground(SystemColor.window);
		mntmNewMenuItem.setMaximumSize(new Dimension(100, 100));
		menuBar.add(mntmNewMenuItem);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Edit");
		mntmNewMenuItem_1.setBackground(SystemColor.window);
		mntmNewMenuItem_1.setMaximumSize(new Dimension(100, 100));
		menuBar.add(mntmNewMenuItem_1);

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Save");
		mntmNewMenuItem_2.setFocusable(true);
		mntmNewMenuItem_2.setFocusTraversalPolicyProvider(true);
		mntmNewMenuItem_2.setFocusCycleRoot(true);
		mntmNewMenuItem_2.setFocusPainted(true);
		mntmNewMenuItem_2.setBackground(SystemColor.window);
		mntmNewMenuItem_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				JFileChooser fc = new JFileChooser();
				int response = fc.showSaveDialog(mntmNewMenuItem_2);
				if (response == JFileChooser.APPROVE_OPTION) {
					String fileName;
					fileName = fc.getSelectedFile().toString();

					try {
						String ss = fileName.substring(fileName.length() - 4);
						if (ss.equals(".xml") || ss.equals("json")) {
							dg.save(fileName);
							System.out.println("correct path");
						} else {
							System.out.println("false location");
						}
					} catch (ArrayIndexOutOfBoundsException e) {
						System.out.println("false location111");
					}

				}
			}
		});
		mntmNewMenuItem_2.setMaximumSize(new Dimension(100, 100));
		menuBar.add(mntmNewMenuItem_2);

		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Load");
		mntmNewMenuItem_3.setBackground(SystemColor.window);
		mntmNewMenuItem_3.addMouseListener(new MouseAdapter() {
			Graphics g = canvse.getGraphics();

			@Override
			public void mousePressed(MouseEvent arg0) {
				canvse.repaint();
				JFileChooser fc = new JFileChooser();
				javax.swing.filechooser.FileFilter filter1 = new FileNameExtensionFilter("XML file", "xml");
				javax.swing.filechooser.FileFilter filter2 = new FileNameExtensionFilter("JSON file", "json");
				fc.setFileFilter(filter1);
				fc.setFileFilter(filter2);
				int response = fc.showOpenDialog(mntmNewMenuItem_3);
				if (response == JFileChooser.APPROVE_OPTION) {
					String fileName;
					fileName = fc.getSelectedFile().toString();
					try {
						String ss = fileName.substring(fileName.length() - 4);
						if (ss.equals(".xml") || ss.equals("json")) {
							dg.load(fileName);
							System.out.println("correct path");
						} else {
							System.out.println("false location");
						}
					} catch (ArrayIndexOutOfBoundsException e) {
						System.out.println("false location111");
					}

				}
				dg.refresh(g);;
			}
		});
		mntmNewMenuItem_3.setMaximumSize(new Dimension(100, 100));
		menuBar.add(mntmNewMenuItem_3);

		JPanel panel_1 = new JPanel();
		panel_1.setMinimumSize(new Dimension(80, 80));
		panel_1.setMaximumSize(new Dimension(200, 200));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(0, 25, 771, 140);
		panel.add(panel_1);
		panel_1.setLayout(null);
		// **************************square***********************
		
		JButton square = new JButton("");
		square.setBackground(Color.WHITE);
		ImageIcon square_shape = new ImageIcon("src/images/square.png");
		int square_shape_scale = 15; 
		int square_shape_width = square_shape.getIconWidth();
		int square_shape_newWidth = square_shape_width / square_shape_scale;
		square.setIcon(new ImageIcon(square_shape.getImage().getScaledInstance(square_shape_newWidth, -1, java.awt.Image.SCALE_SMOOTH)));
		square.addActionListener(new ActionListener() {
			int x, y, x2, y2;
			int psx;

			public void actionPerformed(ActionEvent arg0) {
				canMove = false;
				canvse.removeMouseListener(ml);
				canvse.removeMouseMotionListener(mx);
				canvse.addMouseListener(ml = new MouseListener() {
					@Override
					public void mouseClicked(MouseEvent e) {
					}

					@Override
					public void mouseEntered(MouseEvent e) {
					}

					@Override
					public void mouseExited(MouseEvent e) {
					}

					@Override
					public void mousePressed(MouseEvent e) {
						x = e.getX();
						y = e.getY();
					}

					@Override
					public void mouseReleased(MouseEvent e) {
						Square sq = new Square();
						Point temp = new Point();
						temp.x = x;
						temp.y = y;
						x2 = e.getX();
						y2 = e.getY();
						if (y2 < y)
							temp.y = y2;
						if (x2 < x)
							temp.x = x2;
						if (Math.abs(x2 - x) > Math.abs(y2 - y)) {
							psx = Math.abs(x2 - x);
						} else {
							psx = Math.abs(y2 - y);
						}
						Graphics g = canvse.getGraphics();
						g.setColor(color);
						sq.setColor(color);
						if (isfilled) {
							sq.setFillColor(fillColor);
						}
						sq.setDim(psx, psx);
						sq.setPosition(temp);
						dg.addShape(sq);
						dg.refresh(g);
						sq.setSelectionBounds(temp.x, temp.y, psx, psx);
						Map<String, Double> m = new HashMap<>();
						m.put("type", 1.0);
						m.put("positionx", temp.getX());
						m.put("positiony", temp.getY());
						m.put("lastPositionx", (double) psx);
						m.put("lastPositiony", (double) psx);
						m.put("color", (double) color.getRGB());
						m.put("fillColor", (double) fillColor.getRGB());
						m.put("boundx",(double)temp.x);
						m.put("boundy", (double) temp.y);
						m.put("boundlastPositionx", (double) psx);
						m.put("boundlastPositiony", (double) psx);
						sq.setProperties(m);
						selectedshape.add(sq);
						ArrayList<eg.edu.alexu.csd.oop.draw.Shape> shapes = new ArrayList<eg.edu.alexu.csd.oop.draw.Shape>();
						shapes.addAll(dg.allShapes);
						dg.st.push(shapes);
						dg.firstUndo = true;
						canMove = true;
						dg.stRedo.clear();
						canvse.removeMouseListener(this);
						canvse.removeMouseMotionListener(mx);
					}
				});
				canvse.addMouseMotionListener(mx = new MouseMotionListener() {

					@Override
					public void mouseDragged(MouseEvent e) {
						Graphics g = canvse.getGraphics();
						g.setColor(color);
						Point temp = new Point();
						temp.x = x;
						temp.y = y;
						x2 = e.getX();
						y2 = e.getY();
						if (y2 < y)
							temp.y = y2;
						if (x2 < x)
							temp.x = x2;
						mx = this;
						if (Math.abs(x2 - x) > Math.abs(y2 - y)) {
							psx = Math.abs(x2 - x);
						} else {
							psx = Math.abs(y2 - y);
						}
						if (isfilled) {
							g.fillRect(temp.x, temp.y, psx, psx);
						}
						g.drawRect(temp.x, temp.y, psx, psx);
						canvse.repaint();
						canvse.paint(g);
						dg.refresh(g);

					}

					public void mouseMoved(MouseEvent e) {
					}
				});

			}

		});
		square.setBounds(0, 0, 85, 88);
		panel_1.add(square);
		// ***************************rectangle*******************
		JButton rectangle = new JButton("");
		rectangle.setBackground(Color.WHITE);
		ImageIcon rectangle_shape = new ImageIcon("src/images/rectangle.png");
		int rectangle_shape_scale = 10; 
		int rectangle_shape_width = rectangle_shape.getIconWidth();
		int rectangle_shape_newWidth = rectangle_shape_width / rectangle_shape_scale;
		rectangle.setIcon(new ImageIcon(rectangle_shape.getImage().getScaledInstance(rectangle_shape_newWidth, -1, java.awt.Image.SCALE_SMOOTH)));
		rectangle.addActionListener(new ActionListener() {

			int x, y, x2, y2;

			public void actionPerformed(ActionEvent arg0) {
				canMove = false;
				canvse.removeMouseListener(ml);
				canvse.removeMouseMotionListener(mx);
				canvse.addMouseListener(ml = new MouseListener() {
					@Override
					public void mouseClicked(MouseEvent e) {
					}

					@Override
					public void mouseEntered(MouseEvent e) {
					}

					@Override
					public void mouseExited(MouseEvent e) {
					}

					@Override
					public void mousePressed(MouseEvent e) {
						// TODO Auto-generated method stub
						x = e.getX();
						y = e.getY();
					}

					@Override
					public void mouseReleased(MouseEvent e) {
						Rectangle r = new Rectangle();
						Point temp = new Point();
						temp.x = x;
						temp.y = y;
						x2 = e.getX();
						y2 = e.getY();
						if (y2 < y)
							temp.y = y2;
						if (x2 < x)
							temp.x = x2;
						Graphics g = canvse.getGraphics();
						g.setColor(color);
						r.setColor(color);
						if (isfilled) {
							r.setFillColor(fillColor);
						}
						r.setDim(Math.abs(x - x2), Math.abs(y - y2));
						r.setPosition(temp);
						dg.addShape(r);
						r.draw(g);
						dg.refresh(g);
						selectedshape.add(r);
						selectPositions.add(temp.x);
						selectPositions.add(temp.y);
						selectPositions.add(Math.abs(x - x2));
						selectPositions.add(Math.abs(y - y2));
						r.setSelectionBounds(temp.x, temp.y, Math.abs(x - x2), Math.abs(y - y2));
						Map<String, Double> m = new HashMap<>();
						m.put("type", 2.0);
						m.put("positionx", temp.getX());
						m.put("positiony", temp.getY());
						m.put("lastPositionx", (double) Math.abs(x - x2));
						m.put("lastPositiony", (double) Math.abs(y - y2));
						m.put("color", (double) color.getRGB());
						m.put("fillColor", (double) fillColor.getRGB());
						m.put("boundx",(double)temp.x);
						m.put("boundy", (double) temp.y);
						m.put("boundlastPositionx", (double) Math.abs(x - x2));
						m.put("boundlastPositiony", (double) Math.abs(y - y2));
						r.setProperties(m);
						ArrayList<eg.edu.alexu.csd.oop.draw.Shape> shapes = new ArrayList<eg.edu.alexu.csd.oop.draw.Shape>();
						shapes.addAll(dg.allShapes);
						dg.st.push(shapes);
						dg.firstUndo = true;
						canMove = true;
						dg.stRedo.clear();
						canvse.removeMouseListener(this);
						canvse.removeMouseMotionListener(mx);
					}
				});
				canvse.addMouseMotionListener(mx = new MouseMotionListener() {

					@Override
					public void mouseDragged(MouseEvent e) {
						Graphics g = canvse.getGraphics();
						g.setColor(color);
						Point temp = new Point();
						temp.x = x;
						temp.y = y;
						x2 = e.getX();
						y2 = e.getY();
						if (y2 < y)
							temp.y = y2;
						if (x2 < x)
							temp.x = x2;
						mx = this;
						if (isfilled) {
							g.fillRect(temp.x, temp.y, Math.abs(x - x2), Math.abs(y - y2));
						}
						g.drawRect(temp.x, temp.y, Math.abs(x - x2), Math.abs(y - y2));
						canvse.repaint();
						dg.refresh(g);

					}

					public void mouseMoved(MouseEvent e) {
					}
				});

			}
		});

		rectangle.setBounds(85, 0, 85, 88);
		panel_1.add(rectangle);
		// **************************Trianglr*********************
		JButton triangle = new JButton("");
		triangle.setBackground(Color.WHITE);
		ImageIcon triangle_shape = new ImageIcon("src/images/triangle.png");
		int triangle_shape_scale = 10; 
		int triangle_shape_width = triangle_shape.getIconWidth();
		int triangle_shape_newWidth = triangle_shape_width / triangle_shape_scale;
		triangle.setIcon(new ImageIcon(triangle_shape.getImage().getScaledInstance(triangle_shape_newWidth, -1, java.awt.Image.SCALE_SMOOTH)));
		triangle.addActionListener(new ActionListener() {

			int x, y, x2, y2;

			public void actionPerformed(ActionEvent arg0) {
				canMove = false;
				canvse.removeMouseListener(ml);
				canvse.removeMouseMotionListener(mx);
				canvse.addMouseListener(ml = new MouseListener() {
					@Override
					public void mouseClicked(MouseEvent e) {
					}

					@Override
					public void mouseEntered(MouseEvent e) {
					}

					@Override
					public void mouseExited(MouseEvent e) {
					}

					@Override
					public void mousePressed(MouseEvent e) {
						x = e.getX();
						y = e.getY();
					}

					@Override
					public void mouseReleased(MouseEvent e) {
						Triangle t = new Triangle();
						Point temp = new Point();
						temp.x = x;
						temp.y = y;
						x2 = e.getX();
						y2 = e.getY();
						Point hr = new Point();
						hr.x = x2;
						hr.y = y2;
						Graphics g = canvse.getGraphics();
						g.setColor(color);
						t.setColor(color);
						if (isfilled) {
							t.setFillColor(fillColor);

						}

						t.setPosition(temp);
						t.setDim(x2, y2);
						dg.addShape(t);
						t.draw(g);
						dg.refresh(g);
						selectedshape.add(t);
						t.forTriangle.add(x);
						t.forTriangle.add(y);
						t.forTriangle.add(x2);
						t.forTriangle.add(y2);
						int min = temp.y;
						int max = y2 - temp.y;
						if (y > y2) {
							min = y2;
							max = temp.y - y2;
						}

						selectPositions.add(temp.x - Math.abs(temp.x - x2));
						selectPositions.add(min);
						selectPositions.add(2 * Math.abs(temp.x - x2));
						selectPositions.add(max);
						t.setSelectionBounds(temp.x - Math.abs(temp.x - x2), min, 2 * Math.abs(temp.x - x2), max);
						ArrayList<eg.edu.alexu.csd.oop.draw.Shape> shapes = new ArrayList<eg.edu.alexu.csd.oop.draw.Shape>();
						shapes.addAll(dg.allShapes);
						dg.st.push(shapes);
						dg.firstUndo = true;
						canMove = true;
						dg.stRedo.clear();
						Map<String, Double> m = new HashMap<>();
						m.put("type", 3.0);
						m.put("positionx", temp.getX());
						m.put("positiony", temp.getY());
						m.put("lastPositionx", (double) x2);
						m.put("lastPositiony", (double) y2);
						m.put("color", (double) color.getRGB());
						m.put("fillColor", (double) fillColor.getRGB());
						m.put("boundx",(double)temp.x - Math.abs(temp.x - x2));
						m.put("boundy", (double)  min);
						m.put("boundlastPositionx", (double) 2 * Math.abs(temp.x - x2));
						m.put("boundlastPositiony", (double) max);
						t.setProperties(m);
						canvse.removeMouseListener(this);
						canvse.removeMouseMotionListener(mx);
					}
				});
				canvse.addMouseMotionListener(mx = new MouseMotionListener() {

					@Override
					public void mouseDragged(MouseEvent e) {
						Graphics g = canvse.getGraphics();
						g.setColor(color);
						Point temp = new Point();
						temp.x = x;
						temp.y = y;
						x2 = e.getX();
						y2 = e.getY();
						mx = this;
						if (temp.x > x2) {
							if (isfilled) {
								int f1 = temp.x, f2 = x2, f3 = temp.x + Math.abs(temp.x - x2), z1 = temp.y, z2 = y2,
										z3 = y2;
								int[] xs = { f1, f2, f3 };
								int[] ys = { z1, z2, z3 };
								g.fillPolygon(xs, ys, 3);
							}
							g.drawLine(temp.x, temp.y, x2, y2);
							g.drawLine(x2, y2, temp.x + Math.abs(temp.x - x2), y2);
							g.drawLine(temp.x + Math.abs(temp.x - x2), y2, temp.x, temp.y);
						} else if (temp.x < x2) {
							if (isfilled) {
								int f1 = temp.x, f2 = x2, f3 = temp.x - Math.abs(temp.x - x2), z1 = temp.y, z2 = y2,
										z3 = y2;
								int[] xs = { f1, f2, f3 };
								int[] ys = { z1, z2, z3 };
								g.fillPolygon(xs, ys, 3);
							}
							g.drawLine(temp.x, temp.y, x2, y2);
							g.drawLine(x2, y2, temp.x - Math.abs(temp.x - x2), y2);
							g.drawLine(temp.x - Math.abs(temp.x - x2), y2, temp.x, temp.y);
						}
						canvse.repaint();
						dg.refresh(g);

					}

					public void mouseMoved(MouseEvent e) {
					}
				});

			}
		});
		triangle.setBounds(255, 0, 85, 88);
		panel_1.add(triangle);
		// **************************circle***********************
		JButton circle = new JButton("");
		circle.setBackground(Color.WHITE);
		ImageIcon circle_shape = new ImageIcon("src/images/circle.png");
		int circle_shape_scale = 10; 
		int circle_shape_width = circle_shape.getIconWidth();
		int circle_shape_newWidth = circle_shape_width / circle_shape_scale;
		circle.setIcon(new ImageIcon(circle_shape.getImage().getScaledInstance(circle_shape_newWidth, -1, java.awt.Image.SCALE_SMOOTH)));
		
		circle.addActionListener(new ActionListener() {

			int x, y, x2, y2;

			public void actionPerformed(ActionEvent e) {
				canMove = false;
				canvse.removeMouseListener(ml);
				canvse.removeMouseMotionListener(mx);
				canvse.addMouseListener(ml = new MouseListener() {

					public void mouseClicked(MouseEvent e) {
					}

					public void mouseEntered(MouseEvent e) {
					}

					public void mouseExited(MouseEvent e) {
					}

					public void mousePressed(MouseEvent e) {
						x = e.getX();
						y = e.getY();
					}

					@Override
					public void mouseReleased(MouseEvent e) {
						circle c = new circle();
						Point temp = new Point();
						temp.x = x;
						temp.y = y;
						x2 = e.getX();
						y2 = e.getY();
						if (y2 < y)
							temp.y = y2;
						if (x2 < x)
							temp.x = x2;
						c.setPosition(temp);
						Graphics g = canvse.getGraphics();
						g.setColor(color);
						c.setColor(color);
						if (isfilled) {
							c.setFillColor(fillColor);
						}
						Point hr = new Point();
						hr.x = x2;
						hr.y = y2;
	        			c.setDim(hr);
						double k = Math.sqrt(Math.pow(y - y2, 2) + Math.pow(x - x2, 2));
						int h = (int) Math.round(k);
						c.setSelectionBounds(temp.x, temp.y, h, h);
						dg.addShape(c);
						dg.refresh(g);
						selectedshape.add(c);
						selectPositions.add(temp.x);
						selectPositions.add(temp.y);
						selectPositions.add(h);
						selectPositions.add(h);
						ArrayList<eg.edu.alexu.csd.oop.draw.Shape> shapes = new ArrayList<eg.edu.alexu.csd.oop.draw.Shape>();
						shapes.addAll(dg.allShapes);
						dg.st.push(shapes);
						dg.firstUndo = true;
						canMove = true;
						dg.stRedo.clear();
						Map<String, Double> m = new HashMap<>();
						m.put("type", 4.0);
						m.put("positionx", temp.getX());
						m.put("positiony", temp.getY());
						m.put("lastPositionx", (double) x2);
						m.put("lastPositiony", (double) y2);
						m.put("color", (double) color.getRGB());
						m.put("fillColor", (double) fillColor.getRGB());
						m.put("boundx",(double)temp.x);
						m.put("boundy", (double) temp.y);
						m.put("boundlastPositionx",(double) h);
						m.put("boundlastPositiony", (double) h);
						c.setProperties(m);
						
						canvse.removeMouseListener(this);
						canvse.removeMouseMotionListener(mx);
					}
				});
				canvse.addMouseMotionListener(mx = new MouseMotionListener() {

					@Override
					public void mouseDragged(MouseEvent e) {
						Graphics g = canvse.getGraphics();
						Point temp = new Point();
						temp.x = x;
						temp.y = y;
						x2 = e.getX();
						y2 = e.getY();
						if (y2 < y)
							temp.y = y2;
						if (x2 < x)
							temp.x = x2;
						double k = Math.sqrt(Math.pow(y - y2, 2) + Math.pow(x - x2, 2));
						int h = (int) Math.round(k);
						if (isfilled) {
							g.fillArc(temp.x, temp.y, h, h, 0, 360);
						}
						g.drawArc(temp.x, temp.y, h, h, 0, 360);
						canvse.repaint();
						dg.refresh(g);
						mx = this;
					}

					public void mouseMoved(MouseEvent e) {
					}
				});

			}

		});
		circle.setActionCommand("circle");
		circle.setBounds(425, 0, 85, 87);
		panel_1.add(circle);
		// *********************ellipse****************************
		JButton ellipse = new JButton("");
		ellipse.setBackground(Color.WHITE);
		ImageIcon ellipse_shape = new ImageIcon("src/images/ellipse.png");
		int ellipse_shape_scale = 2; 
		int ellipse_shape_width = ellipse_shape.getIconWidth();
		int ellipse_shape_newWidth = ellipse_shape_width / ellipse_shape_scale;
		ellipse.setIcon(new ImageIcon(ellipse_shape.getImage().getScaledInstance(ellipse_shape_newWidth, -1, java.awt.Image.SCALE_SMOOTH)));
		ellipse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				canMove = false;
				canvse.removeMouseListener(ml);
				canvse.removeMouseMotionListener(mx);
				numOfShape = 6;
				drawShape(canvse);
			}
		});
		ellipse.setBounds(340, 0, 85, 87);
		panel_1.add(ellipse);

		// *******************Line**********************

		JButton line = new JButton("");
		line.setBackground(Color.WHITE);
		ImageIcon line_shape = new ImageIcon("src/images/Line.png");
		int line_shape_scale = 10; 
		int line_shape_width = line_shape.getIconWidth();
		int line_shape_newWidth = line_shape_width / line_shape_scale;
		line.setIcon(new ImageIcon(line_shape.getImage().getScaledInstance(line_shape_newWidth, -1, java.awt.Image.SCALE_SMOOTH)));
		line.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				canMove = false;
				canvse.removeMouseListener(ml);
				canvse.removeMouseMotionListener(mx);
				numOfShape = 5;
				drawShape(canvse);
			}
		});
		line.setActionCommand("Line");
		line.setBounds(170, 0, 85, 87);
		panel_1.add(line);
		ImageIcon menu_shape = new ImageIcon("src/images/menu.png");
		int menu_shape_scale = 2; 
		int menu_shape_width = menu_shape.getIconWidth();
		int menu_shape_newWidth = menu_shape_width / menu_shape_scale;

		JButton btnNewButton = new JButton("");
		btnNewButton.setBackground(Color.WHITE);
		ImageIcon erase = new ImageIcon("src/images/remove.png");
		int line_scale = 2; 
		int line_width = erase.getIconWidth();
		int line_newWidth = line_width / line_scale;
		btnNewButton.setIcon(new ImageIcon(erase.getImage().getScaledInstance(line_newWidth, -1, java.awt.Image.SCALE_SMOOTH)));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (isselect) {
					dg.removeShape((eg.edu.alexu.csd.oop.draw.Shape) typeshape);
					selectedshape.remove(typeIndex);
					canvse.paint(canvse.getGraphics());
					ArrayList<eg.edu.alexu.csd.oop.draw.Shape> shapes = new ArrayList<eg.edu.alexu.csd.oop.draw.Shape>();
					shapes.addAll(dg.allShapes);
					dg.st.push(shapes);
					dg.firstUndo = true;
					dg.stRedo.clear();
					dg.refresh(canvse.getGraphics());
				}
				isselect=false;
			}
		});
		btnNewButton.setBounds(151, 86, 60, 47);
		panel_1.add(btnNewButton);

		JButton button = new JButton("");
		button.setBackground(Color.WHITE);
		ImageIcon btnundo = new ImageIcon("src/images/undo.png");
		int undo_scale = 1; 
		int undo_width = btnundo.getIconWidth();
		int undo_newWidth = undo_width / undo_scale;
		button.setIcon(new ImageIcon(btnundo.getImage().getScaledInstance(undo_newWidth, -1, java.awt.Image.SCALE_SMOOTH)));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (dg.st.isEmpty()) {
					canvse.paint(canvse.getGraphics());
				}
				dg.undo();

				canvse.paint(canvse.getGraphics());
				dg.refresh(canvse.getGraphics());
			}
		});
		button.setBounds(210, 86, 60, 47);
		panel_1.add(button);

		JButton button_1 = new JButton("");
		button_1.setBackground(Color.WHITE);
		ImageIcon btnredo = new ImageIcon("src/images/redo.png");
		int redo_scale = 1; 
		int redo_width = btnredo.getIconWidth();
		int redo_newWidth = redo_width / redo_scale;
		button_1.setIcon(new ImageIcon(btnredo.getImage().getScaledInstance(redo_newWidth, -1, java.awt.Image.SCALE_SMOOTH)));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (dg.stRedo.isEmpty()) {
					canvse.paint(canvse.getGraphics());

				}
				canvse.paint(canvse.getGraphics());
				dg.redo();
				dg.refresh(canvse.getGraphics());

			}
		});
		button_1.setBounds(262, 86, 60, 47);
		panel_1.add(button_1);
								JMenuBar mb = new JMenuBar();
								mb.setPreferredSize(new Dimension(0, 0));
								mb.setMinimumSize(new Dimension(0, 0));
								mb.setMaximumSize(new Dimension(0, 0));
								mb.setBounds(510, 0, 85, 87);
								panel_1.add(mb);
								mb.setBackground(Color.WHITE);
					
							// create a menu
							JMenu x = new JMenu("");
							mb.add(x);
							x.setBackground(Color.WHITE);
							x.setMinimumSize(new Dimension(100, 100));
							x.setMaximumSize(new Dimension(200, 200));
							x.setPreferredSize(new Dimension(80, 80));
							x.setIcon(new ImageIcon(menu_shape.getImage().getScaledInstance(menu_shape_newWidth, -1, java.awt.Image.SCALE_SMOOTH)));
							
									// create menuitems
									JMenuItem m1 = new JMenuItem(" ");
									m1.addMouseListener(new MouseAdapter() {
										@Override
										public void mousePressed(MouseEvent e) {
											color = Color.red;
											fillColor = Color.red;
											isfilled = true;
										}
									});
									m1.setBackground(Color.RED);
									JMenuItem m2 = new JMenuItem(" ");
									m2.addMouseListener(new MouseAdapter() {
										@Override
										public void mousePressed(MouseEvent e) {
											color = Color.blue;
											fillColor = Color.blue;
											isfilled = true;
										}
									});
									m2.setPreferredSize(new Dimension(60, 20));
									m2.setMaximumSize(new Dimension(200, 20));
									m2.setBackground(Color.BLUE);
									JMenuItem m3 = new JMenuItem(" ");
									m3.addMouseListener(new MouseAdapter() {
										@Override
										public void mousePressed(MouseEvent e) {
											color = Color.yellow;
											fillColor = Color.yellow;
											isfilled = true;
										}
									});
									m3.setBackground(Color.YELLOW);
									JMenuItem m4 = new JMenuItem(" ");
									m4.addMouseListener(new MouseAdapter() {
										@Override
										public void mousePressed(MouseEvent e) {
											color = Color.black;
											fillColor = Color.white;
											isfilled = true;
										}
									});
									m4.setBackground(Color.WHITE);
											// add menu items to menu
											x.add(m4);
											x.add(m1);
											x.add(m2);
											x.add(m3);
/* ************************************************Select And Move ********************************************* */
		Graphics2D gr = (Graphics2D) canvse.getGraphics();
		canvse.addMouseListener(mm = new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				if(!isfound) {
					dg.removeShape(typeshape);
					dg.addShape(sh);
					ArrayList<eg.edu.alexu.csd.oop.draw.Shape> shapes = new ArrayList<eg.edu.alexu.csd.oop.draw.Shape>();
					shapes.addAll(dg.allShapes);
					dg.st.pop();
					dg.st.push(shapes);
					dg.removeShape(sh);
					dg.addShape(typeshape);
					shapes = new ArrayList<eg.edu.alexu.csd.oop.draw.Shape>();
					shapes.addAll(dg.allShapes);
					dg.st.push(shapes);
					isfound=true;
				}
				
			}

			public void mousePressed(MouseEvent e) {
				canvse.paint(canvse.getGraphics());
				dg.refresh(canvse.getGraphics());
				isselect = false;
				isfound=true;
/* ************************************************Select ********************************************* */
				if (true) {
					selectedshape.clear();
					selectedshape.addAll((Collection<? extends ourshape>) dg.allShapes);
					for (int j = 0; j < selectedshape.size(); j++) {
						if (selectedshape.get(j).isIn(e.getX(), e.getY(), canvse)) {
							typeshape = selectedshape.get(j);
							isselect = true;
							typeIndex = j;
							pressed_shape = true;
							break;
						} else {
							dg.refresh(canvse.getGraphics());
						}
					}
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseClicked(MouseEvent e) {}
		});
		canvse.addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseMoved(MouseEvent e) {}
			@Override
			public void mouseDragged(MouseEvent e) {
/* ************************************************ Move ********************************************* */
				if (isselect && canMove) {
						
					if(isfound) {
						if(typeshape.getProperties().get("type")==1.0) {
						sh=new Square();
						sh.setProperties(selectedshape.get(typeIndex).getProperties());
						sh.setPosition(selectedshape.get(typeIndex).getPosition());
						sh.setColor(selectedshape.get(typeIndex).getColor());
						sh.setFillColor(selectedshape.get(typeIndex).getFillColor());
						sh.setSelectionBounds(selectedshape.get(typeIndex).getSelectionBounds().x, selectedshape.get(typeIndex).getSelectionBounds().y, selectedshape.get(typeIndex).getval().x, selectedshape.get(typeIndex).getval().y);	
						}else if(typeshape.getProperties().get("type")==2.0) {
							sh=new Rectangle();
							sh.setProperties(selectedshape.get(typeIndex).getProperties());
							sh.setPosition(selectedshape.get(typeIndex).getPosition());
							sh.setColor(selectedshape.get(typeIndex).getColor());
							sh.setFillColor(selectedshape.get(typeIndex).getFillColor());
							sh.setSelectionBounds(selectedshape.get(typeIndex).getSelectionBounds().x, selectedshape.get(typeIndex).getSelectionBounds().y, selectedshape.get(typeIndex).getval().x, selectedshape.get(typeIndex).getval().y);
							}else if(typeshape.getProperties().get("type")==4.0) {
								sh=new circle();
								sh.setProperties(selectedshape.get(typeIndex).getProperties());
								sh.setPosition(selectedshape.get(typeIndex).getPosition());
								sh.setColor(selectedshape.get(typeIndex).getColor());
								sh.setFillColor(selectedshape.get(typeIndex).getFillColor());
								sh.setSelectionBounds(selectedshape.get(typeIndex).getSelectionBounds().x, selectedshape.get(typeIndex).getSelectionBounds().y, selectedshape.get(typeIndex).getval().x, selectedshape.get(typeIndex).getval().y);
							
								}else if(typeshape.getProperties().get("type")==5.0) {
									sh=new line();
									
									sh.setPosition(selectedshape.get(typeIndex).getPosition());
									sh.setColor(selectedshape.get(typeIndex).getColor());
									sh.setFillColor(selectedshape.get(typeIndex).getFillColor());
									sh.setProperties(selectedshape.get(typeIndex).getProperties());
									sh.setSelectionBounds(selectedshape.get(typeIndex).getSelectionBounds().x, selectedshape.get(typeIndex).getSelectionBounds().y, selectedshape.get(typeIndex).getval().x, selectedshape.get(typeIndex).getval().y);
									}else if(typeshape.getProperties().get("type")==6.0) {
										sh=new ellipse();
										sh.setProperties(selectedshape.get(typeIndex).getProperties());
										sh.setPosition(selectedshape.get(typeIndex).getPosition());
										sh.setColor(selectedshape.get(typeIndex).getColor());
										sh.setFillColor(selectedshape.get(typeIndex).getFillColor());
										sh.setSelectionBounds(selectedshape.get(typeIndex).getSelectionBounds().x, selectedshape.get(typeIndex).getSelectionBounds().y, selectedshape.get(typeIndex).getval().x, selectedshape.get(typeIndex).getval().y);
		
										}else if(typeshape.getProperties().get("type")==3.0) {
											sh=new Triangle();
											sh.setPosition(selectedshape.get(typeIndex).getPosition());
											sh.setColor(selectedshape.get(typeIndex).getColor());
											sh.setFillColor(selectedshape.get(typeIndex).getFillColor());
											sh.setSelectionBounds(selectedshape.get(typeIndex).getSelectionBounds().x, selectedshape.get(typeIndex).getSelectionBounds().y, selectedshape.get(typeIndex).getval().x, selectedshape.get(typeIndex).getval().y);
											sh.setProperties(selectedshape.get(typeIndex).getProperties());
											sh.setDim(selectedshape.get(typeIndex).getwid_hie().x,selectedshape.get(typeIndex).getwid_hie().y);
	
											}
						
						dg.stRedo.clear();
						isfound=false;
					}
					int min = e.getX();
					int max = typeshape.getval().y;
					
					typeshape.setPosition(e.getPoint());
					if (typeshape.shaptype() == "Line") {
						line l=(line) typeshape;
						for (int i = 0; i < l.forLine.size(); i = i + 4) {
							if (l.forLine.get(i) > l.forLine.get(i + 2)) {
								if (l.forLine.get(i + 3) > l.forLine.get(i + 1)) {
									l.setPosition(e.getPoint());
									min = e.getX() - l.getval().x;
								} else if (l.forLine.get(i + 1) > l.forLine.get(i + 3)) {
									Point s = new Point();
									s.x = e.getX() + l.getval().x;
									s.y = e.getY() + l.getval().y;
									l.setPosition(s);
									min = e.getX();
								}
							} else if (l.forLine.get(i + 2) > l.forLine.get(i)) {
								if (l.forLine.get(i + 1) > l.forLine.get(i + 3)) {
									Point s = new Point();
									s.x = e.getX() - l.getval().x;
									s.y = e.getY() + l.getval().y;
									l.setPosition(s);
									min = e.getX() - l.getval().x;
								} else {
									l.setPosition(e.getPoint());
									min = e.getX();
									max = l.getval().y;
								}
							}
							l.setSelectionBounds(min, e.getY(), l.getval().x, max);
						}
					} else if (typeshape.shaptype() == "Triangle") {
						Triangle t=(Triangle) typeshape;
						for (int i = 0; i < t.forTriangle.size(); i = i + 4) {
							if (t.forTriangle.get(i) > t.forTriangle.get(i + 2)) {
								if (t.forTriangle.get(i + 1) < t.forTriangle.get(i + 3)) {
									t.setPosition(e.getPoint());
									t.setSelectionBounds(e.getX() - t.getval().x / 2, e.getY(),
											t.getval().x, t.getval().y);
									
								} else if (t.forTriangle.get(i + 1) > t.forTriangle.get(i + 3)) {
									Point s = new Point();
									s.x = e.getX() - t.getval().x / 2;
									s.y = e.getY() + t.getval().y;
									t.setPosition(s);
									t.setSelectionBounds(e.getX() - t.getval().x, e.getY(),
											t.getval().x, t.getval().y);
									
								}
							} else if (t.forTriangle.get(i) < t.forTriangle.get(i + 2)) {
								if (t.forTriangle.get(i + 1) < t.forTriangle.get(i + 3)) {
									t.setPosition(e.getPoint());
									t.setSelectionBounds(e.getX() - t.getval().x / 2, e.getY(),
											t.getval().x, t.getval().y);
									
								} else if (t.forTriangle.get(i + 1) >t.forTriangle.get(i + 3)) {
									Point s = new Point();
									s.x = e.getX() - t.getval().x / 2;
									s.y = e.getY() + t.getval().y;
									t.setPosition(s);
									t.setSelectionBounds(e.getX() - t.getval().x, e.getY(),
											t.getval().x, t.getval().y);
								}
							}
						}
					} else {
						typeshape.setSelectionBounds(e.getX(), e.getY(), typeshape.getval().x, typeshape.getval().y);
					}
					canvse.paint(canvse.getGraphics());
					dg.refresh(canvse.getGraphics());
				}
			}
		});
	}
	Point pos = new Point();
	Point lpos = new Point();
	int x1, x2, y1, y2;
	/* ************************************************Line And Ellipse ********************************************* */
	public void drawShape(Canvas canvse) {

		canvse.addMouseListener(ml = new MouseListener() {

			public void mouseClicked(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent e) {
			}

			public void mouseExited(MouseEvent e) {
			}

			public void mousePressed(MouseEvent e) {
				pos = e.getPoint();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
/* ************************************************Line ********************************************* */				
				if (numOfShape == 5) {
					Color cc = color;
					line l = new line();
					lpos = e.getPoint();
					l.setPosition(pos);
					l.setLastPosition(lpos);
					Graphics g = canvse.getGraphics();
					g.setColor(cc);
					l.setColor(cc);
					Point hr = new Point();
					hr = lpos;
					dg.addShape(l);
					l.draw(g);
					dg.refresh(g);
					l.forLine.add(pos.x);
					l.forLine.add(pos.y);
					l.forLine.add(lpos.x);
					l.forLine.add(lpos.y);
					int minx = pos.x;
					int miny = pos.y;
					int minxx = Math.abs(lpos.x - pos.x);
					int minyy = Math.abs(lpos.y - pos.y);
					if (pos.x > lpos.x) {
						minx = pos.x - minxx;
					}
					if (pos.y > lpos.y) {
						miny = lpos.y;
					}
					selectedshape.add(l);
					selectPositions.add(minx);
					selectPositions.add(miny);
					selectPositions.add(minxx);
					selectPositions.add(minyy);
					l.setSelectionBounds(minx, miny, minxx, minyy);
					ArrayList<eg.edu.alexu.csd.oop.draw.Shape> shapes = new ArrayList<eg.edu.alexu.csd.oop.draw.Shape>();
					shapes.addAll(dg.allShapes);
					dg.st.push(shapes);
					dg.firstUndo = true;
					canMove = true;
					dg.stRedo.clear();
					Map<String, Double> m = new HashMap<>();
					m.put("type", 5.0);
					m.put("positionx", pos.getX());
					m.put("positiony", pos.getY());
					m.put("lastPositionx", lpos.getX());
					m.put("color", (double) color.getRGB());
					m.put("fillColor", (double) fillColor.getRGB());
					m.put("lastPositiony", lpos.getY());
					m.put("boundx",(double)minx);
					m.put("boundy", (double)miny);
					m.put("boundlastPositionx", (double) minxx);
					m.put("boundlastPositiony", (double) minyy);
					l.setProperties(m);
					canvse.removeMouseListener(this);
					canvse.removeMouseMotionListener(mx);
				} else if (numOfShape == 6) {
/* ************************************************Ellipse ********************************************* */
					ellipse el = new ellipse();
					Point temp = new Point();
					temp.x = pos.x;
					temp.y = pos.y;
					x2 = e.getX();
					y2 = e.getY();
					if (y2 < pos.y) {
						temp.y = y2;
						y2 = pos.y;
					}
					if (x2 < pos.x) {
						temp.x = x2;
						x2 = pos.x;
					}
					el.setPosition(temp);
					Graphics g = canvse.getGraphics();
					g.setColor(color);
					el.setColor(color);
					if (isfilled) {
						el.setFillColor(fillColor);
					}
					Point hr = new Point();
					hr.x = x2;
					hr.y = y2;
					el.setLastPosition(hr);
					dg.addShape(el);
					el.draw(g);
					dg.refresh(g);
					selectedshape.add(el);
					selectPositions.add(temp.x);
					selectPositions.add(temp.y);
					selectPositions.add(Math.abs(temp.x - el.lastPos.x));
					selectPositions.add(Math.abs(temp.y - el.lastPos.y));
					el.setSelectionBounds(temp.x, temp.y, Math.abs(temp.x - el.lastPos.x),
							Math.abs(temp.y - el.lastPos.y));
					ArrayList<eg.edu.alexu.csd.oop.draw.Shape> shapes = new ArrayList<eg.edu.alexu.csd.oop.draw.Shape>();
					shapes.addAll(dg.allShapes);
					dg.st.push(shapes);
					dg.firstUndo = true;
					canMove = true;
					dg.stRedo.clear();
					Map<String, Double> m = new HashMap<>();
					m.put("type", 6.0);
					m.put("positionx", temp.getX());
					m.put("positiony", temp.getY());
					m.put("lastPositionx", hr.getX());
					m.put("lastPositiony", hr.getY());
					m.put("color", (double) color.getRGB());
					m.put("fillColor", (double) fillColor.getRGB());
					m.put("boundx",(double)temp.x);
					m.put("boundy", (double)temp.y);
					m.put("boundlastPositionx", (double) Math.abs(temp.x - el.lastPos.x));
					m.put("boundlastPositiony", (double) Math.abs(temp.y - el.lastPos.y));
					el.setProperties(m);
					canvse.removeMouseListener(this);
					canvse.removeMouseMotionListener(mx);
				}
			}
		});
		canvse.addMouseMotionListener(mx = new MouseMotionListener() {
			@Override
			public void mouseDragged(MouseEvent e) {

				if (numOfShape == 5) {
					Graphics g = canvse.getGraphics();
					g.setColor(color);
					lpos = e.getPoint();
					g.drawLine(pos.x, pos.y, lpos.x, lpos.y);
					canvse.repaint();
					dg.refresh(g);
					mx = this;
				} else if (numOfShape == 6) {
					Graphics g = canvse.getGraphics();
					g.setColor(color);
					Point dPos = new Point();
					dPos = e.getPoint();
					if (dPos.y < pos.y) {
						y1 = dPos.y;
						y2 = pos.y;
					} else {
						y1 = pos.y;
						y2 = dPos.y;
					}
					if (dPos.x < pos.x) {
						x1 = dPos.x;
						x2 = pos.x;
					} else {
						x1 = pos.x;
						x2 = dPos.x;
					}
					if (isfilled) {
						g.fillArc(x1, y1, Math.abs(x1 - x2), Math.abs(y1 - y2), 0, 360);
					}
					g.drawArc(x1, y1, Math.abs(x1 - x2), Math.abs(y1 - y2), 0, 360);
					canvse.repaint();
					dg.refresh(g);
					mx = this;
				}
			}

			public void mouseMoved(MouseEvent e) {
			}

		});

	}


	MouseListener mm;

}
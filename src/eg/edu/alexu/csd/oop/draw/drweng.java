package eg.edu.alexu.csd.oop.draw;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;


public class drweng implements DrawingEngine {
ArrayList <Shape> allShapes=new ArrayList<Shape>();
Stack<ArrayList<Shape>> st=new Stack<ArrayList<Shape>>();
Stack<ArrayList<Shape>> stRedo=new Stack<ArrayList<Shape>>();
boolean firstUndo=true;
	@Override
	public void refresh(Graphics canvas) {
		// TODO Auto-generated method stub
		for(int i=0;i<allShapes.size();i++) {
			Shape s=allShapes.get(i);
			s.draw(canvas);	
	
		}
		
	}

	@Override
	public void addShape(Shape shape) {
		allShapes.add(shape);
	}

	@Override
	public void removeShape(Shape shape) {
		// TODO Auto-generated method stub
		for(int i=0;i<allShapes.size();i++) {
			if(allShapes.get(i).getPosition().x==shape.getPosition().x) {
				allShapes.remove(i);
			}
		}
	}

	@Override
	public void updateShape(Shape oldShape, Shape newShape) {
		// TODO Auto-generated method stub
		oldShape=newShape;
	}

	@Override
	public Shape[] getShapes() {
		// TODO Auto-generated method stub
	return (Shape[]) allShapes.toArray();
	}

	@Override
	public List<Class<? extends Shape>> getSupportedShapes() {
		// TODO Auto-generated method stub
		List<Class<? extends Shape>> ss=new ArrayList<Class<? extends Shape>>();
		ss.add(Square.class);
		ss.add(Rectangle.class);
		ss.add(Triangle.class);
		ss.add(circle.class);
		ss.add(line.class);
		ss.add(ellipse.class);
		return ss;
	}

	@Override
	public void undo() {			
		allShapes.clear();
		if(st.isEmpty()) {					
		}else {			    		
			stRedo.add(st.pop());
			if(!st.isEmpty()) {
			allShapes.addAll(st.peek());
			}
		}	
		firstUndo=false;
	}
	@Override
	public void redo() {
		// TODO Auto-generated method stub
		if(!stRedo.isEmpty()&&!firstUndo) {
			allShapes.clear();
			st.add(stRedo.peek());
			allShapes.addAll(stRedo.pop());
		}
	}

	@Override
	public void save(String path) {
		// TODO Auto-generated method stub
		String check=path.substring(path.length()-4);
		if(check.equals(".xml")) {
		try {
			FileOutputStream fos= new FileOutputStream(new File(path));
			XMLEncoder encoder=new XMLEncoder(fos);
			encoder.writeObject(allShapes);
			encoder.close();
			fos.close();
			for(int i=0;i<allShapes.size();i++) {
				System.out.println(allShapes.get(i).getPosition());
			}
		}catch(IOException ex) {
			ex.printStackTrace();
		}
		}else {
		
		String Json="{";
		for(int i=0;i<allShapes.size();i++) {

			String ss=""+allShapes.get(i).getProperties().get("type")+","+allShapes.get(i).getProperties().get("positionx")+","+allShapes.get(i).getProperties().get("positiony")
					+","+allShapes.get(i).getProperties().get("lastPositionx")+","+allShapes.get(i).getProperties().get("lastPositiony");
			String colors=""+allShapes.get(i).getProperties().get("color")+","+allShapes.get(i).getProperties().get("fillColor");
			String bounds=""+allShapes.get(i).getProperties().get("boundx")+","+allShapes.get(i).getProperties().get("boundy")
					+","+allShapes.get(i).getProperties().get("boundlastPositionx")+","+allShapes.get(i).getProperties().get("boundlastPositiony");

			Json+=("\""+i+"\""+":[\""+ss+"\",\""+colors+"\","+"\""+bounds+"\"]");
			if((i+1)!=allShapes.size()) {
				Json+=",";
			}
			
			}
		Json+="}";
			System.out.println(Json);
		
			try(FileWriter file = new FileWriter(path)){
				file.write(Json);
				file.flush();
			}catch(IOException e) {
				 e.printStackTrace();
		            System.out.println("faill");
			}
		
		}
		
	}

	@Override
	public void load(String path) {
		// TODO Auto-generated method stub
		String check=path.substring(path.length()-4);
		System.out.println(check);
		if(check.equals(".xml")) {
			allShapes.clear();
			try {
				FileInputStream fis= new FileInputStream(new File(path));
				XMLDecoder decoder=new XMLDecoder(fis);
				allShapes=((ArrayList<Shape>)decoder.readObject());
				decoder.close();
				fis.close();
			}catch(IOException ex) {
				ex.printStackTrace();
			}
		}else {
			allShapes.clear();
			String Json;
			
			try {

				FileReader fr= new FileReader(path);
				BufferedReader br =new BufferedReader(fr);
				Json=br.readLine();
				System.out.println(Json);
				int first=0,last;
				for(int i=0;i<Json.length();i++) {
					if(Json.charAt(i)=='[') {
						first=i+2;
					}if(Json.charAt(i)==']') {
						last=i-1;
						String allS=Json.substring(first, last);

						String[] arr=allS.split("\",\"");
						String ss=arr[0];
						String colors=arr[1];
						String bound=arr[2];

						String[] color=colors.split(",");
						String[] bounds=bound.split(",");
						String[] pos=ss.split(",");

						System.out.println(color[0]+"  "+color[1]);
						Map<String,Double> m=new HashMap<>();
						m.put("positionx",Double.valueOf(pos[1]));
						m.put("positiony",Double.valueOf(pos[2]));
						m.put("lastPositionx",Double.valueOf(pos[3]));
						m.put("lastPositiony",Double.valueOf(pos[4]));
						m.put("color",Double.valueOf(color[0]));
		    			m.put("fillColor",Double.valueOf(color[1]));
		    			m.put("boundx",Double.valueOf(bounds[0]));
						m.put("boundy", Double.valueOf(bounds[1]));
						m.put("boundlastPositionx", Double.valueOf(bounds[2]));
						m.put("boundlastPositiony",Double.valueOf(bounds[3]));
						

						if(pos[0].equals("1.0")) {
							m.put("type", 1.0);
							Square sq=new Square();
							Point pp=new Point();
							pp.x=(int) Math.round(Double.valueOf(pos[1]));
							pp.y=(int) Math.round(Double.valueOf(pos[2]));
							sq.setProperties(m);
							sq.setPosition(pp);
							pp=new Point();
							pp.x=(int) Math.round(Double.valueOf(pos[3]));
							pp.y=(int) Math.round(Double.valueOf(pos[4]));
							sq.setDim(pp.x, pp.y);
							sq.setColor(Color.getColor((m.get("color").toString()),(int)Math.round(Double.valueOf(color[0]))));
							sq.setFillColor(Color.getColor((m.get("fillColor").toString()),(int)Math.round(Double.valueOf(color[1]))));
							allShapes.add(sq);
						}else if(pos[0].equals("2.0")) {
							m.put("type", 2.0);
							Rectangle r=new Rectangle();
							Point pp=new Point();
							pp.x=(int) Math.round(Double.valueOf(pos[1]));
							pp.y=(int) Math.round(Double.valueOf(pos[2]));
							r.setProperties(m);
							r.setPosition(pp);
							pp=new Point();
							pp.x=(int) Math.round(Double.valueOf(pos[3]));
							pp.y=(int) Math.round(Double.valueOf(pos[4]));
							r.setDim(pp.x, pp.y);
							r.setColor(Color.getColor((m.get("color").toString()),(int)Math.round(Double.valueOf(color[0]))));
							r.setFillColor(Color.getColor((m.get("fillColor").toString()),(int)Math.round(Double.valueOf(color[1]))));
							allShapes.add(r);
				
						}else if(pos[0].equals("3.0")) {
							m.put("type", 3.0);
							Triangle t=new Triangle();
							Point pp=new Point();
							pp.x=(int) Math.round(Double.valueOf(pos[1]));
							pp.y=(int) Math.round(Double.valueOf(pos[2]));
							t.setProperties(m);
							t.setPosition(pp);
							pp=new Point();
							pp.x=(int) Math.round(Double.valueOf(pos[3]));
							pp.y=(int) Math.round(Double.valueOf(pos[4]));
							t.setDim(pp.x, pp.y);
							t.setColor(Color.getColor((m.get("color").toString()),(int)Math.round(Double.valueOf(color[0]))));
							t.setFillColor(Color.getColor((m.get("fillColor").toString()),(int)Math.round(Double.valueOf(color[1]))));
							allShapes.add(t);
							
						}
						else if(pos[0].equals("4.0")) {
							m.put("type", 4.0);
							circle c=new circle();
							Point pp=new Point();
							pp.x=(int) Math.round(Double.valueOf(pos[1]));
							pp.y=(int) Math.round(Double.valueOf(pos[2]));
							
							c.setProperties(m);
							c.setPosition(pp);
							pp=new Point();
							pp.x=(int) Math.round(Double.valueOf(pos[3]));
							pp.y=(int) Math.round(Double.valueOf(pos[4]));
//							c.setDim(pp);
							c.setColor(Color.getColor((m.get("color").toString()),(int)Math.round(Double.valueOf(color[0]))));
							c.setFillColor(Color.getColor((m.get("fillColor").toString()),(int)Math.round(Double.valueOf(color[1]))));
							allShapes.add(c);
							
		
						}else if(pos[0].equals("5.0")) {
							m.put("type", 5.0);
							line l=new line();
							Point pp=new Point();
							pp.x=(int) Math.round(Double.valueOf(pos[1]));
							pp.y=(int) Math.round(Double.valueOf(pos[2]));
							
							l.setProperties(m);
							l.setPosition(pp);
							pp=new Point();
							pp.x=(int) Math.round(Double.valueOf(pos[3]));
							pp.y=(int) Math.round(Double.valueOf(pos[4]));
							l.setLastPosition(pp);
							l.setColor(Color.getColor((m.get("color").toString()),(int)Math.round(Double.valueOf(color[0]))));
							l.setFillColor(Color.getColor((m.get("fillColor").toString()),(int)Math.round(Double.valueOf(color[1]))));
							allShapes.add(l);
						
						}
						else if(pos[0].equals("6.0")) {
							m.put("type", 6.0);
							ellipse el=new ellipse();
							Point pp=new Point();
							pp.x=(int) Math.round(Double.valueOf(pos[1]));
							pp.y=(int) Math.round(Double.valueOf(pos[2]));
							
							el.setProperties(m);
							el.setPosition(pp);
							pp=new Point();
							pp.x=(int) Math.round(Double.valueOf(pos[3]));
							pp.y=(int) Math.round(Double.valueOf(pos[4]));
							el.setColor(Color.getColor((m.get("color").toString()),(int)Math.round(Double.valueOf(color[0]))));
							el.setFillColor(Color.getColor((m.get("fillColor").toString()),(int)Math.round(Double.valueOf(color[1]))));
							allShapes.add(el);
		
						}	
					}	
				}
			}
			catch(Exception e) {e.printStackTrace();}
		}
		ArrayList<eg.edu.alexu.csd.oop.draw.Shape> shapes = new ArrayList<eg.edu.alexu.csd.oop.draw.Shape>();
		shapes.addAll(allShapes);
		st.push(shapes);
	}
}

package eg.edu.alexu.csd.oop.draw;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class line extends ourshape{
	Point pos=new Point();
	Point lpos=new Point();
	Color c=Color.black;
	Map<String, Double> Properties=new HashMap<String, Double>();
	ArrayList<Integer> forLine = new ArrayList<Integer>();
	int width;
	int height;
	@Override
	public java.awt.Point getPosition(){
		return pos;
	}
	@Override
	public void setPosition(Point position) {
		pos=position;
	}
	@Override
	public void setProperties(Map<String, Double> properties) {
		Properties=properties;
		lpos.x=(int)Math.round(Properties.get("lastPositionx"));
		lpos.y=(int)Math.round(Properties.get("lastPositiony"));
		width=pos.x-lpos.x;
		height=pos.y-lpos.y;
		this.selectx= (int)Math.round(Properties.get("boundx"));
		this.selecty = (int)Math.round(Properties.get("boundy"));
		this.selectlastx = (int)Math.round(Properties.get("boundlastPositionx"));
		this.selectlasty = (int)Math.round(Properties.get("boundlastPositiony"));
		forLine.add((int)Math.round(Properties.get("positionx")));
		forLine.add((int)Math.round(Properties.get("positiony")));
		forLine.add(lpos.x);
		forLine.add(lpos.y);
	}

	@Override
	public Map<String, Double> getProperties() {
		if(!Properties.isEmpty()) {
			
			return Properties;
		}
		return null;
	}

	@Override
	public void setColor(Color color) {
		c=color;
	}

	@Override
	public Color getColor() {
		return c;
	}

	@Override
	public void setFillColor(Color color) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Color getFillColor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void draw(Graphics canvas) {
		canvas.setColor(c);
		canvas.drawLine(pos.x,pos.y,pos.x-width,pos.y-height);
	}
	public Object clone() throws CloneNotSupportedException{
		return c;
		
	}
	public void setLastPosition(Point position) {
		lpos=position;
		width=pos.x-lpos.x;
		height=pos.y-lpos.y;
		
	}
	int selectx,selecty,selectlastx,selectlasty;
	public void setSelectionBounds(int x,int y, int w,int h) {
		selectx=x;
		selecty=y;
		selectlastx=w;
		selectlasty=h;
		Properties.put("positionx",(double)pos.x);
		Properties.put("positiony", (double)pos.y);
		Properties.put("lastPositionx", (double) pos.x-width);
		Properties.put("lastPositiony", (double) pos.y-height);
		Properties.put("boundx",(double)x);
		Properties.put("boundy", (double) y);
		Properties.put("boundlastPositionx", (double) w);
		Properties.put("boundlastPositiony", (double) h);
	}
	
	public Point getval() {
		Point w=new Point();
		w.x=selectlastx;
		w.y=selectlasty;
		return w;
	}
	public Point getSelectionBounds() {
		Point f=new Point();
		f.x=selectx;
		f.y=selecty;
		return f;
	}
	public String shaptype(){
		return "Line";
	}
	public boolean isIn(int x,int y,Canvas canvse) {
		 if(x> this.selectx&&x<this.selectx+this.selectlastx && y>this.selecty&&y<this.selectlasty+this.selecty) {
			Graphics g=canvse.getGraphics();
			g.drawRect(this.selectx-3,this.selecty-3, 5, 5);
			g.drawRect(this.selectx+this.selectlastx-3,this.selecty-3 , 5, 5);
			g.drawRect(this.selectx-3,this.selectlasty+this.selecty-3, 5, 5);
			g.drawRect(this.selectx+this.selectlastx-3,this.selectlasty+this.selecty-3, 5, 5);
			g.drawRect((this.selectx+this.selectlastx/2)-3,(this.selecty-3) , 5, 5);
			g.drawRect((this.selectx+this.selectlastx/2)-3,(this.selectlasty+this.selecty-3) , 5, 5);
			g.drawRect(this.selectx-3,(this.selecty+this.selectlasty/2)-3 , 5, 5);
			g.drawRect(this.selectx+this.selectlastx-3,(this.selecty+this.selectlasty/2)-3 , 5, 5);		
			 return true;
		 }
		return false;
	}
}

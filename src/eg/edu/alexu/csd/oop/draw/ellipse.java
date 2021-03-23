package eg.edu.alexu.csd.oop.draw;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

public class ellipse extends ourshape {
	Point pos =new Point();
	Point lastPos =new Point();
	Color c=Color.black;
	Color fillColor=Color.white;
	Map<String, Double> Properties=new HashMap<String, Double>();
	int width;
	int height;
	
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
		lastPos.x=(int)Math.round(Properties.get("lastPositionx"));
		lastPos.y=(int)Math.round(Properties.get("lastPositiony"));
		this.selectx= (int)Math.round(Properties.get("boundx"));
		this.selecty = (int)Math.round(Properties.get("boundy"));
		this.selectlastx = (int)Math.round(Properties.get("boundlastPositionx"));
		this.selectlasty = (int)Math.round(Properties.get("boundlastPositiony"));
		width=(int)Math.round(Properties.get("boundlastPositionx"));
		height=(int)Math.round(Properties.get("boundlastPositiony"));
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
		fillColor=color;
		
	}

	@Override
	public Color getFillColor() {
		// TODO Auto-generated method stub
		return fillColor;
	}

	@Override
	public void draw(Graphics canvas) {
		if(fillColor.getRGB()!=-1) {
			canvas.setColor(fillColor);
			canvas.fillArc(pos.x, pos.y, width, height, 0, 360);
		}
		canvas.setColor(c);
        canvas.drawArc(pos.x, pos.y,width,height, 0, 360);
	}
	public Object clone() throws CloneNotSupportedException{
		return c;
		
	}
	public void setLastPosition(Point position) {
		lastPos=position;
		width=Math.abs(pos.x-lastPos.x);
		height=Math.abs(pos.y-lastPos.y);
	}
	public Point getwid_hie() {

		return lastPos;
	}
	int selectx,selecty,selectlastx,selectlasty;
	public void setSelectionBounds(int x,int y, int w,int h) {
		selectx=x;
		selecty=y;
		selectlastx=w;
		selectlasty=h;
		Properties.put("positionx",(double)this.pos.x);
		Properties.put("positiony", (double)this.pos.y);
		Properties.put("lastPositionx", (double) this.lastPos.x);
		Properties.put("lastPositiony", (double) this.lastPos.y);
		Properties.put("color", (double) c.getRGB());
		Properties.put("fillColor", (double) fillColor.getRGB());
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
		return "Ellipse";
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
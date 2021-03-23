package eg.edu.alexu.csd.oop.draw;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

public class circle extends ourshape {

	private int x,y, x2,y2;
	private int radios=0;
	 
	Color c=Color.black;
	Color fillColor=Color.white;
	Map<String, Double> Properties=new HashMap<String, Double>();
	int selectx,selecty,selectlastx,selectlasty;
	public java.awt.Point getPosition(){
		Point ans =new Point();
		ans.x=x;
		ans.y=y;
		return ans;
	}

	@Override
	public void setPosition(Point position) {
		x=position.x;
		y=position.y;
	}

	@Override
	public void setProperties(Map<String, Double> properties) {
		Properties=properties;
		x2=(int)Math.round(Properties.get("lastPositionx"));
		y2=(int)Math.round(Properties.get("lastPositiony"));
		this.selectx= (int)Math.round(Properties.get("boundx"));
		this.selecty = (int)Math.round(Properties.get("boundy"));
		this.selectlastx = (int)Math.round(Properties.get("boundlastPositionx"));
		this.selectlasty = (int)Math.round(Properties.get("boundlastPositiony"));
		radios=(int)Math.round(Properties.get("boundlastPositionx"));
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
		return fillColor;
	}

	@Override
	public void draw(Graphics canvas) {
		if(fillColor.getRGB()!=-1) {
			canvas.setColor(fillColor);
			canvas.fillArc(x, y,radios,radios, 0, 360);
		}
		canvas.setColor(c);
		canvas.drawArc(x, y, radios, radios, 0, 360);
	}
	public void setDim(Point wi) {
		x2=wi.x;
		y2=wi.y;
		double k=Math.sqrt(Math.pow(this.y-this.y2, 2)+Math.pow(this.x-this.x2, 2));
		radios=(int) Math.round(k);		
	}
	public Object clone() throws CloneNotSupportedException{
		return c;
		
	}
	public void setSelectionBounds(int x,int y, int w,int h) {
		selectx=x;
		selecty=y;
		selectlastx=w;
		selectlasty=h;
		Properties.put("positionx",(double)this.x);
		Properties.put("positiony", (double)this.y);
		Properties.put("lastPositionx", (double) this.x2);
		Properties.put("lastPositiony", (double) this.y2);
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
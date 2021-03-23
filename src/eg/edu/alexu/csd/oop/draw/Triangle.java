package eg.edu.alexu.csd.oop.draw;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Triangle extends ourshape{
	private int posx,posy,wid=0,hie=0;
	Map<String, Double> Properties=new HashMap<String, Double>();
	Color c=Color.black;
	Color fillColor=Color.white;
	ArrayList<Integer> forTriangle = new ArrayList<Integer>();
	private int ln1;
	private int ln2;
	private int ln3;
	@Override
	public void setPosition(Point position) {
		// TODO Auto-generated method stub
		posx=position.x;
		posy=position.y;
		
	}

	@Override
	public Point getPosition() {
		// TODO Auto-generated method stub
		Point ans =new Point();
		ans.x=posx;
		ans.y=posy;
		return ans;
	}

	@Override
	public void setProperties(Map<String, Double> properties) {
		Properties=properties;
		wid=(int)Math.round(Properties.get("lastPositionx"));
		hie=(int)Math.round(Properties.get("lastPositiony"));
		this.selectx= (int)Math.round(Properties.get("boundx"));
		this.selecty = (int)Math.round(Properties.get("boundy"));
		this.selectlastx = (int)Math.round(Properties.get("boundlastPositionx"));
		this.selectlasty = (int)Math.round(Properties.get("boundlastPositiony"));
		forTriangle.clear();
		forTriangle.add((int)Math.round(Properties.get("positionx")));
		forTriangle.add((int)Math.round(Properties.get("positiony")));
		forTriangle.add(wid);
		forTriangle.add(hie);
		ln3=posx;
		ln1=wid-posx;
		ln2=hie-posy;
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
		if(ln3>wid) {
			if(fillColor.getRGB()!=-1) {
				canvas.setColor(fillColor);
				int x1=posx,x2= ln1+posx,x3=this.posx+Math.abs(this.posx- (ln1+posx)),y1=posy,y2=ln2+posy,y3=ln2+posy;
				int[] xs= {x1,x2,x3};
				int[] ys= {y1,y2,y3};
				canvas.fillPolygon(xs, ys, 3);
			}
		canvas.setColor(c);
		canvas.drawLine(posx, posy, ln1+posx, ln2+posy);
		canvas.drawLine(ln1+posx, ln2+posy,posx+Math.abs(posx-(ln1+posx)),ln2+posy);
		canvas.drawLine(posx+Math.abs(posx-(ln1+posx)),ln2+posy,posx,posy);
		}else if(ln3<wid) {
			if(fillColor.getRGB()!=-1) {
				canvas.setColor(fillColor);
				int x1=posx,x2=ln1+posx,x3=this.posx-Math.abs(this.posx-(ln1+posx)),y1=posy,y2=ln2+posy,y3=ln2+posy;
				int[] xs= {x1,x2,x3};
				int[] ys= {y1,y2,y3};
				canvas.fillPolygon(xs, ys, 3);
			}
			canvas.setColor(c);
			canvas.drawLine(posx, posy, ln1+posx, ln2+posy);
			canvas.drawLine(ln1+posx, ln2+posy,posx-Math.abs(posx-(ln1+posx)),ln2+posy);
			canvas.drawLine(posx-Math.abs(posx-(ln1+posx)),ln2+posy,posx,posy);
		}
	}
	public Object clone() throws CloneNotSupportedException{
		return null;
		
	}
	public void setDim(int wi,int hi ) {
		this.wid=wi;
		this.hie=hi;
		ln3=posx;
		ln1=wid-posx;
		ln2=hie-posy;
	}
	public Point getwid_hie() {
		Point s=new Point();
		s.x=wid;
		s.y=hie;
		return s;
	}
 
	int selectx,selecty,selectlastx,selectlasty;
	public void setSelectionBounds(int x,int y, int w,int h) {
		selectx=x;
		selecty=y;
		selectlastx=w;
		selectlasty=h;
		Properties.put("positionx",(double)posx);
		Properties.put("positiony", (double)posy);
		Properties.put("lastPositionx", (double) ln1+posx);
		Properties.put("lastPositiony", (double) ln2+posy);
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
		return "Triangle";
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
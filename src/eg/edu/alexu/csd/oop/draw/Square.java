package eg.edu.alexu.csd.oop.draw;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

public class Square extends ourshape {
	private int posx, posy, wid, hie;
	Map<String, Double> Properties=new HashMap<String, Double>();
	Color c=Color.black;
	Color fillColor=Color.white;
	private int selectx, selecty, selectlastx, selectlasty;

	@Override
	public void setPosition(Point position) {
		// TODO Auto-generated method stub
		posx = position.x;
		posy = position.y;
	}

	@Override
	public Point getPosition() {
		// TODO Auto-generated method stub
		Point ans = new Point();
		ans.x = posx;
		ans.y = posy;
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
			canvas.fillRect(this.posx, this.posy,this.wid,  this.hie);
		}
		canvas.setColor(c);
		canvas.drawRect(this.posx, this.posy, this.wid, this.hie);
	}

	public Object clone() throws CloneNotSupportedException {
		return null;

	}

	public void setDim(int wi, int hi) {
		this.wid = wi;
		this.hie = hi;
	}

	public Point getwid_hie() {
		Point p = new Point();
		p.x = wid;
		p.y = hie;
		return p;
	}

	public void setSelectionBounds(int x, int y, int w, int h) {
		selectx = x;
		selecty = y;
		selectlastx = w;
		selectlasty = h;
		Properties.put("positionx",(double)posx);
		Properties.put("positiony", (double)posy);
		Properties.put("lastPositionx", (double) wid);
		Properties.put("lastPositiony", (double) hie);
		Properties.put("color", (double) c.getRGB());
		Properties.put("fillColor", (double) fillColor.getRGB());
		Properties.put("boundx",(double)x);
		Properties.put("boundy", (double) y);
		Properties.put("boundlastPositionx", (double) w);
		Properties.put("boundlastPositiony", (double) h);
		
	}

	public Point getSelectionBounds() {
		Point f = new Point();
		f.x = selectx;
		f.y = selecty;
		return f;
	}

	public Point getval() {
		Point w = new Point();
		w.x = selectlastx;
		w.y = selectlasty;
		return w;
	}

	public String shaptype() {
		return null;
	}

	public boolean isIn(int x, int y, Canvas canvse) {
		if (x > this.selectx && x < this.selectx + this.selectlastx && y > this.selecty
				&& y < this.selectlasty + this.selecty) {
			Graphics g = canvse.getGraphics();
			g.drawRect(this.posx - 3, this.posy - 3, 5, 5); // R T
			g.drawRect(this.posx + this.wid - 3, this.posy - 3, 5, 5); // L T
			g.drawRect(this.posx - 3, this.hie + this.posy - 3, 5, 5); // R D
			g.drawRect(this.posx + this.wid - 3, this.hie + this.posy - 3, 5, 5); // L D
			g.drawRect((this.posx + this.wid / 2) - 3, (this.posy - 3), 5, 5); // C T
			g.drawRect((this.posx + this.wid / 2) - 3, (this.hie + this.posy - 3), 5, 5); // C D
			g.drawRect(this.posx - 3, (this.posy + this.hie / 2) - 3, 5, 5); // L C
			g.drawRect(this.posx + this.wid - 3, (this.posy + this.hie / 2) - 3, 5, 5); // R C
			return true;
		}
		return false;
	}
}

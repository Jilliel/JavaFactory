package robotsim.model;
import java.io.Serializable;
import fr.tp.inf112.projects.canvas.model.Shape;
import fr.tp.inf112.projects.canvas.model.Style;
import fr.tp.inf112.projects.canvas.model.Figure;

public abstract class Component implements Figure, Serializable{

	private static final long serialVersionUID = 202505091314L;
	
	private int x;
	private int y;
	private String name;
	private Factory factory;
	
	
	public Component(int x, int y, String name, Factory factory) {
		super();
		this.x = x;
		this.y = y;
		this.name = name;
		this.factory = factory;
	}
	
	public void behave() {
		
	}
	
	public abstract Shape getShape();
	@Override
	public abstract Style getStyle();
	

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int getxCoordinate() {
		// TODO Auto-generated method stub
		return this.x;
	}

	@Override
	public int getyCoordinate() {
		// TODO Auto-generated method stub
		return this.y;
	}	
}

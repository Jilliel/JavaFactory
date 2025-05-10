package robotsim.model;
import java.io.Serializable;
import fr.tp.inf112.projects.canvas.model.Shape;
import fr.tp.inf112.projects.canvas.model.Style;
import fr.tp.inf112.projects.canvas.model.Figure;

public abstract class Component implements Figure, Serializable{

	private static final long serialVersionUID = 202505091314L;
	
	private Position position;
	private String name;
	private Factory factory;
	
	
	public Component(Position position, String name, Factory factory) {
		super();
		this.position = position;
		this.name = name;
		this.factory = factory;
	}
	
	public void behave() {
	}

	public void setPosition(Position position) {
		this.position = position;
	}
	
	public Position getPosition() {
		return this.position;
	}
	
	public Factory getFactory() {
		return this.factory;
	}
	
	public abstract Shape getShape();
	@Override
	public abstract Style getStyle();
	

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
		return this.position.getX();
	}

	@Override
	public int getyCoordinate() {
		// TODO Auto-generated method stub
		return this.position.getY();
	}	
}

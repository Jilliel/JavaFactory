package robotsim.model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import fr.tp.inf112.projects.canvas.model.Shape;
import fr.tp.inf112.projects.canvas.model.Style;
import shapes.ComponentStroke;
import shapes.ComponentStyle;
import shapes.Rectangle;

public class ProductionArea extends Component implements Serializable{
	
	private static final long serialVersionUID = 202505090920L;
	private List<Washer> materials;
	private List<ProductionMachine> machines;
	private int width;
	private int height;
	private static final ComponentStyle style = new ComponentStyle(null, new ComponentStroke(null, 1, new float[] {5,5} ));
	
	public ProductionArea(Position position, int width, int height, String name, Factory factory) {
		super(position, name, factory);
		this.width = width;
		this.height = height;
		this.materials = new ArrayList<Washer>();
		this.machines = new ArrayList<ProductionMachine>();
	}
	
	public void behave() {
		for (Washer washer : this.materials) {
			washer.behave();
		}
		for (ProductionMachine machine : this.machines) {
			machine.behave();
			if (machine.getTimeLeft() == 0) {
				// TODO Check if a washer can be produced (otherwise it's just a pile of washers?)
				if (this.getFactory().canProduce(machine)) {
					// System.out.println("Producing washer");
					machine.setTimeLeft(ProductionMachine.getTimeToProduce());
					Position washerposition = new Position(machine.getxCoordinate() + (machine.getWidth()/2) - ProductionMachine.getWasherRadius(),
													machine.getyCoordinate() + (machine.getHeight()/2)- ProductionMachine.getWasherRadius());
					this.materials.add(new Washer(washerposition, ProductionMachine.getWasherRadius(), ProductionMachine.getWasherWeight(), "Washer", this.getFactory()));
					this.getFactory().notifyObservers();
				}
			}
			else {
				machine.setTimeLeft(machine.getTimeLeft()-1);
			}
		}
		this.getFactory().notifyObservers();
	}
	
	public List<Washer> getMaterials() {
		return materials;
	}

	public void setMaterials(List<Washer> materials) {
		this.materials = materials;
	}
	
	public void addMaterial(Washer washer) {
		this.materials.add(washer);
	}
	
	public List<ProductionMachine> getMachines() {
		return machines;
	}

	public void setMachines(List<ProductionMachine> machines) {
		this.machines = machines;
	}
	
	public void addMachine(ProductionMachine machine) {
		this.machines.add(machine);
	}
	
	public List<Component> getComponents() {
		ArrayList <Component> result = new ArrayList<Component>();
		/* Add itself */
		result.add(this);
		/* Add the materials */
		result.addAll(this.materials);
		/* Add the machines */
		result.addAll(this.machines);
		
		return result;
	}
	
	@Override
	public Style getStyle() {
		return ProductionArea.style;
	}

	@Override
	public Shape getShape() {
		// TODO Auto-generated method stub
		return new Rectangle(this.width, this.height);
	}
	
	public void removeWasher(Washer washer) {
		this.materials.remove(washer);
	}
}

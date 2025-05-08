package robotsim.model;
import java.util.ArrayList;
import shapes.Rectangle;

public class ProductionArea extends Component {
	
	private ArrayList<Washer> materials;
	private ArrayList<ProductionMachine> machines;
	
	public ProductionArea(int x, int y, int width, int height, String name) {
		super(x, y, new Rectangle(width, height), name);
		this.materials = new ArrayList<Washer>();
		this.machines = new ArrayList<ProductionMachine>();
	}

	public ArrayList<Washer> getMaterials() {
		return materials;
	}

	public void setMaterials(ArrayList<Washer> materials) {
		this.materials = materials;
	}
	
	public void addMaterial(Washer washer) {
		this.materials.add(washer);
	}
	
	public ArrayList<ProductionMachine> getMachines() {
		return machines;
	}

	public void setMachines(ArrayList<ProductionMachine> machines) {
		this.machines = machines;
	}
	
	public void addMachine(ProductionMachine machine) {
		this.machines.add(machine);
	}
	
	public ArrayList<Component> getComponents() {
		ArrayList <Component> result = new ArrayList<Component>();
		/* Add itself */
		result.add(this);
		/* Add the materials */
		result.addAll(this.materials);
		/* Add the machines */
		result.addAll(this.machines);
		
		return result;
	}
	
	public void behave() {
		for (int i=0; i < this.materials.size(); i++) {
			this.materials.get(i).behave();
		}
		for (int i=0; i < this.machines.size(); i++) {
			this.machines.get(i).behave();
		}
	}
}

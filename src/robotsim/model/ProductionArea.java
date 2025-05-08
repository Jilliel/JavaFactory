package robotsim.model;
import java.util.ArrayList;
import shapes.Rectangle;

public class ProductionArea extends Component {
	
	private ArrayList<Washer> materials;

	public ProductionArea(int x, int y, int width, int height, String name, ArrayList<Washer> materials) {
		super(x, y, new Rectangle(width, height), name);
		this.materials = materials;
	}

	public ArrayList<Washer> getMaterials() {
		return materials;
	}

	public void setMaterials(ArrayList<Washer> materials) {
		this.materials = materials;
	}
	
	public void addMetrial(Washer washer) {
		this.materials.add(washer);
	}
}

package robotsim.model;
import java.util.List;

public class DijkstraPathFinder implements FactoryPathFinder{
	
	private Factory factory;
	
	public DijkstraPathFinder(Factory factory) {
		super();
		this.factory = factory;
	}
	
	public List<Position> findPath(Position start, Position end) {
		return null;
	}
	
}

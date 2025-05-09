package robotsim.model;
import java.util.List;

public interface FactoryPathFinder {
	
	public List<Position> findPath(Position start, Position end);
	
}

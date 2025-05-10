package robotsim.model;
import java.util.List;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.GraphPath;

public class DijkstraPathFinder implements FactoryPathFinder{
	
	private final static int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	private Factory factory;

	private Graph<Position, DefaultEdge> graphe;
	private DijkstraShortestPath<Position, DefaultEdge> pathfinder;

	public DijkstraPathFinder(Factory factory) {
		super();
		this.factory = factory;
		this.graphe = new SimpleGraph<>(DefaultEdge.class);
		this.pathfinder = new DijkstraShortestPath<>(graphe);
	}
	
	private void updateGraph() {
		for (int i = 0; i < factory.getWidth() / Factory.spatialStep; i++) {
			for (int j = 0; j < factory.getHeight() / Factory.spatialStep; i++) {
			
				Position position = new Position(i * Factory.spatialStep, 
												 j * Factory.spatialStep);
				
				if (!(factory.getValidity(position))) {
					continue;
				} else if (!(this.graphe.containsVertex(position))){
					this.graphe.addVertex(position);
				}
				
				for (int[] shift : DijkstraPathFinder.directions) {
					Position neighbor = new Position((i + shift[0]) * Factory.spatialStep, 
							 						 (j + shift[1]) * Factory.spatialStep);
					
					if (!(factory.getValidity(neighbor))) {
						continue;
					} else if (!(this.graphe.containsVertex(neighbor))){
						this.graphe.addVertex(neighbor);
					}
					
					this.graphe.addEdge(position, neighbor);
				}
			}
		}
	}
	
	public List<Position> findPath(Position start, Position end) {
		this.updateGraph();
		GraphPath<Position, DefaultEdge> path  = this.pathfinder.getPath(start, end);
		if (path != null) {
			return path.getVertexList();
		} else {
			return null;
		}
	}
}

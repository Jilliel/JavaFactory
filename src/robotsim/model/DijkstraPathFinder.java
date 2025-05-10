package robotsim.model;
import java.util.List;
import java.util.ArrayList;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.GraphPath;

public class DijkstraPathFinder implements FactoryPathFinder{
	
	private final static int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	private static final int spatialStep = 2;
	
	private Factory factory;

	private Graph<GridCase, DefaultEdge> graphe;
	private DijkstraShortestPath<GridCase, DefaultEdge> pathfinder;
	
	
	public DijkstraPathFinder(Factory factory) {
		super();
		this.factory = factory;
		this.graphe = new SimpleGraph<>(DefaultEdge.class);
		this.pathfinder = new DijkstraShortestPath<>(this.graphe);
	}
	
	private void updateGraph() {
		
		for (int i = 0; i < factory.getWidth() / spatialStep; i++) {
			for (int j = 0; j < factory.getHeight() / spatialStep; j++) {
				
	
				GridCase startcase = new GridCase(i, j);
				Position startpos = startcase.toPos(spatialStep);
				
	
				if (!factory.getValidity(startpos)) {
					continue;
				} else if (!(this.graphe.containsVertex(startcase))){
					this.graphe.addVertex(startcase);
				}
				
				for (int[] shift : DijkstraPathFinder.directions) {
					
					GridCase nearcase = startcase.setShift(shift);
					Position nearpos = nearcase.toPos(spatialStep);
					
					if (!factory.getValidity(nearpos)) {
						continue;
					} else if (!(this.graphe.containsVertex(nearcase))){
						this.graphe.addVertex(nearcase);
					}
					
					this.graphe.addEdge(startcase, nearcase);
				}
			}
		}
	}
	
	private GridCase discrete(Position position) {
		return new GridCase(position.getX() / spatialStep, position.getY() / spatialStep);
	}
	
	public List<Position> findPath(Position start, Position end) {
		this.updateGraph();
		
		GraphPath<GridCase, DefaultEdge> path = this.pathfinder.getPath(discrete(start), discrete(end));
		
		if (path == null) {
			return null;
		} else {
			ArrayList<Position> finalpath = new ArrayList<Position>();
			for (GridCase step : path.getVertexList()) {
				finalpath.add(step.toPos(spatialStep));
			}
			return finalpath;
		}
	}
}

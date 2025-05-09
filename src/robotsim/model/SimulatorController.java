package robotsim.model;
import fr.tp.inf112.projects.canvas.controller.CanvasViewerController;
import fr.tp.inf112.projects.canvas.controller.Observer;
import fr.tp.inf112.projects.canvas.model.Canvas;
import fr.tp.inf112.projects.canvas.model.CanvasPersistenceManager;
import robotsim.model.Factory;

public class SimulatorController implements CanvasViewerController  {
	
	private Factory factory;
	
	public SimulatorController(Factory factory) {
		this.factory = factory;
	}

	@Override
	public boolean addObserver(Observer observer) {
		// TODO Auto-generated method stub
		return this.factory.addObserver(observer);
	}

	@Override
	public boolean removeObserver(Observer observer) {
		// TODO Auto-generated method stub
		return this.factory.removeObserver(observer);
	}

	@Override
	public Canvas getCanvas() {
		// TODO Auto-generated method stub
		return factory;
	}

	@Override
	public void setCanvas(Canvas canvasModel) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		if (canvasModel instanceof Factory) {
			this.factory = (Factory)canvasModel;
		}
		else {
			throw new IllegalArgumentException("canvasModel is not an instance of Factory");
		}
		
	}

	@Override
	public CanvasPersistenceManager getPersistenceManager() {
		// TODO Auto-generated method stub
		return new PersistenceManager();
	}

	@Override
	public void startAnimation() {
		// TODO Auto-generated method stub
		this.factory.startSimulation();
		
	}

	@Override
	public void stopAnimation() {
		// TODO Auto-generated method stub
		this.factory.stopSimulation();
		
	}

	@Override
	public boolean isAnimationRunning() {
		// TODO Auto-generated method stub
		return this.factory.isSimulationRunning();
	}

}

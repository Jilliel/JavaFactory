package robotsim.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.BufferedInputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.BufferedOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

import fr.tp.inf112.projects.canvas.model.Canvas;
import fr.tp.inf112.projects.canvas.model.CanvasPersistenceManager;
import fr.tp.inf112.projects.canvas.model.impl.AbstractCanvasPersistenceManager;
import fr.tp.inf112.projects.canvas.view.FileCanvasChooser;

public class PersistenceManager extends AbstractCanvasPersistenceManager implements CanvasPersistenceManager {
	
	public PersistenceManager() {
		super(new FileCanvasChooser("TEST", ""));
	}
	
	@Override
	public Canvas read(String canvasId) throws IOException {
		Canvas result = null;
		try (	FileInputStream fileInpStr = new FileInputStream(canvasId);
				BufferedInputStream bufInpStream = new BufferedInputStream(fileInpStr);
				ObjectInputStream objInpstream = new ObjectInputStream(bufInpStream);
		){
			result = (Canvas) objInpstream.readObject();
		}
		catch (IOException ex){
			ex.printStackTrace(); 
			result = null;
		}
		catch (ClassNotFoundException ex){
			ex.printStackTrace();
			result = null;
		}
		return result;
		
	}
	
	@Override
	public void persist(Canvas canvasModel) throws IOException {
		try (	FileOutputStream fileOutStr = new FileOutputStream(canvasModel.getId());
				BufferedOutputStream bufOutStream = new BufferedOutputStream(fileOutStr);
				ObjectOutputStream objOutStream = new ObjectOutputStream(bufOutStream);
		) {
			objOutStream.writeObject(canvasModel);
		}
		catch (IOException ex){
			ex.printStackTrace();
		} 
	}
	
	@Override
	public boolean delete(Canvas canvasModel) throws IOException {
		File file = new File(canvasModel.getId());
		return file.delete();
	}

}
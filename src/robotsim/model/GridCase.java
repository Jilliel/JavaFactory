package robotsim.model;
import java.util.Objects;

public class GridCase {
	private int i;
	private int j;
	
	public GridCase(int i, int j) {
		super();
		this.i = i;
		this.j = j;
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public int getJ() {
		return j;
	}

	public void setJ(int j) {
		this.j = j;
	}
	
	public GridCase setShift(int[] shift) {
		return new GridCase(this.i + shift[0], this.j + shift[1]);
	}
	
	public Position toPos(int spatialstep) {
		return new Position(this.i * spatialstep, this.j * spatialstep);
	}
	
	public String toString() {
		return "Case: (" + this.i + ", " + this.j + ").";
	}
	
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GridCase gridcase = (GridCase) o;
        return i == gridcase.getI() && j == gridcase.getJ();
    }

    @Override
    public int hashCode() {
        return Objects.hash(i, j);
    }
}

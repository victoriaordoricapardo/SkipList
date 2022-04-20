
public class NodoSkip<T extends Comparable <T>> {
	
	T elem;
	NodoSkip<T> left, right, up,down;
	
	
	NodoSkip(T elem){
		left=null;
		right=null;
		up=null;
		down=null;
		this.elem=elem;
	}


	/**
	 * @return the elem
	 */
	public T getElem() {
		return elem;
	}


	/**
	 * @return the left
	 */
	public NodoSkip<T> getLeft() {
		return left;
	}


	/**
	 * @return the right
	 */
	public NodoSkip<T> getRight() {
		return right;
	}


	/**
	 * @return the up
	 */
	public NodoSkip<T> getUp() {
		return up;
	}


	/**
	 * @return the down
	 */
	public NodoSkip<T> getDown() {
		return down;
	}


	/**
	 * @param left the left to set
	 */
	public void setLeft(NodoSkip<T> left) {
		this.left = left;
	}


	/**
	 * @param right the right to set
	 */
	public void setRight(NodoSkip<T> right) {
		this.right = right;
	}


	/**
	 * @param up the up to set
	 */
	public void setUp(NodoSkip<T> up) {
		this.up = up;
	}


	/**
	 * @param down the down to set
	 */
	public void setDown(NodoSkip<T> down) {
		this.down = down;
	}
	

}//Clase

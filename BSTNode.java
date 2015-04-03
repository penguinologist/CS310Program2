package program2;

/**
 * The BSTNode<T> class, which is just the class of the object of which the BST
 * is composed
 * 
 * @author Jeroen Goossens
 *
 * @param <T>
 *            generic....
 */
public class BSTNode<T> implements Comparable<BSTNode<T>> {
	private BSTNode<T> left;
	private BSTNode<T> right;
	private T data;

	/**
	 * default parameter, with a single input [the data]
	 * 
	 * @param d
	 *            data to be passed in
	 */
	public BSTNode(T d) {
		setData(d);
		setLeft(null);
		setRight(null);
	}

	/**
	 * constructor taking in a little more
	 * 
	 * @param d
	 *            data
	 * @param l
	 *            left node reference
	 * @param r
	 *            right node reference
	 */
	public BSTNode(T d, BSTNode<T> l, BSTNode<T> r) {
		setData(d);
		setLeft(l);
		setRight(r);
	}

	/**
	 * gets the left node reference
	 * 
	 * @return the left
	 */
	public BSTNode<T> getLeft() {
		return left;
	}

	/**
	 * sets the left node reference
	 * 
	 * @param left
	 *            the left to set
	 */
	public void setLeft(BSTNode<T> left) {
		this.left = left;
	}

	/**
	 * gets the right node reference
	 * 
	 * @return the right
	 */
	public BSTNode<T> getRight() {
		return right;
	}

	/**
	 * sets the right node reference
	 * 
	 * @param right
	 *            the right to set
	 */
	public void setRight(BSTNode<T> right) {
		this.right = right;
	}

	/**
	 * gets the data
	 * 
	 * @return the data
	 */
	public T getData() {
		return data;
	}

	/**
	 * sets the data
	 * 
	 * @param data
	 *            the data to set
	 */
	public void setData(T data) {
		this.data = data;
	}

	/**
	 * compares the node to another node
	 * 
	 * @param o
	 *            a node
	 * @return int value indicating validity
	 */
	@Override
	public int compareTo(BSTNode<T> o) {
		return (this.getData().toString().compareTo(o.getData().toString()));
	}

}

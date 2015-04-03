package program2;

import java.util.Iterator;
import java.util.Stack;

/**
 * Generic Binary Search Tree (aka BST) of type T (so generic....)
 * 
 * @author Jeroen
 *
 * @param <T>
 *            type parameter
 */
public class BST<T extends Comparable<T>> implements Iterable<T> {
	private BSTNode<T> root;

	/**
	 * default constructor
	 */
	public BST() {
		root = null;
	}

	/**
	 * constructor taking in a node
	 * 
	 * @param node
	 *            to be passed in
	 */
	public BST(BSTNode<T> node) {
		root = node;
	}

	/**
	 * constructor taking in raw data
	 * 
	 * @param data
	 *            to be passed in
	 */
	public BST(T data) {
		root = new BSTNode<T>(data);
	}

	/**
	 * insertion method
	 * 
	 * @param value
	 *            to be inserted
	 * @return boolean value indicating success
	 */
	public boolean insert(T value) {

		if (search(value) == null) {
			root = insert(root, value);
			return true;
		}

		return false;

	}

	private BSTNode<T> insert(BSTNode<T> p, T toInsert) {
		if (p == null)
			return new BSTNode<T>(toInsert);

		if (toInsert.compareTo(p.getData()) == 0)
			return p;

		if (toInsert.compareTo(p.getData()) < 0)
			p.setLeft(insert(p.getLeft(), toInsert));
		else if (toInsert.compareTo(p.getData()) > 0)
			p.setRight(insert(p.getRight(), toInsert));

		return p;
	}

	/**
	 * search method, which finds the element if it exists. returns null
	 * otherwise
	 * 
	 * @param target
	 *            to be searched for
	 * @return the Node containing the value, or null if it isn't found
	 */
	public BSTNode<T> search(T target) {
		return search(target, root);
	}

	private BSTNode<T> search(T target, BSTNode<T> p) {
		if (p == null)
			return null;
		int comp = target.compareTo(p.getData());
		if (comp == 0)
			return p;
		if (comp < 0)
			return search(target, p.getLeft());
		if (comp > 0)
			return search(target, p.getRight());
		return null;
	}

	/**
	 * checks if the BST is empty
	 * 
	 * @return boolean indicating emptiness
	 */
	public boolean isEmpty() {
		return root == null;
	}

	/**
	 * deletes a value from the tree
	 * 
	 * @param toDelete
	 *            value to delete
	 */
	public void delete(T toDelete) {
		root = delete(root, toDelete);
	}

	private BSTNode<T> delete(BSTNode<T> p, T toDelete) {
		if (p == null) {
			System.out.println("can't delete.");
			return null;
		} else if (toDelete.compareTo(p.getData()) < 0)
			p.setLeft(delete(p.getLeft(), toDelete));
		else if (toDelete.compareTo(p.getData()) > 0)
			p.setRight(delete(p.getRight(), toDelete));
		else {
			if (p.getLeft() == null)
				return p.getRight();
			else if (p.getRight() == null)
				return p.getLeft();
			else {
				// get data from the rightmost node in the left subtree
				p.setData(retrieveData(p.getLeft()));
				// delete the rightmost node in the left subtree
				p.setLeft(delete(p.getLeft(), p.getData()));
			}
		}

		return p;
	}

	private T retrieveData(BSTNode<T> p) {
		while (p.getRight() != null)
			p = p.getRight();

		return p.getData();
	}

	/**
	 * Iterator method instantiating a new iterator to iterate over the entire
	 * tree inorder
	 * 
	 * @return Iterator<T> the iterator instance to be returned
	 */
	@Override
	public Iterator<T> iterator() {
		return new TreeIterator();
	}

	// pre-order sorting
	private class TreeIterator implements Iterator<T> {
		Stack<BSTNode<T>> stk = new Stack<BSTNode<T>>();
		Stack<T> items = new Stack<T>();

		/**
		 * default constructor for the tree iterator. This constructor also
		 * iterates through the tree non-recursively and pushes all the values
		 * in order onto the stack.
		 */
		public TreeIterator() {
			if (root != null) {
				BSTNode<T> cur = root;
				// stk.push(root);
				while (!stk.isEmpty() || cur != null) {
					if (cur != null) {
						stk.push(cur);
						cur = cur.getRight();
					} else {
						cur = stk.pop();
						items.push(cur.getData());
						cur = cur.getLeft();
					}
				}

			}
		}

		/**
		 * checks if the stack has any items left in it and returns a boolean
		 * 
		 * @return boolean indicating emptiness
		 */
		public boolean hasNext() {
			return !items.isEmpty();
		}

		/**
		 * gets the next element of type T
		 * 
		 * @return T element to be returned
		 */
		public T next() {
			T res = null;
			if (!items.isEmpty()) {
				res = items.pop();
			} else {
				System.out.println("res is null!!!");
			}
			return res;

		}

		/**
		 * removes a value, but we didn't need it so it's not implemented
		 */
		public void remove() {
			// not implemented cuz not necessary
		}

	}
}

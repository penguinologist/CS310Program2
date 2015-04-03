package program2;

import java.util.Iterator;

/**
 * The Set<T> class, which is just a pretty cover for the BST. It's about as
 * important as the wrapping paper on a five year old's Christmas present,
 * except that it's required for the assignment.
 * 
 * @author Jeroen Goossens
 *
 * @param <T>
 *            generic type
 */
public class Set<T extends Comparable<T>> implements Iterable<T>,
		Comparable<Set<T>> {

	private BST<T> tree;

	/**
	 * default constructor
	 */
	public Set() {
		// empty constructor
		tree = new BST<T>();
		
	}

	/**
	 * constructor taking in an array of items
	 * 
	 * @param setElements
	 */
	public Set(T[] setElements) {
		tree = new BST<T>(setElements[0]);

		if (setElements.length > 0) {
			int i = 0;
			for (; i < setElements.length; i++) {
				tree.insert(setElements[i]);
			}

		}
	}

	/**
	 * the search method
	 * 
	 * @param target
	 *            to be searched for
	 * @return T for some reason...
	 */
	public T search(T target) {
		// . If target is in the set return a (reference to) the copy found in
		// the set. Otherwise return null.
		T item = null;

		item = (T) tree.search(target).getData();

		return item;
	}

	/**
	 * returns a boolean value indicating whether an element is part of the set
	 * or not
	 * 
	 * @param t
	 *            item to be checked
	 * @return boolean value indicating whether it's part of it or not
	 */
	public boolean elementOf(T t) {
		// . Returns whether t is in the set or not.

		return tree.search(t) != null;
	}

	/**
	 * inserts a value into the set
	 * 
	 * @param value
	 *            to be inserted
	 * @return boolean value
	 */
	public boolean insert(T value) {
		// . Inserts value into the set if it is not already in the set. If
		// value is already in the set no operation is performed and false is
		// returned.

		if (getTree().search(value) != null) {
			return false;
		}
		tree.insert(value);
		return true;
	}

	/**
	 * deletes the target if it exists
	 * 
	 * @param target
	 *            to be deleted
	 * @return boolean indicating whether the item was deleted or not
	 */
	public boolean delete(T target) {
		// . Deletes target from the set. If target is not in the set return
		// false.

		if (!elementOf(target)) {
			return false;
		}
		tree.delete(target);

		return true;
	}

	/**
	 * returns a copy of the current Set
	 * 
	 * @return Set<T> copy of the current set
	 */
	public Set<T> copy() {
		// . Return a copy of the current set (i.e. of this). Do not just return
		// a reference to this.

		Set<T> copy = new Set<T>();
		Iterator<T> it = getTree().iterator();
		while (it.hasNext()) {
			copy.insert(it.next());
		}
		return copy;
	}

	/**
	 * creates a set of the all the items
	 * 
	 * @param s
	 *            the other set to be joined
	 * @return Set<T> of union workers. I mean elements.
	 */
	public Set<T> union(Set<T> s) {
		// . Return a set which is the union of the current set and s.

		Set<T> temp = new Set<T>();
		Iterator<T> it = getTree().iterator();
		Iterator<T> it2 = s.getTree().iterator();
		while (it.hasNext()) {
			temp.insert(it.next());
		}

		while (it2.hasNext()) {
			temp.insert(it2.next());
		}

		return temp;
	}

	/**
	 * gets the intersection items and puts it in a set to be returned
	 * 
	 * @param s
	 *            to be intersected with
	 * @return Set<T> of intersecting items
	 */
	public Set<T> intersection(Set<T> s) {
		// . Return a set which is the intersection of the current set and s.

		Set<T> temp = new Set<T>();
		// iterate through
		Iterator<T> it = getTree().iterator();
		while (it.hasNext()) {
			T local = it.next();
			Iterator<T> it2 = s.getTree().iterator();

			while (it2.hasNext()) {
				T local2 = it2.next();
				if (local.compareTo(local2) == 0) {
					temp.insert(local2);
				}
			}
		}

		return temp;
	}

	/**
	 * checks if the current set is a subset of the passed set
	 * 
	 * @param s
	 *            to be checked
	 * @return boolean indicating subsettedness
	 */
	public boolean subsetOf(Set<T> s) {
		// . Return whether this is a subset of s.

		Iterator<T> it1 = getTree().iterator();
		Iterator<T> it2 = s.getTree().iterator();
		boolean t = true;
		while (it1.hasNext()) {
			T local = it1.next();
			if (s.getTree().search(local) == null) {
				t = false;
			}
		}
		return t;

	}

	/**
	 * compares two sets
	 * 
	 * @param arg0
	 *            item to be compared to
	 * @return int indicating comparison
	 */
	@Override
	public int compareTo(Set<T> arg0) {
		// To put it another way: in an ordered set, the first element in the
		// set that is not equal to the corresponding element in the second set
		// will determine which is larger. If they both contain the same
		// elements the one with shorter set length is smaller.

		Iterator<T> it1 = getTree().iterator();
		Iterator<T> it2 = arg0.getTree().iterator();
		while (it1.hasNext() && it2.hasNext()) {
			T item1 = it1.next();
			T item2 = it2.next();
			if (item1.compareTo(item2) == -1) {
				return -1;
			}
			if (item1.compareTo(item2) == 1) {
				return 1;
			}
			// if it gets here, the first elements are identical. time to move
			// on to the next round. repeat until end of either list.
		}
 
		if (it1.hasNext()) {// if it1 still has items, the other list is smaller
			return 1;
		}
		if (it2.hasNext()) {// if it2 still had items, the other list is smaller
			return -1;
		}

		return 0;// equal
	}

	/**
	 * gets the iterator
	 * 
	 * @return Iterator<T> to be returned
	 */
	@Override
	public Iterator<T> iterator() {
		return getTree().iterator();
	}

	/**
	 * returns the tree of the set
	 * 
	 * @return BST<T> the tree to be returned
	 */
	public BST<T> getTree() {
		return tree;
	}

	/**
	 * if you've gotten here and don't know what a tostring does, you should
	 * probably head back to your basic java tutorial. or just google it.
	 * whatever
	 * 
	 * @return String ohreally?!
	 */
	public String toString() {
		String t = "{";
		Iterator<T> it = getTree().iterator();
		int count = 0;
		while (it.hasNext()) {
			t += it.next().toString() + ", ";
			count++;
		}
		if (count != 0) {
			t = t.substring(0, t.length() - 2);
		}

		t += "}";
		return t;
	}
}

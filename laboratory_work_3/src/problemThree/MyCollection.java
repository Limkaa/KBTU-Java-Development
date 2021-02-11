package problemThree;

import java.util.Collection;

public interface MyCollection<E> {
	
	public void add(Object element);
	
	public int size();
	public int setSize(int size);

	boolean contains(Object element);

	boolean isEmpty();
	
	boolean removeElement(Object element);
	boolean removeAll(Collection<E> collection);
	
	void setElementAt(Object element, int index);

}

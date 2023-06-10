package BST;

import java.util.Collection;

public interface Tree<E> extends Collection<E> {
    /** Return true if the element is in the tree */
    boolean search(E e);

    /** Insert element e into the binary tree
     * Return true if the element is inserted successfully */
    boolean insert(E e);

    /** Delete the specified element from the tree
     * Return true if the element is deleted successfully */
    boolean delete(E e);

    /** Get the number of elements in the tree */
    int getSize();

    /** Inorder traversal from the root*/
    default void inorder() {
    }

    /** Postorder traversal from the root */
    default void postorder() {
    }

    /** Preorder traversal from the root */
    default void preorder() {
    }

    /** Return true if the tree is empty */
    @Override
    default boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    default boolean contains(Object e) {
        return search((E)e);
    }

    @Override
    default boolean add(E e) {
        return insert(e);
    }

    @Override
    public default boolean remove(Object e) {
        return delete((E)e);
    }

    @Override
    default int size() {
        return getSize();
    }

    @Override
    default boolean containsAll(Collection<?> c) {
        // Left as an exercise
        return false;
    }

    @Override
    default boolean addAll(Collection<? extends E> c) {
        boolean add_all = true;
        for (int i = 0; i < c.size() && add_all; i++) {
            add_all = insert(c.iterator().next());
        }


//        c.forEach(this::insert);
        return add_all;
    }

    @Override
    default boolean removeAll(Collection<?> c) {
        // Left as an exercise
        return false;
    }

    @Override
    default boolean retainAll(Collection<?> c) {
        // Left as an exercise
        return false;
    }

    @Override
    default Object[] toArray() {
        // Left as an exercise
        return null;
    }

    @Override
    default <T> T[] toArray(T[] array) {
        // Left as an exercise
        return null;
    }
}

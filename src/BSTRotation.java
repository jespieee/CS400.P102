public class BSTRotation<T extends Comparable<T>> extends BinarySearchTree_Placeholder<T> {

    protected BSTNode<T> root;
    /**
     * Performs the rotation operation on the provided nodes within this tree.
     * When the provided child is a left child of the provided parent, this
     * method will perform a right rotation. When the provided child is a right
     * child of the provided parent, this method will perform a left rotation.
     * When the provided nodes are not related in one of these ways, this
     * method will either throw a NullPointerException: when either reference is
     * null, or otherwise will throw an IllegalArgumentException.
     *
     * @param child is the node being rotated from child to parent position
     * @param parent is the node being rotated from parent to child position
     * @throws NullPointerException when either passed argument is null
     * @throws IllegalArgumentException when the provided child and parent
     *     nodes are not initially (pre-rotation) related that way
     */
    protected void rotate(BSTNode<T> child, BSTNode<T> parent)
            throws NullPointerException, IllegalArgumentException {
        if (child == null || parent == null) {
            throw new NullPointerException();
        }
        if (parent.getLeft() != child || parent.getRight() != child) {
            throw new IllegalArgumentException();
        }
        if (parent.getLeft() == child) {
            // perform right rotation
        }
        if (parent.getRight() == child) {
            // perform left rotation
        }
    }
}

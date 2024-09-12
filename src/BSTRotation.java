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
            if (parent.getUp() != null) {
                parent.getUp().setRight(child);
                child.setUp(parent.getUp());
            }
            // parent node gets the child's right subtree, including null
            parent.setLeft(child.getRight());
            // but if there's actually data then make that node's up point to the parent node
            if (child.getRight() != null) {
                child.getRight().setUp(parent);
            }
            // we get to keep the left subtree of the child attached, just make the child the up of the parent
            child.setRight(parent);
        }
        if (parent.getRight() == child) {
            // perform left rotation
            if (parent.getUp() != null) {
                parent.getUp().setLeft(child);
                child.setUp(parent.getUp());
            }
            // basically just the complete opposite of right rotation
            // parent gets child's left subtree - then check if there's actually data in the child's left
            // keep the right subtree of the child, and make the parent's parent the child node
            parent.setRight(child.getLeft());
            if (child.getLeft() != null) {
                child.getLeft().setUp(parent);
            }
            child.setLeft(parent);
        }
        if (parent.getUp() == null) {
            root = child;
        }
        else if (parent.getUp().getLeft() == parent) {
            parent.getUp().setLeft(child);
        }
        else {
            parent.getUp().setRight(child);
        }
    }

    /**
     * Perform both left and right rotations.
     * Performing rotations that include the root node, and some that do not.
     * Performing rotations on parent-child pairs of nodes that have between them
     * 0, 1, 2, and 3 shared children (that do not include the child being rotated).
     */
    public void test1() {

    }
    public void test2() {

    }
    public void test3() {

    }
}

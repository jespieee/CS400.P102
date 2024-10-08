public class BSTRotation<T extends Comparable<T>> extends BinarySearchTree_Placeholder<T> {

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
        if (parent.getLeft() != child && parent.getRight() != child) {
            System.out.println(parent.getLeft().getData() + " " + child.getData());
            throw new IllegalArgumentException();
        }

        // handle where the root or its references may need to be changed before modifying
        if (parent.getUp() == null) {
            root = child;
        }
        else if (parent.getUp().getLeft() == parent) {
            parent.getUp().setLeft(child);
        }
        else {
            parent.getUp().setRight(child);
        }

        child.setUp(parent.getUp());

        if (parent.getLeft() == child) {
            // perform right rotation
            // parent node gets the child's right subtree, including null
            parent.setLeft(child.getRight());
            // but if there's actually data then make that node's up point to the parent node
            if (child.getRight() != null) {
                child.getRight().setUp(parent);
            }
            // we get to keep the left subtree of the child attached, just make the child the up of the parent
            child.setRight(parent);
        }
        else if (parent.getRight() == child) {
            // perform left rotation
            // basically just the complete opposite of right rotation
            // parent gets child's left subtree - then check if there's actually data in the child's left
            // keep the right subtree of the child, and make the parent's parent the child node
            parent.setRight(child.getLeft());
            if (child.getLeft() != null) {
                child.getLeft().setUp(parent);
            }
            child.setLeft(parent);
        }
    }

    /**
     * Perform both left and right rotations.
     * Performing rotations that include the root node, and some that do not.
     * Performing rotations on parent-child pairs of nodes that have between them
     * 0, 1, 2, and 3 shared children (that do not include the child being rotated).
     * default tree nodes are inserted in E, B, H, A, D, F, J, C, G, I, K order.
     * tree structure is:
     * E, D, J, B,
     *                  E
     *             B         H
     *          A     D    F    J
     *               C      G  I  K
     */

    /**
     * Performs a right rotation without including the root, as well as a left rotation including the root
     * @return false if rotation fails, or if any value isn't what it is expected to be
     */
    public boolean test1() {
        var tree1 = new BSTRotation<String>();

        if (tree1.size() != 11) {
            return false;
        }
        var parent = tree1.root.getLeft();
        var child = parent.getLeft();

        // rotate B and A  - right rotation not involving switching of the root node
        tree1.rotate(child, parent);
        /*
         * * tree structure now is:
         *                  E
         *             A         H
         *                B    F    J
         *                  D     G  I  K
         *                 C
         */

        if (tree1.root.getLeft().getData() != "A") {
            return false;
        }
        if (tree1.root.getLeft().getRight().getData() != "B") {
            return false;
        }
        if (tree1.root.getLeft().getRight().getRight().getData() != "D") {
            return false;
        }
        if (tree1.root.getRight().getData() != "H") {
            return false;
        }
        // in order traversal is [A, B, C, D, E, F, G, H, I, J, K], this should always remain the same
        if (!tree1.root.toInOrderString().equals("[ A, B, C, D, E, F, G, H, I, J, K ]")) {
            return false;
        }
        // level order traversal is [E, A, H, B, F, J, D, G, I, K, C]
        if (!tree1.root.toLevelOrderString().equals("[ E, A, H, B, F, J, D, G, I, K, C ]")) {
            return false;
        }

        tree1.clear();

        var tree2 = new BSTRotation<String>();

        if (tree2.size() != 11) {
            return false;
        }

        var parent1 = tree2.root;
        var child1 = parent1.getRight();

        // rotate E and H  - left rotation that involves the root
        tree2.rotate(child1, parent1);
        /*
         * * tree structure now is:
         *                  H
         *             E         J
         *          B     F    I    K
         *       A     D    G
         *            C
         */
        if (tree2.root.getData() != "H") {
            return false;
        }
        // double check this is still consistent
        if (!tree2.root.toInOrderString().equals("[ A, B, C, D, E, F, G, H, I, J, K ]")) {
            return false;
        }
        if (!tree2.root.toLevelOrderString().equals("[ H, E, J, B, F, I, K, A, D, G, C ]")) {
            return false;
        }
        return true;
    }

    /**
     * Performs rotations on nodes that do not share any children
     * @return false if rotation fails, or if any value isn't what it is expected to be
     */
    public boolean test2() {
        var tree = new BSTRotation<String>();
        if (tree.size() != 11) {
            return false;
        }

        var dNode = tree.root.getLeft().getRight();
        var cNode = dNode.getLeft();

        if (dNode.getData() != "D" || dNode.getLeft().getData() != "C") {
            return false;
        }
        if (cNode.getData() != "C" || cNode.getLeft() != null || cNode.getRight() != null) {
            return false;
        }

        tree.rotate(cNode, dNode);
        if (!tree.root.toInOrderString().equals("[ A, B, C, D, E, F, G, H, I, J, K ]")) {
            return false;
        }
        if (!tree.root.toLevelOrderString().equals("[ E, B, H, A, C, F, J, D, G, I, K ]")) {
            return false;
        }
        return true;
    }

    /**
     * Performs rotations on nodes that share 1 or 2 children
     * @return false if rotation fails, or if any value isn't what it is expected to be
     */
    public boolean test3() {
        var tree = new BSTRotation<String>();
        if (tree.size() != 11) {
            return false;
        }
        var bNode = tree.root.getLeft();
        var dNode = tree.root.getLeft().getRight();
        if (bNode.getData() != "B" || dNode.getData() != "D" || bNode.getRight() != dNode || dNode.getUp() != bNode) {
            return false;
        }

        // rotate nodes that share 1 child, in this case "D" and "B" share the "C" node
        tree.rotate(dNode, bNode);

        if (!tree.root.toInOrderString().equals("[ A, B, C, D, E, F, G, H, I, J, K ]")) {
            return false;
        }
        if (!tree.root.toLevelOrderString().equals("[ E, D, H, B, F, J, A, C, G, I, K ]")) {
            return false;
        }

        var hNode = tree.root.getRight();
        var jNode = tree.root.getRight().getRight();
        if (hNode.getData() != "H" || jNode.getData() != "J" || hNode.getRight() != jNode || jNode.getUp() != hNode) {
            return false;
        }
        // rotate nodes that share 2 children, in this case "H" and "J" share the "I" and "K" nodes
        tree.rotate(jNode, hNode);

        if (!tree.root.toInOrderString().equals("[ A, B, C, D, E, F, G, H, I, J, K ]")) {
            return false;
        }
        if (!tree.root.toLevelOrderString().equals("[ E, D, J, B, H, K, A, C, F, I, G ]")) {
            return false;
        }
        return true;
    }

    public void main() {
        System.out.println("Test 1 results: " + test1());
        System.out.println("Test 2 results: " + test2());
        System.out.println("Test 3 results: " + test3());
    }
}

package cp213;

/**
 * Implements a Popularity Tree. Extends BST.
 *
 * @author your name here
 * @author David Brown
 * @version 2025-01-05
 * @param <T> the data structure data type
 */
public class PopularityTree<T extends Comparable<T>> extends BST<T> {

    /**
     * Auxiliary method for valid. May force node rotation if the retrieval count of
     * the located node data is incremented.
     *
     * @param node The node to examine for key.
     * @param key  The data to search for. Count is updated to count in matching
     *             node data if key is found.
     * @return The updated node.
     */
    private TreeNode<T> retrieveAux(TreeNode<T> node, final CountedEntity<T> key) {

	// your code here
	if (node == null) {
            return null; // node not found
        }
        int result = key.compareTo(node.getCountedEntity());
        if (result == 0) {
            node.getCountedEntity().incrementCount(); // increase count

            // perform rotations to maintain popularity order
            if (node.getLeft() != null && node.getLeft().getCountedEntity().getCount() > node.getCountedEntity().getCount()) {
                node = rotateRight(node); // rotate right if left child has higher count
            } else if (node.getRight() != null && node.getRight().getCountedEntity().getCount() > node.getCountedEntity().getCount()) {
                node = rotateLeft(node); // rotate left if right child has higher count
            }
            return node;
        } else if (result < 0) {
            node.setLeft(retrieveAux(node.getLeft(), key)); // search left subtree
        } else {
            node.setRight(retrieveAux(node.getRight(), key)); // search right subtree
        }
        return node;
    }

    /**
     * Performs a left rotation around node.
     *
     * @param parent The subtree to rotate.
     * @return The new root of the subtree.
     */
    private TreeNode<T> rotateLeft(final TreeNode<T> parent) {

	// your code here
	TreeNode<T> newRoot = parent.getRight();
        parent.setRight(newRoot.getLeft());
        newRoot.setLeft(parent);
        return newRoot;
    }

    /**
     * Performs a right rotation around {@code node}.
     *
     * @param parent The subtree to rotate.
     * @return The new root of the subtree.
     */
    private TreeNode<T> rotateRight(final TreeNode<T> parent) {

	// your code here
	TreeNode<T> newRoot = parent.getLeft();
        parent.setLeft(newRoot.getRight());
        newRoot.setRight(parent);
        return newRoot;
    }

    /**
     * Replaces BST insertAux - does not increment count on repeated insertion.
     * Counts are incremented only on retrieve.
     */
    @Override
    protected TreeNode<T> insertAux(TreeNode<T> node, final CountedEntity<T> data) {

	// your code here
	if (node == null) {
            return new TreeNode<>(data); // create new node
        }
        int result = data.compareTo(node.getCountedEntity());
        if (result < 0) {
            node.setLeft(insertAux(node.getLeft(), data)); // insert left
        } else if (result > 0) {
            node.setRight(insertAux(node.getRight(), data)); // insert right
        }
        return node;
    }

    /**
     * Auxiliary method for valid. Determines if a subtree based on node is a valid
     * subtree. An Popularity Tree must meet the BST validation conditions, and
     * additionally the counts of any node data must be greater than or equal to the
     * counts of its children.
     *
     * @param node The root of the subtree to test for validity.
     * @return true if the subtree base on node is valid, false otherwise.
     */
    @Override
    protected boolean isValidAux(final TreeNode<T> node, TreeNode<T> minNode, TreeNode<T> maxNode) {

	// your code here
	if (node == null) {
            return true; // empty tree is valid
        }
        if ((minNode != null && node.getCountedEntity().compareTo(minNode.getCountedEntity()) <= 0)
                || (maxNode != null && node.getCountedEntity().compareTo(maxNode.getCountedEntity()) >= 0)) {
            return false; // violates BST properties
        }
        if ((node.getLeft() != null && node.getLeft().getCountedEntity().getCount() > node.getCountedEntity().getCount())
                || (node.getRight() != null && node.getRight().getCountedEntity().getCount() > node.getCountedEntity().getCount())) {
            return false; // parent must have higher or equal count than children
        }
        return isValidAux(node.getLeft(), minNode, node) && isValidAux(node.getRight(), node, maxNode);
    }

    /**
     * Determines whether two PopularityTrees are identical.
     *
     * @param target The PopularityTree to compare this PopularityTree against.
     * @return true if this PopularityTree and target contain nodes that match in
     *         position, data, count, and height, false otherwise.
     */
    public boolean equals(final PopularityTree<T> target) {
	return super.equals(target);
    }

    /**
     * Very similar to the BST retrieve, but increments the data count here instead
     * of in the insertion.
     *
     * @param key The key to search for.
     */
    @Override
    public CountedEntity<T> retrieve(CountedEntity<T> key) {

	// your code here
	root = retrieveAux(root, key);
        return (root != null) ? root.getCountedEntity() : null;
    }

}

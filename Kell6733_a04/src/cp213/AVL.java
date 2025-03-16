package cp213;

/**
 * Implements an AVL (Adelson-Velsky Landis) tree. Extends BST.
 *
 * @author Spencer Kelly
 * @author David Brown
 * @version 2025-01-05
 * @param <T> the data structure data type
 */
public class AVL<T extends Comparable<T>> extends BST<T> {

    /**
     * Returns the balance data of node. If greater than 1, then left heavy, if less
     * than -1, then right heavy. If in the range -1 to 1 inclusive, the node is
     * balanced. Used to determine whether to rotate a node upon insertion.
     *
     * @param node The TreeNode to analyze for balance.
     * @return A balance number.
     */
    private int balance(final TreeNode<T> node) {

	// your code here
	// balance is the height difference between left and right children
        int leftHeight = (node.getLeft() != null) ? node.getLeft().getHeight() : 0;
        int rightHeight = (node.getRight() != null) ? node.getRight().getHeight() : 0;
        return leftHeight - rightHeight;
    }

    /**
     * Rebalances the current node if its children are not balanced.
     *
     * @param node the node to rebalance
     * @return replacement for the rebalanced node
     */
    private TreeNode<T> rebalance(TreeNode<T> node) {

	// your code here
	// get the balance factor of the node
        int balanceFactor = balance(node);

        // if left heavy
        if (balanceFactor > 1) {
            // if left-right case
            if (balance(node.getLeft()) < 0) {
                node.setLeft(rotateLeft(node.getLeft()));
            }
            // left-left case
            node = rotateRight(node);
        }
        // if right heavy
        else if (balanceFactor < -1) {
            // if right-left case
            if (balance(node.getRight()) > 0) {
                node.setRight(rotateRight(node.getRight()));
            }
            // right-right case
            node = rotateLeft(node);
        }
        // update the height of the node
        node.updateHeight();
        return node;
    }

    /**
     * Performs a left rotation around node.
     *
     * @param node The subtree to rotate.
     * @return The new root of the subtree.
     */
    private TreeNode<T> rotateLeft(final TreeNode<T> node) {

	// your code here
	// right child becomes the new root
        TreeNode<T> newRoot = node.getRight();
        // move the left subtree of new root to the right subtree of old root
        node.setRight(newRoot.getRight());
        // old root becomes the left child of new root
        newRoot.setLeft(node);
        // update heights
        node.updateHeight();
        newRoot.updateHeight();
        return newRoot;
    }

    /**
     * Performs a right rotation around node.
     *
     * @param node The subtree to rotate.
     * @return The new root of the subtree.
     */
    private TreeNode<T> rotateRight(final TreeNode<T> node) {

	// your code here
	// left child becomes the new root
        TreeNode<T> newRoot = node.getLeft();
        // move the right subtree of new root to the left subtree of old root
        node.setLeft(newRoot.getRight());
        // old root becomes the right child of new root
        newRoot.setRight(node);
        // update heights
        node.updateHeight();
        newRoot.updateHeight();
        return newRoot;
    }

    /**
     * Auxiliary method for insert. Inserts data into this AVL. Same as BST
     * insertion with addition of rebalance of nodes.
     *
     * @param node          The current node (TreeNode).
     * @param countedEntity Data to be inserted into the node.
     * @return The inserted node.
     */
    @Override
    protected TreeNode<T> insertAux(TreeNode<T> node, final CountedEntity<T> countedEntity) {

	// your code here
	// if node is null, create a new node
        if (node == null) {
            return new TreeNode<>(countedEntity);
        }
        // insert data into the left or right subtree
        if (countedEntity.compareTo(node.getCountedEntity()) < 0) {
            node.setLeft(insertAux(node.getLeft(), countedEntity));
        } else {
            node.setRight(insertAux(node.getRight(), countedEntity));
        }
        // rebalance the node
        return rebalance(node);
    }

    /**
     * Auxiliary method for valid. Determines if a subtree based on node is a valid
     * subtree. An AVL must meet the BST validation conditions, and additionally be
     * balanced in all its subtrees - i.e. the difference in height between any two
     * children must be no greater than 1.
     *
     * @param node The root of the subtree to test for validity.
     * @return true if the subtree base on node is valid, false otherwise.
     */
    @Override
    protected boolean isValidAux(final TreeNode<T> node, TreeNode<T> minNode, TreeNode<T> maxNode) {

	// your code here
	// if node is null, it's valid
        if (node == null) {
            return true;
        }
        // check bst properties
        if ((minNode != null && node.getCountedEntity().compareTo(minNode.getCountedEntity()) <= 0) ||
            (maxNode != null && node.getCountedEntity().compareTo(maxNode.getCountedEntity()) >= 0)) {
            return false;
        }
        // check balance factor
        int balanceFactor = balance(node);
        if (balanceFactor < -1 || balanceFactor > 1) {
            return false;
        }
        // check recursively for left and right subtrees
        return isValidAux(node.getLeft(), minNode, node) && isValidAux(node.getRight(), node, maxNode);
    }

    /**
     * Determines whether two AVLs are identical.
     *
     * @param target The AVL to compare this AVL against.
     * @return true if this AVL and target contain nodes that match in position,
     *         data, count, and height, false otherwise.
     */
    public boolean equals(final AVL<T> target) {
	return super.equals(target);
    }

    /**
     * Auxiliary method for remove. Removes data from this BST. Same as BST removal
     * with addition of rebalance of nodes.
     *
     * @param node          The current node (TreeNode).
     * @param countedEntity Data removed from the tree.
     * @return The replacement node.
     */
    @Override
    protected TreeNode<T> removeAux(TreeNode<T> node, final CountedEntity<T> countedEntity) {

	// your code here
	if (node == null) {
	        // if the node is null, there's nothing to remove
	        return null;
	    }

	    // compare the data to the current node's data
	    final int result = countedEntity.compareTo(node.getCountedEntity());

	    if (result < 0) {
	        // if data is less than the current node's data, go left
	        node.setLeft(removeAux(node.getLeft(), countedEntity));
	    } else if (result > 0) {
	        // if data is greater than the current node's data, go right
	        node.setRight(removeAux(node.getRight(), countedEntity));
	    } else {
	        // node to be removed found
	        if (node.getLeft() == null) {
	            // if the node has no left child, replace it with its right child
	            return node.getRight();
	        } else if (node.getRight() == null) {
	            // if the node has no right child, replace it with its left child
	            return node.getLeft();
	        } else {
	            // node with two children
	            TreeNode<T> parent = node;
	            TreeNode<T> successor = node.getRight();

	            // find the in-order successor (smallest in the right subtree)
	            while (successor.getLeft() != null) {
	                parent = successor;
	                successor = successor.getLeft();
	            }

	            // if the successor is not the immediate right child
	            if (parent != node) {
	                parent.setLeft(successor.getRight());
	                successor.setRight(node.getRight());
	            }

	            // replace node with successor
	            successor.setLeft(node.getLeft());
	            node = successor;
	        }
	    }

	    // update the height of the current node
	    node.updateHeight();
	    // rebalance the current node
	    return rebalance(node);
    }

}

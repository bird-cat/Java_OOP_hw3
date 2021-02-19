package ntu.r09922114.binaryTree;

import ntu.r09922114.util.Comparable;
import ntu.r09922114.gambling.Card;

public class BTNode {
    private BTNode left, right;
    private Comparable data;

    public BTNode(Comparable data) {
        this.left = this.right = null;
        this.data = data;
    }

    public static BTNode createBstFromSortedArray(Comparable[] sortedArray, int lower, int upper) {
        if (lower == upper)
            return null;
        int mid = (lower + upper) / 2;
        BTNode root = new BTNode(sortedArray[mid]);
        root.left = createBstFromSortedArray(sortedArray, lower, mid);
        root.right = createBstFromSortedArray(sortedArray, mid + 1, upper);
        return root;
    }

    public Comparable getData() {
        return data;
    }

    public BTNode getLeft() {
        return left;
    }

    public BTNode getRight() {
        return right;
    }

    public void setLeft(BTNode leftChild) {
        left = leftChild;
    }

    public void setRight(BTNode rightChild) {
        right = rightChild;
    }

    public int degree() {
        if (left == null && right == null)
            return 0;
        if (left == null || right == null)
            return 1;
        return 2;
    }

    public static BTNode insert(BTNode root, BTNode newNode) {
        if (root == null)
            return newNode;
        else if (newNode.data.compare(root.data) < 0)
            root.left = insert(root.left, newNode);
        else
            root.right = insert(root.right, newNode);
        return root;
    }

    public BTNode remove(BTNode droppedNode) {
        BTNode root = this;
        BTNode parentNode = getParent(root, droppedNode);

        if (droppedNode.degree() == 0) { // Case 1
            if (root == droppedNode)
                root = null;
            else if (parentNode.getLeft() == droppedNode)
                parentNode.setLeft(null);
            else
                parentNode.setRight(null);
        } else if (droppedNode.degree() == 1) { // Case 2
            BTNode child = (droppedNode.right == null) ? droppedNode.left : droppedNode.right;
            if (root == droppedNode)
                root = child;
            else if (parentNode.getLeft() == droppedNode)
                parentNode.setLeft(child);
            else
                parentNode.setRight(child);
        } else { // Case 3
            BTNode preSucc = droppedNode.getLeft();
            while (preSucc.getRight() != null)
                preSucc = preSucc.getRight();
            root = remove(preSucc);
            preSucc.setLeft(droppedNode.getLeft());
            preSucc.setRight(droppedNode.getRight());
            if (root == droppedNode)
                root = preSucc;
            else if (parentNode.getLeft() == droppedNode)
                parentNode.setLeft(preSucc);
            else
                parentNode.setRight(preSucc);
        }
        return root;
    }

    public static BTNode searchPair(BTNode root, Card newCard) {
        if (root == null)
            return null;
        Card curCard = (Card) root.getData();
        if (newCard.getRank().equals(curCard.getRank()))
            return root;
        if (newCard.compare(curCard) < 0)
            return searchPair(root.left, newCard);
        else
            return searchPair(root.right, newCard);
    }

    public static void printInorder(BTNode root) {
        if (root != null) {
            printInorder(root.left);
            System.out.print(root.data.toString() + " ");
            printInorder(root.right);
        }
    }

    public static BTNode getParent(BTNode root, BTNode node) {
        if (root == null)
            return null;
        if (root.left == node || root.right == node)
            return root;
        if (node.data.compare(root.data) < 0)
            return getParent(root.left, node);
        else if (node.data.compare(root.data) > 0)
            return getParent(root.right, node);
        else
            return null;
    }
}

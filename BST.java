package ntu.r09922114.binaryTree;

import ntu.r09922114.container.Stack;
import ntu.r09922114.util.Comparable;
import ntu.r09922114.gambling.Card;

public class BST {
    private BTNode root = null;

    public BST(Comparable[] sortedArray) {
        root = BTNode.createBstFromSortedArray(sortedArray, 0, sortedArray.length);
    }

    public void insert(Comparable data) {
        BTNode newNode = new BTNode(data);
        root = BTNode.insert(root, newNode);
    }

    public void remove(BTNode droppedNode) {
        root = root.remove(droppedNode);
    }

    public BTNode searchPair(Card newCard) {
        return BTNode.searchPair(root, newCard);
    }

    public void printInorder() {
        BTNode.printInorder(root);
    }

    public BTNode getCertainNode(int i) {
        Stack s = new Stack();
        BTNode cur = root;

        while (true) {
            do {
                s.push(cur);
                cur = cur.getLeft();
            } while (cur != null);

            do {
                cur = (BTNode) s.front();
                s.pop();
                if (i-- == 0)
                    return cur;
            } while (cur.getRight() == null);
            cur = cur.getRight();
        }
    }
}
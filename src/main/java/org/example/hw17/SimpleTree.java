package org.example.hw17;

public class SimpleTree {
    private Node root;

    SimpleTree() {
        this.root = null;
    }

    public void add(int value) {
        root = addRecursive(root, value);
    }

    private Node addRecursive(Node current, int value) {
        if (current == null) {
            return new Node(value);
        }

        if (value < current.getValue()) {
            current.setLeft(addRecursive(current.getLeft(), value));
        } else if (value > current.getValue()) {
            current.setRight(addRecursive(current.getRight(), value));
        }  // value already exists in the tree, do nothing

        return current;
    }

    public void traverseInOrder() {
        traverseInOrderRecursive(root);
    }

    private void traverseInOrderRecursive(Node current) {
        if (current != null) {
            traverseInOrderRecursive(current.getLeft());
            System.out.print(current.getValue() + " ");
            traverseInOrderRecursive(current.getRight());
        }
    }

    public static void main(String[] args) {
        SimpleTree tree = new SimpleTree();
        tree.add(6);
        tree.add(4);
        tree.add(8);
        tree.add(3);
        tree.add(5);
        tree.add(7);
        tree.add(9);
        tree.traverseInOrder();
        // Output: 3 4 5 6 7 8 9
    }
}

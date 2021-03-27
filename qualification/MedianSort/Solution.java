import java.util.Scanner;

public class Solution {
    private static final Scanner IN = new Scanner(System.in);

    private static class Node {
        int value = 0;
        Node left = null;
        Node right = null;

        public Node() {}

        public Node(final int value) {
            this.value = value;
        }
        
        @Override
        public String toString() {
            String out = "";
            if (left != null) {
                out += left.toString() + " ";
            }
            out += value;
            if (right != null) {
                out += " " + right.toString();
            }
            return out;
        }
    }

    public static void main(String[] args) {
        final var params = IN.nextLine().split(" ");
        final int count = Integer.parseInt(params[0]);
        final int length = Integer.parseInt(params[1]);
        final int queries = Integer.parseInt(params[2]);


        for (int i = 0; i < count; i++) {
            if (!runSort(length)) {
                return;
            }
        }

        IN.close();
    }

    private static boolean runSort(final int length) {

        final var head = new Node();
        head.left = new Node();
        head.right = new Node();

        System.out.println("1 2 3");
        var median = Integer.parseInt(IN.nextLine());
        if (median == -1) {
            return false;
        }
        head.value = median;
        switch (median) {
            case 1:
                head.left.value = 2;
                head.right.value = 3;
                break;
            case 2:
                head.left.value = 1;
                head.right.value = 3;
                break;
            case 3:
                head.left.value = 1;
                head.right.value = 2;
                break;
            default:
                throw new IllegalArgumentException("First response expected to be 1, 2 or 3");
        }

        int element = 4;

        while (element <= length) {
            // System.out.println(String.format("Sorting %d: %s", element, head.toString()));
            if (!sortElement(head, element)) {
                return false;
            }
            // System.out.println(String.format("Sorted %d: %s", element, head.toString()));
            element++;
        }

        System.out.println(head.toString());
        final var result = Integet.parseInt(IN.nextLine());
        return result != -1;
    }

    private static boolean sortElement(final Node head, final int element) {
        var child = head.left;
        var left = head.left;
        var right = head;
        var median = 0;
        while (true) {
            System.out.println(String.format("%d %d %d", left.value, right.value, element));
            median = Integer.parseInt(IN.nextLine());
            if (median  == -1) {
                return false;
            }
            // System.out.println("Median was " + median);
            if (median == element) {
                if (child == left) {
                    if (child.right == null) {
                        child.right = new Node(element);
                        return true;
                    }
                    left = child;
                    right = child.right;
                    child = child.right;
                } else {
                    if (child.left == null) {
                        child.left = new Node(element);
                        return true;
                    }
                    right = child;
                    left = child.left;
                    child = child.left;
                }
            } else if (median == left.value) {
                if (left.left == null) {
                    left.left = new Node(element);
                    return true;
                }
                right = left;
                child = left = left.left;
            } else if (median == right.value) {
                if (right.right == null) {
                    right.right = new Node(element);
                    return true;
                }
                left = right;
                child = right = right.right;
            }
        }
    }
}

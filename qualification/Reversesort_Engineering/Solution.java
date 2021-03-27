import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        final var in = new Scanner(System.in);

        final int count = Integer.parseInt(in.nextLine());

        for (int i = 0; i < count; i++) {
            final var els = in.nextLine().split(" ");
            final var length = Integer.parseInt(els[0]);
            final var cost = Integer.parseInt(els[1]);

            if (cost < length - 1) {
                System.out.println(String.format("Case #%d: IMPOSSIBLE", i + 1));
                continue;
            }

            var maxCost = (length + 1) * (length / 2) - 1;
            if (length % 2 == 1) {
                maxCost += (length + 1) / 2;
            }

            if (cost > maxCost) {
                System.out.println(String.format("Case #%d: IMPOSSIBLE", i + 1));
                continue;
            }

            final int[] arr = new int[length];
            for (int j = 0; j < length; j++) {
                arr[j] = j + 1;
            }

            addCost(arr, 0, length, cost - (length - 1), false);

            System.out.print(String.format("Case #%d: ", i + 1));
            print(arr);
        }

        in.close();
    }

    private static void addCost(final int[] arr, final int start, final int size, final int cost, final boolean forward) {
        // System.out.println(String.format("Add cost %d, start %d, size %d, forward " + forward, cost, start, size));
        // print(arr);
        if (cost >= size) {
            reverseVals(arr, start, size);
            // print(arr);
            addCost(arr, forward ? start + 1 : start, size - 1, cost - size + 1, !forward);
        } else {
            reverseVals(arr, forward ? start : start + size - cost - 1, cost + 1);
            // print(arr);
        }

    }

    public static int reverseVals(final int[] arr, final int start, final int size) {
        // System.out.println(String.format("Reversing from %d size %d", start, size));
        int tmp;
        for (int i = 0; i < size / 2; i++) {
            final var swap = start + i;
            final var with = start + size - 1 - i;
            // System.out.println(String.format("Swap %d with %d", swap, with));
            tmp = arr[swap];
            arr[swap] = arr[with];
            arr[with] = tmp;
        }

        return size;
    }

    private static void print(final int[] arr) {
        final var length = arr.length;
        for (int i = 0; i < length; i++) {
            System.out.print(arr[i]);

            if (i < length - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }
}

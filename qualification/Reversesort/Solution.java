import java.util.Arrays;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        final var in = new Scanner(System.in);

        final int count = Integer.parseInt(in.nextLine());

        for (int i = 0; i < count; i++) {
            final var size = Integer.parseInt(in.nextLine());
            final var els = Arrays.stream(in.nextLine().split(" "))
                .map(Integer::parseInt)
                .toArray(Integer[]::new);
            final var cost = reverse(els);
            // for (final var el : els) {
            //     System.out.print(el);
            // }
            // System.out.println();
            System.out.println("Case #" + (i + 1) + ": " + cost);
        }

        in.close();
    }

    public static int reverse(Integer[] arr) {
        var cost = 0;
        final var length = arr.length;
        for (int i = 0; i < length - 1; i++) {
            var minpos = i;
            var min = arr[i];
            for (int j = i; j < length; j++) {
                final var val = arr[j];
                if (val < min) {
                    min = val;
                    minpos = j;
                }
            }
            cost += reverseVals(arr, i, minpos - i + 1);
        }

        return cost;
    }

    public static int reverseVals(final Integer[] arr, final int start, final int size) {
        // System.out.println(String.format("Reversing from %d to %d cost of %d", start, start + size, size + 1));
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
}

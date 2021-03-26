import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        final var in = new Scanner(System.in);

        final int count = Integer.parseInt(in.nextLine());

        for (int i = 0; i < count; i++) {
            final var els = in.nextLine().split(" ");
            final var length = Integer.parseInt(els[0]);
            final var cost = Integer.parseInt(els[1]);

            System.out.println(String.format("Case #%d: (%d %d)", i + 1, length, cost));
        }

        in.close();
    }
}

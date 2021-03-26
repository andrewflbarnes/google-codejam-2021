import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        final var in = new Scanner(System.in);

        final int count = Integer.parseInt(in.nextLine());

        for (int i = 0; i < count; i++) {
            final var els = in.nextLine().split(" ");

            final var cjCost = Integer.parseInt(els[0]);
            final var jcCost = Integer.parseInt(els[1]);
            final var mural = els[2];

            byte last = 0;
            int cost = 0;

            for (var pic : mural.getBytes()) {
                if (pic == '?') {
                    pic = last;
                }

                if (pic == 'C' && last == 'J') {
                    cost += jcCost;
                } else if (pic == 'J' && last == 'C') {
                    cost += cjCost;
                }
                last = pic;
            }

            System.out.println(String.format("Case #%d: %d", i + 1, cost));
        }

        in.close();
    }
}

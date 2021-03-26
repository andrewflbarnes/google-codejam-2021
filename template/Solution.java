import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        final var in = new Scanner(System.in);

        final int count = Integer.parseInt(in.nextLine());

        for (int i = 0; i < count; i++) {
            final var line = in.nextLine();
            System.out.println(line);
        }

        in.close();
    }
}

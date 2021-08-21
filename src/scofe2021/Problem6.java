package scofe2021;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Problem6 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] widthAndHeight = scanner.nextLine().split(" ");
        int width = Integer.parseInt(widthAndHeight[0]);
        int height = Integer.parseInt(widthAndHeight[1]);

        String[][] space = new String[height][width];
        for (int i = 0; i < height; i++) {
            space[i] = scanner.nextLine().split(" ");
        }

        Set<Integer> result = new HashSet<>();
        new Problem6().dfs(space, 0, 0, 0, result);

        int maxNumberOfClothes = result.stream()
                .max(Comparator.naturalOrder())
                .get();

        System.out.println(maxNumberOfClothes);
    }

    private void dfs(String[][] space, int y, int x, int numberOfClothes, Set<Integer> result) {
        numberOfClothes += Integer.parseInt(space[y][x]);

        if (y == space.length - 1 && x == space[y].length - 1) {
            result.add(numberOfClothes);
            return;
        }

        if (y < space.length - 1) {
            dfs(space, y + 1, x, numberOfClothes, result);
        }

        if (x < space[y].length - 1) {
            dfs(space, y, x + 1, numberOfClothes, result);
        }
    }
}

package scofe2021;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Problem5 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] widthAndHeight = scanner.nextLine().split(" ");
        int width = Integer.parseInt(widthAndHeight[0]);
        int height = Integer.parseInt(widthAndHeight[1]);

        char[][] displayInfo = new char[height][width];
        for (int i = 0; i < height; i++) {
            displayInfo[i] = scanner.nextLine().toCharArray();
        }

        Set<Integer> result = new HashSet<>();
        for (int x = 0; x < width; x++) {
            if (displayInfo[0][x] == 'c') {
                new Problem5().dfs(displayInfo, 0, x, x, 0, result);
            }
        }

        if (result.isEmpty()) {
            System.out.println("-1");
            return;
        }

        int minMovedHorizontal = result.stream()
                .min(Comparator.naturalOrder())
                .get();

        System.out.println(minMovedHorizontal);
    }

    private void dfs(char[][] displayInfo, int y, int x, int beforeX, int movedHorizontal, Set<Integer> result) {
        if (x < 0 || x >= displayInfo[y].length) {
            return;
        }

        if (displayInfo[y][x] == 'x') {
            return;
        }

        if (y == displayInfo.length - 1) {
            result.add(movedHorizontal);
            return;
        }

        dfs(displayInfo, y + 1, x, x, movedHorizontal, result);

        if (x <= beforeX) {
            dfs(displayInfo, y, x - 1, x, movedHorizontal + 1, result);
        }

        if (x >= beforeX) {
            dfs(displayInfo, y, x + 1, x, movedHorizontal + 1, result);
        }
    }
}

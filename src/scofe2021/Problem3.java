package scofe2021;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Problem3 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int length = Integer.parseInt(scanner.nextLine());
        char[][] space = new char[length][length];

        for (int i = 0; i < length; i++) {
            space[i] = scanner.nextLine().toCharArray();
        }

        List<Integer> answer = new ArrayList<>();

        for (int size = 1; size <= length; size++) {
            int numberOfCases = 0;

            for (int y = 0; y < space.length; y++) {
                for (int x = 0; x < space[y].length; x++) {
                    if (space[y][x] == '0') {
                        continue;
                    }

                    boolean canBePlaced = new Problem3().canBePlaced(y, x, size, space);
                    if (canBePlaced) {
                        numberOfCases++;
                    }
                }
            }

            if (numberOfCases == 0) {
                break;
            } else {
                answer.add(numberOfCases);
            }
        }

        long total = answer.stream()
                .collect(Collectors.summarizingInt(i -> i))
                .getSum();

        System.out.println("total: " + total);
        for (int i = 0; i < answer.size(); i++) {
            System.out.printf("size[%d]: %d%n", i + 1, answer.get(i));
        }
    }

    private boolean canBePlaced(int startY, int startX, int size, char[][] space) {
        for (int y = startY; y < startY + size; y++) {
            for (int x = startX; x < startX + size; x++) {
                if (space[y][x] == '0') {
                    return false;
                }
            }
        }

        return true;
    }
}

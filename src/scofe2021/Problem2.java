package scofe2021;

import java.util.Scanner;

public class Problem2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int length = Integer.parseInt(scanner.nextLine());
        char[] route = scanner.nextLine().toCharArray();

        int numberOfCases = new Problem2().dfs(route, 0);
        System.out.println(numberOfCases);
    }

    private int dfs(char[] route, int index) {
        if (index >= route.length || route[index] == '0') {
            return 0;
        }

        if (index == route.length - 1) {
            return 1;
        }

        return dfs(route, index + 1) + dfs(route, index + 2);
    }
}

package programmers.dynamicprogramming;

public class IntegerTriangle {

    public static void main(String[] args) {
        new IntegerTriangle().solution(new int[][]{{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}});
    }

    public int solution(int[][] triangle) {
        for (int i = triangle.length - 1; i > 0; i--) {
            for (int j = 0; j < triangle[i].length - 1; j++) {
                int left = triangle[i][j];
                int right = triangle[i][j + 1];
                triangle[i - 1][j] += Math.max(left, right);
            }
        }
        return triangle[0][0];
    }

    private int dfs(int[][] triangle, int y, int x, int sum) {
        if (y == triangle.length) {
            return sum;
        }

        int num = triangle[y][x];
        int maxSum = 0;
        maxSum = Math.max(maxSum, dfs(triangle, y + 1, x, sum + num));
        maxSum = Math.max(maxSum, dfs(triangle, y + 1, x + 1, sum + num));

        return maxSum;
    }
}

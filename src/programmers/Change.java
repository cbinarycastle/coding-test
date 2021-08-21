package programmers;

public class Change {

    public int solution(int n, int[] money) {
        int[][] answer = new int[money.length][n + 1];
        for (int i = 0; i < n + 1; i++) {
            answer[0][i] = i % money[0] == 0 ? 1 : 0;
        }

        for (int i = 1; i < money.length; i++) {
            for (int j = 0; j < n + 1; j++) {
                answer[i][j] = answer[i - 1][j];
                for (int k = 1; k <= j / money[i]; k++) {
                    answer[i][j] += answer[i - 1][j - money[i] * k];
                }
            }
        }

        return answer[money.length - 1][n] % 1000000007;
    }
}

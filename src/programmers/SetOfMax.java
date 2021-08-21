package programmers;

import java.util.Arrays;

public class SetOfMax {
    public static void main(String[] args) {
        int[] answer = new SetOfMax().solution(2, 9);
        Arrays.stream(answer)
                .forEach(System.out::println);
    }

    public int[] solution(int n, int s) {
        if (n > s) {
            return new int[]{ -1 };
        }

        int value = s / n;
        int[] answer = new int[n];

        for (int i = 0; i < n; i++) {
            answer[i] = value;
        }

        int diff = s - value * n;
        int count = 1;
        while (diff > 0) {
            answer[n - count++] += 1;
            diff--;
        }

        return answer;
    }
}

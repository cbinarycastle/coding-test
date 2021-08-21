package programmers.kakao2021;

public class Problem1 {

    public int solution(int[] giftCards, int[] wants) {
        for (int i = 0; i < wants.length; i++) {
            if (wants[i] == giftCards[i]) {
                continue;
            }

            for (int j = i + 1; j < giftCards.length; j++) {
                if (wants[i] == giftCards[j]) {
                    swap(giftCards, i, j);
                    break;
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < giftCards.length; i++) {
            if (giftCards[i] != wants[i]) {
                answer++;
            }
        }

        return answer;
    }

    private void swap(int[] arr, int srcIndex, int dstIndex) {
        int temp = arr[srcIndex];
        arr[srcIndex] = arr[dstIndex];
        arr[dstIndex] = temp;
    }
}

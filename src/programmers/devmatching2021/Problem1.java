package programmers.devmatching2021;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Problem1 {

    public int[] solution(int[] lottos, int[] winNums) {
        List<Integer> lottosWithoutZero = Arrays.stream(lottos)
                .filter(lotto -> lotto > 0)
                .boxed()
                .collect(Collectors.toList());

        int winning = 0;
        for (int i = 0; i < winNums.length; i++) {
            for (int lotto : lottosWithoutZero) {
                if (lotto == winNums[i]) {
                    winning++;
                    break;
                }
            }
        }

        int minRank = getRank(winning);
        int maxRank = getRank(winning + (Math.min(winNums.length - winning, lottos.length - lottosWithoutZero.size())));
        return new int[] {maxRank, minRank};
    }

    private int getRank(int winning) {
        if (winning <= 1) {
            return 6;
        }
        return 6 - winning + 1;
    }
}

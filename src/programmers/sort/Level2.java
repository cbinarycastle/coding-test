package programmers.sort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Level2 {

    /**
     * 문제 설명
     * 0 또는 양의 정수가 주어졌을 때, 정수를 이어 붙여 만들 수 있는 가장 큰 수를 알아내 주세요.
     * <p>
     * 예를 들어, 주어진 정수가 [6, 10, 2]라면 [6102, 6210, 1062, 1026, 2610, 2106]를 만들 수 있고, 이중 가장 큰 수는 6210입니다.
     * <p>
     * 0 또는 양의 정수가 담긴 배열 numbers가 매개변수로 주어질 때, 순서를 재배치하여 만들 수 있는 가장 큰 수를 문자열로 바꾸어 return 하도록 solution 함수를 작성해주세요.
     * <p>
     * 제한 사항
     * numbers의 길이는 1 이상 100,000 이하입니다.
     * numbers의 원소는 0 이상 1,000 이하입니다.
     * 정답이 너무 클 수 있으니 문자열로 바꾸어 return 합니다.
     * 입출력 예
     * numbers	return
     * [6, 10, 2]	"6210"
     * [3, 30, 34, 5, 9]	"9534330"
     */
    public String problem1(int[] numbers) {
        return Arrays.stream(numbers)
                .mapToObj(String::valueOf)
                .sorted((s1, s2) -> {
                    char[] c1Arr = s1.toCharArray();
                    char[] c2Arr = s2.toCharArray();

                    int maxLength = Math.max(c1Arr.length, c2Arr.length);
                    for (int i = 0; i < maxLength; i++) {
                        char c1 = i < c1Arr.length ? c1Arr[i] : c1Arr[c1Arr.length - 1];
                        char c2 = i < c2Arr.length ? c2Arr[i] : c2Arr[c2Arr.length - 1];

                        if (c1 == c2) {
                            continue;
                        }
                        return c2 - c1;
                    }

                    return 0;
                })
                .collect(Collectors.joining());
    }

    /**
     * 문제 설명
     * H-Index는 과학자의 생산성과 영향력을 나타내는 지표입니다. 어느 과학자의 H-Index를 나타내는 값인 h를 구하려고 합니다. 위키백과1에 따르면, H-Index는 다음과 같이 구합니다.
     *
     * 어떤 과학자가 발표한 논문 n편 중, h번 이상 인용된 논문이 h편 이상이고 나머지 논문이 h번 이하 인용되었다면 h의 최댓값이 이 과학자의 H-Index입니다.
     *
     * 어떤 과학자가 발표한 논문의 인용 횟수를 담은 배열 citations가 매개변수로 주어질 때, 이 과학자의 H-Index를 return 하도록 solution 함수를 작성해주세요.
     *
     * 제한사항
     * 과학자가 발표한 논문의 수는 1편 이상 1,000편 이하입니다.
     * 논문별 인용 횟수는 0회 이상 10,000회 이하입니다.
     * 입출력 예
     * citations	return
     * [3, 0, 6, 1, 5]	3
     * 입출력 예 설명
     * 이 과학자가 발표한 논문의 수는 5편이고, 그중 3편의 논문은 3회 이상 인용되었습니다. 그리고 나머지 2편의 논문은 3회 이하 인용되었기 때문에 이 과학자의 H-Index는 3입니다.
     *
     * ※ 공지 - 2019년 2월 28일 테스트 케이스가 추가되었습니다.
     */
    public int problem2(int[] citations) {
        List<Integer> sortedCitations = Arrays.stream(citations)
                .boxed()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        int hIndex = 0;
        for (int i = 0; i < sortedCitations.size(); i++) {
            int citation = sortedCitations.get(i);
            int h = i + 1;

            if (citation >= h) {
                hIndex = h;
            }
        }

        return hIndex;
    }
}

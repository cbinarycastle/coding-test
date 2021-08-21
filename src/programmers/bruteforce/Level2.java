package programmers.bruteforce;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Level2 {

    /**
     * 문제 설명
     * 한자리 숫자가 적힌 종이 조각이 흩어져있습니다. 흩어진 종이 조각을 붙여 소수를 몇 개 만들 수 있는지 알아내려 합니다.
     *
     * 각 종이 조각에 적힌 숫자가 적힌 문자열 numbers가 주어졌을 때, 종이 조각으로 만들 수 있는 소수가 몇 개인지 return 하도록 solution 함수를 완성해주세요.
     *
     * 제한사항
     * numbers는 길이 1 이상 7 이하인 문자열입니다.
     * numbers는 0~9까지 숫자만으로 이루어져 있습니다.
     * "013"은 0, 1, 3 숫자가 적힌 종이 조각이 흩어져있다는 의미입니다.
     * 입출력 예
     * numbers	return
     * "17"	3
     * "011"	2
     * 입출력 예 설명
     * 예제 #1
     * [1, 7]으로는 소수 [7, 17, 71]를 만들 수 있습니다.
     *
     * 예제 #2
     * [0, 1, 1]으로는 소수 [11, 101]를 만들 수 있습니다.
     *
     * 11과 011은 같은 숫자로 취급합니다.
     */
    public int problem1(String numbers) {
        char[] numberChars = numbers.toCharArray();
        List<String> digits = new ArrayList<>();

        for (char numberChar : numberChars) {
            digits.add(String.valueOf(numberChar));
        }

        Set<Integer> primeNumbers = new HashSet<>();
        recursive("", digits, primeNumbers);

        return primeNumbers.size();
    }

    private void recursive(String prefix, List<String> digits, Set<Integer> primeNumbers) {
        for (int i = 0; i < digits.size(); i++) {
            String mergedNumberStr = prefix + digits.get(i);
            int mergedNumber = Integer.parseInt(mergedNumberStr);

            if (isPrime(mergedNumber)) {
                primeNumbers.add(mergedNumber);
            }

            List<String> newDigits = new ArrayList<>(digits);
            newDigits.remove(i);
            recursive(mergedNumberStr, newDigits, primeNumbers);
        }
    }

    private boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * 문제 설명
     * Leo는 카펫을 사러 갔다가 아래 그림과 같이 중앙에는 노란색으로 칠해져 있고 테두리 1줄은 갈색으로 칠해져 있는 격자 모양 카펫을 봤습니다.
     *
     * Leo는 집으로 돌아와서 아까 본 카펫의 노란색과 갈색으로 색칠된 격자의 개수는 기억했지만, 전체 카펫의 크기는 기억하지 못했습니다.
     *
     * Leo가 본 카펫에서 갈색 격자의 수 brown, 노란색 격자의 수 yellow가 매개변수로 주어질 때 카펫의 가로, 세로 크기를 순서대로 배열에 담아 return 하도록 solution 함수를 작성해주세요.
     *
     * 제한사항
     * 갈색 격자의 수 brown은 8 이상 5,000 이하인 자연수입니다.
     * 노란색 격자의 수 yellow는 1 이상 2,000,000 이하인 자연수입니다.
     * 카펫의 가로 길이는 세로 길이와 같거나, 세로 길이보다 깁니다.
     * 입출력 예
     * brown	yellow	return
     * 10	2	[4, 3]
     * 8	1	[3, 3]
     * 24	24	[8, 6]
     */
    public int[] problem2(int brown, int yellow) {
        int totalElements = brown + yellow;

        for (int i = 3; i <= totalElements / 2; i++) {
            int x = totalElements / i;
            int y = totalElements / x;

            if (x * y != totalElements) {
                continue;
            }

            int brownInCarpet = x * 2 + (y - 2) * 2;
            if (brown == brownInCarpet) {
                return new int[] {x, y};
            }
        }

        return null;
    }
}

package programmers.hash;

import java.util.*;

public class Level2 {

    /**
     * 문제 설명
     * 전화번호부에 적힌 전화번호 중, 한 번호가 다른 번호의 접두어인 경우가 있는지 확인하려 합니다.
     * 전화번호가 다음과 같을 경우, 구조대 전화번호는 영석이의 전화번호의 접두사입니다.
     *
     * 구조대 : 119
     * 박준영 : 97 674 223
     * 지영석 : 11 9552 4421
     * 전화번호부에 적힌 전화번호를 담은 배열 phone_book 이 solution 함수의 매개변수로 주어질 때, 어떤 번호가 다른 번호의 접두어인 경우가 있으면 false를 그렇지 않으면 true를 return 하도록 solution 함수를 작성해주세요.
     *
     * 제한 사항
     * phone_book의 길이는 1 이상 1,000,000 이하입니다.
     * 각 전화번호의 길이는 1 이상 20 이하입니다.
     * 같은 전화번호가 중복해서 들어있지 않습니다.
     * 입출력 예제
     * phone_book	return
     * ["119", "97674223", "1195524421"]	false
     * ["123","456","789"]	true
     * ["12","123","1235","567","88"]	false
     * 입출력 예 설명
     * 입출력 예 #1
     * 앞에서 설명한 예와 같습니다.
     *
     * 입출력 예 #2
     * 한 번호가 다른 번호의 접두사인 경우가 없으므로, 답은 true입니다.
     *
     * 입출력 예 #3
     * 첫 번째 전화번호, “12”가 두 번째 전화번호 “123”의 접두사입니다. 따라서 답은 false입니다.
     *
     * 알림
     *
     * 2021년 3월 4일, 테스트 케이스가 변경되었습니다. 이로 인해 이전에 통과하던 코드가 더 이상 통과하지 않을 수 있습니다.
     */
    public boolean problem1(String[] phoneBook) {
        List<String> phoneBookList = Arrays.asList(phoneBook);
        phoneBookList.sort(Comparator.naturalOrder());

        for (int index = 0; index < phoneBookList.size() - 1; index++) {
            String phone = phoneBookList.get(index);
            String nextPhone = phoneBookList.get(index + 1);
            if (nextPhone.startsWith(phone)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 문제 설명
     * 스파이들은 매일 다른 옷을 조합하여 입어 자신을 위장합니다.
     *
     * 예를 들어 스파이가 가진 옷이 아래와 같고 오늘 스파이가 동그란 안경, 긴 코트, 파란색 티셔츠를 입었다면 다음날은 청바지를 추가로 입거나 동그란 안경 대신 검정 선글라스를 착용하거나 해야 합니다.
     *
     * 종류	이름
     * 얼굴	동그란 안경, 검정 선글라스
     * 상의	파란색 티셔츠
     * 하의	청바지
     * 겉옷	긴 코트
     * 스파이가 가진 의상들이 담긴 2차원 배열 clothes가 주어질 때 서로 다른 옷의 조합의 수를 return 하도록 solution 함수를 작성해주세요.
     *
     * 제한사항
     * clothes의 각 행은 [의상의 이름, 의상의 종류]로 이루어져 있습니다.
     * 스파이가 가진 의상의 수는 1개 이상 30개 이하입니다.
     * 같은 이름을 가진 의상은 존재하지 않습니다.
     * clothes의 모든 원소는 문자열로 이루어져 있습니다.
     * 모든 문자열의 길이는 1 이상 20 이하인 자연수이고 알파벳 소문자 또는 '_' 로만 이루어져 있습니다.
     * 스파이는 하루에 최소 한 개의 의상은 입습니다.
     * 입출력 예
     * clothes	return
     * [["yellowhat", "headgear"], ["bluesunglasses", "eyewear"], ["green_turban", "headgear"]]	5
     * [["crowmask", "face"], ["bluesunglasses", "face"], ["smoky_makeup", "face"]]	3
     * 입출력 예 설명
     * 예제 #1
     * headgear에 해당하는 의상이 yellow_hat, green_turban이고 eyewear에 해당하는 의상이 blue_sunglasses이므로 아래와 같이 5개의 조합이 가능합니다.
     *
     * 1. yellow_hat
     * 2. blue_sunglasses
     * 3. green_turban
     * 4. yellow_hat + blue_sunglasses
     * 5. green_turban + blue_sunglasses
     * 예제 #2
     * face에 해당하는 의상이 crow_mask, blue_sunglasses, smoky_makeup이므로 아래와 같이 3개의 조합이 가능합니다.
     *
     * 1. crow_mask
     * 2. blue_sunglasses
     * 3. smoky_makeup
     */
    public int problem2(String[][] clothes) {
        int answer = 1;

        Map<String, Integer> numberOfClothesMap = new HashMap<>();
        for (int i = 0; i < clothes.length; i++) {
            String kind  = clothes[i][1];
            numberOfClothesMap.put(kind, numberOfClothesMap.getOrDefault(kind, 1) + 1);
        }

        for (String kind : numberOfClothesMap.keySet()) {
            answer *= numberOfClothesMap.get(kind);
        }

        return answer - 1;
    }
}

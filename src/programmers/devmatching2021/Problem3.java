package programmers.devmatching2021;

import java.util.*;
import java.util.stream.Collectors;

public class Problem3 {

    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        Map<String, Seller> sellers = new HashMap<>();
        sellers.put("-", new Seller("center"));
        for (int i = 0; i < enroll.length; i++) {
            sellers.put(enroll[i], new Seller(enroll[i]));
        }

        for (int i = 0; i < enroll.length; i++) {
            Seller referralSeller = sellers.get(referral[i]);
            sellers.get(enroll[i]).setReferral(referralSeller);
        }

        for (int i = 0; i < amount.length; i++) {
            sellers.get(seller[i]).sell(amount[i] * 100);
        }

        return sellers.values().stream()
                .map(Seller::getAmount)
                .mapToInt(i -> i)
                .toArray();
    }

    public static class Seller {
        private String name;
        private int amount;
        private Seller referral;

        public Seller(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setReferral(Seller referral) {
            this.referral = referral;
        }

        public int getAmount() {
            return amount;
        }

        public void sell(int amount) {
            int referralAmount = (int) (amount * 0.1);
            int saleAmount = amount - referralAmount;

            this.amount += saleAmount;
            referral.sell(referralAmount);
        }
    }
}

package programmers;

import java.util.*;
import java.util.stream.Collectors;

public class Delivery {
    public int solution(int n, int[][] road, int k) {
        Set<Integer> deliverableSet = new HashSet<>();
        calc(1, Arrays.stream(road).collect(Collectors.toList()), k, 0, new ArrayList<>(), deliverableSet);
        return deliverableSet.size();
    }

    private void calc(int dpt, List<int[]> road, int k, int accumulate, List<int[]> visited, Set<Integer> deliverableSet) {
        for (int[] root : road) {
            int a = root[0], b = root[1], time = root[2];

            if (!visited.contains(root) && (a == dpt || b == dpt) && accumulate + time <= k) {
                visited.add(root);
                deliverableSet.add(a);
                deliverableSet.add(b);
                calc((a == dpt ? b : a), road, k, accumulate + time, visited, deliverableSet);
            }
        }
    }
}

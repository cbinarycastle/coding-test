package programmers;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DiskController {
    public int solution(int[][] jobs) {
        List<int[]> jobList = Arrays.stream(jobs)
                .sorted(Comparator.comparingInt(value -> value[0]))
                .collect(Collectors.toList());

        int currentTime = 0;
        int sum = 0;
        while (!jobList.isEmpty()) {
            int[] targetJob = jobList.get(0);
            if (currentTime < targetJob[0]) {
                currentTime = targetJob[0];
            }

            for (int[] job : jobList) {
                if (job[0] <= currentTime) {
                    if (job[1] + (currentTime - job[0]) < targetJob[1] + (currentTime - targetJob[0])) {
                        targetJob = job;
                    }
                }
            }

            int time = targetJob[1] + (currentTime - targetJob[0]);
            sum += time;
            currentTime += targetJob[1];
            jobList.remove(targetJob);
        }

        return sum / jobs.length;
    }
}

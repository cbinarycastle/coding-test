package programmers.stackqueue;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Level2 {

    public int problem1(int bridgeLength, int weight, int[] truckWeights) {
        Queue<Integer> queue = new ArrayDeque<>();
        int sumTruckWeight = 0;
        int time = 0;
        int index = 0;

        while (index < truckWeights.length) {
            int truckWeight = truckWeights[index];

            if (queue.size() == bridgeLength) {
                int passedTruckWeight = queue.poll();
                sumTruckWeight -= passedTruckWeight;
            }

            if (sumTruckWeight + truckWeight <= weight) {
                queue.add(truckWeight);
                sumTruckWeight += truckWeight;
                index++;

            } else {
                queue.add(0);
            }

            time++;

            if (index == truckWeights.length) {
                time += bridgeLength;
            }
        }

        return time;
    }

    /**
     * 문제 설명
     * 초 단위로 기록된 주식가격이 담긴 배열 prices가 매개변수로 주어질 때, 가격이 떨어지지 않은 기간은 몇 초인지를 return 하도록 solution 함수를 완성하세요.
     *
     * 제한사항
     * prices의 각 가격은 1 이상 10,000 이하인 자연수입니다.
     * prices의 길이는 2 이상 100,000 이하입니다.
     * 입출력 예
     * prices	return
     * [1, 2, 3, 2, 3]	[4, 3, 1, 1, 0]
     * 입출력 예 설명
     * 1초 시점의 ₩1은 끝까지 가격이 떨어지지 않았습니다.
     * 2초 시점의 ₩2은 끝까지 가격이 떨어지지 않았습니다.
     * 3초 시점의 ₩3은 1초뒤에 가격이 떨어집니다. 따라서 1초간 가격이 떨어지지 않은 것으로 봅니다.
     * 4초 시점의 ₩2은 1초간 가격이 떨어지지 않았습니다.
     * 5초 시점의 ₩3은 0초간 가격이 떨어지지 않았습니다.
     * ※ 공지 - 2019년 2월 28일 지문이 리뉴얼되었습니다.
     */
    public int[] problem2(int[] prices) {
        int[] answer = new int[prices.length];

        Stack<Integer> indexStack = new Stack<>();

        for (int i = 0; i < prices.length; i++) {
            int price = prices[i];

            while (!indexStack.isEmpty() && price < prices[indexStack.peek()]) {
                int targetIndex = indexStack.pop();
                answer[targetIndex] = i - targetIndex;
            }

            indexStack.add(i);
        }

        while (!indexStack.isEmpty()) {
            int index = indexStack.pop();
            answer[index] = prices.length - 1 - index;
        }

        return answer;
    }

    /**
     * 문제 설명
     * 프로그래머스 팀에서는 기능 개선 작업을 수행 중입니다. 각 기능은 진도가 100%일 때 서비스에 반영할 수 있습니다.
     *
     * 또, 각 기능의 개발속도는 모두 다르기 때문에 뒤에 있는 기능이 앞에 있는 기능보다 먼저 개발될 수 있고, 이때 뒤에 있는 기능은 앞에 있는 기능이 배포될 때 함께 배포됩니다.
     *
     * 먼저 배포되어야 하는 순서대로 작업의 진도가 적힌 정수 배열 progresses와 각 작업의 개발 속도가 적힌 정수 배열 speeds가 주어질 때 각 배포마다 몇 개의 기능이 배포되는지를 return 하도록 solution 함수를 완성하세요.
     *
     * 제한 사항
     * 작업의 개수(progresses, speeds배열의 길이)는 100개 이하입니다.
     * 작업 진도는 100 미만의 자연수입니다.
     * 작업 속도는 100 이하의 자연수입니다.
     * 배포는 하루에 한 번만 할 수 있으며, 하루의 끝에 이루어진다고 가정합니다. 예를 들어 진도율이 95%인 작업의 개발 속도가 하루에 4%라면 배포는 2일 뒤에 이루어집니다.
     * 입출력 예
     * progresses	speeds	return
     * [93, 30, 55]	[1, 30, 5]	[2, 1]
     * [95, 90, 99, 99, 80, 99]	[1, 1, 1, 1, 1, 1]	[1, 3, 2]
     * 입출력 예 설명
     * 입출력 예 #1
     * 첫 번째 기능은 93% 완료되어 있고 하루에 1%씩 작업이 가능하므로 7일간 작업 후 배포가 가능합니다.
     * 두 번째 기능은 30%가 완료되어 있고 하루에 30%씩 작업이 가능하므로 3일간 작업 후 배포가 가능합니다. 하지만 이전 첫 번째 기능이 아직 완성된 상태가 아니기 때문에 첫 번째 기능이 배포되는 7일째 배포됩니다.
     * 세 번째 기능은 55%가 완료되어 있고 하루에 5%씩 작업이 가능하므로 9일간 작업 후 배포가 가능합니다.
     *
     * 따라서 7일째에 2개의 기능, 9일째에 1개의 기능이 배포됩니다.
     *
     * 입출력 예 #2
     * 모든 기능이 하루에 1%씩 작업이 가능하므로, 작업이 끝나기까지 남은 일수는 각각 5일, 10일, 1일, 1일, 20일, 1일입니다. 어떤 기능이 먼저 완성되었더라도 앞에 있는 모든 기능이 완성되지 않으면 배포가 불가능합니다.
     *
     * 따라서 5일째에 1개의 기능, 10일째에 3개의 기능, 20일째에 2개의 기능이 배포됩니다.
     *
     * ※ 공지 - 2020년 7월 14일 테스트케이스가 추가되었습니다.
     */
    public int[] problem3(int[] progresses, int[] speeds) {
        List<Integer> answer = new ArrayList<>();

        Queue<Job> jobQueue = IntStream.iterate(0, i -> i + 1)
                .limit(progresses.length)
                .mapToObj(i -> new Job(progresses[i], speeds[i]))
                .collect(Collectors.toCollection(LinkedList::new));

        while (!jobQueue.isEmpty()) {
            int numberOfDeploy = 0;
            int days = jobQueue.peek().remainedDays();

            for (Job job : jobQueue) {
                job.work(days);
            }

            while (!jobQueue.isEmpty() && jobQueue.peek().isCompleted()) {
                jobQueue.poll();
                numberOfDeploy++;
            }

            answer.add(numberOfDeploy);
        }

        return answer.stream()
                .mapToInt(i -> i)
                .toArray();
    }

    private static class Job {

        private int progress;
        private int speed;

        public Job(int progress, int speed) {
            this.progress = progress;
            this.speed = speed;
        }

        public int remainedDays() {
            return (int) Math.ceil((double) (100 - progress) / speed);
        }

        public void work(int days) {
            progress += speed * days;
        }

        public boolean isCompleted() {
            return remainedDays() <= 0;
        }
    }

    public int problem3(int[] priorities, int location) {
        Queue<PrintRequest> waitingQueue = IntStream.iterate(0, i -> i + 1)
                .limit(priorities.length)
                .mapToObj(i -> new PrintRequest(i, priorities[i]))
                .collect(Collectors.toCollection(LinkedList::new));

        Queue<PrintRequest> priorityQueue = new PriorityQueue<>(Comparator.reverseOrder());
        priorityQueue.addAll(waitingQueue);

        int count = 0;
        while (!waitingQueue.isEmpty()) {
            PrintRequest printRequest = waitingQueue.poll();

            if (printRequest.priority == priorityQueue.peek().priority) {
                priorityQueue.poll();
                count++;
                if (printRequest.position == location) {
                    return count;
                }
            } else {
                waitingQueue.add(printRequest);
            }
        }

        return 0;
    }

    private static class PrintRequest implements Comparable<PrintRequest> {

        private final int position;
        private final int priority;

        public PrintRequest(int position, int priority) {
            this.position = position;
            this.priority = priority;
        }

        @Override
        public int compareTo(PrintRequest o) {
            return priority - o.priority;
        }
    }
}

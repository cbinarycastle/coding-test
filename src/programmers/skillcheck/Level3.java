package programmers.skillcheck;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

public class Level3 {

    /**
     * 문제 설명
     * 이중 우선순위 큐는 다음 연산을 할 수 있는 자료구조를 말합니다.
     *
     * 명령어	수신 탑(높이)
     * I 숫자	큐에 주어진 숫자를 삽입합니다.
     * D 1	큐에서 최댓값을 삭제합니다.
     * D -1	큐에서 최솟값을 삭제합니다.
     * 이중 우선순위 큐가 할 연산 operations가 매개변수로 주어질 때, 모든 연산을 처리한 후 큐가 비어있으면 [0,0] 비어있지 않으면 [최댓값, 최솟값]을 return 하도록 solution 함수를 구현해주세요.
     *
     * 제한사항
     * operations는 길이가 1 이상 1,000,000 이하인 문자열 배열입니다.
     * operations의 원소는 큐가 수행할 연산을 나타냅니다.
     * 원소는 “명령어 데이터” 형식으로 주어집니다.- 최댓값/최솟값을 삭제하는 연산에서 최댓값/최솟값이 둘 이상인 경우, 하나만 삭제합니다.
     * 빈 큐에 데이터를 삭제하라는 연산이 주어질 경우, 해당 연산은 무시합니다.
     * 입출력 예
     * operations	return
     * ["I 16","D 1"]	[0,0]
     * ["I 7","I 5","I -5","D -1"]	[7,5]
     * 입출력 예 설명
     * 16을 삽입 후 최댓값을 삭제합니다. 비어있으므로 [0,0]을 반환합니다.
     * 7,5,-5를 삽입 후 최솟값을 삭제합니다. 최대값 7, 최소값 5를 반환합니다.
     */
    public int[] problem1(String[] operations) {
        PriorityQueue<Integer> minPriorityQueue = new PriorityQueue<>();
        PriorityQueue<Integer> maxPriorityQueue = new PriorityQueue<>(Collections.reverseOrder());

        AtomicInteger result = new AtomicInteger();
        Arrays.stream(operations)
                .map(Operation::from)
                .forEach(operation -> {
                    int count = operation.run(minPriorityQueue, maxPriorityQueue);
                    if (count > 0 || result.get() > 0) {
                        result.addAndGet(count);
                    }
                });

        if (result.get() == 0) {
            return new int[]{0, 0};
        } else {
            return new int[]{maxPriorityQueue.poll(), minPriorityQueue.poll()};
        }
    }

    private static class Operation {
        private final String command;
        private final int num;

        private Operation(String command, int num) {
            this.command = command;
            this.num = num;
        }

        public int run(Queue<Integer> minQueue, Queue<Integer> maxQueue) {
            if (command.equals("I")) {
                minQueue.add(num);
                maxQueue.add(num);
                return 1;
            } else {
                if (num == 1) {
                    maxQueue.poll();
                } else {
                    minQueue.poll();
                }
                return -1;
            }
        }

        public static Operation from(String operation) {
            String[] commandAndNum = operation.split(" ");
            String command = commandAndNum[0];
            int num = Integer.parseInt(commandAndNum[1]);

            return new Operation(command, num);
        }
    }

    /**
     * 문제 설명
     * 고고학자인 "튜브"는 고대 유적지에서 보물과 유적이 가득할 것으로 추정되는 비밀의 문을 발견하였습니다. 그런데 문을 열려고 살펴보니 특이한 형태의 자물쇠로 잠겨 있었고 문 앞에는 특이한 형태의 열쇠와 함께 자물쇠를 푸는 방법에 대해 다음과 같이 설명해 주는 종이가 발견되었습니다.
     *
     * 잠겨있는 자물쇠는 격자 한 칸의 크기가 1 x 1인 N x N 크기의 정사각 격자 형태이고 특이한 모양의 열쇠는 M x M 크기인 정사각 격자 형태로 되어 있습니다.
     *
     * 자물쇠에는 홈이 파여 있고 열쇠 또한 홈과 돌기 부분이 있습니다. 열쇠는 회전과 이동이 가능하며 열쇠의 돌기 부분을 자물쇠의 홈 부분에 딱 맞게 채우면 자물쇠가 열리게 되는 구조입니다. 자물쇠 영역을 벗어난 부분에 있는 열쇠의 홈과 돌기는 자물쇠를 여는 데 영향을 주지 않지만, 자물쇠 영역 내에서는 열쇠의 돌기 부분과 자물쇠의 홈 부분이 정확히 일치해야 하며 열쇠의 돌기와 자물쇠의 돌기가 만나서는 안됩니다. 또한 자물쇠의 모든 홈을 채워 비어있는 곳이 없어야 자물쇠를 열 수 있습니다.
     *
     * 열쇠를 나타내는 2차원 배열 key와 자물쇠를 나타내는 2차원 배열 lock이 매개변수로 주어질 때, 열쇠로 자물쇠를 열수 있으면 true를, 열 수 없으면 false를 return 하도록 solution 함수를 완성해주세요.
     *
     * 제한사항
     * key는 M x M(3 ≤ M ≤ 20, M은 자연수)크기 2차원 배열입니다.
     * lock은 N x N(3 ≤ N ≤ 20, N은 자연수)크기 2차원 배열입니다.
     * M은 항상 N 이하입니다.
     * key와 lock의 원소는 0 또는 1로 이루어져 있습니다.
     * 0은 홈 부분, 1은 돌기 부분을 나타냅니다.
     * 입출력 예
     * key	lock	result
     * [[0, 0, 0], [1, 0, 0], [0, 1, 1]]	[[1, 1, 1], [1, 1, 0], [1, 0, 1]]	true
     * 입출력 예에 대한 설명
     * key를 시계 방향으로 90도 회전하고, 오른쪽으로 한 칸, 아래로 한 칸 이동하면 lock의 홈 부분을 정확히 모두 채울 수 있습니다.
     */
    public boolean problem2(int[][] key, int[][] lock) {
        for (int i = 0; i < 4; i++) {
            for (int y = -(key.length - 1); y <= lock.length - 1 + key.length - 1; y++) {
                for (int x = -(key.length - 1); x <= lock.length - 1 + key.length - 1; x++) {
                    boolean result = check(key, lock, x, y);
                    if (result) {
                        return true;
                    }
                }
            }

            key = rotate(key);
        }

        return false;
    }

    private boolean check(int[][] key, int[][] lock, int startX, int startY) {
        int[][] newLock = new int[lock.length][lock.length];
        for (int i = 0; i < lock.length; i++) {
            newLock[i] = Arrays.copyOf(lock[i], lock.length);
        }

        for (int y = 0; y < newLock.length; y++) {
            int keyY = y - startY;

            for (int x = 0; x < newLock.length; x++) {
                int keyX = x - startX;

                if (keyY >= 0 && keyY < key.length && keyX >= 0 && keyX < key.length) {
                    newLock[y][x] += key[keyY][keyX];
                }

                if (newLock[y][x] != 1) {
                    return false;
                }
            }
        }

        return true;
    }

    private int[][] rotate(int[][] key) {
        int[][] rotatedKey = new int[key.length][key.length];

        for (int y = 0; y < key.length; y++) {
            for (int x = 0; x < key.length; x++) {
                rotatedKey[y][x] = key[key.length - 1 - x][y];
            }
        }

        return rotatedKey;
    }
}

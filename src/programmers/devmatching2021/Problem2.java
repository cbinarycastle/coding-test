package programmers.devmatching2021;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Problem2 {

    public int[] solution(int rows, int columns, int[][] queries) {
        int[][] arr = new int[rows][columns];
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < columns; x++) {
                arr[y][x] = y * rows + x + 1;
            }
        }

        List<Query> queryList = Arrays.stream(queries)
                .map(Query::from)
                .collect(Collectors.toList());
        List<Integer> minNums = new ArrayList<>();

        for (Query query : queryList) {
            int startX = query.getStart().getX();
            int startY = query.getStart().getY();
            int minNum = rotate(arr, query, Direction.RIGHT, query.getStart().toRight(), arr[startY][startX], rows * columns);
            minNums.add(minNum);
        }

        return minNums.stream()
                .mapToInt(i -> i)
                .toArray();
    }

    private int rotate(int[][] arr, Query query, Direction direction, Coordinate coordinate, int prevNum, int minNum) {
        int curNum = arr[coordinate.getY()][coordinate.getX()];
        arr[coordinate.getY()][coordinate.getX()] = prevNum;

        minNum = Math.min(curNum, minNum);

        switch (direction) {
            case RIGHT:
                if (coordinate.getX() == query.getEnd().getX()) {
                    return rotate(arr, query, Direction.DOWN, coordinate.toDown(), curNum, minNum);
                } else {
                    return rotate(arr, query, direction, coordinate.toRight(), curNum, minNum);
                }
            case DOWN:
                if (coordinate.getY() == query.getEnd().getY()) {
                    return rotate(arr, query, Direction.LEFT, coordinate.toLeft(), curNum, minNum);
                } else {
                    return rotate(arr, query, direction, coordinate.toDown(), curNum, minNum);
                }
            case LEFT:
                if (coordinate.getX() == query.getStart().getX()) {
                    return rotate(arr, query, Direction.UP, coordinate.toUp(), curNum, minNum);
                } else {
                    return rotate(arr, query, direction, coordinate.toLeft(), curNum, minNum);
                }
            case UP:
                if (coordinate.getY() != query.getStart().getY()) {
                    return rotate(arr, query, direction, coordinate.toUp(), curNum, minNum);
                }
        }

        return minNum;
    }

    public static class Coordinate {
        private int x;
        private int y;

        protected Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public Coordinate toUp() {
            return new Coordinate(x, y - 1);
        }

        public Coordinate toDown() {
            return new Coordinate(x, y + 1);
        }

        public Coordinate toLeft() {
            return new Coordinate(x - 1, y);
        }

        public Coordinate toRight() {
            return new Coordinate(x + 1, y);
        }
    }

    public static class Query {
        private Coordinate start;
        private Coordinate end;

        protected Query(Coordinate start, Coordinate end) {
            this.start = start;
            this.end = end;
        }

        public Coordinate getStart() {
            return start;
        }

        public Coordinate getEnd() {
            return end;
        }

        public static Query from(int[] query) {
            int startX = Math.min(query[1], query[3]) - 1;
            int startY = Math.min(query[0], query[2]) - 1;
            int endX = Math.max(query[1], query[3]) - 1;
            int endY = Math.max(query[0], query[2]) - 1;
            return new Query(new Coordinate(startX, startY), new Coordinate(endX, endY));
        }
    }

    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    public static void main(String[] args) {
        new Problem2().solution(100, 97, new int[][]{{1,1,100,97}});
    }
}

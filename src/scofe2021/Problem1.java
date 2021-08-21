package scofe2021;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Problem1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numberOfPeople = Integer.parseInt(scanner.nextLine());
        List<TimeRange> timeRanges = new ArrayList<>();

        for (int i = 0; i < numberOfPeople; i++) {
            String timeRangeInput = scanner.nextLine();
            timeRanges.add(TimeRange.from(timeRangeInput));
        }

        String startTime = timeRanges.stream()
                .map(TimeRange::getStart)
                .max(Comparator.naturalOrder())
                .get();

        String endTime = timeRanges.stream()
                .map(TimeRange::getEnd)
                .min(Comparator.naturalOrder())
                .get();

        if (startTime.compareTo(endTime) > 0) {
            System.out.println("-1");
        } else {
            System.out.println(startTime + " ~ " + endTime);
        }
    }

    public static class TimeRange {
        private final String start;
        private final String end;

        protected TimeRange(String start, String end) {
            this.start = start;
            this.end = end;
        }

        public String getStart() {
            return start;
        }

        public String getEnd() {
            return end;
        }

        public static TimeRange from(String str) {
            String[] startTimeAndEndTime = str.split(" ~ ");
            String startTime = startTimeAndEndTime[0];
            String endTime = startTimeAndEndTime[1];

            return new TimeRange(startTime, endTime);
        }
    }
}

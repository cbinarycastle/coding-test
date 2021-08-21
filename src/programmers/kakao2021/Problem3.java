package programmers.kakao2021;

import java.util.*;

public class Problem3 {

    public int[] solution(int n, int[] passenger, int[][] train) {
        List<Station> stations = new ArrayList<>();
        Map<Station, Queue<Station>> stationLink = new HashMap<>();

        for (int i = 0; i < n; i++) {
            Station station = new Station(i + 1, passenger[i]);
            stations.add(station);
            stationLink.put(station, new LinkedList<>());
        }

        for (int[] t : train) {
            Station a = stations.get(t[0] - 1);
            Station b = stations.get(t[1] - 1);
            stationLink.get(a).offer(b);
        }

        Map<Station, Integer> result = new HashMap<>();
        visit(stationLink, stations, 1, 0, result);

        return result.entrySet().stream()
                .sorted((o1, o2) -> {
                    if (o1.getValue() - o2.getValue() == 0) {
                        return o2.getKey().getNumber() - o1.getKey().getNumber();
                    }
                    return o2.getValue() - o1.getValue();
                })
                .findFirst()
                .map(entry -> new int[] {entry.getKey().getNumber(), entry.getValue()})
                .get();
    }

    private void visit(Map<Station, Queue<Station>> stationLink, List<Station> stations, int departure,
                       int numberOfPassengers, Map<Station, Integer> result) {

        Station departureStation = stations.get(departure - 1);
        Queue<Station> link = stationLink.get(departureStation);

        numberOfPassengers += departureStation.getPassengers();

        if (link.isEmpty()) {
            result.put(departureStation, numberOfPassengers);
            return;
        }

        while (!link.isEmpty()) {
            Station arrivalStation = link.poll();
            numberOfPassengers += arrivalStation.getPassengers();
            visit(stationLink, stations, arrivalStation.getNumber(),
                    numberOfPassengers + arrivalStation.getPassengers(), result);
        }
    }

    public static class Station {
        private final int number;
        private final int passengers;

        public Station(int number, int passengers) {
            this.number = number;
            this.passengers = passengers;
        }

        public int getNumber() {
            return number;
        }

        public int getPassengers() {
            return passengers;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Station station = (Station) o;
            return number == station.number;
        }

        @Override
        public int hashCode() {
            return Objects.hash(number);
        }
    }
}

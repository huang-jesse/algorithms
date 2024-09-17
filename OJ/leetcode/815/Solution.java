import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

class Solution {
    Map<Integer, Set<Integer>> stationsMap = new HashMap<>();
    Set<Integer> seenBuses = new HashSet<>();
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target)
            return 0;
        int numOfBuses = routes.length;
        for (int bus = 0; bus < numOfBuses; bus++) {
            for (int station : routes[bus]) {
                Set<Integer> buses = stationsMap.getOrDefault(station, new HashSet<>());
                buses.add(bus);
                stationsMap.put(station, buses);
            }
        }

        Queue<Integer> stationsQueue = new LinkedList<>();
        Set<Integer> seenStations = new HashSet<>();
        stationsQueue.offer(source);
        seenStations.add(source);
        int step = 0;
        while (!stationsQueue.isEmpty()) {
            step++;
            int size = stationsQueue.size();
            for (int i = 0; i < size; i++) {
                Integer currentStation = stationsQueue.poll();
                Set<Integer> nextStations = getNextStations(routes, currentStation);
                for (Integer nextStation : nextStations) {
                    if (!seenStations.contains(nextStation)) {
                        if (nextStation == target) {
                            return step;
                        }
                        stationsQueue.offer(nextStation);
                        seenStations.add(nextStation);
                    }
                }
            }
        }
        return -1;
    }

    private Set<Integer> getNextStations(int[][] routes, Integer currentStation) {
        Set<Integer> buses = stationsMap.getOrDefault(currentStation, Collections.emptySet());
        Set<Integer> nextStations = new HashSet<>();
        for (Integer bus : buses) {
            if (!seenBuses.contains(bus)) {
                int[] stations = routes[bus];
                nextStations.addAll(Arrays.stream(stations).boxed().collect(Collectors.toSet()));
                seenBuses.add(bus);
            }
        }
        return nextStations;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] routes = {{7,12},{4,5,15},{6},{15,19},{9,12,13}};
        int source = 7;
        int target = 13;
        System.out.println("test: " + sol.numBusesToDestination(routes, source, target));
    }
}
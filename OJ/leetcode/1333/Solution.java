import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Solution {
    public List<Integer> filterRestaurants(int[][] restaurants, int veganFriendly, int maxPrice, int maxDistance) {
        List<Integer> filteredRestaurants = Arrays.stream(restaurants)
        .filter(o -> {
            boolean veganFilter = (o[2] & veganFriendly) == veganFriendly;
            boolean maxPriceFilter = o[3] <= maxPrice;
            boolean maxDistanceFilter = o[4] <= maxDistance;
            return veganFilter && maxPriceFilter && maxDistanceFilter;
        })
        .sorted((o1, o2) -> {
            int ratingCompare = o2[1] - o1[1];
            if (ratingCompare == 0) {
                int idCompare = o2[0] - o1[0];
                return idCompare;
            } else {
                return ratingCompare;
            }
        })
        .map(o -> o[0])
        .collect(Collectors.toList());
        return filteredRestaurants;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] restaurants = {{1,4,1,40,10},{2,8,0,50,5},{3,8,1,30,4},{4,10,0,10,3},{5,1,1,15,1}};
        int veganFriendly = 1;
        int maxPrice = 50;
        int maxDistance = 10;
        System.out.println("test: " + sol.filterRestaurants(restaurants, veganFriendly, maxPrice, maxDistance));
    }
}
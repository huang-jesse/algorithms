import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

class Solution {
    public int[] closestRoom(int[][] rooms, int[][] queries) {
        List<Room> rs = new ArrayList<>();
        for (int i = 0; i < rooms.length; i++) {
            rs.add(new Room(rooms[i][0], rooms[i][1]));
        }
        rs.sort(Comparator.<Room>comparingInt(r -> r.size).reversed());
        System.out.println("rs: "+rs);
        List<Query> qs = new ArrayList<>();
        for (int i = 0; i < queries.length; i++) {
            qs.add(new Query(queries[i][0], queries[i][1], i));
        }
        qs.sort(Comparator.<Query>comparingInt(q -> q.minSize).reversed());
        System.out.println("qs: "+qs);

        int[] as = new int[queries.length];
        TreeSet<Integer> tm = new TreeSet<>();
        int index = 0;
        for (Query q : qs) {
            // filter minSize
            while (index < rs.size()) {
                Room curRoom = rs.get(index);
                if (q.minSize > rs.get(index).size) {
                    break;
                } else {
                    index++;
                    tm.add(curRoom.id);
                }
            }
            // check id
            if (tm.isEmpty()) {
                as[q.index] = -1;
            } else {
                Integer ceil = tm.ceiling(q.preferred);
                Integer floor = tm.floor(q.preferred);
                if (ceil == null) {
                    as[q.index] = floor;
                } else if (floor == null) {
                    as[q.index] = ceil;
                } else if (Math.abs(q.preferred - floor) <= Math.abs(q.preferred - ceil)) {
                    as[q.index] = floor;
                } else {
                    as[q.index] = ceil;
                }
            }
        }
        return as;
    }

    private static class Room {
        public int id;
        public int size;
        public Room(int id, int size) {
            this.id = id;
            this.size = size;
        }
        public String toString() {
            return "Id: " + this.id + " Size: " + this.size;
        }
    }
    private class Query {
        public int preferred;
        public int minSize;
        public int index;
        public Query(int preferred, int minSize, int index) {
            this.preferred = preferred;
            this.minSize = minSize;
            this.index = index;
        }
        public String toString() {
            return "preferred: " + this.preferred + " minSize: " + this.minSize;
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] rooms = new int[][]{{2,2},{1,2},{3,2}};
        int[][] queries = new int[][]{{3,1},{3,3},{5,2}};
        int[] as = sol.closestRoom(rooms, queries);
        System.out.println("as: " );
        for (int i = 0; i < as.length; i++) {
            System.out.print(as[i] + " ");
        }
    }
}
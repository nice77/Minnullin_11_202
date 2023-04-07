package friendGraph;

import models.User;

import java.util.*;
import java.util.stream.Collectors;

public class Graph {
    private Map<Integer, List<User>> graph;

    public Graph(User user, List<User> friends) {
        this.graph = new HashMap<>();
        this.add(user, friends);
    }

    public Graph() {
        this.graph = new HashMap<>();
    }

    public void add(User user, List<User> friends) {
        this.graph.put(user.getId(), friends);
    }

    public int BFS() {
        // Ключи - пользователи, которые находятся в группе
        // Значения - их друзья, которые тоже в группе
        List<User> queue = new LinkedList<>();
        List<Integer> visitedID = new LinkedList<>();
        List<Integer> keys = new ArrayList<>(this.graph.keySet());
        if (keys.size() == 0) {
            return 0;
        }
        int maximum = 0;
        for (int startPoint = 0; startPoint < keys.size(); startPoint++) {
            if (visitedID.contains(startPoint)) {
                continue;
            }
            int cnt = 1;
            queue.addAll(this.graph.get(keys.get(startPoint)));
            visitedID.add(keys.get(startPoint));
            User currUser;
            while (queue.size() != 0) {
                currUser = queue.remove(0);
                if (!keys.contains(currUser.getId()) && visitedID.contains(currUser.getId())) {
                    continue;
                }
                visitedID.add(currUser.getId());
                queue.addAll(this.graph.get(currUser.getId()).stream()
                        .filter(r -> !queue.contains(r) && !visitedID.contains(r.getId()))
                        .collect(Collectors.toList()));
                cnt++;
            }
            maximum = Math.max(cnt, maximum);
        }
        return (int) ((maximum / (double) keys.size()) * 100);
    }

    public String toString() {
        return this.graph.toString();
    }
}

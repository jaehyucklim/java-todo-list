package org.homework;

import java.util.HashMap;
import java.util.Map;

public class TodoManager {
    private final Map<Integer, String> tasks = new HashMap<>();
    private int currentId = 1;

    public int addTask(String task) {
        tasks.put(currentId, task);
        return currentId++;
    }

    public boolean deleteTask(int id) {
        if (tasks.containsKey(id)) {
            tasks.remove(id);
            return true;
        } else {
            return false;
        }
    }

    public String findTask(int id) {
        return tasks.getOrDefault(id, "해당 ID의 할 일이 없습니다.");
    }
}

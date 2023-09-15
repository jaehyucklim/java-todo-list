package org.homework;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TodoManager {
    private final Map<Integer, TodoItem> tasks = new HashMap<>();
    private int currentId = 1;

    public int addTask(String task) {
        TodoItem todoItem = new TodoItem(currentId, task);
        tasks.put(currentId, todoItem);
        return currentId++;
    }

    public boolean deleteTask(int id) {
        return tasks.remove(id) != null;
    }

    public List<TodoItem> getSortedTasks(int sortOption) {
        List<TodoItem> sortedTasks = new ArrayList<>(tasks.values());

        if (sortOption == 1) {
            sortedTasks.sort(Comparator.comparing(TodoItem::getId).reversed());
        } else if (sortOption == 2) {
            sortedTasks.sort(Comparator.comparing(TodoItem::getId));
        }
        return sortedTasks;
    }

    public TodoItem findTask(int id) {
        return tasks.getOrDefault(id, new TodoItem(-1, "해당 ID의 할 일이 없습니다.")); // 일치하지 않는 ID의 경우: -1
    }

    public boolean checkTask(int id) {
        return tasks.containsKey(id);
    }

    public void completeTask(int id) {
        TodoItem todoItem = tasks.get(id);
        if (todoItem != null) {
            todoItem.setCompleted(true);
        }
    }

    public List<TodoItem> seekTask(String keyword) {
        return tasks.values().stream()
                .filter(todoItem -> todoItem.getContent().contains(keyword))
                .collect(Collectors.toList());
    }
}

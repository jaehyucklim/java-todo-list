package org.homework;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class TodoList {
    private final Map<Integer, String> tasks;
    private final Scanner scanner;
    private int currentId;

    public TodoList(Scanner scanner) {
        this.tasks = new HashMap<>();
        this.scanner = scanner;
        this.currentId = 1;
    }

    public int addTask(String content) {
        tasks.put(currentId, content);
        return currentId++;
    }

    public boolean deleteTask(int id) {
        return tasks.remove(id) != null;
    }

    public String getTask(int id) {
        return tasks.get(id);
    }

    public int getIntInput(String prompt, String errorMessage) {
        while (true) {
            System.out.println(prompt);
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(errorMessage);
                // 사용자의 재시도 여부
                System.out.println("다시 시도하시겠습니까? (y/n)");
                String retry = scanner.nextLine().trim().toLowerCase();
                if (!retry.equals("y")) {
                    System.out.println("입력을 취소하셨습니다.");
                    return -1; // 사용자가 재시도를 원하지 않을 경우: -1
                }
            }
        }
    }

}
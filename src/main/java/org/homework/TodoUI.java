package org.homework;

import java.util.List;
import java.util.Scanner;

public class TodoUI {
    private final TodoManager todoManager = new TodoManager();
    private final Scanner scanner = new Scanner(System.in);

    public void app() {
        try {
            while (true) {
                Options option = getUserOption();
                switch (option) {
                    case ADD:
                        addTaskUI();
                        break;
                    case DEL:
                        deleteTaskUI();
                        break;
                    case FIND:
                        findTaskUI();
                        break;
                    case DOIT:
                        completeTaskUI();
                        break;
                    case LIST:
                        listTaskUI();
                        break;
                    case SEEK:
                        seekTaskUI();
                        break;
                    case EXIT:
                        System.out.println("프로그램을 종료합니다.");
                        scanner.close();
                        return;
                    default:
                        System.out.println("잘못된 입력입니다.");
                }
            }
        } catch (RuntimeException e) {
            System.out.println("프로그램을 종료합니다.");
        }
    }

    private Options getUserOption() {
        while (true) {
            try {
                System.out.println("옵션을 선택하세요 (ADD, DEL, FIND, DOIT, LIST, SEEK, EXIT):");
                return Options.valueOf(scanner.nextLine().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("잘못된 입력입니다. 다시 시도해 주세요.");
            }
        }
    }

    private void addTaskUI() {
        System.out.println("추가할 할 일을 입력하세요:");
        String content = scanner.nextLine();
        int id = todoManager.addTask(content);
        System.out.println("할 일이 추가되었습니다. ID: " + id);
    }

    private void completeTaskUI() { // doit
        int id = getIntInput("완료할 할 일의 ID를 입력하세요:", "잘못된 입력입니다.");
        TodoItem todoItem = todoManager.findTask(id);

        if (todoManager.checkTask(id)) {
            todoManager.completeTask(id);
            TodoItem content = todoManager.findTask(id);
            System.out.println(content);
        } else {
            System.out.println("해당 ID의 할 일이 없습니다.");
        }
    }

    private void findTaskUI() {
        int id = getIntInput("조회할 할 일의 ID를 입력하세요:", "잘못된 입력입니다.");

        TodoItem content = todoManager.findTask(id);
        System.out.println(content);
    }

    private void listTaskUI() {
        System.out.println("할 일 목록을 정렬 방법을 선택하세요:");
        System.out.println("1. 최근에 추가한 순서대로 출력");
        System.out.println("2. 오래된 순서대로 출력");
        int sortOption = getIntInput("옵션을 선택하세요 (1 또는 2):", "잘못된 입력입니다.");

        List<TodoItem> tasks = todoManager.getSortedTasks(sortOption);
        if (tasks.isEmpty()) {
            System.out.println("할 일 목록이 비어 있습니다.");
        } else {
            System.out.println("===== 정렬된 할 일 목록 =====");
            for (TodoItem task : tasks) {
                String status = task.isCompleted() ? "[완료]" : "[미완료]";
                System.out.println("ID: " + task.getId() + ", 내용: " + task.getContent() + " " + status);
            }
            System.out.println("============================");
        }
    }

    private void seekTaskUI() {
        System.out.print("검색할 키워드를 입력하세요: ");
        String keyword = scanner.nextLine();
        List<TodoItem> tasks = todoManager.seekTask(keyword);
        if (tasks.isEmpty()) {
            System.out.println("키워드를 포함하는 할 일이 없습니다.");
        } else {
            System.out.println("===== 검색 결과 =====");
            for (TodoItem task : tasks) {
                System.out.println(task);
            }
            System.out.println("====================");
        }
    }

    private void deleteTaskUI() {
        int id = getIntInput("삭제할 할 일의 ID를 입력하세요:", "잘못된 입력입니다.");
        if (todoManager.deleteTask(id)) {
            System.out.println("할 일이 삭제되었습니다. ID: " + id);
        } else {
            System.out.println("해당 ID의 할 일이 없습니다.");
        }
    }

    public int getIntInput(String prompt, String errorMessage) {
        while (true) {
            System.out.println(prompt);
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(errorMessage);
                System.out.println("다시 시도하시겠습니까? (y/n)");
                String retry = scanner.nextLine().trim().toLowerCase();
                if (!retry.equals("y")) {
                    System.out.println("입력을 취소하셨습니다.");
                    throw new RuntimeException();
                }
            }
        }
    }
}

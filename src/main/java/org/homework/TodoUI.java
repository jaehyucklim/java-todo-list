package org.homework;

import java.util.Scanner;

public class TodoUI {
    TodoManager todoManager = new TodoManager(); // 전역 cover
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
                System.out.println("옵션을 선택하세요 (ADD, DEL, FIND, EXIT):");
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

    private void deleteTaskUI() {
        int id = getIntInput("삭제할 할 일의 ID를 입력하세요:", "잘못된 입력입니다.");
        if (id != -1) {
            if (todoManager.deleteTask(id)) {
                System.out.println("할 일이 삭제되었습니다. ID: " + id);
            } else {
                System.out.println("해당 ID의 할 일이 없습니다.");
            }
        }
    }

    private void findTaskUI() {
            int id = getIntInput("조회할 할 일의 ID를 입력하세요:", "잘못된 입력입니다.");
            String content = todoManager.findTask(id);
            System.out.println("할 일 ID: " + id + ", 내용: " + content);
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

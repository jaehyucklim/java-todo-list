package org.homework;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TodoList todoList = new TodoList(scanner);

        while (true) {
            int option = todoList.getIntInput("옵션을 선택하세요: 1. 추가, 2. 삭제, 3. 조회, 4. 종료", "잘못된 입력입니다.");

            switch (option) {
                case 1:
                    System.out.println("추가할 할 일을 입력하세요:");
                    String content = scanner.nextLine();
                    int id = todoList.addTask(content);
                    System.out.println("할 일이 추가되었습니다. ID: " + id);
                    break;
                case 2:
                    int deleteId = todoList.getIntInput("삭제할 할 일의 ID를 입력하세요:", "잘못된 입력입니다.");
                    if (deleteId == -1) {
                        break;
                    }
                    if (todoList.deleteTask(deleteId)) {
                        System.out.println("할 일이 삭제되었습니다. ID: " + deleteId);
                    } else {
                        System.out.println("해당 ID의 할 일이 없습니다.");
                    }
                    break;
                case 3:
                    int searchId = todoList.getIntInput("조회할 할 일의 ID를 입력하세요:", "잘못된 입력입니다. 다시 입력해주세요.");
                    if (searchId == -1) {
                        break;
                    }
                    String result = todoList.getTask(searchId);
                    if (result != null) {
                        System.out.println("할 일 ID: " + searchId + " 내용: " + result);
                    } else {
                        System.out.println("해당 ID의 할 일이 없습니다.");
                    }
                    break;
                case 4:
                    System.out.println("프로그램을 종료합니다.");
                    scanner.close();
                    return;
                default:
                    System.out.println("잘못된 입력입니다.");
                    break;
            }
        }
    }
}

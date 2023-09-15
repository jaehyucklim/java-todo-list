package org.homework;

public class TodoItem {
    private int id;
    private String content;
    private boolean completed;

    public TodoItem(int id, String content) {
        this.id = id;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", 내용: " + content + "[" + (completed ? "완료" : "미완료") + "]";
    }
}

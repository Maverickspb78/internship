package org.example.todo.entities;

public enum Permission {

    USER_ALL("user:all"),
    TASK_ALL("task:all");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}

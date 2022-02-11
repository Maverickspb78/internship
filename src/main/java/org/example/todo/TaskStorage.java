package org.example.todo;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class TaskStorage {

    private int countId = 0;
    private final Map<Integer,Task> taskMap = new HashMap<>();


}

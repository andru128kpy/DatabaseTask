package org.example;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Task {
    private String description;
    private String priority;
}

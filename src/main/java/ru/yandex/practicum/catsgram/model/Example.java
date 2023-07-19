package ru.yandex.practicum.catsgram.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@AllArgsConstructor
//@Component
@Data
public class Example {
    String text;

    public Example(String text) {
        this.text = text;
    }
}

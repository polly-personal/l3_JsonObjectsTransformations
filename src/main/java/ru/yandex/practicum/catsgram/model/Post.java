package ru.yandex.practicum.catsgram.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDate;

//todo срабатывает @AllArgsConstructor при создании поста
@AllArgsConstructor
@Data
public class Post {
    private Integer id;
    private final String author; // автор
    private final LocalDate creationDate; // дата создания
//    private final Instant creationDate = Instant.now(); // дата создания
    private String description; // описание
    private String photoUrl; // url-адрес фотографии

/*
    public Post(String author, String description, String photoUrl) {
        this.author = author;
        this.description = description;
        this.photoUrl = photoUrl;
    }
*/
}
package ru.yandex.practicum.catsgram.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
//import ru.yandex.practicum.catsgram.Config.DateTimeConfig;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.service.PostService;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    /* ВЫДАЧА ВСЕХ */
/*
    @GetMapping("/posts")
    public Collection<Post> findAll() {
        return postService.findAll();
    }
*/

    /* СОЗДАНИЕ */
    @PostMapping("/posts/post")
    public Post create(@RequestBody Post post) {
        return postService.create(post);
    }

    /*
    ВЫДАЧА
    / ВСЕХ: /posts
    / НЕСКОЛЬКО (ПАРАМЕТР size) В ОТСОРТИРОВАННОМ ВИДЕ (ПАРАМЕТР sort): /posts?sort=desc&size=128
    / НЕСКОЛЬКО на одной странице из нескольких (ПАРАМЕТР page и size) В ОТСОРТИРОВАННОМ ВИДЕ (ПАРАМЕТР sort) //
    /posts?sort=asc&size=5&page=2
    */
    @GetMapping("/posts")
    public List<Post> findAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "sort", defaultValue = "asc") String sort) {

        Integer from = page * size;
        if (!(sort.equals("asc") || sort.equals("desc"))) {
            throw new IllegalArgumentException();
        }
        if (page < 0 || size <= 0) {
            throw new IllegalArgumentException();
        }
        return postService.findAll(size, from, sort);
    }

}
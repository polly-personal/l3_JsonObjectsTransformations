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
    ВЫДАЧА ВСЕХ // /posts
    / НЕСКОЛЬКО (ПАРАМЕТР size) В ОТСОРТИРОВАННОМ ВИДЕ (ПАРАМЕТР sort) // /posts?sort=desc&size=128
    / ОДНОГО (ПАРАМЕТР page) ИЗ НЕСКОЛЬКИХ (ПАРАМЕТР size) В ОТСОРТИРОВАННОМ ВИДЕ (ПАРАМЕТР sort) // /posts?sort=asc&size=5&page=2
    */
/*
    @GetMapping("/posts")
    public List<Post> findAllOrSortListByParametersOrOneFromSortListByParameters(
            @RequestParam(defaultValue = "testSort") String sort,
//            @RequestParam Optional<Integer> size
            @RequestParam(defaultValue = "testSize") String size
//            @RequestParam(defaultValue = "testPage") String page
    ) {
        int sizeInt = Integer.parseInt(size);
        if (sizeInt <= 0) {
            System.out.println("size <= 0");
            return null;
        }

//        int pageInt = Integer.parseInt(page);
        return postService.findAll(sort, sizeInt);
//        return null;

        */
/*
        return sort + " / " + size + " / " + page;

        ?sort=desc&size=1 -- desc / 1 / testPage
        ?sort=asc&size=1&page=1 -- asc / 1 / 1

        ?size=1&page=1 -- testSort / 1 / 1
        ?page=1&size=1 -- testSort / 1 / 1
        *//*

    }
*/
    @GetMapping("/posts")
    public List<Post> findAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
//            @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
//            @RequestParam(value = "size", defaultValue = "10", required = false) Integer size,
            @RequestParam(value = "sort", defaultValue = "asc") String sort) {
//            @RequestParam(value = "sort", defaultValue = "asc", required = false) String sort) {

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
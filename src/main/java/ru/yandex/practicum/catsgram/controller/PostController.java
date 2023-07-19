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

/*
    private final DateTimeConfig dateTimeConfig = new DateTimeConfig();
*/

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    //TODO НАДО подключить к контроллеру глобал конфиг времени (другие опции) https://www.baeldung.com/spring-date-parameters#convert-date-parameters-at-the-application-level:~:text=4.%20Convert%20Date%20Parameters%20at%20the%20Application%20Level
/*
    @Autowired
    public PostController(PostService postService, DateTimeConfig dateTimeConfig) {
        this.postService = postService;
        this.dateTimeConfig = dateTimeConfig;
    }
*/



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

//todo ПЕРЕМЕННЫЕ

    /* ВЫДАЧА ОДНОГО по postId (ЧЕРЕЗ ПЕРЕМЕННУЮ ПУТИ) */
/*
    @GetMapping("/posts/post/{id}")
    public Optional<Post> findByIdVariable(@PathVariable(name = "id") int postId) {
//    public Post findByIdVariable(@PathVariable(value = "postId") int postId) {
        return postService.findPostByOptionalId(postId);
//        return postService.findPostByIntId(postId);
    }
*/

    /* ВЫДАЧА ОДНОГО ПО id и author (ЧЕРЕЗ ПЕРЕМЕННЫЕ ПУТИ)*/
/*
    @GetMapping("/posts/{id}/{author}")
    public Post findPostByIdAndAuthorVariables(@PathVariable int id, @PathVariable String author) {
        return postService.findPostByIdAndAuthor(id, author);
    }
*/

    /* ВЫДАЧА ВСЕХ / ОДНОГО ПО id (ЧЕРЕЗ ПЕРЕМЕННУЮ ПУТИ)*/
/*
    @GetMapping(value = {"/posts","/posts/post/{id}"})
    public String findAllOrOneByIdVariable(@PathVariable Optional<Integer> id) {
//    public String findAllOrOneByIdVariable(@PathVariable(required = false) Integer id) {
        System.out.println(id);
//        if (id != null) {
        if (id.isPresent()) {
            return postService.findPostByOptionalId(id.get()).toString();
        }
        return postService.findAll().toString();
    }
*/

//todo ПАРАМЕТРЫ
    /* ВЫДАЧА ВСЕХ ПО author ПАРАМЕТРУ (ЧЕРЕЗ ПАРАМЕТР)*/
/*
    @GetMapping("posts/post/search") // ожидается posts/post/search?author=email@yandex.com
    public List<Post> findPostsByAuthorParam(@RequestParam String author) {
        System.out.println("Ищем посты пользователя с именем " + author);
        return postService.findPostsByAuthor(author);
    }
*/

    /* ВЫДАЧА ВСЕХ ПО author ПАРАМЕТРУ (ЧЕРЕЗ name)*/
/*
    @GetMapping("posts/post/search") // ожидается posts/post/search?author=email@yandex.com
    public List<Post> findPostsByAuthorParam(@RequestParam(name = "author") String author1) {
        System.out.println("Ищем посты пользователя с именем " + author1);
        return postService.findPostsByAuthor(author1);
    }
*/

    /* ВЫДАЧА ПРЕДУПРЕЖДЕНИЯ / ОДНОГО ПО author ПАРАМЕТРУ (ЧЕРЕЗ required)*/
    // ожидается либо: posts/post/search, либо: posts/post/search?author=email@yandex.com
/*
    @GetMapping("posts/post/search")
    public List<Post> getWarningOrOneByAuthorParamWithRequired(@RequestParam(required = false) String author) {
        return postService.findPostsByAuthor(author);
    }
*/

    /* Java 8
       ВЫДАЧА ПРЕДУПРЕЖДЕНИЯ / ОДНОГО ПО author ПАРАМЕТРУ (ЧЕРЕЗ Optional)*/
    // ожидается либо: posts/post/search, либо: posts/post/search?author=email@yandex.com
/*
    @GetMapping("posts/post/search")
    public String getWarningOrOneByAuthorParamWithOptional(@RequestParam Optional<String> author) {
        return author.orElseGet(() -> "параметр author не предоставлен");
    }
*/

    /* ВЫДАЧА ПРЕДУПРЕЖДЕНИЯ / ОДНОГО ПО author ПАРАМЕТРУ (ЧЕРЕЗ defaultValue)*/
    // ожидается либо: posts/post/search, либо: posts/post/search?author=email@yandex.com
/*
    @GetMapping("posts/post/search")
    public String getWarningOrOneByAuthorParamWithDefaultValue(
            @RequestParam(defaultValue = "mailAuthor@default.ru") String author) {
        return "email author: " + author;
    }
*/

    /*
    ВЫДАЧА ЗНАЧЕНИЙ МАПЫ
       ПО УМОЛЧАНИЮ без ПАРАМЕТРОВ
                            /
       ЗАПОЛНЕННЫХ ЗНАЧЕНИЙ ПО любому ПАРАМЕТРУ

       (ЧЕРЕЗ Map)
     */
    // ожидается
    // либо: posts/post/search,
    // либо: posts/post/search?author=email@yandex.com
    // либо: posts/post/search?date=1945-01-12
    // либо: posts/post/search?author=email@yandex.com&date=1945-01-12
/*
    @GetMapping("posts/post/search")
    public String getMapValuesDefaultOrExistingWithMap(
            @RequestParam Map<String, String> map) {
        String bigString =
                "параметр map в виде ключ=значение: " + map.entrySet()
                        + "\n параметр map в виде map.get(author): " + map.get("author")
                        + "\n параметр map в виде map.get(date): " + map.get("date")
                        + "\n параметр map в виде map.toString(): " + map.toString()
                        + "\n параметр map в виде map.keySet(): " + map.keySet()
                        + "\n параметр map в виде map.values(): " + map.values()
                        + "\n параметр map в виде map.size(): " + map.size();
        return bigString;
    }
*/

    /*
    ВЫДАЧА ЗАПОЛНЕННЫХ ЗНАЧЕНИЙ СПИСКА ПО author ПАРАМЕТРУ (ЧЕРЕЗ List)
     */
    // ожидается
    // либо: posts/post/search,
    // либо: posts/post/search?author=email@yandex.com, 2email@yandex.com
    // либо: posts/post/search?author=email@yandex.com&author=2email@yandex.com
/*
    @GetMapping("posts/post/search")
    public String getExistingValuesFromList(
            @RequestParam List<String> author) {
        String bigString =
                "параметр author в виде списка \"author\": " + author
                        + "\n параметр author в виде author.get(0): " + author.get(0)
                        + "\n параметр author в виде author.get(2): " + author.get(1)
                        + "\n параметр author в виде author.size() ДО author.add(): " + author.size()
                        + "\n параметр author в виде author.add(\"some add\"): " + author.add("someEmail@some.com")
                        + "\n параметр author в виде author.size() ПОСЛЕ author.add(): " + author.size();
        return bigString;
    }
*/


    /* ВЫДАЧА ВСЕХ ПО author и date ПАРАМЕТРАМ (ЧЕРЕЗ pattern)*/
    // ожидается posts/post/search?author=email@yandex.com&date=1945-01-12
/*
    @GetMapping("posts/post/search")
    public List<Post> findAllByAuthorAndDateParametersWithPattern(
            @RequestParam String author,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date
    ) {
        System.out.println("Ищем посты пользователя с именем " + author + " и опубликованные " + date);
        return postService.findPostsByAuthorAndDate(author, date);
    }
*/

    /* ВЫДАЧА ВСЕХ ПО author и date ПАРАМЕТРАМ (ЧЕРЕЗ iso)*/
    // ожидается posts/post/search?author=email@yandex.com&date=1945-01-12
/*
    @GetMapping("posts/post/search")
    public List<Post> findAllByAuthorAndDateParametersWithIso(
            @RequestParam String author,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        System.out.println("Ищем посты пользователя с именем " + author + " и опубликованные " + date);
        return postService.findPostsByAuthorAndDate(author, date);
    }
*/

    /* ВЫДАЧА ВСЕХ ПО author и date ПАРАМЕТРАМ (ЧЕРЕЗ iso)*/
    // ожидается posts/post/search?author=email@yandex.com&date=1945-01-12
    //TODO НАДО подключить к контроллеру глобал конфиг времени млм подключить класс конфигурации https://www.baeldung.com/spring-date-parameters#convert-date-parameters-at-the-application-level:~:text=4.%20Convert%20Date%20Parameters%20at%20the%20Application%20Level
/*
    @GetMapping("posts/post/search")
    public List<Post> findAllByAuthorAndDateParametersWithIso(
            @RequestParam String author,
            @RequestParam @DateTimeFormat(iso = DateTimeConfig.class) LocalDate date
    ) {
        System.out.println("Ищем посты пользователя с именем " + author + " и опубликованные " + date);
        return postService.findPostsByAuthorAndDate(author, date);
    }
*/

    /* ВЫДАЧА ВСЕХ ПО author и date ПАРАМЕТРАМ (ЧЕРЕЗ application.properties date=yyyy-MM-dd)*/
    // ожидается posts/post/search?author=email@yandex.com&date=1945-01-12
/*
    @GetMapping("posts/post/search")
    public List<Post> findAllByAuthorAndDateParametersWithApplicationProperties(
            @RequestParam String author,
            @RequestParam LocalDate date
    ) {
        System.out.println("Ищем посты пользователя с именем " + author + " и опубликованные " + date);
        return postService.findPostsByAuthorAndDate(author, date);
    }
*/

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
package ru.yandex.practicum.catsgram.service;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.catsgram.exception.PostNotFoundException;
import ru.yandex.practicum.catsgram.exception.UserNotFoundException;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.model.User;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Data
@Service
public class PostService {
    private int globalId = 0;
    private final List<Post> posts = new ArrayList<>();
    private final UserService userService;

    @Autowired
    public PostService(UserService userService) {
        this.userService = userService;
    }

    public List<Post> findAllFromYP(Integer size, Integer from, String sort) {
        return posts.stream().sorted((p0, p1) -> {
            int comp = p0.getCreationDate().compareTo(p1.getCreationDate()); //прямой порядок сортировки
            if (sort.equals("desc")) {
                comp = -1 * comp; //обратный порядок сортировки
            }
            return comp;
        }).skip(from).limit(size).collect(Collectors.toList());
    }

    public List<Post> findAll(Integer size, Integer from, String sort) {

        Comparator<Post> comparatorPosts = new Comparator<Post>() {
            @Override
            public int compare(Post p0, Post p1) {
/*
                if (p0.getCreationDate().isBefore(p1.getCreationDate())) {
                    return -1;
                }
                return 1;
*/
                int comp = p0.getCreationDate().compareTo(p1.getCreationDate()); //прямой порядок сортировки
                if (sort.equals("desc")) {
                    comp = -1 * comp; //обратный порядок сортировки
                }
                return comp;

            }
        };

/*
        if (sort.equals("asc")) { // от новых к старым = по убыванию (asc)
            posts.sort(comparatorPosts);
        } else { // от старых к новым = по возрастанию (desc)
            posts.sort(comparatorPosts.reversed());
        }
*/
        posts.sort(comparatorPosts);
        List<Post> sortedPosts = new ArrayList<>(size);
        for (int i = from; i < from + size; i++) { // size=4&page=1 from=4
            sortedPosts.add(posts.get(i));
        }

        log.info("🟢 список постов выдан");
        return sortedPosts;
    }

    public Post create(Post post) {
        User postAuthor = userService.findUserByEmail(post.getAuthor());
        if (postAuthor == null) {
            log.warn("🔴 пост НЕ создан");
            throw new UserNotFoundException(String.format("пользователь s% для поста не найден" + post.getAuthor()));
        }

        post.setId(getNextId());
        log.info("🟢 пост создан");
        posts.add(post);
        return post;
    }

    //----------------------------------------------------------------
    public Post findPostByIntId(int id) {
        for (Post post : posts) {
            if (post.getId() == id) {
                log.info("🟢 пост по id");
                return post;
            }
        }

        log.warn(String.format("🔴 id: s% не существует" + id));
        throw new PostNotFoundException(String.format("id: s% не существует" + id));
    }

    public Optional<Post> findPostByOptionalId(int id) {
        Optional<Post> postFromStream = posts.stream()
                .filter(post -> post.getId() == id)
                .findFirst();
        System.out.println("Optional<Post>: " + postFromStream);

        if (postFromStream.isEmpty()) {
            log.warn(String.format("🔴 id: s% не существует" + id));
            throw new PostNotFoundException(String.format("id: s% не существует" + id));
        } else {
            log.info("🟢 пост по id выдан");
        }

        return postFromStream;
    }
    //-----------------------------------------------------------------

    public Post findPostByIdAndAuthor(int id, String author) {
        for (Post post : posts) {
            if (post.getId() == id && post.getAuthor().equals(author)) {
                log.info("🟢 пост по id и author выдан");
                return post;
            }
        }

        log.warn("🔴 id " + id + " или author " + author + " не существует и не выдан");
        throw new PostNotFoundException("id " + id + " или author " + author + " не существует и не выдан");
    }

    public List<Post> findPostsByAuthor(String author) { //TODO а через стрим?
        if (userService.getUsers().containsKey(author)) {
            List<Post> postsByAuthor = new ArrayList<>();

            for (Post post : posts) {
                if (post.getAuthor().equals(author)) {
                    postsByAuthor.add(post);
                }
            }
            log.info("🟢 список постов по author: " + author + " выдан");
            return postsByAuthor;
        }
        log.warn("🔴 автор: " + author + " не найден. список постов по author НЕ  выдан");
        return null;
    }

    public List<Post> findPostsByAuthorAndDate(String author, LocalDate date) {
        if (userService.getUsers().containsKey(author)) {
            List<Post> postsByAuthor = new ArrayList<>();

            for (Post post : posts) {
                if (post.getAuthor().equals(author) && post.getCreationDate().equals(date)) {
                    postsByAuthor.add(post);
                }
            }
            log.info("🟢 список постов по author: " + author + "и date: " + date + " выдан");
            return postsByAuthor;
        }
        log.warn("🔴 автор: " + author + "или дата: " + date + " не найден(a). список постов по author и date НЕ выдан");
        return null;
    }


    private int getNextId() {
        return ++globalId;
    }

}
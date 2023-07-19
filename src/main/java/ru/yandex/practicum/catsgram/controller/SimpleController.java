package ru.yandex.practicum.catsgram.controller;

import jdk.jfr.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.catsgram.model.Example;
import ru.yandex.practicum.catsgram.model.User;

@RestController
public class SimpleController {

// создаём логер
//private static final Logger log = LoggerFactory.getLogger(SimpleController.class);

//    Example example;
//
//    @Autowired
//    public SimpleController(Example example) {
//        this.example = example;
//    }

    @GetMapping("/home")
    public String homePage() {
        // логируем факт получения запроса
//        log.info("получен запрос");
//        ch.qos.logback.classic.Logger logLogback = (ch.qos.logback.classic.Logger) log;
//        logLogback.setLevel(Level.DEBUG);
//        log.debug("получен запрос GET /home");

        // возвращаем ответ в виде строки
        return "Котограм";
    }

    @PostMapping("/request")
    public ResponseEntity postController(@RequestBody User user) {
        // действия с user
        return ResponseEntity.ok(HttpStatus.OK);
    }

//TODO законспектировать норм работу атрибута produces

//    @GetMapping(value = "/content", produces = MediaType.APPLICATION_XML_VALUE)
//    @GetMapping(value = "/content", produces = "application/xml")
//    @GetMapping(value = "/content", produces = "application/json")

//    @PostMapping(value = "/content", produces = "application/json")
//    @PostMapping(value = "/content", produces = MediaType.APPLICATION_JSON_VALUE)
//    @PostMapping(value = "/content", produces = "application/xml")
//    @PostMapping(value = "/content", produces = MediaType.APPLICATION_XML_VALUE)

    //    @GetMapping(value = "/content", produces = MediaType.APPLICATION_XML_VALUE)

    @GetMapping(value = "/content", produces = "application/xml")
    public Example postResponseContent() {
        return new Example("some2");
    }

/*
    @PostMapping(value = "/content", produces = MediaType.APPLICATION_XML_VALUE)
    public User postResponseXmlContent(@RequestBody User user) {
        // действия с user
       user.setNickname("XML content");
       return user;
    }
*/

/*
    @GetMapping(value = "/content", produces = MediaType.APPLICATION_JSON_VALUE)
    public Example postResponseContent2() {
        return new Example("some2");
    }
*/

}

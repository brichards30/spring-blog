package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {
    @GetMapping(path = "/posts")
    @ResponseBody

    public String postsIndex(){
        return "Posts index page";
    }

    @RequestMapping(path = "/posts/{id}", method = RequestMethod.GET)
    @ResponseBody

    public String usersPost(
            @PathVariable int id
    ){
        return "View " + id + "'s posts";
    }

    @GetMapping("/posts/create")
    @ResponseBody

    public String createPostForm(){
        return "View form for creating posts";
    }

    @PostMapping("/posts/create")
    @ResponseBody

    public String createPost(){
        return "Create a new post";
    }
}

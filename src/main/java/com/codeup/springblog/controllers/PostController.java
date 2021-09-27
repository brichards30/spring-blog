package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.repos.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    private final PostRepository postDao;

    public PostController(PostRepository postDao) {
        this.postDao = postDao;
    }

    @GetMapping(path = "/posts")
    public String showAllPosts(Model model) {

        List<Post> postsToShow = postDao.findAll();
//        List<Post> allPosts = new ArrayList<>();
//
//        allPosts.add(new Post("Another Post!", "Another one"));
//        allPosts.add(new Post("Yet Another Post!", "Yet another one"));
        model.addAttribute("posts", postsToShow);
        return "post/index";
    }

    @GetMapping("/posts/{id}")

    public String singlePost(@PathVariable int id, Model model) {

        Post post = postDao.getPostById(id);
//        Post post = new Post("New Post", "New Body");
        model.addAttribute("postID", id);
        model.addAttribute("newPost", post);

        return "post/show";
    }

    @GetMapping("/posts/create")
    @ResponseBody

    public String createPostForm() {
        return "View form for creating posts";
    }

    @PostMapping("/posts/create")
    @ResponseBody

    public String createPost(
            @RequestParam(name = "title") String title,
            @RequestParam(name = "body") String body
    ) {

        Post postToSubmitToDB = new Post(title, body);

        postDao.save(postToSubmitToDB);
        return "redirect:/posts";
    }

    @GetMapping("/posts/edit/{id}")
    public String editPostForm(@PathVariable long id, Model model) {
        Post postToEdit = postDao.getPostById(id);
        model.addAttribute("post", postToEdit.getId());
        return "post/edit";
    }

    @PostMapping("/posts/edit/{id}")
    public String editPost(
            @PathVariable long id,
            @RequestParam(name = "title") String title,
            @RequestParam(name = "body") String body
    ) {
            Post editedPost = new Post(id, title, body);

            postDao.save(editedPost);
            return "redirect:/posts";
    }

    @PostMapping("/posts/delete/{id}")
    public String deletePost(@PathVariable long id) {
        Post postToDelete = postDao.getPostById(id);
        postDao.delete(postToDelete);
        return "redirect:/posts";
    }


}

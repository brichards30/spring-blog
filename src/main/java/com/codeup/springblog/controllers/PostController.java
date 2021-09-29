package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.User;
import com.codeup.springblog.repos.PostRepository;
import com.codeup.springblog.repos.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostController {

    private final PostRepository postDao;

    private UserRepository userDao;

    public PostController(PostRepository postDao, UserRepository userDao) {
        this.postDao = postDao;
        this.userDao = userDao;
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

    public String showSinglePost(@PathVariable int id, Model model) {

        Post postToShow = postDao.getPostById(id);

        model.addAttribute("postID", id);
        model.addAttribute("newPost", postToShow);

        return "post/show";
    }

    @GetMapping("/posts/create")

    public String createPostForm(Model model) {
        model.addAttribute("post", new Post());
        return "post/create";
    }

    @PostMapping("/posts/create")

    public String createPost(@ModelAttribute Post postToAdd) {

        postToAdd.setOwner(userDao.getById(1L));

        postDao.save(postToAdd);
        return "redirect:/posts";
    }

    @GetMapping("/posts/edit/{id}")
    public String editPostForm(@PathVariable long id, Model model) {
        Post postToEdit = postDao.getPostById(id);
        model.addAttribute("postToEdit", postToEdit);
        return "post/edit";
    }

    @PostMapping("/posts/edit/{id}")
    public String editPost(@PathVariable long id, @ModelAttribute Post updatedPost) {

        updatedPost.setId(id);
        updatedPost.setOwner(userDao.getById(1L));
        postDao.save(updatedPost);

        return "redirect:/posts";
    }

    @PostMapping("/posts/delete/{id}")
    public String deletePost(@PathVariable long id) {
        Post postToDelete = postDao.getPostById(id);
        postDao.delete(postToDelete);
        return "redirect:/posts";
    }


}

package com.swcguild.capstone.controller;

import com.swcguild.capstone.dao.CapstoneDao;
import com.swcguild.capstone.model.Comment;
import com.swcguild.capstone.model.Order;
import com.swcguild.capstone.model.Post;
import com.swcguild.capstone.model.Product;
import com.swcguild.capstone.model.Tag;
import com.swcguild.capstone.model.User;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class HomeController {

    private CapstoneDao dao;

    @Inject
    public HomeController(CapstoneDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value = {"/welcome"}, method = RequestMethod.GET)
    public String displayWelcomePage() {
        return "welcome";
    }

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String displayHomePage() {
        return "home";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String displayLoginPage() {
        return "login";
    }

    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String displayAboutPage() {
        return "about";
    }

    @RequestMapping(value = "/post", method = RequestMethod.GET)
    public String displayPostPage() {
        return "post";
    }

    @RequestMapping(value = "/shop", method = RequestMethod.GET)
    public String displayShopPage() {
        return "shop";
    }

    //////////////////////////////////////////////////////////////
    @RequestMapping(value = "/post/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Post getPost(@PathVariable("id") int id) {
        return dao.getPostById(id);
    }

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Post createPost(@RequestBody Post post) {
        post.setDate(getDateTime());
        dao.addPost(post);
        return post;
    }

    @RequestMapping(value = "/post/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable("id") int id) {
        dao.removePost(id);
    }

    @RequestMapping(value = "/post/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void putPost(@PathVariable("id") int id, @RequestBody Post post) {
        post.setPostId(id);
        dao.updatePost(post);
    }

    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    @ResponseBody
    public List<Post> getAllPosts() {
        return dao.getAllPosts();
    }
    
    @RequestMapping(value = "/postsByTag/{name}", method = RequestMethod.GET)
    @ResponseBody
    public List<Post> getPostsByTag(@PathVariable("name") String name) {
       
        return dao.getPostsByTag(dao.getTagByName(name));
    }
    

    //////////////////////////////////////////////////////////////
    @RequestMapping(value = "/comment/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Comment getComment(@PathVariable("id") int id) {
        return dao.getCommentById(id);
    }

    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Comment createComment(@RequestBody Comment comment) {
        comment.setDate(getDateTime());
        dao.addComment(comment);
        return comment;
    }

    @RequestMapping(value = "/comment/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable("id") int id) {
        dao.removeComment(id);
    }

    @RequestMapping(value = "/comment/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void putComment(@PathVariable("id") int id, @RequestBody Comment comment) {
        comment.setCommentId(id);
        dao.updateComment(comment);
    }

    @RequestMapping(value = "/comments/{postId}", method = RequestMethod.GET)
    @ResponseBody
    public List<Comment> getAllCommentsForPost(@PathVariable("postId") int postId) {
        return dao.getAllCommentsForPost(postId);
    }

    //////////////////////////////////////////////////////////////
    @RequestMapping(value = "/tag/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Tag getTag(@PathVariable("id") int id) {
        return dao.getTagById(id);
    }

    @RequestMapping(value = "/tag", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Tag createTag(@RequestBody Tag tag) {
        dao.addTag(tag);
        return tag;
    }

    @RequestMapping(value = "/tag/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTag(@PathVariable("id") int id) {
        dao.removeTag(id);
    }

//    @RequestMapping(value="/tag/{id}", method=RequestMethod.PUT)
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void putTag(@PathVariable("id") int id, @RequestBody Tag tag) {
//        tag.setTagId(id);
//        dao.updateTag(tag);
//    }
    @RequestMapping(value = "/tags", method = RequestMethod.GET)
    @ResponseBody
    public List<Tag> getAllTags() {
        return dao.getAllTags();
    }

    //////////////////////////////////////////////////////////////
    @RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Product getProduct(@PathVariable("id") int id) {
        return dao.getProductById(id);
    }

    @RequestMapping(value = "/product", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Product createProduct(@RequestBody Product product) {
        dao.addProduct(product);
        return product;
    }

    @RequestMapping(value = "/product/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable("id") int id) {
        dao.removeProduct(id);
    }

    @RequestMapping(value = "/product/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void putProduct(@PathVariable("id") int id, @RequestBody Product product) {
        product.setProductId(id);
        dao.updateProduct(product);
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    @ResponseBody
    public List<Product> getAllProducts() {
        return dao.getAllProducts();
    }

    //////////////////////////////////////////////////////////////
    @RequestMapping(value = "/order/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Order getOrder(@PathVariable("id") int id) {
        return dao.getOrderById(id);
    }

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Order createOrder(@RequestBody Order order) {
        dao.addOrder(order);
        return order;
    }

    @RequestMapping(value = "/order/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable("id") int id) {
        dao.removeOrder(id);
    }

//    @RequestMapping(value = "/order/{id}", method = RequestMethod.PUT)
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void putOrder(@PathVariable("id") int id, @RequestBody Order order) {
//        order.setOrderId(id);
//        dao.updateOrder(order);
//    }
    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    @ResponseBody
    public List<Order> getAllOrders() {
        return dao.getAllOrders();
    }

    //////////////////////////////////////////////////////////////
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    @ResponseBody
    public User getUser(@PathVariable("id") int id) {
        return dao.getUserById(id);
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public User createUser(@RequestBody User user) {
        dao.addUser(user);
        return user;
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable("id") int id) {
        dao.removeUser(id);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void putUser(@PathVariable("id") int id, @RequestBody User user) {
        user.setUserId(id);
        dao.updateUser(user);
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    @ResponseBody
    public List<User> getAllUsers() {
        return dao.getAllUsers();
    }

    //////////////////////////////////////////////////////////////
    private Date getDateTime() {
        return new Date();
    }

}

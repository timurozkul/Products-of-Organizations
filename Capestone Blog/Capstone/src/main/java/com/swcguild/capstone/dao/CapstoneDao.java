/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.capstone.dao;

import com.swcguild.capstone.model.Comment;
import com.swcguild.capstone.model.Order;
import com.swcguild.capstone.model.OrderDetail;
import com.swcguild.capstone.model.Post;
import com.swcguild.capstone.model.Product;
import com.swcguild.capstone.model.Tag;
import com.swcguild.capstone.model.User;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface CapstoneDao {
    
    public Post addPost(Post post);
    public void removePost(int postId);
    public void updatePost(Post post);
    public Post getPostById(int postId);
    public List<Post> getPostsByTag(int tagId);
    public List<Post> getAllPosts();
       
    public Comment addComment(Comment comment);
    public void removeComment(int commentId);
    public void updateComment(Comment comment);
    public Comment getCommentById(int commentId);
    public List<Comment> getAllCommentsForPost(int postId);
    
    public Tag addTag(Tag tag);
    public Tag addTagToPost(Tag tag, int postId);
    public void removeTag(int tagId);
    public Tag getTagById(int tagId);
    public int getTagByName(String name);
    public List<Tag> getAllTagsForPost(int postId);
    public List<Tag> getAllTags();
        
    public Product addProduct(Product product);
    public void removeProduct(int productId);
    public void updateProduct(Product product);
    public Product getProductById(int productId);
    public List<Product> getAllProducts();
    
    public Order addOrder(Order order);
    public void removeOrder(int orderId);
//    public void updateOrder(Order order);
    public Order getOrderById(int orderId);
    public List<Order> getAllOrders();
    
    public List<OrderDetail> getOrderDetailsByOrderId(int orderId);
    
    public User addUser(User user);
    public void removeUser(int userId);
    public void updateUser(User user);
    public User getUserById(int userId);
    public List<User> getAllUsers();
    
}

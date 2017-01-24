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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author apprentice
 */
public class CapstoneDaoImpl implements CapstoneDao {

    ////////////////////////////////////////////////////// POST ////////////////////////////////////////////////////////////////
    private static final String SQL_POST_ADD = "INSERT INTO posts (author, content, post_date) VALUES(?,?,?)";
    private static final String SQL_POST_REMOVE = "DELETE FROM posts WHERE post_id = ?";
    private static final String SQL_POST_UPDATE = "UPDATE posts SET author = ?, content = ?, post_date = ? WHERE post_id = ?";
    private static final String SQL_POST_SELECT_ID = "SELECT * FROM posts WHERE post_id = ?";
    private static final String SQL_POST_SELECT_TAG = "SELECT * FROM posts_tags JOIN posts ON posts_tags.post_id = posts.post_id WHERE tag_id = ?";
    private static final String SQL_POST_SELECT_ALL = "SELECT * FROM posts";
    //////////////////////////////////////////////////////// COMMENT ////////////////////////////////////////////////////////////////
    private static final String SQL_COMMENT_ADD = "INSERT INTO comments (post_id, author, content, comment_date) VALUES(?,?,?,?)";
    private static final String SQL_COMMENT_REMOVE = "DELETE FROM comments WHERE comment_id = ?";
    private static final String SQL_COMMENT_UPDATE = "UPDATE comments SET content = ? WHERE comment_id = ?";
    private static final String SQL_COMMENT_SELECT_ID = "SELECT * FROM comments WHERE comment_id = ?";
    private static final String SQL_COMMENT_SELECT_BY_POST_ID = "SELECT * FROM comments WHERE post_id = ?";
    private static final String SQL_COMMENT_SELECT_ALL = "SELECT * FROM comments";
    //////////////////////////////////////////////////////// TAG /////////////////////////////////////////////////////////////////////
    private static final String SQL_TAG_ADD = "INSERT INTO tags (name) VALUES(?)";
    private static final String SQL_TAG_ADD_TO_POST = "INSERT INTO posts_tags (post_id, tag_id) VALUES(?,?)";
    private static final String SQL_TAG_REMOVE = "DELETE FROM tags WHERE tag_id = ?";
    private static final String SQL_TAG_SELECT_ID = "SELECT * FROM tags WHERE tag_id = ?";
    private static final String SQL_TAG_SELECT_NAME = "SELECT tag_id FROM tags WHERE name = ?";///
    private static final String SQL_TAG_SELECT_ALL = "SELECT * FROM tags";
    private static final String SQL_TAG_SELECT_BY_POST_ID = "SELECT * FROM posts_tags JOIN tags ON posts_tags.tag_id = tags.tag_id WHERE post_id = ?";
    ////////////////////////////////////////////////////// PRODUCT //////////////////////////////////////////////////////////////////
    private static final String SQL_PRODUCT_ADD = "INSERT INTO products (name, price) VALUES (?,?)";
    private static final String SQL_PRODUCT_REMOVE = "DELETE FROM products WHERE product_id = ?";
    private static final String SQL_PRODUCT_UPDATE = "UPDATE products SET name = ?, price = ? WHERE product_id = ?";
    private static final String SQL_PRODUCT_SELECT_ID = "SELECT * FROM products WHERE product_id = ?";
    private static final String SQL_PRODUCT_SELECT_ALL = "SELECT * FROM products";
    /////////////////////////////////////////////////////// ORDER ////////////////////////////////////////////////////////////////////
    private static final String SQL_ORDER_ADD = "INSERT INTO orders (name, total) VALUES (?,?)";
    private static final String SQL_ORDER_DETAILS_ADD = "INSERT INTO order_details (order_id, product_id, quantity) VALUES (?,?,?)";
    private static final String SQL_ORDER_REMOVE = "DELETE FROM orders WHERE order_id = ?";
    private static final String SQL_ORDER_DETAILS_GET_BY_ORDER_ID = "SELECT * FROM order_details WHERE order_id = ?";
    private static final String SQL_ORDER_DETAILS_REMOVE = "DELETE FROM order_details WHERE order_id = ?";
    private static final String SQL_ORDER_UPDATE = "UPDATE orders SET name = ?, total = ?, WHERE order_id = ?";
//    private static final String SQL_ORDER_DETAILS_UPDATE = "UPDATE order_details SET product_id = ?, quantity = ? WHERE order_id = ?";
    private static final String SQL_ORDER_SELECT_ID = "SELECT * FROM orders WHERE order_id = ?";
    private static final String SQL_ORDER_SELECT_ALL = "SELECT * FROM orders";
    /////////////////////////////////////////////////////// USER ////////////////////////////////////////////////////////////////////
    private static final String SQL_USER_ADD = "INSERT INTO users (username, password, access_type) VALUES (?,?,?)";
    private static final String SQL_USER_REMOVE = "DELETE FROM users WHERE user_id = ?";
    private static final String SQL_USER_UPDATE = "UPDATE users SET username = ?, password = ?, access_type = ? WHERE user_id = ?";
    private static final String SQL_USER_SELECT_ID = "SELECT * FROM users WHERE user_id = ?";
    private static final String SQL_USER_SELECT_ALL = "SELECT * FROM users";

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//                                                            POST
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public Post addPost(Post post) {

        jdbcTemplate.update(SQL_POST_ADD,
                post.getAuthor(),
                post.getContent(),
                post.getDate());

        Integer id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        post.setPostId(id);

        for (Tag tag : post.getTags()) {
            addTag(tag);
            addTagToPost(tag, post.getPostId());
        }

        return post;
    }

    @Override
    public void removePost(int postId) {
        jdbcTemplate.update(SQL_POST_REMOVE, postId);
    }

    @Override
    public void updatePost(Post post) {
        jdbcTemplate.update(SQL_POST_UPDATE,
                post.getAuthor(),
                post.getContent(),
                post.getDate(),
                post.getPostId());
    }

    @Override
    public Post getPostById(int postId) {
        try {
            Post post = jdbcTemplate.queryForObject(SQL_POST_SELECT_ID, new PostMapper(), postId);
            List<Comment> comments = getAllCommentsForPost(post.getPostId());
            List<Tag> tags = getAllTagsForPost(post.getPostId());

            post.setComments(comments);
            post.setTags(tags);
            return post;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Post> getPostsByTag(int tagId) {
        List<Post> incompletePosts = jdbcTemplate.query(SQL_POST_SELECT_TAG, new PostMapper(), tagId);
        List<Post> finishedPosts = new ArrayList<>();
        
        for (Post p : incompletePosts) {
            List<Comment> comments = getAllCommentsForPost(p.getPostId());
            List<Tag> tags = getAllTagsForPost(p.getPostId());
            p.setComments(comments);
            p.setTags(tags);
            finishedPosts.add(p);
        }
        return finishedPosts;
    }

    @Override
    public List<Post> getAllPosts() {
        List<Post> incompletePosts = jdbcTemplate.query(SQL_POST_SELECT_ALL, new PostMapper());
        List<Post> finishedPosts = new ArrayList<>();
        for (Post p : incompletePosts) {
            List<Comment> comments = getAllCommentsForPost(p.getPostId());
            List<Tag> tags = getAllTagsForPost(p.getPostId());
            p.setComments(comments);
            p.setTags(tags);
            finishedPosts.add(p);
        }
        return finishedPosts;
    }

    private static final class PostMapper implements RowMapper<Post> {

        @Override
        public Post mapRow(ResultSet rs, int i) throws SQLException {
            Post post = new Post();

            post.setPostId(rs.getInt("post_id"));
            post.setAuthor(rs.getString("author"));
            post.setContent(rs.getString("content"));
            post.setDate(rs.getDate("post_date"));

            return post;
        }
    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//                                                            COMMENT
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public Comment addComment(Comment comment) {
        jdbcTemplate.update(SQL_COMMENT_ADD,
                comment.getPostId(),
                comment.getAuthor(),
                comment.getContent(),
                comment.getDate());

        Integer id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        comment.setCommentId(id);
        return comment;
    }

    @Override
    public void removeComment(int commentId) {
        jdbcTemplate.update(SQL_COMMENT_REMOVE, commentId);
    }

    @Override
    public void updateComment(Comment comment) {
        jdbcTemplate.update(SQL_COMMENT_UPDATE,
                comment.getContent(),
                comment.getCommentId());
    }

    @Override
    public Comment getCommentById(int commentId) {
        try {
            return jdbcTemplate.queryForObject(SQL_COMMENT_SELECT_ID, new CommentMapper(), commentId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Comment> getAllCommentsForPost(int postId) {

        List<Comment> comments = jdbcTemplate.query(SQL_COMMENT_SELECT_BY_POST_ID, new CommentMapper(), postId);
        return comments;
    }

    private static final class CommentMapper implements RowMapper<Comment> {

        @Override
        public Comment mapRow(ResultSet rs, int i) throws SQLException {
            Comment comment = new Comment();

            comment.setCommentId(rs.getInt("comment_id"));
            comment.setPostId(rs.getInt("post_id"));
            comment.setAuthor(rs.getString("author"));
            comment.setContent(rs.getString("content"));
            comment.setDate(rs.getDate("comment_date"));

            return comment;
        }

    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//                                                            TAG
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public Tag addTag(Tag tag) {

        if (!tagExists(tag.getName())) {

            jdbcTemplate.update(SQL_TAG_ADD,
                    tag.getName());

            Integer id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
            tag.setTagId(id);

        }

        return tag;
    }

    public boolean tagExists(String tagName) {

        List<Tag> tags = getAllTags();

        List<String> tagNames = new ArrayList<>();

        tags.stream().forEach((t) -> {
            tagNames.add(t.getName());
        });

        return tagNames.contains(tagName);

    }

    @Override
    public Tag addTagToPost(Tag tag, int postId) {
        jdbcTemplate.update(SQL_TAG_ADD_TO_POST, postId, tag.getTagId());
        return tag;
    }

    @Override
    public void removeTag(int tagId) {
        jdbcTemplate.update(SQL_TAG_REMOVE, tagId);
    }

    @Override
    public Tag getTagById(int tagId) {
        try {
            return jdbcTemplate.queryForObject(SQL_TAG_SELECT_ID, new TagMapper(), tagId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
      @Override
    public int getTagByName(String name) {
        
         return jdbcTemplate.queryForObject(SQL_TAG_SELECT_NAME, Integer.class, name);
      
    }

    @Override
    public List<Tag> getAllTags() {
        List<Tag> tags = jdbcTemplate.query(SQL_TAG_SELECT_ALL, new TagMapper());
        return tags;
    }

    @Override
    public List<Tag> getAllTagsForPost(int postId) {
        List<Tag> tags = jdbcTemplate.query(SQL_TAG_SELECT_BY_POST_ID, new TagMapper(), postId);
        return tags;
    }

    private static final class TagMapper implements RowMapper<Tag> {

        @Override
        public Tag mapRow(ResultSet rs, int i) throws SQLException {
            Tag tag = new Tag();

            tag.setTagId(rs.getInt("tag_id"));
            tag.setName(rs.getString("name"));

            return tag;
        }

    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//                                                            PRODUCT
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public Product addProduct(Product product) {
        jdbcTemplate.update(SQL_PRODUCT_ADD,
                product.getName(),
                product.getPrice());

        Integer id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        product.setProductId(id);
        return product;
    }

    @Override
    public void removeProduct(int productId) {
        jdbcTemplate.update(SQL_PRODUCT_REMOVE, productId);
    }

    @Override
    public void updateProduct(Product product) {
        jdbcTemplate.update(SQL_PRODUCT_UPDATE,
                product.getName(),
                product.getPrice(),
                product.getProductId());

    }

    @Override
    public Product getProductById(int productId) {
        try {
            return jdbcTemplate.queryForObject(SQL_PRODUCT_SELECT_ID, new ProductMapper(), productId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = jdbcTemplate.query(SQL_PRODUCT_SELECT_ALL, new ProductMapper());
        return products;
    }

    private static final class ProductMapper implements RowMapper<Product> {

        @Override
        public Product mapRow(ResultSet rs, int i) throws SQLException {
            Product product = new Product();

            product.setName(rs.getString("name"));
            product.setPrice(rs.getDouble("price"));
            product.setProductId(rs.getInt("product_id"));

            return product;
        }

    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//                                                            ORDERS
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public Order addOrder(Order order) {
        jdbcTemplate.update(SQL_ORDER_ADD,
                order.getName(),
                order.getTotal());

        Integer id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        order.setOrderId(id);

        List<OrderDetail> orderDetails = order.getOrderDetails();

        for (OrderDetail o : orderDetails) {

            jdbcTemplate.update(SQL_ORDER_DETAILS_ADD,
                    order.getOrderId(),
                    o.getProductId(),
                    o.getQuantity());

        }

        return order;
    }
//////////////////////////////////////////////
    @Override
    public void removeOrder(int orderId) {
        jdbcTemplate.update(SQL_ORDER_REMOVE, orderId);
        jdbcTemplate.update(SQL_ORDER_DETAILS_REMOVE, orderId);

    }

//    @Override
//    public void updateOrder(Order order) {
//        jdbcTemplate.update(SQL_ORDER_UPDATE,
//                order.getName(),
//                order.getTotal(),
//                order.getPickupDate(),
//                order.getOrderId());
//        
//        
//        
//        
//        jdbcTemplate.update(SQL_ORDER_DETAILS_UPDATE, );
//        
//        
//        
//    }
    @Override
    public Order getOrderById(int orderId) {
        try {
            Order order = jdbcTemplate.queryForObject(SQL_ORDER_SELECT_ID, new OrderMapper(), orderId);
            order.setOrderDetails(getOrderDetailsByOrderId(orderId));
            return order;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Order> getAllOrders() {
        List<Order> orders = jdbcTemplate.query(SQL_ORDER_SELECT_ALL, new OrderMapper());

        for (Order o : orders) {
            o.setOrderDetails(getOrderDetailsByOrderId(o.getOrderId()));
        }

        return orders;
    }

    private static final class OrderMapper implements RowMapper<Order> {

        @Override
        public Order mapRow(ResultSet rs, int i) throws SQLException {
            Order order = new Order();

            order.setOrderId(rs.getInt("order_id"));
            order.setName(rs.getString("name"));
            order.setTotal(rs.getDouble("total"));

            return order;
        }

    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//                                                       ORDER DETAILS
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
    @Override
    public List<OrderDetail> getOrderDetailsByOrderId(int orderId) {
        return jdbcTemplate.query(SQL_ORDER_DETAILS_GET_BY_ORDER_ID, new OrderDetailMapper(), orderId);

    }

    private static final class OrderDetailMapper implements RowMapper<OrderDetail> {

        @Override
        public OrderDetail mapRow(ResultSet rs, int i) throws SQLException {

            OrderDetail orderDetail = new OrderDetail();

            orderDetail.setOrderId(rs.getInt("order_id"));
            orderDetail.setProductId(rs.getInt("product_id"));
            orderDetail.setQuantity(rs.getInt("quantity"));

            return orderDetail;

        }

    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//                                                            USER
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public User addUser(User user) {
        jdbcTemplate.update(SQL_USER_ADD,
                user.getUsername(),
                user.getPassword(),
                user.getAccessType());

        Integer id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        user.setUserId(id);
        return user;
    }

    @Override
    public void removeUser(int userId) {
        jdbcTemplate.update(SQL_USER_REMOVE, userId);
    }

    @Override
    public void updateUser(User user) {
        jdbcTemplate.update(SQL_USER_UPDATE,
                user.getUsername(),
                user.getPassword(),
                user.getAccessType(),
                user.getUserId());
    }

    @Override
    public User getUserById(int userId) {
        try {
            return jdbcTemplate.queryForObject(SQL_USER_SELECT_ID, new UserMapper(), userId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = jdbcTemplate.query(SQL_USER_SELECT_ALL, new UserMapper());
        return users;
    }

    private static final class UserMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet rs, int i) throws SQLException {
            User user = new User();

            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setAccessType(rs.getInt("access_type"));

            return user;
        }
    }
}

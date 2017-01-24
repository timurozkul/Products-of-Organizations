package com.swcguild.capstone.dao;

import com.swcguild.capstone.model.Comment;
import com.swcguild.capstone.model.Post;
import com.swcguild.capstone.model.Product;
import com.swcguild.capstone.model.Tag;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author Naomi, Timur, Darren
 */
public class CapstoneDaoTest {

    private CapstoneDao dao;

    public CapstoneDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

        /*
         Get a fresh DAO for every test
         */
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        dao = ctx.getBean("capstoneDaoTest", CapstoneDao.class);

        JdbcTemplate cleaner = ctx.getBean("jdbcTemplate", JdbcTemplate.class);

        /*
         Before every test, clear out table data. We need to delete from 
         dependencies first due to constraint issues (since posts contain and
         therefore depend on comments, we need to delete comments before
         posts). Switching the order of these statements will cause errors.
         */
        cleaner.execute("DELETE FROM products WHERE 1=1;");
        cleaner.execute("ALTER TABLE products AUTO_INCREMENT = 1;");
        cleaner.execute("DELETE FROM posts_tags WHERE 1=1;");
        cleaner.execute("ALTER TABLE posts_tags AUTO_INCREMENT = 1;");
        cleaner.execute("DELETE FROM tags WHERE 1=1;");
        cleaner.execute("ALTER TABLE tags AUTO_INCREMENT = 1;");
        cleaner.execute("DELETE FROM comments WHERE 1=1;");
        cleaner.execute("ALTER TABLE comments AUTO_INCREMENT = 1;");
        cleaner.execute("DELETE FROM posts WHERE 1=1;");
        cleaner.execute("ALTER TABLE posts AUTO_INCREMENT = 1;");

    }

    @After
    public void tearDown() {
    }

    //
    //
    //
    //
    //
    //
    //
    //*******************************************************************//
    //*******************************************************************//
    //*************************** T E S T S *****************************//
    //*******************************************************************//
    //*******************************************************************//
    //
    //
    //
    //
    //
    //
    //
    @Test
    public void testAddPost() {

        /*
         Add post and prove that its id is 1.
         */
        Post p1 = new Post();
        p1.setAuthor("Joe");
        p1.setContent("Lorem ipsum dolor sit amet...");
        p1.setDate(new Date());
        p1.setTags(new ArrayList<>());
        p1.setComments(new ArrayList<>());
        dao.addPost(p1);

        Assert.assertTrue(p1.getPostId() == 1);

        /////////////////////////////////////////////////
        /*
         Add another post and assert that its id is exactly 1 more than 
         the first. Testing auto-incrementation.
         */
        Post p2 = new Post();
        p2.setAuthor("Maria");
        p2.setContent("Hi Maria. This is a test comment.");
        p2.setDate(new Date());
        p2.setTags(new ArrayList<>());
        p2.setComments(new ArrayList<>());
        dao.addPost(p2);

        Assert.assertEquals(p2.getPostId(), p1.getPostId() + 1);

        /////////////////////////////////////////////////////////
        /*
         Add a third post and assert that an earlier post's id cannot be 
         greater than a later post's id.
         */
        Post p3 = new Post();
        p3.setAuthor("Michael");
        p3.setContent("Hello Michael. Are you real?");
        p3.setDate(new Date());
        p3.setTags(new ArrayList<>());
        p3.setComments(new ArrayList<>());
        dao.addPost(p3);

        Assert.assertFalse(p2.getPostId() > p3.getPostId());

        /////////////////////////////////////////////////////////
        /*
         Add a fourth post and assert that the seconds of p4's date are either
         equal to or greater than p1's seconds. This assertion will only occur
         if both ints aren't 0, because there is a slight chance that during the
         execution of this test p1's date will end with second :59 and p4's
         will end with :00, which would make the test fail. So we use a boolean
         to protect when this test should occur.
         */
        Post p4 = new Post();
        p4.setAuthor("Donna");
        p4.setContent("this post is cool :) AND SO AM I!!!!");
        p4.setDate(new Date());
        p4.setTags(new ArrayList<>());
        p4.setComments(new ArrayList<>());
        dao.addPost(p4);

        int p4DateSeconds = Integer.parseInt(p4.getDate().toString().substring(26));
        int p1DateSeconds = Integer.parseInt(p1.getDate().toString().substring(26));

        boolean sameMinute = !(p1DateSeconds == 59 && p4DateSeconds == 0);

        if (sameMinute) {
            Assert.assertTrue(p4DateSeconds >= p1DateSeconds);
        }

        /////////////////////////////////////////////////////////
    }

    @Test
    public void testRemovePost() {

        /*
         Add, save id to int, then remove with int. Assert getting by id
         will be null.
         */
        Post p1 = new Post();
        p1.setAuthor("Joe");
        p1.setContent("Lorem ipsum dolor sit amet...");
        p1.setDate(new Date());
        p1.setTags(new ArrayList<>());
        p1.setComments(new ArrayList<>());
        dao.addPost(p1);

        int id = p1.getPostId();
        dao.removePost(id);

        Assert.assertNull(dao.getPostById(id));

        ////////////////////////////////////////////////////
        /*  
         Add posts, remove one, then add another. The post id of p5 should
         be p4's + 1, instead of filling in the newly vacated spot of p3.
         This is testing the auto-incrementation of our database.
         */
        Post p2 = new Post();
        p2.setAuthor("Maria");
        p2.setContent("Hi Maria. This is a test post.");
        p2.setDate(new Date());
        p2.setTags(new ArrayList<>());
        p2.setComments(new ArrayList<>());
        dao.addPost(p2);

        Post p3 = new Post();
        p3.setAuthor("Michael");
        p3.setContent("Hello Michael. Are you real?");
        p3.setDate(new Date());
        p3.setTags(new ArrayList<>());
        p3.setComments(new ArrayList<>());
        dao.addPost(p3);

        Post p4 = new Post();
        p4.setAuthor("Donna");
        p4.setContent("this post is cool :) AND SO AM I!!!!");
        p4.setDate(new Date());
        p4.setTags(new ArrayList<>());
        p4.setComments(new ArrayList<>());
        dao.addPost(p4);

        dao.removePost(p3.getPostId());

        Post p5 = new Post();
        p5.setAuthor("Sarah");
        p5.setContent("I am typing text now");
        p5.setDate(new Date());
        p5.setTags(new ArrayList<>());
        p5.setComments(new ArrayList<>());
        dao.addPost(p5);

        Assert.assertTrue(p5.getPostId() == p4.getPostId() + 1);

        ///////////////////////////////////////////////////////////////
        /*
         Get all, remove one, then get all. Assert new list size is smaller.
         */
        List<Post> beforeList = dao.getAllPosts();

        dao.removePost(p2.getPostId());

        List<Post> afterList = dao.getAllPosts();

        Assert.assertEquals(beforeList.size() - 1, afterList.size());

    }

    @Test
    public void testUpdatePost() {

        /*
         Add post, save content to string, update content, update post, save
         new content to string. Assert strings aren't equal.
         */
        Post p1 = new Post();
        p1.setAuthor("Sandy");
        p1.setContent("asdfjkl;");
        p1.setDate(new Date());
        p1.setTags(new ArrayList<>());
        p1.setComments(new ArrayList<>());
        dao.addPost(p1);

        String contentBefore = dao.getPostById(p1.getPostId()).getContent();

        p1.setContent("Pray I don't alter it any further.");
        dao.updatePost(p1);

        String contentAfter = dao.getPostById(p1.getPostId()).getContent();

        Assert.assertNotEquals(contentBefore, contentAfter);

        //////////////////////////////////////////////////////////////
        /*
         Add 3 additional posts, save them to list. Iterate through and
         change the author of any post with an id > 2 to "Jane", then update
         those posts to the database. Pull all posts, then add any post whose
         author isn't "Jane" to a new list. Assert both that list size is 1,
         and the author of its only post is "Sandy".
         */
        Post p2 = new Post();
        p2.setAuthor("Jane");
        p2.setContent("I am typing this post.");
        p2.setDate(new Date());
        p2.setTags(new ArrayList<>());
        p2.setComments(new ArrayList<>());
        dao.addPost(p2);

        Post p3 = new Post();
        p3.setAuthor("JoAnn");
        p3.setContent("words are here");
        p3.setDate(new Date());
        p3.setTags(new ArrayList<>());
        p3.setComments(new ArrayList<>());
        dao.addPost(p3);

        Post p4 = new Post();
        p4.setAuthor("Gina");
        p4.setContent("this is the text i have chosen to type.");
        p4.setDate(new Date());
        p4.setTags(new ArrayList<>());
        p4.setComments(new ArrayList<>());
        dao.addPost(p4);

        List<Post> posts = dao.getAllPosts();

        for (Post p : posts) {
            if (p.getPostId() > 2) {
                p.setAuthor("Jane");
                dao.updatePost(p);
            }
        }

        List<Post> newPosts = new ArrayList<>();

        for (Post p : posts) {
            if (!p.getAuthor().equals("Jane")) {
                newPosts.add(p);
            }
        }

        Assert.assertEquals(1, newPosts.size());
        Assert.assertEquals("Sandy", newPosts.get(0).getAuthor());

    }

    @Test
    public void testGetPostById() {

        /*
         Add post, get by its id and save to new post, assert properties of the
         two posts are equal.
         */
        Post p1 = new Post();
        p1.setAuthor("Carlos");
        p1.setContent("Welcome to this post I've made!");
        p1.setDate(new Date());
        p1.setTags(new ArrayList<>());
        p1.setComments(new ArrayList<>());
        dao.addPost(p1);

        Post p1FromDb = dao.getPostById(p1.getPostId());

        Assert.assertEquals(p1.getAuthor(), p1FromDb.getAuthor());
        Assert.assertEquals(p1.getContent(), p1FromDb.getContent());

        ////////////////////////////////////////////////////////////////////
        /*
         Add a post with blank content, get by id, assert its content is still
         empty. This tests the NOT NULL requirement of the table column. An
         empty string isn't null, so this should return a complete post.
         */
        Post p2 = new Post();
        p2.setAuthor("Eva");
        p2.setContent("");
        p2.setDate(new Date());
        p2.setTags(new ArrayList<>());
        p2.setComments(new ArrayList<>());
        dao.addPost(p2);

        Assert.assertTrue(dao.getPostById(p2.getPostId()).getContent().isEmpty());

        ///////////////////////////////////////////////////////////////////
        /*
         Assert that getting by a non-existant id returns null
         */
        Assert.assertNull(dao.getPostById(36612));

        /////////////////////////////////////////////////////////////////
        /*
         Clean out posts in the database. Loop to add 100 posts back into the
         database. Create two new lists and randomly add posts to them by getting
         with post id equal to the current iteration. Track the list sizes with
         independent ints, then assert that each list's size is equal to its
         tracking int.
         */
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        dao = ctx.getBean("capstoneDaoTest", CapstoneDao.class);

        JdbcTemplate cleaner = ctx.getBean("jdbcTemplate", JdbcTemplate.class);

        cleaner.execute("DELETE FROM posts WHERE 1=1;");
        cleaner.execute("ALTER TABLE posts AUTO_INCREMENT = 1;");

        for (int i = 1; i < 101; i++) {

            Post p = new Post();
            p.setAuthor("Author #" + i);
            p.setContent("Post #" + i);
            p.setDate(new Date());
            p.setTags(new ArrayList<>());
            p.setComments(new ArrayList<>());
            dao.addPost(p);

        }

        Random r = new Random();

        List<Post> listA = new ArrayList<>();
        List<Post> listB = new ArrayList<>();

        int a = 0;
        int b = 0;

        for (int i = 1; i < 101; i++) {

            int listChoice = r.nextInt(2);

            switch (listChoice) {
                case 0:
                    listA.add(dao.getPostById(i));
                    a++;
                    break;
                default:
                    listB.add(dao.getPostById(i));
                    b++;

            }

        }

        Assert.assertEquals(a, listA.size());
        Assert.assertEquals(b, listB.size());

    }

    @Test
    public void testGetPostsByTag() {

        /*
         Create 3 posts and 3 tags. Give the first post all 3 tags, the second
         post the last two tags, and the third post only the last tag. Add all 
         to database, then call getPostsByTag by each tag and save all three
         calls to separate lists. Assert list sizes are accurate, and assert
         specific properties of specific indexes are accurate.
         */
        Post p1 = new Post();
        p1.setAuthor("First Author");
        p1.setContent("first post content");
        p1.setDate(new Date());
        p1.setTags(new ArrayList<>());
        p1.setComments(new ArrayList<>());

        Post p2 = new Post();
        p2.setAuthor("Second Author");
        p2.setContent("second post content");
        p2.setDate(new Date());
        p2.setTags(new ArrayList<>());
        p2.setComments(new ArrayList<>());

        Post p3 = new Post();
        p3.setAuthor("Third Author");
        p3.setContent("third post content");
        p3.setDate(new Date());
        p3.setTags(new ArrayList<>());
        p3.setComments(new ArrayList<>());

        Tag t1 = new Tag();
        t1.setName("larry");

        Tag t2 = new Tag();
        t2.setName("curly");

        Tag t3 = new Tag();
        t3.setName("moe");

        p1.getTags().add(t1);

        p1.getTags().add(t2);
        p2.getTags().add(t2);

        p1.getTags().add(t3);
        p2.getTags().add(t3);
        p3.getTags().add(t3);

        dao.addPost(p1);
        dao.addPost(p2);
        dao.addPost(p3);

        List<Post> larryList = dao.getPostsByTag(t1.getTagId());
        List<Post> curlyList = dao.getPostsByTag(t2.getTagId());
        List<Post> moeList = dao.getPostsByTag(t3.getTagId());

        Assert.assertEquals(1, larryList.size());
        Assert.assertTrue(larryList.get(0).getAuthor().equals("First Author"));

        Assert.assertEquals(2, curlyList.size());
        Assert.assertTrue(curlyList.get(1).getAuthor().equals("Second Author"));

        Assert.assertEquals(3, moeList.size());
        Assert.assertTrue(moeList.get(2).getAuthor().equals("Third Author"));

    }

    @Test
    public void testGetAllPosts() {

        /*
         Add a post with a comment and a tag. Get all from database and save to
         list, then assert list size is 1.
         */
        Post p1 = new Post();
        p1.setAuthor("Joe");
        p1.setContent("Lorem ipsum dolor sit amet...");
        p1.setDate(new Date());
        p1.setTags(new ArrayList<>());
        p1.setComments(new ArrayList<>());

        Comment c1 = new Comment();
        c1.setAuthor("Mark");
        c1.setContent("In this comment I agree with you.");
        c1.setDate(new Date());
        c1.setPostId(p1.getPostId());

        Tag t1 = new Tag();
        t1.setName("Flowers");

        List<Comment> p1Comments = new ArrayList<>();
        List<Tag> p1Tags = new ArrayList<>();

        p1Comments.add(c1);
        p1Tags.add(t1);

        p1.setComments(p1Comments);
        p1.setTags(p1Tags);

        dao.addPost(p1);

        List<Post> posts = dao.getAllPosts();

        Assert.assertEquals(1, posts.size());

    }

    @Test
    public void testAddComment() {

        /*
         Create post, add a comment to it, assert that the commentId is 1.
         */
        Post p1 = new Post();
        p1.setAuthor("Joe");
        p1.setContent("Lorem ipsum dolor sit amet...");
        p1.setDate(new Date());
        p1.setTags(new ArrayList<>());
        p1.setComments(new ArrayList<>());
        dao.addPost(p1);

        Comment c1 = new Comment();
        c1.setAuthor("Billie");
        c1.setContent("In this comment I agree with you.");
        c1.setDate(new Date());
        c1.setPostId(p1.getPostId());
        dao.addComment(c1);

        Assert.assertTrue(c1.getCommentId() == 1);

        //////////////////////////////////////////////////////
        /*
         Add the same comment multiple times, then assert its comment id is the
         most recent addition, not its original.
         */
        dao.addComment(c1);
        dao.addComment(c1);

        Assert.assertEquals(3, c1.getCommentId());

    }

    @Test
    public void testRemoveComment() {

        /*
         Add a comment to a post, assert that it's there. Then remove it and
         assert it's not there.
         */
        Post p1 = new Post();
        p1.setAuthor("Ashleigh");
        p1.setContent("I'm gonna post this post wherever I feel like posting it...");
        p1.setDate(new Date());
        p1.setTags(new ArrayList<>());
        p1.setComments(new ArrayList<>());
        dao.addPost(p1);

        Comment c1 = new Comment();
        c1.setAuthor("Martin");
        c1.setContent("Well, I think this comment should be this sentence.");
        c1.setDate(new Date());
        c1.setPostId(p1.getPostId());
        dao.addComment(c1);

        Assert.assertNotNull(dao.getCommentById(c1.getCommentId()));
        dao.removeComment(c1.getCommentId());
        Assert.assertNull(dao.getCommentById(c1.getCommentId()));

        //////////////////////////////////////////////////////////////
        /*
         Add multiple comments, save all to list. Then remove a comment and
         save all to list. Assert that the second list is smaller than the first.
         */
        Comment c2 = new Comment();
        c2.setAuthor("");
        c2.setContent("");
        c2.setDate(new Date());
        c2.setPostId(p1.getPostId());
        dao.addComment(c2);

        Comment c3 = new Comment();
        c3.setAuthor("");
        c3.setContent("");
        c3.setDate(new Date());
        c3.setPostId(p1.getPostId());
        dao.addComment(c3);

        Comment c4 = new Comment();
        c4.setAuthor("");
        c4.setContent("");
        c4.setDate(new Date());
        c4.setPostId(p1.getPostId());
        dao.addComment(c4);

        List<Comment> beforeList = dao.getAllCommentsForPost(p1.getPostId());

        dao.removeComment(c2.getCommentId());

        List<Comment> afterList = dao.getAllCommentsForPost(p1.getPostId());

        Assert.assertEquals(beforeList.size() - 1, afterList.size());

    }

    @Test
    public void testUpdateComment() {

        /*
         Add comment to post, save comment content to string. Update comment, pull new
         version of comment from database and save its content to string. Assert
         that the before string and after string are not equal.
         */
        Post p1 = new Post();
        p1.setAuthor("David");
        p1.setContent("This is my post content. Do you like it?");
        p1.setDate(new Date());
        p1.setTags(new ArrayList<>());
        p1.setComments(new ArrayList<>());
        dao.addPost(p1);

        Comment c1 = new Comment();
        c1.setAuthor("Brandi");
        c1.setContent("I am commenting on your post!!");
        c1.setDate(new Date());
        c1.setPostId(p1.getPostId());
        dao.addComment(c1);

        String contentBefore = c1.getContent();

        c1.setContent("I have edited my comment on your post!!");

        dao.updateComment(c1);

        Comment c1FromDb = dao.getCommentById(c1.getCommentId());

        String contentAfter = c1FromDb.getContent();

        Assert.assertNotEquals(contentBefore, contentAfter);

        //////////////////////////////////////////////////////////
    }

    @Test
    public void testGetCommentById() {

        /*
         Add comment, pull copy of comment from database. Assert that the content
         of the comment you put in is the exact same as the content of the comment
         you pulled out.
         */
        Post p1 = new Post();
        p1.setAuthor("Chuck");
        p1.setContent("This is my post to the internet");
        p1.setDate(new Date());
        p1.setTags(new ArrayList<>());
        p1.setComments(new ArrayList<>());
        dao.addPost(p1);

        Comment c1 = new Comment();
        c1.setAuthor("Abby");
        c1.setContent("fh29fh88383nnnnn2n29fnzkxkx");
        c1.setDate(new Date());
        c1.setPostId(p1.getPostId());
        dao.addComment(c1);

        Comment c1FromDb = dao.getCommentById(c1.getCommentId());

        Assert.assertEquals(c1.getContent(), c1FromDb.getContent());

        ///////////////////////////////////////////////////////////////
        /*
         Pull a comment that doesn't exist and assert it's null.
         */
        Assert.assertNull(dao.getCommentById(81833));

    }

    @Test
    public void testGetAllCommentsForPost() {

        /*
         Add multiple comments to post, pull all comments as a list. Assert that
         list size is equal to the number of comments that were added.
         */
        Post p1 = new Post();
        p1.setAuthor("Shane");
        p1.setContent("This is my post on this site on the internet");
        p1.setDate(new Date());
        p1.setTags(new ArrayList<>());
        p1.setComments(new ArrayList<>());
        dao.addPost(p1);

        Comment c1 = new Comment();
        c1.setAuthor("Arthur B. Wiggins");
        c1.setContent("I'd like to leave a comment here.");
        c1.setDate(new Date());
        c1.setPostId(p1.getPostId());
        dao.addComment(c1);

        Comment c2 = new Comment();
        c2.setAuthor("Betsy");
        c2.setContent("My favorite type of ice cream is strawberry");
        c2.setDate(new Date());
        c2.setPostId(p1.getPostId());
        dao.addComment(c2);

        Comment c3 = new Comment();
        c3.setAuthor("sUp3rDUDE");
        c3.setContent("@Betsy lolwat?");
        c3.setDate(new Date());
        c3.setPostId(p1.getPostId());
        dao.addComment(c3);

        List<Comment> comments = dao.getAllCommentsForPost(p1.getPostId());

        Assert.assertEquals(3, comments.size());

        ////////////////////////////////////////////////////////
        /*
         
         */
    }

    @Test
    public void testAddTag() {

        /*
         Create tag, add to database, assert its id is 1.
         */
        Tag t1 = new Tag();
        t1.setName("Sports");
        dao.addTag(t1);

        Assert.assertTrue(t1.getTagId() == 1);

        //////////////////////////////////////////////////////////////
        /*
         Add multiple tags, get all, assert size
         */
        Tag t2 = new Tag();
        t2.setName("Waffles");
        dao.addTag(t2);

        Tag t3 = new Tag();
        t3.setName("Space");
        dao.addTag(t3);

        Tag t4 = new Tag();
        t4.setName("Silly");
        dao.addTag(t4);

        List<Tag> tags = dao.getAllTags();

        Assert.assertEquals(4, tags.size());

        //////////////////////////////////////////////////////////////
        /*
         Add same tag multiple times, assert its id hasn't changed (due to
         dao.tagExists() boolean in the dao.addTag method).
         */
        int before = t4.getTagId();

        dao.addTag(t4);
        dao.addTag(t4);
        dao.addTag(t4);
        dao.addTag(t4);
        dao.addTag(t4);

        int after = t4.getTagId();

        Assert.assertTrue(after == before);

    }

    @Test
    public void testRemoveTag() {

        /*
         Add, save id to int, remove, assert that getting by id is null
         */
        Tag t1 = new Tag();
        t1.setName("Noodles");
        dao.addTag(t1);

        int id = t1.getTagId();

        dao.removeTag(id);

        Assert.assertNull(dao.getTagById(id));

        //////////////////////////////////////////////////////////
        /*
         Add multiple, save size to int, remove, assert sizes are different
         */
        Tag t2 = new Tag();
        t2.setName("spoons");
        dao.addTag(t2);

        Tag t3 = new Tag();
        t3.setName("knives");
        dao.addTag(t3);

        Tag t4 = new Tag();
        t4.setName("forks");
        dao.addTag(t4);

        int before = dao.getAllTags().size();

        dao.removeTag(t2.getTagId());
        dao.removeTag(t3.getTagId());

        int after = dao.getAllTags().size();

        Assert.assertTrue(before - 2 == after);

    }

    @Test
    public void testGetTagById() {

        /*
         Add, save name to string, get by id, assert names are equal
         */
        Tag t1 = new Tag();
        t1.setName("Funny");
        dao.addTag(t1);

        String before = t1.getName();

        String after = dao.getTagById(t1.getTagId()).getName();

        Assert.assertEquals(before, after);

        /////////////////////////////////////////////////////////////
        /*
         Clean out tag data. Loop to add many tags and assert their ids are all
         the iteration # + 1. Adds an extra 4 seconds to total test time.
         */
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        JdbcTemplate cleaner = ctx.getBean("jdbcTemplate", JdbcTemplate.class);
        cleaner.execute("DELETE FROM tags;");
        cleaner.execute("ALTER TABLE tags AUTO_INCREMENT = 1;");

        Tag tag = new Tag();

        for (int i = 0; i < 1000; i++) {

            tag.setName("Tag #" + (i + 1));
            dao.addTag(tag);
            Assert.assertEquals(i + 1, dao.getTagById(i + 1).getTagId());

        }

    }

    @Test
    public void testGetAllTagsForPost() {

        /*
         Create post, create list of tags, loop to add many tags to list.
         Save list size to int. Add list to post and add post to database.
         Get all tags for post id, save to new list. Assert list sizes are equal.
         */
        Post p1 = new Post();
        p1.setAuthor("Lauren");
        p1.setContent("I'm typing something on a keyboard? Neat!");
        p1.setDate(new Date());
        p1.setTags(new ArrayList<>());
        p1.setComments(new ArrayList<>());
        dao.addPost(p1);

        List<Tag> tags = new ArrayList<>();
        Tag tag = new Tag();

        for (int i = 0; i < 25; i++) {

            tag.setName("Tag #" + (i + 1));
            dao.addTag(tag);
            tags.add(tag);

        }

        int before = tags.size();

        p1.setTags(tags);

        dao.addPost(p1);

        int after = dao.getAllTagsForPost(p1.getPostId()).size();

        Assert.assertEquals(before, after);

        ///////////////////////////////////////////////////////////
        /*
         Get tags for a post that doesn't have any.
         */
        Post p2 = new Post();
        p2.setAuthor("Ari");
        p2.setContent("I GREATLY ENJOY KEYBOARDING");
        p2.setDate(new Date());
        p2.setTags(new ArrayList<>());
        p2.setComments(new ArrayList<>());
        dao.addPost(p2);

        Assert.assertEquals(0, dao.getAllTagsForPost(p2.getPostId()).size());

    }

    @Test
    public void testGetAllTags() {

        /*
         Add tags, get all, save to list, assert list contains each tag
         */
        Tag t1 = new Tag();
        t1.setName("Red");
        dao.addTag(t1);

        Tag t2 = new Tag();
        t2.setName("Yellow");
        dao.addTag(t2);

        Tag t3 = new Tag();
        t3.setName("Blue");
        dao.addTag(t3);

        List<Tag> tags = dao.getAllTags();

        List<String> tagNames = new ArrayList<>();

        for (Tag t : tags) {
            tagNames.add(t.getName());
        }

        Assert.assertTrue(tagNames.contains(t1.getName()));
        Assert.assertTrue(tagNames.contains(t2.getName()));
        Assert.assertTrue(tagNames.contains(t3.getName()));

        //////////////////////////////////////////////////////////////
        /*
         Add tag multiple times, assert it's only in the database once
         */
        int before = dao.getAllTags().size();

        dao.addTag(t3);
        dao.addTag(t3);
        dao.addTag(t3);
        dao.addTag(t3);
        dao.addTag(t3);
        dao.addTag(t3);
        dao.addTag(t3);

        int after = dao.getAllTags().size();

        Assert.assertEquals(before, after);

    }

    @Test
    public void testAddProduct() {

        /*
         Add product, get by its id, assert the result isn't null
         */
        Product p1 = new Product();
        p1.setName("Coffee");
        p1.setPrice(2.49);
        dao.addProduct(p1);

        Assert.assertNotNull(dao.getProductById(p1.getProductId()));

        ///////////////////////////////////////////////////////////////
        /*
         Add a copy of the same product and assert its id is 1 more than p1's 
         (testing that you can have products with the same name & price in the 
         database).
         */
        Product p2 = new Product();
        p2.setName(p1.getName());
        p2.setPrice(p1.getPrice());
        dao.addProduct(p2);

        Assert.assertEquals(p1.getProductId() + 1, p2.getProductId());

        ////////////////////////////////////////////////////////////////
        /*
         Add a product with a price above the 999.99 maximum, then assert null
         when you try to get it by its id. The dao.addProduct call must be
         surrounded in a try-catch to avoid a SQL Data Truncation error.
         */
        Product p3 = new Product();
        p3.setName("Million Dollar Espresso");
        p3.setPrice(1000000);
        try {
            dao.addProduct(p3);
        } catch (Exception e) {
        }

        Assert.assertNull(dao.getProductById(p3.getProductId()));

    }

    @Test
    public void testRemoveProduct() {

        /*
         Add, remove, assertNull when getting by id
         */
        Product p1 = new Product();
        p1.setName("Dark Coffee");
        p1.setPrice(2.29);
        dao.addProduct(p1);

        dao.removeProduct(p1.getProductId());

        Assert.assertNull(dao.getProductById(p1.getProductId()));

        ////////////////////////////////////////////////////////////
        /*
         Add product, get all from database and save to list. Assert product
         exists, remove from database, get all and save to list. Assert product
         no longer exists. We have an extra step that uses the name property
         so we can ensure that the product's data is intact.
         */
        Product p2 = new Product();
        p2.setName("Muffin");
        p2.setPrice(1.49);
        dao.addProduct(p2);

        List<Product> products = dao.getAllProducts();

        List<String> productNames = new ArrayList<>();

        for (Product p : products) {
            productNames.add(p.getName());
        }

        Assert.assertTrue(productNames.contains(p2.getName()));

        dao.removeProduct(p2.getProductId());

        products = dao.getAllProducts();

        productNames.clear();

        for (Product p : products) {
            productNames.add(p.getName());
        }

        Assert.assertFalse(productNames.contains(p2.getName()));

    }

    @Test
    public void testUpdateProduct() {

        /*
         Add product, assert its name is correct in database, change name, 
         assert its name has been changed in the database.
         */
        Product p1 = new Product();
        p1.setName("Bagel");
        p1.setPrice(1.29);
        dao.addProduct(p1);

        Assert.assertTrue(dao.getProductById(p1.getProductId()).getName().equals("Bagel"));

        String testString = "New Product Name";

        p1.setName(testString);

        dao.updateProduct(p1);

        Assert.assertTrue(dao.getProductById(p1.getProductId()).getName().equals(testString));

        /////////////////////////////////////////////////////////////////
        
        /*
        
        */
        
        
    }

//    @Test
//    public void testGetProductById() {
//
//    }
//
//    @Test
//    public void testGetAllProducts() {
//
//    }
//
//    @Test
//    public void testAddOrder() {
//
//    }
//
//    @Test
//    public void testRemoveOrder() {
//
//    }
//
//    @Test
//    public void testGetOrderById() {
//
//    }
//
//    @Test
//    public void testGetAllOrders() {
//
//    }
//
//    @Test
//    public void testAddUser() {
//
//    }
//
//    @Test
//    public void testRemoveUser() {
//
//    }
//
//    @Test
//    public void testUpdateUser() {
//
//    }
//
//    @Test
//    public void testGetUserById() {
//
//    }
//
//    @Test
//    public void testGetAllUsers() {
//
//    }
}

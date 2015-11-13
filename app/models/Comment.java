/**
 *
 * @author Mario Lujan
 */
package models;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Reference;
import java.util.*;
import play.data.validation.*;
import play.modules.morphia.Model;
 
@Entity
public class Comment extends Model {
     
    @Required
    public String author;
    
    @Required
    public Date postedAt;
     

    @Required
    @MaxSize(10000)
    public String content;
    
    @Required
    @Reference
    public Post post;
    
    public Comment(Post post, String author, String content) {
        this.post = post;
        this.author = author;
        this.content = content;
        this.postedAt = new Date();
    }
    
    
    public String toString() {
        return content.length() > 50 ? content.substring(0, 50) + "..." : content;
    }
    
    @Added void cascadeAdd() {
        if (!post.comments.contains(this)) {
            post.comments.add(this);
            post.save();
        }
    }
    
  /* public void postComments() {
    // Create a new user and save it
    User bob = new User("bob@gmail.com", "secret", "Bob").save();
 
    // Create a new post
    Post bobPost = new Post(bob, "My first post", "Hello world").save();
 
    // Post a first comment
    new Comment(bobPost, "Jeff", "Nice post").save();
    new Comment(bobPost, "Tom", "I knew that !").save();
 
    // Retrieve all comments
    List<Comment> bobPostComments = Comment.find("byPost", bobPost).fetch();
 
    // Tests
    /*assertEquals(2, bobPostComments.size());
 
    Comment firstComment = bobPostComments.get(0);
    assertNotNull(firstComment);
    assertEquals("Jeff", firstComment.author);
    assertEquals("Nice post", firstComment.content);
    assertNotNull(firstComment.postedAt);
 
    Comment secondComment = bobPostComments.get(1);
    assertNotNull(secondComment);
    assertEquals("Tom", secondComment.author);
    assertEquals("I knew that !", secondComment.content);
    assertNotNull(secondComment.postedAt);
    } */
 
}

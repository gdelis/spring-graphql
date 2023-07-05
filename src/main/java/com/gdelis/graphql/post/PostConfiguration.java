package com.gdelis.graphql.post;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PostConfiguration {

   @Bean
   public List<Post> postList() {

      List<Post> posts = new ArrayList<>();

      posts.add(new Post(1, "title-1", "body-1"));
      posts.add(new Post(2, "title-2", "body-2"));
      posts.add(new Post(3, "title-3", "body-3"));
      posts.add(new Post(4, "title-4", "body-4"));

      return posts;
   }
}

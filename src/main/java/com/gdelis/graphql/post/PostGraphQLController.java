package com.gdelis.graphql.post;

import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class PostGraphQLController {

   private final PostRepository postRepository;

   public PostGraphQLController(final PostRepository postRepository) {
      this.postRepository = postRepository;
   }

}

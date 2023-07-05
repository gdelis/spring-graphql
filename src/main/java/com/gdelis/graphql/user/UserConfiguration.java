package com.gdelis.graphql.user;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfiguration {

   @Bean
   public List<User> userList() {
      ArrayList<User> users = new ArrayList<>();

      users.add(new User(1, "George", List.of(2, 3), List.of(4)));
      users.add(new User(2, "No followers 1", Collections.emptyList(), Collections.emptyList()));
      users.add(new User(3, "No followers 2", Collections.emptyList(), Collections.emptyList()));
      users.add(new User(4, "With posts 1", List.of(1, 2), List.of(1, 2)));
      users.add(new User(5, "With posts 2", List.of(2, 3), List.of(1, 2, 3)));

      return users;
   }
}

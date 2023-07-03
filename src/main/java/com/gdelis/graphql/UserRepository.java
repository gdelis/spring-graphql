package com.gdelis.graphql;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

   private final List<User> userList;

   public UserRepository(final List<User> userList) {
      this.userList = userList;
   }

   public List<User> findAll() {
      return userList;
   }

   public Optional<User> findById(final Integer id) {
      return userList.stream()
                     .filter(s -> Objects.equals(id, s.id()))
                     .findAny();
   }
}

package com.gdelis.graphql.user;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class UserRepository {

   private final List<User> userList;

   public UserRepository(final List<User> userList) {
      this.userList = userList;
   }

   public List<User> findAll() {
      log.info("invocation of user - findAll");
      return userList;
   }

   public Optional<User> findById(final Integer id) {
      log.info("invocation of user - findById");
      return userList.stream()
                     .filter(s -> Objects.equals(id, s.id()))
                     .findAny();
   }
}

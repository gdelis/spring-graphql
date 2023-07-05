package com.gdelis.graphql.user;

import com.gdelis.graphql.post.PostConfiguration;
import com.gdelis.graphql.post.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.context.annotation.Import;
import org.springframework.graphql.test.tester.GraphQlTester;

import static org.assertj.core.api.Assertions.assertThat;

@Import(value = {UserRepository.class, PostRepository.class, UserConfiguration.class, PostConfiguration.class})
@GraphQlTest(controllers = UserGraphQLController.class)
class UserGraphQLControllerIntegrationTest {

   @Autowired
   private GraphQlTester graphQlTester;

   @Test
   void getAllUsers_shouldReturnListOfUsers() {
      String document = """
          query {
           getAllUsers {
              id,
              name
           }
          }
          """;

      graphQlTester.document(document)
                   .execute()
                   .path("getAllUsers")
                   .entityList(User.class)
                   .hasSize(5);
   }

   @Test
   void getUserById_shouldReturnUser() {
      String document = """
          query getUserById($id: ID) {
           getUserById(id: $id) {
              id,
              name
           }
          }
          """;

      graphQlTester.document(document)
                   .variable("id", 1)
                   .execute()
                   .path("getUserById")
                   .entity(User.class)
                   .satisfies(user -> {
                      assertThat(user).isNotNull();
                      assertThat(user).usingRecursiveAssertion()
                                      .isEqualTo(new User(1, "George", null, null));
                   });
   }
}
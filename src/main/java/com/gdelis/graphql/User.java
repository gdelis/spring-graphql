package com.gdelis.graphql;

import java.util.List;

public record User(Integer id, String name, List<Integer> followers, List<Integer> posts) {
}

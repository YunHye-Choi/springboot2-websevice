package com.jojoldu.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
// 기본 Entity Repository. 반드시 Entity 클래스와 같은 위치에 있어야함!
public interface PostsRepository extends JpaRepository<Posts, Long> {

}

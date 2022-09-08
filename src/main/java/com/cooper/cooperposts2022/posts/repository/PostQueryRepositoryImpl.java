package com.cooper.cooperposts2022.posts.repository;

import com.cooper.cooperposts2022.posts.domain.Post;
import com.cooper.cooperposts2022.posts.domain.QPost;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PostQueryRepositoryImpl implements PostQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<Post> findById(String postId) {
        Post post = jpaQueryFactory.selectFrom(QPost.post)
                .where(QPost.post.id.eq(postId))
                .fetchOne();
        return Optional.ofNullable(post);
    }

}
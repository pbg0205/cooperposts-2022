package com.cooper.cooperposts2022.posts.domain;

import com.cooper.cooperposts2022.posts.dto.PostUpdateRequestDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;

    @Column(nullable = false, length = 500)
    private String title;

    @Lob
    @Column(nullable = false)
    private String content;

    @Column(nullable = false, length = 50)
    private String author;

    @Embedded
    private final Questions questions = new Questions();

    private Post(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public static Post create(String title, String content, String author) {
        return new Post(title, content, author);
    }

    public void addAnswer(Question question) {
        questions.addAnswer(question);
    }

    public void updatePost(PostUpdateRequestDto postUpdateRequestDto) {
        this.title = postUpdateRequestDto.getTitle();
        this.author = postUpdateRequestDto.getAuthor();
        this.content = postUpdateRequestDto.getContent();
    }

}
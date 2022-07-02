package com.cooper.cooperposts2022.posts.domain;

import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Embeddable
public class Questions {

    @OneToMany(mappedBy = "post")
    private List<Question> questions = new ArrayList<>();

    public void addAnswer(Question question) {
        questions.add(question);
    }

}

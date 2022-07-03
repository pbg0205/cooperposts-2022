package com.cooper.cooperposts2022.posts.domain;

import com.cooper.cooperposts2022.posts.dto.QuestionLookupResponseDto;

import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Embeddable
public class Questions {

    @OneToMany(mappedBy = "post")
    private List<Question> questions = new ArrayList<>();

    public void addAnswer(Question question) {
        questions.add(question);
    }

    public List<QuestionLookupResponseDto> convertToLookupResponseDtos() {
        return questions.stream()
                .map(QuestionLookupResponseDto::fromEntity)
                .collect(Collectors.toList());
    }

}

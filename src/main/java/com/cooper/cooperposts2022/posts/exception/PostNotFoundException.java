package com.cooper.cooperposts2022.posts.exception;

public class PostNotFoundException extends RuntimeException {

    private static final String POST_NOT_FOUND_EXCEPTION_MESSAGE_FORMAT = "게시글을 찾을 수 없습니다 : (%s : %s)";

    public PostNotFoundException(String postId) {
        super(String.format(POST_NOT_FOUND_EXCEPTION_MESSAGE_FORMAT, "postId", postId));
    }

}

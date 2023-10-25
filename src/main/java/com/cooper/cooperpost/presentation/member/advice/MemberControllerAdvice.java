package com.cooper.cooperpost.presentation.member.advice;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.RequiredArgsConstructor;

import com.cooper.cooperpost.support.error.ErrorCode;
import com.cooper.cooperpost.support.response.ApiResponse;

@RestControllerAdvice
@RequiredArgsConstructor
public class MemberControllerAdvice {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponse<?>> handleValidationErrors(
		MethodArgumentNotValidException methodArgumentNotValidException) {

		Map<String, String> errorMessages = methodArgumentNotValidException.getBindingResult().getFieldErrors()
			.stream()
			.collect(Collectors.toMap(fieldError -> fieldError.getField(), fieldError -> fieldError.getDefaultMessage()));

		return ResponseEntity.badRequest()
			.body(ApiResponse.error(ErrorCode.E40001, errorMessages.toString()));
	}
}

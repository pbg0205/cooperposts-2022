package com.cooper.cooperpost.acceptance.base.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;

import com.cooper.cooperpost.acceptance.base.listener.AcceptanceTestExecutionListener;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestExecutionListeners(value = {AcceptanceTestExecutionListener.class},
	mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS
)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.TYPE)
@Inherited
public @interface AcceptanceTest {
}

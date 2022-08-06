package com.cooper.cooperposts2022.logging.template;

import com.cooper.cooperposts2022.common.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ApiResponseFailTemplate {

    private static final String REQUEST_ID_KEY_NAME = "requestId";

    public static <T> void debugApiResponseFail(ResponseEntity<ApiResult<T>> response) {
        String requestId = MDC.get(REQUEST_ID_KEY_NAME);

        ApiResult<?> apiResult = response.getBody();

        log.debug("[{}] returnCode: {}, responseBody: {}",
                requestId,
                response.getStatusCode().value(),
                apiResult.getData()
        );

        MDC.remove(requestId);
    }

}

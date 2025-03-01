package com.langrentao.antmall.common.advice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.langrentao.antmall.common.constant.ProcessIgnoreUrl;
import com.langrentao.antmall.common.entity.ResultWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@Slf4j
@ControllerAdvice
public class GlobalResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter methodParameter,
                            Class<? extends HttpMessageConverter<?>> converterType) {
        // 若接口返回的类型本身就是ResultWrapper，则无需操作，返回false
        // return !methodParameter.getParameterType().equals(ResultWrapper.class);

        // 本处全部都放过，在后边进行处理
        return true;
    }

    @Override
    @ResponseBody
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter,
                                  MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {

        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        if (body instanceof String) {
            // 若返回值为String类型，需要包装为String类型返回。否则会报错
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                ResultWrapper<Object> resultWrapper = ResultWrapper.success().data(body);
                return objectMapper.writeValueAsString(resultWrapper);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("序列化String错误");
            }
        } else if (body instanceof ResultWrapper) {
            return body;
        } else if (isKnife4jUrl(request.getURI().getPath())) {
            // 如果是接口文档uri，直接跳过
            return body;
        }

        return ResultWrapper.success().data(body);
    }

    private boolean isKnife4jUrl(String uri) {
        AntPathMatcher pathMatcher = new AntPathMatcher();
        for (String s : ProcessIgnoreUrl.KNIFE4J) {
            if (pathMatcher.match(s, uri)) {
                return true;
            }
        }
        return false;
    }
}
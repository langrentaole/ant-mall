package com.langrentao.antmall.common.constant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface ProcessIgnoreUrl {
    List<String> KNIFE4J = Arrays.asList(
            "/doc.html",
            "/swagger-resources",
            "/swagger-resources/configuration",
            "/v3/api-docs",
            "/v2/api-docs",
            "/webjars/**");

    List<String> ALL = new ArrayList<>(KNIFE4J);
}

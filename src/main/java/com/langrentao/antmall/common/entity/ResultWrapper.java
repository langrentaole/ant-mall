package com.langrentao.antmall.common.entity;

import com.langrentao.antmall.common.constant.ResultCode;
import lombok.Data;

@Data
public class ResultWrapper<T> {
    private Boolean success = true;

    private Integer code;

    private T data;

    private String message;

    private ResultWrapper() {
    }

    public static <T> ResultWrapper<T> success() {
        return success(null);
    }

    public static <T> ResultWrapper<T> success(T data) {
        return assemble(ResultCode.SUCCESS.getCode(), true, data);
    }

    public static <T> ResultWrapper<T> error() {
        return error(null);
    }

    public static <T> ResultWrapper<T> error(T data) {
        return assemble(ResultCode.SYSTEM_FAILURE.getCode(), false, data);
    }

    public ResultWrapper<T> data(T data) {
        this.setData(data);
        return this;
    }

    public ResultWrapper<T> message(String message) {
        this.setMessage(message);
        return this;
    }

    public ResultWrapper<T> code(int code) {
        this.setCode(code);
        return this;
    }

    public static <T> ResultWrapper<T> assemble(int code, boolean success, T data) {
        ResultWrapper<T> resultWrapper = new ResultWrapper<>();
        resultWrapper.setCode(code);
        resultWrapper.setSuccess(success);
        resultWrapper.setData(data);

        return resultWrapper;
    }
}
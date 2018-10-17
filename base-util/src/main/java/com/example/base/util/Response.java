package com.example.base.util;

import com.google.common.base.Preconditions;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> {

    private static final int SUCCESS_CODE = 0;
    private static final String SUCCESS_MSG = "成功";
    /**
     * 业务处理成功返回0，否则返回大于（700）的错误码，并返回相应错误提示msg
     */
    private int code;
    private String msg;
    private T payload;

    public static Response success(){
        return Response.builder()
                .code(SUCCESS_CODE).msg(SUCCESS_MSG)
                .build();
    }

    public static <T> Response<T> success(T payload) {
        Response<T> response = new Response<>();
        response.setCode(SUCCESS_CODE);
        response.setMsg(SUCCESS_MSG);
        response.setPayload(payload);
        return response;
    }

    public static Response fail(int code, String msg) {
        Preconditions.checkArgument(code > 700, "业务错误码必须大于700");
        return Response.builder()
                .code(code).msg(msg)
                .build();
    }

}

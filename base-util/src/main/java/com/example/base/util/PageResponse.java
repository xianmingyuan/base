package com.example.base.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageResponse<T> {

    private static final int SUCCESS_CODE = 0;
    private static final String SUCCESS_MSG = "成功";

    /**
     * 业务处理成功返回0，否则返回大于（700）的错误码，并返回相应错误提示msg
     */
    private int code;
    private String msg;
    private List<T> payload;
    /**
     * 当前页
     */
    private int currentPage;
    /**
     * 当前页内容数量
     */
    private int size;
    /**
     * 内容总数
     */
    private int total;

    public static PageResponse success(){
        return PageResponse.builder()
                .code(SUCCESS_CODE).msg(SUCCESS_MSG)
                .build();
    }

    public static <T> PageResponse<T> success(List<T> payload, int currentPage, int size, int total) {
        PageResponse<T> response = new PageResponse<>();
        response.setCode(SUCCESS_CODE);
        response.setMsg(SUCCESS_MSG);
        response.setPayload(payload);
        response.setCurrentPage(currentPage);
        response.setSize(size);
        response.setTotal(total);
        return response;
    }

    public static PageResponse fail(int code, String msg) {
        return PageResponse.builder()
                .code(code).msg(msg)
                .build();
    }


}

package com.cmlx.commons.handler;

import com.cmlx.commons.entity.CmlxResponse;
import com.cmlx.commons.utils.CmlxUtil;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author CMLX
 * @Date -> 2021/10/11 16:58
 * @Desc -> 处理403异常
 **/
public class CmlxAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        CmlxResponse cmlxResponse = new CmlxResponse();
        CmlxUtil.makeResponse(
                response, MediaType.APPLICATION_JSON_UTF8_VALUE,
                HttpServletResponse.SC_FORBIDDEN, cmlxResponse.message("没有权限访问该资源"));
    }
}

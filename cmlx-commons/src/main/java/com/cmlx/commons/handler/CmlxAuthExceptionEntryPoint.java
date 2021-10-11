package com.cmlx.commons.handler;

import com.cmlx.commons.entity.CmlxResponse;
import com.cmlx.commons.utils.CmlxUtil;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author CMLX
 * @Date -> 2021/10/11 16:47
 * @Desc -> 处理资源服务器异常，令牌不正确返回401和用户无权限返回403
 * common是普通maven项目，不是springboot项目，所以使用@Component不能成功注入到各个微服务子系统的Spring IOC容器中
 **/
public class CmlxAuthExceptionEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        CmlxResponse cmlxResponse = new CmlxResponse();
        CmlxUtil.makeResponse(
                response, MediaType.APPLICATION_JSON_UTF8_VALUE,
                HttpServletResponse.SC_UNAUTHORIZED, cmlxResponse.message("token无效")
        );
    }

    //@Override
    //public void commence(HttpServletRequest request, HttpServletResponse response,
    //                     AuthenticationException authException) throws IOException {
    //    CmlxResponse cmlxResponse = new CmlxResponse();
    //
    //    response.setContentType("application/json;charset=UTF-8");
    //    response.setStatus(401);
    //    response.getOutputStream().write(JSONObject.toJSONString(cmlxResponse.message("token无效")).getBytes());
    //}

    //@Override
    //public void commence(HttpServletRequest request, HttpServletResponse response,
    //                     AuthenticationException authException) throws IOException {
    //    CmlxResponse cmlxResponse = new CmlxResponse();
    //
    //    response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
    //    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    //    response.getOutputStream().write(JSONObject.toJSONString(cmlxResponse.message("token无效")).getBytes());
    //}

}
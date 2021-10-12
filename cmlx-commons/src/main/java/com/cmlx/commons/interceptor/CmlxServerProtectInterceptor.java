package com.cmlx.commons.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.cmlx.commons.entity.CmlxConstant;
import com.cmlx.commons.entity.CmlxResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.util.Base64Utils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author CMLX
 * @Date -> 2021/10/12 12:29
 * @Desc -> CmlxServerProtectInterceptor实现了HandlerInterceptor的preHandle方法，该拦截器可以拦截所有Web请求。
 * 在preHandle方法中，我们通过HttpServletRequest获取请求头中的Zuul Token，并校验其正确性，当校验不通过的时候返回403错误
 **/
public class CmlxServerProtectInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 从请求头中获取 ZuulToken
        String token = request.getHeader(CmlxConstant.ZUUL_TOKEN_HEADER);
        String zuulToken = new String(Base64Utils.encode(CmlxConstant.ZUUL_TOKEN_VALUE.getBytes()));
        // 校验 ZuulToken的正确性
        if (StringUtils.equals(zuulToken, token)) {
            return true;
        } else {
            CmlxResponse cmlxResponse = new CmlxResponse();
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write(JSONObject.toJSONString(cmlxResponse.message("请通过网关获取资源")));
            return false;
        }
    }
}

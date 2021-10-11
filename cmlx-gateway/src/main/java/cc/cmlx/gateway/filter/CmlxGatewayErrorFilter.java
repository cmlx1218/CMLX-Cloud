package cc.cmlx.gateway.filter;

import com.cmlx.commons.entity.CmlxResponse;
import com.cmlx.commons.utils.CmlxUtil;
import com.netflix.zuul.context.RequestContext;
import io.lettuce.core.dynamic.support.ReflectionUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.post.SendErrorFilter;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author CMLX
 * @Date -> 2021/10/11 17:44
 * @Desc -> 自定义Zuul异常处理
 **/
@Slf4j
@Component
public class CmlxGatewayErrorFilter extends SendErrorFilter {

    @Override
    public Object run() {
        try {
            CmlxResponse cmlxResponse = new CmlxResponse();
            // 获取当前请求上下文，获取到当前请求的服务名称 serviceId 和当前请求的异常对象 ExceptionHolder 等信息
            RequestContext ctx = RequestContext.getCurrentContext();
            String serviceId = (String) ctx.get(FilterConstants.SERVICE_ID_KEY);

            ExceptionHolder exception = findZuulException(ctx.getThrowable());
            String errorCause = exception.getErrorCause();
            Throwable throwable = exception.getThrowable();
            String message = throwable.getMessage();
            message = StringUtils.isBlank(message) ? errorCause : message;
            cmlxResponse = resolveExceptionMessage(message, serviceId, cmlxResponse);

            HttpServletResponse response = ctx.getResponse();
            CmlxUtil.makeResponse(
                    response, MediaType.APPLICATION_JSON_UTF8_VALUE,
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR, cmlxResponse
            );
            log.error("Zull sendError：{}", cmlxResponse.getMessage());
        } catch (Exception ex) {
            log.error("Zuul sendError", ex);
            ReflectionUtils.rethrowRuntimeException(ex);
        }
        return null;
    }

    private CmlxResponse resolveExceptionMessage(String message, String serviceId, CmlxResponse cmlxResponse) {
        if (StringUtils.containsIgnoreCase(message, "time out")) {
            return cmlxResponse.message("请求" + serviceId + "服务超时");
        }
        if (StringUtils.containsIgnoreCase(message, "forwarding error")) {
            return cmlxResponse.message(serviceId + "服务不可用");
        }
        return cmlxResponse.message("Zuul请求" + serviceId + "服务异常");
    }


}

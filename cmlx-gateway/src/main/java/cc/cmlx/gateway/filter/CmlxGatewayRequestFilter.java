package cc.cmlx.gateway.filter;

import com.cmlx.commons.entity.CmlxConstant;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author CMLX
 * @Date -> 2021/10/12 12:16
 * @Desc -> 避免客户端请求绕过网关，直接调用微服务
 * 在网关转发请求前，请求头部加入网关信息，然后在处理请求的微服务模块里定义全局拦截器，校验请求头部的网关信息，这样就能避免客户端直接访问微服务了
 * 需要在各个微服务里定义一个全局拦截器拦截请求，并校验Zuul Token
 **/
@Slf4j
@Component
public class CmlxGatewayRequestFilter extends ZuulFilter {

    /**
     * 对应Zuul生命周期的四个阶段：pre、post、route和error，我们要在请求转发出去前添加请求头，所以这里指定为pre
     *
     * @return
     */
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    /**
     * 过滤器的优先级，数字越小，优先级越高。PreDecorationFilter过滤器处理请求上下文且优先级为5，所以我们可以指定为6让我们的过滤器优先级比它低
     *
     * @return
     */
    @Override
    public int filterOrder() {
        return 6;
    }

    /**
     * 方法返回boolean类型，true时表示是否执行该过滤器的run方法，false则表示不执行
     *
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器的主要逻辑，这里我们通过请求上下文RequestContext获取了转发的服务名称serviceId和请求对象HttpServletRequest，并打印请求日志。
     * 随后往请求上下文的头部添加了Key为ZuulToken，Value为cmlx:zuul:123456的信息
     *
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        String serviceId = (String) ctx.get(FilterConstants.SERVICE_ID_KEY);
        HttpServletRequest request = ctx.getRequest();
        String host = request.getRemoteHost();
        String method = request.getMethod();
        String uri = request.getRequestURI();
        log.info("请求URI：{}，HTTP Method：{}，请求IP：{}，ServerId：{}", uri, method, host, serviceId);
        byte[] token = Base64Utils.encode((CmlxConstant.ZUUL_TOKEN_VALUE).getBytes());
        ctx.addZuulRequestHeader(CmlxConstant.ZUUL_TOKEN_HEADER, new String(token));
        return null;
    }
}

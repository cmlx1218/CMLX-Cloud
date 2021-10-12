package cc.cmlx.test.service;

import cc.cmlx.test.service.fallback.HelloServiceFallback;
import com.cmlx.commons.entity.CmlxServerConstant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author CMLX
 * @Date -> 2021/10/12 9:29
 * @Desc ->
 **/
// value：远程服务的名称；contextId：Feign Client别名，当我们定义了多个Feign Client并且value值相同（即调用同一个服务）的时候，需要手动通过contextId设置别名，否则程序将抛出异常
@FeignClient(value = CmlxServerConstant.CMLX_SERVER_SYSTEM, contextId = "helloServiceClient", fallbackFactory = HelloServiceFallback.class)
public interface IHelloService {

    @GetMapping("hello")
    String hello(@RequestParam("name") String name);

}

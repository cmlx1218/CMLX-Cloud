package com.cmlx.commons.selector;

import com.cmlx.commons.config.CmlxAuthExceptionConfigure;
import com.cmlx.commons.config.CmlxOAuth2FeignConfigure;
import com.cmlx.commons.config.CmlxServerProtectConfigure;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @Author CMLX
 * @Date -> 2021/10/12 15:00
 * @Desc -> selectImports方法，我们一次性导入了FebsAuthExceptionConfigure、FebsOAuth2FeignConfigure和FebsServerProtectConfigure这三个配置类
 **/
public class CmlxCloudApplicationSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[]{
                CmlxAuthExceptionConfigure.class.getName(),
                CmlxOAuth2FeignConfigure.class.getName(),
                CmlxServerProtectConfigure.class.getName()
        };
    }
}

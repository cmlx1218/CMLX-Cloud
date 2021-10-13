package cc.cmlx.system.config;

import com.cmlx.commons.utils.DateUtil;
import com.p6spy.engine.spy.appender.MessageFormattingStrategy;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;

/**
 * @Author CMLX
 * @Date -> 2021/10/12 17:06
 * @Desc -> 通过logMessageFormat指定了自定义打印配置类
 **/
public class P6spySqlFormatConfigure implements MessageFormattingStrategy {

    @Override
    public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql, String url) {
        return StringUtils.isNotBlank(sql) ? DateUtil.formatFullTime(LocalDateTime.now(), DateUtil.FULL_TIME_SPLIT_PATTERN)
                + " | 耗时 " + elapsed + " ms | SQL 语句：" + StringUtils.LF + sql.replaceAll("[\\s]+", StringUtils.SPACE) + ";" : StringUtils.EMPTY;
    }
}
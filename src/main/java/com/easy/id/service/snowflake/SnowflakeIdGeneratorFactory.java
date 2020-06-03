package com.easy.id.service.snowflake;

import com.easy.id.config.Module;
import com.easy.id.service.generator.AbstractIdGeneratorFactory;
import com.easy.id.service.generator.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zhangbingbing
 * @version 1.0.0
 * @createTime 2020年06月03日
 */
@Component
@Module("snowflake.enable")
public class SnowflakeIdGeneratorFactory extends AbstractIdGeneratorFactory {

    @Autowired
    private SnowflakeZKHolder snowflakeZKHolder;
    private volatile IdGenerator snowflakeIdGenerator;

    @Override
    public IdGenerator getIdGenerator(String businessType) {
        if (snowflakeIdGenerator == null) {
            synchronized (this) {
                if (snowflakeIdGenerator == null) {
                    snowflakeIdGenerator = createIdGenerator(businessType);
                }
            }
        }
        return snowflakeIdGenerator;
    }

    @Override
    protected IdGenerator createIdGenerator(String businessType) {
        return new SnowflakeIdGenerator(snowflakeZKHolder.getWorkerID());
    }
}

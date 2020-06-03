package com.easy.id.service.snowflake;

import com.easy.id.config.Module;
import com.easy.id.service.EasyIdService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author zhangbingbing
 * @version 1.0.0
 * @Description 雪花算法实现
 * @createTime 2020年06月01日
 */
@Service
@Module("snowflake.enable")
@Slf4j
public class SnowflakeEasyIdService implements EasyIdService {

    @Autowired
    private SnowflakeIdGeneratorFactory snowflakeIdGeneratorFactory;

    @Override
    public Long getNextId(String businessType) {
        return snowflakeIdGeneratorFactory.getIdGenerator(businessType).nextId();
    }

    @Override
    public Set<Long> getNextIdBatch(String businessType, int batchSize) {
        return snowflakeIdGeneratorFactory.getIdGenerator(businessType).nextIds(batchSize);
    }
}

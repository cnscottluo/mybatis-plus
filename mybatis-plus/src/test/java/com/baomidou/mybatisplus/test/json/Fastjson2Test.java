package com.baomidou.mybatisplus.test.json;

import com.baomidou.mybatisplus.test.BaseDbTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author nieqiurong 2024年3月4日
 */
public class Fastjson2Test extends BaseDbTest<FastJson2EntityMapper> {

    @Override
    protected List<String> tableSql() {
        return Arrays.asList("drop table if exists t_fastjson2_entity", "CREATE TABLE IF NOT EXISTS t_fastjson2_entity (" +
            "id VARCHAR(32) NOT NULL," +
            "name VARCHAR(30) NULL DEFAULT NULL," +
            "card VARCHAR(255) NULL DEFAULT NULL," +
            "attr VARCHAR(255) NULL DEFAULT NULL," +
            "attr2 VARCHAR(255) NULL DEFAULT NULL," +
            "attr3 VARCHAR(255) NULL DEFAULT NULL," +
            "attr4 VARCHAR(255) NULL DEFAULT NULL," +
            "PRIMARY KEY (id))");
    }

    @Test
    void test() {
        doTest(mapper -> {
            var entity = new FastJson2Entity("123", "秋秋", new FastJson2Entity.Card("1", "1111"),
                Arrays.asList(new FastJson2Entity.Attr("age", "18"), new FastJson2Entity.Attr("sex", "女")),
                new HashMap<>(Map.of("test", new FastJson2Entity.Attr("小红", "1"))),
                new HashMap<>(Map.of("name", "1", "test2", "2")),
                new HashMap<>(Map.of("test1", "1", "test2", 2))
            );
            mapper.insert(entity);
            FastJson2Entity jackJsonEntity = mapper.selectById(entity.getId());
            Assertions.assertEquals("秋秋", jackJsonEntity.getName());
            Assertions.assertEquals(2, jackJsonEntity.getAttr().size());
            Assertions.assertNotNull(jackJsonEntity.getCard());
            Assertions.assertEquals(1, jackJsonEntity.getAttr2().size());
            Assertions.assertEquals("小红", jackJsonEntity.getAttr2().get("test").getName());
            Assertions.assertEquals(2, jackJsonEntity.getAttr3().size());
            Assertions.assertEquals("1", jackJsonEntity.getAttr3().get("name"));
            Assertions.assertEquals("2", jackJsonEntity.getAttr3().get("test2"));
            Assertions.assertEquals(2, jackJsonEntity.getAttr4().size());
            Assertions.assertEquals(2, jackJsonEntity.getAttr4().get("test2"));
        });

    }


}

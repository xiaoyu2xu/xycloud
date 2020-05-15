package com.xy.mybatis.plus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xy.mybatis.plus.entity.AvDict;
import com.xy.mybatis.plus.mapper.AvDictMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AvDictTest {
    @Autowired
    private AvDictMapper avDictMapper;

    @Autowired
    private BaseMapper<AvDict> baseMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<AvDict> avDicts = baseMapper.selectList(null);
        Assert.assertEquals(25, avDicts.size());
        avDicts.forEach(System.out::println);
    }

    @Test
    public void selectByPage() {
        QueryWrapper<AvDict> wrapper = new QueryWrapper();
        wrapper.like("av_value", "希%");

        Page<AvDict> page = new Page<>(1,2);

        IPage<AvDict> mapIPage = avDictMapper.selectPage(page, wrapper);

        // IPage<Map<String, Object>> mapIPage = avDictMapper.selectMapsPage(page, wrapper);
        System.out.println("总页数"+mapIPage.getPages());
        System.out.println("总记录数"+mapIPage.getTotal());
        List<AvDict> records = mapIPage.getRecords();
        records.forEach(System.out::println);
    }
}

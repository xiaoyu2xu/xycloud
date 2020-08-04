package com.xy.controller;

import com.xy.bean.Person;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
//Api注解，描述信息 可通过tag进行分类
@Api(value = "HelloController", description = "HelloController")
public class HelloController {
    @PostMapping("/addPerson")
    //方法描述
    @ApiOperation(notes = "添加人员", value = "addPerson")
    public Person addPerson(
            @ApiParam(name = "name", value = "姓名") @RequestParam("name") String name,
            @ApiParam(name = "age", value = "1")  @RequestParam("age") Integer age) {
        Person person = Person.builder().name(name).age(age).build();
        return person;
    }
}

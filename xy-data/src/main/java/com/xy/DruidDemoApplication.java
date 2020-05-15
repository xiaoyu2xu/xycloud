package com.xy;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@SpringBootApplication
@Slf4j
@MapperScan("com.xy.mybatis.plus.mapper")
public class DruidDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DruidDemoApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//		log.info(dataSource.toString());
//	}
}


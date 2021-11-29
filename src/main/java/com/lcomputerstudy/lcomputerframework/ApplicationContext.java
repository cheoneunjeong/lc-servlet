package com.lcomputerstudy.lcomputerframework;

import java.util.HashMap;
import java.util.Map;

import com.lcomputerstudy.demo.dao.UserDao;
import com.lcomputerstudy.demo.dao.UserDaoImpl;
import com.lcomputerstudy.demo.service.UserService;
import com.lcomputerstudy.lcomputerframework.database.DataSource;
import com.lcomputerstudy.lcomputerframework.database.DataSourcePoolJdbc;
import com.lcomputerstudy.lcomputerframework.jdbc.JdbcTemplate;


public class ApplicationContext {
	
	public static Map<String, Object> beans = new HashMap<String, Object>();
	
	private static DataSource dataSource = null;
	private static UserService userService = null;
	private static UserDao userDao = null;
	private static JdbcTemplate jdbcTemplate = null;
	
	public static void init() {
		
		System.out.println("Applicationcontext (컨테이너) 가 Baen을 생성 및 관리");
		
		dataSource = new DataSourcePoolJdbc();
		userService = new UserService();
		userDao = new UserDaoImpl();
		jdbcTemplate = new jdbcTemplate();
		
		jdbcTemplate.setDataSource(dataSource);
		userDao.setJdbcTemplate(jdbcTemplate);
		userService.setUserDao(userDao);
		
		beans.put("userService", userService);
	}
	
	public static Object getBean(String beanName) {
		return beans.get(beanName);
	}


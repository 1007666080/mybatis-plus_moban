package com.x;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.x.generate.beans.Employee;
import com.x.generate.mapper.EmployeeMapper;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;


/**
 * @Author Lenovo
 * @Date 2022-01-22 11:01
 */
public class Testt {
    private ApplicationContext iocContext = new ClassPathXmlApplicationContext("applicationContext.xml");
    private EmployeeMapper employeeMapper =
            iocContext.getBean("employeeMapper", EmployeeMapper.class);

    //测试删除
    @Test
    public void testEntityWrapperDelete() {

        employeeMapper.delete(
                new EntityWrapper<Employee>()
                        .eq("last_name", "Tom")
                        .eq("age", 22)
        );
    }

    @Test
    public void generate() {
///全局配置
        GlobalConfig config = new GlobalConfig();
        config.setActiveRecord(true) //是否支持AR模式
                .setAuthor("NB_PLUS") //作者
                .setOutputDir("D:\\java\\my_plus\\src\\main\\java")
//生成路径
                .setFileOverride(true)//文件覆盖
                .setServiceName("%sService") //设置生成的service接口名首字母是否为I
                .setIdType(IdType.AUTO); //主键策略
        //数据源配置
        DataSourceConfig dsConfig = new DataSourceConfig();
        dsConfig.setDbType(DbType.MYSQL)
                .setUrl("jdbc:mysql://localhost:3306/mp")
                .setDriverName("com.mysql.jdbc.Driver")
                .setUsername("root")
                .setPassword("141222");
//策略配置
        StrategyConfig stConfig = new StrategyConfig();
        stConfig.setCapitalMode(true) // 全局大写命名
                .setDbColumnUnderline(true) //表名 字段名 是否使用下滑 线命名
                .setNaming(NamingStrategy.underline_to_camel) // 数据库表映射到实体的命名策略
                .setInclude("tbl_employee") //生成的表
                .setTablePrefix("tbl_"); // 表前缀
//包名策略
        PackageConfig pkConfig = new PackageConfig();
        pkConfig.setParent("com.x.generate")
                .setController("controller")
                .setMapper("mapper")
                .setEntity("beans")
                .setService("service")
                .setXml("mapper");
        //整合配置
        AutoGenerator ag = new
                AutoGenerator().setGlobalConfig(config)
                .setDataSource(dsConfig)
                .setStrategy(stConfig)
                .setPackageInfo(pkConfig);
        ag.execute();
    }

    @Test
    public void testEnvironment() throws Exception {
        DataSource ds = iocContext.getBean("dataSource", DataSource.class);
        Connection conn = ds.getConnection();
        System.out.println(conn);
    }
}

package com.maxiaobai.mybatisplusgenerator.mysql;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;
import java.util.List;

public class generator {
    public static void main(String[] args) {
        // 1、全局配置
        GlobalConfig globalConfig = new GlobalConfig();//构建全局配置对象
        String projectPath = System.getProperty("user.dir");// 获取当前用户的目录
//        System.out.println("projectPath = " + projectPath);
        globalConfig
                .setOutputDir("E:\\mybatis-plus")// 输出文件路径
                .setAuthor("微信搜一搜：贺贺学编程")// 设置作者名字
                .setOpen(false)// 是否打开资源管理器
                .setFileOverride(true)// 是否覆盖原来生成的
                .setIdType(IdType.NONE)// 主键策略
                .setBaseResultMap(true)// 生成resultMap
                .setBaseColumnList(true)// XML中生成基础列
                .setServiceName("%sService");// 生成的service接口名字首字母是否为I，这样设置就没有I
        System.out.println("-------1-----------");
        System.out.println(globalConfig);
        // 2、数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig(); // 创建数据源配置
        dataSourceConfig
                .setUrl("jdbc:mysql://staging.tidbk8s.tidb.srv:30503/xmstore_fms?characterEncoding=utf8&useSSL=true&serverTimezone=GMT%2B8&rewriteBatchedStatements=true")
                .setDriverName("com.mysql.jdbc.Driver")
                .setUsername("xmstore_fms_wr")
                .setPassword("KeiQqzcnEnBhGcTObmqWWCju")
                .setDbType(DbType.MYSQL);
        System.out.println("-------2-----------");
        System.out.println(dataSourceConfig);
        // 3、包配置
        PackageConfig packageConfig = new PackageConfig();
        packageConfig
                .setParent("com.maxiaobai")
                .setEntity("PO")
                .setController("controller")
                .setService("service")
                .setMapper("mapper");
        System.out.println("-------3-----------");
        System.out.println(packageConfig);
        // 4、策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig
                .setCapitalMode(true)// 开启全局大写命名
                .setInclude("tbl_organization")// 设置要映射的表
//                .setInclude("tbl_organization_extension")
                .setNaming(NamingStrategy.underline_to_camel)// 下划线到驼峰的命名方式
                .setColumnNaming(NamingStrategy.underline_to_camel)// 下划线到驼峰的命名方式
                .setEntityLombokModel(true)// 是否使用lombok
                .setRestControllerStyle(true)// 是否开启rest风格
                .setControllerMappingHyphenStyle(true);// localhost:8080/hello_a_2
        System.out.println("-------4-----------");
        System.out.println(strategyConfig);

        // 5、自定义配置（配置输出xml文件到resources下）
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        List<FileOutConfig> focList = new ArrayList<>();
        String templatePath = "\\templates\\mapper.xml.vm";
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(com.baomidou.mybatisplus.generator.config.po.TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return "E:\\mybatis-plus\\resources\\mapper/"
                        + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);

        // 6、整合配置
        AutoGenerator autoGenerator = new AutoGenerator();// 构建代码生自动成器对象
        System.out.println("开始生成");
        autoGenerator
                .setGlobalConfig(globalConfig)// 将全局配置放到代码生成器对象中
                .setDataSource(dataSourceConfig)// 将数据源配置放到代码生成器对象中
                .setPackageInfo(packageConfig)// 将包配置放到代码生成器对象中
                .setStrategy(strategyConfig)// 将策略配置放到代码生成器对象中
                .setCfg(cfg);// 将自定义配置放到代码生成器对象中
    }
}

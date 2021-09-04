package com.maxiaobai.mybatisplusgenerator.mysql;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CodeGenerator {
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotBlank(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) {
        // 构建代码生自动成器对象
        AutoGenerator mpg = new AutoGenerator();

        //构建全局配置对象
        GlobalConfig gc = new GlobalConfig()
                .setOutputDir(System.getProperty("user.dir") + "/src/main/java") // 输出文件路径
                .setAuthor("maxiaobai") // 设置作者
                .setOpen(false) // 是否打开资源管理器
                .setFileOverride(true)// 是否覆盖原来生成的
                .setIdType(IdType.NONE)// 主键策略
                .setBaseResultMap(true)// 生成resultMap
                .setBaseColumnList(true)// XML中生成基础列
                .setServiceName("OldService");// 生成的service接口名字首字母是否为I，这样设置就没有I
        ;

        DataSourceConfig dsc = new DataSourceConfig()
                .setUrl("jdbc:mysql://staging.tidbk8s.tidb.srv:30503/xmstore_fms?characterEncoding=utf8&useSSL=true&serverTimezone=GMT%2B8&rewriteBatchedStatements=true")
                .setDriverName("com.mysql.cj.jdbc.Driver")
                .setUsername("xmstore_fms_wr")
                .setPassword("KeiQqzcnEnBhGcTObmqWWCju")
                .setDbType(DbType.MYSQL);

        PackageConfig pc = new PackageConfig()
                .setParent("com.maxiaobai")
                .setEntity("PO")
                .setService("service")
                .setMapper("mapper")
                .setModuleName("maxiaobai");

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        List<FileOutConfig> focList = new ArrayList<>();
        String templatePath = "/templates/mapper.xml";
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(com.baomidou.mybatisplus.generator.config.po.TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return "E:\\mybatis-plus\\resources\\mapper/"
                        + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        /*
        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                // 判断自定义文件夹是否需要创建
                checkDir("调用默认方法创建的目录，自定义目录用");
                if (fileType == FileType.MAPPER) {
                    // 已经生成 mapper 文件判断存在，不想重新生成返回 false
                    return !new File(filePath).exists();
                }
                // 允许生成模板文件
                return true;
            }
        });
        */
        cfg.setFileOutConfigList(focList);

        TemplateConfig templateConfig = new TemplateConfig();
        /*templateConfig.setEntity("templates/entity2.java");
                .setService("")
                .setController("");*/

        templateConfig.setXml(null);

        StrategyConfig strategyConfig = new StrategyConfig()
                .setCapitalMode(true)// 开启全局大写命名
                .setNaming(NamingStrategy.underline_to_camel) // 下划线到驼峰的命名方式
                .setColumnNaming(NamingStrategy.underline_to_camel) // 下划线到驼峰的命名方式
                .setEntityLombokModel(true) // 是否使用lombok
                .setRestControllerStyle(true) // 是否开启rest风格
                .setSuperEntityColumns("id")
                .setInclude(scanner("").split(","))
                //设置要映射的表 用英文逗号分开
                .setControllerMappingHyphenStyle(true)
                .setTablePrefix(pc.getModuleName() + "_");
//                .setSuperControllerClass("你自己的父类控制器,没有就不用设置!")
//                .setSuperEntityClass(“你自己的父类实体,没有就不用设置!”)

        mpg.setGlobalConfig(gc);
        mpg.setDataSource(dsc);
        mpg.setPackageInfo(pc);
        mpg.setCfg(cfg);
        mpg.setTemplate(templateConfig);
        mpg.setStrategy(strategyConfig);
//        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();

    }
}

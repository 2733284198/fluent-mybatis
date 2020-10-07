package cn.org.atool.fluent.mybatis.generator.annoatation;

import org.test4j.generator.mybatis.db.DbType;

import java.lang.annotation.*;

import static cn.org.atool.fluent.mybatis.mapper.StrConstant.NOT_DEFINED;

/**
 * 需要Entity类表定义
 *
 * @author wudarui
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Tables {
    /**
     * 数据库类型
     *
     * @return
     */
    DbType dbType() default DbType.MYSQL;

    /**
     * 数据库链接url
     *
     * @return
     */
    String url();

    /**
     * 数据库用户名
     *
     * @return
     */
    String username();

    /**
     * 数据库用户密码
     *
     * @return
     */
    String password();

    /**
     * 生成的代码相对于工程跟目录鹿路径
     *
     * @return
     */
    String srcDir() default "src/main/java";

    /**
     * 生成的entity package路径
     * 默认和生成定义类相同
     *
     * @return
     */
    String entityPack() default "";

    /**
     * 生成 dao interface 和 dao implement的package路径
     * 默认和生成定义类相同
     *
     * @return
     */
    String daoPack() default "";

    /**
     * 是否同时生成test4j测试辅助文件
     *
     * @return
     */
    boolean test4j() default false;

    /**
     * 生成Entity文件时, 需要去除的表前缀
     *
     * @return
     */
    String[] tablePrefix() default {NOT_DEFINED};

    /**
     * 生成Mapper bean时在bean name前缀
     *
     * @return
     */
    String mapperPrefix() default NOT_DEFINED;

    /**
     * 如果需要生成测试辅助文件, 指定测试文件相对于根目录路径
     *
     * @return
     */
    String testDir() default "src/test/java";

    /**
     * 指定数据库表名
     *
     * @return
     */
    Table[] tables();

    /**
     * entity类自定义接口
     *
     * @return
     */
    Class[] entityInterface() default {Object.class};

    /**
     * dao 类自定义接口
     *
     * @return
     */
    Class[] daoInterface() default {Object.class};

    /**
     * 记录创建字段
     *
     * @return
     */
    String gmtCreated() default NOT_DEFINED;

    /**
     * 记录修改字段
     *
     * @return
     */
    String gmtModified() default NOT_DEFINED;

    /**
     * 逻辑删除字段
     *
     * @return
     */
    String logicDeleted() default NOT_DEFINED;
}

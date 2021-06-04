package cn.org.atool.fluent.mybatis.base.crud;

import cn.org.atool.fluent.mybatis.base.IEntity;
import cn.org.atool.fluent.mybatis.base.IRefs;
import cn.org.atool.fluent.mybatis.base.mapper.IRichMapper;
import cn.org.atool.fluent.mybatis.base.mapper.UpdaterExecutor;
import cn.org.atool.fluent.mybatis.segment.WhereBase;
import cn.org.atool.fluent.mybatis.segment.model.WrapperData;

import java.util.function.Function;

import static cn.org.atool.fluent.mybatis.utility.MybatisUtil.assertNotNull;

public interface IUpdate<E extends IEntity> {
    /**
     * 设置更新值
     *
     * @param column 更新字段
     * @param value  更新值
     * @param <U>
     * @return
     */
    default <U extends IUpdate<E>> U updateSet(String column, Object value) {
        this.getWrapperData().updateSet(column, value);
        return (U) this;
    }

    /**
     * 设置limit值
     *
     * @param limit
     * @return
     */
    <U extends IUpdate<E>> U limit(int limit);

    /**
     * 追加在sql语句的末尾
     * !!!慎用!!!
     * 有sql注入风险
     *
     * @param lastSql
     * @return
     */
    <U extends IUpdate<E>> U last(String lastSql);

    /**
     * 返回where
     *
     * @return
     */
    WhereBase where();

    /**
     * 返回查询器或更新器对应的xml数据
     * 系统方法, 请勿调用
     *
     * @return
     */
    WrapperData getWrapperData();

    /**
     * 根据Updater定义执行后续操作
     * 要使用本方法，需要把编译时生成的 Refs 类加入spring bean定义中
     * Refs类的抽象类, 请自定义
     * <pre>
     *
     * @return
     * @Service public class CustomizedRefs extends Refs{
     * // ...
     * }
     * </pre>
     * 否则，请使用 {@link #of(IRichMapper)} 方法
     */
    default UpdaterExecutor to() {
        Class entityClass = ((IBaseUpdate) this).getWrapperData().getEntityClass();
        assertNotNull("entity class", entityClass);
        IRichMapper mapper = IRefs.instance().mapper(entityClass);
        return new UpdaterExecutor(mapper, (IBaseUpdate) this);
    }


    /**
     * 根据Updater定义执行后续操作
     *
     * @param mapper 执行操作的mapper
     * @return
     */
    default UpdaterExecutor of(IRichMapper<E> mapper) {
        return new UpdaterExecutor(mapper, (IBaseUpdate) this);
    }

    /**
     * 执行更新操作
     *
     * @param executor 具体更新操作
     * @return 返回更新的记录数
     * @deprecated replaced by {@link #of(IRichMapper).method(...)}
     */
    @Deprecated
    default int execute(Function<IUpdate<E>, Integer> executor) {
        return executor.apply(this);
    }
}
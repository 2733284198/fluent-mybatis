package cn.org.atool.fluent.form.meta;

import java.util.HashMap;
import java.util.Map;

public interface ClassKit {
    /**
     * FormService实现类后缀名称
     */
    String FormServiceBeanSuffix = "FormService";

    Map<String, Class> CACHED = new HashMap<>(16);

    static Class forName(String className) {
        if (className == null || className.trim().isEmpty()) {
            return null;
        }
        if (CACHED.containsKey(className)) {
            return CACHED.get(className);
        }
        try {
            Class klass = Class.forName(className);
            CACHED.put(className, klass);
            return klass;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("getClass for Name:" + className + " error:" + e.getMessage(), e);
        }
    }
}
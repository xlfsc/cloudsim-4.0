package org.cloudbus.cloudsim.api;

import junit.framework.TestCase;
import org.apache.commons.lang3.StringUtils;
import org.cloudbus.cloudsim.container.core.ContainerVm;
import org.cloudbus.cloudsim.container.core.store.WideColumnTable;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

public class TestGenerateAPI extends TestCase {
    static Class clazz = WideColumnTable.class;

    public void testField() {
        List<Field> fieldList = ClassUtils.getFields(clazz);

        for (Field field : fieldList) {
            String annotation = ClassUtils.getPointAnnotation(clazz, field);
            System.out.println(String.format("%s\t%s\t%s", field.getName(), field.getType().getSimpleName(), annotation));
        }

    }

    public void testMethod() {
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            String annotation = ClassUtils.getPointAnnotation(clazz, method);
            String types = ClassUtils.getParameter(clazz, method);
            System.out.println(String.format("%s\t%s\t%s\t%s", method.getName(), StringUtils.isEmpty(types) ? " " : types, method.getReturnType().getSimpleName(), annotation));
        }
    }

    public void testMethod2() {
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            String annotation = ClassUtils.getPointAnnotation(clazz, method);
            String types = ClassUtils.getParameter(clazz, method);
            System.out.println(method.getName());
            System.out.println(String.format("%s\t%s\t%s", StringUtils.isEmpty(types) ? " " : types, method.getReturnType().getSimpleName(), annotation));
        }
    }

}

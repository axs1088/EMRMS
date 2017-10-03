package edu.psu.sweng500.emrms.testdrivers;

import edu.psu.sweng500.emrms.util.TestingUtilities;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;

public class GenericModelTester {
    public static void testAllGettersAndSetters(Class plainOldDataClass)
            throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, ClassNotFoundException {
        Object objectInstance = plainOldDataClass.newInstance();

        for (Field field : plainOldDataClass.getDeclaredFields()) {
            String fieldName = field.getName();
            fieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

            Method getterMethod = plainOldDataClass.getMethod("get" + fieldName, new Class[]{});
            Class<?> returnType = getterMethod.getReturnType();
            Method setterMethod = plainOldDataClass.getMethod("set" + fieldName, new Class[]{returnType});

            Object randomObjectFromClass = TestingUtilities.createRandomObjectFromClass(returnType);
            setterMethod.invoke(objectInstance, randomObjectFromClass);
            Object getterResult = getterMethod.invoke(objectInstance, new Class[]{});

            assertEquals(randomObjectFromClass, getterResult);
        }
    }
}

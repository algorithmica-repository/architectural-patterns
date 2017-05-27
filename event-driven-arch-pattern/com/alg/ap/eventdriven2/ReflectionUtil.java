package com.alg.ap.eventdriven2;


import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * A reflection utility class to extract information about subscriber methods
 * from a registering listener object.
 */
class ReflectionUtil {
    /**
     * Finds all subscriber methods in the whole class hierarchy of {@code subscribedClass}.
     *
     * */
    static List<ListenerMethod> findSubscribedMethods(Class<?> subscribedClass) {
        List<ListenerMethod> listenerMethodList = new ArrayList<ListenerMethod>();
        if (subscribedClass != null) {
            Method[] declaredMethods = subscribedClass.getDeclaredMethods();
            for (Method method : declaredMethods) {
                if (method.isAnnotationPresent(Subscribe.class) && !method.isBridge() && !method.isSynthetic()) {
                    if (method.getParameterTypes().length != 1) {
                        System.out.println(method.getName() + " has @Subscribe annotation, " +
                                "but it should have exactly 1 parameter.");
                    }

                    Class<?> parameterType = method.getParameterTypes()[0];
                    if (parameterType.isArray() || method.isVarArgs()) {
                        System.out.println(method.getName() + " has @Subscribe annotation, " +
                                "but its parameter should not be an array or varargs.");
                    }

                    method.setAccessible(true);
                    ListenerMethod listenerMethod = new ListenerMethod(method, method.getParameterTypes()[0]);
                    listenerMethodList.add(listenerMethod);
                }
            }

            if (subscribedClass.getSuperclass() != null && !subscribedClass.getSuperclass().equals(Object.class)) {
                List<ListenerMethod> subscribedMethods = findSubscribedMethods(subscribedClass.getSuperclass());
                listenerMethodList.addAll(subscribedMethods);
            }

            if (subscribedClass.getInterfaces() != null && subscribedClass.getInterfaces().length > 0) {
                for (Class<?> interfaceClass : subscribedClass.getInterfaces()) {
                    List<ListenerMethod> subscribedMethods = findSubscribedMethods(interfaceClass);
                    listenerMethodList.addAll(subscribedMethods);
                }
            }
        }
        return listenerMethodList;
    }
}

package com.alg.ap.eventdriven2;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;


public class ListenerMethod {
    Object target;
    Method method;
    Class<?> eventType;


    ListenerMethod(Method method, Class<?> eventType) {
        this.method = method;
        this.eventType = eventType;
    }

/*    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ListenerMethod other = (ListenerMethod) obj;

          return other.method.getName().equals(method.getName())
                    && other.method.getModifiers() != Modifier.PRIVATE
                    && method.getModifiers() != Modifier.PRIVATE
                    && eventType.equals(other.eventType)
                    && target != null
                    && other.target != null
                    && target.equals(other.target);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(method)
                .append(target)
                .append(eventType)
                .toHashCode();
    	return 10;
    }*/

    @Override
    public String toString() {
        return "[" +
                "method = " +
                method.getName() +
                ", target = " +
                target +
                ", event = " +
                eventType.getName() +
                "]";
    }
}

package com.alg.ap.eventdriven2;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Makes a method eligible to respond to an event.
 *
 * It should be applied to a method (with any access modifier) which has
 * only one argument with the type of the event. If it is applied on a method
 * having no parameter or more than one parameters, during registration,an exception will be thrown
 *
 * If a method in an interface or an abstract class is marked with this annotation,
 * all the overridden methods will be eligible for subscription.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Subscribe {

}

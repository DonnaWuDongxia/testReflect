package testReflection;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks a method as being able to be exposed to JavaScript.  This is used for
 * safety purposes so that only explicitly marked methods get exposed instead
 * of every method in a class.
 * See the explanation for {@link XWalkView#addJavascriptInterface(Object, String)}
 * about the usage.
 */
@SuppressWarnings("javadoc")
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface JsAPI {
  /*  
   * 4 types: method, setter, getter, constructor
   */
  public enum TargetType {
    JS_METHOD,
    JS_SETTER,
    JS_GETTER,
    JS_CONSTRUCTOR
  }
  public TargetType type() default TargetType.JS_METHOD;
}                                                                   
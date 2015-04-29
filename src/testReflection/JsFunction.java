package testReflection;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@SuppressWarnings("javadoc")
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface JsFunction {
	/*
	 * By default, an object will be created and attached to
	 * extension entryPoints, and a @JsFunction element is 
	 * considered as a member method in the new object with
	 * its native name. For example:
	 *   String extensionNamespace = "xwalk.sample.echo";
	 *   @JsFunction
	 *   String echoBack(String msg) {...}
	 * In JavaScript side it will be used as
	 *   xwalk.sample.echo.echoBack("xxx");
	 * 
	 * If isEntryPoint is true, the function
	 * will be exposed at extension entryPoints directly
	 * discarding its native name. For example:
	 *   String extensionNamespace = "xwalk.sample.echo";
	 *   @JsFunction(isEntryPoint = true)
	 *   String echoBack(String msg) {...}
	 * In JavaScript side it will be used as
	 *   xwalk.sample.echo("xxx");
	 */
	/*
	 * NOTICE: One extension just allowed to annotation no more than
	 * ONE element as "isEntryPoint = true".
	 */
	//TODO: make sure no more than one element be annotated as entry point.
	public boolean isEntryPoint() default false;
	public boolean isConstructor() default false;
}
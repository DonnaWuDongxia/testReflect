package testReflection;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

class ReflectionHelper {
	  private Class<?> myClass;
	  public boolean isConstructor = false;
	  private Map<String, MemberInfo> members = new HashMap<String, MemberInfo>();

	  public class MemberInfo {
	    private String name;
	    private Class<?> clazz;
	    private Method getter;
	    private Method setter;

	    MemberInfo() {
	    }   
	  }
	  ReflectionHelper(Class<?> clazz) {
	    myClass = clazz;
	  }

	  Map<String, MemberInfo> getAllMembers() {
	    JsAPI jsAPIs = myClass.getAnnotation(JsAPI.class);                                                                                                                                    
	    Method[] methods = myClass.getDeclaredMethods();
	    for (Method m : methods) {
	      if (m.isAnnotationPresent(JsAPI.class)) {
	    	JsAPI.TargetType type = m.getAnnotation(JsAPI.class).type();
	        switch (type) {
	          case JS_METHOD:
	            break;
	          case JS_SETTER:
	            break;
	          case JS_GETTER:
	            break;
	          case JS_CONSTRUCTOR:
	            break;
	          default:
	            break;

	        }
	      }   
	    }   

	    return members;
	  }

	}

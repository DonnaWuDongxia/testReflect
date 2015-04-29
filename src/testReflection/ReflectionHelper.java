package testReflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

class ReflectionHelper {
	  private Class<?> myClass;
	  public boolean hasEntryPoint = false;
	  private String myNamespace;
	  private Map<String, MemberInfo> members = new HashMap<String, MemberInfo>();
	  
	  /*  
	   * 4 types: method, setter, getter, constructor
	   */
	  public enum MemberType {
	    JS_METHOD,
	    JS_PROPERTY,
	    JS_CONSTRUCTOR
	  }

	  public class MemberInfo {
	    String name;
	    Class<?> clazz;
	    MemberType type;
	    boolean isEntryPoint;
	    boolean isConstructor;
	    boolean isReadOnly;
	    Method getter;
	    Method setter;

	    MemberInfo(Class<?> classObject) {
	    	clazz = classObject;
	    }   
	  }
	  
	  ReflectionHelper(String namespace, Class<?> clazz) {
	    myClass = clazz;
	    myNamespace = namespace;
	    
	    init();
	  }

	  void init() {
		  // Find all functions.
		  Method[] methods = myClass.getDeclaredMethods();
		  for (Method m : methods) {
			  if (m.isAnnotationPresent(JsFunction.class)){
				  MemberInfo mInfo = new MemberInfo(myClass);
				  JsFunction mAnno = m.getAnnotation(JsFunction.class);
				  mInfo.isEntryPoint = mAnno.isEntryPoint();
				  mInfo.isConstructor = mAnno.isConstructor();
				  mInfo.name = mInfo.isEntryPoint ? myNamespace : myNamespace + "." + m.getName();
				  if (members.containsKey(mInfo.name)) {
					  //TODO: LOG out the confliction
					  System.out.println("Warning: conflict namespace - " + mInfo.name);
					  continue;
				  }
				  mInfo.type = mInfo.isConstructor ? MemberType.JS_CONSTRUCTOR : MemberType.JS_METHOD;
				  //TODO: keep the real value?
				  if (mInfo.isEntryPoint){
					  hasEntryPoint = true;
				  }
				  members.put(mInfo.name, mInfo);
			  }
		  }
		  
		  // Find all properties
		  Field[] fields = myClass.getDeclaredFields();
		  for (Field f : fields) {
			  if (f.isAnnotationPresent(JsProperty.class)){
				  MemberInfo mInfo = new MemberInfo(myClass);
				  JsProperty mAnno = f.getAnnotation(JsProperty.class);
				  mInfo.isEntryPoint = mAnno.isEntryPoint();
				  mInfo.isReadOnly = mAnno.isReadOnly();
				  mInfo.name = mInfo.isEntryPoint ? myNamespace : myNamespace + "." + f.getName();
				  if (members.containsKey(mInfo.name)) {
					  //TODO: LOG out the confliction
					  System.out.println("Warning: conflict namespace - " + mInfo.name);
					  continue;
				  }
				  mInfo.type = MemberType.JS_PROPERTY;
				  
				  if (mInfo.isEntryPoint){
					  //TODO: convert the basic type to relative object type?
					  hasEntryPoint = true;
				  }
				  members.put(mInfo.name, mInfo);
			  }
		  }
	  }
	  
	  Map<String, MemberInfo> getMembers() {
		  return members;
	  }
	  
	  Map<String, MemberInfo> getMethods() {
		  return members;
	  }
	  
	  Map<String, MemberInfo> getProperties() {
		  return members;
	  }
	  
	  Map<String, MemberInfo> getConstructors() {
		  return members;
	  }
	  
	  MemberInfo getEntryPoint() {
		  return members.get(myNamespace);
	  }

	}

package testReflection;

import java.lang.reflect.*;
import java.util.HashMap;
import java.util.Map;

class ReflectionHelper {
	  private Class<?> myClass;
	  public MemberInfo entryPoint = null;
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
	    Class<?> clazz;
	    MemberType type;
	    boolean isEntryPoint;
	    boolean isWritable;
	    AccessibleObject accesser;
	    
	    MemberInfo(Class<?> classObject) {
	    	clazz = classObject;
	    }   
	  }
	  
	  ReflectionHelper(Class<?> clazz) {
	    myClass = clazz;
	    
	    init();
	  }
	  
	  void getMemberInfo(AccessibleObject[] accessers, MemberType type) {
		  for (AccessibleObject a : accessers) {
			  
			  if (a.isAnnotationPresent(JsAPI.class)){
				  MemberInfo mInfo = new MemberInfo(myClass);
				  JsAPI mAnno = a.getAnnotation(JsAPI.class);
				  mInfo.isEntryPoint = mAnno.isEntryPoint();
				  mInfo.isWritable = mAnno.isWritable();
				  String name = ((Member) a).getName();
				  mInfo.type = type;
				  
				  if (mInfo.isEntryPoint){
					  if (entryPoint == null) {
						  entryPoint = mInfo;
					  } else {
						  System.out.println("Warning: entry point already exists, conflict - " + name);
					  }
					  continue;
				  }
				  
				  if (members.containsKey(name)) {
					  //TODO: LOG out the confliction
					  System.out.println("Warning: conflict namespace - " + name);
					  continue;
				  }  
				  members.put(name, mInfo);
			  }
		  }
		  
	  }

	  void init() {
		  // Find all functions.
		  getMemberInfo(myClass.getDeclaredMethods(), MemberType.JS_METHOD);
		  
		  // Find all properties
		  getMemberInfo(myClass.getDeclaredFields(), MemberType.JS_PROPERTY);
		  
		  // Find all constructors
		  getMemberInfo(myClass.getDeclaredConstructors(), MemberType.JS_CONSTRUCTOR);
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
		  return entryPoint;
	  }

	}

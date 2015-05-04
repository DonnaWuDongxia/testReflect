package testReflection;

import java.util.Map;

import testReflection.ReflectionHelper.MemberInfo;

public class JsStubGenerator {
	ReflectionHelper reflection;
	String jsHeader = "";
	String jsEnder = "";
	
	JsStubGenerator (String ns, Class<?> clz) {
		reflection = new ReflectionHelper(clz);
	}
	
	String generate(String namespace) {
		String result = "";
		Map<String, MemberInfo> members = reflection.getMembers();
		if (reflection.entryPoint != null) {
			result += generateEntryPoint();
		}
		for (String key : members.keySet()) {
			MemberInfo m = members.get(key);
			switch (m.type) {
			case JS_METHOD:
				result += generateMethod();
			case JS_PROPERTY:
				result += generateProperty();
			case JS_PROPERTY:
				result += generateConstructor();
				
			}
		}
		
		return jsHeader + result + jsEnder;
	}

	String generateMethod() {
		
	}
	
	String generateProperty() {
		
	}
	
	String generateConstructor() {
		
	}
}

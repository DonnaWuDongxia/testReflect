package testReflection;

public class ExtensionTest {
	private String name;
	ExtensionTest(String pname) {
		name = pname;
	}
	
	@JsAPI(type=JsAPI.TargetType.JS_METHOD)
	void printName() {
		System.out.println(name);
	}
	
	//@JsAPI(type=JsAPI.TargetType.JS_SETTER)
	//@JsAPI(type=JsAPI.TargetType.JS_GETTER)
	//@JsAPI(type=JsAPI.TargetType.JS_CONSTRUCTOR)

}

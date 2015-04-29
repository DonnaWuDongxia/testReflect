package testReflection;

public class ExtensionTest {
	@JsProperty(isReadOnly = true)
	private String name;
	
	@JsProperty
	private Home home;
	class Home {
		String address;
		String postID;
		Home(String addr, String pID){
			address = addr;
			postID = pID;
		}
	}
	//@JsFunction(isConstructor = true)
	ExtensionTest(String pname) {
		name = pname;
	}
	
	@JsFunction
	void printName() {
		System.out.println(name);
	}
	
	//@JsFunction(isEntryPoint = true)
	//@JsFunction(isConstructor = true)
	//@JsProperty(isEntryPoint = true)

}

package testReflection;

public class ExtensionTest {
	@JsAPI(isWritable = true)
	private String name;
	
	@JsAPI
	private Home home;
	class Home {
		String address;
		String postID;
		Home(String addr, String pID){
			address = addr;
			postID = pID;
		}
	}
	@JsAPI(isEntryPoint = true)
	ExtensionTest(String pname) {
		name = pname;
	}
	
	@JsAPI
	String getName() {
		return name;
	}
	
	@JsAPI(returnPromise = true)
	void getNameAsync(int callId) {
		System.out.println(name);
	}

}

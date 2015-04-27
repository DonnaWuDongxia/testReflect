package testReflection;

public class testReflection {
	
	static class Tool {
		String _owner = "wdx";
		Tool(String owner) {
			_owner = owner;
		}
		
		void test() {
			Class<?> clazz = this.getClass();
			System.out.println(clazz.equals(Tool.class));
			System.out.println(clazz.equals(Car.class));
			try {
				System.out.println(clazz.getName());
				System.out.println(clazz.getDeclaredField("subOwner"));
			} catch (NoSuchFieldException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		
	}
	
	static class Car extends Tool {
		String subOwner;
		Car(String owner) {
			super(owner);
		}
	}
	public static void main(String[] args) {
		Car c1 = new testReflection.Car("tzz");
		c1.test();
	}
}

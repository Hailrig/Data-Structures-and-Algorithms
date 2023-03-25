
public class MothMan implements Person {
	
	private String name;
	private int age;
	
	public MothMan(String name, int age) {
		this.name = name;
		this.age = age;
	}

	@Override
	public boolean equalTo(Person other) {
		if (this.name == other.getName() && this.age == other.getAge()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getAge() {
		return age;
	}
	
	public void bigFlaps() {
		System.out.println("FLAP FLAP I AM THE MOTHMAN");
	}

}

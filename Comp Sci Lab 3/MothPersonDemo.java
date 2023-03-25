
public class MothPersonDemo {
	 public static void main(String[] args) {
		MothMan mothy = new MothMan("Mothy McFlapperson", 5);
		MothMan mothington = new MothMan("Mothington the First", 7);
		
		System.out.println("Person 1 is " + mothy.getName() + " and they are " + mothy.getAge() + " years old.");
		System.out.println("Person 2 is " + mothington.getName() + " and they are " + mothington.getAge() + " years old.");
		
		System.out.println("Are they the same: " + mothy.equalTo(mothington) + ".");
		
		System.out.println("Watch them flap!");
		mothy.bigFlaps();
		mothington.bigFlaps();
	}
}

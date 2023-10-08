package co.bk.javaskills.basics.queues;

public class StackArrayTest {

	public static void main(String[] args) {
		StackArray sa = new StackArray();
		sa.push("heh1 ");
		sa.push("heh2 ");
		System.out.println("popped item=" + sa.pop());
		sa.push("my1 ");
		sa.push("my2 ");
		System.out.println("popped item=" + sa.pop());
	}

}

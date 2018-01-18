import java.util.Scanner;

public class UserInput {
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		System.out.println("Input Text");
		int n = reader.nextInt();
		System.out.print(n);
		reader.close();
	}
}

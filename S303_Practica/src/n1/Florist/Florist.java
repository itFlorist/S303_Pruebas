package n1.Florist;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Florist implements Serializable {

	public static int id;
	private int floristId;
	private String floristName;
	private ArrayList floristerias;

	public Florist(int floristId, String floristName) {
		super();
		this.floristId = Florist.id++;
		this.floristName = floristName;
		this.floristerias = new ArrayList();
	}

	public int getFloristId() {
		return floristId;
	}

	public void setFloristId(int floristId) {
		this.floristId = floristId;
	}

	public String getFloristName() {
		return floristName;
	}

	public void setFloristName(String floristName) {
		this.floristName = floristName;
	}

	public ArrayList getFloristerias() {
		return floristerias;
	}

	public void setFloristerias(ArrayList floristerias) {
		this.floristerias = floristerias;
	}

	void run() throws ClassNotFoundException, FileNotFoundException, IOException {

		boolean exit = false;

		File file = new File("florist.txt");
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;

		if (file.isFile()) {
			ois = new ObjectInputStream(new FileInputStream(file));
			floristerias = (ArrayList<Florist>) ois.readObject();
			ois.close();
		}

		do {
			switch (menu()) {
			case 1:
				addFlorist(floristerias);
				break;
			case 2:
				deleteFlorist(floristerias);
				break;
			case 3:
				showFlorist(floristerias);
				break;
			case 0:
				System.out.println("Bye bye");
				exit = true;
				break;
			}
		} while (!exit);
	}

	public static byte menu() {
		Scanner sc = new Scanner(System.in);
		byte choice;
		final byte MIN = 0;
		final byte MAX = 3;

		do {
			System.out.println("\nMAIN MENU");
			System.out.println("1. Add Florist.");
			System.out.println("2. Delete Florist.");
			System.out.println("3. Show Florist.");
			System.out.println("0. Exit.\n");
			choice = sc.nextByte();
			if (choice < MIN || choice > MAX) {
				System.out.println("Invalid choice");
			}
		} while (choice < MIN || choice > MAX);
		return choice;
	}

	public static void addFlorist(ArrayList<Florist> floristerias)
			throws ClassNotFoundException, FileNotFoundException, IOException {

		Scanner sc = new Scanner(System.in);
		int floristId = 0;
		String floristName;
		File file = new File("florist.txt");
		ObjectOutputStream oos = null;

		System.out.println("Type the name of the florist");
		floristName = sc.nextLine().toUpperCase();

		Florist florist = new Florist(floristId, floristName);
		floristerias.add(florist);

		oos = new ObjectOutputStream(new FileOutputStream(file));
		oos.writeObject(floristerias);
		oos.close();

		System.out.println(florist.toString());
		System.out.println("Florist " + floristName + " added to application");
	}

	public static void deleteFlorist(ArrayList<Florist> floristerias) throws IOException {

		Scanner sc = new Scanner(System.in);

		String floristName;
		File file = new File("florist.txt");
		ObjectOutputStream oos = null;
		boolean found = false;

		System.out.println("Type the name of the florist to be deleted");
		floristName = sc.nextLine().toUpperCase();

		for (int i = 0; i < floristerias.size(); i++) {

			if (floristName.equals(floristerias.get(i).getFloristName()) && found != true) {
				floristerias.remove(i);

				oos = new ObjectOutputStream(new FileOutputStream(file));
				oos.writeObject(floristerias);
				oos.close();
				System.out.println("Florist " + floristName + " deleted from application");
				found = true;
			}
		}
		if (found != true) {
			System.out.println("The Florist " + floristName + " does not exist in the application.");
		}
	}

	public static void showFlorist(ArrayList<Florist> floristerias) {

		if (floristerias.isEmpty()) {
			System.out.println("There is no Florists in the application");
		} else {
			for (Florist florist : floristerias) {
				System.out.println(florist);
			}
		}
	}

	@Override
	public String toString() {
		return "Florist [floristId=" + floristId + ", floristName=" + floristName + "]";
	}
}

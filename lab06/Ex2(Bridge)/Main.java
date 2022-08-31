import java.io.File;

public class Main {
	public static void main(String[] args) {
		
		ContactsImp contacts = new ContactsImp();

		File file1 = new File("list1.txt");
		File file2 = new File("list2.bin");

		ContactsStorageImp storageImp2 = new ContactsStorageImp(file2);
		contacts.saveAndClose(storageImp2);
		contacts.openAndLoad(storageImp2);

		ContactsStorageImp storageImp1 = new ContactsStorageImp(file1);
		contacts.openAndLoad(storageImp1);

		Contact c1 = new Contact("Guilherme Antunes", 987654321);
		Contact c2 = new Contact("Danilo", 123123123);

		assert contacts.exist(c1) == true;
		assert contacts.exist(c2) == false;

		Contact c3 = contacts.getByName("Guilherme Antunes");
		assert c3.equals(c1) == true;

		contacts.add(c2);
		assert contacts.exist(c2) == true;

		contacts.saveAndClose();

		contacts.remove(c2);
		assert contacts.exist(c2) == false;

	}
}

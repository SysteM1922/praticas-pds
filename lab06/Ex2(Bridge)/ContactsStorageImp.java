import java.io.File;
import java.io.IOException;
import java.util.List;

public class ContactsStorageImp implements ContactsStorageInterface {

	private File file;
	private String extension;

	public ContactsStorageImp(File file) {
		this.file = file;
		this.extension = file.toString().split("\\.")[1];
	}

	@Override
	public List<Contact> loadContacts() {
		ContactsStorageInterface contactStorage = null;
		try {
			switch (extension) {
				case "txt":
					contactStorage = new ContactsStorageTxt(file);
					break;
				case "bin":
					contactStorage = new ContactsStorageBin(file);
					break;
				default:
					System.out.println("Formato não suportado");
					break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return contactStorage.loadContacts();
	}

	@Override
	public boolean saveContacts(List<Contact> list) {
		ContactsStorageInterface contactStorage = null;
		try {
			switch (extension) {
				case "txt":
					contactStorage = new ContactsStorageTxt(file);
					break;
				case "bin":
					contactStorage = new ContactsStorageBin(file);
					break;
				default:
					System.out.println("Formato não suportado");
					break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return contactStorage.saveContacts(list);
	}

}

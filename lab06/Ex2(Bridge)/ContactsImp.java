import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ContactsImp implements ContactsInterface {
	
	private List<Contact> list;

	public ContactsImp() {
		this.list = new ArrayList<>();
	}

	@Override
	public void openAndLoad(ContactsStorageInterface store) {
		this.list.addAll(store.loadContacts());
	}

	@Override
	public void saveAndClose() {
		if (list.size() != 0) {
			Scanner sc = new Scanner(System.in);
			System.out.print("Guardar como: ");
			File file = new File(sc.nextLine());
			sc.close();
			ContactsStorageInterface storage = new ContactsStorageImp(file);
			storage.saveContacts(list);
		}
		else {
			System.out.println("Lista vazia, impossível guardar!");
		}
	}

	@Override
	public void saveAndClose(ContactsStorageInterface store) {
		if(list.size()!=0)
			store.saveContacts(list);
	}

	@Override
	public boolean exist(Contact contact) {
		if (list.contains(contact))
			return true;
		return false;
	}

	@Override
	public Contact getByName(String name) {
		for (Contact c : list) {
			if(c.getNome().equals(name))
				return c;
		}
		return null;
	}

	@Override
	public boolean add(Contact contact) {
		if (exist(contact))
			return false;
		list.add(contact);
		return true;
	}

	@Override
	public boolean remove(Contact contact) {
		if (!exist(contact))
			return false;
		list.remove(contact);
		return true;
	}
	
}

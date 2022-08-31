import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ContactsStorageTxt extends ContactsStorageImp{
	
	private File file;

	public ContactsStorageTxt(File file) throws IOException {
		super(file);
		this.file = file;
	}
	
	public List<Contact> loadContacts() {
		ArrayList<Contact> list = new ArrayList<>();
		try {
			Scanner sc = new Scanner(file);
			String line;
			while (sc.hasNextLine()) {
				line=sc.nextLine();
				if (line.matches("\\D+\t\\d+")) {
					String[] data = line.split("\t");
					list.add(new Contact(data[0], Integer.parseInt(data[1])));
				}
			}
			sc.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return list;
	}

	public boolean saveContacts(List<Contact> list) {
		try{
			PrintWriter out = new PrintWriter(file);
			for(Contact c: list)
				out.println(c.toString());
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
	
}

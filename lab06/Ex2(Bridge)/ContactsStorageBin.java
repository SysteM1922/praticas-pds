import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class ContactsStorageBin extends ContactsStorageImp{
	
	private File file;

	public ContactsStorageBin(File file) throws IOException{
		super(file);
		this.file = file;
	}

	public List<Contact> loadContacts() {
		ArrayList<Contact> list = new ArrayList<>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			while ((line = reader.readLine()) != null) {
				if (line.matches("\\D+\t\\d+")) {
					String[] data = line.split("\t");
					list.add(new Contact(data[0], Integer.parseInt(data[1])));
				}
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return list;
	}
	
	public boolean saveContacts(List<Contact> list) {
		try{
			FileOutputStream out = new FileOutputStream(file);
			for(Contact c: list){
				byte[] bytes = (c.toString() + "\n").getBytes(StandardCharsets.UTF_8);
				out.write(bytes);
			}
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
	
}

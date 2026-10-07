import java.io.*;
import java.util.ArrayList;

public class ContactManager {

    private static final String FILE_NAME = "contacts.txt";

    private ArrayList<Contact> contacts;

    // Constructor
    public ContactManager() {

        contacts = new ArrayList<>();

        loadContacts();
    }

    // =========================================================
    // GET CONTACTS
    // =========================================================

    public ArrayList<Contact> getContacts() {

        return contacts;
    }


    // =========================================================
    // ADD CONTACT
    // =========================================================

    public boolean addContact(
            String name,
            String phone,
            String email) {

        // Check duplicate phone
        if (phoneExists(phone)) {

            return false;
        }

        Contact contact =
                new Contact(
                        name,
                        phone,
                        email
                );

        contacts.add(contact);

        saveContacts();

        return true;
    }


    // =========================================================
    // DELETE CONTACT
    // =========================================================

    public boolean deleteContact(int index) {

        if (
                index < 0 ||
                index >= contacts.size()
        ) {

            return false;
        }

        contacts.remove(index);

        saveContacts();

        return true;
    }


    // =========================================================
    // UPDATE CONTACT
    // =========================================================

    public boolean updateContact(
            int index,
            String name,
            String phone,
            String email) {

        if (
                index < 0 ||
                index >= contacts.size()
        ) {

            return false;
        }


        // Check if another contact
        // already has this phone number

        for (int i = 0; i < contacts.size(); i++) {

            if (
                    i != index &&
                    contacts.get(i)
                            .getPhone()
                            .equals(phone)
            ) {

                return false;
            }
        }


        Contact contact =
                contacts.get(index);


        contact.setName(name);

        contact.setPhone(phone);

        contact.setEmail(email);


        saveContacts();

        return true;
    }


    // =========================================================
    // SEARCH CONTACTS
    // =========================================================

    public ArrayList<Contact> searchContacts(
            String search) {

        ArrayList<Contact> results =
                new ArrayList<>();


        search =
                search.toLowerCase().trim();


        for (Contact contact : contacts) {

            if (
                    contact.getName()
                            .toLowerCase()
                            .contains(search)
                    ||
                    contact.getPhone()
                            .contains(search)
                    ||
                    contact.getEmail()
                            .toLowerCase()
                            .contains(search)
            ) {

                results.add(contact);
            }
        }


        return results;
    }


    // =========================================================
    // PHONE EXISTS
    // =========================================================

    public boolean phoneExists(String phone) {

        for (Contact contact : contacts) {

            if (
                    contact.getPhone()
                            .equals(phone)
            ) {

                return true;
            }
        }

        return false;
    }


    // =========================================================
    // SAVE CONTACTS
    // =========================================================

    public void saveContacts() {

        try (
                BufferedWriter writer =
                        new BufferedWriter(
                                new FileWriter(
                                        FILE_NAME
                                )
                        )
        ) {

            for (Contact contact : contacts) {

                writer.write(
                        contact.getName()
                        + "|"
                        + contact.getPhone()
                        + "|"
                        + contact.getEmail()
                );

                writer.newLine();
            }

        }

        catch (IOException e) {

            System.out.println(
                    "Error saving contacts: "
                    + e.getMessage()
            );
        }
    }


    // =========================================================
    // LOAD CONTACTS
    // =========================================================

    private void loadContacts() {

        File file =
                new File(FILE_NAME);


        // First run
        if (!file.exists()) {

            return;
        }


        try (
                BufferedReader reader =
                        new BufferedReader(
                                new FileReader(
                                        FILE_NAME
                                )
                        )
        ) {

            String line;


            while (
                    (line = reader.readLine())
                            != null
            ) {

                String[] data =
                        line.split(
                                "\\|"
                        );


                if (data.length == 3) {

                    Contact contact =
                            new Contact(
                                    data[0],
                                    data[1],
                                    data[2]
                            );


                    contacts.add(contact);
                }
            }

        }

        catch (IOException e) {

            System.out.println(
                    "Error loading contacts: "
                    + e.getMessage()
            );
        }
    }
}
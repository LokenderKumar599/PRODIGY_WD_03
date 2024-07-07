package Task3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class ContactManager {
	    private static final String FILE_NAME = "contacts.ser";
	    private ArrayList<Contact> contacts;

	    public ContactManager() {
	        contacts = loadContacts();
	    }

	    public void addContact(Contact contact) {
	        contacts.add(contact);
	        saveContacts();
	    }

	    public void viewContacts() {
	        if (contacts.isEmpty()) {
	            System.out.println("No contacts available.");
	        } else {
	            for (int i = 0; i < contacts.size(); i++) {
	                System.out.println((i + 1) + ". " + contacts.get(i));
	            }
	        }
	    }

	    public void editContact(int index, Contact contact) {
	        if (index >= 0 && index < contacts.size()) {
	            contacts.set(index, contact);
	            saveContacts();
	        } else {
	            System.out.println("Invalid index.");
	        }
	    }

	    public void deleteContact(int index) {
	        if (index >= 0 && index < contacts.size()) {
	            contacts.remove(index);
	            saveContacts();
	        } else {
	            System.out.println("Invalid index.");
	        }
	    }

	    private void saveContacts() {
	        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
	            oos.writeObject(contacts);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    @SuppressWarnings("unchecked")
	    private ArrayList<Contact> loadContacts() {
	        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
	            return (ArrayList<Contact>) ois.readObject();
	        } catch (FileNotFoundException e) {
	            return new ArrayList<>();
	        } catch (IOException | ClassNotFoundException e) {
	            e.printStackTrace();
	            return new ArrayList<>();
	        }
	    }

	    public static void main(String[] args) {
	        ContactManager cm = new ContactManager();
	        Scanner scanner = new Scanner(System.in);

	        while (true) {
	            System.out.println("\n*****Contact Manager*****");
	            System.out.println("#------------------------------#");
	            System.out.println("| Press 1 to Add Contact       |");
	            System.out.println("| Press 2 to View Contacts     |");
	            System.out.println("| Press 3 to Edit Contact      |");
	            System.out.println("| Press 4 to Delete Contact    |");
	            System.out.println("| Press 5 to Exit              |");
	            System.out.println("#------------------------------#");
	            System.out.print("Choose an option: ");
	            int choice = scanner.nextInt();
	            scanner.nextLine();  

	            switch (choice) {
	                case 1:
	                	System.out.println("You Selected to Add Contact...");
	                    System.out.print("Enter name: ");
	                    String name = scanner.nextLine();
	                    System.out.print("Enter phone number: ");
	                    String phoneNumber = scanner.nextLine();
	                    System.out.print("Enter email: ");
	                    String email = scanner.nextLine();
	                    cm.addContact(new Contact(name, phoneNumber, email));
	                    System.out.println("Contact Saved...");
	                    break;
	                case 2:
	                	System.out.println("Your Contact List: ");
	                    cm.viewContacts();
	                    break;
	                case 3:
	                	System.out.println("You Selected to Edit Contact...");
	                	System.out.println("Your Contact List: ");
	                    cm.viewContacts();
	                    System.out.print("Enter the contact number to edit: ");
	                    int editIndex = scanner.nextInt() - 1;
	                    scanner.nextLine();  
	                    System.out.print("Enter new name: ");
	                    name = scanner.nextLine();
	                    System.out.print("Enter new phone number: ");
	                    phoneNumber = scanner.nextLine();
	                    System.out.print("Enter new email: ");
	                    email = scanner.nextLine();
	                    cm.editContact(editIndex, new Contact(name, phoneNumber, email));
	                    break;
	                case 4:
	                	System.out.println("Your Contact List: ");
	                    cm.viewContacts();
	                    System.out.print("Enter the contact number to delete: ");
	                    int deleteIndex = scanner.nextInt() - 1;
	                    scanner.nextLine();  
	                    cm.deleteContact(deleteIndex);
	                    break;
	                case 5:
	                    System.out.println("Exiting...");
	                    scanner.close();
	                    System.exit(0);
	                    break;
	                default:
	                    System.out.println("Invalid option. Please try again.");
	            }
	        }
	    }
	}
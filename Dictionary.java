// Amaan Seraj
// 03/11/2024
// CS & 145
// Lab 6: Binary Search Tree
// This program prints a dictionary

import java.util.Scanner;

// Define a class for the dictionary entry
class DictionaryEntry {
    String firstName;
    String lastName;
    String streetAddress;
    String city;
    String state;
    String zip;
    String email;
    String phoneNumber;
    int primaryKey;

    // Constructor
    public DictionaryEntry(int primaryKey,String firstName,String lastName,String streetAddress, 
                           String city,String state,String zip,String email,String phoneNumber) {
        this.primaryKey = primaryKey;
        this.firstName = firstName;
        this.lastName = lastName;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    // Method to display dictionary entry details
    public void displayEntry() {
        System.out.println("Primary Key: " + primaryKey);
        System.out.println("Name: " + firstName + " " + lastName);
        System.out.println("Address: " + streetAddress + ", " + city + ", " + state + " " + zip);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phoneNumber);
        System.out.println("---------------------");
    }
}

// Define a class for the binary search tree node
class TreeNode {
    DictionaryEntry entry;
    TreeNode left;
    TreeNode right;

    // Constructor
    public TreeNode(DictionaryEntry entry) {
        this.entry = entry;
        left = null;
        right = null;
    }
}

// Define a class for the dictionary
public class Dictionary {
    private TreeNode root;

    // Constructor
    public Dictionary() {
        root = null;
    }

    // Method to add a new entry to the dictionary
    public void addEntry(DictionaryEntry entry) {
        root = insert(root, entry);
        System.out.println("Entry added successfully!");
    }

    // Helper method to recursively insert a new entry into the binary search tree
    private TreeNode insert(TreeNode node, DictionaryEntry entry) {
        if (node == null) {
            return new TreeNode(entry);
        }

        if (entry.primaryKey < node.entry.primaryKey) {
            node.left = insert(node.left, entry);
        } else if (entry.primaryKey > node.entry.primaryKey) {
            node.right = insert(node.right, entry);
        }

        return node;
    }

    // Method to display all entries in the dictionary in a specified order
    public void displayEntries(String order) {
        switch (order.toLowerCase()) {
            case "preorder":
                System.out.println("Preorder traversal:");
                preorderTraversal(root);
                break;
            case "inorder":
                System.out.println("Inorder traversal:");
                inorderTraversal(root);
                break;
            case "postorder":
                System.out.println("Postorder traversal:");
                postorderTraversal(root);
                break;
            default:
                System.out.println("Invalid order specified!");
        }
    }

    // Helper method for preorder traversal
    private void preorderTraversal(TreeNode node) {
        if (node != null) {
            node.entry.displayEntry();
            preorderTraversal(node.left);
            preorderTraversal(node.right);
        }
    }

    // Helper method for inorder traversal
    private void inorderTraversal(TreeNode node) {
        if (node != null) {
            inorderTraversal(node.left);
            node.entry.displayEntry();
            inorderTraversal(node.right);
        }
    }

    // Helper method for postorder traversal
    private void postorderTraversal(TreeNode node) {
        if (node != null) {
            postorderTraversal(node.left);
            postorderTraversal(node.right);
            node.entry.displayEntry();
        }
    }

    // Method to delete an entry from the dictionary
    public void deleteEntry(int primaryKey) {
        root = delete(root, primaryKey);
        System.out.println("Entry with primary key " + primaryKey + " deleted successfully!");
    }

    // Helper method to recursively delete an entry from the binary search tree
    private TreeNode delete(TreeNode node, int primaryKey) {
        if (node == null) {
            return null;
        }

        if (primaryKey < node.entry.primaryKey) {
            node.left = delete(node.left, primaryKey);
        } else if (primaryKey > node.entry.primaryKey) {
            node.right = delete(node.right, primaryKey);
        } else {
            // Node to be deleted found
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }

            // Node has two children
            node.entry = minValueNode(node.right).entry;
            node.right = delete(node.right, node.entry.primaryKey);
        }
        return node;
    }

    // Helper method to find the node with the minimum value in a subtree
    private TreeNode minValueNode(TreeNode node) {
        TreeNode current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    // Method to modify an existing entry in the dictionary
    public void modifyEntry(int primaryKey, String field, String newValue) {
        modify(root, primaryKey, field, newValue);
        System.out.println("Entry with primary key " + primaryKey + " modified successfully!");
    }

    // Helper method to recursively modify an entry in the binary search tree
    private void modify(TreeNode node, int primaryKey, String field, String newValue) {
        if (node == null) {
            return;
        }

        if (primaryKey == node.entry.primaryKey) {
            switch (field.toLowerCase()) {
                case "firstname":
                    node.entry.firstName = newValue;
                    break;
                case "lastname":
                    node.entry.lastName = newValue;
                    break;
                case "streetaddress":
                    node.entry.streetAddress = newValue;
                    break;
                case "city":
                    node.entry.city = newValue;
                    break;
                case "state":
                    node.entry.state = newValue;
                    break;
                case "zip":
                    node.entry.zip = newValue;
                    break;
                case "email":
                    node.entry.email = newValue;
                    break;
                case "phonenumber":
                    node.entry.phoneNumber = newValue;
                    break;
                default:
                    System.out.println("Invalid field specified!");
            }
            return;
        }

        if (primaryKey < node.entry.primaryKey) {
            modify(node.left, primaryKey, field, newValue);
        } else {
            modify(node.right, primaryKey, field, newValue);
        }
    }

    // Main method to run the program
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Dictionary dictionary = new Dictionary();

        System.out.println("Welcome to the Dictionary Program!");

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add Entry");
            System.out.println("2. View Entries");
            System.out.println("3. Delete Entry");
            System.out.println("4. Modify Entry");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Primary Key: ");
                    int primaryKey = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter First Name: ");
                    String firstName = scanner.nextLine();
                    System.out.print("Enter Last Name: ");
                    String lastName = scanner.nextLine();
                    System.out.print("Enter Street Address: ");
                    String streetAddress = scanner.nextLine();
                    System.out.print("Enter City: ");
                    String city = scanner.nextLine();
                    System.out.print("Enter State: ");
                    String state = scanner.nextLine();
                    System.out.print("Enter Zip Code: ");
                    String zip = scanner.nextLine();
                    System.out.print("Enter Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter Phone Number: ");
                    String phoneNumber = scanner.nextLine();

                    dictionary.addEntry(new DictionaryEntry(primaryKey, firstName, lastName, 
                                                            streetAddress, city, state, zip, 
                                                            email, phoneNumber));
                    break;
                case 2:
                    System.out.println("Select traversal order:");
                    System.out.println("1. Preorder");
                    System.out.println("2. Inorder");
                    System.out.println("3. Postorder");
                    System.out.print("Enter your choice: ");
                    int viewChoice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    switch (viewChoice) {
                        case 1:
                            dictionary.displayEntries("preorder");
                            break;
                        case 2:
                            dictionary.displayEntries("inorder");
                            break;
                        case 3:
                            dictionary.displayEntries("postorder");
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }
                    break;
                case 3:
                    System.out.print("Enter Primary Key of Entry to Delete: ");
                    int deleteKey = scanner.nextInt();
                    dictionary.deleteEntry(deleteKey);
                    break;
                case 4:
                    System.out.print("Enter Primary Key of Entry to Modify: ");
                    int modifyKey = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Field to Modify (e.g., FirstName): ");
                    String field = scanner.nextLine();
                    System.out.print("Enter New Value: ");
                    String newValue = scanner.nextLine();
                    dictionary.modifyEntry(modifyKey, field, newValue);
                    break;
                case 5:
                    System.out.println("Exiting program...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

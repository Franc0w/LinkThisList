import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class ShoppingListItem {
    String itemName;
    String itemDescription;
    double itemPrice;
    ShoppingListItem next;

    ShoppingListItem(String itemName, String itemDescription, double itemPrice) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemPrice = itemPrice;
        this.next = null;
    }
}

class ShoppingList {
    ShoppingListItem head;

    void addList(String itemName, String itemDescription, double itemPrice) {
        ShoppingListItem newList = new ShoppingListItem(itemName, itemDescription, itemPrice);
        if (head == null) {
            head = newList;
        } else {
            ShoppingListItem current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newList;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        ShoppingList groceryList = new ShoppingList();
        Scanner scanner = new Scanner(System.in);
        List<ShoppingListItem> cart = new ArrayList<>();

        // Predefined items in categories
        addSnacks(groceryList);
        addFrozenGoods(groceryList);
        addFruits(groceryList);
        addHydration(groceryList);
        addDeliFood(groceryList);

        System.out.println("Welcome to the Grocery Store!");

        while (true) {
            System.out.println("Select which section you would like to shop in:");
            System.out.println("[1] Snacks\n[2] Frozen Goods\n[3] Fruits\n[4] Hydration\n[5] Deli Foods");
            System.out.println("[6] View Cart\n[0] Checkout");
            System.out.print("Enter your choice: ");
            int sectionChoice = scanner.nextInt();

            if (sectionChoice == 0) {
                break;
            } else if (sectionChoice == 6) {
                displayCartWithNumbers(cart, scanner);
            } else if (sectionChoice >= 1 && sectionChoice <= 5) {
                displaySectionItems(groceryList.head, sectionChoice, cart, scanner);
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }

        // Display the receipt
        System.out.println("Receipt:");
        double total = displayCart(cart);
        System.out.println("Total: $" + total);
    }

    // Helper function to display items in a specific section
    private static void displaySectionItems(ShoppingListItem head, int sectionChoice, List<ShoppingListItem> cart, Scanner scanner) {
        ShoppingListItem current = head;
        boolean sectionFound = false;

        while (current != null) {
            if (current.itemDescription.equals("Snacks") && sectionChoice == 1) {
                sectionFound = true;
                displayItemDetails(current, cart, scanner);
            } else if (current.itemDescription.equals("Frozen Goods") && sectionChoice == 2) {
                sectionFound = true;
                displayItemDetails(current, cart, scanner);
            } else if (current.itemDescription.equals("Fruits") && sectionChoice == 3) {
                sectionFound = true;
                displayItemDetails(current, cart, scanner);
            } else if (current.itemDescription.equals("Hydration") && sectionChoice == 4) {
                sectionFound = true;
                displayItemDetails(current, cart, scanner);
            } else if (current.itemDescription.equals("Deli Foods") && sectionChoice == 5) {
                sectionFound = true;
                displayItemDetails(current, cart, scanner);
            }
            current = current.next;
        }

        if (!sectionFound) {
            System.out.println("No items found in this section.");
        }
    }

    private static void displayItemDetails(ShoppingListItem item, List<ShoppingListItem> cart, Scanner scanner) {
        System.out.println("Item: " + item.itemName);
        System.out.println("Description: " + item.itemDescription);
        System.out.println("Price: $" + item.itemPrice);
        System.out.println("==========================");
        System.out.print("Enter 'A' to add to cart, 'B' to go back: ");
        String choice = scanner.next();

        if (choice.equalsIgnoreCase("A")) {
            cart.add(item);
            System.out.println(item.itemName + " added to cart.");
        } else if (choice.equalsIgnoreCase("B")) {
            // User chose to go back
        } else {
            System.out.println("Invalid choice. Please try again.");
        }
    }

    // Helper function to display items in the cart and calculate the total
    private static double displayCart(List<ShoppingListItem> cart) {
        double total = 0;
        System.out.println("Items in Cart:");
        int count = 1;
        for (ShoppingListItem item : cart) {
            System.out.println("[" + count + "] Item: " + item.itemName);
            System.out.println("Price: $" + item.itemPrice);
            System.out.println("==========================");
            total += item.itemPrice;
            count++;
        }
        return total;
    }

    // Predefined items in categories
    private static void addSnacks(ShoppingList groceryList) {
        groceryList.addList("Doritos", "Snacks", 6.00);
        groceryList.addList("Chezzy", "Snacks", 4.00);
        groceryList.addList("Piattos", "Snacks", 5.00);
    }

    private static void addFrozenGoods(ShoppingList groceryList) {
        groceryList.addList("Frozen Pizza", "Frozen Goods", 5.00);
        groceryList.addList("Frozen Vegetables", "Frozen Goods", 2.00);
        groceryList.addList("Frozen Whole Chicken", "Frozen Goods", 5.00);
    }

    private static void addFruits(ShoppingList groceryList) {
        groceryList.addList("Apples", "Fruits", 1.00);
        groceryList.addList("Bananas", "Fruits", 1.00);
        groceryList.addList("Orange", "Fruits", 1.00);
    }

    private static void addHydration(ShoppingList groceryList) {
        groceryList.addList("Bottled Water", "Hydration", 1.00);
        groceryList.addList("Coca-cola", "Hydration", 2.00);
        groceryList.addList("Sprite", "Hydration", 2.00);
    }

    private static void addDeliFood(ShoppingList groceryList) {
        groceryList.addList("Sandwich", "Deli Foods", 4.00);
        groceryList.addList("Salad", "Deli Foods", 4.00);
        groceryList.addList("Beef Pattie", "Deli Foods", 5.00);
    }

    private static void displayCartWithNumbers(List<ShoppingListItem> cart, Scanner scanner) {
        System.out.println("Items in Cart:");
        int count = 1;
        for (ShoppingListItem item : cart) {
            System.out.println("[" + count + "] Item: " + item.itemName);
            System.out.println("Price: $" + item.itemPrice);
            System.out.println("==========================");
            count++;
        }

        System.out.print("Enter the item number to remove from the cart, or '0' to go back: ");
        int choice = scanner.nextInt();

        if (choice == 0) {
            // User chose to go back
        } else if (choice >= 1 && choice <= cart.size()) {
            ShoppingListItem removedItem = cart.remove(choice - 1);
            System.out.println(removedItem.itemName + " removed from the cart.");
        } else {
            System.out.println("Invalid choice. Please try again.");
        }
    }
}

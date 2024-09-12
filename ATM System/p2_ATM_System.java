import java.util.*;

public class p2_ATM_System {
    public static void main(String[] args) {
        ATMApp atmApp = new ATMApp();
        atmApp.start();
    }
}
class Transaction {
    String type;
    double amount;
    Date date;
    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
        this.date = new Date();
    }
    @Override
    public String toString() {
        return date + " | " + type + " | $" + amount;
    }
}
class Account {
    private double balance;
    private final List<Transaction> transactions;
    public Account() {
        this.balance = 500.0;
        this.transactions = new ArrayList<>();
    }
    public void deposit(double amount) {
        balance += amount;
        transactions.add(new Transaction("Deposit", amount));
        Centre.center("Successfully deposited $" + amount);
        delay();
    }
    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            transactions.add(new Transaction("Withdrawal", amount));
            Centre.center("Successfully withdrew $" + amount);
        } else
            Centre.center("Insufficient balance.");
        delay();
    }
    public void transfer(Account targetAccount, double amount) {
        if (amount <= balance) {
            balance -= amount;
            targetAccount.deposit(amount);
            transactions.add(new Transaction("Transfer to account", amount));
            Centre.center("Successfully transferred $" + amount);
        } else
            Centre.center("Insufficient balance.");
    }
    public void showTransactionHistory() {
        Centre.center("Transaction History:");
        if(transactions.isEmpty()) {
            Centre.center("No transactions found.");
        }
        else
            for (Transaction transaction : transactions)
                System.out.println(transaction);
        delay();
    }
    public double getBalance() {
        return balance;
    }
    private void delay(){
        for(double i = 0; i < 99999999; i+=0.2);
    }
}
class ATM {
    private final Map<String, String> userDatabase;
    private final Map<String, Account> accounts;
    public ATM() {
        userDatabase = new HashMap<>();
        accounts = new HashMap<>();
    }
    public void createAccount(String username, String password) {
        Account account = new Account();
        if (!(userDatabase.containsKey(username))){
            userDatabase.put(username, password);
            accounts.put(username, new Account());
        }
    }
    public void admin(){
        if (!userDatabase.isEmpty()) {
            Centre.center("");
            Centre.center("Accounts: ");
            Centre.center("             ID     Amt    Pass");
            for (String account : accounts.keySet())
                Centre.center("Account ID: " + account + " : " + accounts.get(account).getBalance() + " : " + userDatabase.get(account));
        }
        else
            Centre.center("No accounts found.");
        delay();
    }
    public boolean authenticate(String userId, String pin) {
        return userDatabase.containsKey(userId) && userDatabase.get(userId).equals(pin);
    }
    public Account getAccount(String userId) {
        return accounts.get(userId);
    }
    private void delay(){
        for(double i = 0; i < 99999999; i+=0.2);
    }
}
class ATMApp {
    private final Scanner scanner;
    private final ATM atm;
    private  boolean bol = false;
    private Account currentAccount;
    private String currentUserId;
    public ATMApp() {
        scanner = new Scanner(System.in);
        atm = new ATM();
    }
    public void start() {
        while (true){
            Centre.box();
            Centre.center("Welcome to the ATM!");
            Centre.center("Press 'ENTER' to continue.");
            Centre.box();
            while (!bol) {
                scanner.nextLine();
                Centre.center("Admin login or Have an Account or Create an Account or Quit ? (A/L/C/Q)", 1);
                String acc = scanner.nextLine().toUpperCase();
                switch (acc){
                    case "L":
                        authenticateUser();
                        break;
                    case "C":
                        createUser();
                        break;
                    case "Q":
                        Centre.center("THANK YOU!");
                        Centre.center("Hope our service met your expectations.");
                        Centre.center("See you later, BYE!!!");
                        Centre.box();
                        return;
                    case "A":
                        admin();
                        break;
                    default:
                        Centre.center("Invalid input! Please try again.");
                }
            }
            while (bol) {
                showMenu();
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        currentAccount.showTransactionHistory();
                        break;
                    case 2:
                        handleWithdraw();
                        break;
                    case 3:
                        handleDeposit();
                        break;
                    case 4:
                        handleTransfer();
                        break;
                    case 5:
                        showDetails();
                        break;
                    case 6:
                        Centre.center("Thank you for using the ATM. Have a nice day!");
                        bol = false;
                        break;
                    default:
                        Centre.center("Invalid choice. Please try again.");
                }
            }
        }
    }
    private void authenticateUser() {
        Centre.center("Note: Enter User ID and PIN exactly equal to length of 4!!");
        Centre.center("");
        Centre.center("Enter User ID: ", 1);
        String userId = scanner.next();
        Centre.center("Enter PIN: ", 1);
        String pin = scanner.next();
        if (atm.authenticate(userId, pin) && userId.length() == 4 && pin.length() == 4) {
            currentUserId = userId;
            currentAccount = atm.getAccount(userId);
            Centre.center("Authentication successful!");
            bol = true;
        } else
            Centre.center("Invalid User ID or PIN. Please try again.");
    }
    private void createUser() {
        Centre.center("Note: Enter User ID and PIN exactly equal to length of 4!!");
        Centre.center("");
        Centre.center("Enter User ID: ", 1);
        String userId = scanner.next();
        Centre.center("Enter PIN: ", 1);
        String pin = scanner.next();
        if(userId.length() == 4 && pin.length() == 4) {
            if (!(atm.authenticate(userId, pin))) {
                atm.createAccount(userId, pin);
                currentUserId = userId;
                currentAccount = atm.getAccount(userId);
                Centre.center("Congratulations!! Your account has been created and a bonus of 500.Rs has been credited to your account.");
                delay();
                bol = true;
            } else {
                Centre.center("Account already exists.");
            }
        }
        else
            Centre.center("Invalid User ID or PIN. Please try again.");
    }
    private void admin(){
        Centre.center("Enter Admin ID: ", 1);
        String userId = scanner.next();
        Centre.center("Enter PIN: ", 1);
        String pin = scanner.next();
        if(userId.equals("admin123") && pin.equals("admin@123"))
            atm.admin();
    }
    private void showMenu() {
        Centre.center("");
        Centre.center("ATM Menu:");
        System.out.println(" ".repeat(Math.max(0, 30))+"1. Transaction History");
        System.out.println(" ".repeat(Math.max(0, 30))+"2. Withdraw");
        System.out.println(" ".repeat(Math.max(0, 30))+"3. Deposit");
        System.out.println(" ".repeat(Math.max(0, 30))+"4. Transfer");
        System.out.println(" ".repeat(Math.max(0, 30))+"5. Account Details");
        System.out.println(" ".repeat(Math.max(0, 30))+"6. Logout");
        Centre.center("Choose an option: ", 1);
    }
    private void handleWithdraw() {
        Centre.center("Enter amount to withdraw: $", 1);
        double amount = scanner.nextDouble();
        currentAccount.withdraw(amount);
    }
    private void handleDeposit() {
        Centre.center("Enter amount to deposit: $", 1);
        double amount = scanner.nextDouble();
        currentAccount.deposit(amount);
    }
    private void handleTransfer() {
        System.out.print("Enter target User ID: ");
        String targetUserId = scanner.next();
        System.out.print("Enter amount to transfer: $");
        double amount = scanner.nextDouble();
        Account targetAccount = atm.getAccount(targetUserId);
        if (targetAccount != null) {
            currentAccount.transfer(targetAccount, amount);
        } else {
            System.out.println("Target User ID not found.");
        }
    }
    private void showDetails() {
        Centre.center("Balance: " + currentAccount.getBalance());
        delay();
    }
    private void delay(){
        for(double i = 0; i < 99999999; i+=0.2);
    }
}
class Centre {
    static int total = 100;
    public static void center(String str) {
        center(str, 0);
    }
    public static void center(String str, int type) {
        int space = (total - str.length()) / 2, c = 0;
        StringBuilder cenText = new StringBuilder();
        cenText.append(" ".repeat(Math.max(0, space)));
        cenText.append(str);
        if(type == 1) System.out.print(cenText.toString());
        else System.out.println(cenText.toString());
    }
    public static void box() {
        System.out.println("-".repeat(Math.max(0, total)));
    }
}
package ir.hossein.util;

import java.awt.print.Book;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import ir.hossein.domain.*;
import ir.hossein.repository.*;

public class Menu {
    static Boolean menuFlag = false;
    static Integer tempUserId;
    static Scanner intScanner = new Scanner(System.in);
    static Scanner strScanner = new Scanner(System.in);
    BookRepository bookRepository = new BookRepository();
    UserRepository userRepository = new UserRepository();
    AdminRepository adminRepository = new AdminRepository();
    OrderRepository orderRepository = new OrderRepository();
    TariffRepository tariffRepository = new TariffRepository();


    public void login() throws SQLException {
//        while (!loginFlag){
//
//        }

        System.out.println("1)login as Admin\n2)login as user\n3)exit");
        Integer loginChoice= intScanner.nextInt();
        switch (loginChoice){
            case 1:
                System.out.println("enter username : ");
                String enteredAdminUsername = strScanner.nextLine();
                System.out.println("enter password : ");
                String enteredAdminPassword = strScanner.nextLine();
                Admin admin = adminRepository.findByUsernameAndPassword(enteredAdminUsername, enteredAdminPassword);
                if (admin != null && Objects.equals(admin.getUserName(), enteredAdminUsername) &&
                        Objects.equals(admin.getPassword(), enteredAdminPassword)) {
                    System.out.println("login succeed");
//                    loginFlag = true;
                    showAdminMenu();
                }else {
                    System.out.println("userName or password is wrong\n try again ");
                    login();
                }
                break;
            case 2:
                System.out.println("enter username : ");
                String enteredUsername = strScanner.nextLine();
                System.out.println("enter password : ");
                String enteredPassword = strScanner.nextLine();
                User user = userRepository.findByUsernameAndPassword(enteredUsername, enteredPassword);
                if (user != null && Objects.equals(user.getUserName(), enteredUsername) &&
                        Objects.equals(user.getPassword(), enteredPassword)) {
                    System.out.println("login succeed");
                    tempUserId=user.getId();
                    showUserMenu();
                }else {
                    System.out.println("userName or password is wrong\n try again ");
                    login();
                }
                break;
            case 3:
//                loginFlag = true;
                System.out.println("have a good day");
                break;
            default:
                System.out.println("this is wrong choice ! \n Please enter a correct number");
        }
    }

    public void addBook() throws SQLException {
        System.out.println("enter name of new book : ");
        String enteredBookName = strScanner.nextLine();
        System.out.println("enter name of author_book : ");
        String enteredAuthorName = strScanner.nextLine();
        Books book = bookRepository.createBook(enteredBookName,enteredAuthorName);
        System.out.println("book create successfully\n\n");
    }

    public void showAllBooks() throws SQLException {
        List<Books> allBooks = bookRepository.findAllBooks();
        allBooks.forEach(System.out::println);
        allBooks.clear();
        System.out.println("\n\n");
    }

    private void showAllMyBooks() throws SQLException {
        List<Books> allMyBooks = bookRepository.findAllMyBooks(tempUserId);
        allMyBooks.forEach(System.out::println);
        allMyBooks.clear();
        System.out.println("\n\n");
    }

    public void addUser() throws SQLException {
        System.out.println("enter your firstName : ");
        String enteredFirstName = strScanner.nextLine();
        System.out.println("enter your surName : ");
        String enteredSurName = strScanner.nextLine();
        System.out.println("enter your userName : ");
        String enteredUsername = strScanner.nextLine();
        System.out.println("enter your password : ");
        String enteredPassword = strScanner.nextLine();
        User user = userRepository.createUser(enteredFirstName,enteredSurName, enteredUsername, enteredPassword);
        System.out.println("user created successfully\n\n");

    }

    private void addTariff() throws SQLException {
        System.out.println("enter new tariff : ");
        Integer enteredNewTariff = intScanner.nextInt();
        LocalDateTime now = LocalDateTime.now();
        Tariff tariff = tariffRepository.createTariff(enteredNewTariff,now);
        System.out.println("new Tariff created successfully\n\n");

    }

    public void deleteBook() throws SQLException {
        System.out.println("enter a book_id to delete the book : ");
        Integer enteredBookId = intScanner.nextInt();
        bookRepository.deleteBook(enteredBookId);
        System.out.println("book deleted successfully\n\n");
    }

    public void deleteUser() throws SQLException {
        System.out.println("enter a user_id to delete the user : ");
        Integer enteredUserId = intScanner.nextInt();
        List<Order> userBookOrderList = userRepository.checkOrdersForUser(enteredUserId);
        if (!userBookOrderList.isEmpty()) {
            System.out.println("this user cant be delete because user have some book that dont return yet.first he must return the books...");
            System.out.println("the list of user books : ");
            userBookOrderList.forEach(System.out::println);
            System.out.println("\n\n");
        } else {
            userRepository.deleteUser(enteredUserId);
            System.out.println("user delete successfully\n\n");
        }
    }

    public void borrowBook() throws SQLException {
        Integer enteredUserId = tempUserId;
        System.out.println("enter a book_id for borrow that : ");
        Integer enteredBookId = intScanner.nextInt();
        System.out.println("enter Tariff_id TO  : ");
        Integer enteredTariffId = intScanner.nextInt();
        LocalDateTime now = LocalDateTime.now();
        boolean status = true;
        boolean returnStatus = true;
        orderRepository.createOrder(enteredUserId, enteredBookId,now,status,returnStatus,enteredTariffId);
        bookRepository.makeBookStatusFalse(enteredBookId);
        System.out.println("book borrow successfully\n\n");
    }

    public void returnBook() throws SQLException {
        Integer enteredUserId = tempUserId;
        System.out.println("enter a book_id TO return that : ");
        Integer enteredBookId = intScanner.nextInt();
        System.out.println("enter Tariff_id TO  : ");
        Integer enteredTariffId = intScanner.nextInt();
        LocalDateTime now = LocalDateTime.now();
        boolean status = false;
        boolean returnStatus = false;
        orderRepository.createOrder(enteredUserId, enteredBookId,now,status,returnStatus,enteredTariffId);
        bookRepository.makeBookStatusTrue(enteredBookId);
        bookRepository.makeOrderStatusFalse(enteredUserId,enteredBookId);
        System.out.println("book return successfully\n\n");
    }

    private void showAllBorrowBook() throws SQLException {
        List<Order> allOrders = orderRepository.findAllOrders();
        allOrders.forEach(System.out::println);
        allOrders.clear();
        System.out.println("\n\n");
    }

    public void showAllUsers() throws SQLException {
        List<User> allUsers = userRepository.findAllUser();
        allUsers.forEach(user -> System.out.println("id : " + user.getId() + ", firstName : " + user.getFirstName()+", surName : "+user.getSurName() + ", userName : " + user.getUserName()));
        allUsers.clear();
        System.out.println("\n\n");
    }

    public void showAdminMenu() throws SQLException {
        String adminMenuText = " please select a choice :\n" +
                "1)add Book\n2)delete Book\n3)show all Book\n4)add User\n" +
                "5)delete User\n6)show all users\n7)show all borrow Book\n8)log out";
        while (!menuFlag) {
            System.out.println(adminMenuText);
            Integer menuChoice = intScanner.nextInt();
            switch (menuChoice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    deleteBook();
                    break;
                case 3:
                    showAllBooks();
                    break;
                case 4:
                    addUser();
                    break;
                case 5:
                    deleteUser();
                    break;
                case 6:
                    showAllUsers();
                    break;
                case 7:
                    showAllBorrowBook();
                    break;
                case 8:
                    menuFlag = true;
                    login();
                    break;
                default:
                    System.out.println("this is wrong choice ! \n Please enter a number from menu");
            }
        }
    }

    public void showUserMenu() throws SQLException {
        String userMenuText = " please select a choice :\n" +
                " 1)show all Book\n2)borrow Book\n3)return Book\n4)show all my books i borrow\n5)add Tariff\n6)log out";

        while (!menuFlag) {
            System.out.println(userMenuText);
            Integer menuChoice = intScanner.nextInt();
            switch (menuChoice) {
                case 1:
                    showAllBooks();
                    break;
                case 2:
                    borrowBook();
                    break;
                case 3:
                    returnBook();
                    break;
                case 4:
                    showAllMyBooks();
                    break;
                case 5:
                    addTariff();
                    break;
                case 6:
                    menuFlag = true;
                    login();
                    break;
                default:
                    System.out.println("this is wrong choice ! \n Please enter a number from menu");
            }
        }
    }

}


//    public void returnBook() throws SQLException {
////        System.out.println("enter your user_id : ");
////        Integer enteredUserId = intScanner.nextInt();
//        Integer enteredUserId = tempUserId;
//        System.out.println("enter a book_id TO return that : ");
//        Integer enteredBookId = intScanner.nextInt();
//        LocalDateTime now = LocalDateTime.now();
//        boolean status = false;
//        boolean returnStatus = false;
//        orderRepository.createOrder(enteredUserId, enteredBookId,now,status,returnStatus);
//        bookRepository.makeBookStatusTrue(enteredBookId);
//        bookRepository.makeOrderStatusFalse(enteredUserId,enteredBookId);
//        System.out.println("book return successfully\n\n");
//    }



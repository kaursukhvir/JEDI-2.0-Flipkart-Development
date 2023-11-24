package com.flipkart.client;
import com.flipkart.bean.Role;
import com.flipkart.business.UserService;

import java.util.Scanner;

import static com.flipkart.constant.Constants.*;

public class MainApplicationClient {

    public static Scanner scanner = new Scanner(System.in);
    private static UserService userService = new UserService();
    private static AdminClient adminClient = new AdminClient();
    private static CustomerClient customerClient = new CustomerClient();
    private static GymOwnerClient gymOwnerClient = new GymOwnerClient();



    private static void redirect(Role role){
        switch (role){
            case ADMIN:
                adminClient.adminMainPage();
                break;
            case GYMOWNER:
//                gymOwnerClient.gymOwnerMainPage();
                break;
            case CUSTOMER:
                customerClient.customerClientMainPage();
                break;

        }
    }

    private static void login(){

        System.out.println("Enter your UserName");
        String userName = scanner.next();

        System.out.println("Enter your Passkey");
        String password = scanner.next();

        if (userService.login(userName,password)){
//   we will extract the user role and call the respective mainPage right now we don't have DB we use default
            redirect(Role.CUSTOMER);
        }


    }
    
    private static void registration(){

        System.out.println("Enter your role");
        Role role = Role.valueOf(scanner.next().toUpperCase());

        switch (role){
            case ADMIN:
                adminClient.register();
                break;
            case CUSTOMER:
                customerClient.register();
                break;
            case GYMOWNER:
                gymOwnerClient.register();
                break;
            default:
                System.out.println(INVALID_CHOICE_ERROR);
        }
        redirect(role);

    }
    
    public static void main(String[] args) {
        System.out.println(WELCOME_MESSAGE);
        while(true) {
            System.out.println("1. Login\n2. Registration\n3. Exit");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    registration();
                    break;
                case 3:
                    System.out.println(EXIT_MESSAGE);
                    return;
                default:
                    System.out.println(INVALID_CHOICE_ERROR);
                    break;
            }

        }

    }
}

package com.joysistvi.day8.activity;

import java.util.Scanner;

public class Activity5_Petralba {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Dial *143# to access Globe Services");
        System.out.print("Enter USSD Code: ");
        String num = sc.nextLine();

        if (!num.equals("*143#")) {
            System.out.println("Invalid USSD code.");
            return;
        }

        System.out.println("        * Load Registration *");
        System.out.println("    [1] Go+");
        System.out.println("    [2] Go");
        System.out.println("    [3] GoEXTRA");
        System.out.println("    [4] UNLI 5G");
        System.out.println("    [5] ALLNET");
        System.out.print("Enter a choice: ");
        int mainChoice = sc.nextInt();

        String promoName = "";
        String details = "";
        boolean validPromo = false;

        switch (mainChoice) {
                case 1:
                    System.out.println();
                    System.out.println("        *** Go+ Promos ***");
                    System.out.println("    [1] Go+99");
                    System.out.println("    [2] Go+129");
                    System.out.println("    [3] Go+149");
                    System.out.print("Enter a choice: ");
                    int goPlus = sc.nextInt();
                    switch (goPlus) {
                        case 1:
                            promoName = "Go+99";
                            details = "Get 8 GB for all sites + 8GB";
                            validPromo = true;
                            break;
                        case 2:
                            promoName = "Go+129";
                            details = "Get 10 GB for all sites + 10GB";
                            validPromo = true;
                            break;
                        case 3:
                            promoName = "Go+149";
                            details = "Get 12 GB for all sites + 12GB";
                            validPromo = true;
                            break;
                        default:
                            System.out.println("Invalid choice.");
                    }
                    break;

                case 2:
                    System.out.println();
                    System.out.println("        *** Go Promos ***");
                    System.out.println("    [1] Go50");
                    System.out.println("    [2] Go90");
                    System.out.println("    [3] Go120");
                    System.out.print("Enter a choice: ");
                    int go = sc.nextInt();
                    switch (go) {
                        case 1:
                            promoName = "Go50";
                            details = "Get 2 GB for all sites";
                            validPromo = true;
                            break;
                        case 2:
                            promoName = "Go90";
                            details = "Get 4 GB for all sites";
                            validPromo = true;
                            break;
                        case 3:
                            promoName = "Go120";
                            details = "Get 6 GB for all sites";
                            validPromo = true;
                            break;
                        default:
                            System.out.println("Invalid choice.");
                    }
                    break;

                case 3:
                    System.out.println();
                    System.out.println("        *** GoEXTRA Promos ***");
                    System.out.println("    [1] GoEXTRA99");
                    System.out.println("    [2] GoEXTRA129");
                    System.out.println("    [3] GoEXTRA149");
                    System.out.print("Enter a choice: ");
                    int goExtra = sc.nextInt();
                    switch (goExtra) {
                        case 1:
                            promoName = "GoEXTRA99";
                            details = "Get 8 GB for all sites + 5GB GoEXTRA";
                            validPromo = true;
                            break;
                        case 2:
                            promoName = "GoEXTRA129";
                            details = "Get 12 GB for all sites + 8GB GoEXTRA";
                            validPromo = true;
                            break;
                        case 3:
                            promoName = "GoEXTRA149";
                            details = "Get 15 GB for all sites + 10GB GoEXTRA";
                            validPromo = true;
                            break;
                        default:
                            System.out.println("Invalid choice.");
                    }
                    break;

                case 4:
                    System.out.println();
                    System.out.println("        *** UNLI 5G Promos ***");
                    System.out.println("    [1] UNLI 5G 99");
                    System.out.println("    [2] UNLI 5G 149");
                    System.out.println("    [3] UNLI 5G 199");
                    System.out.print("Enter a choice: ");
                    int unli = sc.nextInt();
                    switch (unli) {
                        case 1:
                            promoName = "UNLI 5G 99";
                            details = "Get unlimited 5G data for 7 days";
                            validPromo = true;
                            break;
                        case 2:
                            promoName = "UNLI 5G 149";
                            details = "Get unlimited 5G data for 15 days";
                            validPromo = true;
                            break;
                        case 3:
                            promoName = "UNLI 5G 199";
                            details = "Get unlimited 5G data for 30 days";
                            validPromo = true;
                            break;
                        default:
                            System.out.println("Invalid choice.");
                    }
                    break;

                case 5:
                    System.out.println();
                    System.out.println("        *** ALLNET Promos ***");
                    System.out.println("    [1] ALLNET99");
                    System.out.println("    [2] ALLNET149");
                    System.out.println("    [3] ALLNET199");
                    System.out.print("Enter a choice: ");
                    int allnet = sc.nextInt();
                    switch (allnet) {
                        case 1:
                            promoName = "ALLNET99";
                            details = "Get 5 GB data + unlimited texts to all networks";
                            validPromo = true;
                            break;
                        case 2:
                            promoName = "ALLNET149";
                            details = "Get 8 GB data + unlimited texts to all networks";
                            validPromo = true;
                            break;
                        case 3:
                            promoName = "ALLNET199";
                            details = "Get 12 GB data + unlimited texts to all networks";
                            validPromo = true;
                            break;
                        default:
                            System.out.println("Invalid choice.");
                    }
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
            // Promo details menu (only if a valid promo was picked)
        if (validPromo) {
                System.out.println();
                System.out.println("        *** " + promoName + " Promo ***");
                System.out.println("-> " + details);
                System.out.println("    [1] Subscribe");
                System.out.println("    [2] Exit");
                System.out.print("Enter a choice: ");
                int action = sc.nextInt();

                if (action == 1) {
                    System.out.println();
                    System.out.println("You are now subscribed to " + promoName);
                } else if (action != 2) {
                    System.out.println("Alright see you next time!");
                }
            }

        sc.close();
    }
}
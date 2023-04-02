package Proje3;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner oku=new Scanner(System.in);



       Actions action = new Actions();
       action.description.add(0, "Para_Yatir"); //ŞURAYI SONRADAN EKLEDİM AMA EMİN DEĞİLİM
       action.description.add(1, "Para_Cek"); //actions classına tanımladığım için buraya da descriptionu
       action.description.add(2, "Transfer");//verince kabul etti
       action.description.add(3, "Çıkış");

        ArrayList<Customer> musteriler = new ArrayList<>();

        Account m1Account1=new Account("1234",100);
        Account m1Account2=new Account("4321",200);
        ArrayList<Account> m1Accounts=new ArrayList<>(Arrays.asList(m1Account1,m1Account2));
        Customer musteri1= new Customer("User1","password1",m1Accounts);
        musteriler.add(musteri1);

        Account m2Account1=new Account("5678",1000);
        Account m2Account2=new Account("8765",2000);
        ArrayList<Account> m2Accounts=new ArrayList<>(Arrays.asList(m2Account1,m2Account2));
        Customer musteri2=new Customer("User2","password2",m2Accounts);
        musteriler.add(musteri2);

        Account m3Account1=new Account("9999",500);
        Account m3Account2=new Account("1111",400);
        ArrayList<Account> m3Accounts=new ArrayList<>(Arrays.asList(m3Account1,m3Account2));
        Customer musteri3=new Customer("User3","password3",m3Accounts);
        musteriler.add(musteri3);


        Customer currentUser;
        Account chosenAccount;

        while (true) {
            System.out.print("Please enter your username: ");
            String username = oku.nextLine();
            System.out.print("Please enter your password: ");
            String password = oku.nextLine();

            currentUser = confirmUsernameAndPassword(musteriler, username, password);

            if (currentUser != null) {
                System.out.println("Başarılı bir şekilde giriş yaptınız. TEBRİKLER...");
                break;
            }
            System.out.println("Sistemde kayıtlı böyle bir kullanıcı bulunamadı. Lütfen tekrar deneyiniz:");
        }

        while (true) {
            System.out.println("Yapmak istediğiniz işlemi seçiniz:");
            for (int i = 0; i < action.description.size(); i++) { //burada actionsu kabul etmedi descriptionsu aldı
                System.out.println(action.description.get(i) + " - " + (i + 1));
            }
            int userChoice = oku.nextInt();

            switch (userChoice) {

                case 1:{

                    while (true){
                        System.out.println("Lutfen para yatirmak istediginiz hesap numarasini giriniz...: ");
                        for (Account account : currentUser.accounts) {
                            System.out.println(account.accountNumber);
                        }
                        String chosen = oku.next();
                        chosenAccount = confirmAccountNumber(currentUser, chosen);
                        if (chosenAccount == null) {
                            System.out.println("Hatali hesap numarasi girdiniz...");
                            continue;
                        }
                        break;
                    }
                    System.out.println("Lutfen yatirilacak para miktarini giriniz...: ");
                    int amountToAdd = oku.nextInt();
                    chosenAccount.fund += amountToAdd;
                    System.out.println(chosenAccount.accountNumber + " numaralı hesap; guncel bakiyeniz " + chosenAccount.fund + " €...");
                    break;
                }

                case 2: {

                    while (true){
                        System.out.println("Lutfen para cekmek istediginiz hesap numarasini giriniz...: ");
                        for (Account account : currentUser.accounts) {
                            System.out.println(account.accountNumber);
                        }
                        String chosen = oku.next();
                        chosenAccount = confirmAccountNumber(currentUser, chosen);
                        if (chosenAccount == null) {
                            System.out.println("Hatali hesap numarasi girdiniz...");
                            continue;
                        }
                        System.out.println("Lutfen cekmek istediginiz para miktarini giriniz...: ");
                        int amountToWithdraw = oku.nextInt();
                        if (!withdraw(chosenAccount, amountToWithdraw)){
                            System.out.println("Hesabinizda yeterli bakiye bulunmamaktadir...");
                            break;
                        }
                       chosenAccount.fund -= amountToWithdraw;
                        System.out.println("Lutfen paranizi aliniz. " + chosenAccount.accountNumber + " numaralı hesabinizda toplam " + chosenAccount.fund + " € kalmistir...");
                    }
                    break;
                }

                case 3:{
                    System.out.println("Bu islemi su an gerceklestiremiyoruz...");
                    break;
                }

                case 4: System.exit(1);

                default:{
                    System.out.println("Basarili bir secim yapmadiniz...");
                }

            }
        }
    }


    public static Customer confirmUsernameAndPassword(List<Customer> users, String username, String password) {

        for (Customer user : users) {
            if (user.username.equals(username) && user.password.equals(password)) {
                return user;
            }
        }
        return null;
    }
    public static Account confirmAccountNumber(Customer currentUser, String chosenAccount){
        for (Account account : currentUser.accounts)
            if (account.accountNumber.equals(chosenAccount)) {
                return account;
            }
        return null;

    }
    public static boolean withdraw(Account chosenAccount, int amountToWithdraw ){
        for (int i = 0; i < 2; i++){
            if (amountToWithdraw <= chosenAccount.fund){
                return true;
            }
        }
        return false;

    }

    }


package tum.ku.Model.bank;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.UncategorizedSQLException;


import java.util.List;

public class Main {
    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");

        AccountDao accountDao = context.getBean("accountDaoImp", AccountDao.class);

//        accountDao.insertAccount(new Account(6, "Meya"));


        try {

            Account act = accountDao.getOneAccount(6);
            System.out.println("---getOne: " + act);

//        accountDao.deleteAccount(6);

            accountDao.updateAccount(6, "Meyaa");
    Account act2 = accountDao.getOneAccount(6);
        System.out.println("---getOne: "+act2);

            List<Account> accountList = accountDao.getAllAccounts();
            for (Account account : accountList) {
                System.out.println(account);
            }
        }catch (UncategorizedSQLException uncat){
            System.out.println("Error when update ,but DB can update");
            Account act2 = accountDao.getOneAccount(6);
            System.out.println("---getOne: "+act2);
        }
    }
 }


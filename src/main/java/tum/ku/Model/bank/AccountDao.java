package tum.ku.Model.bank;

import org.springframework.jdbc.UncategorizedSQLException;

import java.util.List;

public interface AccountDao {
    void insertAccount(Account account);
    Account getOneAccount(int id);
    List<Account> getAllAccounts();
    void updateAccount(int id, String name) throws UncategorizedSQLException;
    void deleteAccount(int id);
}

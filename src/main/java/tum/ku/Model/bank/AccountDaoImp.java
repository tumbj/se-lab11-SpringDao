package tum.ku.Model.bank;

import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AccountDaoImp implements AccountDao {

    private JdbcTemplate jdbcTemplate;

    public AccountDaoImp(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void insertAccount(Account account) {
        String query = "insert into account (accountNumber, accountName) values (?, ?);";
        Object[] data = new Object[]
                {account.getAccountNumber(), account.getAccountName() };
        jdbcTemplate.update(query, data);
    }

    @Override
    public Account getOneAccount(int id) {
        String query = "select * from account where accountNumber = " + id;
        Account account = jdbcTemplate.queryForObject(query, new AccountRowMapper());
        return account;
    }

    @Override
    public List<Account> getAllAccounts() {
        String query = "select * from account";
        List<Account> accounts = jdbcTemplate.query(query, new AccountRowMapper());
        return accounts;
    }

    @Override
    public void updateAccount(int id, String name) throws UncategorizedSQLException {
//        String query = "UPDATE account " +
//                 String.format("SET accountName = %s ", name) +
//                "WHERE accountNumber= 6;";
//        jdbcTemplate.query(query, new AccountRowMapper());
        String query = "UPDATE account " +
                "SET accountName = '"+name+"'" +
                "WHERE accountNumber = "+id+";";
        jdbcTemplate.query(query, new AccountRowMapper());
    }

    @Override
    public void deleteAccount(int id) {
        String query = "DELETE FROM account WHERE accountNumber = " + id;
        jdbcTemplate.query(query, new AccountRowMapper());
    }

    class AccountRowMapper implements RowMapper<Account> {
        public Account mapRow(ResultSet rs, int rowNum)
                throws SQLException {
            Account account = new Account(rs.getInt("accountNumber"),
                    rs.getString("accountName"));
            return account;
        }
    }
}

package com.silvercoinbank.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.silvercoinbank.domain.Account;
import com.silvercoinbank.domain.AccountType;
import com.silvercoinbank.domain.Branch;
import com.silvercoinbank.domain.Customer;
import com.silvercoinbank.service.BranchService;

@Component
public class AccountDaoImpl implements AccountDao {
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	BranchService branchService;

	@Override
	public List<Account> findAllAccountsUsingJdbcTemplate() {
		String sql = "select * from account";
		List<Account> listOfAccounts = jdbcTemplate.query(sql, new ResultSetExtractor<List<Account>>() {

			@Override
			public List<Account> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<Account> accounts = new ArrayList<>();
				while(rs.next()) {
					Account a = new Account();
					a.setAccountId(rs.getLong("accountId"));
					a.setAccountHolderName(rs.getString("accountHolderName"));					
					AccountType type = AccountType.values()[rs.getInt("accountType")];
					a.setAccountType(type);

					LocalDate localDate = LocalDate.parse(rs.getString("accountDateOpened"));				
					a.setAccountDateOpened(localDate);
					
					a.setAccountCurrentBalance(rs.getDouble("accountCurrentBalance"));

					System.out.println("============== account "+ a);
					accounts.add(a);
				}
				return accounts;
			}			
		});
		return listOfAccounts;
	}

	@Override
	public void saveAccountUsingJdbcTemplate(Account a) {
		String sql = "insert into account(accountId, accountHolderName, accountType, accountDateOpened, accountCurrentBalance, branchId, customerId)"
				+ "values("+ a.getAccountId()+ "'" + a.getAccountHolderName() +"'"+a.getAccountType()+ "'" + a.getAccountDateOpened()
				+ "'" + a.getAccountCurrentBalance() + "'" + a.getAccountBranch().getBranchId() + "'" + a.getAccountCustomer().getCustomerId() + "'" + ")";
		System.out.println("sql: " +sql);
		System.out.println(" going to call JdbcTemplate...");
		jdbcTemplate.execute(sql);
		System.out.println("The details of " + a.getAccountHolderName() + " has been saved to database.");
	}

	@Override
	public void deleteAccountByIdUsingJdbcTemplate(long id) {
		String sql = "delete from account where accountId=?";
		jdbcTemplate.update(sql, new Object[] {id});
		System.out.println("The account id =" + id + " has been deleted");
	} 

	@Override
	public void updateAccountUsingJdbcTemplate(Account a) {
		String sql = "update account set accountHolderName=?, accountCurrentBalance=? where accountId=?";
		jdbcTemplate.update(sql, new Object[] {a.getAccountHolderName(), a.getAccountCurrentBalance(), a.getAccountId()});
		System.out.println("The account id =" + a.getAccountId() + " has been updated");
	}

	@Override
	public Account getAccountByIdUsingJdbcTemplate(long id) {
//		String sql = "select accountId, accountHolderName, accountType, accountDateOpened, accountCurrentBalance, branchId, customerId from account where accountid=?";
		String sql = "select * from account where accountId=?";
		Account account = jdbcTemplate.queryForObject(sql, new Object[] {id}, new RowMapper<Account>() {

			@Override
			public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
				Account a = new Account();
				a.setAccountId(rs.getLong("accountId"));
				a.setAccountHolderName(rs.getString("accountHolderName"));
				rs.getString("accountType");
				AccountType type = AccountType.values()[rs.getInt("accountType")];
				a.setAccountType(type);
				
				LocalDate localDate = LocalDate.parse(rs.getString("accountDateOpened"));				
				a.setAccountDateOpened(localDate);
				
				a.setAccountCurrentBalance(rs.getDouble("accountCurrentBalance"));

				//not show on browser and branchId not show on console because
				// there is @JsonBackReference on the fields in account.java. Remove it to display
				
//				a.setAccountBranch(branchService.findBranchByIdUsingHibernate(rs.getLong("branchId")));				
//				Branch br = new Branch();
//				br.setBranchId(rs.getLong("branchId"));
//				a.setAccountBranch(br);				
//				
//				Customer c = new Customer();
//				c.setCustomerId(rs.getLong("customerId"));
//				a.setAccountCustomer(c);

				System.out.println("============== account "+ a);
				return a;
			}			
		});
		return account;
	}

	@Override
	public void insertAccountsUsingJdbcTemplate(List<Account> accounts) {
		String sql = "insert into account(accountId, accountHolderName, accountType, accountDateOpened, accountCurrentBalance, branchId, customerId) values(?,?,?,?,?,?,?)";
		List<Object[]> objList = new ArrayList<>();
		for (Account a:accounts) {
			Object[] objArr = {a.getAccountId(), a.getAccountHolderName(), a.getAccountType(),
					a.getAccountDateOpened(), a.getAccountCurrentBalance(), 
					a.getAccountBranch().getBranchId(), a.getAccountCustomer().getCustomerId()};
			objList.add(objArr);
		}
		jdbcTemplate.batchUpdate(sql, objList);
	}

	@Override
	public void createTableUsingJdbcTemplate() {
		String sql = "drop table if exists bankdb.account";
		jdbcTemplate.execute(sql);
		System.out.println("Dropping table account ...");
		sql = "create table account(" + "accountId long not null auto_increment," 
									  + "accountHolderName varchar(50) not null,"
									  + "accountType varchar(20),"
									  + "accountDateOpened date,"
									  + "accountCurrentBalance double,"
									  + "branchId long,"
									  + "customerId long" 
									  + "primary key(accountId))";
		System.out.println("======= sql "+ sql);
		jdbcTemplate.execute(sql);
		System.out.println("Table account is created ... ");
	}

}

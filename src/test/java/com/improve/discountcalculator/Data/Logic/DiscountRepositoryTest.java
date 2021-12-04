package com.improve.discountcalculator.Data.Logic;

import com.improve.discountcalculator.Domain.Model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class DiscountRepositoryTest {
    private DiscountRepository _repositoryDiscount;

    @Mock
    private DataSource _dataSource;
    @Mock
    private Connection _connection;
    @Mock
    private Statement _statement;
    @Mock
    private ResultSet _rs;
    private JdbcTemplate _jdbcTemplate;

    @BeforeEach
    void setUp() throws Exception {
        when(_statement.executeQuery(anyString()))
                        .thenReturn(_rs);
        when(_connection.createStatement())
                        .thenReturn(_statement);
        when(_dataSource.getConnection())
                .thenReturn(_connection);

        _jdbcTemplate =  new JdbcTemplate(_dataSource);
        _repositoryDiscount = new DiscountRepository(_jdbcTemplate);
    }

    @Test
    void should_get_Categories() throws Exception {
        Mockito.when(_rs.getInt("Id"))
                .thenReturn(100);
        Mockito.when(_rs.getString("Category"))
                .thenReturn("A Category");
        AtomicInteger count = new AtomicInteger();
        Mockito.when(_rs.next()).then(a -> {
            if(count.getAndIncrement() > 0)
                return false;

            return true;
        });

        List<ItemsCategory> category = _repositoryDiscount.getAllCategories();

        assertThat(category).isNotNull();
        assertThat(category.size()).isEqualTo(1);
        assertThat(category.get(0).getId()).isEqualTo(100);
        assertThat(category.get(0).getCategory()).isEqualTo("A Category");
    }

    @Test
    void should_get_ShopItems() throws SQLException {
        Mockito.when(_rs.getInt("Id"))
                .thenReturn(101);
        Mockito.when(_rs.getInt("CategoryId"))
                .thenReturn(201);
        Mockito.when(_rs.getString("itemName"))
                .thenReturn("Item A");
        Mockito.when(_rs.getDouble("UnitPrice"))
                .thenReturn(0.75);

        AtomicInteger count = new AtomicInteger();
        Mockito.when(_rs.next()).then(i -> {
            if(count.getAndIncrement() > 0)
                return false;

            return true;
        });

        List<Items> items = _repositoryDiscount.getAllShopItems();

        assertThat(items).isNotNull();
        assertThat(items.size()).isEqualTo(1);
        assertThat(items.get(0).getId()).isEqualTo(101);
        assertThat(items.get(0).getItemName()).isEqualTo("Item A");
        assertThat(items.get(0).getCategoryId()).isEqualTo(201);
        assertThat(items.get(0).getUnitPrice()).isEqualTo(0.75);
    }

    @Test
    void should_get_DiscountRules() throws SQLException {
        UUID discountID = UUID.randomUUID();

        Mockito.when(_rs.getString("Id"))
                .thenReturn(discountID.toString());
        Mockito.when(_rs.getInt("usertypeid"))
                .thenReturn(102);
        Mockito.when(_rs.getString("usertype"))
                .thenReturn("Usertype A");
        Mockito.when(_rs.getInt("discounttypeid"))
                .thenReturn(202);
        Mockito.when(_rs.getString("discountname"))
                .thenReturn("Cash");
        Mockito.when(_rs.getInt("ruleapplyid"))
                .thenReturn(302);
        Mockito.when(_rs.getString("applyto"))
                .thenReturn("Apply to A");
        Mockito.when(_rs.getBigDecimal("discountValue"))
                .thenReturn(new BigDecimal(30.00));

        AtomicInteger count = new AtomicInteger();
        Mockito.when(_rs.next()).then(i -> {
            if(count.getAndIncrement() > 0)
                return false;

            return true;
        });

        List<Discounts> discounts = _repositoryDiscount.getDiscountRules();

        assertThat(discounts).isNotNull();
        assertThat(discounts.size()).isEqualTo(1);
        assertThat(discounts.get(0).getId()).isEqualTo(discountID);
        assertThat(discounts.get(0).getDiscountValue()).isEqualTo(new BigDecimal(30.00));
        assertThat(discounts.get(0).getDiscountType().getId()).isEqualTo(202);
        assertThat(discounts.get(0).getDiscountType().getName()).isEqualTo("Cash");
    }

    @Test
    @Disabled
    void should_get_Discount_Rule_By_UserType() throws SQLException {
        UUID discountID = UUID.randomUUID();
        User userTest = new User(1, "Tester", new UserTypes(1, "Customer", null), null, null);

        Mockito.when(_rs.getString("Id"))
                .thenReturn(discountID.toString());
        Mockito.when(_rs.getInt("usertypeid"))
                .thenReturn(userTest.getUserType().getId());
        Mockito.when(_rs.getString("usertype"))
                .thenReturn(userTest.getUserType().getUserType());
        Mockito.when(_rs.getInt("discounttypeid"))
                .thenReturn(202);
        Mockito.when(_rs.getString("discountname"))
                .thenReturn("Cash");
        Mockito.when(_rs.getInt("ruleapplyid"))
                .thenReturn(302);
        Mockito.when(_rs.getString("applyto"))
                .thenReturn("Apply to A");
        Mockito.when(_rs.getBigDecimal("discountValue"))
                .thenReturn(new BigDecimal(30.00));

        AtomicInteger count = new AtomicInteger();
        Mockito.when(_rs.next()).then(u -> {
            if(count.getAndIncrement() > 0)
                return false;

            return true;
        });

        List<Discounts> discounts = _repositoryDiscount.getDiscountRuleByUserType(userTest.getUserType().getId());

        assertThat(discounts).isNotNull();
        assertThat(discounts.size()).isEqualTo(1);
        assertThat(discounts.get(0).getId()).isEqualTo(discountID);
        assertThat(discounts.get(0).getDiscountValue()).isEqualTo(new BigDecimal(30.00));
        assertThat(discounts.get(0).getDiscountType().getId()).isEqualTo(202);
        assertThat(discounts.get(0).getDiscountType().getName()).isEqualTo("Cash");
        assertThat(discounts.get(0).getUserType().getUserType()).isEqualTo("Customer");
    }

    @Test
    @Disabled
    void should_get_Discount_Rule_By_UserType_UserType_ID_Was_Truly_Used() throws SQLException {
        UUID discountID = UUID.randomUUID();
        User user = new User(1, "Tester", new UserTypes(1, "Customer", null), null, null);

        Mockito.when(_rs.getString("Id"))
                .thenReturn(discountID.toString());
        Mockito.when(_rs.getInt("usertypeid"))
                .thenReturn(102);
        Mockito.when(_rs.getString("usertype"))
                .thenReturn("Customer");
        Mockito.when(_rs.getInt("discounttypeid"))
                .thenReturn(202);
        Mockito.when(_rs.getString("discountname"))
                .thenReturn("Cash");
        Mockito.when(_rs.getInt("ruleapplyid"))
                .thenReturn(302);
        Mockito.when(_rs.getString("applyto"))
                .thenReturn("Apply to A");
        Mockito.when(_rs.getBigDecimal("discountValue"))
                .thenReturn(new BigDecimal(30.00));

        AtomicInteger count = new AtomicInteger();
        Mockito.when(_rs.next()).then(i -> {
            if(count.getAndIncrement() > 0)
                return false;

            return true;
        });

        ArgumentCaptor<UserTypes> userTypeArgumentCaptor = ArgumentCaptor.forClass(UserTypes.class);

        verify(_repositoryDiscount).getDiscountRuleByUserType(user.getUserType().getId());
        UserTypes captured = userTypeArgumentCaptor.getValue();

        assertThat(captured.getId()).isEqualTo(user.getUserType().getId());
    }

    @Test
    void should_get_Discount_Types() throws SQLException {
        DiscountsType discountsType = new DiscountsType(401, "Cash");

        Mockito.when(_rs.getInt("Id"))
                .thenReturn(discountsType.getId());
        Mockito.when(_rs.getString("name"))
                .thenReturn(discountsType.getName());

        AtomicInteger count = new AtomicInteger();
        Mockito.when(_rs.next()).then(i -> {
            if(count.getAndIncrement() > 0)
                return false;

            return true;
        });

        List<DiscountsType> discountsTypesList = _repositoryDiscount.getDiscountTypes();

        assertThat(discountsTypesList).isNotNull();
        assertThat(discountsTypesList.size()).isEqualTo(1);
        assertThat(discountsTypesList.get(0).getId()).isEqualTo(401);
        assertThat(discountsTypesList.get(0).getName()).isEqualTo("Cash");
    }

    @Test
    void should_get_User_Types() throws SQLException {
        UserTypes userTypes = new UserTypes(501, "Affiliate", null);

        Mockito.when(_rs.getInt("Id"))
                .thenReturn(userTypes.getId());
        Mockito.when(_rs.getString("usertype"))
                .thenReturn(userTypes.getUserType());

        AtomicInteger count = new AtomicInteger();
        Mockito.when(_rs.next()).then(i -> {
            if(count.getAndIncrement() > 5)
                return false;

            return true;
        });

        List<UserTypes> userTypesList = _repositoryDiscount.getUserTypes();

        assertThat(userTypesList).isNotNull();
        assertThat(userTypesList.size()).isEqualTo(6);
        assertThat(userTypesList.get(0).getId()).isEqualTo(501);
        assertThat(userTypesList.get(0).getUserType()).isEqualTo("Affiliate");
    }

    @Test
    void should_get_Users() throws Exception {
        Date now = new Date();

        User userTest = new User(701, "Tester", new UserTypes(801, "Customer", null), now, null);

        Mockito.when(_rs.getInt("Id"))
                .thenReturn(userTest.getId());
        Mockito.when(_rs.getString("name"))
                .thenReturn(userTest.getName());
        Mockito.when(_rs.getInt("usertypeid"))
                .thenReturn(userTest.getUserType().getId());
        Mockito.when(_rs.getString("usertype"))
                .thenReturn(userTest.getUserType().getUserType());
        Mockito.when(_rs.getDate("MEMBERSHIPDATE"))
                .thenReturn(java.sql.Date.valueOf(LocalDate.now()));

        AtomicInteger count = new AtomicInteger();
        Mockito.when(_rs.next()).then(i -> {
            if(count.getAndIncrement() > 5)
                return false;

            return true;
        });

        List<User> userList = _repositoryDiscount.getUsers();

        assertThat(userList).isNotNull();
        assertThat(userList.size()).isEqualTo(6);
        assertThat(userList.get(0).getId()).isEqualTo(701);
        assertThat(userList.get(0).getName()).isEqualTo("Tester");
        assertThat(userList.get(0).getUserType().getUserType()).isEqualTo("Customer");
        assertThat(userList.get(0).getMembershipDate()).isEqualTo(java.sql.Date.valueOf(LocalDate.now()));
    }

    @Test
    @Disabled
    void getUserById() throws Exception {
        Date now = new Date();

        User userTest = new User(701, "Tester", new UserTypes(801, "Customer", null), now, null);

        Mockito.when(_rs.getInt("Id"))
                .thenReturn(userTest.getId());
        Mockito.when(_rs.getString("name"))
                .thenReturn(userTest.getName());
        Mockito.when(_rs.getInt("usertypeid"))
                .thenReturn(userTest.getUserType().getId());
        Mockito.when(_rs.getString("usertype"))
                .thenReturn(userTest.getUserType().getUserType());
        Mockito.when(_rs.getDate("MEMBERSHIPDATE"))
                .thenReturn(java.sql.Date.valueOf(LocalDate.now()));

        AtomicInteger count = new AtomicInteger();
        Mockito.when(_rs.next()).then(i -> {
            if(count.getAndIncrement() > 5)
                return false;

            return true;
        });

        Optional<User> userList = _repositoryDiscount.getUserById(701);

        assertThat(userList).isNotNull();
        assertThat(userList.get().getId()).isEqualTo(701);
        assertThat(userList.get().getName()).isEqualTo("Tester");
        assertThat(userList.get().getUserType().getUserType()).isEqualTo("Customer");
        assertThat(userList.get().getMembershipDate()).isEqualTo(java.sql.Date.valueOf(LocalDate.now()));
    }
}
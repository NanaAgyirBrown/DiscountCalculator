package com.improve.discountcalculator.Data.Logic;

import com.improve.discountcalculator.Domain.Model.*;
import com.improve.discountcalculator.Interface.IDiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class DiscountRepository implements IDiscountRepository {
    private final JdbcTemplate _jdbcTemplate;

    @Autowired
    public DiscountRepository(JdbcTemplate jdbcTemplate){
        this._jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<ItemsCategory> getAllCategories() {
        final String query = "select * from ItemCategory";
        return _jdbcTemplate.query(query, (resultSet, c) -> {
            int id = resultSet.getInt("Id");
            String category = resultSet.getString("Category");

            return new ItemsCategory(id, category);
        });
    }

    @Override
    public List<Items> getAllShopItems() {
        final String query = "select * from Items";
        return _jdbcTemplate.query(query, (resultSet, i) -> {
            int id = resultSet.getInt("Id");
            int categoryId = resultSet.getInt("CategoryId");
            String itemName = resultSet.getString("itemName");
            double unitPrice = resultSet.getDouble("UnitPrice");

            return new Items(id, itemName, categoryId, unitPrice);
        });
    }

    @Override
    public List<Discounts> getDiscountRules() {
        final String query = "select * from FUNCDISCOUNTS()";
        return _jdbcTemplate.query(query, (resultSet, d) -> {
            UUID id = UUID.fromString(resultSet.getString("Id"));
            int userTypeid = resultSet.getInt("usertypeid");
            String userType = resultSet.getString("usertype");
            int discountTypeid = resultSet.getInt("discounttypeid");
            String discountName = resultSet.getString("discountname");
            int ruleAppliesID = resultSet.getInt("ruleapplyid");
            String ruleAppliesTo = resultSet.getString("applyto");
            BigDecimal discountValue = resultSet.getBigDecimal("discountValue");

            return new Discounts(id, new UserTypes(userTypeid, userType, null),
                    new DiscountsType(discountTypeid, discountName),
                    discountValue, new RulesAppliesTo(ruleAppliesID, ruleAppliesTo));
        });
    }

    @Override
    public List<Discounts> getDiscountRuleByUserType(int UserType) {
        final String query = "select * from FUNCGETDISCOUNT(?)";
        return _jdbcTemplate.query(
                query,
                new Object[]{UserType},
                (resultSet, d) -> {
                    UUID id = UUID.fromString(resultSet.getString("Id"));
                    int userTypeid = resultSet.getInt("usertypeid");
                    String userType = resultSet.getString("usertype");
                    int discountTypeid = resultSet.getInt("discounttypeid");
                    String discountName = resultSet.getString("discountname");
                    int ruleAppliesID = resultSet.getInt("ruleapplyid");
                    String ruleAppliesTo = resultSet.getString("applyto");
                    BigDecimal discountValue = resultSet.getBigDecimal("discountValue");

                    return new Discounts(id, new UserTypes(userTypeid, userType, null),
                            new DiscountsType(discountTypeid, discountName),
                            discountValue, new RulesAppliesTo(ruleAppliesID, ruleAppliesTo));
                });

    }

    @Override
    public List<DiscountsType> getDiscountTypes() {
        final String query = "select * from discountstypes";
        return _jdbcTemplate.query(query, (resultSet, d) -> {
            int id = resultSet.getInt("Id");
            String type = resultSet.getString("name");

            return new DiscountsType(id, type);
        });
    }

    @Override
    public List<UserTypes> getUserTypes() {
        final String query = "select * from usertypes";
        return _jdbcTemplate.query(query, (resultSet, u) -> {
            int id = resultSet.getInt("Id");
            String userTypes = resultSet.getString("usertype");

            return new UserTypes(id, userTypes, null);
        });
    }

    @Override
    public List<User> getUsers() {
        final String query = "select * from FUNCUSERS()";
        return _jdbcTemplate.query(query, (resultSet, d) -> {
            int id = resultSet.getInt("Id");
            String name = resultSet.getString("name");
            int userTypeid = resultSet.getInt("usertypeid");
            String userType = resultSet.getString("usertype");
            Date membership = resultSet.getDate("MEMBERSHIPDATE");

            return new User(id,name, new UserTypes(userTypeid, userType, null),
                    membership, null);
        });
    }

    @Override
    public Optional<User> getUserById(int Userid) {
        final String query = "select * from FUNCUSER(?)";
        User customer = _jdbcTemplate.queryForObject(
                query,
                (resultSet, d) -> {
                    int id = resultSet.getInt("Id");
                    String name = resultSet.getString("name");
                    int userTypeid = resultSet.getInt("usertypeid");
                    String userType = resultSet.getString("usertype");
                    Date membership = resultSet.getDate("MEMBERSHIPDATE");

            return new User(
                    id,
                    name,
                    new UserTypes(userTypeid, userType, null),
                    membership,
                    null);
        }, Userid);

        return Optional.ofNullable(customer);
    }
}

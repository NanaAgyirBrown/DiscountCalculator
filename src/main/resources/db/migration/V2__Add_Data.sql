Insert into ItemCategory (Id, Category) values (1, 'Grocery');
Insert into ItemCategory (Id, Category) values (2, 'Electronics');

Insert into UserTypes(Id, UserType) values (1, 'Employee');
Insert into UserTypes(Id, UserType) values (2, 'Affliate');
Insert into UserTypes(Id, UserType) values (3, 'Customer');

Insert into RulesAppliesTo(Id, ApplyTo) values (1, 'Grocery');
Insert into RulesAppliesTo(Id, ApplyTo) values (2, 'Electronics');
Insert into RulesAppliesTo(Id, ApplyTo) values (3, 'All');

Insert into DiscountsTypes(Id, Name) values (1, 'Cash');
Insert into DiscountsTypes(Id, Name) values (2, 'Percentage');


Insert into Items(CategoryId, ItemName, UnitPrice) values (2, 'Television', 125.00);
Insert into Items(CategoryId, ItemName, UnitPrice) values (2, 'Laptop', 1845.00);
Insert into Items(CategoryId, ItemName, UnitPrice) values (1, 'Apples', 1.15);
Insert into Items(CategoryId, ItemName, UnitPrice) values (1, 'Mangoes', 0.75);
Insert into Items(CategoryId, ItemName, UnitPrice) values (2, 'iPhone', 85.00);
Insert into Items(CategoryId, ItemName, UnitPrice) values (1, 'Sugar', 0.25);
Insert into Items(CategoryId, ItemName, UnitPrice) values (1, 'Milk', 1.92);
Insert into Items(CategoryId, ItemName, UnitPrice) values (2, 'Fan', 25.99);
Insert into Items(CategoryId, ItemName, UnitPrice) values (2, 'Rice cooker', 42.59);
Insert into Items(CategoryId, ItemName, UnitPrice) values (2, 'Lamp', 18.63);

Insert into Users(Name, UserTypeID, MembershipDate) values ('Kofi Mensah', 1, '2017-04-21 00:00:00');
Insert into Users(Name, UserTypeID, MembershipDate) values ('Yaa Mansa', 3, '2020-10-10 00:00:00');
Insert into Users(Name, UserTypeID, MembershipDate) values ('Musa Ishameal', 2, now());
Insert into Users(Name, UserTypeID, MembershipDate) values ('Lettu Shangrila', 3, '2010-01-21 00:00:00');

Insert into Discounts(UserTypeID, DiscountTypeId, DiscountValue, RuleAppliesToId) values (2,2,10.00,2);
Insert into Discounts(UserTypeID, DiscountTypeId, DiscountValue, RuleAppliesToId) values (1,2,30.00,2);
Insert into Discounts(UserTypeID, DiscountTypeId, DiscountValue, RuleAppliesToId) values (3,2,5.00,2);
Insert into Discounts(UserTypeID, DiscountTypeId, DiscountValue, RuleAppliesToId) values (3,1,5.00,3);
---------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION FUNCDISCOUNTS()
    RETURNS TABLE("ID" uuid, "USERTYPEID" INTEGER, "USERTYPE" VARCHAR(50), "DISCOUNTTYPEID" INTEGER, "DISCOUNTNAME" VARCHAR(50), "RULEAPPLYID" INTEGER, "APPLYTO" VARCHAR(50), "DISCOUNTVALUE" NUMERIC)
AS $$
BEGIN
RETURN QUERY SELECT D.ID AS "ID", D.USERTYPEID AS "USERTYPEID", U.USERTYPE AS "USERTYPE", D.DISCOUNTTYPEID AS "DISCOUNTTYPEID", T.NAME AS "DISCOUNTNAME",
	D.RULEAPPLIESTOID AS "RULEAPPLYID", APPLYTO, DISCOUNTVALUE AS "DISCOUNTVALUE"
	from discounts D INNER JOIN USERTYPES U ON D.USERTYPEID = U.ID
	INNER JOIN DISCOUNTSTYPES T ON D.DISCOUNTTYPEID = T.ID
	INNER JOIN RULESAPPLIESTO R ON D.RULEAPPLIESTOID = R.ID;
END;
$$
LANGUAGE 'plpgsql';
--------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION FUNCGETDISCOUNT(vUSERTYPE INTEGER)
    RETURNS TABLE("ID" uuid, "USERTYPEID" INTEGER, "USERTYPE" VARCHAR(50), "DISCOUNTTYPEID" INTEGER, "DISCOUNTNAME" VARCHAR(50), "RULEAPPLYID" INTEGER, "APPLYTO" VARCHAR(50), "DISCOUNTVALUE" NUMERIC)
AS $$
BEGIN
RETURN QUERY SELECT D.ID AS "ID", D.USERTYPEID AS "USERTYPEID", U.USERTYPE AS "USERTYPE", D.DISCOUNTTYPEID AS "DISCOUNTTYPEID", T.NAME AS "DISCOUNTNAME",
	D.RULEAPPLIESTOID AS "RULEAPPLYID", APPLYTO, DISCOUNTVALUE AS "DISCOUNTVALUE"
	from discounts D INNER JOIN USERTYPES U ON D.USERTYPEID = U.ID
	INNER JOIN DISCOUNTSTYPES T ON D.DISCOUNTTYPEID = T.ID
	INNER JOIN RULESAPPLIESTO R ON D.RULEAPPLIESTOID = R.ID
	WHERE D.USERTYPEID = vUSERTYPE;
END;
$$
LANGUAGE 'plpgsql';
-------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION FUNCUSERS()
    RETURNS TABLE("ID" INTEGER, "NAME" VARCHAR(100), "USERTYPEID" INTEGER, "USERTYPE" VARCHAR(50), "MEMBERSHIPDATE" TIMESTAMP WITHOUT TIME ZONE)
AS $$
BEGIN
RETURN QUERY SELECT U.ID AS "ID",U.NAME AS "NAME", U.USERTYPEID AS "USERTYPEID", T.USERTYPE AS "USERTYPE", U.MEMBERSHIPDATE AS "MEMBERSHIPDATE"
	from USERS U INNER JOIN USERTYPES T ON U.USERTYPEID = T.ID;
END;
$$
LANGUAGE 'plpgsql';
------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION FUNCUSER(vID INTEGER)
    RETURNS TABLE("ID" INTEGER, "NAME" VARCHAR(100), "USERTYPEID" INTEGER, "USERTYPE" VARCHAR(50), "MEMBERSHIPDATE" TIMESTAMP WITHOUT TIME ZONE)
AS $$
BEGIN
RETURN QUERY SELECT U.ID AS "ID",U.NAME AS "NAME", U.USERTYPEID AS "USERTYPEID", T.USERTYPE AS "USERTYPE", U.MEMBERSHIPDATE AS "MEMBERSHIPDATE"
	from USERS U INNER JOIN USERTYPES T ON U.USERTYPEID = T.ID
	WHERE U.ID = vID;
END;
$$
LANGUAGE 'plpgsql';
-- Use the existing database
USE instapay;

-- Create User Table
CREATE TABLE UserTable (
    UserID INT PRIMARY KEY,
    Username VARCHAR(255) NOT NULL,
    Password VARCHAR(255) NOT NULL,
    MobileNumber VARCHAR(15) NOT NULL,
    BankAccount VARCHAR(20),
    WalletProvider VARCHAR(50),
    UserType VARCHAR(50) NOT NULL,
    Verified BIT NOT NULL
);

-- Create BankAccount Table
CREATE TABLE BankAccountTable (
    AccountNumber INT PRIMARY KEY,
    UserID INT FOREIGN KEY REFERENCES UserTable(UserID),
    BankName VARCHAR(50) NOT NULL,
    MobileNumber VARCHAR(15) NOT NULL
);

-- Create WalletProvider Table
CREATE TABLE WalletProviderTable (
    ProviderID INT PRIMARY KEY,
    UserID INT FOREIGN KEY REFERENCES UserTable(UserID),
    ProviderName VARCHAR(50) NOT NULL,
    MobileNumber VARCHAR(15) NOT NULL
);


-- Create UtilityBill Table
CREATE TABLE UtilityBillTable (
    BillID INT PRIMARY KEY,
    UserID INT FOREIGN KEY REFERENCES UserTable(UserID),
    BillType VARCHAR(50) NOT NULL,
    Amount DECIMAL(10, 2) NOT NULL
);

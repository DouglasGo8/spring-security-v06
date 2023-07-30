DROP TABLE IF EXISTS tblUsers CASCADE;
DROP TABLE IF EXISTS tblAccounts CASCADE;
DROP TABLE IF EXISTS tblCustomer CASCADE;
DROP TABLE IF EXISTS tblAuthorities CASCADE;
DROP TABLE IF EXISTS tblAccountTransactions CASCADE;
DROP TABLE IF EXISTS tblLoans CASCADE;
DROP TABLE IF EXISTS tblCards CASCADE;
DROP TABLE IF EXISTS tblNoticeDetails CASCADE;
DROP TABLE IF EXISTS tblContactMessages CASCADE;
--
DROP INDEX IF EXISTS customerId_idx;
DROP INDEX IF EXISTS accountNumber_idx;
DROP INDEX IF EXISTS customer_id_loans_idx;
DROP INDEX IF EXISTS customer_id_loans_idx;
--
CREATE TABLE tblCustomer
(
    customerId   SERIAL NOT NULL PRIMARY KEY,
    name         TEXT   NOT NULL,
    email        TEXT   NOT NULL,
    mobileNumber TEXT   NOT NULL,
    pwd          TEXT   NOT NULL,
    role         TEXT   NOT NULL,
    createdDt    DATE DEFAULT NULL
);
--
-- pwd = welcome1
INSERT INTO tblCustomer(name, email, mobileNumber, pwd, role)
VALUES ('Happy', 'happy@example.com', '22113322', '$2a$12$.86q.fCWEiJT5OZ/lEm9MeGr5xYhJt.L7fyUocxDorFfsI/h401Sq',
        'admin');

INSERT INTO tblCustomer(name, email, mobileNumber, pwd, role)
VALUES ('John Doe', 'john.doe@example.com', '331241222', '$2a$12$.86q.fCWEiJT5OZ/lEm9MeGr5xYhJt.L7fyUocxDorFfsI/h401Sq',
        'user');
--
CREATE TABLE tblAuthorities
(
    id         SERIAL NOT NULL PRIMARY KEY,
    customerId int    NOT NULL,
    name       TEXT   NOT NULL,
    CONSTRAINT authorities_ibfk_1 FOREIGN KEY (customerId) REFERENCES tblCustomer (customerId)
);
CREATE INDEX authoritiesCustomerId_idx ON tblAuthorities (customerId);
--
INSERT INTO tblAuthorities (customerId, name)
VALUES (1, 'VIEWACCOUNT');

INSERT INTO tblAuthorities (customerId, name)
VALUES (1, 'VIEWCARDS');

INSERT INTO tblAuthorities (customerId, name)
VALUES (1, 'VIEWLOANS');

INSERT INTO tblAuthorities (customerId, name)
VALUES (1, 'VIEWBALANCE');

DELETE
FROM tblAuthorities;

INSERT INTO tblAuthorities (customerId, name)
VALUES (1, 'ROLE_USER');

INSERT INTO tblAuthorities (customerId, name)
VALUES (1, 'ROLE_ADMIN');
--SELECT *
--FROM tblCustomer;
--
CREATE TABLE tblAccounts
(
    customerId    INT     NOT NULL,
    accountNumber NUMERIC NOT NULL,
    accountType   TEXT    NOT NULL,
    branchAddress TEXT,
    createdDt     DATE DEFAULT NULL,
    PRIMARY KEY (accountNumber),
    FOREIGN KEY (customerId) REFERENCES tblCustomer (customerId) on delete CASCADE
);
--
INSERT INTO tblAccounts (customerId, accountNumber, accountType, branchAddress)
VALUES (1, 1865764534, 'Savings', '123 Main Street, New York');

CREATE TABLE tblAccountTransactions
(
    transactionId      TEXT NOT NULL,
    accountNumber      INT  NOT NULL,
    customerId         INT  NOT NULL,
    transactionDt      DATE NOT NULL,
    transactionSummary TEXT NOT NULL,
    transactionType    TEXT NOT NULL,
    transactionAmt     INT  NOT NULL,
    closingBalance     INT  NOT NULL,
    createDt           DATE DEFAULT NULL,
    PRIMARY KEY (transactionId),
    CONSTRAINT accounts_ibfk_2 FOREIGN KEY (accountNumber) REFERENCES tblAccounts (accountNumber) ON DELETE CASCADE,
    CONSTRAINT acct_user_ibfk_1 FOREIGN KEY (customerId) REFERENCES tblCustomer (customerId) ON DELETE CASCADE
);

CREATE INDEX customerId_idx ON tblAccountTransactions (customerId);
CREATE INDEX accountNumber_idx ON tblAccountTransactions (accountNumber);

INSERT INTO tblAccountTransactions (transactionId, accountNumber, customerId, transactionDt, transactionSummary,
                                    transactionType, transactionAmt,
                                    closingBalance, createDt)
VALUES ('31efc889-f428-41ab-9ec6-3c733e134a72', 1865764534, 1, current_date - 7, 'Coffee Shop', 'Withdrawal', 30, 34500,
        current_date - 7);

INSERT INTO tblAccountTransactions (transactionId, accountNumber, customerId, transactionDt, transactionSummary,
                                    transactionType, transactionAmt,
                                    closingBalance, createDt)
VALUES ('fbf991f4-16ee-4b10-9ab9-4226c08fcf5c', 1865764534, 1, current_date - 6, 'Uber', 'Withdrawal', 100, 34400,
        current_date - 6);

INSERT INTO tblAccountTransactions (transactionId, accountNumber, customerId, transactionDt, transactionSummary,
                                    transactionType, transactionAmt,
                                    closingBalance, createDt)
VALUES ('16726719-d6b1-4542-82ce-97674efe48d1', 1865764534, 1, current_date - 5, 'Self Deposit', 'Deposit', 500, 34900,
        current_date - 5);

INSERT INTO tblAccountTransactions (transactionId, accountNumber, customerId, transactionDt, transactionSummary,
                                    transactionType, transactionAmt,
                                    closingBalance, createDt)
VALUES ('2c2ab7fd-4b3b-4e07-9e86-7f5f7299c293', 1865764534, 1, current_date - 4, 'Ebay', 'Withdrawal', 600, 34300,
        current_date - 4);

INSERT INTO tblAccountTransactions (transactionId, accountNumber, customerId, transactionDt, transactionSummary,
                                    transactionType, transactionAmt,
                                    closingBalance, createDt)
VALUES ('38011e24-3b1b-437c-ab01-8696895e78ac', 1865764534, 1, current_date - 2, 'OnlineTransfer', 'Deposit', 700,
        35000, current_date - 2);

INSERT INTO tblAccountTransactions (transactionId, accountNumber, customerId, transactionDt, transactionSummary,
                                    transactionType, transactionAmt,
                                    closingBalance, createDt)
VALUES ('400bbdf7-1c4d-4773-bc66-10a2071cce1a', 1865764534, 1, current_date - 1, 'Amazon.com', 'Withdrawal', 100, 34900,
        current_date - 1);

CREATE TABLE tblLoans
(
    loanNumber        SERIAL NOT NULL,
    customerId        INT    NOT NULL,
    startDt           DATE   NOT NULL,
    loanType          TEXT   NOT NULL,
    totalLoan         INT    NOT NULL,
    amountPaid        INT    NOT NULL,
    outstandingAmount INT    NOT NULL,
    createDt          DATE DEFAULT NULL,
    PRIMARY KEY (loanNumber),
    CONSTRAINT loan_customer_ibfk_1 FOREIGN KEY (customerId) REFERENCES tblCustomer (customerId) ON DELETE CASCADE
);

CREATE INDEX customer_id_loans_idx ON tblLoans (customerId);

INSERT INTO tblLoans (customerId, startDt, loanType, totalLoan, amountPaid, outstandingAmount, createDt)
VALUES (1, '2020-10-13', 'Home', 200000, 50000, 150000, '2020-10-13');

INSERT INTO tblLoans (customerId, startDt, loanType, totalLoan, amountPaid, outstandingAmount, createDt)
VALUES (1, '2020-06-06', 'Vehicle', 40000, 10000, 30000, '2020-06-06');

INSERT INTO tblLoans (customerId, startDt, loanType, totalLoan, amountPaid, outstandingAmount, createDt)
VALUES (1, '2018-02-14', 'Home', 50000, 10000, 40000, '2018-02-14');

INSERT INTO tblLoans (customerId, startDt, loanType, totalLoan, amountPaid, outstandingAmount, createDt)
VALUES (1, '2018-02-14', 'Personal', 10000, 3500, 6500, '2018-02-14');

CREATE TABLE tblCards
(
    cardId          SERIAL NOT NULL,
    cardNumber      TEXT   NOT NULL,
    customerId      INT    NOT NULL,
    cardType        TEXT   NOT NULL,
    totalLimit      INT    NOT NULL,
    amountUsed      INT    NOT NULL,
    availableAmount INT    NOT NULL,
    createDt        DATE DEFAULT NULL,
    PRIMARY KEY (cardId),
    CONSTRAINT card_customer_ibfk_1 FOREIGN KEY (customerId) REFERENCES tblCustomer (customerId) ON DELETE CASCADE
);

INSERT INTO tblCards (cardNumber, customerId, cardType, totalLimit, amountUsed, availableAmount, createDt)
VALUES ('4565XXXX4656', 1, 'Credit', 10000, 500, 9500, current_date);

INSERT INTO tblCards (cardNumber, customerId, cardType, totalLimit, amountUsed, availableAmount, createDt)
VALUES ('3455XXXX8673', 1, 'Credit', 7500, 600, 6900, current_date);

INSERT INTO tblCards (cardNumber, customerId, cardType, totalLimit, amountUsed, availableAmount, createDt)
VALUES ('2359XXXX9346', 1, 'Credit', 20000, 4000, 16000, current_date);

CREATE TABLE tblNoticeDetails
(
    noticeId      SERIAL NOT NULL,
    noticeSummary TEXT   NOT NULL,
    noticeDetails TEXT   NOT NULL,
    noticeBegDt   DATE   NOT NULL,
    noticeEndDt   DATE DEFAULT NULL,
    createDt      DATE DEFAULT NULL,
    updateDt      DATE DEFAULT NULL,
    PRIMARY KEY (noticeId)
);

INSERT INTO tblNoticeDetails (noticeSummary, noticeDetails, noticeBegDt, noticeEndDt, createDt, updateDt)
VALUES ('Home Loan interest rates reduced',
        'Home loan interest rates are reduced as per the goverment guidelines. The updated rates will be effective immediately',
        current_date - INTERVAL '30 DAY', current_date + INTERVAL '30 DAY', current_date, null);

INSERT INTO tblNoticeDetails (noticeSummary, noticeDetails, noticeBegDt, noticeEndDt, createDt, updateDt)
VALUES ('Net Banking Offers',
        'Customers who will opt for INTernet banking while opening a saving account will get a $50 amazon voucher',
        current_date - INTERVAL '30 DAY', current_date + INTERVAL '30 DAY', current_date, null);

INSERT INTO tblNoticeDetails (noticeSummary, noticeDetails, noticeBegDt, noticeEndDt, createDt, updateDt)
VALUES ('Mobile App Downtime',
        'The mobile application of the EazyBank will be down from 2AM-5AM on 12/05/2020 due to maINTenance activities',
        current_date - INTERVAL '30 DAY', current_date + INTERVAL '30 DAY', current_date, null);

INSERT INTO tblNoticeDetails (noticeSummary, noticeDetails, noticeBegDt, noticeEndDt, createDt, updateDt)
VALUES ('E Auction notice',
        'There will be a e-auction on 12/08/2020 on the Bank website for all the stubborn arrears.INTerested parties can participate in the e-auction',
        current_date - INTERVAL '30 DAY', current_date + INTERVAL '30 DAY', current_date, null);

INSERT INTO tblNoticeDetails (noticeSummary, noticeDetails, noticeBegDt, noticeEndDt, createDt, updateDt)
VALUES ('Launch of Millennia Cards',
        'Millennia Credit Cards are launched for the premium customers of EazyBank. With these cards, you will get 5% cashback for each purchase',
        current_date - INTERVAL '30 DAY', current_date + INTERVAL '30 DAY', current_date, null);

INSERT INTO tblNoticeDetails (noticeSummary, noticeDetails, noticeBegDt, noticeEndDt, createDt, updateDt)
VALUES ('COVID-19 Insurance',
        'EazyBank launched an insurance policy which will cover COVID-19 expenses. Please reach out to the branch for more details',
        current_date - INTERVAL '30 DAY', current_date + INTERVAL '30 DAY', current_date, null);

CREATE TABLE tblContactMessages
(
    contactId    TEXT NOT NULL,
    contactName  TEXT NOT NULL,
    contactEmail TEXT NOT NULL,
    subject      TEXT NOT NULL,
    message      TEXT NOT NULL,
    createDt     DATE DEFAULT NULL,
    PRIMARY KEY (contactId)
);
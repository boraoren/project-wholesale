INSERT INTO ACCOUNT (NUMBER, NAME, TYPE, BALANCE_DATE, CURRENCY, OPENING_AVAILABLE_BALANCE)
VALUES (123456789, 'SGSavings726', 'Savings', '2018-11-08', 'SGD', 84327.51),
       (234567891, 'AUSavings933', 'Savings', '2018-11-08', 'SGD', 88005.93),
       (345678912, 'AUSavings433', 'Current', '2018-11-08', 'AUD', 38010.62);

INSERT INTO TRANSACTION(ID, VALUE_DATE, DEBIT_AMOUNT,CREDIT_AMOUNT,DEBIT_CREDIT,NARRATIVE,ACCOUNT_NUMBER)
VALUES (100, '2012-01-12', NULL , 9540.98, 'Credit', NULL, 123456789),
       (200, '2012-01-12', NULL , 7497.82, 'Credit', NULL, 123456789),
       (300, '2012-01-12', NULL , 5564.79, 'Credit', NULL, 123456789),
       (400, '2012-01-12', NULL , 8136.18, 'Credit', NULL, 123456789),
       (500, '2012-01-12', NULL , 9442.46, 'Credit', NULL, 123456789),
       (600, '2012-01-12', NULL , 7614.45, 'Credit', NULL, 123456789),
       (700, '2012-01-12', NULL , 3311.55, 'Credit', NULL, 123456789),
       (800, '2012-01-12', NULL , 9186.09, 'Credit', NULL, 123456789),
       (900, '2012-01-12', NULL , 1905.86, 'Credit', NULL, 123456789),
       (1000, '2012-01-12', NULL , 197.78, 'Credit', NULL, 123456789),
       (1100, '2012-01-12', NULL , 8430.49, 'Credit', NULL, 123456789),
       (1200, '2012-01-12', NULL , 8.33, 'Credit', NULL, 123456789);
# WalmartLabsAutomation
Problem:

Automate an end-to-end user e-commerce transaction flow using any open source tool for www.walmart.com with an existing customer 
on Chrome or Safari browser.

Scenario to automate:

1. Login using existing account

2. Perform a search on home page from a pool of key words given below

3. Identify an item from the result set that you can add to cart

4. Add the item to cart

5. Validate that item added is present in the cart and is the only item in the cart



TEST DATA:

• Account details: 
  
  Email: abhishek.dhaka28@gmail.com
  
  Password: qwerty@123
  
• Search terms: tv, socks, dvd, toys, iPhone



PROGRAMMING LANGUAGE:

Java : Selenium Web Driver using Page object Model for the automating the end to end flow

TestNG : For executing test cases


STEPS FOR END TO END AUTOMATION

1. Login using existing account

  a. Setting up the browser
  
  b. Load the webpage http://www.walmart.com/
  
  c. Click on the sign in link
  
  d. Enter the email id
  
  e. Enter the password
  
  f. Click on the login button
  

2. Perform a search on home page from a pool of key words

  a. Enter the item to be searched in the search text box
  
  b. Press enter to get the result set
  
3. Identify an item from the result set that you can add to cart

  a. Product is selected from the result set and its product id is retrieved.
  
  b. This retrieved product id is used for further validation.
  
4. Add the item to cart

  a. Click on add to cart button
  
  b. Now click on the view cart button for validation
  
5. Validate that item added is present in the cart and is the only item in the cart

  a. From the view cart get the actual product id of the item.
  
  b. Compare the actual product id with expected product id retrieved earlier in step 3.
  
  c. If product id is same then it is assumed that the item selected is present in the cart.
  
  d. Now calulate the quantity of the item in the cart, it should be one in order to validate that it is only item in the cart.
  
  e. After getting the quantity of the item in the cart, it is compared with "(1 item)".
  
  f. If all the above validation are passed, then we remove the item from the cart to make the cart empty.
  
  g. Close the browser.

DATA PROVIDER:

Data is provided using data layer.

All the data needed for automation is provided in the excel sheet and is retrieved from there while testing.

  
LIBRARIES:
  
Maven project is created for this assignment. All the required dependencies are added in the pom.xml file.
  
EXTRA SCENARIO HANDLED:

Removing the item from the cart after validation.

Handling scenario if the selected item is out of stock.


OTHER TEST SCENARIOS THAT CAN BE AUTOMATED IN FUTURE:

Testing on different browsers.

Running automation in parallel.

Testing with no internet connection.

When the selected item is out of stock, we need to go back and select a new item rather than just saying out of stock.

Searching an item which doesn't exist.

If an item is already present in the cart then quantity validation requires some changes. As we need to check the quantity
of the item corresponding to selected item product id.

Automating the new account creation.



TECHNICAL CHOICES:

Maven is used for adding all required dependencies.

Data is fetched using the excel file so it makes easy for us to add other serach key words for our testing.

Page Object Model is used which provides ease of maintenance, clear separation between test code and navigation code and
also provides easy readibility.


  


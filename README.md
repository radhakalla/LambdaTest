# LambdaTest
I have used TestNG framework with java to demonstrate the problem statement
It can be used in combination with different browsers and operating system where the browsername ,version, OS are passed from the testNG.xml file using parameterization
Parallel attribute is set at the suite level in TestNG.xml file for running the tests parallely.
All the three test scenarios where included in the class file  "SeleniumPlayGround.java"
@BeforeMethod is used to setup the parameters browser,version and os before each test method execution and login to the website.And the build is named dynamically by the browsername and platform that is currently executing on.Also the network logs,videorecording,screenshots,console logs are enabled
@Test annotation is used to demonstrate all the three test and priority is given accordingly based on the scenarios order .
The test methods on a whole has 4 different locators.
@DataProvider annotation is used to pass the data  for filling a form method.
Assertions are used for Testcase verification .

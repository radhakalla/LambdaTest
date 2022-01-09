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
In testscenario1,user is navigating the lambda test playground site and will click on  "Simple-Form-Demo" hyperlink,upon clicking he hyperlink user verifies the url is as expected or not and enter the text in the text box and clicks on the get message button and verifies with the text in right hand panel.
In testscenario2,user is navigating the lambda test playground site and will click on  "Drag and drop sliders" hyperlink,upon clicking he hyperlink user will slide the default 15 slider to 95 and verifies the output.
In testsceanrio3,user is navigates to lambda test playground site and will click on "Input form submit"button  after that user fills all the required data using a dataprovider method and submits the form .User verifies the success message after submitting the form.

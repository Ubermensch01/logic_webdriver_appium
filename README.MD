# Instructions

***

## General
 
 Logical tasks are implemented in corresponding classes Question1, Question2 and Question3.
 
 Run the classes to execute the solutions

 #### Question 1: string reversal
 
 The assignment mentioned reversing the sentence word by word so I implemented 
 both regular reversal and word by word sentence reversal.
 
 #### Question 2: WebDriver usage and extracting data from the webpage
 
 The html page provided with the assignment is structured incorrectly: body tag 
 is located within the head tag, which is incorrect; also, as a suggestion, 
 element pairs could be put in corresponding div tags to provide more structure 
 and simplify location. 
 
 #### Question 3: File manipulation and data structures
 
 The data structure that would work best for the one-to-many data relationship of 
 words to definitions would most likely be a HashMap<String>,List<String>>. However,
 the way data is stored in the file can also be considered a data structure of sorts
 and outputting it does not require any additional transformations.
 
### Considerations
 
Java 8 is required to run the code (See Question3).

If running WebDriver assignment (Question2) returns the following error:
 
``Caused by: java.io.IOException: Server returned HTTP response code: 403 for 
URL: https://api.github.com/repos/mozilla/geckodriver/releases``
 
 Follow these instructions 
 [here](https://github.com/bonigarcia/webdrivermanager#known-issues) 
 or set the system variables through maven.

***

## Mobile
 
 ### Instructions
 
 To execute the tests:
 
 - make sure Appium is running and devices specified in the tests are 
 configured and running too.
   - to install and run Appium run the following command:
   ``npm install -g appium && appium&``
 - to execute tests navigate to the mobile module (``cd mobile``) and run ``cd mvn clean test``
 
 Mobile tests are described in the testng.xml file. 
 Mobile Web test was implemented to be executed on both iOS and Android devices.   
 
 
 
 
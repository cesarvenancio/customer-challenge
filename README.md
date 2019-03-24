# CUstomer Distance - Programming Challenge

## Table of contents

* [General info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)

## General info

This project is a Java solution design for the "Customer" challenge.

### Main Goal

Get a txt from a specific folder, get customers and print only the customers in a range of 100km from Intercom location

### Solution

1. Get txt 
2. Convert the JSON in the Customer object
3. Check if the customer is in the 100km range
    1. If the customer is in the range: 
        1. add to customers list
        2.  Ignore customer and continue the process 
    2. Sort list based on userId (ASC)
    3. Print result - Customer id and distance
	
## Technologies

This project was created using:
* Java 8
		
## Setup

### Execute using maven

```
$ mvn exec:java
```

### OR

### Build the .jar

If you are familiar with Maven, you can use it to build the .jar so you can test the code.
Go to your CLI again and execute the command.

```
$ mvn clean install
```

A `.jar` will be available in the `target` folder.

### Execute the .jar in Your CLI

```
$ java -jar ./target/customer-challenge-1.0.jar
```
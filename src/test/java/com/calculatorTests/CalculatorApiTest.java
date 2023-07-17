package com.calculatorTests;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class CalculatorApiTest {

   // private final String calculatorIp = System.getProperty("calculator.ip");
    // private final int calculatorPort = Integer.parseInt(System.getProperty("calculator.port"));

    @Test
    public void testFirstExpression() throws InterruptedException {
       System.out.println("Running testFirstExpression  ");
        String url= "http://localhost:9080/api/sum/geometric?first=1&ratio=0.5&count=5";
        Response response = RestAssured.get(url);
        // Extract the response as a string
        String responseBody = response.getBody().asString();     
        // Arrange
        String expectedValue = "{\"result\":1.9375}";
        // Act        
        System.out.println("Response was: " + responseBody);
        // Assert
        assert responseBody.equals(expectedValue) : "The calculated value doesn't match the expected value.";
        Thread.sleep(5000);
    }

    @Test
    public void testSecondExpression() throws InterruptedException {
        System.out.println("Running testSecondExpression - 3*7");
        // Arrange
        String expression = "3*7";
        String expectedValue = "{\"result\":21.0}";

        // Act
        String response = evaluateExpression(expression);
        System.out.println("Response was: " + response);

        // Assert
        assert response.equals(expectedValue) : "The calculated value doesn't match the expected value.";
        Thread.sleep(5000);
    }
    @Test
    public void testThirdExpression() throws InterruptedException {
        System.out.println("Running testThirdExpression - (3*7+4)*0.2");
        // Arrange
        String expression = "(3*7+4)*0.2";
        String expectedValue = "{\"result\":5.0}";

        // Act
        String response = evaluateExpression(expression);
        System.out.println("Response was: " + response);

        // Assert
        assert response.equals(expectedValue) : "The calculated value doesn't match the expected value.";
        Thread.sleep(5000);
    }
    @Test
    public void testFourthExpression() throws InterruptedException {
        System.out.println("Running testFourthExpression - 3*cos(2*3.141592653589793238)");
        // Arrange
        String expression = "3*cos(2*3.141592653589793238)";
        String expectedValue = "{\"result\":3.0}";
        // Act
        String response = evaluateExpression(expression);
        System.out.println("Response was: " + response);
        // Assert
        assert response.equals(expectedValue) : "The calculated value doesn't match the expected value.";
        Thread.sleep(5000);
    }
 

    @Test
    public void testFifthExpression() throws InterruptedException {
        System.out.println("Running testFifthExpression - unknowFunction(2)");
       String expression = "unknowFunction(2)";
       String response = evaluateExpression(expression);
        // Extract the response as a string
        String expectedValue = "{\"result\":\"Unexpected API Gateway exception: Could not read the response \"}";
        System.out.println("Response was: " + response);
        // Assert
        assert response.equals(expectedValue) : "The calculated value doesn't match the expected value.";
        Thread.sleep(5000);
    }


    private String evaluateExpression(String expression) {
        String url = "http://" + "localhost" + ":" + 9080 + "/api/evaluate/" + expression;

        // Make the API call and get the response
        Response response = RestAssured.get(url);

        // Extract the response as a string
        String responseBody = response.getBody().asString();

        return responseBody;
    }

}

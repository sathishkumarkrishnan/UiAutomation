package com.sathish.runner;

import java.io.IOException;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sathish.dataproviders.TestDataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/features",
    glue = "com.sathish.stepdefs",
    plugin = {"pretty", "html:target/cucumber-reports.html", "json:target/cucumber.json"},
    monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {

	@Override
	@DataProvider(parallel=true)
	public Object[][] scenarios()
	{
		return super.scenarios();
		
	}
	@Test(dataProvider = "excelData", dataProviderClass = TestDataProvider.class, timeOut = 60000)
    public Object[][] getExcelData() throws IOException {
        // Read test data from Excel
        List<String[]> data = TestDataProvider.getExcelData("src/test/resources/TestData.xlsx", "Sheet1");
        
        Object[][] testData = new Object[data.size()][2];
        for (int i = 0; i < data.size(); i++) {
            testData[i] = data.get(i);
        }

        return testData;
    }
}



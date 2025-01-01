package reports;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.testng.ITestContext;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExtentReportListener extends ExtentManager implements org.testng.ITestListener {

    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getName());
    }

    public void onTestSuccess(ITestResult result) {
        if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, "Pass Test case is: " + result.getName());
        }
    }

    public void onTestFailure(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL,
                    MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
            test.log(Status.FAIL,
                    MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
        }
    }

    public void onTestSkipped(ITestResult result) {
        if (result.getStatus() == ITestResult.SKIP) {
            test.log(Status.SKIP, "Skipped Test case is: " + result.getName());
        }
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        test.log(Status.INFO, "Test case failed but within success percentage: " + result.getName());
    }

    public void onStart(ITestContext context) {
        //test.log(Status.INFO, "Test Execution Started");
    }

    public void onFinish(ITestContext context) {
        try {
            Map<String, Object> testResult = new HashMap<>();
            testResult.put("TotalTestCaseCount", context.getAllTestMethods().length);
            testResult.put("PassedTestCaseCount", context.getPassedTests().size());
            testResult.put("FailedTestCaseCount", context.getFailedTests().size());
            testResult.put("SkippedTestCaseCount", context.getSkippedTests().size());

            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            String filePath = "test-output/ExtentReport/TestExecutionReport.json";
            mapper.writeValue(new File(filePath), testResult);
        } catch (IOException e) {
            throw new RuntimeException("Error occurred while writing to TestExecutionReport.json file: ",
                    e);
        }
    }
}
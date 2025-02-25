package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import java.util.concurrent.TimeoutException;

public class RetryAnalyzer implements IRetryAnalyzer {
    protected Logger logger = LogManager.getLogger(this);
    private int retryCount=0;
    private  static final int maxRetries = 3;
    @Override
    public boolean retry(ITestResult iTestResult) {
        if(iTestResult.getStatus() == ITestResult.FAILURE && retryCount < maxRetries){
            logger.info("Retrying " + iTestResult.getName() + " (attempt " + (retryCount + 1) + ")");
            retryCount++;
            return true;
        }
        return false;
    }
    //Dynamic Retry Based on Exception Type us it after count check on retry method
    private boolean isRetryable(ITestResult result) {
        Throwable throwable = result.getThrowable();
        return throwable instanceof TimeoutException;
    }
}

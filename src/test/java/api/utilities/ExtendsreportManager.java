package api.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtendsreportManager implements ITestListener {
	
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	String repName;
	 public void onStart(ITestContext testContext) {
		 String timeStamp=new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
		 String reportPath = System.getProperty("user.dir") + "/reports/Test-report-" + timeStamp + ".html";
		    sparkReporter = new ExtentSparkReporter(reportPath);
		 sparkReporter.config().setDocumentTitle("RestAssureAutomation");
		 sparkReporter.config().setReportName("pet Store Users API");
		 sparkReporter.config().setTheme(Theme.DARK);
		 
		 extent = new ExtentReports();
		 extent.attachReporter(sparkReporter);
		 extent.setSystemInfo("Application","pet Store Users API");
		 extent.setSystemInfo("Operating System",System.getProperty("os.name"));
		 extent.setSystemInfo("user Name",System.getProperty("user.name"));
		 extent.setSystemInfo("Environment","QA");
		 extent.setSystemInfo("user","ram");
		 
	 }
	 
        public void onTestSuccess(ITestResult result) {
        	test=extent.createTest(result.getName());
        	test.assignCategory(result.getMethod().getGroups());
        	test.createNode(result.getName());
        	test.log(Status.PASS, "Test Passed");
        }
        
        public void onTestFailure(ITestResult result) {
        	test=extent.createTest(result.getName());
        	test.assignCategory(result.getMethod().getGroups());
        	test.createNode(result.getName());
        	test.log(Status.FAIL, "Test Failed");
        	test.log(Status.FAIL, result.getThrowable().getMessage());
        }
        
        public void onTestSkipped(ITestResult result) {
        	test=extent.createTest(result.getName());
        	test.assignCategory(result.getMethod().getGroups());
        	test.createNode(result.getName());
        	test.log(Status.SKIP, "Test Skipped");
        	test.log(Status.SKIP, result.getThrowable().getMessage());
}
        public void onFinish(ITestContext testContext) {
        	extent.flush();
        }
        
}


package testrunners;

import io.cucumber.testng.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@CucumberOptions(
        plugin = {"pretty",
                "json:target/cucumber-reports/CucumberTestReport.json",
                "html:target/cucumber-reports/cucumber-pretty",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
				"timeline:test-output-thread/"
				},
        monochrome = true,
		glue = { "StepDefnitions" },
		features = { "src/test/resources/features" }
)

public class ParallelRun extends AbstractTestNGCucumberTests {
//	@Override
//	@DataProvider(parallel = true)
//	public Object[][] scenarios() {
//		return super.scenarios();
//	}
	
	private TestNGCucumberRunner testNGCucumberRunner;
	private static final String FILE_NAME =System.getProperty("user.dir")+"\\src\\test\\resources"+"\\testdata\\Book.xlsx";

    @BeforeClass(alwaysRun = true)
    public void setUpClass() {
//    	ExceltoFeatures.GenerateFeatureFiles(FILE_NAME);
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }

    @Test(groups = "cucumber", description = "Runs Cucumber Feature", dataProvider = "features")
    public void feature(PickleWrapper pickle, FeatureWrapper cucumberFeature) {
    	 testNGCucumberRunner.runScenario(pickle.getPickle());
    }
    

    @DataProvider(parallel = true)
    public Object[][] features() {
    	return testNGCucumberRunner.provideScenarios();
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() {
        testNGCucumberRunner.finish();
//        EmailReports.Email_Report();
    }
}
package pk_Appium;

import org.testng.annotations.DataProvider;

public class AppTestData {

	@DataProvider(name = "EriBank_MakePayment")
	public Object[][] getDataforEriBank() {
		// Multidimensional Object
		// 3X3 or 4X3 or 4X5
		return new Object[][] {

				{"111111111", "Abhi","10","India"},
				{"222222222", "Priya","15","USA"},
				{"333333333", "Deepika","20","Norway"},
				{"222222222", "Sonali","5","Italy"}
				};

	}
	@DataProvider(name = "MyDemo_Login_Scenario")
	public Object[][] getDataforMyDemoLogin() {
		// Multidimensional Object
		// 3X3 or 4X3 or 4X5
		return new Object[][] {

				{"bob@example.com", "102030401","Provided credentials do not match any user in this service."},
				{"bob1@example.com", "10203040","Provided credentials do not match any user in this service."},
				{"bob@example.com", "","Password is required"},
				{"", "10203040","Username is required"},
				{"bob@example.com", "10203040","Products"}
				};

	}
}

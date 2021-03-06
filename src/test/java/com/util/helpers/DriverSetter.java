package com.util.helpers;


import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;

public class DriverSetter
{
	PropertyReader properties = new PropertyReader();

	public DriverSetter()
	{
		System.out.println("User home directory is :--> \" "+System.getProperty("user.home")+" \" ");
	}

	public WebDriver setDriver()
	{
		if(System.getProperty("Browser")==null){
			System.setProperty("Browser", properties.readProperty("platform"));
		}
		System.out.println("ForkNumber\t"+System.getProperty("Browser"));	
		switch(System.getProperty("Browser"))
		{
		case "chrome":
			System.setProperty("webdriver.chrome.driver", "D:\\BrowserGit\\ProView-Browser-Automated-Tests\\drivers\\chromedriver1.exe");
			//HashMap<String, Object> plugin = new HashMap<String, Object>();
			//plugin.put("enabled", false);
			//plugin.put("name", "Chrome PDF Viewer");

			HashMap<String, Object> prefs = new HashMap<String, Object>();
			//prefs.put("plugins.plugins_list", Arrays.asList(plugin));	
			prefs.put("profile.default_content_settings.popups", 0);
			prefs.put("download.prompt_for_download", "false");
			prefs.put("download.default_directory", System.getProperty("user.home")+"\\Downloads");

			ChromeOptions options =new ChromeOptions();
			options.addArguments("disable-infobars");  // added by chari
			options.setExperimentalOption("prefs", prefs);
			options.setHeadless(true);
			return new ChromeDriver(options);

		case "firefox":
			System.setProperty("webdriver.gecko.driver", "drivers\\geckodriver.exe");
			FirefoxProfile ffProfile = new FirefoxProfile();
			ffProfile.setPreference("browser.download.useDownloadDir", true);
			ffProfile.setPreference("browser.download.dir", System.getProperty("user.home")+"\\Downloads");
			ffProfile.setPreference("browser.download.folderList", 2);
			ffProfile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/zip, application/msword, application/pdf, application/vnd.openxmlformats-officedocument.wordprocessingml.document"); 
			//ffProfile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf"); 
			ffProfile.setPreference( "browser.download.manager.showWhenStarting", false );
			ffProfile.setPreference( "pdfjs.disabled", true );

			FirefoxOptions firefoxOptions= new FirefoxOptions();
			firefoxOptions.setProfile(ffProfile);
			firefoxOptions.setCapability(FirefoxDriver.MARIONETTE, true);
			
			firefoxOptions.setHeadless(true);
			
			return new FirefoxDriver(firefoxOptions);

		case "IE":

			DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
			caps.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
			System.setProperty("webdriver.ie.driver", "D:\\BrowserGit\\ProView-Browser-Automated-Tests\\drivers\\IEDriverServer.exe");
			return new InternetExplorerDriver(caps);

		case "mac":
			return new ChromeDriver();

		case "safari":
			return new SafariDriver();

		case "windows": //this isn't ready so the driver hasn't been downloaded or added to the project yet. If work on the webclient ever works then this will be need to reworked.
			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\UC202647\\AppData\\Local\\Thomson Reuters\\ProView\\chromedriver2_server.exe");
			return new ChromeDriver();

		default:
			return new FirefoxDriver();
		}
	}
}
package in.westerncoal;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.interactions.PointerInput.MouseButton;
import org.openqa.selenium.interactions.internal.MouseAction;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ch.qos.logback.core.joran.action.Action;
import io.appium.java_client.windows.WindowsDriver;
import io.appium.java_client.windows.WindowsElement;

@SpringBootApplication
public class WinGuiAutomationApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(WinGuiAutomationApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("app", "Root");
		capabilities.setCapability("platformName", "Windows");
		capabilities.setCapability("deviceName", "WindowsPC");
		WindowsDriver<WebElement> DesktopSession = new WindowsDriver<WebElement>(new URL("http://127.0.0.1:4723"),
				capabilities);
		DesktopSession.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		int handle = Integer
				.parseInt(DesktopSession.findElementByName("*Untitled - Notepad").getAttribute("NativeWindowHandle"));
		DesiredCapabilities capabilities1 = new DesiredCapabilities();
		capabilities1.setCapability("appTopLevelWindow", Integer.toHexString(handle));
		capabilities1.setCapability("platformName", "Windows");
		capabilities1.setCapability("deviceName", "WindowsPC");
		WindowsDriver<WebElement> notepadSession = new WindowsDriver<WebElement>(new URL("http://127.0.0.1:4723"),
				capabilities1);
		notepadSession.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		// notepadSession.manage().window().maximize();
		notepadSession.findElementByName("File").click();
		notepadSession.findElementByName("Print...").click();
		//notepadSession.findElementByAccessibilityId("12320").click();
		List<WebElement> listItems=notepadSession.findElementsByXPath("//List[@ClassName=\"SysListView32\"]//ListItem");
		listItems.forEach(listItem->System.out.println(listItem.getText()));
		notepadSession.findElementByXPath("//Button[@ClassName=\"Button\"][@Name=\"Preferences\"]").click();
		notepadSession.findElementByAccessibilityId("1232").click();
		WebElement comboBox =notepadSession.findElementByXPath("//ComboBox[@ClassName=\"ComboBox\"][@Name=\"Orientation: \"]//ListItem[@Name=\"Portrait\"]");
		comboBox.sendKeys(Keys.ENTER);
		WebElement okBtn = notepadSession.findElementByXPath("//Button[@Name=\"OK\"]");
		okBtn.click();
		WebElement prtBtn = notepadSession.findElementByXPath("//Button[@Name=\"Print\"]");
		prtBtn.click();
		notepadSession.findElementByXPath("//ComboBox[@Name=\"File name:\"][@AutomationId=\"FileNameControlHost\"]").sendKeys("a.pdf");
		WebElement saveBtn = notepadSession.findElementByXPath("//Button[@Name=\"Save\"]");
		saveBtn.click();
		while(true) {
		try {
		WebElement cancelBtn = notepadSession.findElementByXPath("//Button[@Name=\"Cancel\"]");
		Thread.sleep(5000);
		}catch(Exception e) {
			break;
		}
		}
//		notepadSession.findElementByName("File").click();
//		notepadSession.findElement(By.name("Page Setup...")).click();
//		notepadSession.findElementByAccessibilityId("1137").click();
//		List<WebElement> listItems = notepadSession
//				.findElementsByXPath("//List[@ClassName=\"ComboLBox\"][@Name=\"Size:\"]//ListItem");
//		List<String> comboBoxListItems = new ArrayList();
//		listItems.forEach(listItem -> comboBoxListItems.add(listItem.getText()));
//		System.out.println("ComboBox List Count: " + comboBoxListItems.size());
//		WebElement listItem =notepadSession
//		.findElementByXPath("//List[@ClassName=\"ComboLBox\"][@Name=\"Size:\"]//ListItem[@Name=\"A4\"]");
// 		Actions s = new Actions(notepadSession);
// 		s.moveToElement(listItem).click().build().perform();
//		
//	//	listItem.sendKeys(Keys.ENTER);
//		WebElement radioBtn =notepadSession.findElementByAccessibilityId("1057");
//		radioBtn.click();
//		WebElement okBtn = notepadSession.findElementByXPath("//Button[@Name=\"OK\"]");
//		okBtn.click();
//		
		// List[@ClassName=\"ComboLBox\"][@Name=\"Size:\"]/ListItem[@Name=\"A4\"]"
		// WebElement comboBox = notepadSession.findElementByClassName("ComboBox");
		// comboBox.click();
//	comboBox.sendKeys(Keys.HOME);
//	System.out.println(comboBox.getSize());
//	String prevVal = "";
//	String curVal = "";
//	 
//	for(String element: new String[] {"A1","A2","A3","A4"})
//		try{
//			System.out.println(comboBox.findElement(By.name(element))+" found");
//		}catch(NoSuchElementException e) {
//			System.out.println("Element: "+element+" not found");
//		}
//		//listItem.sendKeys(Keys.ENTER);
//		//comboBox.sendKeys("A4");
//		// System.out.println(s);//("1137").findElements(By.className("list item")));
//		driver.quit();
	}
}

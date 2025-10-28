package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MenuPage {
    private WebDriver driver;

    private By menuButton = By.id("react-burger-menu-btn");
    private By logoutLink = By.id("logout_sidebar_link");

    public MenuPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openMenu() {
        driver.findElement(menuButton).click();
    }

    public void logout() {
        driver.findElement(logoutLink).click();
    }
}

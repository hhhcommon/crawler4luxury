package absCompone;

import org.openqa.selenium.WebDriver;

/**
 * @Author: yang
 * @Date: 2018/1/2.14:03
 * @Desc: to do?
 */
public interface BasePool {

    /**
     * add webDriver
     *
     * @param id
     * @param webDriver
     */
    void addDriver(String id, WebDriver webDriver);


    /**
     * 获取 WebDriver
     *
     * @param id
     * @return
     */
    WebDriver getDriverById(String id);

}

package runnerFiles;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"html:target/Reports/WeatherReport.html", "json:target/Reports/WeatherReport.json", "junit:target/Reports/WeatherReport.xml"},
features= {"src/test/resources/features"},tags= "@test")
public class RunnerClass {

}

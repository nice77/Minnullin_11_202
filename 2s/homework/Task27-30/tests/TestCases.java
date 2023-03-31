import org.junit.Assert;
import org.junit.Test;

public class TestCases {
    @Test
    public void getLastNamesBeforeYear() {
        Assert.assertEquals(AnalyticsService.getNumberByYear(DB.getInstance("input.txt", "subscriptions.txt"), 1969), 2);
    }

    @Test
    public void getLastNamesBeforeYearClassic() {
        Assert.assertEquals(AnalyticsService.getNumberByYearClassical(DB.getInstance("input.txt", "subscriptions.txt"), 1969), 2);
    }
}

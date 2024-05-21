package services.reports;

import models.CloudData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import services.parsers.CloudDataParser;

import java.util.List;
import java.util.Set;

public class CloudMetricsTest {

    static CloudMetrics metrics;

    @BeforeAll
    public static void setup() {
        String input = "2343225,2345,us_east,RedTeam,ProjectApple,3445s\n" +
                "1223456,2345,us_west,BlueTeam,ProjectBanana,2211s\n" +
                "3244332,2346,eu_west,YellowTeam3,ProjectCarrot,4322s\n" +
                "1233456,2345,us_west,BlueTeam,ProjectDate,2221s\n" +
                "3244132,2346,eu_west,YellowTeam3,ProjectEgg,4122s";
        CloudDataParser cloudDataParser = new CloudDataParser();
        List<CloudData> cloudDataList = cloudDataParser.parseData(input);
        metrics = new CloudMetrics(cloudDataList);
    }

    @Test
    public void getUniqueCustomerIdCountForEachContractIdShouldReturnCorrectCounts() {
        Assertions.assertEquals(3, metrics.getUniqueCustomerIdCountFor(CloudMetrics.CONTRACT_ID).get("2345"));
        Assertions.assertEquals(2, metrics.getUniqueCustomerIdCountFor(CloudMetrics.CONTRACT_ID).get("2346"));
    }

    @Test
    public void getUniqueCustomerIdCountForEachGeozoneShouldReturnCorrectCounts() {
        Assertions.assertEquals(1, metrics.getUniqueCustomerIdCountFor(CloudMetrics.GEOZONE).get("us_east"));
        Assertions.assertEquals(2, metrics.getUniqueCustomerIdCountFor(CloudMetrics.GEOZONE).get("us_west"));
        Assertions.assertEquals(2, metrics.getUniqueCustomerIdCountFor(CloudMetrics.GEOZONE).get("eu_west"));
    }

    @Test
    public void getAvgBuidDurationForEachGeozoneShouldReturnCorrectAvgs() {
        Assertions.assertEquals(Double.parseDouble("3445"),metrics.getAvgBuidDurationForEachGeozone().get("us_east"));
        Assertions.assertEquals(Double.parseDouble("2216"),metrics.getAvgBuidDurationForEachGeozone().get("us_west"));
        Assertions.assertEquals(Double.parseDouble("4222"),metrics.getAvgBuidDurationForEachGeozone().get("eu_west"));
    }

    @Test
    public void testGetUniqueCustomerIdsForEachGeozoneShouldReturnUniqueCustomerIdsForEachGeozone() {
        Assertions.assertEquals(Set.of("2343225"), metrics.getUniqueCustomerIdsForEachGeozone().get("us_east"));
        Assertions.assertEquals(Set.of("1223456", "1233456"), metrics.getUniqueCustomerIdsForEachGeozone().get("us_west"));
        Assertions.assertEquals(Set.of("3244132", "3244332"), metrics.getUniqueCustomerIdsForEachGeozone().get("eu_west"));
    }
}

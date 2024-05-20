package services.parsers;

import models.CloudData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.List;

public class CloudDataParserTest {
    static String input;

    @BeforeAll
    public static void setup() {
        input = "2343225,2345,us_east,RedTeam,ProjectApple,3445s\n" +
                "1223456,2345,us_west,BlueTeam,ProjectBanana,2211s\n" +
                "3244332,2346,eu_west,YellowTeam3,ProjectCarrot,4322s\n" +
                "1233456,2345,us_west,BlueTeam,ProjectDate,2221s\n" +
                "3244132,2346,eu_west,YellowTeam3,ProjectEgg,4122s";
    }

    @Test
    public void parseDataShouldReturnListOf5CloudDataObjects() {
        CloudDataParser cloudDataParser = new CloudDataParser();

        List<CloudData> cloudDataList = cloudDataParser.parseData(input);
        Assertions.assertEquals(5, cloudDataList.size());
    }

    @Test
    public void parseDataShouldReturnCorrectCustomerIdForFirstCloudDataObject() {
        CloudDataParser cloudDataParser = new CloudDataParser();

        List<CloudData> cloudDataList = cloudDataParser.parseData(input);

        // get `customerId` of the second cloudData object in cloudDataList
        Assertions.assertEquals("2343225", cloudDataList.get(0).getField(CloudDataParser.HEADERS[0]));
    }

    @Test
    public void parseDataShouldReturnCorrectBuildDurationForLastCloudDataObject() {
        CloudDataParser cloudDataParser = new CloudDataParser();

        List<CloudData> cloudDataList = cloudDataParser.parseData(input);

        // get `buildduration` of the fifth cloudData object in cloudDataList
        Assertions.assertEquals("4122s", cloudDataList.get(4).getField(CloudDataParser.HEADERS[5]));
    }
}

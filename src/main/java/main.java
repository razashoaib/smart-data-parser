import models.CloudData;
import services.parsers.CloudDataParser;
import services.reports.CloudMetrics;

import java.util.List;

public class main {
    public static void main(String[] args) {
        String input = "2343225,2345,us_east,RedTeam,ProjectApple,3445s\n" +
                "1223456,2345,us_west,BlueTeam,ProjectBanana,2211s\n" +
                "3244332,2346,eu_west,YellowTeam3,ProjectCarrot,4322s\n" +
                "1233456,2345,us_west,BlueTeam,ProjectDate,2221s\n" +
                "3244132,2346,eu_west,YellowTeam3,ProjectEgg,4122s";

        CloudDataParser cloudDataParser = new CloudDataParser();
        List<CloudData> cloudDataList = cloudDataParser.parseData(input);

        CloudMetrics cloudMetrics = new CloudMetrics(cloudDataList);

        System.out.println("The number of unique `customerId` for each `contractId`:");
        System.out.println(cloudMetrics.getUniqueCustomerIdCountFor(CloudMetrics.CONTRACT_ID));

        System.out.println("The number of unique `customerId` for each `geozone`:");
        System.out.println(cloudMetrics.getUniqueCustomerIdCountFor(CloudMetrics.GEOZONE));

        System.out.println("The list of unique `customerId` for each `geozone`:");
        System.out.println(cloudMetrics.getUniqueCustomerIdsForEachGeozone());

        System.out.println("The average `buildduration` for each `geozone`:");
        System.out.println(cloudMetrics.getAvgBuidDurationForEachGeozone());
    }
}

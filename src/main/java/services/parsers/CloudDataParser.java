package services.parsers;

import models.CloudData;

import java.util.*;

public class CloudDataParser {
    public static final String[] HEADERS = {
            "customerId",
            "contractId",
            "geozone",
            "teamcode",
            "projectcode",
            "buildduration"
    };

    public List<CloudData> parseData(String inputData) {
        List<CloudData> cloudDataList = new ArrayList<>();

        String[] inputLines = inputData.split("\n");

        for (String inputLine : inputLines) {
            String[] fields = inputLine.split(",");

            if(fields.length == HEADERS.length) {
                Map<String, String> cloudDataFields = new LinkedHashMap<>();
                for (int i=0; i< HEADERS.length; i++) {
                    cloudDataFields.put(HEADERS[i], fields[i]);
                }
                cloudDataList.add(new CloudData(cloudDataFields));
            }
        }

        return cloudDataList;
    }
}

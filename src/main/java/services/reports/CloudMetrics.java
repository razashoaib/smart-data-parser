package services.reports;

import models.CloudData;

import java.util.*;
import java.util.stream.Collectors;

public class CloudMetrics {
    public final static String CONTRACT_ID = "contractId";
    public final static String GEOZONE = "geozone";
    public final static String BUILD_DURATION = "buildduration";
    public final static String CUSTOMER_ID = "customerId";

    private final List<CloudData> cloudDataList;

    public CloudMetrics(List<CloudData> cloudDataList) {
        this.cloudDataList = cloudDataList;
    }

    // Gets the number of unique `customerId` for each `contractId`
    // Gets the number of unique `customerId` for each `geozone`
    public Map<String, Integer> getUniqueCustomerIdCountFor(String groupBy) {
        Map<String, Integer> customerMap = new HashMap<>();

        if (Objects.equals(groupBy, CONTRACT_ID) || Objects.equals(groupBy, GEOZONE)) {
            this.cloudDataList.stream().collect(
                            Collectors.groupingBy(
                                    cloudData -> cloudData.getField(groupBy),
                                    Collectors.mapping(cloudData -> cloudData.getField(CUSTOMER_ID),
                                            Collectors.toSet()
                                    )
                            )
                    ).
                    forEach((k, v) -> customerMap.put(k, v.size()));
        }

        return customerMap;
    }

    // Gets the average `buildduration` for each `geozone`
    public Map<String, Double> getAvgBuidDurationForEachGeozone() {

        return this.cloudDataList.stream().collect(
                Collectors.groupingBy(cloudData -> cloudData.getField(GEOZONE),
                        Collectors.averagingDouble(cloudData -> Double.parseDouble(
                                        cloudData.getField(BUILD_DURATION).
                                                replace("s", "")
                                )
                        )
                )
        );
    }

    // The list of unique `customerId` for each `geozone`
    public Map<String, Set<String>> getUniqueCustomerIdsForEachGeozone() {
        Map<String, Set<String>> result;

        result = this.cloudDataList.stream().collect(
                Collectors.groupingBy(cloudData -> cloudData.getField(GEOZONE),
                        Collectors.mapping(cloudData -> cloudData.getField(CUSTOMER_ID),
                                Collectors.toSet()
                        )
                )
        );

        return result;
    }
}

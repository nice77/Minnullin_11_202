package client;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class Service {
    public static Map<String, String> parser(String event) {
        return Arrays.stream(event.split("&"))
                .map(item -> item.split("="))
                .collect(Collectors.toMap(item -> item[0], item -> item[1]));
    }
}

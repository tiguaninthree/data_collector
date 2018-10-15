package grabber.common;

import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collection;
import java.util.Map;

public class Utils {

    public static boolean isEmpty(Object value) {
        if (value == null) {
            return true;
        } else if (value instanceof String) {
            return ((String) value).isEmpty();
        } else if (value instanceof Map) {
            return ((Map) value).isEmpty();
        } else if (value instanceof Collection) {
            return ((Collection) value).isEmpty();
        } else {
            return false;
        }
    }

    public static String replaceQueryParameter(String url, String paramName, int value) {
        UriComponentsBuilder urlBuilder = UriComponentsBuilder.fromUriString(url);
        urlBuilder.replaceQueryParam(paramName, value);
        String result = urlBuilder.build().toUriString();
        return result;
    }


    public static String nullableReplace(String value, String replaceString) {
        if (value != null) {
            if (!value.isEmpty()) {
                value = value.replace(replaceString, "").trim();
            } else {
                return null;
            }
        }
        return value;
    }

}

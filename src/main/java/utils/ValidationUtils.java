package utils;

public class ValidationUtils {

    public static int validatePage(String page) {
        if (page == null || page.isEmpty()) {
            return 1;
        } else {
            return Integer.parseInt(page.trim());
        }
    }
}

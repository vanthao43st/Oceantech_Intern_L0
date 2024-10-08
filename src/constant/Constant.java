package constant;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

public class Constant {
    public static final int MAX_NAME_LENGTH = 100;
    public static final int MIN_YEAR = 1900;
    public static final int MAX_YEAR = LocalDateTime.now().getYear();
    public static final int MAX_ADDRESS_LENGTH = 300;
    public static final int MIN_HEIGHT = 50;
    public static final int MAX_HEIGHT = 300 ;
    public static final int MIN_WEIGHT = 5;
    public static final int MAX_WEIGHT = 1000;

    public static final int STUDENT_ID_LENGTH = 10;
    public static final int MAX_SCHOOL_NAME_LENGTH = 200;
    public static final double MIN_GPA = 0;
    public static final double MAX_GPA = 10;
    public static final int MAX_STUDENT = 100;
    public static final Pattern DATE_PATTERN = Pattern.compile("\\d{2}/\\d{2}/\\d{4}");

    public static final String STATIC_FILENAME = "Static_StudentLists.txt";
    public static final String DYNAMIC_FILENAME = "Dynamic_StudentLists.txt";
}

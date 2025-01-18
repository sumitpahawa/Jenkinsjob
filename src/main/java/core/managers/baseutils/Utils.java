package core.managers.baseutils;

public class Utils {
    private static Utils utils = null;
    public TestInfo testInfo;
    public DateTime dateTime;
    public MiscUtils miscUtils;


    private Utils() {
        testInfo = new TestInfo();
        dateTime = new DateTime();
        miscUtils = new MiscUtils();
    }

    public static Utils getInstance() {
        if (utils == null) {
            utils = new Utils();
        }
        return utils;
    }


}
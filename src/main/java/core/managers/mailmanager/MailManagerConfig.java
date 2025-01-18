package core.managers.mailmanager;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.net.MalformedURLException;
import java.net.URL;

public class MailManagerConfig {

    private static MailManagerConfig instance;
    private Config mailConf;

    protected MailManagerConfig() {
        mailConf = ConfigFactory.load(MailManagerConfig.class.getClassLoader(), System.getProperty("mail.properties", "propfiles/mailmanager.properties"));
    }

    public static synchronized MailManagerConfig getInstance() {
        if (instance == null) {
            instance = new MailManagerConfig();
        }
        return instance;
    }

    public static String getString(String key) {
        return MailManagerConfig.getInstance().mailConf.getString(key);
    }

    public static int getInt(String key) {
        return MailManagerConfig.getInstance().mailConf.getInt(key);
    }

    public static boolean getBoolean(String key) {
        return MailManagerConfig.getInstance().mailConf.getBoolean(key);
    }

    public static URL getUrl(String key) throws MalformedURLException {
        return new URL(MailManagerConfig.getInstance().mailConf.getString(key));
    }

    public void sendMail() {

    }
}

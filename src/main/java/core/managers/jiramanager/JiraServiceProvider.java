/*
package core.managers.jiramanager;

import net.rcarz.jiraclient.*;
import net.rcarz.jiraclient.Issue.FluentCreate;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Comparator;

public class JiraServiceProvider {

    private JiraClient jira;
    private String project;

    public JiraServiceProvider(String jiraUrl, String username, String password, String project) {
        // create basic authentication object
        BasicCredentials creds = new BasicCredentials(username, password);
        // initialize the core.managers.jira client with the url and the credentials
        jira = new JiraClient(jiraUrl, creds);
        this.project = project;
    }

    public static void main(String[] args) {
        JiraServiceProvider jp = new JiraServiceProvider("https://tmtestauto.atlassian.net/", "ashok.kolli@techmahindra.com", "t274mJkhic7eAyzHYXzy4F6F", "TES");
        jp.createJiraIssue("Bug", "issueSummary", "issueDescription");
    }

    public static File getLastModifiedFile(File directory, String suffix) {
        //find last modified files in folder
        File[] files = directory.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getName().toLowerCase().endsWith(suffix) || pathname.isDirectory();
            }
        });
        if (files.length == 0)
            return null;
        if (files.length == 1)
            return files[0];
        Arrays.sort(files, new Comparator<File>() {
            public int compare(File o1, File o2) {
                return new Long(o2.lastModified()).compareTo(o1.lastModified());
            }
        });
        return files[0];
    }

    public void createJiraIssue(String issueType, String summary, String description) {
        */
/* Create a new issue. *//*


        try {
            FluentCreate newIssueFluentCreate = jira.createIssue(project, issueType);
            // Add the summary
            newIssueFluentCreate.field(Field.SUMMARY, summary);
            // Add the description
            newIssueFluentCreate.field(Field.DESCRIPTION, description);
            // Add the reporter
            //newIssueFluentCreate.field("REPORTER", reporterName);
            // create the issue in the core.managers.jira server
            Issue newIssue = newIssueFluentCreate.execute();
            // Add attachment to new created issue
            File[] filePNG = getRecentFile(System.getProperty("user.dir") + "/test-output", ".png");
            newIssue.addAttachment(filePNG[0]);
            newIssue.addAttachment(getLastModifiedFile(new File(System.getProperty("user.dir") + "/LOGCAT-FAILS-MO"), ".txt"));
            newIssue.addAttachment(getLastModifiedFile(new File(System.getProperty("user.dir") + "/LOGCAT-FAILS-MT"), ".txt"));
        } catch (JiraException e) {
            e.printStackTrace();
        }
    }

    public File[] getRecentFile(String dirPath, String suffix) {
        //find last 2 recent file in folder
        File[] last2 = new File[3];
        FilenameFilter pngFilter = new FilenameFilter() {
            public boolean accept(File file, String name) {
                if (name.endsWith(suffix)) {
                    // filters files whose extension is .png
                    return true;
                } else {
                    return false;
                }
            }
        };
        File dir = new File(dirPath);
        File[] files = dir.listFiles(pngFilter);
        if (files.length == 0) {
            System.out.println("There is no files");
        } else {
            int len = files.length;
            for (File aFile : files) {
                if (aFile.getName().contains(Integer.toString(len))) {
                    last2[0] = aFile;
                }
                int len2 = len - 1;
                if (aFile.getName().contains(Integer.toString(len2))) {
                    last2[1] = aFile;
                }
                if (last2.length == 2)
                    break;
            }
        }
        return last2;
    }
}*/

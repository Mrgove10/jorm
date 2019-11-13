package classes;

import java.util.Date;

public class Album {
    public String Members;
    public String Title;
    public Date DateRelease;

    public Album(String members, String title, Date dateRelease) {
        Members = members;
        Title = title;
        DateRelease = dateRelease;
    }

    public String getMembers() {
        return Members;
    }

    public void setMembers(String members) {
        Members = members;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public Date getDateRelease() {
        return DateRelease;
    }

    public void setDateRelease(Date dateRelease) {
        DateRelease = dateRelease;
    }
}

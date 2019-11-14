package classes;

import java.io.Serializable;
import java.util.Date;

public class Album implements Serializable {
    public Integer Id;
    public String Members;
    public String Title;
    public Date DateRelease;

    public Album(Integer id, String members, String title, Date dateRelease) {
        Id = id;
        Members = members;
        Title = title;
        DateRelease = dateRelease;
    }
}

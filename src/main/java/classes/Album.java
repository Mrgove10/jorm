package classes;

import logging.logger;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

public class Album implements Serializable {
    public Integer Id;
    public String Members;
    public String Title;
    public Date DateRelease;

    public Album(Integer id, String members, String title, Date dateRelease) throws IOException {
        Id = id;
        Members = members;
        Title = title;
        DateRelease = dateRelease;
    }
}

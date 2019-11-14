package logging;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class logger {
    public enum Severity{
        Debug,
        Warning,
        Error
    }
    public void AddLog(Severity severity, String message) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/java/files/Logs.log", true));
        String str = "";
        switch (severity){
            case Debug:
                str += "DEBUG | ";
                break;
            case Warning:
                str += "WARN  | ";
                break;
            case Error:
                str += "ERROR | ";
                break;
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss.SSS");
        LocalDateTime now = LocalDateTime.now();
        str += dtf.format(now) +" | "; //adds th time
        str += message +"\n";
        writer.append(str);
        writer.close();
    }
}

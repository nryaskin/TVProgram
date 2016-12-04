package MyPackage;

/**
 * Created by Никита on 04.12.2016.
 */
public class Program {
    private String chanel, weekday, start_time, genre;
    //private int id;

    public Program() {

    }

    public Program(String chanel, String weekday, String start_time, String genre) {
        //this.id = id;
        this.chanel = chanel;
        this.weekday = weekday;
        this.start_time = start_time;
        this.genre = genre;
    }

    /*public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }*/

    public String getChanel() {
        return chanel;
    }

    public void setChanel(String chanel) {
        this.chanel = chanel;
    }

    public String getWeekday() {
        return weekday;
    }

    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}

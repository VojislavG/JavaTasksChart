import java.util.ArrayList;


public class Tasks{
    int id;
    String taskName;
    int startHour;
    int startMinute;
    int endHour;
    int endMinute;

    ArrayList<Tasks> arrayTasks = new ArrayList<>();



    public Tasks(int id, String taskName, int  startHour,int startMinute,int endHour,int endMinute)
    {
        this.id = id;
        this.taskName= taskName;
        this.startHour = startHour;
        this.startMinute = startMinute;
        this.endHour = endHour;
        this.endMinute = endMinute;
    }

    public Tasks() {

    }


    public ArrayList<Tasks> InsertMeeting (int id, String taskName, int startHour, int startMinute,int endHour, int endMinute)
    {
        arrayTasks.add(new Tasks(id, taskName, startHour, startMinute, endHour, endMinute));
       // id++;
        return  arrayTasks;
    }

    public ArrayList<Tasks> InsertMeeting (Tasks t)
    {
        arrayTasks.add(t);
        id++;
        return  arrayTasks;
    }

    public int getDuration()
    {
        return startHour * 60 + startMinute - endHour * 60 - endMinute;
    }
}

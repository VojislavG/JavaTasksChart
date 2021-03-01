import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.awt.Window;

public class Main extends Tasks{

    private BarChart chart;

    int i = 0;
    Window win = new JDialog();
    JPanel panel = new JPanel();
    JLabel taskNameLabel = new JLabel("Meeting name:");
    JLabel startTimeLabel = new JLabel("Start Time:");
    JLabel endTimeLabel = new JLabel("End Time:");
    JTextField taskNameText = new JTextField(20);
    SpinnerNumberModel modelHourS = new SpinnerNumberModel(0, 0, 23, 1);
    SpinnerNumberModel modelMinS = new SpinnerNumberModel(0, 0, 59, 1);
    SpinnerNumberModel modelHourE = new SpinnerNumberModel(0, 0, 23, 1);
    SpinnerNumberModel modelMinE = new SpinnerNumberModel(0, 0, 59, 1);
    JSpinner startHourSp = new JSpinner(modelHourS);
    JSpinner startMinSp = new JSpinner(modelMinS);
    JSpinner endHourSp = new JSpinner(modelHourE);
    JSpinner endMinSp = new JSpinner(modelMinE);

    JButton addMeeting = new JButton("Add meeting");
    ArrayList<Tasks> events;

    public Main() {
        super();
        win.setSize(800,450);

        panel.add(taskNameLabel);
        panel.add(taskNameText);

        panel.add(startTimeLabel, BorderLayout.NORTH);
        panel.add(startHourSp);
        panel.add(startMinSp);

        panel.add(endTimeLabel, BorderLayout.NORTH);
        panel.add(endHourSp);
        panel.add(endMinSp);

        panel.add(addMeeting, BorderLayout.SOUTH);

        win.add(panel,BorderLayout.BEFORE_FIRST_LINE);

        chart = new BarChart(events, "Tasks", "Time");

        win.add(chart);
        win.setFocusable(true);
        win.setVisible(true);


        addMeeting.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Tasks t = new Tasks(i++, taskNameText.getText(), (Integer) (startHourSp.getValue()), (Integer) (startMinSp.getValue()), (Integer) (endHourSp.getValue()), (Integer) (endMinSp.getValue()));
                ArrayList<Tasks> events = InsertMeeting(t);

                events.sort(new Comparator<Tasks>() {
                    @Override
                    public int compare(Tasks o1, Tasks o2) {
                        if(o1.startHour < o2.startHour) {
                            return -1;
                        }
                        if(o1.startHour > o2.startHour){
                            return 1;
                        }
                        if(o1.startMinute < o2.startMinute){
                            return -1;
                        }
                        if(o1.startMinute > o2.startMinute){
                            return 1;
                        }
                        if (o1.getDuration() < o2.getDuration()){
                            return -1;
                        }
                        if(o1.getDuration() > o2.getDuration()){
                            return 1;
                        }
                        return 0;

                    }
                });
                chart.list = events;
                chart.xLabel = "Tasks";
                chart.yLabel = "Time";
                win.repaint();
            }});

    }


    public static void main (String[]args)
    {
        new Main();

    }

}

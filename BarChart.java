import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Random;


public class BarChart extends JPanel {

    public static final int TOP_BUFFER = 30;
    public static final int AXIS_OFFSET = 20;
    public  ArrayList<Tasks> list;

    private int chartwidth, chartheight, chartX, chartY;

    public  String xLabel, yLabel;

    public BarChart(ArrayList<Tasks> events, String xl, String yl) {
        super();
        this.list = events;
        xLabel = xl;
        yLabel = yl;

    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        computeSize();
        Graphics2D g2 = (Graphics2D) g;

        drawBars(g2);
        drawAxes(g2);
        drawText(g2);
    }

    private void computeSize() {

        int width = 328;
        int height = 220;

        chartwidth = width - 2*AXIS_OFFSET;
        chartheight = height - 2*AXIS_OFFSET - TOP_BUFFER;


        chartX = AXIS_OFFSET;
        chartY = height - AXIS_OFFSET;

    }

    public void drawBars(Graphics2D g2) {

        if (list != null) {
            Color original = g2.getColor();

            double numBars = list.size();  // lenght of array
            double max = 0.;

            for (int i = 0; i <= list.size() - 1; i++) {
                if (max < (list.get(i).endHour * 60 + list.get(i).endMinute)) {
                    max = list.get(i).endHour * 60 + list.get(i).endMinute;
                }
            }

            int barWidth = (int) (chartwidth / numBars);

            int height1, height2, xLeft, yTopLeft;
            int counter = 0;

            for (int i = 0; i <= list.size() - 1; i++) {

                double height2_ = ((list.get(i).endHour * 60 + list.get(i).endMinute) / max) * chartheight;
                double height1_ = ((list.get(i).startHour * 60 + list.get(i).startMinute) / max) * chartheight;

                height2 = (int) height2_;
                height1 = (int) height1_;

                xLeft = AXIS_OFFSET + counter * barWidth;
                yTopLeft = chartY - height2;         //tu ide visina pocetna od bara(zavisi od y ose)
                Rectangle rec = new Rectangle(xLeft, yTopLeft, barWidth, (height2-height1));

                g2.setColor(getRandomColor());
                g2.draw(rec);
                g2.fill(rec);

                counter++;
            }


            g2.setColor(original);
        }
    }

    private void drawAxes(Graphics2D g2) {


        int rightX = chartX + chartwidth;
        int topY = chartY - chartheight;

        g2.drawLine(chartX, chartY, rightX, chartY);

        g2.drawLine(chartX, chartY, chartX, topY);

        g2.drawString(xLabel, chartX + chartwidth/2, chartY + AXIS_OFFSET/2 +3) ;



        Font original = g2.getFont();

        Font font = new Font(null, original.getStyle(), original.getSize());
        AffineTransform affineTransform = new AffineTransform();
        affineTransform.rotate(Math.toRadians(-90), 0, 0);
        Font rotatedFont = font.deriveFont(affineTransform);
        g2.setFont(rotatedFont);
        g2.drawString(yLabel,AXIS_OFFSET/2+3, chartY - chartheight/2);
        g2.setFont(original);


    }

    private void drawText(Graphics2D g2) {
        if (list != null) {
            int size = list.size();

            g2.drawString("Number of tasks: " + size, AXIS_OFFSET + 10, 15);
        }
    }

    private Color getRandomColor() {
        Random rand = new Random();

        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();

        return new Color(r, g, b);
    }

}
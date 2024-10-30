import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.temporal.ChronoUnit;
import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 * Represents a point in space and time, recorded by a GPS sensor.
 *
 * @author Hassan Khawaja Ahmed Roohi
 */
public class Track {

  private ArrayList<Point> pointArrayList;

  // TODO: Create a stub for the constructor
  public Track() throws GPSException {
    this.pointArrayList = new ArrayList<Point>();

  }

  public Track(String filename) throws IOException {
    this.pointArrayList = new ArrayList<Point>();
    readFile(filename);
  }

  // TODO: Create a stub for readFile()
  public void readFile(String filename) throws IOException {
    this.pointArrayList.clear();
    // This is to the filename as input from user
    Scanner scanner = new Scanner(new File(filename));
    // This will skip the first line of the file as that only contain fields
    scanner.nextLine();
    // This is to keep repeating the process until no new line in file
    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();
      // This will store the different values separated by comma into a string
      String[] values = line.split(",");
      // This is to unsure that a string has only 4 values in it
      if (values.length != 4) {
        throw new GPSException("Invalid record in file:" + line);
      }
      // This is to parse the different values into the correct data type
      ZonedDateTime timestamp = ZonedDateTime.parse(values[0], DateTimeFormatter.ISO_DATE_TIME);
      double latitude = Double.parseDouble(values[1]);
      double longitude = Double.parseDouble(values[2]);
      double elevation = Double.parseDouble(values[3]);
      // This will create a point object in the required sequence
      Point point = new Point(timestamp, latitude, longitude, elevation);
      pointArrayList.add(point);
    }
    scanner.close();

  }

  // TODO: Create a stub for add()
  public void add(Point point) {
    this.pointArrayList.add(point);
  }

  // TODO: Create a stub for get()
  public Point get(int index) {
    if (this.pointArrayList.size() < 1) {
      throw new GPSException(null);

    } else {
      if (index < 0 || index >= this.pointArrayList.size()) {
        throw new GPSException(null);
      }
      return this.pointArrayList.get(index);
    }
  }

  // TODO: Create a stub for size()
  public int size() {
    return this.pointArrayList.size();
  }

  // TODO: Create a stub for lowestPoint()
  public Point lowestPoint() throws GPSException {
    Point lowest = this.get(0);
    for (Point point : this.pointArrayList) {
      if (point.getElevation() < lowest.getElevation()) {
        lowest = point;
      }
    }
    return lowest;
  }

  // TODO: Create a stub for highestPoint()
  public Point highestPoint() throws GPSException {
    Point highest = this.get(0);
    for (Point point : this.pointArrayList) {
      if (point.getElevation() > highest.getElevation()) {
        highest = point;
      }
    }
    return highest;
  }

  // TODO: Create a stub for totalDistance()
  public double totalDistance() throws GPSException {
    // This is to check that there are enough points to calculate a distance
    if (this.size() < 2) {
      throw new GPSException("Not enough points to compute total distance.");
    }
    double totalDistance = 0;
    // This will iterate until the last point has been reached
    for (int i = 1; i < this.size(); i++) {
      // This refers to previous point in the array list
      Point prevPoint = this.get(i - 1);
      // This refers to the current point in the array list
      Point currPoint = this.get(i);
      // This calculates the distance by passing the current point
      // and previous point into the greatCircleDistance
      double distance = Point.greatCircleDistance(prevPoint, currPoint);
      totalDistance += distance;
    }
    return totalDistance;
  }

  // TODO: Create a stub for averageSpeed()
  public double averageSpeed() throws GPSException {
    // This is to check that there are enough points to calculate a distance
    if (this.size() < 2) {
      throw new GPSException("Not enough points to compute average speed.");
    }
    // This refers to the first point
    Point startPoint = this.get(0);
    // This refers to the last point
    Point endPoint = this.get(this.size() - 1);
    double totalDistance = this.totalDistance();
    // This is to get the time lap between start and end point
    long timeElapsed = startPoint.getTime().until(endPoint.getTime(), ChronoUnit.SECONDS);
    return totalDistance / timeElapsed;
  }

  // Stub for KML
  public void writeKML(String filename) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
      writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
      writer.write("<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n");
      writer.write("<Document>\n");
      // This is to set a style for the line
      writer.write("<Style id=\"blue\">\n");
      writer.write("  <LineStyle>\n");
      writer.write("    <color>ffff0000</color>\n");
      writer.write("    <width>4</width>\n");
      writer.write("  </LineStyle>\n");
      writer.write("</Style>");
      // write track data as KML placemarks
      writer.write("<Placemark>\n");
      writer.write("<styleUrl>#blue</styleUrl>\n");
      writer.write("<LineString>\n");
      writer.write("<coordinates>");
      // This is feed the coordinates from the points in the correct sequence
      for (Point point : pointArrayList) {
        writer.write(point.getLongitude() + "," +
            point.getLatitude() + "," + point.getElevation() + "\n");
      }
      writer.write("</coordinates>\n");
      writer.write("</LineString>\n");
      writer.write("</Placemark>\n");
      writer.write("</Document>\n");
      writer.write("</kml>\n");
      // flush the buffer to ensure data is written to file
      writer.flush();
    } catch (IOException e) {
      System.err.println("Error writing KML file: " + e.getMessage());
      System.exit(0);
    }
  }

}

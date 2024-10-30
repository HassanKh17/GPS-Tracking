import java.io.IOException;

/**
 * Program to provide information on a GPS track stored in a file.
 *
 * @author Hassan Khawaja Ahmed Roohi
 */
public class TrackInfo {
  public static void main(String[] args) throws IOException {
    // TODO: Implementation TrackInfo application here
    // This checks if a filename is provided or not via command line
    if (args.length == 0) {
      System.err.println("Error: No file provided");
      System.exit(0);
    }
    Track t = new Track(args[0]);
    System.out.println(t.size() + " points in track");
    System.out.println("Lowest point is " + t.lowestPoint());
    System.out.println("Highest point is " + t.highestPoint());
    System.out.println("Total Distance= "
        + String.format("%.3f", t.totalDistance() / 1000) + " km");
    System.out.println("Average Speed= " + String.format("%.3f", t.averageSpeed()) + " m/s");

  }
}

import java.io.IOException;

/**
 * Program to general a KML file from GPS track data stored in a file,
 * for the Advanced task of COMP1721 Coursework 1.
 *
 * @author Hassan Khawaja Ahmed Roohi
 */
public class ConvertToKML {
  public static void main(String[] args) {
    // OPTIONAL: implenent the ConvertToKML application here
    // This is to check if two command line arguments have been passed or not
    if (args.length != 2) {
      System.err.println("Format: ConvertToKML <input-file> <output-file>");
      System.exit(0);
    }
    // The first command line argument would be taken as the filename
    // from which the points are required
    String inputFilename = args[0];
    // The second command line argument as the filename for the output
    // file that is produced
    String outputFilename = args[1];
    // This will write the KML file and handle the Exceptions
    try {
      Track track = new Track(inputFilename);
      track.writeKML(outputFilename);
    } catch (IOException e) {
      System.err.println("Error reading input file: " + e.getMessage());
      System.exit(0);
    } catch (NumberFormatException e) {
      System.err.println("Error parsing input file: " + e.getMessage());
      System.exit(0);
    }
  }
}

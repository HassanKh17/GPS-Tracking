# Elevation Plotter

## Overview

The Elevation Plotter is a Java application that reads GPS track data from a specified file, processes the data, and visualizes the elevation changes over distance using a line chart. This application is useful for visualizing the elevation profile of a track, which can be beneficial for outdoor activities such as hiking, cycling, or running.

## Features

- Reads GPS data from a CSV file, including timestamps, longitude, latitude, and elevation.
- Computes the total distance of the track and identifies the highest and lowest points.
- Calculates the average speed over the track.
- Displays an elevation profile as a line chart, plotting elevation against distance.

## Requirements

- Java Development Kit (JDK) 11 or higher
- JavaFX library for GUI components

## Getting Started

1. **Clone the Repository**
   ```bash
   git clone https://github.com/yourusername/your-repo.git
   cd your-repo
   ```
2. **Build the Project**
   ```bash
    mvn install
   ```
3. **Run the Application** To run the application, provide the path to the GPS data file as a command-line argument:
   ```bash
    java -jar target/elevation-plotter.jar path/to/your/datafile.csv
   ```
   Replace `path/to/your/datafile.csv` with the actual path to your CSV file.

## File Format

The GPS data file should be in CSV format with the following structure:

```bash
timestamp,longitude,latitude,elevation
2024-01-01T00:00:00Z,lon1,lat1,elev1
2024-01-01T00:01:00Z,lon2,lat2,elev2
...
```
### Example:

```bash
timestamp,longitude,latitude,elevation
2024-01-01T00:00:00Z,-120.12345,35.12345,150
2024-01-01T00:01:00Z,-120.12350,35.12350,160
...
```

## Exception Handling

The application uses a custom `GPSException` to handle errors related to GPS data, such as:

- Invalid data format in the input file.
- Out-of-bounds coordinates (longitude and latitude).
- Insufficient data points to compute distance or elevation statistics.

## Classes Overview

- TrackInfo: The main class that initializes the application and processes the input file.
- Track: Represents a collection of Point objects, manages reading the data file, and performs calculations.
- Point: Represents an individual GPS data point, containing methods for distance calculations.
- PlotApplication: A JavaFX application that visualizes the elevation profile as a line chart.
- GPSException: Custom exception for handling GPS-related errors.

## Contributing
If you would like to contribute to this project, feel free to submit a pull request or open an issue for discussion.


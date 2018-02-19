package hashcodeStreamingVideos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileHandler {

    public static void openFile() {
        try {
            Scanner fileNameReader = new Scanner(System.in);
            System.out.println("Enter file name including extention");
            String fileName = fileNameReader.next();
            File inputFile = new File(fileName);

            //File inputFile = new File("Replace with hard coded path if necessary");

            Scanner in = new Scanner(inputFile);
            int[] metadata = readMetadata(in);

            // For testing first line read
            System.out.println("Metadata info");
            for(int i=0; i<metadata.length; i++) {
                System.out.println(metadata[i]);
            }

            int noOfVids = metadata[0];
            int[] fileSizes = readVideoSizes(in,noOfVids);

            //For testing File size read
            System.out.println("File sizes");
            for(int i=0; i<fileSizes.length; i++) {
                System.out.println("File " + i + ": " + fileSizes[i] + "MB" );
            }

            int noOfEndpoints = metadata[1];
            EndpointLatency[] latencies = readEndpointLatencies(in,noOfEndpoints);

            System.out.println("Endpoint sizes");
            for (int i=0; i<latencies.length; i++) {
                System.out.println("Endpoint " + i + ":");
                System.out.println("Datacentre latency: " + latencies[i].getDatacentreLatency() + " MS");
                if (latencies[i].getCacheLatency()!= null) {
                    for (int j=0; j < latencies[i].getCacheLatency().length; j++) {
                        System.out.println("Latency to endpoint " + j + ": " + latencies[i].getCacheLatency()[j] + "MS");
                    }
                }
            }


        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    private static int[] readMetadata(Scanner in){
        int noOfVids = in.nextInt();
        int noOfEndpoints = in.nextInt();
        int noOfRequests = in.nextInt();
        int noOfCacheServers = in.nextInt();
        int cacheCapacity = in.nextInt();
        int[] metadata = new int[5];
        metadata[0] = noOfVids;
        metadata[1] = noOfEndpoints;
        metadata[2] = noOfRequests;
        metadata[3] = noOfCacheServers;
        metadata[4] = cacheCapacity;
        return metadata;
    }

    private static int[] readVideoSizes (Scanner in, int noOfVids) {
        int[] vidSizes = new int[noOfVids];
        for (int i=0; i < noOfVids; i++ ) {
            vidSizes[i] = in.nextInt();
        }
        return vidSizes;
    }

    private static EndpointLatency[] readEndpointLatencies(Scanner in, int noOfEndpoints) {
        EndpointLatency[] latencies = new EndpointLatency[noOfEndpoints];
        for (int i=0; i < noOfEndpoints; i++) {
            int datacentreLatency = in.nextInt();
            int noOfCaches = in.nextInt();

            if (noOfCaches > 0) {

                int[] tempCacheLatency = new int[10];

                for (int j = 0; j < noOfCaches; j++) {
                    int cacheIdentifier = in.nextInt();
                    int cacheLatency = in.nextInt();
                    tempCacheLatency[cacheIdentifier] = cacheLatency;
                }

                EndpointLatency temp = new EndpointLatency(datacentreLatency,tempCacheLatency);
                latencies[i] = temp;
            } else {
                EndpointLatency temp = new EndpointLatency(datacentreLatency, null);
                latencies[i] = temp;
            }
        }
        return latencies;
    }
}

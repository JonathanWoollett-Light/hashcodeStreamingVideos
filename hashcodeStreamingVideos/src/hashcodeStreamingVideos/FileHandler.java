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

            // For testing
            System.out.println("Metadata info");
            for(int i=0; i<metadata.length; i++) {
                System.out.println(metadata[i]);
            }

            int noOfVids = metadata[0];
            int[] fileSizes = readVideoSizes(in,noOfVids);

            System.out.println("File sizes");
            for(int i=0; i<fileSizes.length; i++) {
                System.out.println("File " + i + ": " + fileSizes[i] + "MB" );
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
}

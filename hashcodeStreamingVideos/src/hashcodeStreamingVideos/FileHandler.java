package hashcodeStreamingVideos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileHandler {
    private static int[] metadata;
    private static int[] videoSizes;
    private static Request[] requests;

    public static void openFile() {
        try {
            Scanner fileNameReader = new Scanner(System.in);
            System.out.println("Enter file name including extention");
            String fileName = fileNameReader.next();
            File inputFile = new File(fileName);

            //File inputFile = new File("Replace with hard coded path if necessary");

            Scanner in = new Scanner(inputFile);
            int[] mdata = readMetadata(in);
            setMetadata(mdata);

            // For testing first line read
            System.out.println("\nMetadata info");
            for (int i = 0; i < metadata.length; i++) {
                System.out.println(metadata[i]);
            }

            int[] fileSizes = readVideoSizes(in);
            setVideoSizes(fileSizes);


            //For testing File size read
            System.out.println("\nVideo sizes");
            for (int i = 0; i < videoSizes.length; i++) {
                System.out.println("File " + i + ": " + videoSizes[i] + "MB");
            }


            EndpointLatency[] latencies = readEndpointLatencies(in);

            //Test the read of endpoint latencies
            System.out.println("\nEndpoint latencies to cache");
            for (int i = 0; i < latencies.length; i++) {
                System.out.println("Endpoint " + i + ":");
                System.out.println("Datacentre latency: " + latencies[i].getDatacentreLatency() + " MS");
                if (latencies[i].getCacheLatency() != null) {
                    for (int j = 0; j < latencies[i].getCacheLatency().length; j++) {
                        //Any cache that has latency 0ms isn't connected to the endpoint
                        System.out.println("Latency to endpoint " + j + ": " + latencies[i].getCacheLatency()[j] + "MS");
                    }
                } else {
                    System.out.println("No connected cache servers");
                }
            }

            Request[] reqs = readVideoRequests(in);
            setRequests(reqs);

            //Test requests
            System.out.println("\nVideo Requests");
            for(int i=0; i < requests.length; i++) {
                System.out.println(requests[i].getNumber() + " requests for video " + requests[i].getVideo()
                                   + " from endpoint " + requests[i].getRequestingEndpoint());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    //Reads in metadata from first line
    private static int[] readMetadata(Scanner in) {
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
        Cache.setSize(cacheCapacity);
        return metadata;
    }

    //Reads size of all videos from second line
    private static int[] readVideoSizes(Scanner in) {
        int noOfVids = metadata[0];
        int[] vidSizes = new int[noOfVids];
        for (int i = 0; i < noOfVids; i++) {
            vidSizes[i] = in.nextInt();
        }
        return vidSizes;
    }

    //Reads Latency from all endpoints to their cache
    private static EndpointLatency[] readEndpointLatencies(Scanner in) {

        int noOfEndpoints = metadata[1];
        int noOfCacheServers = metadata[3];

        EndpointLatency[] latencies = new EndpointLatency[noOfEndpoints];
        for (int i = 0; i < noOfEndpoints; i++) {
            int datacentreLatency = in.nextInt();
            int noOfCaches = in.nextInt();

            if (noOfCaches > 0) {

                int[] tempCacheLatency = new int[noOfCacheServers];

                for (int j = 0; j < noOfCaches; j++) {
                    int cacheIdentifier = in.nextInt();
                    int cacheLatency = in.nextInt();
                    tempCacheLatency[cacheIdentifier] = cacheLatency;
                }

                EndpointLatency temp = new EndpointLatency(datacentreLatency, tempCacheLatency);
                latencies[i] = temp;
            } else {
                EndpointLatency temp = new EndpointLatency(datacentreLatency, null);
                latencies[i] = temp;
            }
        }
        return latencies;
    }

    private static Request[] readVideoRequests(Scanner in) {
        int noOfVideos = metadata[0];
        int noOfEndpoints = metadata[1];
        int noOfVideoRequests = metadata[2];

        Request[] requests = new Request[noOfVideos];
        Endpoint[] endpoints = new Endpoint[noOfEndpoints];


        for (int i = 0; i < noOfVideoRequests; i++) {
            int videoNo = in.nextInt();
            int endpointNo = in.nextInt();
            int noOfRequests = in.nextInt();
            int vidSize = videoSizes[videoNo];
            Request temp = new Request(noOfRequests, videoNo, vidSize, endpointNo);
            requests[i] = temp;

        }
        return requests;

    }

    private static void setMetadata(int[] mdata) {
        metadata = mdata;
    }

    private static void setVideoSizes(int[] vSizes) {
        videoSizes = vSizes;
    }

    private static void setRequests(Request[] reqs) {
        requests = reqs;
    }

    public static int[] getVideoSizes() {
        return videoSizes;
    }


    public static int[] getMetadata() {
        return metadata;
    }

    public static Request[] getRequests() {
        return requests;
    }
}
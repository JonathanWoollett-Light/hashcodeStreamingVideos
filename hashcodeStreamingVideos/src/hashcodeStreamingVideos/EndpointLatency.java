package hashcodeStreamingVideos;

public class EndpointLatency {
    private int datacentreLatency;
    private int[] cacheLatency;

    public EndpointLatency(int datacentreLatency, int[] cacheLatency) {
        this.datacentreLatency = datacentreLatency;
        this.cacheLatency = cacheLatency;
    }

    public int getDatacentreLatency() {
        return  datacentreLatency;
    }

    public int[] getCacheLatency() {
        return cacheLatency;
    }
}

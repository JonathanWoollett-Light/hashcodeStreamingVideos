package hashcodeStreamingVideos;

public class Connection {
	private final int latency;
	private final Cache cache;
	private final Endpoint endpoint;
	public Connection(int latency, Cache cache, Endpoint endpoint) {
		this.latency = latency;
		this.cache = cache;
		this.endpoint = endpoint;
	}
	
	public Endpoint getEndpoint() {
		return endpoint;
	}
}
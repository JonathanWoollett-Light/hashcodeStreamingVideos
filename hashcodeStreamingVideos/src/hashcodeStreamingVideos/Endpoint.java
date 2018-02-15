package hashcodeStreamingVideos;

public class Endpoint {
	private final Connection connections[];
	private final int latencyToDataCentre;
	private final Request requests;
	
	public Endpoint(Connection[] connections, int latencyToDataCentre, Request requests) {
		this.connections = connections.clone();
		this.latencyToDataCentre = latencyToDataCentre;
		this.requests = requests;
	}
	
	public Connection[] getConnections() {
		return connections;
	}
}

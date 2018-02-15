package hashcodeStreamingVideos;

public class Endpoint {
	private final Connection connections[];
	private final int latencyToDataCentre;
	
	public Endpoint(Connection[] connections, int latencyToDataCentre) {
		this.connections = connections.clone();
		this.latencyToDataCentre = latencyToDataCentre;
	}
	
	public Connection[] getConnections() {
		return connections;
	}
}

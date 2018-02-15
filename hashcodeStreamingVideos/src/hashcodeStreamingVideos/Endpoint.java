package hashcodeStreamingVideos;

public class Endpoint {
	private final Connection connections[];
	private final int latencyToDataCentre;
	private final Request requests[];
	
	public Endpoint(Connection[] connections, int latencyToDataCentre, Request[] requests) {
		this.connections = connections.clone();
		this.latencyToDataCentre = latencyToDataCentre;
		this.requests = requests;
	}
	
	public Connection[] getConnections() {
		return connections;
	}
	public Request[] getRequests() {
		return requests;
	}
	public int[] getVideoPriorities() {
		int videoPriorities[] = new int[requests.length];
		for(int i=0;i<requests.length;i++) {
			videoPriorities[requests[i].getVideo()] = requests[i].getPriority();
		}
		return videoPriorities;
	}
}

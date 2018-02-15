package hashcodeStreamingVideos;

public class Cache {
	private static int size;
	private final Connection connections[];
	
	public Cache(Connection[] connections) {
		this.connections = connections.clone();
	}
	
	public static void setSize(int size){
		Cache.size = size;
	}
	public static int getSize(){
		return size;
	}
	
	public Connection[] getConnections() {
		return connections;
	}
}

package hashcodeStreamingVideos;

public class Request {
	private final int number;
	private final int video;
	private final int requestingEndpoint;
	private final double priority;
	
	public Request(int number, int video, int videoSize, int endpointNo) {
		this.number = number;
		this.video = video;
		this.requestingEndpoint = endpointNo;
		priority = videoSize / number;
	}
	
	public int getNumber() {
		return number;
	}
	public int getVideo() {
		return video;
	}
	public int getRequestingEndpoint() {
		return this.requestingEndpoint;
	}
	public double getPriority() {
		return priority;
	}
}

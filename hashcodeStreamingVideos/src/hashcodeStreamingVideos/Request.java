package hashcodeStreamingVideos;

public class Request {
	private final int number;
	private final int video;
	private final double priority;
	
	public Request(int number, int video, int videoSize) {
		this.number = number;
		this.video = video;
		priority = videoSize / number;
	}
	
	public int getNumber() {
		return number;
	}
	public int getVideo() {
		return video;
	}
	public double getPriority() {
		return priority;
	}
}

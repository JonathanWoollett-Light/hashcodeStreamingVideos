package hashcodeStreamingVideos;

public class Request {
	private int number;
	private int video;
	
	public Request(int number, int video, int videoSize) {
		this.number = number;
		this.video = video;
	}
	
	public int getNumber() {
		return number;
	}
	public int getVideo() {
		return video;
	}
}

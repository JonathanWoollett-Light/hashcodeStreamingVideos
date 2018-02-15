package hashcodeStreamingVideos;

public class Program {
	private static int[] videoSizes;
	private static Cache[] caches;
	
	public static void main(String args[]) {
		for(int i = 0; i < caches.length; i++) {
			caches[i].setVideoOrder(videoSizes);
		}
	}
}

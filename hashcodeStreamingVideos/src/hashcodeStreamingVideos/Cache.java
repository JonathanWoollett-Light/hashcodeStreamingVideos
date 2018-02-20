package hashcodeStreamingVideos;

import java.util.ArrayList;

public class Cache {
	private static int size;
	private final Connection connections[];
	private static ArrayList<Integer> videos = new ArrayList<Integer>();
	
	private int videoOrder[];
	private int videoPriority[];
	
	public Cache(Connection[] connections, int numbOfVideos) {
		this.connections = connections.clone();
		videoOrder = new int[numbOfVideos];
		videoPriority = new int[numbOfVideos];
	}
	
	public static void setSize(int size) {
		Cache.size = size;
	}
	public static int getSize() {
		return size;
	}
	
	public Connection[] getConnections() {
		return connections;
	}
	
	public void setVideoOrder(int[] videoSizes) {
		//setting video priorities
		double videoPriorityHolder[];
		for(int a = 0; a < videoOrder.length; a++) {
			for(int b = 0; b < connections.length; b++) {
				videoPriorityHolder = connections[b].getEndpoint().getVideoPriorities();
				for(int c = 0; c< videoPriority.length; c++) {
					videoPriority[c] += videoPriorityHolder[c];
				}
			}
		}
		for(int i = 0; i < videoPriority.length; i++) {
			videoPriority[i] /= connections.length;
		}
		//putting video into cache in order of highest priority that fits
		int maxIndex = 0;
		int spaceRemaining = size;
		maxIndex = smallerValueClosest(spaceRemaining);
		while(maxIndex != -1) {
			videos.add(maxIndex);
			spaceRemaining -= videoSizes[maxIndex];
			maxIndex = smallerValueClosest(spaceRemaining);
		}
	}
	//find value in array that is equal to or smaller than passed value
	private int smallerValueClosest(int largerValue) {
		boolean foundValue = false;
		int smallerClosestValueIndex = 0;
		for(int i = 1; i < videoPriority.length; i++) {
			if(largerValue - videoPriority[i] < videoPriority[smallerClosestValueIndex] && videoPriority[smallerClosestValueIndex] - videoPriority[i] >= 0) {
				smallerClosestValueIndex = i;
				foundValue = true;
			}
		}
		if(foundValue) {
			return smallerClosestValueIndex;
		}
		return -1;
	}
}

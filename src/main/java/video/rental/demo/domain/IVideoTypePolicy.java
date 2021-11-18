package video.rental.demo.domain;

public interface IVideoTypePolicy {
	public int getDaysRentedLimit();
	public int getLateReturnPointPenalty();
}

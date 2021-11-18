package video.rental.demo.domain;

public class VHSPolicy implements IVideoTypePolicy {

	@Override
	public int getDaysRentedLimit() {
		return 5;
	}

	@Override
	public int getLateReturnPointPenalty() {
		return 1;
	}

}

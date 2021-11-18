package video.rental.demo.domain;

public class CDPolicy implements IVideoTypePolicy {

	@Override
	public int getDaysRentedLimit() {
		return 3;
	}

	@Override
	public int getLateReturnPointPenalty() {
		return 2;
	}

}

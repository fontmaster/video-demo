package video.rental.demo.domain;

public class DVDPolicy implements IVideoTypePolicy {

	@Override
	public int getDaysRentedLimit() {
		return 2;
	}

	@Override
	public int getLateReturnPointPenalty() {
		return 3;
	}

}

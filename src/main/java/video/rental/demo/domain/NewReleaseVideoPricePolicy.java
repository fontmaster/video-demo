package video.rental.demo.domain;

public class NewReleaseVideoPricePolicy implements IVideoPricePolicy {

	@Override
	public double getPrice(int daysRented) {
		return daysRented * 3;
	}

	@Override
	public int extraPoint() {		
		return 1;
	}

}

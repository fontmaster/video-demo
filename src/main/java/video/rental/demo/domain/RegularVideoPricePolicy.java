package video.rental.demo.domain;

public class RegularVideoPricePolicy implements IVideoPricePolicy {

	@Override
	public double getPrice(int daysRented) {
		double charge = 2;
		if (daysRented > 2)
			charge += (daysRented - 2) * 1.5;		
		return charge;
	}

	@Override
	public int extraPoint() {		
		return 0;
	}

}

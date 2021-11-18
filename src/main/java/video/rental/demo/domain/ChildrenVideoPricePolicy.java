package video.rental.demo.domain;

public class ChildrenVideoPricePolicy implements IVideoPricePolicy {

	@Override
	public double getPrice(int daysRented) {
		double charge = 1.5;
		if (daysRented > 3)
			charge += (daysRented - 3) * 1.5;
		return charge;
	}

	@Override
	public int extraPoint() {		
		return 0;
	}

}

package video.rental.demo.domain;

//Refactoring3
public interface IVideoPricePolicy {
	double getPrice(int daysRented);
	int extraPoint();
}

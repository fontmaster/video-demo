package video.rental.demo.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Customer {
	@Id
	private int code;
	private String name;
	private LocalDate dateOfBirth;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Rental> rentals = new ArrayList<Rental>();

	public Customer() {	// for hibernate
	}

	public Customer(int code, String name, LocalDate dateOfBirth) {
		this.code = code;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
	}

	public int getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public List<Rental> getRentals() {
		return rentals;
	}
	
	//Refectoring1
	public void addRental(Rental rental) {
		this.rentals.add(rental);
	}
	
	public void clearRentals() {
		this.rentals.clear();
	}
	
	
	//Refactoring2
	public String getReport() {
		String result = "Customer Report for " + getName() + "\n";

		List<Rental> rentals = getRentals();

		double totalCharge = 0;
		int totalPoint = 0;

		for (Rental rental : rentals) {		
			int daysRented = rental.getDaysRented();
			double charge = rental.getCharge();			
			int point = rental.getPoint();

			result += "\t" + rental.getVideo().getTitle() + "\tDays rented: " + daysRented + "\tCharge: " + charge
					+ "\tPoint: " + point + "\n";

			totalCharge += charge;
			totalPoint += point;
		}
		
		result += "Total charge: " + totalCharge + "\tTotal Point:" + totalPoint + "\n";
		result += getCoupon(totalPoint);
		
		return result;
	}

	private String getCoupon(int totalPoint) {
		StringBuilder builder = new StringBuilder();
		
		if (totalPoint >= 10) {
			builder.append("Congrat! You earned one free coupon" + "\n");
		}
		if (totalPoint >= 30) {
			builder.append("Congrat! You earned two free coupon" + "\n");
		}
		
		return builder.toString();
	}

}

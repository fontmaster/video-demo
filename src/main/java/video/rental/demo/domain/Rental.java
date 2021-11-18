package video.rental.demo.domain;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Rental {

	@Id
	@GeneratedValue
	private int id;

	private int status; // 0 for Rented, 1 for Returned
	private LocalDateTime rentDate;
	private LocalDateTime returnDate;

	@OneToOne(fetch = FetchType.EAGER)
	private Video video;
	//Refactoring3
	private IVideoPricePolicy videoPricePolicy;
	
	private IVideoTypePolicy videoTypePolicy;

	Rental() {
	}

	public Rental(Video video) {
		this.video = video;
		this.status = 0;
		this.rentDate = LocalDateTime.now();
		
		switch (video.getPriceCode()) {
		case Video.NEW_RELEASE:
			this.videoPricePolicy = new NewReleaseVideoPricePolicy();
			break;
		case Video.CHILDREN:
			this.videoPricePolicy = new ChildrenVideoPricePolicy();
			break;
		default:
			this.videoPricePolicy = new RegularVideoPricePolicy();
			break;			
		}
		
		switch (video.getVideoType()) {
		case Video.VHS:
			this.videoTypePolicy = new VHSPolicy();
			break;
		case Video.CD:
			this.videoTypePolicy = new CDPolicy();
			break;
		default:
			this.videoTypePolicy = new DVDPolicy();
			break;			
		}
	}

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public int getStatus() {
		return status;
	}

	public Video returnVideo() {
		if (status == 0) {
			this.status = 1;
			this.returnDate = LocalDateTime.now();
		}
		return video;
	}

	public LocalDateTime getRentDate() {
		return rentDate;
	}

	public void setRentDate(LocalDateTime rentDate) {
		this.rentDate = rentDate;
	}

	public LocalDateTime getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDateTime returnDate) {
		this.returnDate = returnDate;
	}

	public int getDaysRentedLimit() {
		int limit = 0;
		switch (video.getVideoType()) {
		case Video.VHS:
			limit = 5;
			break;
		case Video.CD:
			limit = 3;
			break;
		case Video.DVD:
			limit = 2;
			break;
		}
		return limit;
	}

	public int getDaysRented() {
		LocalDateTime end = (getStatus() == 1) ? getReturnDate() : LocalDateTime.now();

        int days = (int) (ChronoUnit.HOURS.between(getRentDate(), end) / 24 );

	    return days == 0 ? 1 : days + 1;
	}

	//Refactoring2, Refactoring3
	public double getCharge() {
		int daysRented = getDaysRented();
		return this.videoPricePolicy.getPrice(daysRented);
	}

	//Refactoring2
	public int getPoint() {
		int point = 1;
		int daysRented = getDaysRented();
		
		point += this.videoPricePolicy.extraPoint();
	
		if (daysRented > this.videoTypePolicy.getDaysRentedLimit())
			point -= Math.min(point, this.videoTypePolicy.getLateReturnPointPenalty());
		
		return point;
	}
}
package com.tarikc.ServiceBookingSystem.Services.Client;

import com.tarikc.ServiceBookingSystem.Dto.AdDetailsForClientDto;
import com.tarikc.ServiceBookingSystem.Dto.AdDto;
import com.tarikc.ServiceBookingSystem.Dto.ReservationDto;
import com.tarikc.ServiceBookingSystem.Dto.ReviewDto;
import com.tarikc.ServiceBookingSystem.Entity.Ad;
import com.tarikc.ServiceBookingSystem.Entity.Reservation;
import com.tarikc.ServiceBookingSystem.Entity.Review;
import com.tarikc.ServiceBookingSystem.Entity.User;
import com.tarikc.ServiceBookingSystem.Enum.ReservationStatus;
import com.tarikc.ServiceBookingSystem.Enum.ReviewStatus;
import com.tarikc.ServiceBookingSystem.Repository.AdRepository;
import com.tarikc.ServiceBookingSystem.Repository.ReservationRepository;
import com.tarikc.ServiceBookingSystem.Repository.ReviewRepository;
import com.tarikc.ServiceBookingSystem.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private AdRepository adRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    public List<AdDto> getAllAds(){
        return adRepository.findAll().stream().map(Ad::getAdDto).collect(Collectors.toList());
    }
    public List<AdDto> SearchAdByName(String name){
        return adRepository.findAllByServiceNameContaining(name).stream().map(Ad::getAdDto).collect(Collectors.toList());
    }
    public boolean bookService(ReservationDto reservationDto){
        Optional<Ad> optionalAd = adRepository.findById(reservationDto.getAdId());
        Optional<User> optionalUser = userRepository.findById(reservationDto.getUserId());

        if(optionalAd.isPresent() && optionalUser.isPresent()){
            Reservation reservation = new Reservation();
            reservation.setBookDate(reservationDto.getBookDate());
            reservation.setReservationStatus(ReservationStatus.PENDING);
            reservation.setUser(optionalUser.get());

            reservation.setAd(optionalAd.get());
            reservation.setCompany(optionalAd.get().getUser());
            reservation.setReviewStatus(ReviewStatus.FALSE);

            reservationRepository.save(reservation);
            return  true;
        }
        return  false;
    }
    public AdDetailsForClientDto getAdDetailsByAdId(Long adId){
        Optional<Ad> optionalAd = adRepository.findById(adId);
        AdDetailsForClientDto adDetailsForClientDto = new AdDetailsForClientDto();
        if(optionalAd.isPresent()){
            adDetailsForClientDto.setAdDto(optionalAd.get().getAdDto());
        }
        return  adDetailsForClientDto;
    }
    public List<ReservationDto> getAllBookingsByUserId(Long userId){
        return  reservationRepository.findAllByUserId(userId).stream().map(Reservation::getReservationDto).collect(Collectors.toList());
    }

    public Boolean giveReview(ReviewDto reviewDto) {
        Optional<User> optionalUser = userRepository.findById(reviewDto.getUserId());
        Optional<Reservation> optionalReservation = reservationRepository.findById(reviewDto.getBookId());
        if (optionalUser.isPresent() && optionalReservation.isPresent()) {
            Review review = new Review();
            review.setReviewDate(new Date());
            review.setReview(reviewDto.getReview());
            review.setRating(reviewDto.getRating());
            review.setUser(optionalUser.get());
            review.setAd(optionalReservation.get().getAd());

            reviewRepository.save(review);

            Reservation booking = optionalReservation.get();
            booking.setReviewStatus(ReviewStatus.TRUE);

            reservationRepository.save(booking);

            return true;
        }
        return false;
    }

}

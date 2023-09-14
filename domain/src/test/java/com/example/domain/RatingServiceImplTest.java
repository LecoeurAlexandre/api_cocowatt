package com.example.domain;

import com.example.domain.entity.Rating;
import com.example.domain.exception.EmptyParameterException;
import com.example.domain.exception.InvalidIdException;
import com.example.domain.exception.InvalidValueException;
import com.example.domain.port.RatingRepository;
import com.example.domain.port.UserRepository;
import com.example.domain.service.RatingServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;


@SpringBootTest
public class RatingServiceImplTest {

    private RatingServiceImpl ratingService;

    private RatingRepository ratingRepository;
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        ratingService = new RatingServiceImpl(ratingRepository, userRepository);
    }

    // 1) Tests pour méthode "createRating"

    @Test
    void createRatingTestEmptyComment_ShouldRaiseEmptyParameterException() {
        int value = 5;
        String comment = "";
        int userId = 2;

        Assertions.assertThrows(EmptyParameterException.class, ()-> ratingService.createRating(value, comment, userId));
    }

    @Test
    void createRatingTestNullComment_ShouldRaiseEmptyParameterException() {
        int value = 5;
        String comment = null;
        int userId = 2;

        Assertions.assertThrows(EmptyParameterException.class, ()-> ratingService.createRating(value, comment, userId));
    }

    @Test
    void createRatingTestUserIdIs0_ShouldRaiseInvalidIdException() {
        int value = 5;
        String comment = "Ceci est un commentaire";
        int userId = 0;

        Assertions.assertThrows(InvalidIdException.class, ()-> ratingService.createRating(value, comment, userId));
    }

    @Test
    void createRatingTestUserIdIsBelow0_ShouldRaiseInvalidIdException() {
        int value = 5;
        String comment = "Ceci est un commentaire";
        int userId = -1;

        Assertions.assertThrows(InvalidIdException.class, ()-> ratingService.createRating(value, comment, userId));
    }

    @Test
    void createRatingTestValueIs0_ShouldRaiseInvalidValueException() {
        int value = 0;
        String comment = "Ceci est un commentaire";
        int userId = 4;

        Assertions.assertThrows(InvalidValueException.class, ()-> ratingService.createRating(value, comment, userId));
    }

    @Test
    void createRatingTestValueIsBelow0_ShouldRaiseInvalidValueException() {
        int value = -1;
        String comment = "Ceci est un commentaire";
        int userId = 4;

        Assertions.assertThrows(InvalidValueException.class, ()-> ratingService.createRating(value, comment, userId));
    }

//    @Test
//    void createRatingTestUserIdExists_ShouldAssertTrue() throws EmptyParameterException, InvalidValueException, InvalidIdException {
//        int value = 5;
//        String comment = "Ceci est un commentaire";
//        int userId = 2;
//        LocalDate localDate = LocalDate.now();
//        Rating rating = new Rating(value, comment, localDate, userId);
//        List<Rating> ratingList = new ArrayList<>();
//        ratingList.add(rating);
//
//        ratingService.createRating(value, comment, userId);
//        List<Rating> ratingListBdd = ratingService.getAllByUserId(2);
//
//        Assertions.assertEquals(ratingList, ratingListBdd);
//
//    }


    // 2) Tests pour méthode "getById"

    @Test
    void getByIdTestIdIsEmpty_ShouldRaiseEmptyParameterException() {
        String id = "";

        Assertions.assertThrows(EmptyParameterException.class, ()-> ratingService.getById(id));
    }

    @Test
    void getByIdTestIdIsNull_ShouldRaiseEmptyParameterException() {
        String id = null;

        Assertions.assertThrows(EmptyParameterException.class, ()-> ratingService.getById(id));
    }

    @Test
    void getByIdTestIdIs0_ShouldRaiseInvalidIdException() {
        String id = "0";

        Assertions.assertThrows(InvalidIdException.class, ()-> ratingService.getById(id));
    }

    @Test
    void getByIdTestIdIsBelow0_ShouldRaiseInvalidIdException() {
        String id = "-1";

        Assertions.assertThrows(InvalidIdException.class, ()-> ratingService.getById(id));
    }

    // 3) Tests pour méthode "getAllByUserId"

    @Test
    void getAllByUserIdTestIdIs0_ShouldRaiseInvalidIdException() {
        int id = 0;

        Assertions.assertThrows(InvalidIdException.class, ()-> ratingService.getAllByUserId(id));
    }

    @Test
    void getAllByUserIdTestIdIsBelow0_ShouldRaiseInvalidIdException() {
        int id = -1;

        Assertions.assertThrows(InvalidIdException.class, ()-> ratingService.getAllByUserId(id));
    }

    // 4) Tests pour méthode "delete"

    @Test
    void deleteTestIdIsNull_ShouldRaiseEmptyParameterException() {
        String id = null;

        Assertions.assertThrows(EmptyParameterException.class, ()-> ratingService.delete(id));
    }

    @Test
    void deleteTestIdIsEmpty_ShouldRaiseEmptyParameterException() {
        String id = null;

        Assertions.assertThrows(EmptyParameterException.class, ()-> ratingService.delete(id));
    }

    @Test
    void deleteTestIdIs0_ShouldRaiseInvalidIdException() {
        String id = "0";

        Assertions.assertThrows(InvalidIdException.class, ()-> ratingService.delete(id));
    }

    @Test
    void deleteTestIdIsBelow0_ShouldRaiseInvalidIdException() {
        String id = "-1";

        Assertions.assertThrows(InvalidIdException.class, ()-> ratingService.delete(id));
    }

    // 5) Tests pour méthode "update"

    @Test
    void updateTestIdIsNull_ShouldRaiseEmptyParameterException() {
        String id = null;

        Rating rating = new Rating(5,"blabla", LocalDate.now(),2);

        Assertions.assertThrows(EmptyParameterException.class, ()-> ratingService.update(id, rating));
    }

    @Test
    void updateTestIdIs0_ShouldRaiseInvalidIdException() {
        String id = "0";

        Rating rating = new Rating(5,"blabla", LocalDate.now(),2);

        Assertions.assertThrows(InvalidIdException.class, ()-> ratingService.update(id, rating));
    }

    @Test
    void updateTestIdIsBelow0_ShouldRaiseInvalidIdException() {
        String id = "-1";

        Rating rating = new Rating(5,"blabla", LocalDate.now(),2);

        Assertions.assertThrows(InvalidIdException.class, ()-> ratingService.update(id, rating));
    }

    @Test
    void updateTestRatingIsNull_ShouldRaiseNullPointerException() {
        String id = "4";

        Rating rating = null;

        Assertions.assertThrows(NullPointerException.class, ()-> ratingService.update(id, rating));
    }






}

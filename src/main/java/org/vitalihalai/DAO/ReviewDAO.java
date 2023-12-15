package org.iseedeadpeopleq.DAO;

import org.iseedeadpeopleq.DAO.exception.DatabaseQueryException;
import org.iseedeadpeopleq.beans.Movie;
import org.iseedeadpeopleq.beans.Review;
import org.iseedeadpeopleq.beans.User;

import java.util.Optional;

public interface ReviewDAO {


    /**
     * @param movie бин фильма
     * @return double средняя оценка фильма по отзывам пользователей
     * @throws DatabaseQueryException ошибка базы данных
     */
    double getAverageMark(Movie movie) throws DatabaseQueryException;

    /**
     * @param user бин пользователя
     * @param movie бин фильма
     * @return Optional<Review> отзыв данного фильма заданного пользавателя
     * @throws DatabaseQueryException ошибка базы данных
     */
    Optional<Review> getReviewByUserAndMovie(Movie movie, User user) throws DatabaseQueryException;

    /**
     * @param review сохраняемый отзыв
     * @throws DatabaseQueryException ошибка базы данных
     */
    void saveReview(Review review, boolean isNew) throws DatabaseQueryException;

}

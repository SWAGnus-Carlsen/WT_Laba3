package org.iseedeadpeopleq.DAO.impl;

import org.iseedeadpeopleq.DAO.ReviewDAO;
import org.iseedeadpeopleq.DAO.exception.DatabaseQueryException;
import org.iseedeadpeopleq.beans.Movie;
import org.iseedeadpeopleq.beans.Review;
import org.iseedeadpeopleq.beans.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class ReviewDAOImpl implements ReviewDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public ReviewDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public double getAverageMark(Movie movie) throws DatabaseQueryException {
        return sessionFactory.fromTransaction(session -> {
            var query = session.createQuery("SELECT avg (mark) from Review where movie.id = :id", Double.class);
            query.setParameter("id",movie.getId());
            return query.getSingleResult();
        });
    }

    @Override
    public Optional<Review> getReviewByUserAndMovie(Movie movie, User user) throws DatabaseQueryException {
        return sessionFactory.fromTransaction(session -> {
            var query = session.createSelectionQuery("from Review where movie.id = :movieId AND user.id = :userId", Review.class);
            query.setParameter("movieId", movie.getId());
            query.setParameter("userId", user.getId());
            Review review = query.getSingleResultOrNull();
            if (review == null) return Optional.empty();
            else return Optional.of(review);
        });
    }

    @Override
    public void saveReview(Review review, boolean isNew) throws DatabaseQueryException {
        sessionFactory.inTransaction(session -> {
            if(isNew){
                session.persist(review);
            }
            else{
                session.merge(review);
            }
        });
    }
}

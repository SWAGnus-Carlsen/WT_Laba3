package org.iseedeadpeopleq.service;

import org.iseedeadpeopleq.beans.Movie;
import org.iseedeadpeopleq.beans.User;
import org.iseedeadpeopleq.service.exception.ServiceException;

public interface ReviewService {

    /**
     *
     * @param movie
     * @param user
     * @param mark
     * @param review
     * @throws ServiceException
     */
    void addOrChangeReview(Movie movie, User user, int mark, String review) throws ServiceException;

}

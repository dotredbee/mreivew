package org.moremore.mreview.repository;

import org.junit.jupiter.api.Test;
import org.moremore.mreview.entity.Member;
import org.moremore.mreview.entity.Movie;
import org.moremore.mreview.entity.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
public class ReviewRepositoryTests {

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    public void insertMovieReviews() {

        IntStream.rangeClosed(1, 100).forEach(i -> {
            Long mno = ((long)(Math.random() * 100) + 1);

            Long mid = ((long)(Math.random() * 100) + 1);

            Movie movie = Movie.builder().mno(mno).build();
            Member member = Member.builder().mid(mid).build();

            Review review = Review.builder()
                    .member(member)
                    .movie(movie)
                    .grade((int)(Math.random() * 5) + 1)
                    .text("feel about this movie..." + i)
                    .build();

            reviewRepository.save(review);
        });
    }
}

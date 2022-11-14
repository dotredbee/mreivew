package org.moremore.mreview.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.moremore.mreview.dto.MovieDTO;
import org.moremore.mreview.entity.Movie;
import org.moremore.mreview.entity.MovieImage;
import org.moremore.mreview.repository.MovieImageRepository;
import org.moremore.mreview.repository.MovieRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Service
@Log4j2
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService{
    private final MovieRepository movieRepository;

    private final MovieImageRepository movieImageRepository;

    @Transactional
    @Override
    public Long register(MovieDTO movieDTO){
        Map<String, Object> entityMap = dtoToEntity(movieDTO);

        Movie movie = (Movie) entityMap.get("movie");

        List<MovieImage> movieImageList = (List<MovieImage>) entityMap.get("imgList");

        movieRepository.save(movie);

        movieImageList.forEach(movieImage -> {
            movieImageRepository.save(movieImage);
        });

        return movie.getMno();
    }
}

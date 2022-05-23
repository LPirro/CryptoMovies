package com.lpirro.cryptomovies.data.repository

import com.lpirro.cryptomovies.data.network.CryptoMovieService
import com.lpirro.cryptomovies.data.peristance.MoviesDao
import com.lpirro.cryptomovies.data.repository.mapper.MovieDetailMapper
import com.lpirro.cryptomovies.data.repository.mapper.MovieMapper
import com.lpirro.cryptomovies.domain.model.Category
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class CryptoMoviesRepositoryImpl(
    private val cryptoMovieService: CryptoMovieService,
    private val moviesDao: MoviesDao,
    private val movieMapper: MovieMapper,
    private val movieDetailMapper: MovieDetailMapper
) : CryptoMoviesRepository {

    // TODO CHANGE IF(TRUE) AFTER TESTING
    override suspend fun getPopularMovies() = flow {
        val localMovies = moviesDao.getMoviesListWithCategory(Category.POPULAR)
        if (true) {
            val result = cryptoMovieService.fetchPopularMovies().movies.map {
                movieMapper.mapDtoToEntity(it, Category.POPULAR)
            }
            moviesDao.insertMovieList(result)
            emit(result)
        } else {
            emit(localMovies)
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getTopRatedMovies() = flow {
        val localMovies = moviesDao.getMoviesListWithCategory(Category.TOP_RATED)
        if (true) {
            val result = cryptoMovieService.fetchTopRatedMovies().movies.map {
                movieMapper.mapDtoToEntity(it, Category.TOP_RATED)
            }
            moviesDao.insertMovieList(result)
            emit(result)
        } else {
            emit(localMovies)
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getNowPlayingMovies() = flow {
        val localMovies = moviesDao.getMoviesListWithCategory(Category.NOW_PLAYING)
        if (true) {
            val result = cryptoMovieService.fetchNowPlayingMovies().movies.map {
                movieMapper.mapDtoToEntity(it, Category.NOW_PLAYING)
            }
            moviesDao.insertMovieList(result)
            emit(result)
        } else {
            emit(localMovies)
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getUpcomingMovies() = flow {
        val localMovies = moviesDao.getMoviesListWithCategory(Category.UPCOMING)
        if (true) {
            val result = cryptoMovieService.fetchUpcomingMovies().movies.map {
                movieMapper.mapDtoToEntity(it, Category.UPCOMING)
            }
            moviesDao.insertMovieList(result)
            emit(result)
        } else {
            emit(localMovies)
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getMovie(movieId: Long) = flow {
        val movie = moviesDao.getMovie(movieId)
        emit(movie)
    }.flowOn(Dispatchers.IO)

    override suspend fun getMovieDetail(movieId: Long) = flow {
        val movieDetail = cryptoMovieService.fetchMovieDetail(movieId, "credits")
        emit(movieDetailMapper.mapDtoToEntity(movieDetail))
    }.flowOn(Dispatchers.IO)

    override suspend fun getWatchlist() = flow {
        val watchlistMovies = moviesDao.getWatchlist()
        emit(watchlistMovies)
    }.flowOn(Dispatchers.IO)

    override suspend fun addToWatchList(movieId: Long) {
        moviesDao.insertToWatchlist(movieId)
    }

    override suspend fun isAlreadyOnWatchList(movieId: Long) = flow {
        val isAlreadyOnWatchlist = moviesDao.isAlreadyOnWatchList(movieId)
        emit(isAlreadyOnWatchlist)
    }.flowOn(Dispatchers.IO)

    override suspend fun removeFromWatchlist(movieId: Long) {
        moviesDao.deleteFromWatchlist(movieId)
    }

}

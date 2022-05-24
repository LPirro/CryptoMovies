package com.lpirro.cryptomovies.data.repository

import com.lpirro.cryptomovies.data.network.CryptoMovieService
import com.lpirro.cryptomovies.data.network.interceptors.NetworkConnectionInterceptor
import com.lpirro.cryptomovies.data.network.model.MoviesListDto
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

    private fun getMovies(networkCall: suspend () -> MoviesListDto, category: Category) = flow {
        try {
            val result = networkCall().movies.map { movieMapper.mapDtoToEntity(it, category) }
            moviesDao.insertMovieList(result)
            emit(moviesDao.getMoviesListWithCategory(category))
        } catch (e: NetworkConnectionInterceptor.NoConnectivityException) {
            val localMovies = moviesDao.getMoviesListWithCategory(category)
            emit(localMovies)
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getPopularMovies() =
        getMovies({ cryptoMovieService.fetchPopularMovies() }, Category.POPULAR)

    override suspend fun getTopRatedMovies() =
        getMovies({ cryptoMovieService.fetchTopRatedMovies() }, Category.TOP_RATED)

    override suspend fun getNowPlayingMovies() =
        getMovies({ cryptoMovieService.fetchNowPlayingMovies() }, Category.NOW_PLAYING)

    override suspend fun getUpcomingMovies() =
        getMovies({ cryptoMovieService.fetchUpcomingMovies() }, Category.UPCOMING)

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

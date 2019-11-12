package tk.lorddarthart.swapicharacterfinder.util.network

import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tk.lorddarthart.swapicharacterfinder.util.constants.UrlConstants.BASE_URL
import java.util.concurrent.TimeUnit

class HttpServiceHelper {
    private val mRetrofit: Retrofit

    val jsonApi: JSONPlaceHolderApi
        get() = mRetrofit.create(JSONPlaceHolderApi::class.java)

    init {
        val interceptor = HttpLoggingInterceptor()
            .apply { this.level = HttpLoggingInterceptor.Level.BODY }

        val dispatcher = Dispatcher().apply {
            maxRequests = 1
        }

        val client = OkHttpClient.Builder()
            .dispatcher(dispatcher)
            .connectTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .addInterceptor(interceptor)
            .build()

        mRetrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    companion object {
        private var _instance: HttpServiceHelper? = null
        val instance: HttpServiceHelper?
            get() {
                if (_instance == null) {
                    _instance = HttpServiceHelper()
                }
                return _instance
            }
    }
}
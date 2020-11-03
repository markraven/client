package hu.mark.client;



import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface JsonPlaceHolderApi {
    @GET("user/login")
    Call<List<Post>> getPosts();

    @POST("user/login")
   Call<User>login(@Body User user);

    @GET("mails/mails")
    Call<List<Messages>>getAllMails(@Header("Authorization") String authToken);

    @POST("mails/sending")
    Call<Messages>sendMail(@Header("Authorization")String authToken,@Body Messages message);

    @DELETE("mails/delete")
    Call<Messages>deleteMail(@Body int id);
}

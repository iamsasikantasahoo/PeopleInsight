package com.people.survey.data.remote;


        import com.people.survey.data.local.entity.MarvelEntity;

        import java.util.List;

        import retrofit2.Call;
        import retrofit2.http.GET;

public interface APIRequest {

    String BASE_URL = ApiConstants.BASE_URL;
    @GET("marvel")
    Call<List<MarvelEntity>> getVoters();
}
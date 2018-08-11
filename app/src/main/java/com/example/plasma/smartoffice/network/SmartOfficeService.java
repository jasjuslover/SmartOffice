package com.example.plasma.smartoffice.network;

import com.example.plasma.smartoffice.network.response.ParameterValue;
import com.example.plasma.smartoffice.network.response.SetParamValue;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface SmartOfficeService {
    @GET("Parameter/{id}/Value")
    Call<ParameterValue> getParameterValue(@Path("id") String id, @Header("EIOT-AuthToken") String EIOTAuthToken);

    @POST("Parameter/{id}/Value/{value}")
    Call<SetParamValue> setParameterValue(@Path("id") String id, @Path("value") String value, @Header("EIOT-AuthToken") String EIOTAuthToken);
}

package Database;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface AmbilEventInter {
    @GET("events")
    Call<List<KegiatanHandler>> AmbilEventInter();

    @FormUrlEncoded
    @POST("events/create")
    Call<List<KegiatanHandler>> CreateEventInter(
        @Field("judul")String judul,
        @Field("deskripsi")String deskripsi,
        @Field("tanggal_mulai")String tanggal_mulai,
        @Field("tanggal_akhir")String tanggal_akhir
    );

    @FormUrlEncoded
    @POST("events/update")
    Call<List<KegiatanHandler>> DeleteEventInter(
            @Field("id")int id
    );
}

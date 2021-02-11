package pt.ubi.di.pdm.hojabola.Remote;

import pt.ubi.di.pdm.hojabola.Model.CommentaryAPI;
import pt.ubi.di.pdm.hojabola.Model.FoodPlacesAPI;
import pt.ubi.di.pdm.hojabola.Model.GameAPI;
import pt.ubi.di.pdm.hojabola.Model.ParkingAPI;
import pt.ubi.di.pdm.hojabola.Model.StadiumAPI;
import pt.ubi.di.pdm.hojabola.Model.StandingsAPI;
import pt.ubi.di.pdm.hojabola.Model.UserAPI;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;



public interface api4app {
    @FormUrlEncoded
    @POST("login.php")
    Call<UserAPI> loginUser(@Field("name") String name, @Field("password") String password);

    @FormUrlEncoded
    @POST("register.php")
    Call<UserAPI> registerUser(@Field("name") String name, @Field("email") String email, @Field("password") String password, @Field("confirmpassword") String confirmpassword, @Field("club") String club);

    @FormUrlEncoded
    @POST("preregister.php")
    Call<UserAPI> preRegisterUser(@Field("name") String name, @Field("email") String email, @Field("password") String password, @Field("confirmpassword") String confirmpassword);

    @FormUrlEncoded
    @POST("facebook_email_checker.php")
    Call<UserAPI> isUserRegistered(@Field("email") String email);

    @FormUrlEncoded
    @POST("facebookpreregister.php")
    Call<UserAPI> facebookPreRegisterUser(@Field("name") String name);

    @FormUrlEncoded
    @POST("getUserClub.php")
    Call<UserAPI> getUserClub(@Field("email") String email);

    @FormUrlEncoded
    @POST("getGames.php")
    Call<GameAPI> getClubGames(@Field("club") String club );

    @FormUrlEncoded
    @POST("getStadiums.php")
    Call<StadiumAPI> getStadiums(@Field("club") String club);

    @FormUrlEncoded
    @POST("addParking.php")
    Call<UserAPI> addParking(@Field("parkingName") String parkingName, @Field("parkingAdress") String parkingAdress, @Field("parkingSpots") String parkingSpots, @Field("parkingASpots") String parkingASpots, @Field("parkingStadium") String parkingStadium, @Field("parkingCoordinates") String parkingCoordinates, @Field("parkingISpayed") String parkingISpayed, @Field("encoded_string") String encoded_string);

    @FormUrlEncoded
    @POST("getParkings.php")
    Call<ParkingAPI> getParkings(@Field("idStadium") Integer idStadium);

    @FormUrlEncoded
    @POST("getStandings.php")
    Call<StandingsAPI> getStandings(@Field("club") Integer idStadium);

    @FormUrlEncoded
    @POST("addParkingCommentary.php")
    Call<UserAPI> addParkingCommentary(@Field("idParking") Integer idParking,@Field("aSpots") String aSpots,@Field("commentary") String commentary);

    @FormUrlEncoded
    @POST("getCommentary.php")
    Call<CommentaryAPI> getCommentary(@Field("idParking") Integer idParking);

    @FormUrlEncoded
    @POST("getFoodPlaces.php")
    Call<FoodPlacesAPI> getFoodPlaces(@Field("idStadium") Integer idStadium);

    @FormUrlEncoded
    @POST("addFoodPlaceCommentary.php")
    Call<UserAPI> addFoodPlaceCommentary(@Field("idFoodPlace") Integer idFoodPlace,@Field("Classification") String classification,@Field("commentary") String commentary);

}

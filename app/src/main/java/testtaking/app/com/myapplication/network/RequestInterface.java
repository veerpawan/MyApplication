package testtaking.app.com.myapplication.network;


import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

import testtaking.app.com.myapplication.model.BeanChapter;
import testtaking.app.com.myapplication.model.BeanTopic;
import testtaking.app.com.myapplication.model.Classs;
import testtaking.app.com.myapplication.model.EmailIdByMobileAndDOB;
import testtaking.app.com.myapplication.model.ExecutionAllBean;
import testtaking.app.com.myapplication.model.LoginOtherBean;
import testtaking.app.com.myapplication.model.MainQuestion;
import testtaking.app.com.myapplication.model.MainSubjects;
import testtaking.app.com.myapplication.model.PracticeQuestionList;
import testtaking.app.com.myapplication.model.Report;
import testtaking.app.com.myapplication.model.ReportAllBean;
import testtaking.app.com.myapplication.model.ReportAllTopicsBean;
import testtaking.app.com.myapplication.model.ScheduleTestBean;
import testtaking.app.com.myapplication.model.SiglePracticeQuestion;
import testtaking.app.com.myapplication.model.SmsStatus;
import testtaking.app.com.myapplication.model.SelfEnablerRegistration;
import testtaking.app.com.myapplication.model.HomeQuestionList;
import testtaking.app.com.myapplication.model.LoginSuccess;
import testtaking.app.com.myapplication.model.StudentPerticularSubject;
import testtaking.app.com.myapplication.model.StudentSubject;
import testtaking.app.com.myapplication.model.Success;
import testtaking.app.com.myapplication.model.TestBean;
import testtaking.app.com.myapplication.model.VenueList;
import testtaking.app.com.myapplication.model.CheckAppUpdate;
import testtaking.app.com.myapplication.model.OtpStatus;
import testtaking.app.com.myapplication.model.BdeList;
import testtaking.app.com.myapplication.network.URL_Mapping;


/**
 * Created by pawan on 10/24/2016.
 */

public interface RequestInterface {


    @FormUrlEncoded
    @POST(URL_Mapping.SUBJECTS_SUBDOMAIN_URL)
    Call<List<StudentSubject>> registerUser(@Field("userId") String id);

    @FormUrlEncoded
    @POST(URL_Mapping.SELECTED_SUBJECTS_SUBDOMAIN_URL)
    Call<List<StudentPerticularSubject>> getsubject(@Field("userId") int id);


    @FormUrlEncoded
    @POST(URL_Mapping.CHAPTER_URL)
    Call<List<BeanChapter>> getchapterlist(@Field("subjectId") int id);

    @FormUrlEncoded
    @POST(URL_Mapping.TOPIC_URL)
    Call<List<BeanTopic>> gettopiclist(@Field("chapterId") int id);

    @FormUrlEncoded
    @POST(URL_Mapping.TESTIDSERVLET)
    Call<List<TestBean>> gettestlist(@Field("import_level_id") int id,
                                     @Field("import_level_type") String type,
                                     @Field("user_id") String studentid);


    @FormUrlEncoded
    @POST("ExecutionAll_p")
    Call<List<ExecutionAllBean>> getexecutetopicReport(@Field("student_id") String studentid);


    @FormUrlEncoded
    @POST("ReportList_p")
    Call<List<ReportAllBean>> getreportall(@Field("student_id") String studentid);


    @FormUrlEncoded
    @POST("TestIDReportTable_p")
    Call<List<ReportAllTopicsBean>> getreportalltopics(@Field("studentid") String studentid,
                                                       @Field("test_id") int testid);


    @FormUrlEncoded
    @POST("LoginOtherServlet")
    Call<List<LoginOtherBean>> loginother(@Field("username") String username, @Field("password") String password);


    @FormUrlEncoded
    @POST(URL_Mapping.STUDENTREGISTER)
    Call<List<SelfEnablerRegistration>> registerselfenablerstudent(@Field("user_fname") String str_fname,
                                                                   @Field("user_mname") String str_mname,
                                                                   @Field("user_lname") String str_lname,
                                                                   @Field("user_email") String str_email,
                                                                   @Field("user_password") String str_password,
                                                                   @Field("user_mobile") String user_mobile,
                                                                   @Field("user_imeinumber") String imeinumber,
                                                                   @Field("os_version") String os_version,
                                                                   @Field("mobile_model") String mobile_model,
                                                                   @Field("mobile_brand") String mobile_brand);


    @GET(URL_Mapping.CLASS_URL)
    Call<List<Classs>> get_class();


    @FormUrlEncoded
    @POST("TestList")
    Call<List<TestBean>> gettestlist(@Field("level_id") String level_id, @Field("level_type") String level_type
    );


    @FormUrlEncoded
    @POST(URL_Mapping.Scheduletest)
    Call<List<ScheduleTestBean>> scheduletestlist(@Field("class_id") String Class,
                                                  @Field("class_section") String section,
                                                  @Field("test_id") String test_idd,
                                                  @Field("registration_type") String registration_type,
                                                  @Field("student_id") String studentid,
                                                  @Field("subject_id") String ssubject_id);


    @FormUrlEncoded
    @POST(URL_Mapping.BDE_LIST)
    Call<List<BdeList>> bdelist(@Field("user_role") String s);


    @FormUrlEncoded
    @POST(URL_Mapping.SELECTED_UNIQUEIMEI)
    Call<List<ScheduleTestBean>> getuniqueimei(@Field("user_imei") String imeinumber);


    @FormUrlEncoded
    @POST(URL_Mapping.SEND_OTP)
    Call<List<OtpStatus>> sendotp(@Field("user_mobile") String mobile_number);

    @FormUrlEncoded
    @POST(URL_Mapping.GETEMAILBYIMEI)
    Call<List<EmailIdByMobileAndDOB>> getemailbyimei(@Field("user_imei") String device_imei);


    @FormUrlEncoded
    @POST(URL_Mapping.RESETEMAIL)
    Call<List<SmsStatus>> reset_email(@Field("user_name") String user_name,
                                      @Field("user_old_email") String emailId,
                                      @Field("user_new_email") String new_email);

    @FormUrlEncoded
    @POST(URL_Mapping.GETEMAILFORCHANGEEMAIL)
    Call<List<EmailIdByMobileAndDOB>> getemailbymobileanddob(@Field("user_mobile") String str_mobile,
                                                             @Field("user_dob") String str_dob);


    @FormUrlEncoded
    @POST(URL_Mapping.STUDENTFORGETPASSWORD)
    Call<List<SmsStatus>> reset_password(@Field("user_email") String email);

    @FormUrlEncoded
    @POST("CheckAppUpdate")
    Call<CheckAppUpdate> checkappversion(@Field("os_type") String id);


    @FormUrlEncoded
    @POST(URL_Mapping.STUDENTREGISTERCLASS)
    Call<List<LoginSuccess>> registerclassdetails(@Field("user_email") String email_id,
                                                  @Field("user_board") String selected_board_id,
                                                  @Field("user_class") String selected_class,
                                                  @Field("user_stream") String str_stream,
                                                  @Field("user_medium") String selected_medium,
                                                  @Field("user_subject_ids") String subject_ids);


    @FormUrlEncoded
    @POST(URL_Mapping.STUDENTREGISTERPERSONAL)
    Call<List<SmsStatus>> registerpersonaldetail(@Field("user_email") String email_id,
                                                 @Field("user_dob") String str_dob,
                                                 @Field("user_gender") String str_gender,
                                                 @Field("user_bdeemail") String selected_bdeemail);


    @FormUrlEncoded
    @POST(URL_Mapping.STUDENTREGISTERADDRESS)
    Call<List<SmsStatus>> registeraddressdetail(@Field("user_email") String email_id,
                                                @Field("user_state") String str_state,
                                                @Field("user_city") String str_city,
                                                @Field("user_locality") String str_locality,
                                                @Field("user_pincode") String str_pincode);

    @FormUrlEncoded
    @POST(URL_Mapping.STUDENTLOGIN)
    Call<List<LoginSuccess>> userLogin(@Field("user_email") String email,
                                       @Field("user_password") String password);

    @Multipart
    @POST(URL_Mapping.WALKINDATAREGISTER)
    Call<List<SmsStatus>> registerwalkins(@Query("walk_in_role") String walk_in_role,
                                          @Query("walk_in_name") String walk_in_name,
                                          @Query("walk_in_dob") String walk_in_dob,
                                          @Query("walk_in_email") String walk_in_email,
                                          @Query("walk_in_mobile") String walk_in_mobile,
                                          @Query("walk_in_gender") String walk_in_gender,
                                          @Query("walk_in_address") String walk_in_address,
                                          @Query("walk_in_state") String walk_in_state,
                                          @Query("walk_in_pin") String walk_in_pin,

                                          @Query("walk_in_X_board") String walk_in_X_board,
                                          @Query("walk_in_X_school") String walk_in_X_school,
                                          @Query("walk_in_X_yop") String walk_in_X_yop,
                                          @Query("walk_in_X_percentage") String walk_in_X_percentage,

                                          @Query("walk_in_XII_stream") String walk_in_XII_stream,
                                          @Query("walk_in_XII_board") String walk_in_XII_board,
                                          @Query("walk_in_XII_school") String walk_in_XII_school,
                                          @Query("walk_in_XII_yop") String walk_in_XII_yop,
                                          @Query("walk_in_XII_percentage") String walk_in_XII_percentage,

                                          @Query("walk_in_grad_univ") String walk_in_grad_univ,
                                          @Query("walk_in_grad_clgname") String walk_in_grad_clgname,
                                          @Query("walk_in_grad_degree") String walk_in_grad_degree,
                                          @Query("walk_in_grad_stream") String walk_in_grad_stream,
                                          @Query("walk_in_grad_yop") String walk_in_grad_yop,
                                          @Query("walk_in_grad_percentage") String walk_in_grad_percentage,

                                          @Query("walk_in_post_grad_uniname") String walk_in_post_grad_uniname,
                                          @Query("walk_in_post_grad_clgname") String walk_in_post_grad_clgname,
                                          @Query("walk_in_post_graddegree") String walk_in_post_graddegree,
                                          @Query("walk_in_post_grad_stream") String walk_in_post_grad_stream,
                                          @Query("walk_in_post_grad_yop") String walk_in_post_grad_yop,
                                          @Query("walk_in_post_grad_percentage") String walk_in_post_grad_percentage,

                                          @Query("walk_in_father_name") String walk_in_father_name,
                                          @Query("walk_in_father_education") String walk_in_father_education,
                                          @Query("walk_in_father_occupation") String walk_in_father_occupation,

                                          @Query("walk_in_mother_name") String walk_in_mother_name,
                                          @Query("walk_in_mother_education") String walk_in_mother_education,
                                          @Query("walk_in_mother_occupation") String walk_in_mother_occupation,

                                          @Query("walk_in_sibling_1_name") String walk_in_sibling_1_name,
                                          @Query("walk_in_sibling_1_education") String walk_in_sibling_1_education,
                                          @Query("walk_in_sibling_1_occupation") String walk_in_sibling_1_occupation,

                                          @Query("walk_in_sibling_2_name") String walk_in_sibling_2_name,
                                          @Query("walk_in_sibling_2_education") String walk_in_sibling_2_education,
                                          @Query("walk_in_sibling_2_occupation") String walk_in_sibling_2_occupation,

                                          @Query("walk_in_sibling_3_name") String walk_in_sibling_3_name,
                                          @Query("walk_in_sibling_3_education") String walk_in_sibling_3_education,
                                          @Query("walk_in_sibling_3_occupation") String walk_in_sibling_3_occupation,

                                          @Query("walk_in_sibling_4_name") String walk_in_sibling_4_name,
                                          @Query("walk_in_sibling_4_education") String walk_in_sibling_4_education,
                                          @Query("walk_in_sibling_4_occupation") String walk_in_sibling_4_occupation,

                                          @Query("walk_in_venue") String walk_in_venue,
                                          @Part MultipartBody.Part file,
                                          @Part("desc") RequestBody name);

    @GET(URL_Mapping.VENUE_LIST)
    Call<List<VenueList>> getvenueslist();


    @FormUrlEncoded
    @POST(URL_Mapping.CLASS_LIST_BY_BOARD)
    Call<List<Classs>> getAllBoards(@Field("user_board") String boardId);


    @FormUrlEncoded
    @POST(URL_Mapping.SUBJECT_LIST_BY_CLASSID)
    Call<List<StudentSubject>> getAllSubjectBasedClassId(@Field("class_id") int classId);


    @FormUrlEncoded
    @POST("getFbPostDetailsR")
    Call<List<HomeQuestionList>> fbquestionpost(
            @Field("student_id") int id,
            @Field("student_role") int role
    );


    @FormUrlEncoded
    @POST("TestStart_New")
    Call<MainQuestion> getListOfQuestions(@Field("testid") String s,
                                          @Field("S_ID") String s1);

    @FormUrlEncoded
    @POST("SubmitAnswer")
    Call<Success> sendquestiondata(@Field("S_ID") String str_serial_id,
                                   @Field("jsonData") String question);

    @FormUrlEncoded
    @POST("getSubjectFromUserId")
    Call<List<MainSubjects>> getSubjectList(@Field("userId") String userid);

    @FormUrlEncoded
    @POST("getChaptersListFromSubjectId")
    Call<List<BeanChapter>> getChaptersList(@Field("subjectId") String subject_id);


    @FormUrlEncoded
    @POST("getAllTestFromChapterId")
    Call<List<TestBean>> gettestlist(@Field("chapterId") int level_id);


    @FormUrlEncoded
    @POST("getTestIdFromSelfEnablerApp")
    Call<List<ScheduleTestBean>> scheduletestlist(@Field("testId") String test_id,
                                                  @Field("user_id") String studentid);


    @FormUrlEncoded
    @POST("DisplayReport")
    Call<List<Report>> getReportList(@Field("test_id") String s,
                                     @Field("STUD_ID") String s1);

    @FormUrlEncoded
    @POST("getPracticeQuestionForApp")
    Call<List<PracticeQuestionList>> getQuestionsList(@Field("import_level_id") int import_level_id,
                                                      @Field("import_level") String import_level_type);
    @FormUrlEncoded
    @POST("getPracticeQuestionDisplayForApp")
    Call<List<SiglePracticeQuestion>> getIndivisualQuestion(@Field("qnumber")int q_number);
}



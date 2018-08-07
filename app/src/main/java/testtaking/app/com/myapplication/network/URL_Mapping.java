package testtaking.app.com.myapplication.network;

/**
 * Created by Deepak Upadhyay on 18-Aug-16.
 */
public interface URL_Mapping {


    //######################### Localhost URLs  ##################################


   /* String SUBJECTS_URL = Constants.T4A_IP + "/";
    String BEST_USER_REGISTER = Constants.T4A_IP + "/best_user_register";
    String BEST_USER_REFFERED_BY = Constants.T4A_IP + "/GetRefferedByService";
    String REPORT_URL = Constants.T4A_IP + "/DisplayReport";
    String REPORT_LIST_URL = Constants.T4A_IP + "/ReportList";
    String REPORT_TABLE_URL = Constants.T4A_IP + "/TestIDReportTable";
    String REPORT_SUBJECTWISE_URL = Constants.T4A_IP + "/TestIDSubjectWiseReport";
    String TEST_EXECUTION_ALL_LIST_URL = Constants.T4A_IP + "/ExecutionAll";
    String TEST_ID_URL = Constants.T4A_IP + "/TestIDServlet";
    String TEST_ID_EXECUTION_ALL_URL = Constants.T4A_IP + "/TestIDExecutionAll";
    String QUESTION_URL = Constants.T4A_IP + "/TestStart";
    String QUESTION_WRONG_URL = Constants.T4A_IP + "/WrongQuestions";
    String SUBMIT_ANSWER_URL = Constants.T4A_IP + "/SubmitAnswer";
    String SUBMIT_ANSWER_FREQUENTLY_URL = Constants.T4A_IP + "/SubmitAnswerFrequently";
    String CHAPTER_LIST_URL = Constants.T4A_IP + "/ChapterServlet";
    String SUBJECT_LIST_URL = Constants.T4A_IP + "/SubjectServlet";
    String TOPIC_lIST_URL = Constants.T4A_IP + "/TopicServlet";
    String CHECK_VERSION_UPDATE_URL = Constants.T4A_IP + "CheckAppUpdate";*/
    String TESTIDSERVLET = "GetTestByLevelTypeLevelId";

    String SUBJECTS_SUBDOMAIN_URL = "getAllSubjectGSON";
    String CHAPTER_URL = "getAllChaptersBasedOnSubjectIdGSON";
    String TOPIC_URL = "getAllTopicsBasedOnChapterIdGSON";
    String CLASS_URL = "getAllclass";
    String Scheduletest = "Scheduletest";
    String BDE_LIST = "getrefferedbd";
    String REGISTER_BEST_USER = "registerbestuser";
    String SELECTED_SUBJECTS_SUBDOMAIN_URL = "getAllSubjectBasedOnUserIdGSON";
    String SELECTED_UNIQUEIMEI = "getimeiofuser";
    String SEND_OTP = "sendotp";
    String GETEMAILBYIMEI = "getEmailByImei";
    String RESETEMAIL = "updateemailid";
    String GETEMAILFORCHANGEEMAIL = "getEmailByMobileAndDOB";
    String STUDENTFORGETPASSWORD = "studentForgetPassword";
    String STUDENTLOGIN = "studentLogin";
    String STUDENTREGISTER = "registerbestuser";
    String STUDENTREGISTERCLASS = "studentRegistrationClass";
    String STUDENTREGISTERPERSONAL = "studentRegistrationPersonal";
    String STUDENTREGISTERADDRESS = "studentRegistrationAddress";
    String WALKINDATAREGISTER = "registerWalkInData";
    String CHECKAPPUPDATE = "CheckAppUpdate";
    String GETDEMOTESTLIST = "GetDemoTestList";
    String VENUE_LIST = "getwalkinvenue";
    String CLASS_LIST_BY_BOARD = "getClassByBoardId";
    String SUBJECT_LIST_BY_CLASSID = "getSubjectsByClassId";


    //######################### END URL  ##################################


    //######################### Localhost URLs  ##################################
   /* String BEST_USER_REGISTER = Constants.T4A_IP + "/StudentAssessment/best_user_register";
    String BEST_USER_REFFERED_BY = Constants.T4A_IP + "/StudentAssessment/GetRefferedByService";
    String REPORT_URL = Constants.T4A_IP + "/StudentAssessment/DisplayReport";
    String REPORT_LIST_URL = Constants.T4A_IP + "/StudentAssessment/ReportList";
    String REPORT_TABLE_URL = Constants.T4A_IP + "/StudentAssessment/TestIDReportTable";
    String REPORT_SUBJECTWISE_URL = Constants.T4A_IP + "/StudentAssessment/TestIDSubjectWiseReport";
    String TEST_EXECUTION_ALL_LIST_URL = Constants.T4A_IP + "/StudentAssessment/ExecutionAll";
    String TEST_ID_URL = Constants.T4A_IP + "/StudentAssessment/TestIDServlet";
    String TEST_ID_EXECUTION_ALL_URL = Constants.T4A_IP + "/StudentAssessment/TestIDExecutionAll";
    String QUESTION_URL = Constants.T4A_IP + "/StudentAssessment/TestStart";
    String QUESTION_WRONG_URL = Constants.T4A_IP + "/StudentAssessment/WrongQuestions";
    String SUBMIT_ANSWER_URL = Constants.T4A_IP + "/StudentAssessment/SubmitAnswer";
    String SUBMIT_ANSWER_FREQUENTLY_URL = Constants.T4A_IP + "/StudentAssessment/SubmitAnswerFrequently";
    String CHAPTER_LIST_URL = Constants.T4A_IP + "/StudentAssessment/ChapterServlet";
    String SUBJECT_LIST_URL = Constants.T4A_IP + "/StudentAssessment/SubjectServlet";
    String TOPIC_lIST_URL = Constants.T4A_IP + "/StudentAssessment/TopicServlet";
    String CHECK_VERSION_UPDATE_URL = Constants.T4A_IP + "/StudentAssessment/CheckAppUpdate";
    String SUBJECTS_SUBDOMAIN_URL = "studentAppHomeAction.do?action=getAllSubjectGSON";
    String CHAPTER_URL = "studentAppHomeAction.do?action=getAllChaptersBasedOnSubjectIdGSON";
    String TOPIC_URL = "studentAppHomeAction.do?action=getAllTopicsBasedOnChapterIdGSON";
    String SUBJECTS_URL = Constants.T4A_IP + "/StudentAssessment/";
    String CLASS_URL = "studentAppHomeAction.do?action=getAllclass";
    String TEST_ID_LIST_URL = Constants.T4A_IP + "/StudentAssessment/";
    String Scheduletest = "studentAppHomeAction.do?action=Scheduletest";
    String BDE_LIST = "registrationAppAction.do?action=getrefferedbd";
    String REGISTER_BEST_USER = "registrationAppAction.do?action=registerbestuser";
    String SELECTED_SUBJECTS_SUBDOMAIN_URL = "studentAppHomeAction.do?action=getAllSubjectBasedOnUserIdGSON";
    String SELECTED_UNIQUEIMEI = " registrationAppAction.do?action=getimeiofuser";
    String SEND_OTP = "registrationAppAction.do?action=sendotp";
    String GETEMAILBYIMEI = "registrationAppAction.do?action=getEmailByImei";
    String RESETEMAIL = "loginAppAction.do?action=updateemailid";
    String GETEMAILFORCHANGEEMAIL = "loginAppAction.do?action=getEmailByMobileAndDOB";
    String STUDENTFORGETPASSWORD = "loginAppAction.do?action=studentForgetPassword";
    String STUDENTLOGIN = "loginAppAction.do?action=studentLogin";
    String STUDENTREGISTER = "registrationAppAction.do?action=registerbestuser";
    String STUDENTREGISTERCLASS = "registrationAppAction.do?action=studentRegistrationClass";
    String STUDENTREGISTERPERSONAL = "registrationAppAction.do?action=studentRegistrationPersonal";
    String STUDENTREGISTERADDRESS= "registrationAppAction.do?action=studentRegistrationAddress";*/
}

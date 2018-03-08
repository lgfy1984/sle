package com.qmh.sle.api;

import com.kymjs.rxvolley.client.HttpCallback;
import com.kymjs.rxvolley.client.HttpParams;

import com.qmh.sle.AppContext;
import com.qmh.sle.bean.CodeTree;
import com.qmh.sle.bean.Project;
import com.qmh.sle.bean.SPatient;
import com.qmh.sle.bean.SPatientD;
import com.qmh.sle.bean.ShippingAddress;
import com.qmh.sle.common.ImageUtils;
import com.qmh.sle.utils.JsonUtils;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import static com.qmh.sle.api.AsyncHttpHelp.get;
import static com.qmh.sle.api.AsyncHttpHelp.getPrivateTokenWithParams;
import static com.qmh.sle.api.AsyncHttpHelp.jsonPost;
import static com.qmh.sle.api.AsyncHttpHelp.post;

/**
 * Git@OSC API
 * <p/>
 * Created by 火蚁 on 15/4/10.
 */
public class SleApi {

//    public final static String HOST = "10.227.66.64:8080/";
//    public final static String HOST = "172.23.92.18:8080/";
//    public final static String HOST = "192.168.1.26:8080/";
    public final static String HOST = "47.75.46.74:8080/";
//    public final static String HOST = "147.8.103.232:8080/";
    private static final String API_VERSION = "sle/";// API版本
    public final static String HTTP = "http://";
    public final static String BASE_URL = HTTP + HOST + API_VERSION;
    public final static String NO_API_BASE_URL = HTTP + HOST;
    public final static String PROJECTS = BASE_URL + "projects/";
    public final static String USER = BASE_URL + "user/";
    public final static String EVENT = BASE_URL + "events/";
    public final static String NOTIFICATION = USER + "notifications/";
    public final static String VERSION = BASE_URL + "app_version/new/android";

    public static void login(String account, String passwod, HttpCallback handler) {
        HttpParams params = AsyncHttpHelp.getHttpParams();
        params.put("username", account);
        params.put("password", urlEncode(passwod));
        AsyncHttpHelp.post(BASE_URL + "session", params, handler);
    }

    private static String urlEncode(String value) {
        try {
            return URLEncoder.encode(value, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void getExplorePatient(int page,String departid ,HttpCallback handler) {
        HttpParams params = AsyncHttpHelp.getHttpParams();
        params.put("departid", departid);
        params.put("page", page);
        get(BASE_URL + "patient", params, handler);
    }


    public static void getPatientD(String id,String order, HttpCallback handler) {
        HttpParams params = AsyncHttpHelp.getHttpParams();
        params.put("id", id);
        params.put("order", order);
        get(BASE_URL + "getpatientd", params, handler);
    }

    public static void putPatientD(SPatientD sPatientD, HttpCallback handler) {
        HttpParams params = AsyncHttpHelp.getHttpParams();
        String sPatientDjson="";
        try {
             sPatientDjson =  JsonUtils.bean2Json(sPatientD);
        } catch (IOException e) {
            e.printStackTrace();
        }
        params.putJsonParams(sPatientDjson);
        jsonPost(BASE_URL + "putpatientd", params, handler);
    }

    public static void putPatient(SPatient sPatient, HttpCallback handler) {
        HttpParams params = AsyncHttpHelp.getHttpParams();
        String sPatientjson="";
        try {
            sPatientjson =  JsonUtils.bean2Json(sPatient);
        } catch (IOException e) {
            e.printStackTrace();
        }
        params.putJsonParams(sPatientjson);
        jsonPost(BASE_URL + "putpatient", params, handler);
    }

    public static void updatePatientD(SPatientD sPatientD, HttpCallback handler) {
        HttpParams params = AsyncHttpHelp.getHttpParams();
        String sPatientDjson="";
        try {
            sPatientDjson =  JsonUtils.bean2Json(sPatientD);
        } catch (IOException e) {
            e.printStackTrace();
        }
        params.putJsonParams(sPatientDjson);
        jsonPost(BASE_URL + "updatepatientd", params, handler);
    }

    public static void updatePatient(SPatient sPatient, HttpCallback handler) {
        HttpParams params = AsyncHttpHelp.getHttpParams();
        String sPatientjson="";
        try {
            sPatientjson =  JsonUtils.bean2Json(sPatient);
        } catch (IOException e) {
            e.printStackTrace();
        }
        params.putJsonParams(sPatientjson);
        jsonPost(BASE_URL + "updatepatient", params, handler);
    }

    public static void searchPatients(String query, int page,String departid ,HttpCallback handler) {
        HttpParams params = AsyncHttpHelp.getHttpParams();
        params.put("page", page);
        params.put("query", query);
        params.put("departid", departid);
        get(BASE_URL + "searchpatients", params, handler);
    }
    public static void modifypwd(String uid,String username,String pwdold, String pwdnew, HttpCallback callback) {
        HttpParams params = AsyncHttpHelp.getHttpParams();
        params.put("pwdold", pwdold);
        params.put("pwdnew", pwdnew);
        params.put("username", username);
        params.put("uid", uid);
        post(BASE_URL + "modifypwd", params, callback);
    }

    public static void getExploreLatestProject(int page, HttpCallback handler) {
        HttpParams params = AsyncHttpHelp.getHttpParams();
        params.put("page", page);
        AsyncHttpHelp.get(PROJECTS + "latest", params, handler);
    }

    public static void getExploreFeaturedProject(int page, HttpCallback handler) {
        HttpParams params = AsyncHttpHelp.getHttpParams();
        params.put("page", page);
        get(PROJECTS + "featured", params, handler);
    }

    public static void getExplorePopularProject(int page, HttpCallback handler) {
        HttpParams params = AsyncHttpHelp.getHttpParams();
        params.put("page", page);
        get(PROJECTS + "popular", params, handler);
    }

    public static void getMyProjects(int page, HttpCallback handler) {
        HttpParams params = AsyncHttpHelp.getHttpParams();
        params.put("page", page);
        get(PROJECTS, params, handler);
    }

    public static void getMyEvents(int page, HttpCallback handler) {
        HttpParams params = AsyncHttpHelp.getHttpParams();
        params.put("page", page);
        get(EVENT, params, handler);
    }

    public static void getStarProjects(String uid, int page, HttpCallback handler) {
        HttpParams params = AsyncHttpHelp.getHttpParams();
        params.put("page", page);
        get(USER + uid + "/stared_projects", params, handler);
    }

    public static void getWatchProjects(String uid, int page, HttpCallback handler) {
        HttpParams params = AsyncHttpHelp.getHttpParams();
        params.put("page", page);
        get(USER + uid + "/watched_projects", params, handler);
    }

    public static void getProject(String pId, HttpCallback handler) {
        HttpParams params = AsyncHttpHelp.getHttpParams();
        get(PROJECTS + pId, params, handler);
    }

    public static void getProject(String userName, String projectName, HttpCallback handler) {
        HttpParams params = AsyncHttpHelp.getHttpParams();
        get(PROJECTS + userName + "%2F" + projectName, params, handler);
    }

    public static void searchProjects(String query, int page, HttpCallback handler) {
        HttpParams params = AsyncHttpHelp.getHttpParams();
        params.put("page", page);
        get(PROJECTS + "search/" + URLEncoder.encode(query), params, handler);
    }

    public static void getUserProjects(String uid, int page, HttpCallback handler) {
        HttpParams params = AsyncHttpHelp.getHttpParams();
        params.put("page", page);
        get(USER + uid + "/" + "projects", params, handler);
    }

    public static void getUserEvents(String uid, int page, HttpCallback handler) {
        HttpParams params = AsyncHttpHelp.getHttpParams();
        params.put("page", page);
        get(EVENT + "user" + "/" + uid, params, handler);
    }

    /**
     * 获取用户信息
     */
    public static void getUserInfo(String uid, HttpCallback callback) {
        HttpParams params = AsyncHttpHelp.getHttpParams();
        get("http://git.oschina.net/api/v3/users/"  + uid, params, callback);
    }


    public static void getProjectIssues(String pId, int page, HttpCallback handler) {
        HttpParams params = AsyncHttpHelp.getHttpParams();
        params.put("page", page);
        get(PROJECTS + pId + "/" + "issues", params, handler);
    }


    public static void getIssueDetail(String pId, String issueId, HttpCallback handler) {
        get(PROJECTS + pId + "/issues/" + issueId, handler);
    }

    public static void getIssueComments(String pId, String issueId, int page, HttpCallback
            handler) {
        HttpParams params = AsyncHttpHelp.getHttpParams();
        params.put("page", page);
        get(PROJECTS + pId + "/issues/" + issueId + "/notes", params, handler);
    }

    public static void pubIssueComment(String pId, String issueId, String body, HttpCallback
            handler) {
        HttpParams params = AsyncHttpHelp.getHttpParams();
        params.put("body", body);
        post(PROJECTS + pId + "/issues/" + issueId + "/notes", params, handler);
    }

    public static void getCodeFileDetail(String projectId, String file_path, String ref,
                                         HttpCallback handler) {
        HttpParams params = AsyncHttpHelp.getHttpParams();
        params.put("file_path", file_path);
        params.put("ref", ref);
        get(PROJECTS + projectId + "/repository/files", params, handler);
    }


    public static void getReadMeFile(String projectId, HttpCallback handler) {
        HttpParams params = AsyncHttpHelp.getHttpParams();
        get(PROJECTS + projectId + "/readme", params, handler);
    }

    public static void getCommitDiffList(String projectId, String commitId, HttpCallback handler) {
        HttpParams params = AsyncHttpHelp.getHttpParams();
        get(PROJECTS + projectId + "/repository/commits/" + commitId + "/diff", params, handler);
    }

    public static void getCommitCommentList(String projectId, String commitId, HttpCallback
            handler) {
        HttpParams params = AsyncHttpHelp.getHttpParams();
        get(PROJECTS + projectId + "/repository/commits/" + commitId + "/comment", params, handler);
    }

    public static void getCommitFileDetail(String projectId, String commitId, String filePath,
                                           HttpCallback handler) {
        HttpParams params = AsyncHttpHelp.getHttpParams();
        params.put("filepath", filePath);
        get(PROJECTS + projectId + "/repository/commits/" + commitId + "/blob", params, handler);
    }

    public static void getProjectMembers(String projectId, HttpCallback handler) {
        HttpParams params = AsyncHttpHelp.getHttpParams();
        get(PROJECTS + projectId + "/members", params, handler);
    }

    /**
     * 加载项目的里程碑
     */
    public static void getProjectMilestone(String projectId, HttpCallback handler) {
        HttpParams params = AsyncHttpHelp.getHttpParams();
        get(PROJECTS + projectId + "/milestones", params, handler);
    }

    /**
     * 创建一个issue
     */
    public static void pubCreateIssue(String projectId, String title, String description, String
            assignee_id, String milestone_id, HttpCallback handler) {
        HttpParams params = AsyncHttpHelp.getHttpParams();
        params.put("description", description);
        params.put("title", title);
        params.put("assignee_id", assignee_id);
        params.put("milestone_id", milestone_id);
        post(PROJECTS + projectId + "/issues", params, handler);
    }

    /**
     * 上传文件
     */
    public static void upLoadFile(File file, HttpCallback handler) throws Exception {
        HttpParams params = AsyncHttpHelp.getHttpParams();
        String suffix = file.getName().substring(file.getName().lastIndexOf(".") + 1, file.getName().length()).toLowerCase();
        params.put("files", ImageUtils.fileToByteArray(file), "image/" + suffix, file.getName());
        post("https://git.oschina.net/upload", params, handler);
    }

    /***
     * 更新用户头像
     */
    public static void updateUserProtrait(String protraitUrl, HttpCallback handler) {
        HttpParams params = AsyncHttpHelp.getHttpParams();
        params.put("path", protraitUrl);
        post(USER + "portrait", params, handler);
    }

    /***
     * 获取通知
     *
     * @param filter
     * @param all
     * @param projectId
     * @param handler
     */
    public static void getNotification(String filter, String all, String projectId, HttpCallback
            handler) {
        HttpParams params = getPrivateTokenWithParams();
        params.put("filter", filter);
        params.put("all", all);
        get(NOTIFICATION, params, handler);
    }

    /**
     * 设置通知为已读
     */
    public static void setNotificationReaded(String notificationId, HttpCallback handler) {
        HttpParams params = getPrivateTokenWithParams();
        get(NOTIFICATION + notificationId, params, handler);
    }

    /**
     * 获得App更新的信息
     */
    public static void getUpdateInfo(HttpCallback handler) {
        get(VERSION, handler);
    }

    /**
     * 获得语言列表
     */
    public static void getLanguageList(HttpCallback handler) {
        get(PROJECTS + "languages", handler);
    }

    /**
     * 根据语言的ID获得项目的列表
     */
    public static void getLanguageProjectList(String languageId, int page, HttpCallback handler) {
        HttpParams params = getPrivateTokenWithParams();
        params.put("page", page);
        get(PROJECTS + "languages/" + languageId, params, handler);
    }

    /**
     * star or unstar一个项目
     */
    public static void starProject(String projectId, HttpCallback handler) {
        HttpParams params = getPrivateTokenWithParams();
        post(PROJECTS + projectId + "/star", params, handler);
    }

    public static void unStarProject(String projectId, HttpCallback handler) {
        HttpParams params = getPrivateTokenWithParams();
        post(PROJECTS + projectId + "/unstar", params, handler);
    }

    public static void watchProject(String projectId, HttpCallback handler) {
        HttpParams params = getPrivateTokenWithParams();
        post(PROJECTS + projectId + "/watch", params, handler);
    }

    public static void unWatchProject(String projectId, HttpCallback handler) {
        HttpParams params = getPrivateTokenWithParams();
        post(PROJECTS + projectId + "/unwatch", params, handler);
    }

    public static void getRandomProject(HttpCallback handler) {
        HttpParams params = getPrivateTokenWithParams();
        params.put("luck", 1);
        get(PROJECTS + "random", params, handler);
    }

    public static void updateRepositoryFiles(String projectId, String ref, String file_path,
                                             String branch_name, String content, String
                                                     commit_message, HttpCallback handler) {
        HttpParams params = getPrivateTokenWithParams();
        params.put("ref", ref);
        params.put("file_path", file_path);
        params.put("branch_name", branch_name);
        params.put("content", content);
        params.put("commit_message", commit_message);
        post(PROJECTS + projectId + "/repository/files", params, handler);
    }


    /**
     * 获取用户的收货信息
     */
    public static void getUserShippingAddress(String uid, HttpCallback handler) {
        HttpParams params = getPrivateTokenWithParams();
        get(BASE_URL + "users/" + uid, params, handler);
    }

    /**
     * 更新用户的收货信息
     */
    public static void updateUserShippingAddress(String uid, ShippingAddress shippingAddress,
                                                 HttpCallback handler) {
        HttpParams params = getPrivateTokenWithParams();
        params.put("name", shippingAddress.getName());
        params.put("tel", shippingAddress.getTel());
        params.put("address", shippingAddress.getAddress());
        params.put("comment", shippingAddress.getComment());
        post(BASE_URL + "users/" + uid, params, handler);
    }

    /**
     * 获得抽奖活动的信息
     */
    public static void getLuckMsg(HttpCallback handler) {
        HttpParams params = getPrivateTokenWithParams();
        get(PROJECTS + "luck_msg", handler);
    }


    /**
     * 用户反馈,其实就是发了个issue
     *
     * @param message
     * @param title
     * @param callback
     */
    public static void feedback(String title, String message, HttpCallback callback) {
        HttpParams params = AsyncHttpHelp.getHttpParams();
        params.put("description", message);
        params.put("title", title);
        params.put("assignee_id", 355540);
        params.put("milestone_id", "");
        post(PROJECTS + "142148/issues", params, callback);
    }

    /**
     * 下载二进制文件
     *
     * @param project
     * @param codeTree
     * @param path
     * @param refName
     * @param callback
     */
    public static void downloadFile(Project project, CodeTree codeTree, String path, String refName, HttpCallback callback) {
        String uri = SleApi.NO_API_BASE_URL + project.getPathWithNamespace() + "/raw/" + refName + "/" + path + codeTree.getName() + "?private_token=" + AppContext.getToken();
        get(uri, callback);
    }
}

package common;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.Map;

public class RestAssuredUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    protected RequestSpecBuilder builder = new RequestSpecBuilder();
    protected String method;
    protected String url;
    /**
     * RestAssuredUtils constructor to pass the initial settings for the respected method
     *
     * @param uri
     * @param method
     */

    public RestAssuredUtils(String baseUrl, String uri, String method) {

        //Formulate the API url
        this.url = baseUrl + uri;
        LOGGER.info(this.url);
        this.method = method;
    }

    /**
     * RestAssuredUtils constructor to pass the initial settings for the respected method
     *
     * @param uri
     * @param method
     * @param token
     */

    public RestAssuredUtils(String baseUrl, String uri, String method, String token) {

        //Formulate the API url
        this.url = baseUrl + uri;
        this.method = method;

        if (token != null)
            builder.addHeader("Authorization", "Bearer " + token);
    }

    /**
     * ExecuteAPI to execute the API for GET/POST/DELETE/PUT/PATCH
     *
     * @return ResponseOptions<Response>
     */

    private ResponseOptions<Response> executeAPI() {
        RequestSpecification requestSpecification = builder.build();
        RequestSpecification request = RestAssured.given();
        request.contentType(ContentType.JSON);

        request.log().all();
        request.spec(requestSpecification);

        if (this.method.equalsIgnoreCase(String.valueOf(ApiRequestTypeConstant.POST)))
            return request.post(this.url);
        else if (this.method.equalsIgnoreCase(String.valueOf(ApiRequestTypeConstant.GET)))
            return request.get(this.url);
        else if (this.method.equalsIgnoreCase(String.valueOf(ApiRequestTypeConstant.PUT)))
            return request.put(this.url);
        else if (this.method.equalsIgnoreCase(String.valueOf(ApiRequestTypeConstant.PATCH)))
            return request.patch(this.url);
        else if (this.method.equalsIgnoreCase(String.valueOf(ApiRequestTypeConstant.DELETE)))
            return request.delete(this.url);
        return null;
    }

    /**
     * Authenticate to get the token variable
     *
     * @param body
     * @return string token
     */
    public String authenticate(Map<String, String> body) {
        builder.setContentType(ContentType.URLENC);
        builder.addFormParams(body);
        return executeAPI().getBody().jsonPath().get("access_token");
    }

    /**
     * Authenticate to get the token variable
     *
     * @param body
     * @return string token
     */
    public ResponseOptions<Response> RequestWithBody(Object body, Map<String, String> headerParams) {
        builder.setBody(body);
        builder.addHeaders(headerParams);
        return executeAPI();
    }

    /**
     * Executing API with query params being passed as the input of it
     *
     * @param queryPath
     * @return Reponse
     */
    public ResponseOptions<Response> executeWithQueryParams(Map<String, Object> queryPath, Map<String, String> headerParams) {
        LOGGER.info("query paths are :", queryPath);
        builder.addQueryParams(queryPath);
        builder.addHeaders(headerParams);
        return executeAPI();
    }

    /**
     * ExecuteWithPathParams
     *
     * @param pathParams
     * @return
     */
    public ResponseOptions<Response> executeWithPathParams(Map<String, String> pathParams,Map<String, String> headerParams) {
        LOGGER.info("path parms are :", pathParams);
        builder.addPathParams(pathParams);
        builder.addHeaders(headerParams);
        return executeAPI();
    }

    /**
     * ExecuteWithurl
     *
     * @return
     */
    public ResponseOptions<Response> executeGetapi() {
        return executeAPI();
    }

    /**
     * ExecuteWithurl
     *
     * @return
     */
    public ResponseOptions<Response> executePostApi() {
        return executeAPI();
    }

    /**
     * ExecuteWithurl
     *
     * @return
     */
    public ResponseOptions<Response> executePatchApi() {
        return executeAPI();
    }

    /**
     * ExecuteWithPathParamsAndBody
     *
     * @param pathParams
     * @param body
     * @return
     */
    public ResponseOptions<Response> executeWithPathParamsAndBody(Map<String, String> pathParams, Map<String, String> body,
                                                                  Map<String, String> headerParams) {
        builder.setBody(body);
        builder.addPathParams(pathParams);
        builder.addHeaders(headerParams);
        return executeAPI();
    }

    /**
     * ExecuteWithPathParamsAndBody
     *
     * @param headerParms
     * @return
     */
    public ResponseOptions<Response> executeWithAdditionalHeaders(Map<String, String> headerParms, Map<String, Object> body) {
        LOGGER.info("query Headers are :", headerParms);
        builder.setBody(body);
        builder.addHeaders(headerParms);
        return executeAPI();
    }

    /**
     * ExecuteWithPathParamsAndBody
     *
     * @param cookies
     * @return
     */
    public ResponseOptions<Response> executeWithAddCookies(Map<String, String> cookies) {
        LOGGER.info("query Headers are :", cookies);
        builder.addCookies(cookies);
        return executeAPI();
    }

    /**
     * ExecuteWithPathParamsAndBody
     *
     * @param formParms
     * @return
     */
    public ResponseOptions<Response> executeWithFormParms(Map<String, String> formParms,Map<String, String> headerParams) {
        LOGGER.info("query Headers are :", formParms);
        builder.addFormParams(formParms);
        builder.addHeaders(headerParams);
        return executeAPI();
    }

    /**
     * Executing API with query params being passed as the input of it
     *
     * @param queryPath
     * @return Reponse
     */
    public ResponseOptions<Response> executeWithQueryParamsAndBody(Map<String, Object> queryPath,Object body, Map<String, String> headerParams) {
        LOGGER.info("query paths are :", queryPath);
        builder.addQueryParams(queryPath);
        builder.setBody(body);
        builder.addHeaders(headerParams);
        return executeAPI();
    }

}

package requests;
/**************************
 *  (C) L Somni            *
 ***************************/

import Utils.Helper;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class GET_Request {
    RequestSpecification httpRequest = RestAssured.given();
    Response response;

    public void chooseEndPoint() {
        httpRequest.baseUri(Helper.URL);
    }

    public void chooseEndPoint(final String sEndPoint) {
        switch (sEndPoint.toLowerCase()){
            case "cities" : httpRequest.baseUri(Helper.URL + Helper.CITIES);
                break;
            case "citys" : httpRequest.baseUri(Helper.URL + Helper.CITIES);
                break;
            case "attractions" : httpRequest.baseUri(Helper.URL + Helper.ATTRACTIONS);
                break;
            default: throw new Helper.ConfigurationException("ERROR: Invalid Endpoint supplied");
        }
    }

    public void chooseCityQueryString(final String city) {
        switch (city.toLowerCase()){
            case "new york" : httpRequest.queryParam("cityId","1");
                break;
            case "london" : httpRequest.queryParam("cityId","2");
                break;
            case "los angeles" : httpRequest.queryParam("cityId","3");
                break;
            case "paris" : httpRequest.queryParam("cityId","4");
                break;
            default: throw new Helper.ConfigurationException("ERROR: Invalid queryparameter supplied");
        }
    }

    public void chooseAttractionTypeQueryString(final String attraction) {
        switch (attraction.toLowerCase()){
            case "museum" : httpRequest.queryParam("type","MUSEUM");
                break;
            case "tour" : httpRequest.queryParam("type","TOUR");
                break;
            case "observatory" : httpRequest.queryParam("type","OBSERVATORY");
                break;
            case "unknown" : httpRequest.queryParam("type","UNKNOWN");
                break;
            default: throw new Helper.ConfigurationException("ERROR: Invalid query parameter");
        }
    }



    public Response getRequest() {
        Response request = httpRequest.request(Method.GET);
        return request;
    }

}

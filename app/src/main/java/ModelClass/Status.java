package ModelClass;

public class Status {
    private String name,url,responseCode,responseTime,classStatus;
        public Status(String name, String url, String responseCode, String responseTime, String classStatus)
        {
            this.name= name;
            this.url= url;
            this.responseCode=responseCode;
            this.responseTime=responseTime;
            this.classStatus=classStatus;
}

    public String getClassStatus() {
        return classStatus;
    }

    public String getName() {
        return name;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public String getResponseTime() {
        return responseTime;
    }

    public String getUrl() {
        return url;
    }
}

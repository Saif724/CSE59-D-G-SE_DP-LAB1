import java.lang.module.ModuleDescriptor.Builder;

class HttpRequest {
    private final String url;
    private final String method;
    private final String headers;
    private final String body;

    private HttpRequest(Builder builder){
        url=builder.url;
        method=builder.method;
        headers=builder.headers;
        body=builder.body;
    }

    public void send(){
         System.out.println("Sending " + method + " request to " + url);
    }

    public static class Builder {
        private String url;
        private String method="GET";
        private String headers="";
        private String body="";

        public Builder(String url){
            this.url=url;
        }

        public Builder setMethod(String method){
            this.method=method;
            return this;
        }

        public Builder setHeaders(String headers){
            this.headers += headers + ";";
            return this;
        }

        public Builder setBody(String body){
            this.body=body;
            return this;
        }

        public HttpRequest build(){
            return new HttpRequest(this);
        }
    }
}


public class HttpRequestBuilder {
    public static void main(String[] args){
        HttpRequest hp= new HttpRequest.Builder("https://api.example.com/data")
                        .setMethod("POST")
                        .setHeaders("Auth: Bearer token123")
                        .setBody("{'user_id': '404'}")
                        .build();
        
        hp.send();
    }
}
package org.example.result;

public class RedirectResult extends Result {

    public RedirectResult(String resource) {
        super(resource);
    }

    @Override
    public ResponseType getResponseType() {
        return ResponseType.REDIRECT;
    }
}

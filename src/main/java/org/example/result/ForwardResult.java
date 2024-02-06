package org.example.result;

public class ForwardResult extends Result {

    public ForwardResult(String resource) {
        super(resource);
    }

    @Override
    public ResponseType getResponseType() {
        return ResponseType.FORWARD;
    }
}

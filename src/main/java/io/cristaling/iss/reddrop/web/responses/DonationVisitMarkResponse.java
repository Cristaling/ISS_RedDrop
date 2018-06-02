package io.cristaling.iss.reddrop.web.responses;

public class DonationVisitMarkResponse {

    boolean successful;

    public DonationVisitMarkResponse(boolean successful) {
        this.successful = successful;
    }

    public DonationVisitMarkResponse() {
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }
}

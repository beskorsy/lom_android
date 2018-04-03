package lom.lom_android.service;

public class ResultModel {

    public String phone = "";
    public Float discount = 0f;
    public LocalityModel locality;
    public String address = "";
    public ScrapyardModel scrapyard;
    public int distantce = 0;
    public TransportModel transport;
    public Float cost = 0f;
    public Float tonn = 0f;
    public String data = "";
    public String comment = "";
    public Boolean loader = false;
    public Boolean cutter = false;
    public Boolean calculatedInPlace = false;

    public Boolean phoneValid = false;

    public Boolean isRequestSuccsess = false;
    public Boolean isRequestError = true;



    public Float getScrapyardPrice() {
        if (scrapyard != null) {
            return Float.parseFloat(scrapyard.getPrice());
        }
        return 0f;
    }

//    public Float getFullPrice() {
//        float discount = this.discount / 100 + 1;
//        return Math.max(getScrapyardPrice() * tonn * discount - cost, 0);
//    }


    public Boolean isContactsValid() {
        return phoneValid;
    }

    public Boolean isOrderValid () {
        return phoneValid;
    }
}

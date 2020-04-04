package data.models;

public class NotificationMark {

    private String publicityId;
    private String notificationId;
    private Boolean checked;

    protected NotificationMark() {}

    public String getPublicityId() {
        return publicityId;
    }

    public void setPublicityId(String publicityId) {
        this.publicityId = publicityId;
    }

    public String getNotificationId() {
        return this.notificationId;
    }

    public void setNotificationId(String notificationId) {
        this.notificationId = notificationId;
    }

    public Boolean getChecked() {
        return this.checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }
}

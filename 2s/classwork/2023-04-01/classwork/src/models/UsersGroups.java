package models;

public class UsersGroups extends AbstractModel {
    private int userId;
    private int groupId;

    public UsersGroups(String[] data) {
        this.id = Integer.parseInt(data[0]);
        this.userId = Integer.parseInt(data[1]);
        this.groupId = Integer.parseInt(data[2]);
    }

    public int getUserId() {
        return userId;
    }

    public int getGroupId() {
        return groupId;
    }
}

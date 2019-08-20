package co.com.ceiba.mobile.pruebadeingreso.repository.persistence.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "post")
public class PostEntity {

    @ColumnInfo(name = "userId")
    private String userId;
    @PrimaryKey
    @NonNull
    private String id;
    private String title;
    private String body;

    public PostEntity() {}

    public PostEntity(String uid, String id, String title, String body) {
        this.userId = uid;
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String postId) {
        this.id = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String postTitle) {
        this.title = postTitle;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String postBody) {
        this.body = postBody;
    }
}


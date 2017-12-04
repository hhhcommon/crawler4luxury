package model;

import annotations.Document;
import annotations.Field;
import annotations.ID;
import data.FieldIndex;
import data.FieldType;

/**
 * Created by yang on 2017/7/12.
 */
@Document(index = "mmpic", type = "pic", replicas = 1, shards = 5, settings = "Setting.json")
public class SinaEntity {
    @ID
    private Integer id;

    @Field(type = FieldType.keyword, fields = true)
    private String title;

    @Field(type = FieldType.keyword, fields = true)
    private String pic;

    @Field(type = FieldType.keyword, fields = true)
    private String fileName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

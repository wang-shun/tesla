package cn.acewill.pos.next.model.cache;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ModelContainer;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.structure.BaseModel;

import cn.acewill.pos.next.factory.AppDatabase;
import cn.acewill.pos.next.model.WorkShift;

/**
 * Created by Acewill on 2016/12/1.
 */
@com.raizlabs.android.dbflow.annotation.Table(name="cached_workshift",database =AppDatabase.class)
@ModelContainer
public class CachedWorkshift extends BaseModel {
    public static Gson gson = new Gson();

    public CachedWorkshift() {
    }

    public CachedWorkshift(WorkShift workShift) {
        this.id = workShift.getId();
        this.jsonObject = gson.toJson(workShift);
    }

    @Column
    @SerializedName( "id" )
    @PrimaryKey
    private long id; //本地id

    @Column
    @SerializedName( "json_object" )
    private String jsonObject; //菜品对象对应的json字符串

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(String jsonObject) {
        this.jsonObject = jsonObject;
    }

    public WorkShift toWorkShift() {
        WorkShift order = gson.fromJson(this.jsonObject, WorkShift.class);
        order.setId(this.id);
        return order;
    }
}

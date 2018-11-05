package cn.acewill.pos.next.model.cache;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ModelContainer;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.structure.BaseModel;

import cn.acewill.pos.next.factory.AppDatabase;
import cn.acewill.pos.next.model.KitchenStall;

/**
 * Created by Acewill on 2016/12/1.
 */
@com.raizlabs.android.dbflow.annotation.Table(name="cached_kitchenstall_list",database =AppDatabase.class)
@ModelContainer
public class CachedKitchenStallList extends BaseModel {
    public static Gson gson = new Gson();

    public CachedKitchenStallList() {
    }

    public CachedKitchenStallList(KitchenStall payment) {
        this.id = payment.getStallsid();
        this.jsonObject = gson.toJson(payment);
    }

    @Column
    @PrimaryKey
    @SerializedName( "id" )
    private long id; //支付方式的在服务器上的id

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

    public KitchenStall toKitchenStall() {
        return gson.fromJson(this.jsonObject, KitchenStall.class);
    }
}

package cn.acewill.pos.next.service.retrofit.response;

import java.util.List;

import cn.acewill.pos.next.model.OtherFile;

/**
 * Created by Administrator on 2016/9/3.
 */
public class OtherFileResponse extends PosResponse{

    private List<OtherFile> files;

    public List<OtherFile> getFiles() {
        return files;
    }

    public void setFiles(List<OtherFile> files) {
        this.files = files;
    }
}

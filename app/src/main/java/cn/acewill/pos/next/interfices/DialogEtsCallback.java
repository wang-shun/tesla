package cn.acewill.pos.next.interfices;

/**
 * Created by DHH on 2016/8/23.
 */
public interface DialogEtsCallback<T>  {
    /**
     * 确认  dialogStyle： Constant.DialogStyle.ADD_PRINTER=添加  ， Constant.DialogStyle.MODIFY_PRINTER=修改
     */
    public void onConfirm(String sth);
    /**
     * 取消
     */
    public void onCancle(String sth);
}

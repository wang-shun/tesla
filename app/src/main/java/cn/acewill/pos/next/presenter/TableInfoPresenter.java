package cn.acewill.pos.next.presenter;

import java.util.List;

import cn.acewill.pos.next.config.MyApplication;
import cn.acewill.pos.next.exception.PosServiceException;
import cn.acewill.pos.next.model.order.Order;
import cn.acewill.pos.next.service.ResultCallback;
import cn.acewill.pos.next.service.SystemService;
import cn.acewill.pos.next.service.TableService;
import cn.acewill.pos.next.ui.DialogView;

/**
 * 登陆管理
 * Created by DHH on 2016/6/12.
 */
public class TableInfoPresenter {
    private DialogView dialogView;
    private MyApplication myApplication;
    private SystemService systemService = null;

    public TableInfoPresenter(DialogView loginView) {
        this.dialogView = loginView;
        myApplication = MyApplication.getInstance();
    }

    public <T> void getTableInfoByIdWork(final int type, final long tableId, final long oldTableId) {
        dialogView.showDialog();
        try {
            TableService tableService = TableService.getInstance();
            tableService.ordersTable(tableId, new ResultCallback<List<Order>>() {
                @Override
                public void onResult(List<Order> result) {
                    dialogView.dissDialog();
                    dialogView.callBackData(result);
                }

                @Override
                public void onError(PosServiceException e) {
                    dialogView.dissDialog();
                    dialogView.showError(e);
                }
            });
        } catch (PosServiceException e) {
            e.printStackTrace();
            dialogView.dissDialog();
            dialogView.showError(e);
        }

    }


    //    public <T> void getLoginWork(final String userName,final String pwd) {
    //        dialogView.showDialog();
    //        systemService = null;
    //        try {
    //            systemService = SystemService.getInstance();
    //        } catch (PosServiceException e) {
    //            e.printStackTrace();
    //            return;
    //        }
    //        systemService.terminalLogin(new ResultCallback() {
    //            @Override
    //            public void onResult(Object result) {
    //                int code = (Integer)result;
    //                if(code ==0)
    //                {
    //                    systemService.login(userName, pwd, new ResultCallback<User>() {
    //                        @Override
    //                        public void onResult(User user) {
    //                            dialogView.dissDialog();
    //                            dialogView.callBackData(user);
    //                        }
    //
    //                        @Override
    //                        public void onError(PosServiceException e) {
    //                            dialogView.dissDialog();
    //                            dialogView.showError(e);
    //                        }
    //                    });
    //                }
    //            }
    //
    //            @Override
    //            public void onError(PosServiceException e) {
    //                dialogView.dissDialog();
    //                dialogView.showError(e);
    //            }
    //        });
    //
    //    }
}

package com.lichunliang.huoyunwuliu.utils;

/**
 * ━━━━━━━━━神兽出没━━━━━━━━━
 * <p>
 * 　　　　　　　　┏┓　　　┏┓+ +
 * 　　　　　　　┏┛┻━━━┛┻┓ + +
 * 　　　　　　　┃　　　　　　　┃
 * 　　　　　　　┃　　　━　　　┃ ++ + + +
 * 　　　　　　 ████━████ ┃+
 * 　　　　　　　┃　　　　　　　┃ +
 * 　　　　　　　┃　　　┻　　　┃
 * 　　　　　　　┃　　　　　　　┃ + +
 * 　　　　　　　┗━┓　　　┏━┛
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃ + + + +
 * 　　　　　　　　　┃　　　┃　　　　Code is far away from bug with the animal protecting
 * 　　　　　　　　　┃　　　┃ + 　　　　神兽保佑,代码无bug
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃　　+
 * 　　　　　　　　　┃　 　　┗━━━┓ + +
 * 　　　　　　　　　┃ 　　　　　　　┣┓
 * 　　　　　　　　　┃ 　　　　　　　┏┛
 * 　　　　　　　　　┗┓┓┏━┳┓┏┛ + + + +
 * 　　　　　　　　　　┃┫┫　┃┫┫
 * 　　　　　　　　　　┗┻┛　┗┻┛+ + + +
 * <p>
 * ━━━━━━━━━感觉萌萌哒━━━━━━━━━
 */
public class ResultData {

    private int code;
    private Object data;
    private String msg;

    /**
     * 成功返回
     * @param data
     * @return
     */
    public static ResultData success(Object data){
        ResultData resultData = new ResultData();
        resultData.setCode(CommonConstant.SUCCESS);
        resultData.setData(data);
        return resultData;
    }
    /**
     * 成功返回
     * @return
     */
    public static ResultData success(){
        ResultData resultData = new ResultData();
        resultData.setCode(CommonConstant.SUCCESS);
        return resultData;
    }

    /**
     * 失败返回
     * @param msg
     * @return
     */
    public static ResultData error(String msg){
        ResultData resultData = new ResultData();
        resultData.setCode(CommonConstant.ERROR);
        resultData.setMsg(msg);
        return resultData;
    }
    /**
     * 失败返回
     * @return
     */
    public static ResultData error(){
        ResultData resultData = new ResultData();
        resultData.setCode(CommonConstant.ERROR);
        return resultData;
    }
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

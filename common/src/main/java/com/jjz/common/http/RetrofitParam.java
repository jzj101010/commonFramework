package com.jjz.common.http;

import com.blankj.utilcode.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Weir on 2017/7/4.
 */

public class RetrofitParam {


    private List<Params> mParamsList = new ArrayList<>();

    public Builder newBuilder() {
        return new Builder();
    }

    public class Builder {

        /**
         * 设置列表分页参数
         *
         * @param page 页码
         * @return
         */
        public Builder addPage(int page) {
            Params param = new Params();
            param.key = "page";
            param.value = String.valueOf(page);
            mParamsList.add(param);
            return this;
        }

        /**
         * 设置列表分页参数
         *
         * @param size 页数据长度
         * @return
         */
        public Builder addSize(int size) {
            Params param = new Params();
            param.key = "size";
            param.value = String.valueOf(size);
            mParamsList.add(param);
            return this;
        }

        /**
         * 用户id
         * 对应 {userId} 字段
         *
         * @return
         */
//        public Builder addUserId() {
//            if (AccountRepository.getUser() == null) {
//                return this;
//            }
//            Params param = new Params();
//            param.key = "userId";
//            param.value = String.valueOf(AccountRepository.getUser().id);
//            mParamsList.add(param);
//            return this;
//        }

        /**
         * 添加参数
         *
         * @param key
         * @param value
         * @return
         */
        public Builder addParam(String key, String value) {
            if (StringUtils.isEmpty(value)) {
                return this;
            }
            Params param = new Params();
            param.key = key;
            param.value = value;
            mParamsList.add(param);
            return this;
        }

        public Builder addParam(String key, boolean value) {
            Params param = new Params();
            param.key = key;
            param.value = String.valueOf(value);
            mParamsList.add(param);
            return this;
        }

        /**
         * 添加参数
         *
         * @param key
         * @param value
         * @return
         */
        public Builder addParam(String key, int value) {
            if (value == -1) {
                return this;
            }
            Params param = new Params();
            param.key = key;
            param.value = String.valueOf(value);
            mParamsList.add(param);
            return this;
        }

        /**
         * 添加参数
         *
         * @param key
         * @param value
         * @return
         */
        public Builder addParam(String key, float value) {
            if (value == -1) {
                return this;
            }
            Params param = new Params();
            param.key = key;
            param.value = String.valueOf(value);
            mParamsList.add(param);
            return this;
        }

        /**
         * 添加参数
         *
         * @param key
         * @param value
         * @return
         */
        public Builder addParam(String key, double value) {
            if (value == -1) {
                return this;
            }
            Params param = new Params();
            param.key = key;
            param.value = String.valueOf(value);
            mParamsList.add(param);
            return this;
        }

        /**
         * 添加是否附带返回额外bean
         *
         * @param value
         * @return
         */
        public Builder addProjection(String value) {
            if (StringUtils.isEmpty(value)) {
                return this;
            }
            Params param = new Params();
            param.key = "projection";
            param.value = value;
            mParamsList.add(param);
            return this;
        }

        /**
         * 添加位置
         *
         * @param value 所在位置
         * @return
         */
        public Builder addPosition(String value) {
            if (StringUtils.isEmpty(value)) {
                return this;
            }
            Params param = new Params();
            param.key = "position";
            param.value = value;
            mParamsList.add(param);
            return this;
        }

        /**
         * 添加状态
         *
         * @param value 启用状态
         * @return
         */
        public Builder addStatus(boolean value) {
            Params param = new Params();
            param.key = "status";
            param.value = String.valueOf(value);
            mParamsList.add(param);
            return this;
        }

        public Map<String, String> build() {
            return getMap();
        }

    }

    private class Params {
        String key;
        String value;
    }

    private Map<String, String> getMap() {
        Map<String, String> map = new HashMap<>();
        for (Params params : mParamsList) {
            map.put(params.key, params.value);
        }
        return map;
    }
}

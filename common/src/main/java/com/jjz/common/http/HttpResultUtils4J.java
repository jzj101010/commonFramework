package com.jjz.common.http;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.jjz.common.http.result.HttpResult;
import com.jjz.common.http.result.ListData;

import java.util.ArrayList;

import io.reactivex.functions.Function;



public class HttpResultUtils4J<T> {

    public Function<HttpResult, T> HandleResult(final Class<T> tClass) {
        return new Function<HttpResult, T>() {
            @Override
            public T apply(HttpResult it) throws Exception {
                if (it.isSuccess()) {
                    if (it.getData() == null || it.getData().isJsonNull()) {
                        /*data：null */
                        return tClass.newInstance();
                    }
                    if (it.getData().isJsonPrimitive()) {
                        /*data：false | “string” | 66.4  */
                        return new Gson().fromJson(String.format("\"%s\"", it.getData().getAsString()), tClass);
                    }
                    return new Gson().fromJson(it.getData(), tClass);
                }  else {
                    throw new Exception(it.getMessage());
                }
            }
        };
    }

    public Function<HttpResult, ListData<T>> HandleResultList(final Class<T> tClass) {
        return new Function<HttpResult, ListData<T>>() {
            @Override
            public ListData<T> apply(HttpResult it) throws Exception {
                if (it.isSuccess() && !it.getData().isJsonNull()) {
                    ListData<T> listData = new ListData<T>();
                    JsonObject data = (JsonObject) it.getData();
                    if (data.has("total")) {
                        listData.setTotalElements(data.get("total").getAsInt());
                    }

                    if (data.has("pages")) {
                        listData.setTotalPages(data.getAsJsonPrimitive("pages").getAsInt());
                    }
                    if (data.has("current")) {
                        listData.setLast(data.getAsJsonPrimitive("current").getAsInt()==listData.getTotalPages());
                        listData.setFirst(data.getAsJsonPrimitive("current").getAsInt()==0);
                        listData.setNumber(data.getAsJsonPrimitive("current").getAsInt());
                    }

                    if (data.has("size")) {
                        listData.setSize(data.getAsJsonPrimitive("size").getAsInt());
                    }

                    JsonArray jsonArray = data.getAsJsonArray("records");
                    if (listData.getContent() == null) {
                        listData.setContent(new ArrayList<T>());
                    }
                    if (jsonArray != null) {
                        for (int i = 0; i < jsonArray.size(); i++) {
                            listData.getContent().add(new Gson().fromJson(jsonArray.get(i), tClass));
                        }
                    }
                    return listData;
                }else {
                    throw new Exception(it.getMessage());
                }
            }
        };
    }



}

/*
 * Copyright (C) 2017 zhouyou(478319399@qq.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mecby.base.net;

import com.mecby.base.base.BaseApiBean;
import com.zhouyou.http.callback.CallBack;
import com.zhouyou.http.callback.CallBackProxy;
import com.zhouyou.http.callback.CallClazzProxy;
import com.zhouyou.http.request.GetRequest;

import java.lang.reflect.Type;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;


/**Created by jerry on 2017/9/20 0020.
 * <p>描述：扩展GetResult请求，解决自定义ApiResult重复写代理的问题</p>
 *
 */
public class CustomGetRequest extends GetRequest {
    public CustomGetRequest(String url) {
        super(url);
    }

    /**
     *
     * @param type
     * @param <T>
     * @return
     */
    @Override
    public <T> Observable<T> execute(Type type) {
        return super.execute(new CallClazzProxy<BaseApiBean<T>, T>(type) {
        });
    }

    @Override
    public <T> Observable<T> execute(Class<T> clazz) {
        return super.execute(new CallClazzProxy<BaseApiBean<T>, T>(clazz) {
        });
    }

    @Override
    public <T> Disposable execute(CallBack<T> callBack) {
        return super.execute(new CallBackProxy<BaseApiBean<T>, T>(callBack) {
        });
    }
}

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

import com.mecby.base.utils.AppUtils;
import com.zhouyou.http.interceptor.BaseDynamicInterceptor;

import java.util.HashMap;

/**
 * <p>描述：对参数进行签名、添加token、时间戳处理的拦截器</p>
 * 主要功能说明：<br>
 * 因为参数签名没办法统一，签名的规则不一样，签名加密的方式也不同有MD5、BASE64等等，只提供自己能够扩展的能力。<br>
 * 作者： zhouyou<br>
 * 日期： 2017/5/4 15:21 <br>
 * 版本： v1.0<br>
 */
public class CustomSignInterceptor extends BaseDynamicInterceptor<CustomSignInterceptor> {
    @Override
    public HashMap<String, String> dynamic(HashMap<String, String> dynamicMap) {
        //HttpLog.i("dynamicMap:" + dynamicMap.toString());
        return NativeHelper.getInstance().generateSignParams(dynamicMap, AppUtils.getHeaderMap().headersMap);//dynamicMap:是原有的全局参数+局部参数+新增的动态参数
    }
}

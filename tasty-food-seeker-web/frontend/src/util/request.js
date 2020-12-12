import Vue from 'vue'
import axios from 'axios'
import {getMessageByStatus} from "@/exception";

const service = axios.create({
    baseURL: process.env.VUE_APP_BASE_API,   // url = base url + request url
    withCredentials: false,                   // send cookies when cross-domain requests
    timeout: 10000,                           // request timeout
})

/**
 * [요청] 이전 인터셉터 설정
 */
service.interceptors.request.use(
)

/**
 * [응답] 이전 인터셉터 설정
 */
service.interceptors.response.use(
    response => {
        return response.data;
    },
    error => {
        // error.response 로 접근해야함.
        const message = getMessageByStatus(error.response.data.httpStatus);
        const vm = new Vue()
        vm.$bvToast.toast(message, {
            title: 'tasty-food-seeker error!!',
            autoHideDelay: 1000,
            variant: 'danger'
        });
        return Promise.reject(error);
    }
)

export default service
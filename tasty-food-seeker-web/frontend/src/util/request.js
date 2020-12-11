import axios from 'axios'

const service = axios.create({
    baseURL: process.env.VUE_APP_BASE_API,   // url = base url + request url
    withCredentials: false,                   // send cookies when cross-domain requests
    timeout: 10000,                           // request timeout
})

export default service
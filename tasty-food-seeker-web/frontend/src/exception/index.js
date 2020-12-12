function getMessageByStatus(status) {
    if(status > 1500 && status < 1600) {
        return "서버에서 문제가 발생하였습니다."
    }

    return "알 수 없는 문제가 발생하였습니다."
}

export {
    getMessageByStatus
}
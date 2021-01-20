import request from '../../utils/request'
export default {
  login (user) {
    return request({
      url: '/api/login',
      method: 'post',
      data: user
    })
  }
}

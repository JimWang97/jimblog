import request from '../../utils/request'
export default {
  getTags () {
    return request({
      url: '/api/getTags',
      method: 'get'
    })
  }
}

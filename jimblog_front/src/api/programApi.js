import request from '../../utils/request'
export default {
  getPrograms (page, limit) {
    return request({
      url: `/api/programs/${page}/${limit}`,
      method: 'get'
    })
  },
  adminGetPrograms (page, limit) {
    return request({
      url: `/api/admin/programs/${page}/${limit}`,
      method: 'get'
    })
  },
  adminAddProgram (program) {
    return request({
      url: '/api/admin/addProgram',
      method: 'post',
      data: program
    })
  },
  adminDeleteProgram (id) {
    return request({
      url: `/api/admin/program/${id}`,
      method: 'delete'
    })
  },
  adminGetProgram (id) {
    return request({
      url: `/api/admin/program/${id}`,
      method: 'get'
    })
  }
}

import request from '../../utils/request'
export default {
  adminGetBlogs (page, limit) {
    return request({
      url: `/api/admin/blogs/${page}/${limit}`,
      method: 'get'
    })
  },
  getStat () {
    return request({
      url: '/api/admin/getStat',
      method: 'get'
    })
  },
  adminAddBlog (blog) {
    return request({
      url: '/api/admin/addBlog',
      method: 'post',
      data: blog
    })
  },
  adminDeleteBlog (id) {
    return request({
      url: `/api/admin/blog/${id}`,
      method: 'delete'
    })
  }
}

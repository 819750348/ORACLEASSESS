import {axios} from '@/utils/request'

export function initTestManagement(parameter) {
  return axios({
    url: '/api/testManagement/queryPageList',
    method: 'get',
    params: parameter
  })
}
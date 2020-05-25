import {axios} from '@/utils/request'

export function initStaffingManagement(parameter) {
  return axios({
    url: '/api/staffingManagement/queryPageList',
    method: 'get',
    params: parameter
  })
}
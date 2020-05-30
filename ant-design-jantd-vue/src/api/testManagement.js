import {axios} from '@/utils/request'

export function initTestManagement(parameter) {
  return axios({
    url: '/api/testManagement/queryPageList',
    method: 'get',
    params: parameter
  })
}


export function editTestManagement(parameter) {
  return axios({
    url: '/api/testManagement/editTestManagement',
    method: 'get',
    params: parameter
  })
}


export function deleteTestManagement(parameter) {
  return axios({
    url: '/api/testManagement/deleteTestManagement',
    method: 'get',
    params: parameter
  })
}
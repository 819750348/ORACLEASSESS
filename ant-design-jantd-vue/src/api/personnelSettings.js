import {axios} from '@/utils/request'

export function getInitData(parameter) {
  return axios({
    url: '/api/personnelSettings/queryPageList',
    method: 'get',
    params: parameter
  })
}

export function addPersonnel(parameter) {
  return axios({
    url: '/api/personnelSettings/addPersonnel',
    method: 'get',
    params: parameter
  })
}
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

export function searchPersonnel(parameter) {
  return axios({
    url: '/api/personnelSettings/searchPersonnel',
    method: 'get',
    params: parameter
  })
}

export function deletePersonnel(parameter) {
  return axios({
    url: '/api/personnelSettings/deletePersonnel',
    method: 'get',
    params: parameter
  })
}

export function editPersonnel(parameter) {
  return axios({
    url: '/api/personnelSettings/editPersonnel',
    method: 'get',
    params: parameter
  })
}
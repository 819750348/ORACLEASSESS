import {axios} from '@/utils/request'

export function getInitData(parameter) {
  return axios({
    url: 'http://192.168.5.253:8080/jantd-boot/teacher/problem/queryPageList',
    method: 'get',
    params: parameter
  })
}

export function deleteRow(parameter) {
  return axios({
    url: 'http://192.168.5.253:8080/jantd-boot/teacher/problem/delete',
    method: 'get',
    params: parameter
  })
}

export function deleteAll(parameter) {
  return axios({
    url: 'http://192.168.5.253:8080/jantd-boot/teacher/problem/deleteBatch',
    method: 'get',
    params: parameter
  })
}
export function seeRightkey(parameter) {
  return axios({
    url: 'http://192.168.5.253:8080/jantd-boot/teacher/problem/detail',
    method: 'get',
    params: parameter
  })
}
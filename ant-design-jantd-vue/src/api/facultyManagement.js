import {axios} from '@/utils/request'

export function getStaffList(parameter) {
  return axios({
    url: 'http://192.168.43.160:8080/jantd-boot/teacher/staff/staffList',
    method: 'get',
    params: parameter
  })
}

export function deleteRow(parameter) {
  return axios({
    url: 'http://192.168.43.160:8080/jantd-boot/teacher/staff/delete',
    method: 'get',
    params: parameter
  })
}

export function deleteAll(parameter) {
  return axios({
    url: 'http:192.168.43.160:8080/jantd-boot/teacher/staff/deleteBatch',
    method: 'get',
    params: parameter
  })
}

export function editRow(parameter) {
  return axios({
    url: 'http:192.168.43.160:8080/jantd-boot/teacher/staff/edit',
    method: 'get',
    params: parameter
  })
}
